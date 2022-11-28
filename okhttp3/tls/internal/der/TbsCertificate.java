package okhttp3.tls.internal.der;

import java.math.BigInteger;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TbsCertificate {
    @NotNull
    private final List<Extension> extensions;
    @NotNull
    private final List<List<AttributeTypeAndValue>> issuer;
    @Nullable
    private final BitString issuerUniqueID;
    @NotNull
    private final BigInteger serialNumber;
    @NotNull
    private final AlgorithmIdentifier signature;
    @NotNull
    private final List<List<AttributeTypeAndValue>> subject;
    @NotNull
    private final SubjectPublicKeyInfo subjectPublicKeyInfo;
    @Nullable
    private final BitString subjectUniqueID;
    @NotNull
    private final Validity validity;
    private final long version;

    /* JADX WARN: Multi-variable type inference failed */
    public TbsCertificate(long j2, @NotNull BigInteger serialNumber, @NotNull AlgorithmIdentifier signature, @NotNull List<? extends List<AttributeTypeAndValue>> issuer, @NotNull Validity validity, @NotNull List<? extends List<AttributeTypeAndValue>> subject, @NotNull SubjectPublicKeyInfo subjectPublicKeyInfo, @Nullable BitString bitString, @Nullable BitString bitString2, @NotNull List<Extension> extensions) {
        Intrinsics.checkNotNullParameter(serialNumber, "serialNumber");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(issuer, "issuer");
        Intrinsics.checkNotNullParameter(validity, "validity");
        Intrinsics.checkNotNullParameter(subject, "subject");
        Intrinsics.checkNotNullParameter(subjectPublicKeyInfo, "subjectPublicKeyInfo");
        Intrinsics.checkNotNullParameter(extensions, "extensions");
        this.version = j2;
        this.serialNumber = serialNumber;
        this.signature = signature;
        this.issuer = issuer;
        this.validity = validity;
        this.subject = subject;
        this.subjectPublicKeyInfo = subjectPublicKeyInfo;
        this.issuerUniqueID = bitString;
        this.subjectUniqueID = bitString2;
        this.extensions = extensions;
    }

    public final long component1() {
        return this.version;
    }

    @NotNull
    public final List<Extension> component10() {
        return this.extensions;
    }

    @NotNull
    public final BigInteger component2() {
        return this.serialNumber;
    }

    @NotNull
    public final AlgorithmIdentifier component3() {
        return this.signature;
    }

    @NotNull
    public final List<List<AttributeTypeAndValue>> component4() {
        return this.issuer;
    }

    @NotNull
    public final Validity component5() {
        return this.validity;
    }

    @NotNull
    public final List<List<AttributeTypeAndValue>> component6() {
        return this.subject;
    }

    @NotNull
    public final SubjectPublicKeyInfo component7() {
        return this.subjectPublicKeyInfo;
    }

    @Nullable
    public final BitString component8() {
        return this.issuerUniqueID;
    }

    @Nullable
    public final BitString component9() {
        return this.subjectUniqueID;
    }

    @NotNull
    public final TbsCertificate copy(long j2, @NotNull BigInteger serialNumber, @NotNull AlgorithmIdentifier signature, @NotNull List<? extends List<AttributeTypeAndValue>> issuer, @NotNull Validity validity, @NotNull List<? extends List<AttributeTypeAndValue>> subject, @NotNull SubjectPublicKeyInfo subjectPublicKeyInfo, @Nullable BitString bitString, @Nullable BitString bitString2, @NotNull List<Extension> extensions) {
        Intrinsics.checkNotNullParameter(serialNumber, "serialNumber");
        Intrinsics.checkNotNullParameter(signature, "signature");
        Intrinsics.checkNotNullParameter(issuer, "issuer");
        Intrinsics.checkNotNullParameter(validity, "validity");
        Intrinsics.checkNotNullParameter(subject, "subject");
        Intrinsics.checkNotNullParameter(subjectPublicKeyInfo, "subjectPublicKeyInfo");
        Intrinsics.checkNotNullParameter(extensions, "extensions");
        return new TbsCertificate(j2, serialNumber, signature, issuer, validity, subject, subjectPublicKeyInfo, bitString, bitString2, extensions);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TbsCertificate) {
            TbsCertificate tbsCertificate = (TbsCertificate) obj;
            return this.version == tbsCertificate.version && Intrinsics.areEqual(this.serialNumber, tbsCertificate.serialNumber) && Intrinsics.areEqual(this.signature, tbsCertificate.signature) && Intrinsics.areEqual(this.issuer, tbsCertificate.issuer) && Intrinsics.areEqual(this.validity, tbsCertificate.validity) && Intrinsics.areEqual(this.subject, tbsCertificate.subject) && Intrinsics.areEqual(this.subjectPublicKeyInfo, tbsCertificate.subjectPublicKeyInfo) && Intrinsics.areEqual(this.issuerUniqueID, tbsCertificate.issuerUniqueID) && Intrinsics.areEqual(this.subjectUniqueID, tbsCertificate.subjectUniqueID) && Intrinsics.areEqual(this.extensions, tbsCertificate.extensions);
        }
        return false;
    }

    @NotNull
    public final List<Extension> getExtensions() {
        return this.extensions;
    }

    @NotNull
    public final List<List<AttributeTypeAndValue>> getIssuer() {
        return this.issuer;
    }

    @Nullable
    public final BitString getIssuerUniqueID() {
        return this.issuerUniqueID;
    }

    @NotNull
    public final BigInteger getSerialNumber() {
        return this.serialNumber;
    }

    @NotNull
    public final AlgorithmIdentifier getSignature() {
        return this.signature;
    }

    @NotNull
    public final String getSignatureAlgorithmName() {
        String algorithm = this.signature.getAlgorithm();
        if (Intrinsics.areEqual(algorithm, ObjectIdentifiers.sha256WithRSAEncryption)) {
            return "SHA256WithRSA";
        }
        if (Intrinsics.areEqual(algorithm, ObjectIdentifiers.sha256withEcdsa)) {
            return "SHA256withECDSA";
        }
        throw new IllegalStateException(Intrinsics.stringPlus("unexpected signature algorithm: ", this.signature.getAlgorithm()).toString());
    }

    @NotNull
    public final List<List<AttributeTypeAndValue>> getSubject() {
        return this.subject;
    }

    @NotNull
    public final SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.subjectPublicKeyInfo;
    }

    @Nullable
    public final BitString getSubjectUniqueID() {
        return this.subjectUniqueID;
    }

    @NotNull
    public final Validity getValidity() {
        return this.validity;
    }

    public final long getVersion() {
        return this.version;
    }

    public int hashCode() {
        int hashCode = (((((((((((((((int) this.version) + 0) * 31) + this.serialNumber.hashCode()) * 31) + this.signature.hashCode()) * 31) + this.issuer.hashCode()) * 31) + this.validity.hashCode()) * 31) + this.subject.hashCode()) * 31) + this.subjectPublicKeyInfo.hashCode()) * 31;
        BitString bitString = this.issuerUniqueID;
        int hashCode2 = (hashCode + (bitString == null ? 0 : bitString.hashCode())) * 31;
        BitString bitString2 = this.subjectUniqueID;
        return ((hashCode2 + (bitString2 != null ? bitString2.hashCode() : 0)) * 31) + this.extensions.hashCode();
    }

    @NotNull
    public String toString() {
        return "TbsCertificate(version=" + this.version + ", serialNumber=" + this.serialNumber + ", signature=" + this.signature + ", issuer=" + this.issuer + ", validity=" + this.validity + ", subject=" + this.subject + ", subjectPublicKeyInfo=" + this.subjectPublicKeyInfo + ", issuerUniqueID=" + this.issuerUniqueID + ", subjectUniqueID=" + this.subjectUniqueID + ", extensions=" + this.extensions + ')';
    }
}
