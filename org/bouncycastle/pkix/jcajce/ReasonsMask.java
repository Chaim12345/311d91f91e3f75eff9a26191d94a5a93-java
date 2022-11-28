package org.bouncycastle.pkix.jcajce;

import org.bouncycastle.asn1.x509.ReasonFlags;
/* loaded from: classes4.dex */
class ReasonsMask {

    /* renamed from: a  reason: collision with root package name */
    static final ReasonsMask f14478a = new ReasonsMask(33023);
    private int _reasons;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReasonsMask() {
        this(0);
    }

    private ReasonsMask(int i2) {
        this._reasons = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReasonsMask(ReasonFlags reasonFlags) {
        this._reasons = reasonFlags.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(ReasonsMask reasonsMask) {
        this._reasons = reasonsMask.b() | this._reasons;
    }

    int b() {
        return this._reasons;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(ReasonsMask reasonsMask) {
        return ((reasonsMask.b() ^ this._reasons) | this._reasons) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReasonsMask d(ReasonsMask reasonsMask) {
        ReasonsMask reasonsMask2 = new ReasonsMask();
        reasonsMask2.a(new ReasonsMask(reasonsMask.b() & this._reasons));
        return reasonsMask2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e() {
        return this._reasons == f14478a._reasons;
    }
}
