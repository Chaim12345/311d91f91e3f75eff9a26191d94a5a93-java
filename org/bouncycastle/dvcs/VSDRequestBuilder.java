package org.bouncycastle.dvcs;

import java.io.IOException;
import java.util.Date;
import org.bouncycastle.asn1.dvcs.DVCSRequestInformationBuilder;
import org.bouncycastle.asn1.dvcs.DVCSTime;
import org.bouncycastle.asn1.dvcs.Data;
import org.bouncycastle.asn1.dvcs.ServiceType;
import org.bouncycastle.cms.CMSSignedData;
/* loaded from: classes3.dex */
public class VSDRequestBuilder extends DVCSRequestBuilder {
    public VSDRequestBuilder() {
        super(new DVCSRequestInformationBuilder(ServiceType.VSD));
    }

    public DVCSRequest build(CMSSignedData cMSSignedData) {
        try {
            return a(new Data(cMSSignedData.getEncoded()));
        } catch (IOException e2) {
            throw new DVCSException("Failed to encode CMS signed data", e2);
        }
    }

    public void setRequestTime(Date date) {
        this.f13525a.setRequestTime(new DVCSTime(date));
    }
}
