package com.appmattus.certificatetransparency;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public interface CTLogger {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static void log(@NotNull CTLogger cTLogger, @NotNull String host, @NotNull VerificationResult result) {
            Intrinsics.checkNotNullParameter(host, "host");
            Intrinsics.checkNotNullParameter(result, "result");
        }
    }

    void log(@NotNull String str, @NotNull VerificationResult verificationResult);
}
