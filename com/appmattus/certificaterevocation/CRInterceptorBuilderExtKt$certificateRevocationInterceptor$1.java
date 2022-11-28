package com.appmattus.certificaterevocation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
final class CRInterceptorBuilderExtKt$certificateRevocationInterceptor$1 extends Lambda implements Function1<CRInterceptorBuilder, Unit> {
    public static final CRInterceptorBuilderExtKt$certificateRevocationInterceptor$1 INSTANCE = new CRInterceptorBuilderExtKt$certificateRevocationInterceptor$1();

    CRInterceptorBuilderExtKt$certificateRevocationInterceptor$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(CRInterceptorBuilder cRInterceptorBuilder) {
        invoke2(cRInterceptorBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull CRInterceptorBuilder cRInterceptorBuilder) {
        Intrinsics.checkNotNullParameter(cRInterceptorBuilder, "$this$null");
    }
}
