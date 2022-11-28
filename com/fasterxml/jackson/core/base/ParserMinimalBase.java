package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.exc.InputCoercionException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import kotlin.jvm.internal.LongCompanionObject;
import org.apache.commons.cli.HelpFormatter;
/* loaded from: classes.dex */
public abstract class ParserMinimalBase extends JsonParser {

    /* renamed from: e  reason: collision with root package name */
    protected static final byte[] f5095e = new byte[0];

    /* renamed from: f  reason: collision with root package name */
    protected static final BigInteger f5096f;

    /* renamed from: g  reason: collision with root package name */
    protected static final BigInteger f5097g;

    /* renamed from: h  reason: collision with root package name */
    protected static final BigInteger f5098h;

    /* renamed from: i  reason: collision with root package name */
    protected static final BigInteger f5099i;

    /* renamed from: j  reason: collision with root package name */
    protected static final BigDecimal f5100j;

    /* renamed from: k  reason: collision with root package name */
    protected static final BigDecimal f5101k;

    /* renamed from: l  reason: collision with root package name */
    protected static final BigDecimal f5102l;

    /* renamed from: m  reason: collision with root package name */
    protected static final BigDecimal f5103m;

    /* renamed from: c  reason: collision with root package name */
    protected JsonToken f5104c;

    /* renamed from: d  reason: collision with root package name */
    protected JsonToken f5105d;

