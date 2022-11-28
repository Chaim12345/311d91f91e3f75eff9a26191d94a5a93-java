package org.bouncycastle.jce.provider;

import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.jcajce.interfaces.BCX509Certificate;
import org.bouncycastle.x509.X509AttributeCertificate;
/* loaded from: classes3.dex */
class PrincipalUtils {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Name a(TrustAnchor trustAnchor) {
        return f(notNull(trustAnchor).getCA());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Name b(Object obj) {
        return obj instanceof X509Certificate ? d((X509Certificate) obj) : f((X500Principal) ((X509AttributeCertificate) obj).getIssuer().getPrincipals()[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Name c(X509CRL x509crl) {
        return f(notNull(x509crl).getIssuerX500Principal());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Name d(X509Certificate x509Certificate) {
        return x509Certificate instanceof BCX509Certificate ? notNull(((BCX509Certificate) x509Certificate).getIssuerX500Name()) : f(notNull(x509Certificate).getIssuerX500Principal());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Name e(X509Certificate x509Certificate) {
        return x509Certificate instanceof BCX509Certificate ? notNull(((BCX509Certificate) x509Certificate).getSubjectX500Name()) : f(notNull(x509Certificate).getSubjectX500Principal());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static X500Name f(X500Principal x500Principal) {
        return notNull(X500Name.getInstance(getEncoded(x500Principal)));
    }

    private static byte[] getEncoded(X500Principal x500Principal) {
        return notNull(notNull(x500Principal).getEncoded());
    }

    private static TrustAnchor notNull(TrustAnchor trustAnchor) {
        if (trustAnchor != null) {
            return trustAnchor;
        }
        throw new IllegalStateException();
    }

    private static X509CRL notNull(X509CRL x509crl) {
        if (x509crl != null) {
            return x509crl;
        }
        throw new IllegalStateException();
    }

    private static X509Certificate notNull(X509Certificate x509Certificate) {
        if (x509Certificate != null) {
            return x509Certificate;
        }
        throw new IllegalStateException();
    }

    private static X500Principal notNull(X500Principal x500Principal) {
        if (x500Principal != null) {
            return x500Principal;
        }
        throw new IllegalStateException();
    }

    private static X500Name notNull(X500Name x500Name) {
        if (x500Name != null) {
            return x500Name;
        }
        throw new IllegalStateException();
    }

    private static byte[] notNull(byte[] bArr) {
        if (bArr != null) {
            return bArr;
        }
        throw new IllegalStateException();
    }
}
