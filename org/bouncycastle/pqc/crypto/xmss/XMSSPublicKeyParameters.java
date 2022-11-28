package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.util.Encodable;
import org.bouncycastle.util.Pack;
/* loaded from: classes4.dex */
public final class XMSSPublicKeyParameters extends XMSSKeyParameters implements XMSSStoreableObjectInterface, Encodable {
    private final int oid;
    private final XMSSParameters params;
    private final byte[] publicSeed;
    private final byte[] root;

    /* loaded from: classes4.dex */
    public static class Builder {
        private final XMSSParameters params;
        private byte[] root = null;
        private byte[] publicSeed = null;
        private byte[] publicKey = null;

        public Builder(XMSSParameters xMSSParameters) {
            this.params = xMSSParameters;
        }

        public XMSSPublicKeyParameters build() {
            return new XMSSPublicKeyParameters(this);
        }

        public Builder withPublicKey(byte[] bArr) {
            this.publicKey = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withPublicSeed(byte[] bArr) {
            this.publicSeed = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withRoot(byte[] bArr) {
            this.root = XMSSUtil.cloneArray(bArr);
            return this;
        }
    }

    private XMSSPublicKeyParameters(Builder builder) {
        super(false, builder.params.d());
        XMSSParameters xMSSParameters = builder.params;
        this.params = xMSSParameters;
        Objects.requireNonNull(xMSSParameters, "params == null");
        int treeDigestSize = xMSSParameters.getTreeDigestSize();
        byte[] bArr = builder.publicKey;
        if (bArr != null) {
            if (bArr.length == treeDigestSize + treeDigestSize) {
                this.oid = 0;
                this.root = XMSSUtil.extractBytesAtOffset(bArr, 0, treeDigestSize);
                this.publicSeed = XMSSUtil.extractBytesAtOffset(bArr, treeDigestSize + 0, treeDigestSize);
                return;
            } else if (bArr.length != treeDigestSize + 4 + treeDigestSize) {
                throw new IllegalArgumentException("public key has wrong size");
            } else {
                this.oid = Pack.bigEndianToInt(bArr, 0);
                this.root = XMSSUtil.extractBytesAtOffset(bArr, 4, treeDigestSize);
                this.publicSeed = XMSSUtil.extractBytesAtOffset(bArr, 4 + treeDigestSize, treeDigestSize);
                return;
            }
        }
        if (xMSSParameters.c() != null) {
            this.oid = xMSSParameters.c().getOid();
        } else {
            this.oid = 0;
        }
        byte[] bArr2 = builder.root;
        if (bArr2 == null) {
            this.root = new byte[treeDigestSize];
        } else if (bArr2.length != treeDigestSize) {
            throw new IllegalArgumentException("length of root must be equal to length of digest");
        } else {
            this.root = bArr2;
        }
        byte[] bArr3 = builder.publicSeed;
        if (bArr3 == null) {
            this.publicSeed = new byte[treeDigestSize];
        } else if (bArr3.length != treeDigestSize) {
            throw new IllegalArgumentException("length of publicSeed must be equal to length of digest");
        } else {
            this.publicSeed = bArr3;
        }
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() {
        return toByteArray();
    }

    public XMSSParameters getParameters() {
        return this.params;
    }

    public byte[] getPublicSeed() {
        return XMSSUtil.cloneArray(this.publicSeed);
    }

    public byte[] getRoot() {
        return XMSSUtil.cloneArray(this.root);
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        byte[] bArr;
        int treeDigestSize = this.params.getTreeDigestSize();
        int i2 = this.oid;
        int i3 = 0;
        if (i2 != 0) {
            bArr = new byte[treeDigestSize + 4 + treeDigestSize];
            Pack.intToBigEndian(i2, bArr, 0);
            i3 = 4;
        } else {
            bArr = new byte[treeDigestSize + treeDigestSize];
        }
        XMSSUtil.copyBytesAtOffset(bArr, this.root, i3);
        XMSSUtil.copyBytesAtOffset(bArr, this.publicSeed, i3 + treeDigestSize);
        return bArr;
    }
}