    static {
        BigInteger valueOf = BigInteger.valueOf(-2147483648L);
        f5096f = valueOf;
        BigInteger valueOf2 = BigInteger.valueOf(2147483647L);
        f5097g = valueOf2;
        BigInteger valueOf3 = BigInteger.valueOf(Long.MIN_VALUE);
        f5098h = valueOf3;
        BigInteger valueOf4 = BigInteger.valueOf(LongCompanionObject.MAX_VALUE);
        f5099i = valueOf4;
        f5100j = new BigDecimal(valueOf3);
        f5101k = new BigDecimal(valueOf4);
        f5102l = new BigDecimal(valueOf);
        f5103m = new BigDecimal(valueOf2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ParserMinimalBase(int i2) {
        super(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final String f(int i2) {
        char c2 = (char) i2;
        if (Character.isISOControl(c2)) {
            return "(CTRL-CHAR, code " + i2 + ")";
        } else if (i2 <= 255) {
            return "'" + c2 + "' (code " + i2 + ")";
        } else {
            return "'" + c2 + "' (code " + i2 + " / 0x" + Integer.toHexString(i2) + ")";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void A() {
        B(getText());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void B(String str) {
        C(str, currentToken());
    }

    protected void C(String str, JsonToken jsonToken) {
        n(String.format("Numeric value (%s) out of range of long (%d - %s)", i(str), Long.MIN_VALUE, Long.valueOf((long) LongCompanionObject.MAX_VALUE)), jsonToken, Long.TYPE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void D(int i2, String str) {
        String format = String.format("Unexpected character (%s) in numeric value", f(i2));
        if (str != null) {
            format = format + ": " + str;
        }
        k(format);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void clearCurrentToken() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != null) {
            this.f5105d = jsonToken;
            this.f5104c = null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken currentToken() {
        return this.f5104c;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int currentTokenId() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    protected final JsonParseException d(String str, Throwable th) {
        return new JsonParseException(this, str, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void e(String str, ByteArrayBuilder byteArrayBuilder, Base64Variant base64Variant) {
        try {
            base64Variant.decode(str, byteArrayBuilder);
        } catch (IllegalArgumentException e2) {
            k(e2.getMessage());
        }
    }

    protected abstract void g();

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract byte[] getBinaryValue(Base64Variant base64Variant);

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract String getCurrentName();

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken getCurrentToken() {
        return this.f5104c;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getCurrentTokenId() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken getLastClearedToken() {
        return this.f5105d;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract JsonStreamContext getParsingContext();

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract String getText();

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract char[] getTextCharacters();

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract int getTextLength();

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract int getTextOffset();

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean(boolean z) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != null) {
            switch (jsonToken.id()) {
                case 6:
                    String trim = getText().trim();
                    if ("true".equals(trim)) {
                        return true;
                    }
                    if ("false".equals(trim) || h(trim)) {
                        return false;
                    }
                    break;
                case 7:
                    return getIntValue() != 0;
                case 9:
                    return true;
                case 10:
                case 11:
                    return false;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    if (embeddedObject instanceof Boolean) {
                        return ((Boolean) embeddedObject).booleanValue();
                    }
                    break;
            }
        }
        return z;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble(double d2) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != null) {
            switch (jsonToken.id()) {
                case 6:
                    String text = getText();
                    if (h(text)) {
                        return 0.0d;
                    }
                    return NumberInput.parseAsDouble(text, d2);
                case 7:
                case 8:
                    return getDoubleValue();
                case 9:
                    return 1.0d;
                case 10:
                case 11:
                    return 0.0d;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).doubleValue() : d2;
                default:
                    return d2;
            }
        }
        return d2;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() {
        JsonToken jsonToken = this.f5104c;
        return (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) ? getIntValue() : getValueAsInt(0);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i2) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return getIntValue();
        }
        if (jsonToken != null) {
            int id = jsonToken.id();
            if (id == 6) {
                String text = getText();
                if (h(text)) {
                    return 0;
                }
                return NumberInput.parseAsInt(text, i2);
            }
            switch (id) {
                case 9:
                    return 1;
                case 10:
                case 11:
                    return 0;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).intValue() : i2;
                default:
                    return i2;
            }
        }
        return i2;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong() {
        JsonToken jsonToken = this.f5104c;
        return (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) ? getLongValue() : getValueAsLong(0L);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong(long j2) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            return getLongValue();
        }
        if (jsonToken != null) {
            int id = jsonToken.id();
            if (id == 6) {
                String text = getText();
                if (h(text)) {
                    return 0L;
                }
                return NumberInput.parseAsLong(text, j2);
            }
            switch (id) {
                case 9:
                    return 1L;
                case 10:
                case 11:
                    return 0L;
                case 12:
                    Object embeddedObject = getEmbeddedObject();
                    return embeddedObject instanceof Number ? ((Number) embeddedObject).longValue() : j2;
                default:
                    return j2;
            }
        }
        return j2;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() {
        return getValueAsString(null);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) {
        JsonToken jsonToken = this.f5104c;
        return jsonToken == JsonToken.VALUE_STRING ? getText() : jsonToken == JsonToken.FIELD_NAME ? getCurrentName() : (jsonToken == null || jsonToken == JsonToken.VALUE_NULL || !jsonToken.isScalarValue()) ? str : getText();
    }

    protected boolean h(String str) {
        return "null".equals(str);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasCurrentToken() {
        return this.f5104c != null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract boolean hasTextCharacters();

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasToken(JsonToken jsonToken) {
        return this.f5104c == jsonToken;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasTokenId(int i2) {
        JsonToken jsonToken = this.f5104c;
        return jsonToken == null ? i2 == 0 : jsonToken.id() == i2;
    }

    protected String i(String str) {
        int length = str.length();
        if (length < 1000) {
            return str;
        }
        if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
            length--;
        }
        return String.format("[Integer with %d digits]", Integer.valueOf(length));
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract boolean isClosed();

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartArrayToken() {
        return this.f5104c == JsonToken.START_ARRAY;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartObjectToken() {
        return this.f5104c == JsonToken.START_OBJECT;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String j(String str) {
        int length = str.length();
        if (length < 1000) {
            return str;
        }
        if (str.startsWith(HelpFormatter.DEFAULT_OPT_PREFIX)) {
            length--;
        }
        return String.format("[number with %d characters]", Integer.valueOf(length));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void k(String str) {
        throw b(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void l(String str, Object obj) {
        throw b(String.format(str, obj));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void m(String str, Object obj, Object obj2) {
        throw b(String.format(str, obj, obj2));
    }

    protected void n(String str, JsonToken jsonToken, Class cls) {
        throw new InputCoercionException(this, str, jsonToken, cls);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract JsonToken nextToken();

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken nextValue() {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void o() {
        p(" in " + this.f5104c, this.f5104c);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract void overrideCurrentName(String str);

    /* JADX INFO: Access modifiers changed from: protected */
    public void p(String str, JsonToken jsonToken) {
        throw new JsonEOFException(this, jsonToken, "Unexpected end-of-input" + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void q(JsonToken jsonToken) {
        p(jsonToken == JsonToken.VALUE_STRING ? " in a String value" : (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) ? " in a Number value" : " in a value", jsonToken);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void r(int i2) {
        s(i2, "Expected space separating root-level values");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void s(int i2, String str) {
        if (i2 < 0) {
            o();
        }
        String format = String.format("Unexpected character (%s)", f(i2));
        if (str != null) {
            format = format + ": " + str;
        }
        k(format);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser skipChildren() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != JsonToken.START_OBJECT && jsonToken != JsonToken.START_ARRAY) {
            return this;
        }
        int i2 = 1;
        while (true) {
            JsonToken nextToken = nextToken();
            if (nextToken == null) {
                g();
                return this;
            } else if (nextToken.isStructStart()) {
                i2++;
            } else if (nextToken.isStructEnd()) {
                i2--;
                if (i2 == 0) {
                    return this;
                }
            } else if (nextToken == JsonToken.NOT_AVAILABLE) {
                l("Not enough content available for `skipChildren()`: non-blocking parser? (%s)", getClass().getName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void t() {
        VersionUtil.throwInternal();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void u(int i2) {
        k("Illegal character (" + f((char) i2) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void v(String str, Throwable th) {
        throw d(str, th);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void w(String str) {
        k("Invalid numeric value: " + str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void x() {
        y(getText());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void y(String str) {
        z(str, currentToken());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void z(String str, JsonToken jsonToken) {
        n(String.format("Numeric value (%s) out of range of int (%d - %s)", i(str), Integer.MIN_VALUE, Integer.MAX_VALUE), jsonToken, Integer.TYPE);
    }
}
