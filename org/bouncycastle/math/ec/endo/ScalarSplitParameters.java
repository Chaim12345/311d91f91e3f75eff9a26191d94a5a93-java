package org.bouncycastle.math.ec.endo;

import java.math.BigInteger;
/* loaded from: classes4.dex */
public class ScalarSplitParameters {

    /* renamed from: a  reason: collision with root package name */
    protected final BigInteger f14325a;

    /* renamed from: b  reason: collision with root package name */
    protected final BigInteger f14326b;

    /* renamed from: c  reason: collision with root package name */
    protected final BigInteger f14327c;

    /* renamed from: d  reason: collision with root package name */
    protected final BigInteger f14328d;

    /* renamed from: e  reason: collision with root package name */
    protected final BigInteger f14329e;

    /* renamed from: f  reason: collision with root package name */
    protected final BigInteger f14330f;

    /* renamed from: g  reason: collision with root package name */
    protected final int f14331g;

    public ScalarSplitParameters(BigInteger[] bigIntegerArr, BigInteger[] bigIntegerArr2, BigInteger bigInteger, BigInteger bigInteger2, int i2) {
        checkVector(bigIntegerArr, "v1");
        checkVector(bigIntegerArr2, "v2");
        this.f14325a = bigIntegerArr[0];
        this.f14326b = bigIntegerArr[1];
        this.f14327c = bigIntegerArr2[0];
        this.f14328d = bigIntegerArr2[1];
        this.f14329e = bigInteger;
        this.f14330f = bigInteger2;
        this.f14331g = i2;
    }

    private static void checkVector(BigInteger[] bigIntegerArr, String str) {
        if (bigIntegerArr == null || bigIntegerArr.length != 2 || bigIntegerArr[0] == null || bigIntegerArr[1] == null) {
            throw new IllegalArgumentException("'" + str + "' must consist of exactly 2 (non-null) values");
        }
    }

    public int getBits() {
        return this.f14331g;
    }

    public BigInteger getG1() {
        return this.f14329e;
    }

    public BigInteger getG2() {
        return this.f14330f;
    }

    public BigInteger getV1A() {
        return this.f14325a;
    }

    public BigInteger getV1B() {
        return this.f14326b;
    }

    public BigInteger getV2A() {
        return this.f14327c;
    }

    public BigInteger getV2B() {
        return this.f14328d;
    }
}
