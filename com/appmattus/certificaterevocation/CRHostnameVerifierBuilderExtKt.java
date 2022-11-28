package com.appmattus.certificaterevocation;

import javax.net.ssl.HostnameVerifier;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class CRHostnameVerifierBuilderExtKt {
    public static final /* synthetic */ HostnameVerifier certificateRevocationHostnameVerifier(HostnameVerifier delegate, Function1 init) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(init, "init");
        CRHostnameVerifierBuilder cRHostnameVerifierBuilder = new CRHostnameVerifierBuilder(delegate);
        init.invoke(cRHostnameVerifierBuilder);
        return cRHostnameVerifierBuilder.build();
    }

    public static /* synthetic */ HostnameVerifier certificateRevocationHostnameVerifier$default(HostnameVerifier hostnameVerifier, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function1 = CRHostnameVerifierBuilderExtKt$certificateRevocationHostnameVerifier$1.INSTANCE;
        }
        return certificateRevocationHostnameVerifier(hostnameVerifier, function1);
    }
}
