package kotlin.collections;

import java.util.List;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ArraysKt extends ArraysKt___ArraysKt {
    private ArraysKt() {
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static /* bridge */ /* synthetic */ byte[] copyInto(@NotNull byte[] bArr, @NotNull byte[] bArr2, int i2, int i3, int i4) {
        return ArraysKt___ArraysJvmKt.copyInto(bArr, bArr2, i2, i3, i4);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ Object getOrNull(@NotNull Object[] objArr, int i2) {
        return ArraysKt___ArraysKt.getOrNull(objArr, i2);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ List toList(@NotNull Object[] objArr) {
        return ArraysKt___ArraysKt.toList(objArr);
    }
}
