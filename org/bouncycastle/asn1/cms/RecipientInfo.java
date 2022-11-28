package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1Choice;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes3.dex */
public class RecipientInfo extends ASN1Object implements ASN1Choice {

    /* renamed from: a  reason: collision with root package name */
    ASN1Encodable f12758a;

    public RecipientInfo(ASN1Primitive aSN1Primitive) {
        this.f12758a = aSN1Primitive;
    }

    public RecipientInfo(KEKRecipientInfo kEKRecipientInfo) {
        this.f12758a = new DERTaggedObject(false, 2, (ASN1Encodable) kEKRecipientInfo);
    }

    public RecipientInfo(KeyAgreeRecipientInfo keyAgreeRecipientInfo) {
        this.f12758a = new DERTaggedObject(false, 1, (ASN1Encodable) keyAgreeRecipientInfo);
    }

    public RecipientInfo(KeyTransRecipientInfo keyTransRecipientInfo) {
        this.f12758a = keyTransRecipientInfo;
    }

    public RecipientInfo(OtherRecipientInfo otherRecipientInfo) {
        this.f12758a = new DERTaggedObject(false, 4, (ASN1Encodable) otherRecipientInfo);
    }

    public RecipientInfo(PasswordRecipientInfo passwordRecipientInfo) {
        this.f12758a = new DERTaggedObject(false, 3, (ASN1Encodable) passwordRecipientInfo);
    }

    public static RecipientInfo getInstance(Object obj) {
        if (obj == null || (obj instanceof RecipientInfo)) {
            return (RecipientInfo) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new RecipientInfo((ASN1Sequence) obj);
        }
        if (obj instanceof ASN1TaggedObject) {
            return new RecipientInfo((ASN1TaggedObject) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    private KEKRecipientInfo getKEKInfo(ASN1TaggedObject aSN1TaggedObject) {
        return KEKRecipientInfo.getInstance(aSN1TaggedObject, aSN1TaggedObject.isExplicit());
    }

    public ASN1Encodable getInfo() {
        ASN1Encodable aSN1Encodable = this.f12758a;
        if (aSN1Encodable instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Encodable;
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo != 1) {
                if (tagNo != 2) {
                    if (tagNo != 3) {
                        if (tagNo == 4) {
                            return OtherRecipientInfo.getInstance(aSN1TaggedObject, false);
                        }
                        throw new IllegalStateException("unknown tag");
                    }
                    return PasswordRecipientInfo.getInstance(aSN1TaggedObject, false);
                }
                return getKEKInfo(aSN1TaggedObject);
            }
            return KeyAgreeRecipientInfo.getInstance(aSN1TaggedObject, false);
        }
        return KeyTransRecipientInfo.getInstance(aSN1Encodable);
    }

    public ASN1Integer getVersion() {
        ASN1Encodable aSN1Encodable = this.f12758a;
        if (aSN1Encodable instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Encodable;
            int tagNo = aSN1TaggedObject.getTagNo();
            if (tagNo != 1) {
                if (tagNo != 2) {
                    if (tagNo != 3) {
                        if (tagNo == 4) {
                            return new ASN1Integer(0L);
                        }
                        throw new IllegalStateException("unknown tag");
                    }
                    return PasswordRecipientInfo.getInstance(aSN1TaggedObject, false).getVersion();
                }
                return getKEKInfo(aSN1TaggedObject).getVersion();
            }
            return KeyAgreeRecipientInfo.getInstance(aSN1TaggedObject, false).getVersion();
        }
        return KeyTransRecipientInfo.getInstance(aSN1Encodable).getVersion();
    }

    public boolean isTagged() {
        return this.f12758a instanceof ASN1TaggedObject;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f12758a.toASN1Primitive();
    }
}
