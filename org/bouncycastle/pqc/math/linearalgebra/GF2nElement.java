package org.bouncycastle.pqc.math.linearalgebra;
/* loaded from: classes4.dex */
public abstract class GF2nElement implements GFElement {

    /* renamed from: a  reason: collision with root package name */
    protected GF2nField f14646a;

    /* renamed from: b  reason: collision with root package name */
    protected int f14647b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean b(int i2);

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public abstract Object clone();

    public final GF2nElement convert(GF2nField gF2nField) {
        return this.f14646a.convert(this, gF2nField);
    }

    public final GF2nField getField() {
        return this.f14646a;
    }

    public abstract GF2nElement increase();

    public abstract void increaseThis();

    public abstract GF2nElement solveQuadraticEquation();

    public abstract GF2nElement square();

    public abstract GF2nElement squareRoot();

    public abstract void squareRootThis();

    public abstract void squareThis();

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public final GFElement subtract(GFElement gFElement) {
        return add(gFElement);
    }

    @Override // org.bouncycastle.pqc.math.linearalgebra.GFElement
    public final void subtractFromThis(GFElement gFElement) {
        addToThis(gFElement);
    }

    public abstract boolean testRightmostBit();

    public abstract int trace();
}
