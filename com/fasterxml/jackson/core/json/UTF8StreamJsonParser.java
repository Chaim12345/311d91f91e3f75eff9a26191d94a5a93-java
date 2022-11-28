package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import okio.Utf8;
import org.apache.commons.codec.language.Soundex;
import org.apache.http.message.TokenParser;
import org.bouncycastle.asn1.BERTags;
/* loaded from: classes.dex */
public class UTF8StreamJsonParser extends ParserBase {
    protected ObjectCodec O;
    protected final ByteQuadsCanonicalizer P;
    protected int[] Q;
    protected boolean R;
    protected int S;
    protected int T;
    protected int U;
    protected InputStream V;
    protected byte[] W;
    protected boolean X;
    private int _quad1;
    private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
    private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
    private static final int FEAT_MASK_NON_NUM_NUMBERS = JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
    private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
    private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
    protected static final int[] Y = CharTypes.getInputCodeLatin1();

    public UTF8StreamJsonParser(IOContext iOContext, int i2, InputStream inputStream, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, byte[] bArr, int i3, int i4, int i5, boolean z) {
        super(iOContext, i2);
        this.Q = new int[16];
        this.V = inputStream;
        this.O = objectCodec;
        this.P = byteQuadsCanonicalizer;
        this.W = bArr;
        this.f5090p = i3;
        this.f5091q = i4;
        this.f5094t = i3 - i5;
        this.f5092r = (-i3) + i5;
        this.X = z;
    }

    @Deprecated
    public UTF8StreamJsonParser(IOContext iOContext, int i2, InputStream inputStream, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, byte[] bArr, int i3, int i4, boolean z) {
        this(iOContext, i2, inputStream, objectCodec, byteQuadsCanonicalizer, bArr, i3, i4, 0, z);
    }

    private final void _checkMatchEnd(String str, int i2, int i3) {
        if (Character.isJavaIdentifierPart((char) i0(i3))) {
            B0(str.substring(0, i2));
        }
    }

    private final void _closeArrayScope() {
        _updateLocation();
        if (!this.x.inArray()) {
            Q(93, AbstractJsonLexerKt.END_OBJ);
        }
        this.x = this.x.clearAndGetParent();
    }

    private final void _closeObjectScope() {
        _updateLocation();
        if (!this.x.inObject()) {
            Q(125, AbstractJsonLexerKt.END_LIST);
        }
        this.x = this.x.clearAndGetParent();
    }

    private final JsonToken _closeScope(int i2) {
        JsonToken jsonToken;
        if (i2 == 125) {
            _closeObjectScope();
            jsonToken = JsonToken.END_OBJECT;
        } else {
            _closeArrayScope();
            jsonToken = JsonToken.END_ARRAY;
        }
        this.f5104c = jsonToken;
        return jsonToken;
    }

