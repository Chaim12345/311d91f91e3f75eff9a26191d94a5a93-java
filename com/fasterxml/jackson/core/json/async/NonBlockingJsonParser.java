package com.fasterxml.jackson.core.json.async;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.async.ByteArrayFeeder;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.TextBuffer;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.OutputStream;
import org.apache.commons.codec.language.Soundex;
import org.apache.http.message.TokenParser;
import org.bouncycastle.asn1.BERTags;
/* loaded from: classes.dex */
public class NonBlockingJsonParser extends NonBlockingJsonParserBase implements ByteArrayFeeder {
    protected byte[] g0;
    protected int h0;
    private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
    private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
    private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
    private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
    protected static final int[] i0 = CharTypes.getInputCodeLatin1();

    public NonBlockingJsonParser(IOContext iOContext, int i2, ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        super(iOContext, i2, byteQuadsCanonicalizer);
        this.g0 = ParserMinimalBase.f5095e;
    }

    private final int _decodeCharEscape() {
        return this.f5091q - this.f5090p < 5 ? _decodeSplitEscaped(0, -1) : _decodeFastCharEscape();
    }

    private final int _decodeFastCharEscape() {
        byte[] bArr = this.g0;
        int i2 = this.f5090p;
        int i3 = i2 + 1;
        this.f5090p = i3;
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
                                return M((char) b2);
                            }
                            this.f5090p = i3 + 1;
                            byte b3 = bArr[i3];
                            int charToHex = CharTypes.charToHex(b3);
                            if (charToHex >= 0) {
                                byte[] bArr2 = this.g0;
                                int i4 = this.f5090p;
                                this.f5090p = i4 + 1;
                                b3 = bArr2[i4];
                                int charToHex2 = CharTypes.charToHex(b3);
                                if (charToHex2 >= 0) {
                                    int i5 = (charToHex << 4) | charToHex2;
                                    byte[] bArr3 = this.g0;
                                    int i6 = this.f5090p;
                                    this.f5090p = i6 + 1;
                                    byte b4 = bArr3[i6];
                                    int charToHex3 = CharTypes.charToHex(b4);
                                    if (charToHex3 >= 0) {
                                        int i7 = (i5 << 4) | charToHex3;
                                        byte[] bArr4 = this.g0;
                                        int i8 = this.f5090p;
                                        this.f5090p = i8 + 1;
                                        b4 = bArr4[i8];
                                        int charToHex4 = CharTypes.charToHex(b4);
                                        if (charToHex4 >= 0) {
                                            return (i7 << 4) | charToHex4;
                                        }
                                    }
                                    b3 = b4;
                                }
                            }
                            s(b3 & 255, "expected a hex-digit for character escape sequence");
                            return -1;
                        }
                        return 9;
                    }
                    return 13;
                }
                return 10;
            }
            return 12;
        }
        return 8;
    }

    private int _decodeSplitEscaped(int i2, int i3) {
        int i4 = this.f5090p;
        int i5 = this.f5091q;
        if (i4 >= i5) {
            this.U = i2;
            this.V = i3;
            return -1;
        }
        byte[] bArr = this.g0;
        int i6 = i4 + 1;
        this.f5090p = i6;
        byte b2 = bArr[i4];
        if (i3 == -1) {
            if (b2 == 34 || b2 == 47 || b2 == 92) {
                return b2;
            }
            if (b2 == 98) {
                return 8;
            }
            if (b2 == 102) {
                return 12;
            }
            if (b2 == 110) {
                return 10;
            }
            if (b2 == 114) {
                return 13;
            }
            if (b2 == 116) {
                return 9;
            }
            if (b2 != 117) {
                return M((char) b2);
            }
            if (i6 >= i5) {
                this.V = 0;
                this.U = 0;
                return -1;
            }
            this.f5090p = i6 + 1;
            b2 = bArr[i6];
            i3 = 0;
        }
        while (true) {
            int i7 = b2 & 255;
            int charToHex = CharTypes.charToHex(i7);
            if (charToHex < 0) {
                s(i7 & 255, "expected a hex-digit for character escape sequence");
            }
            i2 = (i2 << 4) | charToHex;
            i3++;
            if (i3 == 4) {
                return i2;
            }
            int i8 = this.f5090p;
            if (i8 >= this.f5091q) {
                this.V = i3;
                this.U = i2;
                return -1;
            }
            byte[] bArr2 = this.g0;
            this.f5090p = i8 + 1;
            b2 = bArr2[i8];
        }
    }

    private final boolean _decodeSplitMultiByte(int i2, int i3, boolean z) {
        if (i3 == 1) {
            i2 = _decodeSplitEscaped(0, -1);
            if (i2 < 0) {
                this.Y = 41;
                return false;
            }
        } else if (i3 != 2) {
            if (i3 == 3) {
                int i4 = i2 & 15;
                if (z) {
                    byte[] bArr = this.g0;
                    int i5 = this.f5090p;
                    this.f5090p = i5 + 1;
                    return _decodeSplitUTF8_3(i4, 1, bArr[i5]);
                }
                this.Y = 43;
                this.S = i4;
                this.T = 1;
                return false;
            } else if (i3 == 4) {
                int i6 = i2 & 7;
                if (z) {
                    byte[] bArr2 = this.g0;
                    int i7 = this.f5090p;
                    this.f5090p = i7 + 1;
                    return _decodeSplitUTF8_4(i6, 1, bArr2[i7]);
                }
                this.S = i6;
                this.T = 1;
                this.Y = 44;
                return false;
            } else if (i2 < 32) {
                S(i2, "string value");
            } else {
                r0(i2);
            }
        } else if (!z) {
            this.Y = 42;
            this.S = i2;
            return false;
        } else {
            byte[] bArr3 = this.g0;
            int i8 = this.f5090p;
            this.f5090p = i8 + 1;
            i2 = _decodeUTF8_2(i2, bArr3[i8]);
        }
        this.z.append((char) i2);
        return true;
    }

    private final boolean _decodeSplitUTF8_3(int i2, int i3, int i4) {
        if (i3 == 1) {
            if ((i4 & 192) != 128) {
                t0(i4 & 255, this.f5090p);
            }
            i2 = (i2 << 6) | (i4 & 63);
            int i5 = this.f5090p;
            if (i5 >= this.f5091q) {
                this.Y = 43;
                this.S = i2;
                this.T = 2;
                return false;
            }
            byte[] bArr = this.g0;
            this.f5090p = i5 + 1;
            i4 = bArr[i5];
        }
        if ((i4 & 192) != 128) {
            t0(i4 & 255, this.f5090p);
        }
        this.z.append((char) ((i2 << 6) | (i4 & 63)));
        return true;
    }

    private final boolean _decodeSplitUTF8_4(int i2, int i3, int i4) {
        if (i3 == 1) {
            if ((i4 & 192) != 128) {
                t0(i4 & 255, this.f5090p);
            }
            i2 = (i2 << 6) | (i4 & 63);
            int i5 = this.f5090p;
            if (i5 >= this.f5091q) {
                this.Y = 44;
                this.S = i2;
                this.T = 2;
                return false;
            }
            byte[] bArr = this.g0;
            this.f5090p = i5 + 1;
            i4 = bArr[i5];
            i3 = 2;
        }
        if (i3 == 2) {
            if ((i4 & 192) != 128) {
                t0(i4 & 255, this.f5090p);
            }
            i2 = (i2 << 6) | (i4 & 63);
            int i6 = this.f5090p;
            if (i6 >= this.f5091q) {
                this.Y = 44;
                this.S = i2;
                this.T = 3;
                return false;
            }
            byte[] bArr2 = this.g0;
            this.f5090p = i6 + 1;
            i4 = bArr2[i6];
        }
        if ((i4 & 192) != 128) {
            t0(i4 & 255, this.f5090p);
        }
        int i7 = ((i2 << 6) | (i4 & 63)) - 65536;
        this.z.append((char) (55296 | (i7 >> 10)));
        this.z.append((char) ((i7 & 1023) | 56320));
        return true;
    }

    private final int _decodeUTF8_2(int i2, int i3) {
        if ((i3 & 192) != 128) {
            t0(i3 & 255, this.f5090p);
        }
        return ((i2 & 31) << 6) | (i3 & 63);
    }

    private final int _decodeUTF8_3(int i2, int i3, int i4) {
        int i5 = i2 & 15;
        if ((i3 & 192) != 128) {
            t0(i3 & 255, this.f5090p);
        }
        int i6 = (i5 << 6) | (i3 & 63);
        if ((i4 & 192) != 128) {
            t0(i4 & 255, this.f5090p);
        }
        return (i6 << 6) | (i4 & 63);
    }

    private final int _decodeUTF8_4(int i2, int i3, int i4, int i5) {
        if ((i3 & 192) != 128) {
            t0(i3 & 255, this.f5090p);
        }
        int i6 = ((i2 & 7) << 6) | (i3 & 63);
        if ((i4 & 192) != 128) {
            t0(i4 & 255, this.f5090p);
        }
        int i7 = (i6 << 6) | (i4 & 63);
        if ((i5 & 192) != 128) {
            t0(i5 & 255, this.f5090p);
        }
        return ((i7 << 6) | (i5 & 63)) - 65536;
    }

    private final String _fastParseName() {
        byte[] bArr = this.g0;
        int[] iArr = i0;
        int i2 = this.f5090p;
        int i3 = i2 + 1;
        int i4 = bArr[i2] & 255;
        if (iArr[i4] != 0) {
            if (i4 == 34) {
                this.f5090p = i3;
                return "";
            }
            return null;
        }
        int i5 = i3 + 1;
        int i6 = bArr[i3] & 255;
        if (iArr[i6] != 0) {
            if (i6 == 34) {
                this.f5090p = i5;
                return m0(i4, 1);
            }
            return null;
        }
        int i7 = (i4 << 8) | i6;
        int i8 = i5 + 1;
        int i9 = bArr[i5] & 255;
        if (iArr[i9] != 0) {
            if (i9 == 34) {
                this.f5090p = i8;
                return m0(i7, 2);
            }
            return null;
        }
        int i10 = (i7 << 8) | i9;
        int i11 = i8 + 1;
        int i12 = bArr[i8] & 255;
        if (iArr[i12] != 0) {
            if (i12 == 34) {
                this.f5090p = i11;
                return m0(i10, 3);
            }
            return null;
        }
        int i13 = (i10 << 8) | i12;
        int i14 = i11 + 1;
        int i15 = bArr[i11] & 255;
        if (iArr[i15] == 0) {
            this.R = i13;
            return _parseMediumName(i14, i15);
        } else if (i15 == 34) {
            this.f5090p = i14;
            return m0(i13, 4);
        } else {
            return null;
        }
    }

    private JsonToken _finishAposName(int i2, int i3, int i4) {
        int[] iArr = this.P;
        int[] iArr2 = i0;
        while (true) {
            int i5 = this.f5090p;
            if (i5 >= this.f5091q) {
                this.Q = i2;
                this.S = i3;
                this.T = i4;
                this.Y = 9;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            byte[] bArr = this.g0;
            this.f5090p = i5 + 1;
            int i6 = bArr[i5] & 255;
            if (i6 == 39) {
                if (i4 > 0) {
                    if (i2 >= iArr.length) {
                        iArr = ParserBase.a0(iArr, iArr.length);
                        this.P = iArr;
                    }
                    iArr[i2] = NonBlockingJsonParserBase._padLastQuad(i3, i4);
                    i2++;
                } else if (i2 == 0) {
                    return l0("");
                }
                String findName = this.O.findName(iArr, i2);
                if (findName == null) {
                    findName = h0(iArr, i2, i4);
                }
                return l0(findName);
            }
            if (i6 != 34 && iArr2[i6] != 0) {
                if (i6 != 92) {
                    S(i6, AppMeasurementSdk.ConditionalUserProperty.NAME);
                } else {
                    i6 = _decodeCharEscape();
                    if (i6 < 0) {
                        this.Y = 8;
                        this.Z = 9;
                        this.Q = i2;
                        this.S = i3;
                        this.T = i4;
                        JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
                        this.f5104c = jsonToken2;
                        return jsonToken2;
                    }
                }
                if (i6 > 127) {
                    int i7 = 0;
                    if (i4 >= 4) {
                        if (i2 >= iArr.length) {
                            int[] a0 = ParserBase.a0(iArr, iArr.length);
                            this.P = a0;
                            iArr = a0;
                        }
                        iArr[i2] = i3;
                        i2++;
                        i3 = 0;
                        i4 = 0;
                    }
                    int i8 = i3 << 8;
                    if (i6 < 2048) {
                        i3 = i8 | (i6 >> 6) | 192;
                        i4++;
                    } else {
                        int i9 = i8 | (i6 >> 12) | BERTags.FLAGS;
                        int i10 = i4 + 1;
                        if (i10 >= 4) {
                            if (i2 >= iArr.length) {
                                int[] a02 = ParserBase.a0(iArr, iArr.length);
                                this.P = a02;
                                iArr = a02;
                            }
                            iArr[i2] = i9;
                            i2++;
                            i10 = 0;
                        } else {
                            i7 = i9;
                        }
                        i3 = (i7 << 8) | ((i6 >> 6) & 63) | 128;
                        i4 = i10 + 1;
                    }
                    i6 = (i6 & 63) | 128;
                }
            }
            if (i4 < 4) {
                i4++;
                i3 = (i3 << 8) | i6;
            } else {
                if (i2 >= iArr.length) {
                    iArr = ParserBase.a0(iArr, iArr.length);
                    this.P = iArr;
                }
                iArr[i2] = i3;
                i2++;
                i3 = i6;
                i4 = 1;
            }
        }
    }

    private final JsonToken _finishAposString() {
        int i2;
        int[] iArr = _icUTF8;
        byte[] bArr = this.g0;
        char[] bufferWithoutReset = this.z.getBufferWithoutReset();
        int currentSegmentSize = this.z.getCurrentSegmentSize();
        int i3 = this.f5090p;
        int i4 = this.f5091q - 5;
        while (i3 < this.f5091q) {
            int i5 = 0;
            if (currentSegmentSize >= bufferWithoutReset.length) {
                bufferWithoutReset = this.z.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            int min = Math.min(this.f5091q, (bufferWithoutReset.length - currentSegmentSize) + i3);
            while (true) {
                if (i3 < min) {
                    int i6 = i3 + 1;
                    int i7 = bArr[i3] & 255;
                    if (iArr[i7] == 0 || i7 == 34) {
                        if (i7 == 39) {
                            this.f5090p = i6;
                            this.z.setCurrentLength(currentSegmentSize);
                            return x0(JsonToken.VALUE_STRING);
                        }
                        bufferWithoutReset[currentSegmentSize] = (char) i7;
                        i3 = i6;
                        currentSegmentSize++;
                    } else if (i6 >= i4) {
                        this.f5090p = i6;
                        this.z.setCurrentLength(currentSegmentSize);
                        if (!_decodeSplitMultiByte(i7, iArr[i7], i6 < this.f5091q)) {
                            this.Z = 45;
                            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                            this.f5104c = jsonToken;
                            return jsonToken;
                        }
                        bufferWithoutReset = this.z.getBufferWithoutReset();
                        currentSegmentSize = this.z.getCurrentSegmentSize();
                        i3 = this.f5090p;
                    } else {
                        int i8 = iArr[i7];
                        if (i8 == 1) {
                            this.f5090p = i6;
                            i7 = _decodeFastCharEscape();
                            i2 = this.f5090p;
                        } else if (i8 == 2) {
                            i7 = _decodeUTF8_2(i7, this.g0[i6]);
                            i2 = i6 + 1;
                        } else if (i8 == 3) {
                            byte[] bArr2 = this.g0;
                            int i9 = i6 + 1;
                            i7 = _decodeUTF8_3(i7, bArr2[i6], bArr2[i9]);
                            i2 = i9 + 1;
                        } else if (i8 != 4) {
                            if (i7 < 32) {
                                S(i7, "string value");
                            } else {
                                r0(i7);
                            }
                            i2 = i6;
                        } else {
                            byte[] bArr3 = this.g0;
                            int i10 = i6 + 1;
                            int i11 = i10 + 1;
                            int i12 = i11 + 1;
                            int _decodeUTF8_4 = _decodeUTF8_4(i7, bArr3[i6], bArr3[i10], bArr3[i11]);
                            int i13 = currentSegmentSize + 1;
                            bufferWithoutReset[currentSegmentSize] = (char) (55296 | (_decodeUTF8_4 >> 10));
                            if (i13 >= bufferWithoutReset.length) {
                                bufferWithoutReset = this.z.finishCurrentSegment();
                                currentSegmentSize = 0;
                            } else {
                                currentSegmentSize = i13;
                            }
                            i7 = (_decodeUTF8_4 & 1023) | 56320;
                            i2 = i12;
                        }
                        if (currentSegmentSize >= bufferWithoutReset.length) {
                            bufferWithoutReset = this.z.finishCurrentSegment();
                        } else {
                            i5 = currentSegmentSize;
                        }
                        currentSegmentSize = i5 + 1;
                        bufferWithoutReset[i5] = (char) i7;
                        i3 = i2;
                    }
                }
            }
        }
        this.f5090p = i3;
        this.Y = 45;
        this.z.setCurrentLength(currentSegmentSize);
        JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
        this.f5104c = jsonToken2;
        return jsonToken2;
    }

    private final JsonToken _finishBOM(int i2) {
        Integer valueOf;
        String str;
        while (true) {
            int i3 = this.f5090p;
            if (i3 >= this.f5091q) {
                this.S = i2;
                this.Y = 1;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            byte[] bArr = this.g0;
            this.f5090p = i3 + 1;
            int i4 = bArr[i3] & 255;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        this.f5092r -= 3;
                        return _startDocument(i4);
                    }
                } else if (i4 != 191) {
                    valueOf = Integer.valueOf(i4);
                    str = "Unexpected byte 0x%02x following 0xEF 0xBB; should get 0xBF as third byte of UTF-8 BOM";
                    l(str, valueOf);
                }
            } else if (i4 != 187) {
                valueOf = Integer.valueOf(i4);
                str = "Unexpected byte 0x%02x following 0xEF; should get 0xBB as second byte UTF-8 BOM";
                l(str, valueOf);
            }
            i2++;
        }
    }

    private final JsonToken _finishCComment(int i2, boolean z) {
        while (true) {
            int i3 = this.f5090p;
            if (i3 >= this.f5091q) {
                this.Y = z ? 52 : 53;
                this.S = i2;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            byte[] bArr = this.g0;
            int i4 = i3 + 1;
            this.f5090p = i4;
            int i5 = bArr[i3] & 255;
            if (i5 < 32) {
                if (i5 == 10) {
                    this.f5093s++;
                } else if (i5 == 13) {
                    this.d0++;
                } else if (i5 != 9) {
                    u(i5);
                }
                this.f5094t = i4;
            } else if (i5 == 42) {
                z = true;
            } else if (i5 == 47 && z) {
                return _startAfterComment(i2);
            }
            z = false;
        }
    }

    private final JsonToken _finishCppComment(int i2) {
        int i3;
        while (true) {
            int i4 = this.f5090p;
            if (i4 >= this.f5091q) {
                this.Y = 54;
                this.S = i2;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            byte[] bArr = this.g0;
            i3 = i4 + 1;
            this.f5090p = i3;
            int i5 = bArr[i4] & 255;
            if (i5 < 32) {
                if (i5 == 10) {
                    this.f5093s++;
                    break;
                } else if (i5 == 13) {
                    this.d0++;
                    break;
                } else if (i5 != 9) {
                    u(i5);
                }
            }
        }
        this.f5094t = i3;
        return _startAfterComment(i2);
    }

    private final JsonToken _finishHashComment(int i2) {
        int i3;
        if ((this.f5048a & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0) {
            s(35, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_YAML_COMMENTS' not enabled for parser)");
        }
        while (true) {
            int i4 = this.f5090p;
            if (i4 >= this.f5091q) {
                this.Y = 55;
                this.S = i2;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            byte[] bArr = this.g0;
            i3 = i4 + 1;
            this.f5090p = i3;
            int i5 = bArr[i4] & 255;
            if (i5 < 32) {
                if (i5 == 10) {
                    this.f5093s++;
                    break;
                } else if (i5 == 13) {
                    this.d0++;
                    break;
                } else if (i5 != 9) {
                    u(i5);
                }
            }
        }
        this.f5094t = i3;
        return _startAfterComment(i2);
    }

    private final JsonToken _finishRegularString() {
        int i2;
        int[] iArr = _icUTF8;
        byte[] bArr = this.g0;
        char[] bufferWithoutReset = this.z.getBufferWithoutReset();
        int currentSegmentSize = this.z.getCurrentSegmentSize();
        int i3 = this.f5090p;
        int i4 = this.f5091q - 5;
        while (i3 < this.f5091q) {
            int i5 = 0;
            if (currentSegmentSize >= bufferWithoutReset.length) {
                bufferWithoutReset = this.z.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            int min = Math.min(this.f5091q, (bufferWithoutReset.length - currentSegmentSize) + i3);
            while (true) {
                if (i3 < min) {
                    int i6 = i3 + 1;
                    int i7 = bArr[i3] & 255;
                    if (iArr[i7] == 0) {
                        bufferWithoutReset[currentSegmentSize] = (char) i7;
                        i3 = i6;
                        currentSegmentSize++;
                    } else if (i7 == 34) {
                        this.f5090p = i6;
                        this.z.setCurrentLength(currentSegmentSize);
                        return x0(JsonToken.VALUE_STRING);
                    } else if (i6 >= i4) {
                        this.f5090p = i6;
                        this.z.setCurrentLength(currentSegmentSize);
                        if (!_decodeSplitMultiByte(i7, iArr[i7], i6 < this.f5091q)) {
                            this.Z = 40;
                            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                            this.f5104c = jsonToken;
                            return jsonToken;
                        }
                        bufferWithoutReset = this.z.getBufferWithoutReset();
                        currentSegmentSize = this.z.getCurrentSegmentSize();
                        i3 = this.f5090p;
                    } else {
                        int i8 = iArr[i7];
                        if (i8 == 1) {
                            this.f5090p = i6;
                            i7 = _decodeFastCharEscape();
                            i2 = this.f5090p;
                        } else if (i8 == 2) {
                            i7 = _decodeUTF8_2(i7, this.g0[i6]);
                            i2 = i6 + 1;
                        } else if (i8 == 3) {
                            byte[] bArr2 = this.g0;
                            int i9 = i6 + 1;
                            i7 = _decodeUTF8_3(i7, bArr2[i6], bArr2[i9]);
                            i2 = i9 + 1;
                        } else if (i8 != 4) {
                            if (i7 < 32) {
                                S(i7, "string value");
                            } else {
                                r0(i7);
                            }
                            i2 = i6;
                        } else {
                            byte[] bArr3 = this.g0;
                            int i10 = i6 + 1;
                            int i11 = i10 + 1;
                            int i12 = i11 + 1;
                            int _decodeUTF8_4 = _decodeUTF8_4(i7, bArr3[i6], bArr3[i10], bArr3[i11]);
                            int i13 = currentSegmentSize + 1;
                            bufferWithoutReset[currentSegmentSize] = (char) (55296 | (_decodeUTF8_4 >> 10));
                            if (i13 >= bufferWithoutReset.length) {
                                bufferWithoutReset = this.z.finishCurrentSegment();
                                currentSegmentSize = 0;
                            } else {
                                currentSegmentSize = i13;
                            }
                            i7 = (_decodeUTF8_4 & 1023) | 56320;
                            i2 = i12;
                        }
                        if (currentSegmentSize >= bufferWithoutReset.length) {
                            bufferWithoutReset = this.z.finishCurrentSegment();
                        } else {
                            i5 = currentSegmentSize;
                        }
                        currentSegmentSize = i5 + 1;
                        bufferWithoutReset[i5] = (char) i7;
                        i3 = i2;
                    }
                }
            }
        }
        this.f5090p = i3;
        this.Y = 40;
        this.z.setCurrentLength(currentSegmentSize);
        JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
        this.f5104c = jsonToken2;
        return jsonToken2;
    }

    private JsonToken _finishUnquotedName(int i2, int i3, int i4) {
        int[] iArr = this.P;
        int[] inputCodeUtf8JsNames = CharTypes.getInputCodeUtf8JsNames();
        while (true) {
            int i5 = this.f5090p;
            if (i5 >= this.f5091q) {
                this.Q = i2;
                this.S = i3;
                this.T = i4;
                this.Y = 10;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            int i6 = this.g0[i5] & 255;
            if (inputCodeUtf8JsNames[i6] != 0) {
                if (i4 > 0) {
                    if (i2 >= iArr.length) {
                        iArr = ParserBase.a0(iArr, iArr.length);
                        this.P = iArr;
                    }
                    iArr[i2] = i3;
                    i2++;
                }
                String findName = this.O.findName(iArr, i2);
                if (findName == null) {
                    findName = h0(iArr, i2, i4);
                }
                return l0(findName);
            }
            this.f5090p = i5 + 1;
            if (i4 < 4) {
                i4++;
                i3 = (i3 << 8) | i6;
            } else {
                if (i2 >= iArr.length) {
                    iArr = ParserBase.a0(iArr, iArr.length);
                    this.P = iArr;
                }
                iArr[i2] = i3;
                i3 = i6;
                i4 = 1;
                i2++;
            }
        }
    }

    private JsonToken _handleOddName(int i2) {
        if (i2 != 35) {
            if (i2 != 39) {
                if (i2 == 47) {
                    return _startSlashComment(4);
                }
                if (i2 == 93) {
                    return i0();
                }
            } else if ((this.f5048a & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0) {
                return _finishAposName(0, 0, 0);
            }
        } else if ((this.f5048a & FEAT_MASK_ALLOW_YAML_COMMENTS) != 0) {
            return _finishHashComment(4);
        }
        if ((this.f5048a & FEAT_MASK_ALLOW_UNQUOTED_NAMES) == 0) {
            s((char) i2, "was expecting double-quote to start field name");
        }
        if (CharTypes.getInputCodeUtf8JsNames()[i2] != 0) {
            s(i2, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        return _finishUnquotedName(0, i2, 1);
    }

    private final JsonToken _parseEscapedName(int i2, int i3, int i4) {
        int i5;
        int[] iArr = this.P;
        int[] iArr2 = i0;
        while (true) {
            int i6 = this.f5090p;
            if (i6 >= this.f5091q) {
                this.Q = i2;
                this.S = i3;
                this.T = i4;
                this.Y = 7;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            byte[] bArr = this.g0;
            this.f5090p = i6 + 1;
            int i7 = bArr[i6] & 255;
            if (iArr2[i7] == 0) {
                if (i4 < 4) {
                    i4++;
                    i3 = (i3 << 8) | i7;
                } else {
                    if (i2 >= iArr.length) {
                        int[] a0 = ParserBase.a0(iArr, iArr.length);
                        this.P = a0;
                        iArr = a0;
                    }
                    i5 = i2 + 1;
                    iArr[i2] = i3;
                    i2 = i5;
                    i4 = 1;
                    i3 = i7;
                }
            } else if (i7 == 34) {
                if (i4 > 0) {
                    if (i2 >= iArr.length) {
                        iArr = ParserBase.a0(iArr, iArr.length);
                        this.P = iArr;
                    }
                    iArr[i2] = NonBlockingJsonParserBase._padLastQuad(i3, i4);
                    i2++;
                } else if (i2 == 0) {
                    return l0("");
                }
                String findName = this.O.findName(iArr, i2);
                if (findName == null) {
                    findName = h0(iArr, i2, i4);
                }
                return l0(findName);
            } else {
                if (i7 != 92) {
                    S(i7, AppMeasurementSdk.ConditionalUserProperty.NAME);
                } else {
                    i7 = _decodeCharEscape();
                    if (i7 < 0) {
                        this.Y = 8;
                        this.Z = 7;
                        this.Q = i2;
                        this.S = i3;
                        this.T = i4;
                        JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
                        this.f5104c = jsonToken2;
                        return jsonToken2;
                    }
                }
                if (i2 >= iArr.length) {
                    iArr = ParserBase.a0(iArr, iArr.length);
                    this.P = iArr;
                }
                if (i7 > 127) {
                    int i8 = 0;
                    if (i4 >= 4) {
                        iArr[i2] = i3;
                        i2++;
                        i3 = 0;
                        i4 = 0;
                    }
                    int i9 = i3 << 8;
                    if (i7 < 2048) {
                        i3 = i9 | (i7 >> 6) | 192;
                        i4++;
                    } else {
                        int i10 = i9 | (i7 >> 12) | BERTags.FLAGS;
                        int i11 = i4 + 1;
                        if (i11 >= 4) {
                            iArr[i2] = i10;
                            i2++;
                            i11 = 0;
                        } else {
                            i8 = i10;
                        }
                        i3 = (i8 << 8) | ((i7 >> 6) & 63) | 128;
                        i4 = i11 + 1;
                    }
                    i7 = (i7 & 63) | 128;
                }
                if (i4 < 4) {
                    i4++;
                    i3 = (i3 << 8) | i7;
                } else {
                    i5 = i2 + 1;
                    iArr[i2] = i3;
                    i2 = i5;
                    i4 = 1;
                    i3 = i7;
                }
            }
        }
    }

    private final String _parseMediumName(int i2, int i3) {
        byte[] bArr = this.g0;
        int[] iArr = i0;
        int i4 = i2 + 1;
        int i5 = bArr[i2] & 255;
        if (iArr[i5] != 0) {
            if (i5 == 34) {
                this.f5090p = i4;
                return n0(this.R, i3, 1);
            }
            return null;
        }
        int i6 = i5 | (i3 << 8);
        int i7 = i4 + 1;
        int i8 = bArr[i4] & 255;
        if (iArr[i8] != 0) {
            if (i8 == 34) {
                this.f5090p = i7;
                return n0(this.R, i6, 2);
            }
            return null;
        }
        int i9 = (i6 << 8) | i8;
        int i10 = i7 + 1;
        int i11 = bArr[i7] & 255;
        if (iArr[i11] != 0) {
            if (i11 == 34) {
                this.f5090p = i10;
                return n0(this.R, i9, 3);
            }
            return null;
        }
        int i12 = (i9 << 8) | i11;
        int i13 = i10 + 1;
        int i14 = bArr[i10] & 255;
        if (iArr[i14] == 0) {
            return _parseMediumName2(i13, i14, i12);
        }
        if (i14 == 34) {
            this.f5090p = i13;
            return n0(this.R, i12, 4);
        }
        return null;
    }

    private final String _parseMediumName2(int i2, int i3, int i4) {
        byte[] bArr = this.g0;
        int[] iArr = i0;
        int i5 = i2 + 1;
        int i6 = bArr[i2] & 255;
        if (iArr[i6] != 0) {
            if (i6 == 34) {
                this.f5090p = i5;
                return o0(this.R, i4, i3, 1);
            }
            return null;
        }
        int i7 = i6 | (i3 << 8);
        int i8 = i5 + 1;
        int i9 = bArr[i5] & 255;
        if (iArr[i9] != 0) {
            if (i9 == 34) {
                this.f5090p = i8;
                return o0(this.R, i4, i7, 2);
            }
            return null;
        }
        int i10 = (i7 << 8) | i9;
        int i11 = i8 + 1;
        int i12 = bArr[i8] & 255;
        if (iArr[i12] != 0) {
            if (i12 == 34) {
                this.f5090p = i11;
                return o0(this.R, i4, i10, 3);
            }
            return null;
        }
        int i13 = (i10 << 8) | i12;
        int i14 = i11 + 1;
        if ((bArr[i11] & 255) == 34) {
            this.f5090p = i14;
            return o0(this.R, i4, i13, 4);
        }
        return null;
    }

    private final int _skipWS(int i2) {
        do {
            if (i2 != 32) {
                if (i2 == 10) {
                    this.f5093s++;
                } else if (i2 == 13) {
                    this.d0++;
                } else if (i2 != 9) {
                    u(i2);
                }
                this.f5094t = this.f5090p;
            }
            int i3 = this.f5090p;
            if (i3 >= this.f5091q) {
                this.f5104c = JsonToken.NOT_AVAILABLE;
                return 0;
            }
            byte[] bArr = this.g0;
            this.f5090p = i3 + 1;
            i2 = bArr[i3] & 255;
        } while (i2 <= 32);
        return i2;
    }

    private final JsonToken _startAfterComment(int i2) {
        int i3 = this.f5090p;
        if (i3 >= this.f5091q) {
            this.Y = i2;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this.f5104c = jsonToken;
            return jsonToken;
        }
        byte[] bArr = this.g0;
        this.f5090p = i3 + 1;
        int i4 = bArr[i3] & 255;
        if (i2 != 4) {
            if (i2 != 5) {
                switch (i2) {
                    case 12:
                        return _startValue(i4);
                    case 13:
                        return _startValueExpectComma(i4);
                    case 14:
                        return _startValueExpectColon(i4);
                    case 15:
                        return _startValueAfterComma(i4);
                    default:
                        VersionUtil.throwInternal();
                        return null;
                }
            }
            return _startFieldNameAfterComma(i4);
        }
        return _startFieldName(i4);
    }

    private final JsonToken _startDocument(int i2) {
        int i3 = i2 & 255;
        if (i3 != 239 || this.Y == 1) {
            while (i3 <= 32) {
                if (i3 != 32) {
                    if (i3 == 10) {
                        this.f5093s++;
                    } else if (i3 == 13) {
                        this.d0++;
                    } else if (i3 != 9) {
                        u(i3);
                    }
                    this.f5094t = this.f5090p;
                }
                int i4 = this.f5090p;
                if (i4 >= this.f5091q) {
                    this.Y = 3;
                    if (this.f5089o) {
                        return null;
                    }
                    return this.a0 ? k0() : JsonToken.NOT_AVAILABLE;
                }
                byte[] bArr = this.g0;
                this.f5090p = i4 + 1;
                i3 = bArr[i4] & 255;
            }
            return _startValue(i3);
        }
        return _finishBOM(1);
    }

    private final JsonToken _startFieldName(int i2) {
        String _fastParseName;
        if (i2 > 32 || (i2 = _skipWS(i2)) > 0) {
            w0();
            return i2 != 34 ? i2 == 125 ? j0() : _handleOddName(i2) : (this.f5090p + 13 > this.f5091q || (_fastParseName = _fastParseName()) == null) ? _parseEscapedName(0, 0, 0) : l0(_fastParseName);
        }
        this.Y = 4;
        return this.f5104c;
    }

    private final JsonToken _startFieldNameAfterComma(int i2) {
        String _fastParseName;
        if (i2 <= 32 && (i2 = _skipWS(i2)) <= 0) {
            this.Y = 5;
            return this.f5104c;
        }
        if (i2 != 44) {
            if (i2 == 125) {
                return j0();
            }
            if (i2 == 35) {
                return _finishHashComment(5);
            }
            if (i2 == 47) {
                return _startSlashComment(5);
            }
            s(i2, "was expecting comma to separate " + this.x.typeDesc() + " entries");
        }
        int i3 = this.f5090p;
        if (i3 >= this.f5091q) {
            this.Y = 4;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this.f5104c = jsonToken;
            return jsonToken;
        }
        int i4 = this.g0[i3];
        this.f5090p = i3 + 1;
        if (i4 > 32 || (i4 = _skipWS(i4)) > 0) {
            w0();
            return i4 != 34 ? (i4 != 125 || (this.f5048a & FEAT_MASK_TRAILING_COMMA) == 0) ? _handleOddName(i4) : j0() : (this.f5090p + 13 > this.f5091q || (_fastParseName = _fastParseName()) == null) ? _parseEscapedName(0, 0, 0) : l0(_fastParseName);
        }
        this.Y = 4;
        return this.f5104c;
    }

    private final JsonToken _startSlashComment(int i2) {
        if ((this.f5048a & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0) {
            s(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        int i3 = this.f5090p;
        if (i3 >= this.f5091q) {
            this.S = i2;
            this.Y = 51;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this.f5104c = jsonToken;
            return jsonToken;
        }
        byte[] bArr = this.g0;
        this.f5090p = i3 + 1;
        byte b2 = bArr[i3];
        if (b2 == 42) {
            return _finishCComment(i2, false);
        }
        if (b2 == 47) {
            return _finishCppComment(i2);
        }
        s(b2 & 255, "was expecting either '*' or '/' for a comment");
        return null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private final JsonToken _startValue(int i2) {
        if (i2 <= 32 && (i2 = _skipWS(i2)) <= 0) {
            this.Y = 12;
            return this.f5104c;
        }
        w0();
        this.x.expectComma();
        if (i2 == 34) {
            return Y0();
        }
        if (i2 != 35) {
            if (i2 != 91) {
                if (i2 != 93) {
                    if (i2 != 102) {
                        if (i2 != 110) {
                            if (i2 != 116) {
                                if (i2 != 123) {
                                    if (i2 != 125) {
                                        switch (i2) {
                                            case 45:
                                                return U0();
                                            case 46:
                                                if (isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
                                                    return T0();
                                                }
                                                break;
                                            case 47:
                                                return _startSlashComment(12);
                                            case 48:
                                                return W0();
                                            case 49:
                                            case 50:
                                            case 51:
                                            case 52:
                                            case 53:
                                            case 54:
                                            case 55:
                                            case 56:
                                            case 57:
                                                return X0(i2);
                                        }
                                        return a1(false, i2);
                                    }
                                    return j0();
                                }
                                return v0();
                            }
                            return Z0();
                        }
                        return V0();
                    }
                    return R0();
                }
                return i0();
            }
            return u0();
        }
        return _finishHashComment(12);
    }

    private final JsonToken _startValueAfterComma(int i2) {
        if (i2 <= 32 && (i2 = _skipWS(i2)) <= 0) {
            this.Y = 15;
            return this.f5104c;
        }
        w0();
        if (i2 == 34) {
            return Y0();
        }
        if (i2 != 35) {
            if (i2 != 45) {
                if (i2 != 91) {
                    if (i2 != 93) {
                        if (i2 == 102) {
                            return R0();
                        }
                        if (i2 == 110) {
                            return V0();
                        }
                        if (i2 == 116) {
                            return Z0();
                        }
                        if (i2 == 123) {
                            return v0();
                        }
                        if (i2 != 125) {
                            switch (i2) {
                                case 47:
                                    return _startSlashComment(15);
                                case 48:
                                    return W0();
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                    return X0(i2);
                            }
                        } else if ((this.f5048a & FEAT_MASK_TRAILING_COMMA) != 0) {
                            return j0();
                        }
                    } else if ((this.f5048a & FEAT_MASK_TRAILING_COMMA) != 0) {
                        return i0();
                    }
                    return a1(true, i2);
                }
                return u0();
            }
            return U0();
        }
        return _finishHashComment(15);
    }

    private final JsonToken _startValueExpectColon(int i2) {
        if (i2 <= 32 && (i2 = _skipWS(i2)) <= 0) {
            this.Y = 14;
            return this.f5104c;
        }
        if (i2 != 58) {
            if (i2 == 47) {
                return _startSlashComment(14);
            }
            if (i2 == 35) {
                return _finishHashComment(14);
            }
            s(i2, "was expecting a colon to separate field name and value");
        }
        int i3 = this.f5090p;
        if (i3 >= this.f5091q) {
            this.Y = 12;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this.f5104c = jsonToken;
            return jsonToken;
        }
        int i4 = this.g0[i3];
        this.f5090p = i3 + 1;
        if (i4 <= 32 && (i4 = _skipWS(i4)) <= 0) {
            this.Y = 12;
            return this.f5104c;
        }
        w0();
        if (i4 == 34) {
            return Y0();
        }
        if (i4 != 35) {
            if (i4 != 45) {
                if (i4 != 91) {
                    if (i4 != 102) {
                        if (i4 != 110) {
                            if (i4 != 116) {
                                if (i4 != 123) {
                                    switch (i4) {
                                        case 47:
                                            return _startSlashComment(12);
                                        case 48:
                                            return W0();
                                        case 49:
                                        case 50:
                                        case 51:
                                        case 52:
                                        case 53:
                                        case 54:
                                        case 55:
                                        case 56:
                                        case 57:
                                            return X0(i4);
                                        default:
                                            return a1(false, i4);
                                    }
                                }
                                return v0();
                            }
                            return Z0();
                        }
                        return V0();
                    }
                    return R0();
                }
                return u0();
            }
            return U0();
        }
        return _finishHashComment(12);
    }

    private final JsonToken _startValueExpectComma(int i2) {
        if (i2 <= 32 && (i2 = _skipWS(i2)) <= 0) {
            this.Y = 13;
            return this.f5104c;
        }
        if (i2 != 44) {
            if (i2 == 93) {
                return i0();
            }
            if (i2 == 125) {
                return j0();
            }
            if (i2 == 47) {
                return _startSlashComment(13);
            }
            if (i2 == 35) {
                return _finishHashComment(13);
            }
            s(i2, "was expecting comma to separate " + this.x.typeDesc() + " entries");
        }
        this.x.expectComma();
        int i3 = this.f5090p;
        if (i3 >= this.f5091q) {
            this.Y = 15;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this.f5104c = jsonToken;
            return jsonToken;
        }
        int i4 = this.g0[i3];
        this.f5090p = i3 + 1;
        if (i4 <= 32 && (i4 = _skipWS(i4)) <= 0) {
            this.Y = 15;
            return this.f5104c;
        }
        w0();
        if (i4 == 34) {
            return Y0();
        }
        if (i4 != 35) {
            if (i4 != 45) {
                if (i4 != 91) {
                    if (i4 != 93) {
                        if (i4 == 102) {
                            return R0();
                        }
                        if (i4 == 110) {
                            return V0();
                        }
                        if (i4 == 116) {
                            return Z0();
                        }
                        if (i4 == 123) {
                            return v0();
                        }
                        if (i4 != 125) {
                            switch (i4) {
                                case 47:
                                    return _startSlashComment(15);
                                case 48:
                                    return W0();
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                    return X0(i4);
                            }
                        } else if ((this.f5048a & FEAT_MASK_TRAILING_COMMA) != 0) {
                            return j0();
                        }
                    } else if ((this.f5048a & FEAT_MASK_TRAILING_COMMA) != 0) {
                        return i0();
                    }
                    return a1(true, i4);
                }
                return u0();
            }
            return U0();
        }
        return _finishHashComment(15);
    }

    protected JsonToken A0() {
        do {
            int i2 = this.f5090p;
            if (i2 >= this.f5091q) {
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            byte[] bArr = this.g0;
            this.f5090p = i2 + 1;
            char c2 = (char) bArr[i2];
            if (!Character.isJavaIdentifierPart(c2)) {
                break;
            }
            this.z.append(c2);
        } while (this.z.size() < 256);
        return P0(this.z.contentsAsString());
    }

    protected JsonToken B0() {
        return P0(this.z.contentsAsString());
    }

    protected final JsonToken C0() {
        int i2;
        int _decodeSplitEscaped = _decodeSplitEscaped(this.U, this.V);
        if (_decodeSplitEscaped < 0) {
            this.Y = 8;
            return JsonToken.NOT_AVAILABLE;
        }
        int i3 = this.Q;
        int[] iArr = this.P;
        if (i3 >= iArr.length) {
            this.P = ParserBase.a0(iArr, 32);
        }
        int i4 = this.S;
        int i5 = this.T;
        int i6 = 1;
        if (_decodeSplitEscaped > 127) {
            int i7 = 0;
            if (i5 >= 4) {
                int[] iArr2 = this.P;
                int i8 = this.Q;
                this.Q = i8 + 1;
                iArr2[i8] = i4;
                i4 = 0;
                i5 = 0;
            }
            int i9 = i4 << 8;
            if (_decodeSplitEscaped < 2048) {
                i2 = (_decodeSplitEscaped >> 6) | 192;
            } else {
                int i10 = i9 | (_decodeSplitEscaped >> 12) | BERTags.FLAGS;
                i5++;
                if (i5 >= 4) {
                    int[] iArr3 = this.P;
                    int i11 = this.Q;
                    this.Q = i11 + 1;
                    iArr3[i11] = i10;
                    i5 = 0;
                } else {
                    i7 = i10;
                }
                i9 = i7 << 8;
                i2 = ((_decodeSplitEscaped >> 6) & 63) | 128;
            }
            i4 = i9 | i2;
            i5++;
            _decodeSplitEscaped = (_decodeSplitEscaped & 63) | 128;
        }
        if (i5 < 4) {
            i6 = 1 + i5;
            _decodeSplitEscaped |= i4 << 8;
        } else {
            int[] iArr4 = this.P;
            int i12 = this.Q;
            this.Q = i12 + 1;
            iArr4[i12] = i4;
        }
        return this.Z == 9 ? _finishAposName(this.Q, _decodeSplitEscaped, i6) : _parseEscapedName(this.Q, _decodeSplitEscaped, i6);
    }

    protected JsonToken D0(boolean z, int i2) {
        if (z) {
            this.Y = 32;
            if (i2 == 45 || i2 == 43) {
                this.z.append((char) i2);
                int i3 = this.f5090p;
                if (i3 >= this.f5091q) {
                    this.Y = 32;
                    this.N = 0;
                    return JsonToken.NOT_AVAILABLE;
                }
                byte[] bArr = this.g0;
                this.f5090p = i3 + 1;
                i2 = bArr[i3];
            }
        }
        char[] bufferWithoutReset = this.z.getBufferWithoutReset();
        int currentSegmentSize = this.z.getCurrentSegmentSize();
        int i4 = this.N;
        while (i2 >= 48 && i2 <= 57) {
            i4++;
            if (currentSegmentSize >= bufferWithoutReset.length) {
                bufferWithoutReset = this.z.expandCurrentSegment();
            }
            int i5 = currentSegmentSize + 1;
            bufferWithoutReset[currentSegmentSize] = (char) i2;
            int i6 = this.f5090p;
            if (i6 >= this.f5091q) {
                this.z.setCurrentLength(i5);
                this.N = i4;
                return JsonToken.NOT_AVAILABLE;
            }
            byte[] bArr2 = this.g0;
            this.f5090p = i6 + 1;
            i2 = bArr2[i6];
            currentSegmentSize = i5;
        }
        int i7 = i2 & 255;
        if (i4 == 0) {
            D(i7, "Exponent indicator not followed by a digit");
        }
        this.f5090p--;
        this.z.setCurrentLength(currentSegmentSize);
        this.N = i4;
        return x0(JsonToken.VALUE_NUMBER_FLOAT);
    }

    protected JsonToken E0() {
        byte b2;
        int i2 = this.M;
        char[] bufferWithoutReset = this.z.getBufferWithoutReset();
        int currentSegmentSize = this.z.getCurrentSegmentSize();
        while (true) {
            byte[] bArr = this.g0;
            int i3 = this.f5090p;
            this.f5090p = i3 + 1;
            b2 = bArr[i3];
            if (b2 < 48 || b2 > 57) {
                break;
            }
            i2++;
            if (currentSegmentSize >= bufferWithoutReset.length) {
                bufferWithoutReset = this.z.expandCurrentSegment();
            }
            int i4 = currentSegmentSize + 1;
            bufferWithoutReset[currentSegmentSize] = (char) b2;
            if (this.f5090p >= this.f5091q) {
                this.z.setCurrentLength(i4);
                this.M = i2;
                return JsonToken.NOT_AVAILABLE;
            }
            currentSegmentSize = i4;
        }
        if (i2 == 0) {
            D(b2, "Decimal point not followed by a digit");
        }
        this.M = i2;
        this.z.setCurrentLength(currentSegmentSize);
        if (b2 != 101 && b2 != 69) {
            this.f5090p--;
            this.z.setCurrentLength(currentSegmentSize);
            this.N = 0;
            return x0(JsonToken.VALUE_NUMBER_FLOAT);
        }
        this.z.append((char) b2);
        this.N = 0;
        int i5 = this.f5090p;
        if (i5 >= this.f5091q) {
            this.Y = 31;
            return JsonToken.NOT_AVAILABLE;
        }
        this.Y = 32;
        byte[] bArr2 = this.g0;
        this.f5090p = i5 + 1;
        return D0(true, bArr2[i5] & 255);
    }

    protected JsonToken F0(String str, int i2, JsonToken jsonToken) {
        int length = str.length();
        while (true) {
            int i3 = this.f5090p;
            if (i3 >= this.f5091q) {
                this.S = i2;
                JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken2;
                return jsonToken2;
            }
            byte b2 = this.g0[i3];
            if (i2 == length) {
                if (b2 < 48 || b2 == 93 || b2 == 125) {
                    return x0(jsonToken);
                }
            } else if (b2 != str.charAt(i2)) {
                break;
            } else {
                i2++;
                this.f5090p++;
            }
        }
        this.Y = 50;
        this.z.resetWithCopy(str, 0, i2);
        return A0();
    }

    protected JsonToken G0(String str, int i2, JsonToken jsonToken) {
        if (i2 == str.length()) {
            this.f5104c = jsonToken;
            return jsonToken;
        }
        this.z.resetWithCopy(str, 0, i2);
        return B0();
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0038, code lost:
        r4.Y = 50;
        r4.z.resetWithCopy(r0, 0, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0046, code lost:
        return A0();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected JsonToken H0(int i2, int i3) {
        String q0 = q0(i2);
        int length = q0.length();
        while (true) {
            int i4 = this.f5090p;
            if (i4 >= this.f5091q) {
                this.b0 = i2;
                this.S = i3;
                this.Y = 19;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            byte b2 = this.g0[i4];
            if (i3 == length) {
                if (b2 < 48 || b2 == 93 || b2 == 125) {
                    return z0(i2);
                }
            } else if (b2 != q0.charAt(i3)) {
                break;
            } else {
                i3++;
                this.f5090p++;
            }
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected char I() {
        VersionUtil.throwInternal();
        return TokenParser.SP;
    }

    protected JsonToken I0(int i2, int i3) {
        String q0 = q0(i2);
        if (i3 == q0.length()) {
            return z0(i2);
        }
        this.z.resetWithCopy(q0, 0, i3);
        return B0();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0042, code lost:
        r4.L = r0 + r6;
        r4.z.setCurrentLength(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
        return x0(com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected JsonToken J0(char[] cArr, int i2) {
        int i3 = this.K ? -1 : 0;
        while (true) {
            int i4 = this.f5090p;
            if (i4 >= this.f5091q) {
                this.Y = 26;
                this.z.setCurrentLength(i2);
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            int i5 = this.g0[i4] & 255;
            if (i5 < 48) {
                if (i5 == 46) {
                    this.L = i3 + i2;
                    this.f5090p = i4 + 1;
                    return S0(cArr, i2, i5);
                }
            } else if (i5 <= 57) {
                this.f5090p = i4 + 1;
                if (i2 >= cArr.length) {
                    cArr = this.z.expandCurrentSegment();
                }
                cArr[i2] = (char) i5;
                i2++;
            } else if (i5 == 101 || i5 == 69) {
                this.L = i3 + i2;
                this.f5090p = i4 + 1;
                return S0(cArr, i2, i5);
            }
        }
    }

    protected JsonToken K0() {
        int i2;
        do {
            int i3 = this.f5090p;
            if (i3 >= this.f5091q) {
                this.Y = 25;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            byte[] bArr = this.g0;
            this.f5090p = i3 + 1;
            i2 = bArr[i3] & 255;
            if (i2 < 48) {
                if (i2 == 46) {
                    char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
                    emptyAndGetCurrentSegment[0] = Soundex.SILENT_MARKER;
                    emptyAndGetCurrentSegment[1] = '0';
                    this.L = 1;
                    return S0(emptyAndGetCurrentSegment, 2, i2);
                }
            } else if (i2 > 57) {
                if (i2 == 101 || i2 == 69) {
                    char[] emptyAndGetCurrentSegment2 = this.z.emptyAndGetCurrentSegment();
                    emptyAndGetCurrentSegment2[0] = Soundex.SILENT_MARKER;
                    emptyAndGetCurrentSegment2[1] = '0';
                    this.L = 1;
                    return S0(emptyAndGetCurrentSegment2, 2, i2);
                } else if (i2 != 93 && i2 != 125) {
                    D(i2, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
                }
            } else if ((this.f5048a & FEAT_MASK_LEADING_ZEROS) == 0) {
                w("Leading zeroes not allowed");
                continue;
            }
            this.f5090p--;
            return y0(0, "0");
        } while (i2 == 48);
        char[] emptyAndGetCurrentSegment3 = this.z.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment3[0] = Soundex.SILENT_MARKER;
        emptyAndGetCurrentSegment3[1] = (char) i2;
        this.L = 1;
        return J0(emptyAndGetCurrentSegment3, 2);
    }

    protected JsonToken L0() {
        int i2;
        do {
            int i3 = this.f5090p;
            if (i3 >= this.f5091q) {
                this.Y = 24;
                JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken;
                return jsonToken;
            }
            byte[] bArr = this.g0;
            this.f5090p = i3 + 1;
            i2 = bArr[i3] & 255;
            if (i2 < 48) {
                if (i2 == 46) {
                    char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
                    emptyAndGetCurrentSegment[0] = '0';
                    this.L = 1;
                    return S0(emptyAndGetCurrentSegment, 1, i2);
                }
            } else if (i2 > 57) {
                if (i2 == 101 || i2 == 69) {
                    char[] emptyAndGetCurrentSegment2 = this.z.emptyAndGetCurrentSegment();
                    emptyAndGetCurrentSegment2[0] = '0';
                    this.L = 1;
                    return S0(emptyAndGetCurrentSegment2, 1, i2);
                } else if (i2 != 93 && i2 != 125) {
                    D(i2, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
                }
            } else if ((this.f5048a & FEAT_MASK_LEADING_ZEROS) == 0) {
                w("Leading zeroes not allowed");
                continue;
            }
            this.f5090p--;
            return y0(0, "0");
        } while (i2 == 48);
        char[] emptyAndGetCurrentSegment3 = this.z.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment3[0] = (char) i2;
        this.L = 1;
        return J0(emptyAndGetCurrentSegment3, 1);
    }

    protected JsonToken M0(int i2) {
        if (i2 > 48) {
            if (i2 > 57) {
                if (i2 == 73) {
                    return H0(3, 2);
                }
            }
            char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
            emptyAndGetCurrentSegment[0] = Soundex.SILENT_MARKER;
            emptyAndGetCurrentSegment[1] = (char) i2;
            this.L = 1;
            return J0(emptyAndGetCurrentSegment, 2);
        } else if (i2 == 48) {
            return K0();
        }
        D(i2, "expected digit (0-9) to follow minus sign, for valid numeric value");
        char[] emptyAndGetCurrentSegment2 = this.z.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment2[0] = Soundex.SILENT_MARKER;
        emptyAndGetCurrentSegment2[1] = (char) i2;
        this.L = 1;
        return J0(emptyAndGetCurrentSegment2, 2);
    }

    protected final JsonToken N0() {
        int i2 = this.Y;
        if (i2 != 1) {
            if (i2 == 4) {
                byte[] bArr = this.g0;
                int i3 = this.f5090p;
                this.f5090p = i3 + 1;
                return _startFieldName(bArr[i3] & 255);
            } else if (i2 == 5) {
                byte[] bArr2 = this.g0;
                int i4 = this.f5090p;
                this.f5090p = i4 + 1;
                return _startFieldNameAfterComma(bArr2[i4] & 255);
            } else {
                switch (i2) {
                    case 7:
                        return _parseEscapedName(this.Q, this.S, this.T);
                    case 8:
                        return C0();
                    case 9:
                        return _finishAposName(this.Q, this.S, this.T);
                    case 10:
                        return _finishUnquotedName(this.Q, this.S, this.T);
                    default:
                        switch (i2) {
                            case 12:
                                byte[] bArr3 = this.g0;
                                int i5 = this.f5090p;
                                this.f5090p = i5 + 1;
                                return _startValue(bArr3[i5] & 255);
                            case 13:
                                byte[] bArr4 = this.g0;
                                int i6 = this.f5090p;
                                this.f5090p = i6 + 1;
                                return _startValueExpectComma(bArr4[i6] & 255);
                            case 14:
                                byte[] bArr5 = this.g0;
                                int i7 = this.f5090p;
                                this.f5090p = i7 + 1;
                                return _startValueExpectColon(bArr5[i7] & 255);
                            case 15:
                                byte[] bArr6 = this.g0;
                                int i8 = this.f5090p;
                                this.f5090p = i8 + 1;
                                return _startValueAfterComma(bArr6[i8] & 255);
                            case 16:
                                return F0("null", this.S, JsonToken.VALUE_NULL);
                            case 17:
                                return F0("true", this.S, JsonToken.VALUE_TRUE);
                            case 18:
                                return F0("false", this.S, JsonToken.VALUE_FALSE);
                            case 19:
                                return H0(this.b0, this.S);
                            default:
                                switch (i2) {
                                    case 23:
                                        byte[] bArr7 = this.g0;
                                        int i9 = this.f5090p;
                                        this.f5090p = i9 + 1;
                                        return M0(bArr7[i9] & 255);
                                    case 24:
                                        return L0();
                                    case 25:
                                        return K0();
                                    case 26:
                                        return J0(this.z.getBufferWithoutReset(), this.z.getCurrentSegmentSize());
                                    default:
                                        switch (i2) {
                                            case 30:
                                                return E0();
                                            case 31:
                                                byte[] bArr8 = this.g0;
                                                int i10 = this.f5090p;
                                                this.f5090p = i10 + 1;
                                                return D0(true, bArr8[i10] & 255);
                                            case 32:
                                                byte[] bArr9 = this.g0;
                                                int i11 = this.f5090p;
                                                this.f5090p = i11 + 1;
                                                return D0(false, bArr9[i11] & 255);
                                            default:
                                                switch (i2) {
                                                    case 40:
                                                        return _finishRegularString();
                                                    case 41:
                                                        int _decodeSplitEscaped = _decodeSplitEscaped(this.U, this.V);
                                                        if (_decodeSplitEscaped < 0) {
                                                            return JsonToken.NOT_AVAILABLE;
                                                        }
                                                        this.z.append((char) _decodeSplitEscaped);
                                                        return this.Z == 45 ? _finishAposString() : _finishRegularString();
                                                    case 42:
                                                        TextBuffer textBuffer = this.z;
                                                        int i12 = this.S;
                                                        byte[] bArr10 = this.g0;
                                                        int i13 = this.f5090p;
                                                        this.f5090p = i13 + 1;
                                                        textBuffer.append((char) _decodeUTF8_2(i12, bArr10[i13]));
                                                        return this.Z == 45 ? _finishAposString() : _finishRegularString();
                                                    case 43:
                                                        int i14 = this.S;
                                                        int i15 = this.T;
                                                        byte[] bArr11 = this.g0;
                                                        int i16 = this.f5090p;
                                                        this.f5090p = i16 + 1;
                                                        return !_decodeSplitUTF8_3(i14, i15, bArr11[i16]) ? JsonToken.NOT_AVAILABLE : this.Z == 45 ? _finishAposString() : _finishRegularString();
                                                    case 44:
                                                        int i17 = this.S;
                                                        int i18 = this.T;
                                                        byte[] bArr12 = this.g0;
                                                        int i19 = this.f5090p;
                                                        this.f5090p = i19 + 1;
                                                        return !_decodeSplitUTF8_4(i17, i18, bArr12[i19]) ? JsonToken.NOT_AVAILABLE : this.Z == 45 ? _finishAposString() : _finishRegularString();
                                                    case 45:
                                                        return _finishAposString();
                                                    default:
                                                        switch (i2) {
                                                            case 50:
                                                                return A0();
                                                            case 51:
                                                                return _startSlashComment(this.S);
                                                            case 52:
                                                                return _finishCComment(this.S, true);
                                                            case 53:
                                                                return _finishCComment(this.S, false);
                                                            case 54:
                                                                return _finishCppComment(this.S);
                                                            case 55:
                                                                return _finishHashComment(this.S);
                                                            default:
                                                                VersionUtil.throwInternal();
                                                                return null;
                                                        }
                                                }
                                        }
                                }
                        }
                }
            }
        }
        return _finishBOM(this.S);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    protected final JsonToken O0() {
        JsonToken jsonToken = this.f5104c;
        int i2 = this.Y;
        if (i2 != 3 && i2 != 12) {
            if (i2 != 50) {
                switch (i2) {
                    case 16:
                        return G0("null", this.S, JsonToken.VALUE_NULL);
                    case 17:
                        return G0("true", this.S, JsonToken.VALUE_TRUE);
                    case 18:
                        return G0("false", this.S, JsonToken.VALUE_FALSE);
                    case 19:
                        return I0(this.b0, this.S);
                    default:
                        switch (i2) {
                            case 24:
                            case 25:
                                return y0(0, "0");
                            case 26:
                                int currentSegmentSize = this.z.getCurrentSegmentSize();
                                if (this.K) {
                                    currentSegmentSize--;
                                }
                                this.L = currentSegmentSize;
                                return x0(JsonToken.VALUE_NUMBER_INT);
                            default:
                                switch (i2) {
                                    case 30:
                                        this.N = 0;
                                        return x0(JsonToken.VALUE_NUMBER_FLOAT);
                                    case 31:
                                        p(": was expecting fraction after exponent marker", JsonToken.VALUE_NUMBER_FLOAT);
                                        p(": was expecting closing '*/' for comment", JsonToken.NOT_AVAILABLE);
                                        return k0();
                                    case 32:
                                        return x0(JsonToken.VALUE_NUMBER_FLOAT);
                                    default:
                                        switch (i2) {
                                            case 52:
                                            case 53:
                                                p(": was expecting closing '*/' for comment", JsonToken.NOT_AVAILABLE);
                                                break;
                                            case 54:
                                            case 55:
                                                break;
                                            default:
                                                p(": was expecting rest of token (internal state: " + this.Y + ")", this.f5104c);
                                                return jsonToken;
                                        }
                                        return k0();
                                }
                        }
                }
            }
            return B0();
        }
        return k0();
    }

    protected JsonToken P0(String str) {
        m("Unrecognized token '%s': was expecting %s", this.z.contentsAsString(), T());
        return JsonToken.NOT_AVAILABLE;
    }

    protected JsonToken Q0() {
        int i2 = this.f5090p;
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int min = Math.min(this.f5091q, emptyAndGetCurrentSegment.length + i2);
        byte[] bArr = this.g0;
        int i3 = 0;
        while (i2 < min) {
            int i4 = bArr[i2] & 255;
            if (i4 == 39) {
                this.f5090p = i2 + 1;
                this.z.setCurrentLength(i3);
                return x0(JsonToken.VALUE_STRING);
            } else if (iArr[i4] != 0) {
                break;
            } else {
                i2++;
                emptyAndGetCurrentSegment[i3] = (char) i4;
                i3++;
            }
        }
        this.z.setCurrentLength(i3);
        this.f5090p = i2;
        return _finishAposString();
    }

    protected JsonToken R0() {
        int i2;
        int i3 = this.f5090p;
        if (i3 + 4 < this.f5091q) {
            byte[] bArr = this.g0;
            int i4 = i3 + 1;
            if (bArr[i3] == 97) {
                int i5 = i4 + 1;
                if (bArr[i4] == 108) {
                    int i6 = i5 + 1;
                    if (bArr[i5] == 115) {
                        int i7 = i6 + 1;
                        if (bArr[i6] == 101 && ((i2 = bArr[i7] & 255) < 48 || i2 == 93 || i2 == 125)) {
                            this.f5090p = i7;
                            return x0(JsonToken.VALUE_FALSE);
                        }
                    }
                }
            }
        }
        this.Y = 18;
        return F0("false", 1, JsonToken.VALUE_FALSE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x004d, code lost:
        r2 = r2 & 255;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x004f, code lost:
        if (r9 != 0) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0051, code lost:
        D(r2, "Decimal point not followed by a digit");
     */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00f9  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x00ec -> B:47:0x00c3). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected JsonToken S0(char[] cArr, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7 = 0;
        if (i3 == 46) {
            if (i2 >= cArr.length) {
                cArr = this.z.expandCurrentSegment();
            }
            cArr[i2] = '.';
            i2++;
            i5 = 0;
            while (true) {
                int i8 = this.f5090p;
                if (i8 >= this.f5091q) {
                    this.z.setCurrentLength(i2);
                    this.Y = 30;
                    this.M = i5;
                    JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
                    this.f5104c = jsonToken;
                    return jsonToken;
                }
                byte[] bArr = this.g0;
                this.f5090p = i8 + 1;
                byte b2 = bArr[i8];
                if (b2 < 48 || b2 > 57) {
                    break;
                }
                if (i2 >= cArr.length) {
                    cArr = this.z.expandCurrentSegment();
                }
                cArr[i2] = (char) b2;
                i5++;
                i2++;
            }
        } else {
            i4 = i3;
            i5 = 0;
        }
        this.M = i5;
        if (i4 == 101 || i4 == 69) {
            if (i2 >= cArr.length) {
                cArr = this.z.expandCurrentSegment();
            }
            int i9 = i2 + 1;
            cArr[i2] = (char) i4;
            int i10 = this.f5090p;
            if (i10 >= this.f5091q) {
                this.z.setCurrentLength(i9);
                this.Y = 31;
                this.N = 0;
                JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken2;
                return jsonToken2;
            }
            byte[] bArr2 = this.g0;
            this.f5090p = i10 + 1;
            byte b3 = bArr2[i10];
            if (b3 == 45 || b3 == 43) {
                if (i9 >= cArr.length) {
                    cArr = this.z.expandCurrentSegment();
                }
                i6 = i9 + 1;
                cArr[i9] = (char) b3;
                int i11 = this.f5090p;
                if (i11 >= this.f5091q) {
                    this.z.setCurrentLength(i6);
                    this.Y = 32;
                    this.N = 0;
                    JsonToken jsonToken3 = JsonToken.NOT_AVAILABLE;
                    this.f5104c = jsonToken3;
                    return jsonToken3;
                }
                byte[] bArr3 = this.g0;
                this.f5090p = i11 + 1;
                b3 = bArr3[i11];
                i9 = i6;
            }
            if (b3 >= 48 || b3 > 57) {
                int i12 = b3 & 255;
                if (i7 == 0) {
                    D(i12, "Exponent indicator not followed by a digit");
                }
                i2 = i9;
            } else {
                i7++;
                if (i9 >= cArr.length) {
                    cArr = this.z.expandCurrentSegment();
                }
                i6 = i9 + 1;
                cArr[i9] = (char) b3;
                int i13 = this.f5090p;
                if (i13 >= this.f5091q) {
                    this.z.setCurrentLength(i6);
                    this.Y = 32;
                    this.N = i7;
                    JsonToken jsonToken4 = JsonToken.NOT_AVAILABLE;
                    this.f5104c = jsonToken4;
                    return jsonToken4;
                }
                byte[] bArr4 = this.g0;
                this.f5090p = i13 + 1;
                b3 = bArr4[i13];
                i9 = i6;
                if (b3 >= 48) {
                }
                int i122 = b3 & 255;
                if (i7 == 0) {
                }
                i2 = i9;
            }
        }
        this.f5090p--;
        this.z.setCurrentLength(i2);
        this.N = i7;
        return x0(JsonToken.VALUE_NUMBER_FLOAT);
    }

    protected JsonToken T0() {
        this.K = false;
        this.L = 0;
        return S0(this.z.emptyAndGetCurrentSegment(), 0, 46);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected JsonToken U0() {
        int i2;
        this.K = true;
        int i3 = this.f5090p;
        if (i3 >= this.f5091q) {
            this.Y = 23;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this.f5104c = jsonToken;
            return jsonToken;
        }
        byte[] bArr = this.g0;
        this.f5090p = i3 + 1;
        int i4 = bArr[i3] & 255;
        int i5 = 2;
        if (i4 > 48) {
            if (i4 > 57) {
                if (i4 == 73) {
                    return H0(3, 2);
                }
            }
            char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
            emptyAndGetCurrentSegment[0] = Soundex.SILENT_MARKER;
            emptyAndGetCurrentSegment[1] = (char) i4;
            i2 = this.f5090p;
            if (i2 < this.f5091q) {
                this.Y = 26;
                this.z.setCurrentLength(2);
                this.L = 1;
                JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
                this.f5104c = jsonToken2;
                return jsonToken2;
            }
            int i6 = this.g0[i2];
            while (true) {
                if (i6 < 48) {
                    if (i6 == 46) {
                        this.L = i5 - 1;
                        this.f5090p++;
                        return S0(emptyAndGetCurrentSegment, i5, i6);
                    }
                } else if (i6 <= 57) {
                    if (i5 >= emptyAndGetCurrentSegment.length) {
                        emptyAndGetCurrentSegment = this.z.expandCurrentSegment();
                    }
                    int i7 = i5 + 1;
                    emptyAndGetCurrentSegment[i5] = (char) i6;
                    int i8 = this.f5090p + 1;
                    this.f5090p = i8;
                    if (i8 >= this.f5091q) {
                        this.Y = 26;
                        this.z.setCurrentLength(i7);
                        JsonToken jsonToken3 = JsonToken.NOT_AVAILABLE;
                        this.f5104c = jsonToken3;
                        return jsonToken3;
                    }
                    i6 = this.g0[i8] & 255;
                    i5 = i7;
                } else if (i6 == 101 || i6 == 69) {
                    this.L = i5 - 1;
                    this.f5090p++;
                    return S0(emptyAndGetCurrentSegment, i5, i6);
                }
            }
            this.L = i5 - 1;
            this.z.setCurrentLength(i5);
            return x0(JsonToken.VALUE_NUMBER_INT);
        } else if (i4 == 48) {
            return K0();
        }
        D(i4, "expected digit (0-9) to follow minus sign, for valid numeric value");
        char[] emptyAndGetCurrentSegment2 = this.z.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment2[0] = Soundex.SILENT_MARKER;
        emptyAndGetCurrentSegment2[1] = (char) i4;
        i2 = this.f5090p;
        if (i2 < this.f5091q) {
        }
    }

    protected JsonToken V0() {
        int i2;
        int i3 = this.f5090p;
        if (i3 + 3 < this.f5091q) {
            byte[] bArr = this.g0;
            int i4 = i3 + 1;
            if (bArr[i3] == 117) {
                int i5 = i4 + 1;
                if (bArr[i4] == 108) {
                    int i6 = i5 + 1;
                    if (bArr[i5] == 108 && ((i2 = bArr[i6] & 255) < 48 || i2 == 93 || i2 == 125)) {
                        this.f5090p = i6;
                        return x0(JsonToken.VALUE_NULL);
                    }
                }
            }
        }
        this.Y = 16;
        return F0("null", 1, JsonToken.VALUE_NULL);
    }

    protected JsonToken W0() {
        int i2 = this.f5090p;
        if (i2 >= this.f5091q) {
            this.Y = 24;
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this.f5104c = jsonToken;
            return jsonToken;
        }
        int i3 = i2 + 1;
        int i4 = this.g0[i2] & 255;
        if (i4 < 48) {
            if (i4 == 46) {
                this.f5090p = i3;
                this.L = 1;
                char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
                emptyAndGetCurrentSegment[0] = '0';
                return S0(emptyAndGetCurrentSegment, 1, i4);
            }
        } else if (i4 <= 57) {
            return L0();
        } else {
            if (i4 == 101 || i4 == 69) {
                this.f5090p = i3;
                this.L = 1;
                char[] emptyAndGetCurrentSegment2 = this.z.emptyAndGetCurrentSegment();
                emptyAndGetCurrentSegment2[0] = '0';
                return S0(emptyAndGetCurrentSegment2, 1, i4);
            } else if (i4 != 93 && i4 != 125) {
                D(i4, "expected digit (0-9), decimal point (.) or exponent indicator (e/E) to follow '0'");
            }
        }
        return y0(0, "0");
    }

    protected JsonToken X0(int i2) {
        this.K = false;
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        emptyAndGetCurrentSegment[0] = (char) i2;
        int i3 = this.f5090p;
        if (i3 >= this.f5091q) {
            this.Y = 26;
            this.z.setCurrentLength(1);
            JsonToken jsonToken = JsonToken.NOT_AVAILABLE;
            this.f5104c = jsonToken;
            return jsonToken;
        }
        int i4 = this.g0[i3] & 255;
        int i5 = 1;
        while (true) {
            if (i4 < 48) {
                if (i4 == 46) {
                    this.L = i5;
                    this.f5090p++;
                    return S0(emptyAndGetCurrentSegment, i5, i4);
                }
            } else if (i4 <= 57) {
                if (i5 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this.z.expandCurrentSegment();
                }
                int i6 = i5 + 1;
                emptyAndGetCurrentSegment[i5] = (char) i4;
                int i7 = this.f5090p + 1;
                this.f5090p = i7;
                if (i7 >= this.f5091q) {
                    this.Y = 26;
                    this.z.setCurrentLength(i6);
                    JsonToken jsonToken2 = JsonToken.NOT_AVAILABLE;
                    this.f5104c = jsonToken2;
                    return jsonToken2;
                }
                i4 = this.g0[i7] & 255;
                i5 = i6;
            } else if (i4 == 101 || i4 == 69) {
                this.L = i5;
                this.f5090p++;
                return S0(emptyAndGetCurrentSegment, i5, i4);
            }
        }
        this.L = i5;
        this.z.setCurrentLength(i5);
        return x0(JsonToken.VALUE_NUMBER_INT);
    }

    protected JsonToken Y0() {
        int i2 = this.f5090p;
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        int[] iArr = _icUTF8;
        int min = Math.min(this.f5091q, emptyAndGetCurrentSegment.length + i2);
        byte[] bArr = this.g0;
        int i3 = 0;
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
                return x0(JsonToken.VALUE_STRING);
            }
        }
        this.z.setCurrentLength(i3);
        this.f5090p = i2;
        return _finishRegularString();
    }

    protected JsonToken Z0() {
        int i2;
        int i3 = this.f5090p;
        if (i3 + 3 < this.f5091q) {
            byte[] bArr = this.g0;
            int i4 = i3 + 1;
            if (bArr[i3] == 114) {
                int i5 = i4 + 1;
                if (bArr[i4] == 117) {
                    int i6 = i5 + 1;
                    if (bArr[i5] == 101 && ((i2 = bArr[i6] & 255) < 48 || i2 == 93 || i2 == 125)) {
                        this.f5090p = i6;
                        return x0(JsonToken.VALUE_TRUE);
                    }
                }
            }
        }
        this.Y = 17;
        return F0("true", 1, JsonToken.VALUE_TRUE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x001b, code lost:
        if (r4 != 44) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x002a, code lost:
        if (r2.x.inArray() == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0033, code lost:
        if (r2.x.inRoot() != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x003a, code lost:
        if ((r2.f5048a & com.fasterxml.jackson.core.json.async.NonBlockingJsonParser.FEAT_MASK_ALLOW_MISSING) == 0) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x003c, code lost:
        r2.f5090p--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0047, code lost:
        return x0(com.fasterxml.jackson.core.JsonToken.VALUE_NULL);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected JsonToken a1(boolean z, int i2) {
        if (i2 != 39) {
            if (i2 == 73) {
                return H0(1, 1);
            }
            if (i2 == 78) {
                return H0(0, 1);
            }
            if (i2 != 93) {
                if (i2 != 125) {
                    if (i2 == 43) {
                        return H0(2, 1);
                    }
                }
            }
        } else if ((this.f5048a & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0) {
            return Q0();
        }
        s(i2, "expected a valid value " + U());
        return null;
    }

    @Override // com.fasterxml.jackson.core.async.NonBlockingInputFeeder
    public void endOfInput() {
        this.a0 = true;
    }

    @Override // com.fasterxml.jackson.core.async.ByteArrayFeeder
    public void feedInput(byte[] bArr, int i2, int i3) {
        int i4 = this.f5090p;
        int i5 = this.f5091q;
        if (i4 < i5) {
            l("Still have %d undecoded bytes, should not call 'feedInput'", Integer.valueOf(i5 - i4));
        }
        if (i3 < i2) {
            m("Input end (%d) may not be before start (%d)", Integer.valueOf(i3), Integer.valueOf(i2));
        }
        if (this.a0) {
            k("Already closed, can not feed more input");
        }
        this.f5092r += this.h0;
        this.f5094t = i2 - (this.f5091q - this.f5094t);
        this.c0 = i2;
        this.g0 = bArr;
        this.f5090p = i2;
        this.f5091q = i3;
        this.h0 = i3 - i2;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ByteArrayFeeder getNonBlockingInputFeeder() {
        return this;
    }

    @Override // com.fasterxml.jackson.core.async.NonBlockingInputFeeder
    public final boolean needMoreInput() {
        return this.f5090p >= this.f5091q && !this.a0;
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() {
        int i2 = this.f5090p;
        if (i2 >= this.f5091q) {
            if (this.f5089o) {
                return null;
            }
            return this.a0 ? this.f5104c == JsonToken.NOT_AVAILABLE ? O0() : k0() : JsonToken.NOT_AVAILABLE;
        } else if (this.f5104c == JsonToken.NOT_AVAILABLE) {
            return N0();
        } else {
            this.E = 0;
            this.u = this.f5092r + i2;
            this.D = null;
            byte[] bArr = this.g0;
            this.f5090p = i2 + 1;
            int i3 = bArr[i2] & 255;
            switch (this.W) {
                case 0:
                    return _startDocument(i3);
                case 1:
                    return _startValue(i3);
                case 2:
                    return _startFieldName(i3);
                case 3:
                    return _startFieldNameAfterComma(i3);
                case 4:
                    return _startValueExpectColon(i3);
                case 5:
                    return _startValue(i3);
                case 6:
                    return _startValueExpectComma(i3);
                default:
                    VersionUtil.throwInternal();
                    return null;
            }
        }
    }

    @Override // com.fasterxml.jackson.core.json.async.NonBlockingJsonParserBase, com.fasterxml.jackson.core.JsonParser
    public int releaseBuffered(OutputStream outputStream) {
        int i2 = this.f5091q;
        int i3 = this.f5090p;
        int i4 = i2 - i3;
        if (i4 > 0) {
            outputStream.write(this.g0, i3, i4);
        }
        return i4;
    }
}
