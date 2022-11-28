package org.bouncycastle.tsp;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.asn1.cmp.PKIFreeText;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.tsp.TimeStampResp;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class TimeStampResponse {

    /* renamed from: a  reason: collision with root package name */
    TimeStampResp f15079a;

    /* renamed from: b  reason: collision with root package name */
    TimeStampToken f15080b;

    public TimeStampResponse(InputStream inputStream) {
        this(readTimeStampResp(inputStream));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TimeStampResponse(DLSequence dLSequence) {
        try {
            this.f15079a = TimeStampResp.getInstance(dLSequence);
            this.f15080b = new TimeStampToken(ContentInfo.getInstance(dLSequence.getObjectAt(1)));
        } catch (ClassCastException e2) {
            throw new TSPException("malformed timestamp response: " + e2, e2);
        } catch (IllegalArgumentException e3) {
            throw new TSPException("malformed timestamp response: " + e3, e3);
        }
    }

    public TimeStampResponse(TimeStampResp timeStampResp) {
        this.f15079a = timeStampResp;
        if (timeStampResp.getTimeStampToken() != null) {
            this.f15080b = new TimeStampToken(timeStampResp.getTimeStampToken());
        }
    }

    public TimeStampResponse(byte[] bArr) {
        this(new ByteArrayInputStream(bArr));
    }

    private static TimeStampResp readTimeStampResp(InputStream inputStream) {
        try {
            return TimeStampResp.getInstance(new ASN1InputStream(inputStream).readObject());
        } catch (ClassCastException e2) {
            throw new TSPException("malformed timestamp response: " + e2, e2);
        } catch (IllegalArgumentException e3) {
            throw new TSPException("malformed timestamp response: " + e3, e3);
        }
    }

    public byte[] getEncoded() {
        return this.f15079a.getEncoded();
    }

    public byte[] getEncoded(String str) {
        return (ASN1Encoding.DL.equals(str) ? this.f15080b == null ? new DLSequence(this.f15079a.getStatus()) : new DLSequence(new ASN1Encodable[]{this.f15079a.getStatus(), this.f15080b.toCMSSignedData().toASN1Structure()}) : this.f15079a).getEncoded(str);
    }

    public PKIFailureInfo getFailInfo() {
        if (this.f15079a.getStatus().getFailInfo() != null) {
            return new PKIFailureInfo(this.f15079a.getStatus().getFailInfo());
        }
        return null;
    }

    public int getStatus() {
        return this.f15079a.getStatus().getStatus().intValue();
    }

    public String getStatusString() {
        if (this.f15079a.getStatus().getStatusString() != null) {
            StringBuffer stringBuffer = new StringBuffer();
            PKIFreeText statusString = this.f15079a.getStatus().getStatusString();
            for (int i2 = 0; i2 != statusString.size(); i2++) {
                stringBuffer.append(statusString.getStringAtUTF8(i2).getString());
            }
            return stringBuffer.toString();
        }
        return null;
    }

    public TimeStampToken getTimeStampToken() {
        return this.f15080b;
    }

    public void validate(TimeStampRequest timeStampRequest) {
        TimeStampToken timeStampToken = getTimeStampToken();
        if (timeStampToken == null) {
            if (getStatus() == 0 || getStatus() == 1) {
                throw new TSPValidationException("no time stamp token found and one expected.");
            }
            return;
        }
        TimeStampTokenInfo timeStampInfo = timeStampToken.getTimeStampInfo();
        if (timeStampRequest.getNonce() != null && !timeStampRequest.getNonce().equals(timeStampInfo.getNonce())) {
            throw new TSPValidationException("response contains wrong nonce value.");
        }
        if (getStatus() != 0 && getStatus() != 1) {
            throw new TSPValidationException("time stamp token found in failed request.");
        }
        if (!Arrays.constantTimeAreEqual(timeStampRequest.getMessageImprintDigest(), timeStampInfo.getMessageImprintDigest())) {
            throw new TSPValidationException("response for different message imprint digest.");
        }
        if (!timeStampInfo.getMessageImprintAlgOID().equals((ASN1Primitive) timeStampRequest.getMessageImprintAlgOID())) {
            throw new TSPValidationException("response for different message imprint algorithm.");
        }
        Attribute attribute = timeStampToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificate);
        Attribute attribute2 = timeStampToken.getSignedAttributes().get(PKCSObjectIdentifiers.id_aa_signingCertificateV2);
        if (attribute == null && attribute2 == null) {
            throw new TSPValidationException("no signing certificate attribute present.");
        }
        if (timeStampRequest.getReqPolicy() != null && !timeStampRequest.getReqPolicy().equals((ASN1Primitive) timeStampInfo.getPolicy())) {
            throw new TSPValidationException("TSA policy wrong for request.");
        }
    }
}
