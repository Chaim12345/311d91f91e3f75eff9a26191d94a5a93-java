package org.bouncycastle.cert.jcajce;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.X509CRL;
import org.bouncycastle.cert.X509CRLHolder;
/* loaded from: classes3.dex */
public class JcaX509CRLConverter {
    private CertHelper helper;

    /* loaded from: classes3.dex */
    private class ExCRLException extends CRLException {
        private Throwable cause;

        public ExCRLException(JcaX509CRLConverter jcaX509CRLConverter, String str, Throwable th) {
            super(str);
            this.cause = th;
        }

        @Override // java.lang.Throwable
        public Throwable getCause() {
            return this.cause;
        }
    }

    public JcaX509CRLConverter() {
        this.helper = new DefaultCertHelper();
        this.helper = new DefaultCertHelper();
    }

    public X509CRL getCRL(X509CRLHolder x509CRLHolder) {
        try {
            return (X509CRL) this.helper.getCertificateFactory("X.509").generateCRL(new ByteArrayInputStream(x509CRLHolder.getEncoded()));
        } catch (IOException e2) {
            throw new ExCRLException(this, "exception parsing certificate: " + e2.getMessage(), e2);
        } catch (NoSuchProviderException e3) {
            throw new ExCRLException(this, "cannot find required provider:" + e3.getMessage(), e3);
        } catch (CertificateException e4) {
            throw new ExCRLException(this, "cannot create factory: " + e4.getMessage(), e4);
        }
    }

    public JcaX509CRLConverter setProvider(String str) {
        this.helper = new NamedCertHelper(str);
        return this;
    }

    public JcaX509CRLConverter setProvider(Provider provider) {
        this.helper = new ProviderCertHelper(provider);
        return this;
    }
}
