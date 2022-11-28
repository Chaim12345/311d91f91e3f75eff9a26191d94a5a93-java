package org.bouncycastle.crypto.engines;

import com.facebook.stetho.dumpapp.Framer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import kotlin.UShort;
import okio.Utf8;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Memoable;
/* loaded from: classes3.dex */
public class Zuc128CoreEngine implements StreamCipher, Memoable {
    private final int[] BRC;
    private final int[] F;
    private final int[] LFSR;
    private final byte[] keyStream;
    private int theIndex;
    private int theIterations;
    private Zuc128CoreEngine theResetState;
    private static final byte[] S0 = {62, 114, 91, 71, -54, -32, 0, 51, 4, -47, 84, -104, 9, -71, 109, -53, 123, Ascii.ESC, -7, Framer.STDERR_FRAME_PREFIX, -81, -99, 106, -91, -72, Framer.STDIN_FRAME_PREFIX, -4, Ascii.GS, 8, 83, 3, -112, 77, 78, -124, -103, -28, -50, -39, -111, -35, -74, -123, 72, -117, 41, 110, -84, -51, -63, -8, Ascii.RS, 115, 67, 105, -58, -75, -67, -3, 57, 99, 32, -44, 56, 118, 125, -78, -89, -49, -19, 87, -59, -13, 44, ByteSourceJsonBootstrapper.UTF8_BOM_2, Ascii.DC4, Framer.ENTER_FRAME_PREFIX, 6, 85, -101, -29, ByteSourceJsonBootstrapper.UTF8_BOM_1, 94, Framer.STDOUT_FRAME_PREFIX, 79, Byte.MAX_VALUE, 90, -92, Ascii.CR, -126, 81, 73, Framer.STDIN_REQUEST_FRAME_PREFIX, -70, 88, Ascii.FS, 74, Ascii.SYN, -43, Ascii.ETB, -88, -110, 36, Ascii.US, -116, -1, -40, -82, 46, 1, -45, -83, 59, 75, -38, 70, -21, -55, -34, -102, -113, -121, -41, 58, Byte.MIN_VALUE, 111, 47, -56, -79, -76, 55, -9, 10, 34, 19, 40, 124, -52, 60, -119, -57, -61, -106, 86, 7, ByteSourceJsonBootstrapper.UTF8_BOM_3, 126, -16, Ascii.VT, 43, -105, 82, 53, 65, 121, 97, -90, 76, 16, -2, PSSSigner.TRAILER_IMPLICIT, 38, -107, -120, -118, -80, -93, -5, -64, Ascii.CAN, -108, -14, -31, -27, -23, 93, -48, -36, 17, 102, 100, 92, -20, 89, 66, 117, Ascii.DC2, -11, 116, -100, -86, 35, Ascii.SO, -122, -85, -66, 42, 2, -25, 103, -26, 68, -94, 108, -62, -109, -97, -15, -10, -6, 54, -46, 80, 104, -98, 98, 113, Ascii.NAK, 61, -42, SignedBytes.MAX_POWER_OF_TWO, -60, -30, Ascii.SI, -114, -125, 119, 107, 37, 5, Utf8.REPLACEMENT_BYTE, Ascii.FF, 48, -22, 112, -73, -95, -24, -87, 101, -115, 39, Ascii.SUB, -37, -127, -77, -96, -12, 69, 122, Ascii.EM, -33, -18, Framer.EXIT_FRAME_PREFIX, 52, 96};
    private static final byte[] S1 = {85, -62, 99, 113, 59, -56, 71, -122, -97, 60, -38, 91, 41, -86, -3, 119, -116, -59, -108, Ascii.FF, -90, Ascii.SUB, 19, 0, -29, -88, Ascii.SYN, 114, SignedBytes.MAX_POWER_OF_TWO, -7, -8, 66, 68, 38, 104, -106, -127, -39, 69, 62, 16, 118, -58, -89, -117, 57, 67, -31, 58, -75, 86, 42, -64, 109, -77, 5, 34, 102, ByteSourceJsonBootstrapper.UTF8_BOM_3, -36, Ascii.VT, -6, 98, 72, -35, 32, 17, 6, 54, -55, -63, -49, -10, 39, 82, ByteSourceJsonBootstrapper.UTF8_BOM_2, 105, -11, -44, -121, Byte.MAX_VALUE, -124, 76, -46, -100, 87, -92, PSSSigner.TRAILER_IMPLICIT, 79, -102, -33, -2, -42, -115, 122, -21, 43, 83, -40, 92, -95, Ascii.DC4, Ascii.ETB, -5, 35, -43, 125, 48, 103, 115, 8, 9, -18, -73, 112, Utf8.REPLACEMENT_BYTE, 97, -78, Ascii.EM, -114, 78, -27, 75, -109, -113, 93, -37, -87, -83, -15, -82, 46, -53, Ascii.CR, -4, -12, Framer.STDIN_FRAME_PREFIX, 70, 110, Ascii.GS, -105, -24, -47, -23, 77, 55, -91, 117, 94, -125, -98, -85, -126, -99, -71, Ascii.FS, -32, -51, 73, -119, 1, -74, -67, 88, 36, -94, Framer.STDIN_REQUEST_FRAME_PREFIX, 56, Framer.EXIT_FRAME_PREFIX, -103, Ascii.NAK, -112, 80, -72, -107, -28, -48, -111, -57, -50, -19, Ascii.SI, -76, 111, -96, -52, -16, 2, 74, 121, -61, -34, -93, ByteSourceJsonBootstrapper.UTF8_BOM_1, -22, 81, -26, 107, Ascii.CAN, -20, Ascii.ESC, 44, Byte.MIN_VALUE, -9, 116, -25, -1, Framer.ENTER_FRAME_PREFIX, 90, 106, 84, Ascii.RS, 65, Framer.STDOUT_FRAME_PREFIX, -110, 53, -60, 51, 7, 10, -70, 126, Ascii.SO, 52, -120, -79, -104, 124, -13, 61, 96, 108, 123, -54, -45, Ascii.US, Framer.STDERR_FRAME_PREFIX, 101, 4, 40, 100, -66, -123, -101, 47, 89, -118, -41, -80, 37, -84, -81, Ascii.DC2, 3, -30, -14};
    private static final short[] EK_d = {17623, 9916, 25195, 4958, 22409, 13794, 28981, 2479, 19832, 12051, 27588, 6897, 24102, 15437, 30874, 18348};

