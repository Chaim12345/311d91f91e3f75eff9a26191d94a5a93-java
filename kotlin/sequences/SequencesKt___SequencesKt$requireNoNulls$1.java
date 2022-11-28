package kotlin.sequences;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class SequencesKt___SequencesKt$requireNoNulls$1 extends Lambda implements Function1<T, T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Sequence f11194a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$requireNoNulls$1(Sequence sequence) {
        super(1);
        this.f11194a = sequence;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final T invoke(@Nullable T t2) {
        if (t2 != 0) {
            return t2;
        }
        throw new IllegalArgumentException("null element found in " + this.f11194a + '.');
    }
}
