package io.opencensus.internal;
/* loaded from: classes3.dex */
public final class StringUtils {
    private StringUtils() {
    }

    private static boolean isPrintableChar(char c2) {
        return c2 >= ' ' && c2 <= '~';
    }

    public static boolean isPrintableString(String str) {
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (!isPrintableChar(str.charAt(i2))) {
                return false;
            }
        }
        return true;
    }
}
