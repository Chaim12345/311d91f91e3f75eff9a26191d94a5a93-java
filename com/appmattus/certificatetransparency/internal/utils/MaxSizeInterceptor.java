package com.appmattus.certificatetransparency.internal.utils;

import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class MaxSizeInterceptor implements Interceptor {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String HEADER = "Max-Size";

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x002d, code lost:
        r0 = kotlin.text.StringsKt__StringNumberConversionsKt.toLongOrNull(r0);
     */
    @Override // okhttp3.Interceptor
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Response intercept(@NotNull Interceptor.Chain chain) {
        Object firstOrNull;
        Long longOrNull;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        Request.Builder newBuilder = request.newBuilder();
        newBuilder.removeHeader(HEADER);
        Response proceed = chain.proceed(newBuilder.build());
        ResponseBody body = proceed.body();
        Intrinsics.checkNotNull(body);
        firstOrNull = CollectionsKt___CollectionsKt.firstOrNull((List<? extends Object>) request.headers(HEADER));
        String str = (String) firstOrNull;
        if (str == null || longOrNull == null) {
            return proceed;
        }
        Response build = proceed.newBuilder().body(ResponseBody.Companion.create(Okio.buffer(Okio.source(new LimitedSizeInputStream(body.byteStream(), longOrNull.longValue()))), body.contentType(), body.contentLength())).build();
        return build == null ? proceed : build;
    }
}
