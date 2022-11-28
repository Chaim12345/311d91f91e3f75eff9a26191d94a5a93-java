package com.google.api.client.googleapis.testing.auth.oauth2;

import com.google.api.client.googleapis.auth.oauth2.GoogleOAuthConstants;
import com.google.api.client.googleapis.testing.TestUtils;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.webtoken.JsonWebSignature;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
@Beta
/* loaded from: classes2.dex */
public class MockTokenServerTransport extends MockHttpTransport {
    private static final String LEGACY_TOKEN_SERVER_URL = "https://accounts.google.com/o/oauth2/token";
    private static final Logger LOGGER = Logger.getLogger(MockTokenServerTransport.class.getName());

    /* renamed from: f  reason: collision with root package name */
    static final JsonFactory f8026f = new JacksonFactory();

    /* renamed from: b  reason: collision with root package name */
    final String f8027b;

    /* renamed from: c  reason: collision with root package name */
    Map f8028c;

    /* renamed from: d  reason: collision with root package name */
    Map f8029d;

    /* renamed from: e  reason: collision with root package name */
    Map f8030e;

    public MockTokenServerTransport() {
        this(GoogleOAuthConstants.TOKEN_SERVER_URL);
    }

    public MockTokenServerTransport(String str) {
        this.f8028c = new HashMap();
        this.f8029d = new HashMap();
        this.f8030e = new HashMap();
        this.f8027b = str;
    }

    private MockLowLevelHttpRequest buildTokenRequest(String str) {
        return new MockLowLevelHttpRequest(str) { // from class: com.google.api.client.googleapis.testing.auth.oauth2.MockTokenServerTransport.1
            @Override // com.google.api.client.testing.http.MockLowLevelHttpRequest, com.google.api.client.http.LowLevelHttpRequest
            public LowLevelHttpResponse execute() {
                String str2;
                Map<String, String> parseQuery = TestUtils.parseQuery(getContentAsString());
                String str3 = parseQuery.get("client_id");
                if (str3 != null) {
                    if (!MockTokenServerTransport.this.f8029d.containsKey(str3)) {
                        throw new IOException("Client ID not found.");
                    }
                    String str4 = parseQuery.get("client_secret");
                    String str5 = (String) MockTokenServerTransport.this.f8029d.get(str3);
                    if (str4 == null || !str4.equals(str5)) {
                        throw new IOException("Client secret not found.");
                    }
                    String str6 = parseQuery.get("refresh_token");
                    if (!MockTokenServerTransport.this.f8030e.containsKey(str6)) {
                        throw new IOException("Refresh Token not found.");
                    }
                    str2 = (String) MockTokenServerTransport.this.f8030e.get(str6);
                } else if (!parseQuery.containsKey("grant_type")) {
                    throw new IOException("Unknown token type.");
                } else {
                    if (!"urn:ietf:params:oauth:grant-type:jwt-bearer".equals(parseQuery.get("grant_type"))) {
                        throw new IOException("Unexpected Grant Type.");
                    }
                    JsonWebSignature parse = JsonWebSignature.parse(MockTokenServerTransport.f8026f, parseQuery.get("assertion"));
                    String issuer = parse.getPayload().getIssuer();
                    if (!MockTokenServerTransport.this.f8028c.containsKey(issuer)) {
                        throw new IOException("Service Account Email not found as issuer.");
                    }
                    String str7 = (String) MockTokenServerTransport.this.f8028c.get(issuer);
                    String str8 = (String) parse.getPayload().get("scope");
                    if (str8 == null || str8.length() == 0) {
                        throw new IOException("Scopes not found.");
                    }
                    str2 = str7;
                }
                GenericJson genericJson = new GenericJson();
                genericJson.setFactory(MockTokenServerTransport.f8026f);
                genericJson.put("access_token", (Object) str2);
                genericJson.put("expires_in", (Object) 3600);
                genericJson.put("token_type", (Object) "Bearer");
                return new MockLowLevelHttpResponse().setContentType(Json.MEDIA_TYPE).setContent(genericJson.toPrettyString());
            }
        };
    }

    public void addClient(String str, String str2) {
        this.f8029d.put(str, str2);
    }

    public void addRefreshToken(String str, String str2) {
        this.f8030e.put(str, str2);
    }

    public void addServiceAccount(String str, String str2) {
        this.f8028c.put(str, str2);
    }

    @Override // com.google.api.client.testing.http.MockHttpTransport, com.google.api.client.http.HttpTransport
    public LowLevelHttpRequest buildRequest(String str, String str2) {
        if (str2.equals(this.f8027b)) {
            return buildTokenRequest(str2);
        }
        if (str2.equals(LEGACY_TOKEN_SERVER_URL)) {
            LOGGER.warning("Your configured token_uri is using a legacy endpoint. You may want to redownload your credentials.");
            return buildTokenRequest(str2);
        }
        return super.buildRequest(str, str2);
    }
}
