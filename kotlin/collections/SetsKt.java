package kotlin.collections;

import java.util.Set;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class SetsKt extends SetsKt___SetsKt {
    private SetsKt() {
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static /* bridge */ /* synthetic */ Set build(@NotNull Set set) {
        return SetsKt__SetsJVMKt.build(set);
    }

    @SinceKotlin(version = "1.3")
    @PublishedApi
    @NotNull
    public static /* bridge */ /* synthetic */ Set createSetBuilder(int i2) {
        return SetsKt__SetsJVMKt.createSetBuilder(i2);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Set emptySet() {
        return SetsKt__SetsKt.emptySet();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Set optimizeReadOnlySet(@NotNull Set set) {
        return SetsKt__SetsKt.optimizeReadOnlySet(set);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Set setOf(Object obj) {
        return SetsKt__SetsJVMKt.setOf(obj);
    }
}
