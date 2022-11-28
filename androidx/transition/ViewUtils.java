package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ViewUtils {
    private static final ViewUtilsBase IMPL;
    private static final String TAG = "ViewUtils";

    /* renamed from: a  reason: collision with root package name */
    static final Property f4151a;

    /* renamed from: b  reason: collision with root package name */
    static final Property f4152b;

    static {
        int i2 = Build.VERSION.SDK_INT;
        IMPL = i2 >= 29 ? new ViewUtilsApi29() : i2 >= 23 ? new ViewUtilsApi23() : i2 >= 22 ? new ViewUtilsApi22() : i2 >= 21 ? new ViewUtilsApi21() : i2 >= 19 ? new ViewUtilsApi19() : new ViewUtilsBase();
        f4151a = new Property<View, Float>(Float.class, "translationAlpha") { // from class: androidx.transition.ViewUtils.1
            @Override // android.util.Property
            public Float get(View view) {
                return Float.valueOf(ViewUtils.c(view));
            }

            @Override // android.util.Property
            public void set(View view, Float f2) {
                ViewUtils.h(view, f2.floatValue());
            }
        };
        f4152b = new Property<View, Rect>(Rect.class, "clipBounds") { // from class: androidx.transition.ViewUtils.2
            @Override // android.util.Property
            public Rect get(View view) {
                return ViewCompat.getClipBounds(view);
            }

            @Override // android.util.Property
            public void set(View view, Rect rect) {
                ViewCompat.setClipBounds(view, rect);
            }
        };
    }

    private ViewUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull View view) {
        IMPL.clearNonTransitionAlpha(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewOverlayImpl b(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 18 ? new ViewOverlayApi18(view) : ViewOverlayApi14.a(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float c(@NonNull View view) {
        return IMPL.getTransitionAlpha(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static WindowIdImpl d(@NonNull View view) {
        return Build.VERSION.SDK_INT >= 18 ? new WindowIdApi18(view) : new WindowIdApi14(view.getWindowToken());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(@NonNull View view) {
        IMPL.saveNonTransitionAlpha(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void f(@NonNull View view, @Nullable Matrix matrix) {
        IMPL.setAnimationMatrix(view, matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void g(@NonNull View view, int i2, int i3, int i4, int i5) {
        IMPL.setLeftTopRightBottom(view, i2, i3, i4, i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void h(@NonNull View view, float f2) {
        IMPL.setTransitionAlpha(view, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i(@NonNull View view, int i2) {
        IMPL.setTransitionVisibility(view, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void j(@NonNull View view, @NonNull Matrix matrix) {
        IMPL.transformMatrixToGlobal(view, matrix);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void k(@NonNull View view, @NonNull Matrix matrix) {
        IMPL.transformMatrixToLocal(view, matrix);
    }
}
