package com.chuckerteam.chucker.internal.support;

import com.fasterxml.jackson.core.JsonPointer;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okhttp3.HttpUrl;
import org.apache.http.HttpHost;
import org.apache.http.cookie.ClientCookie;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0010\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB1\b\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\u0010\u001a\u00020\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\b\u0010\u0003\u001a\u00020\u0002H\u0002R\u0019\u0010\u0005\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\t\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\bR\u0019\u0010\f\u001a\u00020\u000b8\u0006@\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u0010\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0006\u001a\u0004\b\u0011\u0010\bR\u0019\u0010\u0012\u001a\u00020\u00048\u0006@\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0006\u001a\u0004\b\u0013\u0010\bR\u0013\u0010\u0015\u001a\u00020\u00048F@\u0006¢\u0006\u0006\u001a\u0004\b\u0014\u0010\bR\u0013\u0010\u0017\u001a\u00020\u00048F@\u0006¢\u0006\u0006\u001a\u0004\b\u0016\u0010\b¨\u0006\u001b"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/FormattedUrl;", "", "", "shouldShowPort", "", "scheme", "Ljava/lang/String;", "getScheme", "()Ljava/lang/String;", "host", "getHost", "", ClientCookie.PORT_ATTR, "I", "getPort", "()I", ClientCookie.PATH_ATTR, "getPath", SearchIntents.EXTRA_QUERY, "getQuery", "getPathWithQuery", "pathWithQuery", "getUrl", ImagesContract.URL, "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "Companion", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class FormattedUrl {
    public static final Companion Companion = new Companion(null);
    private static final int HTTPS_PORT = 443;
    private static final int HTTP_PORT = 80;
    @NotNull
    private final String host;
    @NotNull
    private final String path;
    private final int port;
    @NotNull
    private final String query;
    @NotNull
    private final String scheme;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007R\u0016\u0010\u000b\u001a\u00020\n8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u00020\n8\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/FormattedUrl$Companion;", "", "Lokhttp3/HttpUrl;", "httpUrl", "Lcom/chuckerteam/chucker/internal/support/FormattedUrl;", "encodedUrl", "decodedUrl", "", "encoded", "fromHttpUrl", "", "HTTPS_PORT", "I", "HTTP_PORT", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final FormattedUrl decodedUrl(HttpUrl httpUrl) {
            String joinToString$default;
            boolean isBlank;
            String str;
            List<String> pathSegments = httpUrl.pathSegments();
            Intrinsics.checkNotNullExpressionValue(pathSegments, "httpUrl.pathSegments()");
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(pathSegments, "/", null, null, 0, null, null, 62, null);
            String scheme = httpUrl.scheme();
            Intrinsics.checkNotNullExpressionValue(scheme, "httpUrl.scheme()");
            String host = httpUrl.host();
            Intrinsics.checkNotNullExpressionValue(host, "httpUrl.host()");
            int port = httpUrl.port();
            isBlank = StringsKt__StringsJVMKt.isBlank(joinToString$default);
            if (!isBlank) {
                str = JsonPointer.SEPARATOR + joinToString$default;
            } else {
                str = "";
            }
            String query = httpUrl.query();
            return new FormattedUrl(scheme, host, port, str, query != null ? query : "", null);
        }

        private final FormattedUrl encodedUrl(HttpUrl httpUrl) {
            String joinToString$default;
            boolean isBlank;
            String str;
            List<String> encodedPathSegments = httpUrl.encodedPathSegments();
            Intrinsics.checkNotNullExpressionValue(encodedPathSegments, "httpUrl.encodedPathSegments()");
            joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(encodedPathSegments, "/", null, null, 0, null, null, 62, null);
            String scheme = httpUrl.scheme();
            Intrinsics.checkNotNullExpressionValue(scheme, "httpUrl.scheme()");
            String host = httpUrl.host();
            Intrinsics.checkNotNullExpressionValue(host, "httpUrl.host()");
            int port = httpUrl.port();
            isBlank = StringsKt__StringsJVMKt.isBlank(joinToString$default);
            if (!isBlank) {
                str = JsonPointer.SEPARATOR + joinToString$default;
            } else {
                str = "";
            }
            String encodedQuery = httpUrl.encodedQuery();
            return new FormattedUrl(scheme, host, port, str, encodedQuery != null ? encodedQuery : "", null);
        }

        @NotNull
        public final FormattedUrl fromHttpUrl(@NotNull HttpUrl httpUrl, boolean z) {
            Intrinsics.checkNotNullParameter(httpUrl, "httpUrl");
            return z ? encodedUrl(httpUrl) : decodedUrl(httpUrl);
        }
    }

    private FormattedUrl(String str, String str2, int i2, String str3, String str4) {
        this.scheme = str;
        this.host = str2;
        this.port = i2;
        this.path = str3;
        this.query = str4;
    }

    public /* synthetic */ FormattedUrl(String str, String str2, int i2, String str3, String str4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i2, str3, str4);
    }

    private final boolean shouldShowPort() {
        if (Intrinsics.areEqual(this.scheme, "https") && this.port == HTTPS_PORT) {
            return false;
        }
        return (Intrinsics.areEqual(this.scheme, HttpHost.DEFAULT_SCHEME_NAME) && this.port == 80) ? false : true;
    }

    @NotNull
    public final String getHost() {
        return this.host;
    }

    @NotNull
    public final String getPath() {
        return this.path;
    }

    @NotNull
    public final String getPathWithQuery() {
        boolean isBlank;
        isBlank = StringsKt__StringsJVMKt.isBlank(this.query);
        if (isBlank) {
            return this.path;
        }
        return this.path + '?' + this.query;
    }

    public final int getPort() {
        return this.port;
    }

    @NotNull
    public final String getQuery() {
        return this.query;
    }

    @NotNull
    public final String getScheme() {
        return this.scheme;
    }

    @NotNull
    public final String getUrl() {
        StringBuilder sb;
        if (shouldShowPort()) {
            sb = new StringBuilder();
            sb.append(this.scheme);
            sb.append("://");
            sb.append(this.host);
            sb.append(AbstractJsonLexerKt.COLON);
            sb.append(this.port);
        } else {
            sb = new StringBuilder();
            sb.append(this.scheme);
            sb.append("://");
            sb.append(this.host);
        }
        sb.append(getPathWithQuery());
        return sb.toString();
    }
}
