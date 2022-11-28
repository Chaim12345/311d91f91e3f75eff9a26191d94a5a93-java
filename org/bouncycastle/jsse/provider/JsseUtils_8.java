package org.bouncycastle.jsse.provider;

import java.security.cert.CertPathBuilder;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXRevocationChecker;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIMatcher;
import javax.net.ssl.SNIServerName;
import org.bouncycastle.jsse.BCApplicationProtocolSelector;
import org.bouncycastle.jsse.BCSNIHostName;
import org.bouncycastle.jsse.BCSNIMatcher;
import org.bouncycastle.jsse.BCSNIServerName;
import org.bouncycastle.jsse.provider.JsseUtils;
/* loaded from: classes3.dex */
abstract class JsseUtils_8 extends JsseUtils_7 {

    /* loaded from: classes3.dex */
    static class ExportAPSelector<T> implements BiFunction<T, List<String>, String> {
        private final BCApplicationProtocolSelector<T> selector;

        ExportAPSelector(BCApplicationProtocolSelector bCApplicationProtocolSelector) {
            this.selector = bCApplicationProtocolSelector;
        }

        BCApplicationProtocolSelector a() {
            return this.selector;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.function.BiFunction
        public /* bridge */ /* synthetic */ String apply(Object obj, List<String> list) {
            return apply2((ExportAPSelector<T>) obj, list);
        }

        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public String apply2(T t2, List<String> list) {
            return this.selector.select(t2, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ExportSNIMatcher extends SNIMatcher {
        private final BCSNIMatcher matcher;

        ExportSNIMatcher(BCSNIMatcher bCSNIMatcher) {
            super(bCSNIMatcher.getType());
            this.matcher = bCSNIMatcher;
        }

        BCSNIMatcher a() {
            return this.matcher;
        }

        @Override // javax.net.ssl.SNIMatcher
        public boolean matches(SNIServerName sNIServerName) {
            return this.matcher.matches(JsseUtils_8.w0(sNIServerName));
        }
    }

    /* loaded from: classes3.dex */
    static class ImportAPSelector<T> implements BCApplicationProtocolSelector<T> {
        private final BiFunction<T, List<String>, String> selector;

        ImportAPSelector(BiFunction biFunction) {
            this.selector = biFunction;
        }

        BiFunction a() {
            return this.selector;
        }

        @Override // org.bouncycastle.jsse.BCApplicationProtocolSelector
        public String select(T t2, List<String> list) {
            return this.selector.apply(t2, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ImportSNIMatcher extends BCSNIMatcher {
        private final SNIMatcher matcher;

        ImportSNIMatcher(SNIMatcher sNIMatcher) {
            super(sNIMatcher.getType());
            this.matcher = sNIMatcher;
        }

        SNIMatcher a() {
            return this.matcher;
        }

        @Override // org.bouncycastle.jsse.BCSNIMatcher
        public boolean matches(BCSNIServerName bCSNIServerName) {
            return this.matcher.matches(JsseUtils_8.o0(bCSNIServerName));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class UnknownServerName extends SNIServerName {
        UnknownServerName(int i2, byte[] bArr) {
            super(i2, bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void j0(CertPathBuilder certPathBuilder, PKIXBuilderParameters pKIXBuilderParameters, Map map) {
        if (map.isEmpty()) {
            return;
        }
        List<PKIXCertPathChecker> certPathCheckers = pKIXBuilderParameters.getCertPathCheckers();
        PKIXRevocationChecker r0 = r0(certPathCheckers);
        if (r0 != null) {
            Map<X509Certificate, byte[]> ocspResponses = r0.getOcspResponses();
            if (z0(ocspResponses, map) > 0) {
                r0.setOcspResponses(ocspResponses);
                pKIXBuilderParameters.setCertPathCheckers(certPathCheckers);
            }
        } else if (pKIXBuilderParameters.isRevocationEnabled()) {
            PKIXRevocationChecker pKIXRevocationChecker = (PKIXRevocationChecker) certPathBuilder.getRevocationChecker();
            pKIXRevocationChecker.setOcspResponses(map);
            pKIXBuilderParameters.addCertPathChecker(pKIXRevocationChecker);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BiFunction k0(BCApplicationProtocolSelector bCApplicationProtocolSelector) {
        if (bCApplicationProtocolSelector == null) {
            return null;
        }
        return bCApplicationProtocolSelector instanceof ImportAPSelector ? ((ImportAPSelector) bCApplicationProtocolSelector).a() : new ExportAPSelector(bCApplicationProtocolSelector);
    }

    static SNIMatcher l0(BCSNIMatcher bCSNIMatcher) {
        if (bCSNIMatcher == null) {
            return null;
        }
        return bCSNIMatcher instanceof ImportSNIMatcher ? ((ImportSNIMatcher) bCSNIMatcher).a() : new ExportSNIMatcher(bCSNIMatcher);
    }

    static List m0(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(l0((BCSNIMatcher) it.next()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object n0(Collection collection) {
        return m0(collection);
    }

    static SNIServerName o0(BCSNIServerName bCSNIServerName) {
        if (bCSNIServerName == null) {
            return null;
        }
        int type = bCSNIServerName.getType();
        byte[] encoded = bCSNIServerName.getEncoded();
        return type != 0 ? new UnknownServerName(type, encoded) : new SNIHostName(encoded);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List p0(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(o0((BCSNIServerName) it.next()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object q0(Collection collection) {
        return p0(collection);
    }

    static PKIXRevocationChecker r0(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            PKIXCertPathChecker pKIXCertPathChecker = (PKIXCertPathChecker) it.next();
            if (pKIXCertPathChecker instanceof PKIXRevocationChecker) {
                return (PKIXRevocationChecker) pKIXCertPathChecker;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BCApplicationProtocolSelector s0(BiFunction biFunction) {
        if (biFunction == null) {
            return null;
        }
        return biFunction instanceof ExportAPSelector ? ((ExportAPSelector) biFunction).a() : new ImportAPSelector(biFunction);
    }

    static BCSNIMatcher t0(SNIMatcher sNIMatcher) {
        if (sNIMatcher == null) {
            return null;
        }
        return sNIMatcher instanceof ExportSNIMatcher ? ((ExportSNIMatcher) sNIMatcher).a() : new ImportSNIMatcher(sNIMatcher);
    }

    static List u0(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(t0((SNIMatcher) it.next()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List v0(Object obj) {
        return u0((Collection) obj);
    }

    static BCSNIServerName w0(SNIServerName sNIServerName) {
        if (sNIServerName == null) {
            return null;
        }
        int type = sNIServerName.getType();
        byte[] encoded = sNIServerName.getEncoded();
        return type != 0 ? new JsseUtils.BCUnknownServerName(type, encoded) : new BCSNIHostName(encoded);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List x0(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(w0((SNIServerName) it.next()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List y0(Object obj) {
        return x0((Collection) obj);
    }

    static int z0(Map map, Map map2) {
        int i2 = 0;
        for (Map.Entry entry : map2.entrySet()) {
            if (map.putIfAbsent(entry.getKey(), entry.getValue()) == null) {
                i2++;
            }
        }
        return i2;
    }
}
