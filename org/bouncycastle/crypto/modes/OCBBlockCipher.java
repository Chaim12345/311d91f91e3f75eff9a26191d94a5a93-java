package org.bouncycastle.crypto.modes;

import java.util.Vector;
import okio.Utf8;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.util.Arrays;
/* loaded from: classes3.dex */
public class OCBBlockCipher implements AEADBlockCipher {
    private static final int BLOCK_SIZE = 16;
    private byte[] Checksum;
    private Vector L;
    private byte[] L_Asterisk;
    private byte[] L_Dollar;
    private byte[] OffsetHASH;
    private byte[] Sum;
    private boolean forEncryption;
    private byte[] hashBlock;
    private long hashBlockCount;
    private int hashBlockPos;
    private BlockCipher hashCipher;
    private byte[] initialAssociatedText;
    private byte[] macBlock;
    private int macSize;
    private byte[] mainBlock;
    private long mainBlockCount;
    private int mainBlockPos;
    private BlockCipher mainCipher;
    private byte[] KtopInput = null;
    private byte[] Stretch = new byte[24];
    private byte[] OffsetMAIN_0 = new byte[16];
    private byte[] OffsetMAIN = new byte[16];

    public OCBBlockCipher(BlockCipher blockCipher, BlockCipher blockCipher2) {
        if (blockCipher == null) {
            throw new IllegalArgumentException("'hashCipher' cannot be null");
        }
        if (blockCipher.getBlockSize() != 16) {
            throw new IllegalArgumentException("'hashCipher' must have a block size of 16");
        }
        if (blockCipher2 == null) {
            throw new IllegalArgumentException("'mainCipher' cannot be null");
        }
        if (blockCipher2.getBlockSize() != 16) {
            throw new IllegalArgumentException("'mainCipher' must have a block size of 16");
        }
        if (!blockCipher.getAlgorithmName().equals(blockCipher2.getAlgorithmName())) {
            throw new IllegalArgumentException("'hashCipher' and 'mainCipher' must be the same algorithm");
        }
        this.hashCipher = blockCipher;
        this.mainCipher = blockCipher2;
    }

