package com.psa.mym.mycitroenconnect.utils.event_bus;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GlobalBusUtil {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private static EventBus bus;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final EventBus getBus() {
            return GlobalBusUtil.bus;
        }

        @NotNull
        public final EventBus optBus() {
            if (getBus() == null) {
                setBus(EventBus.getDefault());
            }
            EventBus bus = getBus();
            Intrinsics.checkNotNull(bus);
            return bus;
        }

        public final void setBus(@Nullable EventBus eventBus) {
            GlobalBusUtil.bus = eventBus;
        }
    }
}
