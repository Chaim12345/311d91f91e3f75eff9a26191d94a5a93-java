package kotlin.sequences;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.collections.CollectionsKt__IteratorsJVMKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
/* loaded from: classes3.dex */
class SequencesKt__SequencesJVMKt extends SequencesKt__SequenceBuilderKt {
    @InlineOnly
    private static final <T> Sequence<T> asSequence(Enumeration<T> enumeration) {
        Iterator it;
        Sequence<T> asSequence;
        Intrinsics.checkNotNullParameter(enumeration, "<this>");
        it = CollectionsKt__IteratorsJVMKt.iterator(enumeration);
        asSequence = SequencesKt__SequencesKt.asSequence(it);
        return asSequence;
    }
}
