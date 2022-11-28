package org.bouncycastle.pqc.crypto.xmss;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Encodable;
/* loaded from: classes4.dex */
public final class XMSSMTSignature implements XMSSStoreableObjectInterface, Encodable {
    private final long index;
    private final XMSSMTParameters params;
    private final byte[] random;
    private final List<XMSSReducedSignature> reducedSignatures;

    /* loaded from: classes4.dex */
    public static class Builder {
        private final XMSSMTParameters params;
        private long index = 0;
        private byte[] random = null;
        private List<XMSSReducedSignature> reducedSignatures = null;
        private byte[] signature = null;

        public Builder(XMSSMTParameters xMSSMTParameters) {
            this.params = xMSSMTParameters;
        }

        public XMSSMTSignature build() {
            return new XMSSMTSignature(this);
        }

        public Builder withIndex(long j2) {
            this.index = j2;
            return this;
        }

        public Builder withRandom(byte[] bArr) {
            this.random = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withReducedSignatures(List<XMSSReducedSignature> list) {
            this.reducedSignatures = list;
            return this;
        }

        public Builder withSignature(byte[] bArr) {
            this.signature = Arrays.clone(bArr);
            return this;
        }
    }

    private XMSSMTSignature(Builder builder) {
        XMSSMTParameters xMSSMTParameters = builder.params;
        this.params = xMSSMTParameters;
        Objects.requireNonNull(xMSSMTParameters, "params == null");
        int treeDigestSize = xMSSMTParameters.getTreeDigestSize();
        byte[] bArr = builder.signature;
        if (bArr == null) {
            this.index = builder.index;
            byte[] bArr2 = builder.random;
            if (bArr2 == null) {
                this.random = new byte[treeDigestSize];
            } else if (bArr2.length != treeDigestSize) {
                throw new IllegalArgumentException("size of random needs to be equal to size of digest");
            } else {
                this.random = bArr2;
            }
            List<XMSSReducedSignature> list = builder.reducedSignatures;
            this.reducedSignatures = list == null ? new ArrayList<>() : list;
            return;
        }
        int a2 = xMSSMTParameters.d().b().a();
        int ceil = (int) Math.ceil(xMSSMTParameters.getHeight() / 8.0d);
        int height = ((xMSSMTParameters.getHeight() / xMSSMTParameters.getLayers()) + a2) * treeDigestSize;
        if (bArr.length != ceil + treeDigestSize + (xMSSMTParameters.getLayers() * height)) {
            throw new IllegalArgumentException("signature has wrong size");
        }
        long bytesToXBigEndian = XMSSUtil.bytesToXBigEndian(bArr, 0, ceil);
        this.index = bytesToXBigEndian;
        if (!XMSSUtil.isIndexValid(xMSSMTParameters.getHeight(), bytesToXBigEndian)) {
            throw new IllegalArgumentException("index out of bounds");
        }
        int i2 = ceil + 0;
        this.random = XMSSUtil.extractBytesAtOffset(bArr, i2, treeDigestSize);
        this.reducedSignatures = new ArrayList();
        for (int i3 = i2 + treeDigestSize; i3 < bArr.length; i3 += height) {
            this.reducedSignatures.add(new XMSSReducedSignature.Builder(this.params.f()).withReducedSignature(XMSSUtil.extractBytesAtOffset(bArr, i3, height)).build());
        }
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() {
        return toByteArray();
    }

    public long getIndex() {
        return this.index;
    }

    public byte[] getRandom() {
        return XMSSUtil.cloneArray(this.random);
    }

    public List<XMSSReducedSignature> getReducedSignatures() {
        return this.reducedSignatures;
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int treeDigestSize = this.params.getTreeDigestSize();
        int a2 = this.params.d().b().a();
        int ceil = (int) Math.ceil(this.params.getHeight() / 8.0d);
        int height = ((this.params.getHeight() / this.params.getLayers()) + a2) * treeDigestSize;
        byte[] bArr = new byte[ceil + treeDigestSize + (this.params.getLayers() * height)];
        XMSSUtil.copyBytesAtOffset(bArr, XMSSUtil.toBytesBigEndian(this.index, ceil), 0);
        int i2 = ceil + 0;
        XMSSUtil.copyBytesAtOffset(bArr, this.random, i2);
        int i3 = i2 + treeDigestSize;
        for (XMSSReducedSignature xMSSReducedSignature : this.reducedSignatures) {
            XMSSUtil.copyBytesAtOffset(bArr, xMSSReducedSignature.toByteArray(), i3);
            i3 += height;
        }
        return bArr;
    }
}
