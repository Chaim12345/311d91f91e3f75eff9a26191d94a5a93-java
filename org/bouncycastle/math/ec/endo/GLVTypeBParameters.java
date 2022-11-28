package org.bouncycastle.math.ec.endo;

import java.math.BigInteger;
/* loaded from: classes4.dex */
public class GLVTypeBParameters {

    /* renamed from: a  reason: collision with root package name */
    protected final BigInteger f14322a;

    /* renamed from: b  reason: collision with root package name */
    protected final BigInteger f14323b;

    /* renamed from: c  reason: collision with root package name */
    protected final ScalarSplitParameters f14324c;

    public GLVTypeBParameters(BigInteger bigInteger, BigInteger bigInteger2, ScalarSplitParameters scalarSplitParameters) {
        this.f14322a = bigInteger;
        this.f14323b = bigInteger2;
        this.f14324c = scalarSplitParameters;
    }

    public BigInteger getBeta() {
        return this.f14322a;
    }

    public BigInteger getLambda() {
        return this.f14323b;
    }

    public ScalarSplitParameters getSplitParams() {
        return this.f14324c;
    }
}
