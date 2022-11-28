package kotlin.collections;

import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class CollectionsKt__CollectionsKt$binarySearchBy$1 extends Lambda implements Function1<T, Integer> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function1 f11064a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Comparable f11065b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Incorrect types in method signature: (Lkotlin/jvm/functions/Function1<-TT;+TK;>;TK;)V */
    public CollectionsKt__CollectionsKt$binarySearchBy$1(Function1 function1, Comparable comparable) {
        super(1);
        this.f11064a = function1;
        this.f11065b = comparable;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Integer invoke(T t2) {
        int compareValues;
        compareValues = ComparisonsKt__ComparisonsKt.compareValues((Comparable) this.f11064a.invoke(t2), this.f11065b);
        return Integer.valueOf(compareValues);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Integer invoke(Object obj) {
        return invoke((CollectionsKt__CollectionsKt$binarySearchBy$1) obj);
    }
}
