package kotlin.sequences;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class SequencesKt___SequencesKt$onEachIndexed$1 extends Lambda implements Function2<Integer, T, T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function2 f11193a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$onEachIndexed$1(Function2 function2) {
        super(2);
        this.f11193a = function2;
    }

    public final T invoke(int i2, T t2) {
        this.f11193a.invoke(Integer.valueOf(i2), t2);
        return t2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Integer num, Object obj) {
        return invoke(num.intValue(), (int) obj);
    }
}
