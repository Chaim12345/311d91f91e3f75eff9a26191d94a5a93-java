package org.apache.http.impl.client;

import java.security.Principal;
import javax.net.ssl.SSLSession;
import org.apache.http.HttpConnection;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.protocol.HttpContext;
@Contract(threading = ThreadingBehavior.IMMUTABLE)
/* loaded from: classes3.dex */
public class DefaultUserTokenHandler implements UserTokenHandler {
    public static final DefaultUserTokenHandler INSTANCE = new DefaultUserTokenHandler();

    private static Principal getAuthPrincipal(AuthState authState) {
        Credentials credentials;
        AuthScheme authScheme = authState.getAuthScheme();
        if (authScheme == null || !authScheme.isComplete() || !authScheme.isConnectionBased() || (credentials = authState.getCredentials()) == null) {
            return null;
        }
        return credentials.getUserPrincipal();
    }

    @Override // org.apache.http.client.UserTokenHandler
    public Object getUserToken(HttpContext httpContext) {
        Principal principal;
        SSLSession sSLSession;
        HttpClientContext adapt = HttpClientContext.adapt(httpContext);
        AuthState targetAuthState = adapt.getTargetAuthState();
        if (targetAuthState != null) {
            principal = getAuthPrincipal(targetAuthState);
            if (principal == null) {
                principal = getAuthPrincipal(adapt.getProxyAuthState());
            }
        } else {
            principal = null;
        }
        if (principal == null) {
            HttpConnection connection = adapt.getConnection();
            return (connection.isOpen() && (connection instanceof ManagedHttpClientConnection) && (sSLSession = ((ManagedHttpClientConnection) connection).getSSLSession()) != null) ? sSLSession.getLocalPrincipal() : principal;
        }
        return principal;
    }
}
