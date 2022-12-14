package org.bouncycastle.cert.jcajce;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.cert.X509CertificateHolder;
/* loaded from: classes3.dex */
public class JcaX509CertificateConverter {
    private CertHelper helper;

    /* loaded from: classes3.dex */
    private class ExCertificateException extends CertificateException {
        private Throwable cause;

        public ExCertificateException(JcaX509CertificateConverter jcaX509CertificateConverter, String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    /* loaded from: classes3.dex */
    private class ExCertificateParsingException extends CertificateParsingException {
        private Throwable cause;

        public ExCertificateParsingException(JcaX509CertificateConverter jcaX509CertificateConverter, String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    public JcaX509CertificateConverter() {
        this.helper = new DefaultCertHelper();
        this.helper = new DefaultCertHelper();
    }

    public X509Certificate getCertificate(X509CertificateHolder x509CertificateHolder) {
        try {
            return (X509Certificate) this.helper.getCertificateFactory("X.509").generateCertificate(new ByteArrayInputStream(x509CertificateHolder.getEncoded()));
        } catch (IOException e2) {
            throw new ExCertificateParsingException(this, "exception parsing certificate: " + e2.getMessage(), e2);
        } catch (NoSuchProviderException e3) {
            throw new ExCertificateException(this, "cannot find required provider:" + e3.getMessage(), e3);
        }
    }

    public JcaX509CertificateConverter setProvider(String str) {
        this.helper = new NamedCertHelper(str);
        return this;
    }

    public JcaX509CertificateConverter setProvider(Provider provider) {
        this.helper = new ProviderCertHelper(provider);
        return this;
    }
}
