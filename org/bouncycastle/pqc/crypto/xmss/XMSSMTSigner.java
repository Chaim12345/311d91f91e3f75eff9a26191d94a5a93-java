package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.crypto.StateAwareMessageSigner;
import org.bouncycastle.pqc.crypto.xmss.OTSHashAddress;
import org.bouncycastle.pqc.crypto.xmss.XMSSMTSignature;
import org.bouncycastle.pqc.crypto.xmss.XMSSReducedSignature;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class XMSSMTSigner implements StateAwareMessageSigner {
    private boolean hasGenerated;
    private boolean initSign;
    private XMSSMTParameters params;
    private XMSSMTPrivateKeyParameters privateKey;
    private XMSSMTPublicKeyParameters publicKey;
    private WOTSPlus wotsPlus;
    private XMSSParameters xmssParams;

    private WOTSPlusSignature wotsSign(byte[] bArr, OTSHashAddress oTSHashAddress) {
        if (bArr.length == this.params.getTreeDigestSize()) {
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            WOTSPlus wOTSPlus = this.wotsPlus;
            wOTSPlus.g(wOTSPlus.f(this.privateKey.getSecretKeySeed(), oTSHashAddress), this.privateKey.getPublicSeed());
            return this.wotsPlus.h(bArr, oTSHashAddress);
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        byte[] byteArray;
        Objects.requireNonNull(bArr, "message == null");
        if (this.initSign) {
            XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = this.privateKey;
            if (xMSSMTPrivateKeyParameters != null) {
                synchronized (xMSSMTPrivateKeyParameters) {
                    if (this.privateKey.getUsagesRemaining() <= 0) {
                        throw new IllegalStateException("no usages of private key remaining");
                    }
                    if (this.privateKey.a().isEmpty()) {
                        throw new IllegalStateException("not initialized");
                    }
                    BDSStateMap a2 = this.privateKey.a();
                    long index = this.privateKey.getIndex();
                    this.params.getHeight();
                    int height = this.xmssParams.getHeight();
                    if (this.privateKey.getUsagesRemaining() <= 0) {
                        throw new IllegalStateException("index out of bounds");
                    }
                    byte[] d2 = this.wotsPlus.a().d(this.privateKey.getSecretKeyPRF(), XMSSUtil.toBytesBigEndian(index, 32));
                    byte[] c2 = this.wotsPlus.a().c(Arrays.concatenate(d2, this.privateKey.getRoot(), XMSSUtil.toBytesBigEndian(index, this.params.getTreeDigestSize())), bArr);
                    this.hasGenerated = true;
                    XMSSMTSignature build = new XMSSMTSignature.Builder(this.params).withIndex(index).withRandom(d2).build();
                    long treeIndex = XMSSUtil.getTreeIndex(index, height);
                    int leafIndex = XMSSUtil.getLeafIndex(index, height);
                    this.wotsPlus.g(new byte[this.params.getTreeDigestSize()], this.privateKey.getPublicSeed());
                    OTSHashAddress oTSHashAddress = (OTSHashAddress) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().h(treeIndex)).p(leafIndex).l();
                    if (a2.a(0) == null || leafIndex == 0) {
                        a2.b(0, new BDS(this.xmssParams, this.privateKey.getPublicSeed(), this.privateKey.getSecretKeySeed(), oTSHashAddress));
                    }
                    build.getReducedSignatures().add(new XMSSReducedSignature.Builder(this.xmssParams).withWOTSPlusSignature(wotsSign(c2, oTSHashAddress)).withAuthPath(a2.a(0).a()).build());
                    for (int i2 = 1; i2 < this.params.getLayers(); i2++) {
                        XMSSNode c3 = a2.a(i2 - 1).c();
                        int leafIndex2 = XMSSUtil.getLeafIndex(treeIndex, height);
                        treeIndex = XMSSUtil.getTreeIndex(treeIndex, height);
                        OTSHashAddress oTSHashAddress2 = (OTSHashAddress) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().g(i2)).h(treeIndex)).p(leafIndex2).l();
                        WOTSPlusSignature wotsSign = wotsSign(c3.getValue(), oTSHashAddress2);
                        if (a2.a(i2) == null || XMSSUtil.isNewBDSInitNeeded(index, height, i2)) {
                            a2.b(i2, new BDS(this.xmssParams, this.privateKey.getPublicSeed(), this.privateKey.getSecretKeySeed(), oTSHashAddress2));
                        }
                        build.getReducedSignatures().add(new XMSSReducedSignature.Builder(this.xmssParams).withWOTSPlusSignature(wotsSign).withAuthPath(a2.a(i2).a()).build());
                    }
                    byteArray = build.toByteArray();
                    this.privateKey.b();
                }
                return byteArray;
            }
            throw new IllegalStateException("signing key no longer usable");
        }
        throw new IllegalStateException("signer not initialized for signature generation");
    }

    @Override // org.bouncycastle.pqc.crypto.StateAwareMessageSigner
    public AsymmetricKeyParameter getUpdatedPrivateKey() {
        if (this.hasGenerated) {
            XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = this.privateKey;
            this.privateKey = null;
            return xMSSMTPrivateKeyParameters;
        }
        XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters2 = this.privateKey;
        if (xMSSMTPrivateKeyParameters2 != null) {
            this.privateKey = xMSSMTPrivateKeyParameters2.getNextKey();
        }
        return xMSSMTPrivateKeyParameters2;
    }

    public long getUsagesRemaining() {
        return this.privateKey.getUsagesRemaining();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        XMSSMTParameters parameters;
        if (z) {
            this.initSign = true;
            this.hasGenerated = false;
            XMSSMTPrivateKeyParameters xMSSMTPrivateKeyParameters = (XMSSMTPrivateKeyParameters) cipherParameters;
            this.privateKey = xMSSMTPrivateKeyParameters;
            parameters = xMSSMTPrivateKeyParameters.getParameters();
        } else {
            this.initSign = false;
            XMSSMTPublicKeyParameters xMSSMTPublicKeyParameters = (XMSSMTPublicKeyParameters) cipherParameters;
            this.publicKey = xMSSMTPublicKeyParameters;
            parameters = xMSSMTPublicKeyParameters.getParameters();
        }
        this.params = parameters;
        this.xmssParams = parameters.f();
        this.wotsPlus = this.params.d();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        Objects.requireNonNull(bArr, "message == null");
        Objects.requireNonNull(bArr2, "signature == null");
        Objects.requireNonNull(this.publicKey, "publicKey == null");
        XMSSMTSignature build = new XMSSMTSignature.Builder(this.params).withSignature(bArr2).build();
        byte[] c2 = this.wotsPlus.a().c(Arrays.concatenate(build.getRandom(), this.publicKey.getRoot(), XMSSUtil.toBytesBigEndian(build.getIndex(), this.params.getTreeDigestSize())), bArr);
        long index = build.getIndex();
        int height = this.xmssParams.getHeight();
        long treeIndex = XMSSUtil.getTreeIndex(index, height);
        int leafIndex = XMSSUtil.getLeafIndex(index, height);
        this.wotsPlus.g(new byte[this.params.getTreeDigestSize()], this.publicKey.getPublicSeed());
        OTSHashAddress oTSHashAddress = (OTSHashAddress) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().h(treeIndex)).p(leafIndex).l();
        XMSSNode a2 = XMSSVerifierUtil.a(this.wotsPlus, height, c2, build.getReducedSignatures().get(0), oTSHashAddress, leafIndex);
        int i2 = 1;
        while (i2 < this.params.getLayers()) {
            int leafIndex2 = XMSSUtil.getLeafIndex(treeIndex, height);
            long treeIndex2 = XMSSUtil.getTreeIndex(treeIndex, height);
            OTSHashAddress oTSHashAddress2 = (OTSHashAddress) ((OTSHashAddress.Builder) ((OTSHashAddress.Builder) new OTSHashAddress.Builder().g(i2)).h(treeIndex2)).p(leafIndex2).l();
            a2 = XMSSVerifierUtil.a(this.wotsPlus, height, a2.getValue(), build.getReducedSignatures().get(i2), oTSHashAddress2, leafIndex2);
            i2++;
            treeIndex = treeIndex2;
        }
        return Arrays.constantTimeAreEqual(a2.getValue(), this.publicKey.getRoot());
    }
}
