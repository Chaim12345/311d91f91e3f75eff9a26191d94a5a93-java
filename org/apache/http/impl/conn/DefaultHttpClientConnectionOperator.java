package org.apache.http.impl.conn;

import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Lookup;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpClientConnectionOperator;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.SchemePortResolver;
import org.apache.http.conn.UnsupportedSchemeException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
@Contract(threading = ThreadingBehavior.IMMUTABLE_CONDITIONAL)
/* loaded from: classes3.dex */
public class DefaultHttpClientConnectionOperator implements HttpClientConnectionOperator {
    static final String SOCKET_FACTORY_REGISTRY = "http.socket-factory-registry";
    private final DnsResolver dnsResolver;
    private final Log log = LogFactory.getLog(getClass());
    private final SchemePortResolver schemePortResolver;
    private final Lookup<ConnectionSocketFactory> socketFactoryRegistry;

    public DefaultHttpClientConnectionOperator(Lookup<ConnectionSocketFactory> lookup, SchemePortResolver schemePortResolver, DnsResolver dnsResolver) {
        Args.notNull(lookup, "Socket factory registry");
        this.socketFactoryRegistry = lookup;
        this.schemePortResolver = schemePortResolver == null ? DefaultSchemePortResolver.INSTANCE : schemePortResolver;
        this.dnsResolver = dnsResolver == null ? SystemDefaultDnsResolver.INSTANCE : dnsResolver;
    }

    private Lookup<ConnectionSocketFactory> getSocketFactoryRegistry(HttpContext httpContext) {
        Lookup<ConnectionSocketFactory> lookup = (Lookup) httpContext.getAttribute("http.socket-factory-registry");
        return lookup == null ? this.socketFactoryRegistry : lookup;
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0135 A[SYNTHETIC] */
    @Override // org.apache.http.conn.HttpClientConnectionOperator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void connect(ManagedHttpClientConnection managedHttpClientConnection, HttpHost httpHost, InetSocketAddress inetSocketAddress, int i2, SocketConfig socketConfig, HttpContext httpContext) {
        ConnectionSocketFactory lookup = getSocketFactoryRegistry(httpContext).lookup(httpHost.getSchemeName());
        if (lookup == null) {
            throw new UnsupportedSchemeException(httpHost.getSchemeName() + " protocol is not supported");
        }
        InetAddress[] resolve = httpHost.getAddress() != null ? new InetAddress[]{httpHost.getAddress()} : this.dnsResolver.resolve(httpHost.getHostName());
        int resolve2 = this.schemePortResolver.resolve(httpHost);
        int i3 = 0;
        while (i3 < resolve.length) {
            InetAddress inetAddress = resolve[i3];
            boolean z = i3 == resolve.length - 1;
            Socket createSocket = lookup.createSocket(httpContext);
            createSocket.setSoTimeout(socketConfig.getSoTimeout());
            createSocket.setReuseAddress(socketConfig.isSoReuseAddress());
            createSocket.setTcpNoDelay(socketConfig.isTcpNoDelay());
            createSocket.setKeepAlive(socketConfig.isSoKeepAlive());
            if (socketConfig.getRcvBufSize() > 0) {
                createSocket.setReceiveBufferSize(socketConfig.getRcvBufSize());
            }
            if (socketConfig.getSndBufSize() > 0) {
                createSocket.setSendBufferSize(socketConfig.getSndBufSize());
            }
            int soLinger = socketConfig.getSoLinger();
            if (soLinger >= 0) {
                createSocket.setSoLinger(true, soLinger);
            }
            managedHttpClientConnection.bind(createSocket);
            InetSocketAddress inetSocketAddress2 = new InetSocketAddress(inetAddress, resolve2);
            if (this.log.isDebugEnabled()) {
                this.log.debug("Connecting to " + inetSocketAddress2);
            }
            int i4 = i3;
            int i5 = resolve2;
            try {
                managedHttpClientConnection.bind(lookup.connectSocket(i2, createSocket, httpHost, inetSocketAddress2, inetSocketAddress, httpContext));
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Connection established " + managedHttpClientConnection);
                    return;
                }
                return;
            } catch (ConnectException e2) {
                if (z) {
                    if (!"Connection timed out".equals(e2.getMessage())) {
                        throw new HttpHostConnectException(e2, httpHost, resolve);
                    }
                    throw new ConnectTimeoutException(e2, httpHost, resolve);
                }
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Connect to " + inetSocketAddress2 + " timed out. Connection will be retried using another IP address");
                }
                i3 = i4 + 1;
                resolve2 = i5;
            } catch (NoRouteToHostException e3) {
                if (z) {
                    throw e3;
                }
                if (this.log.isDebugEnabled()) {
                }
                i3 = i4 + 1;
                resolve2 = i5;
            } catch (SocketTimeoutException e4) {
                if (z) {
                    throw new ConnectTimeoutException(e4, httpHost, resolve);
                }
                if (this.log.isDebugEnabled()) {
                }
                i3 = i4 + 1;
                resolve2 = i5;
            }
        }
    }

    @Override // org.apache.http.conn.HttpClientConnectionOperator
    public void upgrade(ManagedHttpClientConnection managedHttpClientConnection, HttpHost httpHost, HttpContext httpContext) {
        ConnectionSocketFactory lookup = getSocketFactoryRegistry(HttpClientContext.adapt(httpContext)).lookup(httpHost.getSchemeName());
        if (lookup == null) {
            throw new UnsupportedSchemeException(httpHost.getSchemeName() + " protocol is not supported");
        } else if (lookup instanceof LayeredConnectionSocketFactory) {
            managedHttpClientConnection.bind(((LayeredConnectionSocketFactory) lookup).createLayeredSocket(managedHttpClientConnection.getSocket(), httpHost.getHostName(), this.schemePortResolver.resolve(httpHost), httpContext));
        } else {
            throw new UnsupportedSchemeException(httpHost.getSchemeName() + " protocol does not support connection upgrade");
        }
    }
}
