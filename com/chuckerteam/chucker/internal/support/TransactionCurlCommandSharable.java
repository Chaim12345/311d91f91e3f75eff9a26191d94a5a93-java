package com.chuckerteam.chucker.internal.support;

import android.content.Context;
import com.chuckerteam.chucker.internal.data.entity.HttpHeader;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import okio.Buffer;
import okio.Source;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/TransactionCurlCommandSharable;", "Lcom/chuckerteam/chucker/internal/support/Sharable;", "Landroid/content/Context;", "context", "Lokio/Source;", "toSharableContent", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "<init>", "(Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionCurlCommandSharable implements Sharable {
    private final HttpTransaction transaction;

    public TransactionCurlCommandSharable(@NotNull HttpTransaction transaction) {
        Intrinsics.checkNotNullParameter(transaction, "transaction");
        this.transaction = transaction;
    }

    @Override // com.chuckerteam.chucker.internal.support.Sharable
    @NotNull
    public Source toSharableContent(@NotNull Context context) {
        boolean z;
        String replace$default;
        boolean equals;
        boolean equals2;
        Intrinsics.checkNotNullParameter(context, "context");
        Buffer buffer = new Buffer();
        buffer.writeUtf8("curl -X " + this.transaction.getMethod());
        List<HttpHeader> parsedRequestHeaders = this.transaction.getParsedRequestHeaders();
        boolean z2 = true;
        if (parsedRequestHeaders != null) {
            z = false;
            for (HttpHeader httpHeader : parsedRequestHeaders) {
                equals = StringsKt__StringsJVMKt.equals("Accept-Encoding", httpHeader.getName(), true);
                if (equals) {
                    equals2 = StringsKt__StringsJVMKt.equals("gzip", httpHeader.getValue(), true);
                    if (equals2) {
                        z = true;
                    }
                }
                buffer.writeUtf8(" -H \"" + httpHeader.getName() + ": " + httpHeader.getValue() + '\"');
            }
        } else {
            z = false;
        }
        String requestBody = this.transaction.getRequestBody();
        if (requestBody != null && requestBody.length() != 0) {
            z2 = false;
        }
        if (!z2) {
            StringBuilder sb = new StringBuilder();
            sb.append(" --data $'");
            replace$default = StringsKt__StringsJVMKt.replace$default(requestBody, "\n", "\\n", false, 4, (Object) null);
            sb.append(replace$default);
            sb.append('\'');
            buffer.writeUtf8(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(z ? " --compressed " : " ");
        sb2.append(this.transaction.getFormattedUrl(false));
        buffer.writeUtf8(sb2.toString());
        return buffer;
    }
}
