package org.bouncycastle.cert.jcajce;

import java.security.cert.CertificateFactory;
/* loaded from: classes3.dex */
class DefaultCertHelper extends CertHelper {
    @Override // org.bouncycastle.cert.jcajce.CertHelper
    protected CertificateFactory a(String str) {
        return CertificateFactory.getInstance(str);
    }
}
