package com.rd.utils;

import android.content.res.Resources;
import android.util.TypedValue;
/* loaded from: classes3.dex */
public class DensityUtils {
    public static int dpToPx(int i2) {
        return (int) TypedValue.applyDimension(1, i2, Resources.getSystem().getDisplayMetrics());
    }

    public static int pxToDp(float f2) {
        return (int) TypedValue.applyDimension(0, f2, Resources.getSystem().getDisplayMetrics());
    }
}
