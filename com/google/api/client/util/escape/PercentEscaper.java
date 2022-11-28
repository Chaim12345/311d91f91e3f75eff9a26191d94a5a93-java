package com.google.api.client.util.escape;
/* loaded from: classes2.dex */
public class PercentEscaper extends UnicodeEscaper {
    public static final String SAFECHARS_URLENCODER = "-_.*";
    public static final String SAFEPATHCHARS_URLENCODER = "-_.!~*'()@:$&,;=+";
    public static final String SAFEQUERYSTRINGCHARS_URLENCODER = "-_.!~*'()@:$,;/?:";
    public static final String SAFEUSERINFOCHARS_URLENCODER = "-_.!~*'():$&,;=";
    public static final String SAFE_PLUS_RESERVED_CHARS_URLENCODER = "-_.!~*'()@:$&,;=+/?";
    private final boolean plusForSpace;
    private final boolean[] safeOctets;
    private static final char[] URI_ESCAPED_SPACE = {'+'};
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();

    public PercentEscaper(String str) {
        this(str, false);
    }

    @Deprecated
    public PercentEscaper(String str, boolean z) {
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric ASCII characters are always 'safe' and should not be escaped.");
        }
        if (z && str.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        if (str.contains("%")) {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        }
        this.plusForSpace = z;
        this.safeOctets = createSafeOctets(str);
    }

    private static boolean[] createSafeOctets(String str) {
        char[] charArray = str.toCharArray();
        int i2 = 122;
        for (char c2 : charArray) {
            i2 = Math.max((int) c2, i2);
        }
        boolean[] zArr = new boolean[i2 + 1];
        for (int i3 = 48; i3 <= 57; i3++) {
            zArr[i3] = true;
        }
        for (int i4 = 65; i4 <= 90; i4++) {
            zArr[i4] = true;
        }
        for (int i5 = 97; i5 <= 122; i5++) {
            zArr[i5] = true;
        }
        for (char c3 : charArray) {
            zArr[c3] = true;
        }
        return zArr;
    }

    @Override // com.google.api.client.util.escape.UnicodeEscaper
    protected char[] b(int i2) {
        boolean[] zArr = this.safeOctets;
        if (i2 >= zArr.length || !zArr[i2]) {
            if (i2 == 32 && this.plusForSpace) {
                return URI_ESCAPED_SPACE;
            }
            if (i2 <= 127) {
                char[] cArr = UPPER_HEX_DIGITS;
                return new char[]{'%', cArr[i2 >>> 4], cArr[i2 & 15]};
            } else if (i2 <= 2047) {
                char[] cArr2 = UPPER_HEX_DIGITS;
                char[] cArr3 = {'%', cArr2[(r14 >>> 4) | 12], cArr2[r14 & 15], '%', cArr2[(r14 & 3) | 8], cArr2[i2 & 15]};
                int i3 = i2 >>> 4;
                int i4 = i3 >>> 2;
                return cArr3;
            } else if (i2 <= 65535) {
                char[] cArr4 = UPPER_HEX_DIGITS;
                char[] cArr5 = {'%', 'E', cArr4[r14 >>> 2], '%', cArr4[(r14 & 3) | 8], cArr4[r14 & 15], '%', cArr4[(r14 & 3) | 8], cArr4[i2 & 15]};
                int i5 = i2 >>> 4;
                int i6 = i5 >>> 2;
                int i7 = i6 >>> 4;
                return cArr5;
            } else if (i2 > 1114111) {
                throw new IllegalArgumentException("Invalid unicode character value " + i2);
            } else {
                char[] cArr6 = UPPER_HEX_DIGITS;
                char[] cArr7 = {'%', 'F', cArr6[(r14 >>> 2) & 7], '%', cArr6[(r14 & 3) | 8], cArr6[r14 & 15], '%', cArr6[(r14 & 3) | 8], cArr6[r14 & 15], '%', cArr6[(r14 & 3) | 8], cArr6[i2 & 15]};
                int i8 = i2 >>> 4;
                int i9 = i8 >>> 2;
                int i10 = i9 >>> 4;
                int i11 = i10 >>> 2;
                int i12 = i11 >>> 4;
                return cArr7;
            }
        }
        return null;
    }

    @Override // com.google.api.client.util.escape.UnicodeEscaper
    protected int d(CharSequence charSequence, int i2, int i3) {
        while (i2 < i3) {
            char charAt = charSequence.charAt(i2);
            boolean[] zArr = this.safeOctets;
            if (charAt >= zArr.length || !zArr[charAt]) {
                break;
            }
            i2++;
        }
        return i2;
    }

    @Override // com.google.api.client.util.escape.UnicodeEscaper, com.google.api.client.util.escape.Escaper
    public String escape(String str) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            boolean[] zArr = this.safeOctets;
            if (charAt >= zArr.length || !zArr[charAt]) {
                return c(str, i2);
            }
        }
        return str;
    }
}
