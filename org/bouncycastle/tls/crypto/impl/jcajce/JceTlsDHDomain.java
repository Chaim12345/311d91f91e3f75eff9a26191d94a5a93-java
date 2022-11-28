package org.bouncycastle.tls.crypto.impl.jcajce;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Arrays;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import org.bouncycastle.tls.TlsDHUtils;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.crypto.DHGroup;
import org.bouncycastle.tls.crypto.TlsAgreement;
import org.bouncycastle.tls.crypto.TlsCryptoException;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsDHDomain;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes4.dex */
public class JceTlsDHDomain implements TlsDHDomain {

    /* renamed from: a  reason: collision with root package name */
    protected final JcaTlsCrypto f15030a;

    /* renamed from: b  reason: collision with root package name */
    protected final TlsDHConfig f15031b;

    /* renamed from: c  reason: collision with root package name */
    protected final DHParameterSpec f15032c;

    public JceTlsDHDomain(JcaTlsCrypto jcaTlsCrypto, TlsDHConfig tlsDHConfig) {
        DHParameterSpec f2;
        DHGroup dHGroup = TlsDHUtils.getDHGroup(tlsDHConfig);
        if (dHGroup == null || (f2 = DHUtil.f(jcaTlsCrypto, dHGroup)) == null) {
            throw new IllegalArgumentException("No DH configuration provided");
        }
        this.f15030a = jcaTlsCrypto;
        this.f15031b = tlsDHConfig;
        this.f15032c = f2;
    }

    public static JceTlsSecret calculateDHAgreement(JcaTlsCrypto jcaTlsCrypto, DHPrivateKey dHPrivateKey, DHPublicKey dHPublicKey, boolean z) {
        try {
            byte[] calculateKeyAgreement = jcaTlsCrypto.calculateKeyAgreement("DiffieHellman", dHPrivateKey, dHPublicKey, "TlsPremasterSecret");
            if (z) {
                int valueLength = getValueLength(dHPrivateKey.getParams());
                byte[] bArr = new byte[valueLength];
                System.arraycopy(calculateKeyAgreement, 0, bArr, valueLength - calculateKeyAgreement.length, calculateKeyAgreement.length);
                Arrays.fill(calculateKeyAgreement, (byte) 0);
                calculateKeyAgreement = bArr;
            }
            return jcaTlsCrypto.a(calculateKeyAgreement);
        } catch (GeneralSecurityException e2) {
            throw new TlsCryptoException("cannot calculate secret", e2);
        }
    }

    private static byte[] encodeValue(DHParameterSpec dHParameterSpec, boolean z, BigInteger bigInteger) {
        return z ? BigIntegers.asUnsignedByteArray(getValueLength(dHParameterSpec), bigInteger) : BigIntegers.asUnsignedByteArray(bigInteger);
    }

    private static int getValueLength(DHParameterSpec dHParameterSpec) {
        return (dHParameterSpec.getP().bitLength() + 7) / 8;
    }

    public JceTlsSecret calculateDHAgreement(DHPrivateKey dHPrivateKey, DHPublicKey dHPublicKey) {
        return calculateDHAgreement(this.f15030a, dHPrivateKey, dHPublicKey, this.f15031b.isPadded());
    }

    @Override // org.bouncycastle.tls.crypto.TlsDHDomain
    public TlsAgreement createDH() {
        return new JceTlsDH(this);
    }

    public BigInteger decodeParameter(byte[] bArr) {
        if (!this.f15031b.isPadded() || getValueLength(this.f15032c) == bArr.length) {
            return new BigInteger(1, bArr);
        }
        throw new TlsFatalAlert((short) 47);
    }

    public DHPublicKey decodePublicKey(byte[] bArr) {
        try {
            return (DHPublicKey) this.f15030a.getHelper().createKeyFactory("DiffieHellman").generatePublic(DHUtil.b(decodeParameter(bArr), this.f15032c));
        } catch (IOException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new TlsFatalAlert((short) 40, (Throwable) e3);
        }
    }

    public byte[] encodeParameter(BigInteger bigInteger) {
        return encodeValue(this.f15032c, this.f15031b.isPadded(), bigInteger);
    }

    public byte[] encodePublicKey(DHPublicKey dHPublicKey) {
        return encodeValue(this.f15032c, true, dHPublicKey.getY());
    }

    public KeyPair generateKeyPair() {
        try {
            KeyPairGenerator createKeyPairGenerator = this.f15030a.getHelper().createKeyPairGenerator("DiffieHellman");
            createKeyPairGenerator.initialize(this.f15032c, this.f15030a.getSecureRandom());
            return createKeyPairGenerator.generateKeyPair();
        } catch (GeneralSecurityException e2) {
            throw new TlsCryptoException("unable to create key pair", e2);
        }
    }
}
