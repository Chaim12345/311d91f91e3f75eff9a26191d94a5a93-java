package kotlin.sequences;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class SequencesKt___SequencesKt$minus$1$iterator$1 extends Lambda implements Function1<T, Boolean> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Ref.BooleanRef f11181a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Object f11182b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$minus$1$iterator$1(Ref.BooleanRef booleanRef, Object obj) {
        super(1);
        this.f11181a = booleanRef;
        this.f11182b = obj;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final Boolean invoke(T t2) {
        boolean z = true;
        if (!this.f11181a.element && Intrinsics.areEqual(t2, this.f11182b)) {
            this.f11181a.element = true;
            z = false;
        }
        return Boolean.valueOf(z);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Boolean invoke(Object obj) {
        return invoke((SequencesKt___SequencesKt$minus$1$iterator$1) obj);
    }
}
