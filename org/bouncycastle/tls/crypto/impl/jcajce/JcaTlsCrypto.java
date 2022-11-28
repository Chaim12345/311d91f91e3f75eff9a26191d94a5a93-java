package org.bouncycastle.tls.crypto.impl.jcajce;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Hashtable;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.jcajce.spec.XDHParameterSpec;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.tls.DigitallySigned;
import org.bouncycastle.tls.NamedGroup;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SignatureAndHashAlgorithm;
import org.bouncycastle.tls.SignatureScheme;
import org.bouncycastle.tls.TlsDHUtils;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsUtils;
import org.bouncycastle.tls.crypto.SRP6Group;
import org.bouncycastle.tls.crypto.TlsCertificate;
import org.bouncycastle.tls.crypto.TlsCipher;
import org.bouncycastle.tls.crypto.TlsCryptoException;
import org.bouncycastle.tls.crypto.TlsCryptoParameters;
import org.bouncycastle.tls.crypto.TlsCryptoUtils;
import org.bouncycastle.tls.crypto.TlsDHConfig;
import org.bouncycastle.tls.crypto.TlsDHDomain;
import org.bouncycastle.tls.crypto.TlsECConfig;
import org.bouncycastle.tls.crypto.TlsECDomain;
import org.bouncycastle.tls.crypto.TlsHMAC;
import org.bouncycastle.tls.crypto.TlsHash;
import org.bouncycastle.tls.crypto.TlsNonceGenerator;
import org.bouncycastle.tls.crypto.TlsSRP6Client;
import org.bouncycastle.tls.crypto.TlsSRP6Server;
import org.bouncycastle.tls.crypto.TlsSRP6VerifierGenerator;
import org.bouncycastle.tls.crypto.TlsSRPConfig;
import org.bouncycastle.tls.crypto.TlsSecret;
import org.bouncycastle.tls.crypto.TlsStreamSigner;
import org.bouncycastle.tls.crypto.TlsStreamVerifier;
import org.bouncycastle.tls.crypto.impl.AbstractTlsCrypto;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipher;
import org.bouncycastle.tls.crypto.impl.TlsAEADCipherImpl;
import org.bouncycastle.tls.crypto.impl.TlsBlockCipher;
import org.bouncycastle.tls.crypto.impl.TlsBlockCipherImpl;
import org.bouncycastle.tls.crypto.impl.TlsImplUtils;
import org.bouncycastle.tls.crypto.impl.TlsNullCipher;
import org.bouncycastle.tls.crypto.impl.jcajce.srp.SRP6Client;
import org.bouncycastle.tls.crypto.impl.jcajce.srp.SRP6Server;
import org.bouncycastle.tls.crypto.impl.jcajce.srp.SRP6VerifierGenerator;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
/* loaded from: classes4.dex */
public class JcaTlsCrypto extends AbstractTlsCrypto {
    private final SecureRandom entropySource;
    private final JcaJceHelper helper;
    private final SecureRandom nonceEntropySource;
    private final Hashtable supportedEncryptionAlgorithms = new Hashtable();
    private final Hashtable supportedNamedGroups = new Hashtable();
    private final Hashtable supportedOther = new Hashtable();

    /* JADX INFO: Access modifiers changed from: protected */
    public JcaTlsCrypto(JcaJceHelper jcaJceHelper, SecureRandom secureRandom, SecureRandom secureRandom2) {
        this.helper = jcaJceHelper;
        this.entropySource = secureRandom;
        this.nonceEntropySource = secureRandom2;
    }

    private TlsCipher createChaCha20Poly1305(TlsCryptoParameters tlsCryptoParameters) {
        return new TlsAEADCipher(tlsCryptoParameters, new JceChaCha20Poly1305(this.helper, true), new JceChaCha20Poly1305(this.helper, false), 32, 16, 2);
    }

    private TlsAEADCipher createCipher_AES_CCM(TlsCryptoParameters tlsCryptoParameters, int i2, int i3) {
        return new TlsAEADCipher(tlsCryptoParameters, b("AES/CCM/NoPadding", "AES", i2, true), b("AES/CCM/NoPadding", "AES", i2, false), i2, i3, 1);
    }

