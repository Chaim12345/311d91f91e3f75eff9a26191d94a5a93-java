package org.bouncycastle.jsse.provider;

import java.lang.reflect.Method;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSSLEngine;
import org.bouncycastle.jsse.BCSSLParameters;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class SSLEngineUtil {
    private static final Method getHandshakeSession;
    private static final Method getSSLParameters;
    private static final boolean useEngine8;

    static {
        Method[] e2 = ReflectionUtil.e("javax.net.ssl.SSLEngine");
        getHandshakeSession = ReflectionUtil.a(e2, "getHandshakeSession");
        getSSLParameters = ReflectionUtil.a(e2, "getSSLParameters");
        useEngine8 = ReflectionUtil.h(e2, "getApplicationProtocol");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SSLEngine a(ContextData contextData) {
        return useEngine8 ? new ProvSSLEngine_8(contextData) : new ProvSSLEngine(contextData);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SSLEngine b(ContextData contextData, String str, int i2) {
        return useEngine8 ? new ProvSSLEngine_8(contextData, str, i2) : new ProvSSLEngine(contextData, str, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCExtendedSSLSession c(SSLEngine sSLEngine) {
        Method method;
        SSLSession sSLSession;
        if (sSLEngine instanceof BCSSLEngine) {
            return ((BCSSLEngine) sSLEngine).getBCHandshakeSession();
        }
        if (sSLEngine == null || (method = getHandshakeSession) == null || (sSLSession = (SSLSession) ReflectionUtil.i(sSLEngine, method)) == null) {
            return null;
        }
        return SSLSessionUtil.b(sSLSession);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCSSLParameters d(SSLEngine sSLEngine) {
        Method method;
        if (sSLEngine instanceof BCSSLEngine) {
            return ((BCSSLEngine) sSLEngine).getParameters();
        }
        if (sSLEngine == null || (method = getSSLParameters) == null) {
            return null;
        }
        SSLParameters sSLParameters = (SSLParameters) ReflectionUtil.i(sSLEngine, method);
        if (sSLParameters != null) {
            return SSLParametersUtil.c(sSLParameters);
        }
        throw new RuntimeException("SSLEngine.getSSLParameters returned null");
    }
}
