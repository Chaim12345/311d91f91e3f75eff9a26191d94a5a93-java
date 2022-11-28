package org.bouncycastle.eac.operator.jcajce;

import java.security.Provider;
import java.security.Signature;
/* loaded from: classes3.dex */
class ProviderEACHelper extends EACHelper {
    private final Provider provider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProviderEACHelper(Provider provider) {
        this.provider = provider;
    }

    @Override // org.bouncycastle.eac.operator.jcajce.EACHelper
    protected Signature a(String str) {
        return Signature.getInstance(str, this.provider);
    }
}