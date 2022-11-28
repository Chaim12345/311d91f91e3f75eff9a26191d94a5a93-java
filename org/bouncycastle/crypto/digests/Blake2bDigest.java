package org.bouncycastle.crypto.digests;

import com.google.common.base.Ascii;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Longs;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class Blake2bDigest implements ExtendedDigest {
    private static final int BLOCK_LENGTH_BYTES = 128;
    private byte[] buffer;
    private int bufferPos;
    private long[] chainValue;
    private int digestLength;
    private long f0;
    private long[] internalState;
    private byte[] key;
    private int keyLength;
    private byte[] personalization;
    private byte[] salt;
    private long t0;
    private long t1;
    private static final long[] blake2b_IV = {7640891576956012808L, -4942790177534073029L, 4354685564936845355L, -6534734903238641935L, 5840696475078001361L, -7276294671716946913L, 2270897969802886507L, 6620516959819538809L};
    private static final byte[][] blake2b_sigma = {new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.VT, Ascii.FF, Ascii.CR, Ascii.SO, Ascii.SI}, new byte[]{Ascii.SO, 10, 4, 8, 9, Ascii.SI, Ascii.CR, 6, 1, Ascii.FF, 0, 2, Ascii.VT, 7, 5, 3}, new byte[]{Ascii.VT, 8, Ascii.FF, 0, 5, 2, Ascii.SI, Ascii.CR, 10, Ascii.SO, 3, 6, 7, 1, 9, 4}, new byte[]{7, 9, 3, 1, Ascii.CR, Ascii.FF, Ascii.VT, Ascii.SO, 2, 6, 5, 10, 4, 0, Ascii.SI, 8}, new byte[]{9, 0, 5, 7, 2, 4, 10, Ascii.SI, Ascii.SO, 1, Ascii.VT, Ascii.FF, 6, 8, 3, Ascii.CR}, new byte[]{2, Ascii.FF, 6, 10, 0, Ascii.VT, 8, 3, 4, Ascii.CR, 7, 5, Ascii.SI, Ascii.SO, 1, 9}, new byte[]{Ascii.FF, 5, 1, Ascii.SI, Ascii.SO, Ascii.CR, 4, 10, 0, 7, 6, 3, 9, 2, 8, Ascii.VT}, new byte[]{Ascii.CR, Ascii.VT, 7, Ascii.SO, Ascii.FF, 1, 3, 9, 5, 0, Ascii.SI, 4, 8, 6, 2, 10}, new byte[]{6, Ascii.SI, Ascii.SO, 9, Ascii.VT, 3, 0, 8, Ascii.FF, 2, Ascii.CR, 7, 1, 4, 10, 5}, new byte[]{10, 2, 8, 4, 7, 6, 1, 5, Ascii.SI, Ascii.VT, 9, Ascii.SO, 3, Ascii.FF, Ascii.CR, 0}, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, Ascii.VT, Ascii.FF, Ascii.CR, Ascii.SO, Ascii.SI}, new byte[]{Ascii.SO, 10, 4, 8, 9, Ascii.SI, Ascii.CR, 6, 1, Ascii.FF, 0, 2, Ascii.VT, 7, 5, 3}};
    private static int ROUNDS = 12;

    public Blake2bDigest() {
        this(512);
    }

    public Blake2bDigest(int i2) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.t0 = 0L;
        this.t1 = 0L;
        this.f0 = 0L;
        if (i2 < 8 || i2 > 512 || i2 % 8 != 0) {
            throw new IllegalArgumentException("BLAKE2b digest bit length must be a multiple of 8 and not greater than 512");
        }
        this.buffer = new byte[128];
        this.keyLength = 0;
        this.digestLength = i2 / 8;
        init();
    }

    public Blake2bDigest(Blake2bDigest blake2bDigest) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.t0 = 0L;
        this.t1 = 0L;
        this.f0 = 0L;
        this.bufferPos = blake2bDigest.bufferPos;
        this.buffer = Arrays.clone(blake2bDigest.buffer);
        this.keyLength = blake2bDigest.keyLength;
        this.key = Arrays.clone(blake2bDigest.key);
        this.digestLength = blake2bDigest.digestLength;
        this.chainValue = Arrays.clone(blake2bDigest.chainValue);
        this.personalization = Arrays.clone(blake2bDigest.personalization);
        this.salt = Arrays.clone(blake2bDigest.salt);
        this.t0 = blake2bDigest.t0;
        this.t1 = blake2bDigest.t1;
        this.f0 = blake2bDigest.f0;
    }

    public Blake2bDigest(byte[] bArr) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.t0 = 0L;
        this.t1 = 0L;
        this.f0 = 0L;
        this.buffer = new byte[128];
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length];
            this.key = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            if (bArr.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.keyLength = bArr.length;
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
            this.bufferPos = 128;
        }
        this.digestLength = 64;
        init();
    }

    public Blake2bDigest(byte[] bArr, int i2, byte[] bArr2, byte[] bArr3) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.t0 = 0L;
        this.t1 = 0L;
        this.f0 = 0L;
        this.buffer = new byte[128];
        if (i2 < 1 || i2 > 64) {
            throw new IllegalArgumentException("Invalid digest length (required: 1 - 64)");
        }
        this.digestLength = i2;
        if (bArr2 != null) {
            if (bArr2.length != 16) {
                throw new IllegalArgumentException("salt length must be exactly 16 bytes");
            }
            byte[] bArr4 = new byte[16];
            this.salt = bArr4;
            System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
        }
        if (bArr3 != null) {
            if (bArr3.length != 16) {
                throw new IllegalArgumentException("personalization length must be exactly 16 bytes");
            }
            byte[] bArr5 = new byte[16];
            this.personalization = bArr5;
            System.arraycopy(bArr3, 0, bArr5, 0, bArr3.length);
        }
        if (bArr != null) {
            byte[] bArr6 = new byte[bArr.length];
            this.key = bArr6;
            System.arraycopy(bArr, 0, bArr6, 0, bArr.length);
            if (bArr.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.keyLength = bArr.length;
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
            this.bufferPos = 128;
        }
        init();
    }

    private void G(long j2, long j3, int i2, int i3, int i4, int i5) {
        long[] jArr = this.internalState;
        jArr[i2] = jArr[i2] + jArr[i3] + j2;
        jArr[i5] = Longs.rotateRight(jArr[i5] ^ jArr[i2], 32);
        long[] jArr2 = this.internalState;
        jArr2[i4] = jArr2[i4] + jArr2[i5];
        jArr2[i3] = Longs.rotateRight(jArr2[i3] ^ jArr2[i4], 24);
        long[] jArr3 = this.internalState;
        jArr3[i2] = jArr3[i2] + jArr3[i3] + j3;
        jArr3[i5] = Longs.rotateRight(jArr3[i5] ^ jArr3[i2], 16);
        long[] jArr4 = this.internalState;
        jArr4[i4] = jArr4[i4] + jArr4[i5];
        jArr4[i3] = Longs.rotateRight(jArr4[i3] ^ jArr4[i4], 63);
    }

    private void compress(byte[] bArr, int i2) {
        initializeInternalState();
        long[] jArr = new long[16];
        int i3 = 0;
        for (int i4 = 0; i4 < 16; i4++) {
            jArr[i4] = Pack.littleEndianToLong(bArr, (i4 * 8) + i2);
        }
        for (int i5 = 0; i5 < ROUNDS; i5++) {
            byte[][] bArr2 = blake2b_sigma;
            G(jArr[bArr2[i5][0]], jArr[bArr2[i5][1]], 0, 4, 8, 12);
            G(jArr[bArr2[i5][2]], jArr[bArr2[i5][3]], 1, 5, 9, 13);
            G(jArr[bArr2[i5][4]], jArr[bArr2[i5][5]], 2, 6, 10, 14);
            G(jArr[bArr2[i5][6]], jArr[bArr2[i5][7]], 3, 7, 11, 15);
            G(jArr[bArr2[i5][8]], jArr[bArr2[i5][9]], 0, 5, 10, 15);
            G(jArr[bArr2[i5][10]], jArr[bArr2[i5][11]], 1, 6, 11, 12);
            G(jArr[bArr2[i5][12]], jArr[bArr2[i5][13]], 2, 7, 8, 13);
            G(jArr[bArr2[i5][14]], jArr[bArr2[i5][15]], 3, 4, 9, 14);
        }
        while (true) {
            long[] jArr2 = this.chainValue;
            if (i3 >= jArr2.length) {
                return;
            }
            long j2 = jArr2[i3];
            long[] jArr3 = this.internalState;
            jArr2[i3] = (j2 ^ jArr3[i3]) ^ jArr3[i3 + 8];
            i3++;
        }
    }

    private void init() {
        if (this.chainValue == null) {
            this.chainValue = r1;
            long[] jArr = blake2b_IV;
            long[] jArr2 = {jArr[0] ^ ((this.digestLength | (this.keyLength << 8)) | 16842752), jArr[1], jArr[2], jArr[3], jArr[4], jArr[5]};
            byte[] bArr = this.salt;
            if (bArr != null) {
                jArr2[4] = jArr2[4] ^ Pack.littleEndianToLong(bArr, 0);
                long[] jArr3 = this.chainValue;
                jArr3[5] = jArr3[5] ^ Pack.littleEndianToLong(this.salt, 8);
            }
            long[] jArr4 = this.chainValue;
            jArr4[6] = jArr[6];
            jArr4[7] = jArr[7];
            byte[] bArr2 = this.personalization;
            if (bArr2 != null) {
                jArr4[6] = Pack.littleEndianToLong(bArr2, 0) ^ jArr4[6];
                long[] jArr5 = this.chainValue;
                jArr5[7] = jArr5[7] ^ Pack.littleEndianToLong(this.personalization, 8);
            }
        }
    }

    private void initializeInternalState() {
        long[] jArr = this.chainValue;
        System.arraycopy(jArr, 0, this.internalState, 0, jArr.length);
        long[] jArr2 = blake2b_IV;
        System.arraycopy(jArr2, 0, this.internalState, this.chainValue.length, 4);
        long[] jArr3 = this.internalState;
        jArr3[12] = this.t0 ^ jArr2[4];
        jArr3[13] = this.t1 ^ jArr2[5];
        jArr3[14] = this.f0 ^ jArr2[6];
        jArr3[15] = jArr2[7];
    }

    public void clearKey() {
        byte[] bArr = this.key;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            Arrays.fill(this.buffer, (byte) 0);
        }
    }

    public void clearSalt() {
        byte[] bArr = this.salt;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i2) {
        long[] jArr;
        int i3;
        this.f0 = -1L;
        long j2 = this.t0;
        int i4 = this.bufferPos;
        long j3 = j2 + i4;
        this.t0 = j3;
        if (i4 > 0 && j3 == 0) {
            this.t1++;
        }
        compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte) 0);
        Arrays.fill(this.internalState, 0L);
        int i5 = 0;
        while (true) {
            jArr = this.chainValue;
            if (i5 >= jArr.length || (i3 = i5 * 8) >= this.digestLength) {
                break;
            }
            byte[] longToLittleEndian = Pack.longToLittleEndian(jArr[i5]);
            int i6 = this.digestLength;
            if (i3 < i6 - 8) {
                System.arraycopy(longToLittleEndian, 0, bArr, i3 + i2, 8);
            } else {
                System.arraycopy(longToLittleEndian, 0, bArr, i2 + i3, i6 - i3);
            }
            i5++;
        }
        Arrays.fill(jArr, 0L);
        reset();
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "BLAKE2b";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 128;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.digestLength;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.bufferPos = 0;
        this.f0 = 0L;
        this.t0 = 0L;
        this.t1 = 0L;
        this.chainValue = null;
        Arrays.fill(this.buffer, (byte) 0);
        byte[] bArr = this.key;
        if (bArr != null) {
            System.arraycopy(bArr, 0, this.buffer, 0, bArr.length);
            this.bufferPos = 128;
        }
        init();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b2) {
        int i2 = this.bufferPos;
        if (128 - i2 != 0) {
            this.buffer[i2] = b2;
            this.bufferPos = i2 + 1;
            return;
        }
        long j2 = this.t0 + 128;
        this.t0 = j2;
        if (j2 == 0) {
            this.t1++;
        }
        compress(this.buffer, 0);
        Arrays.fill(this.buffer, (byte) 0);
        this.buffer[0] = b2;
        this.bufferPos = 1;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr == null || i3 == 0) {
            return;
        }
        int i5 = this.bufferPos;
        if (i5 != 0) {
            i4 = 128 - i5;
            if (i4 >= i3) {
                System.arraycopy(bArr, i2, this.buffer, i5, i3);
                this.bufferPos += i3;
            }
            System.arraycopy(bArr, i2, this.buffer, i5, i4);
            long j2 = this.t0 + 128;
            this.t0 = j2;
            if (j2 == 0) {
                this.t1++;
            }
            compress(this.buffer, 0);
            this.bufferPos = 0;
            Arrays.fill(this.buffer, (byte) 0);
        } else {
            i4 = 0;
        }
        int i6 = i3 + i2;
        int i7 = i6 - 128;
        int i8 = i2 + i4;
        while (i8 < i7) {
            long j3 = this.t0 + 128;
            this.t0 = j3;
            if (j3 == 0) {
                this.t1++;
            }
            compress(bArr, i8);
            i8 += 128;
        }
        i3 = i6 - i8;
        System.arraycopy(bArr, i8, this.buffer, 0, i3);
        this.bufferPos += i3;
    }
}
