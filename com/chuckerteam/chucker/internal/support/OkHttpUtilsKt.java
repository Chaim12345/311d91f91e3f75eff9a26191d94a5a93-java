package com.chuckerteam.chucker.internal.support;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.protocol.HTTP;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\f\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0000\"\u0016\u0010\u0004\u001a\u00020\u00038\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0004\u0010\u0005\"\u001a\u0010\t\u001a\u00020\u0006*\u00020\u00008B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u001a\u0010\n\u001a\u00020\u0001*\u00020\u00008@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u001c\u0010\u000f\u001a\u0004\u0018\u00010\f*\u00020\u00008@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"\u001a\u0010\u0010\u001a\u00020\u0001*\u00020\u00008@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000b\"\u001a\u0010\u0010\u001a\u00020\u0001*\u00020\u00118@@\u0000X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0012\"\u001a\u0010\u0016\u001a\u00020\u0001*\u00020\u00138B@\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lokhttp3/Response;", "", "hasBody", "", "HTTP_CONTINUE", "I", "", "getContentLength", "(Lokhttp3/Response;)J", "contentLength", "isChunked", "(Lokhttp3/Response;)Z", "", "getContentType", "(Lokhttp3/Response;)Ljava/lang/String;", CMSAttributeTableGenerator.CONTENT_TYPE, "isGzipped", "Lokhttp3/Request;", "(Lokhttp3/Request;)Z", "Lokhttp3/Headers;", "getContainsGzip", "(Lokhttp3/Headers;)Z", "containsGzip", "com.github.ChuckerTeam.Chucker.library"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class OkHttpUtilsKt {
    private static final int HTTP_CONTINUE = 100;

    private static final boolean getContainsGzip(Headers headers) {
        boolean equals;
        equals = StringsKt__StringsJVMKt.equals(headers.get("Content-Encoding"), "gzip", true);
        return equals;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0008, code lost:
        r2 = kotlin.text.StringsKt__StringNumberConversionsKt.toLongOrNull(r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final long getContentLength(Response response) {
        Long longOrNull;
        String header = response.header("Content-Length");
        if (header == null || longOrNull == null) {
            return -1L;
        }
        return longOrNull.longValue();
    }

    @Nullable
    public static final String getContentType(@NotNull Response contentType) {
        Intrinsics.checkNotNullParameter(contentType, "$this$contentType");
        return contentType.header("Content-Type");
    }

    public static final boolean hasBody(@NotNull Response hasBody) {
        Intrinsics.checkNotNullParameter(hasBody, "$this$hasBody");
        if (Intrinsics.areEqual(hasBody.request().method(), "HEAD")) {
            return false;
        }
        int code = hasBody.code();
        if ((code >= 100 && code < 200) || code == 204 || code == 304) {
            return getContentLength(hasBody) > 0 || isChunked(hasBody);
        }
        return true;
    }

    public static final boolean isChunked(@NotNull Response isChunked) {
        boolean equals;
        Intrinsics.checkNotNullParameter(isChunked, "$this$isChunked");
        equals = StringsKt__StringsJVMKt.equals(isChunked.header("Transfer-Encoding"), HTTP.CHUNK_CODING, true);
        return equals;
    }

    public static final boolean isGzipped(@NotNull Request isGzipped) {
        Intrinsics.checkNotNullParameter(isGzipped, "$this$isGzipped");
        Headers headers = isGzipped.headers();
        Intrinsics.checkNotNullExpressionValue(headers, "this.headers()");
        return getContainsGzip(headers);
    }

    public static final boolean isGzipped(@NotNull Response isGzipped) {
        Intrinsics.checkNotNullParameter(isGzipped, "$this$isGzipped");
        Headers headers = isGzipped.headers();
        Intrinsics.checkNotNullExpressionValue(headers, "this.headers()");
        return getContainsGzip(headers);
    }
}
