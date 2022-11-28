package org.apache.http.impl.auth;

import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.Credentials;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;
@Deprecated
/* loaded from: classes3.dex */
public class NegotiateScheme extends GGSSchemeBase {
    private static final String KERBEROS_OID = "1.2.840.113554.1.2.2";
    private static final String SPNEGO_OID = "1.3.6.1.5.5.2";
    private final Log log;
    private final SpnegoTokenGenerator spengoGenerator;

    public NegotiateScheme() {
        this(null, false);
    }

    public NegotiateScheme(SpnegoTokenGenerator spnegoTokenGenerator) {
        this(spnegoTokenGenerator, false);
    }

    public NegotiateScheme(SpnegoTokenGenerator spnegoTokenGenerator, boolean z) {
        super(z);
        this.log = LogFactory.getLog(getClass());
        this.spengoGenerator = spnegoTokenGenerator;
    }

    @Override // org.apache.http.impl.auth.GGSSchemeBase, org.apache.http.auth.AuthScheme
    public Header authenticate(Credentials credentials, HttpRequest httpRequest) {
        return authenticate(credentials, httpRequest, null);
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
        boolean z;
        SpnegoTokenGenerator spnegoTokenGenerator;
        try {
            bArr = generateGSSToken(bArr, new Oid(SPNEGO_OID), str, credentials);
            z = false;
        } catch (GSSException e2) {
            if (e2.getMajor() != 2) {
                throw e2;
            }
            this.log.debug("GSSException BAD_MECH, retry with Kerberos MECH");
            z = true;
        }
        if (z) {
            this.log.debug("Using Kerberos MECH 1.2.840.113554.1.2.2");
            byte[] generateGSSToken = generateGSSToken(bArr, new Oid(KERBEROS_OID), str, credentials);
            if (generateGSSToken == null || (spnegoTokenGenerator = this.spengoGenerator) == null) {
                return generateGSSToken;
            }
            try {
                return spnegoTokenGenerator.generateSpnegoDERObject(generateGSSToken);
            } catch (IOException e3) {
                this.log.error(e3.getMessage(), e3);
                return generateGSSToken;
            }
        }
        return bArr;
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
