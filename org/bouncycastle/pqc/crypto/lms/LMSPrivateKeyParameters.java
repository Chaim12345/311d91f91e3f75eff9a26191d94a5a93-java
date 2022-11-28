package org.bouncycastle.pqc.crypto.lms;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.WeakHashMap;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.ExhaustedPrivateKeyException;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes4.dex */
public class LMSPrivateKeyParameters extends LMSKeyParameters implements LMSContextBasedSigner {
    private static CacheKey T1;
    private static CacheKey[] internedKeys;
    private final byte[] I;
    private final byte[] masterSecret;
    private final int maxCacheR;
    private final int maxQ;
    private final LMOtsParameters otsParameters;
    private final LMSigParameters parameters;
    private LMSPublicKeyParameters publicKey;

    /* renamed from: q  reason: collision with root package name */
    private int f14511q;
    private final Map<CacheKey, byte[]> tCache;
    private final Digest tDigest;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class CacheKey {
        private final int index;

        CacheKey(int i2) {
            this.index = i2;
        }

        public boolean equals(Object obj) {
            return (obj instanceof CacheKey) && ((CacheKey) obj).index == this.index;
        }

        public int hashCode() {
            return this.index;
        }
    }

    static {
        CacheKey cacheKey = new CacheKey(1);
        T1 = cacheKey;
        CacheKey[] cacheKeyArr = new CacheKey[129];
        internedKeys = cacheKeyArr;
        cacheKeyArr[1] = cacheKey;
        int i2 = 2;
        while (true) {
            CacheKey[] cacheKeyArr2 = internedKeys;
            if (i2 >= cacheKeyArr2.length) {
                return;
            }
            cacheKeyArr2[i2] = new CacheKey(i2);
            i2++;
        }
    }

    private LMSPrivateKeyParameters(LMSPrivateKeyParameters lMSPrivateKeyParameters, int i2, int i3) {
        super(true);
        LMSigParameters lMSigParameters = lMSPrivateKeyParameters.parameters;
        this.parameters = lMSigParameters;
        this.otsParameters = lMSPrivateKeyParameters.otsParameters;
        this.f14511q = i2;
        this.I = lMSPrivateKeyParameters.I;
        this.maxQ = i3;
        this.masterSecret = lMSPrivateKeyParameters.masterSecret;
        this.maxCacheR = 1 << lMSigParameters.getH();
        this.tCache = lMSPrivateKeyParameters.tCache;
        this.tDigest = DigestUtil.a(lMSigParameters.getDigestOID());
        this.publicKey = lMSPrivateKeyParameters.publicKey;
    }

    public LMSPrivateKeyParameters(LMSigParameters lMSigParameters, LMOtsParameters lMOtsParameters, int i2, byte[] bArr, int i3, byte[] bArr2) {
        super(true);
        this.parameters = lMSigParameters;
        this.otsParameters = lMOtsParameters;
        this.f14511q = i2;
        this.I = Arrays.clone(bArr);
        this.maxQ = i3;
        this.masterSecret = Arrays.clone(bArr2);
        this.maxCacheR = 1 << (lMSigParameters.getH() + 1);
        this.tCache = new WeakHashMap();
        this.tDigest = DigestUtil.a(lMSigParameters.getDigestOID());
    }

    private byte[] calcT(int i2) {
        int h2 = 1 << getSigParameters().getH();
        if (i2 >= h2) {
            LmsUtils.b(getI(), this.tDigest);
            LmsUtils.e(i2, this.tDigest);
            LmsUtils.d((short) -32126, this.tDigest);
            LmsUtils.b(LM_OTS.a(getOtsParameters(), getI(), i2 - h2, getMasterSecret()), this.tDigest);
            byte[] bArr = new byte[this.tDigest.getDigestSize()];
            this.tDigest.doFinal(bArr, 0);
            return bArr;
        }
        int i3 = i2 * 2;
        byte[] a2 = a(i3);
        byte[] a3 = a(i3 + 1);
        LmsUtils.b(getI(), this.tDigest);
        LmsUtils.e(i2, this.tDigest);
        LmsUtils.d((short) -31869, this.tDigest);
        LmsUtils.b(a2, this.tDigest);
        LmsUtils.b(a3, this.tDigest);
        byte[] bArr2 = new byte[this.tDigest.getDigestSize()];
        this.tDigest.doFinal(bArr2, 0);
        return bArr2;
    }

