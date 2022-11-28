package org.bouncycastle.pqc.crypto.sphincsplus;
/* loaded from: classes4.dex */
class SIG_XMSS {

    /* renamed from: a  reason: collision with root package name */
    final byte[] f14586a;

    /* renamed from: b  reason: collision with root package name */
    final byte[][] f14587b;

    public SIG_XMSS(byte[] bArr, byte[][] bArr2) {
        this.f14586a = bArr;
        this.f14587b = bArr2;
    }

    public byte[] getWOTSSig() {
        return this.f14586a;
    }

    public byte[][] getXMSSAUTH() {
        return this.f14587b;
    }
}
