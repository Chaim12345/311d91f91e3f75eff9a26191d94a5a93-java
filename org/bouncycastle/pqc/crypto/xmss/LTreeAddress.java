package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.XMSSAddress;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
final class LTreeAddress extends XMSSAddress {
    private static final int TYPE = 1;
    private final int lTreeAddress;
    private final int treeHeight;
    private final int treeIndex;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class Builder extends XMSSAddress.Builder<Builder> {
        private int lTreeAddress;
        private int treeHeight;
        private int treeIndex;

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder() {
            super(1);
            this.lTreeAddress = 0;
            this.treeHeight = 0;
            this.treeIndex = 0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public XMSSAddress l() {
            return new LTreeAddress(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress.Builder
        /* renamed from: m */
        public Builder e() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder n(int i2) {
            this.lTreeAddress = i2;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder o(int i2) {
            this.treeHeight = i2;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder p(int i2) {
            this.treeIndex = i2;
            return this;
        }
    }

    private LTreeAddress(Builder builder) {
        super(builder);
        this.lTreeAddress = builder.lTreeAddress;
        this.treeHeight = builder.treeHeight;
        this.treeIndex = builder.treeIndex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress
    public byte[] c() {
        byte[] c2 = super.c();
        Pack.intToBigEndian(this.lTreeAddress, c2, 16);
        Pack.intToBigEndian(this.treeHeight, c2, 20);
        Pack.intToBigEndian(this.treeIndex, c2, 24);
        return c2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int d() {
        return this.lTreeAddress;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int e() {
        return this.treeHeight;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int f() {
        return this.treeIndex;
    }
}
