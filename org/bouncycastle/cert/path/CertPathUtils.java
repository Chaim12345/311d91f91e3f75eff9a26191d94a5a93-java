package org.bouncycastle.cert.path;

import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.cert.X509CertificateHolder;
/* loaded from: classes3.dex */
class CertPathUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set a(X509CertificateHolder[] x509CertificateHolderArr) {
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 != x509CertificateHolderArr.length; i2++) {
            hashSet.addAll(x509CertificateHolderArr[i2].getCriticalExtensionOIDs());
        }
        return hashSet;
    }
}
