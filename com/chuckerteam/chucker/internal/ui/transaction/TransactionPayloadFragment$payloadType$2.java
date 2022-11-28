package com.chuckerteam.chucker.internal.ui.transaction;

import android.os.Bundle;
import java.io.Serializable;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/PayloadType;", "invoke", "()Lcom/chuckerteam/chucker/internal/ui/transaction/PayloadType;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes.dex */
final class TransactionPayloadFragment$payloadType$2 extends Lambda implements Function0<PayloadType> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ TransactionPayloadFragment f4999a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionPayloadFragment$payloadType$2(TransactionPayloadFragment transactionPayloadFragment) {
        super(0);
        this.f4999a = transactionPayloadFragment;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final PayloadType invoke() {
        Bundle arguments = this.f4999a.getArguments();
        Serializable serializable = arguments != null ? arguments.getSerializable("type") : null;
        Objects.requireNonNull(serializable, "null cannot be cast to non-null type com.chuckerteam.chucker.internal.ui.transaction.PayloadType");
        return (PayloadType) serializable;
    }
}
