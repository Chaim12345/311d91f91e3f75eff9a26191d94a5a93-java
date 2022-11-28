package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class CramerShoupCiphertext {

    /* renamed from: a  reason: collision with root package name */
    BigInteger f13351a;

    /* renamed from: b  reason: collision with root package name */
    BigInteger f13352b;

    /* renamed from: c  reason: collision with root package name */
    BigInteger f13353c;

    /* renamed from: d  reason: collision with root package name */
    BigInteger f13354d;

    public CramerShoupCiphertext() {
    }

    public CramerShoupCiphertext(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f13351a = bigInteger;
        this.f13352b = bigInteger2;
        this.f13353c = bigInteger3;
        this.f13354d = bigInteger4;
    }

    public CramerShoupCiphertext(byte[] bArr) {
        int bigEndianToInt = Pack.bigEndianToInt(bArr, 0) + 4;
        this.f13351a = new BigInteger(Arrays.copyOfRange(bArr, 4, bigEndianToInt));
        int bigEndianToInt2 = Pack.bigEndianToInt(bArr, bigEndianToInt);
        int i2 = bigEndianToInt + 4;
        int i3 = bigEndianToInt2 + i2;
        this.f13352b = new BigInteger(Arrays.copyOfRange(bArr, i2, i3));
        int bigEndianToInt3 = Pack.bigEndianToInt(bArr, i3);
        int i4 = i3 + 4;
        int i5 = bigEndianToInt3 + i4;
        this.f13353c = new BigInteger(Arrays.copyOfRange(bArr, i4, i5));
        int bigEndianToInt4 = Pack.bigEndianToInt(bArr, i5);
        int i6 = i5 + 4;
        this.f13354d = new BigInteger(Arrays.copyOfRange(bArr, i6, bigEndianToInt4 + i6));
    }

    public BigInteger getE() {
        return this.f13353c;
    }

    public BigInteger getU1() {
        return this.f13351a;
    }

    public BigInteger getU2() {
        return this.f13352b;
    }

    public BigInteger getV() {
        return this.f13354d;
    }

    public void setE(BigInteger bigInteger) {
        this.f13353c = bigInteger;
    }

    public void setU1(BigInteger bigInteger) {
        this.f13351a = bigInteger;
    }

    public void setU2(BigInteger bigInteger) {
        this.f13352b = bigInteger;
    }

    public void setV(BigInteger bigInteger) {
        this.f13354d = bigInteger;
    }

    public byte[] toByteArray() {
        byte[] byteArray = this.f13351a.toByteArray();
        int length = byteArray.length;
        byte[] byteArray2 = this.f13352b.toByteArray();
        int length2 = byteArray2.length;
        byte[] byteArray3 = this.f13353c.toByteArray();
        int length3 = byteArray3.length;
        byte[] byteArray4 = this.f13354d.toByteArray();
        int length4 = byteArray4.length;
        byte[] bArr = new byte[length + length2 + length3 + length4 + 16];
        Pack.intToBigEndian(length, bArr, 0);
        System.arraycopy(byteArray, 0, bArr, 4, length);
        int i2 = length + 4;
        Pack.intToBigEndian(length2, bArr, i2);
        int i3 = i2 + 4;
        System.arraycopy(byteArray2, 0, bArr, i3, length2);
        int i4 = i3 + length2;
        Pack.intToBigEndian(length3, bArr, i4);
        int i5 = i4 + 4;
        System.arraycopy(byteArray3, 0, bArr, i5, length3);
        int i6 = i5 + length3;
        Pack.intToBigEndian(length4, bArr, i6);
        System.arraycopy(byteArray4, 0, bArr, i6 + 4, length4);
        return bArr;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("u1: " + this.f13351a.toString());
        stringBuffer.append("\nu2: " + this.f13352b.toString());
        stringBuffer.append("\ne: " + this.f13353c.toString());
        stringBuffer.append("\nv: " + this.f13354d.toString());
        return stringBuffer.toString();
    }
}
