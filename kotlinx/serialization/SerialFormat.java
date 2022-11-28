package kotlinx.serialization;

import kotlinx.serialization.modules.SerializersModule;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface SerialFormat {
    @NotNull
    SerializersModule getSerializersModule();
}
