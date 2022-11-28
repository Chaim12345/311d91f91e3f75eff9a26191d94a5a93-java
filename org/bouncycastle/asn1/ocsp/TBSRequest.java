package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.X509Extensions;
/* loaded from: classes3.dex */
public class TBSRequest extends ASN1Object {
    private static final ASN1Integer V1 = new ASN1Integer(0);

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12822a;

    /* renamed from: b  reason: collision with root package name */
    GeneralName f12823b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Sequence f12824c;

    /* renamed from: d  reason: collision with root package name */
    Extensions f12825d;

    /* renamed from: e  reason: collision with root package name */
    boolean f12826e;

    private TBSRequest(ASN1Sequence aSN1Sequence) {
        int i2 = 0;
        if ((aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject) && ((ASN1TaggedObject) aSN1Sequence.getObjectAt(0)).getTagNo() == 0) {
            this.f12826e = true;
            this.f12822a = ASN1Integer.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(0), true);
            i2 = 1;
        } else {
            this.f12822a = V1;
        }
        if (aSN1Sequence.getObjectAt(i2) instanceof ASN1TaggedObject) {
            this.f12823b = GeneralName.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(i2), true);
            i2++;
        }
        int i3 = i2 + 1;
        this.f12824c = (ASN1Sequence) aSN1Sequence.getObjectAt(i2);
        if (aSN1Sequence.size() == i3 + 1) {
            this.f12825d = Extensions.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(i3), true);
        }
    }

    public TBSRequest(GeneralName generalName, ASN1Sequence aSN1Sequence, Extensions extensions) {
        this.f12822a = V1;
        this.f12823b = generalName;
        this.f12824c = aSN1Sequence;
        this.f12825d = extensions;
    }

    public TBSRequest(GeneralName generalName, ASN1Sequence aSN1Sequence, X509Extensions x509Extensions) {
        this.f12822a = V1;
        this.f12823b = generalName;
        this.f12824c = aSN1Sequence;
        this.f12825d = Extensions.getInstance(x509Extensions);
    }

    public static TBSRequest getInstance(Object obj) {
        if (obj instanceof TBSRequest) {
            return (TBSRequest) obj;
        }
        if (obj != null) {
            return new TBSRequest(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TBSRequest getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public Extensions getRequestExtensions() {
        return this.f12825d;
    }

    public ASN1Sequence getRequestList() {
        return this.f12824c;
    }

    public GeneralName getRequestorName() {
        return this.f12823b;
    }

    public ASN1Integer getVersion() {
        return this.f12822a;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(4);
        if (!this.f12822a.equals((ASN1Primitive) V1) || this.f12826e) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, (ASN1Encodable) this.f12822a));
        }
        GeneralName generalName = this.f12823b;
        if (generalName != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 1, (ASN1Encodable) generalName));
        }
        aSN1EncodableVector.add(this.f12824c);
        Extensions extensions = this.f12825d;
        if (extensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 2, (ASN1Encodable) extensions));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
