package com.appmattus.certificatetransparency.internal.verifier.model;

import java.util.Arrays;
import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.Extension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class IssuerInformation {
    private final boolean issuedByPreCertificateSigningCert;
    @NotNull
    private final byte[] keyHash;
    @Nullable
    private final X500Name name;
    @Nullable
    private final Extension x509authorityKeyIdentifier;

    public IssuerInformation(@Nullable X500Name x500Name, @NotNull byte[] keyHash, @Nullable Extension extension, boolean z) {
        Intrinsics.checkNotNullParameter(keyHash, "keyHash");
        this.name = x500Name;
        this.keyHash = keyHash;
        this.x509authorityKeyIdentifier = extension;
        this.issuedByPreCertificateSigningCert = z;
    }

    public /* synthetic */ IssuerInformation(X500Name x500Name, byte[] bArr, Extension extension, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : x500Name, bArr, (i2 & 4) != 0 ? null : extension, z);
    }

    public static /* synthetic */ IssuerInformation copy$default(IssuerInformation issuerInformation, X500Name x500Name, byte[] bArr, Extension extension, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            x500Name = issuerInformation.name;
        }
        if ((i2 & 2) != 0) {
            bArr = issuerInformation.keyHash;
        }
        if ((i2 & 4) != 0) {
            extension = issuerInformation.x509authorityKeyIdentifier;
        }
        if ((i2 & 8) != 0) {
            z = issuerInformation.issuedByPreCertificateSigningCert;
        }
        return issuerInformation.copy(x500Name, bArr, extension, z);
    }

    @Nullable
    public final X500Name component1() {
        return this.name;
    }

    @NotNull
    public final byte[] component2() {
        return this.keyHash;
    }

    @Nullable
    public final Extension component3() {
        return this.x509authorityKeyIdentifier;
    }

    public final boolean component4() {
        return this.issuedByPreCertificateSigningCert;
    }

    @NotNull
    public final IssuerInformation copy(@Nullable X500Name x500Name, @NotNull byte[] keyHash, @Nullable Extension extension, boolean z) {
        Intrinsics.checkNotNullParameter(keyHash, "keyHash");
        return new IssuerInformation(x500Name, keyHash, extension, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (Intrinsics.areEqual(IssuerInformation.class, obj != null ? obj.getClass() : null)) {
            Objects.requireNonNull(obj, "null cannot be cast to non-null type com.appmattus.certificatetransparency.internal.verifier.model.IssuerInformation");
            IssuerInformation issuerInformation = (IssuerInformation) obj;
            return Objects.equals(this.name, issuerInformation.name) && Arrays.equals(this.keyHash, issuerInformation.keyHash) && Objects.equals(this.x509authorityKeyIdentifier, issuerInformation.x509authorityKeyIdentifier) && this.issuedByPreCertificateSigningCert == issuerInformation.issuedByPreCertificateSigningCert;
        }
        return false;
    }

    public final boolean getIssuedByPreCertificateSigningCert() {
        return this.issuedByPreCertificateSigningCert;
    }

    @NotNull
    public final byte[] getKeyHash() {
        return this.keyHash;
    }

    @Nullable
    public final X500Name getName() {
        return this.name;
    }

    @Nullable
    public final Extension getX509authorityKeyIdentifier() {
        return this.x509authorityKeyIdentifier;
    }

    public int hashCode() {
        X500Name x500Name = this.name;
        int hashCode = (((x500Name != null ? x500Name.hashCode() : 0) * 31) + Arrays.hashCode(this.keyHash)) * 31;
        Extension extension = this.x509authorityKeyIdentifier;
        return ((hashCode + (extension != null ? extension.hashCode() : 0)) * 31) + Boolean.hashCode(this.issuedByPreCertificateSigningCert);
    }

    @NotNull
    public String toString() {
        return "IssuerInformation(name=" + this.name + ", keyHash=" + Arrays.toString(this.keyHash) + ", x509authorityKeyIdentifier=" + this.x509authorityKeyIdentifier + ", issuedByPreCertificateSigningCert=" + this.issuedByPreCertificateSigningCert + ')';
    }
}
