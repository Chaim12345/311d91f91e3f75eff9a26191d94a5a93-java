package kotlinx.coroutines.channels;

import java.util.NoSuchElementException;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ClosedReceiveChannelException extends NoSuchElementException {
    public ClosedReceiveChannelException(@Nullable String str) {
        super(str);
    }
}
