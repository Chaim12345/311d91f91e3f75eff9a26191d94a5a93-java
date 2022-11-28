package com.chuckerteam.chucker.internal.support;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.chuckerteam.chucker.internal.support.BitmapUtilsKt$calculateLuminance$2", f = "BitmapUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
public final class BitmapUtilsKt$calculateLuminance$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Double>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f4885a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Bitmap f4886b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Ref.IntRef f4887c;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BitmapUtilsKt$calculateLuminance$2(Bitmap bitmap, Ref.IntRef intRef, Continuation continuation) {
        super(2, continuation);
        this.f4886b = bitmap;
        this.f4887c = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        BitmapUtilsKt$calculateLuminance$2 bitmapUtilsKt$calculateLuminance$2 = new BitmapUtilsKt$calculateLuminance$2(this.f4886b, this.f4887c, completion);
        bitmapUtilsKt$calculateLuminance$2.p$ = (CoroutineScope) obj;
        return bitmapUtilsKt$calculateLuminance$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Double> continuation) {
        return ((BitmapUtilsKt$calculateLuminance$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Bitmap replaceAlphaWithColor;
        Double luminance;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.f4885a == 0) {
            ResultKt.throwOnFailure(obj);
            replaceAlphaWithColor = BitmapUtilsKt.replaceAlphaWithColor(this.f4886b, this.f4887c.element);
            luminance = BitmapUtilsKt.getLuminance(replaceAlphaWithColor, this.f4887c.element);
            return luminance;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
