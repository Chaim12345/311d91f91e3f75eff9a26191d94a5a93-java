package com.google.api.client.googleapis.testing.compute;

import com.google.api.client.googleapis.auth.oauth2.OAuth2Utils;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.Json;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;
import com.google.api.client.util.Beta;
import java.io.IOException;
@Beta
/* loaded from: classes2.dex */
public class MockMetadataServerTransport extends MockHttpTransport {
    private static final String METADATA_SERVER_URL;
    private static final String METADATA_TOKEN_SERVER_URL;

    /* renamed from: d  reason: collision with root package name */
    static final JsonFactory f8032d;

    /* renamed from: b  reason: collision with root package name */
    String f8033b;

    /* renamed from: c  reason: collision with root package name */
    Integer f8034c;

    static {
        String metadataServerUrl = OAuth2Utils.getMetadataServerUrl();
        METADATA_SERVER_URL = metadataServerUrl;
        METADATA_TOKEN_SERVER_URL = metadataServerUrl + "/computeMetadata/v1/instance/service-accounts/default/token";
        f8032d = new JacksonFactory();
    }

    public MockMetadataServerTransport(String str) {
        this.f8033b = str;
    }

    @Override // com.google.api.client.testing.http.MockHttpTransport, com.google.api.client.http.HttpTransport
    public LowLevelHttpRequest buildRequest(String str, String str2) {
        return str2.equals(METADATA_TOKEN_SERVER_URL) ? new MockLowLevelHttpRequest(str2) { // from class: com.google.api.client.googleapis.testing.compute.MockMetadataServerTransport.1
            @Override // com.google.api.client.testing.http.MockLowLevelHttpRequest, com.google.api.client.http.LowLevelHttpRequest
            public LowLevelHttpResponse execute() {
                if (MockMetadataServerTransport.this.f8034c != null) {
                    return new MockLowLevelHttpResponse().setStatusCode(MockMetadataServerTransport.this.f8034c.intValue()).setContent("Token Fetch Error");
                }
                if ("Google".equals(getFirstHeaderValue("Metadata-Flavor"))) {
                    GenericJson genericJson = new GenericJson();
                    genericJson.setFactory(MockMetadataServerTransport.f8032d);
                    genericJson.put("access_token", (Object) MockMetadataServerTransport.this.f8033b);
                    genericJson.put("expires_in", (Object) 3600000);
                    genericJson.put("token_type", (Object) "Bearer");
                    return new MockLowLevelHttpResponse().setContentType(Json.MEDIA_TYPE).setContent(genericJson.toPrettyString());
                }
                throw new IOException("Metadata request header not found.");
            }
        } : str2.equals(METADATA_SERVER_URL) ? new MockLowLevelHttpRequest(this, str2) { // from class: com.google.api.client.googleapis.testing.compute.MockMetadataServerTransport.2
            @Override // com.google.api.client.testing.http.MockLowLevelHttpRequest, com.google.api.client.http.LowLevelHttpRequest
            public LowLevelHttpResponse execute() {
                MockLowLevelHttpResponse mockLowLevelHttpResponse = new MockLowLevelHttpResponse();
                mockLowLevelHttpResponse.addHeader("Metadata-Flavor", "Google");
                return mockLowLevelHttpResponse;
            }
        } : super.buildRequest(str, str2);
    }

    public void setTokenRequestStatusCode(Integer num) {
        this.f8034c = num;
    }
}
