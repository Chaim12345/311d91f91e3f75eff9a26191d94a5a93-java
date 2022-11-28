package com.appmattus.certificatetransparency;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
final class CTInterceptorBuilderExtKt$certificateTransparencyInterceptor$1 extends Lambda implements Function1<CTInterceptorBuilder, Unit> {
    public static final CTInterceptorBuilderExtKt$certificateTransparencyInterceptor$1 INSTANCE = new CTInterceptorBuilderExtKt$certificateTransparencyInterceptor$1();

    CTInterceptorBuilderExtKt$certificateTransparencyInterceptor$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(CTInterceptorBuilder cTInterceptorBuilder) {
        invoke2(cTInterceptorBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull CTInterceptorBuilder cTInterceptorBuilder) {
        Intrinsics.checkNotNullParameter(cTInterceptorBuilder, "$this$null");
    }
}
