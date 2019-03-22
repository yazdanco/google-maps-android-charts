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
import android.graphics.Color;

import com.aminyazdanpanah.maps.android.charts.model.CMarker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.Arrays;

public class ChartRenderer extends DefaultClusterRenderer<CMarker> {
    final IconGenerator mClusterIconGenerator;
    int[] COLORS = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    private String[] names;

    ChartRenderer(Context context, GoogleMap map, ClusterManager<CMarker> clusterManager) {
        super(context, map, clusterManager);
        mClusterIconGenerator = new IconGenerator(context);
    }

    @Override
    protected void onBeforeClusterItemRendered(CMarker CMarker, MarkerOptions markerOptions) {
        markerOptions.icon(BitmapDescriptorFactory.fromResource(CMarker.getMarker())).title(CMarker.getTitle());
    }

    public void colors(int[] colors) {
        COLORS = Arrays.copyOf(colors, colors.length);
    }

    public void names(String[] c_names) {
        names = Arrays.copyOf(c_names, c_names.length);
    }

    float[] values(Cluster<CMarker> cluster){
        float[] values = new float[names.length];
        Arrays.fill(values, 0);

        int key;

        for (CMarker p : cluster.getItems()) {
            key = Arrays.asList(names).indexOf(p.getName());
            values[key]++;
        }

        return values;
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        return cluster.getSize() > 1;
    }
}
