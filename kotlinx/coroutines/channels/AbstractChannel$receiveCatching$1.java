package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.AbstractChannel", f = "AbstractChannel.kt", i = {}, l = {633}, m = "receiveCatching-JP2dKIU", n = {}, s = {})
/* loaded from: classes3.dex */
public final class AbstractChannel$receiveCatching$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    /* synthetic */ Object f11315a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ AbstractChannel f11316b;

    /* renamed from: c  reason: collision with root package name */
    int f11317c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractChannel$receiveCatching$1(AbstractChannel abstractChannel, Continuation continuation) {
        super(continuation);
        this.f11316b = abstractChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        this.f11315a = obj;
        this.f11317c |= Integer.MIN_VALUE;
        Object mo1627receiveCatchingJP2dKIU = this.f11316b.mo1627receiveCatchingJP2dKIU(this);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return mo1627receiveCatchingJP2dKIU == coroutine_suspended ? mo1627receiveCatchingJP2dKIU : ChannelResult.m1634boximpl(mo1627receiveCatchingJP2dKIU);
    }
}
