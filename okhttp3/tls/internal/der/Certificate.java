package okhttp3.tls.internal.der;

import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Certificate {
    @NotNull
    private final AlgorithmIdentifier signatureAlgorithm;
    @NotNull
    private final BitString signatureValue;
    @NotNull
    private final TbsCertificate tbsCertificate;

    public Certificate(@NotNull TbsCertificate tbsCertificate, @NotNull AlgorithmIdentifier signatureAlgorithm, @NotNull BitString signatureValue) {
        Intrinsics.checkNotNullParameter(tbsCertificate, "tbsCertificate");
        Intrinsics.checkNotNullParameter(signatureAlgorithm, "signatureAlgorithm");
        Intrinsics.checkNotNullParameter(signatureValue, "signatureValue");
        this.tbsCertificate = tbsCertificate;
        this.signatureAlgorithm = signatureAlgorithm;
        this.signatureValue = signatureValue;
    }

    public static /* synthetic */ Certificate copy$default(Certificate certificate, TbsCertificate tbsCertificate, AlgorithmIdentifier algorithmIdentifier, BitString bitString, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            tbsCertificate = certificate.tbsCertificate;
        }
        if ((i2 & 2) != 0) {
            algorithmIdentifier = certificate.signatureAlgorithm;
        }
        if ((i2 & 4) != 0) {
            bitString = certificate.signatureValue;
        }
        return certificate.copy(tbsCertificate, algorithmIdentifier, bitString);
    }

    public final boolean checkSignature(@NotNull PublicKey issuer) {
        Intrinsics.checkNotNullParameter(issuer, "issuer");
        ByteString der = CertificateAdapters.INSTANCE.getTbsCertificate$okhttp_tls().toDer(this.tbsCertificate);
        Signature signature = Signature.getInstance(this.tbsCertificate.getSignatureAlgorithmName());
        signature.initVerify(issuer);
        signature.update(der.toByteArray());
        return signature.verify(getSignatureValue().getByteString().toByteArray());
    }

    @NotNull
    public final TbsCertificate component1() {
        return this.tbsCertificate;
    }

    @NotNull
    public final AlgorithmIdentifier component2() {
        return this.signatureAlgorithm;
    }

    @NotNull
    public final BitString component3() {
        return this.signatureValue;
    }

    @NotNull
    public final Certificate copy(@NotNull TbsCertificate tbsCertificate, @NotNull AlgorithmIdentifier signatureAlgorithm, @NotNull BitString signatureValue) {
        Intrinsics.checkNotNullParameter(tbsCertificate, "tbsCertificate");
        Intrinsics.checkNotNullParameter(signatureAlgorithm, "signatureAlgorithm");
        Intrinsics.checkNotNullParameter(signatureValue, "signatureValue");
        return new Certificate(tbsCertificate, signatureAlgorithm, signatureValue);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Certificate) {
            Certificate certificate = (Certificate) obj;
            return Intrinsics.areEqual(this.tbsCertificate, certificate.tbsCertificate) && Intrinsics.areEqual(this.signatureAlgorithm, certificate.signatureAlgorithm) && Intrinsics.areEqual(this.signatureValue, certificate.signatureValue);
        }
        return false;
    }

    @NotNull
    public final Extension getBasicConstraints() {
        for (Extension extension : this.tbsCertificate.getExtensions()) {
            if (Intrinsics.areEqual(extension.getId(), ObjectIdentifiers.basicConstraints)) {
                return extension;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Nullable
    public final Object getCommonName() {
        List flatten;
        Object obj;
        flatten = CollectionsKt__IterablesKt.flatten(this.tbsCertificate.getSubject());
        Iterator it = flatten.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((AttributeTypeAndValue) obj).getType(), ObjectIdentifiers.commonName)) {
                break;
            }
        }
        AttributeTypeAndValue attributeTypeAndValue = (AttributeTypeAndValue) obj;
        if (attributeTypeAndValue == null) {
            return null;
        }
        return attributeTypeAndValue.getValue();
    }

    @Nullable
    public final Object getOrganizationalUnitName() {
        List flatten;
        Object obj;
        flatten = CollectionsKt__IterablesKt.flatten(this.tbsCertificate.getSubject());
        Iterator it = flatten.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((AttributeTypeAndValue) obj).getType(), ObjectIdentifiers.organizationalUnitName)) {
                break;
            }
        }
        AttributeTypeAndValue attributeTypeAndValue = (AttributeTypeAndValue) obj;
        if (attributeTypeAndValue == null) {
            return null;
        }
        return attributeTypeAndValue.getValue();
    }

    @NotNull
    public final AlgorithmIdentifier getSignatureAlgorithm() {
        return this.signatureAlgorithm;
    }

    @NotNull
    public final BitString getSignatureValue() {
        return this.signatureValue;
    }

    @Nullable
    public final Extension getSubjectAlternativeNames() {
        Object obj;
        Iterator<T> it = this.tbsCertificate.getExtensions().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual(((Extension) obj).getId(), ObjectIdentifiers.subjectAlternativeName)) {
                break;
            }
        }
        return (Extension) obj;
    }

    @NotNull
    public final TbsCertificate getTbsCertificate() {
        return this.tbsCertificate;
    }

    public int hashCode() {
        return (((this.tbsCertificate.hashCode() * 31) + this.signatureAlgorithm.hashCode()) * 31) + this.signatureValue.hashCode();
    }

    @NotNull
    public String toString() {
        return "Certificate(tbsCertificate=" + this.tbsCertificate + ", signatureAlgorithm=" + this.signatureAlgorithm + ", signatureValue=" + this.signatureValue + ')';
    }

    @NotNull
    public final X509Certificate toX509Certificate() {
        Object single;
        try {
            Collection<? extends java.security.cert.Certificate> certificates = CertificateFactory.getInstance("X.509").generateCertificates(new Buffer().write(CertificateAdapters.INSTANCE.getCertificate$okhttp_tls().toDer(this)).inputStream());
            Intrinsics.checkNotNullExpressionValue(certificates, "certificates");
            single = CollectionsKt___CollectionsKt.single(certificates);
            if (single != null) {
                return (X509Certificate) single;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.security.cert.X509Certificate");
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException("failed to decode certificate", e2);
        } catch (GeneralSecurityException e3) {
            throw new IllegalArgumentException("failed to decode certificate", e3);
        } catch (NoSuchElementException e4) {
            throw new IllegalArgumentException("failed to decode certificate", e4);
        }
    }
}
