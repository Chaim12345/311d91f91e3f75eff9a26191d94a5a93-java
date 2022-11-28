package org.bouncycastle.eac.operator.jcajce;

import java.security.Signature;
/* loaded from: classes3.dex */
class NamedEACHelper extends EACHelper {
    private final String providerName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NamedEACHelper(String str) {
        this.providerName = str;
    }

    @Override // org.bouncycastle.eac.operator.jcajce.EACHelper
    protected Signature a(String str) {
        return Signature.getInstance(str, this.providerName);
    }
}
