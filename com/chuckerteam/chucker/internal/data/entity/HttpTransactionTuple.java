package com.chuckerteam.chucker.internal.data.entity;

import androidx.core.app.NotificationCompat;
import androidx.room.ColumnInfo;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import com.chuckerteam.chucker.internal.support.FormatUtils;
import com.chuckerteam.chucker.internal.support.FormattedUrl;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okhttp3.HttpUrl;
import org.apache.http.cookie.ClientCookie;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b#\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001B}\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010$\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010'\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010+\u001a\u0004\u0018\u00010*\u0012\b\u00101\u001a\u0004\u0018\u00010\u0002\u0012\b\u00104\u001a\u0004\u0018\u00010\u0002\u0012\b\u00107\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\bD\u0010EJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006R\"\u0010\t\u001a\u00020\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R$\u0010\u0018\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR$\u0010\u001e\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u0019\u001a\u0004\b\u001f\u0010\u001b\"\u0004\b \u0010\u001dR$\u0010!\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b!\u0010\u0019\u001a\u0004\b\"\u0010\u001b\"\u0004\b#\u0010\u001dR$\u0010$\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b$\u0010\u0019\u001a\u0004\b%\u0010\u001b\"\u0004\b&\u0010\u001dR$\u0010'\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b'\u0010\u0019\u001a\u0004\b(\u0010\u001b\"\u0004\b)\u0010\u001dR$\u0010+\u001a\u0004\u0018\u00010*8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R$\u00101\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b1\u0010\u0010\u001a\u0004\b2\u0010\u0012\"\u0004\b3\u0010\u0014R$\u00104\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b4\u0010\u0010\u001a\u0004\b5\u0010\u0012\"\u0004\b6\u0010\u0014R$\u00107\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b7\u0010\u0019\u001a\u0004\b8\u0010\u001b\"\u0004\b9\u0010\u001dR\u0013\u0010:\u001a\u00020\u00068F@\u0006¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0013\u0010?\u001a\u00020<8F@\u0006¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0015\u0010A\u001a\u0004\u0018\u00010\u00048F@\u0006¢\u0006\u0006\u001a\u0004\b@\u0010\u001bR\u0013\u0010C\u001a\u00020\u00048F@\u0006¢\u0006\u0006\u001a\u0004\bB\u0010\u001b¨\u0006F"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/entity/HttpTransactionTuple;", "", "", "bytes", "", "formatBytes", "", "encode", "getFormattedPath", "id", "J", "getId", "()J", "setId", "(J)V", "requestDate", "Ljava/lang/Long;", "getRequestDate", "()Ljava/lang/Long;", "setRequestDate", "(Ljava/lang/Long;)V", "tookMs", "getTookMs", "setTookMs", "protocol", "Ljava/lang/String;", "getProtocol", "()Ljava/lang/String;", "setProtocol", "(Ljava/lang/String;)V", FirebaseAnalytics.Param.METHOD, "getMethod", "setMethod", "host", "getHost", "setHost", ClientCookie.PATH_ATTR, "getPath", "setPath", "scheme", "getScheme", "setScheme", "", "responseCode", "Ljava/lang/Integer;", "getResponseCode", "()Ljava/lang/Integer;", "setResponseCode", "(Ljava/lang/Integer;)V", "requestPayloadSize", "getRequestPayloadSize", "setRequestPayloadSize", "responsePayloadSize", "getResponsePayloadSize", "setResponsePayloadSize", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "getError", "setError", "isSsl", "()Z", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction$Status;", "getStatus", "()Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction$Status;", NotificationCompat.CATEGORY_STATUS, "getDurationString", "durationString", "getTotalSizeString", "totalSizeString", "<init>", "(JLjava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class HttpTransactionTuple {
    @ColumnInfo(name = Constants.IPC_BUNDLE_KEY_SEND_ERROR)
    @Nullable
    private String error;
    @ColumnInfo(name = "host")
    @Nullable
    private String host;
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = FirebaseAnalytics.Param.METHOD)
    @Nullable
    private String method;
    @ColumnInfo(name = ClientCookie.PATH_ATTR)
    @Nullable
    private String path;
    @ColumnInfo(name = "protocol")
    @Nullable
    private String protocol;
    @ColumnInfo(name = "requestDate")
    @Nullable
    private Long requestDate;
    @ColumnInfo(name = "requestPayloadSize")
    @Nullable
    private Long requestPayloadSize;
    @ColumnInfo(name = "responseCode")
    @Nullable
    private Integer responseCode;
    @ColumnInfo(name = "responsePayloadSize")
    @Nullable
    private Long responsePayloadSize;
    @ColumnInfo(name = "scheme")
    @Nullable
    private String scheme;
    @ColumnInfo(name = "tookMs")
    @Nullable
    private Long tookMs;

    public HttpTransactionTuple(long j2, @Nullable Long l2, @Nullable Long l3, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Integer num, @Nullable Long l4, @Nullable Long l5, @Nullable String str6) {
        this.id = j2;
        this.requestDate = l2;
        this.tookMs = l3;
        this.protocol = str;
        this.method = str2;
        this.host = str3;
        this.path = str4;
        this.scheme = str5;
        this.responseCode = num;
        this.requestPayloadSize = l4;
        this.responsePayloadSize = l5;
        this.error = str6;
    }

    private final String formatBytes(long j2) {
        return FormatUtils.INSTANCE.formatByteCount(j2, true);
    }

    @Nullable
    public final String getDurationString() {
        Long l2 = this.tookMs;
        if (l2 != null) {
            long longValue = l2.longValue();
            return longValue + " ms";
        }
        return null;
    }

    @Nullable
    public final String getError() {
        return this.error;
    }

    @NotNull
    public final String getFormattedPath(boolean z) {
        String str = this.path;
        if (str != null) {
            HttpUrl parse = HttpUrl.parse("https://www.example.com" + str);
            if (parse != null) {
                Intrinsics.checkNotNullExpressionValue(parse, "HttpUrl.parse(dummyUrl) ?: return \"\"");
                return FormattedUrl.Companion.fromHttpUrl(parse, z).getPathWithQuery();
            }
        }
        return "";
    }

    @Nullable
    public final String getHost() {
        return this.host;
    }

    public final long getId() {
        return this.id;
    }

    @Nullable
    public final String getMethod() {
        return this.method;
    }

    @Nullable
    public final String getPath() {
        return this.path;
    }

    @Nullable
    public final String getProtocol() {
        return this.protocol;
    }

    @Nullable
    public final Long getRequestDate() {
        return this.requestDate;
    }

    @Nullable
    public final Long getRequestPayloadSize() {
        return this.requestPayloadSize;
    }

    @Nullable
    public final Integer getResponseCode() {
        return this.responseCode;
    }

    @Nullable
    public final Long getResponsePayloadSize() {
        return this.responsePayloadSize;
    }

    @Nullable
    public final String getScheme() {
        return this.scheme;
    }

    @NotNull
    public final HttpTransaction.Status getStatus() {
        return this.error != null ? HttpTransaction.Status.Failed : this.responseCode == null ? HttpTransaction.Status.Requested : HttpTransaction.Status.Complete;
    }

    @Nullable
    public final Long getTookMs() {
        return this.tookMs;
    }

    @NotNull
    public final String getTotalSizeString() {
        Long l2 = this.requestPayloadSize;
        long longValue = l2 != null ? l2.longValue() : 0L;
        Long l3 = this.responsePayloadSize;
        return formatBytes(longValue + (l3 != null ? l3.longValue() : 0L));
    }

    public final boolean isSsl() {
        boolean equals;
        equals = StringsKt__StringsJVMKt.equals(this.scheme, "https", true);
        return equals;
    }

    public final void setError(@Nullable String str) {
        this.error = str;
    }

    public final void setHost(@Nullable String str) {
        this.host = str;
    }

    public final void setId(long j2) {
        this.id = j2;
    }

    public final void setMethod(@Nullable String str) {
        this.method = str;
    }

    public final void setPath(@Nullable String str) {
        this.path = str;
    }

    public final void setProtocol(@Nullable String str) {
        this.protocol = str;
    }

    public final void setRequestDate(@Nullable Long l2) {
        this.requestDate = l2;
    }

    public final void setRequestPayloadSize(@Nullable Long l2) {
        this.requestPayloadSize = l2;
    }

    public final void setResponseCode(@Nullable Integer num) {
        this.responseCode = num;
    }

    public final void setResponsePayloadSize(@Nullable Long l2) {
        this.responsePayloadSize = l2;
    }

    public final void setScheme(@Nullable String str) {
        this.scheme = str;
    }

    public final void setTookMs(@Nullable Long l2) {
        this.tookMs = l2;
    }
}
