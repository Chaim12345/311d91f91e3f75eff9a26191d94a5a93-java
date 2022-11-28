package kotlinx.serialization.json.internal;

import java.util.Iterator;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.json.DecodeSequenceMode;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class JsonIteratorArrayWrapped<T> implements Iterator<T>, KMappedMarker {
    @NotNull
    private final DeserializationStrategy<T> deserializer;
    private boolean first;
    @NotNull
    private final Json json;
    @NotNull
    private final ReaderJsonLexer lexer;

    public JsonIteratorArrayWrapped(@NotNull Json json, @NotNull ReaderJsonLexer lexer, @NotNull DeserializationStrategy<T> deserializer) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        this.json = json;
        this.lexer = lexer;
        this.deserializer = deserializer;
        this.first = true;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.lexer.peekNextToken() != 9) {
            if (this.lexer.isNotEof()) {
                return true;
            }
            this.lexer.fail$kotlinx_serialization_json((byte) 9);
            throw new KotlinNothingValueException();
        }
        this.lexer.consumeNextToken((byte) 9);
        if (this.lexer.isNotEof()) {
            if (this.lexer.peekNextToken() == 8) {
                ReaderJsonLexer readerJsonLexer = this.lexer;
                AbstractJsonLexer.fail$default(readerJsonLexer, "There is a start of the new array after the one parsed to sequence. " + DecodeSequenceMode.ARRAY_WRAPPED.name() + " mode doesn't merge consecutive arrays.\nIf you need to parse a stream of arrays, please use " + DecodeSequenceMode.WHITESPACE_SEPARATED.name() + " mode instead.", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            this.lexer.expectEof();
        }
        return false;
    }

    @Override // java.util.Iterator
    public T next() {
        if (this.first) {
            this.first = false;
        } else {
            this.lexer.consumeNextToken(AbstractJsonLexerKt.COMMA);
        }
        return (T) new StreamingJsonDecoder(this.json, WriteMode.OBJ, this.lexer, this.deserializer.getDescriptor()).decodeSerializableValue(this.deserializer);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
