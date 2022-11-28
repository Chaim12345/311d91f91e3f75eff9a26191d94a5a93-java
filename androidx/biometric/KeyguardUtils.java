package androidx.biometric;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
/* loaded from: classes.dex */
class KeyguardUtils {

    @RequiresApi(16)
    /* loaded from: classes.dex */
    private static class Api16Impl {
        private Api16Impl() {
        }

        static boolean a(@NonNull KeyguardManager keyguardManager) {
            return keyguardManager.isKeyguardSecure();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @RequiresApi(23)
    /* loaded from: classes.dex */
    public static class Api23Impl {
        private Api23Impl() {
        }

        @Nullable
        static KeyguardManager a(@NonNull Context context) {
            return (KeyguardManager) context.getSystemService(KeyguardManager.class);
        }

        static boolean b(@NonNull KeyguardManager keyguardManager) {
            return keyguardManager.isDeviceSecure();
        }
    }

    private KeyguardUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static KeyguardManager a(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            return Api23Impl.a(context);
        }
        Object systemService = context.getSystemService("keyguard");
        if (systemService instanceof KeyguardManager) {
            return (KeyguardManager) systemService;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(@NonNull Context context) {
        KeyguardManager a2 = a(context);
        if (a2 == null) {
            return false;
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 23) {
            return Api23Impl.b(a2);
        }
        if (i2 >= 16) {
            return Api16Impl.a(a2);
        }
        return false;
    }
}
