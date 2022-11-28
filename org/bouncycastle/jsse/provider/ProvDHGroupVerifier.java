package org.bouncycastle.jsse.provider;

import org.bouncycastle.tls.DefaultTlsDHGroupVerifier;
import org.bouncycastle.tls.crypto.DHGroup;
/* loaded from: classes3.dex */
class ProvDHGroupVerifier extends DefaultTlsDHGroupVerifier {
    private static final int provMinimumPrimeBits = PropertyUtils.c("org.bouncycastle.jsse.client.dh.minimumPrimeBits", 2048, 1024, 16384);
    private static final boolean provUnrestrictedGroups = PropertyUtils.b("org.bouncycastle.jsse.client.dh.unrestrictedGroups", false);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvDHGroupVerifier() {
        super(provMinimumPrimeBits);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.tls.DefaultTlsDHGroupVerifier
    public boolean c(DHGroup dHGroup) {
        return provUnrestrictedGroups || super.c(dHGroup);
    }
}
