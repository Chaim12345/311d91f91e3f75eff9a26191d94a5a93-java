package com.appmattus.certificaterevocation;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public interface CRLogger {

    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static void log(@NotNull CRLogger cRLogger, @NotNull String host, @NotNull RevocationResult result) {
            Intrinsics.checkNotNullParameter(host, "host");
            Intrinsics.checkNotNullParameter(result, "result");
        }
    }

    void log(@NotNull String str, @NotNull RevocationResult revocationResult);
}
