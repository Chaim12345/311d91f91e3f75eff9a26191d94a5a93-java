package com.appmattus.certificatetransparency;

import java.security.cert.X509Certificate;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public interface CTPolicy {
    @NotNull
    VerificationResult policyVerificationResult(@NotNull X509Certificate x509Certificate, @NotNull Map<String, ? extends SctVerificationResult> map);
}
