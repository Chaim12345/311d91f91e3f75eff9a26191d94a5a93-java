package org.bouncycastle.crypto.engines;

import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.MaxBytesExceededException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.SkippingStreamCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
public class Salsa20Engine implements SkippingStreamCipher {
    public static final int DEFAULT_ROUNDS = 20;
    private static final int STATE_SIZE = 16;
    private static final int[] TAU_SIGMA = Pack.littleEndianToInt(Strings.toByteArray("expand 16-byte kexpand 32-byte k"), 0, 8);

    /* renamed from: a  reason: collision with root package name */
    protected int f13389a;

    /* renamed from: b  reason: collision with root package name */
    protected int[] f13390b;

    /* renamed from: c  reason: collision with root package name */
    protected int[] f13391c;
    private int cW0;
    private int cW1;
    private int cW2;
    private int index;
    private boolean initialised;
    private byte[] keyStream;

    static {
        Strings.toByteArray("expand 32-byte k");
        Strings.toByteArray("expand 16-byte k");
    }

    public Salsa20Engine() {
        this(20);
    }

    public Salsa20Engine(int i2) {
        this.index = 0;
        this.f13390b = new int[16];
        this.f13391c = new int[16];
        this.keyStream = new byte[64];
        this.initialised = false;
        if (i2 <= 0 || (i2 & 1) != 0) {
            throw new IllegalArgumentException("'rounds' must be a positive, even number");
        }
        this.f13389a = i2;
    }

    private boolean limitExceeded() {
        int i2 = this.cW0 + 1;
        this.cW0 = i2;
        if (i2 == 0) {
            int i3 = this.cW1 + 1;
            this.cW1 = i3;
            if (i3 == 0) {
                int i4 = this.cW2 + 1;
                this.cW2 = i4;
                return (i4 & 32) != 0;
            }
        }
        return false;
    }

    private boolean limitExceeded(int i2) {
        int i3 = this.cW0 + i2;
        this.cW0 = i3;
        if (i3 >= i2 || i3 < 0) {
            return false;
        }
        int i4 = this.cW1 + 1;
        this.cW1 = i4;
        if (i4 == 0) {
            int i5 = this.cW2 + 1;
            this.cW2 = i5;
            return (i5 & 32) != 0;
        }
        return false;
    }

    private void resetLimitCounter() {
        this.cW0 = 0;
        this.cW1 = 0;
        this.cW2 = 0;
    }

