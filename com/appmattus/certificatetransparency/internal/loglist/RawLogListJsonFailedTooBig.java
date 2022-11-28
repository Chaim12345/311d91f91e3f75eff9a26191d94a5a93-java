package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class RawLogListJsonFailedTooBig extends RawLogListResult.Failure {
    @NotNull
    public static final RawLogListJsonFailedTooBig INSTANCE = new RawLogListJsonFailedTooBig();

    private RawLogListJsonFailedTooBig() {
    }

    @NotNull
    public String toString() {
        return "log-list.json is too large";
    }
}
