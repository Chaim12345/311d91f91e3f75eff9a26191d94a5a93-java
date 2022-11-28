package kotlin.sequences;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class SequencesKt___SequencesKt$elementAt$1 extends Lambda implements Function1<Integer, T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f11175a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$elementAt$1(int i2) {
        super(1);
        this.f11175a = i2;
    }

    public final T invoke(int i2) {
        throw new IndexOutOfBoundsException("Sequence doesn't contain element at index " + this.f11175a + '.');
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Integer num) {
        return invoke(num.intValue());
    }
}
