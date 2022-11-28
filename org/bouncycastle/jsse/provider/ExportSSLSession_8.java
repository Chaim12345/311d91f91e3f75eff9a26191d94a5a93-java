package org.bouncycastle.jsse.provider;

import java.util.List;
import javax.net.ssl.SNIServerName;
/* loaded from: classes3.dex */
class ExportSSLSession_8 extends ExportSSLSession_7 {
    @Override // javax.net.ssl.ExtendedSSLSession
    public List<SNIServerName> getRequestedServerNames() {
        return JsseUtils_8.p0(this.f13877a.getRequestedServerNames());
    }
}
