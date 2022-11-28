package org.bouncycastle.pqc.math.linearalgebra;
/* loaded from: classes4.dex */
public abstract class Matrix {
    public static final char MATRIX_TYPE_RANDOM_LT = 'L';
    public static final char MATRIX_TYPE_RANDOM_REGULAR = 'R';
    public static final char MATRIX_TYPE_RANDOM_UT = 'U';
    public static final char MATRIX_TYPE_UNIT = 'I';
    public static final char MATRIX_TYPE_ZERO = 'Z';

    /* renamed from: a  reason: collision with root package name */
    protected int f14659a;

    /* renamed from: b  reason: collision with root package name */
    protected int f14660b;

    public abstract Matrix computeInverse();

    public abstract byte[] getEncoded();

    public int getNumColumns() {
        return this.f14660b;
    }

    public int getNumRows() {
        return this.f14659a;
    }

    public abstract boolean isZero();

    public abstract Vector leftMultiply(Vector vector);

    public abstract Matrix rightMultiply(Matrix matrix);

    public abstract Matrix rightMultiply(Permutation permutation);

    public abstract Vector rightMultiply(Vector vector);

    public abstract String toString();
}
