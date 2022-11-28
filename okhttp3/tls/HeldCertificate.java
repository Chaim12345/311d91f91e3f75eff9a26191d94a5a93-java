package okhttp3.tls;

import java.math.BigInteger;
import java.net.InetAddress;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchGroup;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import okhttp3.internal.Util;
import okhttp3.tls.internal.der.AlgorithmIdentifier;
import okhttp3.tls.internal.der.AttributeTypeAndValue;
import okhttp3.tls.internal.der.BasicConstraints;
import okhttp3.tls.internal.der.BasicDerAdapter;
import okhttp3.tls.internal.der.BitString;
import okhttp3.tls.internal.der.Certificate;
import okhttp3.tls.internal.der.CertificateAdapters;
import okhttp3.tls.internal.der.Extension;
import okhttp3.tls.internal.der.ObjectIdentifiers;
import okhttp3.tls.internal.der.PrivateKeyInfo;
import okhttp3.tls.internal.der.SubjectPublicKeyInfo;
import okhttp3.tls.internal.der.TbsCertificate;
import okhttp3.tls.internal.der.Validity;
import okio.ByteString;
import org.bouncycastle.openssl.PEMParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class HeldCertificate {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Regex PEM_REGEX = new Regex("-----BEGIN ([!-,.-~ ]*)-----([^-]*)-----END \\1-----");
    @NotNull
    private final X509Certificate certificate;
    @NotNull
    private final KeyPair keyPair;

    /* loaded from: classes3.dex */
    public static final class Builder {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private static final long DEFAULT_DURATION_MILLIS = 86400000;
        @Nullable
        private String commonName;
        @Nullable
        private String keyAlgorithm;
        @Nullable
        private KeyPair keyPair;
        private int keySize;
        @Nullable
        private String organizationalUnit;
        @Nullable
        private BigInteger serialNumber;
        @Nullable
        private HeldCertificate signedBy;
        private long notBefore = -1;
        private long notAfter = -1;
        @NotNull
        private final List<String> altNames = new ArrayList();
        private int maxIntermediateCas = -1;

        /* loaded from: classes3.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        public Builder() {
            ecdsa256();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v10, types: [okio.ByteString] */
        /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.String] */
        private final List<Extension> extensions() {
            int collectionSizeOrDefault;
            Object generalNameDnsName$okhttp_tls;
            ArrayList arrayList = new ArrayList();
            int i2 = this.maxIntermediateCas;
            if (i2 != -1) {
                arrayList.add(new Extension(ObjectIdentifiers.basicConstraints, true, new BasicConstraints(true, Long.valueOf(i2))));
            }
            if (!this.altNames.isEmpty()) {
                List<String> list = this.altNames;
                collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
                ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    Object obj = (String) it.next();
                    if (Util.canParseAsIpAddress(obj)) {
                        generalNameDnsName$okhttp_tls = CertificateAdapters.INSTANCE.getGeneralNameIpAddress$okhttp_tls();
                        ByteString.Companion companion = ByteString.Companion;
                        byte[] address = InetAddress.getByName(obj).getAddress();
                        Intrinsics.checkNotNullExpressionValue(address, "getByName(it).address");
                        obj = ByteString.Companion.of$default(companion, address, 0, 0, 3, null);
                    } else {
                        generalNameDnsName$okhttp_tls = CertificateAdapters.INSTANCE.getGeneralNameDnsName$okhttp_tls();
                    }
                    arrayList2.add(TuplesKt.to(generalNameDnsName$okhttp_tls, obj));
                }
                arrayList.add(new Extension(ObjectIdentifiers.subjectAlternativeName, true, arrayList2));
            }
            return arrayList;
        }

        private final KeyPair generateKeyPair() {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(this.keyAlgorithm);
            keyPairGenerator.initialize(this.keySize, new SecureRandom());
            KeyPair generateKeyPair = keyPairGenerator.generateKeyPair();
            Intrinsics.checkNotNullExpressionValue(generateKeyPair, "getInstance(keyAlgorithm…generateKeyPair()\n      }");
            return generateKeyPair;
        }

        private final AlgorithmIdentifier signatureAlgorithm(KeyPair keyPair) {
            return keyPair.getPrivate() instanceof RSAPrivateKey ? new AlgorithmIdentifier(ObjectIdentifiers.sha256WithRSAEncryption, null) : new AlgorithmIdentifier(ObjectIdentifiers.sha256withEcdsa, ByteString.EMPTY);
        }

        private final List<List<AttributeTypeAndValue>> subject() {
            List listOf;
            List listOf2;
            ArrayList arrayList = new ArrayList();
            String str = this.organizationalUnit;
            if (str != null) {
                listOf2 = CollectionsKt__CollectionsJVMKt.listOf(new AttributeTypeAndValue(ObjectIdentifiers.organizationalUnitName, str));
                arrayList.add(listOf2);
            }
            String str2 = this.commonName;
            if (str2 == null) {
                str2 = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(str2, "randomUUID().toString()");
            }
            listOf = CollectionsKt__CollectionsJVMKt.listOf(new AttributeTypeAndValue(ObjectIdentifiers.commonName, str2));
            arrayList.add(listOf);
            return arrayList;
        }

        private final Validity validity() {
            long j2 = this.notBefore;
            if (j2 == -1) {
                j2 = System.currentTimeMillis();
            }
            long j3 = this.notAfter;
            if (j3 == -1) {
                j3 = j2 + DEFAULT_DURATION_MILLIS;
            }
            return new Validity(j2, j3);
        }

        @NotNull
        public final Builder addSubjectAlternativeName(@NotNull String altName) {
            Intrinsics.checkNotNullParameter(altName, "altName");
            this.altNames.add(altName);
            return this;
        }

        @NotNull
        public final HeldCertificate build() {
            KeyPair keyPair;
            List<List<AttributeTypeAndValue>> list;
            KeyPair keyPair2 = this.keyPair;
            if (keyPair2 == null) {
                keyPair2 = generateKeyPair();
            }
            CertificateAdapters certificateAdapters = CertificateAdapters.INSTANCE;
            BasicDerAdapter<SubjectPublicKeyInfo> subjectPublicKeyInfo$okhttp_tls = certificateAdapters.getSubjectPublicKeyInfo$okhttp_tls();
            ByteString.Companion companion = ByteString.Companion;
            byte[] encoded = keyPair2.getPublic().getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded, "subjectKeyPair.public.encoded");
            SubjectPublicKeyInfo fromDer = subjectPublicKeyInfo$okhttp_tls.fromDer(ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null));
            List<List<AttributeTypeAndValue>> subject = subject();
            HeldCertificate heldCertificate = this.signedBy;
            if (heldCertificate != null) {
                Intrinsics.checkNotNull(heldCertificate);
                keyPair = heldCertificate.keyPair();
                BasicDerAdapter<List<List<AttributeTypeAndValue>>> rdnSequence$okhttp_tls = certificateAdapters.getRdnSequence$okhttp_tls();
                HeldCertificate heldCertificate2 = this.signedBy;
                Intrinsics.checkNotNull(heldCertificate2);
                byte[] encoded2 = heldCertificate2.certificate().getSubjectX500Principal().getEncoded();
                Intrinsics.checkNotNullExpressionValue(encoded2, "signedBy!!.certificate.s…jectX500Principal.encoded");
                list = rdnSequence$okhttp_tls.fromDer(ByteString.Companion.of$default(companion, encoded2, 0, 0, 3, null));
            } else {
                keyPair = keyPair2;
                list = subject;
            }
            AlgorithmIdentifier signatureAlgorithm = signatureAlgorithm(keyPair);
            BigInteger bigInteger = this.serialNumber;
            if (bigInteger == null) {
                bigInteger = BigInteger.ONE;
            }
            BigInteger bigInteger2 = bigInteger;
            Intrinsics.checkNotNullExpressionValue(bigInteger2, "serialNumber ?: BigInteger.ONE");
            TbsCertificate tbsCertificate = new TbsCertificate(2L, bigInteger2, signatureAlgorithm, list, validity(), subject, fromDer, null, null, extensions());
            Signature signature = Signature.getInstance(tbsCertificate.getSignatureAlgorithmName());
            signature.initSign(keyPair.getPrivate());
            signature.update(certificateAdapters.getTbsCertificate$okhttp_tls().toDer(tbsCertificate).toByteArray());
            byte[] sign = signature.sign();
            Intrinsics.checkNotNullExpressionValue(sign, "sign()");
            return new HeldCertificate(keyPair2, new Certificate(tbsCertificate, signatureAlgorithm, new BitString(ByteString.Companion.of$default(companion, sign, 0, 0, 3, null), 0)).toX509Certificate());
        }

        @NotNull
        public final Builder certificateAuthority(int i2) {
            if (i2 >= 0) {
                this.maxIntermediateCas = i2;
                return this;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("maxIntermediateCas < 0: ", Integer.valueOf(i2)).toString());
        }

        @NotNull
        public final Builder commonName(@NotNull String cn) {
            Intrinsics.checkNotNullParameter(cn, "cn");
            this.commonName = cn;
            return this;
        }

        @NotNull
        public final Builder duration(long j2, @NotNull TimeUnit unit) {
            Intrinsics.checkNotNullParameter(unit, "unit");
            long currentTimeMillis = System.currentTimeMillis();
            validityInterval(currentTimeMillis, unit.toMillis(j2) + currentTimeMillis);
            return this;
        }

        @NotNull
        public final Builder ecdsa256() {
            this.keyAlgorithm = "EC";
            this.keySize = 256;
            return this;
        }

        @NotNull
        public final Builder keyPair(@NotNull KeyPair keyPair) {
            Intrinsics.checkNotNullParameter(keyPair, "keyPair");
            this.keyPair = keyPair;
            return this;
        }

        @NotNull
        public final Builder keyPair(@NotNull PublicKey publicKey, @NotNull PrivateKey privateKey) {
            Intrinsics.checkNotNullParameter(publicKey, "publicKey");
            Intrinsics.checkNotNullParameter(privateKey, "privateKey");
            keyPair(new KeyPair(publicKey, privateKey));
            return this;
        }

        @NotNull
        public final Builder organizationalUnit(@NotNull String ou) {
            Intrinsics.checkNotNullParameter(ou, "ou");
            this.organizationalUnit = ou;
            return this;
        }

        @NotNull
        public final Builder rsa2048() {
            this.keyAlgorithm = "RSA";
            this.keySize = 2048;
            return this;
        }

        @NotNull
        public final Builder serialNumber(long j2) {
            BigInteger valueOf = BigInteger.valueOf(j2);
            Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(serialNumber)");
            serialNumber(valueOf);
            return this;
        }

        @NotNull
        public final Builder serialNumber(@NotNull BigInteger serialNumber) {
            Intrinsics.checkNotNullParameter(serialNumber, "serialNumber");
            this.serialNumber = serialNumber;
            return this;
        }

        @NotNull
        public final Builder signedBy(@Nullable HeldCertificate heldCertificate) {
            this.signedBy = heldCertificate;
            return this;
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x0016, code lost:
            if ((r6 == -1) == (r8 == -1)) goto L10;
         */
        @NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Builder validityInterval(long j2, long j3) {
            boolean z = true;
            if (j2 <= j3) {
            }
            z = false;
            if (z) {
                this.notBefore = j2;
                this.notAfter = j3;
                return this;
            }
            throw new IllegalArgumentException(("invalid interval: " + j2 + ".." + j3).toString());
        }
    }

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final HeldCertificate decode(String str, String str2) {
            String str3;
            X509Certificate decodeCertificatePem = Certificates.decodeCertificatePem(str);
            ByteString decodeBase64 = ByteString.Companion.decodeBase64(str2);
            if (decodeBase64 != null) {
                PublicKey publicKey = decodeCertificatePem.getPublicKey();
                if (publicKey instanceof ECPublicKey) {
                    str3 = "EC";
                } else if (!(publicKey instanceof RSAPublicKey)) {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("unexpected key type: ", decodeCertificatePem.getPublicKey()));
                } else {
                    str3 = "RSA";
                }
                return new HeldCertificate(new KeyPair(decodeCertificatePem.getPublicKey(), decodePkcs8(decodeBase64, str3)), decodeCertificatePem);
            }
            throw new IllegalArgumentException("failed to decode private key");
        }

        private final PrivateKey decodePkcs8(ByteString byteString, String str) {
            try {
                PrivateKey generatePrivate = KeyFactory.getInstance(str).generatePrivate(new PKCS8EncodedKeySpec(byteString.toByteArray()));
                Intrinsics.checkNotNullExpressionValue(generatePrivate, "keyFactory.generatePriva…Spec(data.toByteArray()))");
                return generatePrivate;
            } catch (GeneralSecurityException e2) {
                throw new IllegalArgumentException("failed to decode private key", e2);
            }
        }

        @JvmStatic
        @NotNull
        public final HeldCertificate decode(@NotNull String certificateAndPrivateKeyPem) {
            Intrinsics.checkNotNullParameter(certificateAndPrivateKeyPem, "certificateAndPrivateKeyPem");
            String str = null;
            Iterator it = Regex.findAll$default(HeldCertificate.PEM_REGEX, certificateAndPrivateKeyPem, 0, 2, null).iterator();
            String str2 = null;
            while (true) {
                if (!it.hasNext()) {
                    if (str != null) {
                        if (str2 != null) {
                            return decode(str, str2);
                        }
                        throw new IllegalArgumentException("string does not include a private key".toString());
                    }
                    throw new IllegalArgumentException("string does not include a certificate".toString());
                }
                MatchResult matchResult = (MatchResult) it.next();
                MatchGroup matchGroup = matchResult.getGroups().get(1);
                Intrinsics.checkNotNull(matchGroup);
                String value = matchGroup.getValue();
                if (Intrinsics.areEqual(value, PEMParser.TYPE_CERTIFICATE)) {
                    if (!(str == null)) {
                        throw new IllegalArgumentException("string includes multiple certificates".toString());
                    }
                    MatchGroup matchGroup2 = matchResult.getGroups().get(0);
                    Intrinsics.checkNotNull(matchGroup2);
                    str = matchGroup2.getValue();
                } else if (!Intrinsics.areEqual(value, PEMParser.TYPE_PRIVATE_KEY)) {
                    throw new IllegalArgumentException(Intrinsics.stringPlus("unexpected type: ", value));
                } else {
                    if (!(str2 == null)) {
                        throw new IllegalArgumentException("string includes multiple private keys".toString());
                    }
                    MatchGroup matchGroup3 = matchResult.getGroups().get(2);
                    Intrinsics.checkNotNull(matchGroup3);
                    str2 = matchGroup3.getValue();
                }
            }
        }
    }

    public HeldCertificate(@NotNull KeyPair keyPair, @NotNull X509Certificate certificate) {
        Intrinsics.checkNotNullParameter(keyPair, "keyPair");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        this.keyPair = keyPair;
        this.certificate = certificate;
    }

    @JvmStatic
    @NotNull
    public static final HeldCertificate decode(@NotNull String str) {
        return Companion.decode(str);
    }

    private final ByteString pkcs1Bytes() {
        BasicDerAdapter<PrivateKeyInfo> privateKeyInfo$okhttp_tls = CertificateAdapters.INSTANCE.getPrivateKeyInfo$okhttp_tls();
        ByteString.Companion companion = ByteString.Companion;
        byte[] encoded = this.keyPair.getPrivate().getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "keyPair.private.encoded");
        return privateKeyInfo$okhttp_tls.fromDer(ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null)).getPrivateKey();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "certificate", imports = {}))
    @JvmName(name = "-deprecated_certificate")
    @NotNull
    /* renamed from: -deprecated_certificate  reason: not valid java name */
    public final X509Certificate m1832deprecated_certificate() {
        return this.certificate;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "keyPair", imports = {}))
    @JvmName(name = "-deprecated_keyPair")
    @NotNull
    /* renamed from: -deprecated_keyPair  reason: not valid java name */
    public final KeyPair m1833deprecated_keyPair() {
        return this.keyPair;
    }

    @JvmName(name = "certificate")
    @NotNull
    public final X509Certificate certificate() {
        return this.certificate;
    }

    @NotNull
    public final String certificatePem() {
        return Certificates.certificatePem(this.certificate);
    }

    @JvmName(name = "keyPair")
    @NotNull
    public final KeyPair keyPair() {
        return this.keyPair;
    }

    @NotNull
    public final String privateKeyPkcs1Pem() {
        if (this.keyPair.getPrivate() instanceof RSAPrivateKey) {
            StringBuilder sb = new StringBuilder();
            sb.append("-----BEGIN RSA PRIVATE KEY-----\n");
            Certificates.encodeBase64Lines(sb, pkcs1Bytes());
            sb.append("-----END RSA PRIVATE KEY-----\n");
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
            return sb2;
        }
        throw new IllegalStateException("PKCS1 only supports RSA keys".toString());
    }

    @NotNull
    public final String privateKeyPkcs8Pem() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----BEGIN PRIVATE KEY-----\n");
        ByteString.Companion companion = ByteString.Companion;
        byte[] encoded = keyPair().getPrivate().getEncoded();
        Intrinsics.checkNotNullExpressionValue(encoded, "keyPair.private.encoded");
        Certificates.encodeBase64Lines(sb, ByteString.Companion.of$default(companion, encoded, 0, 0, 3, null));
        sb.append("-----END PRIVATE KEY-----\n");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }
}
