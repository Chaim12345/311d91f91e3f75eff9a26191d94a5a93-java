package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Unit;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class CancelFutureOnCancel extends CancelHandler {
    @NotNull
    private final Future<?> future;

    public CancelFutureOnCancel(@NotNull Future<?> future) {
        this.future = future;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        if (th != null) {
            this.future.cancel(false);
        }
    }

    @NotNull
    public String toString() {
        return "CancelFutureOnCancel[" + this.future + AbstractJsonLexerKt.END_LIST;
    }
}
