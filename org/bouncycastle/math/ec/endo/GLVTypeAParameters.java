package org.bouncycastle.math.ec.endo;

import java.math.BigInteger;
/* loaded from: classes4.dex */
public class GLVTypeAParameters {

    /* renamed from: a  reason: collision with root package name */
    protected final BigInteger f14317a;

    /* renamed from: b  reason: collision with root package name */
    protected final BigInteger f14318b;

    /* renamed from: c  reason: collision with root package name */
    protected final ScalarSplitParameters f14319c;

    public GLVTypeAParameters(BigInteger bigInteger, BigInteger bigInteger2, ScalarSplitParameters scalarSplitParameters) {
        this.f14317a = bigInteger;
        this.f14318b = bigInteger2;
        this.f14319c = scalarSplitParameters;
    }

    public BigInteger getI() {
        return this.f14317a;
    }

    public BigInteger getLambda() {
        return this.f14318b;
    }

    public ScalarSplitParameters getSplitParams() {
        return this.f14319c;
    }
}
