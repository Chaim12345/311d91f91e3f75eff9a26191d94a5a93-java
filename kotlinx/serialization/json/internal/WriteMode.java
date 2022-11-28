package kotlinx.serialization.json.internal;

import kotlin.jvm.JvmField;
/* loaded from: classes3.dex */
public enum WriteMode {
    OBJ(AbstractJsonLexerKt.BEGIN_OBJ, AbstractJsonLexerKt.END_OBJ),
    LIST(AbstractJsonLexerKt.BEGIN_LIST, AbstractJsonLexerKt.END_LIST),
    MAP(AbstractJsonLexerKt.BEGIN_OBJ, AbstractJsonLexerKt.END_OBJ),
    POLY_OBJ(AbstractJsonLexerKt.BEGIN_LIST, AbstractJsonLexerKt.END_LIST);
    
    @JvmField
    public final char begin;
    @JvmField
    public final char end;

    WriteMode(char c2, char c3) {
        this.begin = c2;
        this.end = c3;
    }
}
