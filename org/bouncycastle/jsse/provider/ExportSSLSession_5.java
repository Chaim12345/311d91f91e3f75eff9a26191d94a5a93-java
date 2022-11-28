package org.bouncycastle.jsse.provider;

import java.security.Principal;
import java.security.cert.Certificate;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.security.cert.X509Certificate;
import org.bouncycastle.jsse.BCExtendedSSLSession;
/* loaded from: classes3.dex */
class ExportSSLSession_5 implements SSLSession, ExportSSLSession {

    /* renamed from: a  reason: collision with root package name */
    final BCExtendedSSLSession f13876a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ExportSSLSession_5(BCExtendedSSLSession bCExtendedSSLSession) {
        this.f13876a = bCExtendedSSLSession;
    }

    public boolean equals(Object obj) {
        return obj != null && obj.equals(this.f13876a);
    }

    @Override // javax.net.ssl.SSLSession
    public int getApplicationBufferSize() {
        return this.f13876a.getApplicationBufferSize();
    }

    @Override // javax.net.ssl.SSLSession
    public String getCipherSuite() {
        return this.f13876a.getCipherSuite();
    }

    @Override // javax.net.ssl.SSLSession
    public long getCreationTime() {
        return this.f13876a.getCreationTime();
    }

    @Override // javax.net.ssl.SSLSession
    public byte[] getId() {
        return this.f13876a.getId();
    }

    @Override // javax.net.ssl.SSLSession
    public long getLastAccessedTime() {
        return this.f13876a.getLastAccessedTime();
    }

    @Override // javax.net.ssl.SSLSession
    public Certificate[] getLocalCertificates() {
        return this.f13876a.getLocalCertificates();
    }

    @Override // javax.net.ssl.SSLSession
    public Principal getLocalPrincipal() {
        return this.f13876a.getLocalPrincipal();
    }

    @Override // javax.net.ssl.SSLSession
    public int getPacketBufferSize() {
        return this.f13876a.getPacketBufferSize();
    }

    @Override // javax.net.ssl.SSLSession
    public X509Certificate[] getPeerCertificateChain() {
        return this.f13876a.getPeerCertificateChain();
    }

    @Override // javax.net.ssl.SSLSession
    public Certificate[] getPeerCertificates() {
        return this.f13876a.getPeerCertificates();
    }

    @Override // javax.net.ssl.SSLSession
    public String getPeerHost() {
        return this.f13876a.getPeerHost();
    }

    @Override // javax.net.ssl.SSLSession
    public int getPeerPort() {
        return this.f13876a.getPeerPort();
    }

    @Override // javax.net.ssl.SSLSession
    public Principal getPeerPrincipal() {
        return this.f13876a.getPeerPrincipal();
    }

    @Override // javax.net.ssl.SSLSession
    public String getProtocol() {
        return this.f13876a.getProtocol();
    }

    @Override // javax.net.ssl.SSLSession
    public SSLSessionContext getSessionContext() {
        return this.f13876a.getSessionContext();
    }

    @Override // javax.net.ssl.SSLSession
    public Object getValue(String str) {
        return this.f13876a.getValue(str);
    }

    @Override // javax.net.ssl.SSLSession
    public String[] getValueNames() {
        return this.f13876a.getValueNames();
    }

    public int hashCode() {
        return this.f13876a.hashCode();
    }

    @Override // javax.net.ssl.SSLSession
    public void invalidate() {
        this.f13876a.invalidate();
    }

    @Override // javax.net.ssl.SSLSession
    public boolean isValid() {
        return this.f13876a.isValid();
    }

    @Override // javax.net.ssl.SSLSession
    public void putValue(String str, Object obj) {
        this.f13876a.putValue(str, obj);
    }

    @Override // javax.net.ssl.SSLSession
    public void removeValue(String str) {
        this.f13876a.removeValue(str);
    }

    public String toString() {
        return this.f13876a.toString();
    }

    @Override // org.bouncycastle.jsse.provider.ExportSSLSession
    public BCExtendedSSLSession unwrap() {
        return this.f13876a;
    }
}