    public static void salsaCore(int i2, int[] iArr, int[] iArr2) {
        if (iArr.length != 16) {
            throw new IllegalArgumentException();
        }
        if (iArr2.length != 16) {
            throw new IllegalArgumentException();
        }
        if (i2 % 2 != 0) {
            throw new IllegalArgumentException("Number of rounds must be even");
        }
        char c2 = 0;
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        int i6 = iArr[3];
        int i7 = iArr[4];
        int i8 = iArr[5];
        int i9 = iArr[6];
        int i10 = 7;
        int i11 = iArr[7];
        int i12 = iArr[8];
        int i13 = 9;
        int i14 = iArr[9];
        int i15 = iArr[10];
        int i16 = iArr[11];
        int i17 = iArr[12];
        int i18 = 13;
        int i19 = iArr[13];
        int i20 = iArr[14];
        int i21 = iArr[15];
        int i22 = i20;
        int i23 = i19;
        int i24 = i17;
        int i25 = i16;
        int i26 = i15;
        int i27 = i14;
        int i28 = i12;
        int i29 = i11;
        int i30 = i9;
        int i31 = i8;
        int i32 = i7;
        int i33 = i6;
        int i34 = i5;
        int i35 = i4;
        int i36 = i3;
        int i37 = i2;
        while (i37 > 0) {
            int rotateLeft = Integers.rotateLeft(i36 + i24, i10) ^ i32;
            int rotateLeft2 = i28 ^ Integers.rotateLeft(rotateLeft + i36, i13);
            int rotateLeft3 = i24 ^ Integers.rotateLeft(rotateLeft2 + rotateLeft, i18);
            int rotateLeft4 = Integers.rotateLeft(rotateLeft3 + rotateLeft2, 18) ^ i36;
            int rotateLeft5 = i27 ^ Integers.rotateLeft(i31 + i35, i10);
            int rotateLeft6 = i23 ^ Integers.rotateLeft(rotateLeft5 + i31, i13);
            int rotateLeft7 = i35 ^ Integers.rotateLeft(rotateLeft6 + rotateLeft5, i18);
            int rotateLeft8 = Integers.rotateLeft(rotateLeft7 + rotateLeft6, 18) ^ i31;
            int rotateLeft9 = i22 ^ Integers.rotateLeft(i26 + i30, 7);
            int rotateLeft10 = i34 ^ Integers.rotateLeft(rotateLeft9 + i26, 9);
            int rotateLeft11 = i30 ^ Integers.rotateLeft(rotateLeft10 + rotateLeft9, 13);
            int rotateLeft12 = i26 ^ Integers.rotateLeft(rotateLeft11 + rotateLeft10, 18);
            int rotateLeft13 = i33 ^ Integers.rotateLeft(i21 + i25, 7);
            int rotateLeft14 = i29 ^ Integers.rotateLeft(rotateLeft13 + i21, 9);
            int i38 = i37;
            int rotateLeft15 = i25 ^ Integers.rotateLeft(rotateLeft14 + rotateLeft13, 13);
            int rotateLeft16 = i21 ^ Integers.rotateLeft(rotateLeft15 + rotateLeft14, 18);
            i35 = rotateLeft7 ^ Integers.rotateLeft(rotateLeft4 + rotateLeft13, 7);
            i34 = rotateLeft10 ^ Integers.rotateLeft(i35 + rotateLeft4, 9);
            int rotateLeft17 = rotateLeft13 ^ Integers.rotateLeft(i34 + i35, 13);
            int rotateLeft18 = rotateLeft4 ^ Integers.rotateLeft(rotateLeft17 + i34, 18);
            i30 = rotateLeft11 ^ Integers.rotateLeft(rotateLeft8 + rotateLeft, 7);
            i29 = rotateLeft14 ^ Integers.rotateLeft(i30 + rotateLeft8, 9);
            int rotateLeft19 = Integers.rotateLeft(i29 + i30, 13) ^ rotateLeft;
            i31 = rotateLeft8 ^ Integers.rotateLeft(rotateLeft19 + i29, 18);
            i25 = rotateLeft15 ^ Integers.rotateLeft(rotateLeft12 + rotateLeft5, 7);
            int rotateLeft20 = Integers.rotateLeft(i25 + rotateLeft12, 9) ^ rotateLeft2;
            i27 = rotateLeft5 ^ Integers.rotateLeft(rotateLeft20 + i25, 13);
            i26 = rotateLeft12 ^ Integers.rotateLeft(i27 + rotateLeft20, 18);
            i24 = rotateLeft3 ^ Integers.rotateLeft(rotateLeft16 + rotateLeft9, 7);
            i23 = rotateLeft6 ^ Integers.rotateLeft(i24 + rotateLeft16, 9);
            i22 = rotateLeft9 ^ Integers.rotateLeft(i23 + i24, 13);
            i21 = rotateLeft16 ^ Integers.rotateLeft(i22 + i23, 18);
            i33 = rotateLeft17;
            i28 = rotateLeft20;
            i36 = rotateLeft18;
            i32 = rotateLeft19;
            c2 = 0;
            i18 = 13;
            i13 = 9;
            i10 = 7;
            i37 = i38 - 2;
        }
        char c3 = c2;
        iArr2[c3] = i36 + iArr[c3];
        iArr2[1] = i35 + iArr[1];
        iArr2[2] = i34 + iArr[2];
        iArr2[3] = i33 + iArr[3];
        iArr2[4] = i32 + iArr[4];
        iArr2[5] = i31 + iArr[5];
        iArr2[6] = i30 + iArr[6];
        iArr2[7] = i29 + iArr[7];
        iArr2[8] = i28 + iArr[8];
        iArr2[9] = i27 + iArr[9];
        iArr2[10] = i26 + iArr[10];
        iArr2[11] = i25 + iArr[11];
        iArr2[12] = i24 + iArr[12];
        iArr2[13] = i23 + iArr[13];
        iArr2[14] = i22 + iArr[14];
        iArr2[15] = i21 + iArr[15];
    }

    protected void a() {
        int[] iArr = this.f13390b;
        int i2 = iArr[8] + 1;
        iArr[8] = i2;
        if (i2 == 0) {
            iArr[9] = iArr[9] + 1;
        }
    }

    protected void b(long j2) {
        int i2 = (int) (j2 >>> 32);
        int i3 = (int) j2;
        if (i2 > 0) {
            int[] iArr = this.f13390b;
            iArr[9] = iArr[9] + i2;
        }
        int[] iArr2 = this.f13390b;
        int i4 = iArr2[8];
        iArr2[8] = iArr2[8] + i3;
        if (i4 == 0 || iArr2[8] >= i4) {
            return;
        }
        iArr2[9] = iArr2[9] + 1;
    }

    protected void c(byte[] bArr) {
        salsaCore(this.f13389a, this.f13390b, this.f13391c);
        Pack.intToLittleEndian(this.f13391c, bArr, 0);
    }

    protected long d() {
        int[] iArr = this.f13390b;
        return (iArr[9] << 32) | (iArr[8] & BodyPartID.bodyIdMax);
    }

