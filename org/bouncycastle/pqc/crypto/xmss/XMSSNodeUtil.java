package org.bouncycastle.pqc.crypto.xmss;

import java.util.Objects;
import org.bouncycastle.pqc.crypto.xmss.HashTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.LTreeAddress;
import org.bouncycastle.pqc.crypto.xmss.XMSSAddress;
/* loaded from: classes4.dex */
class XMSSNodeUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static XMSSNode a(WOTSPlus wOTSPlus, WOTSPlusPublicKeyParameters wOTSPlusPublicKeyParameters, LTreeAddress lTreeAddress) {
        double d2;
        Objects.requireNonNull(wOTSPlusPublicKeyParameters, "publicKey == null");
        Objects.requireNonNull(lTreeAddress, "address == null");
        int a2 = wOTSPlus.b().a();
        byte[][] a3 = wOTSPlusPublicKeyParameters.a();
        XMSSNode[] xMSSNodeArr = new XMSSNode[a3.length];
        for (int i2 = 0; i2 < a3.length; i2++) {
            xMSSNodeArr[i2] = new XMSSNode(0, a3[i2]);
        }
        XMSSAddress.Builder f2 = ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(lTreeAddress.a())).h(lTreeAddress.b())).n(lTreeAddress.d()).o(0).p(lTreeAddress.f()).f(lTreeAddress.getKeyAndMask());
        while (true) {
            LTreeAddress lTreeAddress2 = (LTreeAddress) ((LTreeAddress.Builder) f2).l();
            if (a2 <= 1) {
                return xMSSNodeArr[0];
            }
            int i3 = 0;
            while (true) {
                d2 = a2 / 2;
                if (i3 >= ((int) Math.floor(d2))) {
                    break;
                }
                lTreeAddress2 = (LTreeAddress) ((LTreeAddress.Builder) ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(lTreeAddress2.a())).h(lTreeAddress2.b())).n(lTreeAddress2.d()).o(lTreeAddress2.e()).p(i3).f(lTreeAddress2.getKeyAndMask())).l();
                int i4 = i3 * 2;
                xMSSNodeArr[i3] = b(wOTSPlus, xMSSNodeArr[i4], xMSSNodeArr[i4 + 1], lTreeAddress2);
                i3++;
            }
            if (a2 % 2 == 1) {
                xMSSNodeArr[(int) Math.floor(d2)] = xMSSNodeArr[a2 - 1];
            }
            a2 = (int) Math.ceil(a2 / 2.0d);
            f2 = ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(lTreeAddress2.a())).h(lTreeAddress2.b())).n(lTreeAddress2.d()).o(lTreeAddress2.e() + 1).p(lTreeAddress2.f()).f(lTreeAddress2.getKeyAndMask());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static XMSSNode b(WOTSPlus wOTSPlus, XMSSNode xMSSNode, XMSSNode xMSSNode2, XMSSAddress xMSSAddress) {
        Objects.requireNonNull(xMSSNode, "left == null");
        Objects.requireNonNull(xMSSNode2, "right == null");
        if (xMSSNode.getHeight() == xMSSNode2.getHeight()) {
            Objects.requireNonNull(xMSSAddress, "address == null");
            byte[] e2 = wOTSPlus.e();
            if (xMSSAddress instanceof LTreeAddress) {
                LTreeAddress lTreeAddress = (LTreeAddress) xMSSAddress;
                xMSSAddress = (LTreeAddress) ((LTreeAddress.Builder) ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(lTreeAddress.a())).h(lTreeAddress.b())).n(lTreeAddress.d()).o(lTreeAddress.e()).p(lTreeAddress.f()).f(0)).l();
            } else if (xMSSAddress instanceof HashTreeAddress) {
                HashTreeAddress hashTreeAddress = (HashTreeAddress) xMSSAddress;
                xMSSAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress.a())).h(hashTreeAddress.b())).m(hashTreeAddress.d()).n(hashTreeAddress.e()).f(0)).k();
            }
            byte[] d2 = wOTSPlus.a().d(e2, xMSSAddress.c());
            if (xMSSAddress instanceof LTreeAddress) {
                LTreeAddress lTreeAddress2 = (LTreeAddress) xMSSAddress;
                xMSSAddress = (LTreeAddress) ((LTreeAddress.Builder) ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(lTreeAddress2.a())).h(lTreeAddress2.b())).n(lTreeAddress2.d()).o(lTreeAddress2.e()).p(lTreeAddress2.f()).f(1)).l();
            } else if (xMSSAddress instanceof HashTreeAddress) {
                HashTreeAddress hashTreeAddress2 = (HashTreeAddress) xMSSAddress;
                xMSSAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress2.a())).h(hashTreeAddress2.b())).m(hashTreeAddress2.d()).n(hashTreeAddress2.e()).f(1)).k();
            }
            byte[] d3 = wOTSPlus.a().d(e2, xMSSAddress.c());
            if (xMSSAddress instanceof LTreeAddress) {
                LTreeAddress lTreeAddress3 = (LTreeAddress) xMSSAddress;
                xMSSAddress = (LTreeAddress) ((LTreeAddress.Builder) ((LTreeAddress.Builder) ((LTreeAddress.Builder) new LTreeAddress.Builder().g(lTreeAddress3.a())).h(lTreeAddress3.b())).n(lTreeAddress3.d()).o(lTreeAddress3.e()).p(lTreeAddress3.f()).f(2)).l();
            } else if (xMSSAddress instanceof HashTreeAddress) {
                HashTreeAddress hashTreeAddress3 = (HashTreeAddress) xMSSAddress;
                xMSSAddress = (HashTreeAddress) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) ((HashTreeAddress.Builder) new HashTreeAddress.Builder().g(hashTreeAddress3.a())).h(hashTreeAddress3.b())).m(hashTreeAddress3.d()).n(hashTreeAddress3.e()).f(2)).k();
            }
            byte[] d4 = wOTSPlus.a().d(e2, xMSSAddress.c());
            int d5 = wOTSPlus.b().d();
            byte[] bArr = new byte[d5 * 2];
            for (int i2 = 0; i2 < d5; i2++) {
                bArr[i2] = (byte) (xMSSNode.getValue()[i2] ^ d3[i2]);
            }
            for (int i3 = 0; i3 < d5; i3++) {
                bArr[i3 + d5] = (byte) (xMSSNode2.getValue()[i3] ^ d4[i3]);
            }
            return new XMSSNode(xMSSNode.getHeight(), wOTSPlus.a().b(d2, bArr));
        }
        throw new IllegalStateException("height of both nodes must be equal");
    }
}
