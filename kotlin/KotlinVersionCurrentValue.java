package kotlin;

import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class KotlinVersionCurrentValue {
    @NotNull
    public static final KotlinVersionCurrentValue INSTANCE = new KotlinVersionCurrentValue();

    private KotlinVersionCurrentValue() {
    }

    @JvmStatic
    @NotNull
    public static final KotlinVersion get() {
        return new KotlinVersion(1, 6, 21);
    }
}
