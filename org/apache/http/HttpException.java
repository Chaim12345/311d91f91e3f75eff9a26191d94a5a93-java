package org.apache.http;
/* loaded from: classes3.dex */
public class HttpException extends Exception {
    private static final int FIRST_VALID_CHAR = 32;
    private static final long serialVersionUID = -5437299376222011036L;

    public HttpException() {
    }

    public HttpException(String str) {
        super(clean(str));
    }

    public HttpException(String str, Throwable th) {
        super(clean(str));
        initCause(th);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String clean(String str) {
        char[] charArray = str.toCharArray();
        int i2 = 0;
        while (i2 < charArray.length && charArray[i2] >= ' ') {
            i2++;
        }
        if (i2 == charArray.length) {
            return str;
        }
        StringBuilder sb = new StringBuilder(charArray.length * 2);
        for (int i3 = 0; i3 < charArray.length; i3++) {
            char c2 = charArray[i3];
            if (c2 < ' ') {
                sb.append("[0x");
                String hexString = Integer.toHexString(i3);
                if (hexString.length() == 1) {
                    sb.append("0");
                }
                sb.append(hexString);
                sb.append("]");
            } else {
                sb.append(c2);
            }
        }
        return sb.toString();
    }
}
