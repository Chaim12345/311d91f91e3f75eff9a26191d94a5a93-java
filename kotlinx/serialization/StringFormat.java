package kotlinx.serialization;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface StringFormat extends SerialFormat {
    <T> T decodeFromString(@NotNull DeserializationStrategy<T> deserializationStrategy, @NotNull String str);

    @NotNull
    <T> String encodeToString(@NotNull SerializationStrategy<? super T> serializationStrategy, T t2);
}
