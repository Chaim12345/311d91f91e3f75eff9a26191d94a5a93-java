package org.bouncycastle.util.encoders;

import com.facebook.stetho.dumpapp.Framer;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes4.dex */
public class Base64Encoder implements Encoder {

    /* renamed from: a  reason: collision with root package name */
    protected final byte[] f15102a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, Framer.EXIT_FRAME_PREFIX, 121, 122, 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* renamed from: b  reason: collision with root package name */
    protected byte f15103b = 61;

    /* renamed from: c  reason: collision with root package name */
    protected final byte[] f15104c = new byte[128];

    public Base64Encoder() {
        a();
    }

    private int decodeLastBlock(OutputStream outputStream, char c2, char c3, char c4, char c5) {
        byte b2 = this.f15103b;
        if (c4 == b2) {
            if (c5 == b2) {
                byte[] bArr = this.f15104c;
                byte b3 = bArr[c2];
                byte b4 = bArr[c3];
                if ((b3 | b4) >= 0) {
                    outputStream.write((b3 << 2) | (b4 >> 4));
                    return 1;
                }
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else if (c5 == b2) {
            byte[] bArr2 = this.f15104c;
            byte b5 = bArr2[c2];
            byte b6 = bArr2[c3];
            byte b7 = bArr2[c4];
            if ((b5 | b6 | b7) >= 0) {
                outputStream.write((b5 << 2) | (b6 >> 4));
                outputStream.write((b6 << 4) | (b7 >> 2));
                return 2;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else {
            byte[] bArr3 = this.f15104c;
            byte b8 = bArr3[c2];
            byte b9 = bArr3[c3];
            byte b10 = bArr3[c4];
            byte b11 = bArr3[c5];
            if ((b8 | b9 | b10 | b11) >= 0) {
                outputStream.write((b8 << 2) | (b9 >> 4));
                outputStream.write((b9 << 4) | (b10 >> 2));
                outputStream.write((b10 << 6) | b11);
                return 3;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        }
    }

    private boolean ignore(char c2) {
        return c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == ' ';
    }

    private int nextI(String str, int i2, int i3) {
        while (i2 < i3 && ignore(str.charAt(i2))) {
            i2++;
        }
        return i2;
    }

    private int nextI(byte[] bArr, int i2, int i3) {
        while (i2 < i3 && ignore((char) bArr[i2])) {
            i2++;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a() {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte[] bArr = this.f15104c;
            if (i3 >= bArr.length) {
                break;
            }
            bArr[i3] = -1;
            i3++;
        }
        while (true) {
            byte[] bArr2 = this.f15102a;
            if (i2 >= bArr2.length) {
                return;
            }
            this.f15104c[bArr2[i2]] = (byte) i2;
            i2++;
        }
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) {
        byte[] bArr = new byte[54];
        int length = str.length();
        while (length > 0 && ignore(str.charAt(length - 1))) {
            length--;
        }
        if (length == 0) {
            return 0;
        }
        int i2 = length;
        int i3 = 0;
        while (i2 > 0 && i3 != 4) {
            if (!ignore(str.charAt(i2 - 1))) {
                i3++;
            }
            i2--;
        }
        int nextI = nextI(str, 0, i2);
        int i4 = 0;
        int i5 = 0;
        while (nextI < i2) {
            int i6 = nextI + 1;
            byte b2 = this.f15104c[str.charAt(nextI)];
            int nextI2 = nextI(str, i6, i2);
            int i7 = nextI2 + 1;
            byte b3 = this.f15104c[str.charAt(nextI2)];
            int nextI3 = nextI(str, i7, i2);
            int i8 = nextI3 + 1;
            byte b4 = this.f15104c[str.charAt(nextI3)];
            int nextI4 = nextI(str, i8, i2);
            int i9 = nextI4 + 1;
            byte b5 = this.f15104c[str.charAt(nextI4)];
            if ((b2 | b3 | b4 | b5) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            int i10 = i4 + 1;
            bArr[i4] = (byte) ((b2 << 2) | (b3 >> 4));
            int i11 = i10 + 1;
            bArr[i10] = (byte) ((b3 << 4) | (b4 >> 2));
            i4 = i11 + 1;
            bArr[i11] = (byte) ((b4 << 6) | b5);
            i5 += 3;
            if (i4 == 54) {
                outputStream.write(bArr);
                i4 = 0;
            }
            nextI = nextI(str, i9, i2);
        }
        if (i4 > 0) {
            outputStream.write(bArr, 0, i4);
        }
        int nextI5 = nextI(str, nextI, length);
        int nextI6 = nextI(str, nextI5 + 1, length);
        int nextI7 = nextI(str, nextI6 + 1, length);
        return i5 + decodeLastBlock(outputStream, str.charAt(nextI5), str.charAt(nextI6), str.charAt(nextI7), str.charAt(nextI(str, nextI7 + 1, length)));
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int i2, int i3, OutputStream outputStream) {
        byte[] bArr2 = new byte[54];
        int i4 = i2 + i3;
        while (i4 > i2 && ignore((char) bArr[i4 - 1])) {
            i4--;
        }
        if (i4 == 0) {
            return 0;
        }
        int i5 = i4;
        int i6 = 0;
        while (i5 > i2 && i6 != 4) {
            if (!ignore((char) bArr[i5 - 1])) {
                i6++;
            }
            i5--;
        }
        int nextI = nextI(bArr, i2, i5);
        int i7 = 0;
        int i8 = 0;
        while (nextI < i5) {
            int i9 = nextI + 1;
            byte b2 = this.f15104c[bArr[nextI]];
            int nextI2 = nextI(bArr, i9, i5);
            int i10 = nextI2 + 1;
            byte b3 = this.f15104c[bArr[nextI2]];
            int nextI3 = nextI(bArr, i10, i5);
            int i11 = nextI3 + 1;
            byte b4 = this.f15104c[bArr[nextI3]];
            int nextI4 = nextI(bArr, i11, i5);
            int i12 = nextI4 + 1;
            byte b5 = this.f15104c[bArr[nextI4]];
            if ((b2 | b3 | b4 | b5) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            int i13 = i7 + 1;
            bArr2[i7] = (byte) ((b2 << 2) | (b3 >> 4));
            int i14 = i13 + 1;
            bArr2[i13] = (byte) ((b3 << 4) | (b4 >> 2));
            i7 = i14 + 1;
            bArr2[i14] = (byte) ((b4 << 6) | b5);
            if (i7 == 54) {
                outputStream.write(bArr2);
                i7 = 0;
            }
            i8 += 3;
            nextI = nextI(bArr, i12, i5);
        }
        if (i7 > 0) {
            outputStream.write(bArr2, 0, i7);
        }
        int nextI5 = nextI(bArr, nextI, i4);
        int nextI6 = nextI(bArr, nextI5 + 1, i4);
        int nextI7 = nextI(bArr, nextI6 + 1, i4);
        return i8 + decodeLastBlock(outputStream, (char) bArr[nextI5], (char) bArr[nextI6], (char) bArr[nextI7], (char) bArr[nextI(bArr, nextI7 + 1, i4)]);
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int encode(byte[] bArr, int i2, int i3, OutputStream outputStream) {
        if (i3 < 0) {
            return 0;
        }
        byte[] bArr2 = new byte[72];
        int i4 = i3;
        while (i4 > 0) {
            int min = Math.min(54, i4);
            outputStream.write(bArr2, 0, encode(bArr, i2, min, bArr2, 0));
            i2 += min;
            i4 -= min;
        }
        return ((i3 + 2) / 3) * 4;
    }

    public int encode(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        int i5 = (i2 + i3) - 2;
        int i6 = i2;
        int i7 = i4;
        while (i6 < i5) {
            int i8 = i6 + 1;
            byte b2 = bArr[i6];
            int i9 = i8 + 1;
            int i10 = bArr[i8] & 255;
            int i11 = i9 + 1;
            int i12 = bArr[i9] & 255;
            int i13 = i7 + 1;
            byte[] bArr3 = this.f15102a;
            bArr2[i7] = bArr3[(b2 >>> 2) & 63];
            int i14 = i13 + 1;
            bArr2[i13] = bArr3[((b2 << 4) | (i10 >>> 4)) & 63];
            int i15 = i14 + 1;
            bArr2[i14] = bArr3[((i10 << 2) | (i12 >>> 6)) & 63];
            i7 = i15 + 1;
            bArr2[i15] = bArr3[i12 & 63];
            i6 = i11;
        }
        int i16 = i3 - (i6 - i2);
        if (i16 == 1) {
            int i17 = bArr[i6] & 255;
            int i18 = i7 + 1;
            byte[] bArr4 = this.f15102a;
            bArr2[i7] = bArr4[(i17 >>> 2) & 63];
            int i19 = i18 + 1;
            bArr2[i18] = bArr4[(i17 << 4) & 63];
            int i20 = i19 + 1;
            byte b3 = this.f15103b;
            bArr2[i19] = b3;
            i7 = i20 + 1;
            bArr2[i20] = b3;
        } else if (i16 == 2) {
            int i21 = bArr[i6] & 255;
            int i22 = bArr[i6 + 1] & 255;
            int i23 = i7 + 1;
            byte[] bArr5 = this.f15102a;
            bArr2[i7] = bArr5[(i21 >>> 2) & 63];
            int i24 = i23 + 1;
            bArr2[i23] = bArr5[((i21 << 4) | (i22 >>> 4)) & 63];
            int i25 = i24 + 1;
            bArr2[i24] = bArr5[(i22 << 2) & 63];
            i7 = i25 + 1;
            bArr2[i25] = this.f15103b;
        }
        return i7 - i4;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getEncodedLength(int i2) {
        return ((i2 + 2) / 3) * 4;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int getMaxDecodedLength(int i2) {
        return (i2 / 4) * 3;
    }
}
