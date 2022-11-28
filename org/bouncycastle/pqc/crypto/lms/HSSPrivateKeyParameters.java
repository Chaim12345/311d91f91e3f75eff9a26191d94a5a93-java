package org.bouncycastle.pqc.crypto.lms;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes4.dex */
public class HSSPrivateKeyParameters extends LMSKeyParameters implements LMSContextBasedSigner {
    private long index;
    private final long indexLimit;
    private final boolean isShard;
    private List<LMSPrivateKeyParameters> keys;

    /* renamed from: l  reason: collision with root package name */
    private final int f14504l;
    private HSSPublicKeyParameters publicKey;
    private List<LMSSignature> sig;

    public HSSPrivateKeyParameters(int i2, List<LMSPrivateKeyParameters> list, List<LMSSignature> list2, long j2, long j3) {
        super(true);
        this.index = 0L;
        this.f14504l = i2;
        this.keys = Collections.unmodifiableList(list);
        this.sig = Collections.unmodifiableList(list2);
        this.index = j2;
        this.indexLimit = j3;
        this.isShard = false;
        h();
    }

    private HSSPrivateKeyParameters(int i2, List<LMSPrivateKeyParameters> list, List<LMSSignature> list2, long j2, long j3, boolean z) {
        super(true);
        this.index = 0L;
        this.f14504l = i2;
        this.keys = Collections.unmodifiableList(list);
        this.sig = Collections.unmodifiableList(list2);
        this.index = j2;
        this.indexLimit = j3;
        this.isShard = z;
    }

