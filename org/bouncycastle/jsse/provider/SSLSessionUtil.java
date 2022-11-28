package org.bouncycastle.jsse.provider;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import javax.net.ssl.SSLSession;
import org.bouncycastle.jsse.BCExtendedSSLSession;
/* loaded from: classes3.dex */
abstract class SSLSessionUtil {
    private static final Constructor<? extends SSLSession> exportSSLSessionConstructor;
    private static final Class<?> extendedSSLSessionClass;
    private static final Constructor<? extends BCExtendedSSLSession> importSSLSessionConstructor;

    /* JADX WARN: Removed duplicated region for block: B:30:0x0032 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        Class<?> cls;
        Constructor<? extends SSLSession> constructor;
        Class<?> cls2;
        Method[] e2;
        Constructor<? extends BCExtendedSSLSession> constructor2 = null;
        try {
            cls = ReflectionUtil.b("javax.net.ssl.ExtendedSSLSession");
        } catch (Exception unused) {
            cls = null;
        }
        extendedSSLSessionClass = cls;
        try {
            e2 = ReflectionUtil.e("javax.net.ssl.ExtendedSSLSession");
        } catch (Exception unused2) {
        }
        if (e2 != null) {
            constructor = ReflectionUtil.c(ReflectionUtil.h(e2, "getRequestedServerNames") ? "org.bouncycastle.jsse.provider.ExportSSLSession_8" : "org.bouncycastle.jsse.provider.ExportSSLSession_7", BCExtendedSSLSession.class);
            exportSSLSessionConstructor = constructor;
            cls2 = extendedSSLSessionClass;
            if (cls2 != null) {
                try {
                    Method[] e3 = ReflectionUtil.e("javax.net.ssl.ExtendedSSLSession");
                    if (e3 != null) {
                        constructor2 = ReflectionUtil.c(ReflectionUtil.h(e3, "getRequestedServerNames") ? "org.bouncycastle.jsse.provider.ImportSSLSession_8" : "org.bouncycastle.jsse.provider.ImportSSLSession_7", cls2);
                    }
                } catch (Exception unused3) {
                }
            }
            importSSLSessionConstructor = constructor2;
        }
        constructor = null;
        exportSSLSessionConstructor = constructor;
        cls2 = extendedSSLSessionClass;
        if (cls2 != null) {
        }
        importSSLSessionConstructor = constructor2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SSLSession a(BCExtendedSSLSession bCExtendedSSLSession) {
        if (bCExtendedSSLSession instanceof ImportSSLSession) {
            return ((ImportSSLSession) bCExtendedSSLSession).unwrap();
        }
        Constructor<? extends SSLSession> constructor = exportSSLSessionConstructor;
        if (constructor != null) {
            try {
                return constructor.newInstance(bCExtendedSSLSession);
            } catch (Exception unused) {
            }
        }
        return new ExportSSLSession_5(bCExtendedSSLSession);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCExtendedSSLSession b(SSLSession sSLSession) {
        if (sSLSession instanceof BCExtendedSSLSession) {
            return (BCExtendedSSLSession) sSLSession;
        }
        if (sSLSession instanceof ExportSSLSession) {
            return ((ExportSSLSession) sSLSession).unwrap();
        }
        Constructor<? extends BCExtendedSSLSession> constructor = importSSLSessionConstructor;
        if (constructor != null && extendedSSLSessionClass.isInstance(sSLSession)) {
            try {
                return constructor.newInstance(sSLSession);
            } catch (Exception unused) {
            }
        }
        return new ImportSSLSession_5(sSLSession);
    }
}
