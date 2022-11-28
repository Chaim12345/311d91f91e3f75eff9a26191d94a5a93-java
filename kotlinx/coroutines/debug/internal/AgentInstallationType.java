package kotlinx.coroutines.debug.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class AgentInstallationType {
    @NotNull
    public static final AgentInstallationType INSTANCE = new AgentInstallationType();
    private static boolean isInstalledStatically;

    private AgentInstallationType() {
    }

    public final boolean isInstalledStatically$kotlinx_coroutines_core() {
        return isInstalledStatically;
    }

    public final void setInstalledStatically$kotlinx_coroutines_core(boolean z) {
        isInstalledStatically = z;
    }
}
