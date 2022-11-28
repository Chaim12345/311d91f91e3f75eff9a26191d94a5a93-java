package com.appmattus.certificatetransparency.chaincleaner;

import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class BasicCertificateChainCleaner implements CertificateChainCleaner {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final int MAX_SIGNERS = 9;
    @NotNull
    private final Map<X500Principal, List<X509Certificate>> subjectToCaCerts;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BasicCertificateChainCleaner(@NotNull X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        X509Certificate[] acceptedIssuers = trustManager.getAcceptedIssuers();
        Intrinsics.checkNotNullExpressionValue(acceptedIssuers, "trustManager.acceptedIssuers");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (X509Certificate x509Certificate : acceptedIssuers) {
            X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            Object obj = linkedHashMap.get(subjectX500Principal);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(subjectX500Principal, obj);
            }
            ((List) obj).add(x509Certificate);
        }
        this.subjectToCaCerts = linkedHashMap;
    }

    private final X509Certificate findTrustedCertByIssuerAndSignature(X509Certificate x509Certificate) {
        List<X509Certificate> list = this.subjectToCaCerts.get(x509Certificate.getIssuerX500Principal());
        Object obj = null;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                X509Certificate it2 = (X509Certificate) next;
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                if (isSignedBy(x509Certificate, it2)) {
                    obj = next;
                    break;
                }
            }
            return (X509Certificate) obj;
        }
        return null;
    }

    private final boolean isSignedBy(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        if (Intrinsics.areEqual(x509Certificate.getIssuerX500Principal(), x509Certificate2.getSubjectX500Principal())) {
            try {
                x509Certificate.verify(x509Certificate2.getPublicKey());
                return true;
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    @Override // com.appmattus.certificatetransparency.chaincleaner.CertificateChainCleaner
    @NotNull
    public List<X509Certificate> clean(@NotNull List<? extends X509Certificate> chain, @NotNull String hostname) {
        Object last;
        Object obj;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        if (chain.isEmpty()) {
            throw new SSLPeerUnverifiedException("Certificate chain is empty");
        }
        ArrayDeque arrayDeque = new ArrayDeque(chain);
        ArrayList arrayList = new ArrayList();
        Object removeFirst = arrayDeque.removeFirst();
        Intrinsics.checkNotNullExpressionValue(removeFirst, "queue.removeFirst()");
        arrayList.add(removeFirst);
        boolean z = false;
        for (int i2 = 0; i2 < 9; i2++) {
            last = CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList);
            X509Certificate x509Certificate = (X509Certificate) last;
            X509Certificate findTrustedCertByIssuerAndSignature = findTrustedCertByIssuerAndSignature(x509Certificate);
            if (findTrustedCertByIssuerAndSignature != null) {
                if (arrayList.size() > 1 || !Intrinsics.areEqual(x509Certificate, findTrustedCertByIssuerAndSignature)) {
                    arrayList.add(findTrustedCertByIssuerAndSignature);
                }
                if (isSignedBy(findTrustedCertByIssuerAndSignature, findTrustedCertByIssuerAndSignature)) {
                    return arrayList;
                }
                z = true;
            } else {
                Iterator it = arrayDeque.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    X509Certificate signingCert = (X509Certificate) obj;
                    Intrinsics.checkNotNullExpressionValue(signingCert, "signingCert");
                    if (isSignedBy(x509Certificate, signingCert)) {
                        break;
                    }
                }
                X509Certificate x509Certificate2 = (X509Certificate) obj;
                if (x509Certificate2 == null) {
                    if (z) {
                        return arrayList;
                    }
                    throw new SSLPeerUnverifiedException("Failed to find a trusted cert that signed " + x509Certificate);
                }
                arrayDeque.remove(x509Certificate2);
                arrayList.add(x509Certificate2);
            }
        }
        throw new SSLPeerUnverifiedException("Certificate chain too long: " + arrayList);
    }
}
