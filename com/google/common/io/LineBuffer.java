package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
@GwtIncompatible
/* loaded from: classes2.dex */
abstract class LineBuffer {
    private StringBuilder line = new StringBuilder();
    private boolean sawReturn;

    @CanIgnoreReturnValue
    private boolean finishLine(boolean z) {
        c(this.line.toString(), this.sawReturn ? z ? "\r\n" : "\r" : z ? "\n" : "");
        this.line = new StringBuilder();
        this.sawReturn = false;
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:15:0x001f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void a(char[] cArr, int i2, int i3) {
        int i4;
        int i5;
        if (this.sawReturn && i3 > 0) {
            if (finishLine(cArr[i2] == '\n')) {
                i4 = i2 + 1;
                i5 = i2 + i3;
                int i6 = i4;
                while (i4 < i5) {
                    char c2 = cArr[i4];
                    if (c2 == '\n') {
                        this.line.append(cArr, i6, i4 - i6);
                        finishLine(true);
                    } else if (c2 != '\r') {
                        i4++;
                    } else {
                        this.line.append(cArr, i6, i4 - i6);
                        this.sawReturn = true;
                        int i7 = i4 + 1;
                        if (i7 < i5) {
                            if (finishLine(cArr[i7] == '\n')) {
                                i4 = i7;
                            }
                        }
                    }
                    i6 = i4 + 1;
                    i4++;
                }
                this.line.append(cArr, i6, i5 - i6);
            }
        }
        i4 = i2;
        i5 = i2 + i3;
        int i62 = i4;
        while (i4 < i5) {
        }
        this.line.append(cArr, i62, i5 - i62);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        if (this.sawReturn || this.line.length() > 0) {
            finishLine(false);
        }
    }

    protected abstract void c(String str, String str2);
}
