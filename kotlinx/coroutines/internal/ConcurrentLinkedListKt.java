package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ConcurrentLinkedListKt {
    @NotNull
    private static final Symbol CLOSED = new Symbol("CLOSED");
    private static final int POINTERS_SHIFT = 16;

    public static final /* synthetic */ Symbol access$getCLOSED$p() {
        return CLOSED;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [kotlinx.coroutines.internal.ConcurrentLinkedListNode] */
    @NotNull
    public static final <N extends ConcurrentLinkedListNode<N>> N close(@NotNull N n2) {
        while (true) {
            Object nextOrClosed = n2.getNextOrClosed();
            if (nextOrClosed == CLOSED) {
                return n2;
            }
            ?? r0 = (ConcurrentLinkedListNode) nextOrClosed;
            if (r0 != 0) {
                n2 = r0;
            } else if (n2.markAsClosed()) {
                return n2;
            }
        }
    }

    private static final <S extends Segment<S>> Object findSegmentInternal(S s2, long j2, Function2<? super Long, ? super S, ? extends S> function2) {
        while (true) {
            if (s2.getId() >= j2 && !s2.getRemoved()) {
                return SegmentOrClosed.m1674constructorimpl(s2);
            }
            Object nextOrClosed = s2.getNextOrClosed();
            if (nextOrClosed == CLOSED) {
                return SegmentOrClosed.m1674constructorimpl(CLOSED);
            }
            S s3 = (S) ((ConcurrentLinkedListNode) nextOrClosed);
            if (s3 == null) {
                s3 = function2.invoke(Long.valueOf(s2.getId() + 1), s2);
                if (s2.trySetNext(s3)) {
                    if (s2.getRemoved()) {
                        s2.remove();
                    }
                }
            }
            s2 = s3;
        }
    }

    private static /* synthetic */ void getCLOSED$annotations() {
    }
}
