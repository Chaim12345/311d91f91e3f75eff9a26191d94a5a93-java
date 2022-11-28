package org.bouncycastle.cert.jcajce;

import java.security.cert.CertificateFactory;
/* loaded from: classes3.dex */
abstract class CertHelper {
    protected abstract CertificateFactory a(String str);

    public CertificateFactory getCertificateFactory(String str) {
        return a(str);
    }
}
