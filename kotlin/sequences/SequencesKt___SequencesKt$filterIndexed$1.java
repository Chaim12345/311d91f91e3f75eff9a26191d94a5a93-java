package kotlin.sequences;

import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class SequencesKt___SequencesKt$filterIndexed$1 extends Lambda implements Function1<IndexedValue<? extends T>, Boolean> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function2 f11176a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$filterIndexed$1(Function2 function2) {
        super(1);
        this.f11176a = function2;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Boolean invoke(@NotNull IndexedValue<? extends T> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return (Boolean) this.f11176a.invoke(Integer.valueOf(it.getIndex()), it.getValue());
    }
}
