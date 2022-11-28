package org.bouncycastle.crypto.params;

import org.bouncycastle.crypto.DerivationParameters;
/* loaded from: classes3.dex */
public class ISO18033KDFParameters implements DerivationParameters {

    /* renamed from: a  reason: collision with root package name */
    byte[] f13491a;

    public ISO18033KDFParameters(byte[] bArr) {
        this.f13491a = bArr;
    }

    public byte[] getSeed() {
        return this.f13491a;
    }
}
