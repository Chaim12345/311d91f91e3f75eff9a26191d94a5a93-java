package kotlinx.coroutines.flow;

import kotlin.time.Duration;
import kotlinx.coroutines.flow.SharingStarted;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class SharingStartedKt {
    @NotNull
    /* renamed from: WhileSubscribed-5qebJ5I  reason: not valid java name */
    public static final SharingStarted m1661WhileSubscribed5qebJ5I(@NotNull SharingStarted.Companion companion, long j2, long j3) {
        return new StartedWhileSubscribed(Duration.m1496getInWholeMillisecondsimpl(j2), Duration.m1496getInWholeMillisecondsimpl(j3));
    }

    /* renamed from: WhileSubscribed-5qebJ5I$default  reason: not valid java name */
    public static /* synthetic */ SharingStarted m1662WhileSubscribed5qebJ5I$default(SharingStarted.Companion companion, long j2, long j3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = Duration.Companion.m1580getZEROUwyO8pc();
        }
        if ((i2 & 2) != 0) {
            j3 = Duration.Companion.m1578getINFINITEUwyO8pc();
        }
        return m1661WhileSubscribed5qebJ5I(companion, j2, j3);
    }
}
