package kotlinx.coroutines;

import java.lang.Throwable;
import kotlinx.coroutines.CopyableThrowable;
import org.jetbrains.annotations.Nullable;
@ExperimentalCoroutinesApi
/* loaded from: classes3.dex */
public interface CopyableThrowable<T extends Throwable & CopyableThrowable<T>> {
    @Nullable
    T createCopy();
}
