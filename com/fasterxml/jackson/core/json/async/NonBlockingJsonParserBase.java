package com.fasterxml.jackson.core.json.async;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.OutputStream;
import java.io.Writer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.bouncycastle.asn1.BERTags;
/* loaded from: classes.dex */
public abstract class NonBlockingJsonParserBase extends ParserBase {
    protected static final String[] e0 = {"NaN", "Infinity", "+Infinity", "-Infinity"};
    protected static final double[] f0 = {Double.NaN, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};
    protected final ByteQuadsCanonicalizer O;
    protected int[] P;
    protected int Q;
    protected int R;
    protected int S;
    protected int T;
    protected int U;
    protected int V;
    protected int W;
    protected int X;
    protected int Y;
    protected int Z;
    protected boolean a0;
    protected int b0;
    protected int c0;
    protected int d0;

    public NonBlockingJsonParserBase(IOContext iOContext, int i2, ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        super(iOContext, i2);
        this.P = new int[8];
        this.a0 = false;
        this.c0 = 0;
        this.d0 = 1;
        this.O = byteQuadsCanonicalizer;
        this.f5104c = null;
        this.W = 0;
        this.X = 1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static final int _padLastQuad(int i2, int i3) {
        return i3 == 4 ? i2 : i2 | ((-1) << (i3 << 3));
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void F() {
        this.c0 = 0;
        this.f5091q = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserBase
    public void P() {
        super.P();
        this.O.release();
    }

    protected void _reportInvalidOther(int i2) {
        k("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i2));
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean canParseAsync() {
        return true;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != JsonToken.VALUE_STRING) {
            l("Current token (%s) not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary", jsonToken);
        }
        if (this.D == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            e(getText(), _getByteArrayBuilder, base64Variant);
            this.D = _getByteArrayBuilder.toByteArray();
        }
        return this.D;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return null;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        int i2 = (this.f5090p - this.f5094t) + 1;
        return new JsonLocation(K(), this.f5092r + (this.f5090p - this.c0), -1L, Math.max(this.f5093s, this.d0), i2);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getEmbeddedObject() {
        if (this.f5104c == JsonToken.VALUE_EMBEDDED_OBJECT) {
            return this.D;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getInputSource() {
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getText(Writer writer) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this.z.contentsToWriter(writer);
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            String currentName = this.x.getCurrentName();
            writer.write(currentName);
            return currentName.length();
        } else if (jsonToken != null) {
            if (jsonToken.isNumeric()) {
                return this.z.contentsToWriter(writer);
            }
            if (jsonToken == JsonToken.NOT_AVAILABLE) {
                k("Current token not available: can not call this method");
            }
            char[] asCharArray = jsonToken.asCharArray();
            writer.write(asCharArray);
            return asCharArray.length;
        } else {
            return 0;
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getText() {
        JsonToken jsonToken = this.f5104c;
        return jsonToken == JsonToken.VALUE_STRING ? this.z.contentsAsString() : p0(jsonToken);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != null) {
            int id = jsonToken.id();
            if (id != 5) {
                return (id == 6 || id == 7 || id == 8) ? this.z.getTextBuffer() : this.f5104c.asCharArray();
            }
            if (!this.B) {
                String currentName = this.x.getCurrentName();
                int length = currentName.length();
                char[] cArr = this.A;
                if (cArr == null) {
                    this.A = this.f5088n.allocNameCopyBuffer(length);
                } else if (cArr.length < length) {
                    this.A = new char[length];
                }
                currentName.getChars(0, length, this.A, 0);
                this.B = true;
            }
            return this.A;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getTextLength() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != null) {
            int id = jsonToken.id();
            return id != 5 ? (id == 6 || id == 7 || id == 8) ? this.z.size() : this.f5104c.asCharArray().length : this.x.getCurrentName().length();
        }
        return 0;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getTextOffset() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != null) {
            int id = jsonToken.id();
            if (id == 6 || id == 7 || id == 8) {
                return this.z.getTextOffset();
            }
            return 0;
        }
        return 0;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        return new JsonLocation(K(), this.u, -1L, this.v, this.w);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() {
        JsonToken jsonToken = this.f5104c;
        return jsonToken == JsonToken.VALUE_STRING ? this.z.contentsAsString() : jsonToken == JsonToken.FIELD_NAME ? getCurrentName() : super.getValueAsString(null);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) {
        JsonToken jsonToken = this.f5104c;
        return jsonToken == JsonToken.VALUE_STRING ? this.z.contentsAsString() : jsonToken == JsonToken.FIELD_NAME ? getCurrentName() : super.getValueAsString(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String h0(int[] iArr, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = ((i2 << 2) - 4) + i3;
        if (i3 < 4) {
            int i9 = i2 - 1;
            i4 = iArr[i9];
            iArr[i9] = i4 << ((4 - i3) << 3);
        } else {
            i4 = 0;
        }
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        int i10 = 0;
        int i11 = 0;
        while (i10 < i8) {
            int i12 = (iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3)) & 255;
            i10++;
            if (i12 > 127) {
                if ((i12 & BERTags.FLAGS) == 192) {
                    i5 = i12 & 31;
                    i6 = 1;
                } else if ((i12 & 240) == 224) {
                    i5 = i12 & 15;
                    i6 = 2;
                } else if ((i12 & 248) == 240) {
                    i5 = i12 & 7;
                    i6 = 3;
                } else {
                    s0(i12);
                    i5 = 1;
                    i6 = 1;
                }
                if (i10 + i6 > i8) {
                    p(" in field name", JsonToken.FIELD_NAME);
                }
                int i13 = iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                i10++;
                if ((i13 & 192) != 128) {
                    _reportInvalidOther(i13);
                }
                int i14 = (i13 & 63) | (i5 << 6);
                if (i6 > 1) {
                    int i15 = iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                    i10++;
                    if ((i15 & 192) != 128) {
                        _reportInvalidOther(i15);
                    }
                    int i16 = (i15 & 63) | (i14 << 6);
                    if (i6 > 2) {
                        int i17 = iArr[i10 >> 2] >> ((3 - (i10 & 3)) << 3);
                        i10++;
                        if ((i17 & 192) != 128) {
                            _reportInvalidOther(i17 & 255);
                        }
                        i12 = (i16 << 6) | (i17 & 63);
                    } else {
                        i12 = i16;
                        i7 = 2;
                        if (i6 > i7) {
                            int i18 = i12 - 65536;
                            if (i11 >= emptyAndGetCurrentSegment.length) {
                                emptyAndGetCurrentSegment = this.z.expandCurrentSegment();
                            }
                            emptyAndGetCurrentSegment[i11] = (char) ((i18 >> 10) + GeneratorBase.SURR1_FIRST);
                            i12 = (i18 & 1023) | 56320;
                            i11++;
                        }
                    }
                } else {
                    i12 = i14;
                }
                i7 = 2;
                if (i6 > i7) {
                }
            }
            if (i11 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this.z.expandCurrentSegment();
            }
            emptyAndGetCurrentSegment[i11] = (char) i12;
            i11++;
        }
        String str = new String(emptyAndGetCurrentSegment, 0, i11);
        if (i3 < 4) {
            iArr[i2 - 1] = i4;
        }
        return this.O.addName(str, iArr, i2);
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public boolean hasTextCharacters() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this.z.hasTextAsCharacters();
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            return this.B;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken i0() {
        if (!this.x.inArray()) {
            Q(93, AbstractJsonLexerKt.END_OBJ);
        }
        JsonReadContext parent = this.x.getParent();
        this.x = parent;
        int i2 = parent.inObject() ? 3 : parent.inArray() ? 6 : 1;
        this.W = i2;
        this.X = i2;
        JsonToken jsonToken = JsonToken.END_ARRAY;
        this.f5104c = jsonToken;
        return jsonToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken j0() {
        if (!this.x.inObject()) {
            Q(125, AbstractJsonLexerKt.END_LIST);
        }
        JsonReadContext parent = this.x.getParent();
        this.x = parent;
        int i2 = parent.inObject() ? 3 : parent.inArray() ? 6 : 1;
        this.W = i2;
        this.X = i2;
        JsonToken jsonToken = JsonToken.END_OBJECT;
        this.f5104c = jsonToken;
        return jsonToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken k0() {
        this.W = 7;
        if (!this.x.inRoot()) {
            g();
        }
        close();
        this.f5104c = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken l0(String str) {
        this.W = 4;
        this.x.setCurrentName(str);
        JsonToken jsonToken = JsonToken.FIELD_NAME;
        this.f5104c = jsonToken;
        return jsonToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String m0(int i2, int i3) {
        int _padLastQuad = _padLastQuad(i2, i3);
        String findName = this.O.findName(_padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this.P;
        iArr[0] = _padLastQuad;
        return h0(iArr, 1, i3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String n0(int i2, int i3, int i4) {
        int _padLastQuad = _padLastQuad(i3, i4);
        String findName = this.O.findName(i2, _padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this.P;
        iArr[0] = i2;
        iArr[1] = _padLastQuad;
        return h0(iArr, 2, i4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String o0(int i2, int i3, int i4, int i5) {
        int _padLastQuad = _padLastQuad(i4, i5);
        String findName = this.O.findName(i2, i3, _padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this.P;
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = _padLastQuad(_padLastQuad, i5);
        return h0(iArr, 3, i5);
    }

    protected final String p0(JsonToken jsonToken) {
        int id;
        if (jsonToken == null || (id = jsonToken.id()) == -1) {
            return null;
        }
        return id != 5 ? (id == 6 || id == 7 || id == 8) ? this.z.contentsAsString() : jsonToken.asString() : this.x.getCurrentName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String q0(int i2) {
        return e0[i2];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void r0(int i2) {
        if (i2 < 32) {
            u(i2);
        }
        s0(i2);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
        byte[] binaryValue = getBinaryValue(base64Variant);
        outputStream.write(binaryValue);
        return binaryValue.length;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public abstract int releaseBuffered(OutputStream outputStream);

    protected void s0(int i2) {
        k("Invalid UTF-8 start byte 0x" + Integer.toHexString(i2));
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        throw new UnsupportedOperationException("Can not use ObjectMapper with non-blocking parser");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void t0(int i2, int i3) {
        this.f5090p = i3;
        _reportInvalidOther(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken u0() {
        this.x = this.x.createChildArrayContext(-1, -1);
        this.W = 5;
        this.X = 6;
        JsonToken jsonToken = JsonToken.START_ARRAY;
        this.f5104c = jsonToken;
        return jsonToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken v0() {
        this.x = this.x.createChildObjectContext(-1, -1);
        this.W = 2;
        this.X = 3;
        JsonToken jsonToken = JsonToken.START_OBJECT;
        this.f5104c = jsonToken;
        return jsonToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void w0() {
        this.v = Math.max(this.f5093s, this.d0);
        int i2 = this.f5090p;
        this.w = i2 - this.f5094t;
        this.u = this.f5092r + (i2 - this.c0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken x0(JsonToken jsonToken) {
        this.W = this.X;
        this.f5104c = jsonToken;
        return jsonToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken y0(int i2, String str) {
        this.z.resetWithString(str);
        this.L = str.length();
        this.E = 1;
        this.F = i2;
        this.W = this.X;
        JsonToken jsonToken = JsonToken.VALUE_NUMBER_INT;
        this.f5104c = jsonToken;
        return jsonToken;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonToken z0(int i2) {
        String str = e0[i2];
        this.z.resetWithString(str);
        if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            l("Non-standard token '%s': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow", str);
        }
        this.L = 0;
        this.E = 8;
        this.H = f0[i2];
        this.W = this.X;
        JsonToken jsonToken = JsonToken.VALUE_NUMBER_FLOAT;
        this.f5104c = jsonToken;
        return jsonToken;
    }
}
