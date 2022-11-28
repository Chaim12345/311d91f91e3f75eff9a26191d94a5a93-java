package org.bouncycastle.pqc.crypto.sphincsplus;

import java.util.LinkedList;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
class Fors {

    /* renamed from: a  reason: collision with root package name */
    SPHINCSPlusEngine f14572a;
    private final WotsPlus wots;

    public Fors(SPHINCSPlusEngine sPHINCSPlusEngine) {
        this.f14572a = sPHINCSPlusEngine;
        this.wots = new WotsPlus(sPHINCSPlusEngine);
    }

    static int[] a(byte[] bArr, int i2, int i3) {
        int[] iArr = new int[i2];
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            iArr[i5] = 0;
            for (int i6 = 0; i6 < i3; i6++) {
                iArr[i5] = iArr[i5] ^ (((bArr[i4 >> 3] >> (i4 & 7)) & 1) << i6);
                i4++;
            }
        }
        return iArr;
    }

    byte[] b(byte[] bArr, int i2, int i3, byte[] bArr2, ADRS adrs) {
        ADRS adrs2 = new ADRS(adrs);
        LinkedList linkedList = new LinkedList();
        int i4 = 1 << i3;
        if (i2 % i4 != 0) {
            return null;
        }
        for (int i5 = 0; i5 < i4; i5++) {
            adrs2.setTreeHeight(0);
            int i6 = i2 + i5;
            adrs2.setTreeIndex(i6);
            byte[] F = this.f14572a.F(bArr2, adrs2, this.f14572a.b(bArr, adrs2));
            adrs2.setTreeHeight(1);
            adrs2.setTreeIndex(i6);
            while (!linkedList.isEmpty() && ((NodeEntry) linkedList.get(0)).f14580b == adrs2.getTreeHeight()) {
                adrs2.setTreeIndex((adrs2.getTreeIndex() - 1) / 2);
                F = this.f14572a.H(bArr2, adrs2, ((NodeEntry) linkedList.remove(0)).f14579a, F);
                adrs2.setTreeHeight(adrs2.getTreeHeight() + 1);
            }
            linkedList.add(0, new NodeEntry(F, adrs2.getTreeHeight()));
        }
        return ((NodeEntry) linkedList.get(0)).f14579a;
    }

    public byte[] pkFromSig(SIG_FORS[] sig_forsArr, byte[] bArr, byte[] bArr2, ADRS adrs) {
        int i2 = 2;
        byte[][] bArr3 = new byte[2];
        SPHINCSPlusEngine sPHINCSPlusEngine = this.f14572a;
        int i3 = sPHINCSPlusEngine.f14599j;
        byte[][] bArr4 = new byte[i3];
        int i4 = sPHINCSPlusEngine.f14602m;
        int[] a2 = a(bArr, i3, sPHINCSPlusEngine.f14598i);
        int i5 = 0;
        while (i5 < this.f14572a.f14599j) {
            int i6 = a2[i5];
            byte[] a3 = sig_forsArr[i5].a();
            adrs.setTreeHeight(0);
            int i7 = (i5 * i4) + i6;
            adrs.setTreeIndex(i7);
            bArr3[0] = this.f14572a.F(bArr2, adrs, a3);
            byte[][] authPath = sig_forsArr[i5].getAuthPath();
            adrs.setTreeIndex(i7);
            int i8 = 0;
            while (i8 < this.f14572a.f14598i) {
                int i9 = i8 + 1;
                adrs.setTreeHeight(i9);
                if ((i6 / (1 << i8)) % i2 == 0) {
                    adrs.setTreeIndex(adrs.getTreeIndex() / i2);
                    bArr3[1] = this.f14572a.H(bArr2, adrs, bArr3[0], authPath[i8]);
                } else {
                    adrs.setTreeIndex((adrs.getTreeIndex() - 1) / 2);
                    bArr3[1] = this.f14572a.H(bArr2, adrs, authPath[i8], bArr3[0]);
                }
                bArr3[0] = bArr3[1];
                i8 = i9;
                i2 = 2;
            }
            bArr4[i5] = bArr3[0];
            i5++;
            i2 = 2;
        }
        ADRS adrs2 = new ADRS(adrs);
        adrs2.setType(4);
        adrs2.setKeyPairAddress(adrs.getKeyPairAddress());
        return this.f14572a.T_l(bArr2, adrs2, Arrays.concatenate(bArr4));
    }

    public SIG_FORS[] sign(byte[] bArr, byte[] bArr2, byte[] bArr3, ADRS adrs) {
        Fors fors = this;
        SPHINCSPlusEngine sPHINCSPlusEngine = fors.f14572a;
        int[] a2 = a(bArr, sPHINCSPlusEngine.f14599j, sPHINCSPlusEngine.f14598i);
        SPHINCSPlusEngine sPHINCSPlusEngine2 = fors.f14572a;
        SIG_FORS[] sig_forsArr = new SIG_FORS[sPHINCSPlusEngine2.f14599j];
        int i2 = sPHINCSPlusEngine2.f14602m;
        int i3 = 0;
        int i4 = 0;
        while (i4 < fors.f14572a.f14599j) {
            int i5 = a2[i4];
            adrs.setTreeHeight(i3);
            int i6 = i4 * i2;
            adrs.setTreeIndex(i6 + i5);
            byte[] b2 = fors.f14572a.b(bArr2, adrs);
            byte[][] bArr4 = new byte[fors.f14572a.f14598i];
            int i7 = i3;
            while (i7 < fors.f14572a.f14598i) {
                int i8 = 1 << i7;
                int i9 = i7;
                byte[][] bArr5 = bArr4;
                bArr5[i9] = b(bArr2, i6 + ((1 ^ (i5 / i8)) * i8), i7, bArr3, adrs);
                i7 = i9 + 1;
                b2 = b2;
                bArr4 = bArr5;
                fors = this;
            }
            sig_forsArr[i4] = new SIG_FORS(b2, bArr4);
            i4++;
            i3 = 0;
            fors = this;
        }
        return sig_forsArr;
    }
}
