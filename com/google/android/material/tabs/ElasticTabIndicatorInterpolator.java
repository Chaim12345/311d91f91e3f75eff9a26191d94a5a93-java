package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.google.android.material.animation.AnimationUtils;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ElasticTabIndicatorInterpolator extends TabIndicatorInterpolator {
    private static float accInterp(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        return (float) (1.0d - Math.cos((f2 * 3.141592653589793d) / 2.0d));
    }

    private static float decInterp(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        return (float) Math.sin((f2 * 3.141592653589793d) / 2.0d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.material.tabs.TabIndicatorInterpolator
    public void c(TabLayout tabLayout, View view, View view2, float f2, @NonNull Drawable drawable) {
        float decInterp;
        float accInterp;
        RectF a2 = TabIndicatorInterpolator.a(tabLayout, view);
        RectF a3 = TabIndicatorInterpolator.a(tabLayout, view2);
        if (a2.left < a3.left) {
            decInterp = accInterp(f2);
            accInterp = decInterp(f2);
        } else {
            decInterp = decInterp(f2);
            accInterp = accInterp(f2);
        }
        drawable.setBounds(AnimationUtils.lerp((int) a2.left, (int) a3.left, decInterp), drawable.getBounds().top, AnimationUtils.lerp((int) a2.right, (int) a3.right, accInterp), drawable.getBounds().bottom);
    }
}
