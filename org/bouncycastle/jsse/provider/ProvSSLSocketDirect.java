package org.bouncycastle.jsse.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCApplicationProtocolSelector;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSSLConnection;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsProtocol;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvSSLSocketDirect extends ProvSSLSocketBase implements ProvTlsManager {
    private static final Logger LOG = Logger.getLogger(ProvSSLSocketDirect.class.getName());

    /* renamed from: e  reason: collision with root package name */
    protected final AppDataInput f13981e;

    /* renamed from: f  reason: collision with root package name */
    protected final AppDataOutput f13982f;

    /* renamed from: g  reason: collision with root package name */
    protected final ContextData f13983g;

    /* renamed from: h  reason: collision with root package name */
    protected final ProvSSLParameters f13984h;

    /* renamed from: i  reason: collision with root package name */
    protected String f13985i;

    /* renamed from: j  reason: collision with root package name */
    protected String f13986j;

    /* renamed from: k  reason: collision with root package name */
    protected boolean f13987k;

    /* renamed from: l  reason: collision with root package name */
    protected boolean f13988l;

    /* renamed from: m  reason: collision with root package name */
    protected TlsProtocol f13989m;

    /* renamed from: n  reason: collision with root package name */
    protected ProvSSLConnection f13990n;

    /* renamed from: o  reason: collision with root package name */
    protected ProvSSLSessionHandshake f13991o;

    /* loaded from: classes3.dex */
    class AppDataInput extends InputStream {
        AppDataInput() {
        }

        @Override // java.io.InputStream
        public int available() {
            int applicationDataAvailable;
            synchronized (ProvSSLSocketDirect.this) {
                TlsProtocol tlsProtocol = ProvSSLSocketDirect.this.f13989m;
                applicationDataAvailable = tlsProtocol == null ? 0 : tlsProtocol.applicationDataAvailable();
            }
            return applicationDataAvailable;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            ProvSSLSocketDirect.this.close();
        }

        @Override // java.io.InputStream
        public int read() {
            byte[] bArr = new byte[1];
            if (read(bArr, 0, 1) <= 0) {
                return -1;
            }
            return bArr[0] & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            if (i3 < 1) {
                return 0;
            }
            ProvSSLSocketDirect.this.i(true);
            return ProvSSLSocketDirect.this.f13989m.readApplicationData(bArr, i2, i3);
        }
    }

    /* loaded from: classes3.dex */
    class AppDataOutput extends OutputStream {
        AppDataOutput() {
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            ProvSSLSocketDirect.this.close();
        }

        @Override // java.io.OutputStream
        public void write(int i2) {
            write(new byte[]{(byte) i2}, 0, 1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            if (i3 > 0) {
                ProvSSLSocketDirect.this.i(true);
                ProvSSLSocketDirect.this.f13989m.writeApplicationData(bArr, i2, i3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect(ContextData contextData) {
        this.f13981e = new AppDataInput();
        this.f13982f = new AppDataOutput();
        this.f13985i = null;
        this.f13986j = null;
        this.f13987k = true;
        this.f13988l = true;
        this.f13989m = null;
        this.f13990n = null;
        this.f13991o = null;
        this.f13983g = contextData;
        this.f13984h = contextData.c().h(this.f13988l);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect(ContextData contextData, String str, int i2) {
        this.f13981e = new AppDataInput();
        this.f13982f = new AppDataOutput();
        this.f13985i = null;
        this.f13986j = null;
        this.f13987k = true;
        this.f13988l = true;
        this.f13989m = null;
        this.f13990n = null;
        this.f13991o = null;
        this.f13983g = contextData;
        this.f13984h = contextData.c().h(this.f13988l);
        this.f13985i = str;
        e(str, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect(ContextData contextData, String str, int i2, InetAddress inetAddress, int i3) {
        this.f13981e = new AppDataInput();
        this.f13982f = new AppDataOutput();
        this.f13985i = null;
        this.f13986j = null;
        this.f13987k = true;
        this.f13988l = true;
        this.f13989m = null;
        this.f13990n = null;
        this.f13991o = null;
        this.f13983g = contextData;
        this.f13984h = contextData.c().h(this.f13988l);
        this.f13985i = str;
        d(inetAddress, i3);
        e(str, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect(ContextData contextData, InetAddress inetAddress, int i2) {
        this.f13981e = new AppDataInput();
        this.f13982f = new AppDataOutput();
        this.f13985i = null;
        this.f13986j = null;
        this.f13987k = true;
        this.f13988l = true;
        this.f13989m = null;
        this.f13990n = null;
        this.f13991o = null;
        this.f13983g = contextData;
        this.f13984h = contextData.c().h(this.f13988l);
        f(inetAddress, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketDirect(ContextData contextData, InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) {
        this.f13981e = new AppDataInput();
        this.f13982f = new AppDataOutput();
        this.f13985i = null;
        this.f13986j = null;
        this.f13987k = true;
        this.f13988l = true;
        this.f13989m = null;
        this.f13990n = null;
        this.f13991o = null;
        this.f13983g = contextData;
        this.f13984h = contextData.c().h(this.f13988l);
        d(inetAddress2, i3);
        f(inetAddress, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSocketDirect(ContextData contextData, boolean z, boolean z2, ProvSSLParameters provSSLParameters) {
        this.f13981e = new AppDataInput();
        this.f13982f = new AppDataOutput();
        this.f13985i = null;
        this.f13986j = null;
        this.f13987k = true;
        this.f13988l = true;
        this.f13989m = null;
        this.f13990n = null;
        this.f13991o = null;
        this.f13983g = contextData;
        this.f13987k = z;
        this.f13988l = z2;
        this.f13984h = provSSLParameters;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        try {
            this.f13983g.i().checkClientTrusted((X509Certificate[]) x509CertificateArr.clone(), str, this);
        } catch (CertificateException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        try {
            this.f13983g.i().checkServerTrusted((X509Certificate[]) x509CertificateArr.clone(), str, this);
        } catch (CertificateException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public BCX509Key chooseClientKey(String[] strArr, Principal[] principalArr) {
        return getContextData().h().chooseClientKeyBC(strArr, (Principal[]) JsseUtils.d(principalArr), this);
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public BCX509Key chooseServerKey(String[] strArr, Principal[] principalArr) {
        return getContextData().h().chooseServerKeyBC(strArr, (Principal[]) JsseUtils.d(principalArr), this);
    }

    @Override // java.net.Socket, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        TlsProtocol tlsProtocol = this.f13989m;
        if (tlsProtocol == null) {
            a();
        } else {
            tlsProtocol.close();
        }
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress, int i2) {
        if (!(socketAddress instanceof InetSocketAddress)) {
            throw new SocketException("Only InetSocketAddress is supported.");
        }
        super.connect(socketAddress, i2);
        j();
    }

    protected void finalize() {
        try {
            try {
                try {
                    close();
                } catch (IOException unused) {
                    super.close();
                }
            } catch (IOException unused2) {
            }
        } finally {
            super.finalize();
        }
    }

    @Override // javax.net.ssl.SSLSocket, org.bouncycastle.jsse.BCSSLSocket
    public synchronized String getApplicationProtocol() {
        ProvSSLConnection provSSLConnection;
        provSSLConnection = this.f13990n;
        return provSSLConnection == null ? null : provSSLConnection.getApplicationProtocol();
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCApplicationProtocolSelector<SSLSocket> getBCHandshakeApplicationProtocolSelector() {
        return this.f13984h.getSocketAPSelector();
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCExtendedSSLSession getBCHandshakeSession() {
        return this.f13991o;
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public BCExtendedSSLSession getBCSession() {
        return h();
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCSSLConnection getConnection() {
        try {
            i(false);
        } catch (IOException e2) {
            LOG.log(Level.FINE, "Failed to establish connection", (Throwable) e2);
        }
        return this.f13990n;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public ContextData getContextData() {
        return this.f13983g;
    }

    @Override // javax.net.ssl.SSLSocket, org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized boolean getEnableSessionCreation() {
        return this.f13987k;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getEnabledCipherSuites() {
        return this.f13984h.getCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getEnabledProtocols() {
        return this.f13984h.getProtocols();
    }

    @Override // javax.net.ssl.SSLSocket, org.bouncycastle.jsse.BCSSLSocket
    public synchronized String getHandshakeApplicationProtocol() {
        ProvSSLSessionHandshake provSSLSessionHandshake;
        provSSLSessionHandshake = this.f13991o;
        return provSSLSessionHandshake == null ? null : provSSLSessionHandshake.l();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized SSLSession getHandshakeSession() {
        ProvSSLSessionHandshake provSSLSessionHandshake;
        provSSLSessionHandshake = this.f13991o;
        return provSSLSessionHandshake == null ? null : provSSLSessionHandshake.c();
    }

    @Override // java.net.Socket
    public InputStream getInputStream() {
        return this.f13981e;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized boolean getNeedClientAuth() {
        return this.f13984h.getNeedClientAuth();
    }

    @Override // java.net.Socket
    public OutputStream getOutputStream() {
        return this.f13982f;
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCSSLParameters getParameters() {
        return SSLParametersUtil.a(this.f13984h);
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized String getPeerHost() {
        return this.f13985i;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized String getPeerHostSNI() {
        return this.f13986j;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public int getPeerPort() {
        return getPort();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized SSLParameters getSSLParameters() {
        return SSLParametersUtil.b(this.f13984h);
    }

    @Override // javax.net.ssl.SSLSocket
    public SSLSession getSession() {
        return h().c();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getSupportedCipherSuites() {
        return this.f13983g.c().l();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getSupportedProtocols() {
        return this.f13983g.c().n();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized boolean getUseClientMode() {
        return this.f13988l;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized boolean getWantClientAuth() {
        return this.f13984h.getWantClientAuth();
    }

    synchronized ProvSSLSession h() {
        ProvSSLConnection provSSLConnection;
        getConnection();
        provSSLConnection = this.f13990n;
        return provSSLConnection == null ? ProvSSLSession.f13946m : provSSLConnection.getSession();
    }

    synchronized void i(boolean z) {
        TlsProtocol tlsProtocol = this.f13989m;
        if (tlsProtocol == null || tlsProtocol.isHandshaking()) {
            k(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void j() {
        if (JsseUtils.Q(this.f13985i)) {
            this.f13986j = this.f13985i;
            return;
        }
        InetAddress inetAddress = getInetAddress();
        if (inetAddress == null) {
            return;
        }
        boolean z = this.f13988l;
        if (!z || !ProvSSLSocketBase.f13971c) {
            this.f13985i = (z && ProvSSLSocketBase.f13972d) ? inetAddress.getHostName() : inetAddress.getHostAddress();
            this.f13986j = null;
            return;
        }
        String hostName = inetAddress.getHostName();
        this.f13985i = hostName;
        this.f13986j = hostName;
    }

    protected void k(boolean z) {
        TlsProtocol tlsProtocol = this.f13989m;
        if (tlsProtocol != null) {
            if (!tlsProtocol.isHandshaking()) {
                throw new UnsupportedOperationException("Renegotiation not supported");
            }
            this.f13989m.setResumableHandshake(z);
            this.f13989m.resumeHandshake();
            return;
        }
        InputStream inputStream = super.getInputStream();
        OutputStream outputStream = super.getOutputStream();
        if (this.f13988l) {
            ProvTlsClientProtocol provTlsClientProtocol = new ProvTlsClientProtocol(inputStream, outputStream, this.f13973a);
            provTlsClientProtocol.setResumableHandshake(z);
            this.f13989m = provTlsClientProtocol;
            provTlsClientProtocol.connect(new ProvTlsClient(this, this.f13984h));
            return;
        }
        ProvTlsServerProtocol provTlsServerProtocol = new ProvTlsServerProtocol(inputStream, outputStream, this.f13973a);
        provTlsServerProtocol.setResumableHandshake(z);
        this.f13989m = provTlsServerProtocol;
        provTlsServerProtocol.accept(new ProvTlsServer(this, this.f13984h));
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized void notifyHandshakeComplete(ProvSSLConnection provSSLConnection) {
        ProvSSLSessionHandshake provSSLSessionHandshake = this.f13991o;
        if (provSSLSessionHandshake != null) {
            if (!provSSLSessionHandshake.isValid()) {
                provSSLConnection.getSession().invalidate();
            }
            this.f13991o.m().a();
        }
        this.f13991o = null;
        this.f13990n = provSSLConnection;
        g(provSSLConnection.getSession().f13957h);
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized void notifyHandshakeSession(ProvSSLSessionContext provSSLSessionContext, SecurityParameters securityParameters, JsseSecurityParameters jsseSecurityParameters, ProvSSLSession provSSLSession) {
        String peerHost = getPeerHost();
        int peerPort = getPeerPort();
        if (provSSLSession != null) {
            this.f13991o = new ProvSSLSessionResumed(provSSLSessionContext, peerHost, peerPort, securityParameters, jsseSecurityParameters, provSSLSession.m(), provSSLSession.l());
        } else {
            this.f13991o = new ProvSSLSessionHandshake(provSSLSessionContext, peerHost, peerPort, securityParameters, jsseSecurityParameters);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized String selectApplicationProtocol(List<String> list) {
        return this.f13984h.getSocketAPSelector().select(this, list);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized void setBCHandshakeApplicationProtocolSelector(BCApplicationProtocolSelector<SSLSocket> bCApplicationProtocolSelector) {
        this.f13984h.setSocketAPSelector(bCApplicationProtocolSelector);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized void setBCSessionToResume(BCExtendedSSLSession bCExtendedSSLSession) {
        try {
            if (bCExtendedSSLSession == null) {
                throw new NullPointerException("'session' cannot be null");
            }
            if (!(bCExtendedSSLSession instanceof ProvSSLSession)) {
                throw new IllegalArgumentException("Session-to-resume must be a session returned from 'getBCSession'");
            }
            if (this.f13989m != null) {
                throw new IllegalArgumentException("Session-to-resume cannot be set after the handshake has begun");
            }
            this.f13984h.setSessionToResume((ProvSSLSession) bCExtendedSSLSession);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setEnableSessionCreation(boolean z) {
        this.f13987k = z;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setEnabledCipherSuites(String[] strArr) {
        this.f13984h.setCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setEnabledProtocols(String[] strArr) {
        this.f13984h.setProtocols(strArr);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized void setHost(String str) {
        this.f13985i = str;
        this.f13986j = str;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setNeedClientAuth(boolean z) {
        this.f13984h.setNeedClientAuth(z);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized void setParameters(BCSSLParameters bCSSLParameters) {
        SSLParametersUtil.d(this.f13984h, bCSSLParameters);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setSSLParameters(SSLParameters sSLParameters) {
        SSLParametersUtil.e(this.f13984h, sSLParameters);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setUseClientMode(boolean z) {
        if (this.f13989m != null) {
            throw new IllegalArgumentException("Mode cannot be changed after the initial handshake has begun");
        }
        if (this.f13988l != z) {
            this.f13983g.c().t(this.f13984h, z);
            this.f13988l = z;
        }
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setWantClientAuth(boolean z) {
        this.f13984h.setWantClientAuth(z);
    }

    @Override // java.net.Socket
    public void shutdownInput() {
        throw new UnsupportedOperationException("shutdownInput() not supported in TLS");
    }

    @Override // java.net.Socket
    public void shutdownOutput() {
        throw new UnsupportedOperationException("shutdownOutput() not supported in TLS");
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void startHandshake() {
        k(true);
    }
}
