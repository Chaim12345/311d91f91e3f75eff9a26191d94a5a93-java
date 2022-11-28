package org.apache.http.impl.pool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpConnectionFactory;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.apache.http.impl.DefaultBHttpClientConnectionFactory;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParamConfig;
import org.apache.http.params.HttpParams;
import org.apache.http.pool.ConnFactory;
import org.apache.http.util.Args;
import org.apache.http.util.Asserts;
@Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/* loaded from: classes3.dex */
public class BasicConnFactory implements ConnFactory<HttpHost, HttpClientConnection> {
    private final HttpConnectionFactory<? extends HttpClientConnection> connFactory;
    private final int connectTimeout;
    private final SocketFactory plainfactory;
    private final SocketConfig sconfig;
    private final SSLSocketFactory sslfactory;

    public BasicConnFactory() {
        this(null, null, 0, SocketConfig.DEFAULT, ConnectionConfig.DEFAULT);
    }

    public BasicConnFactory(int i2, SocketConfig socketConfig, ConnectionConfig connectionConfig) {
        this(null, null, i2, socketConfig, connectionConfig);
    }

    public BasicConnFactory(SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, int i2, SocketConfig socketConfig, ConnectionConfig connectionConfig) {
        this.plainfactory = socketFactory;
        this.sslfactory = sSLSocketFactory;
        this.connectTimeout = i2;
        this.sconfig = socketConfig == null ? SocketConfig.DEFAULT : socketConfig;
        this.connFactory = new DefaultBHttpClientConnectionFactory(connectionConfig == null ? ConnectionConfig.DEFAULT : connectionConfig);
    }

    @Deprecated
    public BasicConnFactory(SSLSocketFactory sSLSocketFactory, HttpParams httpParams) {
        Args.notNull(httpParams, "HTTP params");
        this.plainfactory = null;
        this.sslfactory = sSLSocketFactory;
        this.connectTimeout = httpParams.getIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 0);
        this.sconfig = HttpParamConfig.getSocketConfig(httpParams);
        this.connFactory = new DefaultBHttpClientConnectionFactory(HttpParamConfig.getConnectionConfig(httpParams));
    }

    public BasicConnFactory(SocketConfig socketConfig, ConnectionConfig connectionConfig) {
        this(null, null, 0, socketConfig, connectionConfig);
    }

    @Deprecated
    public BasicConnFactory(HttpParams httpParams) {
        this((SSLSocketFactory) null, httpParams);
    }

    @Deprecated
    protected HttpClientConnection create(Socket socket, HttpParams httpParams) {
        DefaultBHttpClientConnection defaultBHttpClientConnection = new DefaultBHttpClientConnection(httpParams.getIntParameter(CoreConnectionPNames.SOCKET_BUFFER_SIZE, 8192));
        defaultBHttpClientConnection.bind(socket);
        return defaultBHttpClientConnection;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008c  */
    @Override // org.apache.http.pool.ConnFactory
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public HttpClientConnection create(HttpHost httpHost) {
        SocketFactory socketFactory;
        final Socket createSocket;
        int port;
        int soLinger;
        String schemeName = httpHost.getSchemeName();
        try {
            if (HttpHost.DEFAULT_SCHEME_NAME.equalsIgnoreCase(schemeName)) {
                socketFactory = this.plainfactory;
                if (socketFactory == null) {
                    createSocket = new Socket();
                    String hostName = httpHost.getHostName();
                    port = httpHost.getPort();
                    if (port == -1) {
                        if (httpHost.getSchemeName().equalsIgnoreCase(HttpHost.DEFAULT_SCHEME_NAME)) {
                            port = 80;
                        } else if (httpHost.getSchemeName().equalsIgnoreCase("https")) {
                            port = 443;
                        }
                    }
                    createSocket.setSoTimeout(this.sconfig.getSoTimeout());
                    if (this.sconfig.getSndBufSize() > 0) {
                        createSocket.setSendBufferSize(this.sconfig.getSndBufSize());
                    }
                    if (this.sconfig.getRcvBufSize() > 0) {
                        createSocket.setReceiveBufferSize(this.sconfig.getRcvBufSize());
                    }
                    createSocket.setTcpNoDelay(this.sconfig.isTcpNoDelay());
                    soLinger = this.sconfig.getSoLinger();
                    if (soLinger >= 0) {
                        createSocket.setSoLinger(true, soLinger);
                    }
                    createSocket.setKeepAlive(this.sconfig.isSoKeepAlive());
                    final InetSocketAddress inetSocketAddress = new InetSocketAddress(hostName, port);
                    AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() { // from class: org.apache.http.impl.pool.BasicConnFactory.1
                        @Override // java.security.PrivilegedExceptionAction
                        public Object run() {
                            createSocket.connect(inetSocketAddress, BasicConnFactory.this.connectTimeout);
                            return null;
                        }
                    });
                    return this.connFactory.createConnection(createSocket);
                }
            } else if (!"https".equalsIgnoreCase(schemeName)) {
                throw new IOException(schemeName + " scheme is not supported");
            } else {
                socketFactory = this.sslfactory;
                if (socketFactory == null) {
                    socketFactory = SSLSocketFactory.getDefault();
                }
            }
            AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() { // from class: org.apache.http.impl.pool.BasicConnFactory.1
                @Override // java.security.PrivilegedExceptionAction
                public Object run() {
                    createSocket.connect(inetSocketAddress, BasicConnFactory.this.connectTimeout);
                    return null;
                }
            });
            return this.connFactory.createConnection(createSocket);
        } catch (PrivilegedActionException e2) {
            Asserts.check(e2.getCause() instanceof IOException, "method contract violation only checked exceptions are wrapped: " + e2.getCause());
            throw ((IOException) e2.getCause());
        }
        createSocket = socketFactory.createSocket();
        String hostName2 = httpHost.getHostName();
        port = httpHost.getPort();
        if (port == -1) {
        }
        createSocket.setSoTimeout(this.sconfig.getSoTimeout());
        if (this.sconfig.getSndBufSize() > 0) {
        }
        if (this.sconfig.getRcvBufSize() > 0) {
        }
        createSocket.setTcpNoDelay(this.sconfig.isTcpNoDelay());
        soLinger = this.sconfig.getSoLinger();
        if (soLinger >= 0) {
        }
        createSocket.setKeepAlive(this.sconfig.isSoKeepAlive());
        final InetSocketAddress inetSocketAddress2 = new InetSocketAddress(hostName2, port);
    }
}
