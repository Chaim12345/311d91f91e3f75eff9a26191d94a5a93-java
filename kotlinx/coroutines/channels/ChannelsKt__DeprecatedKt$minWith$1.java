package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {434, 436}, m = "minWith", n = {"comparator", "$this$consume$iv", "iterator", "comparator", "$this$consume$iv", "iterator", "min"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$minWith$1<E> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11471a;

    /* renamed from: b  reason: collision with root package name */
    Object f11472b;

    /* renamed from: c  reason: collision with root package name */
    Object f11473c;

    /* renamed from: d  reason: collision with root package name */
    Object f11474d;

    /* renamed from: e  reason: collision with root package name */
    /* synthetic */ Object f11475e;

    /* renamed from: f  reason: collision with root package name */
    int f11476f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$minWith$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object minWith;
        this.f11475e = obj;
        this.f11476f |= Integer.MIN_VALUE;
        minWith = ChannelsKt__DeprecatedKt.minWith(null, null, this);
        return minWith;
    }
}
