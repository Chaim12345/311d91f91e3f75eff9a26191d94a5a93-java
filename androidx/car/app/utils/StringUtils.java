package androidx.car.app.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class StringUtils {
    private static final int MAX_SHORT_STRING_LENGTH = 16;

    private StringUtils() {
    }

    @NonNull
    public static String shortenString(@NonNull String str) {
        if (str.length() <= 16) {
            return str;
        }
        return str.substring(0, 8) + "~" + str.substring(str.length() - 8);
    }
}
