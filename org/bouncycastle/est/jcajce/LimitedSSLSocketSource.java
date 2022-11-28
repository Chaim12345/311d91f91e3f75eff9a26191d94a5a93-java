package org.bouncycastle.est.jcajce;

import java.io.InputStream;
import java.io.OutputStream;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.est.LimitedSource;
import org.bouncycastle.est.Source;
import org.bouncycastle.est.TLSUniqueProvider;
/* loaded from: classes3.dex */
class LimitedSSLSocketSource implements Source<SSLSession>, TLSUniqueProvider, LimitedSource {

    /* renamed from: a  reason: collision with root package name */
    protected final SSLSocket f13574a;
    private final Long absoluteReadLimit;
    private final ChannelBindingProvider bindingProvider;

    public LimitedSSLSocketSource(SSLSocket sSLSocket, ChannelBindingProvider channelBindingProvider, Long l2) {
        this.f13574a = sSLSocket;
        this.bindingProvider = channelBindingProvider;
        this.absoluteReadLimit = l2;
    }

    @Override // org.bouncycastle.est.Source
    public void close() {
        this.f13574a.close();
    }

    @Override // org.bouncycastle.est.LimitedSource
    public Long getAbsoluteReadLimit() {
        return this.absoluteReadLimit;
    }

    @Override // org.bouncycastle.est.Source
    public InputStream getInputStream() {
        return this.f13574a.getInputStream();
    }

    @Override // org.bouncycastle.est.Source
    public OutputStream getOutputStream() {
        return this.f13574a.getOutputStream();
    }

    @Override // org.bouncycastle.est.Source
    public SSLSession getSession() {
        return this.f13574a.getSession();
    }

    @Override // org.bouncycastle.est.TLSUniqueProvider
    public byte[] getTLSUnique() {
        if (isTLSUniqueAvailable()) {
            return this.bindingProvider.getChannelBinding(this.f13574a, "tls-unique");
        }
        throw new IllegalStateException("No binding provider.");
    }

    @Override // org.bouncycastle.est.TLSUniqueProvider
    public boolean isTLSUniqueAvailable() {
        return this.bindingProvider.canAccessChannelBinding(this.f13574a);
    }
}
