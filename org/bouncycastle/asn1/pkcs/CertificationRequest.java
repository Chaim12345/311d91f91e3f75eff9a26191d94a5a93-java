package org.bouncycastle.asn1.pkcs;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
/* loaded from: classes3.dex */
public class CertificationRequest extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    protected CertificationRequestInfo f12829a;

    /* renamed from: b  reason: collision with root package name */
    protected AlgorithmIdentifier f12830b;

    /* renamed from: c  reason: collision with root package name */
    protected DERBitString f12831c;

    /* JADX INFO: Access modifiers changed from: protected */
    public CertificationRequest() {
        this.f12829a = null;
        this.f12830b = null;
        this.f12831c = null;
    }

    public CertificationRequest(ASN1Sequence aSN1Sequence) {
        this.f12829a = null;
        this.f12830b = null;
        this.f12831c = null;
        this.f12829a = CertificationRequestInfo.getInstance(aSN1Sequence.getObjectAt(0));
        this.f12830b = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.f12831c = (DERBitString) aSN1Sequence.getObjectAt(2);
    }

    public CertificationRequest(CertificationRequestInfo certificationRequestInfo, AlgorithmIdentifier algorithmIdentifier, DERBitString dERBitString) {
        this.f12829a = null;
        this.f12830b = null;
        this.f12831c = null;
        this.f12829a = certificationRequestInfo;
        this.f12830b = algorithmIdentifier;
        this.f12831c = dERBitString;
    }

    public static CertificationRequest getInstance(Object obj) {
        if (obj instanceof CertificationRequest) {
            return (CertificationRequest) obj;
        }
        if (obj != null) {
            return new CertificationRequest(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CertificationRequestInfo getCertificationRequestInfo() {
        return this.f12829a;
    }

    public DERBitString getSignature() {
        return this.f12831c;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.f12830b;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f12829a);
        aSN1EncodableVector.add(this.f12830b);
        aSN1EncodableVector.add(this.f12831c);
        return new DERSequence(aSN1EncodableVector);
    }
}
