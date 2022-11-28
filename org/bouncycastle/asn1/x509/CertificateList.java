package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1BitString;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.TBSCertList;
/* loaded from: classes3.dex */
public class CertificateList extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    TBSCertList f12940a;

    /* renamed from: b  reason: collision with root package name */
    AlgorithmIdentifier f12941b;

    /* renamed from: c  reason: collision with root package name */
    ASN1BitString f12942c;

    /* renamed from: d  reason: collision with root package name */
    boolean f12943d = false;

    /* renamed from: e  reason: collision with root package name */
    int f12944e;

    private CertificateList(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("sequence wrong size for CertificateList");
        }
        this.f12940a = TBSCertList.getInstance(aSN1Sequence.getObjectAt(0));
        this.f12941b = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
        this.f12942c = DERBitString.getInstance((Object) aSN1Sequence.getObjectAt(2));
    }

    public static CertificateList getInstance(Object obj) {
        if (obj instanceof CertificateList) {
            return (CertificateList) obj;
        }
        if (obj != null) {
            return new CertificateList(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static CertificateList getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public X500Name getIssuer() {
        return this.f12940a.getIssuer();
    }

    public Time getNextUpdate() {
        return this.f12940a.getNextUpdate();
    }

    public Enumeration getRevokedCertificateEnumeration() {
        return this.f12940a.getRevokedCertificateEnumeration();
    }

    public TBSCertList.CRLEntry[] getRevokedCertificates() {
        return this.f12940a.getRevokedCertificates();
    }

    public ASN1BitString getSignature() {
        return this.f12942c;
    }

    public AlgorithmIdentifier getSignatureAlgorithm() {
        return this.f12941b;
    }

    public TBSCertList getTBSCertList() {
        return this.f12940a;
    }

    public Time getThisUpdate() {
        return this.f12940a.getThisUpdate();
    }

    public int getVersionNumber() {
        return this.f12940a.getVersionNumber();
    }

    @Override // org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        if (!this.f12943d) {
            this.f12944e = super.hashCode();
            this.f12943d = true;
        }
        return this.f12944e;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f12940a);
        aSN1EncodableVector.add(this.f12941b);
        aSN1EncodableVector.add(this.f12942c);
        return new DERSequence(aSN1EncodableVector);
    }
}
