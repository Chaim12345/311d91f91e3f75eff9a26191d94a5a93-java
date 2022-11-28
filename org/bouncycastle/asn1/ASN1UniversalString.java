package org.bouncycastle.asn1;

import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public abstract class ASN1UniversalString extends ASN1Primitive implements ASN1String {

    /* renamed from: b  reason: collision with root package name */
    static final ASN1UniversalType f12730b = new ASN1UniversalType(ASN1UniversalString.class, 28) { // from class: org.bouncycastle.asn1.ASN1UniversalString.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive d(DEROctetString dEROctetString) {
            return ASN1UniversalString.h(dEROctetString.getOctets());
        }
    };
    private static final char[] table = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a  reason: collision with root package name */
    final byte[] f12731a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1UniversalString(byte[] bArr, boolean z) {
        this.f12731a = z ? Arrays.clone(bArr) : bArr;
    }

    private static void encodeHexByte(StringBuffer stringBuffer, int i2) {
        char[] cArr = table;
        stringBuffer.append(cArr[(i2 >>> 4) & 15]);
        stringBuffer.append(cArr[i2 & 15]);
    }

    private static void encodeHexDL(StringBuffer stringBuffer, int i2) {
        if (i2 < 128) {
            encodeHexByte(stringBuffer, i2);
            return;
        }
        byte[] bArr = new byte[5];
        int i3 = 5;
        do {
            i3--;
            bArr[i3] = (byte) i2;
            i2 >>>= 8;
        } while (i2 != 0);
        int i4 = 5 - i3;
        int i5 = i3 - 1;
        bArr[i5] = (byte) (i4 | 128);
        while (true) {
            int i6 = i5 + 1;
            encodeHexByte(stringBuffer, bArr[i5]);
            if (i6 >= 5) {
                return;
            }
            i5 = i6;
        }
    }

    public static ASN1UniversalString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1UniversalString)) {
            return (ASN1UniversalString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1UniversalString) {
                return (ASN1UniversalString) aSN1Primitive;
            }
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1UniversalString) f12730b.b((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error getInstance: " + e2.toString());
        }
    }

    public static ASN1UniversalString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1UniversalString) f12730b.e(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1UniversalString h(byte[] bArr) {
        return new DERUniversalString(bArr, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1UniversalString) {
            return Arrays.areEqual(this.f12731a, ((ASN1UniversalString) aSN1Primitive).f12731a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.m(z, 28, this.f12731a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean d() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final int e(boolean z) {
        return ASN1OutputStream.e(z, this.f12731a.length);
    }

    public final byte[] getOctets() {
        return Arrays.clone(this.f12731a);
    }

    @Override // org.bouncycastle.asn1.ASN1String
    public final String getString() {
        int length = this.f12731a.length;
        StringBuffer stringBuffer = new StringBuffer(((ASN1OutputStream.d(length) + length) * 2) + 3);
        stringBuffer.append("#1C");
        encodeHexDL(stringBuffer, length);
        for (int i2 = 0; i2 < length; i2++) {
            encodeHexByte(stringBuffer, this.f12731a[i2]);
        }
        return stringBuffer.toString();
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public final int hashCode() {
        return Arrays.hashCode(this.f12731a);
    }

    public String toString() {
        return getString();
    }
}
