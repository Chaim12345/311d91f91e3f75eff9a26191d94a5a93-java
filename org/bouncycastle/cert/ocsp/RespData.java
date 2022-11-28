package org.bouncycastle.cert.ocsp;

import java.util.Date;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.bouncycastle.asn1.ocsp.SingleResponse;
import org.bouncycastle.asn1.x509.Extensions;
/* loaded from: classes3.dex */
public class RespData {
    private ResponseData data;

    public RespData(ResponseData responseData) {
        this.data = responseData;
    }

    public Date getProducedAt() {
        return OCSPUtils.a(this.data.getProducedAt());
    }

    public RespID getResponderId() {
        return new RespID(this.data.getResponderID());
    }

    public Extensions getResponseExtensions() {
        return this.data.getResponseExtensions();
    }

    public SingleResp[] getResponses() {
        ASN1Sequence responses = this.data.getResponses();
        int size = responses.size();
        SingleResp[] singleRespArr = new SingleResp[size];
        for (int i2 = 0; i2 != size; i2++) {
            singleRespArr[i2] = new SingleResp(SingleResponse.getInstance(responses.getObjectAt(i2)));
        }
        return singleRespArr;
    }

    public int getVersion() {
        return this.data.getVersion().intValueExact() + 1;
    }
}
