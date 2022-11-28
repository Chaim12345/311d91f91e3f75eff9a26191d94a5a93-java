package org.bouncycastle.asn1;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
public abstract class ASN1VisibleString extends ASN1Primitive implements ASN1String {

    /* renamed from: b  reason: collision with root package name */
    static final ASN1UniversalType f12734b = new ASN1UniversalType(ASN1VisibleString.class, 26) { // from class: org.bouncycastle.asn1.ASN1VisibleString.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive d(DEROctetString dEROctetString) {
            return ASN1VisibleString.h(dEROctetString.getOctets());
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final byte[] f12735a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1VisibleString(String str) {
        this.f12735a = Strings.toByteArray(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1VisibleString(byte[] bArr, boolean z) {
        this.f12735a = z ? Arrays.clone(bArr) : bArr;
    }

    public static ASN1VisibleString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1VisibleString)) {
            return (ASN1VisibleString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1VisibleString) {
                return (ASN1VisibleString) aSN1Primitive;
            }
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1VisibleString) f12734b.b((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    public static ASN1VisibleString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1VisibleString) f12734b.e(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1VisibleString h(byte[] bArr) {
        return new DERVisibleString(bArr, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1VisibleString) {
            return Arrays.areEqual(this.f12735a, ((ASN1VisibleString) aSN1Primitive).f12735a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.m(z, 26, this.f12735a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean d() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final int e(boolean z) {
        return ASN1OutputStream.e(z, this.f12735a.length);
    }

    public final byte[] getOctets() {
        return Arrays.clone(this.f12735a);
    }

    @Override // org.bouncycastle.asn1.ASN1String
    public final String getString() {
        return Strings.fromByteArray(this.f12735a);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public final int hashCode() {
        return Arrays.hashCode(this.f12735a);
    }

    public String toString() {
        return getString();
    }
}
