package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
/* loaded from: classes4.dex */
public class McElieceCCA2PublicKeyParameters extends McElieceCCA2KeyParameters {
    private GF2Matrix matrixG;

    /* renamed from: n  reason: collision with root package name */
    private int f14524n;

    /* renamed from: t  reason: collision with root package name */
    private int f14525t;

    public McElieceCCA2PublicKeyParameters(int i2, int i3, GF2Matrix gF2Matrix, String str) {
        super(false, str);
        this.f14524n = i2;
        this.f14525t = i3;
        this.matrixG = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.matrixG;
    }

    public int getK() {
        return this.matrixG.getNumRows();
    }

    public int getN() {
        return this.f14524n;
    }

    public int getT() {
        return this.f14525t;
    }
}
