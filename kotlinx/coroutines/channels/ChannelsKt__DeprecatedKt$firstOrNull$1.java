package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {75}, m = "firstOrNull", n = {"$this$consume$iv", "iterator"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$firstOrNull$1<E> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11421a;

    /* renamed from: b  reason: collision with root package name */
    Object f11422b;

    /* renamed from: c  reason: collision with root package name */
    /* synthetic */ Object f11423c;

    /* renamed from: d  reason: collision with root package name */
    int f11424d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$firstOrNull$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object firstOrNull;
        this.f11423c = obj;
        this.f11424d |= Integer.MIN_VALUE;
        firstOrNull = ChannelsKt__DeprecatedKt.firstOrNull(null, this);
        return firstOrNull;
    }
}
