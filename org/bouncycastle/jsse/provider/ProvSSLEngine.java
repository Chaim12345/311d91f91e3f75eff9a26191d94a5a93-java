package org.bouncycastle.jsse.provider;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.Principal;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import org.bouncycastle.jsse.BCApplicationProtocolSelector;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSSLConnection;
import org.bouncycastle.jsse.BCSSLEngine;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCX509Key;
import org.bouncycastle.tls.RecordPreview;
import org.bouncycastle.tls.SecurityParameters;
import org.bouncycastle.tls.TlsClientProtocol;
import org.bouncycastle.tls.TlsFatalAlert;
import org.bouncycastle.tls.TlsProtocol;
import org.bouncycastle.tls.TlsServerProtocol;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvSSLEngine extends SSLEngine implements BCSSLEngine, ProvTlsManager {
    private static final Logger LOG = Logger.getLogger(ProvSSLEngine.class.getName());

    /* renamed from: a  reason: collision with root package name */
    protected final ContextData f13928a;

    /* renamed from: b  reason: collision with root package name */
    protected final ProvSSLParameters f13929b;

    /* renamed from: c  reason: collision with root package name */
    protected boolean f13930c;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f13931d;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f13932e;

    /* renamed from: f  reason: collision with root package name */
    protected boolean f13933f;

    /* renamed from: g  reason: collision with root package name */
    protected boolean f13934g;

    /* renamed from: h  reason: collision with root package name */
    protected SSLEngineResult.HandshakeStatus f13935h;

    /* renamed from: i  reason: collision with root package name */
    protected TlsProtocol f13936i;

    /* renamed from: j  reason: collision with root package name */
    protected ProvTlsPeer f13937j;

    /* renamed from: k  reason: collision with root package name */
    protected ProvSSLConnection f13938k;

    /* renamed from: l  reason: collision with root package name */
    protected ProvSSLSessionHandshake f13939l;

    /* renamed from: m  reason: collision with root package name */
    protected SSLException f13940m;

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLEngine(ContextData contextData) {
        this(contextData, null, -1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLEngine(ContextData contextData, String str, int i2) {
        super(str, i2);
        this.f13930c = true;
        this.f13931d = true;
        this.f13932e = false;
        this.f13933f = false;
        this.f13934g = false;
        this.f13935h = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
        this.f13936i = null;
        this.f13937j = null;
        this.f13938k = null;
        this.f13939l = null;
        this.f13940m = null;
        this.f13928a = contextData;
        this.f13929b = contextData.c().h(this.f13931d);
    }

    private RecordPreview getRecordPreview(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < 5) {
            return null;
        }
        byte[] bArr = new byte[5];
        int position = byteBuffer.position();
        byteBuffer.get(bArr);
        byteBuffer.position(position);
        return this.f13936i.previewInputRecord(bArr);
    }

    private int getTotalRemaining(ByteBuffer[] byteBufferArr, int i2, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < i3; i6++) {
            int remaining = byteBufferArr[i2 + i6].remaining();
            if (remaining >= i4 - i5) {
                return i4;
            }
            i5 += remaining;
        }
        return i5;
    }

    private boolean hasInsufficientSpace(ByteBuffer[] byteBufferArr, int i2, int i3, int i4) {
        return getTotalRemaining(byteBufferArr, i2, i3, i4) < i4;
    }

    ProvSSLSession a() {
        ProvSSLConnection provSSLConnection = this.f13938k;
        return provSSLConnection == null ? ProvSSLSession.f13946m : provSSLConnection.getSession();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void beginHandshake() {
        SSLEngineResult.HandshakeStatus handshakeStatus;
        if (!this.f13932e) {
            throw new IllegalStateException("Client/Server mode must be set before the handshake can begin");
        }
        if (this.f13933f) {
            throw new SSLException("Connection is already closed");
        }
        if (this.f13934g) {
            throw new UnsupportedOperationException("Renegotiation not supported");
        }
        this.f13934g = true;
        try {
            if (this.f13931d) {
                TlsClientProtocol tlsClientProtocol = new TlsClientProtocol();
                this.f13936i = tlsClientProtocol;
                ProvTlsClient provTlsClient = new ProvTlsClient(this, this.f13929b);
                this.f13937j = provTlsClient;
                tlsClientProtocol.connect(provTlsClient);
                handshakeStatus = SSLEngineResult.HandshakeStatus.NEED_WRAP;
            } else {
                TlsServerProtocol tlsServerProtocol = new TlsServerProtocol();
                this.f13936i = tlsServerProtocol;
                ProvTlsServer provTlsServer = new ProvTlsServer(this, this.f13929b);
                this.f13937j = provTlsServer;
                tlsServerProtocol.accept(provTlsServer);
                handshakeStatus = SSLEngineResult.HandshakeStatus.NEED_UNWRAP;
            }
            this.f13935h = handshakeStatus;
        } catch (SSLException e2) {
            throw e2;
        } catch (IOException e3) {
            throw new SSLException(e3);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        try {
            this.f13928a.i().checkClientTrusted((X509Certificate[]) x509CertificateArr.clone(), str, this);
        } catch (CertificateException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        try {
            this.f13928a.i().checkServerTrusted((X509Certificate[]) x509CertificateArr.clone(), str, this);
        } catch (CertificateException e2) {
            throw new TlsFatalAlert((short) 46, (Throwable) e2);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public BCX509Key chooseClientKey(String[] strArr, Principal[] principalArr) {
        return getContextData().h().chooseEngineClientKeyBC(strArr, (Principal[]) JsseUtils.d(principalArr), this);
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public BCX509Key chooseServerKey(String[] strArr, Principal[] principalArr) {
        return getContextData().h().chooseEngineServerKeyBC(strArr, (Principal[]) JsseUtils.d(principalArr), this);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void closeInbound() {
        if (!this.f13933f) {
            TlsProtocol tlsProtocol = this.f13936i;
            if (tlsProtocol == null) {
                this.f13933f = true;
            } else {
                try {
                    tlsProtocol.closeInput();
                } catch (IOException e2) {
                    throw new SSLException(e2);
                }
            }
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void closeOutbound() {
        if (!this.f13933f) {
            TlsProtocol tlsProtocol = this.f13936i;
            if (tlsProtocol == null) {
                this.f13933f = true;
            } else {
                try {
                    tlsProtocol.close();
                } catch (IOException e2) {
                    LOG.log(Level.WARNING, "Failed to close outbound", (Throwable) e2);
                }
            }
        }
    }

    @Override // javax.net.ssl.SSLEngine, org.bouncycastle.jsse.BCSSLEngine
    public synchronized String getApplicationProtocol() {
        ProvSSLConnection provSSLConnection;
        provSSLConnection = this.f13938k;
        return provSSLConnection == null ? null : provSSLConnection.getApplicationProtocol();
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized BCApplicationProtocolSelector<SSLEngine> getBCHandshakeApplicationProtocolSelector() {
        return this.f13929b.getEngineAPSelector();
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized BCExtendedSSLSession getBCHandshakeSession() {
        return this.f13939l;
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public BCExtendedSSLSession getBCSession() {
        return a();
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized BCSSLConnection getConnection() {
        return this.f13938k;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public ContextData getContextData() {
        return this.f13928a;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized Runnable getDelegatedTask() {
        return null;
    }

    @Override // javax.net.ssl.SSLEngine, org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized boolean getEnableSessionCreation() {
        return this.f13930c;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized String[] getEnabledCipherSuites() {
        return this.f13929b.getCipherSuites();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized String[] getEnabledProtocols() {
        return this.f13929b.getProtocols();
    }

    @Override // javax.net.ssl.SSLEngine, org.bouncycastle.jsse.BCSSLEngine
    public synchronized String getHandshakeApplicationProtocol() {
        ProvSSLSessionHandshake provSSLSessionHandshake;
        provSSLSessionHandshake = this.f13939l;
        return provSSLSessionHandshake == null ? null : provSSLSessionHandshake.l();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized SSLSession getHandshakeSession() {
        ProvSSLSessionHandshake provSSLSessionHandshake;
        provSSLSessionHandshake = this.f13939l;
        return provSSLSessionHandshake == null ? null : provSSLSessionHandshake.c();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized SSLEngineResult.HandshakeStatus getHandshakeStatus() {
        return this.f13935h;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized boolean getNeedClientAuth() {
        return this.f13929b.getNeedClientAuth();
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized BCSSLParameters getParameters() {
        return SSLParametersUtil.a(this.f13929b);
    }

    @Override // javax.net.ssl.SSLEngine, org.bouncycastle.jsse.provider.ProvTlsManager
    public String getPeerHost() {
        return super.getPeerHost();
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public String getPeerHostSNI() {
        return super.getPeerHost();
    }

    @Override // javax.net.ssl.SSLEngine, org.bouncycastle.jsse.provider.ProvTlsManager
    public int getPeerPort() {
        return super.getPeerPort();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized SSLParameters getSSLParameters() {
        return SSLParametersUtil.b(this.f13929b);
    }

    @Override // javax.net.ssl.SSLEngine
    public SSLSession getSession() {
        return a().c();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized String[] getSupportedCipherSuites() {
        return this.f13928a.c().l();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized String[] getSupportedProtocols() {
        return this.f13928a.c().n();
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized boolean getUseClientMode() {
        return this.f13931d;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized boolean getWantClientAuth() {
        return this.f13929b.getWantClientAuth();
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000d, code lost:
        if (r0.isClosed() != false) goto L14;
     */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean isInboundDone() {
        boolean z;
        if (!this.f13933f) {
            TlsProtocol tlsProtocol = this.f13936i;
            if (tlsProtocol != null) {
            }
            z = false;
        }
        z = true;
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0016, code lost:
        if (r2.f13936i.getAvailableOutputBytes() < 1) goto L13;
     */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized boolean isOutboundDone() {
        boolean z;
        z = true;
        if (!this.f13933f) {
            TlsProtocol tlsProtocol = this.f13936i;
            if (tlsProtocol != null && tlsProtocol.isClosed()) {
            }
            z = false;
        }
        return z;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized void notifyHandshakeComplete(ProvSSLConnection provSSLConnection) {
        ProvSSLSessionHandshake provSSLSessionHandshake = this.f13939l;
        if (provSSLSessionHandshake != null) {
            if (!provSSLSessionHandshake.isValid()) {
                provSSLConnection.getSession().invalidate();
            }
            this.f13939l.m().a();
        }
        this.f13939l = null;
        this.f13938k = provSSLConnection;
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized void notifyHandshakeSession(ProvSSLSessionContext provSSLSessionContext, SecurityParameters securityParameters, JsseSecurityParameters jsseSecurityParameters, ProvSSLSession provSSLSession) {
        String peerHost = getPeerHost();
        int peerPort = getPeerPort();
        if (provSSLSession != null) {
            this.f13939l = new ProvSSLSessionResumed(provSSLSessionContext, peerHost, peerPort, securityParameters, jsseSecurityParameters, provSSLSession.m(), provSSLSession.l());
        } else {
            this.f13939l = new ProvSSLSessionHandshake(provSSLSessionContext, peerHost, peerPort, securityParameters, jsseSecurityParameters);
        }
    }

    @Override // org.bouncycastle.jsse.provider.ProvTlsManager
    public synchronized String selectApplicationProtocol(List<String> list) {
        return this.f13929b.getEngineAPSelector().select(this, list);
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized void setBCHandshakeApplicationProtocolSelector(BCApplicationProtocolSelector<SSLEngine> bCApplicationProtocolSelector) {
        this.f13929b.setEngineAPSelector(bCApplicationProtocolSelector);
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized void setBCSessionToResume(BCExtendedSSLSession bCExtendedSSLSession) {
        try {
            if (bCExtendedSSLSession == null) {
                throw new NullPointerException("'session' cannot be null");
            }
            if (!(bCExtendedSSLSession instanceof ProvSSLSession)) {
                throw new IllegalArgumentException("Session-to-resume must be a session returned from 'getBCSession'");
            }
            if (this.f13934g) {
                throw new IllegalArgumentException("Session-to-resume cannot be set after the handshake has begun");
            }
            this.f13929b.setSessionToResume((ProvSSLSession) bCExtendedSSLSession);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setEnableSessionCreation(boolean z) {
        this.f13930c = z;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setEnabledCipherSuites(String[] strArr) {
        this.f13929b.setCipherSuites(strArr);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setEnabledProtocols(String[] strArr) {
        this.f13929b.setProtocols(strArr);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setNeedClientAuth(boolean z) {
        this.f13929b.setNeedClientAuth(z);
    }

    @Override // org.bouncycastle.jsse.BCSSLEngine
    public synchronized void setParameters(BCSSLParameters bCSSLParameters) {
        SSLParametersUtil.d(this.f13929b, bCSSLParameters);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setSSLParameters(SSLParameters sSLParameters) {
        SSLParametersUtil.e(this.f13929b, sSLParameters);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setUseClientMode(boolean z) {
        if (this.f13934g) {
            throw new IllegalArgumentException("Client/Server mode cannot be changed after the handshake has begun");
        }
        if (this.f13931d != z) {
            this.f13928a.c().t(this.f13929b, z);
            this.f13931d = z;
        }
        this.f13932e = true;
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setWantClientAuth(boolean z) {
        this.f13929b.setWantClientAuth(z);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr, int i2, int i3) {
        int i4;
        SSLEngineResult.Status status;
        if (!this.f13934g) {
            beginHandshake();
        }
        SSLEngineResult.Status status2 = SSLEngineResult.Status.OK;
        int i5 = 0;
        if (this.f13936i.isClosed()) {
            status = SSLEngineResult.Status.CLOSED;
            i4 = 0;
        } else {
            try {
                RecordPreview recordPreview = getRecordPreview(byteBuffer);
                if (recordPreview != null && byteBuffer.remaining() >= recordPreview.getRecordSize()) {
                    if (hasInsufficientSpace(byteBufferArr, i2, i3, recordPreview.getContentLimit())) {
                        status2 = SSLEngineResult.Status.BUFFER_OVERFLOW;
                        i4 = 0;
                        status = status2;
                    } else {
                        int recordSize = recordPreview.getRecordSize();
                        byte[] bArr = new byte[recordSize];
                        byteBuffer.get(bArr);
                        this.f13936i.offerInput(bArr, 0, recordSize);
                        int i6 = recordSize + 0;
                        try {
                            int availableInputBytes = this.f13936i.getAvailableInputBytes();
                            i4 = 0;
                            for (int i7 = 0; i7 < i3 && availableInputBytes > 0; i7++) {
                                try {
                                    ByteBuffer byteBuffer2 = byteBufferArr[i2 + i7];
                                    int min = Math.min(byteBuffer2.remaining(), availableInputBytes);
                                    if (min > 0) {
                                        byte[] bArr2 = new byte[min];
                                        this.f13936i.readInput(bArr2, 0, min);
                                        byteBuffer2.put(bArr2);
                                        i4 += min;
                                        availableInputBytes -= min;
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    i5 = i6;
                                    if (this.f13935h == SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
                                        if (this.f13940m == null) {
                                            this.f13940m = new SSLException(e);
                                        }
                                        this.f13935h = SSLEngineResult.HandshakeStatus.NEED_WRAP;
                                        return new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_WRAP, i5, i4);
                                    }
                                    throw new SSLException(e);
                                }
                            }
                            if (availableInputBytes != 0) {
                                throw new TlsFatalAlert((short) 22);
                            }
                            i5 = i6;
                            status = status2;
                        } catch (IOException e3) {
                            e = e3;
                            i4 = 0;
                        }
                    }
                }
                status2 = SSLEngineResult.Status.BUFFER_UNDERFLOW;
                i4 = 0;
                status = status2;
            } catch (IOException e4) {
                e = e4;
                i4 = 0;
            }
        }
        SSLEngineResult.HandshakeStatus handshakeStatus = this.f13935h;
        if (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
            if (this.f13936i.getAvailableOutputBytes() > 0) {
                handshakeStatus = SSLEngineResult.HandshakeStatus.NEED_WRAP;
            } else if (this.f13937j.isHandshakeComplete()) {
                this.f13935h = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
                handshakeStatus = SSLEngineResult.HandshakeStatus.FINISHED;
            } else if (this.f13936i.isClosed()) {
                handshakeStatus = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
            }
            this.f13935h = handshakeStatus;
        }
        return new SSLEngineResult(status, handshakeStatus, i5, i4);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0082 A[Catch: all -> 0x00ce, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x0009, B:8:0x000c, B:10:0x0015, B:12:0x001d, B:13:0x0020, B:16:0x0029, B:18:0x0035, B:20:0x0049, B:21:0x004d, B:24:0x0055, B:26:0x0065, B:27:0x0069, B:28:0x006c, B:34:0x007a, B:36:0x0082, B:38:0x008c, B:39:0x0099, B:40:0x009b, B:44:0x00a4, B:46:0x00ac, B:47:0x00b3, B:49:0x00bb, B:50:0x00bd, B:51:0x00c0, B:52:0x00c3, B:31:0x0073, B:32:0x0078, B:56:0x00cb, B:57:0x00cd), top: B:62:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ac A[Catch: all -> 0x00ce, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x0009, B:8:0x000c, B:10:0x0015, B:12:0x001d, B:13:0x0020, B:16:0x0029, B:18:0x0035, B:20:0x0049, B:21:0x004d, B:24:0x0055, B:26:0x0065, B:27:0x0069, B:28:0x006c, B:34:0x007a, B:36:0x0082, B:38:0x008c, B:39:0x0099, B:40:0x009b, B:44:0x00a4, B:46:0x00ac, B:47:0x00b3, B:49:0x00bb, B:50:0x00bd, B:51:0x00c0, B:52:0x00c3, B:31:0x0073, B:32:0x0078, B:56:0x00cb, B:57:0x00cd), top: B:62:0x0001, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b3 A[Catch: all -> 0x00ce, TryCatch #1 {, blocks: (B:3:0x0001, B:5:0x0005, B:7:0x0009, B:8:0x000c, B:10:0x0015, B:12:0x001d, B:13:0x0020, B:16:0x0029, B:18:0x0035, B:20:0x0049, B:21:0x004d, B:24:0x0055, B:26:0x0065, B:27:0x0069, B:28:0x006c, B:34:0x007a, B:36:0x0082, B:38:0x008c, B:39:0x0099, B:40:0x009b, B:44:0x00a4, B:46:0x00ac, B:47:0x00b3, B:49:0x00bb, B:50:0x00bd, B:51:0x00c0, B:52:0x00c3, B:31:0x0073, B:32:0x0078, B:56:0x00cb, B:57:0x00cd), top: B:62:0x0001, inners: #0 }] */
    @Override // javax.net.ssl.SSLEngine
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public synchronized SSLEngineResult wrap(ByteBuffer[] byteBufferArr, int i2, int i3, ByteBuffer byteBuffer) {
        SSLEngineResult.Status status;
        int i4;
        int i5;
        int availableOutputBytes;
        SSLEngineResult.HandshakeStatus handshakeStatus;
        SSLException sSLException = this.f13940m;
        if (sSLException != null) {
            this.f13940m = null;
            throw sSLException;
        }
        if (!this.f13934g) {
            beginHandshake();
        }
        status = SSLEngineResult.Status.OK;
        i4 = 0;
        if (this.f13935h == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            if (this.f13936i.isClosed()) {
                status = SSLEngineResult.Status.CLOSED;
            } else if (this.f13936i.getAvailableOutputBytes() <= 0) {
                try {
                    int totalRemaining = getTotalRemaining(byteBufferArr, i2, i3, this.f13936i.getApplicationDataLimit());
                    if (totalRemaining > 0) {
                        RecordPreview previewOutputRecord = this.f13936i.previewOutputRecord(totalRemaining);
                        int contentLimit = previewOutputRecord.getContentLimit();
                        if (byteBuffer.remaining() < previewOutputRecord.getRecordSize()) {
                            status = SSLEngineResult.Status.BUFFER_OVERFLOW;
                        } else {
                            byte[] bArr = new byte[contentLimit];
                            i5 = 0;
                            for (int i6 = 0; i6 < i3 && i5 < contentLimit; i6++) {
                                ByteBuffer byteBuffer2 = byteBufferArr[i2 + i6];
                                int min = Math.min(byteBuffer2.remaining(), contentLimit - i5);
                                if (min > 0) {
                                    byteBuffer2.get(bArr, i5, min);
                                    i5 += min;
                                }
                            }
                            this.f13936i.writeApplicationData(bArr, 0, i5);
                            availableOutputBytes = this.f13936i.getAvailableOutputBytes();
                            if (availableOutputBytes > 0) {
                                int min2 = Math.min(byteBuffer.remaining(), availableOutputBytes);
                                if (min2 > 0) {
                                    byte[] bArr2 = new byte[min2];
                                    this.f13936i.readOutput(bArr2, 0, min2);
                                    byteBuffer.put(bArr2);
                                    i4 = 0 + min2;
                                    availableOutputBytes -= min2;
                                } else {
                                    status = SSLEngineResult.Status.BUFFER_OVERFLOW;
                                }
                            }
                            handshakeStatus = this.f13935h;
                            if (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_WRAP && availableOutputBytes <= 0) {
                                if (this.f13937j.isHandshakeComplete()) {
                                    handshakeStatus = this.f13936i.isClosed() ? SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING : SSLEngineResult.HandshakeStatus.NEED_UNWRAP;
                                    this.f13935h = handshakeStatus;
                                } else {
                                    this.f13935h = SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
                                    handshakeStatus = SSLEngineResult.HandshakeStatus.FINISHED;
                                }
                            }
                        }
                    }
                } catch (IOException e2) {
                    throw new SSLException(e2);
                }
            }
        }
        i5 = 0;
        availableOutputBytes = this.f13936i.getAvailableOutputBytes();
        if (availableOutputBytes > 0) {
        }
        handshakeStatus = this.f13935h;
        if (handshakeStatus == SSLEngineResult.HandshakeStatus.NEED_WRAP) {
            if (this.f13937j.isHandshakeComplete()) {
            }
        }
        return new SSLEngineResult(status, handshakeStatus, i5, i4);
    }
}
