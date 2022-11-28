package org.bouncycastle.jsse.provider;

import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionContext;
import javax.security.cert.X509Certificate;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSNIServerName;
/* loaded from: classes3.dex */
final class ImportSSLSession_5 extends BCExtendedSSLSession implements ImportSSLSession {

    /* renamed from: a  reason: collision with root package name */
    final SSLSession f13880a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImportSSLSession_5(SSLSession sSLSession) {
        this.f13880a = sSLSession;
    }

    public boolean equals(Object obj) {
        return obj != null && obj.equals(this.f13880a);
    }

    @Override // javax.net.ssl.SSLSession
    public int getApplicationBufferSize() {
        return this.f13880a.getApplicationBufferSize();
    }

    @Override // javax.net.ssl.SSLSession
    public String getCipherSuite() {
        return this.f13880a.getCipherSuite();
    }

    @Override // javax.net.ssl.SSLSession
    public long getCreationTime() {
        return this.f13880a.getCreationTime();
    }

    @Override // javax.net.ssl.SSLSession
    public byte[] getId() {
        return this.f13880a.getId();
    }

    @Override // javax.net.ssl.SSLSession
    public long getLastAccessedTime() {
        return this.f13880a.getLastAccessedTime();
    }

    @Override // javax.net.ssl.SSLSession
    public Certificate[] getLocalCertificates() {
        return this.f13880a.getLocalCertificates();
    }

    @Override // javax.net.ssl.SSLSession
    public Principal getLocalPrincipal() {
        return this.f13880a.getLocalPrincipal();
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public String[] getLocalSupportedSignatureAlgorithms() {
        return null;
    }

    @Override // javax.net.ssl.SSLSession
    public int getPacketBufferSize() {
        return this.f13880a.getPacketBufferSize();
    }

    @Override // javax.net.ssl.SSLSession
    public X509Certificate[] getPeerCertificateChain() {
        return this.f13880a.getPeerCertificateChain();
    }

    @Override // javax.net.ssl.SSLSession
    public Certificate[] getPeerCertificates() {
        return this.f13880a.getPeerCertificates();
    }

    @Override // javax.net.ssl.SSLSession
    public String getPeerHost() {
        return this.f13880a.getPeerHost();
    }

    @Override // javax.net.ssl.SSLSession
    public int getPeerPort() {
        return this.f13880a.getPeerPort();
    }

    @Override // javax.net.ssl.SSLSession
    public Principal getPeerPrincipal() {
        return this.f13880a.getPeerPrincipal();
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public String[] getPeerSupportedSignatureAlgorithms() {
        return null;
    }

    @Override // javax.net.ssl.SSLSession
    public String getProtocol() {
        return this.f13880a.getProtocol();
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public List<BCSNIServerName> getRequestedServerNames() {
        return Collections.emptyList();
    }

    @Override // javax.net.ssl.SSLSession
    public SSLSessionContext getSessionContext() {
        return this.f13880a.getSessionContext();
    }

    @Override // javax.net.ssl.SSLSession
    public Object getValue(String str) {
        return this.f13880a.getValue(str);
    }

    @Override // javax.net.ssl.SSLSession
    public String[] getValueNames() {
        return this.f13880a.getValueNames();
    }

    public int hashCode() {
        return this.f13880a.hashCode();
    }

    @Override // javax.net.ssl.SSLSession
    public void invalidate() {
        this.f13880a.invalidate();
    }

    @Override // javax.net.ssl.SSLSession
    public boolean isValid() {
        return this.f13880a.isValid();
    }

    @Override // javax.net.ssl.SSLSession
    public void putValue(String str, Object obj) {
        this.f13880a.putValue(str, obj);
    }

    @Override // javax.net.ssl.SSLSession
    public void removeValue(String str) {
        this.f13880a.removeValue(str);
    }

    public String toString() {
        return this.f13880a.toString();
    }

    @Override // org.bouncycastle.jsse.provider.ImportSSLSession
    public SSLSession unwrap() {
        return this.f13880a;
    }
}
