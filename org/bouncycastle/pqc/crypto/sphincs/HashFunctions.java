package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
class HashFunctions {
    private static final byte[] hashc = Strings.toByteArray("expand 32-byte to 64-byte state!");
    private final Digest dig256;
    private final Digest dig512;
    private final Permute perm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashFunctions(Digest digest) {
        this(digest, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashFunctions(Digest digest, Digest digest2) {
        this.perm = new Permute();
        this.dig256 = digest;
        this.dig512 = digest2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Digest a() {
        return this.dig512;
    }

    int b(byte[] bArr, int i2, byte[] bArr2, int i3) {
        byte[] bArr3 = new byte[64];
        for (int i4 = 0; i4 < 32; i4++) {
            bArr3[i4] = bArr2[i3 + i4];
            bArr3[i4 + 32] = hashc[i4];
        }
        this.perm.a(bArr3, bArr3);
        for (int i5 = 0; i5 < 32; i5++) {
            bArr3[i5] = (byte) (bArr3[i5] ^ bArr2[(i3 + i5) + 32]);
        }
        this.perm.a(bArr3, bArr3);
        for (int i6 = 0; i6 < 32; i6++) {
            bArr[i2 + i6] = bArr3[i6];
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4) {
        byte[] bArr4 = new byte[64];
        for (int i5 = 0; i5 < 64; i5++) {
            bArr4[i5] = (byte) (bArr2[i3 + i5] ^ bArr3[i4 + i5]);
        }
        return b(bArr, i2, bArr4, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d(byte[] bArr, int i2, byte[] bArr2, int i3) {
        byte[] bArr3 = new byte[64];
        for (int i4 = 0; i4 < 32; i4++) {
            bArr3[i4] = bArr2[i3 + i4];
            bArr3[i4 + 32] = hashc[i4];
        }
        this.perm.a(bArr3, bArr3);
        for (int i5 = 0; i5 < 32; i5++) {
            bArr[i2 + i5] = bArr3[i5];
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e(byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4) {
        byte[] bArr4 = new byte[32];
        for (int i5 = 0; i5 < 32; i5++) {
            bArr4[i5] = (byte) (bArr2[i3 + i5] ^ bArr3[i4 + i5]);
        }
        return d(bArr, i2, bArr4, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f(byte[] bArr, int i2, byte[] bArr2, int i3) {
        this.dig256.update(bArr2, 0, i3);
        this.dig256.doFinal(bArr, i2);
        return 0;
    }
}
