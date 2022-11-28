package org.bouncycastle.pqc.crypto.xmss;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/* loaded from: classes4.dex */
public class XMSSReducedSignature implements XMSSStoreableObjectInterface {
    private final List<XMSSNode> authPath;
    private final XMSSParameters params;
    private final WOTSPlusSignature wotsPlusSignature;

    /* loaded from: classes4.dex */
    public static class Builder {
        private final XMSSParameters params;
        private WOTSPlusSignature wotsPlusSignature = null;
        private List<XMSSNode> authPath = null;
        private byte[] reducedSignature = null;

        public Builder(XMSSParameters xMSSParameters) {
            this.params = xMSSParameters;
        }

        public XMSSReducedSignature build() {
            return new XMSSReducedSignature(this);
        }

        public Builder withAuthPath(List<XMSSNode> list) {
            this.authPath = list;
            return this;
        }

        public Builder withReducedSignature(byte[] bArr) {
            this.reducedSignature = XMSSUtil.cloneArray(bArr);
            return this;
        }

        public Builder withWOTSPlusSignature(WOTSPlusSignature wOTSPlusSignature) {
            this.wotsPlusSignature = wOTSPlusSignature;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public XMSSReducedSignature(Builder builder) {
        List<XMSSNode> list;
        XMSSParameters xMSSParameters = builder.params;
        this.params = xMSSParameters;
        Objects.requireNonNull(xMSSParameters, "params == null");
        int treeDigestSize = xMSSParameters.getTreeDigestSize();
        int a2 = xMSSParameters.e().b().a();
        int height = xMSSParameters.getHeight();
        byte[] bArr = builder.reducedSignature;
        if (bArr == null) {
            WOTSPlusSignature wOTSPlusSignature = builder.wotsPlusSignature;
            this.wotsPlusSignature = wOTSPlusSignature == null ? new WOTSPlusSignature(xMSSParameters.e().b(), (byte[][]) Array.newInstance(byte.class, a2, treeDigestSize)) : wOTSPlusSignature;
            list = builder.authPath;
            if (list == null) {
                list = new ArrayList<>();
            } else if (list.size() != height) {
                throw new IllegalArgumentException("size of authPath needs to be equal to height of tree");
            }
        } else if (bArr.length != (a2 * treeDigestSize) + (height * treeDigestSize)) {
            throw new IllegalArgumentException("signature has wrong size");
        } else {
            byte[][] bArr2 = new byte[a2];
            int i2 = 0;
            for (int i3 = 0; i3 < a2; i3++) {
                bArr2[i3] = XMSSUtil.extractBytesAtOffset(bArr, i2, treeDigestSize);
                i2 += treeDigestSize;
            }
            this.wotsPlusSignature = new WOTSPlusSignature(this.params.e().b(), bArr2);
            list = new ArrayList<>();
            for (int i4 = 0; i4 < height; i4++) {
                list.add(new XMSSNode(i4, XMSSUtil.extractBytesAtOffset(bArr, i2, treeDigestSize)));
                i2 += treeDigestSize;
            }
        }
        this.authPath = list;
    }

    public List<XMSSNode> getAuthPath() {
        return this.authPath;
    }

    public XMSSParameters getParams() {
        return this.params;
    }

    public WOTSPlusSignature getWOTSPlusSignature() {
        return this.wotsPlusSignature;
    }

    @Override // org.bouncycastle.pqc.crypto.xmss.XMSSStoreableObjectInterface
    public byte[] toByteArray() {
        int treeDigestSize = this.params.getTreeDigestSize();
        byte[] bArr = new byte[(this.params.e().b().a() * treeDigestSize) + (this.params.getHeight() * treeDigestSize)];
        int i2 = 0;
        for (byte[] bArr2 : this.wotsPlusSignature.toByteArray()) {
            XMSSUtil.copyBytesAtOffset(bArr, bArr2, i2);
            i2 += treeDigestSize;
        }
        for (int i3 = 0; i3 < this.authPath.size(); i3++) {
            XMSSUtil.copyBytesAtOffset(bArr, this.authPath.get(i3).getValue(), i2);
            i2 += treeDigestSize;
        }
        return bArr;
    }
}
