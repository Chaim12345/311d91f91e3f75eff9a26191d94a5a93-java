package org.bouncycastle.jsse.provider;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class SSLSocketUtil {
    private static final Method getHandshakeSession;
    private static final Method getSSLParameters;
    private static AtomicInteger threadNumber = new AtomicInteger();
    private static final boolean useSocket8;

    static {
        Method[] e2 = ReflectionUtil.e("javax.net.ssl.SSLSocket");
        getHandshakeSession = ReflectionUtil.a(e2, "getHandshakeSession");
        getSSLParameters = ReflectionUtil.a(e2, "getSSLParameters");
        useSocket8 = ReflectionUtil.h(e2, "getApplicationProtocol");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect a(ContextData contextData) {
        return useSocket8 ? new ProvSSLSocketDirect_8(contextData) : new ProvSSLSocketDirect(contextData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect b(ContextData contextData, String str, int i2) {
        return useSocket8 ? new ProvSSLSocketDirect_8(contextData, str, i2) : new ProvSSLSocketDirect(contextData, str, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect c(ContextData contextData, String str, int i2, InetAddress inetAddress, int i3) {
        return useSocket8 ? new ProvSSLSocketDirect_8(contextData, str, i2, inetAddress, i3) : new ProvSSLSocketDirect(contextData, str, i2, inetAddress, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect d(ContextData contextData, InetAddress inetAddress, int i2) {
        return useSocket8 ? new ProvSSLSocketDirect_8(contextData, inetAddress, i2) : new ProvSSLSocketDirect(contextData, inetAddress, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect e(ContextData contextData, InetAddress inetAddress, int i2, InetAddress inetAddress2, int i3) {
        return useSocket8 ? new ProvSSLSocketDirect_8(contextData, inetAddress, i2, inetAddress2, i3) : new ProvSSLSocketDirect(contextData, inetAddress, i2, inetAddress2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketDirect f(ContextData contextData, boolean z, boolean z2, ProvSSLParameters provSSLParameters) {
        return useSocket8 ? new ProvSSLSocketDirect_8(contextData, z, z2, provSSLParameters) : new ProvSSLSocketDirect(contextData, z, z2, provSSLParameters);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketWrap g(ContextData contextData, Socket socket, InputStream inputStream, boolean z) {
        return useSocket8 ? new ProvSSLSocketWrap_8(contextData, socket, inputStream, z) : new ProvSSLSocketWrap(contextData, socket, inputStream, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProvSSLSocketWrap h(ContextData contextData, Socket socket, String str, int i2, boolean z) {
        return useSocket8 ? new ProvSSLSocketWrap_8(contextData, socket, str, i2, z) : new ProvSSLSocketWrap(contextData, socket, str, i2, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void i(Runnable runnable) {
        new Thread(runnable, "BCJSSE-HandshakeCompleted-" + (threadNumber.getAndIncrement() & Integer.MAX_VALUE)).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCExtendedSSLSession j(SSLSocket sSLSocket) {
        Method method;
        SSLSession sSLSession;
        if (sSLSocket instanceof BCSSLSocket) {
            return ((BCSSLSocket) sSLSocket).getBCHandshakeSession();
        }
        if (sSLSocket == null || (method = getHandshakeSession) == null || (sSLSession = (SSLSession) ReflectionUtil.i(sSLSocket, method)) == null) {
            return null;
        }
        return SSLSessionUtil.b(sSLSession);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCSSLParameters k(SSLSocket sSLSocket) {
        Method method;
        if (sSLSocket instanceof BCSSLSocket) {
            return ((BCSSLSocket) sSLSocket).getParameters();
        }
        if (sSLSocket == null || (method = getSSLParameters) == null) {
            return null;
        }
        SSLParameters sSLParameters = (SSLParameters) ReflectionUtil.i(sSLSocket, method);
        if (sSLParameters != null) {
            return SSLParametersUtil.c(sSLParameters);
        }
        throw new RuntimeException("SSLSocket.getSSLParameters returned null");
    }
}
