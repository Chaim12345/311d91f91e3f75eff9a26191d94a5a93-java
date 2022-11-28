package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class FlowKt__DistinctKt$defaultKeySelector$1 extends Lambda implements Function1<Object, Object> {
    public static final FlowKt__DistinctKt$defaultKeySelector$1 INSTANCE = new FlowKt__DistinctKt$defaultKeySelector$1();

    FlowKt__DistinctKt$defaultKeySelector$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final Object invoke(@Nullable Object obj) {
        return obj;
    }
}
