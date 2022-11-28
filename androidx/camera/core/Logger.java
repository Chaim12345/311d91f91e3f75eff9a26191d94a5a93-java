package androidx.camera.core;

import android.os.Build;
import android.util.Log;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public final class Logger {
    private static final int MAX_TAG_LENGTH = 23;
    private static int sMinLogLevel = 3;

    private Logger() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        sMinLogLevel = 3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@IntRange(from = 3, to = 6) int i2) {
        sMinLogLevel = i2;
    }

    public static void d(@NonNull String str, @NonNull String str2) {
        d(str, str2, null);
    }

    public static void d(@NonNull String str, @NonNull String str2, @Nullable Throwable th) {
        if (isDebugEnabled(str)) {
            truncateTag(str);
        }
    }

    public static void e(@NonNull String str, @NonNull String str2) {
        e(str, str2, null);
    }

    public static void e(@NonNull String str, @NonNull String str2, @Nullable Throwable th) {
        if (isErrorEnabled(str)) {
            Log.e(truncateTag(str), str2, th);
        }
    }

    public static void i(@NonNull String str, @NonNull String str2) {
        i(str, str2, null);
    }

    public static void i(@NonNull String str, @NonNull String str2, @Nullable Throwable th) {
        if (isInfoEnabled(str)) {
            truncateTag(str);
        }
    }

    public static boolean isDebugEnabled(@NonNull String str) {
        return sMinLogLevel <= 3 || Log.isLoggable(truncateTag(str), 3);
    }

    public static boolean isErrorEnabled(@NonNull String str) {
        return sMinLogLevel <= 6 || Log.isLoggable(truncateTag(str), 6);
    }

    public static boolean isInfoEnabled(@NonNull String str) {
        return sMinLogLevel <= 4 || Log.isLoggable(truncateTag(str), 4);
    }

    public static boolean isWarnEnabled(@NonNull String str) {
        return sMinLogLevel <= 5 || Log.isLoggable(truncateTag(str), 5);
    }

    @NonNull
    private static String truncateTag(@NonNull String str) {
        return (23 >= str.length() || Build.VERSION.SDK_INT >= 24) ? str : str.substring(0, 23);
    }

    public static void w(@NonNull String str, @NonNull String str2) {
        w(str, str2, null);
    }

    public static void w(@NonNull String str, @NonNull String str2, @Nullable Throwable th) {
        if (isWarnEnabled(str)) {
            truncateTag(str);
        }
    }
}
