package com.chuckerteam.chucker.internal.support;

import android.content.Context;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.Source;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\u0006\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016R\u001c\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\t¨\u0006\u000f"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/TransactionListDetailsSharable;", "Lcom/chuckerteam/chucker/internal/support/Sharable;", "Landroid/content/Context;", "context", "Lokio/Source;", "toSharableContent", "", "Lcom/chuckerteam/chucker/internal/support/TransactionDetailsSharable;", "transactions", "Ljava/util/List;", "Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "", "encodeUrls", "<init>", "(Ljava/util/List;Z)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionListDetailsSharable implements Sharable {
    private final List<TransactionDetailsSharable> transactions;

    public TransactionListDetailsSharable(@NotNull List<HttpTransaction> transactions, boolean z) {
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(transactions, "transactions");
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(transactions, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (HttpTransaction httpTransaction : transactions) {
            arrayList.add(new TransactionDetailsSharable(httpTransaction, z));
        }
        this.transactions = arrayList;
    }

    @Override // com.chuckerteam.chucker.internal.support.Sharable
    @NotNull
    public Source toSharableContent(@NotNull Context context) {
        String joinToString$default;
        Intrinsics.checkNotNullParameter(context, "context");
        Buffer buffer = new Buffer();
        joinToString$default = CollectionsKt___CollectionsKt.joinToString$default(this.transactions, '\n' + context.getString(R.string.chucker_export_separator) + '\n', context.getString(R.string.chucker_export_prefix) + '\n', '\n' + context.getString(R.string.chucker_export_postfix) + '\n', 0, null, new TransactionListDetailsSharable$toSharableContent$$inlined$apply$lambda$1(this, context), 24, null);
        buffer.writeUtf8(joinToString$default);
        return buffer;
    }
}
