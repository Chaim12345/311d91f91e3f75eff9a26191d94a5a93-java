package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.SctVerificationResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class NoIssuerWithPreCert extends SctVerificationResult.Invalid.Failed {
    @NotNull
    public static final NoIssuerWithPreCert INSTANCE = new NoIssuerWithPreCert();

    private NoIssuerWithPreCert() {
    }

    @NotNull
    public String toString() {
        return "Chain with PreCertificate signed by PreCertificate Signing Cert must contain issuer";
    }
}
