package kotlinx.serialization.json.internal;

import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class CharArrayPool {
    private static final int MAX_CHARS_IN_POOL;
    private static int charsTotal;
    @NotNull
    public static final CharArrayPool INSTANCE = new CharArrayPool();
    @NotNull
    private static final ArrayDeque<char[]> arrays = new ArrayDeque<>();

    static {
        Object m187constructorimpl;
        Integer intOrNull;
        try {
            Result.Companion companion = Result.Companion;
            String property = System.getProperty("kotlinx.serialization.json.pool.size");
            Intrinsics.checkNotNullExpressionValue(property, "getProperty(\"kotlinx.ser…lization.json.pool.size\")");
            intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(property);
            m187constructorimpl = Result.m187constructorimpl(intOrNull);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            m187constructorimpl = Result.m187constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m193isFailureimpl(m187constructorimpl)) {
            m187constructorimpl = null;
        }
        Integer num = (Integer) m187constructorimpl;
        MAX_CHARS_IN_POOL = num == null ? 1048576 : num.intValue();
    }

    private CharArrayPool() {
    }

    public final void release(@NotNull char[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        synchronized (this) {
            int i2 = charsTotal;
            if (array.length + i2 < MAX_CHARS_IN_POOL) {
                charsTotal = i2 + array.length;
                arrays.addLast(array);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @NotNull
    public final char[] take() {
        char[] removeLastOrNull;
        synchronized (this) {
            removeLastOrNull = arrays.removeLastOrNull();
            if (removeLastOrNull == null) {
                removeLastOrNull = null;
            } else {
                charsTotal -= removeLastOrNull.length;
            }
        }
        return removeLastOrNull == null ? new char[128] : removeLastOrNull;
    }
}
