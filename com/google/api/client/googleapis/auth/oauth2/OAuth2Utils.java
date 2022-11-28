package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Beta;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
@Beta
/* loaded from: classes2.dex */
public class OAuth2Utils {
    private static final int COMPUTE_PING_CONNECTION_TIMEOUT_MS = 500;
    private static final String DEFAULT_METADATA_SERVER_URL = "http://169.254.169.254";
    private static final int MAX_COMPUTE_PING_TRIES = 3;

    /* renamed from: a  reason: collision with root package name */
    static final Charset f7999a = Charset.forName("UTF-8");
    private static final Logger LOGGER = Logger.getLogger(OAuth2Utils.class.getName());

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Throwable a(Throwable th, Throwable th2) {
        th.initCause(th2);
        return th;
    }

    static String b(SystemEnvironmentProvider systemEnvironmentProvider) {
        String a2 = systemEnvironmentProvider.a("GCE_METADATA_HOST");
        if (a2 != null) {
            return "http://" + a2;
        }
        return DEFAULT_METADATA_SERVER_URL;
    }

    static boolean c(HttpHeaders httpHeaders, String str, String str2) {
        Object obj = httpHeaders.get(str);
        if (obj instanceof Collection) {
            for (Object obj2 : (Collection) obj) {
                if ((obj2 instanceof String) && ((String) obj2).equals(str2)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(HttpTransport httpTransport, SystemEnvironmentProvider systemEnvironmentProvider) {
        if (Boolean.parseBoolean(systemEnvironmentProvider.a("NO_GCE_CHECK"))) {
            return false;
        }
        GenericUrl genericUrl = new GenericUrl(b(systemEnvironmentProvider));
        for (int i2 = 1; i2 <= 3; i2++) {
            try {
                HttpRequest buildGetRequest = httpTransport.createRequestFactory().buildGetRequest(genericUrl);
                buildGetRequest.setConnectTimeout(500);
                buildGetRequest.getHeaders().set("Metadata-Flavor", "Google");
                HttpResponse execute = buildGetRequest.execute();
                boolean c2 = c(execute.getHeaders(), "Metadata-Flavor", "Google");
                execute.disconnect();
                return c2;
            } catch (SocketTimeoutException unused) {
            } catch (IOException e2) {
                LOGGER.log(Level.WARNING, "Failed to detect whether we are running on Google Compute Engine.", (Throwable) e2);
            }
        }
        return false;
    }

    public static String getMetadataServerUrl() {
        return b(SystemEnvironmentProvider.f8000a);
    }
}
