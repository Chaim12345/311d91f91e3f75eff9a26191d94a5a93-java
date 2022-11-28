package org.bouncycastle.jsse.provider;

import org.bouncycastle.jsse.BCSSLConnection;
import org.bouncycastle.tls.TlsContext;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvSSLConnection implements BCSSLConnection {

    /* renamed from: a  reason: collision with root package name */
    protected final TlsContext f13918a;

    /* renamed from: b  reason: collision with root package name */
    protected final ProvSSLSession f13919b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLConnection(TlsContext tlsContext, ProvSSLSession provSSLSession) {
        this.f13918a = tlsContext;
        this.f13919b = provSSLSession;
    }

    @Override // org.bouncycastle.jsse.BCSSLConnection
    public String getApplicationProtocol() {
        return JsseUtils.p(this.f13918a.getSecurityParametersConnection());
    }

    @Override // org.bouncycastle.jsse.BCSSLConnection
    public byte[] getChannelBinding(String str) {
        TlsContext tlsContext;
        int i2;
        if (str.equals("tls-server-end-point")) {
            tlsContext = this.f13918a;
            i2 = 0;
        } else if (!str.equals("tls-unique")) {
            throw new UnsupportedOperationException();
        } else {
            tlsContext = this.f13918a;
            i2 = 1;
        }
        return tlsContext.exportChannelBinding(i2);
    }

    @Override // org.bouncycastle.jsse.BCSSLConnection
    public ProvSSLSession getSession() {
        return this.f13919b;
    }
}
