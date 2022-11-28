package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
/* loaded from: classes3.dex */
public class MGFParameters implements DerivationParameters {

    /* renamed from: a  reason: collision with root package name */
    byte[] f13497a;

    public MGFParameters(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    public MGFParameters(byte[] bArr, int i2, int i3) {
        byte[] bArr2 = new byte[i3];
        this.f13497a = bArr2;
        System.arraycopy(bArr, i2, bArr2, 0, i3);
    }

    public byte[] getSeed() {
        return this.f13497a;
    }
}
