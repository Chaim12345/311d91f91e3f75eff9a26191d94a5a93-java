package org.bouncycastle.jsse.provider;

import java.util.List;
import org.bouncycastle.jsse.BCSNIServerName;
/* loaded from: classes3.dex */
class ImportSSLSession_8 extends ImportSSLSession_7 {
    @Override // org.bouncycastle.jsse.provider.ImportSSLSession_7, org.bouncycastle.jsse.BCExtendedSSLSession
    public List<BCSNIServerName> getRequestedServerNames() {
        return JsseUtils_8.x0(this.f13881a.getRequestedServerNames());
    }
}
