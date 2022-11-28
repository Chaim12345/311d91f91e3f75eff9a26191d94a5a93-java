package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.facebook.stetho.dumpapp.Framer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.lang.reflect.Array;
import okio.Utf8;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int m1 = -2139062144;
    private static final int m2 = 2139062143;
    private static final int m3 = 27;
    private static final int m4 = -1061109568;
    private static final int m5 = 1061109567;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;
    private static final byte[] S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, Framer.STDOUT_FRAME_PREFIX, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, Framer.ENTER_FRAME_PREFIX, 16, -1, -13, -46, -51, Ascii.FF, 19, -20, Framer.STDIN_REQUEST_FRAME_PREFIX, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.EM, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.VT, -37, -32, Framer.STDERR_FRAME_PREFIX, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, Framer.EXIT_FRAME_PREFIX, 37, 46, Ascii.FS, -90, -76, -58, -24, -35, 116, Ascii.US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.SO, 97, 53, 87, -71, -122, -63, Ascii.GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, Ascii.CR, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, -103, Framer.STDIN_FRAME_PREFIX, Ascii.SI, -80, 84, ByteSourceJsonBootstrapper.UTF8_BOM_2, Ascii.SYN};
    private static final byte[] Si = {82, 9, 106, -43, 48, 54, -91, 56, ByteSourceJsonBootstrapper.UTF8_BOM_3, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, Framer.STDERR_FRAME_PREFIX, -90, -62, 35, 61, -18, 76, -107, Ascii.VT, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, Ascii.RS, -113, -54, Utf8.REPLACEMENT_BYTE, Ascii.SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.GS, 41, -59, -119, 111, -73, 98, Ascii.SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, Framer.EXIT_FRAME_PREFIX, -51, 90, -12, Ascii.US, -35, -88, 51, -120, 7, -57, Framer.STDOUT_FRAME_PREFIX, -79, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, Framer.STDIN_REQUEST_FRAME_PREFIX, 96, 81, Byte.MAX_VALUE, -87, Ascii.EM, -75, 74, Ascii.CR, Framer.STDIN_FRAME_PREFIX, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, ByteSourceJsonBootstrapper.UTF8_BOM_2, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, Framer.ENTER_FRAME_PREFIX, Ascii.FF, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 77, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 47, 94, 188, 99, CipherSuite.TLS_SM4_GCM_SM3, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 53, 106, 212, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 145};

    private static int FFmulX(int i2) {
        return (((i2 & m1) >>> 7) * 27) ^ ((m2 & i2) << 1);
    }

    private static int FFmulX2(int i2) {
        int i3 = i2 & m4;
        int i4 = i3 ^ (i3 >>> 1);
        return (i4 >>> 5) ^ (((m5 & i2) << 2) ^ (i4 >>> 2));
    }

    private void decryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3, int[][] iArr) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i2 + 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i2 + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i2 + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i2 + 12);
        int i4 = this.ROUNDS;
        int i5 = littleEndianToInt ^ iArr[i4][0];
        int i6 = littleEndianToInt2 ^ iArr[i4][1];
        int i7 = littleEndianToInt3 ^ iArr[i4][2];
        int i8 = i4 - 1;
        int i9 = littleEndianToInt4 ^ iArr[i4][3];
        while (true) {
            byte[] bArr3 = Si;
            int i10 = i5 & 255;
            if (i8 <= 1) {
                int inv_mcol = inv_mcol((((bArr3[i10] & 255) ^ ((bArr3[(i9 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i7 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i6 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][0];
                int inv_mcol2 = inv_mcol((((bArr3[i6 & 255] & 255) ^ ((bArr3[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i9 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][1];
                int inv_mcol3 = inv_mcol((((bArr3[i7 & 255] & 255) ^ ((bArr3[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i9 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][2];
                int inv_mcol4 = inv_mcol((((bArr3[i9 & 255] & 255) ^ ((bArr3[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][3];
                int i11 = ((((bArr3[inv_mcol & 255] & 255) ^ ((bArr3[(inv_mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol2 >> 24) & 255] << Ascii.CAN)) ^ iArr[0][0];
                int i12 = ((((bArr3[inv_mcol2 & 255] & 255) ^ ((bArr3[(inv_mcol >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol4 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol3 >> 24) & 255] << Ascii.CAN)) ^ iArr[0][1];
                int i13 = ((((bArr3[inv_mcol3 & 255] & 255) ^ ((bArr3[(inv_mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol4 >> 24) & 255] << Ascii.CAN)) ^ iArr[0][2];
                int i14 = ((((bArr3[inv_mcol4 & 255] & 255) ^ ((bArr3[(inv_mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol >> 24) & 255] << Ascii.CAN)) ^ iArr[0][3];
                Pack.intToLittleEndian(i11, bArr2, i3 + 0);
                Pack.intToLittleEndian(i12, bArr2, i3 + 4);
                Pack.intToLittleEndian(i13, bArr2, i3 + 8);
                Pack.intToLittleEndian(i14, bArr2, i3 + 12);
                return;
            }
            int inv_mcol5 = inv_mcol((((bArr3[i10] & 255) ^ ((bArr3[(i9 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i7 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i6 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][0];
            int inv_mcol6 = inv_mcol((((bArr3[i6 & 255] & 255) ^ ((bArr3[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i9 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][1];
            int inv_mcol7 = inv_mcol((((bArr3[i7 & 255] & 255) ^ ((bArr3[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i9 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][2];
            int i15 = i8 - 1;
            int inv_mcol8 = inv_mcol((((bArr3[i9 & 255] & 255) ^ ((bArr3[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][3];
            int inv_mcol9 = inv_mcol((((bArr3[inv_mcol5 & 255] & 255) ^ ((bArr3[(inv_mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol7 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol6 >> 24) & 255] << Ascii.CAN)) ^ iArr[i15][0];
            int inv_mcol10 = inv_mcol((((bArr3[inv_mcol6 & 255] & 255) ^ ((bArr3[(inv_mcol5 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol8 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i15][1];
            int i16 = i15 - 1;
            i9 = inv_mcol((((bArr3[inv_mcol8 & 255] & 255) ^ ((bArr3[(inv_mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol6 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i15][3];
            i5 = inv_mcol9;
            i6 = inv_mcol10;
            i7 = inv_mcol((((bArr3[inv_mcol7 & 255] & 255) ^ ((bArr3[(inv_mcol6 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(inv_mcol5 >> 16) & 255] & 255) << 16)) ^ (bArr3[(inv_mcol8 >> 24) & 255] << Ascii.CAN)) ^ iArr[i15][2];
            i8 = i16;
        }
    }

    private void encryptBlock(byte[] bArr, int i2, byte[] bArr2, int i3, int[][] iArr) {
        int littleEndianToInt = Pack.littleEndianToInt(bArr, i2 + 0);
        int littleEndianToInt2 = Pack.littleEndianToInt(bArr, i2 + 4);
        int littleEndianToInt3 = Pack.littleEndianToInt(bArr, i2 + 8);
        int littleEndianToInt4 = Pack.littleEndianToInt(bArr, i2 + 12);
        int i4 = littleEndianToInt ^ iArr[0][0];
        int i5 = littleEndianToInt2 ^ iArr[0][1];
        int i6 = littleEndianToInt3 ^ iArr[0][2];
        int i7 = littleEndianToInt4 ^ iArr[0][3];
        int i8 = 1;
        while (i8 < this.ROUNDS - 1) {
            byte[] bArr3 = S;
            int mcol = mcol((((bArr3[i4 & 255] & 255) ^ ((bArr3[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i6 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][0];
            int mcol2 = mcol((((bArr3[i5 & 255] & 255) ^ ((bArr3[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i7 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][1];
            int mcol3 = mcol((((bArr3[i6 & 255] & 255) ^ ((bArr3[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][2];
            int i9 = i8 + 1;
            int mcol4 = mcol((((bArr3[i7 & 255] & 255) ^ ((bArr3[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr3[(i6 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][3];
            int mcol5 = mcol((((bArr3[mcol & 255] & 255) ^ ((bArr3[(mcol2 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(mcol3 >> 16) & 255] & 255) << 16)) ^ (bArr3[(mcol4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][0];
            int mcol6 = mcol((((bArr3[mcol2 & 255] & 255) ^ ((bArr3[(mcol3 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(mcol4 >> 16) & 255] & 255) << 16)) ^ (bArr3[(mcol >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][1];
            int i10 = i9 + 1;
            i7 = mcol((((bArr3[mcol4 & 255] & 255) ^ ((bArr3[(mcol >> 8) & 255] & 255) << 8)) ^ ((bArr3[(mcol2 >> 16) & 255] & 255) << 16)) ^ (bArr3[(mcol3 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][3];
            i4 = mcol5;
            i5 = mcol6;
            i6 = mcol((((bArr3[mcol3 & 255] & 255) ^ ((bArr3[(mcol4 >> 8) & 255] & 255) << 8)) ^ ((bArr3[(mcol >> 16) & 255] & 255) << 16)) ^ (bArr3[(mcol2 >> 24) & 255] << Ascii.CAN)) ^ iArr[i9][2];
            i8 = i10;
        }
        byte[] bArr4 = S;
        int mcol7 = mcol((((bArr4[i4 & 255] & 255) ^ ((bArr4[(i5 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(i6 >> 16) & 255] & 255) << 16)) ^ (bArr4[(i7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][0];
        int mcol8 = mcol((((bArr4[i5 & 255] & 255) ^ ((bArr4[(i6 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(i7 >> 16) & 255] & 255) << 16)) ^ (bArr4[(i4 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][1];
        int mcol9 = mcol((((bArr4[i6 & 255] & 255) ^ ((bArr4[(i7 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(i4 >> 16) & 255] & 255) << 16)) ^ (bArr4[(i5 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][2];
        int i11 = i8 + 1;
        int mcol10 = mcol((((bArr4[i7 & 255] & 255) ^ ((bArr4[(i4 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(i5 >> 16) & 255] & 255) << 16)) ^ (bArr4[(i6 >> 24) & 255] << Ascii.CAN)) ^ iArr[i8][3];
        int i12 = ((((bArr4[mcol7 & 255] & 255) ^ ((bArr4[(mcol8 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(mcol9 >> 16) & 255] & 255) << 16)) ^ (bArr4[(mcol10 >> 24) & 255] << Ascii.CAN)) ^ iArr[i11][0];
        int i13 = ((((bArr4[mcol8 & 255] & 255) ^ ((bArr4[(mcol9 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(mcol10 >> 16) & 255] & 255) << 16)) ^ (bArr4[(mcol7 >> 24) & 255] << Ascii.CAN)) ^ iArr[i11][1];
        int i14 = ((((bArr4[mcol9 & 255] & 255) ^ ((bArr4[(mcol10 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(mcol7 >> 16) & 255] & 255) << 16)) ^ (bArr4[(mcol8 >> 24) & 255] << Ascii.CAN)) ^ iArr[i11][2];
        int i15 = ((((bArr4[mcol10 & 255] & 255) ^ ((bArr4[(mcol7 >> 8) & 255] & 255) << 8)) ^ ((bArr4[(mcol8 >> 16) & 255] & 255) << 16)) ^ (bArr4[(mcol9 >> 24) & 255] << Ascii.CAN)) ^ iArr[i11][3];
        Pack.intToLittleEndian(i12, bArr2, i3 + 0);
        Pack.intToLittleEndian(i13, bArr2, i3 + 4);
        Pack.intToLittleEndian(i14, bArr2, i3 + 8);
        Pack.intToLittleEndian(i15, bArr2, i3 + 12);
    }

    private int[][] generateWorkingKey(byte[] bArr, boolean z) {
        int length = bArr.length;
        if (length < 16 || length > 32 || (length & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int i2 = length >>> 2;
        int i3 = i2 + 6;
        this.ROUNDS = i3;
        int[][] iArr = (int[][]) Array.newInstance(int.class, i3 + 1, 4);
        int i4 = 8;
        char c2 = 3;
        if (i2 == 4) {
            int littleEndianToInt = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt;
            int littleEndianToInt2 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt2;
            int littleEndianToInt3 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt3;
            int littleEndianToInt4 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt4;
            for (int i5 = 1; i5 <= 10; i5++) {
                littleEndianToInt ^= subWord(shift(littleEndianToInt4, 8)) ^ rcon[i5 - 1];
                iArr[i5][0] = littleEndianToInt;
                littleEndianToInt2 ^= littleEndianToInt;
                iArr[i5][1] = littleEndianToInt2;
                littleEndianToInt3 ^= littleEndianToInt2;
                iArr[i5][2] = littleEndianToInt3;
                littleEndianToInt4 ^= littleEndianToInt3;
                iArr[i5][3] = littleEndianToInt4;
            }
        } else if (i2 == 6) {
            int littleEndianToInt5 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt5;
            int littleEndianToInt6 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt6;
            int littleEndianToInt7 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt7;
            int littleEndianToInt8 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt8;
            int littleEndianToInt9 = Pack.littleEndianToInt(bArr, 16);
            int littleEndianToInt10 = Pack.littleEndianToInt(bArr, 20);
            int i6 = 1;
            int i7 = 1;
            while (true) {
                iArr[i6][0] = littleEndianToInt9;
                iArr[i6][1] = littleEndianToInt10;
                int subWord = subWord(shift(littleEndianToInt10, 8)) ^ i7;
                int i8 = i7 << 1;
                int i9 = littleEndianToInt5 ^ subWord;
                iArr[i6][2] = i9;
                int i10 = littleEndianToInt6 ^ i9;
                iArr[i6][3] = i10;
                int i11 = littleEndianToInt7 ^ i10;
                int i12 = i6 + 1;
                iArr[i12][0] = i11;
                int i13 = littleEndianToInt8 ^ i11;
                iArr[i12][1] = i13;
                int i14 = littleEndianToInt9 ^ i13;
                iArr[i12][2] = i14;
                int i15 = littleEndianToInt10 ^ i14;
                iArr[i12][3] = i15;
                int subWord2 = subWord(shift(i15, 8)) ^ i8;
                i7 = i8 << 1;
                littleEndianToInt5 = i9 ^ subWord2;
                int i16 = i6 + 2;
                iArr[i16][0] = littleEndianToInt5;
                littleEndianToInt6 = i10 ^ littleEndianToInt5;
                iArr[i16][1] = littleEndianToInt6;
                littleEndianToInt7 = i11 ^ littleEndianToInt6;
                iArr[i16][2] = littleEndianToInt7;
                littleEndianToInt8 = i13 ^ littleEndianToInt7;
                iArr[i16][3] = littleEndianToInt8;
                i6 += 3;
                if (i6 >= 13) {
                    break;
                }
                littleEndianToInt9 = i14 ^ littleEndianToInt8;
                littleEndianToInt10 = i15 ^ littleEndianToInt9;
            }
        } else if (i2 != 8) {
            throw new IllegalStateException("Should never get here");
        } else {
            int littleEndianToInt11 = Pack.littleEndianToInt(bArr, 0);
            iArr[0][0] = littleEndianToInt11;
            int littleEndianToInt12 = Pack.littleEndianToInt(bArr, 4);
            iArr[0][1] = littleEndianToInt12;
            int littleEndianToInt13 = Pack.littleEndianToInt(bArr, 8);
            iArr[0][2] = littleEndianToInt13;
            int littleEndianToInt14 = Pack.littleEndianToInt(bArr, 12);
            iArr[0][3] = littleEndianToInt14;
            int littleEndianToInt15 = Pack.littleEndianToInt(bArr, 16);
            iArr[1][0] = littleEndianToInt15;
            int littleEndianToInt16 = Pack.littleEndianToInt(bArr, 20);
            iArr[1][1] = littleEndianToInt16;
            int littleEndianToInt17 = Pack.littleEndianToInt(bArr, 24);
            iArr[1][2] = littleEndianToInt17;
            int littleEndianToInt18 = Pack.littleEndianToInt(bArr, 28);
            iArr[1][3] = littleEndianToInt18;
            int i17 = 2;
            int i18 = 1;
            while (true) {
                int subWord3 = subWord(shift(littleEndianToInt18, i4)) ^ i18;
                i18 <<= 1;
                littleEndianToInt11 ^= subWord3;
                iArr[i17][0] = littleEndianToInt11;
                littleEndianToInt12 ^= littleEndianToInt11;
                iArr[i17][1] = littleEndianToInt12;
                littleEndianToInt13 ^= littleEndianToInt12;
                iArr[i17][2] = littleEndianToInt13;
                littleEndianToInt14 ^= littleEndianToInt13;
                iArr[i17][c2] = littleEndianToInt14;
                int i19 = i17 + 1;
                if (i19 >= 15) {
                    break;
                }
                littleEndianToInt15 ^= subWord(littleEndianToInt14);
                iArr[i19][0] = littleEndianToInt15;
                littleEndianToInt16 ^= littleEndianToInt15;
                iArr[i19][1] = littleEndianToInt16;
                littleEndianToInt17 ^= littleEndianToInt16;
                iArr[i19][2] = littleEndianToInt17;
                littleEndianToInt18 ^= littleEndianToInt17;
                iArr[i19][3] = littleEndianToInt18;
                i17 = i19 + 1;
                i4 = 8;
                c2 = 3;
            }
        }
        if (!z) {
            for (int i20 = 1; i20 < this.ROUNDS; i20++) {
                for (int i21 = 0; i21 < 4; i21++) {
                    iArr[i20][i21] = inv_mcol(iArr[i20][i21]);
                }
            }
        }
        return iArr;
    }

    private static int inv_mcol(int i2) {
        int shift = shift(i2, 8) ^ i2;
        int FFmulX = i2 ^ FFmulX(shift);
        int FFmulX2 = shift ^ FFmulX2(FFmulX);
        return FFmulX ^ (FFmulX2 ^ shift(FFmulX2, 16));
    }

    private static int mcol(int i2) {
        int shift = shift(i2, 8);
        int i3 = i2 ^ shift;
        return FFmulX(i3) ^ (shift ^ shift(i3, 16));
    }

    private static int shift(int i2, int i3) {
        return (i2 << (-i3)) | (i2 >>> i3);
    }

    private static int subWord(int i2) {
        byte[] bArr = S;
        return (bArr[(i2 >> 24) & 255] << Ascii.CAN) | (bArr[i2 & 255] & 255) | ((bArr[(i2 >> 8) & 255] & 255) << 8) | ((bArr[(i2 >> 16) & 255] & 255) << 16);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "AES";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey(), z);
            this.forEncryption = z;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to AES init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        int[][] iArr = this.WorkingKey;
        if (iArr != null) {
            if (i2 <= bArr.length - 16) {
                if (i3 <= bArr2.length - 16) {
                    if (this.forEncryption) {
                        encryptBlock(bArr, i2, bArr2, i3, iArr);
                    } else {
                        decryptBlock(bArr, i2, bArr2, i3, iArr);
                    }
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("AES engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
