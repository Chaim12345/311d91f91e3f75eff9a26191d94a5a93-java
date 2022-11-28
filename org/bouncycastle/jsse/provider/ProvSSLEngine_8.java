package org.bouncycastle.jsse.provider;

import java.util.List;
import java.util.function.BiFunction;
import javax.net.ssl.SSLEngine;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvSSLEngine_8 extends ProvSSLEngine {
    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLEngine_8(ContextData contextData) {
        super(contextData);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ProvSSLEngine_8(ContextData contextData, String str, int i2) {
        super(contextData, str, i2);
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized BiFunction<SSLEngine, List<String>, String> getHandshakeApplicationProtocolSelector() {
        return JsseUtils_8.k0(this.f13929b.getEngineAPSelector());
    }

    @Override // javax.net.ssl.SSLEngine
    public synchronized void setHandshakeApplicationProtocolSelector(BiFunction<SSLEngine, List<String>, String> biFunction) {
        this.f13929b.setEngineAPSelector(JsseUtils_8.s0(biFunction));
    }
}
