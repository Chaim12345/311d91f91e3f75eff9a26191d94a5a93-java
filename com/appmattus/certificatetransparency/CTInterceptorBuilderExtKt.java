package com.appmattus.certificatetransparency;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
/* loaded from: classes.dex */
public final class CTInterceptorBuilderExtKt {
    public static final /* synthetic */ Interceptor certificateTransparencyInterceptor(Function1 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        CTInterceptorBuilder cTInterceptorBuilder = new CTInterceptorBuilder();
        init.invoke(cTInterceptorBuilder);
        return cTInterceptorBuilder.build();
    }

    public static /* synthetic */ Interceptor certificateTransparencyInterceptor$default(Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function1 = CTInterceptorBuilderExtKt$certificateTransparencyInterceptor$1.INSTANCE;
        }
        return certificateTransparencyInterceptor(function1);
    }
}
