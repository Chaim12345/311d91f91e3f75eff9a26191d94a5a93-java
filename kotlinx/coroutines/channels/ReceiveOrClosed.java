package kotlinx.coroutines.channels;

import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public interface ReceiveOrClosed<E> {
    void completeResumeReceive(E e2);

    @NotNull
    Object getOfferResult();

    @Nullable
    Symbol tryResumeReceive(E e2, @Nullable LockFreeLinkedListNode.PrepareOp prepareOp);
}
