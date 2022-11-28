package kotlinx.serialization;

import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public class SerializationException extends IllegalArgumentException {
    public SerializationException() {
    }

    public SerializationException(@Nullable String str) {
        super(str);
    }

    public SerializationException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }

    public SerializationException(@Nullable Throwable th) {
        super(th);
    }
}
