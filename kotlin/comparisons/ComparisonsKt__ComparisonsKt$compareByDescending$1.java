package kotlin.comparisons;

import java.util.Comparator;
import kotlin.jvm.functions.Function1;
/* loaded from: classes3.dex */
public final class ComparisonsKt__ComparisonsKt$compareByDescending$1<T> implements Comparator {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function1 f11100a;

    public ComparisonsKt__ComparisonsKt$compareByDescending$1(Function1<? super T, ? extends Comparable<?>> function1) {
        this.f11100a = function1;
    }

    @Override // java.util.Comparator
    public final int compare(T t2, T t3) {
        int compareValues;
        Function1 function1 = this.f11100a;
        compareValues = ComparisonsKt__ComparisonsKt.compareValues((Comparable) function1.invoke(t3), (Comparable) function1.invoke(t2));
        return compareValues;
    }
}
