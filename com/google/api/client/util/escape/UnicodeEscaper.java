package com.google.api.client.util.escape;
/* loaded from: classes2.dex */
public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    protected static int a(CharSequence charSequence, int i2, int i3) {
        if (i2 < i3) {
            int i4 = i2 + 1;
            char charAt = charSequence.charAt(i2);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected low surrogate character '");
                sb.append(charAt);
                sb.append("' with value ");
                sb.append((int) charAt);
                sb.append(" at index ");
                sb.append(i4 - 1);
                throw new IllegalArgumentException(sb.toString());
            } else if (i4 == i3) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i4);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                throw new IllegalArgumentException("Expected low surrogate but got char '" + charAt2 + "' with value " + ((int) charAt2) + " at index " + i4);
            }
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    private static char[] growBuffer(char[] cArr, int i2, int i3) {
        char[] cArr2 = new char[i3];
        if (i2 > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i2);
        }
        return cArr2;
    }

    protected abstract char[] b(int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public final String c(String str, int i2) {
        int length = str.length();
        char[] a2 = Platform.a();
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            int a3 = a(str, i2, length);
            if (a3 < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            char[] b2 = b(a3);
            int i5 = (Character.isSupplementaryCodePoint(a3) ? 2 : 1) + i2;
            if (b2 != null) {
                int i6 = i2 - i3;
                int i7 = i4 + i6;
                int length2 = b2.length + i7;
                if (a2.length < length2) {
                    a2 = growBuffer(a2, i4, ((length2 + length) - i2) + 32);
                }
                if (i6 > 0) {
                    str.getChars(i3, i2, a2, i4);
                    i4 = i7;
                }
                if (b2.length > 0) {
                    System.arraycopy(b2, 0, a2, i4, b2.length);
                    i4 += b2.length;
                }
                i3 = i5;
            }
            i2 = d(str, i5, length);
        }
        int i8 = length - i3;
        if (i8 > 0) {
            int i9 = i8 + i4;
            if (a2.length < i9) {
                a2 = growBuffer(a2, i4, i9);
            }
            str.getChars(i3, length, a2, i4);
            i4 = i9;
        }
        return new String(a2, 0, i4);
    }

    protected abstract int d(CharSequence charSequence, int i2, int i3);

    @Override // com.google.api.client.util.escape.Escaper
    public abstract String escape(String str);
}
