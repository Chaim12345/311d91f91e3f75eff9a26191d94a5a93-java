package org.bouncycastle.asn1;

import java.io.IOException;
import org.bouncycastle.util.Objects;
/* loaded from: classes3.dex */
public abstract class ASN1External extends ASN1Primitive {

    /* renamed from: f  reason: collision with root package name */
    static final ASN1UniversalType f12681f = new ASN1UniversalType(ASN1External.class, 8) { // from class: org.bouncycastle.asn1.ASN1External.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive c(ASN1Sequence aSN1Sequence) {
            return aSN1Sequence.k();
        }
    };

    /* renamed from: a  reason: collision with root package name */
    ASN1ObjectIdentifier f12682a;

    /* renamed from: b  reason: collision with root package name */
    ASN1Integer f12683b;

    /* renamed from: c  reason: collision with root package name */
    ASN1Primitive f12684c;

    /* renamed from: d  reason: collision with root package name */
    int f12685d;

    /* renamed from: e  reason: collision with root package name */
    ASN1Primitive f12686e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1External(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Integer aSN1Integer, ASN1Primitive aSN1Primitive, int i2, ASN1Primitive aSN1Primitive2) {
        this.f12682a = aSN1ObjectIdentifier;
        this.f12683b = aSN1Integer;
        this.f12684c = aSN1Primitive;
        this.f12685d = checkEncoding(i2);
        this.f12686e = checkExternalContent(i2, aSN1Primitive2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1External(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Integer aSN1Integer, ASN1Primitive aSN1Primitive, DERTaggedObject dERTaggedObject) {
        this.f12682a = aSN1ObjectIdentifier;
        this.f12683b = aSN1Integer;
        this.f12684c = aSN1Primitive;
        this.f12685d = checkEncoding(dERTaggedObject.getTagNo());
        this.f12686e = getExternalContent(dERTaggedObject);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1External(ASN1Sequence aSN1Sequence) {
        int i2 = 0;
        ASN1Primitive objFromSequence = getObjFromSequence(aSN1Sequence, 0);
        if (objFromSequence instanceof ASN1ObjectIdentifier) {
            this.f12682a = (ASN1ObjectIdentifier) objFromSequence;
            objFromSequence = getObjFromSequence(aSN1Sequence, 1);
            i2 = 1;
        }
        if (objFromSequence instanceof ASN1Integer) {
            this.f12683b = (ASN1Integer) objFromSequence;
            i2++;
            objFromSequence = getObjFromSequence(aSN1Sequence, i2);
        }
        if (!(objFromSequence instanceof ASN1TaggedObject)) {
            this.f12684c = objFromSequence;
            i2++;
            objFromSequence = getObjFromSequence(aSN1Sequence, i2);
        }
        if (aSN1Sequence.size() != i2 + 1) {
            throw new IllegalArgumentException("input sequence too large");
        }
        if (!(objFromSequence instanceof ASN1TaggedObject)) {
            throw new IllegalArgumentException("No tagged object found in sequence. Structure doesn't seem to be of type External");
        }
        ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objFromSequence;
        this.f12685d = checkEncoding(aSN1TaggedObject.getTagNo());
        this.f12686e = getExternalContent(aSN1TaggedObject);
    }

    private static int checkEncoding(int i2) {
        if (i2 < 0 || i2 > 2) {
            throw new IllegalArgumentException("invalid encoding value: " + i2);
        }
        return i2;
    }

    private static ASN1Primitive checkExternalContent(int i2, ASN1Primitive aSN1Primitive) {
        ASN1UniversalType aSN1UniversalType;
        if (i2 == 1) {
            aSN1UniversalType = ASN1OctetString.f12702b;
        } else if (i2 != 2) {
            return aSN1Primitive;
        } else {
            aSN1UniversalType = ASN1BitString.f12676b;
        }
        return aSN1UniversalType.a(aSN1Primitive);
    }

    private static ASN1Primitive getExternalContent(ASN1TaggedObject aSN1TaggedObject) {
        int tagClass = aSN1TaggedObject.getTagClass();
        int tagNo = aSN1TaggedObject.getTagNo();
        if (128 != tagClass) {
            throw new IllegalArgumentException("invalid tag: " + ASN1Util.getTagText(tagClass, tagNo));
        } else if (tagNo != 0) {
            if (tagNo != 1) {
                if (tagNo == 2) {
                    return ASN1BitString.getInstance(aSN1TaggedObject, false);
                }
                throw new IllegalArgumentException("invalid tag: " + ASN1Util.getTagText(tagClass, tagNo));
            }
            return ASN1OctetString.getInstance(aSN1TaggedObject, false);
        } else {
            return aSN1TaggedObject.getExplicitBaseObject().toASN1Primitive();
        }
    }

    public static ASN1External getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1External)) {
            return (ASN1External) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1External) {
                return (ASN1External) aSN1Primitive;
            }
        } else if (obj instanceof byte[]) {
            try {
                return (ASN1External) f12681f.b((byte[]) obj);
            } catch (IOException e2) {
                throw new IllegalArgumentException("failed to construct external from byte[]: " + e2.getMessage());
            }
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    public static ASN1External getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1External) f12681f.e(aSN1TaggedObject, z);
    }

    private static ASN1Primitive getObjFromSequence(ASN1Sequence aSN1Sequence, int i2) {
        if (aSN1Sequence.size() > i2) {
            return aSN1Sequence.getObjectAt(i2).toASN1Primitive();
        }
        throw new IllegalArgumentException("too few objects in input sequence");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean b(ASN1Primitive aSN1Primitive) {
        if (this == aSN1Primitive) {
            return true;
        }
        if (aSN1Primitive instanceof ASN1External) {
            ASN1External aSN1External = (ASN1External) aSN1Primitive;
            return Objects.areEqual(this.f12682a, aSN1External.f12682a) && Objects.areEqual(this.f12683b, aSN1External.f12683b) && Objects.areEqual(this.f12684c, aSN1External.f12684c) && this.f12685d == aSN1External.f12685d && this.f12686e.equals(aSN1External.f12686e);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.q(z, 40);
        h().c(aSN1OutputStream, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean d() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        return h().e(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive f() {
        return new DERExternal(this.f12682a, this.f12683b, this.f12684c, this.f12685d, this.f12686e);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public ASN1Primitive g() {
        return new DLExternal(this.f12682a, this.f12683b, this.f12684c, this.f12685d, this.f12686e);
    }

    public ASN1Primitive getDataValueDescriptor() {
        return this.f12684c;
    }

    public ASN1ObjectIdentifier getDirectReference() {
        return this.f12682a;
    }

    public int getEncoding() {
        return this.f12685d;
    }

    public ASN1Primitive getExternalContent() {
        return this.f12686e;
    }

    public ASN1Integer getIndirectReference() {
        return this.f12683b;
    }

    abstract ASN1Sequence h();

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return (((Objects.hashCode(this.f12682a) ^ Objects.hashCode(this.f12683b)) ^ Objects.hashCode(this.f12684c)) ^ this.f12685d) ^ this.f12686e.hashCode();
    }
}
