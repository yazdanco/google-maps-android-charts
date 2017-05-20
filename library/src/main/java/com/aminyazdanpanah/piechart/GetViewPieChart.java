/*
 * Copyright by Amin Yazdanpanah 2016
 * http://www.aminyazdanpanah.com
 */
package com.aminyazdanpanah.piechart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class GetViewPieChart extends RelativeLayout {
    public GetViewPieChart(Context context) {
        super(context);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(getPieCharCluster(getContext()), params);
    }

    public View getPieCharCluster(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.piechart_cluster, this, false);
    }
}
