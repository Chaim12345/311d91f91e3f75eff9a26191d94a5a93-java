package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.SctVerificationResult;
import com.appmattus.certificatetransparency.internal.verifier.model.SignedCertificateTimestamp;
import java.security.cert.Certificate;
import java.util.List;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public interface SignatureVerifier {
    @NotNull
    SctVerificationResult verifySignature(@NotNull SignedCertificateTimestamp signedCertificateTimestamp, @NotNull List<? extends Certificate> list);
}
