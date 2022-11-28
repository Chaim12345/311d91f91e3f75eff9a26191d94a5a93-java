package com.appmattus.certificatetransparency;

import com.appmattus.certificatetransparency.internal.verifier.CertificateTransparencyProvider;
import com.appmattus.certificatetransparency.internal.verifier.CertificateTransparencyTrustManagerFactoryState;
import java.security.Security;
import javax.net.ssl.TrustManagerFactory;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes.dex */
public final class CTProviderKt {
    public static final /* synthetic */ void installCertificateTransparencyProvider(Function1 init) {
        Intrinsics.checkNotNullParameter(init, "init");
        CertificateTransparencyTrustManagerFactoryState certificateTransparencyTrustManagerFactoryState = CertificateTransparencyTrustManagerFactoryState.INSTANCE;
        certificateTransparencyTrustManagerFactoryState.setDelegate(TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()));
        certificateTransparencyTrustManagerFactoryState.setInit(init);
        Security.insertProviderAt(new CertificateTransparencyProvider(), 1);
    }

    public static /* synthetic */ void installCertificateTransparencyProvider$default(Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            function1 = CTProviderKt$installCertificateTransparencyProvider$1.INSTANCE;
        }
        installCertificateTransparencyProvider(function1);
    }

    public static final /* synthetic */ void removeCertificateTransparencyProvider() {
        Security.removeProvider("CertificateTransparencyProvider");
        CertificateTransparencyTrustManagerFactoryState certificateTransparencyTrustManagerFactoryState = CertificateTransparencyTrustManagerFactoryState.INSTANCE;
        certificateTransparencyTrustManagerFactoryState.setDelegate(null);
        certificateTransparencyTrustManagerFactoryState.setInit(CTProviderKt$removeCertificateTransparencyProvider$1$1.INSTANCE);
    }
}
