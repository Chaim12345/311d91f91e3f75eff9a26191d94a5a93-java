package com.appmattus.certificaterevocation;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
/* loaded from: classes.dex */
public final class CRInterceptorBuilderExtKt {
    public static final /* synthetic */ Interceptor certificateRevocationInterceptor(Function1 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        CRInterceptorBuilder cRInterceptorBuilder = new CRInterceptorBuilder();
        init.invoke(cRInterceptorBuilder);
        return cRInterceptorBuilder.build();
    }

    public static /* synthetic */ Interceptor certificateRevocationInterceptor$default(Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function1 = CRInterceptorBuilderExtKt$certificateRevocationInterceptor$1.INSTANCE;
        }
        return certificateRevocationInterceptor(function1);
    }
}
