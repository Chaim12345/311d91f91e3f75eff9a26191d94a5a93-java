package kotlin.sequences;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface DropTakeSequence<T> extends Sequence<T> {
    @NotNull
    Sequence<T> drop(int i2);

    @NotNull
    Sequence<T> take(int i2);
}
