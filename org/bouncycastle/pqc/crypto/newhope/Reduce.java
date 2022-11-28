package org.bouncycastle.pqc.crypto.newhope;

import kotlin.UShort;
/* loaded from: classes4.dex */
class Reduce {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static short a(short s2) {
        int i2 = s2 & UShort.MAX_VALUE;
        return (short) (i2 - (((i2 * 5) >>> 16) * 12289));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short b(int i2) {
        return (short) (((((i2 * 12287) & 262143) * 12289) + i2) >>> 18);
    }
}
