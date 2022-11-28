package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.AbstractFlow", f = "Flow.kt", i = {0}, l = {230}, m = "collect", n = {"safeCollector"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class AbstractFlow$collect$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11570a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11571b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ AbstractFlow f11572c;

    /* renamed from: d  reason: collision with root package name */
    int f11573d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractFlow$collect$1(AbstractFlow abstractFlow, Continuation continuation) {
        super(continuation);
        this.f11572c = abstractFlow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11571b = obj;
        this.f11573d |= Integer.MIN_VALUE;
        return this.f11572c.collect(null, this);
    }
}
