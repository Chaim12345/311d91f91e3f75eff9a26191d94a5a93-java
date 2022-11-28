package com.appmattus.certificaterevocation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
final class CRHostnameVerifierBuilderExtKt$certificateRevocationHostnameVerifier$1 extends Lambda implements Function1<CRHostnameVerifierBuilder, Unit> {
    public static final CRHostnameVerifierBuilderExtKt$certificateRevocationHostnameVerifier$1 INSTANCE = new CRHostnameVerifierBuilderExtKt$certificateRevocationHostnameVerifier$1();

    CRHostnameVerifierBuilderExtKt$certificateRevocationHostnameVerifier$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(CRHostnameVerifierBuilder cRHostnameVerifierBuilder) {
        invoke2(cRHostnameVerifierBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull CRHostnameVerifierBuilder cRHostnameVerifierBuilder) {
        Intrinsics.checkNotNullParameter(cRHostnameVerifierBuilder, "$this$null");
    }
}
