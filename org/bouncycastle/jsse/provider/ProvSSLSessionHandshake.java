package org.bouncycastle.jsse.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.jsse.BCSNIServerName;
import org.bouncycastle.tls.Certificate;
import org.bouncycastle.tls.ProtocolVersion;
import org.bouncycastle.tls.SecurityParameters;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class ProvSSLSessionHandshake extends ProvSSLSessionBase {

    /* renamed from: j  reason: collision with root package name */
    protected final SecurityParameters f13967j;

    /* renamed from: k  reason: collision with root package name */
    protected final JsseSecurityParameters f13968k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProvSSLSessionHandshake(ProvSSLSessionContext provSSLSessionContext, String str, int i2, SecurityParameters securityParameters, JsseSecurityParameters jsseSecurityParameters) {
        super(provSSLSessionContext, str, i2);
        this.f13967j = securityParameters;
        this.f13968k = jsseSecurityParameters;
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected int b() {
        return this.f13967j.getCipherSuite();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected byte[] d() {
        return this.f13967j.getSessionID();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected Certificate e() {
        return this.f13967j.getLocalCertificate();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected Certificate f() {
        return this.f13967j.getPeerCertificate();
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected ProtocolVersion g() {
        return this.f13967j.getNegotiatedVersion();
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public String[] getLocalSupportedSignatureAlgorithms() {
        return SignatureSchemeInfo.e(this.f13968k.f13891c);
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public String[] getLocalSupportedSignatureAlgorithmsBC() {
        return SignatureSchemeInfo.f(this.f13968k.f13891c);
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public String[] getPeerSupportedSignatureAlgorithms() {
        return SignatureSchemeInfo.e(this.f13968k.f13893e);
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public String[] getPeerSupportedSignatureAlgorithmsBC() {
        return SignatureSchemeInfo.f(this.f13968k.f13893e);
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public List<BCSNIServerName> getRequestedServerNames() {
        return JsseUtils.h(this.f13967j.getClientServerNames());
    }

    @Override // org.bouncycastle.jsse.BCExtendedSSLSession
    public List<byte[]> getStatusResponses() {
        List<byte[]> list = this.f13968k.f13894f;
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (byte[] bArr : list) {
            arrayList.add(bArr.clone());
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // org.bouncycastle.jsse.provider.ProvSSLSessionBase
    protected void h() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String l() {
        return JsseUtils.p(this.f13967j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public JsseSecurityParameters m() {
        return this.f13968k;
    }
}
