package kotlinx.serialization;

import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
@PublishedApi
/* loaded from: classes3.dex */
public final class UnknownFieldException extends SerializationException {
    public UnknownFieldException(int i2) {
        this(Intrinsics.stringPlus("An unknown field for index ", Integer.valueOf(i2)));
    }

    public UnknownFieldException(@Nullable String str) {
        super(str);
    }
}
