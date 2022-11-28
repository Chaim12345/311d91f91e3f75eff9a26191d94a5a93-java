package org.bouncycastle.oer.its;
/* loaded from: classes4.dex */
public class Certificate {
    private final CertificateBase certificateBase;

    /* loaded from: classes4.dex */
    public static class Builder {
        private CertificateBase certificateBase;

        public Certificate createCertificate() {
            return new Certificate(this.certificateBase);
        }

        public Builder setCertificateBase(CertificateBase certificateBase) {
            this.certificateBase = certificateBase;
            return this;
        }
    }

    public Certificate(CertificateBase certificateBase) {
        this.certificateBase = certificateBase;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Certificate getInstance(Object obj) {
        return obj instanceof Certificate ? (Certificate) obj : new Builder().setCertificateBase(CertificateBase.getInstance(obj)).createCertificate();
    }

    public CertificateBase getCertificateBase() {
        return this.certificateBase;
    }
}
