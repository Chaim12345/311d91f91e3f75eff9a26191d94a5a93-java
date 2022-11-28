package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.loglist.LogListResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class LogListJsonFailedLoading extends LogListResult.Invalid {
    @NotNull
    public static final LogListJsonFailedLoading INSTANCE = new LogListJsonFailedLoading();

    private LogListJsonFailedLoading() {
    }

    @NotNull
    public String toString() {
        return "log-list.json failed to load";
    }
}
