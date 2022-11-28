package com.chuckerteam.chucker.internal.ui.transaction;

import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u00042\b\u0010\u0001\u001a\u0004\u0018\u00010\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;", "transaction", "", "encodeUrl", "", "invoke", "(Lcom/chuckerteam/chucker/internal/data/entity/HttpTransaction;Z)Ljava/lang/String;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class TransactionViewModel$transactionTitle$1 extends Lambda implements Function2<HttpTransaction, Boolean, String> {
    public static final TransactionViewModel$transactionTitle$1 INSTANCE = new TransactionViewModel$transactionTitle$1();

    TransactionViewModel$transactionTitle$1() {
        super(2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ String invoke(HttpTransaction httpTransaction, Boolean bool) {
        return invoke(httpTransaction, bool.booleanValue());
    }

    @NotNull
    public final String invoke(@Nullable HttpTransaction httpTransaction, boolean z) {
        if (httpTransaction != null) {
            return httpTransaction.getMethod() + TokenParser.SP + httpTransaction.getFormattedPath(z);
        }
        return "";
    }
}
