package androidx.camera.view.internal.compat.quirk;

import android.os.Build;
import androidx.camera.core.impl.Quirk;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes.dex */
public class TextureViewRotationQuirk implements Quirk {
    private static final String FAIRPHONE = "Fairphone";
    private static final String FAIRPHONE_2_MODEL = "FP2";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        return isFairphone2();
    }

    private static boolean isFairphone2() {
        return FAIRPHONE.equalsIgnoreCase(Build.MANUFACTURER) && FAIRPHONE_2_MODEL.equalsIgnoreCase(Build.MODEL);
    }

    public int getCorrectionRotation(boolean z) {
        if (isFairphone2() && z) {
            return CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA256;
        }
        return 0;
    }
}
