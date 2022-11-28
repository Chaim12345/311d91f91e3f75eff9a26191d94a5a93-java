package com.appmattus.certificatetransparency.internal.loglist.parser;

import com.appmattus.certificatetransparency.loglist.LogListResult;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public interface LogListJsonParser {
    @NotNull
    LogListResult parseJson(@NotNull String str);
}
