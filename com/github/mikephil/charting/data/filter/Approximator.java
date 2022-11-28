package com.github.mikephil.charting.data.filter;

import android.annotation.TargetApi;
import java.util.Arrays;
/* loaded from: classes.dex */
public class Approximator {

    /* loaded from: classes.dex */
    private class Line {
        private float dx;
        private float dy;
        private float exsy;
        private float length;
        private float[] points;
        private float sxey;

        public Line(Approximator approximator, float f2, float f3, float f4, float f5) {
            float f6 = f2 - f4;
            this.dx = f6;
            float f7 = f3 - f5;
            this.dy = f7;
            this.sxey = f2 * f5;
            this.exsy = f4 * f3;
            this.length = (float) Math.sqrt((f6 * f6) + (f7 * f7));
            this.points = new float[]{f2, f3, f4, f5};
        }

        public float distance(float f2, float f3) {
            return Math.abs((((this.dy * f2) - (this.dx * f3)) + this.sxey) - this.exsy) / this.length;
        }

        public float[] getPoints() {
            return this.points;
        }
    }

    float[] a(float[]... fArr) {
        int i2 = 0;
        for (float[] fArr2 : fArr) {
            i2 += fArr2.length;
        }
        float[] fArr3 = new float[i2];
        int i3 = 0;
        for (float[] fArr4 : fArr) {
            for (float f2 : fArr4) {
                fArr3[i3] = f2;
                i3++;
            }
        }
        return fArr3;
    }

    @TargetApi(9)
    public float[] reduceWithDouglasPeucker(float[] fArr, float f2) {
        Line line = new Line(this, fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1]);
        float f3 = 0.0f;
        int i2 = 0;
        for (int i3 = 2; i3 < fArr.length - 2; i3 += 2) {
            float distance = line.distance(fArr[i3], fArr[i3 + 1]);
            if (distance > f3) {
                i2 = i3;
                f3 = distance;
            }
        }
        if (f3 > f2) {
            float[] reduceWithDouglasPeucker = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, 0, i2 + 2), f2);
            float[] reduceWithDouglasPeucker2 = reduceWithDouglasPeucker(Arrays.copyOfRange(fArr, i2, fArr.length), f2);
            return a(reduceWithDouglasPeucker, Arrays.copyOfRange(reduceWithDouglasPeucker2, 2, reduceWithDouglasPeucker2.length));
        }
        return line.getPoints();
    }
}
