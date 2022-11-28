package com.appmattus.certificatetransparency.internal.loglist;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import okhttp3.Call;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CallExtKt {
    @Nullable
    public static final Object await(@NotNull Call call, @NotNull Continuation<? super byte[]> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new CallExtKt$await$2(call, null), continuation);
    }
}
