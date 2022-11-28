package com.google.maps.internal;

import java.util.Objects;
/* loaded from: classes2.dex */
public class StringJoin {

    /* loaded from: classes2.dex */
    public interface UrlValue {
        String toUrlValue();
    }

    private StringJoin() {
    }

    public static String join(char c2, UrlValue... urlValueArr) {
        String[] strArr = new String[urlValueArr.length];
        int length = urlValueArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            strArr[i3] = urlValueArr[i2].toUrlValue();
            i2++;
            i3++;
        }
        return join(c2, strArr);
    }

    public static String join(char c2, Object... objArr) {
        return join(new String(new char[]{c2}), objArr);
    }

    public static String join(char c2, String... strArr) {
        return join((CharSequence) new String(new char[]{c2}), strArr);
    }

    public static String join(CharSequence charSequence, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < objArr.length; i2++) {
            if (i2 != 0) {
                sb.append(charSequence);
            }
            sb.append(Objects.toString(objArr[i2]));
        }
        return sb.toString();
    }

    public static String join(CharSequence charSequence, String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (i2 != 0) {
                sb.append(charSequence);
            }
            sb.append(strArr[i2]);
        }
        return sb.toString();
    }
}
