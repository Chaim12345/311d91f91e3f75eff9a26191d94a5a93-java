package org.bouncycastle.pqc.crypto.sphincsplus;

import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
class ADRS {
    public static final int FORS_ROOTS = 4;
    public static final int FORS_TREE = 3;
    public static final int TREE = 2;
    public static final int WOTS_HASH = 0;
    public static final int WOTS_PK = 1;

    /* renamed from: a  reason: collision with root package name */
    final byte[] f14571a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ADRS() {
        this.f14571a = new byte[32];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ADRS(ADRS adrs) {
        byte[] bArr = new byte[32];
        this.f14571a = bArr;
        byte[] bArr2 = adrs.f14571a;
        System.arraycopy(bArr2, 0, bArr, 0, bArr2.length);
    }

    public int getKeyPairAddress() {
        return Pack.bigEndianToInt(this.f14571a, 20);
    }

    public int getLayerAddress() {
        return Pack.bigEndianToInt(this.f14571a, 0);
    }

    public long getTreeAddress() {
        return Pack.bigEndianToLong(this.f14571a, 8);
    }

    public int getTreeHeight() {
        return Pack.bigEndianToInt(this.f14571a, 24);
    }

    public int getTreeIndex() {
        return Pack.bigEndianToInt(this.f14571a, 28);
    }

    public int getType() {
        return Pack.bigEndianToInt(this.f14571a, 16);
    }

    public void setChainAddress(int i2) {
        Pack.intToBigEndian(i2, this.f14571a, 24);
    }

    public void setHashAddress(int i2) {
        Pack.intToBigEndian(i2, this.f14571a, 28);
    }

    public void setKeyPairAddress(int i2) {
        Pack.intToBigEndian(i2, this.f14571a, 20);
    }

    public void setLayerAddress(int i2) {
        Pack.intToBigEndian(i2, this.f14571a, 0);
    }

    public void setTreeAddress(long j2) {
        Pack.longToBigEndian(j2, this.f14571a, 8);
    }

    public void setTreeHeight(int i2) {
        Pack.intToBigEndian(i2, this.f14571a, 24);
    }

    public void setTreeIndex(int i2) {
        Pack.intToBigEndian(i2, this.f14571a, 28);
    }

    public void setType(int i2) {
        Pack.intToBigEndian(i2, this.f14571a, 16);
        byte[] bArr = this.f14571a;
        Arrays.fill(bArr, 20, bArr.length, (byte) 0);
    }
}
