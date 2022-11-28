package com.appmattus.certificatetransparency;

import javax.net.ssl.X509TrustManager;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class CTTrustManagerBuilderExtKt {
    public static final /* synthetic */ X509TrustManager certificateTransparencyTrustManager(X509TrustManager delegate, Function1 init) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(init, "init");
        CTTrustManagerBuilder cTTrustManagerBuilder = new CTTrustManagerBuilder(delegate);
        init.invoke(cTTrustManagerBuilder);
        return cTTrustManagerBuilder.build();
    }

    public static /* synthetic */ X509TrustManager certificateTransparencyTrustManager$default(X509TrustManager x509TrustManager, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            function1 = CTTrustManagerBuilderExtKt$certificateTransparencyTrustManager$1.INSTANCE;
        }
        return certificateTransparencyTrustManager(x509TrustManager, function1);
    }
}
