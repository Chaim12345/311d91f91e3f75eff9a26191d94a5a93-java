package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public abstract class Receive<E> extends LockFreeLinkedListNode implements ReceiveOrClosed<E> {
    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    @NotNull
    public Symbol getOfferResult() {
        return AbstractChannelKt.OFFER_SUCCESS;
    }

    @Nullable
    public Function1<Throwable, Unit> resumeOnCancellationFun(E e2) {
        return null;
    }

    public abstract void resumeReceiveClosed(@NotNull Closed<?> closed);
}
