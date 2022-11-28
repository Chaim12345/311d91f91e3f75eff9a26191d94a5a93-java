package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;
/* loaded from: classes4.dex */
public class McElieceKeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    private int fieldPoly;

    /* renamed from: m  reason: collision with root package name */
    private int f14641m;

    /* renamed from: n  reason: collision with root package name */
    private int f14642n;

    /* renamed from: t  reason: collision with root package name */
    private int f14643t;

    public McElieceKeyGenParameterSpec() {
        this(11, 50);
    }

    public McElieceKeyGenParameterSpec(int i2) {
        if (i2 < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        this.f14641m = 0;
        this.f14642n = 1;
        while (true) {
            int i3 = this.f14642n;
            if (i3 >= i2) {
                int i4 = i3 >>> 1;
                this.f14643t = i4;
                int i5 = this.f14641m;
                this.f14643t = i4 / i5;
                this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i5);
                return;
            }
            this.f14642n = i3 << 1;
            this.f14641m++;
        }
    }

    public McElieceKeyGenParameterSpec(int i2, int i3) {
        if (i2 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i2 > 32) {
            throw new IllegalArgumentException("m is too large");
        }
        this.f14641m = i2;
        int i4 = 1 << i2;
        this.f14642n = i4;
        if (i3 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i3 > i4) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        this.f14643t = i3;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i2);
    }

    public McElieceKeyGenParameterSpec(int i2, int i3, int i4) {
        this.f14641m = i2;
        if (i2 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i2 > 32) {
            throw new IllegalArgumentException(" m is too large");
        }
        int i5 = 1 << i2;
        this.f14642n = i5;
        this.f14643t = i3;
        if (i3 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i3 > i5) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        if (PolynomialRingGF2.degree(i4) != i2 || !PolynomialRingGF2.isIrreducible(i4)) {
            throw new IllegalArgumentException("polynomial is not a field polynomial for GF(2^m)");
        }
        this.fieldPoly = i4;
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }

    public int getM() {
        return this.f14641m;
    }

    public int getN() {
        return this.f14642n;
    }

    public int getT() {
        return this.f14643t;
    }
}
