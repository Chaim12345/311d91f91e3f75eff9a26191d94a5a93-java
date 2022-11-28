package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class SPHINCSPlusPrivateKeyParameters extends SPHINCSPlusKeyParameters {

    /* renamed from: f  reason: collision with root package name */
    final SK f14604f;

    /* renamed from: i  reason: collision with root package name */
    final PK f14605i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, SK sk, PK pk) {
        super(true, sPHINCSPlusParameters);
        this.f14604f = sk;
        this.f14605i = pk;
    }

    public SPHINCSPlusPrivateKeyParameters(SPHINCSPlusParameters sPHINCSPlusParameters, byte[] bArr) {
        super(true, sPHINCSPlusParameters);
        int i2 = sPHINCSPlusParameters.a().f14591b;
        int i3 = i2 * 4;
        if (bArr.length != i3) {
            throw new IllegalArgumentException("private key encoding does not match parameters");
        }
        int i4 = i2 * 2;
        this.f14604f = new SK(Arrays.copyOfRange(bArr, 0, i2), Arrays.copyOfRange(bArr, i2, i4));
        int i5 = i2 * 3;
        this.f14605i = new PK(Arrays.copyOfRange(bArr, i4, i5), Arrays.copyOfRange(bArr, i5, i3));
    }

    public byte[] getEncoded() {
        SK sk = this.f14604f;
        byte[] bArr = sk.f14588a;
        byte[] bArr2 = sk.f14589b;
        PK pk = this.f14605i;
        return Arrays.concatenate(bArr, bArr2, pk.f14581a, pk.f14582b);
    }

    public byte[] getPrf() {
        return Arrays.clone(this.f14604f.f14589b);
    }

    public byte[] getPublicKey() {
        PK pk = this.f14605i;
        return Arrays.concatenate(pk.f14581a, pk.f14582b);
    }

    public byte[] getPublicSeed() {
        return Arrays.clone(this.f14605i.f14581a);
    }

    public byte[] getSeed() {
        return Arrays.clone(this.f14604f.f14588a);
    }
}
