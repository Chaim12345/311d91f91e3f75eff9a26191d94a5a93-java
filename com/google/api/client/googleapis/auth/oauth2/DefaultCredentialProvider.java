package com.google.api.client.googleapis.auth.oauth2;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.Beta;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessControlException;
import java.util.Locale;
@Beta
/* loaded from: classes2.dex */
class DefaultCredentialProvider extends SystemEnvironmentProvider {
    private GoogleCredential cachedCredential = null;
    private Environment detectedEnvironment = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.api.client.googleapis.auth.oauth2.DefaultCredentialProvider$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f7985a;

        static {
            int[] iArr = new int[Environment.values().length];
            f7985a = iArr;
            try {
                iArr[Environment.ENVIRONMENT_VARIABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7985a[Environment.WELL_KNOWN_FILE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7985a[Environment.APP_ENGINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7985a[Environment.CLOUD_SHELL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f7985a[Environment.COMPUTE_ENGINE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ComputeGoogleCredential extends GoogleCredential {
        private static final String TOKEN_SERVER_ENCODED_URL = OAuth2Utils.getMetadataServerUrl() + "/computeMetadata/v1/instance/service-accounts/default/token";

        ComputeGoogleCredential(HttpTransport httpTransport, JsonFactory jsonFactory) {
            super(new GoogleCredential.Builder().setTransport(httpTransport).setJsonFactory(jsonFactory).setTokenServerEncodedUrl(TOKEN_SERVER_ENCODED_URL));
        }

        @Override // com.google.api.client.googleapis.auth.oauth2.GoogleCredential, com.google.api.client.auth.oauth2.Credential
        protected TokenResponse a() {
            HttpRequest buildGetRequest = getTransport().createRequestFactory().buildGetRequest(new GenericUrl(getTokenServerEncodedUrl()));
            JsonObjectParser jsonObjectParser = new JsonObjectParser(getJsonFactory());
            buildGetRequest.setParser(jsonObjectParser);
            buildGetRequest.getHeaders().set("Metadata-Flavor", "Google");
            buildGetRequest.setThrowExceptionOnExecuteError(false);
            HttpResponse execute = buildGetRequest.execute();
            int statusCode = execute.getStatusCode();
            if (statusCode != 200) {
                if (statusCode == 404) {
                    throw new IOException(String.format("Error code %s trying to get security access token from Compute Engine metadata for the default service account. This may be because the virtual machine instance does not have permission scopes specified.", Integer.valueOf(statusCode)));
                }
                throw new IOException(String.format("Unexpected Error code %s trying to get security access token from Compute Engine metadata for the default service account: %s", Integer.valueOf(statusCode), execute.parseAsString()));
            }
            InputStream content = execute.getContent();
            if (content != null) {
                return (TokenResponse) jsonObjectParser.parseAndClose(content, execute.getContentCharset(), (Class<Object>) TokenResponse.class);
            }
            throw new IOException("Empty content from metadata token server request.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum Environment {
        UNKNOWN,
        ENVIRONMENT_VARIABLE,
        WELL_KNOWN_FILE,
        CLOUD_SHELL,
        APP_ENGINE,
        COMPUTE_ENGINE
    }

    private final Environment detectEnvironment(HttpTransport httpTransport) {
        return runningUsingEnvironmentVariable() ? Environment.ENVIRONMENT_VARIABLE : runningUsingWellKnownFile() ? Environment.WELL_KNOWN_FILE : useGAEStandardAPI() ? Environment.APP_ENGINE : runningOnCloudShell() ? Environment.CLOUD_SHELL : OAuth2Utils.d(httpTransport, this) ? Environment.COMPUTE_ENGINE : Environment.UNKNOWN;
    }

    private final GoogleCredential getAppEngineCredential(HttpTransport httpTransport, JsonFactory jsonFactory) {
        try {
            return (GoogleCredential) c("com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential$AppEngineCredentialWrapper").getConstructor(HttpTransport.class, JsonFactory.class).newInstance(httpTransport, jsonFactory);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e2) {
            throw ((IOException) OAuth2Utils.a(new IOException(String.format("Application Default Credentials failed to create the Google App Engine service account credentials class %s. Check that the component 'google-api-client-appengine' is deployed.", "com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential$AppEngineCredentialWrapper")), e2));
        }
    }

    private GoogleCredential getCloudShellCredential(JsonFactory jsonFactory) {
        return new CloudShellCredential(Integer.parseInt(a("DEVSHELL_CLIENT_PORT")), jsonFactory);
    }

    private final GoogleCredential getComputeCredential(HttpTransport httpTransport, JsonFactory jsonFactory) {
        return new ComputeGoogleCredential(httpTransport, jsonFactory);
    }

    private GoogleCredential getCredentialUsingEnvironmentVariable(HttpTransport httpTransport, JsonFactory jsonFactory) {
        FileInputStream fileInputStream;
        String a2 = a("GOOGLE_APPLICATION_CREDENTIALS");
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(a2);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            GoogleCredential fromStream = GoogleCredential.fromStream(fileInputStream, httpTransport, jsonFactory);
            fileInputStream.close();
            return fromStream;
        } catch (IOException e3) {
            e = e3;
            throw ((IOException) OAuth2Utils.a(new IOException(String.format("Error reading credential file from environment variable %s, value '%s': %s", "GOOGLE_APPLICATION_CREDENTIALS", a2, e.getMessage())), e));
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    private GoogleCredential getCredentialUsingWellKnownFile(HttpTransport httpTransport, JsonFactory jsonFactory) {
        FileInputStream fileInputStream;
        File wellKnownCredentialsFile = getWellKnownCredentialsFile();
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(wellKnownCredentialsFile);
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            GoogleCredential fromStream = GoogleCredential.fromStream(fileInputStream, httpTransport, jsonFactory);
            fileInputStream.close();
            return fromStream;
        } catch (IOException e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            throw new IOException(String.format("Error reading credential file from location %s: %s", wellKnownCredentialsFile, e.getMessage()));
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            throw th;
        }
    }

    private final GoogleCredential getDefaultCredentialUnsynchronized(HttpTransport httpTransport, JsonFactory jsonFactory) {
        if (this.detectedEnvironment == null) {
            this.detectedEnvironment = detectEnvironment(httpTransport);
        }
        int i2 = AnonymousClass1.f7985a[this.detectedEnvironment.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return null;
                        }
                        return getComputeCredential(httpTransport, jsonFactory);
                    }
                    return getCloudShellCredential(jsonFactory);
                }
                return getAppEngineCredential(httpTransport, jsonFactory);
            }
            return getCredentialUsingWellKnownFile(httpTransport, jsonFactory);
        }
        return getCredentialUsingEnvironmentVariable(httpTransport, jsonFactory);
    }

    private final File getWellKnownCredentialsFile() {
        return new File(e("os.name", "").toLowerCase(Locale.US).indexOf("windows") >= 0 ? new File(new File(a("APPDATA")), "gcloud") : new File(new File(e("user.home", ""), ".config"), "gcloud"), "application_default_credentials.json");
    }

    private boolean runningOnCloudShell() {
        return a("DEVSHELL_CLIENT_PORT") != null;
    }

    private boolean runningUsingEnvironmentVariable() {
        String a2 = a("GOOGLE_APPLICATION_CREDENTIALS");
        if (a2 != null && a2.length() != 0) {
            try {
                File file = new File(a2);
                if (!file.exists() || file.isDirectory()) {
                    throw new IOException(String.format("Error reading credential file from environment variable %s, value '%s': File does not exist.", "GOOGLE_APPLICATION_CREDENTIALS", a2));
                }
                return true;
            } catch (AccessControlException unused) {
            }
        }
        return false;
    }

    private boolean runningUsingWellKnownFile() {
        try {
            return b(getWellKnownCredentialsFile());
        } catch (AccessControlException unused) {
            return false;
        }
    }

    private boolean useGAEStandardAPI() {
        try {
            try {
                Field field = c("com.google.appengine.api.utils.SystemProperty").getField("environment");
                return field.getType().getMethod("value", new Class[0]).invoke(field.get(null), new Object[0]) != null;
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException | InvocationTargetException e2) {
                throw ((RuntimeException) OAuth2Utils.a(new RuntimeException(String.format("Unexpcted error trying to determine if runnning on Google App Engine: %s", e2.getMessage())), e2));
            }
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    boolean b(File file) {
        return file.exists() && !file.isDirectory();
    }

    Class c(String str) {
        return Class.forName(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final GoogleCredential d(HttpTransport httpTransport, JsonFactory jsonFactory) {
        synchronized (this) {
            if (this.cachedCredential == null) {
                this.cachedCredential = getDefaultCredentialUnsynchronized(httpTransport, jsonFactory);
            }
            GoogleCredential googleCredential = this.cachedCredential;
            if (googleCredential != null) {
                return googleCredential;
            }
            throw new IOException(String.format("The Application Default Credentials are not available. They are available if running on Google App Engine, Google Compute Engine, or Google Cloud Shell. Otherwise, the environment variable %s must be defined pointing to a file defining the credentials. See %s for more information.", "GOOGLE_APPLICATION_CREDENTIALS", "https://developers.google.com/accounts/docs/application-default-credentials"));
        }
    }

    String e(String str, String str2) {
        return System.getProperty(str, str2);
    }
}
