package org.bouncycastle.eac.operator.jcajce;

import java.security.Signature;
/* loaded from: classes3.dex */
class DefaultEACHelper extends EACHelper {
    @Override // org.bouncycastle.eac.operator.jcajce.EACHelper
    protected Signature a(String str) {
        return Signature.getInstance(str);
    }
}
