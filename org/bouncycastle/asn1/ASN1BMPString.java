package org.bouncycastle.asn1;

import java.util.Objects;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public abstract class ASN1BMPString extends ASN1Primitive implements ASN1String {

    /* renamed from: b  reason: collision with root package name */
    static final ASN1UniversalType f12674b = new ASN1UniversalType(ASN1BMPString.class, 30) { // from class: org.bouncycastle.asn1.ASN1BMPString.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        public ASN1Primitive d(DEROctetString dEROctetString) {
            return ASN1BMPString.h(dEROctetString.getOctets());
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final char[] f12675a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1BMPString(String str) {
        Objects.requireNonNull(str, "'string' cannot be null");
        this.f12675a = str.toCharArray();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1BMPString(byte[] bArr) {
        Objects.requireNonNull(bArr, "'string' cannot be null");
        int length = bArr.length;
        if ((length & 1) != 0) {
            throw new IllegalArgumentException("malformed BMPString encoding encountered");
        }
        int i2 = length / 2;
        char[] cArr = new char[i2];
        for (int i3 = 0; i3 != i2; i3++) {
            int i4 = i3 * 2;
            cArr[i3] = (char) ((bArr[i4 + 1] & 255) | (bArr[i4] << 8));
        }
        this.f12675a = cArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1BMPString(char[] cArr) {
        Objects.requireNonNull(cArr, "'string' cannot be null");
        this.f12675a = cArr;
    }

    public static ASN1BMPString getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1BMPString)) {
            return (ASN1BMPString) obj;
        }
        if (obj instanceof ASN1Encodable) {
            ASN1Primitive aSN1Primitive = ((ASN1Encodable) obj).toASN1Primitive();
            if (aSN1Primitive instanceof ASN1BMPString) {
                return (ASN1BMPString) aSN1Primitive;
            }
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1BMPString) f12674b.b((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    public static ASN1BMPString getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1BMPString) f12674b.e(aSN1TaggedObject, z);
    }

    static ASN1BMPString h(byte[] bArr) {
        return new DERBMPString(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1BMPString i(char[] cArr) {
        return new DERBMPString(cArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1BMPString) {
            return Arrays.areEqual(this.f12675a, ((ASN1BMPString) aSN1Primitive).f12675a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        int length = this.f12675a.length;
        aSN1OutputStream.q(z, 30);
        aSN1OutputStream.i(length * 2);
        byte[] bArr = new byte[8];
        int i2 = length & (-4);
        int i3 = 0;
        while (i3 < i2) {
            char[] cArr = this.f12675a;
            char c2 = cArr[i3];
            char c3 = cArr[i3 + 1];
            char c4 = cArr[i3 + 2];
            char c5 = cArr[i3 + 3];
            i3 += 4;
            bArr[0] = (byte) (c2 >> '\b');
            bArr[1] = (byte) c2;
            bArr[2] = (byte) (c3 >> '\b');
            bArr[3] = (byte) c3;
            bArr[4] = (byte) (c4 >> '\b');
            bArr[5] = (byte) c4;
            bArr[6] = (byte) (c5 >> '\b');
            bArr[7] = (byte) c5;
            aSN1OutputStream.h(bArr, 0, 8);
        }
        if (i3 < length) {
            int i4 = 0;
            do {
                char c6 = this.f12675a[i3];
                i3++;
                int i5 = i4 + 1;
                bArr[i4] = (byte) (c6 >> '\b');
                i4 = i5 + 1;
                bArr[i5] = (byte) c6;
            } while (i3 < length);
            aSN1OutputStream.h(bArr, 0, i4);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final boolean d() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public final int e(boolean z) {
        return ASN1OutputStream.e(z, this.f12675a.length * 2);
    }

    @Override // org.bouncycastle.asn1.ASN1String
    public final String getString() {
        return new String(this.f12675a);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public final int hashCode() {
        return Arrays.hashCode(this.f12675a);
    }

    public String toString() {
        return getString();
    }
}
