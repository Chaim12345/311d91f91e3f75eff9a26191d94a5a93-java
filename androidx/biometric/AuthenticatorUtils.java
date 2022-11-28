package androidx.biometric;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricPrompt;
/* loaded from: classes.dex */
class AuthenticatorUtils {
    private static final int BIOMETRIC_CLASS_MASK = 32767;

    private AuthenticatorUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(int i2) {
        return i2 != 15 ? i2 != 255 ? i2 != 32768 ? i2 != 32783 ? i2 != 33023 ? String.valueOf(i2) : "BIOMETRIC_WEAK | DEVICE_CREDENTIAL" : "BIOMETRIC_STRONG | DEVICE_CREDENTIAL" : "DEVICE_CREDENTIAL" : "BIOMETRIC_WEAK" : "BIOMETRIC_STRONG";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(@NonNull BiometricPrompt.PromptInfo promptInfo, @Nullable BiometricPrompt.CryptoObject cryptoObject) {
        if (promptInfo.getAllowedAuthenticators() != 0) {
            return promptInfo.getAllowedAuthenticators();
        }
        int i2 = cryptoObject != null ? 15 : 255;
        return promptInfo.isDeviceCredentialAllowed() ? 32768 | i2 : i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(int i2) {
        return (i2 & 32768) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(int i2) {
        return (i2 & BIOMETRIC_CLASS_MASK) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(int i2) {
        if (i2 == 15 || i2 == 255) {
            return true;
        }
        if (i2 == 32768) {
            return Build.VERSION.SDK_INT >= 30;
        } else if (i2 != 32783) {
            return i2 == 33023 || i2 == 0;
        } else {
            int i3 = Build.VERSION.SDK_INT;
            return i3 < 28 || i3 > 29;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(int i2) {
        return (i2 & 255) == 255;
    }
}
