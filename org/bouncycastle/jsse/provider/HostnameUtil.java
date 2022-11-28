package org.bouncycastle.jsse.provider;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.security.auth.x500.X500Principal;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.x500.AttributeTypeAndValue;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.util.IPAddress;
import org.slf4j.Marker;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class HostnameUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(String str, X509Certificate x509Certificate, boolean z) {
        if (str == null) {
            throw new CertificateException("No hostname specified for HTTPS endpoint ID check");
        }
        if (IPAddress.isValid(str)) {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames != null) {
                for (List<?> list : subjectAlternativeNames) {
                    if (7 == ((Integer) list.get(0)).intValue()) {
                        String str2 = (String) list.get(1);
                        if (str.equalsIgnoreCase(str2)) {
                            return;
                        }
                        try {
                            if (InetAddress.getByName(str).equals(InetAddress.getByName(str2))) {
                                return;
                            }
                        } catch (SecurityException | UnknownHostException unused) {
                            continue;
                        }
                    }
                }
            }
            throw new CertificateException("No subject alternative name found matching IP address " + str);
        } else if (!isValidDomainName(str)) {
            throw new CertificateException("Invalid hostname specified for HTTPS endpoint ID check");
        } else {
            Collection<List<?>> subjectAlternativeNames2 = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames2 != null) {
                boolean z2 = false;
                for (List<?> list2 : subjectAlternativeNames2) {
                    if (2 == ((Integer) list2.get(0)).intValue()) {
                        if (matchesDNSName(str, (String) list2.get(1), z)) {
                            return;
                        }
                        z2 = true;
                    }
                }
                if (z2) {
                    throw new CertificateException("No subject alternative name found matching domain name " + str);
                }
            }
            ASN1Primitive findMostSpecificCN = findMostSpecificCN(x509Certificate.getSubjectX500Principal());
            if ((findMostSpecificCN instanceof ASN1String) && matchesDNSName(str, ((ASN1String) findMostSpecificCN).getString(), z)) {
                return;
            }
            throw new CertificateException("No name found matching " + str);
        }
    }

    private static ASN1Primitive findMostSpecificCN(X500Principal x500Principal) {
        AttributeTypeAndValue[] typesAndValues;
        if (x500Principal != null) {
            RDN[] rDNs = X500Name.getInstance(x500Principal.getEncoded()).getRDNs();
            for (int length = rDNs.length - 1; length >= 0; length--) {
                for (AttributeTypeAndValue attributeTypeAndValue : rDNs[length].getTypesAndValues()) {
                    if (BCStyle.CN.equals((ASN1Primitive) attributeTypeAndValue.getType())) {
                        return attributeTypeAndValue.getValue().toASN1Primitive();
                    }
                }
            }
            return null;
        }
        return null;
    }

    private static String getLabel(String str, int i2) {
        int indexOf = str.indexOf(46, i2);
        if (indexOf < 0) {
            indexOf = str.length();
        }
        return str.substring(i2, indexOf);
    }

    private static boolean isValidDomainName(String str) {
        try {
            new BCSNIHostName(str);
            return true;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    private static boolean labelMatchesPattern(String str, String str2) {
        int indexOf = str2.indexOf(42);
        if (indexOf < 0) {
            return str.equals(str2);
        }
        int i2 = 0;
        int i3 = 0;
        do {
            String substring = str2.substring(i2, indexOf);
            int indexOf2 = str.indexOf(substring, i3);
            if (indexOf2 < 0 || (i2 == 0 && indexOf2 > 0)) {
                return false;
            }
            i3 = indexOf2 + substring.length();
            i2 = indexOf + 1;
            indexOf = str2.indexOf(42, i2);
        } while (indexOf >= 0);
        return str.substring(i3).endsWith(str2.substring(i2));
    }

    private static boolean matchesDNSName(String str, String str2, boolean z) {
        try {
            String unicode = IDNUtil.toUnicode(IDNUtil.toASCII(str, 0), 0);
            String unicode2 = IDNUtil.toUnicode(IDNUtil.toASCII(str2, 0), 0);
            if (validateWildcards(unicode2) && isValidDomainName(unicode2.replace('*', 'z'))) {
                Locale locale = Locale.ENGLISH;
                String lowerCase = unicode.toLowerCase(locale);
                String lowerCase2 = unicode2.toLowerCase(locale);
                return z ? matchesWildcardsAllLabels(lowerCase, lowerCase2) : matchesWildcardsFirstLabel(lowerCase, lowerCase2);
            }
            return false;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    private static boolean matchesWildcardsAllLabels(String str, String str2) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
        StringTokenizer stringTokenizer2 = new StringTokenizer(str2, ".");
        if (stringTokenizer.countTokens() != stringTokenizer2.countTokens()) {
            return false;
        }
        while (stringTokenizer.hasMoreTokens()) {
            if (!labelMatchesPattern(stringTokenizer.nextToken(), stringTokenizer2.nextToken())) {
                return false;
            }
        }
        return true;
    }

    private static boolean matchesWildcardsFirstLabel(String str, String str2) {
        String label = getLabel(str, 0);
        String label2 = getLabel(str2, 0);
        if (labelMatchesPattern(label, label2)) {
            return str.substring(label.length()).equals(str2.substring(label2.length()));
        }
        return false;
    }

    private static boolean validateWildcards(String str) {
        int lastIndexOf = str.lastIndexOf(42);
        return lastIndexOf < 0 || !(str.equals(Marker.ANY_MARKER) || str.equals("*.") || str.indexOf(46, lastIndexOf + 1) < 0);
    }
}
