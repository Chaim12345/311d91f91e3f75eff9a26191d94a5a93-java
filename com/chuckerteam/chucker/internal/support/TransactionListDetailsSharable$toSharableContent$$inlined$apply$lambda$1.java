package com.chuckerteam.chucker.internal.support;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/TransactionDetailsSharable;", "it", "", "invoke", "(Lcom/chuckerteam/chucker/internal/support/TransactionDetailsSharable;)Ljava/lang/CharSequence;", "com/chuckerteam/chucker/internal/support/TransactionListDetailsSharable$toSharableContent$1$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
final class TransactionListDetailsSharable$toSharableContent$$inlined$apply$lambda$1 extends Lambda implements Function1<TransactionDetailsSharable, CharSequence> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Context f4938a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionListDetailsSharable$toSharableContent$$inlined$apply$lambda$1(TransactionListDetailsSharable transactionListDetailsSharable, Context context) {
        super(1);
        this.f4938a = context;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final CharSequence invoke(@NotNull TransactionDetailsSharable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String sharableUtf8Content = SharableKt.toSharableUtf8Content(it, this.f4938a);
        Intrinsics.checkNotNullExpressionValue(sharableUtf8Content, "it.toSharableUtf8Content(context)");
        return sharableUtf8Content;
    }
}
