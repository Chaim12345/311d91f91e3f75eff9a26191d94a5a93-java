package kotlin.sequences;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SequencesKt__SequencesKt$generateSequence$1 extends Lambda implements Function1<T, T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function0 f11164a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt__SequencesKt$generateSequence$1(Function0 function0) {
        super(1);
        this.f11164a = function0;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [T, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final T invoke(@NotNull T it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return this.f11164a.invoke();
    }
}
