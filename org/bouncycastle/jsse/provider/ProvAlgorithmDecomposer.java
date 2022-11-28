package org.bouncycastle.jsse.provider;

import java.util.Set;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes3.dex */
class ProvAlgorithmDecomposer extends JcaAlgorithmDecomposer {

    /* renamed from: b  reason: collision with root package name */
    static final ProvAlgorithmDecomposer f13913b = new ProvAlgorithmDecomposer(true);

    /* renamed from: c  reason: collision with root package name */
    static final ProvAlgorithmDecomposer f13914c = new ProvAlgorithmDecomposer(false);
    private final boolean enableTLSAlgorithms;

    private ProvAlgorithmDecomposer(boolean z) {
        this.enableTLSAlgorithms = z;
    }

    @Override // org.bouncycastle.jsse.provider.JcaAlgorithmDecomposer, org.bouncycastle.jsse.provider.AlgorithmDecomposer
    public Set<String> decompose(String str) {
        CipherSuiteInfo c2;
        return (!str.startsWith("TLS_") || (c2 = ProvSSLContextSpi.c(str)) == null || CipherSuite.isSCSV(c2.getCipherSuite())) ? super.decompose(str) : this.enableTLSAlgorithms ? c2.getDecompositionTLS() : c2.getDecompositionX509();
    }
}
