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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0010\u0005\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.support.SharableKt$shareAsUtf8Text$content$1", f = "Sharable.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class SharableKt$shareAsUtf8Text$content$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f4935a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Sharable f4936b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Activity f4937c;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SharableKt$shareAsUtf8Text$content$1(Sharable sharable, Activity activity, Continuation continuation) {
        super(2, continuation);
        this.f4936b = sharable;
        this.f4937c = activity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        SharableKt$shareAsUtf8Text$content$1 sharableKt$shareAsUtf8Text$content$1 = new SharableKt$shareAsUtf8Text$content$1(this.f4936b, this.f4937c, completion);
        sharableKt$shareAsUtf8Text$content$1.p$ = (CoroutineScope) obj;
        return sharableKt$shareAsUtf8Text$content$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((SharableKt$shareAsUtf8Text$content$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.f4935a == 0) {
            ResultKt.throwOnFailure(obj);
            return SharableKt.toSharableUtf8Content(this.f4936b, this.f4937c);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
