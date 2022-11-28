package org.bouncycastle.jsse.provider;

import java.util.List;
import java.util.Vector;
import org.bouncycastle.jsse.BCX509ExtendedKeyManager;
import org.bouncycastle.jsse.BCX509ExtendedTrustManager;
import org.bouncycastle.jsse.provider.NamedGroupInfo;
import org.bouncycastle.jsse.provider.SignatureSchemeInfo;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.crypto.impl.jcajce.JcaTlsCrypto;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ContextData {
    private final ProvSSLContextSpi context;
    private final JcaTlsCrypto crypto;
    private final NamedGroupInfo.PerContext namedGroups;
    private final SignatureSchemeInfo.PerContext signatureSchemes;
    private final BCX509ExtendedKeyManager x509KeyManager;
    private final BCX509ExtendedTrustManager x509TrustManager;
    private final ProvSSLSessionContext clientSessionContext = new ProvSSLSessionContext(this);
    private final ProvSSLSessionContext serverSessionContext = new ProvSSLSessionContext(this);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ContextData(ProvSSLContextSpi provSSLContextSpi, JcaTlsCrypto jcaTlsCrypto, BCX509ExtendedKeyManager bCX509ExtendedKeyManager, BCX509ExtendedTrustManager bCX509ExtendedTrustManager) {
        this.context = provSSLContextSpi;
        this.crypto = jcaTlsCrypto;
        this.x509KeyManager = bCX509ExtendedKeyManager;
        this.x509TrustManager = bCX509ExtendedTrustManager;
        NamedGroupInfo.PerContext b2 = NamedGroupInfo.b(provSSLContextSpi.p(), jcaTlsCrypto);
        this.namedGroups = b2;
        this.signatureSchemes = SignatureSchemeInfo.a(provSSLContextSpi.p(), jcaTlsCrypto, b2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List a(boolean z, ProvSSLParameters provSSLParameters, ProtocolVersion[] protocolVersionArr, NamedGroupInfo.PerConnection perConnection) {
        return SignatureSchemeInfo.b(this.signatureSchemes, z, provSSLParameters, protocolVersionArr, perConnection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSessionContext b() {
        return this.clientSessionContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLContextSpi c() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JcaTlsCrypto d() {
        return this.crypto;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NamedGroupInfo.PerConnection e(ProvSSLParameters provSSLParameters, ProtocolVersion[] protocolVersionArr) {
        return NamedGroupInfo.a(this.namedGroups, provSSLParameters, protocolVersionArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSessionContext f() {
        return this.serverSessionContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List g(Vector vector) {
        return SignatureSchemeInfo.n(this.signatureSchemes, vector);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCX509ExtendedKeyManager h() {
        return this.x509KeyManager;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BCX509ExtendedTrustManager i() {
        return this.x509TrustManager;
    }
}
