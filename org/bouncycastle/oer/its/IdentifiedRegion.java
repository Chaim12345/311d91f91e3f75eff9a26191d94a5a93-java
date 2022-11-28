package org.bouncycastle.oer.its;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes4.dex */
public class IdentifiedRegion extends ASN1Object implements ASN1Choice, RegionInterface {
    public static final int countAndSubregions = 2;
    public static final int countryAndRegions = 1;
    public static final int countryOnly = 0;
    public static final int extension = 3;
    private int choice;
    private ASN1Encodable region;

    public IdentifiedRegion(int i2, ASN1Encodable aSN1Encodable) {
        this.choice = i2;
        this.region = aSN1Encodable;
    }

    public static IdentifiedRegion getInstance(Object obj) {
        if (obj instanceof IdentifiedRegion) {
            return (IdentifiedRegion) obj;
        }
        ASN1TaggedObject aSN1TaggedObject = ASN1TaggedObject.getInstance(obj);
        int tagNo = aSN1TaggedObject.getTagNo();
        ASN1Primitive object = aSN1TaggedObject.getObject();
        if (tagNo != 0) {
            if (tagNo != 1) {
                if (tagNo != 2) {
                    if (tagNo == 3) {
                        return new IdentifiedRegion(tagNo, ASN1OctetString.getInstance(object));
                    }
                    throw new IllegalArgumentException("unknown choice " + tagNo);
                }
                return new IdentifiedRegion(tagNo, RegionAndSubregions.getInstance(object));
            }
            return new IdentifiedRegion(tagNo, CountryAndRegions.getInstance(object));
        }
        return new IdentifiedRegion(tagNo, CountryOnly.getInstance((Object) object));
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(this.choice, (ASN1Object) this.region).toASN1Primitive();
    }
}
