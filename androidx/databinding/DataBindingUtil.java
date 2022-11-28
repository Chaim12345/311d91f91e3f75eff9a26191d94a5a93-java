package androidx.databinding;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public class DataBindingUtil {
    private static DataBinderMapper sMapper = new DataBinderMapperImpl();
    private static DataBindingComponent sDefaultComponent = null;

    private DataBindingUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewDataBinding a(DataBindingComponent dataBindingComponent, View view, int i2) {
        return sMapper.getDataBinder(dataBindingComponent, view, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ViewDataBinding b(DataBindingComponent dataBindingComponent, View[] viewArr, int i2) {
        return sMapper.getDataBinder(dataBindingComponent, viewArr, i2);
    }

    @Nullable
    public static <T extends ViewDataBinding> T bind(@NonNull View view) {
        return (T) bind(view, sDefaultComponent);
    }

    @Nullable
    public static <T extends ViewDataBinding> T bind(@NonNull View view, DataBindingComponent dataBindingComponent) {
        T t2 = (T) getBinding(view);
        if (t2 != null) {
            return t2;
        }
        Object tag = view.getTag();
        if (tag instanceof String) {
            int layoutId = sMapper.getLayoutId((String) tag);
            if (layoutId != 0) {
                return (T) sMapper.getDataBinder(dataBindingComponent, view, layoutId);
            }
            throw new IllegalArgumentException("View is not a binding layout. Tag: " + tag);
        }
        throw new IllegalArgumentException("View is not a binding layout");
    }

    private static <T extends ViewDataBinding> T bindToAddedViews(DataBindingComponent dataBindingComponent, ViewGroup viewGroup, int i2, int i3) {
        int childCount = viewGroup.getChildCount();
        int i4 = childCount - i2;
        if (i4 == 1) {
            return (T) a(dataBindingComponent, viewGroup.getChildAt(childCount - 1), i3);
        }
        View[] viewArr = new View[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            viewArr[i5] = viewGroup.getChildAt(i5 + i2);
        }
        return (T) b(dataBindingComponent, viewArr, i3);
    }

    @Nullable
    public static String convertBrIdToString(int i2) {
        return sMapper.convertBrIdToString(i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r3 == (-1)) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0038, code lost:
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
        r7 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0047, code lost:
        if (r1.indexOf(47, r3 + 1) == (-1)) goto L15;
     */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static <T extends ViewDataBinding> T findBinding(@NonNull View view) {
        while (view != null) {
            T t2 = (T) ViewDataBinding.i(view);
            if (t2 != null) {
                return t2;
            }
            Object tag = view.getTag();
            if (tag instanceof String) {
                String str = (String) tag;
                if (str.startsWith("layout") && str.endsWith("_0")) {
                    char charAt = str.charAt(6);
                    int indexOf = str.indexOf(47, 7);
                    boolean z = true;
                    boolean z2 = false;
                    if (charAt != '/') {
                        if (charAt == '-' && indexOf != -1) {
                        }
                    }
                    if (z2) {
                        return null;
                    }
                }
            }
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    @Nullable
    public static <T extends ViewDataBinding> T getBinding(@NonNull View view) {
        return (T) ViewDataBinding.i(view);
    }

    @Nullable
    public static DataBindingComponent getDefaultComponent() {
        return sDefaultComponent;
    }

    public static <T extends ViewDataBinding> T inflate(@NonNull LayoutInflater layoutInflater, int i2, @Nullable ViewGroup viewGroup, boolean z) {
        return (T) inflate(layoutInflater, i2, viewGroup, z, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T inflate(@NonNull LayoutInflater layoutInflater, int i2, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        boolean z2 = viewGroup != null && z;
        return z2 ? (T) bindToAddedViews(dataBindingComponent, viewGroup, z2 ? viewGroup.getChildCount() : 0, i2) : (T) a(dataBindingComponent, layoutInflater.inflate(i2, viewGroup, z), i2);
    }

    public static <T extends ViewDataBinding> T setContentView(@NonNull Activity activity, int i2) {
        return (T) setContentView(activity, i2, sDefaultComponent);
    }

    public static <T extends ViewDataBinding> T setContentView(@NonNull Activity activity, int i2, @Nullable DataBindingComponent dataBindingComponent) {
        activity.setContentView(i2);
        return (T) bindToAddedViews(dataBindingComponent, (ViewGroup) activity.getWindow().getDecorView().findViewById(16908290), 0, i2);
    }

    public static void setDefaultComponent(@Nullable DataBindingComponent dataBindingComponent) {
        sDefaultComponent = dataBindingComponent;
    }
}
