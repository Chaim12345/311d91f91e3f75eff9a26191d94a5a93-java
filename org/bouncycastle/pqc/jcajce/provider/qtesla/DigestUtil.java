package org.bouncycastle.pqc.jcajce.provider.qtesla;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Xof;
/* loaded from: classes4.dex */
class DigestUtil {
    public static byte[] getDigestResult(Digest digest) {
        int digestSize = getDigestSize(digest);
        byte[] bArr = new byte[digestSize];
        if (digest instanceof Xof) {
            ((Xof) digest).doFinal(bArr, 0, digestSize);
        } else {
            digest.doFinal(bArr, 0);
        }
        return bArr;
    }

    public static int getDigestSize(Digest digest) {
        boolean z = digest instanceof Xof;
        int digestSize = digest.getDigestSize();
        return z ? digestSize * 2 : digestSize;
    }
}
