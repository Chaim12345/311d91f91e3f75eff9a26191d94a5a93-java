package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.MergedStream;
import com.fasterxml.jackson.core.io.UTF32Reader;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.google.common.base.Ascii;
import java.io.ByteArrayInputStream;
import java.io.CharConversionException;
import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
/* loaded from: classes.dex */
public final class ByteSourceJsonBootstrapper {
    public static final byte UTF8_BOM_1 = -17;
    public static final byte UTF8_BOM_2 = -69;
    public static final byte UTF8_BOM_3 = -65;
    private boolean _bigEndian;
    private final boolean _bufferRecyclable;
    private int _bytesPerChar;
    private final IOContext _context;
    private final InputStream _in;
    private final byte[] _inputBuffer;
    private int _inputEnd;
    private int _inputPtr;

    public ByteSourceJsonBootstrapper(IOContext iOContext, InputStream inputStream) {
        this._bigEndian = true;
        this._context = iOContext;
        this._in = inputStream;
        this._inputBuffer = iOContext.allocReadIOBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._bufferRecyclable = true;
    }

    public ByteSourceJsonBootstrapper(IOContext iOContext, byte[] bArr, int i2, int i3) {
        this._bigEndian = true;
        this._context = iOContext;
        this._in = null;
        this._inputBuffer = bArr;
        this._inputPtr = i2;
        this._inputEnd = i2 + i3;
        this._bufferRecyclable = false;
    }

    private boolean checkUTF16(int i2) {
        if ((65280 & i2) == 0) {
            this._bigEndian = true;
        } else if ((i2 & 255) != 0) {
            return false;
        } else {
            this._bigEndian = false;
        }
        this._bytesPerChar = 2;
        return true;
    }