    /* JADX INFO: Access modifiers changed from: protected */
    public Zuc128CoreEngine() {
        this.LFSR = new int[16];
        this.F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Zuc128CoreEngine(Zuc128CoreEngine zuc128CoreEngine) {
        this.LFSR = new int[16];
        this.F = new int[2];
        this.BRC = new int[4];
        this.keyStream = new byte[4];
        reset(zuc128CoreEngine);
    }

    private int AddM(int i2, int i3) {
        int i4 = i2 + i3;
        return (Integer.MAX_VALUE & i4) + (i4 >>> 31);
    }

    private void BitReorganization() {
        int[] iArr = this.BRC;
        int[] iArr2 = this.LFSR;
        iArr[0] = ((iArr2[15] & 2147450880) << 1) | (iArr2[14] & 65535);
        iArr[1] = ((iArr2[11] & 65535) << 16) | (iArr2[9] >>> 15);
        iArr[2] = ((iArr2[7] & 65535) << 16) | (iArr2[5] >>> 15);
        iArr[3] = (iArr2[0] >>> 15) | ((iArr2[2] & 65535) << 16);
    }

    private static int L1(int i2) {
        return b(i2, 24) ^ (((b(i2, 2) ^ i2) ^ b(i2, 10)) ^ b(i2, 18));
    }

    private static int L2(int i2) {
        return b(i2, 30) ^ (((b(i2, 8) ^ i2) ^ b(i2, 14)) ^ b(i2, 22));
    }

    private void LFSRWithInitialisationMode(int i2) {
        int[] iArr = this.LFSR;
        int AddM = AddM(AddM(AddM(AddM(AddM(AddM(iArr[0], MulByPow2(iArr[0], 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15)), i2);
        int[] iArr2 = this.LFSR;
        iArr2[0] = iArr2[1];
        iArr2[1] = iArr2[2];
        iArr2[2] = iArr2[3];
        iArr2[3] = iArr2[4];
        iArr2[4] = iArr2[5];
        iArr2[5] = iArr2[6];
        iArr2[6] = iArr2[7];
        iArr2[7] = iArr2[8];
        iArr2[8] = iArr2[9];
        iArr2[9] = iArr2[10];
        iArr2[10] = iArr2[11];
        iArr2[11] = iArr2[12];
        iArr2[12] = iArr2[13];
        iArr2[13] = iArr2[14];
        iArr2[14] = iArr2[15];
        iArr2[15] = AddM;
    }

    private void LFSRWithWorkMode() {
        int[] iArr = this.LFSR;
        int AddM = AddM(AddM(AddM(AddM(AddM(iArr[0], MulByPow2(iArr[0], 8)), MulByPow2(this.LFSR[4], 20)), MulByPow2(this.LFSR[10], 21)), MulByPow2(this.LFSR[13], 17)), MulByPow2(this.LFSR[15], 15));
        int[] iArr2 = this.LFSR;
        iArr2[0] = iArr2[1];
        iArr2[1] = iArr2[2];
        iArr2[2] = iArr2[3];
        iArr2[3] = iArr2[4];
        iArr2[4] = iArr2[5];
        iArr2[5] = iArr2[6];
        iArr2[6] = iArr2[7];
        iArr2[7] = iArr2[8];
        iArr2[8] = iArr2[9];
        iArr2[9] = iArr2[10];
        iArr2[10] = iArr2[11];
        iArr2[11] = iArr2[12];
        iArr2[12] = iArr2[13];
        iArr2[13] = iArr2[14];
        iArr2[14] = iArr2[15];
        iArr2[15] = AddM;
    }

    private static int MAKEU31(byte b2, short s2, byte b3) {
        return ((b2 & 255) << 23) | ((s2 & UShort.MAX_VALUE) << 8) | (b3 & 255);
    }

    private static int MAKEU32(byte b2, byte b3, byte b4, byte b5) {
        return ((b2 & 255) << 24) | ((b3 & 255) << 16) | ((b4 & 255) << 8) | (b5 & 255);
    }

    private static int MulByPow2(int i2, int i3) {
        return ((i2 >>> (31 - i3)) | (i2 << i3)) & Integer.MAX_VALUE;
    }

    static int b(int i2, int i3) {
        return (i2 >>> (32 - i3)) | (i2 << i3);
    }

    public static void encode32be(int i2, byte[] bArr, int i3) {
        bArr[i3] = (byte) (i2 >> 24);
        bArr[i3 + 1] = (byte) (i2 >> 16);
        bArr[i3 + 2] = (byte) (i2 >> 8);
        bArr[i3 + 3] = (byte) i2;
    }

    private void makeKeyStream() {
        encode32be(d(), this.keyStream, 0);
    }

    private void setKeyAndIV(byte[] bArr, byte[] bArr2) {
        e(this.LFSR, bArr, bArr2);
        int[] iArr = this.F;
        iArr[0] = 0;
        iArr[1] = 0;
        int i2 = 32;
        while (true) {
            BitReorganization();
            if (i2 <= 0) {
                a();
                LFSRWithWorkMode();
                return;
            }
            LFSRWithInitialisationMode(a() >>> 1);
            i2--;
        }
    }

    int a() {
        int[] iArr = this.BRC;
        int i2 = iArr[0];
        int[] iArr2 = this.F;
        int i3 = (i2 ^ iArr2[0]) + iArr2[1];
        int i4 = iArr2[0] + iArr[1];
        int i5 = iArr[2] ^ iArr2[1];
        int L1 = L1((i4 << 16) | (i5 >>> 16));
        int L2 = L2((i5 << 16) | (i4 >>> 16));
        int[] iArr3 = this.F;
        byte[] bArr = S0;
        byte b2 = bArr[L1 >>> 24];
        byte[] bArr2 = S1;
        iArr3[0] = MAKEU32(b2, bArr2[(L1 >>> 16) & 255], bArr[(L1 >>> 8) & 255], bArr2[L1 & 255]);
        this.F[1] = MAKEU32(bArr[L2 >>> 24], bArr2[(L2 >>> 16) & 255], bArr[(L2 >>> 8) & 255], bArr2[L2 & 255]);
        return i3;
    }

    protected int c() {
        return 2047;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new Zuc128CoreEngine(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int d() {
        int i2 = this.theIterations;
        this.theIterations = i2 + 1;
        if (i2 < c()) {
            BitReorganization();
            int a2 = a() ^ this.BRC[3];
            LFSRWithWorkMode();
            return a2;
        }
        throw new IllegalStateException("Too much data processed by singleKey/IV");
    }

    protected void e(int[] iArr, byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length != 16) {
            throw new IllegalArgumentException("A key of 16 bytes is needed");
        }
        if (bArr2 == null || bArr2.length != 16) {
            throw new IllegalArgumentException("An IV of 16 bytes is needed");
        }
        int[] iArr2 = this.LFSR;
        byte b2 = bArr[0];
        short[] sArr = EK_d;
        iArr2[0] = MAKEU31(b2, sArr[0], bArr2[0]);
        this.LFSR[1] = MAKEU31(bArr[1], sArr[1], bArr2[1]);
        this.LFSR[2] = MAKEU31(bArr[2], sArr[2], bArr2[2]);
        this.LFSR[3] = MAKEU31(bArr[3], sArr[3], bArr2[3]);
        this.LFSR[4] = MAKEU31(bArr[4], sArr[4], bArr2[4]);
        this.LFSR[5] = MAKEU31(bArr[5], sArr[5], bArr2[5]);
        this.LFSR[6] = MAKEU31(bArr[6], sArr[6], bArr2[6]);
        this.LFSR[7] = MAKEU31(bArr[7], sArr[7], bArr2[7]);
        this.LFSR[8] = MAKEU31(bArr[8], sArr[8], bArr2[8]);
        this.LFSR[9] = MAKEU31(bArr[9], sArr[9], bArr2[9]);
        this.LFSR[10] = MAKEU31(bArr[10], sArr[10], bArr2[10]);
        this.LFSR[11] = MAKEU31(bArr[11], sArr[11], bArr2[11]);
        this.LFSR[12] = MAKEU31(bArr[12], sArr[12], bArr2[12]);
        this.LFSR[13] = MAKEU31(bArr[13], sArr[13], bArr2[13]);
        this.LFSR[14] = MAKEU31(bArr[14], sArr[14], bArr2[14]);
        this.LFSR[15] = MAKEU31(bArr[15], sArr[15], bArr2[15]);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        return "Zuc-128";
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        byte[] bArr;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            bArr = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            bArr = null;
        }
        byte[] key = cipherParameters instanceof KeyParameter ? ((KeyParameter) cipherParameters).getKey() : null;
        this.theIndex = 0;
        this.theIterations = 0;
        setKeyAndIV(key, bArr);
        this.theResetState = (Zuc128CoreEngine) copy();
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (this.theResetState == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i2 + i3 <= bArr.length) {
            if (i4 + i3 <= bArr2.length) {
                for (int i5 = 0; i5 < i3; i5++) {
                    bArr2[i5 + i4] = returnByte(bArr[i5 + i2]);
                }
                return i3;
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void reset() {
        Zuc128CoreEngine zuc128CoreEngine = this.theResetState;
        if (zuc128CoreEngine != null) {
            reset(zuc128CoreEngine);
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        Zuc128CoreEngine zuc128CoreEngine = (Zuc128CoreEngine) memoable;
        int[] iArr = zuc128CoreEngine.LFSR;
        int[] iArr2 = this.LFSR;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = zuc128CoreEngine.F;
        int[] iArr4 = this.F;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        int[] iArr5 = zuc128CoreEngine.BRC;
        int[] iArr6 = this.BRC;
        System.arraycopy(iArr5, 0, iArr6, 0, iArr6.length);
        byte[] bArr = zuc128CoreEngine.keyStream;
        byte[] bArr2 = this.keyStream;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.theIndex = zuc128CoreEngine.theIndex;
        this.theIterations = zuc128CoreEngine.theIterations;
        this.theResetState = zuc128CoreEngine;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b2) {
        if (this.theIndex == 0) {
            makeKeyStream();
        }
        byte[] bArr = this.keyStream;
        int i2 = this.theIndex;
        byte b3 = (byte) (b2 ^ bArr[i2]);
        this.theIndex = (i2 + 1) % 4;
        return b3;
    }
}
