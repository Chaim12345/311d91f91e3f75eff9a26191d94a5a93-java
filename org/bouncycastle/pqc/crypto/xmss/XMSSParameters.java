package org.bouncycastle.pqc.crypto.xmss;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Integers;
/* loaded from: classes4.dex */
public final class XMSSParameters {
    private static final Map<Integer, XMSSParameters> paramsLookupTable;
    private final int height;

    /* renamed from: k  reason: collision with root package name */
    private final int f14616k;
    private final XMSSOid oid;
    private final String treeDigest;
    private final ASN1ObjectIdentifier treeDigestOID;
    private final int treeDigestSize;
    private final int winternitzParameter;
    private final WOTSPlusParameters wotsPlusParams;

    static {
        HashMap hashMap = new HashMap();
        Integer valueOf = Integers.valueOf(1);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = NISTObjectIdentifiers.id_sha256;
        hashMap.put(valueOf, new XMSSParameters(10, aSN1ObjectIdentifier));
        hashMap.put(Integers.valueOf(2), new XMSSParameters(16, aSN1ObjectIdentifier));
        hashMap.put(Integers.valueOf(3), new XMSSParameters(20, aSN1ObjectIdentifier));
        Integer valueOf2 = Integers.valueOf(4);
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_sha512;
        hashMap.put(valueOf2, new XMSSParameters(10, aSN1ObjectIdentifier2));
        hashMap.put(Integers.valueOf(5), new XMSSParameters(16, aSN1ObjectIdentifier2));
        hashMap.put(Integers.valueOf(6), new XMSSParameters(20, aSN1ObjectIdentifier2));
        Integer valueOf3 = Integers.valueOf(7);
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = NISTObjectIdentifiers.id_shake128;
        hashMap.put(valueOf3, new XMSSParameters(10, aSN1ObjectIdentifier3));
        hashMap.put(Integers.valueOf(8), new XMSSParameters(16, aSN1ObjectIdentifier3));
        hashMap.put(Integers.valueOf(9), new XMSSParameters(20, aSN1ObjectIdentifier3));
        Integer valueOf4 = Integers.valueOf(10);
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = NISTObjectIdentifiers.id_shake256;
        hashMap.put(valueOf4, new XMSSParameters(10, aSN1ObjectIdentifier4));
        hashMap.put(Integers.valueOf(11), new XMSSParameters(16, aSN1ObjectIdentifier4));
        hashMap.put(Integers.valueOf(12), new XMSSParameters(20, aSN1ObjectIdentifier4));
        paramsLookupTable = Collections.unmodifiableMap(hashMap);
    }

    public XMSSParameters(int i2, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (i2 < 2) {
            throw new IllegalArgumentException("height must be >= 2");
        }
        Objects.requireNonNull(aSN1ObjectIdentifier, "digest == null");
        this.height = i2;
        this.f14616k = determineMinK();
        String b2 = DigestUtil.b(aSN1ObjectIdentifier);
        this.treeDigest = b2;
        this.treeDigestOID = aSN1ObjectIdentifier;
        WOTSPlusParameters wOTSPlusParameters = new WOTSPlusParameters(aSN1ObjectIdentifier);
        this.wotsPlusParams = wOTSPlusParameters;
        int d2 = wOTSPlusParameters.d();
        this.treeDigestSize = d2;
        int e2 = wOTSPlusParameters.e();
        this.winternitzParameter = e2;
        this.oid = DefaultXMSSOid.lookup(b2, d2, e2, wOTSPlusParameters.a(), i2);
    }

    public XMSSParameters(int i2, Digest digest) {
        this(i2, DigestUtil.c(digest.getAlgorithmName()));
    }

    private int determineMinK() {
        int i2 = 2;
        while (true) {
            int i3 = this.height;
            if (i2 > i3) {
                throw new IllegalStateException("should never happen...");
            }
            if ((i3 - i2) % 2 == 0) {
                return i2;
            }
            i2++;
        }
    }

    public static XMSSParameters lookupByOID(int i2) {
        return paramsLookupTable.get(Integers.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        return this.f14616k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        return this.wotsPlusParams.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XMSSOid c() {
        return this.oid;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String d() {
        return this.treeDigest;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WOTSPlus e() {
        return new WOTSPlus(this.wotsPlusParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.winternitzParameter;
    }

    public int getHeight() {
        return this.height;
    }

    public ASN1ObjectIdentifier getTreeDigestOID() {
        return this.treeDigestOID;
    }

    public int getTreeDigestSize() {
        return this.treeDigestSize;
    }
}
