package kotlinx.serialization.json.internal;

import java.util.Iterator;
import kotlin.KotlinNothingValueException;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.json.DecodeSequenceMode;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class JsonIteratorKt {

    /* loaded from: classes3.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DecodeSequenceMode.values().length];
            iArr[DecodeSequenceMode.WHITESPACE_SEPARATED.ordinal()] = 1;
            iArr[DecodeSequenceMode.ARRAY_WRAPPED.ordinal()] = 2;
            iArr[DecodeSequenceMode.AUTO_DETECT.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @NotNull
    public static final <T> Iterator<T> JsonIterator(@NotNull DecodeSequenceMode mode, @NotNull Json json, @NotNull ReaderJsonLexer lexer, @NotNull DeserializationStrategy<T> deserializer) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        int i2 = WhenMappings.$EnumSwitchMapping$0[determineFormat(lexer, mode).ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                throw new IllegalStateException("AbstractJsonLexer.determineFormat must be called beforehand.".toString());
            }
            return new JsonIteratorArrayWrapped(json, lexer, deserializer);
        }
        return new JsonIteratorWsSeparated(json, lexer, deserializer);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0015, code lost:
        if (tryConsumeStartArray(r1) != false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final DecodeSequenceMode determineFormat(AbstractJsonLexer abstractJsonLexer, DecodeSequenceMode decodeSequenceMode) {
        int i2 = WhenMappings.$EnumSwitchMapping$0[decodeSequenceMode.ordinal()];
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    throw new NoWhenBranchMatchedException();
                }
            } else if (!tryConsumeStartArray(abstractJsonLexer)) {
                abstractJsonLexer.fail$kotlinx_serialization_json((byte) 8);
                throw new KotlinNothingValueException();
            }
            return DecodeSequenceMode.ARRAY_WRAPPED;
        }
        return DecodeSequenceMode.WHITESPACE_SEPARATED;
    }

    private static final boolean tryConsumeStartArray(AbstractJsonLexer abstractJsonLexer) {
        if (abstractJsonLexer.peekNextToken() == 8) {
            abstractJsonLexer.consumeNextToken((byte) 8);
            return true;
        }
        return false;
    }
}
