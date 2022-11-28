package org.bouncycastle.jsse.provider;

import java.security.cert.CertPathBuilder;
import java.security.cert.PKIXBuilderParameters;
import java.util.Map;
/* loaded from: classes3.dex */
abstract class PKIXUtil {
    private static final Class<?> pkixRevocationCheckerClass;

    static {
        Class<?> cls;
        try {
            cls = ReflectionUtil.b("java.security.cert.PKIXRevocationChecker");
        } catch (Exception unused) {
            cls = null;
        }
        pkixRevocationCheckerClass = cls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(CertPathBuilder certPathBuilder, PKIXBuilderParameters pKIXBuilderParameters, Map map) {
        if (pkixRevocationCheckerClass != null) {
            JsseUtils_8.j0(certPathBuilder, pKIXBuilderParameters, map);
        }
    }
}
