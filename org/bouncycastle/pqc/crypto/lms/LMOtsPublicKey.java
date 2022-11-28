package org.bouncycastle.pqc.crypto.lms;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Arrays;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.io.Streams;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class LMOtsPublicKey implements Encodable {
    private final byte[] I;
    private final byte[] K;
    private final LMOtsParameters parameter;

    /* renamed from: q  reason: collision with root package name */
    private final int f14509q;

    public LMOtsPublicKey(LMOtsParameters lMOtsParameters, byte[] bArr, int i2, byte[] bArr2) {
        this.parameter = lMOtsParameters;
        this.I = bArr;
        this.f14509q = i2;
        this.K = bArr2;
    }

    public static LMOtsPublicKey getInstance(Object obj) {
        if (obj instanceof LMOtsPublicKey) {
            return (LMOtsPublicKey) obj;
        }
        if (obj instanceof DataInputStream) {
            DataInputStream dataInputStream = (DataInputStream) obj;
            LMOtsParameters parametersForType = LMOtsParameters.getParametersForType(dataInputStream.readInt());
            byte[] bArr = new byte[16];
            dataInputStream.readFully(bArr);
            int readInt = dataInputStream.readInt();
            byte[] bArr2 = new byte[parametersForType.getN()];
            dataInputStream.readFully(bArr2);
            return new LMOtsPublicKey(parametersForType, bArr, readInt, bArr2);
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
                    LMOtsPublicKey lMOtsPublicKey = getInstance(dataInputStream3);
                    dataInputStream3.close();
                    return lMOtsPublicKey;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMSContext a(LMOtsSignature lMOtsSignature) {
        Digest a2 = DigestUtil.a(this.parameter.getDigestOID());
        LmsUtils.b(this.I, a2);
        LmsUtils.e(this.f14509q, a2);
        LmsUtils.d((short) -32383, a2);
        LmsUtils.b(lMOtsSignature.getC(), a2);
        return new LMSContext(this, lMOtsSignature, a2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LMSContext b(LMSSignature lMSSignature) {
        Digest a2 = DigestUtil.a(this.parameter.getDigestOID());
        LmsUtils.b(this.I, a2);
        LmsUtils.e(this.f14509q, a2);
        LmsUtils.d((short) -32383, a2);
        LmsUtils.b(lMSSignature.getOtsSignature().getC(), a2);
        return new LMSContext(this, lMSSignature, a2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LMOtsPublicKey lMOtsPublicKey = (LMOtsPublicKey) obj;
        if (this.f14509q != lMOtsPublicKey.f14509q) {
            return false;
        }
        LMOtsParameters lMOtsParameters = this.parameter;
        if (lMOtsParameters == null ? lMOtsPublicKey.parameter == null : lMOtsParameters.equals(lMOtsPublicKey.parameter)) {
            if (Arrays.equals(this.I, lMOtsPublicKey.I)) {
                return Arrays.equals(this.K, lMOtsPublicKey.K);
            }
            return false;
        }
        return false;
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() {
        return Composer.compose().u32str(this.parameter.getType()).bytes(this.I).u32str(this.f14509q).bytes(this.K).build();
    }

    public byte[] getI() {
        return this.I;
    }

    public byte[] getK() {
        return this.K;
    }

    public LMOtsParameters getParameter() {
        return this.parameter;
    }

    public int getQ() {
        return this.f14509q;
    }

    public int hashCode() {
        LMOtsParameters lMOtsParameters = this.parameter;
        return ((((((lMOtsParameters != null ? lMOtsParameters.hashCode() : 0) * 31) + Arrays.hashCode(this.I)) * 31) + this.f14509q) * 31) + Arrays.hashCode(this.K);
    }
}
