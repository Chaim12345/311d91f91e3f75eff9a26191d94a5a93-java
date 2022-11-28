package kotlinx.coroutines.selects;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.selects.WhileSelectKt", f = "WhileSelect.kt", i = {0}, l = {37}, m = "whileSelect", n = {"builder"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class WhileSelectKt$whileSelect$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f12384a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f12385b;

    /* renamed from: c  reason: collision with root package name */
    int f12386c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WhileSelectKt$whileSelect$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f12385b = obj;
        this.f12386c |= Integer.MIN_VALUE;
        return WhileSelectKt.whileSelect(null, this);
    }
}
