package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.XMSSAddress;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
final class HashTreeAddress extends XMSSAddress {
    private static final int PADDING = 0;
    private static final int TYPE = 2;
    private final int padding;
    private final int treeHeight;
    private final int treeIndex;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class Builder extends XMSSAddress.Builder<Builder> {
        private int treeHeight;
        private int treeIndex;

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder() {
            super(2);
            this.treeHeight = 0;
            this.treeIndex = 0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public XMSSAddress k() {
            return new HashTreeAddress(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress.Builder
        /* renamed from: l */
        public Builder e() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder m(int i2) {
            this.treeHeight = i2;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder n(int i2) {
            this.treeIndex = i2;
            return this;
        }
    }

    private HashTreeAddress(Builder builder) {
        super(builder);
        this.padding = 0;
        this.treeHeight = builder.treeHeight;
        this.treeIndex = builder.treeIndex;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress
    public byte[] c() {
        byte[] c2 = super.c();
        Pack.intToBigEndian(this.padding, c2, 16);
        Pack.intToBigEndian(this.treeHeight, c2, 20);
        Pack.intToBigEndian(this.treeIndex, c2, 24);
        return c2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int d() {
        return this.treeHeight;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int e() {
        return this.treeIndex;
    }
}
