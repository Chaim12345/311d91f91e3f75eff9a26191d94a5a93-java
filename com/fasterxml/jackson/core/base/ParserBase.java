package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
/* loaded from: classes.dex */
public abstract class ParserBase extends ParserMinimalBase {
    protected char[] A;
    protected boolean B;
    protected ByteArrayBuilder C;
    protected byte[] D;
    protected int E;
    protected int F;
    protected long G;
    protected double H;
    protected BigInteger I;
    protected BigDecimal J;
    protected boolean K;
    protected int L;
    protected int M;
    protected int N;

    /* renamed from: n  reason: collision with root package name */
    protected final IOContext f5088n;

    /* renamed from: o  reason: collision with root package name */
    protected boolean f5089o;

    /* renamed from: p  reason: collision with root package name */
    protected int f5090p;

    /* renamed from: q  reason: collision with root package name */
    protected int f5091q;

    /* renamed from: r  reason: collision with root package name */
    protected long f5092r;

    /* renamed from: s  reason: collision with root package name */
    protected int f5093s;

    /* renamed from: t  reason: collision with root package name */
    protected int f5094t;
    protected long u;
    protected int v;
    protected int w;
    protected JsonReadContext x;
    protected JsonToken y;
    protected final TextBuffer z;

    /* JADX INFO: Access modifiers changed from: protected */
    public ParserBase(IOContext iOContext, int i2) {
        super(i2);
        this.f5093s = 1;
        this.v = 1;
        this.E = 0;
        this.f5088n = iOContext;
        this.z = iOContext.constructTextBuffer();
        this.x = JsonReadContext.createRootContext(JsonParser.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i2) ? DupDetector.rootDetector(this) : null);
    }

    private void _parseSlowFloat(int i2) {
        try {
            if (i2 == 16) {
                this.J = this.z.contentsAsDecimal();
                this.E = 16;
            } else {
                this.H = this.z.contentsAsDouble();
                this.E = 8;
            }
        } catch (NumberFormatException e2) {
            v("Malformed numeric value (" + j(this.z.contentsAsString()) + ")", e2);
        }
    }

    private void _parseSlowInt(int i2) {
        String contentsAsString = this.z.contentsAsString();
        try {
            int i3 = this.L;
            char[] textBuffer = this.z.getTextBuffer();
            int textOffset = this.z.getTextOffset();
            boolean z = this.K;
            if (z) {
                textOffset++;
            }
            if (NumberInput.inLongRange(textBuffer, textOffset, i3, z)) {
                this.G = Long.parseLong(contentsAsString);
                this.E = 2;
                return;
            }
            if (i2 == 1 || i2 == 2) {
                R(i2, contentsAsString);
            }
            if (i2 != 8 && i2 != 32) {
                this.I = new BigInteger(contentsAsString);
                this.E = 4;
                return;
            }
            this.H = NumberInput.parseDouble(contentsAsString);
            this.E = 8;
        } catch (NumberFormatException e2) {
            v("Malformed numeric value (" + j(contentsAsString) + ")", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static int[] a0(int[] iArr, int i2) {
        return iArr == null ? new int[i2] : Arrays.copyOf(iArr, iArr.length + i2);
    }

    protected void E(int i2, int i3) {
        JsonReadContext jsonReadContext;
        DupDetector dupDetector;
        int mask = JsonParser.Feature.STRICT_DUPLICATE_DETECTION.getMask();
        if ((i3 & mask) == 0 || (i2 & mask) == 0) {
            return;
        }
        if (this.x.getDupDetector() == null) {
            jsonReadContext = this.x;
            dupDetector = DupDetector.rootDetector(this);
        } else {
            jsonReadContext = this.x;
            dupDetector = null;
        }
        this.x = jsonReadContext.withDupDetector(dupDetector);
    }

    protected abstract void F();

    /* JADX INFO: Access modifiers changed from: protected */
    public final int G(Base64Variant base64Variant, char c2, int i2) {
        if (c2 == '\\') {
            char I = I();
            if (I > ' ' || i2 != 0) {
                int decodeBase64Char = base64Variant.decodeBase64Char(I);
                if (decodeBase64Char >= 0 || (decodeBase64Char == -2 && i2 >= 2)) {
                    return decodeBase64Char;
                }
                throw b0(base64Variant, I, i2);
            }
            return -1;
        }
        throw b0(base64Variant, c2, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int H(Base64Variant base64Variant, int i2, int i3) {
        if (i2 == 92) {
            char I = I();
            if (I > ' ' || i3 != 0) {
                int decodeBase64Char = base64Variant.decodeBase64Char((int) I);
                if (decodeBase64Char >= 0 || decodeBase64Char == -2) {
                    return decodeBase64Char;
                }
                throw b0(base64Variant, I, i3);
            }
            return -1;
        }
        throw b0(base64Variant, i2, i3);
    }

    protected char I() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int J() {
        g();
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object K() {
        if (JsonParser.Feature.INCLUDE_SOURCE_IN_LOCATION.enabledIn(this.f5048a)) {
            return this.f5088n.getSourceReference();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void L(Base64Variant base64Variant) {
        k(base64Variant.missingPaddingMessage());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public char M(char c2) {
        if (isEnabled(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)) {
            return c2;
        }
        if (c2 == '\'' && isEnabled(JsonParser.Feature.ALLOW_SINGLE_QUOTES)) {
            return c2;
        }
        k("Unrecognized character escape " + ParserMinimalBase.f(c2));
        return c2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int N() {
        if (this.f5104c != JsonToken.VALUE_NUMBER_INT || this.L > 9) {
            O(1);
            if ((this.E & 1) == 0) {
                Y();
            }
            return this.F;
        }
        int contentsAsInt = this.z.contentsAsInt(this.K);
        this.F = contentsAsInt;
        this.E = 1;
        return contentsAsInt;
    }

    protected void O(int i2) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != JsonToken.VALUE_NUMBER_INT) {
            if (jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
                _parseSlowFloat(i2);
                return;
            } else {
                l("Current token (%s) not numeric, can not use numeric value accessors", jsonToken);
                return;
            }
        }
        int i3 = this.L;
        if (i3 <= 9) {
            this.F = this.z.contentsAsInt(this.K);
            this.E = 1;
        } else if (i3 > 18) {
            _parseSlowInt(i2);
        } else {
            long contentsAsLong = this.z.contentsAsLong(this.K);
            if (i3 == 10) {
                if (this.K) {
                    if (contentsAsLong >= -2147483648L) {
                        this.F = (int) contentsAsLong;
                        this.E = 1;
                        return;
                    }
                } else if (contentsAsLong <= 2147483647L) {
                    this.F = (int) contentsAsLong;
                    this.E = 1;
                    return;
                }
            }
            this.G = contentsAsLong;
            this.E = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void P() {
        this.z.releaseBuffers();
        char[] cArr = this.A;
        if (cArr != null) {
            this.A = null;
            this.f5088n.releaseNameCopyBuffer(cArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void Q(int i2, char c2) {
        JsonReadContext parsingContext = getParsingContext();
        k(String.format("Unexpected close marker '%s': expected '%c' (for %s starting at %s)", Character.valueOf((char) i2), Character.valueOf(c2), parsingContext.typeDesc(), parsingContext.getStartLocation(K())));
    }

    protected void R(int i2, String str) {
        if (i2 == 1) {
            y(str);
        } else {
            B(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void S(int i2, String str) {
        if (!isEnabled(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i2 > 32) {
            k("Illegal unquoted character (" + ParserMinimalBase.f((char) i2) + "): has to be escaped using backslash to be included in " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String T() {
        return U();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String U() {
        return isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS) ? "(JSON String, Number (or 'NaN'/'INF'/'+INF'), Array, Object or token 'null', 'true' or 'false')" : "(JSON String, Number, Array, Object or token 'null', 'true' or 'false')";
    }

    protected void V() {
        long j2;
        BigDecimal valueOf;
        int i2 = this.E;
        if ((i2 & 8) != 0) {
            valueOf = NumberInput.parseBigDecimal(getText());
        } else if ((i2 & 4) != 0) {
            valueOf = new BigDecimal(this.I);
        } else {
            if ((i2 & 2) != 0) {
                j2 = this.G;
            } else if ((i2 & 1) == 0) {
                t();
                this.E |= 16;
            } else {
                j2 = this.F;
            }
            valueOf = BigDecimal.valueOf(j2);
        }
        this.J = valueOf;
        this.E |= 16;
    }

    protected void W() {
        BigDecimal valueOf;
        long j2;
        BigInteger valueOf2;
        int i2 = this.E;
        if ((i2 & 16) == 0) {
            if ((i2 & 2) != 0) {
                j2 = this.G;
            } else if ((i2 & 1) != 0) {
                j2 = this.F;
            } else if ((i2 & 8) == 0) {
                t();
                this.E |= 4;
            } else {
                valueOf = BigDecimal.valueOf(this.H);
            }
            valueOf2 = BigInteger.valueOf(j2);
            this.I = valueOf2;
            this.E |= 4;
        }
        valueOf = this.J;
        valueOf2 = valueOf.toBigInteger();
        this.I = valueOf2;
        this.E |= 4;
    }

    protected void X() {
        double d2;
        int i2 = this.E;
        if ((i2 & 16) != 0) {
            d2 = this.J.doubleValue();
        } else if ((i2 & 4) != 0) {
            d2 = this.I.doubleValue();
        } else if ((i2 & 2) != 0) {
            d2 = this.G;
        } else if ((i2 & 1) == 0) {
            t();
            this.E |= 8;
        } else {
            d2 = this.F;
        }
        this.H = d2;
        this.E |= 8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void Y() {
        int intValue;
        int i2 = this.E;
        if ((i2 & 2) != 0) {
            long j2 = this.G;
            int i3 = (int) j2;
            if (i3 != j2) {
                z(getText(), currentToken());
            }
            this.F = i3;
        } else {
            if ((i2 & 4) != 0) {
                if (ParserMinimalBase.f5096f.compareTo(this.I) > 0 || ParserMinimalBase.f5097g.compareTo(this.I) < 0) {
                    x();
                }
                intValue = this.I.intValue();
            } else if ((i2 & 8) != 0) {
                double d2 = this.H;
                if (d2 < -2.147483648E9d || d2 > 2.147483647E9d) {
                    x();
                }
                intValue = (int) this.H;
            } else if ((i2 & 16) != 0) {
                if (ParserMinimalBase.f5102l.compareTo(this.J) > 0 || ParserMinimalBase.f5103m.compareTo(this.J) < 0) {
                    x();
                }
                intValue = this.J.intValue();
            } else {
                t();
            }
            this.F = intValue;
        }
        this.E |= 1;
    }

    protected void Z() {
        long longValue;
        int i2 = this.E;
        if ((i2 & 1) != 0) {
            longValue = this.F;
        } else if ((i2 & 4) != 0) {
            if (ParserMinimalBase.f5098h.compareTo(this.I) > 0 || ParserMinimalBase.f5099i.compareTo(this.I) < 0) {
                A();
            }
            longValue = this.I.longValue();
        } else if ((i2 & 8) != 0) {
            double d2 = this.H;
            if (d2 < -9.223372036854776E18d || d2 > 9.223372036854776E18d) {
                A();
            }
            longValue = (long) this.H;
        } else if ((i2 & 16) == 0) {
            t();
            this.E |= 2;
        } else {
            if (ParserMinimalBase.f5100j.compareTo(this.J) > 0 || ParserMinimalBase.f5101k.compareTo(this.J) < 0) {
                A();
            }
            longValue = this.J.longValue();
        }
        this.G = longValue;
        this.E |= 2;
    }

    public ByteArrayBuilder _getByteArrayBuilder() {
        ByteArrayBuilder byteArrayBuilder = this.C;
        if (byteArrayBuilder == null) {
            this.C = new ByteArrayBuilder();
        } else {
            byteArrayBuilder.reset();
        }
        return this.C;
    }

    protected IllegalArgumentException b0(Base64Variant base64Variant, int i2, int i3) {
        return c0(base64Variant, i2, i3, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IllegalArgumentException c0(Base64Variant base64Variant, int i2, int i3, String str) {
        StringBuilder sb;
        String str2;
        String sb2;
        if (i2 <= 32) {
            sb2 = String.format("Illegal white space character (code 0x%s) as character #%d of 4-char base64 unit: can only used between units", Integer.toHexString(i2), Integer.valueOf(i3 + 1));
        } else if (base64Variant.usesPaddingChar(i2)) {
            sb2 = "Unexpected padding character ('" + base64Variant.getPaddingChar() + "') as character #" + (i3 + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else {
            if (!Character.isDefined(i2) || Character.isISOControl(i2)) {
                sb = new StringBuilder();
                str2 = "Illegal character (code 0x";
            } else {
                sb = new StringBuilder();
                sb.append("Illegal character '");
                sb.append((char) i2);
                str2 = "' (code 0x";
            }
            sb.append(str2);
            sb.append(Integer.toHexString(i2));
            sb.append(") in base64 content");
            sb2 = sb.toString();
        }
        if (str != null) {
            sb2 = sb2 + ": " + str;
        }
        return new IllegalArgumentException(sb2);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f5089o) {
            return;
        }
        this.f5090p = Math.max(this.f5090p, this.f5091q);
        this.f5089o = true;
        try {
            F();
        } finally {
            P();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken d0(boolean z, int i2, int i3, int i4) {
        return (i3 >= 1 || i4 >= 1) ? f0(z, i2, i3, i4) : g0(z, i2);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser disable(JsonParser.Feature feature) {
        this.f5048a &= ~feature.getMask();
        if (feature == JsonParser.Feature.STRICT_DUPLICATE_DETECTION) {
            this.x = this.x.withDupDetector(null);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken e0(String str, double d2) {
        this.z.resetWithString(str);
        this.H = d2;
        this.E = 8;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser enable(JsonParser.Feature feature) {
        this.f5048a |= feature.getMask();
        if (feature == JsonParser.Feature.STRICT_DUPLICATE_DETECTION && this.x.getDupDetector() == null) {
            this.x = this.x.withDupDetector(DupDetector.rootDetector(this));
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken f0(boolean z, int i2, int i3, int i4) {
        this.K = z;
        this.L = i2;
        this.M = i3;
        this.N = i4;
        this.E = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase
    public void g() {
        if (this.x.inRoot()) {
            return;
        }
        p(String.format(": expected close marker for %s (start marker at %s)", this.x.inArray() ? "Array" : "Object", this.x.getStartLocation(K())), null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken g0(boolean z, int i2) {
        this.K = z;
        this.L = i2;
        this.M = 0;
        this.N = 0;
        this.E = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public BigInteger getBigIntegerValue() {
        int i2 = this.E;
        if ((i2 & 4) == 0) {
            if (i2 == 0) {
                O(4);
            }
            if ((this.E & 4) == 0) {
                W();
            }
        }
        return this.I;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        if (this.D == null) {
            if (this.f5104c != JsonToken.VALUE_STRING) {
                k("Current token (" + this.f5104c + ") not VALUE_STRING, can not access as binary");
            }
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            e(getText(), _getByteArrayBuilder, base64Variant);
            this.D = _getByteArrayBuilder.toByteArray();
        }
        return this.D;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(K(), -1L, this.f5090p + this.f5092r, this.f5093s, (this.f5090p - this.f5094t) + 1);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getCurrentName() {
        JsonReadContext parent;
        JsonToken jsonToken = this.f5104c;
        return ((jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) && (parent = this.x.getParent()) != null) ? parent.getCurrentName() : this.x.getCurrentName();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getCurrentValue() {
        return this.x.getCurrentValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public BigDecimal getDecimalValue() {
        int i2 = this.E;
        if ((i2 & 16) == 0) {
            if (i2 == 0) {
                O(16);
            }
            if ((this.E & 16) == 0) {
                V();
            }
        }
        return this.J;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public double getDoubleValue() {
        int i2 = this.E;
        if ((i2 & 8) == 0) {
            if (i2 == 0) {
                O(8);
            }
            if ((this.E & 8) == 0) {
                X();
            }
        }
        return this.H;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public float getFloatValue() {
        return (float) getDoubleValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getIntValue() {
        int i2 = this.E;
        if ((i2 & 1) == 0) {
            if (i2 == 0) {
                return N();
            }
            if ((i2 & 1) == 0) {
                Y();
            }
        }
        return this.F;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getLongValue() {
        int i2 = this.E;
        if ((i2 & 2) == 0) {
            if (i2 == 0) {
                O(2);
            }
            if ((this.E & 2) == 0) {
                Z();
            }
        }
        return this.G;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser.NumberType getNumberType() {
        if (this.E == 0) {
            O(0);
        }
        if (this.f5104c != JsonToken.VALUE_NUMBER_INT) {
            return (this.E & 16) != 0 ? JsonParser.NumberType.BIG_DECIMAL : JsonParser.NumberType.DOUBLE;
        }
        int i2 = this.E;
        return (i2 & 1) != 0 ? JsonParser.NumberType.INT : (i2 & 2) != 0 ? JsonParser.NumberType.LONG : JsonParser.NumberType.BIG_INTEGER;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Number getNumberValue() {
        if (this.E == 0) {
            O(0);
        }
        if (this.f5104c == JsonToken.VALUE_NUMBER_INT) {
            int i2 = this.E;
            return (i2 & 1) != 0 ? Integer.valueOf(this.F) : (i2 & 2) != 0 ? Long.valueOf(this.G) : (i2 & 4) != 0 ? this.I : this.J;
        }
        int i3 = this.E;
        if ((i3 & 16) != 0) {
            return this.J;
        }
        if ((i3 & 8) == 0) {
            t();
        }
        return Double.valueOf(this.H);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public JsonReadContext getParsingContext() {
        return this.x;
    }

    public long getTokenCharacterOffset() {
        return this.u;
    }

    public int getTokenColumnNr() {
        int i2 = this.w;
        return i2 < 0 ? i2 : i2 + 1;
    }

    public int getTokenLineNr() {
        return this.v;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        return new JsonLocation(K(), -1L, getTokenCharacterOffset(), getTokenLineNr(), getTokenColumnNr());
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public boolean hasTextCharacters() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return true;
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return this.B;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public boolean isClosed() {
        return this.f5089o;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isNaN() {
        if (this.f5104c != JsonToken.VALUE_NUMBER_FLOAT || (this.E & 8) == 0) {
            return false;
        }
        double d2 = this.H;
        return Double.isNaN(d2) || Double.isInfinite(d2);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public void overrideCurrentName(String str) {
        JsonReadContext jsonReadContext = this.x;
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) {
            jsonReadContext = jsonReadContext.getParent();
        }
        try {
            jsonReadContext.setCurrentName(str);
        } catch (IOException e2) {
            throw new IllegalStateException(e2);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser overrideStdFeatures(int i2, int i3) {
        int i4 = this.f5048a;
        int i5 = (i2 & i3) | ((~i3) & i4);
        int i6 = i4 ^ i5;
        if (i6 != 0) {
            this.f5048a = i5;
            E(i5, i6);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCurrentValue(Object obj) {
        this.x.setCurrentValue(obj);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    @Deprecated
    public JsonParser setFeatureMask(int i2) {
        int i3 = this.f5048a ^ i2;
        if (i3 != 0) {
            this.f5048a = i2;
            E(i2, i3);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }
}
