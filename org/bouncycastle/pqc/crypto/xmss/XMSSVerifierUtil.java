package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
/* loaded from: classes4.dex */
class XMSSVerifierUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static XMSSNode a(WOTSPlus wOTSPlus, int i2, byte[] bArr, XMSSReducedSignature xMSSReducedSignature, OTSHashAddress oTSHashAddress, int i3) {
        if (bArr.length == wOTSPlus.b().d()) {
            Objects.requireNonNull(xMSSReducedSignature, "signature == null");
            Objects.requireNonNull(oTSHashAddress, "otsHashAddress == null");
            HashTreeAddress hashTreeAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).n(oTSHashAddress.f()).k();
            XMSSNode[] xMSSNodeArr = new XMSSNode[2];
            xMSSNodeArr[0] = XMSSNodeUtil.a(wOTSPlus, wOTSPlus.d(bArr, xMSSReducedSignature.getWOTSPlusSignature(), oTSHashAddress), (LTreeAddress) ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(oTSHashAddress.a())).h(oTSHashAddress.b())).n(oTSHashAddress.f()).l());
            for (int i4 = 0; i4 < i2; i4++) {
                HashTreeAddress hashTreeAddress2 = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress.a())).h(hashTreeAddress.b())).m(i4).n(hashTreeAddress.e()).f(hashTreeAddress.getKeyAndMask())).k();
                if (Math.floor(i3 / (1 << i4)) % 2.0d == 0.0d) {
                    hashTreeAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress2.a())).h(hashTreeAddress2.b())).m(hashTreeAddress2.d()).n(hashTreeAddress2.e() / 2).f(hashTreeAddress2.getKeyAndMask())).k();
                    xMSSNodeArr[1] = XMSSNodeUtil.b(wOTSPlus, xMSSNodeArr[0], xMSSReducedSignature.getAuthPath().get(i4), hashTreeAddress);
                    xMSSNodeArr[1] = new XMSSNode(xMSSNodeArr[1].getHeight() + 1, xMSSNodeArr[1].getValue());
                } else {
                    hashTreeAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress2.a())).h(hashTreeAddress2.b())).m(hashTreeAddress2.d()).n((hashTreeAddress2.e() - 1) / 2).f(hashTreeAddress2.getKeyAndMask())).k();
                    xMSSNodeArr[1] = XMSSNodeUtil.b(wOTSPlus, xMSSReducedSignature.getAuthPath().get(i4), xMSSNodeArr[0], hashTreeAddress);
                    xMSSNodeArr[1] = new XMSSNode(xMSSNodeArr[1].getHeight() + 1, xMSSNodeArr[1].getValue());
                }
                xMSSNodeArr[0] = xMSSNodeArr[1];
            }
            return xMSSNodeArr[0];
        }
        throw new IllegalArgumentException("size of messageDigest needs to be equal to size of digest");
    }
}
