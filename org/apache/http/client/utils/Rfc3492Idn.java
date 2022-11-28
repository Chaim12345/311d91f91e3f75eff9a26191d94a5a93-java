package org.apache.http.client.utils;

import java.util.StringTokenizer;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
@Contract(threading = ThreadingBehavior.IMMUTABLE)
@Deprecated
/* loaded from: classes3.dex */
public class Rfc3492Idn implements Idn {
    private static final String ACE_PREFIX = "xn--";
    private static final int base = 36;
    private static final int damp = 700;
    private static final char delimiter = '-';
    private static final int initial_bias = 72;
    private static final int initial_n = 128;
    private static final int skew = 38;
    private static final int tmax = 26;
    private static final int tmin = 1;

    private int adapt(int i2, int i3, boolean z) {
        int i4 = z ? i2 / 700 : i2 / 2;
        int i5 = i4 + (i4 / i3);
        int i6 = 0;
        while (i5 > 455) {
            i5 /= 35;
            i6 += 36;
        }
        return i6 + ((i5 * 36) / (i5 + 38));
    }

    private int digit(char c2) {
        if (c2 < 'A' || c2 > 'Z') {
            if (c2 < 'a' || c2 > 'z') {
                if (c2 < '0' || c2 > '9') {
                    throw new IllegalArgumentException("illegal digit: " + c2);
                }
                return (c2 - '0') + 26;
            }
            return c2 - 'a';
        }
        return c2 - 'A';
    }

    protected String decode(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        int lastIndexOf = str.lastIndexOf(45);
        int i2 = 128;
        int i3 = 72;
        if (lastIndexOf != -1) {
            sb.append(str.subSequence(0, lastIndexOf));
            str = str.substring(lastIndexOf + 1);
        }
        int i4 = 0;
        while (!str.isEmpty()) {
            int i5 = 36;
            int i6 = i4;
            int i7 = 1;
            while (!str.isEmpty()) {
                char charAt = str.charAt(0);
                str = str.substring(1);
                int digit = digit(charAt);
                i6 += digit * i7;
                int i8 = i5 <= i3 + 1 ? 1 : i5 >= i3 + 26 ? 26 : i5 - i3;
                if (digit < i8) {
                    break;
                }
                i7 *= 36 - i8;
                i5 += 36;
            }
            i3 = adapt(i6 - i4, sb.length() + 1, i4 == 0);
            i2 += i6 / (sb.length() + 1);
            int length = i6 % (sb.length() + 1);
            sb.insert(length, (char) i2);
            i4 = length + 1;
        }
        return sb.toString();
    }

    @Override // org.apache.http.client.utils.Idn
    public String toUnicode(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (sb.length() > 0) {
                sb.append('.');
            }
            if (nextToken.startsWith(ACE_PREFIX)) {
                nextToken = decode(nextToken.substring(4));
            }
            sb.append(nextToken);
        }
        return sb.toString();
    }
}
