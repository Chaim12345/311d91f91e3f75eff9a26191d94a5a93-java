package com.google.android.datatransport.cct;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.backend.cct.BuildConfig;
import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.android.datatransport.cct.internal.AndroidClientInfo;
import com.google.android.datatransport.cct.internal.BatchedLogRequest;
import com.google.android.datatransport.cct.internal.ClientInfo;
import com.google.android.datatransport.cct.internal.LogEvent;
import com.google.android.datatransport.cct.internal.LogRequest;
import com.google.android.datatransport.cct.internal.LogResponse;
import com.google.android.datatransport.cct.internal.NetworkConnectionInfo;
import com.google.android.datatransport.cct.internal.QosTier;
import com.google.android.datatransport.runtime.EncodedPayload;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.backends.BackendRequest;
import com.google.android.datatransport.runtime.backends.BackendResponse;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.retries.Function;
import com.google.android.datatransport.runtime.retries.Retries;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.EncodingException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class CctTransportBackend implements TransportBackend {
    private static final String ACCEPT_ENCODING_HEADER_KEY = "Accept-Encoding";
    private static final int CONNECTION_TIME_OUT = 30000;
    private static final String CONTENT_ENCODING_HEADER_KEY = "Content-Encoding";
    private static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    private static final String GZIP_CONTENT_ENCODING = "gzip";
    private static final int INVALID_VERSION_CODE = -1;
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final String KEY_APPLICATION_BUILD = "application_build";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_DEVICE = "device";
    private static final String KEY_FINGERPRINT = "fingerprint";
    private static final String KEY_HARDWARE = "hardware";
    private static final String KEY_LOCALE = "locale";
    private static final String KEY_MANUFACTURER = "manufacturer";
    private static final String KEY_MCC_MNC = "mcc_mnc";
    private static final String KEY_MODEL = "model";
    private static final String KEY_OS_BUILD = "os-uild";
    private static final String KEY_PRODUCT = "product";
    private static final String KEY_SDK_VERSION = "sdk-version";
    private static final String KEY_TIMEZONE_OFFSET = "tz-offset";
    private static final String LOG_TAG = "CctTransportBackend";
    private static final int READ_TIME_OUT = 40000;

    /* renamed from: a  reason: collision with root package name */
    final URL f5480a;
    private final Context applicationContext;
    private final ConnectivityManager connectivityManager;
    private final DataEncoder dataEncoder;
    private final int readTimeout;
    private final Clock uptimeClock;
    private final Clock wallTimeClock;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class HttpRequest {

        /* renamed from: a  reason: collision with root package name */
        final URL f5481a;

        /* renamed from: b  reason: collision with root package name */
        final BatchedLogRequest f5482b;
        @Nullable

        /* renamed from: c  reason: collision with root package name */
        final String f5483c;

        HttpRequest(URL url, BatchedLogRequest batchedLogRequest, @Nullable String str) {
            this.f5481a = url;
            this.f5482b = batchedLogRequest;
            this.f5483c = str;
        }

        HttpRequest a(URL url) {
            return new HttpRequest(url, this.f5482b, this.f5483c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class HttpResponse {

        /* renamed from: a  reason: collision with root package name */
        final int f5484a;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        final URL f5485b;

        /* renamed from: c  reason: collision with root package name */
        final long f5486c;

        HttpResponse(int i2, @Nullable URL url, long j2) {
            this.f5484a = i2;
            this.f5485b = url;
            this.f5486c = j2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CctTransportBackend(Context context, Clock clock, Clock clock2) {
        this(context, clock, clock2, READ_TIME_OUT);
    }

    CctTransportBackend(Context context, Clock clock, Clock clock2, int i2) {
        this.dataEncoder = BatchedLogRequest.createDataEncoder();
        this.applicationContext = context;
        this.connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.f5480a = parseUrlOrThrow(CCTDestination.f5478a);
        this.uptimeClock = clock2;
        this.wallTimeClock = clock;
        this.readTimeout = i2;
    }

    @VisibleForTesting
    static long c() {
        Calendar.getInstance();
        return TimeZone.getDefault().getOffset(Calendar.getInstance().getTimeInMillis()) / 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HttpResponse doSend(HttpRequest httpRequest) {
        Logging.i(LOG_TAG, "Making request to: %s", httpRequest.f5481a);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpRequest.f5481a.openConnection();
        httpURLConnection.setConnectTimeout(CONNECTION_TIME_OUT);
        httpURLConnection.setReadTimeout(this.readTimeout);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("User-Agent", String.format("datatransport/%s android/", BuildConfig.VERSION_NAME));
        httpURLConnection.setRequestProperty("Content-Encoding", GZIP_CONTENT_ENCODING);
        httpURLConnection.setRequestProperty("Content-Type", JSON_CONTENT_TYPE);
        httpURLConnection.setRequestProperty("Accept-Encoding", GZIP_CONTENT_ENCODING);
        String str = httpRequest.f5483c;
        if (str != null) {
            httpURLConnection.setRequestProperty("X-Goog-Api-Key", str);
        }
        try {
            OutputStream outputStream = httpURLConnection.getOutputStream();
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
                this.dataEncoder.encode(httpRequest.f5482b, new BufferedWriter(new OutputStreamWriter(gZIPOutputStream)));
                gZIPOutputStream.close();
                if (outputStream != null) {
                    outputStream.close();
                }
                int responseCode = httpURLConnection.getResponseCode();
                Logging.i(LOG_TAG, "Status Code: %d", Integer.valueOf(responseCode));
                Logging.d(LOG_TAG, "Content-Type: %s", httpURLConnection.getHeaderField("Content-Type"));
                Logging.d(LOG_TAG, "Content-Encoding: %s", httpURLConnection.getHeaderField("Content-Encoding"));
                if (responseCode == 302 || responseCode == 301 || responseCode == 307) {
                    return new HttpResponse(responseCode, new URL(httpURLConnection.getHeaderField("Location")), 0L);
                }
                if (responseCode != 200) {
                    return new HttpResponse(responseCode, null, 0L);
                }
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    InputStream maybeUnGzip = maybeUnGzip(inputStream, httpURLConnection.getHeaderField("Content-Encoding"));
                    HttpResponse httpResponse = new HttpResponse(responseCode, null, LogResponse.fromJson(new BufferedReader(new InputStreamReader(maybeUnGzip))).getNextRequestWaitMillis());
                    if (maybeUnGzip != null) {
                        maybeUnGzip.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    return httpResponse;
                } catch (Throwable th) {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                }
                throw th3;
            }
        } catch (EncodingException e2) {
            e = e2;
            Logging.e(LOG_TAG, "Couldn't encode request, returning with 400", e);
            return new HttpResponse(400, null, 0L);
        } catch (ConnectException e3) {
            e = e3;
            Logging.e(LOG_TAG, "Couldn't open connection, returning with 500", e);
            return new HttpResponse(500, null, 0L);
        } catch (UnknownHostException e4) {
            e = e4;
            Logging.e(LOG_TAG, "Couldn't open connection, returning with 500", e);
            return new HttpResponse(500, null, 0L);
        } catch (IOException e5) {
            e = e5;
            Logging.e(LOG_TAG, "Couldn't encode request, returning with 400", e);
            return new HttpResponse(400, null, 0L);
        }
    }

    private static int getNetSubtypeValue(NetworkInfo networkInfo) {
        NetworkConnectionInfo.MobileSubtype mobileSubtype;
        if (networkInfo == null) {
            mobileSubtype = NetworkConnectionInfo.MobileSubtype.UNKNOWN_MOBILE_SUBTYPE;
        } else {
            int subtype = networkInfo.getSubtype();
            if (subtype != -1) {
                if (NetworkConnectionInfo.MobileSubtype.forNumber(subtype) != null) {
                    return subtype;
                }
                return 0;
            }
            mobileSubtype = NetworkConnectionInfo.MobileSubtype.COMBINED;
        }
        return mobileSubtype.getValue();
    }

    private static int getNetTypeValue(NetworkInfo networkInfo) {
        return networkInfo == null ? NetworkConnectionInfo.NetworkType.NONE.getValue() : networkInfo.getType();
    }

    private static int getPackageVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            Logging.e(LOG_TAG, "Unable to find version code for package", e2);
            return -1;
        }
    }

    private BatchedLogRequest getRequestBody(BackendRequest backendRequest) {
        LogEvent.Builder protoBuilder;
        HashMap hashMap = new HashMap();
        for (EventInternal eventInternal : backendRequest.getEvents()) {
            String transportName = eventInternal.getTransportName();
            if (hashMap.containsKey(transportName)) {
                ((List) hashMap.get(transportName)).add(eventInternal);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(eventInternal);
                hashMap.put(transportName, arrayList);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : hashMap.entrySet()) {
            EventInternal eventInternal2 = (EventInternal) ((List) entry.getValue()).get(0);
            LogRequest.Builder clientInfo = LogRequest.builder().setQosTier(QosTier.DEFAULT).setRequestTimeMs(this.wallTimeClock.getTime()).setRequestUptimeMs(this.uptimeClock.getTime()).setClientInfo(ClientInfo.builder().setClientType(ClientInfo.ClientType.ANDROID_FIREBASE).setAndroidClientInfo(AndroidClientInfo.builder().setSdkVersion(Integer.valueOf(eventInternal2.getInteger(KEY_SDK_VERSION))).setModel(eventInternal2.get(KEY_MODEL)).setHardware(eventInternal2.get("hardware")).setDevice(eventInternal2.get(KEY_DEVICE)).setProduct(eventInternal2.get(KEY_PRODUCT)).setOsBuild(eventInternal2.get(KEY_OS_BUILD)).setManufacturer(eventInternal2.get(KEY_MANUFACTURER)).setFingerprint(eventInternal2.get(KEY_FINGERPRINT)).setCountry(eventInternal2.get(KEY_COUNTRY)).setLocale(eventInternal2.get(KEY_LOCALE)).setMccMnc(eventInternal2.get(KEY_MCC_MNC)).setApplicationBuild(eventInternal2.get(KEY_APPLICATION_BUILD)).build()).build());
            try {
                clientInfo.setSource(Integer.parseInt((String) entry.getKey()));
            } catch (NumberFormatException unused) {
                clientInfo.setSource((String) entry.getKey());
            }
            ArrayList arrayList3 = new ArrayList();
            for (EventInternal eventInternal3 : (List) entry.getValue()) {
                EncodedPayload encodedPayload = eventInternal3.getEncodedPayload();
                Encoding encoding = encodedPayload.getEncoding();
                if (encoding.equals(Encoding.of("proto"))) {
                    protoBuilder = LogEvent.protoBuilder(encodedPayload.getBytes());
                } else if (encoding.equals(Encoding.of("json"))) {
                    protoBuilder = LogEvent.jsonBuilder(new String(encodedPayload.getBytes(), Charset.forName("UTF-8")));
                } else {
                    Logging.w(LOG_TAG, "Received event of unsupported encoding %s. Skipping...", encoding);
                }
                protoBuilder.setEventTimeMs(eventInternal3.getEventMillis()).setEventUptimeMs(eventInternal3.getUptimeMillis()).setTimezoneOffsetSeconds(eventInternal3.getLong(KEY_TIMEZONE_OFFSET)).setNetworkConnectionInfo(NetworkConnectionInfo.builder().setNetworkType(NetworkConnectionInfo.NetworkType.forNumber(eventInternal3.getInteger("net-type"))).setMobileSubtype(NetworkConnectionInfo.MobileSubtype.forNumber(eventInternal3.getInteger("mobile-subtype"))).build());
                if (eventInternal3.getCode() != null) {
                    protoBuilder.setEventCode(eventInternal3.getCode());
                }
                arrayList3.add(protoBuilder.build());
            }
            clientInfo.setLogEvents(arrayList3);
            arrayList2.add(clientInfo.build());
        }
        return BatchedLogRequest.create(arrayList2);
    }

    private static TelephonyManager getTelephonyManager(Context context) {
        return (TelephonyManager) context.getSystemService("phone");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ HttpRequest lambda$send$0(HttpRequest httpRequest, HttpResponse httpResponse) {
        URL url = httpResponse.f5485b;
        if (url != null) {
            Logging.d(LOG_TAG, "Following redirect to: %s", url);
            return httpRequest.a(httpResponse.f5485b);
        }
        return null;
    }

    private static InputStream maybeUnGzip(InputStream inputStream, String str) {
        return GZIP_CONTENT_ENCODING.equals(str) ? new GZIPInputStream(inputStream) : inputStream;
    }

    private static URL parseUrlOrThrow(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e2) {
            throw new IllegalArgumentException("Invalid url: " + str, e2);
        }
    }

    @Override // com.google.android.datatransport.runtime.backends.TransportBackend
    public EventInternal decorate(EventInternal eventInternal) {
        NetworkInfo activeNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
        return eventInternal.toBuilder().addMetadata(KEY_SDK_VERSION, Build.VERSION.SDK_INT).addMetadata(KEY_MODEL, Build.MODEL).addMetadata("hardware", Build.HARDWARE).addMetadata(KEY_DEVICE, Build.DEVICE).addMetadata(KEY_PRODUCT, Build.PRODUCT).addMetadata(KEY_OS_BUILD, Build.ID).addMetadata(KEY_MANUFACTURER, Build.MANUFACTURER).addMetadata(KEY_FINGERPRINT, Build.FINGERPRINT).addMetadata(KEY_TIMEZONE_OFFSET, c()).addMetadata("net-type", getNetTypeValue(activeNetworkInfo)).addMetadata("mobile-subtype", getNetSubtypeValue(activeNetworkInfo)).addMetadata(KEY_COUNTRY, Locale.getDefault().getCountry()).addMetadata(KEY_LOCALE, Locale.getDefault().getLanguage()).addMetadata(KEY_MCC_MNC, getTelephonyManager(this.applicationContext).getSimOperator()).addMetadata(KEY_APPLICATION_BUILD, Integer.toString(getPackageVersionCode(this.applicationContext))).build();
    }

    @Override // com.google.android.datatransport.runtime.backends.TransportBackend
    public BackendResponse send(BackendRequest backendRequest) {
        BatchedLogRequest requestBody = getRequestBody(backendRequest);
        URL url = this.f5480a;
        if (backendRequest.getExtras() != null) {
            try {
                CCTDestination fromByteArray = CCTDestination.fromByteArray(backendRequest.getExtras());
                r3 = fromByteArray.getAPIKey() != null ? fromByteArray.getAPIKey() : null;
                if (fromByteArray.getEndPoint() != null) {
                    url = parseUrlOrThrow(fromByteArray.getEndPoint());
                }
            } catch (IllegalArgumentException unused) {
                return BackendResponse.fatalError();
            }
        }
        try {
            HttpResponse httpResponse = (HttpResponse) Retries.retry(5, new HttpRequest(url, requestBody, r3), new Function() { // from class: com.google.android.datatransport.cct.a
                @Override // com.google.android.datatransport.runtime.retries.Function
                public final Object apply(Object obj) {
                    CctTransportBackend.HttpResponse doSend;
                    doSend = CctTransportBackend.this.doSend((CctTransportBackend.HttpRequest) obj);
                    return doSend;
                }
            }, b.f5488a);
            int i2 = httpResponse.f5484a;
            if (i2 == 200) {
                return BackendResponse.ok(httpResponse.f5486c);
            }
            if (i2 < 500 && i2 != 404) {
                return i2 == 400 ? BackendResponse.invalidPayload() : BackendResponse.fatalError();
            }
            return BackendResponse.transientError();
        } catch (IOException e2) {
            Logging.e(LOG_TAG, "Could not make request to the backend", e2);
            return BackendResponse.transientError();
        }
    }
}
