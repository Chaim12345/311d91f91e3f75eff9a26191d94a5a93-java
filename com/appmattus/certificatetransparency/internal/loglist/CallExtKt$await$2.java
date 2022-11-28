package com.appmattus.certificatetransparency.internal.loglist;

import java.io.IOException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.appmattus.certificatetransparency.internal.loglist.CallExtKt$await$2", f = "CallExt.kt", i = {}, l = {72}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class CallExtKt$await$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {

    /* renamed from: a  reason: collision with root package name */
    Object f4600a;

    /* renamed from: b  reason: collision with root package name */
    int f4601b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Call f4602c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallExtKt$await$2(Call call, Continuation continuation) {
        super(2, continuation);
        this.f4602c = call;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new CallExtKt$await$2(this.f4602c, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super byte[]> continuation) {
        return ((CallExtKt$await$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Continuation intercepted;
        Object coroutine_suspended2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f4601b;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Call call = this.f4602c;
            this.f4600a = call;
            this.f4601b = 1;
            intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(this);
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
            cancellableContinuationImpl.initCancellability();
            call.enqueue(new Callback() { // from class: com.appmattus.certificatetransparency.internal.loglist.CallExtKt$await$2$1$1
                @Override // okhttp3.Callback
                public void onFailure(@NotNull Call call2, @NotNull IOException e2) {
                    Intrinsics.checkNotNullParameter(call2, "call");
                    Intrinsics.checkNotNullParameter(e2, "e");
                    if (CancellableContinuation.this.isCancelled()) {
                        return;
                    }
                    CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                    Result.Companion companion = Result.Companion;
                    cancellableContinuation.resumeWith(Result.m187constructorimpl(ResultKt.createFailure(e2)));
                }

                @Override // okhttp3.Callback
                public void onResponse(@NotNull Call call2, @NotNull Response response) {
                    CancellableContinuation cancellableContinuation;
                    Object m187constructorimpl;
                    Intrinsics.checkNotNullParameter(call2, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    try {
                        ResponseBody body = response.body();
                        byte[] bytes = body != null ? body.bytes() : null;
                        if (!response.isSuccessful()) {
                            cancellableContinuation = CancellableContinuation.this;
                            Result.Companion companion = Result.Companion;
                            m187constructorimpl = Result.m187constructorimpl(ResultKt.createFailure(new IOException("Invalid response " + response.code())));
                        } else if (bytes != null) {
                            CancellableContinuation cancellableContinuation2 = CancellableContinuation.this;
                            Result.Companion companion2 = Result.Companion;
                            cancellableContinuation2.resumeWith(Result.m187constructorimpl(bytes));
                            return;
                        } else {
                            cancellableContinuation = CancellableContinuation.this;
                            Result.Companion companion3 = Result.Companion;
                            m187constructorimpl = Result.m187constructorimpl(ResultKt.createFailure(new IOException("No data")));
                        }
                        cancellableContinuation.resumeWith(m187constructorimpl);
                    } catch (Exception e2) {
                        CancellableContinuation cancellableContinuation3 = CancellableContinuation.this;
                        Result.Companion companion4 = Result.Companion;
                        cancellableContinuation3.resumeWith(Result.m187constructorimpl(ResultKt.createFailure(e2)));
                    }
                }
            });
            cancellableContinuationImpl.invokeOnCancellation(new CallExtKt$await$2$1$2(call));
            obj = cancellableContinuationImpl.getResult();
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (obj == coroutine_suspended2) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            Call call2 = (Call) this.f4600a;
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
