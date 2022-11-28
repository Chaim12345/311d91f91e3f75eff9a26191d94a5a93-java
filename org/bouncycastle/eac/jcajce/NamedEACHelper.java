package org.bouncycastle.eac.jcajce;

import java.security.KeyFactory;
/* loaded from: classes3.dex */
class NamedEACHelper implements EACHelper {
    private final String providerName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NamedEACHelper(String str) {
        this.providerName = str;
    }

    @Override // org.bouncycastle.eac.jcajce.EACHelper
    public KeyFactory createKeyFactory(String str) {
        return KeyFactory.getInstance(str, this.providerName);
    }
}
