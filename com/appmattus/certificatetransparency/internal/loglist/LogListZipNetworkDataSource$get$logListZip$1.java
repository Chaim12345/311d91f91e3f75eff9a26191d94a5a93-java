package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.loglist.LogListService;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "com.appmattus.certificatetransparency.internal.loglist.LogListZipNetworkDataSource$get$logListZip$1", f = "LogListZipNetworkDataSource.kt", i = {}, l = {35}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class LogListZipNetworkDataSource$get$logListZip$1 extends SuspendLambda implements Function1<Continuation<? super byte[]>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f4613a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ LogListZipNetworkDataSource f4614b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogListZipNetworkDataSource$get$logListZip$1(LogListZipNetworkDataSource logListZipNetworkDataSource, Continuation continuation) {
        super(1, continuation);
        this.f4614b = logListZipNetworkDataSource;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        return new LogListZipNetworkDataSource$get$logListZip$1(this.f4614b, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final Object invoke(@Nullable Continuation<? super byte[]> continuation) {
        return ((LogListZipNetworkDataSource$get$logListZip$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        LogListService logListService;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f4613a;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            logListService = this.f4614b.logListService;
            this.f4613a = 1;
            obj = logListService.getLogListZip(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
