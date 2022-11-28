package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class RawLogListSigFailedTooBig extends RawLogListResult.Failure {
    @NotNull
    public static final RawLogListSigFailedTooBig INSTANCE = new RawLogListSigFailedTooBig();

    private RawLogListSigFailedTooBig() {
    }

    @NotNull
    public String toString() {
        return "log-list.sig is too large";
    }
}
