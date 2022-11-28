package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SemaphoreSegment extends Segment<SemaphoreSegment> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    /* synthetic */ AtomicReferenceArray f12407a;

    public SemaphoreSegment(long j2, @Nullable SemaphoreSegment semaphoreSegment, int i2) {
        super(j2, semaphoreSegment, i2);
        int i3;
        i3 = SemaphoreKt.SEGMENT_SIZE;
        this.f12407a = new AtomicReferenceArray(i3);
    }

    public final void cancel(int i2) {
        Symbol symbol;
        symbol = SemaphoreKt.CANCELLED;
        this.f12407a.set(i2, symbol);
        onSlotCleaned();
    }

    public final boolean cas(int i2, @Nullable Object obj, @Nullable Object obj2) {
        return this.f12407a.compareAndSet(i2, obj, obj2);
    }

    @Nullable
    public final Object get(int i2) {
        return this.f12407a.get(i2);
    }

    @Nullable
    public final Object getAndSet(int i2, @Nullable Object obj) {
        return this.f12407a.getAndSet(i2, obj);
    }

    @Override // kotlinx.coroutines.internal.Segment
    public int getMaxSlots() {
        int i2;
        i2 = SemaphoreKt.SEGMENT_SIZE;
        return i2;
    }

    public final void set(int i2, @Nullable Object obj) {
        this.f12407a.set(i2, obj);
    }

    @NotNull
    public String toString() {
        return "SemaphoreSegment[id=" + getId() + ", hashCode=" + hashCode() + AbstractJsonLexerKt.END_LIST;
    }
}
