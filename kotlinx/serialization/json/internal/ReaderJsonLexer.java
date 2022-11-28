package kotlinx.serialization.json.internal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ReaderJsonLexer extends AbstractJsonLexer {
    @NotNull
    private char[] _source;
    @NotNull
    private final Reader reader;
    @NotNull
    private CharSequence source;
    private int threshold;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ReaderJsonLexer(@NotNull InputStream i2, @NotNull Charset charset) {
        this(r0 instanceof BufferedReader ? (BufferedReader) r0 : new BufferedReader(r0, 262144), (char[]) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(i2, "i");
        Intrinsics.checkNotNullParameter(charset, "charset");
        InputStreamReader inputStreamReader = new InputStreamReader(i2, charset);
    }

    public /* synthetic */ ReaderJsonLexer(InputStream inputStream, Charset charset, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(inputStream, (i2 & 2) != 0 ? Charsets.UTF_8 : charset);
    }

    public ReaderJsonLexer(@NotNull Reader reader, @NotNull char[] _source) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(_source, "_source");
        this.reader = reader;
        this._source = _source;
        this.threshold = 128;
        this.source = new ArrayAsSequence(_source);
        preload(0);
    }

    public /* synthetic */ ReaderJsonLexer(Reader reader, char[] cArr, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(reader, (i2 & 2) != 0 ? new char[16384] : cArr);
    }

    private final void preload(int i2) {
        char[] cArr = this._source;
        System.arraycopy(cArr, this.f12451a, cArr, 0, i2);
        int length = this._source.length;
        while (true) {
            if (i2 == length) {
                break;
            }
            int read = this.reader.read(cArr, i2, length - i2);
            if (read == -1) {
                char[] copyOf = Arrays.copyOf(this._source, i2);
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                this._source = copyOf;
                setSource(new ArrayAsSequence(copyOf));
                this.threshold = -1;
                break;
            }
            i2 += read;
        }
        this.f12451a = 0;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    protected void a(int i2, int i3) {
        c().append(this._source, i2, i3 - i2);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean canConsumeValue() {
        ensureHaveChars();
        int i2 = this.f12451a;
        while (true) {
            int prefetchOrEof = prefetchOrEof(i2);
            if (prefetchOrEof == -1) {
                this.f12451a = prefetchOrEof;
                return false;
            }
            char charAt = getSource().charAt(prefetchOrEof);
            if (charAt != ' ' && charAt != '\n' && charAt != '\r' && charAt != '\t') {
                this.f12451a = prefetchOrEof;
                return d(charAt);
            }
            i2 = prefetchOrEof + 1;
        }
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @NotNull
    public String consumeKeyString() {
        consumeNextToken('\"');
        int i2 = this.f12451a;
        int indexOf = indexOf('\"', i2);
        if (indexOf == -1) {
            int prefetchOrEof = prefetchOrEof(i2);
            if (prefetchOrEof != -1) {
                return b(getSource(), this.f12451a, prefetchOrEof);
            }
            fail$kotlinx_serialization_json((byte) 1);
            throw new KotlinNothingValueException();
        }
        int i3 = i2;
        while (i3 < indexOf) {
            int i4 = i3 + 1;
            if (getSource().charAt(i3) == '\\') {
                return b(getSource(), this.f12451a, i3);
            }
            i3 = i4;
        }
        this.f12451a = indexOf + 1;
        return substring(i2, indexOf);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public byte consumeNextToken() {
        ensureHaveChars();
        CharSequence source = getSource();
        int i2 = this.f12451a;
        while (true) {
            int prefetchOrEof = prefetchOrEof(i2);
            if (prefetchOrEof == -1) {
                this.f12451a = prefetchOrEof;
                return (byte) 10;
            }
            int i3 = prefetchOrEof + 1;
            byte charToTokenClass = AbstractJsonLexerKt.charToTokenClass(source.charAt(prefetchOrEof));
            if (charToTokenClass != 3) {
                this.f12451a = i3;
                return charToTokenClass;
            }
            i2 = i3;
        }
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public void ensureHaveChars() {
        int length = this._source.length - this.f12451a;
        if (length > this.threshold) {
            return;
        }
        preload(length);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @NotNull
    protected CharSequence getSource() {
        return this.source;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int indexOf(char c2, int i2) {
        char[] cArr = this._source;
        int length = cArr.length;
        while (i2 < length) {
            int i3 = i2 + 1;
            if (cArr[i2] == c2) {
                return i2;
            }
            i2 = i3;
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public int prefetchOrEof(int i2) {
        if (i2 < getSource().length()) {
            return i2;
        }
        this.f12451a = i2;
        ensureHaveChars();
        if (this.f12451a == 0) {
            return getSource().length() == 0 ? -1 : 0;
        }
        return -1;
    }

    public void setSource(@NotNull CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<set-?>");
        this.source = charSequence;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    @NotNull
    public String substring(int i2, int i3) {
        return new String(this._source, i2, i3 - i2);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonLexer
    public boolean tryConsumeComma() {
        int skipWhitespaces = skipWhitespaces();
        if (skipWhitespaces >= getSource().length() || skipWhitespaces == -1 || getSource().charAt(skipWhitespaces) != ',') {
            return false;
        }
        this.f12451a++;
        return true;
    }
}
