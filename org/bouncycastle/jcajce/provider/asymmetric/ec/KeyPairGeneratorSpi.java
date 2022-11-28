package org.bouncycastle.jcajce.provider.asymmetric.ec;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECGenParameterSpec;
import java.util.Hashtable;
import org.bouncycastle.asn1.BERTags;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.bouncycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.bouncycastle.jcajce.provider.config.ProviderConfiguration;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveGenParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.util.Integers;
/* loaded from: classes3.dex */
public abstract class KeyPairGeneratorSpi extends KeyPairGenerator {

    /* loaded from: classes3.dex */
    public static class EC extends KeyPairGeneratorSpi {
        private static Hashtable ecParameters;

        /* renamed from: a  reason: collision with root package name */
        ECKeyGenerationParameters f13653a;

        /* renamed from: b  reason: collision with root package name */
        ECKeyPairGenerator f13654b;

        /* renamed from: c  reason: collision with root package name */
        Object f13655c;

        /* renamed from: d  reason: collision with root package name */
        int f13656d;

        /* renamed from: e  reason: collision with root package name */
        boolean f13657e;

        /* renamed from: f  reason: collision with root package name */
        String f13658f;

        /* renamed from: g  reason: collision with root package name */
        ProviderConfiguration f13659g;

        static {
            Hashtable hashtable = new Hashtable();
            ecParameters = hashtable;
            hashtable.put(Integers.valueOf(192), new ECGenParameterSpec("prime192v1"));
            ecParameters.put(Integers.valueOf(239), new ECGenParameterSpec("prime239v1"));
            ecParameters.put(Integers.valueOf(256), new ECGenParameterSpec("prime256v1"));
            ecParameters.put(Integers.valueOf(BERTags.FLAGS), new ECGenParameterSpec("P-224"));
            ecParameters.put(Integers.valueOf(384), new ECGenParameterSpec("P-384"));
            ecParameters.put(Integers.valueOf(521), new ECGenParameterSpec("P-521"));
        }

        public EC() {
            super("EC");
            this.f13654b = new ECKeyPairGenerator();
            this.f13655c = null;
            this.f13656d = 239;
            CryptoServicesRegistrar.getSecureRandom();
            this.f13657e = false;
            this.f13658f = "EC";
            this.f13659g = BouncyCastleProvider.CONFIGURATION;
        }

        public EC(String str, ProviderConfiguration providerConfiguration) {
            super(str);
            this.f13654b = new ECKeyPairGenerator();
            this.f13655c = null;
            this.f13656d = 239;
            CryptoServicesRegistrar.getSecureRandom();
            this.f13657e = false;
            this.f13658f = str;
            this.f13659g = providerConfiguration;
        }

        protected ECKeyGenerationParameters a(ECParameterSpec eCParameterSpec, SecureRandom secureRandom) {
            return new ECKeyGenerationParameters(new ECDomainParameters(eCParameterSpec.getCurve(), eCParameterSpec.getG(), eCParameterSpec.getN(), eCParameterSpec.getH()), secureRandom);
        }

        protected ECKeyGenerationParameters b(java.security.spec.ECParameterSpec eCParameterSpec, SecureRandom secureRandom) {
            X9ECParameters d2;
            if (!(eCParameterSpec instanceof ECNamedCurveSpec) || (d2 = ECUtils.d(((ECNamedCurveSpec) eCParameterSpec).getName(), this.f13659g)) == null) {
                ECCurve convertCurve = EC5Util.convertCurve(eCParameterSpec.getCurve());
                return new ECKeyGenerationParameters(new ECDomainParameters(convertCurve, EC5Util.convertPoint(convertCurve, eCParameterSpec.getGenerator()), eCParameterSpec.getOrder(), BigInteger.valueOf(eCParameterSpec.getCofactor())), secureRandom);
            }
            return c(d2, secureRandom);
        }

        protected ECKeyGenerationParameters c(X9ECParameters x9ECParameters, SecureRandom secureRandom) {
            return new ECKeyGenerationParameters(new ECDomainParameters(x9ECParameters.getCurve(), x9ECParameters.getG(), x9ECParameters.getN(), x9ECParameters.getH()), secureRandom);
        }

