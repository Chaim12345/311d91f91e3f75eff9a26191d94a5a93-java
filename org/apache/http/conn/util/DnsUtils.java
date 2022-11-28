package org.apache.http.conn.util;

import org.apache.http.message.TokenParser;
/* loaded from: classes3.dex */
public class DnsUtils {
    private DnsUtils() {
    }

    private static boolean isUpper(char c2) {
        return c2 >= 'A' && c2 <= 'Z';
    }

    public static String normalize(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        int i2 = 0;
        while (length > 0 && !isUpper(str.charAt(i2))) {
            i2++;
            length--;
        }
        if (length > 0) {
            StringBuilder sb = new StringBuilder(str.length());
            sb.append((CharSequence) str, 0, i2);
            while (length > 0) {
                char charAt = str.charAt(i2);
                if (isUpper(charAt)) {
                    charAt = (char) (charAt + TokenParser.SP);
                }
                sb.append(charAt);
                i2++;
                length--;
            }
            return sb.toString();
        }
        return str;
    }
}
