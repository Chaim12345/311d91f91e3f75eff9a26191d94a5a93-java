package org.bouncycastle.pqc.math.linearalgebra;
/* loaded from: classes4.dex */
public abstract class Vector {

    /* renamed from: a  reason: collision with root package name */
    protected int f14664a;

    public abstract Vector add(Vector vector);

    public abstract boolean equals(Object obj);

    public abstract byte[] getEncoded();

    public final int getLength() {
        return this.f14664a;
    }

    public abstract int hashCode();

    public abstract boolean isZero();

    public abstract Vector multiply(Permutation permutation);

    public abstract String toString();
}
