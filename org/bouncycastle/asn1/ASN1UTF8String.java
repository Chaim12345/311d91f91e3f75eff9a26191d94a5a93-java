package org.bouncycastle.asn1;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
public abstract class ASN1UTF8String extends ASN1Primitive implements ASN1String {

    /* renamed from: b  reason: collision with root package name */
    static final ASN1UniversalType f12728b = new ASN1UniversalType(ASN1UTF8String.class, 12) { // from class: org.bouncycastle.asn1.ASN1UTF8String.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive d(DEROctetString dEROctetString) {
            return ASN1UTF8String.h(dEROctetString.getOctets());
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final byte[] f12729a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1UTF8String(String str) {
        this(Strings.toUTF8ByteArray(str), false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1UTF8String(byte[] bArr, boolean z) {
        this.f12729a = z ? Arrays.clone(bArr) : bArr;
    }

    public static ASN1UTF8String getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1UTF8String)) {
            return (ASN1UTF8String) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1UTF8String) {
                return (ASN1UTF8String) aSN1Primitive;
            }
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1UTF8String) f12728b.b((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    public static ASN1UTF8String getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1UTF8String) f12728b.e(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1UTF8String h(byte[] bArr) {
        return new DERUTF8String(bArr, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1UTF8String) {
            return Arrays.areEqual(this.f12729a, ((ASN1UTF8String) aSN1Primitive).f12729a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.m(z, 12, this.f12729a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean d() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final int e(boolean z) {
        return ASN1OutputStream.e(z, this.f12729a.length);
    }

    @Override // org.bouncycastle.asn1.ASN1String
    public final String getString() {
        return Strings.fromUTF8ByteArray(this.f12729a);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public final int hashCode() {
        return Arrays.hashCode(this.f12729a);
    }

    public String toString() {
        return getString();
    }
}
