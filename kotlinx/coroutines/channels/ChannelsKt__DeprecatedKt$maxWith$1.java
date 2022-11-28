package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {420, 422}, m = "maxWith", n = {"comparator", "$this$consume$iv", "iterator", "comparator", "$this$consume$iv", "iterator", "max"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$maxWith$1<E> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11465a;

    /* renamed from: b  reason: collision with root package name */
    Object f11466b;

    /* renamed from: c  reason: collision with root package name */
    Object f11467c;

    /* renamed from: d  reason: collision with root package name */
    Object f11468d;

    /* renamed from: e  reason: collision with root package name */
    /* synthetic */ Object f11469e;

    /* renamed from: f  reason: collision with root package name */
    int f11470f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$maxWith$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object maxWith;
        this.f11469e = obj;
        this.f11470f |= Integer.MIN_VALUE;
        maxWith = ChannelsKt__DeprecatedKt.maxWith(null, null, this);
        return maxWith;
    }
}
