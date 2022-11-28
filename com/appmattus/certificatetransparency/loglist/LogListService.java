package com.appmattus.certificatetransparency.loglist;

import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public interface LogListService {
    @Nullable
    Object getLogList(@NotNull Continuation<? super byte[]> continuation);

    @Nullable
    Object getLogListSignature(@NotNull Continuation<? super byte[]> continuation);

    @Nullable
    Object getLogListZip(@NotNull Continuation<? super byte[]> continuation);
}
