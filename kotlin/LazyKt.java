package kotlin;

import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class LazyKt extends LazyKt__LazyKt {
    private LazyKt() {
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Lazy lazy(@NotNull LazyThreadSafetyMode lazyThreadSafetyMode, @NotNull Function0 function0) {
        return LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, function0);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Lazy lazy(@NotNull Function0 function0) {
        return LazyKt__LazyJVMKt.lazy(function0);
    }
}
