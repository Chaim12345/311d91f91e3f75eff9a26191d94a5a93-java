package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.SctVerificationResult;
import com.appmattus.certificatetransparency.internal.serialization.CTConstants;
import com.appmattus.certificatetransparency.internal.serialization.OutputStreamExtKt;
import com.appmattus.certificatetransparency.internal.utils.CertificateExtKt;
import com.appmattus.certificatetransparency.internal.verifier.model.IssuerInformation;
import com.appmattus.certificatetransparency.internal.verifier.model.SignedCertificateTimestamp;
import com.appmattus.certificatetransparency.internal.verifier.model.Version;
import com.appmattus.certificatetransparency.loglist.LogServer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.Certificate;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.TBSCertificate;
import org.bouncycastle.asn1.x509.V3TBSCertificateGenerator;
import org.bouncycastle.util.encoders.Base64;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class LogSignatureVerifier implements SignatureVerifier {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final long PRECERT_ENTRY = 1;
    @NotNull
    private static final String X509_AUTHORITY_KEY_IDENTIFIER = "2.5.29.35";
    private static final long X509_ENTRY = 0;
    @NotNull
    private final LogServer logServer;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public LogSignatureVerifier(@NotNull LogServer logServer) {
        Intrinsics.checkNotNullParameter(logServer, "logServer");
        this.logServer = logServer;
    }

    private final TBSCertificate createTbsForVerification(X509Certificate x509Certificate, IssuerInformation issuerInformation) {
        boolean z = true;
        if (!(x509Certificate.getVersion() >= 3)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        ASN1InputStream aSN1InputStream = new ASN1InputStream(x509Certificate.getEncoded());
        try {
            Certificate parsedPreCertificate = Certificate.getInstance(aSN1InputStream.readObject());
            Intrinsics.checkNotNullExpressionValue(parsedPreCertificate, "parsedPreCertificate");
            if (hasX509AuthorityKeyIdentifier(parsedPreCertificate) && issuerInformation.getIssuedByPreCertificateSigningCert()) {
                if (issuerInformation.getX509authorityKeyIdentifier() == null) {
                    z = false;
                }
                if (!z) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            }
            Extensions extensions = parsedPreCertificate.getTBSCertificate().getExtensions();
            Intrinsics.checkNotNullExpressionValue(extensions, "parsedPreCertificate.tbsCertificate.extensions");
            List<Extension> extensionsWithoutPoisonAndSct = getExtensionsWithoutPoisonAndSct(extensions, issuerInformation.getX509authorityKeyIdentifier());
            V3TBSCertificateGenerator v3TBSCertificateGenerator = new V3TBSCertificateGenerator();
            TBSCertificate tBSCertificate = parsedPreCertificate.getTBSCertificate();
            v3TBSCertificateGenerator.setSerialNumber(tBSCertificate.getSerialNumber());
            v3TBSCertificateGenerator.setSignature(tBSCertificate.getSignature());
            X500Name name = issuerInformation.getName();
            if (name == null) {
                name = tBSCertificate.getIssuer();
            }
            v3TBSCertificateGenerator.setIssuer(name);
            v3TBSCertificateGenerator.setStartDate(tBSCertificate.getStartDate());
            v3TBSCertificateGenerator.setEndDate(tBSCertificate.getEndDate());
            v3TBSCertificateGenerator.setSubject(tBSCertificate.getSubject());
            v3TBSCertificateGenerator.setSubjectPublicKeyInfo(tBSCertificate.getSubjectPublicKeyInfo());
            v3TBSCertificateGenerator.setIssuerUniqueID((DERBitString) tBSCertificate.getIssuerUniqueId());
            v3TBSCertificateGenerator.setSubjectUniqueID((DERBitString) tBSCertificate.getSubjectUniqueId());
            Object[] array = extensionsWithoutPoisonAndSct.toArray(new Extension[0]);
            if (array != null) {
                v3TBSCertificateGenerator.setExtensions(new Extensions((Extension[]) array));
                TBSCertificate generateTBSCertificate = v3TBSCertificateGenerator.generateTBSCertificate();
                CloseableKt.closeFinally(aSN1InputStream, null);
                Intrinsics.checkNotNullExpressionValue(generateTBSCertificate, "ASN1InputStream(preCerti…BSCertificate()\n        }");
                return generateTBSCertificate;
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        } finally {
        }
    }

    private final List<Extension> getExtensionsWithoutPoisonAndSct(Extensions extensions, Extension extension) {
        int collectionSizeOrDefault;
        ASN1ObjectIdentifier[] extensionOIDs = extensions.getExtensionOIDs();
        Intrinsics.checkNotNullExpressionValue(extensionOIDs, "extensions.extensionOIDs");
        ArrayList arrayList = new ArrayList();
        for (ASN1ObjectIdentifier aSN1ObjectIdentifier : extensionOIDs) {
            if (!Intrinsics.areEqual(aSN1ObjectIdentifier.getId(), CTConstants.POISON_EXTENSION_OID)) {
                arrayList.add(aSN1ObjectIdentifier);
            }
        }
        ArrayList<ASN1ObjectIdentifier> arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (!Intrinsics.areEqual(((ASN1ObjectIdentifier) obj).getId(), CTConstants.SCT_CERTIFICATE_OID)) {
                arrayList2.add(obj);
            }
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10);
        ArrayList arrayList3 = new ArrayList(collectionSizeOrDefault);
        for (ASN1ObjectIdentifier aSN1ObjectIdentifier2 : arrayList2) {
            arrayList3.add((!Intrinsics.areEqual(aSN1ObjectIdentifier2.getId(), X509_AUTHORITY_KEY_IDENTIFIER) || extension == null) ? extensions.getExtension(aSN1ObjectIdentifier2) : extension);
        }
        return arrayList3;
    }

    private final boolean hasX509AuthorityKeyIdentifier(Certificate certificate) {
        return certificate.getTBSCertificate().getExtensions().getExtension(new ASN1ObjectIdentifier(X509_AUTHORITY_KEY_IDENTIFIER)) != null;
    }

    private final void serializeCommonSctFields(OutputStream outputStream, SignedCertificateTimestamp signedCertificateTimestamp) {
        if (!(signedCertificateTimestamp.getSctVersion() == Version.V1)) {
            throw new IllegalArgumentException("Can only serialize SCT v1 for now.".toString());
        }
        OutputStreamExtKt.writeUint(outputStream, signedCertificateTimestamp.getSctVersion().getNumber(), 1);
        OutputStreamExtKt.writeUint(outputStream, 0L, 1);
        OutputStreamExtKt.writeUint(outputStream, signedCertificateTimestamp.getTimestamp(), 8);
    }

    private final byte[] serializeSignedSctData(java.security.cert.Certificate certificate, SignedCertificateTimestamp signedCertificateTimestamp) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            serializeCommonSctFields(byteArrayOutputStream, signedCertificateTimestamp);
            OutputStreamExtKt.writeUint(byteArrayOutputStream, 0L, 2);
            byte[] encoded = certificate.getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded, "certificate.encoded");
            OutputStreamExtKt.writeVariableLength(byteArrayOutputStream, encoded, 16777215);
            OutputStreamExtKt.writeVariableLength(byteArrayOutputStream, signedCertificateTimestamp.getExtensions(), 65535);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            CloseableKt.closeFinally(byteArrayOutputStream, null);
            Intrinsics.checkNotNullExpressionValue(byteArray, "ByteArrayOutputStream().…t.toByteArray()\n        }");
            return byteArray;
        } finally {
        }
    }

    private final byte[] serializeSignedSctDataForPreCertificate(byte[] bArr, byte[] bArr2, SignedCertificateTimestamp signedCertificateTimestamp) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            serializeCommonSctFields(byteArrayOutputStream, signedCertificateTimestamp);
            OutputStreamExtKt.writeUint(byteArrayOutputStream, 1L, 2);
            byteArrayOutputStream.write(bArr2);
            OutputStreamExtKt.writeVariableLength(byteArrayOutputStream, bArr, 16777215);
            OutputStreamExtKt.writeVariableLength(byteArrayOutputStream, signedCertificateTimestamp.getExtensions(), 65535);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            CloseableKt.closeFinally(byteArrayOutputStream, null);
            Intrinsics.checkNotNullExpressionValue(byteArray, "ByteArrayOutputStream().…t.toByteArray()\n        }");
            return byteArray;
        } finally {
        }
    }

    private final SctVerificationResult verifySctSignatureOverBytes(SignedCertificateTimestamp signedCertificateTimestamp, byte[] bArr) {
        String str;
        SctVerificationResult signatureNotValid;
        if (Intrinsics.areEqual(this.logServer.getKey().getAlgorithm(), "EC")) {
            str = "SHA256withECDSA";
        } else if (!Intrinsics.areEqual(this.logServer.getKey().getAlgorithm(), "RSA")) {
            String algorithm = this.logServer.getKey().getAlgorithm();
            Intrinsics.checkNotNullExpressionValue(algorithm, "logServer.key.algorithm");
            return new UnsupportedSignatureAlgorithm(algorithm, null, 2, null);
        } else {
            str = "SHA256withRSA";
        }
        try {
            Signature signature = Signature.getInstance(str);
            signature.initVerify(this.logServer.getKey());
            signature.update(bArr);
            return signature.verify(signedCertificateTimestamp.getSignature().getSignature()) ? SctVerificationResult.Valid.INSTANCE : SctVerificationResult.Invalid.FailedVerification.INSTANCE;
        } catch (InvalidKeyException e2) {
            signatureNotValid = new LogPublicKeyNotValid(e2);
            return signatureNotValid;
        } catch (NoSuchAlgorithmException e3) {
            signatureNotValid = new UnsupportedSignatureAlgorithm(str, e3);
            return signatureNotValid;
        } catch (SignatureException e4) {
            signatureNotValid = new SignatureNotValid(e4);
            return signatureNotValid;
        }
    }

    @NotNull
    public final SctVerificationResult verifySCTOverPreCertificate$certificatetransparency(@NotNull SignedCertificateTimestamp sct, @NotNull X509Certificate certificate, @NotNull IssuerInformation issuerInfo) {
        CertificateEncodingFailed certificateEncodingFailed;
        Intrinsics.checkNotNullParameter(sct, "sct");
        Intrinsics.checkNotNullParameter(certificate, "certificate");
        Intrinsics.checkNotNullParameter(issuerInfo, "issuerInfo");
        try {
            byte[] encoded = createTbsForVerification(certificate, issuerInfo).getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded, "preCertificateTBS.encoded");
            return verifySctSignatureOverBytes(sct, serializeSignedSctDataForPreCertificate(encoded, issuerInfo.getKeyHash(), sct));
        } catch (IOException e2) {
            certificateEncodingFailed = new CertificateEncodingFailed(e2);
            return certificateEncodingFailed;
        } catch (CertificateException e3) {
            certificateEncodingFailed = new CertificateEncodingFailed(e3);
            return certificateEncodingFailed;
        }
    }

    @Override // com.appmattus.certificatetransparency.internal.verifier.SignatureVerifier
    @NotNull
    public SctVerificationResult verifySignature(@NotNull SignedCertificateTimestamp sct, @NotNull List<? extends java.security.cert.Certificate> chain) {
        IssuerInformation issuerInformation;
        CertificateEncodingFailed certificateEncodingFailed;
        Intrinsics.checkNotNullParameter(sct, "sct");
        Intrinsics.checkNotNullParameter(chain, "chain");
        long currentTimeMillis = System.currentTimeMillis();
        if (sct.getTimestamp() > currentTimeMillis) {
            return new SctVerificationResult.Invalid.FutureTimestamp(sct.getTimestamp(), currentTimeMillis);
        }
        if (this.logServer.getValidUntil() == null || sct.getTimestamp() <= this.logServer.getValidUntil().longValue()) {
            if (!Arrays.equals(this.logServer.getId(), sct.getId().getKeyId())) {
                String base64String = Base64.toBase64String(sct.getId().getKeyId());
                Intrinsics.checkNotNullExpressionValue(base64String, "toBase64String(sct.id.keyId)");
                String base64String2 = Base64.toBase64String(this.logServer.getId());
                Intrinsics.checkNotNullExpressionValue(base64String2, "toBase64String(logServer.id)");
                return new LogIdMismatch(base64String, base64String2);
            }
            java.security.cert.Certificate certificate = chain.get(0);
            if (!CertificateExtKt.isPreCertificate(certificate) && !CertificateExtKt.hasEmbeddedSct(certificate)) {
                try {
                    return verifySctSignatureOverBytes(sct, serializeSignedSctData(certificate, sct));
                } catch (IOException e2) {
                    certificateEncodingFailed = new CertificateEncodingFailed(e2);
                    return certificateEncodingFailed;
                } catch (CertificateEncodingException e3) {
                    certificateEncodingFailed = new CertificateEncodingFailed(e3);
                    return certificateEncodingFailed;
                }
            } else if (chain.size() < 2) {
                return NoIssuer.INSTANCE;
            } else {
                java.security.cert.Certificate certificate2 = chain.get(1);
                try {
                    if (!CertificateExtKt.isPreCertificateSigningCert(certificate2)) {
                        try {
                            issuerInformation = CertificateExtKt.issuerInformation(certificate2);
                        } catch (NoSuchAlgorithmException e4) {
                            return new UnsupportedSignatureAlgorithm("SHA-256", e4);
                        }
                    } else if (chain.size() < 3) {
                        return NoIssuerWithPreCert.INSTANCE;
                    } else {
                        try {
                            issuerInformation = CertificateExtKt.issuerInformationFromPreCertificate(certificate2, chain.get(2));
                        } catch (IOException e5) {
                            return new ASN1ParsingFailed(e5);
                        } catch (NoSuchAlgorithmException e6) {
                            return new UnsupportedSignatureAlgorithm("SHA-256", e6);
                        } catch (CertificateEncodingException e7) {
                            return new CertificateEncodingFailed(e7);
                        }
                    }
                    return verifySCTOverPreCertificate$certificatetransparency(sct, (X509Certificate) certificate, issuerInformation);
                } catch (CertificateParsingException e8) {
                    return new CertificateParsingFailed(e8);
                }
            }
        }
        return new SctVerificationResult.Invalid.LogServerUntrusted(sct.getTimestamp(), this.logServer.getValidUntil().longValue());
    }
}
