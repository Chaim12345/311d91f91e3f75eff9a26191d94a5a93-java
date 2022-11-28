package com.facebook.stetho.common.android;

import android.view.View;
import android.view.ViewGroup;
/* loaded from: classes.dex */
public final class ViewGroupUtil {
    private ViewGroupUtil() {
    }

    public static int findChildIndex(ViewGroup viewGroup, View view) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            if (viewGroup.getChildAt(i2) == view) {
                return i2;
            }
        }
        return -1;
    }
}
