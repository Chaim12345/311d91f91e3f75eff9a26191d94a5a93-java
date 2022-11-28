package androidx.biometric;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes.dex */
class ErrorUtils {
    private ErrorUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static String a(@Nullable Context context, int i2) {
        int i3;
        if (context == null) {
            return "";
        }
        if (i2 != 1) {
            if (i2 != 7) {
                switch (i2) {
                    case 9:
                        break;
                    case 10:
                        i3 = R.string.fingerprint_error_user_canceled;
                        break;
                    case 11:
                        i3 = R.string.fingerprint_error_no_fingerprints;
                        break;
                    case 12:
                        i3 = R.string.fingerprint_error_hw_not_present;
                        break;
                    default:
                        Log.e("BiometricUtils", "Unknown error code: " + i2);
                        i3 = R.string.default_error_msg;
                        break;
                }
            }
            i3 = R.string.fingerprint_error_lockout;
        } else {
            i3 = R.string.fingerprint_error_hw_not_available;
        }
        return context.getString(i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(int i2) {
        switch (i2) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
                return true;
            case 6:
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(int i2) {
        return i2 == 7 || i2 == 9;
    }
}