    private TlsAEADCipher createCipher_AES_GCM(TlsCryptoParameters tlsCryptoParameters, int i2, int i3) {
        return new TlsAEADCipher(tlsCryptoParameters, b("AES/GCM/NoPadding", "AES", i2, true), b("AES/GCM/NoPadding", "AES", i2, false), i2, i3, 3);
    }

    private TlsAEADCipher createCipher_ARIA_GCM(TlsCryptoParameters tlsCryptoParameters, int i2, int i3) {
        return new TlsAEADCipher(tlsCryptoParameters, b("ARIA/GCM/NoPadding", "ARIA", i2, true), b("ARIA/GCM/NoPadding", "ARIA", i2, false), i2, i3, 3);
    }

    private TlsAEADCipher createCipher_Camellia_GCM(TlsCryptoParameters tlsCryptoParameters, int i2, int i3) {
        return new TlsAEADCipher(tlsCryptoParameters, b("Camellia/GCM/NoPadding", "Camellia", i2, true), b("Camellia/GCM/NoPadding", "Camellia", i2, false), i2, i3, 3);
    }

    private TlsAEADCipher createCipher_SM4_CCM(TlsCryptoParameters tlsCryptoParameters) {
        return new TlsAEADCipher(tlsCryptoParameters, b("SM4/CCM/NoPadding", "SM4", 16, true), b("SM4/CCM/NoPadding", "SM4", 16, false), 16, 16, 1);
    }

