package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlinx.coroutines.CancelHandler;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class CancelSemaphoreAcquisitionHandler extends CancelHandler {
    private final int index;
    @NotNull
    private final SemaphoreSegment segment;

    public CancelSemaphoreAcquisitionHandler(@NotNull SemaphoreSegment semaphoreSegment, int i2) {
        this.segment = semaphoreSegment;
        this.index = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable th) {
        this.segment.cancel(this.index);
    }

    @NotNull
    public String toString() {
        return "CancelSemaphoreAcquisitionHandler[" + this.segment + ", " + this.index + AbstractJsonLexerKt.END_LIST;
    }
}
