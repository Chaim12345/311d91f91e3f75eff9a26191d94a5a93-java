package org.bouncycastle.pqc.math.linearalgebra;

import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class Permutation {
    private int[] perm;

    public Permutation(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("invalid length");
        }
        this.perm = new int[i2];
        for (int i3 = i2 - 1; i3 >= 0; i3--) {
            this.perm[i3] = i3;
        }
    }

    public Permutation(int i2, SecureRandom secureRandom) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("invalid length");
        }
        this.perm = new int[i2];
        int[] iArr = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iArr[i3] = i3;
        }
        int i4 = i2;
        for (int i5 = 0; i5 < i2; i5++) {
            int a2 = RandUtils.a(secureRandom, i4);
            i4--;
            this.perm[i5] = iArr[a2];
            iArr[a2] = iArr[i4];
        }
    }

    public Permutation(byte[] bArr) {
        if (bArr.length <= 4) {
            throw new IllegalArgumentException("invalid encoding");
        }
        int OS2IP = LittleEndianConversions.OS2IP(bArr, 0);
        int ceilLog256 = IntegerFunctions.ceilLog256(OS2IP - 1);
        if (bArr.length != (OS2IP * ceilLog256) + 4) {
            throw new IllegalArgumentException("invalid encoding");
        }
        this.perm = new int[OS2IP];
        for (int i2 = 0; i2 < OS2IP; i2++) {
            this.perm[i2] = LittleEndianConversions.OS2IP(bArr, (i2 * ceilLog256) + 4, ceilLog256);
        }
        if (!isPermutation(this.perm)) {
            throw new IllegalArgumentException("invalid encoding");
        }
    }

    public Permutation(int[] iArr) {
        if (!isPermutation(iArr)) {
            throw new IllegalArgumentException("array is not a permutation vector");
        }
        this.perm = IntUtils.clone(iArr);
    }

    private boolean isPermutation(int[] iArr) {
        int length = iArr.length;
        boolean[] zArr = new boolean[length];
        for (int i2 = 0; i2 < length; i2++) {
            if (iArr[i2] < 0 || iArr[i2] >= length || zArr[iArr[i2]]) {
                return false;
            }
            zArr[iArr[i2]] = true;
        }
        return true;
    }

    public Permutation computeInverse() {
        Permutation permutation = new Permutation(this.perm.length);
        for (int length = this.perm.length - 1; length >= 0; length--) {
            permutation.perm[this.perm[length]] = length;
        }
        return permutation;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Permutation) {
            return IntUtils.equals(this.perm, ((Permutation) obj).perm);
        }
        return false;
    }

    public byte[] getEncoded() {
        int length = this.perm.length;
        int ceilLog256 = IntegerFunctions.ceilLog256(length - 1);
        byte[] bArr = new byte[(length * ceilLog256) + 4];
        LittleEndianConversions.I2OSP(length, bArr, 0);
        for (int i2 = 0; i2 < length; i2++) {
            LittleEndianConversions.I2OSP(this.perm[i2], bArr, (i2 * ceilLog256) + 4, ceilLog256);
        }
        return bArr;
    }

    public int[] getVector() {
        return IntUtils.clone(this.perm);
    }

    public int hashCode() {
        return Arrays.hashCode(this.perm);
    }

    public Permutation rightMultiply(Permutation permutation) {
        int length = permutation.perm.length;
        int[] iArr = this.perm;
        if (length == iArr.length) {
            Permutation permutation2 = new Permutation(iArr.length);
            for (int length2 = this.perm.length - 1; length2 >= 0; length2--) {
                permutation2.perm[length2] = this.perm[permutation.perm[length2]];
            }
            return permutation2;
        }
        throw new IllegalArgumentException("length mismatch");
    }

    public String toString() {
        String str = "[" + this.perm[0];
        for (int i2 = 1; i2 < this.perm.length; i2++) {
            str = str + ", " + this.perm[i2];
        }
        return str + "]";
    }
}
