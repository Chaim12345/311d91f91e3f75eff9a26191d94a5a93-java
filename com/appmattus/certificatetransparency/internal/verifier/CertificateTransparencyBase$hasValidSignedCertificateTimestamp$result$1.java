package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.datasource.DataSource;
import com.appmattus.certificatetransparency.loglist.LogListResult;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "com.appmattus.certificatetransparency.internal.verifier.CertificateTransparencyBase$hasValidSignedCertificateTimestamp$result$1", f = "CertificateTransparencyBase.kt", i = {}, l = {113}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class CertificateTransparencyBase$hasValidSignedCertificateTimestamp$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super LogListResult>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f4636a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ CertificateTransparencyBase f4637b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CertificateTransparencyBase$hasValidSignedCertificateTimestamp$result$1(CertificateTransparencyBase certificateTransparencyBase, Continuation continuation) {
        super(2, continuation);
        this.f4637b = certificateTransparencyBase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new CertificateTransparencyBase$hasValidSignedCertificateTimestamp$result$1(this.f4637b, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super LogListResult> continuation) {
        return ((CertificateTransparencyBase$hasValidSignedCertificateTimestamp$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        DataSource dataSource;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f4636a;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            dataSource = this.f4637b.logListDataSource;
            this.f4636a = 1;
            obj = dataSource.get(this);
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
