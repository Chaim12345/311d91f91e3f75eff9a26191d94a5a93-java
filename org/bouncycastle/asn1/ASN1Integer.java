package org.bouncycastle.asn1;

import java.math.BigInteger;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Properties;
/* loaded from: classes3.dex */
public class ASN1Integer extends ASN1Primitive {

    /* renamed from: a  reason: collision with root package name */
    static final ASN1UniversalType f12696a = new ASN1UniversalType(ASN1Integer.class, 2) { // from class: org.bouncycastle.asn1.ASN1Integer.1
        @Override // org.bouncycastle.asn1.ASN1UniversalType
        ASN1Primitive d(DEROctetString dEROctetString) {
            return ASN1Integer.h(dEROctetString.getOctets());
        }
    };
    private final byte[] bytes;
    private final int start;

    public ASN1Integer(long j2) {
        this.bytes = BigInteger.valueOf(j2).toByteArray();
        this.start = 0;
    }

    public ASN1Integer(BigInteger bigInteger) {
        this.bytes = bigInteger.toByteArray();
        this.start = 0;
    }

    public ASN1Integer(byte[] bArr) {
        this(bArr, true);
    }

    ASN1Integer(byte[] bArr, boolean z) {
        if (j(bArr)) {
            throw new IllegalArgumentException("malformed integer");
        }
        this.bytes = z ? Arrays.clone(bArr) : bArr;
        this.start = l(bArr);
    }

    public static ASN1Integer getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Integer)) {
            return (ASN1Integer) obj;
        }
        if (!(obj instanceof byte[])) {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        try {
            return (ASN1Integer) f12696a.b((byte[]) obj);
        } catch (Exception e2) {
            throw new IllegalArgumentException("encoding error in getInstance: " + e2.toString());
        }
    }

    public static ASN1Integer getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return (ASN1Integer) f12696a.e(aSN1TaggedObject, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Integer h(byte[] bArr) {
        return new ASN1Integer(bArr, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(byte[] bArr, int i2, int i3) {
        int length = bArr.length;
        int max = Math.max(i2, length - 4);
        int i4 = i3 & bArr[max];
        while (true) {
            max++;
            if (max >= length) {
                return i4;
            }
            i4 = (i4 << 8) | (bArr[max] & 255);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean j(byte[] bArr) {
        int length = bArr.length;
        if (length != 0) {
            if (length != 1) {
                return bArr[0] == (bArr[1] >> 7) && !Properties.isOverrideSet("org.bouncycastle.asn1.allow_unsafe_integer");
            }
            return false;
        }
        return true;
    }

    static long k(byte[] bArr, int i2, int i3) {
        int length = bArr.length;
        int max = Math.max(i2, length - 8);
        long j2 = i3 & bArr[max];
        while (true) {
            max++;
            if (max >= length) {
                return j2;
            }
            j2 = (j2 << 8) | (bArr[max] & 255);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int l(byte[] bArr) {
        int length = bArr.length - 1;
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            if (bArr[i2] != (bArr[i3] >> 7)) {
                break;
            }
            i2 = i3;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean b(ASN1Primitive aSN1Primitive) {
        if (aSN1Primitive instanceof ASN1Integer) {
            return Arrays.areEqual(this.bytes, ((ASN1Integer) aSN1Primitive).bytes);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public void c(ASN1OutputStream aSN1OutputStream, boolean z) {
        aSN1OutputStream.m(z, 2, this.bytes);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean d() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int e(boolean z) {
        return ASN1OutputStream.e(z, this.bytes.length);
    }

    public BigInteger getPositiveValue() {
        return new BigInteger(1, this.bytes);
    }

    public BigInteger getValue() {
        return new BigInteger(this.bytes);
    }

    public boolean hasValue(int i2) {
        byte[] bArr = this.bytes;
        int length = bArr.length;
        int i3 = this.start;
        return length - i3 <= 4 && i(bArr, i3, -1) == i2;
    }

    public boolean hasValue(long j2) {
        byte[] bArr = this.bytes;
        int length = bArr.length;
        int i2 = this.start;
        return length - i2 <= 8 && k(bArr, i2, -1) == j2;
    }

    public boolean hasValue(BigInteger bigInteger) {
        return bigInteger != null && i(this.bytes, this.start, -1) == bigInteger.intValue() && getValue().equals(bigInteger);
    }

    @Override // org.bouncycastle.asn1.ASN1Primitive, org.bouncycastle.asn1.ASN1Object
    public int hashCode() {
        return Arrays.hashCode(this.bytes);
    }

    public int intPositiveValueExact() {
        byte[] bArr = this.bytes;
        int length = bArr.length;
        int i2 = this.start;
        int i3 = length - i2;
        if (i3 > 4 || (i3 == 4 && (bArr[i2] & 128) != 0)) {
            throw new ArithmeticException("ASN.1 Integer out of positive int range");
        }
        return i(bArr, i2, 255);
    }

    public int intValueExact() {
        byte[] bArr = this.bytes;
        int length = bArr.length;
        int i2 = this.start;
        if (length - i2 <= 4) {
            return i(bArr, i2, -1);
        }
        throw new ArithmeticException("ASN.1 Integer out of int range");
    }

    public long longValueExact() {
        byte[] bArr = this.bytes;
        int length = bArr.length;
        int i2 = this.start;
        if (length - i2 <= 8) {
            return k(bArr, i2, -1);
        }
        throw new ArithmeticException("ASN.1 Integer out of long range");
    }

    public String toString() {
        return getValue().toString();
    }
}
