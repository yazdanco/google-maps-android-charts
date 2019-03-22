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

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

import java.util.Arrays;

import androidx.annotation.NonNull;


public class PieChart extends Drawable {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float[] value_degree;
    private final float[] value_real;
    private int[] COLORS;
    private RectF rectf = new RectF(convertDpToPixel(20),convertDpToPixel(17),convertDpToPixel(80), convertDpToPixel(57));
    private RectF rect = new RectF(convertDpToPixel(20),convertDpToPixel(17),convertDpToPixel(80), convertDpToPixel(67));
    private int temp = 0;

    PieChart(float[] values, int[] colors) {
        COLORS = Arrays.copyOf(colors, colors.length);
        value_real = Arrays.copyOf(values, values.length);
        value_degree = calculateData(values);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        paint.setColor(Color.BLACK);
        paint.setShadowLayer(10.0f, 0.0f, 2.0f, 0xFF000000);
        paint.setStrokeWidth(100);
        paint.setColor(0xAA777777);
        canvas.drawOval(rect, paint);

        for (int i = 0; i < value_degree.length; i++) {
            if (i != 0) {
                temp += value_degree[i - 1];
            }
            paint.setShadowLayer(10.0f, 0.0f, 2.0f, 0xFF000000);
            paint.setColor(COLORS[i]);
            canvas.drawArc(rectf, temp, value_degree[i], true, paint);
            paint.setColor(Color.BLACK);
            paint.setTextSize(convertDpToPixel(13));
            if (value_real[i] != 0) {
                double x = 40 * Math.cos(Math.toRadians(temp + (value_degree[i] / 2))) + 45;
                double y = 35 * Math.sin(Math.toRadians(temp + (value_degree[i] / 2))) + 45;
                canvas.drawText((int) value_real[i] + "", convertDpToPixel((int) x), convertDpToPixel((int) y), paint);
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    private static float convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    private float[] calculateData(float[] data) {
        int total = 0;
        for (float aData : data) {
            total += aData;
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = 360 * (data[i] / total);
        }
        return data;
    }
}