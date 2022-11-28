package kotlinx.serialization;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface BinaryFormat extends SerialFormat {
    <T> T decodeFromByteArray(@NotNull DeserializationStrategy<T> deserializationStrategy, @NotNull byte[] bArr);

    @NotNull
    <T> byte[] encodeToByteArray(@NotNull SerializationStrategy<? super T> serializationStrategy, T t2);
}
