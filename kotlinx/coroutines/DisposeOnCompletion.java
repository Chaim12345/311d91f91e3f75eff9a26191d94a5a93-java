package kotlinx.coroutines;

import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DisposeOnCompletion extends JobNode {
    @NotNull
    private final DisposableHandle handle;

    public DisposeOnCompletion(@NotNull DisposableHandle disposableHandle) {
        this.handle = disposableHandle;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        this.handle.dispose();
    }
}
