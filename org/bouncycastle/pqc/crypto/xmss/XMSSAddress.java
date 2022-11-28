package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public abstract class XMSSAddress {
    private final int keyAndMask;
    private final int layerAddress;
    private final long treeAddress;
    private final int type;

    /* loaded from: classes4.dex */
    protected static abstract class Builder<T extends Builder> {
        private final int type;
        private int layerAddress = 0;
        private long treeAddress = 0;
        private int keyAndMask = 0;

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder(int i2) {
            this.type = i2;
        }

        protected abstract Builder e();

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder f(int i2) {
            this.keyAndMask = i2;
            return e();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder g(int i2) {
            this.layerAddress = i2;
            return e();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder h(long j2) {
            this.treeAddress = j2;
            return e();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XMSSAddress(Builder builder) {
        this.layerAddress = builder.layerAddress;
        this.treeAddress = builder.treeAddress;
        this.type = builder.type;
        this.keyAndMask = builder.keyAndMask;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int a() {
        return this.layerAddress;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long b() {
        return this.treeAddress;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] c() {
        byte[] bArr = new byte[32];
        Pack.intToBigEndian(this.layerAddress, bArr, 0);
        Pack.longToBigEndian(this.treeAddress, bArr, 4);
        Pack.intToBigEndian(this.type, bArr, 12);
        Pack.intToBigEndian(this.keyAndMask, bArr, 28);
        return bArr;
    }

    public final int getKeyAndMask() {
        return this.keyAndMask;
    }

    public final int getType() {
        return this.type;
    }
}
