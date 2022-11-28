package kotlinx.coroutines;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class Active implements NotCompleted {
    @NotNull
    public static final Active INSTANCE = new Active();

    private Active() {
    }

    @NotNull
    public String toString() {
        return "Active";
    }
}
