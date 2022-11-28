package androidx.palette.graphics;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.palette.graphics.Palette;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\n¨\u0006\u0005"}, d2 = {"Landroidx/palette/graphics/Palette;", "Landroidx/palette/graphics/Target;", TypedValues.Attributes.S_TARGET, "Landroidx/palette/graphics/Palette$Swatch;", "get", "palette-ktx_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class PaletteKt {
    @Nullable
    public static final Palette.Swatch get(@NotNull Palette receiver, @NotNull Target target) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(target, "target");
        return receiver.getSwatchForTarget(target);
    }
}
