package org.apache.http.impl.client;

import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;
@Contract(threading = ThreadingBehavior.IMMUTABLE)
@Deprecated
/* loaded from: classes3.dex */
public class DefaultTargetAuthenticationHandler extends AbstractAuthenticationHandler {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.http.impl.client.AbstractAuthenticationHandler
    public List<String> getAuthPreferences(HttpResponse httpResponse, HttpContext httpContext) {
        List<String> list = (List) httpResponse.getParams().getParameter(AuthPNames.TARGET_AUTH_PREF);
        return list != null ? list : super.getAuthPreferences(httpResponse, httpContext);
    }

    @Override // org.apache.http.client.AuthenticationHandler
    public Map<String, Header> getChallenges(HttpResponse httpResponse, HttpContext httpContext) {
        Args.notNull(httpResponse, "HTTP response");
        return parseChallenges(httpResponse.getHeaders("WWW-Authenticate"));
    }

    @Override // org.apache.http.client.AuthenticationHandler
    public boolean isAuthenticationRequested(HttpResponse httpResponse, HttpContext httpContext) {
        Args.notNull(httpResponse, "HTTP response");
        return httpResponse.getStatusLine().getStatusCode() == 401;
    }
}
