package org.bouncycastle.jsse.provider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Objects;
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
public class ProvSSLSocketWrap extends ProvSSLSocketBase implements ProvTlsManager {
    private static final Logger LOG = Logger.getLogger(ProvSSLSocketWrap.class.getName());

    /* renamed from: e  reason: collision with root package name */
    protected final AppDataInput f13995e;

    /* renamed from: f  reason: collision with root package name */
    protected final AppDataOutput f13996f;

    /* renamed from: g  reason: collision with root package name */
    protected final ContextData f13997g;

    /* renamed from: h  reason: collision with root package name */
    protected final Socket f13998h;

    /* renamed from: i  reason: collision with root package name */
    protected final InputStream f13999i;

    /* renamed from: j  reason: collision with root package name */
    protected final boolean f14000j;

    /* renamed from: k  reason: collision with root package name */
    protected final ProvSSLParameters f14001k;

    /* renamed from: l  reason: collision with root package name */
    protected String f14002l;

    /* renamed from: m  reason: collision with root package name */
    protected String f14003m;

    /* renamed from: n  reason: collision with root package name */
    protected boolean f14004n;

    /* renamed from: o  reason: collision with root package name */
    protected boolean f14005o;

    /* renamed from: p  reason: collision with root package name */
    protected TlsProtocol f14006p;

    /* renamed from: q  reason: collision with root package name */
    protected ProvSSLConnection f14007q;

    /* renamed from: r  reason: collision with root package name */
    protected ProvSSLSessionHandshake f14008r;

    /* loaded from: classes3.dex */
    class AppDataInput extends InputStream {
        AppDataInput() {
        }

        @Override // java.io.InputStream
        public int available() {
            int applicationDataAvailable;
            synchronized (ProvSSLSocketWrap.this) {
                TlsProtocol tlsProtocol = ProvSSLSocketWrap.this.f14006p;
                applicationDataAvailable = tlsProtocol == null ? 0 : tlsProtocol.applicationDataAvailable();
            }
            return applicationDataAvailable;
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            ProvSSLSocketWrap.this.close();
        }

        @Override // java.io.InputStream
        public int read() {
            ProvSSLSocketWrap.this.i(true);
            byte[] bArr = new byte[1];
            if (ProvSSLSocketWrap.this.f14006p.readApplicationData(bArr, 0, 1) < 0) {
                return -1;
            }
            return bArr[0] & 255;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            if (i3 < 1) {
                return 0;
            }
            ProvSSLSocketWrap.this.i(true);
            return ProvSSLSocketWrap.this.f14006p.readApplicationData(bArr, i2, i3);
        }
    }

    /* loaded from: classes3.dex */
    class AppDataOutput extends OutputStream {
        AppDataOutput() {
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            ProvSSLSocketWrap.this.close();
        }

