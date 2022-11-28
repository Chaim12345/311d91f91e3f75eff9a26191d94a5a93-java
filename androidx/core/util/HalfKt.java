package androidx.core.util;

import android.util.Half;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0010\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u0087\b\u001a\r\u0010\u0002\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0002\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u001a\r\u0010\u0002\u001a\u00020\u0001*\u00020\u0005H\u0087\b¨\u0006\u0006"}, d2 = {"", "Landroid/util/Half;", "toHalf", "", "", "", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class HalfKt {
    @RequiresApi(26)
    @NotNull
    public static final Half toHalf(double d2) {
        Half valueOf = Half.valueOf((float) d2);
        Intrinsics.checkNotNullExpressionValue(valueOf, "Half.valueOf(this)");
        return valueOf;
    }

    @RequiresApi(26)
    @NotNull
    public static final Half toHalf(float f2) {
        Half valueOf = Half.valueOf(f2);
        Intrinsics.checkNotNullExpressionValue(valueOf, "Half.valueOf(this)");
        return valueOf;
    }

    @RequiresApi(26)
    @NotNull
    public static final Half toHalf(@NotNull String toHalf) {
        Intrinsics.checkNotNullParameter(toHalf, "$this$toHalf");
        Half valueOf = Half.valueOf(toHalf);
        Intrinsics.checkNotNullExpressionValue(valueOf, "Half.valueOf(this)");
        return valueOf;
    }

    @RequiresApi(26)
    @NotNull
    public static final Half toHalf(short s2) {
        Half valueOf = Half.valueOf(s2);
        Intrinsics.checkNotNullExpressionValue(valueOf, "Half.valueOf(this)");
        return valueOf;
    }
}
