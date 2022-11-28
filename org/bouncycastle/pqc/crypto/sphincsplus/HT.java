package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.LinkedList;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
class HT {

    /* renamed from: a  reason: collision with root package name */
    SPHINCSPlusEngine f14573a;

    /* renamed from: b  reason: collision with root package name */
    WotsPlus f14574b;

    /* renamed from: c  reason: collision with root package name */
    final byte[] f14575c;
    private final byte[] pkSeed;
    private final byte[] skSeed;

    public HT(SPHINCSPlusEngine sPHINCSPlusEngine, byte[] bArr, byte[] bArr2) {
        this.skSeed = bArr;
        this.pkSeed = bArr2;
        this.f14573a = sPHINCSPlusEngine;
        this.f14574b = new WotsPlus(sPHINCSPlusEngine);
        ADRS adrs = new ADRS();
        adrs.setLayerAddress(sPHINCSPlusEngine.f14597h - 1);
        adrs.setTreeAddress(0L);
        if (bArr != null) {
            this.f14575c = c(bArr, bArr2, adrs);
        } else {
            this.f14575c = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] a(byte[] bArr, long j2, int i2) {
        int i3;
        long j3 = j2;
        ADRS adrs = new ADRS();
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j3);
        SIG_XMSS e2 = e(bArr, this.skSeed, i2, this.pkSeed, adrs);
        int i4 = this.f14573a.f14597h;
        SIG_XMSS[] sig_xmssArr = new SIG_XMSS[i4];
        sig_xmssArr[0] = e2;
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j3);
        byte[] d2 = d(i2, e2, bArr, this.pkSeed, adrs);
        int i5 = 1;
        while (true) {
            SPHINCSPlusEngine sPHINCSPlusEngine = this.f14573a;
            if (i5 >= sPHINCSPlusEngine.f14597h) {
                break;
            }
            int i6 = (int) (((1 << i3) - 1) & j3);
            j3 >>>= sPHINCSPlusEngine.f14601l;
            adrs.setLayerAddress(i5);
            adrs.setTreeAddress(j3);
            SIG_XMSS e3 = e(d2, this.skSeed, i6, this.pkSeed, adrs);
            sig_xmssArr[i5] = e3;
            if (i5 < this.f14573a.f14597h - 1) {
                d2 = d(i6, e3, d2, this.pkSeed, adrs);
            }
            i5++;
        }
        byte[][] bArr2 = new byte[i4];
        for (int i7 = 0; i7 != i4; i7++) {
            bArr2[i7] = Arrays.concatenate(sig_xmssArr[i7].f14586a, Arrays.concatenate(sig_xmssArr[i7].f14587b));
        }
        return Arrays.concatenate(bArr2);
    }

    byte[] b(byte[] bArr, int i2, int i3, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        LinkedList linkedList = new LinkedList();
        int i4 = 1 << i3;
        if (i2 % i4 != 0) {
            return null;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            adrs2.setType(0);
            int i6 = i2 + i5;
            adrs2.setKeyPairAddress(i6);
            byte[] c2 = this.f14574b.c(bArr, bArr2, adrs2);
            adrs2.setType(2);
            adrs2.setTreeHeight(1);
            adrs2.setTreeIndex(i6);
            while (!linkedList.isEmpty() && ((NodeEntry) linkedList.get(0)).f14580b == adrs2.getTreeHeight()) {
                adrs2.setTreeIndex((adrs2.getTreeIndex() - 1) / 2);
                c2 = this.f14573a.H(bArr2, adrs2, ((NodeEntry) linkedList.remove(0)).f14579a, c2);
                adrs2.setTreeHeight(adrs2.getTreeHeight() + 1);
            }
            linkedList.add(0, new NodeEntry(c2, adrs2.getTreeHeight()));
        }
        return ((NodeEntry) linkedList.get(0)).f14579a;
    }

    byte[] c(byte[] bArr, byte[] bArr2, ADRS adrs) {
        return b(bArr, 0, this.f14573a.f14601l, bArr2, adrs);
    }

    byte[] d(int i2, SIG_XMSS sig_xmss, byte[] bArr, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        int i3 = 0;
        adrs2.setType(0);
        adrs2.setKeyPairAddress(i2);
        byte[] wOTSSig = sig_xmss.getWOTSSig();
        byte[][] xmssauth = sig_xmss.getXMSSAUTH();
        byte[] pkFromSig = this.f14574b.pkFromSig(wOTSSig, bArr, bArr2, adrs2);
        adrs2.setType(2);
        adrs2.setTreeIndex(i2);
        while (i3 < this.f14573a.f14601l) {
            int i4 = i3 + 1;
            adrs2.setTreeHeight(i4);
            if ((i2 / (1 << i3)) % 2 == 0) {
                adrs2.setTreeIndex(adrs2.getTreeIndex() / 2);
                pkFromSig = this.f14573a.H(bArr2, adrs2, pkFromSig, xmssauth[i3]);
            } else {
                adrs2.setTreeIndex((adrs2.getTreeIndex() - 1) / 2);
                pkFromSig = this.f14573a.H(bArr2, adrs2, xmssauth[i3], pkFromSig);
            }
            i3 = i4;
        }
        return pkFromSig;
    }

    SIG_XMSS e(byte[] bArr, byte[] bArr2, int i2, byte[] bArr3, ADRS adrs) {
        byte[][] bArr4 = new byte[this.f14573a.f14601l];
        for (int i3 = 0; i3 < this.f14573a.f14601l; i3++) {
            int i4 = 1 << i3;
            bArr4[i3] = b(bArr2, (1 ^ (i2 / i4)) * i4, i3, bArr3, adrs);
        }
        ADRS adrs2 = new ADRS(adrs);
        adrs2.setType(0);
        adrs2.setKeyPairAddress(i2);
        return new SIG_XMSS(this.f14574b.sign(bArr, bArr2, bArr3, adrs2), bArr4);
    }

    public boolean verify(byte[] bArr, SIG_XMSS[] sig_xmssArr, byte[] bArr2, long j2, int i2, byte[] bArr3) {
        int i3;
        ADRS adrs = new ADRS();
        SIG_XMSS sig_xmss = sig_xmssArr[0];
        adrs.setLayerAddress(0);
        adrs.setTreeAddress(j2);
        byte[] d2 = d(i2, sig_xmss, bArr, bArr2, adrs);
        int i4 = 1;
        while (true) {
            SPHINCSPlusEngine sPHINCSPlusEngine = this.f14573a;
            if (i4 >= sPHINCSPlusEngine.f14597h) {
                return Arrays.areEqual(bArr3, d2);
            }
            j2 >>>= sPHINCSPlusEngine.f14601l;
            SIG_XMSS sig_xmss2 = sig_xmssArr[i4];
            adrs.setLayerAddress(i4);
            adrs.setTreeAddress(j2);
            d2 = d((int) (((1 << i3) - 1) & j2), sig_xmss2, d2, bArr2, adrs);
            i4++;
        }
    }
}