    private byte[] findT(CacheKey cacheKey) {
        synchronized (this.tCache) {
            byte[] bArr = this.tCache.get(cacheKey);
            if (bArr != null) {
                return bArr;
            }
            byte[] calcT = calcT(cacheKey.index);
            this.tCache.put(cacheKey, calcT);
            return calcT;
        }
    }

    public static LMSPrivateKeyParameters getInstance(Object obj) {
        DataInputStream dataInputStream;
        if (obj instanceof LMSPrivateKeyParameters) {
            return (LMSPrivateKeyParameters) obj;
        }
        if (!(obj instanceof DataInputStream)) {
            if (!(obj instanceof byte[])) {
                if (obj instanceof InputStream) {
                    return getInstance(Streams.readAll((InputStream) obj));
                }
                throw new IllegalArgumentException("cannot parse " + obj);
            }
            DataInputStream dataInputStream2 = null;
            try {
                dataInputStream = new DataInputStream(new ByteArrayInputStream((byte[]) obj));
            } catch (Throwable th) {
                th = th;
            }
            try {
                LMSPrivateKeyParameters lMSPrivateKeyParameters = getInstance(dataInputStream);
                dataInputStream.close();
                return lMSPrivateKeyParameters;
            } catch (Throwable th2) {
                th = th2;
                dataInputStream2 = dataInputStream;
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                throw th;
            }
        }
        DataInputStream dataInputStream3 = (DataInputStream) obj;
        if (dataInputStream3.readInt() == 0) {
            LMSigParameters b2 = LMSigParameters.b(dataInputStream3.readInt());
            LMOtsParameters parametersForType = LMOtsParameters.getParametersForType(dataInputStream3.readInt());
            byte[] bArr = new byte[16];
            dataInputStream3.readFully(bArr);
            int readInt = dataInputStream3.readInt();
            int readInt2 = dataInputStream3.readInt();
            int readInt3 = dataInputStream3.readInt();
            if (readInt3 >= 0) {
                if (readInt3 <= dataInputStream3.available()) {
                    byte[] bArr2 = new byte[readInt3];
                    dataInputStream3.readFully(bArr2);
                    return new LMSPrivateKeyParameters(b2, parametersForType, readInt, bArr, readInt2, bArr2);
                }
                throw new IOException("secret length exceeded " + dataInputStream3.available());
            }
            throw new IllegalStateException("secret length less than zero");
        }
        throw new IllegalStateException("expected version 0 lms private key");
    }

    public static LMSPrivateKeyParameters getInstance(byte[] bArr, byte[] bArr2) {
        LMSPrivateKeyParameters lMSPrivateKeyParameters = getInstance(bArr);
        lMSPrivateKeyParameters.publicKey = LMSPublicKeyParameters.getInstance(bArr2);
        return lMSPrivateKeyParameters;
    }

