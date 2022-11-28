package com.chuckerteam.chucker.internal.data.entity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.chuckerteam.chucker.internal.support.FormatUtils;
import com.chuckerteam.chucker.internal.support.FormattedUrl;
import com.chuckerteam.chucker.internal.support.JsonConverter;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import org.apache.http.cookie.ClientCookie;
import org.apache.http.message.TokenParser;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(tableName = "transactions")
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\bD\n\u0002\u0010\b\n\u0002\b\u0019\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u00002\u00020\u0001:\u0002\u009b\u0001B\u008d\u0002\u0012\b\b\u0002\u0010!\u001a\u00020\u000b\u0012\b\u0010'\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010-\u001a\u0004\u0018\u00010\u000b\u0012\b\u00100\u001a\u0004\u0018\u00010\u000b\u0012\b\u00103\u001a\u0004\u0018\u00010\u0007\u0012\b\u00109\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010<\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010?\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010B\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010E\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010H\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010K\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010N\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010Q\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010T\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010V\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010Y\u001a\u00020\u0012\u0012\b\u0010_\u001a\u0004\u0018\u00010^\u0012\b\u0010e\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010h\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010k\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010q\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010s\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010v\u001a\u00020\u0012\u0012\b\u0010y\u001a\u0004\u0018\u00010x¢\u0006\u0006\b\u0098\u0001\u0010\u0099\u0001B\u000b\b\u0017¢\u0006\u0006\b\u0098\u0001\u0010\u009a\u0001J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u001a\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\u0007H\u0002J\u0010\u0010\r\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u000bH\u0002J\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0002J\u0014\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J\u000e\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u0002J\u0014\u0010\u0015\u001a\u00020\u000e2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J\u000e\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0012J\u0006\u0010\u0017\u001a\u00020\u0007J\u0006\u0010\u0018\u001a\u00020\u0007J\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0019J\u000e\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0012J\u000e\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0012J\u0010\u0010 \u001a\u00020\u00122\b\u0010\u001f\u001a\u0004\u0018\u00010\u0000R\"\u0010!\u001a\u00020\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010'\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R$\u0010-\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b-\u0010(\u001a\u0004\b.\u0010*\"\u0004\b/\u0010,R$\u00100\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b0\u0010(\u001a\u0004\b1\u0010*\"\u0004\b2\u0010,R$\u00103\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R$\u00109\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b9\u00104\u001a\u0004\b:\u00106\"\u0004\b;\u00108R$\u0010<\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b<\u00104\u001a\u0004\b=\u00106\"\u0004\b>\u00108R$\u0010?\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b?\u00104\u001a\u0004\b@\u00106\"\u0004\bA\u00108R$\u0010B\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bB\u00104\u001a\u0004\bC\u00106\"\u0004\bD\u00108R$\u0010E\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bE\u00104\u001a\u0004\bF\u00106\"\u0004\bG\u00108R$\u0010H\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bH\u00104\u001a\u0004\bI\u00106\"\u0004\bJ\u00108R$\u0010K\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bK\u00104\u001a\u0004\bL\u00106\"\u0004\bM\u00108R$\u0010N\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bN\u0010(\u001a\u0004\bO\u0010*\"\u0004\bP\u0010,R$\u0010Q\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bQ\u00104\u001a\u0004\bR\u00106\"\u0004\bS\u00108R$\u0010T\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bT\u00104\u001a\u0004\bU\u00106\"\u0004\b\u000f\u00108R$\u0010V\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bV\u00104\u001a\u0004\bW\u00106\"\u0004\bX\u00108R\"\u0010Y\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bY\u0010Z\u001a\u0004\bY\u0010[\"\u0004\b\\\u0010]R$\u0010_\u001a\u0004\u0018\u00010^8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\b_\u0010`\u001a\u0004\ba\u0010b\"\u0004\bc\u0010dR$\u0010e\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\be\u00104\u001a\u0004\bf\u00106\"\u0004\bg\u00108R$\u0010h\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bh\u00104\u001a\u0004\bi\u00106\"\u0004\bj\u00108R$\u0010k\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bk\u0010(\u001a\u0004\bl\u0010*\"\u0004\bm\u0010,R$\u0010n\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bn\u00104\u001a\u0004\bo\u00106\"\u0004\bp\u00108R$\u0010q\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bq\u00104\u001a\u0004\br\u00106\"\u0004\b\u0015\u00108R$\u0010s\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bs\u00104\u001a\u0004\bt\u00106\"\u0004\bu\u00108R\"\u0010v\u001a\u00020\u00128\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\bv\u0010Z\u001a\u0004\bv\u0010[\"\u0004\bw\u0010]R$\u0010y\u001a\u0004\u0018\u00010x8\u0006@\u0006X\u0087\u000e¢\u0006\u0012\n\u0004\by\u0010z\u001a\u0004\b{\u0010|\"\u0004\b}\u0010~R\u0016\u0010\u0082\u0001\u001a\u00020\u007f8F@\u0006¢\u0006\b\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0017\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00078F@\u0006¢\u0006\u0007\u001a\u0005\b\u0083\u0001\u00106R\u0017\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00078F@\u0006¢\u0006\u0007\u001a\u0005\b\u0085\u0001\u00106R\u0017\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00078F@\u0006¢\u0006\u0007\u001a\u0005\b\u0087\u0001\u00106R\u0015\u0010\u008a\u0001\u001a\u00020\u00078F@\u0006¢\u0006\u0007\u001a\u0005\b\u0089\u0001\u00106R\u0017\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u00078F@\u0006¢\u0006\u0007\u001a\u0005\b\u008b\u0001\u00106R\u0015\u0010\u008e\u0001\u001a\u00020\u00078F@\u0006¢\u0006\u0007\u001a\u0005\b\u008d\u0001\u00106R\u0017\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u00078F@\u0006¢\u0006\u0007\u001a\u0005\b\u008f\u0001\u00106R\u0015\u0010\u0092\u0001\u001a\u00020\u00078F@\u0006¢\u0006\u0007\u001a\u0005\b\u0091\u0001\u00106R\u0015\u0010\u0093\u0001\u001a\u00020\u00128F@\u0006¢\u0006\u0007\u001a\u0005\b\u0093\u0001\u0010[R\u0019\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0094\u00018F@\u0006¢\u0006\b\u001a\u0006\b\u0095\u0001\u0010\u0096\u0001¨\u0006\u009c\u0001"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "", "Lokhttp3/Headers;", "headers", "", "Lcom/chuckerteam/chucker/internal/data/entity/HttpHeader;", "toHttpHeaderList", "", "body", CMSAttributeTableGenerator.CONTENT_TYPE, "formatBody", "", "bytes", "formatBytes", "", "setRequestHeaders", "getParsedRequestHeaders", "getParsedResponseHeaders", "", "withMarkup", "getRequestHeadersString", "setResponseHeaders", "getResponseHeadersString", "getFormattedRequestBody", "getFormattedResponseBody", "Lokhttp3/HttpUrl;", "httpUrl", "populateUrl", "encode", "getFormattedUrl", "getFormattedPath", "other", "hasTheSameContent", "id", "J", "getId", "()J", "setId", "(J)V", "requestDate", "Ljava/lang/Long;", "getRequestDate", "()Ljava/lang/Long;", "setRequestDate", "(Ljava/lang/Long;)V", "responseDate", "getResponseDate", "setResponseDate", "tookMs", "getTookMs", "setTookMs", "protocol", "Ljava/lang/String;", "getProtocol", "()Ljava/lang/String;", "setProtocol", "(Ljava/lang/String;)V", FirebaseAnalytics.Param.METHOD, "getMethod", "setMethod", ImagesContract.URL, "getUrl", "setUrl", "host", "getHost", "setHost", ClientCookie.PATH_ATTR, "getPath", "setPath", "scheme", "getScheme", "setScheme", "responseTlsVersion", "getResponseTlsVersion", "setResponseTlsVersion", "responseCipherSuite", "getResponseCipherSuite", "setResponseCipherSuite", "requestPayloadSize", "getRequestPayloadSize", "setRequestPayloadSize", "requestContentType", "getRequestContentType", "setRequestContentType", "requestHeaders", "getRequestHeaders", "requestBody", "getRequestBody", "setRequestBody", "isRequestBodyPlainText", "Z", "()Z", "setRequestBodyPlainText", "(Z)V", "", "responseCode", "Ljava/lang/Integer;", "getResponseCode", "()Ljava/lang/Integer;", "setResponseCode", "(Ljava/lang/Integer;)V", "responseMessage", "getResponseMessage", "setResponseMessage", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "getError", "setError", "responsePayloadSize", "getResponsePayloadSize", "setResponsePayloadSize", "responseContentType", "getResponseContentType", "setResponseContentType", "responseHeaders", "getResponseHeaders", "responseBody", "getResponseBody", "setResponseBody", "isResponseBodyPlainText", "setResponseBodyPlainText", "", "responseImageData", "[B", "getResponseImageData", "()[B", "setResponseImageData", "([B)V", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction$Status;", "getStatus", "()Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction$Status;", NotificationCompat.CATEGORY_STATUS, "getRequestDateString", "requestDateString", "getResponseDateString", "responseDateString", "getDurationString", "durationString", "getRequestSizeString", "requestSizeString", "getResponseSizeString", "responseSizeString", "getTotalSizeString", "totalSizeString", "getResponseSummaryText", "responseSummaryText", "getNotificationText", "notificationText", "isSsl", "Landroid/graphics/Bitmap;", "getResponseImageBitmap", "()Landroid/graphics/Bitmap;", "responseImageBitmap", "<init>", "(JLjava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z[B)V", "()V", "Status", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class HttpTransaction {
    @ColumnInfo(name = Constants.IPC_BUNDLE_KEY_SEND_ERROR)
    @Nullable
    private String error;
    @ColumnInfo(name = "host")
    @Nullable
    private String host;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "isRequestBodyPlainText")
    private boolean isRequestBodyPlainText;
    @ColumnInfo(name = "isResponseBodyPlainText")
    private boolean isResponseBodyPlainText;
    @ColumnInfo(name = FirebaseAnalytics.Param.METHOD)
    @Nullable
    private String method;
    @ColumnInfo(name = ClientCookie.PATH_ATTR)
    @Nullable
    private String path;
    @ColumnInfo(name = "protocol")
    @Nullable
    private String protocol;
    @ColumnInfo(name = "requestBody")
    @Nullable
    private String requestBody;
    @ColumnInfo(name = "requestContentType")
    @Nullable
    private String requestContentType;
    @ColumnInfo(name = "requestDate")
    @Nullable
    private Long requestDate;
    @ColumnInfo(name = "requestHeaders")
    @Nullable
    private String requestHeaders;
    @ColumnInfo(name = "requestPayloadSize")
    @Nullable
    private Long requestPayloadSize;
    @ColumnInfo(name = "responseBody")
    @Nullable
    private String responseBody;
    @ColumnInfo(name = "responseCipherSuite")
    @Nullable
    private String responseCipherSuite;
    @ColumnInfo(name = "responseCode")
    @Nullable
    private Integer responseCode;
    @ColumnInfo(name = "responseContentType")
    @Nullable
    private String responseContentType;
    @ColumnInfo(name = "responseDate")
    @Nullable
    private Long responseDate;
    @ColumnInfo(name = "responseHeaders")
    @Nullable
    private String responseHeaders;
    @ColumnInfo(name = "responseImageData")
    @Nullable
    private byte[] responseImageData;
    @ColumnInfo(name = "responseMessage")
    @Nullable
    private String responseMessage;
    @ColumnInfo(name = "responsePayloadSize")
    @Nullable
    private Long responsePayloadSize;
    @ColumnInfo(name = "responseTlsVersion")
    @Nullable
    private String responseTlsVersion;
    @ColumnInfo(name = "scheme")
    @Nullable
    private String scheme;
    @ColumnInfo(name = "tookMs")
    @Nullable
    private Long tookMs;
    @ColumnInfo(name = ImagesContract.URL)
    @Nullable
    private String url;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction$Status;", "", "<init>", "(Ljava/lang/String;I)V", "Requested", "Complete", "Failed", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public enum Status {
        Requested,
        Complete,
        Failed
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Status.values().length];
            $EnumSwitchMapping$0 = iArr;
            Status status = Status.Failed;
            iArr[status.ordinal()] = 1;
            Status status2 = Status.Requested;
            iArr[status2.ordinal()] = 2;
            int[] iArr2 = new int[Status.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[status.ordinal()] = 1;
            iArr2[status2.ordinal()] = 2;
        }
    }

    @Ignore
    public HttpTransaction() {
        this(0L, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, false, null, 16842753, null);
    }

    public HttpTransaction(long j2, @Nullable Long l2, @Nullable Long l3, @Nullable Long l4, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable Long l5, @Nullable String str9, @Nullable String str10, @Nullable String str11, boolean z, @Nullable Integer num, @Nullable String str12, @Nullable String str13, @Nullable Long l6, @Nullable String str14, @Nullable String str15, @Nullable String str16, boolean z2, @Nullable byte[] bArr) {
        this.id = j2;
        this.requestDate = l2;
        this.responseDate = l3;
        this.tookMs = l4;
        this.protocol = str;
        this.method = str2;
        this.url = str3;
        this.host = str4;
        this.path = str5;
        this.scheme = str6;
        this.responseTlsVersion = str7;
        this.responseCipherSuite = str8;
        this.requestPayloadSize = l5;
        this.requestContentType = str9;
        this.requestHeaders = str10;
        this.requestBody = str11;
        this.isRequestBodyPlainText = z;
        this.responseCode = num;
        this.responseMessage = str12;
        this.error = str13;
        this.responsePayloadSize = l6;
        this.responseContentType = str14;
        this.responseHeaders = str15;
        this.responseBody = str16;
        this.isResponseBodyPlainText = z2;
        this.responseImageData = bArr;
    }

    public /* synthetic */ HttpTransaction(long j2, Long l2, Long l3, Long l4, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Long l5, String str9, String str10, String str11, boolean z, Integer num, String str12, String str13, Long l6, String str14, String str15, String str16, boolean z2, byte[] bArr, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0L : j2, l2, l3, l4, str, str2, str3, str4, str5, str6, str7, str8, l5, str9, str10, str11, (i2 & 65536) != 0 ? true : z, num, str12, str13, l6, str14, str15, str16, (i2 & 16777216) != 0 ? true : z2, bArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0010  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String formatBody(String str, String str2) {
        boolean z;
        boolean contains;
        boolean contains2;
        boolean contains3;
        boolean isBlank;
        if (str2 != null) {
            isBlank = StringsKt__StringsJVMKt.isBlank(str2);
            if (!isBlank) {
                z = false;
                if (z) {
                    contains = StringsKt__StringsKt.contains((CharSequence) str2, (CharSequence) "json", true);
                    if (contains) {
                        return FormatUtils.INSTANCE.formatJson(str);
                    }
                    contains2 = StringsKt__StringsKt.contains((CharSequence) str2, (CharSequence) "xml", true);
                    if (contains2) {
                        return FormatUtils.INSTANCE.formatXml(str);
                    }
                    contains3 = StringsKt__StringsKt.contains((CharSequence) str2, (CharSequence) "x-www-form-urlencoded", true);
                    return contains3 ? FormatUtils.INSTANCE.formatUrlEncodedForm(str) : str;
                }
                return str;
            }
        }
        z = true;
        if (z) {
        }
    }

    private final String formatBytes(long j2) {
        return FormatUtils.INSTANCE.formatByteCount(j2, true);
    }

    private final List<HttpHeader> toHttpHeaderList(Headers headers) {
        ArrayList arrayList = new ArrayList();
        int size = headers.size();
        for (int i2 = 0; i2 < size; i2++) {
            String name = headers.name(i2);
            Intrinsics.checkNotNullExpressionValue(name, "headers.name(i)");
            String value = headers.value(i2);
            Intrinsics.checkNotNullExpressionValue(value, "headers.value(i)");
            arrayList.add(new HttpHeader(name, value));
        }
        return arrayList;
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
        HttpUrl httpUrl;
        String str = this.url;
        return (str == null || (httpUrl = HttpUrl.get(str)) == null) ? "" : FormattedUrl.Companion.fromHttpUrl(httpUrl, z).getPathWithQuery();
    }

    @NotNull
    public final String getFormattedRequestBody() {
        String formatBody;
        String str = this.requestBody;
        return (str == null || (formatBody = formatBody(str, this.requestContentType)) == null) ? "" : formatBody;
    }

    @NotNull
    public final String getFormattedResponseBody() {
        String formatBody;
        String str = this.responseBody;
        return (str == null || (formatBody = formatBody(str, this.responseContentType)) == null) ? "" : formatBody;
    }

    @NotNull
    public final String getFormattedUrl(boolean z) {
        HttpUrl httpUrl;
        String str = this.url;
        return (str == null || (httpUrl = HttpUrl.get(str)) == null) ? "" : FormattedUrl.Companion.fromHttpUrl(httpUrl, z).getUrl();
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

    @NotNull
    public final String getNotificationText() {
        StringBuilder sb;
        String str;
        int i2 = WhenMappings.$EnumSwitchMapping$1[getStatus().ordinal()];
        if (i2 == 1) {
            sb = new StringBuilder();
            str = " ! ! !  ";
        } else if (i2 != 2) {
            sb = new StringBuilder();
            sb.append(this.responseCode);
            sb.append(TokenParser.SP);
            sb.append(this.method);
            sb.append(TokenParser.SP);
            sb.append(this.path);
            return sb.toString();
        } else {
            sb = new StringBuilder();
            str = " . . .  ";
        }
        sb.append(str);
        sb.append(this.method);
        sb.append(TokenParser.SP);
        sb.append(this.path);
        return sb.toString();
    }

    @Nullable
    public final List<HttpHeader> getParsedRequestHeaders() {
        return (List) JsonConverter.INSTANCE.getInstance().fromJson(this.requestHeaders, new TypeToken<List<? extends HttpHeader>>() { // from class: com.chuckerteam.chucker.internal.data.entity.HttpTransaction$getParsedRequestHeaders$1
        }.getType());
    }

    @Nullable
    public final List<HttpHeader> getParsedResponseHeaders() {
        return (List) JsonConverter.INSTANCE.getInstance().fromJson(this.responseHeaders, new TypeToken<List<? extends HttpHeader>>() { // from class: com.chuckerteam.chucker.internal.data.entity.HttpTransaction$getParsedResponseHeaders$1
        }.getType());
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
    public final String getRequestBody() {
        return this.requestBody;
    }

    @Nullable
    public final String getRequestContentType() {
        return this.requestContentType;
    }

    @Nullable
    public final Long getRequestDate() {
        return this.requestDate;
    }

    @Nullable
    public final String getRequestDateString() {
        Long l2 = this.requestDate;
        if (l2 != null) {
            return new Date(l2.longValue()).toString();
        }
        return null;
    }

    @Nullable
    public final String getRequestHeaders() {
        return this.requestHeaders;
    }

    @NotNull
    public final String getRequestHeadersString(boolean z) {
        return FormatUtils.INSTANCE.formatHeaders(getParsedRequestHeaders(), z);
    }

    @Nullable
    public final Long getRequestPayloadSize() {
        return this.requestPayloadSize;
    }

    @NotNull
    public final String getRequestSizeString() {
        Long l2 = this.requestPayloadSize;
        return formatBytes(l2 != null ? l2.longValue() : 0L);
    }

    @Nullable
    public final String getResponseBody() {
        return this.responseBody;
    }

    @Nullable
    public final String getResponseCipherSuite() {
        return this.responseCipherSuite;
    }

    @Nullable
    public final Integer getResponseCode() {
        return this.responseCode;
    }

    @Nullable
    public final String getResponseContentType() {
        return this.responseContentType;
    }

    @Nullable
    public final Long getResponseDate() {
        return this.responseDate;
    }

    @Nullable
    public final String getResponseDateString() {
        Long l2 = this.responseDate;
        if (l2 != null) {
            return new Date(l2.longValue()).toString();
        }
        return null;
    }

    @Nullable
    public final String getResponseHeaders() {
        return this.responseHeaders;
    }

    @NotNull
    public final String getResponseHeadersString(boolean z) {
        return FormatUtils.INSTANCE.formatHeaders(getParsedResponseHeaders(), z);
    }

    @Nullable
    public final Bitmap getResponseImageBitmap() {
        byte[] bArr = this.responseImageData;
        if (bArr != null) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    @Nullable
    public final byte[] getResponseImageData() {
        return this.responseImageData;
    }

    @Nullable
    public final String getResponseMessage() {
        return this.responseMessage;
    }

    @Nullable
    public final Long getResponsePayloadSize() {
        return this.responsePayloadSize;
    }

    @Nullable
    public final String getResponseSizeString() {
        Long l2 = this.responsePayloadSize;
        if (l2 != null) {
            return formatBytes(l2.longValue());
        }
        return null;
    }

    @Nullable
    public final String getResponseSummaryText() {
        int i2 = WhenMappings.$EnumSwitchMapping$0[getStatus().ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                return this.responseCode + TokenParser.SP + this.responseMessage;
            }
            return null;
        }
        return this.error;
    }

    @Nullable
    public final String getResponseTlsVersion() {
        return this.responseTlsVersion;
    }

    @Nullable
    public final String getScheme() {
        return this.scheme;
    }

    @NotNull
    public final Status getStatus() {
        return this.error != null ? Status.Failed : this.responseCode == null ? Status.Requested : Status.Complete;
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

    @Nullable
    public final String getUrl() {
        return this.url;
    }

    public final boolean hasTheSameContent(@Nullable HttpTransaction httpTransaction) {
        if (this == httpTransaction) {
            return true;
        }
        if (httpTransaction == null) {
            return false;
        }
        if (this.id == httpTransaction.id && Intrinsics.areEqual(this.requestDate, httpTransaction.requestDate) && Intrinsics.areEqual(this.responseDate, httpTransaction.responseDate) && Intrinsics.areEqual(this.tookMs, httpTransaction.tookMs) && Intrinsics.areEqual(this.protocol, httpTransaction.protocol) && Intrinsics.areEqual(this.method, httpTransaction.method) && Intrinsics.areEqual(this.url, httpTransaction.url) && Intrinsics.areEqual(this.host, httpTransaction.host) && Intrinsics.areEqual(this.path, httpTransaction.path) && Intrinsics.areEqual(this.scheme, httpTransaction.scheme) && Intrinsics.areEqual(this.responseTlsVersion, httpTransaction.responseTlsVersion) && Intrinsics.areEqual(this.responseCipherSuite, httpTransaction.responseCipherSuite) && Intrinsics.areEqual(this.requestPayloadSize, httpTransaction.requestPayloadSize) && Intrinsics.areEqual(this.requestContentType, httpTransaction.requestContentType) && Intrinsics.areEqual(this.requestHeaders, httpTransaction.requestHeaders) && Intrinsics.areEqual(this.requestBody, httpTransaction.requestBody) && this.isRequestBodyPlainText == httpTransaction.isRequestBodyPlainText && Intrinsics.areEqual(this.responseCode, httpTransaction.responseCode) && Intrinsics.areEqual(this.responseMessage, httpTransaction.responseMessage) && Intrinsics.areEqual(this.error, httpTransaction.error) && Intrinsics.areEqual(this.responsePayloadSize, httpTransaction.responsePayloadSize) && Intrinsics.areEqual(this.responseContentType, httpTransaction.responseContentType) && Intrinsics.areEqual(this.responseHeaders, httpTransaction.responseHeaders) && Intrinsics.areEqual(this.responseBody, httpTransaction.responseBody) && this.isResponseBodyPlainText == httpTransaction.isResponseBodyPlainText) {
            byte[] bArr = this.responseImageData;
            if (bArr == null) {
                return true;
            }
            byte[] bArr2 = httpTransaction.responseImageData;
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            if (Arrays.equals(bArr, bArr2)) {
                return true;
            }
        }
        return false;
    }

    public final boolean isRequestBodyPlainText() {
        return this.isRequestBodyPlainText;
    }

    public final boolean isResponseBodyPlainText() {
        return this.isResponseBodyPlainText;
    }

    public final boolean isSsl() {
        boolean equals;
        equals = StringsKt__StringsJVMKt.equals(this.scheme, "https", true);
        return equals;
    }

    @NotNull
    public final HttpTransaction populateUrl(@NotNull HttpUrl httpUrl) {
        Intrinsics.checkNotNullParameter(httpUrl, "httpUrl");
        FormattedUrl fromHttpUrl = FormattedUrl.Companion.fromHttpUrl(httpUrl, false);
        this.url = fromHttpUrl.getUrl();
        this.host = fromHttpUrl.getHost();
        this.path = fromHttpUrl.getPathWithQuery();
        this.scheme = fromHttpUrl.getScheme();
        return this;
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

    public final void setRequestBody(@Nullable String str) {
        this.requestBody = str;
    }

    public final void setRequestBodyPlainText(boolean z) {
        this.isRequestBodyPlainText = z;
    }

    public final void setRequestContentType(@Nullable String str) {
        this.requestContentType = str;
    }

    public final void setRequestDate(@Nullable Long l2) {
        this.requestDate = l2;
    }

    public final void setRequestHeaders(@Nullable String str) {
        this.requestHeaders = str;
    }

    public final void setRequestHeaders(@NotNull List<HttpHeader> headers) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.requestHeaders = JsonConverter.INSTANCE.getInstance().toJson(headers);
    }

    public final void setRequestHeaders(@NotNull Headers headers) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        setRequestHeaders(toHttpHeaderList(headers));
    }

    public final void setRequestPayloadSize(@Nullable Long l2) {
        this.requestPayloadSize = l2;
    }

    public final void setResponseBody(@Nullable String str) {
        this.responseBody = str;
    }

    public final void setResponseBodyPlainText(boolean z) {
        this.isResponseBodyPlainText = z;
    }

    public final void setResponseCipherSuite(@Nullable String str) {
        this.responseCipherSuite = str;
    }

    public final void setResponseCode(@Nullable Integer num) {
        this.responseCode = num;
    }

    public final void setResponseContentType(@Nullable String str) {
        this.responseContentType = str;
    }

    public final void setResponseDate(@Nullable Long l2) {
        this.responseDate = l2;
    }

    public final void setResponseHeaders(@Nullable String str) {
        this.responseHeaders = str;
    }

    public final void setResponseHeaders(@NotNull List<HttpHeader> headers) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.responseHeaders = JsonConverter.INSTANCE.getInstance().toJson(headers);
    }

    public final void setResponseHeaders(@NotNull Headers headers) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        setResponseHeaders(toHttpHeaderList(headers));
    }

    public final void setResponseImageData(@Nullable byte[] bArr) {
        this.responseImageData = bArr;
    }

    public final void setResponseMessage(@Nullable String str) {
        this.responseMessage = str;
    }

    public final void setResponsePayloadSize(@Nullable Long l2) {
        this.responsePayloadSize = l2;
    }

    public final void setResponseTlsVersion(@Nullable String str) {
        this.responseTlsVersion = str;
    }

    public final void setScheme(@Nullable String str) {
        this.scheme = str;
    }

    public final void setTookMs(@Nullable Long l2) {
        this.tookMs = l2;
    }

    public final void setUrl(@Nullable String str) {
        this.url = str;
    }
}
