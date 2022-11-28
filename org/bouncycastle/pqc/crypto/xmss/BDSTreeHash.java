package org.bouncycastle.pqc.crypto.xmss;

import java.io.Serializable;
import java.util.Objects;
import java.util.Stack;
import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
/* loaded from: classes4.dex */
class BDSTreeHash implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    private int height;
    private final int initialHeight;
    private int nextIndex;
    private XMSSNode tailNode;
    private boolean initialized = false;
    private boolean finished = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BDSTreeHash(int i2) {
        this.initialHeight = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public BDSTreeHash clone() {
        BDSTreeHash bDSTreeHash = new BDSTreeHash(this.initialHeight);
        bDSTreeHash.tailNode = this.tailNode;
        bDSTreeHash.height = this.height;
        bDSTreeHash.nextIndex = this.nextIndex;
        bDSTreeHash.initialized = this.initialized;
        bDSTreeHash.finished = this.finished;
        return bDSTreeHash;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int b() {
        if (!this.initialized || this.finished) {
            return Integer.MAX_VALUE;
        }
        return this.height;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int c() {
        return this.nextIndex;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(int i2) {
        this.tailNode = null;
        this.height = this.initialHeight;
        this.nextIndex = i2;
        this.initialized = true;
        this.finished = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean e() {
        return this.finished;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f() {
        return this.initialized;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(XMSSNode xMSSNode) {
        this.tailNode = xMSSNode;
        int height = xMSSNode.getHeight();
        this.height = height;
        if (height == this.initialHeight) {
            this.finished = true;
        }
    }

    public XMSSNode getTailNode() {
        return this.tailNode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(Stack stack, WOTSPlus wOTSPlus, byte[] bArr, byte[] bArr2, OTSHashAddress oTSHashAddress) {
        Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
        if (this.finished || !this.initialized) {
            throw new IllegalStateException("finished or not initialized");
        }
        OTSHashAddress oTSHashAddress2 = (OTSHashAddress) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).p(this.nextIndex).n(oTSHashAddress.d()).o(oTSHashAddress.e()).f(oTSHashAddress.getKeyAndMask())).l();
        HashTreeAddress hashTreeAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(oTSHashAddress2.a())).h(oTSHashAddress2.b())).n(this.nextIndex).k();
        wOTSPlus.g(wOTSPlus.f(bArr2, oTSHashAddress2), bArr);
        XMSSNode a2 = XMSSNodeUtil.a(wOTSPlus, wOTSPlus.c(oTSHashAddress2), (LTreeAddress) ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(oTSHashAddress2.a())).h(oTSHashAddress2.b())).n(this.nextIndex).l());
        while (!stack.isEmpty() && ((XMSSNode) stack.peek()).getHeight() == a2.getHeight() && ((XMSSNode) stack.peek()).getHeight() != this.initialHeight) {
            HashTreeAddress hashTreeAddress2 = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress.a())).h(hashTreeAddress.b())).m(hashTreeAddress.d()).n((hashTreeAddress.e() - 1) / 2).f(hashTreeAddress.getKeyAndMask())).k();
            XMSSNode b2 = XMSSNodeUtil.b(wOTSPlus, (XMSSNode) stack.pop(), a2, hashTreeAddress2);
            XMSSNode xMSSNode = new XMSSNode(b2.getHeight() + 1, b2.getValue());
            hashTreeAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress2.a())).h(hashTreeAddress2.b())).m(hashTreeAddress2.d() + 1).n(hashTreeAddress2.e()).f(hashTreeAddress2.getKeyAndMask())).k();
            a2 = xMSSNode;
        }
        XMSSNode xMSSNode2 = this.tailNode;
        if (xMSSNode2 == null) {
            this.tailNode = a2;
        } else if (xMSSNode2.getHeight() == a2.getHeight()) {
            HashTreeAddress hashTreeAddress3 = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress.a())).h(hashTreeAddress.b())).m(hashTreeAddress.d()).n((hashTreeAddress.e() - 1) / 2).f(hashTreeAddress.getKeyAndMask())).k();
            a2 = new XMSSNode(this.tailNode.getHeight() + 1, XMSSNodeUtil.b(wOTSPlus, this.tailNode, a2, hashTreeAddress3).getValue());
            this.tailNode = a2;
            HashTreeAddress hashTreeAddress4 = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress3.a())).h(hashTreeAddress3.b())).m(hashTreeAddress3.d() + 1).n(hashTreeAddress3.e()).f(hashTreeAddress3.getKeyAndMask())).k();
        } else {
            stack.push(a2);
        }
        if (this.tailNode.getHeight() == this.initialHeight) {
            this.finished = true;
            return;
        }
        this.height = a2.getHeight();
        this.nextIndex++;
    }
}
