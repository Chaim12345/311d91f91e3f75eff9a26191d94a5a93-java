package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.CTTrustManagerBuilder;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
final class CertificateTransparencyTrustManagerFactoryState$init$1 extends Lambda implements Function1<CTTrustManagerBuilder, Unit> {
    public static final CertificateTransparencyTrustManagerFactoryState$init$1 INSTANCE = new CertificateTransparencyTrustManagerFactoryState$init$1();

    CertificateTransparencyTrustManagerFactoryState$init$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(CTTrustManagerBuilder cTTrustManagerBuilder) {
        invoke2(cTTrustManagerBuilder);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull CTTrustManagerBuilder cTTrustManagerBuilder) {
        Intrinsics.checkNotNullParameter(cTTrustManagerBuilder, "$this$null");
    }
}
