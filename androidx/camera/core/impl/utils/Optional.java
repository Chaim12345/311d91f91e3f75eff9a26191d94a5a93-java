package androidx.camera.core.impl.utils;

import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import androidx.core.util.Supplier;
import java.io.Serializable;
/* loaded from: classes.dex */
public abstract class Optional<T> implements Serializable {
    private static final long serialVersionUID = 0;

    public static <T> Optional<T> absent() {
        return Absent.a();
    }

    public static <T> Optional<T> fromNullable(@Nullable T t2) {
        return t2 == null ? absent() : new Present(t2);
    }

    public static <T> Optional<T> of(T t2) {
        return new Present(Preconditions.checkNotNull(t2));
    }

    public abstract boolean equals(@Nullable Object obj);

    public abstract T get();

    public abstract int hashCode();

    public abstract boolean isPresent();

    public abstract Optional<T> or(Optional<? extends T> optional);

    public abstract T or(Supplier<? extends T> supplier);

    public abstract T or(T t2);

    @Nullable
    public abstract T orNull();

    public abstract String toString();
}
