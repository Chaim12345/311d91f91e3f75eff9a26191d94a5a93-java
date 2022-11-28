package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.ietf.jgss.Oid;
/* loaded from: classes3.dex */
public class SPNegoScheme extends GGSSchemeBase {
    private static final String SPNEGO_OID = "1.3.6.1.5.5.2";

    public SPNegoScheme() {
    }

    public SPNegoScheme(boolean z) {
        super(z);
    }

    public SPNegoScheme(boolean z, boolean z2) {
        super(z, z2);
    }

    @Override // org.apache.http.impl.auth.GGSSchemeBase, org.apache.http.impl.auth.AuthSchemeBase, org.apache.http.auth.ContextAwareAuthScheme
    public Header authenticate(Credentials credentials, HttpRequest httpRequest, HttpContext httpContext) {
        return super.authenticate(credentials, httpRequest, httpContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.http.impl.auth.GGSSchemeBase
    public byte[] generateToken(byte[] bArr, String str) {
        return super.generateToken(bArr, str);
    }

    @Override // org.apache.http.impl.auth.GGSSchemeBase
    protected byte[] generateToken(byte[] bArr, String str, Credentials credentials) {
        return generateGSSToken(bArr, new Oid(SPNEGO_OID), str, credentials);
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getParameter(String str) {
        Args.notNull(str, "Parameter name");
        return null;
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getRealm() {
        return null;
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getSchemeName() {
        return "Negotiate";
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isConnectionBased() {
        return true;
    }
}
