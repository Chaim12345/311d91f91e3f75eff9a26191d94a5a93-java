package org.bouncycastle.asn1.pkcs;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.X509Name;
/* loaded from: classes3.dex */
public class IssuerAndSerialNumber extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    X500Name f12840a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12841b;

    private IssuerAndSerialNumber(ASN1Sequence aSN1Sequence) {
        this.f12840a = X500Name.getInstance(aSN1Sequence.getObjectAt(0));
        this.f12841b = (ASN1Integer) aSN1Sequence.getObjectAt(1);
    }

    public IssuerAndSerialNumber(X500Name x500Name, BigInteger bigInteger) {
        this.f12840a = x500Name;
        this.f12841b = new ASN1Integer(bigInteger);
    }

    public IssuerAndSerialNumber(X509Name x509Name, BigInteger bigInteger) {
        this.f12840a = X500Name.getInstance(x509Name.toASN1Primitive());
        this.f12841b = new ASN1Integer(bigInteger);
    }

    public IssuerAndSerialNumber(X509Name x509Name, ASN1Integer aSN1Integer) {
        this.f12840a = X500Name.getInstance(x509Name.toASN1Primitive());
        this.f12841b = aSN1Integer;
    }

    public static IssuerAndSerialNumber getInstance(Object obj) {
        if (obj instanceof IssuerAndSerialNumber) {
            return (IssuerAndSerialNumber) obj;
        }
        if (obj != null) {
            return new IssuerAndSerialNumber(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Integer getCertificateSerialNumber() {
        return this.f12841b;
    }

    public X500Name getName() {
        return this.f12840a;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.f12840a);
        aSN1EncodableVector.add(this.f12841b);
        return new DERSequence(aSN1EncodableVector);
    }
}
