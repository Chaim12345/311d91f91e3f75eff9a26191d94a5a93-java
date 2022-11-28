package org.bouncycastle.x509;

import java.security.cert.CertificateEncodingException;
/* loaded from: classes4.dex */
class ExtCertificateEncodingException extends CertificateEncodingException {

    /* renamed from: a  reason: collision with root package name */
    Throwable f15138a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExtCertificateEncodingException(String str, Throwable th) {
        super(str);
        this.f15138a = th;
    }

    @Override // java.lang.Throwable
    public Throwable getCause() {
        return this.f15138a;
    }
}
