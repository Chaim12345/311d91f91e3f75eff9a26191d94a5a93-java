package com.appmattus.certificatetransparency.loglist;

import android.support.v4.media.session.PlaybackStateCompat;
import com.appmattus.certificatetransparency.cache.DiskCache;
import com.appmattus.certificatetransparency.datasource.DataSource;
import com.appmattus.certificatetransparency.internal.loglist.CallExtKt;
import com.appmattus.certificatetransparency.internal.loglist.InMemoryDataSource;
import com.appmattus.certificatetransparency.internal.loglist.LogListNetworkDataSource;
import com.appmattus.certificatetransparency.internal.loglist.LogListZipNetworkDataSource;
import com.appmattus.certificatetransparency.internal.loglist.parser.RawLogListToLogListResultTransformer;
import com.appmattus.certificatetransparency.internal.utils.MaxSizeInterceptor;
import com.appmattus.certificatetransparency.loglist.RawLogListResult;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LogListDataSourceFactory {
    @NotNull
    public static final LogListDataSourceFactory INSTANCE = new LogListDataSourceFactory();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class InMemoryCache extends InMemoryDataSource<RawLogListResult> {
        @Nullable
        public Object isValid(@Nullable RawLogListResult rawLogListResult, @NotNull Continuation<? super Boolean> continuation) {
            return Boxing.boxBoolean(rawLogListResult instanceof RawLogListResult.Success);
        }

        @Override // com.appmattus.certificatetransparency.internal.loglist.InMemoryDataSource, com.appmattus.certificatetransparency.datasource.DataSource
        public /* bridge */ /* synthetic */ Object isValid(Object obj, Continuation continuation) {
            return isValid((RawLogListResult) obj, (Continuation<? super Boolean>) continuation);
        }
    }

    private LogListDataSourceFactory() {
    }

    public static /* synthetic */ DataSource createDataSource$default(LogListDataSourceFactory logListDataSourceFactory, LogListService logListService, DiskCache diskCache, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            logListService = createLogListService$default(logListDataSourceFactory, null, null, 0L, null, 15, null);
        }
        if ((i2 & 2) != 0) {
            diskCache = null;
        }
        return logListDataSourceFactory.createDataSource(logListService, diskCache);
    }

    public static /* synthetic */ LogListService createLogListService$default(LogListDataSourceFactory logListDataSourceFactory, String str, OkHttpClient okHttpClient, long j2, X509TrustManager x509TrustManager, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = "https://www.gstatic.com/ct/log_list/v2/";
        }
        OkHttpClient okHttpClient2 = (i2 & 2) != 0 ? null : okHttpClient;
        if ((i2 & 4) != 0) {
            j2 = 30;
        }
        return logListDataSourceFactory.createLogListService(str, okHttpClient2, j2, (i2 & 8) == 0 ? x509TrustManager : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final DataSource<LogListResult> createDataSource(@NotNull LogListService logListService, @Nullable DiskCache diskCache) {
        DataSource compose;
        Intrinsics.checkNotNullParameter(logListService, "logListService");
        RawLogListToLogListResultTransformer rawLogListToLogListResultTransformer = new RawLogListToLogListResultTransformer(null, null, 3, null);
        DataSource inMemoryCache = new InMemoryCache();
        if (diskCache != null && (compose = inMemoryCache.compose(diskCache)) != null) {
            inMemoryCache = compose;
        }
        return inMemoryCache.compose(new LogListZipNetworkDataSource(logListService)).compose(new LogListNetworkDataSource(logListService)).oneWayTransform(new LogListDataSourceFactory$createDataSource$2(rawLogListToLogListResultTransformer)).reuseInflight();
    }

    @NotNull
    public final LogListService createLogListService(@NotNull final String baseUrl, @Nullable OkHttpClient okHttpClient, long j2, @Nullable X509TrustManager x509TrustManager) {
        OkHttpClient.Builder builder;
        Intrinsics.checkNotNullParameter(baseUrl, "baseUrl");
        if (okHttpClient == null || (builder = okHttpClient.newBuilder()) == null) {
            builder = new OkHttpClient.Builder();
        }
        if (x509TrustManager != null) {
            try {
                SSLContext sSLContext = SSLContext.getInstance("SSL");
                Intrinsics.checkNotNullExpressionValue(sSLContext, "getInstance(\"SSL\")");
                sSLContext.init(null, new X509TrustManager[]{x509TrustManager}, new SecureRandom());
                SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
                Intrinsics.checkNotNullExpressionValue(socketFactory, "sslContext.socketFactory");
                builder.sslSocketFactory(socketFactory, x509TrustManager);
            } catch (KeyManagementException unused) {
                throw new IllegalStateException("Unable to create an SSLContext");
            } catch (NoSuchAlgorithmException unused2) {
                throw new IllegalStateException("Unable to create an SSLContext");
            }
        }
        builder.addInterceptor(new MaxSizeInterceptor());
        TimeUnit timeUnit = TimeUnit.SECONDS;
        builder.connectTimeout(j2, timeUnit);
        builder.readTimeout(j2, timeUnit);
        builder.writeTimeout(j2, timeUnit);
        builder.cache(null);
        final OkHttpClient build = builder.build();
        return new LogListService() { // from class: com.appmattus.certificatetransparency.loglist.LogListDataSourceFactory$createLogListService$1
            /* JADX INFO: Access modifiers changed from: private */
            public final Object get(String str, long j3, Continuation<? super byte[]> continuation) {
                return CallExtKt.await(build.newCall(new Request.Builder().url(HttpUrl.Companion.get(baseUrl).newBuilder().addPathSegment(str).build()).cacheControl(new CacheControl.Builder().noCache().noStore().build()).addHeader(MaxSizeInterceptor.HEADER, String.valueOf(j3)).build()), continuation);
            }

            @Override // com.appmattus.certificatetransparency.loglist.LogListService
            @Nullable
            public Object getLogList(@NotNull Continuation<? super byte[]> continuation) {
                return get("log_list.json", PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, continuation);
            }

            @Override // com.appmattus.certificatetransparency.loglist.LogListService
            @Nullable
            public Object getLogListSignature(@NotNull Continuation<? super byte[]> continuation) {
                return get("log_list.sig", 512L, continuation);
            }

            @Override // com.appmattus.certificatetransparency.loglist.LogListService
            @Nullable
            public Object getLogListZip(@NotNull Continuation<? super byte[]> continuation) {
                return get("log_list.zip", PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, continuation);
            }
        };
    }
}
