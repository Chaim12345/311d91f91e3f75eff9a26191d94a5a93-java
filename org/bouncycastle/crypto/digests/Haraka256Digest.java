package org.bouncycastle.crypto.digests;

import com.facebook.stetho.dumpapp.Framer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.lang.reflect.Array;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class Haraka256Digest extends HarakaBase {
    private static final byte[][] RC = {new byte[]{6, -124, 112, 76, -26, 32, -64, 10, -78, -59, -2, -16, 117, -127, 123, -99}, new byte[]{-117, 102, -76, -31, -120, -13, -96, 107, 100, Ascii.SI, 107, -92, 47, 8, -9, Ascii.ETB}, new byte[]{52, 2, -34, Framer.STDIN_FRAME_PREFIX, 83, -14, -124, -104, -49, 2, -99, 96, -97, 2, -111, Ascii.DC4}, new byte[]{Ascii.SO, -42, -22, -26, 46, 123, 79, 8, ByteSourceJsonBootstrapper.UTF8_BOM_2, -13, PSSSigner.TRAILER_IMPLICIT, -81, -3, 91, 79, 121}, new byte[]{-53, -49, -80, -53, 72, 114, 68, -117, 121, -18, -51, Ascii.FS, -66, 57, 112, 68}, new byte[]{126, -22, -51, -18, 110, -112, Framer.STDERR_FRAME_PREFIX, -73, -115, 83, 53, -19, 43, -118, 5, 123}, new byte[]{103, -62, -113, 67, 94, 46, 124, -48, -30, 65, 39, 97, -38, 79, ByteSourceJsonBootstrapper.UTF8_BOM_1, Ascii.ESC}, new byte[]{41, 36, -39, -80, -81, -54, -52, 7, 103, Framer.STDIN_REQUEST_FRAME_PREFIX, -3, -30, Ascii.US, -57, Ascii.VT, 59}, new byte[]{-85, 77, 99, -15, -26, -122, Byte.MAX_VALUE, -23, -20, -37, -113, -54, -71, -44, 101, -18}, new byte[]{Ascii.FS, 48, ByteSourceJsonBootstrapper.UTF8_BOM_3, -124, -44, -73, -51, 100, 91, 42, SignedBytes.MAX_POWER_OF_TWO, 79, -83, 3, 126, 51}, new byte[]{-78, -52, Ascii.VT, -71, -108, Ascii.ETB, 35, ByteSourceJsonBootstrapper.UTF8_BOM_3, 105, 2, -117, 46, -115, -10, -104, 0}, new byte[]{-6, 4, Framer.EXIT_FRAME_PREFIX, -90, -34, 111, 85, 114, 74, -86, -98, -56, 92, -99, Framer.STDIN_FRAME_PREFIX, -118}, new byte[]{-33, -76, -97, 43, 107, 119, 42, Ascii.DC2, Ascii.SO, -6, 79, 46, 41, Ascii.DC2, -97, -44}, new byte[]{Ascii.RS, -95, 3, 68, -12, 73, -94, 54, Framer.STDERR_FRAME_PREFIX, -42, 17, -82, ByteSourceJsonBootstrapper.UTF8_BOM_2, 106, Ascii.DC2, -18}, new byte[]{-81, 4, 73, -120, 75, 5, 0, -124, Framer.STDIN_REQUEST_FRAME_PREFIX, -106, 0, -55, -100, -88, -20, -90}, new byte[]{Framer.ENTER_FRAME_PREFIX, 2, 94, -40, -99, Ascii.EM, -100, 79, Framer.EXIT_FRAME_PREFIX, -94, -57, -29, 39, -27, -109, -20}, new byte[]{ByteSourceJsonBootstrapper.UTF8_BOM_3, 58, -86, -8, -89, 89, -55, -73, -71, 40, 46, -51, -126, -44, 1, 115}, new byte[]{98, 96, 112, Ascii.CR, 97, -122, -80, Ascii.ETB, 55, -14, ByteSourceJsonBootstrapper.UTF8_BOM_1, -39, 16, 48, 125, 107}, new byte[]{90, -54, 69, -62, Framer.ENTER_FRAME_PREFIX, 48, 4, 67, -127, -62, -111, 83, -10, -4, -102, -58}, new byte[]{-110, 35, -105, 60, 34, 107, 104, ByteSourceJsonBootstrapper.UTF8_BOM_2, 44, -81, -110, -24, 54, -47, -108, 58}};
    private final byte[] buffer;
    private int off;

    public Haraka256Digest() {
        this.buffer = new byte[32];
    }

    public Haraka256Digest(Haraka256Digest haraka256Digest) {
        this.buffer = Arrays.clone(haraka256Digest.buffer);
        this.off = haraka256Digest.off;
    }

    private int haraka256256(byte[] bArr, byte[] bArr2, int i2) {
        byte[][] bArr3 = (byte[][]) Array.newInstance(byte.class, 2, 16);
        byte[][] bArr4 = (byte[][]) Array.newInstance(byte.class, 2, 16);
        System.arraycopy(bArr, 0, bArr3[0], 0, 16);
        System.arraycopy(bArr, 16, bArr3[1], 0, 16);
        byte[] bArr5 = bArr3[0];
        byte[][] bArr6 = RC;
        bArr3[0] = HarakaBase.a(bArr5, bArr6[0]);
        bArr3[1] = HarakaBase.a(bArr3[1], bArr6[1]);
        bArr3[0] = HarakaBase.a(bArr3[0], bArr6[2]);
        bArr3[1] = HarakaBase.a(bArr3[1], bArr6[3]);
        mix256(bArr3, bArr4);
        bArr3[0] = HarakaBase.a(bArr4[0], bArr6[4]);
        bArr3[1] = HarakaBase.a(bArr4[1], bArr6[5]);
        bArr3[0] = HarakaBase.a(bArr3[0], bArr6[6]);
        bArr3[1] = HarakaBase.a(bArr3[1], bArr6[7]);
        mix256(bArr3, bArr4);
        bArr3[0] = HarakaBase.a(bArr4[0], bArr6[8]);
        bArr3[1] = HarakaBase.a(bArr4[1], bArr6[9]);
        bArr3[0] = HarakaBase.a(bArr3[0], bArr6[10]);
        bArr3[1] = HarakaBase.a(bArr3[1], bArr6[11]);
        mix256(bArr3, bArr4);
        bArr3[0] = HarakaBase.a(bArr4[0], bArr6[12]);
        bArr3[1] = HarakaBase.a(bArr4[1], bArr6[13]);
        bArr3[0] = HarakaBase.a(bArr3[0], bArr6[14]);
        bArr3[1] = HarakaBase.a(bArr3[1], bArr6[15]);
        mix256(bArr3, bArr4);
        bArr3[0] = HarakaBase.a(bArr4[0], bArr6[16]);
        bArr3[1] = HarakaBase.a(bArr4[1], bArr6[17]);
        bArr3[0] = HarakaBase.a(bArr3[0], bArr6[18]);
        bArr3[1] = HarakaBase.a(bArr3[1], bArr6[19]);
        mix256(bArr3, bArr4);
        bArr3[0] = HarakaBase.f(bArr4[0], bArr, 0);
        bArr3[1] = HarakaBase.f(bArr4[1], bArr, 16);
        System.arraycopy(bArr3[0], 0, bArr2, i2, 16);
        System.arraycopy(bArr3[1], 0, bArr2, i2 + 16, 16);
        return 32;
    }

    private void mix256(byte[][] bArr, byte[][] bArr2) {
        System.arraycopy(bArr[0], 0, bArr2[0], 0, 4);
        System.arraycopy(bArr[1], 0, bArr2[0], 4, 4);
        System.arraycopy(bArr[0], 4, bArr2[0], 8, 4);
        System.arraycopy(bArr[1], 4, bArr2[0], 12, 4);
        System.arraycopy(bArr[0], 8, bArr2[1], 0, 4);
        System.arraycopy(bArr[1], 8, bArr2[1], 4, 4);
        System.arraycopy(bArr[0], 12, bArr2[1], 8, 4);
        System.arraycopy(bArr[1], 12, bArr2[1], 12, 4);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        if (this.off == 32) {
            if (bArr.length - i2 >= 32) {
                int haraka256256 = haraka256256(this.buffer, bArr, i2);
                reset();
                return haraka256256;
            }
            throw new IllegalArgumentException("output too short to receive digest");
        }
        throw new IllegalStateException("input must be exactly 32 bytes");
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Haraka-256";
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.off = 0;
        Arrays.clear(this.buffer);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b2) {
        int i2 = this.off;
        if (i2 + 1 > 32) {
            throw new IllegalArgumentException("total input cannot be more than 32 bytes");
        }
        byte[] bArr = this.buffer;
        this.off = i2 + 1;
        bArr[i2] = b2;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        int i4 = this.off;
        if (i4 + i3 > 32) {
            throw new IllegalArgumentException("total input cannot be more than 32 bytes");
        }
        System.arraycopy(bArr, i2, this.buffer, i4, i3);
        this.off += i3;
    }
}
