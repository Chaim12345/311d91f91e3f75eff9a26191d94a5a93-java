package com.appmattus.certificatetransparency.internal.loglist;

import com.appmattus.certificatetransparency.internal.utils.LimitedSizeInputStream;
import java.util.zip.ZipInputStream;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.appmattus.certificatetransparency.internal.loglist.LogListZipNetworkDataSource$readZip$2$3$2", f = "LogListZipNetworkDataSource.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class LogListZipNetworkDataSource$readZip$2$3$2 extends SuspendLambda implements Function1<Continuation<? super byte[]>, Object> {

    /* renamed from: a  reason: collision with root package name */
    int f4628a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ZipInputStream f4629b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogListZipNetworkDataSource$readZip$2$3$2(ZipInputStream zipInputStream, Continuation continuation) {
        super(1, continuation);
        this.f4629b = zipInputStream;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@NotNull Continuation<?> continuation) {
        return new LogListZipNetworkDataSource$readZip$2$3$2(this.f4629b, continuation);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final Object invoke(@Nullable Continuation<? super byte[]> continuation) {
        return ((LogListZipNetworkDataSource$readZip$2$3$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.f4628a == 0) {
            ResultKt.throwOnFailure(obj);
            return ByteStreamsKt.readBytes(new LimitedSizeInputStream(this.f4629b, 512L));
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
