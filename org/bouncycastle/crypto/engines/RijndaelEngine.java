package org.bouncycastle.crypto.engines;

import androidx.recyclerview.widget.ItemTouchHelper;
import com.facebook.stetho.dumpapp.Framer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.lang.reflect.Array;
import okio.Utf8;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.tls.CipherSuite;
/* loaded from: classes3.dex */
public class RijndaelEngine implements BlockCipher {
    private static final int MAXKC = 64;
    private static final int MAXROUNDS = 14;
    private long A0;
    private long A1;
    private long A2;
    private long A3;
    private int BC;
    private long BC_MASK;
    private int ROUNDS;
    private int blockBits;
    private boolean forEncryption;
    private byte[] shifts0SC;
    private byte[] shifts1SC;
    private long[][] workingKey;
    private static final byte[] logtable = {0, 0, Ascii.EM, 1, Framer.STDERR_FRAME_PREFIX, 2, Ascii.SUB, -58, 75, -57, Ascii.ESC, 104, 51, -18, -33, 3, 100, 4, -32, Ascii.SO, 52, -115, -127, ByteSourceJsonBootstrapper.UTF8_BOM_1, 76, 113, 8, -56, -8, 105, Ascii.FS, -63, 125, -62, Ascii.GS, -75, -7, -71, 39, 106, 77, -28, -90, 114, -102, -55, 9, Framer.EXIT_FRAME_PREFIX, 101, 47, -118, 5, Framer.ENTER_FRAME_PREFIX, Ascii.SI, -31, 36, Ascii.DC2, -16, -126, 69, 53, -109, -38, -114, -106, -113, -37, -67, 54, -48, -50, -108, 19, 92, -46, -15, SignedBytes.MAX_POWER_OF_TWO, 70, -125, 56, 102, -35, -3, 48, ByteSourceJsonBootstrapper.UTF8_BOM_3, 6, -117, 98, -77, 37, -30, -104, 34, -120, -111, 16, 126, 110, 72, -61, -93, -74, Ascii.RS, 66, 58, 107, 40, 84, -6, -123, 61, -70, 43, 121, 10, Ascii.NAK, -101, -97, 94, -54, 78, -44, -84, -27, -13, 115, -89, 87, -81, 88, -88, 80, -12, -22, -42, 116, 79, -82, -23, -43, -25, -26, -83, -24, 44, -41, 117, 122, -21, Ascii.SYN, Ascii.VT, -11, 89, -53, Framer.STDIN_REQUEST_FRAME_PREFIX, -80, -100, -87, 81, -96, Byte.MAX_VALUE, Ascii.FF, -10, 111, Ascii.ETB, -60, 73, -20, -40, 67, Ascii.US, Framer.STDIN_FRAME_PREFIX, -92, 118, 123, -73, -52, ByteSourceJsonBootstrapper.UTF8_BOM_2, 62, 90, -5, 96, -79, -122, 59, 82, -95, 108, -86, 85, 41, -99, -105, -78, -121, -112, 97, -66, -36, -4, PSSSigner.TRAILER_IMPLICIT, -107, -49, -51, 55, Utf8.REPLACEMENT_BYTE, 91, -47, 83, 57, -124, 60, 65, -94, 109, 71, Ascii.DC4, 42, -98, 93, 86, -14, -45, -85, 68, 17, -110, -39, 35, 32, 46, -119, -76, 124, -72, 38, 119, -103, -29, -91, 103, 74, -19, -34, -59, Framer.STDOUT_FRAME_PREFIX, -2, Ascii.CAN, Ascii.CR, 99, -116, Byte.MIN_VALUE, -64, -9, 112, 7};
    private static final byte[] aLogtable = {0, 3, 5, Ascii.SI, 17, 51, 85, -1, Ascii.SUB, 46, 114, -106, -95, -8, 19, 53, Framer.STDIN_REQUEST_FRAME_PREFIX, -31, 56, 72, -40, 115, -107, -92, -9, 2, 6, 10, Ascii.RS, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, 112, -112, -85, -26, Framer.STDOUT_FRAME_PREFIX, 83, -11, 4, Ascii.FF, Ascii.DC4, 60, 68, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, -32, 59, 77, -41, 98, -90, -15, 8, Ascii.CAN, 40, Framer.EXIT_FRAME_PREFIX, -120, -125, -98, -71, -48, 107, -67, -36, Byte.MAX_VALUE, -127, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, 48, 80, -16, Ascii.VT, Ascii.GS, 39, 105, ByteSourceJsonBootstrapper.UTF8_BOM_2, -42, 97, -93, -2, Ascii.EM, 43, 125, -121, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, -96, -5, Ascii.SYN, 58, 78, -46, 109, -73, -62, 93, -25, Framer.STDERR_FRAME_PREFIX, 86, -6, Ascii.NAK, Utf8.REPLACEMENT_BYTE, 65, -61, 94, -30, 61, 71, -55, SignedBytes.MAX_POWER_OF_TWO, -64, 91, -19, 44, 116, -100, ByteSourceJsonBootstrapper.UTF8_BOM_3, -38, 117, -97, -70, -43, 100, -84, ByteSourceJsonBootstrapper.UTF8_BOM_1, 42, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, -119, Byte.MIN_VALUE, -101, -74, -63, 88, -24, 35, 101, -81, -22, 37, 111, -79, -56, 67, -59, 84, -4, Ascii.US, Framer.ENTER_FRAME_PREFIX, 99, -91, -12, 7, 9, Ascii.ESC, Framer.STDIN_FRAME_PREFIX, 119, -103, -80, -53, 70, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, Ascii.SO, Ascii.DC2, 54, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, -89, -14, Ascii.CR, Ascii.ETB, 57, 75, -35, 124, -124, -105, -94, -3, Ascii.FS, 36, 108, -76, -57, 82, -10, 1, 3, 5, Ascii.SI, 17, 51, 85, -1, Ascii.SUB, 46, 114, -106, -95, -8, 19, 53, Framer.STDIN_REQUEST_FRAME_PREFIX, -31, 56, 72, -40, 115, -107, -92, -9, 2, 6, 10, Ascii.RS, 34, 102, -86, -27, 52, 92, -28, 55, 89, -21, 38, 106, -66, -39, 112, -112, -85, -26, Framer.STDOUT_FRAME_PREFIX, 83, -11, 4, Ascii.FF, Ascii.DC4, 60, 68, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, -32, 59, 77, -41, 98, -90, -15, 8, Ascii.CAN, 40, Framer.EXIT_FRAME_PREFIX, -120, -125, -98, -71, -48, 107, -67, -36, Byte.MAX_VALUE, -127, -104, -77, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, 48, 80, -16, Ascii.VT, Ascii.GS, 39, 105, ByteSourceJsonBootstrapper.UTF8_BOM_2, -42, 97, -93, -2, Ascii.EM, 43, 125, -121, -110, -83, -20, 47, 113, -109, -82, -23, 32, 96, -96, -5, Ascii.SYN, 58, 78, -46, 109, -73, -62, 93, -25, Framer.STDERR_FRAME_PREFIX, 86, -6, Ascii.NAK, Utf8.REPLACEMENT_BYTE, 65, -61, 94, -30, 61, 71, -55, SignedBytes.MAX_POWER_OF_TWO, -64, 91, -19, 44, 116, -100, ByteSourceJsonBootstrapper.UTF8_BOM_3, -38, 117, -97, -70, -43, 100, -84, ByteSourceJsonBootstrapper.UTF8_BOM_1, 42, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, -114, -119, Byte.MIN_VALUE, -101, -74, -63, 88, -24, 35, 101, -81, -22, 37, 111, -79, -56, 67, -59, 84, -4, Ascii.US, Framer.ENTER_FRAME_PREFIX, 99, -91, -12, 7, 9, Ascii.ESC, Framer.STDIN_FRAME_PREFIX, 119, -103, -80, -53, 70, -54, 69, -49, 74, -34, 121, -117, -122, -111, -88, -29, 62, 66, -58, 81, -13, Ascii.SO, Ascii.DC2, 54, 90, -18, 41, 123, -115, -116, -113, -118, -123, -108, -89, -14, Ascii.CR, Ascii.ETB, 57, 75, -35, 124, -124, -105, -94, -3, Ascii.FS, 36, 108, -76, -57, 82, -10, 1};
    private static final byte[] S = {99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, Utf8.REPLACEMENT_BYTE, -9, -52, 52, -91, -27, -15, 113, -40, Framer.STDOUT_FRAME_PREFIX, Ascii.NAK, 4, -57, 35, -61, Ascii.CAN, -106, 5, -102, 7, Ascii.DC2, Byte.MIN_VALUE, -30, -21, 39, -78, 117, 9, -125, 44, Ascii.SUB, Ascii.ESC, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, ByteSourceJsonBootstrapper.UTF8_BOM_1, -86, -5, 67, 77, 51, -123, 69, -7, 2, Byte.MAX_VALUE, 80, 60, -97, -88, 81, -93, SignedBytes.MAX_POWER_OF_TWO, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, -74, -38, Framer.ENTER_FRAME_PREFIX, 16, -1, -13, -46, -51, Ascii.FF, 19, -20, Framer.STDIN_REQUEST_FRAME_PREFIX, -105, 68, Ascii.ETB, -60, -89, 126, 61, 100, 93, Ascii.EM, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, Ascii.DC4, -34, 94, Ascii.VT, -37, -32, Framer.STDERR_FRAME_PREFIX, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, Framer.EXIT_FRAME_PREFIX, 37, 46, Ascii.FS, -90, -76, -58, -24, -35, 116, Ascii.US, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, Ascii.SO, 97, 53, 87, -71, -122, -63, Ascii.GS, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, Ascii.RS, -121, -23, -50, 85, 40, -33, -116, -95, -119, Ascii.CR, ByteSourceJsonBootstrapper.UTF8_BOM_3, -26, 66, 104, 65, -103, Framer.STDIN_FRAME_PREFIX, Ascii.SI, -80, 84, ByteSourceJsonBootstrapper.UTF8_BOM_2, Ascii.SYN};
    private static final byte[] Si = {82, 9, 106, -43, 48, 54, -91, 56, ByteSourceJsonBootstrapper.UTF8_BOM_3, SignedBytes.MAX_POWER_OF_TWO, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, Framer.STDERR_FRAME_PREFIX, -90, -62, 35, 61, -18, 76, -107, Ascii.VT, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, Ascii.SYN, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, Ascii.NAK, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, Ascii.RS, -113, -54, Utf8.REPLACEMENT_BYTE, Ascii.SI, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, Ascii.FS, 117, -33, 110, 71, -15, Ascii.SUB, 113, Ascii.GS, 41, -59, -119, 111, -73, 98, Ascii.SO, -86, Ascii.CAN, -66, Ascii.ESC, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, Framer.EXIT_FRAME_PREFIX, -51, 90, -12, Ascii.US, -35, -88, 51, -120, 7, -57, Framer.STDOUT_FRAME_PREFIX, -79, Ascii.DC2, 16, 89, 39, Byte.MIN_VALUE, -20, Framer.STDIN_REQUEST_FRAME_PREFIX, 96, 81, Byte.MAX_VALUE, -87, Ascii.EM, -75, 74, Ascii.CR, Framer.STDIN_FRAME_PREFIX, -27, 122, -97, -109, -55, -100, ByteSourceJsonBootstrapper.UTF8_BOM_1, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, ByteSourceJsonBootstrapper.UTF8_BOM_2, 60, -125, 83, -103, 97, Ascii.ETB, 43, 4, 126, -70, 119, -42, 38, -31, 105, Ascii.DC4, 99, 85, Framer.ENTER_FRAME_PREFIX, Ascii.FF, 125};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, 216, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 77, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 47, 94, 188, 99, CipherSuite.TLS_SM4_GCM_SM3, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 53, 106, 212, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 125, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 145};

    /* renamed from: a  reason: collision with root package name */
    static byte[][] f13386a = {new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, Ascii.CAN}, new byte[]{0, 8, 16, 32}, new byte[]{0, 8, Ascii.CAN, 32}};

    /* renamed from: b  reason: collision with root package name */
    static byte[][] f13387b = {new byte[]{0, Ascii.CAN, 16, 8}, new byte[]{0, 32, Ascii.CAN, 16}, new byte[]{0, 40, 32, Ascii.CAN}, new byte[]{0, 48, 40, Ascii.CAN}, new byte[]{0, 56, 40, 32}};

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int i2) {
        if (i2 == 128) {
            this.BC = 32;
            this.BC_MASK = BodyPartID.bodyIdMax;
            this.shifts0SC = f13386a[0];
            this.shifts1SC = f13387b[0];
        } else if (i2 == 160) {
            this.BC = 40;
            this.BC_MASK = 1099511627775L;
            this.shifts0SC = f13386a[1];
            this.shifts1SC = f13387b[1];
        } else if (i2 == 192) {
            this.BC = 48;
            this.BC_MASK = 281474976710655L;
            this.shifts0SC = f13386a[2];
            this.shifts1SC = f13387b[2];
        } else if (i2 == 224) {
            this.BC = 56;
            this.BC_MASK = 72057594037927935L;
            this.shifts0SC = f13386a[3];
            this.shifts1SC = f13387b[3];
        } else if (i2 != 256) {
            throw new IllegalArgumentException("unknown blocksize to Rijndael");
        } else {
            this.BC = 64;
            this.BC_MASK = -1L;
            this.shifts0SC = f13386a[4];
            this.shifts1SC = f13387b[4];
        }
        this.blockBits = i2;
    }

    private void InvMixColumn() {
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        for (int i2 = 0; i2 < this.BC; i2 += 8) {
            int i3 = (int) ((this.A0 >> i2) & 255);
            int i4 = (int) ((this.A1 >> i2) & 255);
            int i5 = (int) ((this.A2 >> i2) & 255);
            long j6 = j4;
            int i6 = (int) ((this.A3 >> i2) & 255);
            int i7 = -1;
            int i8 = i3 != 0 ? logtable[i3 & 255] & 255 : -1;
            int i9 = i4 != 0 ? logtable[i4 & 255] & 255 : -1;
            int i10 = i5 != 0 ? logtable[i5 & 255] & 255 : -1;
            if (i6 != 0) {
                i7 = logtable[i6 & 255] & 255;
            }
            j2 |= ((((mul0xe(i8) ^ mul0xb(i9)) ^ mul0xd(i10)) ^ mul0x9(i7)) & 255) << i2;
            j5 |= ((((mul0xe(i9) ^ mul0xb(i10)) ^ mul0xd(i7)) ^ mul0x9(i8)) & 255) << i2;
            j3 |= ((((mul0xe(i10) ^ mul0xb(i7)) ^ mul0xd(i8)) ^ mul0x9(i9)) & 255) << i2;
            j4 = (((((mul0xe(i7) ^ mul0xb(i8)) ^ mul0xd(i9)) ^ mul0x9(i10)) & 255) << i2) | j6;
        }
        this.A0 = j2;
        this.A1 = j5;
        this.A2 = j3;
        this.A3 = j4;
    }

    private void KeyAddition(long[] jArr) {
        this.A0 ^= jArr[0];
        this.A1 ^= jArr[1];
        this.A2 ^= jArr[2];
        this.A3 ^= jArr[3];
    }

    private void MixColumn() {
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        for (int i2 = 0; i2 < this.BC; i2 += 8) {
            int i3 = (int) ((this.A0 >> i2) & 255);
            int i4 = (int) ((this.A1 >> i2) & 255);
            int i5 = (int) ((this.A2 >> i2) & 255);
            long j6 = j4;
            int i6 = (int) ((this.A3 >> i2) & 255);
            j2 |= ((((mul0x2(i3) ^ mul0x3(i4)) ^ i5) ^ i6) & 255) << i2;
            j5 |= ((((mul0x2(i4) ^ mul0x3(i5)) ^ i6) ^ i3) & 255) << i2;
            j3 |= ((((mul0x2(i5) ^ mul0x3(i6)) ^ i3) ^ i4) & 255) << i2;
            j4 = (((((mul0x2(i6) ^ mul0x3(i3)) ^ i4) ^ i5) & 255) << i2) | j6;
        }
        this.A0 = j2;
        this.A1 = j5;
        this.A2 = j3;
        this.A3 = j4;
    }

    private void ShiftRow(byte[] bArr) {
        this.A1 = shift(this.A1, bArr[1]);
        this.A2 = shift(this.A2, bArr[2]);
        this.A3 = shift(this.A3, bArr[3]);
    }

    private void Substitution(byte[] bArr) {
        this.A0 = applyS(this.A0, bArr);
        this.A1 = applyS(this.A1, bArr);
        this.A2 = applyS(this.A2, bArr);
        this.A3 = applyS(this.A3, bArr);
    }

    private long applyS(long j2, byte[] bArr) {
        long j3 = 0;
        for (int i2 = 0; i2 < this.BC; i2 += 8) {
            j3 |= (bArr[(int) ((j2 >> i2) & 255)] & 255) << i2;
        }
        return j3;
    }

    private void decryptBlock(long[][] jArr) {
        KeyAddition(jArr[this.ROUNDS]);
        Substitution(Si);
        ShiftRow(this.shifts1SC);
        for (int i2 = this.ROUNDS - 1; i2 > 0; i2--) {
            KeyAddition(jArr[i2]);
            InvMixColumn();
            Substitution(Si);
            ShiftRow(this.shifts1SC);
        }
        KeyAddition(jArr[0]);
    }

    private void encryptBlock(long[][] jArr) {
        KeyAddition(jArr[0]);
        for (int i2 = 1; i2 < this.ROUNDS; i2++) {
            Substitution(S);
            ShiftRow(this.shifts0SC);
            MixColumn();
            KeyAddition(jArr[i2]);
        }
        Substitution(S);
        ShiftRow(this.shifts0SC);
        KeyAddition(jArr[this.ROUNDS]);
    }

    private long[][] generateWorkingKey(byte[] bArr) {
        int i2;
        int i3;
        int i4 = 8;
        int length = bArr.length * 8;
        byte[][] bArr2 = (byte[][]) Array.newInstance(byte.class, 4, 64);
        long[][] jArr = (long[][]) Array.newInstance(long.class, 15, 4);
        int i5 = 4;
        if (length == 128) {
            i2 = 4;
        } else if (length == 160) {
            i2 = 5;
        } else if (length == 192) {
            i2 = 6;
        } else if (length == 224) {
            i2 = 7;
        } else if (length != 256) {
            throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
        } else {
            i2 = 8;
        }
        this.ROUNDS = length >= this.blockBits ? i2 + 6 : (this.BC / 8) + 6;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i7 < bArr.length) {
            bArr2[i7 % 4][i7 / 4] = bArr[i8];
            i7++;
            i8++;
        }
        int i9 = 0;
        int i10 = 0;
        while (i9 < i2 && i10 < (this.ROUNDS + 1) * (this.BC / 8)) {
            int i11 = 0;
            while (i11 < i5) {
                int i12 = this.BC;
                long[] jArr2 = jArr[i10 / (i12 / 8)];
                jArr2[i11] = ((bArr2[i11][i9] & 255) << ((i10 * 8) % i12)) | jArr2[i11];
                i11++;
                i5 = 4;
            }
            i9++;
            i10++;
            i5 = 4;
        }
        int i13 = 0;
        while (i10 < (this.ROUNDS + 1) * (this.BC / i4)) {
            int i14 = i6;
            while (i14 < 4) {
                byte[] bArr3 = bArr2[i14];
                i14++;
                bArr3[i6] = (byte) (bArr3[i6] ^ S[bArr2[i14 % 4][i2 - 1] & 255]);
            }
            byte[] bArr4 = bArr2[i6];
            int i15 = i13 + 1;
            bArr4[i6] = (byte) (rcon[i13] ^ bArr4[i6]);
            int i16 = 1;
            if (i2 <= 6) {
                while (i16 < i2) {
                    for (int i17 = i6; i17 < 4; i17++) {
                        byte[] bArr5 = bArr2[i17];
                        bArr5[i16] = (byte) (bArr5[i16] ^ bArr2[i17][i16 - 1]);
                    }
                    i16++;
                }
            } else {
                while (true) {
                    i3 = 4;
                    if (i16 >= 4) {
                        break;
                    }
                    int i18 = i6;
                    while (i18 < i3) {
                        byte[] bArr6 = bArr2[i18];
                        bArr6[i16] = (byte) (bArr6[i16] ^ bArr2[i18][i16 - 1]);
                        i18++;
                        i3 = 4;
                    }
                    i16++;
                }
                for (int i19 = i6; i19 < 4; i19++) {
                    byte[] bArr7 = bArr2[i19];
                    bArr7[4] = (byte) (bArr7[4] ^ S[bArr2[i19][3] & 255]);
                }
                int i20 = 5;
                while (i20 < i2) {
                    int i21 = i6;
                    while (i21 < i3) {
                        byte[] bArr8 = bArr2[i21];
                        bArr8[i20] = (byte) (bArr8[i20] ^ bArr2[i21][i20 - 1]);
                        i21++;
                        i3 = 4;
                    }
                    i20++;
                    i3 = 4;
                }
            }
            int i22 = i6;
            while (i22 < i2 && i10 < (this.ROUNDS + 1) * (this.BC / i4)) {
                for (int i23 = i6; i23 < 4; i23++) {
                    int i24 = this.BC;
                    long[] jArr3 = jArr[i10 / (i24 / 8)];
                    jArr3[i23] = ((bArr2[i23][i22] & 255) << ((i10 * 8) % i24)) | jArr3[i23];
                }
                i22++;
                i10++;
                i6 = 0;
                i4 = 8;
            }
            i13 = i15;
            i6 = 0;
            i4 = 8;
        }
        return jArr;
    }

    private byte mul0x2(int i2) {
        if (i2 != 0) {
            return aLogtable[(logtable[i2] & 255) + 25];
        }
        return (byte) 0;
    }

    private byte mul0x3(int i2) {
        if (i2 != 0) {
            return aLogtable[(logtable[i2] & 255) + 1];
        }
        return (byte) 0;
    }

    private byte mul0x9(int i2) {
        if (i2 >= 0) {
            return aLogtable[i2 + CipherSuite.TLS_SM4_CCM_SM3];
        }
        return (byte) 0;
    }

    private byte mul0xb(int i2) {
        if (i2 >= 0) {
            return aLogtable[i2 + 104];
        }
        return (byte) 0;
    }

    private byte mul0xd(int i2) {
        if (i2 >= 0) {
            return aLogtable[i2 + 238];
        }
        return (byte) 0;
    }

    private byte mul0xe(int i2) {
        if (i2 >= 0) {
            return aLogtable[i2 + 223];
        }
        return (byte) 0;
    }

    private void packBlock(byte[] bArr, int i2) {
        for (int i3 = 0; i3 != this.BC; i3 += 8) {
            int i4 = i2 + 1;
            bArr[i2] = (byte) (this.A0 >> i3);
            int i5 = i4 + 1;
            bArr[i4] = (byte) (this.A1 >> i3);
            int i6 = i5 + 1;
            bArr[i5] = (byte) (this.A2 >> i3);
            i2 = i6 + 1;
            bArr[i6] = (byte) (this.A3 >> i3);
        }
    }

    private long shift(long j2, int i2) {
        return ((j2 << (this.BC - i2)) | (j2 >>> i2)) & this.BC_MASK;
    }

    private void unpackBlock(byte[] bArr, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        this.A0 = bArr[i2] & 255;
        this.A1 = bArr[i3] & 255;
        this.A2 = bArr[i4] & 255;
        int i9 = i2 + 1 + 1 + 1 + 1;
        this.A3 = bArr[i5] & 255;
        for (int i10 = 8; i10 != this.BC; i10 += 8) {
            this.A0 |= (bArr[i9] & 255) << i10;
            this.A1 |= (bArr[i6] & 255) << i10;
            this.A2 |= (bArr[i7] & 255) << i10;
            i9 = i9 + 1 + 1 + 1 + 1;
            this.A3 |= (bArr[i8] & 255) << i10;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Rijndael";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.BC / 2;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) cipherParameters).getKey());
            this.forEncryption = z;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i2, byte[] bArr2, int i3) {
        if (this.workingKey != null) {
            int i4 = this.BC;
            if ((i4 / 2) + i2 <= bArr.length) {
                if ((i4 / 2) + i3 <= bArr2.length) {
                    boolean z = this.forEncryption;
                    unpackBlock(bArr, i2);
                    long[][] jArr = this.workingKey;
                    if (z) {
                        encryptBlock(jArr);
                    } else {
                        decryptBlock(jArr);
                    }
                    packBlock(bArr2, i3);
                    return this.BC / 2;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("Rijndael engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