    private boolean checkUTF32(int i2) {
        String str;
        if ((i2 >> 8) == 0) {
            this._bigEndian = true;
        } else if ((16777215 & i2) == 0) {
            this._bigEndian = false;
        } else {
            if (((-16711681) & i2) == 0) {
                str = "3412";
            } else if ((i2 & (-65281)) != 0) {
                return false;
            } else {
                str = "2143";
            }
            reportWeirdUCS4(str);
        }
        this._bytesPerChar = 4;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean handleBOM(int i2) {
        int i3;
        String str;
        if (i2 != -16842752) {
            if (i2 == -131072) {
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                this._bigEndian = false;
                return true;
            } else if (i2 == 65279) {
                this._bigEndian = true;
                this._inputPtr += 4;
                this._bytesPerChar = 4;
                return true;
            } else {
                str = i2 == 65534 ? "2143" : "3412";
                i3 = i2 >>> 16;
                if (i3 != 65279) {
                    this._inputPtr += 2;
                    this._bytesPerChar = 2;
                    this._bigEndian = true;
                    return true;
                } else if (i3 == 65534) {
                    this._inputPtr += 2;
                    this._bytesPerChar = 2;
                    this._bigEndian = false;
                    return true;
                } else if ((i2 >>> 8) == 15711167) {
                    this._inputPtr += 3;
                    this._bytesPerChar = 1;
                    this._bigEndian = true;
                    return true;
                } else {
                    return false;
                }
            }
        }
        reportWeirdUCS4(str);
        i3 = i2 >>> 16;
        if (i3 != 65279) {
        }
    }

    public static MatchStrength hasJSONFormat(InputAccessor inputAccessor) {
        String str;
        if (inputAccessor.hasMoreBytes()) {
            byte nextByte = inputAccessor.nextByte();
            if (nextByte == -17) {
                if (!inputAccessor.hasMoreBytes()) {
                    return MatchStrength.INCONCLUSIVE;
                }
                if (inputAccessor.nextByte() != -69) {
                    return MatchStrength.NO_MATCH;
                }
                if (!inputAccessor.hasMoreBytes()) {
                    return MatchStrength.INCONCLUSIVE;
                }
                if (inputAccessor.nextByte() != -65) {
                    return MatchStrength.NO_MATCH;
                }
                if (!inputAccessor.hasMoreBytes()) {
                    return MatchStrength.INCONCLUSIVE;
                }
                nextByte = inputAccessor.nextByte();
            }
            int skipSpace = skipSpace(inputAccessor, nextByte);
            if (skipSpace < 0) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (skipSpace == 123) {
                int skipSpace2 = skipSpace(inputAccessor);
                return skipSpace2 < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace2 == 34 || skipSpace2 == 125) ? MatchStrength.SOLID_MATCH : MatchStrength.NO_MATCH;
            } else if (skipSpace == 91) {
                int skipSpace3 = skipSpace(inputAccessor);
                return skipSpace3 < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace3 == 93 || skipSpace3 == 91) ? MatchStrength.SOLID_MATCH : MatchStrength.SOLID_MATCH;
            } else {
                MatchStrength matchStrength = MatchStrength.WEAK_MATCH;
                if (skipSpace == 34) {
                    return matchStrength;
                }
                if (skipSpace > 57 || skipSpace < 48) {
                    if (skipSpace == 45) {
                        int skipSpace4 = skipSpace(inputAccessor);
                        return skipSpace4 < 0 ? MatchStrength.INCONCLUSIVE : (skipSpace4 > 57 || skipSpace4 < 48) ? MatchStrength.NO_MATCH : matchStrength;
                    }
                    if (skipSpace == 110) {
                        str = "ull";
                    } else if (skipSpace == 116) {
                        str = "rue";
                    } else if (skipSpace != 102) {
                        return MatchStrength.NO_MATCH;
                    } else {
                        str = "alse";
                    }
                    return tryMatch(inputAccessor, str, matchStrength);
                }
                return matchStrength;
            }
        }
        return MatchStrength.INCONCLUSIVE;
    }

    private void reportWeirdUCS4(String str) {
        throw new CharConversionException("Unsupported UCS-4 endianness (" + str + ") detected");
    }

    private static int skipSpace(InputAccessor inputAccessor) {
        if (inputAccessor.hasMoreBytes()) {
            return skipSpace(inputAccessor, inputAccessor.nextByte());
        }
        return -1;
    }

    private static int skipSpace(InputAccessor inputAccessor, byte b2) {
        while (true) {
            int i2 = b2 & 255;
            if (i2 != 32 && i2 != 13 && i2 != 10 && i2 != 9) {
                return i2;
            }
            if (!inputAccessor.hasMoreBytes()) {
                return -1;
            }
            b2 = inputAccessor.nextByte();
        }
    }

    public static int skipUTF8BOM(DataInput dataInput) {
        int readUnsignedByte = dataInput.readUnsignedByte();
        if (readUnsignedByte != 239) {
            return readUnsignedByte;
        }
        int readUnsignedByte2 = dataInput.readUnsignedByte();
        if (readUnsignedByte2 != 187) {
            throw new IOException("Unexpected byte 0x" + Integer.toHexString(readUnsignedByte2) + " following 0xEF; should get 0xBB as part of UTF-8 BOM");
        }
        int readUnsignedByte3 = dataInput.readUnsignedByte();
        if (readUnsignedByte3 == 191) {
            return dataInput.readUnsignedByte();
        }
        throw new IOException("Unexpected byte 0x" + Integer.toHexString(readUnsignedByte3) + " following 0xEF 0xBB; should get 0xBF as part of UTF-8 BOM");
    }

    private static MatchStrength tryMatch(InputAccessor inputAccessor, String str, MatchStrength matchStrength) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            if (!inputAccessor.hasMoreBytes()) {
                return MatchStrength.INCONCLUSIVE;
            }
            if (inputAccessor.nextByte() != str.charAt(i2)) {
                return MatchStrength.NO_MATCH;
            }
        }
        return matchStrength;
    }

    protected boolean a(int i2) {
        int read;
        int i3 = this._inputEnd - this._inputPtr;
        while (i3 < i2) {
            InputStream inputStream = this._in;
            if (inputStream == null) {
                read = -1;
            } else {
                byte[] bArr = this._inputBuffer;
                int i4 = this._inputEnd;
                read = inputStream.read(bArr, i4, bArr.length - i4);
            }
            if (read < 1) {
                return false;
            }
            this._inputEnd += read;
            i3 += read;
        }
        return true;
    }

    public JsonParser constructParser(int i2, ObjectCodec objectCodec, ByteQuadsCanonicalizer byteQuadsCanonicalizer, CharsToNameCanonicalizer charsToNameCanonicalizer, int i3) {
        int i4 = this._inputPtr;
        JsonEncoding detectEncoding = detectEncoding();
        int i5 = this._inputPtr - i4;
        if (detectEncoding == JsonEncoding.UTF8 && JsonFactory.Feature.CANONICALIZE_FIELD_NAMES.enabledIn(i3)) {
            return new UTF8StreamJsonParser(this._context, i2, this._in, objectCodec, byteQuadsCanonicalizer.makeChild(i3), this._inputBuffer, this._inputPtr, this._inputEnd, i5, this._bufferRecyclable);
        }
        return new ReaderBasedJsonParser(this._context, i2, constructReader(), objectCodec, charsToNameCanonicalizer.makeChild(i3));
    }

    public Reader constructReader() {
        JsonEncoding encoding = this._context.getEncoding();
        int bits = encoding.bits();
        if (bits != 8 && bits != 16) {
            if (bits == 32) {
                IOContext iOContext = this._context;
                return new UTF32Reader(iOContext, this._in, this._inputBuffer, this._inputPtr, this._inputEnd, iOContext.getEncoding().isBigEndian());
            }
            throw new RuntimeException("Internal error");
        }
        InputStream inputStream = this._in;
        if (inputStream == null) {
            inputStream = new ByteArrayInputStream(this._inputBuffer, this._inputPtr, this._inputEnd);
        } else if (this._inputPtr < this._inputEnd) {
            inputStream = new MergedStream(this._context, inputStream, this._inputBuffer, this._inputPtr, this._inputEnd);
        }
        return new InputStreamReader(inputStream, encoding.getJavaName());
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x003f, code lost:
        if (checkUTF16(r1 >>> 16) != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x005c, code lost:
        if (checkUTF16((r1[r5 + 1] & 255) | ((r1[r5] & 255) << 8)) != false) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public JsonEncoding detectEncoding() {
        int i2;
        JsonEncoding jsonEncoding;
        boolean z = false;
        if (a(4)) {
            byte[] bArr = this._inputBuffer;
            int i3 = this._inputPtr;
            int i4 = (bArr[i3 + 3] & 255) | (bArr[i3] << Ascii.CAN) | ((bArr[i3 + 1] & 255) << 16) | ((bArr[i3 + 2] & 255) << 8);
            if (!handleBOM(i4)) {
                if (!checkUTF32(i4)) {
                }
            }
            z = true;
        } else if (a(2)) {
            byte[] bArr2 = this._inputBuffer;
            int i5 = this._inputPtr;
        }
        if (!z || (i2 = this._bytesPerChar) == 1) {
            jsonEncoding = JsonEncoding.UTF8;
        } else if (i2 == 2) {
            jsonEncoding = this._bigEndian ? JsonEncoding.UTF16_BE : JsonEncoding.UTF16_LE;
        } else if (i2 != 4) {
            throw new RuntimeException("Internal error");
        } else {
            jsonEncoding = this._bigEndian ? JsonEncoding.UTF32_BE : JsonEncoding.UTF32_LE;
        }
        this._context.setEncoding(jsonEncoding);
        return jsonEncoding;
    }
}
