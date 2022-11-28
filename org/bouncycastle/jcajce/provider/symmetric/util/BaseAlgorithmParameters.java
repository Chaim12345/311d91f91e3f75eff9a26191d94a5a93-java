package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParametersSpi;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;
/* loaded from: classes3.dex */
public abstract class BaseAlgorithmParameters extends AlgorithmParametersSpi {
    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(String str) {
        return str == null || str.equals("ASN.1");
    }

    protected abstract AlgorithmParameterSpec b(Class cls);

    @Override // java.security.AlgorithmParametersSpi
    protected AlgorithmParameterSpec engineGetParameterSpec(Class cls) {
        Objects.requireNonNull(cls, "argument to getParameterSpec must not be null");
        return b(cls);
    }
}
