package org.bouncycastle.dvcs;

import org.bouncycastle.asn1.dvcs.Data;
/* loaded from: classes3.dex */
public class CPDRequestData extends DVCSRequestData {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CPDRequestData(Data data) {
        super(data);
        initMessage();
    }

    private void initMessage() {
        if (this.f13526a.getMessage() == null) {
            throw new DVCSConstructionException("DVCSRequest.data.message should be specified for CPD service");
        }
    }

    public byte[] getMessage() {
        return this.f13526a.getMessage().getOctets();
    }
}
