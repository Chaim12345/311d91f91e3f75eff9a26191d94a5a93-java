package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "count", n = {"count", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$count$1<E> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11356a;

    /* renamed from: b  reason: collision with root package name */
    Object f11357b;

    /* renamed from: c  reason: collision with root package name */
    Object f11358c;

    /* renamed from: d  reason: collision with root package name */
    /* synthetic */ Object f11359d;

    /* renamed from: e  reason: collision with root package name */
    int f11360e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$count$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object count;
        this.f11359d = obj;
        this.f11360e |= Integer.MIN_VALUE;
        count = ChannelsKt__DeprecatedKt.count(null, this);
        return count;
    }
}
