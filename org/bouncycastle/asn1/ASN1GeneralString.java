package org.bouncycastle.asn1;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
public abstract class ASN1GeneralString extends ASN1Primitive implements ASN1String {

    /* renamed from: b  reason: collision with root package name */
    static final ASN1UniversalType f12687b = new ASN1UniversalType(ASN1GeneralString.class, 27) { // from class: org.bouncycastle.asn1.ASN1GeneralString.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive d(DEROctetString dEROctetString) {
            return ASN1GeneralString.h(dEROctetString.getOctets());
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final byte[] f12688a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1GeneralString(String str) {
        this.f12688a = Strings.toByteArray(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1GeneralString(byte[] bArr, boolean z) {
        this.f12688a = z ? Arrays.clone(bArr) : bArr;
    }

    public static ASN1GeneralString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1GeneralString)) {
            return (ASN1GeneralString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1GeneralString) {
                return (ASN1GeneralString) aSN1Primitive;
            }
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1GeneralString) f12687b.b((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    public static ASN1GeneralString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1GeneralString) f12687b.e(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1GeneralString h(byte[] bArr) {
        return new DERGeneralString(bArr, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1GeneralString) {
            return Arrays.areEqual(this.f12688a, ((ASN1GeneralString) aSN1Primitive).f12688a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.m(z, 27, this.f12688a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean d() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final int e(boolean z) {
        return ASN1OutputStream.e(z, this.f12688a.length);
    }

    public final byte[] getOctets() {
        return Arrays.clone(this.f12688a);
    }

    @Override // org.bouncycastle.asn1.ASN1String
    public final String getString() {
        return Strings.fromByteArray(this.f12688a);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public final int hashCode() {
        return Arrays.hashCode(this.f12688a);
    }

    public String toString() {
        return getString();
    }
}
