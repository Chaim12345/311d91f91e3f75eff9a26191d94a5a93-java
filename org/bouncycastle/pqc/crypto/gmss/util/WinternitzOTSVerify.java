package org.bouncycastle.pqc.crypto.gmss.util;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes4.dex */
public class WinternitzOTSVerify {
    private int mdsize;
    private Digest messDigestOTS;
    private int w;

    public WinternitzOTSVerify(Digest digest, int i2) {
        this.w = i2;
        this.messDigestOTS = digest;
        this.mdsize = digest.getDigestSize();
    }

    private void hashSignatureBlock(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (i3 < 1) {
            System.arraycopy(bArr, i2, bArr2, i4, this.mdsize);
            return;
        }
        this.messDigestOTS.update(bArr, i2, this.mdsize);
        while (true) {
            this.messDigestOTS.doFinal(bArr2, i4);
            i3--;
            if (i3 <= 0) {
                return;
            }
            this.messDigestOTS.update(bArr2, i4, this.mdsize);
        }
    }

    public byte[] Verify(byte[] bArr, byte[] bArr2) {
        int i2;
        int i3;
        int i4;
        WinternitzOTSVerify winternitzOTSVerify = this;
        int i5 = winternitzOTSVerify.mdsize;
        byte[] bArr3 = new byte[i5];
        int i6 = 0;
        winternitzOTSVerify.messDigestOTS.update(bArr, 0, bArr.length);
        winternitzOTSVerify.messDigestOTS.doFinal(bArr3, 0);
        int i7 = winternitzOTSVerify.w;
        int i8 = ((winternitzOTSVerify.mdsize << 3) + (i7 - 1)) / i7;
        int log = winternitzOTSVerify.getLog((i8 << i7) + 1);
        int i9 = winternitzOTSVerify.w;
        int i10 = winternitzOTSVerify.mdsize;
        int i11 = i10 * ((((log + i9) - 1) / i9) + i8);
        if (i11 != bArr2.length) {
            return null;
        }
        byte[] bArr4 = new byte[i11];
        int i12 = 8;
        if (8 % i9 == 0) {
            int i13 = 8 / i9;
            int i14 = (1 << i9) - 1;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            while (i17 < i5) {
                int i18 = i16;
                int i19 = 0;
                while (i19 < i13) {
                    int i20 = bArr3[i17] & i14;
                    int i21 = i15 + i20;
                    int i22 = winternitzOTSVerify.mdsize;
                    int i23 = i17;
                    hashSignatureBlock(bArr2, i18 * i22, i14 - i20, bArr4, i18 * i22);
                    bArr3[i23] = (byte) (bArr3[i23] >>> winternitzOTSVerify.w);
                    i18++;
                    i19++;
                    i15 = i21;
                    i17 = i23;
                    i13 = i13;
                }
                i17++;
                i16 = i18;
            }
            int i24 = i16;
            int i25 = (i8 << winternitzOTSVerify.w) - i15;
            int i26 = 0;
            while (i26 < log) {
                int i27 = winternitzOTSVerify.mdsize;
                hashSignatureBlock(bArr2, i24 * i27, i14 - (i25 & i14), bArr4, i24 * i27);
                int i28 = winternitzOTSVerify.w;
                i25 >>>= i28;
                i24++;
                i26 += i28;
            }
            i4 = 0;
            i2 = i11;
        } else {
            long j2 = 0;
            if (i9 < 8) {
                int i29 = i10 / i9;
                int i30 = (1 << i9) - 1;
                int i31 = 0;
                int i32 = 0;
                int i33 = 0;
                int i34 = 0;
                while (i34 < i29) {
                    int i35 = i31;
                    int i36 = i6;
                    long j3 = 0;
                    while (i36 < winternitzOTSVerify.w) {
                        j3 ^= (bArr3[i35] & 255) << (i36 << 3);
                        i35++;
                        i36++;
                        log = log;
                    }
                    int i37 = log;
                    int i38 = i33;
                    int i39 = 0;
                    while (i39 < i12) {
                        int i40 = (int) (j3 & i30);
                        int i41 = i32 + i40;
                        int i42 = this.mdsize;
                        winternitzOTSVerify = this;
                        hashSignatureBlock(bArr2, i38 * i42, i30 - i40, bArr4, i38 * i42);
                        j3 >>>= winternitzOTSVerify.w;
                        i38++;
                        i39++;
                        i30 = i30;
                        i12 = 8;
                        i34 = i34;
                        i32 = i41;
                    }
                    i34++;
                    i33 = i38;
                    i31 = i35;
                    log = i37;
                    i6 = 0;
                }
                int i43 = log;
                int i44 = i30;
                int i45 = winternitzOTSVerify.mdsize % winternitzOTSVerify.w;
                int i46 = 0;
                while (i46 < i45) {
                    j2 ^= (bArr3[i31] & 255) << (i46 << 3);
                    i31++;
                    i46++;
                    i32 = i32;
                    i33 = i33;
                }
                int i47 = i33;
                int i48 = i45 << 3;
                int i49 = 0;
                while (i49 < i48) {
                    int i50 = (int) (j2 & i44);
                    int i51 = i32 + i50;
                    int i52 = winternitzOTSVerify.mdsize;
                    hashSignatureBlock(bArr2, i47 * i52, i44 - i50, bArr4, i47 * i52);
                    int i53 = winternitzOTSVerify.w;
                    j2 >>>= i53;
                    i47++;
                    i49 += i53;
                    i32 = i51;
                }
                int i54 = (i8 << winternitzOTSVerify.w) - i32;
                int i55 = 0;
                while (i55 < i43) {
                    int i56 = winternitzOTSVerify.mdsize;
                    hashSignatureBlock(bArr2, i47 * i56, i44 - (i54 & i44), bArr4, i47 * i56);
                    int i57 = winternitzOTSVerify.w;
                    i54 >>>= i57;
                    i47++;
                    i55 += i57;
                }
            } else if (i9 < 57) {
                int i58 = (i10 << 3) - i9;
                int i59 = (1 << i9) - 1;
                byte[] bArr5 = new byte[i10];
                int i60 = 0;
                int i61 = 0;
                int i62 = 0;
                while (i60 <= i58) {
                    int i63 = i60 >>> 3;
                    int i64 = i60 % 8;
                    int i65 = i58;
                    int i66 = i60 + winternitzOTSVerify.w;
                    int i67 = (i66 + 7) >>> 3;
                    long j4 = 0;
                    int i68 = 0;
                    while (i63 < i67) {
                        j4 ^= (bArr3[i63] & 255) << (i68 << 3);
                        i68++;
                        i63++;
                        i67 = i67;
                        i66 = i66;
                    }
                    int i69 = i66;
                    long j5 = j4 >>> i64;
                    int i70 = i11;
                    long j6 = i59;
                    long j7 = j5 & j6;
                    int i71 = i8;
                    i61 = (int) (i61 + j7);
                    int i72 = winternitzOTSVerify.mdsize;
                    System.arraycopy(bArr2, i62 * i72, bArr5, 0, i72);
                    for (long j8 = j7; j8 < j6; j8++) {
                        winternitzOTSVerify.messDigestOTS.update(bArr5, 0, i10);
                        winternitzOTSVerify.messDigestOTS.doFinal(bArr5, 0);
                    }
                    int i73 = winternitzOTSVerify.mdsize;
                    System.arraycopy(bArr5, 0, bArr4, i62 * i73, i73);
                    i62++;
                    i58 = i65;
                    i8 = i71;
                    i11 = i70;
                    i60 = i69;
                }
                int i74 = i8;
                i2 = i11;
                int i75 = i60 >>> 3;
                if (i75 < winternitzOTSVerify.mdsize) {
                    int i76 = i60 % 8;
                    int i77 = 0;
                    while (true) {
                        i3 = winternitzOTSVerify.mdsize;
                        if (i75 >= i3) {
                            break;
                        }
                        j2 ^= (bArr3[i75] & 255) << (i77 << 3);
                        i77++;
                        i75++;
                    }
                    long j9 = i59;
                    long j10 = (j2 >>> i76) & j9;
                    i61 = (int) (i61 + j10);
                    System.arraycopy(bArr2, i62 * i3, bArr5, 0, i3);
                    while (j10 < j9) {
                        winternitzOTSVerify.messDigestOTS.update(bArr5, 0, i10);
                        winternitzOTSVerify.messDigestOTS.doFinal(bArr5, 0);
                        j10++;
                    }
                    int i78 = winternitzOTSVerify.mdsize;
                    System.arraycopy(bArr5, 0, bArr4, i62 * i78, i78);
                    i62++;
                }
                int i79 = (i74 << winternitzOTSVerify.w) - i61;
                int i80 = 0;
                while (i80 < log) {
                    int i81 = winternitzOTSVerify.mdsize;
                    System.arraycopy(bArr2, i62 * i81, bArr5, 0, i81);
                    for (long j11 = i79 & i59; j11 < i59; j11++) {
                        winternitzOTSVerify.messDigestOTS.update(bArr5, 0, i10);
                        winternitzOTSVerify.messDigestOTS.doFinal(bArr5, 0);
                    }
                    int i82 = winternitzOTSVerify.mdsize;
                    System.arraycopy(bArr5, 0, bArr4, i62 * i82, i82);
                    int i83 = winternitzOTSVerify.w;
                    i79 >>>= i83;
                    i62++;
                    i80 += i83;
                }
                i4 = 0;
            }
            i2 = i11;
            i4 = 0;
        }
        winternitzOTSVerify.messDigestOTS.update(bArr4, i4, i2);
        byte[] bArr6 = new byte[winternitzOTSVerify.mdsize];
        winternitzOTSVerify.messDigestOTS.doFinal(bArr6, i4);
        return bArr6;
    }

    public int getLog(int i2) {
        int i3 = 1;
        int i4 = 2;
        while (i4 < i2) {
            i4 <<= 1;
            i3++;
        }
        return i3;
    }

    public int getSignatureLength() {
        int digestSize = this.messDigestOTS.getDigestSize();
        int i2 = this.w;
        int i3 = ((digestSize << 3) + (i2 - 1)) / i2;
        int log = getLog((i3 << i2) + 1);
        int i4 = this.w;
        return digestSize * (i3 + (((log + i4) - 1) / i4));
    }
}
