package org.apache.http.auth;

import java.io.Serializable;
import java.security.Principal;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.ietf.jgss.GSSCredential;
@Contract(threading = ThreadingBehavior.IMMUTABLE)
/* loaded from: classes3.dex */
public class KerberosCredentials implements Credentials, Serializable {
    private static final long serialVersionUID = 487421613855550713L;
    private final GSSCredential gssCredential;

    public KerberosCredentials(GSSCredential gSSCredential) {
        this.gssCredential = gSSCredential;
    }

    public GSSCredential getGSSCredential() {
        return this.gssCredential;
    }

    @Override // org.apache.http.auth.Credentials
    public String getPassword() {
        return null;
    }

    @Override // org.apache.http.auth.Credentials
    public Principal getUserPrincipal() {
        return null;
    }
}
