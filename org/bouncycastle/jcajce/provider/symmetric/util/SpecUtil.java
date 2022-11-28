package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.AlgorithmParameters;
import java.security.spec.AlgorithmParameterSpec;
/* loaded from: classes3.dex */
class SpecUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmParameterSpec a(AlgorithmParameters algorithmParameters, Class[] clsArr) {
        try {
            return algorithmParameters.getParameterSpec(AlgorithmParameterSpec.class);
        } catch (Exception unused) {
            for (int i2 = 0; i2 != clsArr.length; i2++) {
                if (clsArr[i2] != null) {
                    try {
                        return algorithmParameters.getParameterSpec(clsArr[i2]);
                    } catch (Exception unused2) {
                    }
                }
            }
            return null;
        }
    }
}