    protected int e() {
        return 8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void f(int i2, int[] iArr, int i3) {
        int i4 = (i2 - 16) / 4;
        int[] iArr2 = TAU_SIGMA;
        iArr[i3] = iArr2[i4];
        iArr[i3 + 1] = iArr2[i4 + 1];
        iArr[i3 + 2] = iArr2[i4 + 2];
        iArr[i3 + 3] = iArr2[i4 + 3];
    }

    protected void g() {
        int[] iArr = this.f13390b;
        iArr[9] = 0;
        iArr[8] = 0;
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public String getAlgorithmName() {
        if (this.f13389a != 20) {
            return "Salsa20/" + this.f13389a;
        }
        return "Salsa20";
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long getPosition() {
        return (d() * 64) + this.index;
    }

    protected void h() {
        int[] iArr = this.f13390b;
        if (iArr[8] == 0 && iArr[9] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        }
        int i2 = iArr[8] - 1;
        iArr[8] = i2;
        if (i2 == -1) {
            iArr[9] = iArr[9] - 1;
        }
    }

    protected void i(long j2) {
        int i2 = (int) (j2 >>> 32);
        int i3 = (int) j2;
        if (i2 != 0) {
            int[] iArr = this.f13390b;
            if ((iArr[9] & BodyPartID.bodyIdMax) < (i2 & BodyPartID.bodyIdMax)) {
                throw new IllegalStateException("attempt to reduce counter past zero.");
            }
            iArr[9] = iArr[9] - i2;
        }
        int[] iArr2 = this.f13390b;
        if ((iArr2[8] & BodyPartID.bodyIdMax) >= (BodyPartID.bodyIdMax & i3)) {
            iArr2[8] = iArr2[8] - i3;
        } else if (iArr2[9] == 0) {
            throw new IllegalStateException("attempt to reduce counter past zero.");
        } else {
            iArr2[9] = iArr2[9] - 1;
            iArr2[8] = iArr2[8] - i3;
        }
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] iv = parametersWithIV.getIV();
        if (iv == null || iv.length != e()) {
            throw new IllegalArgumentException(getAlgorithmName() + " requires exactly " + e() + " bytes of IV");
        }
        CipherParameters parameters = parametersWithIV.getParameters();
        if (parameters == null) {
            if (!this.initialised) {
                throw new IllegalStateException(getAlgorithmName() + " KeyParameter can not be null for first initialisation");
            }
            j(null, iv);
        } else if (!(parameters instanceof KeyParameter)) {
            throw new IllegalArgumentException(getAlgorithmName() + " Init parameters must contain a KeyParameter (or null for re-init)");
        } else {
            j(((KeyParameter) parameters).getKey(), iv);
        }
        reset();
        this.initialised = true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void j(byte[] bArr, byte[] bArr2) {
        if (bArr != null) {
            if (bArr.length != 16 && bArr.length != 32) {
                throw new IllegalArgumentException(getAlgorithmName() + " requires 128 bit or 256 bit key");
            }
            int length = (bArr.length - 16) / 4;
            int[] iArr = this.f13390b;
            int[] iArr2 = TAU_SIGMA;
            iArr[0] = iArr2[length];
            iArr[5] = iArr2[length + 1];
            iArr[10] = iArr2[length + 2];
            iArr[15] = iArr2[length + 3];
            Pack.littleEndianToInt(bArr, 0, iArr, 1, 4);
            Pack.littleEndianToInt(bArr, bArr.length - 16, this.f13390b, 11, 4);
        }
        Pack.littleEndianToInt(bArr2, 0, this.f13390b, 6, 2);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (!this.initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i2 + i3 <= bArr.length) {
            if (i4 + i3 <= bArr2.length) {
                if (limitExceeded(i3)) {
                    throw new MaxBytesExceededException("2^70 byte limit per IV would be exceeded; Change IV");
                }
                for (int i5 = 0; i5 < i3; i5++) {
                    byte[] bArr3 = this.keyStream;
                    int i6 = this.index;
                    bArr2[i5 + i4] = (byte) (bArr3[i6] ^ bArr[i5 + i2]);
                    int i7 = (i6 + 1) & 63;
                    this.index = i7;
                    if (i7 == 0) {
                        a();
                        c(this.keyStream);
                    }
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
        this.index = 0;
        resetLimitCounter();
        g();
        c(this.keyStream);
    }

    @Override // org.bouncycastle.crypto.StreamCipher
    public byte returnByte(byte b2) {
        if (limitExceeded()) {
            throw new MaxBytesExceededException("2^70 byte limit per IV; Change IV");
        }
        byte[] bArr = this.keyStream;
        int i2 = this.index;
        byte b3 = (byte) (b2 ^ bArr[i2]);
        int i3 = (i2 + 1) & 63;
        this.index = i3;
        if (i3 == 0) {
            a();
            c(this.keyStream);
        }
        return b3;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long seekTo(long j2) {
        reset();
        return skip(j2);
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long skip(long j2) {
        long j3;
        if (j2 >= 0) {
            if (j2 >= 64) {
                long j4 = j2 / 64;
                b(j4);
                j3 = j2 - (j4 * 64);
            } else {
                j3 = j2;
            }
            int i2 = this.index;
            int i3 = (((int) j3) + i2) & 63;
            this.index = i3;
            if (i3 < i2) {
                a();
            }
        } else {
            long j5 = -j2;
            if (j5 >= 64) {
                long j6 = j5 / 64;
                i(j6);
                j5 -= j6 * 64;
            }
            for (long j7 = 0; j7 < j5; j7++) {
                if (this.index == 0) {
                    h();
                }
                this.index = (this.index - 1) & 63;
            }
        }
        c(this.keyStream);
        return j2;
    }
}
