package com.appmattus.certificatetransparency.internal.utils;

import com.appmattus.certificatetransparency.internal.serialization.CTConstants;
import com.appmattus.certificatetransparency.internal.verifier.model.IssuerInformation;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Set;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.Extensions;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class CertificateExtKt {
    @NotNull
    private static final String X509_AUTHORITY_KEY_IDENTIFIER = "2.5.29.35";

    public static final boolean hasEmbeddedSct(@NotNull Certificate certificate) {
        Intrinsics.checkNotNullParameter(certificate, "<this>");
        if (certificate instanceof X509Certificate) {
            Set<String> nonCriticalExtensionOIDs = ((X509Certificate) certificate).getNonCriticalExtensionOIDs();
            if (nonCriticalExtensionOIDs != null && nonCriticalExtensionOIDs.contains(CTConstants.SCT_CERTIFICATE_OID)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isPreCertificate(@NotNull Certificate certificate) {
        Intrinsics.checkNotNullParameter(certificate, "<this>");
        if (certificate instanceof X509Certificate) {
            Set<String> criticalExtensionOIDs = ((X509Certificate) certificate).getCriticalExtensionOIDs();
            if (criticalExtensionOIDs != null && criticalExtensionOIDs.contains(CTConstants.POISON_EXTENSION_OID)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isPreCertificateSigningCert(@NotNull Certificate certificate) {
        Intrinsics.checkNotNullParameter(certificate, "<this>");
        if (certificate instanceof X509Certificate) {
            List<String> extendedKeyUsage = ((X509Certificate) certificate).getExtendedKeyUsage();
            if (extendedKeyUsage != null && extendedKeyUsage.contains(CTConstants.PRECERTIFICATE_SIGNING_OID)) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public static final IssuerInformation issuerInformation(@NotNull Certificate certificate) {
        Intrinsics.checkNotNullParameter(certificate, "<this>");
        return new IssuerInformation(null, keyHash(certificate), null, false, 5, null);
    }

    @NotNull
    public static final IssuerInformation issuerInformationFromPreCertificate(@NotNull Certificate certificate, @NotNull Certificate preCertificate) {
        Intrinsics.checkNotNullParameter(certificate, "<this>");
        Intrinsics.checkNotNullParameter(preCertificate, "preCertificate");
        ASN1InputStream aSN1InputStream = new ASN1InputStream(certificate.getEncoded());
        try {
            org.bouncycastle.asn1.x509.Certificate certificate2 = org.bouncycastle.asn1.x509.Certificate.getInstance(aSN1InputStream.readObject());
            Extensions extensions = certificate2.getTBSCertificate().getExtensions();
            IssuerInformation issuerInformation = new IssuerInformation(certificate2.getIssuer(), keyHash(preCertificate), extensions != null ? extensions.getExtension(new ASN1ObjectIdentifier(X509_AUTHORITY_KEY_IDENTIFIER)) : null, true);
            CloseableKt.closeFinally(aSN1InputStream, null);
            return issuerInformation;
        } finally {
        }
    }

    private static final byte[] keyHash(Certificate certificate) {
        PublicKey publicKey = certificate.getPublicKey();
        Intrinsics.checkNotNullExpressionValue(publicKey, "publicKey");
        return PublicKeyExtKt.sha256Hash(publicKey);
    }
}
