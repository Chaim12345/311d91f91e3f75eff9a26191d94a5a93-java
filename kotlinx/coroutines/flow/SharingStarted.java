package kotlinx.coroutines.flow;

import kotlin.jvm.internal.LongCompanionObject;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface SharingStarted {
    @NotNull
    public static final Companion Companion = Companion.f12208a;

    /* loaded from: classes3.dex */
    public static final class Companion {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ Companion f12208a = new Companion();
        @NotNull
        private static final SharingStarted Eagerly = new StartedEagerly();
        @NotNull
        private static final SharingStarted Lazily = new StartedLazily();

        private Companion() {
        }

        public static /* synthetic */ SharingStarted WhileSubscribed$default(Companion companion, long j2, long j3, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = 0;
            }
            if ((i2 & 2) != 0) {
                j3 = LongCompanionObject.MAX_VALUE;
            }
            return companion.WhileSubscribed(j2, j3);
        }

        @NotNull
        public final SharingStarted WhileSubscribed(long j2, long j3) {
            return new StartedWhileSubscribed(j2, j3);
        }

        @NotNull
        public final SharingStarted getEagerly() {
            return Eagerly;
        }

        @NotNull
        public final SharingStarted getLazily() {
            return Lazily;
        }
    }

    @NotNull
    Flow<SharingCommand> command(@NotNull StateFlow<Integer> stateFlow);
}
