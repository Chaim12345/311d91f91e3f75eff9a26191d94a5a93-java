package org.bouncycastle.cert.jcajce;

import java.security.cert.CertificateFactory;
/* loaded from: classes3.dex */
class NamedCertHelper extends CertHelper {
    private final String providerName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NamedCertHelper(String str) {
        this.providerName = str;
    }

    @Override // org.bouncycastle.cert.jcajce.CertHelper
    protected CertificateFactory a(String str) {
        return CertificateFactory.getInstance(str, this.providerName);
    }
}
