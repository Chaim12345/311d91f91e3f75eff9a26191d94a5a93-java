package com.appmattus.certificatetransparency.chaincleaner;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
final class CertificateChainCleaner$Companion$androidCertificateChainCleanerFactory$2 extends Lambda implements Function0<CertificateChainCleanerFactory> {
    public static final CertificateChainCleaner$Companion$androidCertificateChainCleanerFactory$2 INSTANCE = new CertificateChainCleaner$Companion$androidCertificateChainCleanerFactory$2();

    CertificateChainCleaner$Companion$androidCertificateChainCleanerFactory$2() {
        super(0);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @Nullable
    public final CertificateChainCleanerFactory invoke() {
        try {
            Object newInstance = Class.forName("com.appmattus.certificatetransparency.chaincleaner.AndroidCertificateChainCleaner$Factory").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            if (newInstance != null) {
                return (CertificateChainCleanerFactory) newInstance;
            }
            throw new NullPointerException("null cannot be cast to non-null type com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleanerFactory");
        } catch (Exception unused) {
            return null;
        }
    }
}
