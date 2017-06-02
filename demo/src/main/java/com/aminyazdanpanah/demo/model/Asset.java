/*
 * Copyright by Amin Yazdanpanah 2016
 * http://www.aminyazdanpanah.com
 */

package com.aminyazdanpanah.demo.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Asset implements ClusterItem {
    public final String name;
    public final int marker;
    private final LatLng mPosition;

    public Asset(LatLng position, String name, int pictureResource) {
        this.name = name;
        marker = pictureResource;
        mPosition = position;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
