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
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

import java.util.Arrays;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class DonutChart extends Drawable {


    private float radius = 120;
    private Paint paint;
    private Path myPath;
    private RectF outterCircle;
    private RectF innerCircle;
    private int[] COLORS;
    private float[] value_degree;
    private final float[] value_real;
    private int start = -1;
    private Paint paint_text = new Paint(Paint.ANTI_ALIAS_FLAG);

    DonutChart(float[] values, int[] colors) {
        COLORS = Arrays.copyOf(colors, colors.length);
        value_real = Arrays.copyOf(values, values.length);
        value_degree = calculateData(values);

        paint = new Paint();
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(radius / 14.0f);

        Paint shadowPaint = new Paint();
        shadowPaint.setColor(0xf0000000);
        shadowPaint.setStyle(Paint.Style.STROKE);
        shadowPaint.setAntiAlias(true);
        shadowPaint.setStrokeWidth(6.0f);
        shadowPaint.setMaskFilter(new BlurMaskFilter(4, BlurMaskFilter.Blur.SOLID));


        myPath = new Path();


        outterCircle = new RectF();
        innerCircle = new RectF();
        RectF shadowRectF = new RectF();

        float adjust = (.019f * radius);
        shadowRectF.set(adjust, adjust, radius * 2 - adjust, radius * 2 - adjust);

        adjust = .038f * radius;
        outterCircle.set(adjust, adjust, radius * 2 - adjust, radius * 2 - adjust);

        adjust = .276f * radius;
        innerCircle.set(adjust, adjust, radius * 2 - adjust, radius * 2 - adjust);

    }


    @Override
    public void draw(@NonNull Canvas canvas) {
        // draw shadow
        paint.setShader(null);
        float adjust = (.0095f * radius);
        paint.setShadowLayer(8, adjust, -adjust, 0xaa000000);
        drawDonut(canvas, paint, 0, 359.9f);
        float sweep;
        for (int i = 0; i < value_degree.length; i++) {
            sweep = value_degree[i];
            if (i != 0) {
                start += value_degree[i - 1];
            }

            if(start == -1 && sweep == 0){
                sweep = 359;
            }

            setGradient(COLORS[i], darkenColor(COLORS[i]));
            drawDonut(canvas, paint, start, sweep);

            if (value_real[i] != 0) {
                paint_text.setColor(Color.BLACK);
                paint_text.setTextSize(convertDpToPixel(13));
                double x = 35 * Math.cos(Math.toRadians(start + (sweep / 2))) + 35;
                double y = 35 * Math.sin(Math.toRadians(start + (sweep / 2))) + 45;
                canvas.drawText((int)value_real[i] + "", convertDpToPixel((float) x), convertDpToPixel((float) y), paint_text);
            }
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }

    private void drawDonut(Canvas canvas, Paint paint, float start, float sweep) {

        myPath.reset();
        myPath.arcTo(outterCircle, start, sweep, false);
        myPath.arcTo(innerCircle, start + sweep, -sweep, false);
        myPath.close();
        canvas.drawPath(myPath, paint);
    }

    private void setGradient(int sColor, int eColor) {
        paint.setShader(new RadialGradient(radius, radius, radius - 5,
                new int[]{sColor, eColor},
                new float[]{.6f, .95f}, TileMode.CLAMP));
    }

    private float[] calculateData(float[] data) {
        float total = 0;
        for (float aData : data) {
            total += aData;
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = (360 * (data[i] / total));
        }
        return data;
    }

    private int darkenColor(int color) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * 0.8f);
        int g = Math.round(Color.green(color) * 0.8f);
        int b = Math.round(Color.blue(color) * 0.8f);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }

    private static float convertDpToPixel(float dp){
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return Math.round(px);
    }
}