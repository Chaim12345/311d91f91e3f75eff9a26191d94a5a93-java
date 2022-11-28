package kotlinx.serialization.json.internal;

import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class StringJsonLexer extends AbstractJsonLexer {
    @NotNull
    private final String source;

    public StringJsonLexer(@NotNull String source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean canConsumeValue() {
        int i2 = this.f12451a;
        if (i2 == -1) {
            return false;
        }
        while (i2 < getSource().length()) {
            char charAt = getSource().charAt(i2);
            if (charAt != ' ' && charAt != '\n' && charAt != '\r' && charAt != '\t') {
                this.f12451a = i2;
                return d(charAt);
            }
            i2++;
        }
        this.f12451a = i2;
        return false;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @NotNull
    public String consumeKeyString() {
        int indexOf$default;
        consumeNextToken('\"');
        int i2 = this.f12451a;
        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) getSource(), '\"', i2, false, 4, (Object) null);
        if (indexOf$default == -1) {
            fail$kotlinx_serialization_json((byte) 1);
            throw new KotlinNothingValueException();
        }
        int i3 = i2;
        while (i3 < indexOf$default) {
            int i4 = i3 + 1;
            if (getSource().charAt(i3) == '\\') {
                return b(getSource(), this.f12451a, i3);
            }
            i3 = i4;
        }
        this.f12451a = indexOf$default + 1;
        String substring = getSource().substring(i2, indexOf$default);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte consumeNextToken() {
        byte charToTokenClass;
        String source = getSource();
        do {
            int i2 = this.f12451a;
            if (i2 == -1 || i2 >= source.length()) {
                return (byte) 10;
            }
            int i3 = this.f12451a;
            this.f12451a = i3 + 1;
            charToTokenClass = AbstractJsonLexerKt.charToTokenClass(source.charAt(i3));
        } while (charToTokenClass == 3);
        return charToTokenClass;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public void consumeNextToken(char c2) {
        if (this.f12451a == -1) {
            e(c2);
        }
        String source = getSource();
        while (this.f12451a < source.length()) {
            int i2 = this.f12451a;
            this.f12451a = i2 + 1;
            char charAt = source.charAt(i2);
            if (charAt != ' ' && charAt != '\n' && charAt != '\r' && charAt != '\t') {
                if (charAt == c2) {
                    return;
                }
                e(c2);
            }
        }
        e(c2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @NotNull
    /* renamed from: f */
    public String getSource() {
        return this.source;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int prefetchOrEof(int i2) {
        if (i2 < getSource().length()) {
            return i2;
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int skipWhitespaces() {
        char charAt;
        int i2 = this.f12451a;
        if (i2 == -1) {
            return i2;
        }
        while (i2 < getSource().length() && ((charAt = getSource().charAt(i2)) == ' ' || charAt == '\n' || charAt == '\r' || charAt == '\t')) {
            i2++;
        }
        this.f12451a = i2;
        return i2;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean tryConsumeComma() {
        int skipWhitespaces = skipWhitespaces();
        if (skipWhitespaces == getSource().length() || skipWhitespaces == -1 || getSource().charAt(skipWhitespaces) != ',') {
            return false;
        }
        this.f12451a++;
        return true;
    }
}
