package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.loglist.LogListResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class NoLogServers extends LogListResult.Invalid {
    @NotNull
    public static final NoLogServers INSTANCE = new NoLogServers();

    private NoLogServers() {
    }

    @NotNull
    public String toString() {
        return "log-list.json contains no log servers";
    }
}
