package org.apache.http.conn.ssl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.security.auth.x500.X500Principal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.apache.http.util.TextUtils;
@Contract(threading = ThreadingBehavior.SAFE)
/* loaded from: classes3.dex */
public class SSLConnectionSocketFactory implements LayeredConnectionSocketFactory {
    public static final String SSL = "SSL";
    public static final String SSLV2 = "SSLv2";
    public static final String TLS = "TLS";
    private final HostnameVerifier hostnameVerifier;
    private final Log log;
    private final javax.net.ssl.SSLSocketFactory socketfactory;
    private final String[] supportedCipherSuites;
    private final String[] supportedProtocols;
    @Deprecated
    public static final X509HostnameVerifier ALLOW_ALL_HOSTNAME_VERIFIER = AllowAllHostnameVerifier.INSTANCE;
    @Deprecated
    public static final X509HostnameVerifier BROWSER_COMPATIBLE_HOSTNAME_VERIFIER = BrowserCompatHostnameVerifier.INSTANCE;
    @Deprecated
    public static final X509HostnameVerifier STRICT_HOSTNAME_VERIFIER = StrictHostnameVerifier.INSTANCE;
    private static final String WEAK_KEY_EXCHANGES = "^(TLS|SSL)_(NULL|ECDH_anon|DH_anon|DH_anon_EXPORT|DHE_RSA_EXPORT|DHE_DSS_EXPORT|DSS_EXPORT|DH_DSS_EXPORT|DH_RSA_EXPORT|RSA_EXPORT|KRB5_EXPORT)_(.*)";
    private static final String WEAK_CIPHERS = "^(TLS|SSL)_(.*)_WITH_(NULL|DES_CBC|DES40_CBC|DES_CBC_40|3DES_EDE_CBC|RC4_128|RC4_40|RC2_CBC_40)_(.*)";
    private static final List<Pattern> WEAK_CIPHER_SUITE_PATTERNS = Collections.unmodifiableList(Arrays.asList(Pattern.compile(WEAK_KEY_EXCHANGES, 2), Pattern.compile(WEAK_CIPHERS, 2)));

    public SSLConnectionSocketFactory(SSLContext sSLContext) {
        this(sSLContext, getDefaultHostnameVerifier());
    }

    public SSLConnectionSocketFactory(SSLContext sSLContext, HostnameVerifier hostnameVerifier) {
        this(((SSLContext) Args.notNull(sSLContext, "SSL context")).getSocketFactory(), (String[]) null, (String[]) null, hostnameVerifier);
    }

    @Deprecated
    public SSLConnectionSocketFactory(SSLContext sSLContext, X509HostnameVerifier x509HostnameVerifier) {
        this(((SSLContext) Args.notNull(sSLContext, "SSL context")).getSocketFactory(), (String[]) null, (String[]) null, x509HostnameVerifier);
    }

    public SSLConnectionSocketFactory(SSLContext sSLContext, String[] strArr, String[] strArr2, HostnameVerifier hostnameVerifier) {
        this(((SSLContext) Args.notNull(sSLContext, "SSL context")).getSocketFactory(), strArr, strArr2, hostnameVerifier);
    }

    @Deprecated
    public SSLConnectionSocketFactory(SSLContext sSLContext, String[] strArr, String[] strArr2, X509HostnameVerifier x509HostnameVerifier) {
        this(((SSLContext) Args.notNull(sSLContext, "SSL context")).getSocketFactory(), strArr, strArr2, x509HostnameVerifier);
    }

    public SSLConnectionSocketFactory(javax.net.ssl.SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier) {
        this(sSLSocketFactory, (String[]) null, (String[]) null, hostnameVerifier);
    }

    @Deprecated
    public SSLConnectionSocketFactory(javax.net.ssl.SSLSocketFactory sSLSocketFactory, X509HostnameVerifier x509HostnameVerifier) {
        this(sSLSocketFactory, (String[]) null, (String[]) null, x509HostnameVerifier);
    }

