package org.bouncycastle.asn1.tsp;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes3.dex */
public class Accuracy extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12888a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12889b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Integer f12890c;

    public Accuracy(ASN1Integer aSN1Integer, ASN1Integer aSN1Integer2, ASN1Integer aSN1Integer3) {
        int intValueExact;
        int intValueExact2;
        if (aSN1Integer2 != null && ((intValueExact2 = aSN1Integer2.intValueExact()) < 1 || intValueExact2 > 999)) {
            throw new IllegalArgumentException("Invalid millis field : not in (1..999)");
        }
        if (aSN1Integer3 != null && ((intValueExact = aSN1Integer3.intValueExact()) < 1 || intValueExact > 999)) {
            throw new IllegalArgumentException("Invalid micros field : not in (1..999)");
        }
        this.f12888a = aSN1Integer;
        this.f12889b = aSN1Integer2;
        this.f12890c = aSN1Integer3;
    }

    private Accuracy(ASN1Sequence aSN1Sequence) {
        this.f12888a = null;
        this.f12889b = null;
        this.f12890c = null;
        for (int i2 = 0; i2 < aSN1Sequence.size(); i2++) {
            if (aSN1Sequence.getObjectAt(i2) instanceof ASN1Integer) {
                this.f12888a = (ASN1Integer) aSN1Sequence.getObjectAt(i2);
            } else if (aSN1Sequence.getObjectAt(i2) instanceof ASN1TaggedObject) {
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(i2);
                int tagNo = aSN1TaggedObject.getTagNo();
                if (tagNo == 0) {
                    ASN1Integer aSN1Integer = ASN1Integer.getInstance(aSN1TaggedObject, false);
                    this.f12889b = aSN1Integer;
                    int intValueExact = aSN1Integer.intValueExact();
                    if (intValueExact < 1 || intValueExact > 999) {
                        throw new IllegalArgumentException("Invalid millis field : not in (1..999)");
                    }
                } else if (tagNo != 1) {
                    throw new IllegalArgumentException("Invalid tag number");
                } else {
                    ASN1Integer aSN1Integer2 = ASN1Integer.getInstance(aSN1TaggedObject, false);
                    this.f12890c = aSN1Integer2;
                    int intValueExact2 = aSN1Integer2.intValueExact();
                    if (intValueExact2 < 1 || intValueExact2 > 999) {
                        throw new IllegalArgumentException("Invalid micros field : not in (1..999)");
                    }
                }
            } else {
                continue;
            }
        }
    }

    public static Accuracy getInstance(Object obj) {
        if (obj instanceof Accuracy) {
            return (Accuracy) obj;
        }
        if (obj != null) {
            return new Accuracy(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Integer getMicros() {
        return this.f12890c;
    }

    public ASN1Integer getMillis() {
        return this.f12889b;
    }

    public ASN1Integer getSeconds() {
        return this.f12888a;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        ASN1Integer aSN1Integer = this.f12888a;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        ASN1Integer aSN1Integer2 = this.f12889b;
        if (aSN1Integer2 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, (ASN1Encodable) aSN1Integer2));
        }
        ASN1Integer aSN1Integer3 = this.f12890c;
        if (aSN1Integer3 != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 1, (ASN1Encodable) aSN1Integer3));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
