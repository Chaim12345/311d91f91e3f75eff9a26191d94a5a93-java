package com.airbnb.lottie.parser.moshi;

import androidx.annotation.Nullable;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import java.io.EOFException;
import java.util.Objects;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.apache.http.message.TokenParser;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class JsonUtf8Reader extends JsonReader {
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_BUFFERED_NAME = 15;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 18;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 16;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 17;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final Buffer buffer;
    private int peeked = 0;
    private long peekedLong;
    private int peekedNumberLength;
    @Nullable
    private String peekedString;
    private final BufferedSource source;
    private static final ByteString SINGLE_QUOTE_OR_SLASH = ByteString.encodeUtf8("'\\");
    private static final ByteString DOUBLE_QUOTE_OR_SLASH = ByteString.encodeUtf8(BasicHeaderValueFormatter.UNSAFE_CHARS);
    private static final ByteString UNQUOTED_STRING_TERMINALS = ByteString.encodeUtf8("{}[]:, \n\t\r\f/\\;#=");
    private static final ByteString LINEFEED_OR_CARRIAGE_RETURN = ByteString.encodeUtf8("\n\r");
    private static final ByteString CLOSING_BLOCK_COMMENT = ByteString.encodeUtf8("*/");

    /* JADX INFO: Access modifiers changed from: package-private */
    public JsonUtf8Reader(BufferedSource bufferedSource) {
        Objects.requireNonNull(bufferedSource, "source == null");
        this.source = bufferedSource;
        this.buffer = bufferedSource.buffer();
        b(6);
    }

    private void checkLenient() {
        if (!this.f4454e) {
            throw c("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private int doPeek() {
        int i2;
        int[] iArr = this.f4451b;
        int i3 = this.f4450a;
        int i4 = iArr[i3 - 1];
        if (i4 == 1) {
            iArr[i3 - 1] = 2;
        } else if (i4 != 2) {
            if (i4 == 3 || i4 == 5) {
                iArr[i3 - 1] = 4;
                if (i4 == 5) {
                    int nextNonWhitespace = nextNonWhitespace(true);
                    this.buffer.readByte();
                    if (nextNonWhitespace != 44) {
                        if (nextNonWhitespace != 59) {
                            if (nextNonWhitespace != 125) {
                                throw c("Unterminated object");
                            }
                            i2 = 2;
                        } else {
                            checkLenient();
                        }
                    }
                }
                int nextNonWhitespace2 = nextNonWhitespace(true);
                if (nextNonWhitespace2 == 34) {
                    this.buffer.readByte();
                    i2 = 13;
                } else if (nextNonWhitespace2 == 39) {
                    this.buffer.readByte();
                    checkLenient();
                    i2 = 12;
                } else if (nextNonWhitespace2 != 125) {
                    checkLenient();
                    if (!isLiteral((char) nextNonWhitespace2)) {
                        throw c("Expected name");
                    }
                    i2 = 14;
                } else if (i4 == 5) {
                    throw c("Expected name");
                } else {
                    this.buffer.readByte();
                    i2 = 2;
                }
            } else if (i4 == 4) {
                iArr[i3 - 1] = 5;
                int nextNonWhitespace3 = nextNonWhitespace(true);
                this.buffer.readByte();
                if (nextNonWhitespace3 != 58) {
                    if (nextNonWhitespace3 != 61) {
                        throw c("Expected ':'");
                    }
                    checkLenient();
                    if (this.source.request(1L) && this.buffer.getByte(0L) == 62) {
                        this.buffer.readByte();
                    }
                }
            } else if (i4 == 6) {
                iArr[i3 - 1] = 7;
            } else if (i4 == 7) {
                if (nextNonWhitespace(false) == -1) {
                    i2 = 18;
                }
                checkLenient();
            } else if (i4 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
            this.peeked = i2;
            return i2;
        } else {
            int nextNonWhitespace4 = nextNonWhitespace(true);
            this.buffer.readByte();
            if (nextNonWhitespace4 != 44) {
                if (nextNonWhitespace4 != 59) {
                    if (nextNonWhitespace4 == 93) {
                        this.peeked = 4;
                        return 4;
                    }
                    throw c("Unterminated array");
                }
                checkLenient();
            }
        }
        int nextNonWhitespace5 = nextNonWhitespace(true);
        if (nextNonWhitespace5 != 34) {
            if (nextNonWhitespace5 == 39) {
                checkLenient();
                this.buffer.readByte();
                this.peeked = 8;
                return 8;
            }
            if (nextNonWhitespace5 != 44 && nextNonWhitespace5 != 59) {
                if (nextNonWhitespace5 == 91) {
                    this.buffer.readByte();
                    this.peeked = 3;
                    return 3;
                } else if (nextNonWhitespace5 != 93) {
                    if (nextNonWhitespace5 == 123) {
                        this.buffer.readByte();
                        this.peeked = 1;
                        return 1;
                    }
                    int peekKeyword = peekKeyword();
                    if (peekKeyword != 0) {
                        return peekKeyword;
                    }
                    int peekNumber = peekNumber();
                    if (peekNumber != 0) {
                        return peekNumber;
                    }
                    if (!isLiteral(this.buffer.getByte(0L))) {
                        throw c("Expected value");
                    }
                    checkLenient();
                    i2 = 10;
                } else if (i4 == 1) {
                    this.buffer.readByte();
                    i2 = 4;
                }
            }
            if (i4 == 1 || i4 == 2) {
                checkLenient();
                this.peeked = 7;
                return 7;
            }
            throw c("Unexpected value");
        }
        this.buffer.readByte();
        i2 = 9;
        this.peeked = i2;
        return i2;
    }

    private int findName(String str, JsonReader.Options options) {
        int length = options.f4456a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(options.f4456a[i2])) {
                this.peeked = 0;
                this.f4452c[this.f4450a - 1] = str;
                return i2;
            }
        }
        return -1;
    }

    private int findString(String str, JsonReader.Options options) {
        int length = options.f4456a.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(options.f4456a[i2])) {
                this.peeked = 0;
                int[] iArr = this.f4453d;
                int i3 = this.f4450a - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
        }
        return -1;
    }

    private boolean isLiteral(int i2) {
        if (i2 == 9 || i2 == 10 || i2 == 12 || i2 == 13 || i2 == 32) {
            return false;
        }
        if (i2 != 35) {
            if (i2 == 44) {
                return false;
            }
            if (i2 != 47 && i2 != 61) {
                if (i2 == 123 || i2 == 125 || i2 == 58) {
                    return false;
                }
                if (i2 != 59) {
                    switch (i2) {
                        case 91:
                        case 93:
                            return false;
                        case 92:
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        checkLenient();
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
        r6.buffer.skip(r3 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
        if (r1 != 47) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
        if (r6.source.request(2) != false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003b, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
        checkLenient();
        r3 = r6.buffer.getByte(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
        if (r3 == 42) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004b, code lost:
        if (r3 == 47) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004d, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004e, code lost:
        r6.buffer.readByte();
        r6.buffer.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0058, code lost:
        skipToEndOfLine();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x005c, code lost:
        r6.buffer.readByte();
        r6.buffer.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x006a, code lost:
        if (skipToEndOfBlockComment() == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0073, code lost:
        throw c("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0076, code lost:
        if (r1 != 35) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0078, code lost:
        checkLenient();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x007c, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int nextNonWhitespace(boolean z) {
        while (true) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (!this.source.request(i3)) {
                    if (z) {
                        throw new EOFException("End of input");
                    }
                    return -1;
                }
                byte b2 = this.buffer.getByte(i2);
                if (b2 != 10 && b2 != 32 && b2 != 13 && b2 != 9) {
                    break;
                }
                i2 = i3;
            }
        }
    }

    private String nextQuotedValue(ByteString byteString) {
        StringBuilder sb = null;
        while (true) {
            long indexOfElement = this.source.indexOfElement(byteString);
            if (indexOfElement == -1) {
                throw c("Unterminated string");
            }
            if (this.buffer.getByte(indexOfElement) != 92) {
                String readUtf8 = this.buffer.readUtf8(indexOfElement);
                if (sb == null) {
                    this.buffer.readByte();
                    return readUtf8;
                }
                sb.append(readUtf8);
                this.buffer.readByte();
                return sb.toString();
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(this.buffer.readUtf8(indexOfElement));
            this.buffer.readByte();
            sb.append(readEscapeCharacter());
        }
    }

    private String nextUnquotedValue() {
        long indexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        return indexOfElement != -1 ? this.buffer.readUtf8(indexOfElement) : this.buffer.readUtf8();
    }

    private int peekKeyword() {
        int i2;
        String str;
        String str2;
        byte b2 = this.buffer.getByte(0L);
        if (b2 == 116 || b2 == 84) {
            i2 = 5;
            str = "true";
            str2 = "TRUE";
        } else if (b2 == 102 || b2 == 70) {
            i2 = 6;
            str = "false";
            str2 = "FALSE";
        } else if (b2 != 110 && b2 != 78) {
            return 0;
        } else {
            i2 = 7;
            str = "null";
            str2 = "NULL";
        }
        int length = str.length();
        int i3 = 1;
        while (i3 < length) {
            int i4 = i3 + 1;
            if (!this.source.request(i4)) {
                return 0;
            }
            byte b3 = this.buffer.getByte(i3);
            if (b3 != str.charAt(i3) && b3 != str2.charAt(i3)) {
                return 0;
            }
            i3 = i4;
        }
        if (this.source.request(length + 1) && isLiteral(this.buffer.getByte(length))) {
            return 0;
        }
        this.buffer.skip(length);
        this.peeked = i2;
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0082, code lost:
        if (isLiteral(r11) != false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0084, code lost:
        if (r6 != 2) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0086, code lost:
        if (r7 == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x008c, code lost:
        if (r8 != Long.MIN_VALUE) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
        if (r10 == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0092, code lost:
        if (r8 != 0) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0094, code lost:
        if (r10 != false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0096, code lost:
        if (r10 == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0099, code lost:
        r8 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009a, code lost:
        r16.peekedLong = r8;
        r16.buffer.skip(r5);
        r1 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a4, code lost:
        r16.peeked = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00a6, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a7, code lost:
        if (r6 == 2) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00aa, code lost:
        if (r6 == 4) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00ad, code lost:
        if (r6 != 7) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00b0, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00b2, code lost:
        r16.peekedNumberLength = r5;
        r1 = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00b7, code lost:
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int peekNumber() {
        char c2;
        char c3 = 1;
        int i2 = 0;
        long j2 = 0;
        boolean z = true;
        int i3 = 0;
        char c4 = 0;
        boolean z2 = false;
        while (true) {
            int i4 = i3 + 1;
            if (!this.source.request(i4)) {
                break;
            }
            byte b2 = this.buffer.getByte(i3);
            if (b2 != 43) {
                if (b2 == 69 || b2 == 101) {
                    if (c4 != 2 && c4 != 4) {
                        return i2;
                    }
                    c4 = 5;
                } else if (b2 == 45) {
                    c2 = 6;
                    if (c4 == 0) {
                        c4 = 1;
                        z2 = true;
                    } else if (c4 != 5) {
                        return i2;
                    }
                } else if (b2 == 46) {
                    c2 = 3;
                    if (c4 != 2) {
                        return i2;
                    }
                } else if (b2 < 48 || b2 > 57) {
                    break;
                } else {
                    if (c4 == c3 || c4 == 0) {
                        j2 = -(b2 - 48);
                        c4 = 2;
                    } else if (c4 == 2) {
                        if (j2 == 0) {
                            return i2;
                        }
                        long j3 = (10 * j2) - (b2 - 48);
                        int i5 = (j2 > (-922337203685477580L) ? 1 : (j2 == (-922337203685477580L) ? 0 : -1));
                        z &= i5 > 0 || (i5 == 0 && j3 < j2);
                        j2 = j3;
                    } else if (c4 == 3) {
                        i2 = 0;
                        c4 = 4;
                    } else if (c4 == 5 || c4 == 6) {
                        i2 = 0;
                        c4 = 7;
                    }
                    i2 = 0;
                }
                i3 = i4;
                c3 = 1;
            } else {
                c2 = 6;
                if (c4 != 5) {
                    return i2;
                }
            }
            c4 = c2;
            i3 = i4;
            c3 = 1;
        }
    }

    private char readEscapeCharacter() {
        int i2;
        int i3;
        if (this.source.request(1L)) {
            byte readByte = this.buffer.readByte();
            if (readByte == 10 || readByte == 34 || readByte == 39 || readByte == 47 || readByte == 92) {
                return (char) readByte;
            }
            if (readByte != 98) {
                if (readByte != 102) {
                    if (readByte != 110) {
                        if (readByte != 114) {
                            if (readByte != 116) {
                                if (readByte != 117) {
                                    if (this.f4454e) {
                                        return (char) readByte;
                                    }
                                    throw c("Invalid escape sequence: \\" + ((char) readByte));
                                } else if (!this.source.request(4L)) {
                                    throw new EOFException("Unterminated escape sequence at path " + getPath());
                                } else {
                                    char c2 = 0;
                                    for (int i4 = 0; i4 < 4; i4++) {
                                        byte b2 = this.buffer.getByte(i4);
                                        char c3 = (char) (c2 << 4);
                                        if (b2 < 48 || b2 > 57) {
                                            if (b2 >= 97 && b2 <= 102) {
                                                i2 = b2 - 97;
                                            } else if (b2 < 65 || b2 > 70) {
                                                throw c("\\u" + this.buffer.readUtf8(4L));
                                            } else {
                                                i2 = b2 + ByteSourceJsonBootstrapper.UTF8_BOM_3;
                                            }
                                            i3 = i2 + 10;
                                        } else {
                                            i3 = b2 - 48;
                                        }
                                        c2 = (char) (c3 + i3);
                                    }
                                    this.buffer.skip(4L);
                                    return c2;
                                }
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
        throw c("Unterminated escape sequence");
    }

    private void skipQuotedValue(ByteString byteString) {
        while (true) {
            long indexOfElement = this.source.indexOfElement(byteString);
            if (indexOfElement == -1) {
                throw c("Unterminated string");
            }
            if (this.buffer.getByte(indexOfElement) != 92) {
                this.buffer.skip(indexOfElement + 1);
                return;
            } else {
                this.buffer.skip(indexOfElement + 1);
                readEscapeCharacter();
            }
        }
    }

    private boolean skipToEndOfBlockComment() {
        BufferedSource bufferedSource = this.source;
        ByteString byteString = CLOSING_BLOCK_COMMENT;
        long indexOf = bufferedSource.indexOf(byteString);
        boolean z = indexOf != -1;
        Buffer buffer = this.buffer;
        buffer.skip(z ? indexOf + byteString.size() : buffer.size());
        return z;
    }

    private void skipToEndOfLine() {
        long indexOfElement = this.source.indexOfElement(LINEFEED_OR_CARRIAGE_RETURN);
        Buffer buffer = this.buffer;
        buffer.skip(indexOfElement != -1 ? indexOfElement + 1 : buffer.size());
    }

    private void skipUnquotedValue() {
        long indexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        Buffer buffer = this.buffer;
        if (indexOfElement == -1) {
            indexOfElement = buffer.size();
        }
        buffer.skip(indexOfElement);
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void beginArray() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 3) {
            b(1);
            this.f4453d[this.f4450a - 1] = 0;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_ARRAY but was " + peek() + " at path " + getPath());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void beginObject() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 1) {
            b(3);
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + peek() + " at path " + getPath());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.peeked = 0;
        this.f4451b[0] = 8;
        this.f4450a = 1;
        this.buffer.clear();
        this.source.close();
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void endArray() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 != 4) {
            throw new JsonDataException("Expected END_ARRAY but was " + peek() + " at path " + getPath());
        }
        int i3 = this.f4450a - 1;
        this.f4450a = i3;
        int[] iArr = this.f4453d;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.peeked = 0;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void endObject() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 != 2) {
            throw new JsonDataException("Expected END_OBJECT but was " + peek() + " at path " + getPath());
        }
        int i3 = this.f4450a - 1;
        this.f4450a = i3;
        this.f4452c[i3] = null;
        int[] iArr = this.f4453d;
        int i4 = i3 - 1;
        iArr[i4] = iArr[i4] + 1;
        this.peeked = 0;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean hasNext() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        return (i2 == 2 || i2 == 4 || i2 == 18) ? false : true;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean nextBoolean() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 5) {
            this.peeked = 0;
            int[] iArr = this.f4453d;
            int i3 = this.f4450a - 1;
            iArr[i3] = iArr[i3] + 1;
            return true;
        } else if (i2 == 6) {
            this.peeked = 0;
            int[] iArr2 = this.f4453d;
            int i4 = this.f4450a - 1;
            iArr2[i4] = iArr2[i4] + 1;
            return false;
        } else {
            throw new JsonDataException("Expected a boolean but was " + peek() + " at path " + getPath());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public double nextDouble() {
        String nextUnquotedValue;
        ByteString byteString;
        double parseDouble;
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 16) {
            this.peeked = 0;
            int[] iArr = this.f4453d;
            int i3 = this.f4450a - 1;
            iArr[i3] = iArr[i3] + 1;
            return this.peekedLong;
        }
        try {
            if (i2 == 17) {
                nextUnquotedValue = this.buffer.readUtf8(this.peekedNumberLength);
            } else {
                if (i2 == 9) {
                    byteString = DOUBLE_QUOTE_OR_SLASH;
                } else if (i2 == 8) {
                    byteString = SINGLE_QUOTE_OR_SLASH;
                } else if (i2 != 10) {
                    if (i2 != 11) {
                        throw new JsonDataException("Expected a double but was " + peek() + " at path " + getPath());
                    }
                    this.peeked = 11;
                    parseDouble = Double.parseDouble(this.peekedString);
                    if (this.f4454e && (Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
                        throw new JsonEncodingException("JSON forbids NaN and infinities: " + parseDouble + " at path " + getPath());
                    }
                    this.peekedString = null;
                    this.peeked = 0;
                    int[] iArr2 = this.f4453d;
                    int i4 = this.f4450a - 1;
                    iArr2[i4] = iArr2[i4] + 1;
                    return parseDouble;
                } else {
                    nextUnquotedValue = nextUnquotedValue();
                }
                nextUnquotedValue = nextQuotedValue(byteString);
            }
            parseDouble = Double.parseDouble(this.peekedString);
            if (this.f4454e) {
            }
            this.peekedString = null;
            this.peeked = 0;
            int[] iArr22 = this.f4453d;
            int i42 = this.f4450a - 1;
            iArr22[i42] = iArr22[i42] + 1;
            return parseDouble;
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.peekedString + " at path " + getPath());
        }
        this.peekedString = nextUnquotedValue;
        this.peeked = 11;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int nextInt() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 16) {
            long j2 = this.peekedLong;
            int i3 = (int) j2;
            if (j2 == i3) {
                this.peeked = 0;
                int[] iArr = this.f4453d;
                int i4 = this.f4450a - 1;
                iArr[i4] = iArr[i4] + 1;
                return i3;
            }
            throw new JsonDataException("Expected an int but was " + this.peekedLong + " at path " + getPath());
        }
        if (i2 == 17) {
            this.peekedString = this.buffer.readUtf8(this.peekedNumberLength);
        } else if (i2 == 9 || i2 == 8) {
            String nextQuotedValue = nextQuotedValue(i2 == 9 ? DOUBLE_QUOTE_OR_SLASH : SINGLE_QUOTE_OR_SLASH);
            this.peekedString = nextQuotedValue;
            try {
                int parseInt = Integer.parseInt(nextQuotedValue);
                this.peeked = 0;
                int[] iArr2 = this.f4453d;
                int i5 = this.f4450a - 1;
                iArr2[i5] = iArr2[i5] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else if (i2 != 11) {
            throw new JsonDataException("Expected an int but was " + peek() + " at path " + getPath());
        }
        this.peeked = 11;
        try {
            double parseDouble = Double.parseDouble(this.peekedString);
            int i6 = (int) parseDouble;
            if (i6 == parseDouble) {
                this.peekedString = null;
                this.peeked = 0;
                int[] iArr3 = this.f4453d;
                int i7 = this.f4450a - 1;
                iArr3[i7] = iArr3[i7] + 1;
                return i6;
            }
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPath());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPath());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String nextName() {
        String str;
        ByteString byteString;
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 14) {
            str = nextUnquotedValue();
        } else {
            if (i2 == 13) {
                byteString = DOUBLE_QUOTE_OR_SLASH;
            } else if (i2 == 12) {
                byteString = SINGLE_QUOTE_OR_SLASH;
            } else if (i2 != 15) {
                throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
            } else {
                str = this.peekedString;
            }
            str = nextQuotedValue(byteString);
        }
        this.peeked = 0;
        this.f4452c[this.f4450a - 1] = str;
        return str;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String nextString() {
        String readUtf8;
        ByteString byteString;
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 10) {
            readUtf8 = nextUnquotedValue();
        } else {
            if (i2 == 9) {
                byteString = DOUBLE_QUOTE_OR_SLASH;
            } else if (i2 == 8) {
                byteString = SINGLE_QUOTE_OR_SLASH;
            } else if (i2 == 11) {
                readUtf8 = this.peekedString;
                this.peekedString = null;
            } else if (i2 == 16) {
                readUtf8 = Long.toString(this.peekedLong);
            } else if (i2 != 17) {
                throw new JsonDataException("Expected a string but was " + peek() + " at path " + getPath());
            } else {
                readUtf8 = this.buffer.readUtf8(this.peekedNumberLength);
            }
            readUtf8 = nextQuotedValue(byteString);
        }
        this.peeked = 0;
        int[] iArr = this.f4453d;
        int i3 = this.f4450a - 1;
        iArr[i3] = iArr[i3] + 1;
        return readUtf8;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public JsonReader.Token peek() {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        switch (i2) {
            case 1:
                return JsonReader.Token.BEGIN_OBJECT;
            case 2:
                return JsonReader.Token.END_OBJECT;
            case 3:
                return JsonReader.Token.BEGIN_ARRAY;
            case 4:
                return JsonReader.Token.END_ARRAY;
            case 5:
            case 6:
                return JsonReader.Token.BOOLEAN;
            case 7:
                return JsonReader.Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonReader.Token.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return JsonReader.Token.NAME;
            case 16:
            case 17:
                return JsonReader.Token.NUMBER;
            case 18:
                return JsonReader.Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int selectName(JsonReader.Options options) {
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 < 12 || i2 > 15) {
            return -1;
        }
        if (i2 == 15) {
            return findName(this.peekedString, options);
        }
        int select = this.source.select(options.f4457b);
        if (select != -1) {
            this.peeked = 0;
            this.f4452c[this.f4450a - 1] = options.f4456a[select];
            return select;
        }
        String str = this.f4452c[this.f4450a - 1];
        String nextName = nextName();
        int findName = findName(nextName, options);
        if (findName == -1) {
            this.peeked = 15;
            this.peekedString = nextName;
            this.f4452c[this.f4450a - 1] = str;
        }
        return findName;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void skipName() {
        ByteString byteString;
        if (this.f4455f) {
            throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
        }
        int i2 = this.peeked;
        if (i2 == 0) {
            i2 = doPeek();
        }
        if (i2 == 14) {
            skipUnquotedValue();
        } else {
            if (i2 == 13) {
                byteString = DOUBLE_QUOTE_OR_SLASH;
            } else if (i2 == 12) {
                byteString = SINGLE_QUOTE_OR_SLASH;
            } else if (i2 != 15) {
                throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
            }
            skipQuotedValue(byteString);
        }
        this.peeked = 0;
        this.f4452c[this.f4450a - 1] = "null";
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void skipValue() {
        ByteString byteString;
        if (this.f4455f) {
            throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
        }
        int i2 = 0;
        do {
            int i3 = this.peeked;
            if (i3 == 0) {
                i3 = doPeek();
            }
            if (i3 == 3) {
                b(1);
            } else if (i3 == 1) {
                b(3);
            } else {
                if (i3 == 4) {
                    i2--;
                    if (i2 < 0) {
                        throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                    }
                } else if (i3 == 2) {
                    i2--;
                    if (i2 < 0) {
                        throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                    }
                } else {
                    if (i3 == 14 || i3 == 10) {
                        skipUnquotedValue();
                    } else {
                        if (i3 == 9 || i3 == 13) {
                            byteString = DOUBLE_QUOTE_OR_SLASH;
                        } else if (i3 == 8 || i3 == 12) {
                            byteString = SINGLE_QUOTE_OR_SLASH;
                        } else if (i3 == 17) {
                            this.buffer.skip(this.peekedNumberLength);
                        } else if (i3 == 18) {
                            throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                        }
                        skipQuotedValue(byteString);
                    }
                    this.peeked = 0;
                }
                this.f4450a--;
                this.peeked = 0;
            }
            i2++;
            this.peeked = 0;
        } while (i2 != 0);
        int[] iArr = this.f4453d;
        int i4 = this.f4450a;
        int i5 = i4 - 1;
        iArr[i5] = iArr[i5] + 1;
        this.f4452c[i4 - 1] = "null";
    }

    public String toString() {
        return "JsonReader(" + this.source + ")";
    }
}