    public SSLConnectionSocketFactory(javax.net.ssl.SSLSocketFactory sSLSocketFactory, String[] strArr, String[] strArr2, HostnameVerifier hostnameVerifier) {
        this.log = LogFactory.getLog(getClass());
        this.socketfactory = (javax.net.ssl.SSLSocketFactory) Args.notNull(sSLSocketFactory, "SSL socket factory");
        this.supportedProtocols = strArr;
        this.supportedCipherSuites = strArr2;
        this.hostnameVerifier = hostnameVerifier == null ? getDefaultHostnameVerifier() : hostnameVerifier;
    }

    @Deprecated
    public SSLConnectionSocketFactory(javax.net.ssl.SSLSocketFactory sSLSocketFactory, String[] strArr, String[] strArr2, X509HostnameVerifier x509HostnameVerifier) {
        this(sSLSocketFactory, strArr, strArr2, (HostnameVerifier) x509HostnameVerifier);
    }

    public static HostnameVerifier getDefaultHostnameVerifier() {
        return new DefaultHostnameVerifier(PublicSuffixMatcherLoader.getDefault());
    }

    public static SSLConnectionSocketFactory getSocketFactory() {
        return new SSLConnectionSocketFactory(org.apache.http.ssl.SSLContexts.createDefault(), getDefaultHostnameVerifier());
    }

    public static SSLConnectionSocketFactory getSystemSocketFactory() {
        return new SSLConnectionSocketFactory((javax.net.ssl.SSLSocketFactory) javax.net.ssl.SSLSocketFactory.getDefault(), split(System.getProperty("https.protocols")), split(System.getProperty("https.cipherSuites")), getDefaultHostnameVerifier());
    }

    static boolean isWeakCipherSuite(String str) {
        for (Pattern pattern : WEAK_CIPHER_SUITE_PATTERNS) {
            if (pattern.matcher(str).matches()) {
                return true;
            }
        }
        return false;
    }

    private static String[] split(String str) {
        if (TextUtils.isBlank(str)) {
            return null;
        }
        return str.split(" *, *");
    }

    private void verifyHostname(SSLSocket sSLSocket, String str) {
        try {
            SSLSession session = sSLSocket.getSession();
            if (session == null) {
                sSLSocket.getInputStream().available();
                session = sSLSocket.getSession();
                if (session == null) {
                    sSLSocket.startHandshake();
                    session = sSLSocket.getSession();
                }
            }
            if (session == null) {
                throw new SSLHandshakeException("SSL session not available");
            }
            if (this.log.isDebugEnabled()) {
                this.log.debug("Secure session established");
                Log log = this.log;
                log.debug(" negotiated protocol: " + session.getProtocol());
                Log log2 = this.log;
                log2.debug(" negotiated cipher suite: " + session.getCipherSuite());
                try {
                    X509Certificate x509Certificate = (X509Certificate) session.getPeerCertificates()[0];
                    X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
                    Log log3 = this.log;
                    log3.debug(" peer principal: " + subjectX500Principal.toString());
                    Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
                    if (subjectAlternativeNames != null) {
                        ArrayList arrayList = new ArrayList();
                        for (List<?> list : subjectAlternativeNames) {
                            if (!list.isEmpty()) {
                                arrayList.add((String) list.get(1));
                            }
                        }
                        Log log4 = this.log;
                        log4.debug(" peer alternative names: " + arrayList);
                    }
                    X500Principal issuerX500Principal = x509Certificate.getIssuerX500Principal();
                    Log log5 = this.log;
                    log5.debug(" issuer principal: " + issuerX500Principal.toString());
                    Collection<List<?>> issuerAlternativeNames = x509Certificate.getIssuerAlternativeNames();
                    if (issuerAlternativeNames != null) {
                        ArrayList arrayList2 = new ArrayList();
                        for (List<?> list2 : issuerAlternativeNames) {
                            if (!list2.isEmpty()) {
                                arrayList2.add((String) list2.get(1));
                            }
                        }
                        Log log6 = this.log;
                        log6.debug(" issuer alternative names: " + arrayList2);
                    }
                } catch (Exception unused) {
                }
            }
            if (this.hostnameVerifier.verify(str, session)) {
                return;
            }
            List<SubjectName> subjectAltNames = DefaultHostnameVerifier.getSubjectAltNames((X509Certificate) session.getPeerCertificates()[0]);
            throw new SSLPeerUnverifiedException("Certificate for <" + str + "> doesn't match any of the subject alternative names: " + subjectAltNames);
        } catch (IOException e2) {
            try {
                sSLSocket.close();
            } catch (Exception unused2) {
            }
            throw e2;
        }
    }

