package kotlinx.serialization.json.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public abstract class AbstractJsonLexer {
    @JvmField

    /* renamed from: a  reason: collision with root package name */
    protected int f12451a;
    @NotNull
    private StringBuilder escapedString = new StringBuilder();
    @Nullable
    private String peekedString;

    private final int appendEsc(int i2) {
        int prefetchOrEof = prefetchOrEof(i2);
        if (prefetchOrEof == -1) {
            fail$default(this, "Expected escape sequence to continue, got EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int i3 = prefetchOrEof + 1;
        char charAt = getSource().charAt(prefetchOrEof);
        if (charAt == 'u') {
            return appendHex(getSource(), i3);
        }
        char escapeToChar = AbstractJsonLexerKt.escapeToChar(charAt);
        if (escapeToChar != 0) {
            this.escapedString.append(escapeToChar);
            return i3;
        }
        fail$default(this, "Invalid escaped char '" + charAt + '\'', 0, 2, null);
        throw new KotlinNothingValueException();
    }

    private final int appendEscape(int i2, int i3) {
        a(i2, i3);
        return appendEsc(i3 + 1);
    }

    private final int appendHex(CharSequence charSequence, int i2) {
        int i3 = i2 + 4;
        if (i3 < charSequence.length()) {
            this.escapedString.append((char) ((fromHexChar(charSequence, i2) << 12) + (fromHexChar(charSequence, i2 + 1) << 8) + (fromHexChar(charSequence, i2 + 2) << 4) + fromHexChar(charSequence, i2 + 3)));
            return i3;
        }
        this.f12451a = i2;
        ensureHaveChars();
        if (this.f12451a + 4 < charSequence.length()) {
            return appendHex(charSequence, this.f12451a);
        }
        fail$default(this, "Unexpected EOF during unicode escape", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    private final boolean consumeBoolean(int i2) {
        int prefetchOrEof = prefetchOrEof(i2);
        if (prefetchOrEof >= getSource().length() || prefetchOrEof == -1) {
            fail$default(this, "EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int i3 = prefetchOrEof + 1;
        int charAt = getSource().charAt(prefetchOrEof) | TokenParser.SP;
        if (charAt == 116) {
            consumeBooleanLiteral("rue", i3);
            return true;
        } else if (charAt == 102) {
            consumeBooleanLiteral("alse", i3);
            return false;
        } else {
            fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
    }

    private final void consumeBooleanLiteral(String str, int i2) {
        if (getSource().length() - i2 < str.length()) {
            fail$default(this, "Unexpected end of boolean literal", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int length = str.length();
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            if (str.charAt(i3) != (getSource().charAt(i3 + i2) | TokenParser.SP)) {
                fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, 2, null);
                throw new KotlinNothingValueException();
            }
            i3 = i4;
        }
        this.f12451a = i2 + str.length();
    }

    private final String decodedString(int i2, int i3) {
        a(i2, i3);
        String sb = this.escapedString.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "escapedString.toString()");
        this.escapedString.setLength(0);
        return sb;
    }

    public static /* synthetic */ Void fail$default(AbstractJsonLexer abstractJsonLexer, String str, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                i2 = abstractJsonLexer.f12451a;
            }
            return abstractJsonLexer.fail(str, i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
    }

    private final int fromHexChar(CharSequence charSequence, int i2) {
        char charAt = charSequence.charAt(i2);
        boolean z = true;
        if ('0' <= charAt && charAt < ':') {
            return charAt - '0';
        }
        char c2 = 'a';
        if (!('a' <= charAt && charAt < 'g')) {
            c2 = 'A';
            if ('A' > charAt || charAt >= 'G') {
                z = false;
            }
            if (!z) {
                fail$default(this, "Invalid toHexChar char '" + charAt + "' in unicode escape", 0, 2, null);
                throw new KotlinNothingValueException();
            }
        }
        return (charAt - c2) + 10;
    }

    public static /* synthetic */ void require$kotlinx_serialization_json$default(AbstractJsonLexer abstractJsonLexer, boolean z, int i2, Function0 message, int i3, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: require");
        }
        if ((i3 & 2) != 0) {
            i2 = abstractJsonLexer.f12451a;
        }
        Intrinsics.checkNotNullParameter(message, "message");
        if (z) {
            return;
        }
        abstractJsonLexer.fail((String) message.invoke(), i2);
        throw new KotlinNothingValueException();
    }

    private final String takePeeked() {
        String str = this.peekedString;
        Intrinsics.checkNotNull(str);
        this.peekedString = null;
        return str;
    }

    private final boolean wasUnquotedString() {
        return getSource().charAt(this.f12451a - 1) != '\"';
    }

    protected void a(int i2, int i3) {
        this.escapedString.append(getSource(), i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final String b(@NotNull CharSequence source, int i2, int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        char charAt = source.charAt(i3);
        boolean z = false;
        while (charAt != '\"') {
            if (charAt == '\\') {
                i2 = prefetchOrEof(appendEscape(i2, i3));
                if (i2 == -1) {
                    fail("EOF", i2);
                    throw new KotlinNothingValueException();
                }
            } else {
                i3++;
                if (i3 >= source.length()) {
                    a(i2, i3);
                    i2 = prefetchOrEof(i3);
                    if (i2 == -1) {
                        fail("EOF", i2);
                        throw new KotlinNothingValueException();
                    }
                } else {
                    continue;
                    charAt = source.charAt(i3);
                }
            }
            i3 = i2;
            z = true;
            charAt = source.charAt(i3);
        }
        String substring = !z ? substring(i2, i3) : decodedString(i2, i3);
        this.f12451a = i3 + 1;
        return substring;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final StringBuilder c() {
        return this.escapedString;
    }

    public abstract boolean canConsumeValue();

    public final boolean consumeBoolean() {
        return consumeBoolean(skipWhitespaces());
    }

    public final boolean consumeBooleanLenient() {
        boolean z;
        int skipWhitespaces = skipWhitespaces();
        if (skipWhitespaces == getSource().length()) {
            fail$default(this, "EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(skipWhitespaces) == '\"') {
            skipWhitespaces++;
            z = true;
        } else {
            z = false;
        }
        boolean consumeBoolean = consumeBoolean(skipWhitespaces);
        if (z) {
            if (this.f12451a == getSource().length()) {
                fail$default(this, "EOF", 0, 2, null);
                throw new KotlinNothingValueException();
            } else if (getSource().charAt(this.f12451a) != '\"') {
                fail$default(this, "Expected closing quotation mark", 0, 2, null);
                throw new KotlinNothingValueException();
            } else {
                this.f12451a++;
            }
        }
        return consumeBoolean;
    }

    @NotNull
    public abstract String consumeKeyString();

    public abstract byte consumeNextToken();

    public final byte consumeNextToken(byte b2) {
        byte consumeNextToken = consumeNextToken();
        if (consumeNextToken == b2) {
            return consumeNextToken;
        }
        fail$kotlinx_serialization_json(b2);
        throw new KotlinNothingValueException();
    }

    public void consumeNextToken(char c2) {
        ensureHaveChars();
        CharSequence source = getSource();
        int i2 = this.f12451a;
        while (true) {
            int prefetchOrEof = prefetchOrEof(i2);
            if (prefetchOrEof == -1) {
                this.f12451a = prefetchOrEof;
                e(c2);
                return;
            }
            int i3 = prefetchOrEof + 1;
            char charAt = source.charAt(prefetchOrEof);
            if (charAt != ' ' && charAt != '\n' && charAt != '\r' && charAt != '\t') {
                this.f12451a = i3;
                if (charAt == c2) {
                    return;
                }
                e(c2);
            }
            i2 = i3;
        }
    }

    public final long consumeNumericLiteral() {
        boolean z;
        int prefetchOrEof = prefetchOrEof(skipWhitespaces());
        Object obj = null;
        int i2 = 2;
        if (prefetchOrEof >= getSource().length() || prefetchOrEof == -1) {
            fail$default(this, "EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(prefetchOrEof) == '\"') {
            prefetchOrEof++;
            if (prefetchOrEof == getSource().length()) {
                fail$default(this, "EOF", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            z = true;
        } else {
            z = false;
        }
        int i3 = prefetchOrEof;
        boolean z2 = false;
        boolean z3 = true;
        long j2 = 0;
        while (z3) {
            char charAt = getSource().charAt(i3);
            if (charAt == '-') {
                if (i3 != prefetchOrEof) {
                    fail$default(this, "Unexpected symbol '-' in numeric literal", 0, i2, obj);
                    throw new KotlinNothingValueException();
                }
                i3++;
                z2 = true;
            } else if (AbstractJsonLexerKt.charToTokenClass(charAt) != 0) {
                break;
            } else {
                i3++;
                z3 = i3 != getSource().length();
                int i4 = charAt - '0';
                if (!(i4 >= 0 && i4 < 10)) {
                    fail$default(this, "Unexpected symbol '" + charAt + "' in numeric literal", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
                j2 = (j2 * 10) - i4;
                if (j2 > 0) {
                    fail$default(this, "Numeric value overflow", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
                obj = null;
                i2 = 2;
            }
        }
        if (prefetchOrEof == i3 || (z2 && prefetchOrEof == i3 - 1)) {
            fail$default(this, "Expected numeric literal", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        if (z) {
            if (!z3) {
                fail$default(this, "EOF", 0, 2, null);
                throw new KotlinNothingValueException();
            } else if (getSource().charAt(i3) != '\"') {
                fail$default(this, "Expected closing quotation mark", 0, 2, null);
                throw new KotlinNothingValueException();
            } else {
                i3++;
            }
        }
        this.f12451a = i3;
        if (z2) {
            return j2;
        }
        if (j2 != Long.MIN_VALUE) {
            return -j2;
        }
        fail$default(this, "Numeric value overflow", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    @NotNull
    public final String consumeString() {
        return this.peekedString != null ? takePeeked() : consumeKeyString();
    }

    @NotNull
    public final String consumeStringLenient() {
        if (this.peekedString != null) {
            return takePeeked();
        }
        int skipWhitespaces = skipWhitespaces();
        if (skipWhitespaces >= getSource().length() || skipWhitespaces == -1) {
            fail("EOF", skipWhitespaces);
            throw new KotlinNothingValueException();
        }
        byte charToTokenClass = AbstractJsonLexerKt.charToTokenClass(getSource().charAt(skipWhitespaces));
        if (charToTokenClass == 1) {
            return consumeString();
        }
        if (charToTokenClass != 0) {
            fail$default(this, Intrinsics.stringPlus("Expected beginning of the string, but got ", Character.valueOf(getSource().charAt(skipWhitespaces))), 0, 2, null);
            throw new KotlinNothingValueException();
        }
        boolean z = false;
        while (AbstractJsonLexerKt.charToTokenClass(getSource().charAt(skipWhitespaces)) == 0) {
            skipWhitespaces++;
            if (skipWhitespaces >= getSource().length()) {
                a(this.f12451a, skipWhitespaces);
                int prefetchOrEof = prefetchOrEof(skipWhitespaces);
                if (prefetchOrEof == -1) {
                    this.f12451a = skipWhitespaces;
                    return decodedString(0, 0);
                }
                skipWhitespaces = prefetchOrEof;
                z = true;
            }
        }
        int i2 = this.f12451a;
        String substring = !z ? substring(i2, skipWhitespaces) : decodedString(i2, skipWhitespaces);
        this.f12451a = skipWhitespaces;
        return substring;
    }

    @NotNull
    public final String consumeStringLenientNotNull() {
        String consumeStringLenient = consumeStringLenient();
        if (Intrinsics.areEqual(consumeStringLenient, "null") && wasUnquotedString()) {
            fail$default(this, "Unexpected 'null' value instead of string literal", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        return consumeStringLenient;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean d(char c2) {
        return !(((c2 == '}' || c2 == ']') || c2 == ':') || c2 == ',');
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void e(char c2) {
        int i2 = this.f12451a - 1;
        this.f12451a = i2;
        if (i2 >= 0 && c2 == '\"' && Intrinsics.areEqual(consumeStringLenient(), "null")) {
            fail("Expected string literal but 'null' literal was found.\nUse 'coerceInputValues = true' in 'Json {}` builder to coerce nulls to default values.", this.f12451a - 4);
            throw new KotlinNothingValueException();
        } else {
            fail$kotlinx_serialization_json(AbstractJsonLexerKt.charToTokenClass(c2));
            throw new KotlinNothingValueException();
        }
    }

    public void ensureHaveChars() {
    }

    public final void expectEof() {
        if (consumeNextToken() == 10) {
            return;
        }
        fail$default(this, "Expected EOF after parsing, but had " + getSource().charAt(this.f12451a - 1) + " instead", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    @NotNull
    public final Void fail(@NotNull String message, int i2) {
        Intrinsics.checkNotNullParameter(message, "message");
        throw JsonExceptionsKt.JsonDecodingException(i2, message, getSource());
    }

    @NotNull
    public final Void fail$kotlinx_serialization_json(byte b2) {
        String str = b2 == 1 ? "quotation mark '\"'" : b2 == 4 ? "comma ','" : b2 == 5 ? "semicolon ':'" : b2 == 6 ? "start of the object '{'" : b2 == 7 ? "end of the object '}'" : b2 == 8 ? "start of the array '['" : b2 == 9 ? "end of the array ']'" : "valid token";
        String valueOf = (this.f12451a == getSource().length() || this.f12451a <= 0) ? "EOF" : String.valueOf(getSource().charAt(this.f12451a - 1));
        fail("Expected " + str + ", but had '" + valueOf + "' instead", this.f12451a - 1);
        throw new KotlinNothingValueException();
    }

    public final void failOnUnknownKey(@NotNull String key) {
        int lastIndexOf$default;
        Intrinsics.checkNotNullParameter(key, "key");
        lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) substring(0, this.f12451a), key, 0, false, 6, (Object) null);
        fail("Encountered an unknown key '" + key + "'.\nUse 'ignoreUnknownKeys = true' in 'Json {}' builder to ignore unknown keys.", lastIndexOf$default);
        throw new KotlinNothingValueException();
    }

    @NotNull
    protected abstract CharSequence getSource();

    public int indexOf(char c2, int i2) {
        int indexOf$default;
        indexOf$default = StringsKt__StringsKt.indexOf$default(getSource(), c2, i2, false, 4, (Object) null);
        return indexOf$default;
    }

    public final boolean isNotEof() {
        return peekNextToken() != 10;
    }

    public final byte peekNextToken() {
        CharSequence source = getSource();
        int i2 = this.f12451a;
        while (true) {
            int prefetchOrEof = prefetchOrEof(i2);
            if (prefetchOrEof == -1) {
                this.f12451a = prefetchOrEof;
                return (byte) 10;
            }
            char charAt = source.charAt(prefetchOrEof);
            if (charAt != ' ' && charAt != '\n' && charAt != '\r' && charAt != '\t') {
                this.f12451a = prefetchOrEof;
                return AbstractJsonLexerKt.charToTokenClass(charAt);
            }
            i2 = prefetchOrEof + 1;
        }
    }

    @Nullable
    public final String peekString(boolean z) {
        String consumeString;
        byte peekNextToken = peekNextToken();
        if (z) {
            if (peekNextToken != 1 && peekNextToken != 0) {
                return null;
            }
            consumeString = consumeStringLenient();
        } else if (peekNextToken != 1) {
            return null;
        } else {
            consumeString = consumeString();
        }
        this.peekedString = consumeString;
        return consumeString;
    }

    public abstract int prefetchOrEof(int i2);

    public final void require$kotlinx_serialization_json(boolean z, int i2, @NotNull Function0<String> message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (z) {
            return;
        }
        fail(message.invoke(), i2);
        throw new KotlinNothingValueException();
    }

    public final void skipElement(boolean z) {
        Object last;
        Object last2;
        ArrayList arrayList = new ArrayList();
        byte peekNextToken = peekNextToken();
        if (peekNextToken != 8 && peekNextToken != 6) {
            consumeStringLenient();
            return;
        }
        while (true) {
            byte peekNextToken2 = peekNextToken();
            boolean z2 = true;
            if (peekNextToken2 != 1) {
                if (peekNextToken2 != 8 && peekNextToken2 != 6) {
                    z2 = false;
                }
                if (z2) {
                    arrayList.add(Byte.valueOf(peekNextToken2));
                } else {
                    if (peekNextToken2 == 9) {
                        last2 = CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList);
                        if (((Number) last2).byteValue() != 8) {
                            throw JsonExceptionsKt.JsonDecodingException(this.f12451a, "found ] instead of }", getSource());
                        }
                    } else if (peekNextToken2 == 7) {
                        last = CollectionsKt___CollectionsKt.last((List<? extends Object>) arrayList);
                        if (((Number) last).byteValue() != 6) {
                            throw JsonExceptionsKt.JsonDecodingException(this.f12451a, "found } instead of ]", getSource());
                        }
                    } else if (peekNextToken2 == 10) {
                        fail$default(this, "Unexpected end of input due to malformed JSON during ignoring unknown keys", 0, 2, null);
                        throw new KotlinNothingValueException();
                    }
                    CollectionsKt__MutableCollectionsKt.removeLast(arrayList);
                }
                consumeNextToken();
                if (arrayList.size() == 0) {
                    return;
                }
            } else if (z) {
                consumeStringLenient();
            } else {
                consumeKeyString();
            }
        }
    }

    public int skipWhitespaces() {
        int prefetchOrEof;
        char charAt;
        int i2 = this.f12451a;
        while (true) {
            prefetchOrEof = prefetchOrEof(i2);
            if (prefetchOrEof != -1 && ((charAt = getSource().charAt(prefetchOrEof)) == ' ' || charAt == '\n' || charAt == '\r' || charAt == '\t')) {
                i2 = prefetchOrEof + 1;
            }
        }
        this.f12451a = prefetchOrEof;
        return prefetchOrEof;
    }

    @NotNull
    public String substring(int i2, int i3) {
        return getSource().subSequence(i2, i3).toString();
    }

    @NotNull
    public String toString() {
        return "JsonReader(source='" + ((Object) getSource()) + "', currentPosition=" + this.f12451a + ')';
    }

    public abstract boolean tryConsumeComma();

    public final boolean tryConsumeNotNull() {
        int prefetchOrEof = prefetchOrEof(skipWhitespaces());
        int length = getSource().length() - prefetchOrEof;
        if (length < 4 || prefetchOrEof == -1) {
            return true;
        }
        int i2 = 0;
        while (i2 < 4) {
            int i3 = i2 + 1;
            if ("null".charAt(i2) != getSource().charAt(i2 + prefetchOrEof)) {
                return true;
            }
            i2 = i3;
        }
        if (length <= 4 || AbstractJsonLexerKt.charToTokenClass(getSource().charAt(prefetchOrEof + 4)) != 0) {
            this.f12451a = prefetchOrEof + 4;
            return false;
        }
        return true;
    }
}
