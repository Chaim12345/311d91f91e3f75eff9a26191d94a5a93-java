package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class SequencesKt__SequencesKt$flatten$1 extends Lambda implements Function1<Sequence<? extends T>, Iterator<? extends T>> {
    public static final SequencesKt__SequencesKt$flatten$1 INSTANCE = new SequencesKt__SequencesKt$flatten$1();

    SequencesKt__SequencesKt$flatten$1() {
        super(1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Iterator<T> invoke(@NotNull Sequence<? extends T> it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.iterator();
    }
}
