package kotlinx.serialization.json.internal;

import com.fasterxml.jackson.core.JsonPointer;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class CharMappings {
    @JvmField
    @NotNull
    public static final byte[] CHAR_TO_TOKEN;
    @JvmField
    @NotNull
    public static final char[] ESCAPE_2_CHAR;
    @NotNull
    public static final CharMappings INSTANCE;

    static {
        CharMappings charMappings = new CharMappings();
        INSTANCE = charMappings;
        ESCAPE_2_CHAR = new char[117];
        CHAR_TO_TOKEN = new byte[126];
        charMappings.initEscape();
        charMappings.initCharToToken();
    }

    private CharMappings() {
    }

    private final void initC2ESC(char c2, char c3) {
        initC2ESC((int) c2, c3);
    }

    private final void initC2ESC(int i2, char c2) {
        if (c2 != 'u') {
            ESCAPE_2_CHAR[c2] = (char) i2;
        }
    }

    private final void initC2TC(char c2, byte b2) {
        initC2TC((int) c2, b2);
    }

    private final void initC2TC(int i2, byte b2) {
        CHAR_TO_TOKEN[i2] = b2;
    }

    private final void initCharToToken() {
        for (int i2 = 0; i2 < 33; i2++) {
            initC2TC(i2, Byte.MAX_VALUE);
        }
        initC2TC(9, (byte) 3);
        initC2TC(10, (byte) 3);
        initC2TC(13, (byte) 3);
        initC2TC(32, (byte) 3);
        initC2TC(AbstractJsonLexerKt.COMMA, (byte) 4);
        initC2TC(AbstractJsonLexerKt.COLON, (byte) 5);
        initC2TC(AbstractJsonLexerKt.BEGIN_OBJ, (byte) 6);
        initC2TC(AbstractJsonLexerKt.END_OBJ, (byte) 7);
        initC2TC(AbstractJsonLexerKt.BEGIN_LIST, (byte) 8);
        initC2TC(AbstractJsonLexerKt.END_LIST, (byte) 9);
        initC2TC('\"', (byte) 1);
        initC2TC('\\', (byte) 2);
    }

    private final void initEscape() {
        for (int i2 = 0; i2 < 32; i2++) {
            initC2ESC(i2, AbstractJsonLexerKt.UNICODE_ESC);
        }
        initC2ESC(8, 'b');
        initC2ESC(9, 't');
        initC2ESC(10, 'n');
        initC2ESC(12, 'f');
        initC2ESC(13, 'r');
        initC2ESC(JsonPointer.SEPARATOR, JsonPointer.SEPARATOR);
        initC2ESC('\"', '\"');
        initC2ESC('\\', '\\');
    }
}
