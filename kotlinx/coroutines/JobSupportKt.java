package kotlinx.coroutines;

import kotlin.jvm.JvmField;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class JobSupportKt {
    private static final int FALSE = 0;
    private static final int RETRY = -1;
    private static final int TRUE = 1;
    @NotNull
    private static final Symbol COMPLETING_ALREADY = new Symbol("COMPLETING_ALREADY");
    @JvmField
    @NotNull
    public static final Symbol COMPLETING_WAITING_CHILDREN = new Symbol("COMPLETING_WAITING_CHILDREN");
    @NotNull
    private static final Symbol COMPLETING_RETRY = new Symbol("COMPLETING_RETRY");
    @NotNull
    private static final Symbol TOO_LATE_TO_CANCEL = new Symbol("TOO_LATE_TO_CANCEL");
    @NotNull
    private static final Symbol SEALED = new Symbol("SEALED");
    @NotNull
    private static final Empty EMPTY_NEW = new Empty(false);
    @NotNull
    private static final Empty EMPTY_ACTIVE = new Empty(true);

    @Nullable
    public static final Object boxIncomplete(@Nullable Object obj) {
        return obj instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj) : obj;
    }

    private static /* synthetic */ void getCOMPLETING_ALREADY$annotations() {
    }

    private static /* synthetic */ void getCOMPLETING_RETRY$annotations() {
    }

    public static /* synthetic */ void getCOMPLETING_WAITING_CHILDREN$annotations() {
    }

    private static /* synthetic */ void getEMPTY_ACTIVE$annotations() {
    }

    private static /* synthetic */ void getEMPTY_NEW$annotations() {
    }

    private static /* synthetic */ void getSEALED$annotations() {
    }

    private static /* synthetic */ void getTOO_LATE_TO_CANCEL$annotations() {
    }

    @Nullable
    public static final Object unboxState(@Nullable Object obj) {
        Incomplete incomplete;
        IncompleteStateBox incompleteStateBox = obj instanceof IncompleteStateBox ? (IncompleteStateBox) obj : null;
        return (incompleteStateBox == null || (incomplete = incompleteStateBox.state) == null) ? obj : incomplete;
    }
}
