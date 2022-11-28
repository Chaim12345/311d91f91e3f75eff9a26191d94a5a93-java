package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.sphincs.Tree;
import org.bouncycastle.tls.CipherSuite;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public class SPHINCS256Signer implements MessageSigner {
    private final HashFunctions hashFunctions;
    private byte[] keyData;

    public SPHINCS256Signer(Digest digest, Digest digest2) {
        if (digest.getDigestSize() != 32) {
            throw new IllegalArgumentException("n-digest needs to produce 32 bytes of output");
        }
        if (digest2.getDigestSize() != 64) {
            throw new IllegalArgumentException("2n-digest needs to produce 64 bytes of output");
        }
        this.hashFunctions = new HashFunctions(digest, digest2);
    }

    static void a(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, int i2, Tree.leafaddr leafaddrVar, byte[] bArr3, byte[] bArr4, int i3) {
        Tree.leafaddr leafaddrVar2 = new Tree.leafaddr(leafaddrVar);
        byte[] bArr5 = new byte[2048];
        byte[] bArr6 = new byte[1024];
        byte[] bArr7 = new byte[68608];
        leafaddrVar2.f14570c = 0L;
        while (true) {
            long j2 = leafaddrVar2.f14570c;
            if (j2 >= 32) {
                break;
            }
            Seed.a(hashFunctions, bArr6, (int) (j2 * 32), bArr3, leafaddrVar2);
            leafaddrVar2.f14570c++;
        }
        Wots wots = new Wots();
        leafaddrVar2.f14570c = 0L;
        while (true) {
            long j3 = leafaddrVar2.f14570c;
            if (j3 >= 32) {
                break;
            }
            wots.c(hashFunctions, bArr7, (int) (67 * j3 * 32), bArr6, (int) (j3 * 32), bArr4, 0);
            leafaddrVar2.f14570c++;
        }
        leafaddrVar2.f14570c = 0L;
        while (true) {
            long j4 = leafaddrVar2.f14570c;
            if (j4 >= 32) {
                break;
            }
            Tree.b(hashFunctions, bArr5, (int) ((j4 * 32) + 1024), bArr7, (int) (j4 * 67 * 32), bArr4, 0);
            leafaddrVar2.f14570c++;
        }
        int i4 = 0;
        for (int i5 = 32; i5 > 0; i5 >>>= 1) {
            for (int i6 = 0; i6 < i5; i6 += 2) {
                hashFunctions.c(bArr5, ((i5 >>> 1) * 32) + ((i6 >>> 1) * 32), bArr5, (i5 * 32) + (i6 * 32), bArr4, (i4 + 7) * 2 * 32);
            }
            i4++;
        }
        int i7 = (int) leafaddrVar.f14570c;
        for (int i8 = 0; i8 < i3; i8++) {
            System.arraycopy(bArr5, ((32 >>> i8) * 32) + (((i7 >>> i8) ^ 1) * 32), bArr2, i2 + (i8 * 32), 32);
        }
        System.arraycopy(bArr5, 32, bArr, 0, 32);
    }

    static void c(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, int i3, byte[] bArr4, int i4) {
        byte[] bArr5 = new byte[64];
        if ((i2 & 1) != 0) {
            for (int i5 = 0; i5 < 32; i5++) {
                bArr5[i5 + 32] = bArr2[i5];
            }
            for (int i6 = 0; i6 < 32; i6++) {
                bArr5[i6] = bArr3[i3 + i6];
            }
        } else {
            for (int i7 = 0; i7 < 32; i7++) {
                bArr5[i7] = bArr2[i7];
            }
            for (int i8 = 0; i8 < 32; i8++) {
                bArr5[i8 + 32] = bArr3[i3 + i8];
            }
        }
        int i9 = i3 + 32;
        int i10 = 0;
        int i11 = i2;
        while (i10 < i4 - 1) {
            int i12 = i11 >>> 1;
            if ((i12 & 1) != 0) {
                hashFunctions.c(bArr5, 32, bArr5, 0, bArr4, (i10 + 7) * 2 * 32);
                for (int i13 = 0; i13 < 32; i13++) {
                    bArr5[i13] = bArr3[i9 + i13];
                }
            } else {
                hashFunctions.c(bArr5, 0, bArr5, 0, bArr4, (i10 + 7) * 2 * 32);
                for (int i14 = 0; i14 < 32; i14++) {
                    bArr5[i14 + 32] = bArr3[i9 + i14];
                }
            }
            i9 += 32;
            i10++;
            i11 = i12;
        }
        hashFunctions.c(bArr, 0, bArr5, 0, bArr4, ((i4 + 7) - 1) * 2 * 32);
    }

    private void zerobytes(byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 != i3; i4++) {
            bArr[i2 + i4] = 0;
        }
    }

    byte[] b(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[41000];
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[64];
        long[] jArr = new long[8];
        byte[] bArr6 = new byte[32];
        byte[] bArr7 = new byte[32];
        byte[] bArr8 = new byte[1024];
        byte[] bArr9 = new byte[1088];
        for (int i2 = 0; i2 < 1088; i2++) {
            bArr9[i2] = bArr2[i2];
        }
        System.arraycopy(bArr9, 1056, bArr3, 40968, 32);
        Digest a2 = hashFunctions.a();
        byte[] bArr10 = new byte[a2.getDigestSize()];
        a2.update(bArr3, 40968, 32);
        a2.update(bArr, 0, bArr.length);
        a2.doFinal(bArr10, 0);
        zerobytes(bArr3, 40968, 32);
        for (int i3 = 0; i3 != 8; i3++) {
            jArr[i3] = Pack.littleEndianToLong(bArr10, i3 * 8);
        }
        long j2 = jArr[0] & 1152921504606846975L;
        System.arraycopy(bArr10, 16, bArr4, 0, 32);
        System.arraycopy(bArr4, 0, bArr3, 39912, 32);
        Tree.leafaddr leafaddrVar = new Tree.leafaddr();
        leafaddrVar.f14568a = 11;
        leafaddrVar.f14569b = 0L;
        leafaddrVar.f14570c = 0L;
        System.arraycopy(bArr9, 32, bArr3, 39944, 1024);
        Tree.c(hashFunctions, bArr3, 40968, 5, bArr9, leafaddrVar, bArr3, 39944);
        Digest a3 = hashFunctions.a();
        a3.update(bArr3, 39912, 1088);
        a3.update(bArr, 0, bArr.length);
        a3.doFinal(bArr5, 0);
        Tree.leafaddr leafaddrVar2 = new Tree.leafaddr();
        leafaddrVar2.f14568a = 12;
        leafaddrVar2.f14570c = (int) (j2 & 31);
        leafaddrVar2.f14569b = j2 >>> 5;
        for (int i4 = 0; i4 < 32; i4++) {
            bArr3[i4] = bArr4[i4];
        }
        byte[] bArr11 = bArr8;
        System.arraycopy(bArr9, 32, bArr11, 0, 1024);
        for (int i5 = 0; i5 < 8; i5++) {
            bArr3[32 + i5] = (byte) ((j2 >>> (i5 * 8)) & 255);
        }
        Seed.a(hashFunctions, bArr7, 0, bArr9, leafaddrVar2);
        new Horst();
        byte[] bArr12 = bArr9;
        Wots wots = new Wots();
        int b2 = 40 + Horst.b(hashFunctions, bArr3, 40, bArr6, bArr7, bArr11, bArr5);
        int i6 = 0;
        for (int i7 = 12; i6 < i7; i7 = 12) {
            leafaddrVar2.f14568a = i6;
            byte[] bArr13 = bArr12;
            Seed.a(hashFunctions, bArr7, 0, bArr13, leafaddrVar2);
            int i8 = b2;
            int i9 = b2;
            byte[] bArr14 = bArr11;
            wots.d(hashFunctions, bArr3, i8, bArr6, bArr7, bArr14);
            int i10 = i9 + 2144;
            a(hashFunctions, bArr6, bArr3, i10, leafaddrVar2, bArr13, bArr14, 5);
            b2 = i10 + CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256;
            long j3 = leafaddrVar2.f14569b;
            leafaddrVar2.f14570c = (int) (j3 & 31);
            leafaddrVar2.f14569b = j3 >>> 5;
            i6++;
            bArr12 = bArr13;
            bArr11 = bArr11;
        }
        zerobytes(bArr12, 0, 1088);
        return bArr3;
    }

    boolean d(HashFunctions hashFunctions, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[2144];
        byte[] bArr5 = new byte[32];
        byte[] bArr6 = new byte[32];
        byte[] bArr7 = new byte[41000];
        byte[] bArr8 = new byte[1056];
        if (bArr2.length == 41000) {
            byte[] bArr9 = new byte[64];
            for (int i2 = 0; i2 < 1056; i2++) {
                bArr8[i2] = bArr3[i2];
            }
            byte[] bArr10 = new byte[32];
            for (int i3 = 0; i3 < 32; i3++) {
                bArr10[i3] = bArr2[i3];
            }
            System.arraycopy(bArr2, 0, bArr7, 0, 41000);
            Digest a2 = hashFunctions.a();
            a2.update(bArr10, 0, 32);
            a2.update(bArr8, 0, 1056);
            a2.update(bArr, 0, bArr.length);
            a2.doFinal(bArr9, 0);
            long j2 = 0;
            for (int i4 = 0; i4 < 8; i4++) {
                j2 ^= (bArr7[32 + i4] & 255) << (i4 * 8);
            }
            new Horst();
            Horst.c(hashFunctions, bArr6, bArr7, 40, bArr8, bArr9);
            Wots wots = new Wots();
            int i5 = 0;
            int i6 = 13352;
            while (i5 < 12) {
                byte[] bArr11 = bArr8;
                wots.e(hashFunctions, bArr4, bArr7, i6, bArr6, bArr8);
                int i7 = i6 + 2144;
                Tree.b(hashFunctions, bArr5, 0, bArr4, 0, bArr11, 0);
                byte[] bArr12 = bArr7;
                c(hashFunctions, bArr6, bArr5, (int) (31 & j2), bArr12, i7, bArr11, 5);
                j2 >>= 5;
                i6 = i7 + CipherSuite.TLS_DH_RSA_WITH_AES_128_GCM_SHA256;
                i5++;
                bArr7 = bArr12;
                bArr8 = bArr11;
            }
            byte[] bArr13 = bArr8;
            boolean z = true;
            for (int i8 = 0; i8 < 32; i8++) {
                if (bArr6[i8] != bArr13[i8 + 1024]) {
                    z = false;
                }
            }
            return z;
        }
        throw new IllegalArgumentException("signature wrong size");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        return b(this.hashFunctions, bArr, this.keyData);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.keyData = ((SPHINCSPublicKeyParameters) cipherParameters).getKeyData();
        } else if (cipherParameters instanceof ParametersWithRandom) {
            this.keyData = ((SPHINCSPrivateKeyParameters) ((ParametersWithRandom) cipherParameters).getParameters()).getKeyData();
        } else {
            this.keyData = ((SPHINCSPrivateKeyParameters) cipherParameters).getKeyData();
        }
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        return d(this.hashFunctions, bArr, bArr2, this.keyData);
    }
}
