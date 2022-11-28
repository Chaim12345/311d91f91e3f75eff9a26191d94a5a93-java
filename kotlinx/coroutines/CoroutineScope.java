package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface CoroutineScope {
    @NotNull
    CoroutineContext getCoroutineContext();
}
