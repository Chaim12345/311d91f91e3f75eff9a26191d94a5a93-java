package org.bouncycastle.util.encoders;

import com.facebook.stetho.dumpapp.Framer;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
/* loaded from: classes4.dex */
public class HexEncoder implements Encoder {

    /* renamed from: a  reason: collision with root package name */
    protected final byte[] f15111a = {48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* renamed from: b  reason: collision with root package name */
    protected final byte[] f15112b = new byte[128];

    public HexEncoder() {
        b();
    }

    private static boolean ignore(char c2) {
        return c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == ' ';
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a(String str, int i2, int i3) {
        Objects.requireNonNull(str, "'str' cannot be null");
        if (i2 < 0 || i3 < 0 || i2 > str.length() - i3) {
            throw new IndexOutOfBoundsException("invalid offset and/or length specified");
        }
        if ((i3 & 1) == 0) {
            int i4 = i3 >>> 1;
            byte[] bArr = new byte[i4];
            int i5 = 0;
            while (i5 < i4) {
                int i6 = i2 + 1;
                int i7 = i6 + 1;
                int i8 = (this.f15112b[str.charAt(i2)] << 4) | this.f15112b[str.charAt(i6)];
                if (i8 < 0) {
                    throw new IOException("invalid characters encountered in Hex string");
                }
                bArr[i5] = (byte) i8;
                i5++;
                i2 = i7;
            }
            return bArr;
        }
        throw new IOException("a hexadecimal encoding must have an even number of characters");
    }

    protected void b() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.f15112b;
            if (i3 >= bArr.length) {
                break;
            }
            bArr[i3] = -1;
            i3++;
        }
        while (true) {
            byte[] bArr2 = this.f15111a;
            if (i2 >= bArr2.length) {
                byte[] bArr3 = this.f15112b;
                bArr3[65] = bArr3[97];
                bArr3[66] = bArr3[98];
                bArr3[67] = bArr3[99];
                bArr3[68] = bArr3[100];
                bArr3[69] = bArr3[101];
                bArr3[70] = bArr3[102];
                return;
            }
            this.f15112b[bArr2[i2]] = (byte) i2;
            i2++;
        }
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) {
        byte[] bArr = new byte[36];
        int length = str.length();
        while (length > 0 && ignore(str.charAt(length - 1))) {
            length--;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            while (i2 < length && ignore(str.charAt(i2))) {
                i2++;
            }
            int i5 = i2 + 1;
            byte b2 = this.f15112b[str.charAt(i2)];
            while (i5 < length && ignore(str.charAt(i5))) {
                i5++;
            }
            int i6 = i5 + 1;
            byte b3 = this.f15112b[str.charAt(i5)];
            if ((b2 | b3) < 0) {
                throw new IOException("invalid characters encountered in Hex string");
            }
            int i7 = i3 + 1;
            bArr[i3] = (byte) ((b2 << 4) | b3);
            if (i7 == 36) {
                outputStream.write(bArr);
                i3 = 0;
            } else {
                i3 = i7;
            }
            i4++;
            i2 = i6;
        }
        if (i3 > 0) {
            outputStream.write(bArr, 0, i3);
        }
        return i4;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int i2, int i3, OutputStream outputStream) {
        byte[] bArr2 = new byte[36];
        int i4 = i3 + i2;
        while (i4 > i2 && ignore((char) bArr[i4 - 1])) {
            i4--;
        }
        int i5 = 0;
        int i6 = 0;
        while (i2 < i4) {
            while (i2 < i4 && ignore((char) bArr[i2])) {
                i2++;
            }
            int i7 = i2 + 1;
            byte b2 = this.f15112b[bArr[i2]];
            while (i7 < i4 && ignore((char) bArr[i7])) {
                i7++;
            }
            int i8 = i7 + 1;
            byte b3 = this.f15112b[bArr[i7]];
            if ((b2 | b3) < 0) {
                throw new IOException("invalid characters encountered in Hex data");
            }
            int i9 = i5 + 1;
            bArr2[i5] = (byte) ((b2 << 4) | b3);
            if (i9 == 36) {
                outputStream.write(bArr2);
                i5 = 0;
            } else {
                i5 = i9;
            }
            i6++;
            i2 = i8;
        }
        if (i5 > 0) {
            outputStream.write(bArr2, 0, i5);
        }
        return i6;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int encode(byte[] bArr, int i2, int i3, OutputStream outputStream) {
        if (i3 < 0) {
            return 0;
        }
        byte[] bArr2 = new byte[72];
        int i4 = i3;
        while (i4 > 0) {
            int min = Math.min(36, i4);
            outputStream.write(bArr2, 0, encode(bArr, i2, min, bArr2, 0));
            i2 += min;
            i4 -= min;
        }
        return i3 * 2;
    }

    public int encode(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        int i5 = i3 + i2;
        int i6 = i4;
        while (i2 < i5) {
            int i7 = i2 + 1;
            int i8 = bArr[i2] & 255;
            int i9 = i6 + 1;
            byte[] bArr3 = this.f15111a;
            bArr2[i6] = bArr3[i8 >>> 4];
            i6 = i9 + 1;
            bArr2[i9] = bArr3[i8 & 15];
            i2 = i7;
        }
        return i6 - i4;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getEncodedLength(int i2) {
        return i2 * 2;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getMaxDecodedLength(int i2) {
        return i2 / 2;
    }
}
