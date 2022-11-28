package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.SctVerificationResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class NoIssuer extends SctVerificationResult.Invalid.Failed {
    @NotNull
    public static final NoIssuer INSTANCE = new NoIssuer();

    private NoIssuer() {
    }

    @NotNull
    public String toString() {
        return "Chain with PreCertificate or Certificate must contain issuer";
    }
}
