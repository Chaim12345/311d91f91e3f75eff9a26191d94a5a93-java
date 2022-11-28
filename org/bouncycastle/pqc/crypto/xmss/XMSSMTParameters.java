package org.bouncycastle.pqc.crypto.xmss;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Integers;
/* loaded from: classes4.dex */
public final class XMSSMTParameters {
    private static final Map<Integer, XMSSMTParameters> paramsLookupTable;
    private final int height;
    private final int layers;
    private final XMSSOid oid;
    private final XMSSParameters xmssParams;

    static {
        HashMap hashMap = new HashMap();
        Integer valueOf = Integers.valueOf(1);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = NISTObjectIdentifiers.id_sha256;
        hashMap.put(valueOf, new XMSSMTParameters(20, 2, aSN1ObjectIdentifier));
        hashMap.put(Integers.valueOf(2), new XMSSMTParameters(20, 4, aSN1ObjectIdentifier));
        hashMap.put(Integers.valueOf(3), new XMSSMTParameters(40, 2, aSN1ObjectIdentifier));
        hashMap.put(Integers.valueOf(4), new XMSSMTParameters(40, 4, aSN1ObjectIdentifier));
        hashMap.put(Integers.valueOf(5), new XMSSMTParameters(40, 8, aSN1ObjectIdentifier));
        hashMap.put(Integers.valueOf(6), new XMSSMTParameters(60, 3, aSN1ObjectIdentifier));
        hashMap.put(Integers.valueOf(7), new XMSSMTParameters(60, 6, aSN1ObjectIdentifier));
        hashMap.put(Integers.valueOf(8), new XMSSMTParameters(60, 12, aSN1ObjectIdentifier));
        Integer valueOf2 = Integers.valueOf(9);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_sha512;
        hashMap.put(valueOf2, new XMSSMTParameters(20, 2, aSN1ObjectIdentifier2));
        hashMap.put(Integers.valueOf(10), new XMSSMTParameters(20, 4, aSN1ObjectIdentifier2));
        hashMap.put(Integers.valueOf(11), new XMSSMTParameters(40, 2, aSN1ObjectIdentifier2));
        hashMap.put(Integers.valueOf(12), new XMSSMTParameters(40, 4, aSN1ObjectIdentifier2));
        hashMap.put(Integers.valueOf(13), new XMSSMTParameters(40, 8, aSN1ObjectIdentifier2));
        hashMap.put(Integers.valueOf(14), new XMSSMTParameters(60, 3, aSN1ObjectIdentifier2));
        hashMap.put(Integers.valueOf(15), new XMSSMTParameters(60, 6, aSN1ObjectIdentifier2));
        hashMap.put(Integers.valueOf(16), new XMSSMTParameters(60, 12, aSN1ObjectIdentifier2));
        Integer valueOf3 = Integers.valueOf(17);
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = NISTObjectIdentifiers.id_shake128;
        hashMap.put(valueOf3, new XMSSMTParameters(20, 2, aSN1ObjectIdentifier3));
        hashMap.put(Integers.valueOf(18), new XMSSMTParameters(20, 4, aSN1ObjectIdentifier3));
        hashMap.put(Integers.valueOf(19), new XMSSMTParameters(40, 2, aSN1ObjectIdentifier3));
        hashMap.put(Integers.valueOf(20), new XMSSMTParameters(40, 4, aSN1ObjectIdentifier3));
        hashMap.put(Integers.valueOf(21), new XMSSMTParameters(40, 8, aSN1ObjectIdentifier3));
        hashMap.put(Integers.valueOf(22), new XMSSMTParameters(60, 3, aSN1ObjectIdentifier3));
        hashMap.put(Integers.valueOf(23), new XMSSMTParameters(60, 6, aSN1ObjectIdentifier3));
        hashMap.put(Integers.valueOf(24), new XMSSMTParameters(60, 12, aSN1ObjectIdentifier3));
        Integer valueOf4 = Integers.valueOf(25);
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = NISTObjectIdentifiers.id_shake256;
        hashMap.put(valueOf4, new XMSSMTParameters(20, 2, aSN1ObjectIdentifier4));
        hashMap.put(Integers.valueOf(26), new XMSSMTParameters(20, 4, aSN1ObjectIdentifier4));
        hashMap.put(Integers.valueOf(27), new XMSSMTParameters(40, 2, aSN1ObjectIdentifier4));
        hashMap.put(Integers.valueOf(28), new XMSSMTParameters(40, 4, aSN1ObjectIdentifier4));
        hashMap.put(Integers.valueOf(29), new XMSSMTParameters(40, 8, aSN1ObjectIdentifier4));
        hashMap.put(Integers.valueOf(30), new XMSSMTParameters(60, 3, aSN1ObjectIdentifier4));
        hashMap.put(Integers.valueOf(31), new XMSSMTParameters(60, 6, aSN1ObjectIdentifier4));
        hashMap.put(Integers.valueOf(32), new XMSSMTParameters(60, 12, aSN1ObjectIdentifier4));
        paramsLookupTable = Collections.unmodifiableMap(hashMap);
    }

    public XMSSMTParameters(int i2, int i3, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.height = i2;
        this.layers = i3;
        this.xmssParams = new XMSSParameters(xmssTreeHeight(i2, i3), aSN1ObjectIdentifier);
        this.oid = DefaultXMSSMTOid.lookup(c(), getTreeDigestSize(), e(), a(), getHeight(), i3);
    }

    public XMSSMTParameters(int i2, int i3, Digest digest) {
        this(i2, i3, DigestUtil.c(digest.getAlgorithmName()));
    }

    public static XMSSMTParameters lookupByOID(int i2) {
        return paramsLookupTable.get(Integers.valueOf(i2));
    }

    private static int xmssTreeHeight(int i2, int i3) {
        if (i2 >= 2) {
            if (i2 % i3 == 0) {
                int i4 = i2 / i3;
                if (i4 != 1) {
                    return i4;
                }
                throw new IllegalArgumentException("height / layers must be greater than 1");
            }
            throw new IllegalArgumentException("layers must divide totalHeight without remainder");
        }
        throw new IllegalArgumentException("totalHeight must be > 1");
    }

    protected int a() {
        return this.xmssParams.b();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XMSSOid b() {
        return this.oid;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String c() {
        return this.xmssParams.d();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WOTSPlus d() {
        return this.xmssParams.e();
    }

    int e() {
        return this.xmssParams.f();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XMSSParameters f() {
        return this.xmssParams;
    }

    public int getHeight() {
        return this.height;
    }

    public int getLayers() {
        return this.layers;
    }

    public ASN1ObjectIdentifier getTreeDigestOID() {
        return this.xmssParams.getTreeDigestOID();
    }

    public int getTreeDigestSize() {
        return this.xmssParams.getTreeDigestSize();
    }
}
