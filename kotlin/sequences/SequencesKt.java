package kotlin.sequences;

import java.util.Comparator;
import java.util.List;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class SequencesKt extends SequencesKt___SequencesKt {
    private SequencesKt() {
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Sequence filter(@NotNull Sequence sequence, @NotNull Function1 function1) {
        return SequencesKt___SequencesKt.filter(sequence, function1);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Sequence mapNotNull(@NotNull Sequence sequence, @NotNull Function1 function1) {
        return SequencesKt___SequencesKt.mapNotNull(sequence, function1);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Sequence sortedWith(@NotNull Sequence sequence, @NotNull Comparator comparator) {
        return SequencesKt___SequencesKt.sortedWith(sequence, comparator);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ List toList(@NotNull Sequence sequence) {
        return SequencesKt___SequencesKt.toList(sequence);
    }
}
