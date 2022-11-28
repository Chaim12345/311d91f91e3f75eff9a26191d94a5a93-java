package com.chuckerteam.chucker.api;

import android.content.Context;
import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.support.CacheDirectoryProvider;
import com.chuckerteam.chucker.internal.support.DepletingSource;
import com.chuckerteam.chucker.internal.support.FileFactory;
import com.chuckerteam.chucker.internal.support.IOUtils;
import com.chuckerteam.chucker.internal.support.OkHttpUtilsKt;
import com.chuckerteam.chucker.internal.support.ReportingSink;
import com.chuckerteam.chucker.internal.support.TeeSource;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.Handshake;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0007\u0018\u0000 52\u00020\u0001:\u0003675BG\b\u0000\u0012\u0006\u0010#\u001a\u00020\"\u0012\b\b\u0002\u0010&\u001a\u00020%\u0012\b\b\u0002\u0010)\u001a\u00020(\u0012\u0006\u0010,\u001a\u00020+\u0012\b\b\u0002\u0010/\u001a\u00020.\u0012\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001501¢\u0006\u0004\b2\u00103B?\b\u0017\u0012\u0006\u0010#\u001a\u00020\"\u0012\b\b\u0002\u0010&\u001a\u00020%\u0012\b\b\u0002\u0010)\u001a\u00020(\u0012\u000e\b\u0002\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001501\u0012\b\b\u0002\u0010/\u001a\u00020.¢\u0006\u0004\b2\u00104J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\n\u0010\r\u001a\u0004\u0018\u00010\fH\u0002J \u0010\u0010\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002J!\u0010\u0017\u001a\u00020\u00062\u0012\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00150\u0014\"\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016R\u0016\u0010\u001d\u001a\u00020\u001c8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u001eR\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00150\u001f8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010!R\u0016\u0010#\u001a\u00020\"8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010&\u001a\u00020%8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u0016\u0010)\u001a\u00020(8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010,\u001a\u00020+8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010/\u001a\u00020.8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100¨\u00068"}, d2 = {"Lcom/chuckerteam/chucker/api/ChuckerInterceptor;", "Lokhttp3/Interceptor;", "Lokhttp3/Request;", "request", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "", "processRequest", "Lokhttp3/Response;", "response", "processResponseMetadata", "multiCastResponseBody", "Ljava/io/File;", "createTempTransactionFile", "Lokio/Buffer;", "responseBodyBuffer", "processResponseBody", "Lokhttp3/Headers;", "headers", "filterHeaders", "", "", "headerName", "redactHeader", "([Ljava/lang/String;)V", "Lokhttp3/Interceptor$Chain;", "chain", "intercept", "Lcom/chuckerteam/chucker/internal/support/IOUtils;", "io", "Lcom/chuckerteam/chucker/internal/support/IOUtils;", "", "headersToRedact", "Ljava/util/Set;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/chuckerteam/chucker/api/ChuckerCollector;", "collector", "Lcom/chuckerteam/chucker/api/ChuckerCollector;", "", "maxContentLength", "J", "Lcom/chuckerteam/chucker/internal/support/CacheDirectoryProvider;", "cacheDirectoryProvider", "Lcom/chuckerteam/chucker/internal/support/CacheDirectoryProvider;", "", "alwaysReadResponseBody", "Z", "", "<init>", "(Landroid/content/Context;Lcom/chuckerteam/chucker/api/ChuckerCollector;JLcom/chuckerteam/chucker/internal/support/CacheDirectoryProvider;ZLjava/util/Set;)V", "(Landroid/content/Context;Lcom/chuckerteam/chucker/api/ChuckerCollector;JLjava/util/Set;Z)V", "Companion", "Builder", "ChuckerTransactionReportingSinkCallback", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ChuckerInterceptor implements Interceptor {
    private static final String CONTENT_ENCODING = "Content-Encoding";
    private static final String CONTENT_TYPE_IMAGE = "image";
    private static final long MAX_BLOB_SIZE = 1000000;
    private static final long MAX_CONTENT_LENGTH = 250000;
    private final boolean alwaysReadResponseBody;
    private final CacheDirectoryProvider cacheDirectoryProvider;
    private final ChuckerCollector collector;
    private final Context context;
    private final Set<String> headersToRedact;

    /* renamed from: io  reason: collision with root package name */
    private final IOUtils f4850io;
    private final long maxContentLength;
    private static final Companion Companion = new Companion(null);
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b\u001e\u0010\u001fJ\u000e\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u0014\u0010\n\u001a\u00020\u00002\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J!\u0010\n\u001a\u00020\u00002\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\u000b\"\u00020\b¢\u0006\u0004\b\n\u0010\fJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u0011\u001a\u00020\u0010R\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0003\u0010\u0012R\u0016\u0010\u0006\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0006\u0010\u0013R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u000f\u001a\u00020\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0017R\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001c\u001a\u00020\u001b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lcom/chuckerteam/chucker/api/ChuckerInterceptor$Builder;", "", "Lcom/chuckerteam/chucker/api/ChuckerCollector;", "collector", "", "length", "maxContentLength", "", "", "headerNames", "redactHeaders", "", "([Ljava/lang/String;)Lcom/chuckerteam/chucker/api/ChuckerInterceptor$Builder;", "", "enable", "alwaysReadResponseBody", "Lcom/chuckerteam/chucker/api/ChuckerInterceptor;", "build", "Lcom/chuckerteam/chucker/api/ChuckerCollector;", "J", "Lcom/chuckerteam/chucker/internal/support/CacheDirectoryProvider;", "cacheDirectoryProvider", "Lcom/chuckerteam/chucker/internal/support/CacheDirectoryProvider;", "Z", "", "headersToRedact", "Ljava/util/Set;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Builder {
        private boolean alwaysReadResponseBody;
        private CacheDirectoryProvider cacheDirectoryProvider;
        private ChuckerCollector collector;
        private Context context;
        private Set<String> headersToRedact;
        private long maxContentLength;

        public Builder(@NotNull Context context) {
            Set<String> emptySet;
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
            Companion unused = ChuckerInterceptor.Companion;
            this.maxContentLength = ChuckerInterceptor.MAX_CONTENT_LENGTH;
            emptySet = SetsKt__SetsKt.emptySet();
            this.headersToRedact = emptySet;
        }

        @NotNull
        public final Builder alwaysReadResponseBody(boolean z) {
            this.alwaysReadResponseBody = z;
            return this;
        }

        @NotNull
        public final ChuckerInterceptor build() {
            Context context = this.context;
            ChuckerCollector chuckerCollector = this.collector;
            ChuckerCollector chuckerCollector2 = chuckerCollector != null ? chuckerCollector : new ChuckerCollector(context, false, null, 6, null);
            long j2 = this.maxContentLength;
            CacheDirectoryProvider cacheDirectoryProvider = this.cacheDirectoryProvider;
            if (cacheDirectoryProvider == null) {
                cacheDirectoryProvider = new CacheDirectoryProvider() { // from class: com.chuckerteam.chucker.api.ChuckerInterceptor$Builder$build$1
                    @Override // com.chuckerteam.chucker.internal.support.CacheDirectoryProvider
                    @Nullable
                    public final File provide() {
                        Context context2;
                        context2 = ChuckerInterceptor.Builder.this.context;
                        return context2.getFilesDir();
                    }
                };
            }
            return new ChuckerInterceptor(context, chuckerCollector2, j2, cacheDirectoryProvider, this.alwaysReadResponseBody, this.headersToRedact);
        }

        @NotNull
        public final Builder collector(@NotNull ChuckerCollector collector) {
            Intrinsics.checkNotNullParameter(collector, "collector");
            this.collector = collector;
            return this;
        }

        @NotNull
        public final Builder maxContentLength(long j2) {
            this.maxContentLength = j2;
            return this;
        }

        @NotNull
        public final Builder redactHeaders(@NotNull Iterable<String> headerNames) {
            Set<String> set;
            Intrinsics.checkNotNullParameter(headerNames, "headerNames");
            set = CollectionsKt___CollectionsKt.toSet(headerNames);
            this.headersToRedact = set;
            return this;
        }

        @NotNull
        public final Builder redactHeaders(@NotNull String... headerNames) {
            Set<String> set;
            Intrinsics.checkNotNullParameter(headerNames, "headerNames");
            set = ArraysKt___ArraysKt.toSet(headerNames);
            this.headersToRedact = set;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017J\u001a\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\n\u001a\u00020\tH\u0016J\u001a\u0010\u000f\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0016R\u0016\u0010\u0011\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00138\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/chuckerteam/chucker/api/ChuckerInterceptor$ChuckerTransactionReportingSinkCallback;", "Lcom/chuckerteam/chucker/internal/support/ReportingSink$Callback;", "Ljava/io/File;", "responseBody", "", "isGzipped", "Lokio/Buffer;", "readResponseBuffer", "file", "", "sourceByteCount", "", "onClosed", "Ljava/io/IOException;", "exception", "onFailure", "Lokhttp3/Response;", "response", "Lokhttp3/Response;", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "<init>", "(Lcom/chuckerteam/chucker/api/ChuckerInterceptor;Lokhttp3/Response;Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public final class ChuckerTransactionReportingSinkCallback implements ReportingSink.Callback {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ChuckerInterceptor f4853a;
        private final Response response;
        private final HttpTransaction transaction;

        public ChuckerTransactionReportingSinkCallback(@NotNull ChuckerInterceptor chuckerInterceptor, @NotNull Response response, HttpTransaction transaction) {
            Intrinsics.checkNotNullParameter(response, "response");
            Intrinsics.checkNotNullParameter(transaction, "transaction");
            this.f4853a = chuckerInterceptor;
            this.response = response;
            this.transaction = transaction;
        }

        private final Buffer readResponseBuffer(File file, boolean z) {
            try {
                Source buffer = Okio.buffer(Okio.source(file));
                if (z) {
                    buffer = new GzipSource(buffer);
                }
                Buffer buffer2 = new Buffer();
                buffer2.writeAll(buffer);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(buffer, null);
                return buffer2;
            } catch (IOException e2) {
                new IOException("Response payload couldn't be processed by Chucker", e2).printStackTrace();
                return null;
            }
        }

        @Override // com.chuckerteam.chucker.internal.support.ReportingSink.Callback
        public void onClosed(@Nullable File file, long j2) {
            Buffer readResponseBuffer;
            if (file != null && (readResponseBuffer = readResponseBuffer(file, OkHttpUtilsKt.isGzipped(this.response))) != null) {
                this.f4853a.processResponseBody(this.response, readResponseBuffer, this.transaction);
            }
            this.transaction.setResponsePayloadSize(Long.valueOf(j2));
            this.f4853a.collector.onResponseReceived$com_github_ChuckerTeam_Chucker_library(this.transaction);
            if (file != null) {
                file.delete();
            }
        }

        @Override // com.chuckerteam.chucker.internal.support.ReportingSink.Callback
        public void onFailure(@Nullable File file, @NotNull IOException exception) {
            Intrinsics.checkNotNullParameter(exception, "exception");
            exception.printStackTrace();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u00020\u00068\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\t\u0010\bR\u001e\u0010\f\u001a\n \u000b*\u0004\u0018\u00010\n0\n8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/chuckerteam/chucker/api/ChuckerInterceptor$Companion;", "", "", "CONTENT_ENCODING", "Ljava/lang/String;", "CONTENT_TYPE_IMAGE", "", "MAX_BLOB_SIZE", "J", "MAX_CONTENT_LENGTH", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "UTF8", "Ljava/nio/charset/Charset;", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(message = "Customisation of ChuckerInterceptor should be replaced with a builder pattern unless you pass only Context.", replaceWith = @ReplaceWith(expression = "ChuckerInterceptor.Builder(context)\n.collector(collector)\n.maxContentLength(maxContentLength)\n.redactHeaders(headersToRedact)\n.alwaysReadResponseBody(alwaysReadResponseBody)\n.build()", imports = {}))
    @JvmOverloads
    public ChuckerInterceptor(@NotNull Context context) {
        this(context, null, 0L, null, false, 30, null);
    }

    @Deprecated(message = "Customisation of ChuckerInterceptor should be replaced with a builder pattern unless you pass only Context.", replaceWith = @ReplaceWith(expression = "ChuckerInterceptor.Builder(context)\n.collector(collector)\n.maxContentLength(maxContentLength)\n.redactHeaders(headersToRedact)\n.alwaysReadResponseBody(alwaysReadResponseBody)\n.build()", imports = {}))
    @JvmOverloads
    public ChuckerInterceptor(@NotNull Context context, @NotNull ChuckerCollector chuckerCollector) {
        this(context, chuckerCollector, 0L, null, false, 28, null);
    }

    @Deprecated(message = "Customisation of ChuckerInterceptor should be replaced with a builder pattern unless you pass only Context.", replaceWith = @ReplaceWith(expression = "ChuckerInterceptor.Builder(context)\n.collector(collector)\n.maxContentLength(maxContentLength)\n.redactHeaders(headersToRedact)\n.alwaysReadResponseBody(alwaysReadResponseBody)\n.build()", imports = {}))
    @JvmOverloads
    public ChuckerInterceptor(@NotNull Context context, @NotNull ChuckerCollector chuckerCollector, long j2) {
        this(context, chuckerCollector, j2, null, false, 24, null);
    }

    public ChuckerInterceptor(@NotNull Context context, @NotNull ChuckerCollector collector, long j2, @NotNull CacheDirectoryProvider cacheDirectoryProvider, boolean z, @NotNull Set<String> headersToRedact) {
        Set<String> mutableSet;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(collector, "collector");
        Intrinsics.checkNotNullParameter(cacheDirectoryProvider, "cacheDirectoryProvider");
        Intrinsics.checkNotNullParameter(headersToRedact, "headersToRedact");
        this.context = context;
        this.collector = collector;
        this.maxContentLength = j2;
        this.cacheDirectoryProvider = cacheDirectoryProvider;
        this.alwaysReadResponseBody = z;
        this.f4850io = new IOUtils(context);
        mutableSet = CollectionsKt___CollectionsKt.toMutableSet(headersToRedact);
        this.headersToRedact = mutableSet;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ ChuckerInterceptor(Context context, ChuckerCollector chuckerCollector, long j2, CacheDirectoryProvider cacheDirectoryProvider, boolean z, Set set, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, r9, r10, cacheDirectoryProvider, r13, r14);
        Set set2;
        Set emptySet;
        ChuckerCollector chuckerCollector2 = (i2 & 2) != 0 ? new ChuckerCollector(context, false, null, 6, null) : chuckerCollector;
        long j3 = (i2 & 4) != 0 ? 250000L : j2;
        boolean z2 = (i2 & 16) != 0 ? false : z;
        if ((i2 & 32) != 0) {
            emptySet = SetsKt__SetsKt.emptySet();
            set2 = emptySet;
        } else {
            set2 = set;
        }
    }

    @Deprecated(message = "Customisation of ChuckerInterceptor should be replaced with a builder pattern unless you pass only Context.", replaceWith = @ReplaceWith(expression = "ChuckerInterceptor.Builder(context)\n.collector(collector)\n.maxContentLength(maxContentLength)\n.redactHeaders(headersToRedact)\n.alwaysReadResponseBody(alwaysReadResponseBody)\n.build()", imports = {}))
    @JvmOverloads
    public ChuckerInterceptor(@NotNull Context context, @NotNull ChuckerCollector chuckerCollector, long j2, @NotNull Set<String> set) {
        this(context, chuckerCollector, j2, set, false, 16, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "Customisation of ChuckerInterceptor should be replaced with a builder pattern unless you pass only Context.", replaceWith = @ReplaceWith(expression = "ChuckerInterceptor.Builder(context)\n.collector(collector)\n.maxContentLength(maxContentLength)\n.redactHeaders(headersToRedact)\n.alwaysReadResponseBody(alwaysReadResponseBody)\n.build()", imports = {}))
    @JvmOverloads
    public ChuckerInterceptor(@NotNull final Context context, @NotNull ChuckerCollector collector, long j2, @NotNull Set<String> headersToRedact, boolean z) {
        this(context, collector, j2, new CacheDirectoryProvider() { // from class: com.chuckerteam.chucker.api.ChuckerInterceptor.1
            @Override // com.chuckerteam.chucker.internal.support.CacheDirectoryProvider
            @Nullable
            public final File provide() {
                return context.getCacheDir();
            }
        }, z, headersToRedact);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(collector, "collector");
        Intrinsics.checkNotNullParameter(headersToRedact, "headersToRedact");
    }

    public /* synthetic */ ChuckerInterceptor(Context context, ChuckerCollector chuckerCollector, long j2, Set set, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? new ChuckerCollector(context, false, null, 6, null) : chuckerCollector, (i2 & 4) != 0 ? MAX_CONTENT_LENGTH : j2, (i2 & 8) != 0 ? SetsKt__SetsKt.emptySet() : set, (i2 & 16) != 0 ? false : z);
    }

    private final File createTempTransactionFile() {
        File provide = this.cacheDirectoryProvider.provide();
        if (provide == null) {
            new IOException("Failed to obtain a valid cache directory for Chucker transaction file").printStackTrace();
            return null;
        }
        return FileFactory.INSTANCE.create(provide);
    }

    private final Headers filterHeaders(Headers headers) {
        boolean equals;
        Headers.Builder newBuilder = headers.newBuilder();
        for (String str : headers.names()) {
            Set<String> set = this.headersToRedact;
            boolean z = true;
            if (!(set instanceof Collection) || !set.isEmpty()) {
                for (String str2 : set) {
                    equals = StringsKt__StringsJVMKt.equals(str2, str, true);
                    if (equals) {
                        break;
                    }
                }
            }
            z = false;
            if (z) {
                newBuilder.set(str, "**");
            }
        }
        Headers build = newBuilder.build();
        Intrinsics.checkNotNullExpressionValue(build, "builder.build()");
        return build;
    }

    private final Response multiCastResponseBody(Response response, HttpTransaction httpTransaction) {
        ResponseBody body = response.body();
        if (!OkHttpUtilsKt.hasBody(response) || body == null) {
            this.collector.onResponseReceived$com_github_ChuckerTeam_Chucker_library(httpTransaction);
            return response;
        }
        MediaType contentType = body.contentType();
        long contentLength = body.contentLength();
        ReportingSink reportingSink = new ReportingSink(createTempTransactionFile(), new ChuckerTransactionReportingSinkCallback(this, response, httpTransaction), this.maxContentLength);
        BufferedSource source = body.source();
        Intrinsics.checkNotNullExpressionValue(source, "responseBody.source()");
        Source teeSource = new TeeSource(source, reportingSink);
        if (this.alwaysReadResponseBody) {
            teeSource = new DepletingSource(teeSource);
        }
        Response build = response.newBuilder().body(ResponseBody.create(contentType, contentLength, Okio.buffer(teeSource))).build();
        Intrinsics.checkNotNullExpressionValue(build, "response.newBuilder()\n  …m)))\n            .build()");
        return build;
    }

    private final void processRequest(Request request, HttpTransaction httpTransaction) {
        MediaType contentType;
        RequestBody body = request.body();
        boolean bodyHasSupportedEncoding = this.f4850io.bodyHasSupportedEncoding(request.headers().get("Content-Encoding"));
        Headers headers = request.headers();
        Intrinsics.checkNotNullExpressionValue(headers, "request.headers()");
        httpTransaction.setRequestHeaders(headers);
        HttpUrl url = request.url();
        Intrinsics.checkNotNullExpressionValue(url, "request.url()");
        httpTransaction.populateUrl(url);
        httpTransaction.setRequestBodyPlainText(bodyHasSupportedEncoding);
        httpTransaction.setRequestDate(Long.valueOf(System.currentTimeMillis()));
        httpTransaction.setMethod(request.method());
        httpTransaction.setRequestContentType((body == null || (contentType = body.contentType()) == null) ? null : contentType.toString());
        httpTransaction.setRequestPayloadSize(Long.valueOf(body != null ? body.contentLength() : 0L));
        if (body == null || !bodyHasSupportedEncoding) {
            return;
        }
        Buffer buffer = this.f4850io.getNativeSource(new Buffer(), OkHttpUtilsKt.isGzipped(request)).buffer();
        body.writeTo(buffer);
        Charset UTF82 = UTF8;
        Intrinsics.checkNotNullExpressionValue(UTF82, "UTF8");
        MediaType contentType2 = body.contentType();
        if (contentType2 != null) {
            Charset charset = contentType2.charset(UTF82);
            if (charset != null) {
                UTF82 = charset;
            } else {
                Intrinsics.checkNotNullExpressionValue(UTF82, "UTF8");
            }
        }
        IOUtils iOUtils = this.f4850io;
        Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
        if (iOUtils.isPlaintext(buffer)) {
            httpTransaction.setRequestBody(this.f4850io.readFromBuffer(buffer, UTF82, this.maxContentLength));
        } else {
            httpTransaction.setResponseBodyPlainText(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004c, code lost:
        if (r6 == true) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void processResponseBody(Response response, Buffer buffer, HttpTransaction httpTransaction) {
        Charset charset;
        String mediaType;
        boolean contains;
        ResponseBody body = response.body();
        if (body != null) {
            Intrinsics.checkNotNullExpressionValue(body, "response.body() ?: return");
            MediaType contentType = body.contentType();
            if (contentType == null || (charset = contentType.charset(UTF8)) == null) {
                charset = UTF8;
            }
            boolean z = true;
            if (this.f4850io.isPlaintext(buffer)) {
                httpTransaction.setResponseBodyPlainText(true);
                if (buffer.size() != 0) {
                    httpTransaction.setResponseBody(buffer.readString(charset));
                    return;
                }
                return;
            }
            httpTransaction.setResponseBodyPlainText(false);
            if (contentType != null && (mediaType = contentType.toString()) != null) {
                contains = StringsKt__StringsKt.contains((CharSequence) mediaType, (CharSequence) CONTENT_TYPE_IMAGE, true);
            }
            z = false;
            if (!z || buffer.size() >= MAX_BLOB_SIZE) {
                return;
            }
            httpTransaction.setResponseImageData(buffer.readByteArray());
        }
    }

    private final void processResponseMetadata(Response response, HttpTransaction httpTransaction) {
        boolean bodyHasSupportedEncoding = this.f4850io.bodyHasSupportedEncoding(response.headers().get("Content-Encoding"));
        Headers headers = response.request().headers();
        Intrinsics.checkNotNullExpressionValue(headers, "response.request().headers()");
        httpTransaction.setRequestHeaders(filterHeaders(headers));
        Headers headers2 = response.headers();
        Intrinsics.checkNotNullExpressionValue(headers2, "response.headers()");
        httpTransaction.setResponseHeaders(filterHeaders(headers2));
        httpTransaction.setResponseBodyPlainText(bodyHasSupportedEncoding);
        httpTransaction.setRequestDate(Long.valueOf(response.sentRequestAtMillis()));
        httpTransaction.setResponseDate(Long.valueOf(response.receivedResponseAtMillis()));
        httpTransaction.setProtocol(response.protocol().toString());
        httpTransaction.setResponseCode(Integer.valueOf(response.code()));
        httpTransaction.setResponseMessage(response.message());
        Handshake handshake = response.handshake();
        if (handshake != null) {
            httpTransaction.setResponseTlsVersion(handshake.tlsVersion().javaName());
            httpTransaction.setResponseCipherSuite(handshake.cipherSuite().javaName());
        }
        httpTransaction.setResponseContentType(OkHttpUtilsKt.getContentType(response));
        httpTransaction.setTookMs(Long.valueOf(response.receivedResponseAtMillis() - response.sentRequestAtMillis()));
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        HttpTransaction httpTransaction = new HttpTransaction();
        Intrinsics.checkNotNullExpressionValue(request, "request");
        processRequest(request, httpTransaction);
        this.collector.onRequestSent$com_github_ChuckerTeam_Chucker_library(httpTransaction);
        try {
            Response proceed = chain.proceed(request);
            Intrinsics.checkNotNullExpressionValue(proceed, "chain.proceed(request)");
            processResponseMetadata(proceed, httpTransaction);
            return multiCastResponseBody(proceed, httpTransaction);
        } catch (IOException e2) {
            httpTransaction.setError(e2.toString());
            this.collector.onResponseReceived$com_github_ChuckerTeam_Chucker_library(httpTransaction);
            throw e2;
        }
    }

    public final void redactHeader(@NotNull String... headerName) {
        Intrinsics.checkNotNullParameter(headerName, "headerName");
        CollectionsKt__MutableCollectionsKt.addAll(this.headersToRedact, headerName);
    }
}
