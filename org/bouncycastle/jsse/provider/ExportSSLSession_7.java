package org.bouncycastle.jsse.provider;

import java.security.Principal;
import java.security.cert.Certificate;
import javax.net.ssl.ExtendedSSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.security.cert.X509Certificate;
import org.bouncycastle.jsse.BCExtendedSSLSession;
/* loaded from: classes3.dex */
class ExportSSLSession_7 extends ExtendedSSLSession implements ExportSSLSession {

    /* renamed from: a  reason: collision with root package name */
    final BCExtendedSSLSession f13877a;

    public boolean equals(Object obj) {
        return obj != null && obj.equals(this.f13877a);
    }

    @Override // javax.net.ssl.SSLSession
    public int getApplicationBufferSize() {
        return this.f13877a.getApplicationBufferSize();
    }

    @Override // javax.net.ssl.SSLSession
    public String getCipherSuite() {
        return this.f13877a.getCipherSuite();
    }

    @Override // javax.net.ssl.SSLSession
    public long getCreationTime() {
        return this.f13877a.getCreationTime();
    }

    @Override // javax.net.ssl.SSLSession
    public byte[] getId() {
        return this.f13877a.getId();
    }

    @Override // javax.net.ssl.SSLSession
    public long getLastAccessedTime() {
        return this.f13877a.getLastAccessedTime();
    }

    @Override // javax.net.ssl.SSLSession
    public Certificate[] getLocalCertificates() {
        return this.f13877a.getLocalCertificates();
    }

    @Override // javax.net.ssl.SSLSession
    public Principal getLocalPrincipal() {
        return this.f13877a.getLocalPrincipal();
    }

    @Override // javax.net.ssl.ExtendedSSLSession
    public String[] getLocalSupportedSignatureAlgorithms() {
        return this.f13877a.getLocalSupportedSignatureAlgorithms();
    }

    @Override // javax.net.ssl.SSLSession
    public int getPacketBufferSize() {
        return this.f13877a.getPacketBufferSize();
    }

    @Override // javax.net.ssl.SSLSession
    public X509Certificate[] getPeerCertificateChain() {
        return this.f13877a.getPeerCertificateChain();
    }

    @Override // javax.net.ssl.SSLSession
    public Certificate[] getPeerCertificates() {
        return this.f13877a.getPeerCertificates();
    }

    @Override // javax.net.ssl.SSLSession
    public String getPeerHost() {
        return this.f13877a.getPeerHost();
    }

    @Override // javax.net.ssl.SSLSession
    public int getPeerPort() {
        return this.f13877a.getPeerPort();
    }

    @Override // javax.net.ssl.SSLSession
    public Principal getPeerPrincipal() {
        return this.f13877a.getPeerPrincipal();
    }

    @Override // javax.net.ssl.ExtendedSSLSession
    public String[] getPeerSupportedSignatureAlgorithms() {
        return this.f13877a.getPeerSupportedSignatureAlgorithms();
    }

    @Override // javax.net.ssl.SSLSession
    public String getProtocol() {
        return this.f13877a.getProtocol();
    }

    @Override // javax.net.ssl.SSLSession
    public SSLSessionContext getSessionContext() {
        return this.f13877a.getSessionContext();
    }

    @Override // javax.net.ssl.SSLSession
    public Object getValue(String str) {
        return this.f13877a.getValue(str);
    }

    @Override // javax.net.ssl.SSLSession
    public String[] getValueNames() {
        return this.f13877a.getValueNames();
    }

    public int hashCode() {
        return this.f13877a.hashCode();
    }

    @Override // javax.net.ssl.SSLSession
    public void invalidate() {
        this.f13877a.invalidate();
    }

    @Override // javax.net.ssl.SSLSession
    public boolean isValid() {
        return this.f13877a.isValid();
    }

    @Override // javax.net.ssl.SSLSession
    public void putValue(String str, Object obj) {
        this.f13877a.putValue(str, obj);
    }

    @Override // javax.net.ssl.SSLSession
    public void removeValue(String str) {
        this.f13877a.removeValue(str);
    }

    public String toString() {
        return this.f13877a.toString();
    }

    @Override // org.bouncycastle.jsse.provider.ExportSSLSession
    public BCExtendedSSLSession unwrap() {
        return this.f13877a;
    }
}
