package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.time.Duration;
import kotlinx.coroutines.DelayKt;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class FlowKt__DelayKt$debounce$3 extends Lambda implements Function1<T, Long> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function1 f11691a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__DelayKt$debounce$3(Function1 function1) {
        super(1);
        this.f11691a = function1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Long invoke(T t2) {
        return Long.valueOf(DelayKt.m1615toDelayMillisLRDsOJo(((Duration) this.f11691a.invoke(t2)).m1532unboximpl()));
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Long invoke(Object obj) {
        return invoke((FlowKt__DelayKt$debounce$3) obj);
    }
}
