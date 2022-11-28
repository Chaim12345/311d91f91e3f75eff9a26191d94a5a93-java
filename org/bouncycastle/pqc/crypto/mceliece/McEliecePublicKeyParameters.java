package org.bouncycastle.pqc.crypto.mceliece;

import org.bouncycastle.pqc.math.linearalgebra.GF2Matrix;
/* loaded from: classes4.dex */
public class McEliecePublicKeyParameters extends McElieceKeyParameters {

    /* renamed from: g  reason: collision with root package name */
    private GF2Matrix f14550g;

    /* renamed from: n  reason: collision with root package name */
    private int f14551n;

    /* renamed from: t  reason: collision with root package name */
    private int f14552t;

    public McEliecePublicKeyParameters(int i2, int i3, GF2Matrix gF2Matrix) {
        super(false, null);
        this.f14551n = i2;
        this.f14552t = i3;
        this.f14550g = new GF2Matrix(gF2Matrix);
    }

    public GF2Matrix getG() {
        return this.f14550g;
    }

    public int getK() {
        return this.f14550g.getNumRows();
    }

    public int getN() {
        return this.f14551n;
    }

    public int getT() {
        return this.f14552t;
    }
}
