package okhttp3.internal.cache;

import okio.Sink;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface CacheRequest {
    void abort();

    @NotNull
    Sink body();
}
