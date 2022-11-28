package kotlin.sequences;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class SequencesKt___SequencesKt$onEach$1 extends Lambda implements Function1<T, T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function1 f11192a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$onEach$1(Function1 function1) {
        super(1);
        this.f11192a = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final T invoke(T t2) {
        this.f11192a.invoke(t2);
        return t2;
    }
}
