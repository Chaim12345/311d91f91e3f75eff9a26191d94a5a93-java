package com.chuckerteam.chucker.internal.support;

import android.app.Activity;
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
import okio.Source;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Lokio/Source;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.support.SharableKt$shareAsFile$fileContent$1", f = "Sharable.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class SharableKt$shareAsFile$fileContent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Source>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f4926a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Sharable f4927b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Activity f4928c;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharableKt$shareAsFile$fileContent$1(Sharable sharable, Activity activity, Continuation continuation) {
        super(2, continuation);
        this.f4927b = sharable;
        this.f4928c = activity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        SharableKt$shareAsFile$fileContent$1 sharableKt$shareAsFile$fileContent$1 = new SharableKt$shareAsFile$fileContent$1(this.f4927b, this.f4928c, completion);
        sharableKt$shareAsFile$fileContent$1.p$ = (CoroutineScope) obj;
        return sharableKt$shareAsFile$fileContent$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Source> continuation) {
        return ((SharableKt$shareAsFile$fileContent$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.f4926a == 0) {
            ResultKt.throwOnFailure(obj);
            return this.f4927b.toSharableContent(this.f4928c);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
