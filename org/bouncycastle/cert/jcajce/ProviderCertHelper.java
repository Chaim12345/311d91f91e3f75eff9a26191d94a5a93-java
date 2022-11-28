package org.bouncycastle.cert.jcajce;

import java.security.Provider;
import java.security.cert.CertificateFactory;
/* loaded from: classes3.dex */
class ProviderCertHelper extends CertHelper {
    private final Provider provider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProviderCertHelper(Provider provider) {
        this.provider = provider;
    }

    @Override // org.bouncycastle.cert.jcajce.CertHelper
    protected CertificateFactory a(String str) {
        return CertificateFactory.getInstance(str, this.provider);
    }
}
