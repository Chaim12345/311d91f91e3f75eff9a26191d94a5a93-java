package kotlinx.coroutines.debug.internal;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class HashedWeakRef<T> extends WeakReference<T> {
    @JvmField
    public final int hash;

    public HashedWeakRef(T t2, @Nullable ReferenceQueue<T> referenceQueue) {
        super(t2, referenceQueue);
        this.hash = t2 != null ? t2.hashCode() : 0;
    }
}
