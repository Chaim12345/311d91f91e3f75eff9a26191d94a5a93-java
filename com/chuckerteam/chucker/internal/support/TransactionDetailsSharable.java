package com.chuckerteam.chucker.internal.support;

import android.content.Context;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okio.Buffer;
import okio.Source;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\bR\u0016\u0010\n\u001a\u00020\t8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/TransactionDetailsSharable;", "Lcom/chuckerteam/chucker/internal/support/Sharable;", "Landroid/content/Context;", "context", "Lokio/Source;", "toSharableContent", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "", "encodeUrls", "Z", "<init>", "(Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;Z)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionDetailsSharable implements Sharable {
    private final boolean encodeUrls;
    private final HttpTransaction transaction;

    public TransactionDetailsSharable(@NotNull HttpTransaction transaction, boolean z) {
        Intrinsics.checkNotNullParameter(transaction, "transaction");
        this.transaction = transaction;
        this.encodeUrls = z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0288, code lost:
        if (r1 != false) goto L33;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0297  */
    @Override // com.chuckerteam.chucker.internal.support.Sharable
    @NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Source toSharableContent(@NotNull Context context) {
        int i2;
        boolean isBlank;
        int i3;
        String str;
        boolean isBlank2;
        int i4;
        String str2;
        boolean isBlank3;
        boolean z;
        boolean isBlank4;
        Intrinsics.checkNotNullParameter(context, "context");
        Buffer buffer = new Buffer();
        buffer.writeUtf8(context.getString(R.string.chucker_url) + ": " + this.transaction.getFormattedUrl(this.encodeUrls) + '\n');
        buffer.writeUtf8(context.getString(R.string.chucker_method) + ": " + this.transaction.getMethod() + '\n');
        buffer.writeUtf8(context.getString(R.string.chucker_protocol) + ": " + this.transaction.getProtocol() + '\n');
        buffer.writeUtf8(context.getString(R.string.chucker_status) + ": " + this.transaction.getStatus() + '\n');
        StringBuilder sb = new StringBuilder();
        sb.append(context.getString(R.string.chucker_response));
        sb.append(": ");
        sb.append(this.transaction.getResponseSummaryText());
        sb.append('\n');
        buffer.writeUtf8(sb.toString());
        int i5 = this.transaction.isSsl() ? R.string.chucker_yes : R.string.chucker_no;
        buffer.writeUtf8(context.getString(R.string.chucker_ssl) + ": " + context.getString(i5) + '\n');
        buffer.writeUtf8("\n");
        buffer.writeUtf8(context.getString(R.string.chucker_request_time) + ": " + this.transaction.getRequestDateString() + '\n');
        buffer.writeUtf8(context.getString(R.string.chucker_response_time) + ": " + this.transaction.getResponseDateString() + '\n');
        buffer.writeUtf8(context.getString(R.string.chucker_duration) + ": " + this.transaction.getDurationString() + '\n');
        buffer.writeUtf8("\n");
        buffer.writeUtf8(context.getString(R.string.chucker_request_size) + ": " + this.transaction.getRequestSizeString() + '\n');
        buffer.writeUtf8(context.getString(R.string.chucker_response_size) + ": " + this.transaction.getResponseSizeString() + '\n');
        buffer.writeUtf8(context.getString(R.string.chucker_total_size) + ": " + this.transaction.getTotalSizeString() + '\n');
        buffer.writeUtf8("\n");
        buffer.writeUtf8("---------- " + context.getString(R.string.chucker_request) + " ----------\n\n");
        FormatUtils formatUtils = FormatUtils.INSTANCE;
        boolean z2 = false;
        String formatHeaders = formatUtils.formatHeaders(this.transaction.getParsedRequestHeaders(), false);
        isBlank = StringsKt__StringsJVMKt.isBlank(formatHeaders);
        if (!isBlank) {
            buffer.writeUtf8(formatHeaders);
            buffer.writeUtf8("\n");
        }
        if (this.transaction.isRequestBodyPlainText()) {
            String requestBody = this.transaction.getRequestBody();
            if (requestBody != null) {
                isBlank4 = StringsKt__StringsJVMKt.isBlank(requestBody);
                if (!isBlank4) {
                    z = false;
                    if (z) {
                        str = this.transaction.getFormattedRequestBody();
                        buffer.writeUtf8(str);
                        buffer.writeUtf8("\n\n");
                        buffer.writeUtf8("---------- " + context.getString(i2) + " ----------\n\n");
                        String formatHeaders2 = formatUtils.formatHeaders(this.transaction.getParsedResponseHeaders(), false);
                        isBlank2 = StringsKt__StringsJVMKt.isBlank(formatHeaders2);
                        if (!isBlank2) {
                            buffer.writeUtf8(formatHeaders2);
                            buffer.writeUtf8("\n");
                        }
                        if (this.transaction.isResponseBodyPlainText()) {
                            i4 = R.string.chucker_body_omitted;
                        } else {
                            String responseBody = this.transaction.getResponseBody();
                            if (responseBody != null) {
                                isBlank3 = StringsKt__StringsJVMKt.isBlank(responseBody);
                            }
                            z2 = true;
                            if (!z2) {
                                str2 = this.transaction.getFormattedResponseBody();
                                buffer.writeUtf8(str2);
                                return buffer;
                            }
                            i4 = R.string.chucker_body_empty;
                        }
                        str2 = context.getString(i4);
                        buffer.writeUtf8(str2);
                        return buffer;
                    }
                    i3 = R.string.chucker_body_empty;
                }
            }
            z = true;
            if (z) {
            }
        } else {
            i3 = R.string.chucker_body_omitted;
        }
        str = context.getString(i3);
        buffer.writeUtf8(str);
        buffer.writeUtf8("\n\n");
        buffer.writeUtf8("---------- " + context.getString(i2) + " ----------\n\n");
        String formatHeaders22 = formatUtils.formatHeaders(this.transaction.getParsedResponseHeaders(), false);
        isBlank2 = StringsKt__StringsJVMKt.isBlank(formatHeaders22);
        if (!isBlank2) {
        }
        if (this.transaction.isResponseBodyPlainText()) {
        }
        str2 = context.getString(i4);
        buffer.writeUtf8(str2);
        return buffer;
    }
}
