package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.DataInput;
import java.io.EOFException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.codec.language.Soundex;
import org.apache.http.message.TokenParser;
import org.bouncycastle.asn1.BERTags;
/* loaded from: classes.dex */
public class UTF8DataInputJsonParser extends ParserBase {
    protected ObjectCodec O;
    protected final ByteQuadsCanonicalizer P;
    protected int[] Q;
    protected boolean R;
    protected DataInput S;
    protected int T;
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
    protected static final int[] U = CharTypes.getInputCodeLatin1();

    public UTF8DataInputJsonParser(IOContext iOContext, int i2, DataInput dataInput, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, int i3) {
        super(iOContext, i2);
        this.Q = new int[16];
        this.T = -1;
        this.O = objectCodec;
        this.P = byteQuadsCanonicalizer;
        this.S = dataInput;
        this.T = i3;
    }

    private final void _checkMatchEnd(String str, int i2, int i3) {
        char i0 = (char) i0(i3);
        if (Character.isJavaIdentifierPart(i0)) {
            y0(i0, str.substring(0, i2));
        }
    }

    private void _closeScope(int i2) {
        if (i2 == 93) {
            if (!this.x.inArray()) {
                Q(i2, AbstractJsonLexerKt.END_OBJ);
            }
            this.x = this.x.clearAndGetParent();
            this.f5104c = JsonToken.END_ARRAY;
        }
        if (i2 == 125) {
            if (!this.x.inObject()) {
                Q(i2, AbstractJsonLexerKt.END_LIST);
            }
            this.x = this.x.clearAndGetParent();
            this.f5104c = JsonToken.END_OBJECT;
        }
    }

    private final int _decodeUtf8_2(int i2) {
        int readUnsignedByte = this.S.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        return ((i2 & 31) << 6) | (readUnsignedByte & 63);
    }

    private final int _decodeUtf8_3(int i2) {
        int i3 = i2 & 15;
        int readUnsignedByte = this.S.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int i4 = (i3 << 6) | (readUnsignedByte & 63);
        int readUnsignedByte2 = this.S.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        return (i4 << 6) | (readUnsignedByte2 & 63);
    }

    private final int _decodeUtf8_4(int i2) {
        int readUnsignedByte = this.S.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int i3 = ((i2 & 7) << 6) | (readUnsignedByte & 63);
        int readUnsignedByte2 = this.S.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        int i4 = (i3 << 6) | (readUnsignedByte2 & 63);
        int readUnsignedByte3 = this.S.readUnsignedByte();
        if ((readUnsignedByte3 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte3 & 255);
        }
        return ((i4 << 6) | (readUnsignedByte3 & 63)) - 65536;
    }

    private String _finishAndReturnString() {
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int length = emptyAndGetCurrentSegment.length;
        int i2 = 0;
        while (true) {
            int readUnsignedByte = this.S.readUnsignedByte();
            if (iArr[readUnsignedByte] != 0) {
                if (readUnsignedByte == 34) {
                    return this.z.setCurrentAndReturn(i2);
                }
                _finishString2(emptyAndGetCurrentSegment, i2, readUnsignedByte);
                return this.z.contentsAsString();
            }
            int i3 = i2 + 1;
            emptyAndGetCurrentSegment[i2] = (char) readUnsignedByte;
            if (i3 >= length) {
                _finishString2(emptyAndGetCurrentSegment, i3, this.S.readUnsignedByte());
                return this.z.contentsAsString();
            }
            i2 = i3;
        }
    }

    private final void _finishString2(char[] cArr, int i2, int i3) {
        int[] iArr = _icUTF8;
        int length = cArr.length;
        while (true) {
            int i4 = 0;
            if (iArr[i3] == 0) {
                if (i2 >= length) {
                    cArr = this.z.finishCurrentSegment();
                    length = cArr.length;
                    i2 = 0;
                }
                cArr[i2] = (char) i3;
                i3 = this.S.readUnsignedByte();
                i2++;
            } else if (i3 == 34) {
                this.z.setCurrentLength(i2);
                return;
            } else {
                int i5 = iArr[i3];
                if (i5 == 1) {
                    i3 = I();
                } else if (i5 == 2) {
                    i3 = _decodeUtf8_2(i3);
                } else if (i5 == 3) {
                    i3 = _decodeUtf8_3(i3);
                } else if (i5 == 4) {
                    int _decodeUtf8_4 = _decodeUtf8_4(i3);
                    if (i2 >= cArr.length) {
                        cArr = this.z.finishCurrentSegment();
                        length = cArr.length;
                        i2 = 0;
                    }
                    cArr[i2] = (char) (55296 | (_decodeUtf8_4 >> 10));
                    i3 = (_decodeUtf8_4 & 1023) | 56320;
                    i2++;
                } else if (i3 < 32) {
                    S(i3, "string value");
                } else {
                    w0(i3);
                }
                if (i2 >= cArr.length) {
                    cArr = this.z.finishCurrentSegment();
                    length = cArr.length;
                } else {
                    i4 = i2;
                }
                i2 = i4 + 1;
                cArr[i4] = (char) i3;
                i3 = this.S.readUnsignedByte();
            }
        }
    }

    private static int[] _growArrayBy(int[] iArr, int i2) {
        return iArr == null ? new int[i2] : Arrays.copyOf(iArr, iArr.length + i2);
    }

