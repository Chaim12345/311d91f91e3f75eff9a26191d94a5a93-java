package org.bouncycastle.mime.smime;

import com.google.common.base.Ascii;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
class SMimeUtils {
    private static final Map RFC3851_MICALGS;
    private static final Map RFC5751_MICALGS;
    private static final Map STANDARD_MICALGS;
    private static final Map forMic;
    private static final byte[] nl;

    static {
        nl = r0;
        byte[] bArr = {Ascii.CR, 10};
        HashMap hashMap = new HashMap();
        ASN1ObjectIdentifier aSN1ObjectIdentifier = CMSAlgorithm.MD5;
        hashMap.put(aSN1ObjectIdentifier, "md5");
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = CMSAlgorithm.SHA1;
        hashMap.put(aSN1ObjectIdentifier2, "sha-1");
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = CMSAlgorithm.SHA224;
        hashMap.put(aSN1ObjectIdentifier3, "sha-224");
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = CMSAlgorithm.SHA256;
        hashMap.put(aSN1ObjectIdentifier4, "sha-256");
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = CMSAlgorithm.SHA384;
        hashMap.put(aSN1ObjectIdentifier5, "sha-384");
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = CMSAlgorithm.SHA512;
        hashMap.put(aSN1ObjectIdentifier6, "sha-512");
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = CMSAlgorithm.GOST3411;
        hashMap.put(aSN1ObjectIdentifier7, "gostr3411-94");
        ASN1ObjectIdentifier aSN1ObjectIdentifier8 = CMSAlgorithm.GOST3411_2012_256;
        hashMap.put(aSN1ObjectIdentifier8, "gostr3411-2012-256");
        ASN1ObjectIdentifier aSN1ObjectIdentifier9 = CMSAlgorithm.GOST3411_2012_512;
        hashMap.put(aSN1ObjectIdentifier9, "gostr3411-2012-512");
        Map unmodifiableMap = Collections.unmodifiableMap(hashMap);
        RFC5751_MICALGS = unmodifiableMap;
        HashMap hashMap2 = new HashMap();
        hashMap2.put(aSN1ObjectIdentifier, "md5");
        hashMap2.put(aSN1ObjectIdentifier2, "sha1");
        hashMap2.put(aSN1ObjectIdentifier3, "sha224");
        hashMap2.put(aSN1ObjectIdentifier4, "sha256");
        hashMap2.put(aSN1ObjectIdentifier5, "sha384");
        hashMap2.put(aSN1ObjectIdentifier6, "sha512");
        hashMap2.put(aSN1ObjectIdentifier7, "gostr3411-94");
        hashMap2.put(aSN1ObjectIdentifier8, "gostr3411-2012-256");
        hashMap2.put(aSN1ObjectIdentifier9, "gostr3411-2012-512");
        RFC3851_MICALGS = Collections.unmodifiableMap(hashMap2);
        STANDARD_MICALGS = unmodifiableMap;
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Object obj : unmodifiableMap.keySet()) {
            treeMap.put(STANDARD_MICALGS.get(obj).toString(), (ASN1ObjectIdentifier) obj);
        }
        for (Object obj2 : RFC3851_MICALGS.keySet()) {
            treeMap.put(RFC3851_MICALGS.get(obj2).toString(), (ASN1ObjectIdentifier) obj2);
        }
        forMic = Collections.unmodifiableMap(treeMap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static InputStream a(InputStream inputStream) {
        return inputStream instanceof FileInputStream ? new BufferedInputStream(inputStream) : inputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OutputStream b(OutputStream outputStream) {
        return outputStream instanceof FileOutputStream ? new BufferedOutputStream(outputStream) : outputStream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static OutputStream c(OutputStream outputStream) {
        return new FilterOutputStream(outputStream) { // from class: org.bouncycastle.mime.smime.SMimeUtils.1
            @Override // java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i2, int i3) {
                Objects.requireNonNull(bArr);
                int i4 = i3 + i2;
                if ((i2 | i3 | (bArr.length - i4) | i4) < 0) {
                    throw new IndexOutOfBoundsException();
                }
                ((FilterOutputStream) this).out.write(bArr, i2, i3);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1ObjectIdentifier d(String str) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) forMic.get(Strings.toLowerCase(str));
        if (aSN1ObjectIdentifier != null) {
            return aSN1ObjectIdentifier;
        }
        throw new IllegalArgumentException("unknown micalg passed: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String e(String str) {
        return (str == null || str.length() <= 1 || str.charAt(0) != '\"' || str.charAt(str.length() - 1) != '\"') ? str : str.substring(1, str.length() - 1);
    }
}
