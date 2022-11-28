package org.bouncycastle.jsse.provider;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLParameters;
import org.bouncycastle.jsse.BCSNIMatcher;
import org.bouncycastle.jsse.BCSNIServerName;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.java.security.BCAlgorithmConstraints;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class SSLParametersUtil {
    private static final Method getAlgorithmConstraints;
    private static final Method getApplicationProtocols;
    private static final Method getEndpointIdentificationAlgorithm;
    private static final Method getSNIMatchers;
    private static final Method getServerNames;
    private static final Method getUseCipherSuitesOrder;
    private static final Method setAlgorithmConstraints;
    private static final Method setApplicationProtocols;
    private static final Method setEndpointIdentificationAlgorithm;
    private static final Method setSNIMatchers;
    private static final Method setServerNames;
    private static final Method setUseCipherSuitesOrder;

    static {
        Method[] e2 = ReflectionUtil.e("javax.net.ssl.SSLParameters");
        getAlgorithmConstraints = ReflectionUtil.a(e2, "getAlgorithmConstraints");
        setAlgorithmConstraints = ReflectionUtil.a(e2, "setAlgorithmConstraints");
        getApplicationProtocols = ReflectionUtil.a(e2, "getApplicationProtocols");
        setApplicationProtocols = ReflectionUtil.a(e2, "setApplicationProtocols");
        getEndpointIdentificationAlgorithm = ReflectionUtil.a(e2, "getEndpointIdentificationAlgorithm");
        setEndpointIdentificationAlgorithm = ReflectionUtil.a(e2, "setEndpointIdentificationAlgorithm");
        getServerNames = ReflectionUtil.a(e2, "getServerNames");
        setServerNames = ReflectionUtil.a(e2, "setServerNames");
        getSNIMatchers = ReflectionUtil.a(e2, "getSNIMatchers");
        setSNIMatchers = ReflectionUtil.a(e2, "setSNIMatchers");
        getUseCipherSuitesOrder = ReflectionUtil.a(e2, "getUseCipherSuitesOrder");
        setUseCipherSuitesOrder = ReflectionUtil.a(e2, "setUseCipherSuitesOrder");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCSSLParameters a(ProvSSLParameters provSSLParameters) {
        BCSSLParameters bCSSLParameters = new BCSSLParameters(provSSLParameters.getCipherSuites(), provSSLParameters.getProtocols());
        if (provSSLParameters.getNeedClientAuth()) {
            bCSSLParameters.setNeedClientAuth(true);
        } else if (provSSLParameters.getWantClientAuth()) {
            bCSSLParameters.setWantClientAuth(true);
        } else {
            bCSSLParameters.setWantClientAuth(false);
        }
        bCSSLParameters.setAlgorithmConstraints(provSSLParameters.getAlgorithmConstraints());
        bCSSLParameters.setEndpointIdentificationAlgorithm(provSSLParameters.getEndpointIdentificationAlgorithm());
        bCSSLParameters.setUseCipherSuitesOrder(provSSLParameters.getUseCipherSuitesOrder());
        bCSSLParameters.setServerNames(provSSLParameters.getServerNames());
        bCSSLParameters.setSNIMatchers(provSSLParameters.getSNIMatchers());
        bCSSLParameters.setApplicationProtocols(provSSLParameters.getApplicationProtocols());
        return bCSSLParameters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SSLParameters b(ProvSSLParameters provSSLParameters) {
        String[] applicationProtocols;
        Collection<BCSNIMatcher> sNIMatchers;
        List<BCSNIServerName> serverNames;
        SSLParameters sSLParameters = new SSLParameters(provSSLParameters.getCipherSuites(), provSSLParameters.getProtocols());
        if (provSSLParameters.getNeedClientAuth()) {
            sSLParameters.setNeedClientAuth(true);
        } else if (provSSLParameters.getWantClientAuth()) {
            sSLParameters.setWantClientAuth(true);
        } else {
            sSLParameters.setWantClientAuth(false);
        }
        Method method = setAlgorithmConstraints;
        if (method != null) {
            set(sSLParameters, method, JsseUtils_7.c0(provSSLParameters.getAlgorithmConstraints()));
        }
        Method method2 = setEndpointIdentificationAlgorithm;
        if (method2 != null) {
            set(sSLParameters, method2, provSSLParameters.getEndpointIdentificationAlgorithm());
        }
        Method method3 = setUseCipherSuitesOrder;
        if (method3 != null) {
            set(sSLParameters, method3, Boolean.valueOf(provSSLParameters.getUseCipherSuitesOrder()));
        }
        Method method4 = setServerNames;
        if (method4 != null && (serverNames = provSSLParameters.getServerNames()) != null) {
            set(sSLParameters, method4, JsseUtils_8.q0(serverNames));
        }
        Method method5 = setSNIMatchers;
        if (method5 != null && (sNIMatchers = provSSLParameters.getSNIMatchers()) != null) {
            set(sSLParameters, method5, JsseUtils_8.n0(sNIMatchers));
        }
        Method method6 = setApplicationProtocols;
        if (method6 != null && (applicationProtocols = provSSLParameters.getApplicationProtocols()) != null) {
            set(sSLParameters, method6, applicationProtocols);
        }
        return sSLParameters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCSSLParameters c(SSLParameters sSLParameters) {
        String[] strArr;
        Object obj;
        Object obj2;
        String str;
        Object obj3;
        BCSSLParameters bCSSLParameters = new BCSSLParameters(sSLParameters.getCipherSuites(), sSLParameters.getProtocols());
        if (sSLParameters.getNeedClientAuth()) {
            bCSSLParameters.setNeedClientAuth(true);
        } else if (sSLParameters.getWantClientAuth()) {
            bCSSLParameters.setWantClientAuth(true);
        } else {
            bCSSLParameters.setWantClientAuth(false);
        }
        Method method = getAlgorithmConstraints;
        if (method != null && (obj3 = get(sSLParameters, method)) != null) {
            bCSSLParameters.setAlgorithmConstraints(JsseUtils_7.g0(obj3));
        }
        Method method2 = getEndpointIdentificationAlgorithm;
        if (method2 != null && (str = (String) get(sSLParameters, method2)) != null) {
            bCSSLParameters.setEndpointIdentificationAlgorithm(str);
        }
        Method method3 = getUseCipherSuitesOrder;
        if (method3 != null) {
            bCSSLParameters.setUseCipherSuitesOrder(((Boolean) get(sSLParameters, method3)).booleanValue());
        }
        Method method4 = getServerNames;
        if (method4 != null && (obj2 = get(sSLParameters, method4)) != null) {
            bCSSLParameters.setServerNames(JsseUtils_8.y0(obj2));
        }
        Method method5 = getSNIMatchers;
        if (method5 != null && (obj = get(sSLParameters, method5)) != null) {
            bCSSLParameters.setSNIMatchers(JsseUtils_8.v0(obj));
        }
        Method method6 = getApplicationProtocols;
        if (method6 != null && (strArr = (String[]) get(sSLParameters, method6)) != null) {
            bCSSLParameters.setApplicationProtocols(strArr);
        }
        return bCSSLParameters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void d(ProvSSLParameters provSSLParameters, BCSSLParameters bCSSLParameters) {
        String[] cipherSuites = bCSSLParameters.getCipherSuites();
        if (cipherSuites != null) {
            provSSLParameters.setCipherSuites(cipherSuites);
        }
        String[] protocols = bCSSLParameters.getProtocols();
        if (protocols != null) {
            provSSLParameters.setProtocols(protocols);
        }
        if (bCSSLParameters.getNeedClientAuth()) {
            provSSLParameters.setNeedClientAuth(true);
        } else if (bCSSLParameters.getWantClientAuth()) {
            provSSLParameters.setWantClientAuth(true);
        } else {
            provSSLParameters.setWantClientAuth(false);
        }
        BCAlgorithmConstraints algorithmConstraints = bCSSLParameters.getAlgorithmConstraints();
        if (algorithmConstraints != null) {
            provSSLParameters.setAlgorithmConstraints(algorithmConstraints);
        }
        String endpointIdentificationAlgorithm = bCSSLParameters.getEndpointIdentificationAlgorithm();
        if (endpointIdentificationAlgorithm != null) {
            provSSLParameters.setEndpointIdentificationAlgorithm(endpointIdentificationAlgorithm);
        }
        provSSLParameters.setUseCipherSuitesOrder(bCSSLParameters.getUseCipherSuitesOrder());
        List<BCSNIServerName> serverNames = bCSSLParameters.getServerNames();
        if (serverNames != null) {
            provSSLParameters.setServerNames(serverNames);
        }
        Collection<BCSNIMatcher> sNIMatchers = bCSSLParameters.getSNIMatchers();
        if (sNIMatchers != null) {
            provSSLParameters.setSNIMatchers(sNIMatchers);
        }
        String[] applicationProtocols = bCSSLParameters.getApplicationProtocols();
        if (applicationProtocols != null) {
            provSSLParameters.setApplicationProtocols(applicationProtocols);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void e(ProvSSLParameters provSSLParameters, SSLParameters sSLParameters) {
        String[] strArr;
        Object obj;
        Object obj2;
        String str;
        Object obj3;
        String[] cipherSuites = sSLParameters.getCipherSuites();
        if (cipherSuites != null) {
            provSSLParameters.setCipherSuites(cipherSuites);
        }
        String[] protocols = sSLParameters.getProtocols();
        if (protocols != null) {
            provSSLParameters.setProtocols(protocols);
        }
        if (sSLParameters.getNeedClientAuth()) {
            provSSLParameters.setNeedClientAuth(true);
        } else if (sSLParameters.getWantClientAuth()) {
            provSSLParameters.setWantClientAuth(true);
        } else {
            provSSLParameters.setWantClientAuth(false);
        }
        Method method = getAlgorithmConstraints;
        if (method != null && (obj3 = get(sSLParameters, method)) != null) {
            provSSLParameters.setAlgorithmConstraints(JsseUtils_7.g0(obj3));
        }
        Method method2 = getEndpointIdentificationAlgorithm;
        if (method2 != null && (str = (String) get(sSLParameters, method2)) != null) {
            provSSLParameters.setEndpointIdentificationAlgorithm(str);
        }
        Method method3 = getUseCipherSuitesOrder;
        if (method3 != null) {
            provSSLParameters.setUseCipherSuitesOrder(((Boolean) get(sSLParameters, method3)).booleanValue());
        }
        Method method4 = getServerNames;
        if (method4 != null && (obj2 = get(sSLParameters, method4)) != null) {
            provSSLParameters.setServerNames(JsseUtils_8.y0(obj2));
        }
        Method method5 = getSNIMatchers;
        if (method5 != null && (obj = get(sSLParameters, method5)) != null) {
            provSSLParameters.setSNIMatchers(JsseUtils_8.v0(obj));
        }
        Method method6 = getApplicationProtocols;
        if (method6 == null || (strArr = (String[]) get(sSLParameters, method6)) == null) {
            return;
        }
        provSSLParameters.setApplicationProtocols(strArr);
    }

    private static Object get(Object obj, Method method) {
        return ReflectionUtil.i(obj, method);
    }

    private static void set(Object obj, Method method, Object obj2) {
        ReflectionUtil.k(obj, method, obj2);
    }
}
