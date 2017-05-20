/*
 * Copyright by Amin Yazdanpanah 2016
 * http://www.aminyazdanpanah.com
 */

package com.aminyazdanpanah.piechartclustermarker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import com.aminyazdanpanah.piechartclustermarker.model.Asset;
import com.aminyazdanpanah.piechart.PieChart;
import com.aminyazdanpanah.piechart.GetViewPieChart;

import java.lang.reflect.Field;
import java.util.Random;


public class MainActivity extends BaseDemoActivity implements ClusterManager.OnClusterClickListener<Asset>, ClusterManager.OnClusterInfoWindowClickListener<Asset>, ClusterManager.OnClusterItemClickListener<Asset>, ClusterManager.OnClusterItemInfoWindowClickListener<Asset> {
    private ClusterManager<Asset> mClusterManager;
    private Random mRandom = new Random(1984);
    private Random r = new Random();
    private String[] names = {"Emma", "Noah", "Olivia", "Liam", "Sophia", "Mason", "Ava", "Jacob", "Isabella", "William"};
    private String[] status = {"active", "semiactive", "nonactive", "inactive"};
    int active;
    int semiActive;
    int inactive;
    int nonactive;
    float values[];
    GetViewPieChart viewPieChart;
    private static final Drawable TRANSPARENT_DRAWABLE = new ColorDrawable(Color.TRANSPARENT);

    /**
     * Draws 3D pieChart inside markers (using IconGenerator).
     */
    private class AssetRenderer extends DefaultClusterRenderer<Asset> {
        private final IconGenerator mClusterIconGenerator = new IconGenerator(getApplicationContext());
        private final ImageView mClusterImageView;

        public AssetRenderer() {
            super(getApplicationContext(), getMap(), mClusterManager);

            viewPieChart = new GetViewPieChart(MainActivity.this);
            mClusterIconGenerator.setContentView(viewPieChart);
            mClusterImageView = (ImageView) viewPieChart.findViewById(R.id.image);
        }

        @Override
        protected void onBeforeClusterItemRendered(Asset Asset, MarkerOptions markerOptions) {
            // Draw a single Asset.
            // Set the info window to show their name.
            markerOptions.icon(BitmapDescriptorFactory.fromResource(Asset.marker)).title(Asset.name);
        }

        @Override
        protected void onBeforeClusterRendered(Cluster<Asset> cluster, MarkerOptions markerOptions) {
            //Draw 3D PieChart on map
            active = 0;
            semiActive = 0;
            inactive = 0;
            nonactive = 0;
            mClusterIconGenerator.setBackground(TRANSPARENT_DRAWABLE);
            for (Asset p : cluster.getItems()) {
                if (p.marker == 2130837587) {
                    active++;
                } else if (p.marker == 2130837625) {
                    semiActive++;
                } else if (p.marker == 2130837613) {
                    nonactive++;
                } else {
                    inactive++;
                }
            }
            values = new float[]{active, semiActive, nonactive, inactive};
            PieChart pieChart = new PieChart(values);
            mClusterImageView.setImageDrawable(pieChart);
            Bitmap icon = mClusterIconGenerator.makeIcon(cluster.getSize() + "");
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).anchor(.5f, .5f);
        }

        @Override
        protected boolean shouldRenderAsCluster(Cluster cluster) {
            // Always render clusters.
            return cluster.getSize() > 1;
        }
    }

    @Override
    public boolean onClusterClick(Cluster<Asset> cluster) {
        // Show a toast with some info when the cluster is clicked.
        String firstName = cluster.getItems().iterator().next().name;
        Toast.makeText(this, cluster.getSize() + " (including " + firstName + ")", Toast.LENGTH_SHORT).show();

        // Zoom in the cluster. Need to create LatLngBounds and including all the cluster items
        // inside of bounds, then animate to center of the bounds.

        // Create the builder to collect all essential cluster items for the bounds.
        LatLngBounds.Builder builder = LatLngBounds.builder();
        for (ClusterItem item : cluster.getItems()) {
            builder.include(item.getPosition());
        }
        // Get the LatLngBounds
        final LatLngBounds bounds = builder.build();

        // Animate camera to the bounds
        try {
            getMap().animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<Asset> cluster) {
        // Does nothing.
    }

    @Override
    public boolean onClusterItemClick(Asset item) {
        // Does nothing.
        return false;
    }

    @Override
    public void onClusterItemInfoWindowClick(Asset item) {
        // Does nothing.
    }

    @Override
    protected void startDemo() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32.988612, 53.459411), 4.5f));

        mClusterManager = new ClusterManager<>(this, getMap());
        mClusterManager.setRenderer(new AssetRenderer());
        getMap().setOnCameraIdleListener(mClusterManager);
        getMap().setOnMarkerClickListener(mClusterManager);
        getMap().setOnInfoWindowClickListener(mClusterManager);
        mClusterManager.setOnClusterClickListener(this);
        mClusterManager.setOnClusterInfoWindowClickListener(this);
        mClusterManager.setOnClusterItemClickListener(this);
        mClusterManager.setOnClusterItemInfoWindowClickListener(this);

        addItems();
        mClusterManager.cluster();
    }

    private void addItems() {
        for (int i = 0; i < 300; i++) {
            mClusterManager.addItem(new Asset(position(), randomStatus(), randomImage()));
        }
    }

    private int randomImage() {
        int min = 0;
        int max = 4;
        return getDrawableId(status[r.nextInt(max - min) + min]);
    }

    private LatLng position() {
        return new LatLng(random(30.325877, 36.615905), random(60.648976, 47.465382));
    }

    private double random(double min, double max) {
        return mRandom.nextDouble() * (max - min) + min;
    }

    private String randomStatus() {
        int min = 0;
        int max = 9;
        return "Driver: " + names[r.nextInt(max - min) + min];
    }

    private int getDrawableId(String name) {
        try {
            Field field = R.drawable.class.getField(name);
            return field.getInt(null);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return -1;
    }


    public void info(View v) {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)

                .setTitle("About")
                .setMessage("The app was created by Amin Yazdanpanah \n Please share your idea about this app to my website \n https://www.aminyazdanpanah.com")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        myQuittingDialogBox.show();
    }

}

