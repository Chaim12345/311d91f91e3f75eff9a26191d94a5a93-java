package org.bouncycastle.tls.crypto.impl.bc;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.agreement.DHBasicAgreement;
import org.bouncycastle.crypto.generators.DHBasicKeyPairGenerator;
import org.bouncycastle.crypto.params.DHKeyGenerationParameters;
import org.bouncycastle.crypto.params.DHParameters;
import org.bouncycastle.crypto.params.DHPrivateKeyParameters;
import org.bouncycastle.crypto.params.DHPublicKeyParameters;
import org.bouncycastle.tls.TlsDHUtils;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.DHGroup;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsDHDomain;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes4.dex */
public class BcTlsDHDomain implements TlsDHDomain {

    /* renamed from: a  reason: collision with root package name */
    protected BcTlsCrypto f14973a;

    /* renamed from: b  reason: collision with root package name */
    protected TlsDHConfig f14974b;

    /* renamed from: c  reason: collision with root package name */
    protected DHParameters f14975c;

    public BcTlsDHDomain(BcTlsCrypto bcTlsCrypto, TlsDHConfig tlsDHConfig) {
        this.f14973a = bcTlsCrypto;
        this.f14974b = tlsDHConfig;
        this.f14975c = getParameters(tlsDHConfig);
    }

    public static BcTlsSecret calculateDHAgreement(BcTlsCrypto bcTlsCrypto, DHPrivateKeyParameters dHPrivateKeyParameters, DHPublicKeyParameters dHPublicKeyParameters, boolean z) {
        DHBasicAgreement dHBasicAgreement = new DHBasicAgreement();
        dHBasicAgreement.init(dHPrivateKeyParameters);
        return bcTlsCrypto.a(encodeValue(dHPrivateKeyParameters.getParameters(), z, dHBasicAgreement.calculateAgreement(dHPublicKeyParameters)));
    }

    private static byte[] encodeValue(DHParameters dHParameters, boolean z, BigInteger bigInteger) {
        return z ? BigIntegers.asUnsignedByteArray(getValueLength(dHParameters), bigInteger) : BigIntegers.asUnsignedByteArray(bigInteger);
    }

    public static DHParameters getParameters(TlsDHConfig tlsDHConfig) {
        DHGroup dHGroup = TlsDHUtils.getDHGroup(tlsDHConfig);
        if (dHGroup != null) {
            return new DHParameters(dHGroup.getP(), dHGroup.getG(), dHGroup.getQ(), dHGroup.getL());
        }
        throw new IllegalArgumentException("No DH configuration provided");
    }

    private static int getValueLength(DHParameters dHParameters) {
        return (dHParameters.getP().bitLength() + 7) / 8;
    }

    public BcTlsSecret calculateDHAgreement(DHPrivateKeyParameters dHPrivateKeyParameters, DHPublicKeyParameters dHPublicKeyParameters) {
        return calculateDHAgreement(this.f14973a, dHPrivateKeyParameters, dHPublicKeyParameters, this.f14974b.isPadded());
    }

    @Override // org.bouncycastle.tls.crypto.TlsDHDomain
    public TlsAgreement createDH() {
        return new BcTlsDH(this);
    }

    public BigInteger decodeParameter(byte[] bArr) {
        if (!this.f14974b.isPadded() || getValueLength(this.f14975c) == bArr.length) {
            return new BigInteger(1, bArr);
        }
        throw new TlsFatalAlert((short) 47);
    }

    public DHPublicKeyParameters decodePublicKey(byte[] bArr) {
        try {
            return new DHPublicKeyParameters(decodeParameter(bArr), this.f14975c);
        } catch (RuntimeException e2) {
            throw new TlsFatalAlert((short) 40, (Throwable) e2);
        }
    }

    public byte[] encodeParameter(BigInteger bigInteger) {
        return encodeValue(this.f14975c, this.f14974b.isPadded(), bigInteger);
    }

    public byte[] encodePublicKey(DHPublicKeyParameters dHPublicKeyParameters) {
        return encodeValue(this.f14975c, true, dHPublicKeyParameters.getY());
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        DHBasicKeyPairGenerator dHBasicKeyPairGenerator = new DHBasicKeyPairGenerator();
        dHBasicKeyPairGenerator.init(new DHKeyGenerationParameters(this.f14973a.getSecureRandom(), this.f14975c));
        return dHBasicKeyPairGenerator.generateKeyPair();
    }
}
