package okio;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u000f\u001a\u00020\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\u000f\u0010\u000f\u001a\u00020\u0001H\u0007¢\u0006\u0004\b\r\u0010\u000eR\u0019\u0010\u000f\u001a\u00020\u00018\u0007@\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u0013"}, d2 = {"Lokio/ForwardingSource;", "Lokio/Source;", "Lokio/Buffer;", "sink", "", "byteCount", "read", "Lokio/Timeout;", "timeout", "", "close", "", "toString", "-deprecated_delegate", "()Lokio/Source;", "delegate", "Lokio/Source;", "<init>", "(Lokio/Source;)V", "okio"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public abstract class ForwardingSource implements Source {
    @NotNull
    private final Source delegate;

    public ForwardingSource(@NotNull Source delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "delegate", imports = {}))
    @JvmName(name = "-deprecated_delegate")
    @NotNull
    /* renamed from: -deprecated_delegate  reason: not valid java name */
    public final Source m1850deprecated_delegate() {
        return this.delegate;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    @JvmName(name = "delegate")
    @NotNull
    public final Source delegate() {
        return this.delegate;
    }

    @Override // okio.Source
    public long read(@NotNull Buffer sink, long j2) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return this.delegate.read(sink, j2);
    }

    @Override // okio.Source
    @NotNull
    public Timeout timeout() {
        return this.delegate.timeout();
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) getClass().getSimpleName());
        sb.append('(');
        sb.append(this.delegate);
        sb.append(')');
        return sb.toString();
    }
}
