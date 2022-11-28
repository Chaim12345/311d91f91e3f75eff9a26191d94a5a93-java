package org.bouncycastle.pqc.crypto.sphincsplus;
/* loaded from: classes4.dex */
class SIG_FORS {

    /* renamed from: a  reason: collision with root package name */
    final byte[][] f14584a;

    /* renamed from: b  reason: collision with root package name */
    final byte[] f14585b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SIG_FORS(byte[] bArr, byte[][] bArr2) {
        this.f14584a = bArr2;
        this.f14585b = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a() {
        return this.f14585b;
    }

    public byte[][] getAuthPath() {
        return this.f14584a;
    }
}
