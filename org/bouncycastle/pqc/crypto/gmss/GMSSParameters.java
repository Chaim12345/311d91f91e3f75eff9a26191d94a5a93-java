package org.bouncycastle.pqc.crypto.gmss;

import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class GMSSParameters {
    private int[] K;
    private int[] heightOfTrees;
    private int numOfLayers;
    private int[] winternitzParameter;

    public GMSSParameters(int i2) {
        if (i2 <= 10) {
            init(1, new int[]{10}, new int[]{3}, new int[]{2});
        } else if (i2 <= 20) {
            init(2, new int[]{10, 10}, new int[]{5, 4}, new int[]{2, 2});
        } else {
            init(4, new int[]{10, 10, 10, 10}, new int[]{9, 9, 9, 3}, new int[]{2, 2, 2, 2});
        }
    }

    public GMSSParameters(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        init(i2, iArr, iArr2, iArr3);
    }

    private void init(int i2, int[] iArr, int[] iArr2, int[] iArr3) {
        String str;
        boolean z;
        this.numOfLayers = i2;
        if (i2 == iArr2.length && i2 == iArr.length && i2 == iArr3.length) {
            z = true;
            str = "";
        } else {
            str = "Unexpected parameterset format";
            z = false;
        }
        for (int i3 = 0; i3 < this.numOfLayers; i3++) {
            if (iArr3[i3] < 2 || (iArr[i3] - iArr3[i3]) % 2 != 0) {
                str = "Wrong parameter K (K >= 2 and H-K even required)!";
                z = false;
            }
            if (iArr[i3] < 4 || iArr2[i3] < 2) {
                str = "Wrong parameter H or w (H > 3 and w > 1 required)!";
                z = false;
            }
        }
        if (!z) {
            throw new IllegalArgumentException(str);
        }
        this.heightOfTrees = Arrays.clone(iArr);
        this.winternitzParameter = Arrays.clone(iArr2);
        this.K = Arrays.clone(iArr3);
    }

    public int[] getHeightOfTrees() {
        return Arrays.clone(this.heightOfTrees);
    }

    public int[] getK() {
        return Arrays.clone(this.K);
    }

    public int getNumOfLayers() {
        return this.numOfLayers;
    }

    public int[] getWinternitzParameter() {
        return Arrays.clone(this.winternitzParameter);
    }
}