    protected static byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int j2 = j(bArr, bArr2);
        bArr2[15] = (byte) ((CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA >>> ((1 - j2) << 3)) ^ bArr2[15]);
        return bArr2;
    }

    protected static void b(byte[] bArr, int i2) {
        bArr[i2] = Byte.MIN_VALUE;
        while (true) {
            i2++;
            if (i2 >= 16) {
                return;
            }
            bArr[i2] = 0;
        }
    }

    protected static int c(long j2) {
        if (j2 == 0) {
            return 64;
        }
        int i2 = 0;
        while ((1 & j2) == 0) {
            i2++;
            j2 >>>= 1;
        }
        return i2;
    }

    protected static int j(byte[] bArr, byte[] bArr2) {
        int i2 = 16;
        int i3 = 0;
        while (true) {
            i2--;
            if (i2 < 0) {
                return i3;
            }
            int i4 = bArr[i2] & 255;
            bArr2[i2] = (byte) (i3 | (i4 << 1));
            i3 = (i4 >>> 7) & 1;
        }
    }

    protected static void l(byte[] bArr, byte[] bArr2) {
        for (int i2 = 15; i2 >= 0; i2--) {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2]);
        }
    }

    protected void d(byte[] bArr) {
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int doFinal(byte[] bArr, int i2) {
        byte[] bArr2;
        if (this.forEncryption) {
            bArr2 = null;
        } else {
            int i3 = this.mainBlockPos;
            int i4 = this.macSize;
            if (i3 < i4) {
                throw new InvalidCipherTextException("data too short");
            }
            int i5 = i3 - i4;
            this.mainBlockPos = i5;
            bArr2 = new byte[i4];
            System.arraycopy(this.mainBlock, i5, bArr2, 0, i4);
        }
        int i6 = this.hashBlockPos;
        if (i6 > 0) {
            b(this.hashBlock, i6);
            k(this.L_Asterisk);
        }
        int i7 = this.mainBlockPos;
        if (i7 > 0) {
            if (this.forEncryption) {
                b(this.mainBlock, i7);
                l(this.Checksum, this.mainBlock);
            }
            l(this.OffsetMAIN, this.L_Asterisk);
            byte[] bArr3 = new byte[16];
            this.hashCipher.processBlock(this.OffsetMAIN, 0, bArr3, 0);
            l(this.mainBlock, bArr3);
            int length = bArr.length;
            int i8 = this.mainBlockPos;
            if (length < i2 + i8) {
                throw new OutputLengthException("Output buffer too short");
            }
            System.arraycopy(this.mainBlock, 0, bArr, i2, i8);
            if (!this.forEncryption) {
                b(this.mainBlock, this.mainBlockPos);
                l(this.Checksum, this.mainBlock);
            }
        }
        l(this.Checksum, this.OffsetMAIN);
        l(this.Checksum, this.L_Dollar);
        BlockCipher blockCipher = this.hashCipher;
        byte[] bArr4 = this.Checksum;
        blockCipher.processBlock(bArr4, 0, bArr4, 0);
        l(this.Checksum, this.Sum);
        int i9 = this.macSize;
        byte[] bArr5 = new byte[i9];
        this.macBlock = bArr5;
        System.arraycopy(this.Checksum, 0, bArr5, 0, i9);
        int i10 = this.mainBlockPos;
        if (this.forEncryption) {
            int length2 = bArr.length;
            int i11 = i2 + i10;
            int i12 = this.macSize;
            if (length2 < i11 + i12) {
                throw new OutputLengthException("Output buffer too short");
            }
            System.arraycopy(this.macBlock, 0, bArr, i11, i12);
            i10 += this.macSize;
        } else if (!Arrays.constantTimeAreEqual(this.macBlock, bArr2)) {
            throw new InvalidCipherTextException("mac check in OCB failed");
        }
        i(false);
        return i10;
    }

    protected byte[] e(int i2) {
        while (i2 >= this.L.size()) {
            Vector vector = this.L;
            vector.addElement(a((byte[]) vector.lastElement()));
        }
        return (byte[]) this.L.elementAt(i2);
    }

    protected void f() {
        long j2 = this.hashBlockCount + 1;
        this.hashBlockCount = j2;
        k(e(c(j2)));
        this.hashBlockPos = 0;
    }

    protected void g(byte[] bArr, int i2) {
        if (bArr.length < i2 + 16) {
            throw new OutputLengthException("Output buffer too short");
        }
        if (this.forEncryption) {
            l(this.Checksum, this.mainBlock);
            this.mainBlockPos = 0;
        }
        byte[] bArr2 = this.OffsetMAIN;
        long j2 = this.mainBlockCount + 1;
        this.mainBlockCount = j2;
        l(bArr2, e(c(j2)));
        l(this.mainBlock, this.OffsetMAIN);
        BlockCipher blockCipher = this.mainCipher;
        byte[] bArr3 = this.mainBlock;
        blockCipher.processBlock(bArr3, 0, bArr3, 0);
        l(this.mainBlock, this.OffsetMAIN);
        System.arraycopy(this.mainBlock, 0, bArr, i2, 16);
        if (this.forEncryption) {
            return;
        }
        l(this.Checksum, this.mainBlock);
        byte[] bArr4 = this.mainBlock;
        System.arraycopy(bArr4, 16, bArr4, 0, this.macSize);
        this.mainBlockPos = this.macSize;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public String getAlgorithmName() {
        return this.mainCipher.getAlgorithmName() + "/OCB";
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public byte[] getMac() {
        byte[] bArr = this.macBlock;
        return bArr == null ? new byte[this.macSize] : Arrays.clone(bArr);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getOutputSize(int i2) {
        int i3 = i2 + this.mainBlockPos;
        if (this.forEncryption) {
            return i3 + this.macSize;
        }
        int i4 = this.macSize;
        if (i3 < i4) {
            return 0;
        }
        return i3 - i4;
    }

    @Override // org.bouncycastle.crypto.modes.AEADBlockCipher
    public BlockCipher getUnderlyingCipher() {
        return this.mainCipher;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int getUpdateOutputSize(int i2) {
        int i3 = i2 + this.mainBlockPos;
        if (!this.forEncryption) {
            int i4 = this.macSize;
            if (i3 < i4) {
                return 0;
            }
            i3 -= i4;
        }
        return i3 - (i3 % 16);
    }

    protected int h(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i2 = 0;
        System.arraycopy(bArr, 0, bArr2, 16 - bArr.length, bArr.length);
        bArr2[0] = (byte) (this.macSize << 4);
        int length = 15 - bArr.length;
        bArr2[length] = (byte) (bArr2[length] | 1);
        int i3 = bArr2[15] & Utf8.REPLACEMENT_BYTE;
        bArr2[15] = (byte) (bArr2[15] & 192);
        byte[] bArr3 = this.KtopInput;
        if (bArr3 == null || !Arrays.areEqual(bArr2, bArr3)) {
            byte[] bArr4 = new byte[16];
            this.KtopInput = bArr2;
            this.hashCipher.processBlock(bArr2, 0, bArr4, 0);
            System.arraycopy(bArr4, 0, this.Stretch, 0, 16);
            while (i2 < 8) {
                byte[] bArr5 = this.Stretch;
                int i4 = i2 + 16;
                byte b2 = bArr4[i2];
                i2++;
                bArr5[i4] = (byte) (b2 ^ bArr4[i2]);
            }
        }
        return i3;
    }

    protected void i(boolean z) {
        this.hashCipher.reset();
        this.mainCipher.reset();
        d(this.hashBlock);
        d(this.mainBlock);
        this.hashBlockPos = 0;
        this.mainBlockPos = 0;
        this.hashBlockCount = 0L;
        this.mainBlockCount = 0L;
        d(this.OffsetHASH);
        d(this.Sum);
        System.arraycopy(this.OffsetMAIN_0, 0, this.OffsetMAIN, 0, 16);
        d(this.Checksum);
        if (z) {
            this.macBlock = null;
        }
        byte[] bArr = this.initialAssociatedText;
        if (bArr != null) {
            processAADBytes(bArr, 0, bArr.length);
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        byte[] iv;
        KeyParameter keyParameter;
        boolean z2 = this.forEncryption;
        this.forEncryption = z;
        this.macBlock = null;
        if (cipherParameters instanceof AEADParameters) {
            AEADParameters aEADParameters = (AEADParameters) cipherParameters;
            iv = aEADParameters.getNonce();
            this.initialAssociatedText = aEADParameters.getAssociatedText();
            int macSize = aEADParameters.getMacSize();
            if (macSize < 64 || macSize > 128 || macSize % 8 != 0) {
                throw new IllegalArgumentException("Invalid value for MAC size: " + macSize);
            }
            this.macSize = macSize / 8;
            keyParameter = aEADParameters.getKey();
        } else if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("invalid parameters passed to OCB");
        } else {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            iv = parametersWithIV.getIV();
            this.initialAssociatedText = null;
            this.macSize = 16;
            keyParameter = (KeyParameter) parametersWithIV.getParameters();
        }
        this.hashBlock = new byte[16];
        this.mainBlock = new byte[z ? 16 : this.macSize + 16];
        if (iv == null) {
            iv = new byte[0];
        }
        if (iv.length > 15) {
            throw new IllegalArgumentException("IV must be no more than 15 bytes");
        }
        if (keyParameter != null) {
            this.hashCipher.init(true, keyParameter);
            this.mainCipher.init(z, keyParameter);
            this.KtopInput = null;
        } else if (z2 != z) {
            throw new IllegalArgumentException("cannot change encrypting state without providing key.");
        }
        byte[] bArr = new byte[16];
        this.L_Asterisk = bArr;
        this.hashCipher.processBlock(bArr, 0, bArr, 0);
        this.L_Dollar = a(this.L_Asterisk);
        Vector vector = new Vector();
        this.L = vector;
        vector.addElement(a(this.L_Dollar));
        int h2 = h(iv);
        int i2 = h2 % 8;
        int i3 = h2 / 8;
        if (i2 == 0) {
            System.arraycopy(this.Stretch, i3, this.OffsetMAIN_0, 0, 16);
        } else {
            for (int i4 = 0; i4 < 16; i4++) {
                byte[] bArr2 = this.Stretch;
                i3++;
                this.OffsetMAIN_0[i4] = (byte) (((bArr2[i3] & 255) >>> (8 - i2)) | ((bArr2[i3] & 255) << i2));
            }
        }
        this.hashBlockPos = 0;
        this.mainBlockPos = 0;
        this.hashBlockCount = 0L;
        this.mainBlockCount = 0L;
        this.OffsetHASH = new byte[16];
        this.Sum = new byte[16];
        System.arraycopy(this.OffsetMAIN_0, 0, this.OffsetMAIN, 0, 16);
        this.Checksum = new byte[16];
        byte[] bArr3 = this.initialAssociatedText;
        if (bArr3 != null) {
            processAADBytes(bArr3, 0, bArr3.length);
        }
    }

    protected void k(byte[] bArr) {
        l(this.OffsetHASH, bArr);
        l(this.hashBlock, this.OffsetHASH);
        BlockCipher blockCipher = this.hashCipher;
        byte[] bArr2 = this.hashBlock;
        blockCipher.processBlock(bArr2, 0, bArr2, 0);
        l(this.Sum, this.hashBlock);
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADByte(byte b2) {
        byte[] bArr = this.hashBlock;
        int i2 = this.hashBlockPos;
        bArr[i2] = b2;
        int i3 = i2 + 1;
        this.hashBlockPos = i3;
        if (i3 == bArr.length) {
            f();
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void processAADBytes(byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            byte[] bArr2 = this.hashBlock;
            int i5 = this.hashBlockPos;
            bArr2[i5] = bArr[i2 + i4];
            int i6 = i5 + 1;
            this.hashBlockPos = i6;
            if (i6 == bArr2.length) {
                f();
            }
        }
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processByte(byte b2, byte[] bArr, int i2) {
        byte[] bArr2 = this.mainBlock;
        int i3 = this.mainBlockPos;
        bArr2[i3] = b2;
        int i4 = i3 + 1;
        this.mainBlockPos = i4;
        if (i4 == bArr2.length) {
            g(bArr, i2);
            return 16;
        }
        return 0;
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public int processBytes(byte[] bArr, int i2, int i3, byte[] bArr2, int i4) {
        if (bArr.length >= i2 + i3) {
            int i5 = 0;
            for (int i6 = 0; i6 < i3; i6++) {
                byte[] bArr3 = this.mainBlock;
                int i7 = this.mainBlockPos;
                bArr3[i7] = bArr[i2 + i6];
                int i8 = i7 + 1;
                this.mainBlockPos = i8;
                if (i8 == bArr3.length) {
                    g(bArr2, i4 + i5);
                    i5 += 16;
                }
            }
            return i5;
        }
        throw new DataLengthException("Input buffer too short");
    }

    @Override // org.bouncycastle.crypto.modes.AEADCipher
    public void reset() {
        i(true);
    }
}
