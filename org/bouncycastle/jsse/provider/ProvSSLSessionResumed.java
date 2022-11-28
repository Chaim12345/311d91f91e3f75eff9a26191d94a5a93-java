package org.bouncycastle.jsse.provider;

import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.SessionParameters;
import org.bouncycastle.tls.TlsSession;
/* loaded from: classes3.dex */
class ProvSSLSessionResumed extends ProvSSLSessionHandshake {

    /* renamed from: l  reason: collision with root package name */
    protected final TlsSession f13969l;

    /* renamed from: m  reason: collision with root package name */
    protected final SessionParameters f13970m;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSessionResumed(ProvSSLSessionContext provSSLSessionContext, String str, int i2, SecurityParameters securityParameters, JsseSecurityParameters jsseSecurityParameters, TlsSession tlsSession, JsseSessionParameters jsseSessionParameters) {
        super(provSSLSessionContext, str, i2, securityParameters, jsseSecurityParameters);
        this.f13969l = tlsSession;
        this.f13970m = tlsSession.exportSessionParameters();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionHandshake, org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected int b() {
        return this.f13970m.getCipherSuite();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionHandshake, org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected byte[] d() {
        return this.f13969l.getSessionID();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionHandshake, org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected Certificate e() {
        return this.f13970m.getLocalCertificate();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionHandshake, org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected Certificate f() {
        return this.f13970m.getPeerCertificate();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionHandshake, org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected ProtocolVersion g() {
        return this.f13970m.getNegotiatedVersion();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionHandshake, org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected void h() {
        this.f13969l.invalidate();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase, javax.net.ssl.SSLSession
    public boolean isValid() {
        return super.isValid() && this.f13969l.isResumable();
    }
}
