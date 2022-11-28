package com.appmattus.certificaterevocation.internal.revoker;

import com.appmattus.certificaterevocation.RevocationResult;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleaner;
import com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleanerFactory;
import java.io.IOException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.net.ssl.X509TrustManager;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public class CertificateRevocationBase {
    @Nullable
    private final CertificateChainCleanerFactory certificateChainCleanerFactory;
    @NotNull
    private final Lazy cleaner$delegate;
    @NotNull
    private final Set<CrlItem> crlSet;

    public CertificateRevocationBase() {
        this(null, null, null, 7, null);
    }

    public CertificateRevocationBase(@NotNull Set<CrlItem> crlSet, @Nullable CertificateChainCleanerFactory certificateChainCleanerFactory, @Nullable X509TrustManager x509TrustManager) {
        Lazy lazy;
        Intrinsics.checkNotNullParameter(crlSet, "crlSet");
        this.crlSet = crlSet;
        this.certificateChainCleanerFactory = certificateChainCleanerFactory;
        lazy = LazyKt__LazyJVMKt.lazy(new CertificateRevocationBase$cleaner$2(x509TrustManager, this));
        this.cleaner$delegate = lazy;
    }

    public /* synthetic */ CertificateRevocationBase(Set set, CertificateChainCleanerFactory certificateChainCleanerFactory, X509TrustManager x509TrustManager, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? SetsKt__SetsKt.emptySet() : set, (i2 & 2) != 0 ? null : certificateChainCleanerFactory, (i2 & 4) != 0 ? null : x509TrustManager);
    }

    private final CertificateChainCleaner getCleaner() {
        return (CertificateChainCleaner) this.cleaner$delegate.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0051, code lost:
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final RevocationResult hasRevokedCertificate(List<? extends X509Certificate> list) {
        boolean z;
        try {
            for (X509Certificate x509Certificate : list) {
                Set<CrlItem> set = this.crlSet;
                boolean z2 = true;
                if (!(set instanceof Collection) || !set.isEmpty()) {
                    for (CrlItem crlItem : set) {
                        if (Intrinsics.areEqual(crlItem.getIssuerDistinguishedName(), x509Certificate.getIssuerX500Principal()) && crlItem.getSerialNumbers().contains(x509Certificate.getSerialNumber())) {
                            z = true;
                            continue;
                        } else {
                            z = false;
                            continue;
                        }
                        if (z) {
                            break;
                        }
                    }
                }
                z2 = false;
                continue;
                if (z2) {
                    return new RevocationResult.Failure.CertificateRevoked(x509Certificate);
                }
            }
            return RevocationResult.Success.Trusted.INSTANCE;
        } catch (IOException e2) {
            return new RevocationResult.Failure.UnknownIoException(e2);
        }
    }

    @NotNull
    public final RevocationResult verifyCertificateRevocation(@NotNull String host, @NotNull List<? extends Certificate> certificates) {
        Intrinsics.checkNotNullParameter(host, "host");
        Intrinsics.checkNotNullParameter(certificates, "certificates");
        if (!certificates.isEmpty()) {
            CertificateChainCleaner cleaner = getCleaner();
            ArrayList arrayList = new ArrayList();
            for (Object obj : certificates) {
                if (obj instanceof X509Certificate) {
                    arrayList.add(obj);
                }
            }
            List<X509Certificate> clean = cleaner.clean(arrayList, host);
            if (!clean.isEmpty()) {
                return hasRevokedCertificate(clean);
            }
        }
        return RevocationResult.Failure.NoCertificates.INSTANCE;
    }
}
