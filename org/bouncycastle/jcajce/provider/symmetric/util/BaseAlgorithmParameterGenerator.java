package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameterGeneratorSpi;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import org.bouncycastle.jcajce.util.BCJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
/* loaded from: classes3.dex */
public abstract class BaseAlgorithmParameterGenerator extends AlgorithmParameterGeneratorSpi {

    /* renamed from: a  reason: collision with root package name */
    protected SecureRandom f13780a;
    private final JcaJceHelper helper = new BCJcaJceHelper();

    /* JADX INFO: Access modifiers changed from: protected */
    public final AlgorithmParameters a(String str) {
        return this.helper.createAlgorithmParameters(str);
    }

    @Override // java.security.AlgorithmParameterGeneratorSpi
    protected void engineInit(int i2, SecureRandom secureRandom) {
        this.f13780a = secureRandom;
    }
}
