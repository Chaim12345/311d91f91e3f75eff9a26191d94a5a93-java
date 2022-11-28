package org.bouncycastle.pqc.crypto.lms;

import java.util.Objects;
import org.bouncycastle.crypto.Digest;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class LmsUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(byte[] bArr, int i2, int i3, Digest digest) {
        digest.update(bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(byte[] bArr, Digest digest) {
        digest.update(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int c(LMSParameters lMSParameters) {
        Objects.requireNonNull(lMSParameters, "lmsParameters cannot be null");
        LMSigParameters lMSigParam = lMSParameters.getLMSigParam();
        return (1 << lMSigParam.getH()) * lMSigParam.getM();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(short s2, Digest digest) {
        digest.update((byte) (s2 >>> 8));
        digest.update((byte) s2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(int i2, Digest digest) {
        digest.update((byte) (i2 >>> 24));
        digest.update((byte) (i2 >>> 16));
        digest.update((byte) (i2 >>> 8));
        digest.update((byte) i2);
    }
}
