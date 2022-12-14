package androidx.camera.view.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
/* loaded from: classes.dex */
public class SurfaceViewStretchedQuirk implements Quirk {
    private static final String GALAXY_Z_FOLD_2 = "F2Q";
    private static final String SAMSUNG = "SAMSUNG";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        return SAMSUNG.equals(Build.MANUFACTURER.toUpperCase()) && GALAXY_Z_FOLD_2.equals(Build.DEVICE.toUpperCase());
    }
}
