package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
@Beta
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class CharEscaper extends Escaper {
    private static final int DEST_PAD_MULTIPLIER = 2;

    private static char[] growBuffer(char[] cArr, int i2, int i3) {
        if (i3 >= 0) {
            char[] cArr2 = new char[i3];
            if (i2 > 0) {
                System.arraycopy(cArr, 0, cArr2, 0, i2);
            }
            return cArr2;
        }
        throw new AssertionError("Cannot increase internal buffer any further");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract char[] a(char c2);

    /* JADX INFO: Access modifiers changed from: protected */
    public final String b(String str, int i2) {
        int length = str.length();
        char[] a2 = Platform.a();
        int length2 = a2.length;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            char[] a3 = a(str.charAt(i2));
            if (a3 != null) {
                int length3 = a3.length;
                int i5 = i2 - i3;
                int i6 = i4 + i5;
                int i7 = i6 + length3;
                if (length2 < i7) {
                    length2 = ((length - i2) * 2) + i7;
                    a2 = growBuffer(a2, i4, length2);
                }
                if (i5 > 0) {
                    str.getChars(i3, i2, a2, i4);
                    i4 = i6;
                }
                if (length3 > 0) {
                    System.arraycopy(a3, 0, a2, i4, length3);
                    i4 += length3;
                }
                i3 = i2 + 1;
            }
            i2++;
        }
        int i8 = length - i3;
        if (i8 > 0) {
            int i9 = i8 + i4;
            if (length2 < i9) {
                a2 = growBuffer(a2, i4, i9);
            }
            str.getChars(i3, length, a2, i4);
            i4 = i9;
        }
        return new String(a2, 0, i4);
    }

    @Override // com.google.common.escape.Escaper
    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (a(str.charAt(i2)) != null) {
                return b(str, i2);
            }
        }
        return str;
    }
}
