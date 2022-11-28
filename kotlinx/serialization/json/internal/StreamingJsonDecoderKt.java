package kotlinx.serialization.json.internal;

import kotlin.KotlinNothingValueException;
import kotlin.jvm.functions.Function1;
/* loaded from: classes3.dex */
public final class StreamingJsonDecoderKt {
    private static final <T> T parseString(AbstractJsonLexer abstractJsonLexer, String str, Function1<? super String, ? extends T> function1) {
        String consumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            return function1.invoke(consumeStringLenient);
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type '" + str + "' for input '" + consumeStringLenient + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
    }
}
