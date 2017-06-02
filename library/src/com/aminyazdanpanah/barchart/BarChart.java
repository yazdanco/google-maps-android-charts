/*
 * Copyright 2016 Amin Yazdanpanah http://www.aminyazdanpanah.com.
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
package com.aminyazdanpanah.barchart;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Arrays;


public class BarChart extends Drawable {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int[] COLORS = {0xFF74E370, 0xFF54B8FF, 0xFFFF5754, 0xFF939393};
    private float[] value_siza;
    private final float[] value_real;
    private float leftx = 17;
    private float rightx = 3;

    public BarChart(float[] values) {
        value_real = Arrays.copyOf(values, values.length);
        value_siza = calculateData(values);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        float bottomy = 70;
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setStrokeWidth(10);
        for (int i = 0; i < value_siza.length; i++) {
            Log.e("amin - adad", String.valueOf(value_real[i]));
            paint.setTextSize(convertDpToPixel(13));
            paint.setColor(Color.BLACK);
            canvas.drawText((int) value_real[i] + "", convertDpToPixel(rightx), convertDpToPixel(value_siza[i] - 10), paint);
            paint.setColor(COLORS[i]);
            canvas.drawRect(convertDpToPixel(leftx), convertDpToPixel(value_siza[i]), convertDpToPixel(rightx), convertDpToPixel(bottomy), paint);
            leftx = leftx +17;
            rightx = rightx +17;
        }


    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

    private static float convertDpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }

    private float[] calculateData(float[] data) {
        float total = 0;
        for (float aData : data) {
            total += aData;
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = (1 - (data[i] / total)) * 70;
        }
        return data;

    }
}
