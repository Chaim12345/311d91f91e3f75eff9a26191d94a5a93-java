package org.bouncycastle.pqc.crypto.xmss;

import org.bouncycastle.pqc.crypto.xmss.XMSSAddress;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
final class OTSHashAddress extends XMSSAddress {
    private static final int TYPE = 0;
    private final int chainAddress;
    private final int hashAddress;
    private final int otsAddress;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class Builder extends XMSSAddress.Builder<Builder> {
        private int chainAddress;
        private int hashAddress;
        private int otsAddress;

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder() {
            super(0);
            this.otsAddress = 0;
            this.chainAddress = 0;
            this.hashAddress = 0;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public XMSSAddress l() {
            return new OTSHashAddress(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress.Builder
        /* renamed from: m */
        public Builder e() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder n(int i2) {
            this.chainAddress = i2;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder o(int i2) {
            this.hashAddress = i2;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder p(int i2) {
            this.otsAddress = i2;
            return this;
        }
    }

    private OTSHashAddress(Builder builder) {
        super(builder);
        this.otsAddress = builder.otsAddress;
        this.chainAddress = builder.chainAddress;
        this.hashAddress = builder.hashAddress;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSAddress
    public byte[] c() {
        byte[] c2 = super.c();
        Pack.intToBigEndian(this.otsAddress, c2, 16);
        Pack.intToBigEndian(this.chainAddress, c2, 20);
        Pack.intToBigEndian(this.hashAddress, c2, 24);
        return c2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int d() {
        return this.chainAddress;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int e() {
        return this.hashAddress;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int f() {
        return this.otsAddress;
    }
}
