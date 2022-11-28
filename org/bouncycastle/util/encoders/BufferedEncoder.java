package org.bouncycastle.util.encoders;
/* loaded from: classes4.dex */
public class BufferedEncoder {

    /* renamed from: a  reason: collision with root package name */
    protected byte[] f15108a;

    /* renamed from: b  reason: collision with root package name */
    protected int f15109b;

    /* renamed from: c  reason: collision with root package name */
    protected Translator f15110c;

    public BufferedEncoder(Translator translator, int i2) {
        this.f15110c = translator;
        if (i2 % translator.getEncodedBlockSize() != 0) {
            throw new IllegalArgumentException("buffer size not multiple of input block size");
        }
        this.f15108a = new byte[i2];
        this.f15109b = 0;
    }

    public int processByte(byte b2, byte[] bArr, int i2) {
        byte[] bArr2 = this.f15108a;
        int i3 = this.f15109b;
        int i4 = i3 + 1;
        this.f15109b = i4;
        bArr2[i3] = b2;
        if (i4 == bArr2.length) {
            int encode = this.f15110c.encode(bArr2, 0, bArr2.length, bArr, i2);
            this.f15109b = 0;
            return encode;
        }
        return 0;
    }

    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (i3 >= 0) {
            byte[] bArr3 = this.f15108a;
            int length = bArr3.length;
            int i5 = this.f15109b;
            int i6 = length - i5;
            int i7 = 0;
            if (i3 > i6) {
                System.arraycopy(bArr, i2, bArr3, i5, i6);
                Translator translator = this.f15110c;
                byte[] bArr4 = this.f15108a;
                int encode = translator.encode(bArr4, 0, bArr4.length, bArr2, i4) + 0;
                this.f15109b = 0;
                int i8 = i3 - i6;
                int i9 = i2 + i6;
                int i10 = i4 + encode;
                int length2 = i8 - (i8 % this.f15108a.length);
                i7 = encode + this.f15110c.encode(bArr, i9, length2, bArr2, i10);
                i3 = i8 - length2;
                i2 = i9 + length2;
            }
            if (i3 != 0) {
                System.arraycopy(bArr, i2, this.f15108a, this.f15109b, i3);
                this.f15109b += i3;
            }
            return i7;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }
}
