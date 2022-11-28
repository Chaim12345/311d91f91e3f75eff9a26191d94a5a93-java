package com.chuckerteam.chucker.internal.ui.transaction;

import android.net.Uri;
import android.widget.Toast;
import com.chuckerteam.chucker.R;
import com.chuckerteam.chucker.internal.data.entity.HttpTransaction;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.ui.transaction.TransactionPayloadFragment$onActivityResult$1", f = "TransactionPayloadFragment.kt", i = {0}, l = {206}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes.dex */
final class TransactionPayloadFragment$onActivityResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: a  reason: collision with root package name */
    Object f4987a;

    /* renamed from: b  reason: collision with root package name */
    int f4988b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ TransactionPayloadFragment f4989c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Uri f4990d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ HttpTransaction f4991e;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TransactionPayloadFragment$onActivityResult$1(TransactionPayloadFragment transactionPayloadFragment, Uri uri, HttpTransaction httpTransaction, Continuation continuation) {
        super(2, continuation);
        this.f4989c = transactionPayloadFragment;
        this.f4990d = uri;
        this.f4991e = httpTransaction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        TransactionPayloadFragment$onActivityResult$1 transactionPayloadFragment$onActivityResult$1 = new TransactionPayloadFragment$onActivityResult$1(this.f4989c, this.f4990d, this.f4991e, completion);
        transactionPayloadFragment$onActivityResult$1.p$ = (CoroutineScope) obj;
        return transactionPayloadFragment$onActivityResult$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TransactionPayloadFragment$onActivityResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        PayloadType payloadType;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f4988b;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            TransactionPayloadFragment transactionPayloadFragment = this.f4989c;
            payloadType = transactionPayloadFragment.getPayloadType();
            Uri uri = this.f4990d;
            HttpTransaction httpTransaction = this.f4991e;
            this.f4987a = coroutineScope;
            this.f4988b = 1;
            obj = transactionPayloadFragment.b(payloadType, uri, httpTransaction, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.f4987a;
            ResultKt.throwOnFailure(obj);
        }
        Toast.makeText(this.f4989c.getContext(), ((Boolean) obj).booleanValue() ? R.string.chucker_file_saved : R.string.chucker_file_not_saved, 0).show();
        return Unit.INSTANCE;
    }
}
