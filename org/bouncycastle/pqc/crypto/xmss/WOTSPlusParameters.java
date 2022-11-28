package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.Digest;
/* loaded from: classes4.dex */
final class WOTSPlusParameters {
    private final int digestSize;
    private final int len;
    private final int len1;
    private final int len2;
    private final XMSSOid oid;
    private final ASN1ObjectIdentifier treeDigest;
    private final int winternitzParameter;

    /* JADX INFO: Access modifiers changed from: protected */
    public WOTSPlusParameters(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        Objects.requireNonNull(aSN1ObjectIdentifier, "treeDigest == null");
        this.treeDigest = aSN1ObjectIdentifier;
        Digest a2 = DigestUtil.a(aSN1ObjectIdentifier);
        int digestSize = XMSSUtil.getDigestSize(a2);
        this.digestSize = digestSize;
        this.winternitzParameter = 16;
        int ceil = (int) Math.ceil((digestSize * 8) / XMSSUtil.log2(16));
        this.len1 = ceil;
        int floor = ((int) Math.floor(XMSSUtil.log2((16 - 1) * ceil) / XMSSUtil.log2(16))) + 1;
        this.len2 = floor;
        int i2 = ceil + floor;
        this.len = i2;
        WOTSPlusOid a3 = WOTSPlusOid.a(a2.getAlgorithmName(), digestSize, 16, i2);
        this.oid = a3;
        if (a3 != null) {
            return;
        }
        throw new IllegalArgumentException("cannot find OID for digest algorithm: " + a2.getAlgorithmName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int a() {
        return this.len;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int b() {
        return this.len1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int c() {
        return this.len2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int d() {
        return this.digestSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int e() {
        return this.winternitzParameter;
    }

    public ASN1ObjectIdentifier getTreeDigest() {
        return this.treeDigest;
    }
}