    private TlsAEADCipher createCipher_SM4_GCM(TlsCryptoParameters tlsCryptoParameters) {
        return new TlsAEADCipher(tlsCryptoParameters, b("SM4/GCM/NoPadding", "SM4", 16, true), b("SM4/GCM/NoPadding", "SM4", 16, false), 16, 16, 3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JceTlsSecret a(byte[] bArr) {
        return new JceTlsSecret(this, bArr);
    }

    protected TlsAEADCipherImpl b(String str, String str2, int i2, boolean z) {
        return new JceAEADCipherImpl(this.helper, str, str2, i2, z);
    }

    protected TlsBlockCipherImpl c(String str, String str2, int i2, boolean z) {
        return new JceBlockCipherImpl(this.helper.createCipher(str), str2, i2, z);
    }

    public byte[] calculateKeyAgreement(String str, PrivateKey privateKey, PublicKey publicKey, String str2) {
        KeyAgreement createKeyAgreement = this.helper.createKeyAgreement(str);
        createKeyAgreement.init(privateKey);
        createKeyAgreement.doPhase(publicKey, true);
        try {
            return createKeyAgreement.generateSecret(str2).getEncoded();
        } catch (NoSuchAlgorithmException e2) {
            if (XDHParameterSpec.X25519.equals(str) || XDHParameterSpec.X448.equals(str)) {
                return createKeyAgreement.generateSecret();
            }
            throw e2;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsCertificate createCertificate(byte[] bArr) {
        return new JcaTlsCertificate(this, bArr);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsCipher createCipher(TlsCryptoParameters tlsCryptoParameters, int i2, int i3) {
        try {
            if (i2 != 0) {
                switch (i2) {
                    case 7:
                        return f(tlsCryptoParameters, "DESede", 24, i3);
                    case 8:
                        return f(tlsCryptoParameters, "AES", 16, i3);
                    case 9:
                        return f(tlsCryptoParameters, "AES", 32, i3);
                    case 10:
                        return createCipher_AES_GCM(tlsCryptoParameters, 16, 16);
                    case 11:
                        return createCipher_AES_GCM(tlsCryptoParameters, 32, 16);
                    case 12:
                        return f(tlsCryptoParameters, "Camellia", 16, i3);
                    case 13:
                        return f(tlsCryptoParameters, "Camellia", 32, i3);
                    case 14:
                        return f(tlsCryptoParameters, "SEED", 16, i3);
                    case 15:
                        return createCipher_AES_CCM(tlsCryptoParameters, 16, 16);
                    case 16:
                        return createCipher_AES_CCM(tlsCryptoParameters, 16, 8);
                    case 17:
                        return createCipher_AES_CCM(tlsCryptoParameters, 32, 16);
                    case 18:
                        return createCipher_AES_CCM(tlsCryptoParameters, 32, 8);
                    case 19:
                        return createCipher_Camellia_GCM(tlsCryptoParameters, 16, 16);
                    case 20:
                        return createCipher_Camellia_GCM(tlsCryptoParameters, 32, 16);
                    case 21:
                        return createChaCha20Poly1305(tlsCryptoParameters);
                    case 22:
                        return f(tlsCryptoParameters, "ARIA", 16, i3);
                    case 23:
                        return f(tlsCryptoParameters, "ARIA", 32, i3);
                    case 24:
                        return createCipher_ARIA_GCM(tlsCryptoParameters, 16, 16);
                    case 25:
                        return createCipher_ARIA_GCM(tlsCryptoParameters, 32, 16);
                    case 26:
                        return createCipher_SM4_CCM(tlsCryptoParameters);
                    case 27:
                        return createCipher_SM4_GCM(tlsCryptoParameters);
                    case 28:
                        return f(tlsCryptoParameters, "SM4", 16, i3);
                    default:
                        throw new TlsFatalAlert((short) 80);
                }
            }
            return k(tlsCryptoParameters, i3);
        } catch (GeneralSecurityException e2) {
            throw new TlsCryptoException("cannot create cipher: " + e2.getMessage(), e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsDHDomain createDHDomain(TlsDHConfig tlsDHConfig) {
        return new JceTlsDHDomain(this, tlsDHConfig);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsECDomain createECDomain(TlsECConfig tlsECConfig) {
        int namedGroup = tlsECConfig.getNamedGroup();
        return namedGroup != 29 ? namedGroup != 30 ? new JceTlsECDomain(this, tlsECConfig) : new JceX448Domain(this) : new JceX25519Domain(this);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsHMAC createHMAC(int i2) {
        return createHMACForHash(TlsCryptoUtils.getHashForHMAC(i2));
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsHMAC createHMACForHash(int i2) {
        return g(t(i2));
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsHash createHash(int i2) {
        try {
            return i(s(i2));
        } catch (GeneralSecurityException e2) {
            throw Exceptions.a("unable to create message digest:" + e2.getMessage(), e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsNonceGenerator createNonceGenerator(byte[] bArr) {
        return new JcaNonceGenerator(this.nonceEntropySource, bArr);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSRP6Client createSRP6Client(TlsSRPConfig tlsSRPConfig) {
        final SRP6Client sRP6Client = new SRP6Client();
        BigInteger[] explicitNG = tlsSRPConfig.getExplicitNG();
        sRP6Client.init(new SRP6Group(explicitNG[0], explicitNG[1]), createHash(2), getSecureRandom());
        return new TlsSRP6Client(this) { // from class: org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto.1
            @Override // org.bouncycastle.tls.crypto.TlsSRP6Client
            public BigInteger calculateSecret(BigInteger bigInteger) {
                try {
                    return sRP6Client.calculateSecret(bigInteger);
                } catch (IllegalArgumentException e2) {
                    throw new TlsFatalAlert((short) 47, (Throwable) e2);
                }
            }

            @Override // org.bouncycastle.tls.crypto.TlsSRP6Client
            public BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3) {
                return sRP6Client.generateClientCredentials(bArr, bArr2, bArr3);
            }
        };
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSRP6Server createSRP6Server(TlsSRPConfig tlsSRPConfig, BigInteger bigInteger) {
        final SRP6Server sRP6Server = new SRP6Server();
        BigInteger[] explicitNG = tlsSRPConfig.getExplicitNG();
        sRP6Server.init(new SRP6Group(explicitNG[0], explicitNG[1]), bigInteger, createHash(2), getSecureRandom());
        return new TlsSRP6Server(this) { // from class: org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto.2
            @Override // org.bouncycastle.tls.crypto.TlsSRP6Server
            public BigInteger calculateSecret(BigInteger bigInteger2) {
                try {
                    return sRP6Server.calculateSecret(bigInteger2);
                } catch (IllegalArgumentException e2) {
                    throw new TlsFatalAlert((short) 47, (Throwable) e2);
                }
            }

            @Override // org.bouncycastle.tls.crypto.TlsSRP6Server
            public BigInteger generateServerCredentials() {
                return sRP6Server.generateServerCredentials();
            }
        };
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSRP6VerifierGenerator createSRP6VerifierGenerator(TlsSRPConfig tlsSRPConfig) {
        BigInteger[] explicitNG = tlsSRPConfig.getExplicitNG();
        final SRP6VerifierGenerator sRP6VerifierGenerator = new SRP6VerifierGenerator();
        sRP6VerifierGenerator.init(explicitNG[0], explicitNG[1], createHash(2));
        return new TlsSRP6VerifierGenerator(this) { // from class: org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto.3
            @Override // org.bouncycastle.tls.crypto.TlsSRP6VerifierGenerator
            public BigInteger generateVerifier(byte[] bArr, byte[] bArr2, byte[] bArr3) {
                return sRP6VerifierGenerator.generateVerifier(bArr, bArr2, bArr3);
            }
        };
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSecret createSecret(byte[] bArr) {
        return a(Arrays.clone(bArr));
    }

    protected TlsBlockCipherImpl d(String str, String str2, int i2, boolean z) {
        return new JceBlockCipherWithCBCImplicitIVImpl(this.helper.createCipher(str), str2, z);
    }

    protected TlsBlockCipherImpl e(TlsCryptoParameters tlsCryptoParameters, String str, int i2, boolean z) {
        String str2 = str + "/CBC/NoPadding";
        return TlsImplUtils.isTLSv11(tlsCryptoParameters) ? c(str2, str, i2, z) : d(str2, str, i2, z);
    }

    protected TlsCipher f(TlsCryptoParameters tlsCryptoParameters, String str, int i2, int i3) {
        return new TlsBlockCipher(tlsCryptoParameters, e(tlsCryptoParameters, str, i2, true), e(tlsCryptoParameters, str, i2, false), j(tlsCryptoParameters, i3), j(tlsCryptoParameters, i3), i2);
    }

    protected TlsHMAC g(String str) {
        try {
            return new JceTlsHMAC(this.helper.createMac(str), str);
        } catch (GeneralSecurityException e2) {
            throw new RuntimeException("cannot create HMAC: " + str, e2);
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSecret generateRSAPreMasterSecret(ProtocolVersion protocolVersion) {
        byte[] bArr = new byte[48];
        getSecureRandom().nextBytes(bArr);
        TlsUtils.writeVersion(protocolVersion, bArr, 0);
        return a(bArr);
    }

    public JcaJceHelper getHelper() {
        return this.helper;
    }

    public AlgorithmParameters getNamedGroupAlgorithmParameters(int i2) {
        if (NamedGroup.refersToAnXDHCurve(i2)) {
            if (i2 == 29 || i2 == 30) {
                return null;
            }
        } else if (NamedGroup.refersToAnECDSACurve(i2)) {
            return ECUtil.d(this, NamedGroup.getCurveName(i2));
        } else {
            if (NamedGroup.refersToASpecificFiniteField(i2)) {
                return DHUtil.d(this, TlsDHUtils.getNamedDHGroup(i2));
            }
        }
        throw new IllegalArgumentException("NamedGroup not supported: " + NamedGroup.getText(i2));
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public SecureRandom getSecureRandom() {
        return this.entropySource;
    }

    public AlgorithmParameters getSignatureSchemeAlgorithmParameters(int i2) {
        int cryptoHashAlgorithm;
        String s2;
        if (SignatureScheme.isRSAPSS(i2) && (cryptoHashAlgorithm = SignatureScheme.getCryptoHashAlgorithm(i2)) >= 0) {
            AlgorithmParameterSpec b2 = RSAUtil.b(cryptoHashAlgorithm, s(cryptoHashAlgorithm), getHelper());
            Signature createSignature = getHelper().createSignature(RSAUtil.a(s2) + "WITHRSAANDMGF1");
            createSignature.setParameter(b2);
            return createSignature.getParameters();
        }
        return null;
    }

    protected TlsHMAC h(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 == 5) {
                            return new JcaSSL3HMAC(i(s(6)), 64, 128);
                        }
                        throw new TlsFatalAlert((short) 80);
                    }
                    return new JcaSSL3HMAC(i(s(5)), 48, 128);
                }
                return new JcaSSL3HMAC(i(s(4)), 32, 64);
            }
            return new JcaSSL3HMAC(i(s(2)), 20, 64);
        }
        return new JcaSSL3HMAC(i(s(1)), 16, 64);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasAllRawSignatureAlgorithms() {
        return (JcaUtils.c() || hasSignatureAlgorithm((short) 7) || hasSignatureAlgorithm((short) 8)) ? false : true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasCryptoHashAlgorithm(int i2) {
        return true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasCryptoSignatureAlgorithm(int i2) {
        switch (i2) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return true;
            default:
                return false;
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasDHAgreement() {
        return true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasECDHAgreement() {
        return true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasEncryptionAlgorithm(int i2) {
        Integer valueOf = Integers.valueOf(i2);
        synchronized (this.supportedEncryptionAlgorithms) {
            Boolean bool = (Boolean) this.supportedEncryptionAlgorithms.get(valueOf);
            if (bool != null) {
                return bool.booleanValue();
            }
            Boolean u = u(i2);
            if (u == null) {
                return false;
            }
            synchronized (this.supportedEncryptionAlgorithms) {
                Boolean bool2 = (Boolean) this.supportedEncryptionAlgorithms.put(valueOf, u);
                if (bool2 != null && u != bool2) {
                    this.supportedEncryptionAlgorithms.put(valueOf, bool2);
                    u = bool2;
                }
            }
            return u.booleanValue();
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasMacAlgorithm(int i2) {
        return true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasNamedGroup(int i2) {
        Integer valueOf = Integers.valueOf(i2);
        synchronized (this.supportedNamedGroups) {
            Boolean bool = (Boolean) this.supportedNamedGroups.get(valueOf);
            if (bool != null) {
                return bool.booleanValue();
            }
            Boolean v = v(i2);
            if (v == null) {
                return false;
            }
            synchronized (this.supportedNamedGroups) {
                Boolean bool2 = (Boolean) this.supportedNamedGroups.put(valueOf, v);
                if (bool2 != null && v != bool2) {
                    this.supportedNamedGroups.put(valueOf, bool2);
                    v = bool2;
                }
            }
            return v.booleanValue();
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasRSAEncryption() {
        Boolean bool;
        synchronized (this.supportedOther) {
            Boolean bool2 = (Boolean) this.supportedOther.get("KE_RSA");
            if (bool2 != null) {
                return bool2.booleanValue();
            }
            try {
                l();
                bool = Boolean.TRUE;
            } catch (GeneralSecurityException unused) {
                bool = Boolean.FALSE;
            }
            synchronized (this.supportedOther) {
                Boolean bool3 = (Boolean) this.supportedOther.put("KE_RSA", bool);
                if (bool3 != null && bool != bool3) {
                    this.supportedOther.put("KE_RSA", bool3);
                    bool = bool3;
                }
            }
            return bool.booleanValue();
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasSRPAuthentication() {
        return true;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasSignatureAlgorithm(short s2) {
        switch (s2) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return true;
            default:
                switch (s2) {
                    case 26:
                    case 27:
                    case 28:
                        return true;
                    default:
                        return false;
                }
        }
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasSignatureAndHashAlgorithm(SignatureAndHashAlgorithm signatureAndHashAlgorithm) {
        short signature = signatureAndHashAlgorithm.getSignature();
        short hash = signatureAndHashAlgorithm.getHash();
        return hash != 1 ? hash != 3 ? hasSignatureAlgorithm(signature) : !JcaUtils.c() && hasSignatureAlgorithm(signature) : 1 == signature && hasSignatureAlgorithm(signature);
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public boolean hasSignatureScheme(int i2) {
        if (i2 != 1800) {
            short signatureAlgorithm = SignatureScheme.getSignatureAlgorithm(i2);
            int cryptoHashAlgorithm = SignatureScheme.getCryptoHashAlgorithm(i2);
            return cryptoHashAlgorithm != 1 ? cryptoHashAlgorithm != 3 ? hasSignatureAlgorithm(signatureAlgorithm) : !JcaUtils.c() && hasSignatureAlgorithm(signatureAlgorithm) : 1 == signatureAlgorithm && hasSignatureAlgorithm(signatureAlgorithm);
        }
        return false;
    }

    @Override // org.bouncycastle.tls.crypto.TlsCrypto
    public TlsSecret hkdfInit(int i2) {
        return a(new byte[TlsCryptoUtils.getHashOutputSize(i2)]);
    }

    protected TlsHash i(String str) {
        return new JcaTlsHash(this.helper.createDigest(str));
    }

    protected TlsHMAC j(TlsCryptoParameters tlsCryptoParameters, int i2) {
        return TlsImplUtils.isSSL(tlsCryptoParameters) ? h(i2) : createHMAC(i2);
    }

    protected TlsNullCipher k(TlsCryptoParameters tlsCryptoParameters, int i2) {
        return new TlsNullCipher(tlsCryptoParameters, j(tlsCryptoParameters, i2), j(tlsCryptoParameters, i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cipher l() {
        try {
            return getHelper().createCipher("RSA/NONE/PKCS1Padding");
        } catch (GeneralSecurityException unused) {
            return getHelper().createCipher("RSA/ECB/PKCS1Padding");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsStreamSigner m(String str, AlgorithmParameterSpec algorithmParameterSpec, PrivateKey privateKey, boolean z) {
        try {
            Signature createSignature = getHelper().createSignature(str);
            if (algorithmParameterSpec != null) {
                createSignature.setParameter(algorithmParameterSpec);
            }
            createSignature.initSign(privateKey, z ? getSecureRandom() : null);
            return new JcaTlsStreamSigner(createSignature);
        } catch (GeneralSecurityException e2) {
            throw new TlsFatalAlert((short) 80, (Throwable) e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsStreamSigner n(SignatureAndHashAlgorithm signatureAndHashAlgorithm, PrivateKey privateKey, boolean z) {
        return m(JcaUtils.a(signatureAndHashAlgorithm), null, privateKey, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsStreamVerifier o(String str, AlgorithmParameterSpec algorithmParameterSpec, byte[] bArr, PublicKey publicKey) {
        try {
            Signature createSignature = getHelper().createSignature(str);
            if (algorithmParameterSpec != null) {
                createSignature.setParameter(algorithmParameterSpec);
            }
            createSignature.initVerify(publicKey);
            return new JcaTlsStreamVerifier(createSignature, bArr);
        } catch (GeneralSecurityException e2) {
            throw new TlsFatalAlert((short) 80, (Throwable) e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsStreamVerifier p(DigitallySigned digitallySigned, PublicKey publicKey) {
        return o(JcaUtils.a(digitallySigned.getAlgorithm()), null, digitallySigned.getSignature(), publicKey);
    }

    protected TlsStreamSigner q(String str, AlgorithmParameterSpec algorithmParameterSpec, PrivateKey privateKey, boolean z, PublicKey publicKey) {
        try {
            Signature createSignature = getHelper().createSignature(str);
            Signature createSignature2 = getHelper().createSignature(str);
            if (algorithmParameterSpec != null) {
                createSignature.setParameter(algorithmParameterSpec);
                createSignature2.setParameter(algorithmParameterSpec);
            }
            createSignature.initSign(privateKey, z ? getSecureRandom() : null);
            createSignature2.initVerify(publicKey);
            return new JcaVerifyingStreamSigner(createSignature, createSignature2);
        } catch (GeneralSecurityException e2) {
            throw new TlsFatalAlert((short) 80, (Throwable) e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TlsStreamSigner r(SignatureAndHashAlgorithm signatureAndHashAlgorithm, PrivateKey privateKey, boolean z, PublicKey publicKey) {
        return q(JcaUtils.a(signatureAndHashAlgorithm), null, privateKey, z, publicKey);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String s(int i2) {
        switch (i2) {
            case 1:
                return MessageDigestAlgorithms.MD5;
            case 2:
                return "SHA-1";
            case 3:
                return "SHA-224";
            case 4:
                return "SHA-256";
            case 5:
                return "SHA-384";
            case 6:
                return "SHA-512";
            case 7:
                return "SM3";
            default:
                throw new IllegalArgumentException("invalid CryptoHashAlgorithm: " + i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String t(int i2) {
        switch (i2) {
            case 1:
                return "HmacMD5";
            case 2:
                return "HmacSHA1";
            case 3:
                return "HmacSHA224";
            case 4:
                return "HmacSHA256";
            case 5:
                return "HmacSHA384";
            case 6:
                return "HmacSHA512";
            case 7:
                return "HmacSM3";
            default:
                throw new IllegalArgumentException("invalid CryptoHashAlgorithm: " + i2);
        }
    }

    protected Boolean u(int i2) {
        boolean w;
        String str;
        switch (i2) {
            case 0:
                return Boolean.TRUE;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return Boolean.FALSE;
            case 7:
                w = w("DESede/CBC/NoPadding", 192);
                break;
            case 8:
                w = w("AES/CBC/NoPadding", 128);
                break;
            case 9:
                w = w("AES/CBC/NoPadding", 256);
                break;
            case 10:
                w = w("AES/GCM/NoPadding", 128);
                break;
            case 11:
                w = w("AES/GCM/NoPadding", 256);
                break;
            case 12:
                w = w("Camellia/CBC/NoPadding", 128);
                break;
            case 13:
                w = w("Camellia/CBC/NoPadding", 256);
                break;
            case 14:
                str = "SEED/CBC/NoPadding";
                w = w(str, 128);
                break;
            case 15:
            case 16:
                w = w("AES/CCM/NoPadding", 128);
                break;
            case 17:
            case 18:
                w = w("AES/CCM/NoPadding", 256);
                break;
            case 19:
                w = w("Camellia/GCM/NoPadding", 128);
                break;
            case 20:
                w = w("Camellia/GCM/NoPadding", 256);
                break;
            case 21:
                return Boolean.valueOf(w("ChaCha7539", 256) && x("Poly1305"));
            case 22:
                w = w("ARIA/CBC/NoPadding", 128);
                break;
            case 23:
                w = w("ARIA/CBC/NoPadding", 256);
                break;
            case 24:
                w = w("ARIA/GCM/NoPadding", 128);
                break;
            case 25:
                w = w("ARIA/GCM/NoPadding", 256);
                break;
            case 26:
                str = "SM4/CCM/NoPadding";
                w = w(str, 128);
                break;
            case 27:
                str = "SM4/GCM/NoPadding";
                w = w(str, 128);
                break;
            case 28:
                str = "SM4/CBC/NoPadding";
                w = w(str, 128);
                break;
            default:
                return null;
        }
        return Boolean.valueOf(w);
    }

    protected Boolean v(int i2) {
        try {
            if (!NamedGroup.refersToAnXDHCurve(i2)) {
                if (NamedGroup.refersToAnECDSACurve(i2)) {
                    return Boolean.valueOf(ECUtil.h(this, NamedGroup.getCurveName(i2)));
                }
                if (NamedGroup.refersToASpecificFiniteField(i2)) {
                    return Boolean.valueOf(DHUtil.g(this, TlsDHUtils.getNamedDHGroup(i2)));
                }
                return null;
            } else if (i2 == 29) {
                this.helper.createKeyAgreement(XDHParameterSpec.X25519);
                return Boolean.TRUE;
            } else if (i2 != 30) {
                return null;
            } else {
                this.helper.createKeyAgreement(XDHParameterSpec.X448);
                return Boolean.TRUE;
            }
        } catch (GeneralSecurityException unused) {
            return Boolean.FALSE;
        }
    }

    protected boolean w(String str, int i2) {
        try {
            this.helper.createCipher(str);
            return Cipher.getMaxAllowedKeyLength(str) >= i2;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }

    protected boolean x(String str) {
        try {
            this.helper.createMac(str);
            return true;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }
}
