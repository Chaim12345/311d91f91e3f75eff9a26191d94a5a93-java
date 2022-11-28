package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.ASN1UTF8String;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
/* loaded from: classes3.dex */
public class IetfAttrSyntax extends ASN1Object {
    public static final int VALUE_OCTETS = 1;
    public static final int VALUE_OID = 2;
    public static final int VALUE_UTF8 = 3;

    /* renamed from: a  reason: collision with root package name */
    GeneralNames f12959a;

    /* renamed from: b  reason: collision with root package name */
    Vector f12960b = new Vector();

    /* renamed from: c  reason: collision with root package name */
    int f12961c;

    /* JADX WARN: Removed duplicated region for block: B:11:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private IetfAttrSyntax(ASN1Sequence aSN1Sequence) {
        GeneralNames generalNames;
        int i2;
        this.f12959a = null;
        this.f12961c = -1;
        int i3 = 0;
        if (!(aSN1Sequence.getObjectAt(0) instanceof ASN1TaggedObject)) {
            generalNames = aSN1Sequence.size() == 2 ? GeneralNames.getInstance(aSN1Sequence.getObjectAt(0)) : generalNames;
            if (aSN1Sequence.getObjectAt(i3) instanceof ASN1Sequence) {
                throw new IllegalArgumentException("Non-IetfAttrSyntax encoding");
            }
            Enumeration objects = ((ASN1Sequence) aSN1Sequence.getObjectAt(i3)).getObjects();
            while (objects.hasMoreElements()) {
                ASN1Primitive aSN1Primitive = (ASN1Primitive) objects.nextElement();
                if (aSN1Primitive instanceof ASN1ObjectIdentifier) {
                    i2 = 2;
                } else if (aSN1Primitive instanceof ASN1UTF8String) {
                    i2 = 3;
                } else if (!(aSN1Primitive instanceof DEROctetString)) {
                    throw new IllegalArgumentException("Bad value type encoding IetfAttrSyntax");
                } else {
                    i2 = 1;
                }
                if (this.f12961c < 0) {
                    this.f12961c = i2;
                }
                if (i2 != this.f12961c) {
                    throw new IllegalArgumentException("Mix of value types in IetfAttrSyntax");
                }
                this.f12960b.addElement(aSN1Primitive);
            }
            return;
        }
        GeneralNames.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(0), false);
        this.f12959a = generalNames;
        i3 = 1;
        if (aSN1Sequence.getObjectAt(i3) instanceof ASN1Sequence) {
        }
    }

    public static IetfAttrSyntax getInstance(Object obj) {
        if (obj instanceof IetfAttrSyntax) {
            return (IetfAttrSyntax) obj;
        }
        if (obj != null) {
            return new IetfAttrSyntax(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public GeneralNames getPolicyAuthority() {
        return this.f12959a;
    }

    public int getValueType() {
        return this.f12961c;
    }

    public Object[] getValues() {
        int i2 = 0;
        if (getValueType() == 1) {
            int size = this.f12960b.size();
            ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[size];
            while (i2 != size) {
                aSN1OctetStringArr[i2] = (ASN1OctetString) this.f12960b.elementAt(i2);
                i2++;
            }
            return aSN1OctetStringArr;
        } else if (getValueType() == 2) {
            int size2 = this.f12960b.size();
            ASN1ObjectIdentifier[] aSN1ObjectIdentifierArr = new ASN1ObjectIdentifier[size2];
            while (i2 != size2) {
                aSN1ObjectIdentifierArr[i2] = (ASN1ObjectIdentifier) this.f12960b.elementAt(i2);
                i2++;
            }
            return aSN1ObjectIdentifierArr;
        } else {
            int size3 = this.f12960b.size();
            ASN1UTF8String[] aSN1UTF8StringArr = new ASN1UTF8String[size3];
            while (i2 != size3) {
                aSN1UTF8StringArr[i2] = (ASN1UTF8String) this.f12960b.elementAt(i2);
                i2++;
            }
            return aSN1UTF8StringArr;
        }
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        GeneralNames generalNames = this.f12959a;
        if (generalNames != null) {
            aSN1EncodableVector.add(new DERTaggedObject(0, generalNames));
        }
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(this.f12960b.size());
        Enumeration elements = this.f12960b.elements();
        while (elements.hasMoreElements()) {
            aSN1EncodableVector2.add((ASN1Encodable) elements.nextElement());
        }
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        return new DERSequence(aSN1EncodableVector);
    }
}
