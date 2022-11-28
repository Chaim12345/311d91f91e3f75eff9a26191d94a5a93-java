package org.bouncycastle.pqc.crypto.newhope;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class NHPublicKeyParameters extends AsymmetricKeyParameter {

    /* renamed from: b  reason: collision with root package name */
    final byte[] f14557b;

    public NHPublicKeyParameters(byte[] bArr) {
        super(false);
        this.f14557b = Arrays.clone(bArr);
    }

    public byte[] getPubData() {
        return Arrays.clone(this.f14557b);
    }
}
