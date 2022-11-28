package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
/* loaded from: classes3.dex */
public class KDFParameters implements DerivationParameters {

    /* renamed from: a  reason: collision with root package name */
    byte[] f13495a;

    /* renamed from: b  reason: collision with root package name */
    byte[] f13496b;

    public KDFParameters(byte[] bArr, byte[] bArr2) {
        this.f13496b = bArr;
        this.f13495a = bArr2;
    }

    public byte[] getIV() {
        return this.f13495a;
    }

    public byte[] getSharedSecret() {
        return this.f13496b;
    }
}