    private final int _handleLeadingZeroes() {
        int readUnsignedByte = this.S.readUnsignedByte();
        if (readUnsignedByte >= 48 && readUnsignedByte <= 57) {
            if ((this.f5048a & FEAT_MASK_LEADING_ZEROS) == 0) {
                w("Leading zeroes not allowed");
            }
            while (readUnsignedByte == 48) {
                readUnsignedByte = this.S.readUnsignedByte();
            }
        }
        return readUnsignedByte;
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
        JsonToken t0;
        if (i2 == 34) {
            this.R = true;
            t0 = JsonToken.VALUE_STRING;
        } else if (i2 == 45) {
            t0 = t0();
        } else if (i2 == 46) {
            t0 = r0();
        } else if (i2 == 91) {
            this.x = this.x.createChildArrayContext(this.v, this.w);
            t0 = JsonToken.START_ARRAY;
        } else if (i2 == 102) {
            p0("false", 1);
            t0 = JsonToken.VALUE_FALSE;
        } else if (i2 == 110) {
            p0("null", 1);
            t0 = JsonToken.VALUE_NULL;
        } else if (i2 == 116) {
            p0("true", 1);
            t0 = JsonToken.VALUE_TRUE;
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
                    t0 = u0(i2);
                    break;
                default:
                    t0 = o0(i2);
                    break;
            }
        } else {
            this.x = this.x.createChildObjectContext(this.v, this.w);
            t0 = JsonToken.START_OBJECT;
        }
        this.f5104c = t0;
        return t0;
    }

    private final JsonToken _parseFloat(char[] cArr, int i2, int i3, boolean z, int i4) {
        int i5;
        int i6;
        int readUnsignedByte;
        int i7 = 0;
        if (i3 == 46) {
            cArr[i2] = (char) i3;
            int i8 = 0;
            i2++;
            while (true) {
                readUnsignedByte = this.S.readUnsignedByte();
                if (readUnsignedByte < 48 || readUnsignedByte > 57) {
                    break;
                }
                i8++;
                if (i2 >= cArr.length) {
                    cArr = this.z.finishCurrentSegment();
                    i2 = 0;
                }
                cArr[i2] = (char) readUnsignedByte;
                i2++;
            }
            if (i8 == 0) {
                D(readUnsignedByte, "Decimal point not followed by a digit");
            }
            i5 = i8;
            i3 = readUnsignedByte;
        } else {
            i5 = 0;
        }
        if (i3 == 101 || i3 == 69) {
            if (i2 >= cArr.length) {
                cArr = this.z.finishCurrentSegment();
                i2 = 0;
            }
            int i9 = i2 + 1;
            cArr[i2] = (char) i3;
            int readUnsignedByte2 = this.S.readUnsignedByte();
            if (readUnsignedByte2 == 45 || readUnsignedByte2 == 43) {
                if (i9 >= cArr.length) {
                    cArr = this.z.finishCurrentSegment();
                    i9 = 0;
                }
                int i10 = i9 + 1;
                cArr[i9] = (char) readUnsignedByte2;
                i6 = 0;
                i3 = this.S.readUnsignedByte();
                i2 = i10;
            } else {
                i3 = readUnsignedByte2;
                i2 = i9;
                i6 = 0;
            }
            while (i3 <= 57 && i3 >= 48) {
                i6++;
                if (i2 >= cArr.length) {
                    cArr = this.z.finishCurrentSegment();
                    i2 = 0;
                }
                cArr[i2] = (char) i3;
                i3 = this.S.readUnsignedByte();
                i2++;
            }
            if (i6 == 0) {
                D(i3, "Exponent indicator not followed by a digit");
            }
            i7 = i6;
        }
        this.T = i3;
        if (this.x.inRoot()) {
            _verifyRootSpace();
        }
        this.z.setCurrentLength(i2);
        return f0(z, i4, i5, i7);
    }

    private final String _parseLongName(int i2, int i3, int i4) {
        int[] iArr = this.Q;
        iArr[0] = this._quad1;
        iArr[1] = i3;
        iArr[2] = i4;
        int[] iArr2 = U;
        int i5 = i2;
        int i6 = 3;
        while (true) {
            int readUnsignedByte = this.S.readUnsignedByte();
            if (iArr2[readUnsignedByte] != 0) {
                return readUnsignedByte == 34 ? findName(this.Q, i6, i5, 1) : B0(this.Q, i6, i5, readUnsignedByte, 1);
            }
            int i7 = (i5 << 8) | readUnsignedByte;
            int readUnsignedByte2 = this.S.readUnsignedByte();
            if (iArr2[readUnsignedByte2] != 0) {
                return readUnsignedByte2 == 34 ? findName(this.Q, i6, i7, 2) : B0(this.Q, i6, i7, readUnsignedByte2, 2);
            }
            int i8 = (i7 << 8) | readUnsignedByte2;
            int readUnsignedByte3 = this.S.readUnsignedByte();
            if (iArr2[readUnsignedByte3] != 0) {
                return readUnsignedByte3 == 34 ? findName(this.Q, i6, i8, 3) : B0(this.Q, i6, i8, readUnsignedByte3, 3);
            }
            int i9 = (i8 << 8) | readUnsignedByte3;
            int readUnsignedByte4 = this.S.readUnsignedByte();
            if (iArr2[readUnsignedByte4] != 0) {
                return readUnsignedByte4 == 34 ? findName(this.Q, i6, i9, 4) : B0(this.Q, i6, i9, readUnsignedByte4, 4);
            }
            int[] iArr3 = this.Q;
            if (i6 >= iArr3.length) {
                this.Q = _growArrayBy(iArr3, i6);
            }
            this.Q[i6] = i9;
            i6++;
            i5 = readUnsignedByte4;
        }
    }

    private final String _parseMediumName(int i2) {
        int[] iArr = U;
        int readUnsignedByte = this.S.readUnsignedByte();
        if (iArr[readUnsignedByte] != 0) {
            return readUnsignedByte == 34 ? findName(this._quad1, i2, 1) : parseName(this._quad1, i2, readUnsignedByte, 1);
        }
        int i3 = (i2 << 8) | readUnsignedByte;
        int readUnsignedByte2 = this.S.readUnsignedByte();
        if (iArr[readUnsignedByte2] != 0) {
            return readUnsignedByte2 == 34 ? findName(this._quad1, i3, 2) : parseName(this._quad1, i3, readUnsignedByte2, 2);
        }
        int i4 = (i3 << 8) | readUnsignedByte2;
        int readUnsignedByte3 = this.S.readUnsignedByte();
        if (iArr[readUnsignedByte3] != 0) {
            return readUnsignedByte3 == 34 ? findName(this._quad1, i4, 3) : parseName(this._quad1, i4, readUnsignedByte3, 3);
        }
        int i5 = (i4 << 8) | readUnsignedByte3;
        int readUnsignedByte4 = this.S.readUnsignedByte();
        return iArr[readUnsignedByte4] != 0 ? readUnsignedByte4 == 34 ? findName(this._quad1, i5, 4) : parseName(this._quad1, i5, readUnsignedByte4, 4) : _parseMediumName2(readUnsignedByte4, i5);
    }

    private final String _parseMediumName2(int i2, int i3) {
        int[] iArr = U;
        int readUnsignedByte = this.S.readUnsignedByte();
        if (iArr[readUnsignedByte] != 0) {
            return readUnsignedByte == 34 ? findName(this._quad1, i3, i2, 1) : parseName(this._quad1, i3, i2, readUnsignedByte, 1);
        }
        int i4 = (i2 << 8) | readUnsignedByte;
        int readUnsignedByte2 = this.S.readUnsignedByte();
        if (iArr[readUnsignedByte2] != 0) {
            return readUnsignedByte2 == 34 ? findName(this._quad1, i3, i4, 2) : parseName(this._quad1, i3, i4, readUnsignedByte2, 2);
        }
        int i5 = (i4 << 8) | readUnsignedByte2;
        int readUnsignedByte3 = this.S.readUnsignedByte();
        if (iArr[readUnsignedByte3] != 0) {
            return readUnsignedByte3 == 34 ? findName(this._quad1, i3, i5, 3) : parseName(this._quad1, i3, i5, readUnsignedByte3, 3);
        }
        int i6 = (i5 << 8) | readUnsignedByte3;
        int readUnsignedByte4 = this.S.readUnsignedByte();
        return iArr[readUnsignedByte4] != 0 ? readUnsignedByte4 == 34 ? findName(this._quad1, i3, i6, 4) : parseName(this._quad1, i3, i6, readUnsignedByte4, 4) : _parseLongName(readUnsignedByte4, i3, i6);
    }

    private void _reportInvalidOther(int i2) {
        k("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i2));
    }

    private final void _skipCComment() {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            int readUnsignedByte = this.S.readUnsignedByte();
            while (true) {
                int i2 = inputCodeComment[readUnsignedByte];
                if (i2 != 0) {
                    if (i2 == 2) {
                        _skipUtf8_2();
                        break;
                    } else if (i2 == 3) {
                        _skipUtf8_3();
                        break;
                    } else if (i2 == 4) {
                        _skipUtf8_4();
                        break;
                    } else if (i2 == 10 || i2 == 13) {
                        break;
                    } else if (i2 != 42) {
                        w0(readUnsignedByte);
                        break;
                    } else {
                        readUnsignedByte = this.S.readUnsignedByte();
                        if (readUnsignedByte == 47) {
                            return;
                        }
                    }
                } else {
                    break;
                }
            }
            this.f5093s++;
        }
    }

    private final int _skipColon() {
        int i2 = this.T;
        if (i2 < 0) {
            i2 = this.S.readUnsignedByte();
        } else {
            this.T = -1;
        }
        if (i2 == 58) {
            int readUnsignedByte = this.S.readUnsignedByte();
            return readUnsignedByte > 32 ? (readUnsignedByte == 47 || readUnsignedByte == 35) ? _skipColon2(readUnsignedByte, true) : readUnsignedByte : ((readUnsignedByte == 32 || readUnsignedByte == 9) && (readUnsignedByte = this.S.readUnsignedByte()) > 32) ? (readUnsignedByte == 47 || readUnsignedByte == 35) ? _skipColon2(readUnsignedByte, true) : readUnsignedByte : _skipColon2(readUnsignedByte, true);
        }
        if (i2 == 32 || i2 == 9) {
            i2 = this.S.readUnsignedByte();
        }
        if (i2 == 58) {
            int readUnsignedByte2 = this.S.readUnsignedByte();
            return readUnsignedByte2 > 32 ? (readUnsignedByte2 == 47 || readUnsignedByte2 == 35) ? _skipColon2(readUnsignedByte2, true) : readUnsignedByte2 : ((readUnsignedByte2 == 32 || readUnsignedByte2 == 9) && (readUnsignedByte2 = this.S.readUnsignedByte()) > 32) ? (readUnsignedByte2 == 47 || readUnsignedByte2 == 35) ? _skipColon2(readUnsignedByte2, true) : readUnsignedByte2 : _skipColon2(readUnsignedByte2, true);
        }
        return _skipColon2(i2, false);
    }

    private final int _skipColon2(int i2, boolean z) {
        while (true) {
            if (i2 > 32) {
                if (i2 == 47) {
                    _skipComment();
                } else if (i2 != 35 || !_skipYAMLComment()) {
                    if (z) {
                        return i2;
                    }
                    if (i2 != 58) {
                        s(i2, "was expecting a colon to separate field name and value");
                    }
                    z = true;
                }
            } else if (i2 == 13 || i2 == 10) {
                this.f5093s++;
            }
            i2 = this.S.readUnsignedByte();
        }
    }

    private final void _skipComment() {
        if ((this.f5048a & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0) {
            s(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        int readUnsignedByte = this.S.readUnsignedByte();
        if (readUnsignedByte == 47) {
            _skipLine();
        } else if (readUnsignedByte == 42) {
            _skipCComment();
        } else {
            s(readUnsignedByte, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipLine() {
        int[] inputCodeComment = CharTypes.getInputCodeComment();
        while (true) {
            int readUnsignedByte = this.S.readUnsignedByte();
            int i2 = inputCodeComment[readUnsignedByte];
            if (i2 != 0) {
                if (i2 == 2) {
                    _skipUtf8_2();
                } else if (i2 == 3) {
                    _skipUtf8_3();
                } else if (i2 == 4) {
                    _skipUtf8_4();
                } else if (i2 == 10 || i2 == 13) {
                    break;
                } else if (i2 != 42 && i2 < 0) {
                    w0(readUnsignedByte);
                }
            }
        }
        this.f5093s++;
    }

    private final void _skipUtf8_2() {
        int readUnsignedByte = this.S.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
    }

    private final void _skipUtf8_3() {
        int readUnsignedByte = this.S.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int readUnsignedByte2 = this.S.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
    }

    private final void _skipUtf8_4() {
        int readUnsignedByte = this.S.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
            _reportInvalidOther(readUnsignedByte & 255);
        }
        int readUnsignedByte2 = this.S.readUnsignedByte();
        if ((readUnsignedByte2 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte2 & 255);
        }
        int readUnsignedByte3 = this.S.readUnsignedByte();
        if ((readUnsignedByte3 & 192) != 128) {
            _reportInvalidOther(readUnsignedByte3 & 255);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0012  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0027 -> B:4:0x0004). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0029 -> B:4:0x0004). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int _skipWS() {
        int i2 = this.T;
        if (i2 >= 0) {
            this.T = -1;
            if (i2 > 32) {
                return (i2 == 47 || i2 == 35) ? _skipWSComment(i2) : i2;
            } else if (i2 == 13 || i2 == 10) {
                this.f5093s++;
            }
        }
        i2 = this.S.readUnsignedByte();
        if (i2 > 32) {
        }
    }

    private final int _skipWSComment(int i2) {
        while (true) {
            if (i2 > 32) {
                if (i2 == 47) {
                    _skipComment();
                } else if (i2 != 35 || !_skipYAMLComment()) {
                    break;
                }
            } else if (i2 == 13 || i2 == 10) {
                this.f5093s++;
            }
            i2 = this.S.readUnsignedByte();
        }
        return i2;
    }

    private final int _skipWSOrEnd() {
        int i2 = this.T;
        if (i2 < 0) {
            try {
                i2 = this.S.readUnsignedByte();
            } catch (EOFException unused) {
                return J();
            }
        } else {
            this.T = -1;
        }
        while (i2 <= 32) {
            if (i2 == 13 || i2 == 10) {
                this.f5093s++;
            }
            try {
                i2 = this.S.readUnsignedByte();
            } catch (EOFException unused2) {
                return J();
            }
        }
        return (i2 == 47 || i2 == 35) ? _skipWSComment(i2) : i2;
    }

    private final boolean _skipYAMLComment() {
        if ((this.f5048a & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0) {
            return false;
        }
        _skipLine();
        return true;
    }

    private final void _verifyRootSpace() {
        int i2 = this.T;
        if (i2 > 32) {
            r(i2);
            return;
        }
        this.T = -1;
        if (i2 == 13 || i2 == 10) {
            this.f5093s++;
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
                    x0(i12);
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
        int pad = pad(i2, i3);
        String findName = this.P.findName(pad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this.Q;
        iArr[0] = pad;
        return addName(iArr, 1, i3);
    }

    private final String findName(int i2, int i3, int i4) {
        int pad = pad(i3, i4);
        String findName = this.P.findName(i2, pad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this.Q;
        iArr[0] = i2;
        iArr[1] = pad;
        return addName(iArr, 2, i4);
    }

    private final String findName(int i2, int i3, int i4, int i5) {
        int pad = pad(i4, i5);
        String findName = this.P.findName(i2, i3, pad);
        if (findName != null) {
            return findName;
        }
        int[] iArr = this.Q;
        iArr[0] = i2;
        iArr[1] = i3;
        iArr[2] = pad(pad, i5);
        return addName(iArr, 3, i5);
    }

    private final String findName(int[] iArr, int i2, int i3, int i4) {
        if (i2 >= iArr.length) {
            iArr = _growArrayBy(iArr, iArr.length);
            this.Q = iArr;
        }
        int i5 = i2 + 1;
        iArr[i2] = pad(i3, i4);
        String findName = this.P.findName(iArr, i5);
        return findName == null ? addName(iArr, i5, i4) : findName;
    }

    private static final int pad(int i2, int i3) {
        return i3 == 4 ? i2 : i2 | ((-1) << (i3 << 3));
    }

    private final String parseName(int i2, int i3, int i4) {
        return B0(this.Q, 0, i2, i3, i4);
    }

    private final String parseName(int i2, int i3, int i4, int i5) {
        int[] iArr = this.Q;
        iArr[0] = i2;
        return B0(iArr, 1, i3, i4, i5);
    }

    private final String parseName(int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = this.Q;
        iArr[0] = i2;
        iArr[1] = i3;
        return B0(iArr, 2, i4, i5, i6);
    }

    protected void A0() {
        this.R = false;
        int[] iArr = _icUTF8;
        while (true) {
            int readUnsignedByte = this.S.readUnsignedByte();
            if (iArr[readUnsignedByte] != 0) {
                if (readUnsignedByte == 34) {
                    return;
                }
                int i2 = iArr[readUnsignedByte];
                if (i2 == 1) {
                    I();
                } else if (i2 == 2) {
                    _skipUtf8_2();
                } else if (i2 == 3) {
                    _skipUtf8_3();
                } else if (i2 == 4) {
                    _skipUtf8_4();
                } else if (readUnsignedByte < 32) {
                    S(readUnsignedByte, "string value");
                } else {
                    w0(readUnsignedByte);
                }
            }
        }
    }

    protected final String B0(int[] iArr, int i2, int i3, int i4, int i5) {
        int[] iArr2 = U;
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
                            iArr = _growArrayBy(iArr, iArr.length);
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
                                iArr = _growArrayBy(iArr, iArr.length);
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
                    iArr = _growArrayBy(iArr, iArr.length);
                    this.Q = iArr;
                }
                iArr[i2] = i3;
                i3 = i4;
                i2++;
                i5 = 1;
            }
            i4 = this.S.readUnsignedByte();
        }
        if (i5 > 0) {
            if (i2 >= iArr.length) {
                iArr = _growArrayBy(iArr, iArr.length);
                this.Q = iArr;
            }
            iArr[i2] = pad(i3, i5);
            i2++;
        }
        String findName = this.P.findName(iArr, i2);
        return findName == null ? addName(iArr, i2, i5) : findName;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void F() {
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected char I() {
        int readUnsignedByte = this.S.readUnsignedByte();
        if (readUnsignedByte == 34 || readUnsignedByte == 47 || readUnsignedByte == 92) {
            return (char) readUnsignedByte;
        }
        if (readUnsignedByte != 98) {
            if (readUnsignedByte != 102) {
                if (readUnsignedByte != 110) {
                    if (readUnsignedByte != 114) {
                        if (readUnsignedByte != 116) {
                            if (readUnsignedByte != 117) {
                                return M((char) i0(readUnsignedByte));
                            }
                            int i2 = 0;
                            for (int i3 = 0; i3 < 4; i3++) {
                                int readUnsignedByte2 = this.S.readUnsignedByte();
                                int charToHex = CharTypes.charToHex(readUnsignedByte2);
                                if (charToHex < 0) {
                                    s(readUnsignedByte2, "expected a hex-digit for character escape sequence");
                                }
                                i2 = (i2 << 4) | charToHex;
                            }
                            return (char) i2;
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

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void P() {
        super.P();
        this.P.release();
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
        return new JsonLocation(K(), -1L, -1L, this.f5093s, -1);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getInputSource() {
        return this.S;
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
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this.R) {
                this.R = false;
                j0();
            }
            return this.z.size();
        } else if (jsonToken == JsonToken.FIELD_NAME) {
            return this.x.getCurrentName().length();
        } else {
            if (jsonToken != null) {
                return jsonToken.isNumeric() ? this.z.size() : this.f5104c.asCharArray().length;
            }
            return 0;
        }
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
        return new JsonLocation(K(), -1L, -1L, this.v, -1);
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
        int readUnsignedByte;
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            int readUnsignedByte2 = this.S.readUnsignedByte();
            if (readUnsignedByte2 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(readUnsignedByte2);
                if (decodeBase64Char < 0) {
                    if (readUnsignedByte2 == 34) {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = H(base64Variant, readUnsignedByte2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                int readUnsignedByte3 = this.S.readUnsignedByte();
                int decodeBase64Char2 = base64Variant.decodeBase64Char(readUnsignedByte3);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = H(base64Variant, readUnsignedByte3, 1);
                }
                int i2 = (decodeBase64Char << 6) | decodeBase64Char2;
                int readUnsignedByte4 = this.S.readUnsignedByte();
                int decodeBase64Char3 = base64Variant.decodeBase64Char(readUnsignedByte4);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (readUnsignedByte4 == 34) {
                            _getByteArrayBuilder.append(i2 >> 4);
                            if (base64Variant.usesPadding()) {
                                L(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = H(base64Variant, readUnsignedByte4, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        readUnsignedByte = this.S.readUnsignedByte();
                        if (base64Variant.usesPaddingChar(readUnsignedByte) || (readUnsignedByte == 92 && H(base64Variant, readUnsignedByte, 3) == -2)) {
                            _getByteArrayBuilder.append(i2 >> 4);
                        }
                    }
                }
                int i3 = (i2 << 6) | decodeBase64Char3;
                int readUnsignedByte5 = this.S.readUnsignedByte();
                int decodeBase64Char4 = base64Variant.decodeBase64Char(readUnsignedByte5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (readUnsignedByte5 == 34) {
                            _getByteArrayBuilder.appendTwoBytes(i3 >> 2);
                            if (base64Variant.usesPadding()) {
                                L(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = H(base64Variant, readUnsignedByte5, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i3 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i3 << 6) | decodeBase64Char4);
            }
        }
        throw c0(base64Variant, readUnsignedByte, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int i0(int i2) {
        char c2;
        int readUnsignedByte;
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
                x0(i3 & 255);
            }
            readUnsignedByte = this.S.readUnsignedByte();
            if ((readUnsignedByte & 192) != 128) {
                _reportInvalidOther(readUnsignedByte & 255);
            }
            int i4 = (i3 << 6) | (readUnsignedByte & 63);
            if (c2 <= 1) {
                int readUnsignedByte2 = this.S.readUnsignedByte();
                if ((readUnsignedByte2 & 192) != 128) {
                    _reportInvalidOther(readUnsignedByte2 & 255);
                }
                int i5 = (i4 << 6) | (readUnsignedByte2 & 63);
                if (c2 > 2) {
                    int readUnsignedByte3 = this.S.readUnsignedByte();
                    if ((readUnsignedByte3 & 192) != 128) {
                        _reportInvalidOther(readUnsignedByte3 & 255);
                    }
                    return (i5 << 6) | (readUnsignedByte3 & 63);
                }
                return i5;
            }
            return i4;
        }
        i3 &= 31;
        c2 = 1;
        readUnsignedByte = this.S.readUnsignedByte();
        if ((readUnsignedByte & 192) != 128) {
        }
        int i42 = (i3 << 6) | (readUnsignedByte & 63);
        if (c2 <= 1) {
        }
    }

    protected void j0() {
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int length = emptyAndGetCurrentSegment.length;
        int i2 = 0;
        while (true) {
            int readUnsignedByte = this.S.readUnsignedByte();
            if (iArr[readUnsignedByte] != 0) {
                if (readUnsignedByte == 34) {
                    this.z.setCurrentLength(i2);
                    return;
                } else {
                    _finishString2(emptyAndGetCurrentSegment, i2, readUnsignedByte);
                    return;
                }
            }
            int i3 = i2 + 1;
            emptyAndGetCurrentSegment[i2] = (char) readUnsignedByte;
            if (i3 >= length) {
                _finishString2(emptyAndGetCurrentSegment, i3, this.S.readUnsignedByte());
                return;
            }
            i2 = i3;
        }
    }

    protected final String k0(JsonToken jsonToken) {
        if (jsonToken == null) {
            return null;
        }
        int id = jsonToken.id();
        return id != 5 ? (id == 6 || id == 7 || id == 8) ? this.z.contentsAsString() : jsonToken.asString() : this.x.getCurrentName();
    }

    protected JsonToken l0() {
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int i2 = 0;
        while (true) {
            int length = emptyAndGetCurrentSegment.length;
            if (i2 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this.z.finishCurrentSegment();
                length = emptyAndGetCurrentSegment.length;
                i2 = 0;
            }
            while (true) {
                int readUnsignedByte = this.S.readUnsignedByte();
                if (readUnsignedByte != 39) {
                    if (iArr[readUnsignedByte] == 0) {
                        int i3 = i2 + 1;
                        emptyAndGetCurrentSegment[i2] = (char) readUnsignedByte;
                        i2 = i3;
                        if (i3 >= length) {
                            break;
                        }
                    } else {
                        int i4 = iArr[readUnsignedByte];
                        if (i4 == 1) {
                            readUnsignedByte = I();
                        } else if (i4 == 2) {
                            readUnsignedByte = _decodeUtf8_2(readUnsignedByte);
                        } else if (i4 == 3) {
                            readUnsignedByte = _decodeUtf8_3(readUnsignedByte);
                        } else if (i4 != 4) {
                            if (readUnsignedByte < 32) {
                                S(readUnsignedByte, "string value");
                            }
                            w0(readUnsignedByte);
                        } else {
                            int _decodeUtf8_4 = _decodeUtf8_4(readUnsignedByte);
                            int i5 = i2 + 1;
                            emptyAndGetCurrentSegment[i2] = (char) (55296 | (_decodeUtf8_4 >> 10));
                            if (i5 >= emptyAndGetCurrentSegment.length) {
                                emptyAndGetCurrentSegment = this.z.finishCurrentSegment();
                                i2 = 0;
                            } else {
                                i2 = i5;
                            }
                            readUnsignedByte = 56320 | (_decodeUtf8_4 & 1023);
                        }
                        if (i2 >= emptyAndGetCurrentSegment.length) {
                            emptyAndGetCurrentSegment = this.z.finishCurrentSegment();
                            i2 = 0;
                        }
                        emptyAndGetCurrentSegment[i2] = (char) readUnsignedByte;
                        i2++;
                    }
                } else {
                    this.z.setCurrentLength(i2);
                    return JsonToken.VALUE_STRING;
                }
            }
        }
    }

    protected JsonToken m0(int i2, boolean z) {
        String str;
        while (i2 == 73) {
            i2 = this.S.readUnsignedByte();
            if (i2 != 78) {
                if (i2 != 110) {
                    break;
                }
                str = z ? "-Infinity" : "+Infinity";
            } else {
                str = z ? "-INF" : "+INF";
            }
            p0(str, 3);
            if ((this.f5048a & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                return e0(str, z ? Double.NEGATIVE_INFINITY : Double.POSITIVE_INFINITY);
            }
            k("Non-standard token '" + str + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
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
            do {
                if (i3 < 4) {
                    i3++;
                    i5 = i2 | (i5 << 8);
                } else {
                    if (i4 >= iArr.length) {
                        iArr = _growArrayBy(iArr, iArr.length);
                        this.Q = iArr;
                    }
                    iArr[i4] = i5;
                    i5 = i2;
                    i4++;
                    i3 = 1;
                }
                i2 = this.S.readUnsignedByte();
            } while (inputCodeUtf8JsNames[i2] == 0);
            this.T = i2;
            if (i3 > 0) {
                if (i4 >= iArr.length) {
                    int[] _growArrayBy = _growArrayBy(iArr, iArr.length);
                    this.Q = _growArrayBy;
                    iArr = _growArrayBy;
                }
                iArr[i4] = i5;
                i4++;
            }
            String findName = this.P.findName(iArr, i4);
            return findName == null ? addName(iArr, i4, i3) : findName;
        }
        return q0();
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
        JsonToken t0;
        this.E = 0;
        JsonToken jsonToken = this.f5104c;
        JsonToken jsonToken2 = JsonToken.FIELD_NAME;
        if (jsonToken == jsonToken2) {
            _nextAfterName();
            return null;
        }
        if (this.R) {
            A0();
        }
        int _skipWS = _skipWS();
        this.D = null;
        this.v = this.f5093s;
        if (_skipWS == 93 || _skipWS == 125) {
            _closeScope(_skipWS);
            return null;
        }
        if (this.x.expectComma()) {
            if (_skipWS != 44) {
                s(_skipWS, "was expecting comma to separate " + this.x.typeDesc() + " entries");
            }
            _skipWS = _skipWS();
            if ((this.f5048a & FEAT_MASK_TRAILING_COMMA) != 0 && (_skipWS == 93 || _skipWS == 125)) {
                _closeScope(_skipWS);
                return null;
            }
        }
        if (!this.x.inObject()) {
            _nextTokenNotInObject(_skipWS);
            return null;
        }
        String s0 = s0(_skipWS);
        this.x.setCurrentName(s0);
        this.f5104c = jsonToken2;
        int _skipColon = _skipColon();
        if (_skipColon == 34) {
            this.R = true;
            this.y = JsonToken.VALUE_STRING;
            return s0;
        }
        if (_skipColon != 45) {
            if (_skipColon == 46) {
                r0();
            } else if (_skipColon == 91) {
                t0 = JsonToken.START_ARRAY;
            } else if (_skipColon == 102) {
                p0("false", 1);
                t0 = JsonToken.VALUE_FALSE;
            } else if (_skipColon == 110) {
                p0("null", 1);
                t0 = JsonToken.VALUE_NULL;
            } else if (_skipColon == 116) {
                p0("true", 1);
                t0 = JsonToken.VALUE_TRUE;
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
                        break;
                    default:
                        t0 = o0(_skipColon);
                        break;
                }
            } else {
                t0 = JsonToken.START_OBJECT;
            }
            t0 = u0(_skipColon);
        } else {
            t0 = t0();
        }
        this.y = t0;
        return s0;
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

    /* JADX WARN: Code restructure failed: missing block: B:29:0x006e, code lost:
        if (r0 == 125) goto L63;
     */
    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JsonToken nextToken() {
        JsonToken t0;
        if (this.f5089o) {
            return null;
        }
        JsonToken jsonToken = this.f5104c;
        JsonToken jsonToken2 = JsonToken.FIELD_NAME;
        if (jsonToken == jsonToken2) {
            return _nextAfterName();
        }
        this.E = 0;
        if (this.R) {
            A0();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this.f5104c = null;
            return null;
        }
        this.D = null;
        this.v = this.f5093s;
        if (_skipWSOrEnd != 93 && _skipWSOrEnd != 125) {
            if (this.x.expectComma()) {
                if (_skipWSOrEnd != 44) {
                    s(_skipWSOrEnd, "was expecting comma to separate " + this.x.typeDesc() + " entries");
                }
                _skipWSOrEnd = _skipWS();
                if ((this.f5048a & FEAT_MASK_TRAILING_COMMA) != 0) {
                    if (_skipWSOrEnd != 93) {
                    }
                }
            }
            if (this.x.inObject()) {
                this.x.setCurrentName(s0(_skipWSOrEnd));
                this.f5104c = jsonToken2;
                int _skipColon = _skipColon();
                if (_skipColon == 34) {
                    this.R = true;
                    t0 = JsonToken.VALUE_STRING;
                } else if (_skipColon == 45) {
                    t0 = t0();
                } else if (_skipColon == 46) {
                    t0 = r0();
                } else if (_skipColon == 91) {
                    t0 = JsonToken.START_ARRAY;
                } else if (_skipColon == 102) {
                    p0("false", 1);
                    t0 = JsonToken.VALUE_FALSE;
                } else if (_skipColon == 110) {
                    p0("null", 1);
                    t0 = JsonToken.VALUE_NULL;
                } else if (_skipColon == 116) {
                    p0("true", 1);
                    t0 = JsonToken.VALUE_TRUE;
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
                            t0 = u0(_skipColon);
                            break;
                        default:
                            t0 = o0(_skipColon);
                            break;
                    }
                } else {
                    t0 = JsonToken.START_OBJECT;
                }
                this.y = t0;
                return this.f5104c;
            }
            return _nextTokenNotInObject(_skipWSOrEnd);
        }
        _closeScope(_skipWSOrEnd);
        return this.f5104c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001b, code lost:
        if (r4 != 44) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0030, code lost:
        if (r3.x.inArray() == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0039, code lost:
        if (r3.x.inRoot() != false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0040, code lost:
        if ((r3.f5048a & com.fasterxml.jackson.core.json.UTF8DataInputJsonParser.FEAT_MASK_ALLOW_MISSING) == 0) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0042, code lost:
        r3.T = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0046, code lost:
        return com.fasterxml.jackson.core.JsonToken.VALUE_NULL;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected JsonToken o0(int i2) {
        String str;
        if (i2 != 39) {
            if (i2 == 73) {
                p0("Infinity", 1);
                if ((this.f5048a & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                    return e0("Infinity", Double.POSITIVE_INFINITY);
                }
                str = "Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow";
            } else if (i2 != 78) {
                if (i2 != 93) {
                    if (i2 != 125) {
                        if (i2 == 43) {
                            return m0(this.S.readUnsignedByte(), false);
                        }
                    }
                }
                s(i2, "expected a value");
            } else {
                p0("NaN", 1);
                if ((this.f5048a & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                    return e0("NaN", Double.NaN);
                }
                str = "Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow";
            }
            k(str);
            if (Character.isJavaIdentifierStart(i2)) {
                z0(i2, "" + ((char) i2), T());
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

    protected final void p0(String str, int i2) {
        int length = str.length();
        do {
            int readUnsignedByte = this.S.readUnsignedByte();
            if (readUnsignedByte != str.charAt(i2)) {
                y0(readUnsignedByte, str.substring(0, i2));
            }
            i2++;
        } while (i2 < length);
        int readUnsignedByte2 = this.S.readUnsignedByte();
        if (readUnsignedByte2 >= 48 && readUnsignedByte2 != 93 && readUnsignedByte2 != 125) {
            _checkMatchEnd(str, i2, readUnsignedByte2);
        }
        this.T = readUnsignedByte2;
    }

    protected String q0() {
        int readUnsignedByte = this.S.readUnsignedByte();
        if (readUnsignedByte == 39) {
            return "";
        }
        int[] iArr = this.Q;
        int[] iArr2 = U;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (readUnsignedByte != 39) {
            if (readUnsignedByte != 34 && iArr2[readUnsignedByte] != 0) {
                if (readUnsignedByte != 92) {
                    S(readUnsignedByte, AppMeasurementSdk.ConditionalUserProperty.NAME);
                } else {
                    readUnsignedByte = I();
                }
                if (readUnsignedByte > 127) {
                    if (i2 >= 4) {
                        if (i3 >= iArr.length) {
                            iArr = _growArrayBy(iArr, iArr.length);
                            this.Q = iArr;
                        }
                        iArr[i3] = i4;
                        i4 = 0;
                        i3++;
                        i2 = 0;
                    }
                    int i5 = i4 << 8;
                    if (readUnsignedByte < 2048) {
                        i4 = i5 | (readUnsignedByte >> 6) | 192;
                        i2++;
                    } else {
                        int i6 = i5 | (readUnsignedByte >> 12) | BERTags.FLAGS;
                        int i7 = i2 + 1;
                        if (i7 >= 4) {
                            if (i3 >= iArr.length) {
                                iArr = _growArrayBy(iArr, iArr.length);
                                this.Q = iArr;
                            }
                            iArr[i3] = i6;
                            i6 = 0;
                            i3++;
                            i7 = 0;
                        }
                        i4 = (i6 << 8) | ((readUnsignedByte >> 6) & 63) | 128;
                        i2 = i7 + 1;
                    }
                    readUnsignedByte = (readUnsignedByte & 63) | 128;
                }
            }
            if (i2 < 4) {
                i2++;
                i4 = readUnsignedByte | (i4 << 8);
            } else {
                if (i3 >= iArr.length) {
                    iArr = _growArrayBy(iArr, iArr.length);
                    this.Q = iArr;
                }
                iArr[i3] = i4;
                i4 = readUnsignedByte;
                i3++;
                i2 = 1;
            }
            readUnsignedByte = this.S.readUnsignedByte();
        }
        if (i2 > 0) {
            if (i3 >= iArr.length) {
                int[] _growArrayBy = _growArrayBy(iArr, iArr.length);
                this.Q = _growArrayBy;
                iArr = _growArrayBy;
            }
            iArr[i3] = pad(i4, i2);
            i3++;
        }
        String findName = this.P.findName(iArr, i3);
        return findName == null ? addName(iArr, i3, i2) : findName;
    }

    protected final JsonToken r0() {
        return !isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature()) ? o0(46) : _parseFloat(this.z.emptyAndGetCurrentSegment(), 0, 46, false, 0);
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
            return v0(base64Variant, outputStream, allocBase64Buffer);
        } finally {
            this.f5088n.releaseBase64Buffer(allocBase64Buffer);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int releaseBuffered(OutputStream outputStream) {
        return 0;
    }

    protected final String s0(int i2) {
        if (i2 != 34) {
            return n0(i2);
        }
        int[] iArr = U;
        int readUnsignedByte = this.S.readUnsignedByte();
        if (iArr[readUnsignedByte] != 0) {
            return readUnsignedByte == 34 ? "" : parseName(0, readUnsignedByte, 0);
        }
        int readUnsignedByte2 = this.S.readUnsignedByte();
        if (iArr[readUnsignedByte2] != 0) {
            return readUnsignedByte2 == 34 ? findName(readUnsignedByte, 1) : parseName(readUnsignedByte, readUnsignedByte2, 1);
        }
        int i3 = (readUnsignedByte << 8) | readUnsignedByte2;
        int readUnsignedByte3 = this.S.readUnsignedByte();
        if (iArr[readUnsignedByte3] != 0) {
            return readUnsignedByte3 == 34 ? findName(i3, 2) : parseName(i3, readUnsignedByte3, 2);
        }
        int i4 = (i3 << 8) | readUnsignedByte3;
        int readUnsignedByte4 = this.S.readUnsignedByte();
        if (iArr[readUnsignedByte4] != 0) {
            return readUnsignedByte4 == 34 ? findName(i4, 3) : parseName(i4, readUnsignedByte4, 3);
        }
        int i5 = (i4 << 8) | readUnsignedByte4;
        int readUnsignedByte5 = this.S.readUnsignedByte();
        if (iArr[readUnsignedByte5] != 0) {
            return readUnsignedByte5 == 34 ? findName(i5, 4) : parseName(i5, readUnsignedByte5, 4);
        }
        this._quad1 = i5;
        return _parseMediumName(readUnsignedByte5);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this.O = objectCodec;
    }

    protected JsonToken t0() {
        int readUnsignedByte;
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment[0] = Soundex.SILENT_MARKER;
        int readUnsignedByte2 = this.S.readUnsignedByte();
        emptyAndGetCurrentSegment[1] = (char) readUnsignedByte2;
        if (readUnsignedByte2 <= 48) {
            if (readUnsignedByte2 != 48) {
                return m0(readUnsignedByte2, true);
            }
            readUnsignedByte = _handleLeadingZeroes();
        } else if (readUnsignedByte2 > 57) {
            return m0(readUnsignedByte2, true);
        } else {
            readUnsignedByte = this.S.readUnsignedByte();
        }
        int i2 = 2;
        int i3 = 1;
        while (readUnsignedByte <= 57 && readUnsignedByte >= 48) {
            i3++;
            emptyAndGetCurrentSegment[i2] = (char) readUnsignedByte;
            readUnsignedByte = this.S.readUnsignedByte();
            i2++;
        }
        if (readUnsignedByte == 46 || readUnsignedByte == 101 || readUnsignedByte == 69) {
            return _parseFloat(emptyAndGetCurrentSegment, i2, readUnsignedByte, true, i3);
        }
        this.z.setCurrentLength(i2);
        this.T = readUnsignedByte;
        if (this.x.inRoot()) {
            _verifyRootSpace();
        }
        return g0(true, i3);
    }

    protected JsonToken u0(int i2) {
        int readUnsignedByte;
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        int i3 = 1;
        if (i2 == 48) {
            readUnsignedByte = _handleLeadingZeroes();
            if (readUnsignedByte > 57 || readUnsignedByte < 48) {
                emptyAndGetCurrentSegment[0] = '0';
            } else {
                i3 = 0;
            }
        } else {
            emptyAndGetCurrentSegment[0] = (char) i2;
            readUnsignedByte = this.S.readUnsignedByte();
        }
        int i4 = readUnsignedByte;
        char[] cArr = emptyAndGetCurrentSegment;
        int i5 = i3;
        int i6 = i5;
        while (i4 <= 57 && i4 >= 48) {
            i6++;
            if (i5 >= cArr.length) {
                cArr = this.z.finishCurrentSegment();
                i5 = 0;
            }
            cArr[i5] = (char) i4;
            i4 = this.S.readUnsignedByte();
            i5++;
        }
        if (i4 == 46 || i4 == 101 || i4 == 69) {
            return _parseFloat(cArr, i5, i4, false, i6);
        }
        this.z.setCurrentLength(i5);
        if (this.x.inRoot()) {
            _verifyRootSpace();
        } else {
            this.T = i4;
        }
        return g0(false, i6);
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d2, code lost:
        r11.R = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00d4, code lost:
        if (r3 <= 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00d6, code lost:
        r4 = r4 + r3;
        r13.write(r14, 0, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00da, code lost:
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:?, code lost:
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected int v0(Base64Variant base64Variant, OutputStream outputStream, byte[] bArr) {
        int i2;
        int readUnsignedByte;
        int length = bArr.length - 3;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            int readUnsignedByte2 = this.S.readUnsignedByte();
            if (readUnsignedByte2 > 32) {
                int decodeBase64Char = base64Variant.decodeBase64Char(readUnsignedByte2);
                if (decodeBase64Char < 0) {
                    if (readUnsignedByte2 == 34) {
                        break;
                    }
                    decodeBase64Char = H(base64Variant, readUnsignedByte2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (i3 > length) {
                    i4 += i3;
                    outputStream.write(bArr, 0, i3);
                    i3 = 0;
                }
                int readUnsignedByte3 = this.S.readUnsignedByte();
                int decodeBase64Char2 = base64Variant.decodeBase64Char(readUnsignedByte3);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = H(base64Variant, readUnsignedByte3, 1);
                }
                int i5 = (decodeBase64Char << 6) | decodeBase64Char2;
                int readUnsignedByte4 = this.S.readUnsignedByte();
                int decodeBase64Char3 = base64Variant.decodeBase64Char(readUnsignedByte4);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (readUnsignedByte4 == 34) {
                            int i6 = i3 + 1;
                            bArr[i3] = (byte) (i5 >> 4);
                            if (base64Variant.usesPadding()) {
                                L(base64Variant);
                            }
                            i3 = i6;
                        } else {
                            decodeBase64Char3 = H(base64Variant, readUnsignedByte4, 2);
                        }
                    }
                    if (decodeBase64Char3 == -2) {
                        readUnsignedByte = this.S.readUnsignedByte();
                        if (base64Variant.usesPaddingChar(readUnsignedByte) || (readUnsignedByte == 92 && H(base64Variant, readUnsignedByte, 3) == -2)) {
                            i2 = i3 + 1;
                            bArr[i3] = (byte) (i5 >> 4);
                            i3 = i2;
                        }
                    }
                }
                int i7 = (i5 << 6) | decodeBase64Char3;
                int readUnsignedByte5 = this.S.readUnsignedByte();
                int decodeBase64Char4 = base64Variant.decodeBase64Char(readUnsignedByte5);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (readUnsignedByte5 == 34) {
                            int i8 = i7 >> 2;
                            int i9 = i3 + 1;
                            bArr[i3] = (byte) (i8 >> 8);
                            i3 = i9 + 1;
                            bArr[i9] = (byte) i8;
                            if (base64Variant.usesPadding()) {
                                L(base64Variant);
                            }
                        } else {
                            decodeBase64Char4 = H(base64Variant, readUnsignedByte5, 3);
                        }
                    }
                    if (decodeBase64Char4 == -2) {
                        int i10 = i7 >> 2;
                        int i11 = i3 + 1;
                        bArr[i3] = (byte) (i10 >> 8);
                        i3 = i11 + 1;
                        bArr[i11] = (byte) i10;
                    }
                }
                int i12 = (i7 << 6) | decodeBase64Char4;
                int i13 = i3 + 1;
                bArr[i3] = (byte) (i12 >> 16);
                int i14 = i13 + 1;
                bArr[i13] = (byte) (i12 >> 8);
                i2 = i14 + 1;
                bArr[i14] = (byte) i12;
                i3 = i2;
            }
        }
        throw c0(base64Variant, readUnsignedByte, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
    }

    protected void w0(int i2) {
        if (i2 < 32) {
            u(i2);
        }
        x0(i2);
    }

    protected void x0(int i2) {
        k("Invalid UTF-8 start byte 0x" + Integer.toHexString(i2));
    }

    protected void y0(int i2, String str) {
        z0(i2, str, T());
    }

    protected void z0(int i2, String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            char i0 = (char) i0(i2);
            if (!Character.isJavaIdentifierPart(i0)) {
                k("Unrecognized token '" + sb.toString() + "': was expecting " + str2);
                return;
            }
            sb.append(i0);
            i2 = this.S.readUnsignedByte();
        }
    }
}
