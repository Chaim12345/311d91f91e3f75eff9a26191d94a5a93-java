package kotlinx.coroutines;

import kotlinx.coroutines.internal.MainDispatchersKt;
import kotlinx.coroutines.internal.SystemPropsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class DefaultExecutorKt {
    private static final boolean defaultMainDelayOptIn = SystemPropsKt.systemProp("kotlinx.coroutines.main.delay", false);
    @NotNull
    private static final Delay DefaultDelay = initializeDefaultDelay();

    @NotNull
    public static final Delay getDefaultDelay() {
        return DefaultDelay;
    }

    private static final Delay initializeDefaultDelay() {
        if (defaultMainDelayOptIn) {
            MainCoroutineDispatcher main = Dispatchers.getMain();
            return (MainDispatchersKt.isMissing(main) || !(main instanceof Delay)) ? DefaultExecutor.INSTANCE : (Delay) main;
        }
        return DefaultExecutor.INSTANCE;
    }
}
