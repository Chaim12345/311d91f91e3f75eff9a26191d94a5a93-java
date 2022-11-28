package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.ColorInt;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;
/* loaded from: classes2.dex */
abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {

    /* renamed from: a  reason: collision with root package name */
    BaseProgressIndicatorSpec f7428a;

    /* renamed from: b  reason: collision with root package name */
    protected DrawableWithAnimatedVisibilityChange f7429b;

    public DrawingDelegate(S s2) {
        this.f7428a = s2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(@NonNull Canvas canvas, @NonNull Paint paint);

    abstract void adjustCanvas(@NonNull Canvas canvas, @FloatRange(from = 0.0d, to = 1.0d) float f2);

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(@NonNull DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange) {
        this.f7429b = drawableWithAnimatedVisibilityChange;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(@NonNull Canvas canvas, @FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.f7428a.a();
        adjustCanvas(canvas, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void fillIndicator(@NonNull Canvas canvas, @NonNull Paint paint, @FloatRange(from = 0.0d, to = 1.0d) float f2, @FloatRange(from = 0.0d, to = 1.0d) float f3, @ColorInt int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getPreferredHeight();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int getPreferredWidth();
}
