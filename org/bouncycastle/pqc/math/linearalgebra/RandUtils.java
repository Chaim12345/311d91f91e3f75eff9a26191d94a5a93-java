package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
/* loaded from: classes4.dex */
public class RandUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(SecureRandom secureRandom, int i2) {
        int nextInt;
        int i3;
        if (((-i2) & i2) == i2) {
            return (int) ((i2 * (secureRandom.nextInt() >>> 1)) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() >>> 1;
            i3 = nextInt % i2;
        } while ((nextInt - i3) + (i2 - 1) < 0);
        return i3;
    }
}