    @Override // org.apache.http.conn.socket.ConnectionSocketFactory
    public Socket connectSocket(int i2, Socket socket, HttpHost httpHost, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, HttpContext httpContext) {
        Args.notNull(httpHost, "HTTP host");
        Args.notNull(inetSocketAddress, "Remote address");
        if (socket == null) {
            socket = createSocket(httpContext);
        }
        if (inetSocketAddress2 != null) {
            socket.bind(inetSocketAddress2);
        }
        if (i2 > 0) {
            try {
                if (socket.getSoTimeout() == 0) {
                    socket.setSoTimeout(i2);
                }
            } catch (IOException e2) {
                try {
                    socket.close();
                } catch (IOException unused) {
                }
                throw e2;
            }
        }
        if (this.log.isDebugEnabled()) {
            Log log = this.log;
            log.debug("Connecting socket to " + inetSocketAddress + " with timeout " + i2);
        }
        socket.connect(inetSocketAddress, i2);
        if (socket instanceof SSLSocket) {
            SSLSocket sSLSocket = (SSLSocket) socket;
            this.log.debug("Starting handshake");
            sSLSocket.startHandshake();
            verifyHostname(sSLSocket, httpHost.getHostName());
            return socket;
        }
        return createLayeredSocket(socket, httpHost.getHostName(), inetSocketAddress.getPort(), httpContext);
    }

    @Override // org.apache.http.conn.socket.LayeredConnectionSocketFactory
    public Socket createLayeredSocket(Socket socket, String str, int i2, HttpContext httpContext) {
        SSLSocket sSLSocket = (SSLSocket) this.socketfactory.createSocket(socket, str, i2, true);
        String[] strArr = this.supportedProtocols;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        } else {
            String[] enabledProtocols = sSLSocket.getEnabledProtocols();
            ArrayList arrayList = new ArrayList(enabledProtocols.length);
            for (String str2 : enabledProtocols) {
                if (!str2.startsWith("SSL")) {
                    arrayList.add(str2);
                }
            }
            if (!arrayList.isEmpty()) {
                sSLSocket.setEnabledProtocols((String[]) arrayList.toArray(new String[arrayList.size()]));
            }
        }
        String[] strArr2 = this.supportedCipherSuites;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        } else {
            String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
            ArrayList arrayList2 = new ArrayList(enabledCipherSuites.length);
            for (String str3 : enabledCipherSuites) {
                if (!isWeakCipherSuite(str3)) {
                    arrayList2.add(str3);
                }
            }
            if (!arrayList2.isEmpty()) {
                sSLSocket.setEnabledCipherSuites((String[]) arrayList2.toArray(new String[arrayList2.size()]));
            }
        }
        if (this.log.isDebugEnabled()) {
            this.log.debug("Enabled protocols: " + Arrays.asList(sSLSocket.getEnabledProtocols()));
            this.log.debug("Enabled cipher suites:" + Arrays.asList(sSLSocket.getEnabledCipherSuites()));
        }
        prepareSocket(sSLSocket);
        this.log.debug("Starting handshake");
        sSLSocket.startHandshake();
        verifyHostname(sSLSocket, str);
        return sSLSocket;
    }

    @Override // org.apache.http.conn.socket.ConnectionSocketFactory
    public Socket createSocket(HttpContext httpContext) {
        return SocketFactory.getDefault().createSocket();
    }

    protected void prepareSocket(SSLSocket sSLSocket) {
    }
}
