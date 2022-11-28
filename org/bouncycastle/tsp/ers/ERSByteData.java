package org.bouncycastle.tsp.ers;

import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes4.dex */
public class ERSByteData extends ERSCachingData {
    private final byte[] content;

    public ERSByteData(byte[] bArr) {
        this.content = bArr;
    }

    @Override // org.bouncycastle.tsp.ers.ERSCachingData
    protected byte[] a(DigestCalculator digestCalculator) {
        return ERSUtil.g(digestCalculator, this.content);
    }
}
