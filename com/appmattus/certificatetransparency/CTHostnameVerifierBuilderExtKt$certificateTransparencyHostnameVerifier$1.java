package com.appmattus.certificatetransparency;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
final class CTHostnameVerifierBuilderExtKt$certificateTransparencyHostnameVerifier$1 extends Lambda implements Function1<CTHostnameVerifierBuilder, Unit> {
    public static final CTHostnameVerifierBuilderExtKt$certificateTransparencyHostnameVerifier$1 INSTANCE = new CTHostnameVerifierBuilderExtKt$certificateTransparencyHostnameVerifier$1();

    CTHostnameVerifierBuilderExtKt$certificateTransparencyHostnameVerifier$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(CTHostnameVerifierBuilder cTHostnameVerifierBuilder) {
        invoke2(cTHostnameVerifierBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull CTHostnameVerifierBuilder cTHostnameVerifierBuilder) {
        Intrinsics.checkNotNullParameter(cTHostnameVerifierBuilder, "$this$null");
    }
}
