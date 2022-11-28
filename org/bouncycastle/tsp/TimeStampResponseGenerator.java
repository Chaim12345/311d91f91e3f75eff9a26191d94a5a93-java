package org.bouncycastle.tsp;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.cmp.PKIFreeText;
import org.bouncycastle.asn1.cmp.PKIStatusInfo;
import org.bouncycastle.asn1.tsp.TimeStampResp;
import org.bouncycastle.asn1.x509.Extensions;
/* loaded from: classes4.dex */
public class TimeStampResponseGenerator {

    /* renamed from: a  reason: collision with root package name */
    int f15081a;
    private Set acceptedAlgorithms;
    private Set acceptedExtensions;
    private Set acceptedPolicies;

    /* renamed from: b  reason: collision with root package name */
    ASN1EncodableVector f15082b;

    /* renamed from: c  reason: collision with root package name */
    int f15083c;
    private TimeStampTokenGenerator tokenGenerator;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class FailInfo extends DERBitString {
        FailInfo(TimeStampResponseGenerator timeStampResponseGenerator, int i2) {
            super(ASN1BitString.i(i2), ASN1BitString.j(i2));
        }
    }

    public TimeStampResponseGenerator(TimeStampTokenGenerator timeStampTokenGenerator, Set set) {
        this(timeStampTokenGenerator, set, null, null);
    }

    public TimeStampResponseGenerator(TimeStampTokenGenerator timeStampTokenGenerator, Set set, Set set2) {
        this(timeStampTokenGenerator, set, set2, null);
    }

    public TimeStampResponseGenerator(TimeStampTokenGenerator timeStampTokenGenerator, Set set, Set set2, Set set3) {
        this.tokenGenerator = timeStampTokenGenerator;
        this.acceptedAlgorithms = convert(set);
        this.acceptedPolicies = convert(set2);
        this.acceptedExtensions = convert(set3);
        this.f15082b = new ASN1EncodableVector();
    }

    private void addStatusString(String str) {
        this.f15082b.add(new DERUTF8String(str));
    }

    private Set convert(Set set) {
        if (set == null) {
            return set;
        }
        HashSet hashSet = new HashSet(set.size());
        for (Object obj : set) {
            if (obj instanceof String) {
                hashSet.add(new ASN1ObjectIdentifier((String) obj));
            } else {
                hashSet.add(obj);
            }
        }
        return hashSet;
    }

    private PKIStatusInfo getPKIStatusInfo() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1Integer(this.f15081a));
        if (this.f15082b.size() > 0) {
            aSN1EncodableVector.add(PKIFreeText.getInstance(new DERSequence(this.f15082b)));
        }
        if (this.f15083c != 0) {
            aSN1EncodableVector.add(new FailInfo(this, this.f15083c));
        }
        return PKIStatusInfo.getInstance(new DERSequence(aSN1EncodableVector));
    }

    private void setFailInfoField(int i2) {
        this.f15083c = i2 | this.f15083c;
    }

    public TimeStampResponse generate(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date) {
        try {
            return generateGrantedResponse(timeStampRequest, bigInteger, date, "Operation Okay");
        } catch (Exception e2) {
            return generateRejectedResponse(e2);
        }
    }

    public TimeStampResponse generateFailResponse(int i2, int i3, String str) {
        this.f15081a = i2;
        this.f15082b = new ASN1EncodableVector();
        setFailInfoField(i3);
        if (str != null) {
            addStatusString(str);
        }
        try {
            return new TimeStampResponse(new TimeStampResp(getPKIStatusInfo(), null));
        } catch (IOException unused) {
            throw new TSPException("created badly formatted response!");
        }
    }

    public TimeStampResponse generateGrantedResponse(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date) {
        return generateGrantedResponse(timeStampRequest, bigInteger, date, null);
    }

    public TimeStampResponse generateGrantedResponse(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date, String str) {
        return generateGrantedResponse(timeStampRequest, bigInteger, date, str, null);
    }

    public TimeStampResponse generateGrantedResponse(TimeStampRequest timeStampRequest, BigInteger bigInteger, Date date, String str, Extensions extensions) {
        if (date != null) {
            timeStampRequest.validate(this.acceptedAlgorithms, this.acceptedPolicies, this.acceptedExtensions);
            this.f15081a = 0;
            this.f15082b = new ASN1EncodableVector();
            if (str != null) {
                addStatusString(str);
            }
            try {
                try {
                    return new TimeStampResponse(new DLSequence(new ASN1Encodable[]{getPKIStatusInfo().toASN1Primitive(), this.tokenGenerator.generate(timeStampRequest, bigInteger, date, extensions).toCMSSignedData().toASN1Structure().toASN1Primitive()}));
                } catch (IOException unused) {
                    throw new TSPException("created badly formatted response!");
                }
            } catch (TSPException e2) {
                throw e2;
            } catch (Exception e3) {
                throw new TSPException("Timestamp token received cannot be converted to ContentInfo", e3);
            }
        }
        throw new TSPValidationException("The time source is not available.", 512);
    }

    public TimeStampResponse generateRejectedResponse(Exception exc) {
        return generateFailResponse(2, exc instanceof TSPValidationException ? ((TSPValidationException) exc).getFailureCode() : 1073741824, exc.getMessage());
    }
}
