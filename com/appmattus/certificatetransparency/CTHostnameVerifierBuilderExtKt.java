package com.appmattus.certificatetransparency;

import javax.net.ssl.HostnameVerifier;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class CTHostnameVerifierBuilderExtKt {
    public static final /* synthetic */ HostnameVerifier certificateTransparencyHostnameVerifier(HostnameVerifier delegate, Function1 init) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(init, "init");
        CTHostnameVerifierBuilder cTHostnameVerifierBuilder = new CTHostnameVerifierBuilder(delegate);
        init.invoke(cTHostnameVerifierBuilder);
        return cTHostnameVerifierBuilder.build();
    }

    public static /* synthetic */ HostnameVerifier certificateTransparencyHostnameVerifier$default(HostnameVerifier hostnameVerifier, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function1 = CTHostnameVerifierBuilderExtKt$certificateTransparencyHostnameVerifier$1.INSTANCE;
        }
        return certificateTransparencyHostnameVerifier(hostnameVerifier, function1);
    }
}
