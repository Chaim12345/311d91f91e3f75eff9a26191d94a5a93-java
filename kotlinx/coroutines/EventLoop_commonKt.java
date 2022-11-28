package kotlinx.coroutines;

import kotlin.jvm.internal.LongCompanionObject;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class EventLoop_commonKt {
    private static final long MAX_DELAY_NS = 4611686018427387903L;
    private static final long MAX_MS = 9223372036854L;
    private static final long MS_TO_NS = 1000000;
    private static final int SCHEDULE_COMPLETED = 1;
    private static final int SCHEDULE_DISPOSED = 2;
    private static final int SCHEDULE_OK = 0;
    @NotNull
    private static final Symbol DISPOSED_TASK = new Symbol("REMOVED_TASK");
    @NotNull
    private static final Symbol CLOSED_EMPTY = new Symbol("CLOSED_EMPTY");

    public static final long delayNanosToMillis(long j2) {
        return j2 / MS_TO_NS;
    }

    public static final long delayToNanos(long j2) {
        if (j2 <= 0) {
            return 0L;
        }
        return j2 >= MAX_MS ? LongCompanionObject.MAX_VALUE : MS_TO_NS * j2;
    }

    private static /* synthetic */ void getCLOSED_EMPTY$annotations() {
    }

    private static /* synthetic */ void getDISPOSED_TASK$annotations() {
    }
}
