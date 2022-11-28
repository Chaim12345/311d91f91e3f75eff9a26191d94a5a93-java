package kotlinx.serialization.internal;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
@Deprecated(level = DeprecationLevel.HIDDEN, message = "Inserted into generated code and should not be used directly")
/* loaded from: classes3.dex */
public interface SerializerFactory {
    @NotNull
    KSerializer<?> serializer(@NotNull KSerializer<?>... kSerializerArr);
}
