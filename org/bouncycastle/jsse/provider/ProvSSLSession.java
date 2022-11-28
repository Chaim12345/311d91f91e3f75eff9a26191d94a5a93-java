package org.bouncycastle.jsse.provider;

import java.util.List;
import org.bouncycastle.jsse.BCSNIServerName;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SessionParameters;
import org.bouncycastle.tls.TlsSession;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvSSLSession extends ProvSSLSessionBase {

    /* renamed from: m  reason: collision with root package name */
    static final ProvSSLSession f13946m = new ProvSSLSession(null, null, -1, null, new JsseSessionParameters(null, null));

    /* renamed from: j  reason: collision with root package name */
    protected final TlsSession f13947j;

    /* renamed from: k  reason: collision with root package name */
    protected final SessionParameters f13948k;

    /* renamed from: l  reason: collision with root package name */
    protected final JsseSessionParameters f13949l;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSession(ProvSSLSessionContext provSSLSessionContext, String str, int i2, TlsSession tlsSession, JsseSessionParameters jsseSessionParameters) {
        super(provSSLSessionContext, str, i2);
        this.f13947j = tlsSession;
        this.f13948k = tlsSession == null ? null : tlsSession.exportSessionParameters();
        this.f13949l = jsseSessionParameters;
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected int b() {
        SessionParameters sessionParameters = this.f13948k;
        if (sessionParameters == null) {
            return 0;
        }
        return sessionParameters.getCipherSuite();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected byte[] d() {
        TlsSession tlsSession = this.f13947j;
        if (tlsSession == null) {
            return null;
        }
        return tlsSession.getSessionID();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected Certificate e() {
        SessionParameters sessionParameters = this.f13948k;
        if (sessionParameters == null) {
            return null;
        }
        return sessionParameters.getLocalCertificate();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected Certificate f() {
        SessionParameters sessionParameters = this.f13948k;
        if (sessionParameters == null) {
            return null;
        }
        return sessionParameters.getPeerCertificate();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected ProtocolVersion g() {
        SessionParameters sessionParameters = this.f13948k;
        if (sessionParameters == null) {
            return null;
        }
        return sessionParameters.getNegotiatedVersion();
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public String[] getLocalSupportedSignatureAlgorithms() {
        return null;
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public String[] getPeerSupportedSignatureAlgorithms() {
        return null;
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public List<BCSNIServerName> getRequestedServerNames() {
        throw new UnsupportedOperationException();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected void h() {
        TlsSession tlsSession = this.f13947j;
        if (tlsSession != null) {
            tlsSession.invalidate();
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase, javax.net.ssl.SSLSession
    public boolean isValid() {
        TlsSession tlsSession;
        return super.isValid() && (tlsSession = this.f13947j) != null && tlsSession.isResumable();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsseSessionParameters l() {
        return this.f13949l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TlsSession m() {
        return this.f13947j;
    }
}
