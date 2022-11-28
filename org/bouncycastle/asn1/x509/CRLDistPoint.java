package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
public class CRLDistPoint extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Sequence f12935a;

    private CRLDistPoint(ASN1Sequence aSN1Sequence) {
        this.f12935a = null;
        this.f12935a = aSN1Sequence;
    }

    public CRLDistPoint(DistributionPoint[] distributionPointArr) {
        this.f12935a = null;
        this.f12935a = new DERSequence(distributionPointArr);
    }

    public static CRLDistPoint fromExtensions(Extensions extensions) {
        return getInstance(Extensions.getExtensionParsedValue(extensions, Extension.cRLDistributionPoints));
    }

    public static CRLDistPoint getInstance(Object obj) {
        if (obj instanceof CRLDistPoint) {
            return (CRLDistPoint) obj;
        }
        if (obj != null) {
            return new CRLDistPoint(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static CRLDistPoint getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public DistributionPoint[] getDistributionPoints() {
        DistributionPoint[] distributionPointArr = new DistributionPoint[this.f12935a.size()];
        for (int i2 = 0; i2 != this.f12935a.size(); i2++) {
            distributionPointArr[i2] = DistributionPoint.getInstance(this.f12935a.getObjectAt(i2));
        }
        return distributionPointArr;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f12935a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        String lineSeparator = Strings.lineSeparator();
        stringBuffer.append("CRLDistPoint:");
        stringBuffer.append(lineSeparator);
        DistributionPoint[] distributionPoints = getDistributionPoints();
        for (int i2 = 0; i2 != distributionPoints.length; i2++) {
            stringBuffer.append("    ");
            stringBuffer.append(distributionPoints[i2]);
            stringBuffer.append(lineSeparator);
        }
        return stringBuffer.toString();
    }
}
