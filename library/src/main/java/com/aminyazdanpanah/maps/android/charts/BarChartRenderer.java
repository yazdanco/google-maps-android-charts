/*
 * Copyright 2019 Amin Yazdanpanah<http://www.aminyazdanpanah.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.aminyazdanpanah.maps.android.charts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.aminyazdanpanah.maps.android.charts.model.CMarker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;

public class BarChartRenderer extends ChartRenderer {
    private final ImageView mClusterImageView;

    public BarChartRenderer(Context context, GoogleMap map, ClusterManager<CMarker> clusterManager) {
        super(context, map, clusterManager);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View barChart = inflater.inflate(R.layout.barchart_cluster, null);
        mClusterIconGenerator.setContentView(barChart);
        mClusterImageView = barChart.findViewById(R.id.image);
    }

    @Override
    protected void onBeforeClusterRendered(Cluster<CMarker> cluster, MarkerOptions markerOptions) {
        Drawable backgroundColor;
        backgroundColor = new ColorDrawable(Color.WHITE);
        mClusterIconGenerator.setBackground(backgroundColor);
        BarChart Chart = new BarChart(values(cluster), COLORS);
        mClusterImageView.setImageDrawable(Chart);
        Bitmap icon = mClusterIconGenerator.makeIcon(cluster.getSize() + "");
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).anchor(.5f, .5f);
    }
}