    byte[] a(int i2) {
        if (i2 < this.maxCacheR) {
            CacheKey[] cacheKeyArr = internedKeys;
            return findT(i2 < cacheKeyArr.length ? cacheKeyArr[i2] : new CacheKey(i2));
        }
        return calcT(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMOtsPrivateKey b() {
        LMOtsPrivateKey lMOtsPrivateKey;
        synchronized (this) {
            int i2 = this.f14511q;
            if (i2 >= this.maxQ) {
                throw new ExhaustedPrivateKeyException("ots private keys expired");
            }
            lMOtsPrivateKey = new LMOtsPrivateKey(this.otsParameters, this.I, i2, this.masterSecret);
        }
        return lMOtsPrivateKey;
    }

    LMOtsPrivateKey c() {
        LMOtsPrivateKey lMOtsPrivateKey;
        synchronized (this) {
            int i2 = this.f14511q;
            if (i2 >= this.maxQ) {
                throw new ExhaustedPrivateKeyException("ots private key exhausted");
            }
            lMOtsPrivateKey = new LMOtsPrivateKey(this.otsParameters, this.I, i2, this.masterSecret);
            d();
        }
        return lMOtsPrivateKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void d() {
        this.f14511q++;
    }

    public boolean equals(Object obj) {
        LMSPublicKeyParameters lMSPublicKeyParameters;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LMSPrivateKeyParameters lMSPrivateKeyParameters = (LMSPrivateKeyParameters) obj;
        if (this.f14511q == lMSPrivateKeyParameters.f14511q && this.maxQ == lMSPrivateKeyParameters.maxQ && Arrays.areEqual(this.I, lMSPrivateKeyParameters.I)) {
            LMSigParameters lMSigParameters = this.parameters;
            if (lMSigParameters == null ? lMSPrivateKeyParameters.parameters == null : lMSigParameters.equals(lMSPrivateKeyParameters.parameters)) {
                LMOtsParameters lMOtsParameters = this.otsParameters;
                if (lMOtsParameters == null ? lMSPrivateKeyParameters.otsParameters == null : lMOtsParameters.equals(lMSPrivateKeyParameters.otsParameters)) {
                    if (Arrays.areEqual(this.masterSecret, lMSPrivateKeyParameters.masterSecret)) {
                        LMSPublicKeyParameters lMSPublicKeyParameters2 = this.publicKey;
                        if (lMSPublicKeyParameters2 == null || (lMSPublicKeyParameters = lMSPrivateKeyParameters.publicKey) == null) {
                            return true;
                        }
                        return lMSPublicKeyParameters2.equals(lMSPublicKeyParameters);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public LMSPrivateKeyParameters extractKeyShard(int i2) {
        LMSPrivateKeyParameters lMSPrivateKeyParameters;
        synchronized (this) {
            int i3 = this.f14511q;
            if (i3 + i2 >= this.maxQ) {
                throw new IllegalArgumentException("usageCount exceeds usages remaining");
            }
            lMSPrivateKeyParameters = new LMSPrivateKeyParameters(this, i3, i3 + i2);
            this.f14511q += i2;
        }
        return lMSPrivateKeyParameters;
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSContextBasedSigner
    public LMSContext generateLMSContext() {
        int h2 = getSigParameters().getH();
        int index = getIndex();
        LMOtsPrivateKey c2 = c();
        int i2 = (1 << h2) + index;
        byte[][] bArr = new byte[h2];
        for (int i3 = 0; i3 < h2; i3++) {
            bArr[i3] = a((i2 / (1 << i3)) ^ 1);
        }
        return c2.b(getSigParameters(), bArr);
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSContextBasedSigner
    public byte[] generateSignature(LMSContext lMSContext) {
        try {
            return LMS.generateSign(lMSContext).getEncoded();
        } catch (IOException e2) {
            throw new IllegalStateException("unable to encode signature: " + e2.getMessage(), e2);
        }
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSKeyParameters, org.bouncycastle.util.Encodable
    public byte[] getEncoded() {
        return Composer.compose().u32str(0).u32str(this.parameters.getType()).u32str(this.otsParameters.getType()).bytes(this.I).u32str(this.f14511q).u32str(this.maxQ).u32str(this.masterSecret.length).bytes(this.masterSecret).build();
    }

    public byte[] getI() {
        return Arrays.clone(this.I);
    }

    public synchronized int getIndex() {
        return this.f14511q;
    }

    public byte[] getMasterSecret() {
        return Arrays.clone(this.masterSecret);
    }

    public LMOtsParameters getOtsParameters() {
        return this.otsParameters;
    }

    public LMSPublicKeyParameters getPublicKey() {
        LMSPublicKeyParameters lMSPublicKeyParameters;
        synchronized (this) {
            if (this.publicKey == null) {
                this.publicKey = new LMSPublicKeyParameters(this.parameters, this.otsParameters, findT(T1), this.I);
            }
            lMSPublicKeyParameters = this.publicKey;
        }
        return lMSPublicKeyParameters;
    }

    public LMSigParameters getSigParameters() {
        return this.parameters;
    }

    @Override // org.bouncycastle.pqc.crypto.lms.LMSContextBasedSigner
    public long getUsagesRemaining() {
        return this.maxQ - this.f14511q;
    }

    public int hashCode() {
        int hashCode = ((this.f14511q * 31) + Arrays.hashCode(this.I)) * 31;
        LMSigParameters lMSigParameters = this.parameters;
        int hashCode2 = (hashCode + (lMSigParameters != null ? lMSigParameters.hashCode() : 0)) * 31;
        LMOtsParameters lMOtsParameters = this.otsParameters;
        int hashCode3 = (((((hashCode2 + (lMOtsParameters != null ? lMOtsParameters.hashCode() : 0)) * 31) + this.maxQ) * 31) + Arrays.hashCode(this.masterSecret)) * 31;
        LMSPublicKeyParameters lMSPublicKeyParameters = this.publicKey;
        return hashCode3 + (lMSPublicKeyParameters != null ? lMSPublicKeyParameters.hashCode() : 0);
    }
}
