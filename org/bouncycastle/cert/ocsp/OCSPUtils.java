package org.bouncycastle.cert.ocsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bouncycastle.asn1.ASN1GeneralizedTime;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.cert.X509CertificateHolder;
/* loaded from: classes3.dex */
class OCSPUtils {

    /* renamed from: a  reason: collision with root package name */
    static final X509CertificateHolder[] f13093a = new X509CertificateHolder[0];

    /* renamed from: b  reason: collision with root package name */
    static Set f13094b = Collections.unmodifiableSet(new HashSet());

    /* renamed from: c  reason: collision with root package name */
    static List f13095c = Collections.unmodifiableList(new ArrayList());

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Date a(ASN1GeneralizedTime aSN1GeneralizedTime) {
        try {
            return aSN1GeneralizedTime.getDate();
        } catch (Exception e2) {
            throw new IllegalStateException("exception processing GeneralizedTime: " + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set b(Extensions extensions) {
        return extensions == null ? f13094b : Collections.unmodifiableSet(new HashSet(Arrays.asList(extensions.getCriticalExtensionOIDs())));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List c(Extensions extensions) {
        return extensions == null ? f13095c : Collections.unmodifiableList(Arrays.asList(extensions.getExtensionOIDs()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set d(Extensions extensions) {
        return extensions == null ? f13094b : Collections.unmodifiableSet(new HashSet(Arrays.asList(extensions.getNonCriticalExtensionOIDs())));
    }
}
