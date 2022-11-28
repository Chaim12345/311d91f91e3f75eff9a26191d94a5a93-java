package kotlin;

import kotlin.internal.AccessibleLateinitPropertyLiteral;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty0;
@JvmName(name = "LateinitKt")
/* loaded from: classes3.dex */
public final class LateinitKt {
    private static final boolean isInitialized(@AccessibleLateinitPropertyLiteral KProperty0<?> kProperty0) {
        Intrinsics.checkNotNullParameter(kProperty0, "<this>");
        throw new NotImplementedError("Implementation is intrinsic");
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static /* synthetic */ void isInitialized$annotations(KProperty0 kProperty0) {
    }
}
