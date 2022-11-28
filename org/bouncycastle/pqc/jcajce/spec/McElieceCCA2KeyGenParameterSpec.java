package org.bouncycastle.pqc.jcajce.spec;

import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;
/* loaded from: classes4.dex */
public class McElieceCCA2KeyGenParameterSpec implements AlgorithmParameterSpec {
    public static final int DEFAULT_M = 11;
    public static final int DEFAULT_T = 50;
    public static final String SHA1 = "SHA-1";
    public static final String SHA224 = "SHA-224";
    public static final String SHA256 = "SHA-256";
    public static final String SHA384 = "SHA-384";
    public static final String SHA512 = "SHA-512";
    private final String digest;
    private int fieldPoly;

    /* renamed from: m  reason: collision with root package name */
    private final int f14638m;

    /* renamed from: n  reason: collision with root package name */
    private final int f14639n;

    /* renamed from: t  reason: collision with root package name */
    private final int f14640t;

    public McElieceCCA2KeyGenParameterSpec() {
        this(11, 50, "SHA-256");
    }

    public McElieceCCA2KeyGenParameterSpec(int i2) {
        this(i2, "SHA-256");
    }

    public McElieceCCA2KeyGenParameterSpec(int i2, int i3) {
        this(i2, i3, "SHA-256");
    }

    public McElieceCCA2KeyGenParameterSpec(int i2, int i3, int i4) {
        this(i2, i3, i4, "SHA-256");
    }

    public McElieceCCA2KeyGenParameterSpec(int i2, int i3, int i4, String str) {
        this.f14638m = i2;
        if (i2 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i2 > 32) {
            throw new IllegalArgumentException(" m is too large");
        }
        int i5 = 1 << i2;
        this.f14639n = i5;
        this.f14640t = i3;
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
        this.digest = str;
    }

    public McElieceCCA2KeyGenParameterSpec(int i2, int i3, String str) {
        if (i2 < 1) {
            throw new IllegalArgumentException("m must be positive");
        }
        if (i2 > 32) {
            throw new IllegalArgumentException("m is too large");
        }
        this.f14638m = i2;
        int i4 = 1 << i2;
        this.f14639n = i4;
        if (i3 < 0) {
            throw new IllegalArgumentException("t must be positive");
        }
        if (i3 > i4) {
            throw new IllegalArgumentException("t must be less than n = 2^m");
        }
        this.f14640t = i3;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i2);
        this.digest = str;
    }

    public McElieceCCA2KeyGenParameterSpec(int i2, String str) {
        int i3 = 1;
        if (i2 < 1) {
            throw new IllegalArgumentException("key size must be positive");
        }
        int i4 = 0;
        while (i3 < i2) {
            i3 <<= 1;
            i4++;
        }
        this.f14640t = (i3 >>> 1) / i4;
        this.f14638m = i4;
        this.f14639n = i3;
        this.fieldPoly = PolynomialRingGF2.getIrreduciblePolynomial(i4);
        this.digest = str;
    }

    public String getDigest() {
        return this.digest;
    }

    public int getFieldPoly() {
        return this.fieldPoly;
    }

    public int getM() {
        return this.f14638m;
    }

    public int getN() {
        return this.f14639n;
    }

    public int getT() {
        return this.f14640t;
    }
}