        @Override // java.io.OutputStream
        public void write(int i2) {
            write(new byte[]{(byte) i2}, 0, 1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            if (i3 > 0) {
                ProvSSLSocketWrap.this.i(true);
                ProvSSLSocketWrap.this.f14006p.writeApplicationData(bArr, i2, i3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketWrap(ContextData contextData, Socket socket, InputStream inputStream, boolean z) {
        this.f13995e = new AppDataInput();
        this.f13996f = new AppDataOutput();
        this.f14002l = null;
        this.f14003m = null;
        this.f14004n = true;
        this.f14006p = null;
        this.f14007q = null;
        this.f14008r = null;
        this.f13997g = contextData;
        this.f13998h = checkSocket(socket);
        this.f13999i = inputStream;
        this.f14000j = z;
        this.f14005o = false;
        this.f14001k = contextData.c().h(this.f14005o);
        j();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLSocketWrap(ContextData contextData, Socket socket, String str, int i2, boolean z) {
        this.f13995e = new AppDataInput();
        this.f13996f = new AppDataOutput();
        this.f14002l = null;
        this.f14003m = null;
        this.f14004n = true;
        this.f14006p = null;
        this.f14007q = null;
        this.f14008r = null;
        this.f13997g = contextData;
        this.f13998h = checkSocket(socket);
        this.f13999i = null;
        this.f14002l = str;
        this.f14000j = z;
        this.f14005o = true;
        this.f14001k = contextData.c().h(this.f14005o);
        j();
    }

    private static Socket checkSocket(Socket socket) {
        Objects.requireNonNull(socket, "'s' cannot be null");
        if (socket.isConnected()) {
            return socket;
        }
        throw new SocketException("'s' is not a connected socket");
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSocketBase
    protected void a() {
        if (this.f14000j) {
            this.f13998h.close();
        }
    }

    @Override // java.net.Socket
    public void bind(SocketAddress socketAddress) {
        throw new SocketException("Wrapped socket should already be bound");
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        try {
            this.f13997g.i().checkClientTrusted((X509Certificate[]) x509CertificateArr.clone(), str, this);
        } catch (CertificateException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        try {
            this.f13997g.i().checkServerTrusted((X509Certificate[]) x509CertificateArr.clone(), str, this);
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
        TlsProtocol tlsProtocol = this.f14006p;
        if (tlsProtocol == null) {
            a();
        } else {
            tlsProtocol.close();
        }
    }

    @Override // java.net.Socket
    public void connect(SocketAddress socketAddress, int i2) {
        throw new SocketException("Wrapped socket should already be connected");
    }

    protected void finalize() {
        try {
            close();
        } catch (IOException unused) {
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
        super.finalize();
    }

    @Override // javax.net.ssl.SSLSocket, org.bouncycastle.jsse.BCSSLSocket
    public synchronized String getApplicationProtocol() {
        ProvSSLConnection provSSLConnection;
        provSSLConnection = this.f14007q;
        return provSSLConnection == null ? null : provSSLConnection.getApplicationProtocol();
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCApplicationProtocolSelector<SSLSocket> getBCHandshakeApplicationProtocolSelector() {
        return this.f14001k.getSocketAPSelector();
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCExtendedSSLSession getBCHandshakeSession() {
        return this.f14008r;
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public BCExtendedSSLSession getBCSession() {
        return h();
    }

    @Override // java.net.Socket
    public SocketChannel getChannel() {
        return this.f13998h.getChannel();
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCSSLConnection getConnection() {
        try {
            i(false);
        } catch (Exception e2) {
            LOG.log(Level.FINE, "Failed to establish connection", (Throwable) e2);
        }
        return this.f14007q;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public ContextData getContextData() {
        return this.f13997g;
    }

    @Override // javax.net.ssl.SSLSocket, org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized boolean getEnableSessionCreation() {
        return this.f14004n;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getEnabledCipherSuites() {
        return this.f14001k.getCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getEnabledProtocols() {
        return this.f14001k.getProtocols();
    }

    @Override // javax.net.ssl.SSLSocket, org.bouncycastle.jsse.BCSSLSocket
    public synchronized String getHandshakeApplicationProtocol() {
        ProvSSLSessionHandshake provSSLSessionHandshake;
        provSSLSessionHandshake = this.f14008r;
        return provSSLSessionHandshake == null ? null : provSSLSessionHandshake.l();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized SSLSession getHandshakeSession() {
        ProvSSLSessionHandshake provSSLSessionHandshake;
        provSSLSessionHandshake = this.f14008r;
        return provSSLSessionHandshake == null ? null : provSSLSessionHandshake.c();
    }

    @Override // java.net.Socket
    public InetAddress getInetAddress() {
        return this.f13998h.getInetAddress();
    }

    @Override // java.net.Socket
    public InputStream getInputStream() {
        return this.f13995e;
    }

    @Override // java.net.Socket
    public boolean getKeepAlive() {
        return this.f13998h.getKeepAlive();
    }

    @Override // java.net.Socket
    public InetAddress getLocalAddress() {
        return this.f13998h.getLocalAddress();
    }

    @Override // java.net.Socket
    public int getLocalPort() {
        return this.f13998h.getLocalPort();
    }

    @Override // java.net.Socket
    public SocketAddress getLocalSocketAddress() {
        return this.f13998h.getLocalSocketAddress();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized boolean getNeedClientAuth() {
        return this.f14001k.getNeedClientAuth();
    }

    @Override // java.net.Socket
    public OutputStream getOutputStream() {
        return this.f13996f;
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized BCSSLParameters getParameters() {
        return SSLParametersUtil.a(this.f14001k);
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized String getPeerHost() {
        return this.f14002l;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized String getPeerHostSNI() {
        return this.f14003m;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public int getPeerPort() {
        return getPort();
    }

    @Override // java.net.Socket
    public int getPort() {
        return this.f13998h.getPort();
    }

    @Override // java.net.Socket
    public int getReceiveBufferSize() {
        return this.f13998h.getReceiveBufferSize();
    }

    @Override // java.net.Socket
    public SocketAddress getRemoteSocketAddress() {
        return this.f13998h.getRemoteSocketAddress();
    }

    @Override // java.net.Socket
    public boolean getReuseAddress() {
        return this.f13998h.getReuseAddress();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized SSLParameters getSSLParameters() {
        return SSLParametersUtil.b(this.f14001k);
    }

    @Override // java.net.Socket
    public int getSendBufferSize() {
        return this.f13998h.getSendBufferSize();
    }

    @Override // javax.net.ssl.SSLSocket
    public SSLSession getSession() {
        return h().c();
    }

    @Override // java.net.Socket
    public int getSoLinger() {
        return this.f13998h.getSoLinger();
    }

    @Override // java.net.Socket
    public int getSoTimeout() {
        return this.f13998h.getSoTimeout();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getSupportedCipherSuites() {
        return this.f13997g.c().l();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized String[] getSupportedProtocols() {
        return this.f13997g.c().n();
    }

    @Override // java.net.Socket
    public boolean getTcpNoDelay() {
        return this.f13998h.getTcpNoDelay();
    }

    @Override // java.net.Socket
    public int getTrafficClass() {
        return this.f13998h.getTrafficClass();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized boolean getUseClientMode() {
        return this.f14005o;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized boolean getWantClientAuth() {
        return this.f14001k.getWantClientAuth();
    }

    synchronized ProvSSLSession h() {
        ProvSSLConnection provSSLConnection;
        getConnection();
        provSSLConnection = this.f14007q;
        return provSSLConnection == null ? ProvSSLSession.f13946m : provSSLConnection.getSession();
    }

    synchronized void i(boolean z) {
        TlsProtocol tlsProtocol = this.f14006p;
        if (tlsProtocol == null || tlsProtocol.isHandshaking()) {
            k(z);
        }
    }

    @Override // java.net.Socket
    public boolean isBound() {
        return this.f13998h.isBound();
    }

    @Override // java.net.Socket
    public synchronized boolean isClosed() {
        boolean z;
        TlsProtocol tlsProtocol = this.f14006p;
        if (tlsProtocol != null) {
            z = tlsProtocol.isClosed();
        }
        return z;
    }

    @Override // java.net.Socket
    public boolean isConnected() {
        return this.f13998h.isConnected();
    }

    @Override // java.net.Socket
    public boolean isInputShutdown() {
        return this.f13998h.isInputShutdown();
    }

    @Override // java.net.Socket
    public boolean isOutputShutdown() {
        return this.f13998h.isOutputShutdown();
    }

    synchronized void j() {
        if (JsseUtils.Q(this.f14002l)) {
            this.f14003m = this.f14002l;
            return;
        }
        InetAddress inetAddress = getInetAddress();
        if (inetAddress == null) {
            return;
        }
        boolean z = this.f14005o;
        if (!z || !ProvSSLSocketBase.f13971c) {
            this.f14002l = (z && ProvSSLSocketBase.f13972d) ? inetAddress.getHostName() : inetAddress.getHostAddress();
            this.f14003m = null;
            return;
        }
        String hostName = inetAddress.getHostName();
        this.f14002l = hostName;
        this.f14003m = hostName;
    }

    protected void k(boolean z) {
        TlsProtocol tlsProtocol = this.f14006p;
        if (tlsProtocol != null) {
            if (!tlsProtocol.isHandshaking()) {
                throw new UnsupportedOperationException("Renegotiation not supported");
            }
            this.f14006p.setResumableHandshake(z);
            this.f14006p.resumeHandshake();
            return;
        }
        InputStream inputStream = this.f13998h.getInputStream();
        if (this.f13999i != null) {
            inputStream = new SequenceInputStream(this.f13999i, inputStream);
        }
        OutputStream outputStream = this.f13998h.getOutputStream();
        if (this.f14005o) {
            ProvTlsClientProtocol provTlsClientProtocol = new ProvTlsClientProtocol(inputStream, outputStream, this.f13973a);
            provTlsClientProtocol.setResumableHandshake(z);
            this.f14006p = provTlsClientProtocol;
            provTlsClientProtocol.connect(new ProvTlsClient(this, this.f14001k));
            return;
        }
        ProvTlsServerProtocol provTlsServerProtocol = new ProvTlsServerProtocol(inputStream, outputStream, this.f13973a);
        provTlsServerProtocol.setResumableHandshake(z);
        this.f14006p = provTlsServerProtocol;
        provTlsServerProtocol.accept(new ProvTlsServer(this, this.f14001k));
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized void notifyHandshakeComplete(ProvSSLConnection provSSLConnection) {
        ProvSSLSessionHandshake provSSLSessionHandshake = this.f14008r;
        if (provSSLSessionHandshake != null) {
            if (!provSSLSessionHandshake.isValid()) {
                provSSLConnection.getSession().invalidate();
            }
            this.f14008r.m().a();
        }
        this.f14008r = null;
        this.f14007q = provSSLConnection;
        g(provSSLConnection.getSession().f13957h);
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized void notifyHandshakeSession(ProvSSLSessionContext provSSLSessionContext, SecurityParameters securityParameters, JsseSecurityParameters jsseSecurityParameters, ProvSSLSession provSSLSession) {
        String peerHost = getPeerHost();
        int peerPort = getPeerPort();
        if (provSSLSession != null) {
            this.f14008r = new ProvSSLSessionResumed(provSSLSessionContext, peerHost, peerPort, securityParameters, jsseSecurityParameters, provSSLSession.m(), provSSLSession.l());
        } else {
            this.f14008r = new ProvSSLSessionHandshake(provSSLSessionContext, peerHost, peerPort, securityParameters, jsseSecurityParameters);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized String selectApplicationProtocol(List<String> list) {
        return this.f14001k.getSocketAPSelector().select(this, list);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized void setBCHandshakeApplicationProtocolSelector(BCApplicationProtocolSelector<SSLSocket> bCApplicationProtocolSelector) {
        this.f14001k.setSocketAPSelector(bCApplicationProtocolSelector);
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
            if (this.f14006p != null) {
                throw new IllegalArgumentException("Session-to-resume cannot be set after the handshake has begun");
            }
            this.f14001k.setSessionToResume((ProvSSLSession) bCExtendedSSLSession);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setEnableSessionCreation(boolean z) {
        this.f14004n = z;
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setEnabledCipherSuites(String[] strArr) {
        this.f14001k.setCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setEnabledProtocols(String[] strArr) {
        this.f14001k.setProtocols(strArr);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized void setHost(String str) {
        this.f14002l = str;
        this.f14003m = str;
    }

    @Override // java.net.Socket
    public void setKeepAlive(boolean z) {
        this.f13998h.setKeepAlive(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setNeedClientAuth(boolean z) {
        this.f14001k.setNeedClientAuth(z);
    }

    @Override // org.bouncycastle.jsse.BCSSLSocket
    public synchronized void setParameters(BCSSLParameters bCSSLParameters) {
        SSLParametersUtil.d(this.f14001k, bCSSLParameters);
    }

    @Override // java.net.Socket
    public void setPerformancePreferences(int i2, int i3, int i4) {
        this.f13998h.setPerformancePreferences(i2, i3, i4);
    }

    @Override // java.net.Socket
    public void setReceiveBufferSize(int i2) {
        this.f13998h.setReceiveBufferSize(i2);
    }

    @Override // java.net.Socket
    public void setReuseAddress(boolean z) {
        this.f13998h.setReuseAddress(z);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setSSLParameters(SSLParameters sSLParameters) {
        SSLParametersUtil.e(this.f14001k, sSLParameters);
    }

    @Override // java.net.Socket
    public void setSendBufferSize(int i2) {
        this.f13998h.setSendBufferSize(i2);
    }

    @Override // java.net.Socket
    public void setSoLinger(boolean z, int i2) {
        this.f13998h.setSoLinger(z, i2);
    }

    @Override // java.net.Socket
    public void setSoTimeout(int i2) {
        this.f13998h.setSoTimeout(i2);
    }

    @Override // java.net.Socket
    public void setTcpNoDelay(boolean z) {
        this.f13998h.setTcpNoDelay(z);
    }

    @Override // java.net.Socket
    public void setTrafficClass(int i2) {
        this.f13998h.setTrafficClass(i2);
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setUseClientMode(boolean z) {
        if (this.f14006p != null) {
            throw new IllegalArgumentException("Mode cannot be changed after the initial handshake has begun");
        }
        if (this.f14005o != z) {
            this.f13997g.c().t(this.f14001k, z);
            this.f14005o = z;
        }
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void setWantClientAuth(boolean z) {
        this.f14001k.setWantClientAuth(z);
    }

    @Override // java.net.Socket
    public void shutdownInput() {
        this.f13998h.shutdownInput();
    }

    @Override // java.net.Socket
    public void shutdownOutput() {
        this.f13998h.shutdownOutput();
    }

    @Override // javax.net.ssl.SSLSocket
    public synchronized void startHandshake() {
        k(true);
    }

    @Override // javax.net.ssl.SSLSocket, java.net.Socket
    public String toString() {
        return this.f13998h.toString();
    }
}
