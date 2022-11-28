package org.bouncycastle.cms;

import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.cms.OriginatorInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.util.Store;
/* loaded from: classes3.dex */
public class OriginatorInfoGenerator {
    private final List origCRLs;
    private final List origCerts;

    public OriginatorInfoGenerator(X509CertificateHolder x509CertificateHolder) {
        ArrayList arrayList = new ArrayList(1);
        this.origCerts = arrayList;
        this.origCRLs = null;
        arrayList.add(x509CertificateHolder.toASN1Structure());
    }

    public OriginatorInfoGenerator(Store store) {
        this(store, null);
    }

    public OriginatorInfoGenerator(Store store, Store store2) {
        this.origCerts = CMSUtils.j(store);
        this.origCRLs = store2 != null ? CMSUtils.i(store2) : null;
    }

    public OriginatorInformation generate() {
        return this.origCRLs != null ? new OriginatorInformation(new OriginatorInfo(CMSUtils.g(this.origCerts), CMSUtils.g(this.origCRLs))) : new OriginatorInformation(new OriginatorInfo(CMSUtils.g(this.origCerts), null));
    }
}
