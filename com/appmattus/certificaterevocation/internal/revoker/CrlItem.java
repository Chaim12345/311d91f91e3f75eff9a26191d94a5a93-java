package com.appmattus.certificaterevocation.internal.revoker;

import java.math.BigInteger;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CrlItem {
    @NotNull
    private final X500Principal issuerDistinguishedName;
    @NotNull
    private final List<BigInteger> serialNumbers;

    /* JADX WARN: Multi-variable type inference failed */
    public CrlItem(@NotNull X500Principal issuerDistinguishedName, @NotNull List<? extends BigInteger> serialNumbers) {
        Intrinsics.checkNotNullParameter(issuerDistinguishedName, "issuerDistinguishedName");
        Intrinsics.checkNotNullParameter(serialNumbers, "serialNumbers");
        this.issuerDistinguishedName = issuerDistinguishedName;
        this.serialNumbers = serialNumbers;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CrlItem copy$default(CrlItem crlItem, X500Principal x500Principal, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            x500Principal = crlItem.issuerDistinguishedName;
        }
        if ((i2 & 2) != 0) {
            list = crlItem.serialNumbers;
        }
        return crlItem.copy(x500Principal, list);
    }

    @NotNull
    public final X500Principal component1() {
        return this.issuerDistinguishedName;
    }

    @NotNull
    public final List<BigInteger> component2() {
        return this.serialNumbers;
    }

    @NotNull
    public final CrlItem copy(@NotNull X500Principal issuerDistinguishedName, @NotNull List<? extends BigInteger> serialNumbers) {
        Intrinsics.checkNotNullParameter(issuerDistinguishedName, "issuerDistinguishedName");
        Intrinsics.checkNotNullParameter(serialNumbers, "serialNumbers");
        return new CrlItem(issuerDistinguishedName, serialNumbers);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CrlItem) {
            CrlItem crlItem = (CrlItem) obj;
            return Intrinsics.areEqual(this.issuerDistinguishedName, crlItem.issuerDistinguishedName) && Intrinsics.areEqual(this.serialNumbers, crlItem.serialNumbers);
        }
        return false;
    }

    @NotNull
    public final X500Principal getIssuerDistinguishedName() {
        return this.issuerDistinguishedName;
    }

    @NotNull
    public final List<BigInteger> getSerialNumbers() {
        return this.serialNumbers;
    }

    public int hashCode() {
        return (this.issuerDistinguishedName.hashCode() * 31) + this.serialNumbers.hashCode();
    }

    @NotNull
    public String toString() {
        return "CrlItem(issuerDistinguishedName=" + this.issuerDistinguishedName + ", serialNumbers=" + this.serialNumbers + ')';
    }
}