    private final int _decodeUtf8_2(int i2) {
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr = this.W;
        int i3 = this.f5090p;
        int i4 = i3 + 1;
        this.f5090p = i4;
        byte b2 = bArr[i3];
        if ((b2 & 192) != 128) {
            A0(b2 & 255, i4);
        }
        return ((i2 & 31) << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_3(int i2) {
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        int i3 = i2 & 15;
        byte[] bArr = this.W;
        int i4 = this.f5090p;
        int i5 = i4 + 1;
        this.f5090p = i5;
        byte b2 = bArr[i4];
        if ((b2 & 192) != 128) {
            A0(b2 & 255, i5);
        }
        int i6 = (i3 << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr2 = this.W;
        int i7 = this.f5090p;
        int i8 = i7 + 1;
        this.f5090p = i8;
        byte b3 = bArr2[i7];
        if ((b3 & 192) != 128) {
            A0(b3 & 255, i8);
        }
        return (i6 << 6) | (b3 & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_3fast(int i2) {
        int i3 = i2 & 15;
        byte[] bArr = this.W;
        int i4 = this.f5090p;
        int i5 = i4 + 1;
        this.f5090p = i5;
        byte b2 = bArr[i4];
        if ((b2 & 192) != 128) {
            A0(b2 & 255, i5);
        }
        int i6 = (i3 << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
        byte[] bArr2 = this.W;
        int i7 = this.f5090p;
        int i8 = i7 + 1;
        this.f5090p = i8;
        byte b3 = bArr2[i7];
        if ((b3 & 192) != 128) {
            A0(b3 & 255, i8);
        }
        return (i6 << 6) | (b3 & Utf8.REPLACEMENT_BYTE);
    }

    private final int _decodeUtf8_4(int i2) {
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr = this.W;
        int i3 = this.f5090p;
        int i4 = i3 + 1;
        this.f5090p = i4;
        byte b2 = bArr[i3];
        if ((b2 & 192) != 128) {
            A0(b2 & 255, i4);
        }
        int i5 = ((i2 & 7) << 6) | (b2 & Utf8.REPLACEMENT_BYTE);
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr2 = this.W;
        int i6 = this.f5090p;
        int i7 = i6 + 1;
        this.f5090p = i7;
        byte b3 = bArr2[i6];
        if ((b3 & 192) != 128) {
            A0(b3 & 255, i7);
        }
        int i8 = (i5 << 6) | (b3 & Utf8.REPLACEMENT_BYTE);
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr3 = this.W;
        int i9 = this.f5090p;
        int i10 = i9 + 1;
        this.f5090p = i10;
        byte b4 = bArr3[i9];
        if ((b4 & 192) != 128) {
            A0(b4 & 255, i10);
        }
        return ((i8 << 6) | (b4 & Utf8.REPLACEMENT_BYTE)) - 65536;
    }

    private final void _finishString2(char[] cArr, int i2) {
        int[] iArr = _icUTF8;
        byte[] bArr = this.W;
        while (true) {
            int i3 = this.f5090p;
            if (i3 >= this.f5091q) {
                q0();
                i3 = this.f5090p;
            }
            int i4 = 0;
            if (i2 >= cArr.length) {
                cArr = this.z.finishCurrentSegment();
                i2 = 0;
            }
            int min = Math.min(this.f5091q, (cArr.length - i2) + i3);
            while (true) {
                if (i3 >= min) {
                    this.f5090p = i3;
                    break;
                }
                int i5 = i3 + 1;
                int i6 = bArr[i3] & 255;
                if (iArr[i6] != 0) {
                    this.f5090p = i5;
                    if (i6 == 34) {
                        this.z.setCurrentLength(i2);
                        return;
                    }
                    int i7 = iArr[i6];
                    if (i7 == 1) {
                        i6 = I();
                    } else if (i7 == 2) {
                        i6 = _decodeUtf8_2(i6);
                    } else if (i7 == 3) {
                        i6 = this.f5091q - i5 >= 2 ? _decodeUtf8_3fast(i6) : _decodeUtf8_3(i6);
                    } else if (i7 == 4) {
                        int _decodeUtf8_4 = _decodeUtf8_4(i6);
                        int i8 = i2 + 1;
                        cArr[i2] = (char) (55296 | (_decodeUtf8_4 >> 10));
                        if (i8 >= cArr.length) {
                            cArr = this.z.finishCurrentSegment();
                            i2 = 0;
                        } else {
                            i2 = i8;
                        }
                        i6 = (_decodeUtf8_4 & 1023) | 56320;
                    } else if (i6 < 32) {
                        S(i6, "string value");
                    } else {
                        y0(i6);
                    }
                    if (i2 >= cArr.length) {
                        cArr = this.z.finishCurrentSegment();
                    } else {
                        i4 = i2;
                    }
                    i2 = i4 + 1;
                    cArr[i4] = (char) i6;
                } else {
                    cArr[i2] = (char) i6;
                    i3 = i5;
                    i2++;
                }
            }
        }
    }

    private final boolean _isNextTokenNameMaybe(int i2, SerializableString serializableString) {
        JsonToken v0;
        String u0 = u0(i2);
        this.x.setCurrentName(u0);
        boolean equals = u0.equals(serializableString.getValue());
        this.f5104c = JsonToken.FIELD_NAME;
        int _skipColon = _skipColon();
        _updateLocation();
        if (_skipColon == 34) {
            this.R = true;
            this.y = JsonToken.VALUE_STRING;
            return equals;
        }
        if (_skipColon == 45) {
            v0 = v0();
        } else if (_skipColon == 46) {
            v0 = t0();
        } else if (_skipColon == 91) {
            v0 = JsonToken.START_ARRAY;
        } else if (_skipColon == 102) {
            _matchFalse();
            v0 = JsonToken.VALUE_FALSE;
        } else if (_skipColon == 110) {
            _matchNull();
            v0 = JsonToken.VALUE_NULL;
        } else if (_skipColon == 116) {
            _matchTrue();
            v0 = JsonToken.VALUE_TRUE;
        } else if (_skipColon != 123) {
            switch (_skipColon) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    v0 = w0(_skipColon);
                    break;
                default:
                    v0 = o0(_skipColon);
                    break;
            }
        } else {
            v0 = JsonToken.START_OBJECT;
        }
        this.y = v0;
        return equals;
    }

    private final void _isNextTokenNameYes(int i2) {
        JsonToken jsonToken;
        this.f5104c = JsonToken.FIELD_NAME;
        _updateLocation();
        if (i2 == 34) {
            this.R = true;
            jsonToken = JsonToken.VALUE_STRING;
        } else if (i2 == 91) {
            jsonToken = JsonToken.START_ARRAY;
        } else if (i2 == 102) {
            _matchFalse();
            jsonToken = JsonToken.VALUE_FALSE;
        } else if (i2 == 110) {
            _matchNull();
            jsonToken = JsonToken.VALUE_NULL;
        } else if (i2 == 116) {
            _matchTrue();
            jsonToken = JsonToken.VALUE_TRUE;
        } else if (i2 == 123) {
            jsonToken = JsonToken.START_OBJECT;
        } else if (i2 == 45) {
            jsonToken = v0();
        } else if (i2 != 46) {
            switch (i2) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    jsonToken = w0(i2);
                    break;
                default:
                    jsonToken = o0(i2);
                    break;
            }
        } else {
            jsonToken = t0();
        }
        this.y = jsonToken;
    }

    private final void _matchToken2(String str, int i2) {
        int i3;
        int i4;
        int length = str.length();
        do {
            if ((this.f5090p >= this.f5091q && !p0()) || this.W[this.f5090p] != str.charAt(i2)) {
                B0(str.substring(0, i2));
            }
            i3 = this.f5090p + 1;
            this.f5090p = i3;
            i2++;
        } while (i2 < length);
        if ((i3 < this.f5091q || p0()) && (i4 = this.W[this.f5090p] & 255) >= 48 && i4 != 93 && i4 != 125) {
            _checkMatchEnd(str, i2, i4);
        }
    }

    private final JsonToken _nextAfterName() {
        JsonReadContext createChildObjectContext;
        this.B = false;
        JsonToken jsonToken = this.y;
        this.y = null;
        if (jsonToken != JsonToken.START_ARRAY) {
            if (jsonToken == JsonToken.START_OBJECT) {
                createChildObjectContext = this.x.createChildObjectContext(this.v, this.w);
            }
            this.f5104c = jsonToken;
            return jsonToken;
        }
        createChildObjectContext = this.x.createChildArrayContext(this.v, this.w);
        this.x = createChildObjectContext;
        this.f5104c = jsonToken;
        return jsonToken;
    }

    private final JsonToken _nextTokenNotInObject(int i2) {
        JsonToken v0;
        if (i2 == 34) {
            this.R = true;
            v0 = JsonToken.VALUE_STRING;
        } else if (i2 == 45) {
            v0 = v0();
        } else if (i2 == 46) {
            v0 = t0();
        } else if (i2 == 91) {
            this.x = this.x.createChildArrayContext(this.v, this.w);
            v0 = JsonToken.START_ARRAY;
        } else if (i2 == 102) {
            _matchFalse();
            v0 = JsonToken.VALUE_FALSE;
        } else if (i2 == 110) {
            _matchNull();
            v0 = JsonToken.VALUE_NULL;
        } else if (i2 == 116) {
            _matchTrue();
            v0 = JsonToken.VALUE_TRUE;
        } else if (i2 != 123) {
            switch (i2) {
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    v0 = w0(i2);
                    break;
                default:
                    v0 = o0(i2);
                    break;
            }
        } else {
            this.x = this.x.createChildObjectContext(this.v, this.w);
            v0 = JsonToken.START_OBJECT;
        }
        this.f5104c = v0;
        return v0;
    }

    private static final int _padLastQuad(int i2, int i3) {
        return i3 == 4 ? i2 : i2 | ((-1) << (i3 << 3));
    }

    private final JsonToken _parseFloat(char[] cArr, int i2, int i3, boolean z, int i4) {
        int i5;
        boolean z2;
        int i6 = 0;
        if (i3 == 46) {
            if (i2 >= cArr.length) {
                cArr = this.z.finishCurrentSegment();
                i2 = 0;
            }
            cArr[i2] = (char) i3;
            i2++;
            i5 = 0;
            while (true) {
                if (this.f5090p >= this.f5091q && !p0()) {
                    z2 = true;
                    break;
                }
                byte[] bArr = this.W;
                int i7 = this.f5090p;
                this.f5090p = i7 + 1;
                i3 = bArr[i7] & 255;
                if (i3 < 48 || i3 > 57) {
                    break;
                }
                i5++;
                if (i2 >= cArr.length) {
                    cArr = this.z.finishCurrentSegment();
                    i2 = 0;
                }
                cArr[i2] = (char) i3;
                i2++;
            }
            z2 = false;
            if (i5 == 0) {
                D(i3, "Decimal point not followed by a digit");
            }
        } else {
            i5 = 0;
            z2 = false;
        }
        if (i3 == 101 || i3 == 69) {
            if (i2 >= cArr.length) {
                cArr = this.z.finishCurrentSegment();
                i2 = 0;
            }
            int i8 = i2 + 1;
            cArr[i2] = (char) i3;
            if (this.f5090p >= this.f5091q) {
                q0();
            }
            byte[] bArr2 = this.W;
            int i9 = this.f5090p;
            this.f5090p = i9 + 1;
            int i10 = bArr2[i9] & 255;
            if (i10 == 45 || i10 == 43) {
                if (i8 >= cArr.length) {
                    cArr = this.z.finishCurrentSegment();
                    i8 = 0;
                }
                int i11 = i8 + 1;
                cArr[i8] = (char) i10;
                if (this.f5090p >= this.f5091q) {
                    q0();
                }
                byte[] bArr3 = this.W;
                int i12 = this.f5090p;
                this.f5090p = i12 + 1;
                i10 = bArr3[i12] & 255;
                i8 = i11;
            }
            i3 = i10;
            int i13 = 0;
            while (i3 >= 48 && i3 <= 57) {
                i13++;
                if (i8 >= cArr.length) {
                    cArr = this.z.finishCurrentSegment();
                    i8 = 0;
                }
                int i14 = i8 + 1;
                cArr[i8] = (char) i3;
                if (this.f5090p >= this.f5091q && !p0()) {
                    i6 = i13;
                    z2 = true;
                    i2 = i14;
                    break;
                }
                byte[] bArr4 = this.W;
                int i15 = this.f5090p;
                this.f5090p = i15 + 1;
                i3 = bArr4[i15] & 255;
                i8 = i14;
            }
            i6 = i13;
            i2 = i8;
            if (i6 == 0) {
                D(i3, "Exponent indicator not followed by a digit");
            }
        }
        if (!z2) {
            this.f5090p--;
            if (this.x.inRoot()) {
                _verifyRootSpace(i3);
            }
        }
        this.z.setCurrentLength(i2);
        return f0(z, i4, i5, i6);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0044, code lost:
        if (r3 == 46) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
        if (r3 == 101) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004c, code lost:
        if (r3 != 69) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004f, code lost:
        r6.f5090p = r10 - 1;
        r6.z.setCurrentLength(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005e, code lost:
        if (r6.x.inRoot() == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0060, code lost:
        _verifyRootSpace(r6.W[r6.f5090p] & 255);
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006f, code lost:
        return g0(r9, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0076, code lost:
        return _parseFloat(r1, r2, r3, r9, r5);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final JsonToken _parseNumber2(char[] cArr, int i2, boolean z, int i3) {
        char[] cArr2 = cArr;
        int i4 = i2;
        int i5 = i3;
        while (true) {
            if (this.f5090p >= this.f5091q && !p0()) {
                this.z.setCurrentLength(i4);
                return g0(z, i5);
            }
            byte[] bArr = this.W;
            int i6 = this.f5090p;
            int i7 = i6 + 1;
            this.f5090p = i7;
            int i8 = bArr[i6] & 255;
            if (i8 > 57 || i8 < 48) {
                break;
            }
            if (i4 >= cArr2.length) {
                i4 = 0;
                cArr2 = this.z.finishCurrentSegment();
            }
            cArr2[i4] = (char) i8;
            i5++;
            i4++;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0043, code lost:
        p(" in a comment", null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0049, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void _skipCComment() {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this.f5090p >= this.f5091q && !p0()) {
                break;
            }
            byte[] bArr = this.W;
            int i2 = this.f5090p;
            int i3 = i2 + 1;
            this.f5090p = i3;
            int i4 = bArr[i2] & 255;
            int i5 = inputCodeComment[i4];
            if (i5 != 0) {
                if (i5 == 2) {
                    _skipUtf8_2();
                } else if (i5 == 3) {
                    _skipUtf8_3();
                } else if (i5 == 4) {
                    _skipUtf8_4(i4);
                } else if (i5 == 10) {
                    this.f5093s++;
                    this.f5094t = i3;
                } else if (i5 == 13) {
                    D0();
                } else if (i5 == 42) {
                    if (i3 >= this.f5091q && !p0()) {
                        break;
                    }
                    byte[] bArr2 = this.W;
                    int i6 = this.f5090p;
                    if (bArr2[i6] == 47) {
                        this.f5090p = i6 + 1;
                        return;
                    }
                } else {
                    y0(i4);
                }
            }
        }
    }

    private final int _skipColon() {
        int i2;
        byte b2;
        int i3;
        byte b3;
        int i4 = this.f5090p;
        if (i4 + 4 >= this.f5091q) {
            return _skipColon2(false);
        }
        byte[] bArr = this.W;
        byte b4 = bArr[i4];
        if (b4 == 58) {
            i2 = i4 + 1;
            this.f5090p = i2;
            b2 = bArr[i2];
            if (b2 > 32) {
                if (b2 == 47 || b2 == 35) {
                    return _skipColon2(true);
                }
                this.f5090p = i2 + 1;
                return b2;
            }
            if (b2 == 32 || b2 == 9) {
                i3 = i2 + 1;
                this.f5090p = i3;
                b3 = bArr[i3];
                if (b3 > 32) {
                    if (b3 == 47 || b3 == 35) {
                        return _skipColon2(true);
                    }
                    this.f5090p = i3 + 1;
                    return b3;
                }
            }
            return _skipColon2(true);
        }
        if (b4 == 32 || b4 == 9) {
            int i5 = i4 + 1;
            this.f5090p = i5;
            b4 = bArr[i5];
        }
        if (b4 == 58) {
            i2 = this.f5090p + 1;
            this.f5090p = i2;
            b2 = bArr[i2];
            if (b2 > 32) {
                if (b2 == 47 || b2 == 35) {
                    return _skipColon2(true);
                }
                this.f5090p = i2 + 1;
                return b2;
            }
            if (b2 == 32 || b2 == 9) {
                i3 = i2 + 1;
                this.f5090p = i3;
                b3 = bArr[i3];
                if (b3 > 32) {
                    if (b3 == 47 || b3 == 35) {
                        return _skipColon2(true);
                    }
                    this.f5090p = i3 + 1;
                    return b3;
                }
            }
            return _skipColon2(true);
        }
        return _skipColon2(false);
    }

    private final int _skipColon2(boolean z) {
        while (true) {
            if (this.f5090p >= this.f5091q && !p0()) {
                p(" within/between " + this.x.typeDesc() + " entries", null);
                return -1;
            }
            byte[] bArr = this.W;
            int i2 = this.f5090p;
            int i3 = i2 + 1;
            this.f5090p = i3;
            int i4 = bArr[i2] & 255;
            if (i4 > 32) {
                if (i4 == 47) {
                    _skipComment();
                } else if (i4 != 35 || !_skipYAMLComment()) {
                    if (z) {
                        return i4;
                    }
                    if (i4 != 58) {
                        s(i4, "was expecting a colon to separate field name and value");
                    }
                    z = true;
                }
            } else if (i4 != 32) {
                if (i4 == 10) {
                    this.f5093s++;
                    this.f5094t = i3;
                } else if (i4 == 13) {
                    D0();
                } else if (i4 != 9) {
                    u(i4);
                }
            }
        }
    }

    private final int _skipColonFast(int i2) {
        byte[] bArr = this.W;
        int i3 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 == 58) {
            int i4 = i3 + 1;
            byte b3 = bArr[i3];
            if (b3 > 32) {
                if (b3 != 47 && b3 != 35) {
                    this.f5090p = i4;
                    return b3;
                }
            } else if (b3 == 32 || b3 == 9) {
                int i5 = i4 + 1;
                byte b4 = bArr[i4];
                if (b4 > 32 && b4 != 47 && b4 != 35) {
                    this.f5090p = i5;
                    return b4;
                }
                i4 = i5;
            }
            this.f5090p = i4 - 1;
            return _skipColon2(true);
        }
        if (b2 == 32 || b2 == 9) {
            int i6 = i3 + 1;
            byte b5 = bArr[i3];
            i3 = i6;
            b2 = b5;
        }
        if (b2 != 58) {
            this.f5090p = i3 - 1;
            return _skipColon2(false);
        }
        int i7 = i3 + 1;
        byte b6 = bArr[i3];
        if (b6 > 32) {
            if (b6 != 47 && b6 != 35) {
                this.f5090p = i7;
                return b6;
            }
        } else if (b6 == 32 || b6 == 9) {
            int i8 = i7 + 1;
            byte b7 = bArr[i7];
            if (b7 > 32 && b7 != 47 && b7 != 35) {
                this.f5090p = i8;
                return b7;
            }
            i7 = i8;
        }
        this.f5090p = i7 - 1;
        return _skipColon2(true);
    }

    private final void _skipComment() {
        if ((this.f5048a & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0) {
            s(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this.f5090p >= this.f5091q && !p0()) {
            p(" in a comment", null);
        }
        byte[] bArr = this.W;
        int i2 = this.f5090p;
        this.f5090p = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (i3 == 47) {
            _skipLine();
        } else if (i3 == 42) {
            _skipCComment();
        } else {
            s(i3, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipLine() {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            if (this.f5090p >= this.f5091q && !p0()) {
                return;
            }
            byte[] bArr = this.W;
            int i2 = this.f5090p;
            int i3 = i2 + 1;
            this.f5090p = i3;
            int i4 = bArr[i2] & 255;
            int i5 = inputCodeComment[i4];
            if (i5 != 0) {
                if (i5 == 2) {
                    _skipUtf8_2();
                } else if (i5 == 3) {
                    _skipUtf8_3();
                } else if (i5 == 4) {
                    _skipUtf8_4(i4);
                } else if (i5 == 10) {
                    this.f5093s++;
                    this.f5094t = i3;
                    return;
                } else if (i5 == 13) {
                    D0();
                    return;
                } else if (i5 != 42 && i5 < 0) {
                    y0(i4);
                }
            }
        }
    }

    private final void _skipUtf8_2() {
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr = this.W;
        int i2 = this.f5090p;
        int i3 = i2 + 1;
        this.f5090p = i3;
        byte b2 = bArr[i2];
        if ((b2 & 192) != 128) {
            A0(b2 & 255, i3);
        }
    }

    private final void _skipUtf8_3() {
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr = this.W;
        int i2 = this.f5090p;
        int i3 = i2 + 1;
        this.f5090p = i3;
        byte b2 = bArr[i2];
        if ((b2 & 192) != 128) {
            A0(b2 & 255, i3);
        }
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr2 = this.W;
        int i4 = this.f5090p;
        int i5 = i4 + 1;
        this.f5090p = i5;
        byte b3 = bArr2[i4];
        if ((b3 & 192) != 128) {
            A0(b3 & 255, i5);
        }
    }

    private final void _skipUtf8_4(int i2) {
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr = this.W;
        int i3 = this.f5090p;
        int i4 = i3 + 1;
        this.f5090p = i4;
        byte b2 = bArr[i3];
        if ((b2 & 192) != 128) {
            A0(b2 & 255, i4);
        }
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr2 = this.W;
        int i5 = this.f5090p;
        int i6 = i5 + 1;
        this.f5090p = i6;
        byte b3 = bArr2[i5];
        if ((b3 & 192) != 128) {
            A0(b3 & 255, i6);
        }
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr3 = this.W;
        int i7 = this.f5090p;
        int i8 = i7 + 1;
        this.f5090p = i8;
        byte b4 = bArr3[i7];
        if ((b4 & 192) != 128) {
            A0(b4 & 255, i8);
        }
    }

    private final int _skipWS() {
        while (true) {
            int i2 = this.f5090p;
            if (i2 >= this.f5091q) {
                return _skipWS2();
            }
            byte[] bArr = this.W;
            int i3 = i2 + 1;
            this.f5090p = i3;
            int i4 = bArr[i2] & 255;
            if (i4 > 32) {
                if (i4 == 47 || i4 == 35) {
                    this.f5090p = i3 - 1;
                    return _skipWS2();
                }
                return i4;
            } else if (i4 != 32) {
                if (i4 == 10) {
                    this.f5093s++;
                    this.f5094t = i3;
                } else if (i4 == 13) {
                    D0();
                } else if (i4 != 9) {
                    u(i4);
                }
            }
        }
    }

    private final int _skipWS2() {
        int i2;
        while (true) {
            if (this.f5090p >= this.f5091q && !p0()) {
                throw b("Unexpected end-of-input within/between " + this.x.typeDesc() + " entries");
            }
            byte[] bArr = this.W;
            int i3 = this.f5090p;
            int i4 = i3 + 1;
            this.f5090p = i4;
            i2 = bArr[i3] & 255;
            if (i2 > 32) {
                if (i2 == 47) {
                    _skipComment();
                } else if (i2 != 35 || !_skipYAMLComment()) {
                    break;
                }
            } else if (i2 != 32) {
                if (i2 == 10) {
                    this.f5093s++;
                    this.f5094t = i4;
                } else if (i2 == 13) {
                    D0();
                } else if (i2 != 9) {
                    u(i2);
                }
            }
        }
        return i2;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0082 A[SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0049 -> B:25:0x0052). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x004f -> B:25:0x0052). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int _skipWSOrEnd() {
        /*
            r9 = this;
            int r0 = r9.f5090p
            int r1 = r9.f5091q
            if (r0 < r1) goto L11
            boolean r0 = r9.p0()
            if (r0 != 0) goto L11
            int r0 = r9.J()
            return r0
        L11:
            byte[] r0 = r9.W
            int r1 = r9.f5090p
            int r2 = r1 + 1
            r9.f5090p = r2
            r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 35
            r3 = 47
            r4 = 32
            if (r0 <= r4) goto L34
            if (r0 == r3) goto L2b
            if (r0 != r1) goto L2a
            goto L2b
        L2a:
            return r0
        L2b:
            int r2 = r2 + (-1)
            r9.f5090p = r2
        L2f:
            int r0 = r9._skipWSOrEnd2()
            return r0
        L34:
            r5 = 9
            r6 = 13
            r7 = 10
            if (r0 == r4) goto L52
            if (r0 != r7) goto L47
            int r0 = r9.f5093s
            int r0 = r0 + 1
            r9.f5093s = r0
            r9.f5094t = r2
            goto L52
        L47:
            if (r0 != r6) goto L4d
        L49:
            r9.D0()
            goto L52
        L4d:
            if (r0 == r5) goto L52
        L4f:
            r9.u(r0)
        L52:
            int r0 = r9.f5090p
            int r2 = r9.f5091q
            if (r0 >= r2) goto L82
            byte[] r2 = r9.W
            int r8 = r0 + 1
            r9.f5090p = r8
            r0 = r2[r0]
            r0 = r0 & 255(0xff, float:3.57E-43)
            if (r0 <= r4) goto L6f
            if (r0 == r3) goto L6a
            if (r0 != r1) goto L69
            goto L6a
        L69:
            return r0
        L6a:
            int r8 = r8 + (-1)
            r9.f5090p = r8
            goto L2f
        L6f:
            if (r0 == r4) goto L52
            if (r0 != r7) goto L7c
            int r0 = r9.f5093s
            int r0 = r0 + 1
            r9.f5093s = r0
            r9.f5094t = r8
            goto L52
        L7c:
            if (r0 != r6) goto L7f
            goto L49
        L7f:
            if (r0 == r5) goto L52
            goto L4f
        L82:
            int r0 = r9._skipWSOrEnd2()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._skipWSOrEnd():int");
    }

    private final int _skipWSOrEnd2() {
        int i2;
        while (true) {
            if (this.f5090p >= this.f5091q && !p0()) {
                return J();
            }
            byte[] bArr = this.W;
            int i3 = this.f5090p;
            int i4 = i3 + 1;
            this.f5090p = i4;
            i2 = bArr[i3] & 255;
            if (i2 > 32) {
                if (i2 == 47) {
                    _skipComment();
                } else if (i2 != 35 || !_skipYAMLComment()) {
                    break;
                }
            } else if (i2 != 32) {
                if (i2 == 10) {
                    this.f5093s++;
                    this.f5094t = i4;
                } else if (i2 == 13) {
                    D0();
                } else if (i2 != 9) {
                    u(i2);
                }
            }
        }
        return i2;
    }

    private final boolean _skipYAMLComment() {
        if ((this.f5048a & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0) {
            return false;
        }
        _skipLine();
        return true;
    }

    private final void _updateLocation() {
        this.v = this.f5093s;
        int i2 = this.f5090p;
        this.u = this.f5092r + i2;
        this.w = i2 - this.f5094t;
    }

    private final void _updateNameLocation() {
        this.T = this.f5093s;
        int i2 = this.f5090p;
        this.S = i2;
        this.U = i2 - this.f5094t;
    }

    private final int _verifyNoLeadingZeroes() {
        int i2;
        if ((this.f5090p < this.f5091q || p0()) && (i2 = this.W[this.f5090p] & 255) >= 48 && i2 <= 57) {
            if ((this.f5048a & FEAT_MASK_LEADING_ZEROS) == 0) {
                w("Leading zeroes not allowed");
            }
            this.f5090p++;
            if (i2 == 48) {
                do {
                    if (this.f5090p >= this.f5091q && !p0()) {
                        break;
                    }
                    byte[] bArr = this.W;
                    int i3 = this.f5090p;
                    i2 = bArr[i3] & 255;
                    if (i2 < 48 || i2 > 57) {
                        return 48;
                    }
                    this.f5090p = i3 + 1;
                } while (i2 == 48);
            }
            return i2;
        }
        return 48;
    }

    private final void _verifyRootSpace(int i2) {
        int i3 = this.f5090p + 1;
        this.f5090p = i3;
        if (i2 != 9) {
            if (i2 == 10) {
                this.f5093s++;
                this.f5094t = i3;
            } else if (i2 == 13) {
                D0();
            } else if (i2 != 32) {
                r(i2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00c2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final String addName(int[] iArr, int i2, int i3) {
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
                    z0(i12);
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
        return this.P.addName(str, iArr, i2);
    }

    private final String findName(int i2, int i3) {
        int _padLastQuad = _padLastQuad(i2, i3);
        String findName = this.P.findName(_padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this.Q;
        iArr[0] = _padLastQuad;
        return addName(iArr, 1, i3);
    }

    private final String findName(int i2, int i3, int i4) {
        int _padLastQuad = _padLastQuad(i3, i4);
        String findName = this.P.findName(i2, _padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this.Q;
        iArr[0] = i2;
        iArr[1] = _padLastQuad;
        return addName(iArr, 2, i4);
    }

    private final String findName(int i2, int i3, int i4, int i5) {
        int _padLastQuad = _padLastQuad(i4, i5);
        String findName = this.P.findName(i2, i3, _padLastQuad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this.Q;
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = _padLastQuad(_padLastQuad, i5);
        return addName(iArr, 3, i5);
    }

    private final String findName(int[] iArr, int i2, int i3, int i4) {
        if (i2 >= iArr.length) {
            iArr = ParserBase.a0(iArr, iArr.length);
            this.Q = iArr;
        }
        int i5 = i2 + 1;
        iArr[i2] = _padLastQuad(i3, i4);
        String findName = this.P.findName(iArr, i5);
        return findName == null ? addName(iArr, i5, i4) : findName;
    }

    private int nextByte() {
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr = this.W;
        int i2 = this.f5090p;
        this.f5090p = i2 + 1;
        return bArr[i2] & 255;
    }

    private final String parseName(int i2, int i3, int i4) {
        return F0(this.Q, 0, i2, i3, i4);
    }

    private final String parseName(int i2, int i3, int i4, int i5) {
        int[] iArr = this.Q;
        iArr[0] = i2;
        return F0(iArr, 1, i3, i4, i5);
    }

    private final String parseName(int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = this.Q;
        iArr[0] = i2;
        iArr[1] = i3;
        return F0(iArr, 2, i4, i5, i6);
    }

    protected void A0(int i2, int i3) {
        this.f5090p = i3;
        _reportInvalidOther(i2);
    }

    protected void B0(String str) {
        C0(str, T());
    }

    protected void C0(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this.f5090p >= this.f5091q && !p0()) {
                break;
            }
            byte[] bArr = this.W;
            int i2 = this.f5090p;
            this.f5090p = i2 + 1;
            char i0 = (char) i0(bArr[i2]);
            if (!Character.isJavaIdentifierPart(i0)) {
                break;
            }
            sb.append(i0);
            if (sb.length() >= 256) {
                sb.append("...");
                break;
            }
        }
        m("Unrecognized token '%s': was expecting %s", sb, str2);
    }

    protected final void D0() {
        if (this.f5090p < this.f5091q || p0()) {
            byte[] bArr = this.W;
            int i2 = this.f5090p;
            if (bArr[i2] == 10) {
                this.f5090p = i2 + 1;
            }
        }
        this.f5093s++;
        this.f5094t = this.f5090p;
    }

    protected void E0() {
        this.R = false;
        int[] iArr = _icUTF8;
        byte[] bArr = this.W;
        while (true) {
            int i2 = this.f5090p;
            int i3 = this.f5091q;
            if (i2 >= i3) {
                q0();
                i2 = this.f5090p;
                i3 = this.f5091q;
            }
            while (true) {
                if (i2 >= i3) {
                    this.f5090p = i2;
                    break;
                }
                int i4 = i2 + 1;
                int i5 = bArr[i2] & 255;
                if (iArr[i5] != 0) {
                    this.f5090p = i4;
                    if (i5 == 34) {
                        return;
                    }
                    int i6 = iArr[i5];
                    if (i6 == 1) {
                        I();
                    } else if (i6 == 2) {
                        _skipUtf8_2();
                    } else if (i6 == 3) {
                        _skipUtf8_3();
                    } else if (i6 == 4) {
                        _skipUtf8_4(i5);
                    } else if (i5 < 32) {
                        S(i5, "string value");
                    } else {
                        y0(i5);
                    }
                } else {
                    i2 = i4;
                }
            }
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void F() {
        if (this.V != null) {
            if (this.f5088n.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this.V.close();
            }
            this.V = null;
        }
    }

    protected final String F0(int[] iArr, int i2, int i3, int i4, int i5) {
        int[] iArr2 = Y;
        while (true) {
            if (iArr2[i4] != 0) {
                if (i4 == 34) {
                    break;
                }
                if (i4 != 92) {
                    S(i4, AppMeasurementSdk.ConditionalUserProperty.NAME);
                } else {
                    i4 = I();
                }
                if (i4 > 127) {
                    int i6 = 0;
                    if (i5 >= 4) {
                        if (i2 >= iArr.length) {
                            iArr = ParserBase.a0(iArr, iArr.length);
                            this.Q = iArr;
                        }
                        iArr[i2] = i3;
                        i2++;
                        i3 = 0;
                        i5 = 0;
                    }
                    int i7 = i3 << 8;
                    if (i4 < 2048) {
                        i3 = i7 | (i4 >> 6) | 192;
                        i5++;
                    } else {
                        int i8 = i7 | (i4 >> 12) | BERTags.FLAGS;
                        int i9 = i5 + 1;
                        if (i9 >= 4) {
                            if (i2 >= iArr.length) {
                                iArr = ParserBase.a0(iArr, iArr.length);
                                this.Q = iArr;
                            }
                            iArr[i2] = i8;
                            i2++;
                            i9 = 0;
                        } else {
                            i6 = i8;
                        }
                        i3 = (i6 << 8) | ((i4 >> 6) & 63) | 128;
                        i5 = i9 + 1;
                    }
                    i4 = (i4 & 63) | 128;
                }
            }
            if (i5 < 4) {
                i5++;
                i3 = (i3 << 8) | i4;
            } else {
                if (i2 >= iArr.length) {
                    iArr = ParserBase.a0(iArr, iArr.length);
                    this.Q = iArr;
                }
                iArr[i2] = i3;
                i3 = i4;
                i2++;
                i5 = 1;
            }
            if (this.f5090p >= this.f5091q && !p0()) {
                p(" in field name", JsonToken.FIELD_NAME);
            }
            byte[] bArr = this.W;
            int i10 = this.f5090p;
            this.f5090p = i10 + 1;
            i4 = bArr[i10] & 255;
        }
        if (i5 > 0) {
            if (i2 >= iArr.length) {
                iArr = ParserBase.a0(iArr, iArr.length);
                this.Q = iArr;
            }
            iArr[i2] = _padLastQuad(i3, i5);
            i2++;
        }
        String findName = this.P.findName(iArr, i2);
        return findName == null ? addName(iArr, i2, i5) : findName;
    }

    protected final String G0(int i2, int i3, int i4) {
        int[] iArr = this.Q;
        iArr[0] = this._quad1;
        iArr[1] = i3;
        iArr[2] = i4;
        byte[] bArr = this.W;
        int[] iArr2 = Y;
        int i5 = i2;
        int i6 = 3;
        while (true) {
            int i7 = this.f5090p;
            if (i7 + 4 > this.f5091q) {
                return F0(this.Q, i6, 0, i5, 0);
            }
            int i8 = i7 + 1;
            this.f5090p = i8;
            int i9 = bArr[i7] & 255;
            if (iArr2[i9] != 0) {
                return i9 == 34 ? findName(this.Q, i6, i5, 1) : F0(this.Q, i6, i5, i9, 1);
            }
            int i10 = (i5 << 8) | i9;
            int i11 = i8 + 1;
            this.f5090p = i11;
            int i12 = bArr[i8] & 255;
            if (iArr2[i12] != 0) {
                return i12 == 34 ? findName(this.Q, i6, i10, 2) : F0(this.Q, i6, i10, i12, 2);
            }
            int i13 = (i10 << 8) | i12;
            int i14 = i11 + 1;
            this.f5090p = i14;
            int i15 = bArr[i11] & 255;
            if (iArr2[i15] != 0) {
                return i15 == 34 ? findName(this.Q, i6, i13, 3) : F0(this.Q, i6, i13, i15, 3);
            }
            int i16 = (i13 << 8) | i15;
            this.f5090p = i14 + 1;
            int i17 = bArr[i14] & 255;
            if (iArr2[i17] != 0) {
                return i17 == 34 ? findName(this.Q, i6, i16, 4) : F0(this.Q, i6, i16, i17, 4);
            }
            int[] iArr3 = this.Q;
            if (i6 >= iArr3.length) {
                this.Q = ParserBase.a0(iArr3, i6);
            }
            this.Q[i6] = i16;
            i5 = i17;
            i6++;
        }
    }

    protected final String H0(int i2) {
        byte[] bArr = this.W;
        int[] iArr = Y;
        int i3 = this.f5090p;
        int i4 = i3 + 1;
        this.f5090p = i4;
        int i5 = bArr[i3] & 255;
        if (iArr[i5] != 0) {
            int i6 = this._quad1;
            return i5 == 34 ? findName(i6, i2, 1) : parseName(i6, i2, i5, 1);
        }
        int i7 = (i2 << 8) | i5;
        int i8 = i4 + 1;
        this.f5090p = i8;
        int i9 = bArr[i4] & 255;
        if (iArr[i9] != 0) {
            int i10 = this._quad1;
            return i9 == 34 ? findName(i10, i7, 2) : parseName(i10, i7, i9, 2);
        }
        int i11 = (i7 << 8) | i9;
        int i12 = i8 + 1;
        this.f5090p = i12;
        int i13 = bArr[i8] & 255;
        if (iArr[i13] != 0) {
            int i14 = this._quad1;
            return i13 == 34 ? findName(i14, i11, 3) : parseName(i14, i11, i13, 3);
        }
        int i15 = (i11 << 8) | i13;
        this.f5090p = i12 + 1;
        int i16 = bArr[i12] & 255;
        return iArr[i16] != 0 ? i16 == 34 ? findName(this._quad1, i15, 4) : parseName(this._quad1, i15, i16, 4) : I0(i16, i15);
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected char I() {
        if (this.f5090p >= this.f5091q && !p0()) {
            p(" in character escape sequence", JsonToken.VALUE_STRING);
        }
        byte[] bArr = this.W;
        int i2 = this.f5090p;
        this.f5090p = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 == 34 || b2 == 47 || b2 == 92) {
            return (char) b2;
        }
        if (b2 != 98) {
            if (b2 != 102) {
                if (b2 != 110) {
                    if (b2 != 114) {
                        if (b2 != 116) {
                            if (b2 != 117) {
                                return M((char) i0(b2));
                            }
                            int i3 = 0;
                            for (int i4 = 0; i4 < 4; i4++) {
                                if (this.f5090p >= this.f5091q && !p0()) {
                                    p(" in character escape sequence", JsonToken.VALUE_STRING);
                                }
                                byte[] bArr2 = this.W;
                                int i5 = this.f5090p;
                                this.f5090p = i5 + 1;
                                byte b3 = bArr2[i5];
                                int charToHex = CharTypes.charToHex(b3);
                                if (charToHex < 0) {
                                    s(b3 & 255, "expected a hex-digit for character escape sequence");
                                }
                                i3 = (i3 << 4) | charToHex;
                            }
                            return (char) i3;
                        }
                        return '\t';
                    }
                    return TokenParser.CR;
                }
                return '\n';
            }
            return '\f';
        }
        return '\b';
    }

    protected final String I0(int i2, int i3) {
        byte[] bArr = this.W;
        int[] iArr = Y;
        int i4 = this.f5090p;
        int i5 = i4 + 1;
        this.f5090p = i5;
        int i6 = bArr[i4] & 255;
        if (iArr[i6] != 0) {
            return i6 == 34 ? findName(this._quad1, i3, i2, 1) : parseName(this._quad1, i3, i2, i6, 1);
        }
        int i7 = (i2 << 8) | i6;
        int i8 = i5 + 1;
        this.f5090p = i8;
        int i9 = bArr[i5] & 255;
        if (iArr[i9] != 0) {
            return i9 == 34 ? findName(this._quad1, i3, i7, 2) : parseName(this._quad1, i3, i7, i9, 2);
        }
        int i10 = (i7 << 8) | i9;
        int i11 = i8 + 1;
        this.f5090p = i11;
        int i12 = bArr[i8] & 255;
        if (iArr[i12] != 0) {
            return i12 == 34 ? findName(this._quad1, i3, i10, 3) : parseName(this._quad1, i3, i10, i12, 3);
        }
        int i13 = (i10 << 8) | i12;
        this.f5090p = i11 + 1;
        int i14 = bArr[i11] & 255;
        return iArr[i14] != 0 ? i14 == 34 ? findName(this._quad1, i3, i13, 4) : parseName(this._quad1, i3, i13, i14, 4) : G0(i14, i3, i13);
    }

    protected String J0() {
        if (this.f5090p >= this.f5091q && !p0()) {
            p(": was expecting closing '\"' for name", JsonToken.FIELD_NAME);
        }
        byte[] bArr = this.W;
        int i2 = this.f5090p;
        this.f5090p = i2 + 1;
        int i3 = bArr[i2] & 255;
        return i3 == 34 ? "" : F0(this.Q, 0, 0, i3, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.fasterxml.jackson.core.base.ParserBase
    public void P() {
        byte[] bArr;
        byte[] bArr2;
        super.P();
        this.P.release();
        if (!this.X || (bArr = this.W) == null || bArr == (bArr2 = ParserMinimalBase.f5095e)) {
            return;
        }
        this.W = bArr2;
        this.f5088n.releaseReadIOBuffer(bArr);
    }

    protected String _finishAndReturnString() {
        int i2 = this.f5090p;
        if (i2 >= this.f5091q) {
            q0();
            i2 = this.f5090p;
        }
        int i3 = 0;
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int min = Math.min(this.f5091q, emptyAndGetCurrentSegment.length + i2);
        byte[] bArr = this.W;
        while (true) {
            if (i2 >= min) {
                break;
            }
            int i4 = bArr[i2] & 255;
            if (iArr[i4] == 0) {
                i2++;
                emptyAndGetCurrentSegment[i3] = (char) i4;
                i3++;
            } else if (i4 == 34) {
                this.f5090p = i2 + 1;
                return this.z.setCurrentAndReturn(i3);
            }
        }
        this.f5090p = i2;
        _finishString2(emptyAndGetCurrentSegment, i3);
        return this.z.contentsAsString();
    }

    protected final void _matchFalse() {
        int i2;
        int i3 = this.f5090p;
        if (i3 + 4 < this.f5091q) {
            byte[] bArr = this.W;
            int i4 = i3 + 1;
            if (bArr[i3] == 97) {
                int i5 = i4 + 1;
                if (bArr[i4] == 108) {
                    int i6 = i5 + 1;
                    if (bArr[i5] == 115) {
                        int i7 = i6 + 1;
                        if (bArr[i6] == 101 && ((i2 = bArr[i7] & 255) < 48 || i2 == 93 || i2 == 125)) {
                            this.f5090p = i7;
                            return;
                        }
                    }
                }
            }
        }
        _matchToken2("false", 1);
    }

    protected final void _matchNull() {
        int i2;
        int i3 = this.f5090p;
        if (i3 + 3 < this.f5091q) {
            byte[] bArr = this.W;
            int i4 = i3 + 1;
            if (bArr[i3] == 117) {
                int i5 = i4 + 1;
                if (bArr[i4] == 108) {
                    int i6 = i5 + 1;
                    if (bArr[i5] == 108 && ((i2 = bArr[i6] & 255) < 48 || i2 == 93 || i2 == 125)) {
                        this.f5090p = i6;
                        return;
                    }
                }
            }
        }
        _matchToken2("null", 1);
    }

    protected final void _matchTrue() {
        int i2;
        int i3 = this.f5090p;
        if (i3 + 3 < this.f5091q) {
            byte[] bArr = this.W;
            int i4 = i3 + 1;
            if (bArr[i3] == 114) {
                int i5 = i4 + 1;
                if (bArr[i4] == 117) {
                    int i6 = i5 + 1;
                    if (bArr[i5] == 101 && ((i2 = bArr[i6] & 255) < 48 || i2 == 93 || i2 == 125)) {
                        this.f5090p = i6;
                        return;
                    }
                }
            }
        }
        _matchToken2("true", 1);
    }

    protected void _reportInvalidOther(int i2) {
        k("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i2));
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void finishToken() {
        if (this.R) {
            this.R = false;
            j0();
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != JsonToken.VALUE_STRING && (jsonToken != JsonToken.VALUE_EMBEDDED_OBJECT || this.D == null)) {
            k("Current token (" + this.f5104c + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this.R) {
            try {
                this.D = h0(base64Variant);
                this.R = false;
            } catch (IllegalArgumentException e2) {
                throw b("Failed to decode VALUE_STRING as base64 (" + base64Variant + "): " + e2.getMessage());
            }
        } else if (this.D == null) {
            ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
            e(getText(), _getByteArrayBuilder, base64Variant);
            this.D = _getByteArrayBuilder.toByteArray();
        }
        return this.D;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return this.O;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(K(), this.f5092r + this.f5090p, -1L, this.f5093s, (this.f5090p - this.f5094t) + 1);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getInputSource() {
        return this.V;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getText(Writer writer) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this.R) {
                this.R = false;
                j0();
            }
            return this.z.contentsToWriter(writer);
        } else if (jsonToken == JsonToken.FIELD_NAME) {
            String currentName = this.x.getCurrentName();
            writer.write(currentName);
            return currentName.length();
        } else if (jsonToken != null) {
            if (jsonToken.isNumeric()) {
                return this.z.contentsToWriter(writer);
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
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this.R) {
                this.R = false;
                return _finishAndReturnString();
            }
            return this.z.contentsAsString();
        }
        return k0(jsonToken);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != null) {
            int id = jsonToken.id();
            if (id != 5) {
                if (id != 6) {
                    if (id != 7 && id != 8) {
                        return this.f5104c.asCharArray();
                    }
                } else if (this.R) {
                    this.R = false;
                    j0();
                }
                return this.z.getTextBuffer();
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
            if (id != 5) {
                if (id != 6) {
                    if (id != 7 && id != 8) {
                        return this.f5104c.asCharArray().length;
                    }
                } else if (this.R) {
                    this.R = false;
                    j0();
                }
                return this.z.size();
            }
            return this.x.getCurrentName().length();
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0011, code lost:
        if (r0 != 8) goto L15;
     */
    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int getTextOffset() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != null) {
            int id = jsonToken.id();
            if (id != 6) {
                if (id != 7) {
                }
            } else if (this.R) {
                this.R = false;
                j0();
            }
            return this.z.getTextOffset();
        }
        return 0;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        if (this.f5104c == JsonToken.FIELD_NAME) {
            return new JsonLocation(K(), this.f5092r + (this.S - 1), -1L, this.T, this.U);
        }
        return new JsonLocation(K(), this.u - 1, -1L, this.v, this.w);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
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
        return super.getValueAsInt(0);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i2) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT || jsonToken == JsonToken.VALUE_NUMBER_FLOAT) {
            int i3 = this.E;
            if ((i3 & 1) == 0) {
                if (i3 == 0) {
                    return N();
                }
                if ((i3 & 1) == 0) {
                    Y();
                }
            }
            return this.F;
        }
        return super.getValueAsInt(i2);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return jsonToken == JsonToken.FIELD_NAME ? getCurrentName() : super.getValueAsString(null);
        } else if (this.R) {
            this.R = false;
            return _finishAndReturnString();
        } else {
            return this.z.contentsAsString();
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return jsonToken == JsonToken.FIELD_NAME ? getCurrentName() : super.getValueAsString(str);
        } else if (this.R) {
            this.R = false;
            return _finishAndReturnString();
        } else {
            return this.z.contentsAsString();
        }
    }

    protected final byte[] h0(Base64Variant base64Variant) {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this.f5090p >= this.f5091q) {
                q0();
            }
            byte[] bArr = this.W;
            int i2 = this.f5090p;
            this.f5090p = i2 + 1;
            int i3 = bArr[i2] & 255;
            if (i3 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(i3);
                if (decodeBase64Char < 0) {
                    if (i3 == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = H(base64Variant, i3, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this.f5090p >= this.f5091q) {
                    q0();
                }
                byte[] bArr2 = this.W;
                int i4 = this.f5090p;
                this.f5090p = i4 + 1;
                int i5 = bArr2[i4] & 255;
                int decodeBase64Char2 = base64Variant.decodeBase64Char(i5);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = H(base64Variant, i5, 1);
                }
                int i6 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this.f5090p >= this.f5091q) {
                    q0();
                }
                byte[] bArr3 = this.W;
                int i7 = this.f5090p;
                this.f5090p = i7 + 1;
                int i8 = bArr3[i7] & 255;
                int decodeBase64Char3 = base64Variant.decodeBase64Char(i8);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (i8 == 34) {
                            _getByteArrayBuilder.append(i6 >> 4);
                            if (base64Variant.usesPadding()) {
                                this.f5090p--;
                                L(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = H(base64Variant, i8, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this.f5090p >= this.f5091q) {
                            q0();
                        }
                        byte[] bArr4 = this.W;
                        int i9 = this.f5090p;
                        this.f5090p = i9 + 1;
                        int i10 = bArr4[i9] & 255;
                        if (!base64Variant.usesPaddingChar(i10) && H(base64Variant, i10, 3) != -2) {
                            throw c0(base64Variant, i10, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        _getByteArrayBuilder.append(i6 >> 4);
                    }
                }
                int i11 = (i6 << 6) | decodeBase64Char3;
                if (this.f5090p >= this.f5091q) {
                    q0();
                }
                byte[] bArr5 = this.W;
                int i12 = this.f5090p;
                this.f5090p = i12 + 1;
                int i13 = bArr5[i12] & 255;
                int decodeBase64Char4 = base64Variant.decodeBase64Char(i13);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (i13 == 34) {
                            _getByteArrayBuilder.appendTwoBytes(i11 >> 2);
                            if (base64Variant.usesPadding()) {
                                this.f5090p--;
                                L(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = H(base64Variant, i13, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i11 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i11 << 6) | decodeBase64Char4);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int i0(int i2) {
        char c2;
        int nextByte;
        int i3 = i2 & 255;
        if (i3 <= 127) {
            return i3;
        }
        if ((i3 & BERTags.FLAGS) != 192) {
            if ((i3 & 240) == 224) {
                i3 &= 15;
                c2 = 2;
            } else if ((i3 & 248) == 240) {
                i3 &= 7;
                c2 = 3;
            } else {
                z0(i3 & 255);
            }
            nextByte = nextByte();
            if ((nextByte & 192) != 128) {
                _reportInvalidOther(nextByte & 255);
            }
            int i4 = (i3 << 6) | (nextByte & 63);
            if (c2 <= 1) {
                int nextByte2 = nextByte();
                if ((nextByte2 & 192) != 128) {
                    _reportInvalidOther(nextByte2 & 255);
                }
                int i5 = (i4 << 6) | (nextByte2 & 63);
                if (c2 > 2) {
                    int nextByte3 = nextByte();
                    if ((nextByte3 & 192) != 128) {
                        _reportInvalidOther(nextByte3 & 255);
                    }
                    return (i5 << 6) | (nextByte3 & 63);
                }
                return i5;
            }
            return i4;
        }
        i3 &= 31;
        c2 = 1;
        nextByte = nextByte();
        if ((nextByte & 192) != 128) {
        }
        int i42 = (i3 << 6) | (nextByte & 63);
        if (c2 <= 1) {
        }
    }

    protected void j0() {
        int i2 = this.f5090p;
        if (i2 >= this.f5091q) {
            q0();
            i2 = this.f5090p;
        }
        int i3 = 0;
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int min = Math.min(this.f5091q, emptyAndGetCurrentSegment.length + i2);
        byte[] bArr = this.W;
        while (true) {
            if (i2 >= min) {
                break;
            }
            int i4 = bArr[i2] & 255;
            if (iArr[i4] == 0) {
                i2++;
                emptyAndGetCurrentSegment[i3] = (char) i4;
                i3++;
            } else if (i4 == 34) {
                this.f5090p = i2 + 1;
                this.z.setCurrentLength(i3);
                return;
            }
        }
        this.f5090p = i2;
        _finishString2(emptyAndGetCurrentSegment, i3);
    }

    protected final String k0(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        int id = jsonToken.id();
        return id != 5 ? (id == 6 || id == 7 || id == 8) ? this.z.contentsAsString() : jsonToken.asString() : this.x.getCurrentName();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0045, code lost:
        if (r6 != 39) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0047, code lost:
        r10.z.setCurrentLength(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004e, code lost:
        return com.fasterxml.jackson.core.JsonToken.VALUE_STRING;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004f, code lost:
        r5 = r1[r6];
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0052, code lost:
        if (r5 == 1) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0055, code lost:
        if (r5 == 2) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0058, code lost:
        if (r5 == 3) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005b, code lost:
        if (r5 == 4) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005f, code lost:
        if (r6 >= 32) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0061, code lost:
        S(r6, "string value");
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0066, code lost:
        y0(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006a, code lost:
        r5 = _decodeUtf8_4(r6);
        r6 = r4 + 1;
        r0[r4] = (char) (55296 | (r5 >> 10));
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x007a, code lost:
        if (r6 < r0.length) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007c, code lost:
        r0 = r10.z.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0084, code lost:
        r4 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0085, code lost:
        r6 = 56320 | (r5 & 1023);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x008f, code lost:
        if ((r10.f5091q - r7) < 2) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0091, code lost:
        r6 = _decodeUtf8_3fast(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0096, code lost:
        r6 = _decodeUtf8_3(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009b, code lost:
        r6 = _decodeUtf8_2(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00a0, code lost:
        r6 = I();
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00a5, code lost:
        if (r4 < r0.length) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a7, code lost:
        r0 = r10.z.finishCurrentSegment();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00ae, code lost:
        r0[r4] = (char) r6;
        r4 = r4 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected JsonToken l0() {
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        byte[] bArr = this.W;
        int i2 = 0;
        while (true) {
            if (this.f5090p >= this.f5091q) {
                q0();
            }
            if (i2 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this.z.finishCurrentSegment();
                i2 = 0;
            }
            int i3 = this.f5091q;
            int length = this.f5090p + (emptyAndGetCurrentSegment.length - i2);
            if (length < i3) {
                i3 = length;
            }
            while (true) {
                int i4 = this.f5090p;
                if (i4 < i3) {
                    int i5 = i4 + 1;
                    this.f5090p = i5;
                    int i6 = bArr[i4] & 255;
                    if (i6 == 39 || iArr[i6] != 0) {
                        break;
                    }
                    emptyAndGetCurrentSegment[i2] = (char) i6;
                    i2++;
                }
            }
        }
    }

    protected JsonToken m0(int i2, boolean z) {
        String str;
        while (i2 == 73) {
            if (this.f5090p >= this.f5091q && !p0()) {
                q(JsonToken.VALUE_NUMBER_FLOAT);
            }
            byte[] bArr = this.W;
            int i3 = this.f5090p;
            this.f5090p = i3 + 1;
            i2 = bArr[i3];
            if (i2 != 78) {
                if (i2 != 110) {
                    break;
                }
                str = z ? "-Infinity" : "+Infinity";
            } else {
                str = z ? "-INF" : "+INF";
            }
            r0(str, 3);
            if ((this.f5048a & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                return e0(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            l("Non-standard token '%s': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow", str);
        }
        D(i2, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    protected String n0(int i2) {
        if (i2 != 39 || (this.f5048a & FEAT_MASK_ALLOW_SINGLE_QUOTES) == 0) {
            if ((this.f5048a & FEAT_MASK_ALLOW_UNQUOTED_NAMES) == 0) {
                s((char) i0(i2), "was expecting double-quote to start field name");
            }
            int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
            if (inputCodeUtf8JsNames[i2] != 0) {
                s(i2, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
            }
            int[] iArr = this.Q;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                if (i3 < 4) {
                    i3++;
                    i5 = i2 | (i5 << 8);
                } else {
                    if (i4 >= iArr.length) {
                        iArr = ParserBase.a0(iArr, iArr.length);
                        this.Q = iArr;
                    }
                    iArr[i4] = i5;
                    i5 = i2;
                    i4++;
                    i3 = 1;
                }
                if (this.f5090p >= this.f5091q && !p0()) {
                    p(" in field name", JsonToken.FIELD_NAME);
                }
                byte[] bArr = this.W;
                int i6 = this.f5090p;
                i2 = bArr[i6] & 255;
                if (inputCodeUtf8JsNames[i2] != 0) {
                    break;
                }
                this.f5090p = i6 + 1;
            }
            if (i3 > 0) {
                if (i4 >= iArr.length) {
                    int[] a0 = ParserBase.a0(iArr, iArr.length);
                    this.Q = a0;
                    iArr = a0;
                }
                iArr[i4] = i5;
                i4++;
            }
            String findName = this.P.findName(iArr, i4);
            return findName == null ? addName(iArr, i4, i3) : findName;
        }
        return s0();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Boolean nextBooleanValue() {
        JsonReadContext createChildObjectContext;
        if (this.f5104c != JsonToken.FIELD_NAME) {
            JsonToken nextToken = nextToken();
            if (nextToken == JsonToken.VALUE_TRUE) {
                return Boolean.TRUE;
            }
            if (nextToken == JsonToken.VALUE_FALSE) {
                return Boolean.FALSE;
            }
            return null;
        }
        this.B = false;
        JsonToken jsonToken = this.y;
        this.y = null;
        this.f5104c = jsonToken;
        if (jsonToken == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (jsonToken == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        if (jsonToken != JsonToken.START_ARRAY) {
            if (jsonToken == JsonToken.START_OBJECT) {
                createChildObjectContext = this.x.createChildObjectContext(this.v, this.w);
            }
            return null;
        }
        createChildObjectContext = this.x.createChildArrayContext(this.v, this.w);
        this.x = createChildObjectContext;
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextFieldName() {
        JsonToken v0;
        this.E = 0;
        JsonToken jsonToken = this.f5104c;
        JsonToken jsonToken2 = JsonToken.FIELD_NAME;
        if (jsonToken == jsonToken2) {
            _nextAfterName();
            return null;
        }
        if (this.R) {
            E0();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this.f5104c = null;
            return null;
        }
        this.D = null;
        if (_skipWSOrEnd == 93) {
            _closeArrayScope();
            this.f5104c = JsonToken.END_ARRAY;
            return null;
        } else if (_skipWSOrEnd == 125) {
            _closeObjectScope();
            this.f5104c = JsonToken.END_OBJECT;
            return null;
        } else {
            if (this.x.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    s(_skipWSOrEnd, "was expecting comma to separate " + this.x.typeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
                if ((this.f5048a & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                    _closeScope(_skipWSOrEnd);
                    return null;
                }
            }
            if (!this.x.inObject()) {
                _updateLocation();
                _nextTokenNotInObject(_skipWSOrEnd);
                return null;
            }
            _updateNameLocation();
            String u0 = u0(_skipWSOrEnd);
            this.x.setCurrentName(u0);
            this.f5104c = jsonToken2;
            int _skipColon = _skipColon();
            _updateLocation();
            if (_skipColon == 34) {
                this.R = true;
                this.y = JsonToken.VALUE_STRING;
                return u0;
            }
            if (_skipColon == 45) {
                v0 = v0();
            } else if (_skipColon == 46) {
                v0 = t0();
            } else if (_skipColon == 91) {
                v0 = JsonToken.START_ARRAY;
            } else if (_skipColon == 102) {
                _matchFalse();
                v0 = JsonToken.VALUE_FALSE;
            } else if (_skipColon == 110) {
                _matchNull();
                v0 = JsonToken.VALUE_NULL;
            } else if (_skipColon == 116) {
                _matchTrue();
                v0 = JsonToken.VALUE_TRUE;
            } else if (_skipColon != 123) {
                switch (_skipColon) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        v0 = w0(_skipColon);
                        break;
                    default:
                        v0 = o0(_skipColon);
                        break;
                }
            } else {
                v0 = JsonToken.START_OBJECT;
            }
            this.y = v0;
            return u0;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean nextFieldName(SerializableString serializableString) {
        int i2 = 0;
        this.E = 0;
        if (this.f5104c == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return false;
        }
        if (this.R) {
            E0();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this.f5104c = null;
            return false;
        }
        this.D = null;
        if (_skipWSOrEnd == 93) {
            _closeArrayScope();
            this.f5104c = JsonToken.END_ARRAY;
            return false;
        } else if (_skipWSOrEnd == 125) {
            _closeObjectScope();
            this.f5104c = JsonToken.END_OBJECT;
            return false;
        } else {
            if (this.x.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    s(_skipWSOrEnd, "was expecting comma to separate " + this.x.typeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
                if ((this.f5048a & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                    _closeScope(_skipWSOrEnd);
                    return false;
                }
            }
            if (!this.x.inObject()) {
                _updateLocation();
                _nextTokenNotInObject(_skipWSOrEnd);
                return false;
            }
            _updateNameLocation();
            if (_skipWSOrEnd == 34) {
                byte[] asQuotedUTF8 = serializableString.asQuotedUTF8();
                int length = asQuotedUTF8.length;
                int i3 = this.f5090p;
                if (i3 + length + 4 < this.f5091q) {
                    int i4 = length + i3;
                    if (this.W[i4] == 34) {
                        while (i3 != i4) {
                            if (asQuotedUTF8[i2] == this.W[i3]) {
                                i2++;
                                i3++;
                            }
                        }
                        this.x.setCurrentName(serializableString.getValue());
                        _isNextTokenNameYes(_skipColonFast(i3 + 1));
                        return true;
                    }
                }
            }
            return _isNextTokenNameMaybe(_skipWSOrEnd, serializableString);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int nextIntValue(int i2) {
        JsonReadContext createChildObjectContext;
        if (this.f5104c != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getIntValue() : i2;
        }
        this.B = false;
        JsonToken jsonToken = this.y;
        this.y = null;
        this.f5104c = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getIntValue();
        }
        if (jsonToken != JsonToken.START_ARRAY) {
            if (jsonToken == JsonToken.START_OBJECT) {
                createChildObjectContext = this.x.createChildObjectContext(this.v, this.w);
            }
            return i2;
        }
        createChildObjectContext = this.x.createChildArrayContext(this.v, this.w);
        this.x = createChildObjectContext;
        return i2;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long nextLongValue(long j2) {
        JsonReadContext createChildObjectContext;
        if (this.f5104c != JsonToken.FIELD_NAME) {
            return nextToken() == JsonToken.VALUE_NUMBER_INT ? getLongValue() : j2;
        }
        this.B = false;
        JsonToken jsonToken = this.y;
        this.y = null;
        this.f5104c = jsonToken;
        if (jsonToken == JsonToken.VALUE_NUMBER_INT) {
            return getLongValue();
        }
        if (jsonToken != JsonToken.START_ARRAY) {
            if (jsonToken == JsonToken.START_OBJECT) {
                createChildObjectContext = this.x.createChildObjectContext(this.v, this.w);
            }
            return j2;
        }
        createChildObjectContext = this.x.createChildArrayContext(this.v, this.w);
        this.x = createChildObjectContext;
        return j2;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String nextTextValue() {
        JsonReadContext createChildObjectContext;
        if (this.f5104c != JsonToken.FIELD_NAME) {
            if (nextToken() == JsonToken.VALUE_STRING) {
                return getText();
            }
            return null;
        }
        this.B = false;
        JsonToken jsonToken = this.y;
        this.y = null;
        this.f5104c = jsonToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this.R) {
                this.R = false;
                return _finishAndReturnString();
            }
            return this.z.contentsAsString();
        }
        if (jsonToken != JsonToken.START_ARRAY) {
            if (jsonToken == JsonToken.START_OBJECT) {
                createChildObjectContext = this.x.createChildObjectContext(this.v, this.w);
            }
            return null;
        }
        createChildObjectContext = this.x.createChildArrayContext(this.v, this.w);
        this.x = createChildObjectContext;
        return null;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() {
        JsonToken v0;
        JsonToken jsonToken = this.f5104c;
        JsonToken jsonToken2 = JsonToken.FIELD_NAME;
        if (jsonToken == jsonToken2) {
            return _nextAfterName();
        }
        this.E = 0;
        if (this.R) {
            E0();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this.f5104c = null;
            return null;
        }
        this.D = null;
        if (_skipWSOrEnd == 93) {
            _closeArrayScope();
            JsonToken jsonToken3 = JsonToken.END_ARRAY;
            this.f5104c = jsonToken3;
            return jsonToken3;
        } else if (_skipWSOrEnd == 125) {
            _closeObjectScope();
            JsonToken jsonToken4 = JsonToken.END_OBJECT;
            this.f5104c = jsonToken4;
            return jsonToken4;
        } else {
            if (this.x.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    s(_skipWSOrEnd, "was expecting comma to separate " + this.x.typeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
                if ((this.f5048a & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWSOrEnd == 93 || _skipWSOrEnd == 125)) {
                    return _closeScope(_skipWSOrEnd);
                }
            }
            if (!this.x.inObject()) {
                _updateLocation();
                return _nextTokenNotInObject(_skipWSOrEnd);
            }
            _updateNameLocation();
            this.x.setCurrentName(u0(_skipWSOrEnd));
            this.f5104c = jsonToken2;
            int _skipColon = _skipColon();
            _updateLocation();
            if (_skipColon == 34) {
                this.R = true;
                this.y = JsonToken.VALUE_STRING;
                return this.f5104c;
            }
            if (_skipColon == 45) {
                v0 = v0();
            } else if (_skipColon == 46) {
                v0 = t0();
            } else if (_skipColon == 91) {
                v0 = JsonToken.START_ARRAY;
            } else if (_skipColon == 102) {
                _matchFalse();
                v0 = JsonToken.VALUE_FALSE;
            } else if (_skipColon == 110) {
                _matchNull();
                v0 = JsonToken.VALUE_NULL;
            } else if (_skipColon == 116) {
                _matchTrue();
                v0 = JsonToken.VALUE_TRUE;
            } else if (_skipColon != 123) {
                switch (_skipColon) {
                    case 48:
                    case 49:
                    case 50:
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                        v0 = w0(_skipColon);
                        break;
                    default:
                        v0 = o0(_skipColon);
                        break;
                }
            } else {
                v0 = JsonToken.START_OBJECT;
            }
            this.y = v0;
            return this.f5104c;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001b, code lost:
        if (r4 != 44) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0048, code lost:
        if (r3.x.inArray() == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0051, code lost:
        if (r3.x.inRoot() != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0058, code lost:
        if ((r3.f5048a & com.fasterxml.jackson.core.json.UTF8StreamJsonParser.FEAT_MASK_ALLOW_MISSING) == 0) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005a, code lost:
        r3.f5090p--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0061, code lost:
        return com.fasterxml.jackson.core.JsonToken.VALUE_NULL;
     */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected JsonToken o0(int i2) {
        String str;
        if (i2 != 39) {
            if (i2 == 73) {
                r0("Infinity", 1);
                if ((this.f5048a & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                    return e0("Infinity", Double.POSITIVE_INFINITY);
                }
                str = "Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow";
            } else if (i2 != 78) {
                if (i2 != 93) {
                    if (i2 != 125) {
                        if (i2 == 43) {
                            if (this.f5090p >= this.f5091q && !p0()) {
                                q(JsonToken.VALUE_NUMBER_INT);
                            }
                            byte[] bArr = this.W;
                            int i3 = this.f5090p;
                            this.f5090p = i3 + 1;
                            return m0(bArr[i3] & 255, false);
                        }
                    }
                }
                s(i2, "expected a value");
            } else {
                r0("NaN", 1);
                if ((this.f5048a & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                    return e0("NaN", Double.NaN);
                }
                str = "Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow";
            }
            k(str);
            if (Character.isJavaIdentifierStart(i2)) {
                C0("" + ((char) i2), T());
            }
            s(i2, "expected a valid value " + U());
            return null;
        }
        if ((this.f5048a & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0) {
            return l0();
        }
        if (Character.isJavaIdentifierStart(i2)) {
        }
        s(i2, "expected a valid value " + U());
        return null;
    }

    protected final boolean p0() {
        byte[] bArr;
        int length;
        InputStream inputStream = this.V;
        if (inputStream == null || (length = (bArr = this.W).length) == 0) {
            return false;
        }
        int read = inputStream.read(bArr, 0, length);
        if (read > 0) {
            int i2 = this.f5091q;
            this.f5092r += i2;
            this.f5094t -= i2;
            this.S -= i2;
            this.f5090p = 0;
            this.f5091q = read;
            return true;
        }
        F();
        if (read == 0) {
            throw new IOException("InputStream.read() returned 0 characters when trying to read " + this.W.length + " bytes");
        }
        return false;
    }

    protected void q0() {
        if (p0()) {
            return;
        }
        o();
    }

    protected final void r0(String str, int i2) {
        int i3;
        int length = str.length();
        if (this.f5090p + length >= this.f5091q) {
            _matchToken2(str, i2);
            return;
        }
        do {
            if (this.W[this.f5090p] != str.charAt(i2)) {
                B0(str.substring(0, i2));
            }
            i3 = this.f5090p + 1;
            this.f5090p = i3;
            i2++;
        } while (i2 < length);
        int i4 = this.W[i3] & 255;
        if (i4 < 48 || i4 == 93 || i4 == 125) {
            return;
        }
        _checkMatchEnd(str, i2, i4);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
        if (!this.R || this.f5104c != JsonToken.VALUE_STRING) {
            byte[] binaryValue = getBinaryValue(base64Variant);
            outputStream.write(binaryValue);
            return binaryValue.length;
        }
        byte[] allocBase64Buffer = this.f5088n.allocBase64Buffer();
        try {
            return x0(base64Variant, outputStream, allocBase64Buffer);
        } finally {
            this.f5088n.releaseBase64Buffer(allocBase64Buffer);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int releaseBuffered(OutputStream outputStream) {
        int i2 = this.f5091q;
        int i3 = this.f5090p;
        int i4 = i2 - i3;
        if (i4 < 1) {
            return 0;
        }
        this.f5090p = i3 + i4;
        outputStream.write(this.W, i3, i4);
        return i4;
    }

    protected String s0() {
        if (this.f5090p >= this.f5091q && !p0()) {
            p(": was expecting closing ''' for field name", JsonToken.FIELD_NAME);
        }
        byte[] bArr = this.W;
        int i2 = this.f5090p;
        this.f5090p = i2 + 1;
        int i3 = bArr[i2] & 255;
        if (i3 == 39) {
            return "";
        }
        int[] iArr = this.Q;
        int[] iArr2 = Y;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 != 39) {
            if (iArr2[i3] != 0 && i3 != 34) {
                if (i3 != 92) {
                    S(i3, AppMeasurementSdk.ConditionalUserProperty.NAME);
                } else {
                    i3 = I();
                }
                if (i3 > 127) {
                    if (i4 >= 4) {
                        if (i5 >= iArr.length) {
                            iArr = ParserBase.a0(iArr, iArr.length);
                            this.Q = iArr;
                        }
                        iArr[i5] = i6;
                        i6 = 0;
                        i5++;
                        i4 = 0;
                    }
                    int i7 = i6 << 8;
                    if (i3 < 2048) {
                        i6 = i7 | (i3 >> 6) | 192;
                        i4++;
                    } else {
                        int i8 = i7 | (i3 >> 12) | BERTags.FLAGS;
                        int i9 = i4 + 1;
                        if (i9 >= 4) {
                            if (i5 >= iArr.length) {
                                iArr = ParserBase.a0(iArr, iArr.length);
                                this.Q = iArr;
                            }
                            iArr[i5] = i8;
                            i8 = 0;
                            i5++;
                            i9 = 0;
                        }
                        i6 = (i8 << 8) | ((i3 >> 6) & 63) | 128;
                        i4 = i9 + 1;
                    }
                    i3 = (i3 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i6 = i3 | (i6 << 8);
            } else {
                if (i5 >= iArr.length) {
                    iArr = ParserBase.a0(iArr, iArr.length);
                    this.Q = iArr;
                }
                iArr[i5] = i6;
                i6 = i3;
                i5++;
                i4 = 1;
            }
            if (this.f5090p >= this.f5091q && !p0()) {
                p(" in field name", JsonToken.FIELD_NAME);
            }
            byte[] bArr2 = this.W;
            int i10 = this.f5090p;
            this.f5090p = i10 + 1;
            i3 = bArr2[i10] & 255;
        }
        if (i4 > 0) {
            if (i5 >= iArr.length) {
                int[] a0 = ParserBase.a0(iArr, iArr.length);
                this.Q = a0;
                iArr = a0;
            }
            iArr[i5] = _padLastQuad(i6, i4);
            i5++;
        }
        String findName = this.P.findName(iArr, i5);
        return findName == null ? addName(iArr, i5, i4) : findName;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this.O = objectCodec;
    }

    protected final JsonToken t0() {
        return !isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature()) ? o0(46) : _parseFloat(this.z.emptyAndGetCurrentSegment(), 0, 46, false, 0);
    }

    protected final String u0(int i2) {
        if (i2 != 34) {
            return n0(i2);
        }
        int i3 = this.f5090p;
        if (i3 + 13 > this.f5091q) {
            return J0();
        }
        byte[] bArr = this.W;
        int[] iArr = Y;
        int i4 = i3 + 1;
        this.f5090p = i4;
        int i5 = bArr[i3] & 255;
        if (iArr[i5] != 0) {
            return i5 == 34 ? "" : parseName(0, i5, 0);
        }
        int i6 = i4 + 1;
        this.f5090p = i6;
        int i7 = bArr[i4] & 255;
        if (iArr[i7] != 0) {
            return i7 == 34 ? findName(i5, 1) : parseName(i5, i7, 1);
        }
        int i8 = (i5 << 8) | i7;
        int i9 = i6 + 1;
        this.f5090p = i9;
        int i10 = bArr[i6] & 255;
        if (iArr[i10] != 0) {
            return i10 == 34 ? findName(i8, 2) : parseName(i8, i10, 2);
        }
        int i11 = (i8 << 8) | i10;
        int i12 = i9 + 1;
        this.f5090p = i12;
        int i13 = bArr[i9] & 255;
        if (iArr[i13] != 0) {
            return i13 == 34 ? findName(i11, 3) : parseName(i11, i13, 3);
        }
        int i14 = (i11 << 8) | i13;
        this.f5090p = i12 + 1;
        int i15 = bArr[i12] & 255;
        if (iArr[i15] != 0) {
            return i15 == 34 ? findName(i14, 4) : parseName(i14, i15, 4);
        }
        this._quad1 = i14;
        return H0(i15);
    }

    protected JsonToken v0() {
        int i2;
        int i3;
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment[0] = Soundex.SILENT_MARKER;
        if (this.f5090p >= this.f5091q) {
            q0();
        }
        byte[] bArr = this.W;
        int i4 = this.f5090p;
        this.f5090p = i4 + 1;
        int i5 = bArr[i4] & 255;
        if (i5 <= 48) {
            if (i5 != 48) {
                return m0(i5, true);
            }
            i5 = _verifyNoLeadingZeroes();
        } else if (i5 > 57) {
            return m0(i5, true);
        }
        int i6 = 2;
        emptyAndGetCurrentSegment[1] = (char) i5;
        int min = Math.min(this.f5091q, (this.f5090p + emptyAndGetCurrentSegment.length) - 2);
        int i7 = 1;
        while (true) {
            int i8 = this.f5090p;
            if (i8 >= min) {
                return _parseNumber2(emptyAndGetCurrentSegment, i6, true, i7);
            }
            byte[] bArr2 = this.W;
            i2 = i8 + 1;
            this.f5090p = i2;
            i3 = bArr2[i8] & 255;
            if (i3 < 48 || i3 > 57) {
                break;
            }
            i7++;
            emptyAndGetCurrentSegment[i6] = (char) i3;
            i6++;
        }
        if (i3 == 46 || i3 == 101 || i3 == 69) {
            return _parseFloat(emptyAndGetCurrentSegment, i6, i3, true, i7);
        }
        this.f5090p = i2 - 1;
        this.z.setCurrentLength(i6);
        if (this.x.inRoot()) {
            _verifyRootSpace(i3);
        }
        return g0(true, i7);
    }

    protected JsonToken w0(int i2) {
        int i3;
        int i4;
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        if (i2 == 48) {
            i2 = _verifyNoLeadingZeroes();
        }
        emptyAndGetCurrentSegment[0] = (char) i2;
        int min = Math.min(this.f5091q, (this.f5090p + emptyAndGetCurrentSegment.length) - 1);
        int i5 = 1;
        int i6 = 1;
        while (true) {
            int i7 = this.f5090p;
            if (i7 >= min) {
                return _parseNumber2(emptyAndGetCurrentSegment, i5, false, i6);
            }
            byte[] bArr = this.W;
            i3 = i7 + 1;
            this.f5090p = i3;
            i4 = bArr[i7] & 255;
            if (i4 < 48 || i4 > 57) {
                break;
            }
            i6++;
            emptyAndGetCurrentSegment[i5] = (char) i4;
            i5++;
        }
        if (i4 == 46 || i4 == 101 || i4 == 69) {
            return _parseFloat(emptyAndGetCurrentSegment, i5, i4, false, i6);
        }
        this.f5090p = i3 - 1;
        this.z.setCurrentLength(i5);
        if (this.x.inRoot()) {
            _verifyRootSpace(i4);
        }
        return g0(false, i6);
    }

    /* JADX WARN: Code restructure failed: missing block: B:57:0x012e, code lost:
        r16.R = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0130, code lost:
        if (r7 <= 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0132, code lost:
        r8 = r8 + r7;
        r18.write(r19, 0, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0136, code lost:
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:?, code lost:
        return r8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int x0(Base64Variant base64Variant, OutputStream outputStream, byte[] bArr) {
        int i2;
        int i3 = 3;
        int length = bArr.length - 3;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (this.f5090p >= this.f5091q) {
                q0();
            }
            byte[] bArr2 = this.W;
            int i6 = this.f5090p;
            this.f5090p = i6 + 1;
            int i7 = bArr2[i6] & 255;
            if (i7 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(i7);
                if (decodeBase64Char < 0) {
                    if (i7 == 34) {
                        break;
                    }
                    decodeBase64Char = H(base64Variant, i7, 0);
                    if (decodeBase64Char < 0) {
                    }
                }
                if (i4 > length) {
                    i5 += i4;
                    outputStream.write(bArr, 0, i4);
                    i4 = 0;
                }
                if (this.f5090p >= this.f5091q) {
                    q0();
                }
                byte[] bArr3 = this.W;
                int i8 = this.f5090p;
                this.f5090p = i8 + 1;
                int i9 = bArr3[i8] & 255;
                int decodeBase64Char2 = base64Variant.decodeBase64Char(i9);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = H(base64Variant, i9, 1);
                }
                int i10 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this.f5090p >= this.f5091q) {
                    q0();
                }
                byte[] bArr4 = this.W;
                int i11 = this.f5090p;
                this.f5090p = i11 + 1;
                int i12 = bArr4[i11] & 255;
                int decodeBase64Char3 = base64Variant.decodeBase64Char(i12);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (i12 == 34) {
                            int i13 = i4 + 1;
                            bArr[i4] = (byte) (i10 >> 4);
                            if (base64Variant.usesPadding()) {
                                this.f5090p--;
                                L(base64Variant);
                            }
                            i4 = i13;
                        } else {
                            decodeBase64Char3 = H(base64Variant, i12, 2);
                        }
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this.f5090p >= this.f5091q) {
                            q0();
                        }
                        byte[] bArr5 = this.W;
                        int i14 = this.f5090p;
                        this.f5090p = i14 + 1;
                        int i15 = bArr5[i14] & 255;
                        if (!base64Variant.usesPaddingChar(i15) && H(base64Variant, i15, i3) != -2) {
                            throw c0(base64Variant, i15, i3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        bArr[i4] = (byte) (i10 >> 4);
                        i4++;
                    }
                }
                int i16 = (i10 << 6) | decodeBase64Char3;
                if (this.f5090p >= this.f5091q) {
                    q0();
                }
                byte[] bArr6 = this.W;
                int i17 = this.f5090p;
                this.f5090p = i17 + 1;
                int i18 = bArr6[i17] & 255;
                int decodeBase64Char4 = base64Variant.decodeBase64Char(i18);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 == -2) {
                        i2 = 3;
                    } else if (i18 == 34) {
                        int i19 = i16 >> 2;
                        int i20 = i4 + 1;
                        bArr[i4] = (byte) (i19 >> 8);
                        i4 = i20 + 1;
                        bArr[i20] = (byte) i19;
                        if (base64Variant.usesPadding()) {
                            this.f5090p--;
                            L(base64Variant);
                        }
                    } else {
                        i2 = 3;
                        decodeBase64Char4 = H(base64Variant, i18, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        int i21 = i16 >> 2;
                        int i22 = i4 + 1;
                        bArr[i4] = (byte) (i21 >> 8);
                        i4 = i22 + 1;
                        bArr[i22] = (byte) i21;
                        i3 = i2;
                    }
                } else {
                    i2 = 3;
                }
                int i23 = (i16 << 6) | decodeBase64Char4;
                int i24 = i4 + 1;
                bArr[i4] = (byte) (i23 >> 16);
                int i25 = i24 + 1;
                bArr[i24] = (byte) (i23 >> 8);
                bArr[i25] = (byte) i23;
                i4 = i25 + 1;
                i3 = i2;
            }
            i2 = i3;
            i3 = i2;
        }
    }

    protected void y0(int i2) {
        if (i2 < 32) {
            u(i2);
        }
        z0(i2);
    }

    protected void z0(int i2) {
        k("Invalid UTF-8 start byte 0x" + Integer.toHexString(i2));
    }
}
