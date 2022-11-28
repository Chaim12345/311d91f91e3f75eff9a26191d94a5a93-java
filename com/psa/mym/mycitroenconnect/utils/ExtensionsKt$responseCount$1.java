package com.psa.mym.mycitroenconnect.utils;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ExtensionsKt$responseCount$1 extends Lambda implements Function1<Response, Response> {
    public static final ExtensionsKt$responseCount$1 INSTANCE = new ExtensionsKt$responseCount$1();

    ExtensionsKt$responseCount$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final Response invoke(@NotNull Response it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.priorResponse();
    }
}
