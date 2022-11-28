package org.bouncycastle.asn1.tsp;

import org.bouncycastle.asn1.ASN1Boolean;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Extensions;
/* loaded from: classes3.dex */
public class TimeStampReq extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    ASN1Integer f12893a;

    /* renamed from: b  reason: collision with root package name */
    MessageImprint f12894b;

    /* renamed from: c  reason: collision with root package name */
    ASN1ObjectIdentifier f12895c;

    /* renamed from: d  reason: collision with root package name */
    ASN1Integer f12896d;

    /* renamed from: e  reason: collision with root package name */
    ASN1Boolean f12897e;

    /* renamed from: f  reason: collision with root package name */
    Extensions f12898f;

    private TimeStampReq(ASN1Sequence aSN1Sequence) {
        int size = aSN1Sequence.size();
        this.f12893a = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(0));
        this.f12894b = MessageImprint.getInstance(aSN1Sequence.getObjectAt(1));
        for (int i2 = 2; i2 < size; i2++) {
            if (aSN1Sequence.getObjectAt(i2) instanceof ASN1ObjectIdentifier) {
                checkOption(this.f12895c, i2, 2);
                this.f12895c = ASN1ObjectIdentifier.getInstance(aSN1Sequence.getObjectAt(i2));
            } else if (aSN1Sequence.getObjectAt(i2) instanceof ASN1Integer) {
                checkOption(this.f12896d, i2, 3);
                this.f12896d = ASN1Integer.getInstance(aSN1Sequence.getObjectAt(i2));
            } else if (aSN1Sequence.getObjectAt(i2) instanceof ASN1Boolean) {
                checkOption(this.f12897e, i2, 4);
                this.f12897e = ASN1Boolean.getInstance(aSN1Sequence.getObjectAt(i2));
            } else if (!(aSN1Sequence.getObjectAt(i2) instanceof ASN1TaggedObject)) {
                throw new IllegalArgumentException("unidentified structure in sequence");
            } else {
                checkOption(this.f12898f, i2, 5);
                ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) aSN1Sequence.getObjectAt(i2);
                if (aSN1TaggedObject.getTagNo() == 0) {
                    this.f12898f = Extensions.getInstance(aSN1TaggedObject, false);
                }
            }
        }
    }

    public TimeStampReq(MessageImprint messageImprint, ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Integer aSN1Integer, ASN1Boolean aSN1Boolean, Extensions extensions) {
        this.f12893a = new ASN1Integer(1L);
        this.f12894b = messageImprint;
        this.f12895c = aSN1ObjectIdentifier;
        this.f12896d = aSN1Integer;
        this.f12897e = aSN1Boolean;
        this.f12898f = extensions;
    }

    private void checkOption(Object obj, int i2, int i3) {
        if (obj != null || i2 > i3) {
            throw new IllegalArgumentException("badly placed optional in sequence");
        }
    }

    public static TimeStampReq getInstance(Object obj) {
        if (obj instanceof TimeStampReq) {
            return (TimeStampReq) obj;
        }
        if (obj != null) {
            return new TimeStampReq(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1Boolean getCertReq() {
        ASN1Boolean aSN1Boolean = this.f12897e;
        return aSN1Boolean == null ? ASN1Boolean.FALSE : aSN1Boolean;
    }

    public Extensions getExtensions() {
        return this.f12898f;
    }

    public MessageImprint getMessageImprint() {
        return this.f12894b;
    }

    public ASN1Integer getNonce() {
        return this.f12896d;
    }

    public ASN1ObjectIdentifier getReqPolicy() {
        return this.f12895c;
    }

    public ASN1Integer getVersion() {
        return this.f12893a;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(6);
        aSN1EncodableVector.add(this.f12893a);
        aSN1EncodableVector.add(this.f12894b);
        ASN1ObjectIdentifier aSN1ObjectIdentifier = this.f12895c;
        if (aSN1ObjectIdentifier != null) {
            aSN1EncodableVector.add(aSN1ObjectIdentifier);
        }
        ASN1Integer aSN1Integer = this.f12896d;
        if (aSN1Integer != null) {
            aSN1EncodableVector.add(aSN1Integer);
        }
        ASN1Boolean aSN1Boolean = this.f12897e;
        if (aSN1Boolean != null && aSN1Boolean.isTrue()) {
            aSN1EncodableVector.add(this.f12897e);
        }
        Extensions extensions = this.f12898f;
        if (extensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(false, 0, (ASN1Encodable) extensions));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
