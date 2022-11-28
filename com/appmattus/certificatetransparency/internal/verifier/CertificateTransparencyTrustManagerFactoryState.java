package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.CTTrustManagerBuilder;
import javax.net.ssl.TrustManagerFactory;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class CertificateTransparencyTrustManagerFactoryState {
    @Nullable
    private static TrustManagerFactory delegate;
    @NotNull
    public static final CertificateTransparencyTrustManagerFactoryState INSTANCE = new CertificateTransparencyTrustManagerFactoryState();
    @NotNull
    private static Function1<? super CTTrustManagerBuilder, Unit> init = CertificateTransparencyTrustManagerFactoryState$init$1.INSTANCE;

    private CertificateTransparencyTrustManagerFactoryState() {
    }

    @Nullable
    public final TrustManagerFactory getDelegate() {
        return delegate;
    }

    @NotNull
    public final Function1<CTTrustManagerBuilder, Unit> getInit() {
        return init;
    }

    public final void setDelegate(@Nullable TrustManagerFactory trustManagerFactory) {
        delegate = trustManagerFactory;
    }

    public final void setInit(@NotNull Function1<? super CTTrustManagerBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        init = function1;
    }
}
