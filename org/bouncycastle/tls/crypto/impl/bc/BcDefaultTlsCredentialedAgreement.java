package org.bouncycastle.tls.crypto.impl.bc;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.TlsCredentialedAgreement;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsSecret;
/* loaded from: classes4.dex */
public class BcDefaultTlsCredentialedAgreement implements TlsCredentialedAgreement {

    /* renamed from: a  reason: collision with root package name */
    protected TlsCredentialedAgreement f14957a;

    /* loaded from: classes4.dex */
    private class DHCredentialedAgreement implements TlsCredentialedAgreement {

        /* renamed from: a  reason: collision with root package name */
        final BcTlsCrypto f14958a;

        /* renamed from: b  reason: collision with root package name */
        final Certificate f14959b;

        /* renamed from: c  reason: collision with root package name */
        final DHPrivateKeyParameters f14960c;

        DHCredentialedAgreement(BcDefaultTlsCredentialedAgreement bcDefaultTlsCredentialedAgreement, BcTlsCrypto bcTlsCrypto, Certificate certificate, DHPrivateKeyParameters dHPrivateKeyParameters) {
            this.f14958a = bcTlsCrypto;
            this.f14959b = certificate;
            this.f14960c = dHPrivateKeyParameters;
        }

        @Override // org.bouncycastle.tls.TlsCredentialedAgreement
        public TlsSecret generateAgreement(TlsCertificate tlsCertificate) {
            return BcTlsDHDomain.calculateDHAgreement(this.f14958a, this.f14960c, BcTlsCertificate.convert(this.f14958a, tlsCertificate).getPubKeyDH(), false);
        }

        @Override // org.bouncycastle.tls.TlsCredentials
        public Certificate getCertificate() {
            return this.f14959b;
        }
    }

    /* loaded from: classes4.dex */
    private class ECCredentialedAgreement implements TlsCredentialedAgreement {

        /* renamed from: a  reason: collision with root package name */
        final BcTlsCrypto f14961a;

        /* renamed from: b  reason: collision with root package name */
        final Certificate f14962b;

        /* renamed from: c  reason: collision with root package name */
        final ECPrivateKeyParameters f14963c;

        ECCredentialedAgreement(BcDefaultTlsCredentialedAgreement bcDefaultTlsCredentialedAgreement, BcTlsCrypto bcTlsCrypto, Certificate certificate, ECPrivateKeyParameters eCPrivateKeyParameters) {
            this.f14961a = bcTlsCrypto;
            this.f14962b = certificate;
            this.f14963c = eCPrivateKeyParameters;
        }

        @Override // org.bouncycastle.tls.TlsCredentialedAgreement
        public TlsSecret generateAgreement(TlsCertificate tlsCertificate) {
            return BcTlsECDomain.calculateBasicAgreement(this.f14961a, this.f14963c, BcTlsCertificate.convert(this.f14961a, tlsCertificate).getPubKeyEC());
        }

        @Override // org.bouncycastle.tls.TlsCredentials
        public Certificate getCertificate() {
            return this.f14962b;
        }
    }

    public BcDefaultTlsCredentialedAgreement(BcTlsCrypto bcTlsCrypto, Certificate certificate, AsymmetricKeyParameter asymmetricKeyParameter) {
        TlsCredentialedAgreement eCCredentialedAgreement;
        if (bcTlsCrypto == null) {
            throw new IllegalArgumentException("'crypto' cannot be null");
        }
        if (certificate == null) {
            throw new IllegalArgumentException("'certificate' cannot be null");
        }
        if (certificate.isEmpty()) {
            throw new IllegalArgumentException("'certificate' cannot be empty");
        }
        if (asymmetricKeyParameter == null) {
            throw new IllegalArgumentException("'privateKey' cannot be null");
        }
        if (!asymmetricKeyParameter.isPrivate()) {
            throw new IllegalArgumentException("'privateKey' must be private");
        }
        if (asymmetricKeyParameter instanceof DHPrivateKeyParameters) {
            eCCredentialedAgreement = new DHCredentialedAgreement(this, bcTlsCrypto, certificate, (DHPrivateKeyParameters) asymmetricKeyParameter);
        } else if (!(asymmetricKeyParameter instanceof ECPrivateKeyParameters)) {
            throw new IllegalArgumentException("'privateKey' type not supported: " + asymmetricKeyParameter.getClass().getName());
        } else {
            eCCredentialedAgreement = new ECCredentialedAgreement(this, bcTlsCrypto, certificate, (ECPrivateKeyParameters) asymmetricKeyParameter);
        }
        this.f14957a = eCCredentialedAgreement;
    }

    @Override // org.bouncycastle.tls.TlsCredentialedAgreement
    public TlsSecret generateAgreement(TlsCertificate tlsCertificate) {
        return this.f14957a.generateAgreement(tlsCertificate);
    }

    @Override // org.bouncycastle.tls.TlsCredentials
    public Certificate getCertificate() {
        return this.f14957a.getCertificate();
    }
}