    public static HSSPrivateKeyParameters getInstance(Object obj) {
        if (obj instanceof HSSPrivateKeyParameters) {
            return (HSSPrivateKeyParameters) obj;
        }
        if (obj instanceof DataInputStream) {
            DataInputStream dataInputStream = (DataInputStream) obj;
            if (dataInputStream.readInt() == 0) {
                int readInt = dataInputStream.readInt();
                long readLong = dataInputStream.readLong();
                long readLong2 = dataInputStream.readLong();
                boolean readBoolean = dataInputStream.readBoolean();
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < readInt; i2++) {
                    arrayList.add(LMSPrivateKeyParameters.getInstance(obj));
                }
                for (int i3 = 0; i3 < readInt - 1; i3++) {
                    arrayList2.add(LMSSignature.getInstance(obj));
                }
                return new HSSPrivateKeyParameters(readInt, arrayList, arrayList2, readLong, readLong2, readBoolean);
            }
            throw new IllegalStateException("unknown version for hss private key");
        } else if (!(obj instanceof byte[])) {
            if (obj instanceof InputStream) {
                return getInstance(Streams.readAll((InputStream) obj));
            }
            throw new IllegalArgumentException("cannot parse " + obj);
        } else {
            DataInputStream dataInputStream2 = null;
            try {
                DataInputStream dataInputStream3 = new DataInputStream(new ByteArrayInputStream((byte[]) obj));
                try {
                    HSSPrivateKeyParameters hSSPrivateKeyParameters = getInstance(dataInputStream3);
                    dataInputStream3.close();
                    return hSSPrivateKeyParameters;
                } catch (Throwable th) {
                    th = th;
                    dataInputStream2 = dataInputStream3;
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public static HSSPrivateKeyParameters getInstance(byte[] bArr, byte[] bArr2) {
        HSSPrivateKeyParameters hSSPrivateKeyParameters = getInstance(bArr);
        hSSPrivateKeyParameters.publicKey = HSSPublicKeyParameters.getInstance(bArr2);
        return hSSPrivateKeyParameters;
    }

    private static HSSPrivateKeyParameters makeCopy(HSSPrivateKeyParameters hSSPrivateKeyParameters) {
        try {
            return getInstance(hSSPrivateKeyParameters.getEncoded());
        } catch (Exception e2) {
            throw new RuntimeException(e2.getMessage(), e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a() {
        return this.indexLimit;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized List b() {
        return this.keys;
    }

    LMSPrivateKeyParameters c() {
        return this.keys.get(0);
    }

    protected Object clone() {
        return makeCopy(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized List d() {
        return this.sig;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void e() {
        this.index++;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HSSPrivateKeyParameters hSSPrivateKeyParameters = (HSSPrivateKeyParameters) obj;
        if (this.f14504l == hSSPrivateKeyParameters.f14504l && this.isShard == hSSPrivateKeyParameters.isShard && this.indexLimit == hSSPrivateKeyParameters.indexLimit && this.index == hSSPrivateKeyParameters.index && this.keys.equals(hSSPrivateKeyParameters.keys)) {
            return this.sig.equals(hSSPrivateKeyParameters.sig);
        }
        return false;
    }

    public HSSPrivateKeyParameters extractKeyShard(int i2) {
        HSSPrivateKeyParameters makeCopy;
        synchronized (this) {
            long j2 = i2;
            if (getUsagesRemaining() < j2) {
                throw new IllegalArgumentException("usageCount exceeds usages remaining in current leaf");
            }
            long j3 = this.index;
            this.index = j2 + j3;
            makeCopy = makeCopy(new HSSPrivateKeyParameters(this.f14504l, new ArrayList(b()), new ArrayList(d()), j3, j3 + j2, true));
            h();
        }
        return makeCopy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f() {
        return this.isShard;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(int i2) {
        int i3 = i2 - 1;
        SeedDerive a2 = this.keys.get(i3).b().a();
        a2.setJ(-2);
        byte[] bArr = new byte[32];
        a2.deriveSeed(bArr, true);
        byte[] bArr2 = new byte[32];
        a2.deriveSeed(bArr2, false);
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr2, 0, bArr3, 0, 16);
        ArrayList arrayList = new ArrayList(this.keys);
        LMSPrivateKeyParameters lMSPrivateKeyParameters = this.keys.get(i2);
        arrayList.set(i2, LMS.generateKeys(lMSPrivateKeyParameters.getSigParameters(), lMSPrivateKeyParameters.getOtsParameters(), 0, bArr3, bArr));
        ArrayList arrayList2 = new ArrayList(this.sig);
        arrayList2.set(i3, LMS.generateSign((LMSPrivateKeyParameters) arrayList.get(i3), ((LMSPrivateKeyParameters) arrayList.get(i2)).getPublicKey().toByteArray()));
        this.keys = Collections.unmodifiableList(arrayList);
        this.sig = Collections.unmodifiableList(arrayList2);
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSContextBasedSigner
    public LMSContext generateLMSContext() {
        LMSPrivateKeyParameters lMSPrivateKeyParameters;
        LMSSignedPubKey[] lMSSignedPubKeyArr;
        int l2 = getL();
        synchronized (this) {
            HSS.a(this);
            List b2 = b();
            List d2 = d();
            int i2 = l2 - 1;
            lMSPrivateKeyParameters = (LMSPrivateKeyParameters) b().get(i2);
            int i3 = 0;
            lMSSignedPubKeyArr = new LMSSignedPubKey[i2];
            while (i3 < i2) {
                int i4 = i3 + 1;
                lMSSignedPubKeyArr[i3] = new LMSSignedPubKey((LMSSignature) d2.get(i3), ((LMSPrivateKeyParameters) b2.get(i4)).getPublicKey());
                i3 = i4;
            }
            e();
        }
        return lMSPrivateKeyParameters.generateLMSContext().g(lMSSignedPubKeyArr);
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSContextBasedSigner
    public byte[] generateSignature(LMSContext lMSContext) {
        try {
            return HSS.generateSignature(getL(), lMSContext).getEncoded();
        } catch (IOException e2) {
            throw new IllegalStateException("unable to encode signature: " + e2.getMessage(), e2);
        }
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSKeyParameters, org.bouncycastle.util.Encodable
    public synchronized byte[] getEncoded() {
        Composer bool;
        bool = Composer.compose().u32str(0).u32str(this.f14504l).u64str(this.index).u64str(this.indexLimit).bool(this.isShard);
        for (LMSPrivateKeyParameters lMSPrivateKeyParameters : this.keys) {
            bool.bytes(lMSPrivateKeyParameters);
        }
        for (LMSSignature lMSSignature : this.sig) {
            bool.bytes(lMSSignature);
        }
        return bool.build();
    }

    public synchronized long getIndex() {
        return this.index;
    }

    public int getL() {
        return this.f14504l;
    }

    public synchronized LMSParameters[] getLMSParameters() {
        LMSParameters[] lMSParametersArr;
        int size = this.keys.size();
        lMSParametersArr = new LMSParameters[size];
        for (int i2 = 0; i2 < size; i2++) {
            LMSPrivateKeyParameters lMSPrivateKeyParameters = this.keys.get(i2);
            lMSParametersArr[i2] = new LMSParameters(lMSPrivateKeyParameters.getSigParameters(), lMSPrivateKeyParameters.getOtsParameters());
        }
        return lMSParametersArr;
    }

    public synchronized HSSPublicKeyParameters getPublicKey() {
        return new HSSPublicKeyParameters(this.f14504l, c().getPublicKey());
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSContextBasedSigner
    public long getUsagesRemaining() {
        return this.indexLimit - this.index;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x00d1, code lost:
        if (r3[r9] == (r4[r9].getIndex() - 1)) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void h() {
        boolean z;
        List b2 = b();
        int size = b2.size();
        long[] jArr = new long[size];
        long index = getIndex();
        for (int size2 = b2.size() - 1; size2 >= 0; size2--) {
            LMSigParameters sigParameters = ((LMSPrivateKeyParameters) b2.get(size2)).getSigParameters();
            jArr[size2] = ((1 << sigParameters.getH()) - 1) & index;
            index >>>= sigParameters.getH();
        }
        LMSPrivateKeyParameters[] lMSPrivateKeyParametersArr = (LMSPrivateKeyParameters[]) b2.toArray(new LMSPrivateKeyParameters[b2.size()]);
        List<LMSSignature> list = this.sig;
        LMSSignature[] lMSSignatureArr = (LMSSignature[]) list.toArray(new LMSSignature[list.size()]);
        LMSPrivateKeyParameters c2 = c();
        if (lMSPrivateKeyParametersArr[0].getIndex() - 1 != jArr[0]) {
            lMSPrivateKeyParametersArr[0] = LMS.generateKeys(c2.getSigParameters(), c2.getOtsParameters(), (int) jArr[0], c2.getI(), c2.getMasterSecret());
            z = true;
        } else {
            z = false;
        }
        int i2 = 1;
        while (i2 < size) {
            int i3 = i2 - 1;
            LMSPrivateKeyParameters lMSPrivateKeyParameters = lMSPrivateKeyParametersArr[i3];
            byte[] bArr = new byte[16];
            byte[] bArr2 = new byte[32];
            SeedDerive seedDerive = new SeedDerive(lMSPrivateKeyParameters.getI(), lMSPrivateKeyParameters.getMasterSecret(), DigestUtil.a(lMSPrivateKeyParameters.getOtsParameters().getDigestOID()));
            seedDerive.setQ((int) jArr[i3]);
            seedDerive.setJ(-2);
            seedDerive.deriveSeed(bArr2, true);
            byte[] bArr3 = new byte[32];
            boolean z2 = false;
            seedDerive.deriveSeed(bArr3, false);
            System.arraycopy(bArr3, 0, bArr, 0, 16);
            if (i2 >= size - 1) {
                if (jArr[i2] != lMSPrivateKeyParametersArr[i2].getIndex()) {
                    z2 = false;
                }
                z2 = true;
            }
            if (!(Arrays.areEqual(bArr, lMSPrivateKeyParametersArr[i2].getI()) && Arrays.areEqual(bArr2, lMSPrivateKeyParametersArr[i2].getMasterSecret()))) {
                lMSPrivateKeyParametersArr[i2] = LMS.generateKeys(((LMSPrivateKeyParameters) b2.get(i2)).getSigParameters(), ((LMSPrivateKeyParameters) b2.get(i2)).getOtsParameters(), (int) jArr[i2], bArr, bArr2);
                lMSSignatureArr[i3] = LMS.generateSign(lMSPrivateKeyParametersArr[i3], lMSPrivateKeyParametersArr[i2].getPublicKey().toByteArray());
            } else if (z2) {
                i2++;
            } else {
                lMSPrivateKeyParametersArr[i2] = LMS.generateKeys(((LMSPrivateKeyParameters) b2.get(i2)).getSigParameters(), ((LMSPrivateKeyParameters) b2.get(i2)).getOtsParameters(), (int) jArr[i2], bArr, bArr2);
            }
            z = true;
            i2++;
        }
        if (z) {
            i(lMSPrivateKeyParametersArr, lMSSignatureArr);
        }
    }

    public int hashCode() {
        long j2 = this.indexLimit;
        long j3 = this.index;
        return (((((((((this.f14504l * 31) + (this.isShard ? 1 : 0)) * 31) + this.keys.hashCode()) * 31) + this.sig.hashCode()) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)));
    }

    protected void i(LMSPrivateKeyParameters[] lMSPrivateKeyParametersArr, LMSSignature[] lMSSignatureArr) {
        synchronized (this) {
            this.keys = Collections.unmodifiableList(java.util.Arrays.asList(lMSPrivateKeyParametersArr));
            this.sig = Collections.unmodifiableList(java.util.Arrays.asList(lMSSignatureArr));
        }
    }
}