        protected void d(String str, SecureRandom secureRandom) {
            X9ECParameters d2 = ECUtils.d(str, this.f13659g);
            if (d2 != null) {
                this.f13655c = new ECNamedCurveSpec(str, d2.getCurve(), d2.getG(), d2.getN(), d2.getH(), null);
                this.f13653a = c(d2, secureRandom);
                return;
            }
            throw new InvalidAlgorithmParameterException("unknown curve name: " + str);
        }

        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        public KeyPair generateKeyPair() {
            if (!this.f13657e) {
                initialize(this.f13656d, new SecureRandom());
            }
            AsymmetricCipherKeyPair generateKeyPair = this.f13654b.generateKeyPair();
            ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) generateKeyPair.getPublic();
            ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters) generateKeyPair.getPrivate();
            Object obj = this.f13655c;
            if (obj instanceof ECParameterSpec) {
                ECParameterSpec eCParameterSpec = (ECParameterSpec) obj;
                BCECPublicKey bCECPublicKey = new BCECPublicKey(this.f13658f, eCPublicKeyParameters, eCParameterSpec, this.f13659g);
                return new KeyPair(bCECPublicKey, new BCECPrivateKey(this.f13658f, eCPrivateKeyParameters, bCECPublicKey, eCParameterSpec, this.f13659g));
            } else if (obj == null) {
                return new KeyPair(new BCECPublicKey(this.f13658f, eCPublicKeyParameters, this.f13659g), new BCECPrivateKey(this.f13658f, eCPrivateKeyParameters, this.f13659g));
            } else {
                java.security.spec.ECParameterSpec eCParameterSpec2 = (java.security.spec.ECParameterSpec) obj;
                BCECPublicKey bCECPublicKey2 = new BCECPublicKey(this.f13658f, eCPublicKeyParameters, eCParameterSpec2, this.f13659g);
                return new KeyPair(bCECPublicKey2, new BCECPrivateKey(this.f13658f, eCPrivateKeyParameters, bCECPublicKey2, eCParameterSpec2, this.f13659g));
            }
        }

        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        public void initialize(int i2, SecureRandom secureRandom) {
            this.f13656d = i2;
            ECGenParameterSpec eCGenParameterSpec = (ECGenParameterSpec) ecParameters.get(Integers.valueOf(i2));
            if (eCGenParameterSpec == null) {
                throw new InvalidParameterException("unknown key size.");
            }
            try {
                initialize(eCGenParameterSpec, secureRandom);
            } catch (InvalidAlgorithmParameterException unused) {
                throw new InvalidParameterException("key size not configurable.");
            }
        }

        @Override // java.security.KeyPairGenerator, java.security.KeyPairGeneratorSpi
        public void initialize(AlgorithmParameterSpec algorithmParameterSpec, SecureRandom secureRandom) {
            String name;
            ECKeyGenerationParameters b2;
            ECParameterSpec eCParameterSpec;
            if (algorithmParameterSpec == null) {
                eCParameterSpec = this.f13659g.getEcImplicitlyCa();
                if (eCParameterSpec == null) {
                    throw new InvalidAlgorithmParameterException("null parameter passed but no implicitCA set");
                }
                this.f13655c = null;
            } else if (!(algorithmParameterSpec instanceof ECParameterSpec)) {
                if (algorithmParameterSpec instanceof java.security.spec.ECParameterSpec) {
                    this.f13655c = algorithmParameterSpec;
                    b2 = b((java.security.spec.ECParameterSpec) algorithmParameterSpec, secureRandom);
                    this.f13653a = b2;
                    this.f13654b.init(this.f13653a);
                    this.f13657e = true;
                }
                if (algorithmParameterSpec instanceof ECGenParameterSpec) {
                    name = ((ECGenParameterSpec) algorithmParameterSpec).getName();
                } else if (!(algorithmParameterSpec instanceof ECNamedCurveGenParameterSpec)) {
                    String nameFrom = ECUtil.getNameFrom(algorithmParameterSpec);
                    if (nameFrom != null) {
                        d(nameFrom, secureRandom);
                        this.f13654b.init(this.f13653a);
                        this.f13657e = true;
                    }
                    throw new InvalidAlgorithmParameterException("invalid parameterSpec: " + algorithmParameterSpec);
                } else {
                    name = ((ECNamedCurveGenParameterSpec) algorithmParameterSpec).getName();
                }
                d(name, secureRandom);
                this.f13654b.init(this.f13653a);
                this.f13657e = true;
            } else {
                this.f13655c = algorithmParameterSpec;
                eCParameterSpec = (ECParameterSpec) algorithmParameterSpec;
            }
            b2 = a(eCParameterSpec, secureRandom);
            this.f13653a = b2;
            this.f13654b.init(this.f13653a);
            this.f13657e = true;
        }
    }

    /* loaded from: classes3.dex */
    public static class ECDH extends EC {
        public ECDH() {
            super("ECDH", BouncyCastleProvider.CONFIGURATION);
        }
    }

    /* loaded from: classes3.dex */
    public static class ECDHC extends EC {
        public ECDHC() {
            super("ECDHC", BouncyCastleProvider.CONFIGURATION);
        }
    }

    /* loaded from: classes3.dex */
    public static class ECDSA extends EC {
        public ECDSA() {
            super("ECDSA", BouncyCastleProvider.CONFIGURATION);
        }
    }

    /* loaded from: classes3.dex */
    public static class ECMQV extends EC {
        public ECMQV() {
            super("ECMQV", BouncyCastleProvider.CONFIGURATION);
        }
    }

    public KeyPairGeneratorSpi(String str) {
        super(str);
    }
}
