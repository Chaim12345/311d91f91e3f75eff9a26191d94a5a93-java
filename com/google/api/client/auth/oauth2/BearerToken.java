package com.google.api.client.auth.oauth2;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.util.Data;
import com.google.api.client.util.Preconditions;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
/* loaded from: classes2.dex */
public class BearerToken {

    /* renamed from: a  reason: collision with root package name */
    static final Pattern f7963a = Pattern.compile("\\s*error\\s*=\\s*\"?invalid_token\"?");

    /* loaded from: classes2.dex */
    static final class AuthorizationHeaderAccessMethod implements Credential.AccessMethod {
        AuthorizationHeaderAccessMethod() {
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public String getAccessTokenFromRequest(HttpRequest httpRequest) {
            List<String> authorizationAsList = httpRequest.getHeaders().getAuthorizationAsList();
            if (authorizationAsList != null) {
                for (String str : authorizationAsList) {
                    if (str.startsWith("Bearer ")) {
                        return str.substring(7);
                    }
                }
                return null;
            }
            return null;
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public void intercept(HttpRequest httpRequest, String str) {
            HttpHeaders headers = httpRequest.getHeaders();
            headers.setAuthorization("Bearer " + str);
        }
    }

    /* loaded from: classes2.dex */
    static final class FormEncodedBodyAccessMethod implements Credential.AccessMethod {
        FormEncodedBodyAccessMethod() {
        }

        private static Map<String, Object> getData(HttpRequest httpRequest) {
            return Data.mapOf(UrlEncodedContent.getContent(httpRequest).getData());
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public String getAccessTokenFromRequest(HttpRequest httpRequest) {
            Object obj = getData(httpRequest).get("access_token");
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public void intercept(HttpRequest httpRequest, String str) {
            Preconditions.checkArgument(!"GET".equals(httpRequest.getRequestMethod()), "HTTP GET method is not supported");
            getData(httpRequest).put("access_token", str);
        }
    }

    /* loaded from: classes2.dex */
    static final class QueryParameterAccessMethod implements Credential.AccessMethod {
        QueryParameterAccessMethod() {
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public String getAccessTokenFromRequest(HttpRequest httpRequest) {
            Object obj = httpRequest.getUrl().get("access_token");
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }

        @Override // com.google.api.client.auth.oauth2.Credential.AccessMethod
        public void intercept(HttpRequest httpRequest, String str) {
            httpRequest.getUrl().set("access_token", (Object) str);
        }
    }

    public static Credential.AccessMethod authorizationHeaderAccessMethod() {
        return new AuthorizationHeaderAccessMethod();
    }

    public static Credential.AccessMethod formEncodedBodyAccessMethod() {
        return new FormEncodedBodyAccessMethod();
    }

    public static Credential.AccessMethod queryParameterAccessMethod() {
        return new QueryParameterAccessMethod();
    }
}
