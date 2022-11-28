package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.codec.language.Soundex;
import org.apache.http.message.TokenParser;
/* loaded from: classes.dex */
public class ReaderBasedJsonParser extends ParserBase {
    protected Reader O;
    protected char[] P;
    protected boolean Q;
    protected ObjectCodec R;
    protected final CharsToNameCanonicalizer S;
    protected final int T;
    protected boolean U;
    protected long V;
    protected int W;
    protected int X;
    private static final int FEAT_MASK_TRAILING_COMMA = JsonParser.Feature.ALLOW_TRAILING_COMMA.getMask();
    private static final int FEAT_MASK_LEADING_ZEROS = JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS.getMask();
    private static final int FEAT_MASK_NON_NUM_NUMBERS = JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS.getMask();
    private static final int FEAT_MASK_ALLOW_MISSING = JsonParser.Feature.ALLOW_MISSING_VALUES.getMask();
    private static final int FEAT_MASK_ALLOW_SINGLE_QUOTES = JsonParser.Feature.ALLOW_SINGLE_QUOTES.getMask();
    private static final int FEAT_MASK_ALLOW_UNQUOTED_NAMES = JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES.getMask();
    private static final int FEAT_MASK_ALLOW_JAVA_COMMENTS = JsonParser.Feature.ALLOW_COMMENTS.getMask();
    private static final int FEAT_MASK_ALLOW_YAML_COMMENTS = JsonParser.Feature.ALLOW_YAML_COMMENTS.getMask();
    protected static final int[] Y = CharTypes.getInputCodeLatin1();

    public ReaderBasedJsonParser(IOContext iOContext, int i2, Reader reader, ObjectCodec objectCodec, CharsToNameCanonicalizer charsToNameCanonicalizer) {
        super(iOContext, i2);
        this.O = reader;
        this.P = iOContext.allocTokenBuffer();
        this.f5090p = 0;
        this.f5091q = 0;
        this.R = objectCodec;
        this.S = charsToNameCanonicalizer;
        this.T = charsToNameCanonicalizer.hashSeed();
        this.Q = true;
    }

    public ReaderBasedJsonParser(IOContext iOContext, int i2, Reader reader, ObjectCodec objectCodec, CharsToNameCanonicalizer charsToNameCanonicalizer, char[] cArr, int i3, int i4, boolean z) {
        super(iOContext, i2);
        this.O = reader;
        this.P = cArr;
        this.f5090p = i3;
        this.f5091q = i4;
        this.R = objectCodec;
        this.S = charsToNameCanonicalizer;
        this.T = charsToNameCanonicalizer.hashSeed();
        this.Q = z;
    }

    private final void _checkMatchEnd(String str, int i2, int i3) {
        if (Character.isJavaIdentifierPart((char) i3)) {
            z0(str.substring(0, i2));
        }
    }

    private void _closeScope(int i2) {
        if (i2 == 93) {
            _updateLocation();
            if (!this.x.inArray()) {
                Q(i2, AbstractJsonLexerKt.END_OBJ);
            }
            this.x = this.x.clearAndGetParent();
            this.f5104c = JsonToken.END_ARRAY;
        }
        if (i2 == 125) {
            _updateLocation();
            if (!this.x.inObject()) {
                Q(i2, AbstractJsonLexerKt.END_LIST);
            }
            this.x = this.x.clearAndGetParent();
            this.f5104c = JsonToken.END_OBJECT;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0069 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0061 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String _handleOddName2(int i2, int i3, int[] iArr) {
        int i4;
        this.z.resetWithShared(this.P, i2, this.f5090p - i2);
        char[] currentSegment = this.z.getCurrentSegment();
        int currentSegmentSize = this.z.getCurrentSegmentSize();
        int length = iArr.length;
        while (true) {
            if (this.f5090p >= this.f5091q && !q0()) {
                break;
            }
            char c2 = this.P[this.f5090p];
            if (c2 < length) {
                if (iArr[c2] != 0) {
                    break;
                }
                this.f5090p++;
                i3 = (i3 * 33) + c2;
                i4 = currentSegmentSize + 1;
                currentSegment[currentSegmentSize] = c2;
                if (i4 < currentSegment.length) {
                    currentSegment = this.z.finishCurrentSegment();
                    currentSegmentSize = 0;
                } else {
                    currentSegmentSize = i4;
                }
            } else {
                if (!Character.isJavaIdentifierPart(c2)) {
                    break;
                }
                this.f5090p++;
                i3 = (i3 * 33) + c2;
                i4 = currentSegmentSize + 1;
                currentSegment[currentSegmentSize] = c2;
                if (i4 < currentSegment.length) {
                }
            }
        }
        this.z.setCurrentLength(currentSegmentSize);
        TextBuffer textBuffer = this.z;
        return this.S.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i3);
    }

    private final void _isNextTokenNameYes(int i2) {
        JsonToken jsonToken;
        this.f5104c = JsonToken.FIELD_NAME;
        _updateLocation();
        if (i2 == 34) {
            this.U = true;
            jsonToken = JsonToken.VALUE_STRING;
        } else if (i2 == 91) {
            jsonToken = JsonToken.START_ARRAY;
        } else if (i2 == 102) {
            s0("false", 1);
            jsonToken = JsonToken.VALUE_FALSE;
        } else if (i2 == 110) {
            s0("null", 1);
            jsonToken = JsonToken.VALUE_NULL;
        } else if (i2 == 116) {
            s0("true", 1);
            jsonToken = JsonToken.VALUE_TRUE;
        } else if (i2 == 123) {
            jsonToken = JsonToken.START_OBJECT;
        } else if (i2 == 45) {
            jsonToken = w0();
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
                    jsonToken = x0(i2);
                    break;
                default:
                    jsonToken = o0(i2);
                    break;
            }
        } else {
            jsonToken = u0();
        }
        this.y = jsonToken;
    }

    private final void _matchFalse() {
        int i2;
        char c2;
        int i3 = this.f5090p;
        if (i3 + 4 < this.f5091q) {
            char[] cArr = this.P;
            if (cArr[i3] == 'a') {
                int i4 = i3 + 1;
                if (cArr[i4] == 'l') {
                    int i5 = i4 + 1;
                    if (cArr[i5] == 's') {
                        int i6 = i5 + 1;
                        if (cArr[i6] == 'e' && ((c2 = cArr[(i2 = i6 + 1)]) < '0' || c2 == ']' || c2 == '}')) {
                            this.f5090p = i2;
                            return;
                        }
                    }
                }
            }
        }
        s0("false", 1);
    }

    private final void _matchNull() {
        int i2;
        char c2;
        int i3 = this.f5090p;
        if (i3 + 3 < this.f5091q) {
            char[] cArr = this.P;
            if (cArr[i3] == 'u') {
                int i4 = i3 + 1;
                if (cArr[i4] == 'l') {
                    int i5 = i4 + 1;
                    if (cArr[i5] == 'l' && ((c2 = cArr[(i2 = i5 + 1)]) < '0' || c2 == ']' || c2 == '}')) {
                        this.f5090p = i2;
                        return;
                    }
                }
            }
        }
        s0("null", 1);
    }

    private final void _matchToken2(String str, int i2) {
        int i3;
        char c2;
        int length = str.length();
        do {
            if ((this.f5090p >= this.f5091q && !q0()) || this.P[this.f5090p] != str.charAt(i2)) {
                z0(str.substring(0, i2));
            }
            i3 = this.f5090p + 1;
            this.f5090p = i3;
            i2++;
        } while (i2 < length);
        if ((i3 < this.f5091q || q0()) && (c2 = this.P[this.f5090p]) >= '0' && c2 != ']' && c2 != '}') {
            _checkMatchEnd(str, i2, c2);
        }
    }

    private final void _matchTrue() {
        int i2;
        char c2;
        int i3 = this.f5090p;
        if (i3 + 3 < this.f5091q) {
            char[] cArr = this.P;
            if (cArr[i3] == 'r') {
                int i4 = i3 + 1;
                if (cArr[i4] == 'u') {
                    int i5 = i4 + 1;
                    if (cArr[i5] == 'e' && ((c2 = cArr[(i2 = i5 + 1)]) < '0' || c2 == ']' || c2 == '}')) {
                        this.f5090p = i2;
                        return;
                    }
                }
            }
        }
        s0("true", 1);
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
        JsonToken jsonToken;
        if (i2 == 34) {
            this.U = true;
            jsonToken = JsonToken.VALUE_STRING;
        } else if (i2 == 91) {
            this.x = this.x.createChildArrayContext(this.v, this.w);
            jsonToken = JsonToken.START_ARRAY;
        } else if (i2 != 102) {
            if (i2 == 110) {
                s0("null", 1);
            } else if (i2 == 116) {
                s0("true", 1);
                jsonToken = JsonToken.VALUE_TRUE;
            } else if (i2 != 123) {
                switch (i2) {
                    case 44:
                        if (!this.x.inRoot() && (this.f5048a & FEAT_MASK_ALLOW_MISSING) != 0) {
                            this.f5090p--;
                            break;
                        }
                        jsonToken = o0(i2);
                        break;
                    case 45:
                        jsonToken = w0();
                        break;
                    case 46:
                        jsonToken = u0();
                        break;
                    default:
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
                                jsonToken = x0(i2);
                                break;
                            default:
                                jsonToken = o0(i2);
                                break;
                        }
                }
            } else {
                this.x = this.x.createChildObjectContext(this.v, this.w);
                jsonToken = JsonToken.START_OBJECT;
            }
            jsonToken = JsonToken.VALUE_NULL;
        } else {
            s0("false", 1);
            jsonToken = JsonToken.VALUE_FALSE;
        }
        this.f5104c = jsonToken;
        return jsonToken;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r9v0 ??, r9v1 ??, r9v18 ??, r9v12 ??, r9v5 ??, r9v3 ??, r9v9 ??, r9v7 ??, r9v10 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x006e -> B:30:0x0050). Please submit an issue!!! */
    private final com.fasterxml.jackson.core.JsonToken _parseFloat(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r9v0 ??, r9v1 ??, r9v18 ??, r9v12 ??, r9v5 ??, r9v3 ??, r9v9 ??, r9v7 ??, r9v10 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r9v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:227)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:222)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:167)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:372)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        */

    private String _parseName2(int i2, int i3, int i4) {
        this.z.resetWithShared(this.P, i2, this.f5090p - i2);
        char[] currentSegment = this.z.getCurrentSegment();
        int currentSegmentSize = this.z.getCurrentSegmentSize();
        while (true) {
            if (this.f5090p >= this.f5091q && !q0()) {
                p(" in field name", JsonToken.FIELD_NAME);
            }
            char[] cArr = this.P;
            int i5 = this.f5090p;
            this.f5090p = i5 + 1;
            char c2 = cArr[i5];
            if (c2 <= '\\') {
                if (c2 == '\\') {
                    c2 = I();
                } else if (c2 <= i4) {
                    if (c2 == i4) {
                        this.z.setCurrentLength(currentSegmentSize);
                        TextBuffer textBuffer = this.z;
                        return this.S.findSymbol(textBuffer.getTextBuffer(), textBuffer.getTextOffset(), textBuffer.size(), i3);
                    } else if (c2 < ' ') {
                        S(c2, AppMeasurementSdk.ConditionalUserProperty.NAME);
                    }
                }
            }
            i3 = (i3 * 33) + c2;
            int i6 = currentSegmentSize + 1;
            currentSegment[currentSegmentSize] = c2;
            if (i6 >= currentSegment.length) {
                currentSegment = this.z.finishCurrentSegment();
                currentSegmentSize = 0;
            } else {
                currentSegmentSize = i6;
            }
        }
    }

    private final JsonToken _parseNumber2(boolean z, int i2) {
        int i3;
        char E0;
        boolean z2;
        int i4;
        char D0;
        if (z) {
            i2++;
        }
        this.f5090p = i2;
        char[] emptyAndGetCurrentSegment = this.z.emptyAndGetCurrentSegment();
        int i5 = 0;
        if (z) {
            emptyAndGetCurrentSegment[0] = Soundex.SILENT_MARKER;
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i6 = this.f5090p;
        if (i6 < this.f5091q) {
            char[] cArr = this.P;
            this.f5090p = i6 + 1;
            E0 = cArr[i6];
        } else {
            E0 = E0("No digit following minus sign", JsonToken.VALUE_NUMBER_INT);
        }
        if (E0 == '0') {
            E0 = _verifyNoLeadingZeroes();
        }
        int i7 = 0;
        while (E0 >= '0' && E0 <= '9') {
            i7++;
            if (i3 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this.z.finishCurrentSegment();
                i3 = 0;
            }
            int i8 = i3 + 1;
            emptyAndGetCurrentSegment[i3] = E0;
            if (this.f5090p >= this.f5091q && !q0()) {
                E0 = 0;
                i3 = i8;
                z2 = true;
                break;
            }
            char[] cArr2 = this.P;
            int i9 = this.f5090p;
            this.f5090p = i9 + 1;
            E0 = cArr2[i9];
            i3 = i8;
        }
        z2 = false;
        if (i7 == 0) {
            return m0(E0, z);
        }
        if (E0 == '.') {
            if (i3 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this.z.finishCurrentSegment();
                i3 = 0;
            }
            emptyAndGetCurrentSegment[i3] = E0;
            i3++;
            i4 = 0;
            while (true) {
                if (this.f5090p >= this.f5091q && !q0()) {
                    z2 = true;
                    break;
                }
                char[] cArr3 = this.P;
                int i10 = this.f5090p;
                this.f5090p = i10 + 1;
                E0 = cArr3[i10];
                if (E0 < '0' || E0 > '9') {
                    break;
                }
                i4++;
                if (i3 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this.z.finishCurrentSegment();
                    i3 = 0;
                }
                emptyAndGetCurrentSegment[i3] = E0;
                i3++;
            }
            if (i4 == 0) {
                D(E0, "Decimal point not followed by a digit");
            }
        } else {
            i4 = 0;
        }
        if (E0 == 'e' || E0 == 'E') {
            if (i3 >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this.z.finishCurrentSegment();
                i3 = 0;
            }
            int i11 = i3 + 1;
            emptyAndGetCurrentSegment[i3] = E0;
            int i12 = this.f5090p;
            if (i12 < this.f5091q) {
                char[] cArr4 = this.P;
                this.f5090p = i12 + 1;
                D0 = cArr4[i12];
            } else {
                D0 = D0("expected a digit for number exponent");
            }
            if (D0 == '-' || D0 == '+') {
                if (i11 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this.z.finishCurrentSegment();
                    i11 = 0;
                }
                int i13 = i11 + 1;
                emptyAndGetCurrentSegment[i11] = D0;
                int i14 = this.f5090p;
                if (i14 < this.f5091q) {
                    char[] cArr5 = this.P;
                    this.f5090p = i14 + 1;
                    D0 = cArr5[i14];
                } else {
                    D0 = D0("expected a digit for number exponent");
                }
                i11 = i13;
            }
            int i15 = 0;
            E0 = D0;
            while (E0 <= '9' && E0 >= '0') {
                i15++;
                if (i11 >= emptyAndGetCurrentSegment.length) {
                    emptyAndGetCurrentSegment = this.z.finishCurrentSegment();
                    i11 = 0;
                }
                i3 = i11 + 1;
                emptyAndGetCurrentSegment[i11] = E0;
                if (this.f5090p >= this.f5091q && !q0()) {
                    i5 = i15;
                    z2 = true;
                    break;
                }
                char[] cArr6 = this.P;
                int i16 = this.f5090p;
                this.f5090p = i16 + 1;
                E0 = cArr6[i16];
                i11 = i3;
            }
            i5 = i15;
            i3 = i11;
            if (i5 == 0) {
                D(E0, "Exponent indicator not followed by a digit");
            }
        }
        if (!z2) {
            this.f5090p--;
            if (this.x.inRoot()) {
                _verifyRootSpace(E0);
            }
        }
        this.z.setCurrentLength(i3);
        return d0(z, i7, i4, i5);
    }

    private final int _skipAfterComma2() {
        char c2;
        while (true) {
            if (this.f5090p >= this.f5091q && !q0()) {
                throw b("Unexpected end-of-input within/between " + this.x.typeDesc() + " entries");
            }
            char[] cArr = this.P;
            int i2 = this.f5090p;
            int i3 = i2 + 1;
            this.f5090p = i3;
            c2 = cArr[i2];
            if (c2 > ' ') {
                if (c2 == '/') {
                    _skipComment();
                } else if (c2 != '#' || !_skipYAMLComment()) {
                    break;
                }
            } else if (c2 < ' ') {
                if (c2 == '\n') {
                    this.f5093s++;
                    this.f5094t = i3;
                } else if (c2 == '\r') {
                    B0();
                } else if (c2 != '\t') {
                    u(c2);
                }
            }
        }
        return c2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0026, code lost:
        p(" in a comment", null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002c, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void _skipCComment() {
        while (true) {
            if (this.f5090p >= this.f5091q && !q0()) {
                break;
            }
            char[] cArr = this.P;
            int i2 = this.f5090p;
            int i3 = i2 + 1;
            this.f5090p = i3;
            char c2 = cArr[i2];
            if (c2 <= '*') {
                if (c2 == '*') {
                    if (i3 >= this.f5091q && !q0()) {
                        break;
                    }
                    char[] cArr2 = this.P;
                    int i4 = this.f5090p;
                    if (cArr2[i4] == '/') {
                        this.f5090p = i4 + 1;
                        return;
                    }
                } else if (c2 < ' ') {
                    if (c2 == '\n') {
                        this.f5093s++;
                        this.f5094t = i3;
                    } else if (c2 == '\r') {
                        B0();
                    } else if (c2 != '\t') {
                        u(c2);
                    }
                }
            }
        }
    }

    private final int _skipColon() {
        int i2;
        char c2;
        int i3;
        char c3;
        int i4 = this.f5090p;
        if (i4 + 4 >= this.f5091q) {
            return _skipColon2(false);
        }
        char[] cArr = this.P;
        char c4 = cArr[i4];
        if (c4 == ':') {
            i2 = i4 + 1;
            this.f5090p = i2;
            c2 = cArr[i2];
            if (c2 > ' ') {
                if (c2 == '/' || c2 == '#') {
                    return _skipColon2(true);
                }
                this.f5090p = i2 + 1;
                return c2;
            }
            if (c2 == ' ' || c2 == '\t') {
                i3 = i2 + 1;
                this.f5090p = i3;
                c3 = cArr[i3];
                if (c3 > ' ') {
                    if (c3 == '/' || c3 == '#') {
                        return _skipColon2(true);
                    }
                    this.f5090p = i3 + 1;
                    return c3;
                }
            }
            return _skipColon2(true);
        }
        if (c4 == ' ' || c4 == '\t') {
            int i5 = i4 + 1;
            this.f5090p = i5;
            c4 = cArr[i5];
        }
        if (c4 == ':') {
            i2 = this.f5090p + 1;
            this.f5090p = i2;
            c2 = cArr[i2];
            if (c2 > ' ') {
                if (c2 == '/' || c2 == '#') {
                    return _skipColon2(true);
                }
                this.f5090p = i2 + 1;
                return c2;
            }
            if (c2 == ' ' || c2 == '\t') {
                i3 = i2 + 1;
                this.f5090p = i3;
                c3 = cArr[i3];
                if (c3 > ' ') {
                    if (c3 == '/' || c3 == '#') {
                        return _skipColon2(true);
                    }
                    this.f5090p = i3 + 1;
                    return c3;
                }
            }
            return _skipColon2(true);
        }
        return _skipColon2(false);
    }

    private final int _skipColon2(boolean z) {
        while (true) {
            if (this.f5090p >= this.f5091q && !q0()) {
                p(" within/between " + this.x.typeDesc() + " entries", null);
                return -1;
            }
            char[] cArr = this.P;
            int i2 = this.f5090p;
            int i3 = i2 + 1;
            this.f5090p = i3;
            char c2 = cArr[i2];
            if (c2 > ' ') {
                if (c2 == '/') {
                    _skipComment();
                } else if (c2 != '#' || !_skipYAMLComment()) {
                    if (z) {
                        return c2;
                    }
                    if (c2 != ':') {
                        s(c2, "was expecting a colon to separate field name and value");
                    }
                    z = true;
                }
            } else if (c2 < ' ') {
                if (c2 == '\n') {
                    this.f5093s++;
                    this.f5094t = i3;
                } else if (c2 == '\r') {
                    B0();
                } else if (c2 != '\t') {
                    u(c2);
                }
            }
        }
    }

    private final int _skipColonFast(int i2) {
        char[] cArr = this.P;
        int i3 = i2 + 1;
        char c2 = cArr[i2];
        if (c2 == ':') {
            int i4 = i3 + 1;
            char c3 = cArr[i3];
            if (c3 > ' ') {
                if (c3 != '/' && c3 != '#') {
                    this.f5090p = i4;
                    return c3;
                }
            } else if (c3 == ' ' || c3 == '\t') {
                int i5 = i4 + 1;
                char c4 = cArr[i4];
                if (c4 > ' ' && c4 != '/' && c4 != '#') {
                    this.f5090p = i5;
                    return c4;
                }
                i4 = i5;
            }
            this.f5090p = i4 - 1;
            return _skipColon2(true);
        }
        if (c2 == ' ' || c2 == '\t') {
            int i6 = i3 + 1;
            char c5 = cArr[i3];
            i3 = i6;
            c2 = c5;
        }
        boolean z = c2 == ':';
        if (z) {
            int i7 = i3 + 1;
            char c6 = cArr[i3];
            if (c6 > ' ') {
                if (c6 != '/' && c6 != '#') {
                    this.f5090p = i7;
                    return c6;
                }
            } else if (c6 == ' ' || c6 == '\t') {
                i3 = i7 + 1;
                char c7 = cArr[i7];
                if (c7 > ' ' && c7 != '/' && c7 != '#') {
                    this.f5090p = i3;
                    return c7;
                }
            }
            i3 = i7;
        }
        this.f5090p = i3 - 1;
        return _skipColon2(z);
    }

    private final int _skipComma(int i2) {
        if (i2 != 44) {
            s(i2, "was expecting comma to separate " + this.x.typeDesc() + " entries");
        }
        while (true) {
            int i3 = this.f5090p;
            if (i3 >= this.f5091q) {
                return _skipAfterComma2();
            }
            char[] cArr = this.P;
            int i4 = i3 + 1;
            this.f5090p = i4;
            char c2 = cArr[i3];
            if (c2 > ' ') {
                if (c2 == '/' || c2 == '#') {
                    this.f5090p = i4 - 1;
                    return _skipAfterComma2();
                }
                return c2;
            } else if (c2 < ' ') {
                if (c2 == '\n') {
                    this.f5093s++;
                    this.f5094t = i4;
                } else if (c2 == '\r') {
                    B0();
                } else if (c2 != '\t') {
                    u(c2);
                }
            }
        }
    }

    private void _skipComment() {
        if ((this.f5048a & FEAT_MASK_ALLOW_JAVA_COMMENTS) == 0) {
            s(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this.f5090p >= this.f5091q && !q0()) {
            p(" in a comment", null);
        }
        char[] cArr = this.P;
        int i2 = this.f5090p;
        this.f5090p = i2 + 1;
        char c2 = cArr[i2];
        if (c2 == '/') {
            _skipLine();
        } else if (c2 == '*') {
            _skipCComment();
        } else {
            s(c2, "was expecting either '*' or '/' for a comment");
        }
    }

    private void _skipLine() {
        while (true) {
            if (this.f5090p >= this.f5091q && !q0()) {
                return;
            }
            char[] cArr = this.P;
            int i2 = this.f5090p;
            int i3 = i2 + 1;
            this.f5090p = i3;
            char c2 = cArr[i2];
            if (c2 < ' ') {
                if (c2 == '\n') {
                    this.f5093s++;
                    this.f5094t = i3;
                    return;
                } else if (c2 == '\r') {
                    B0();
                    return;
                } else if (c2 != '\t') {
                    u(c2);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x004b, code lost:
        if (r0 != '\t') goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004d, code lost:
        u(r0);
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0047 -> B:25:0x0050). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x004d -> B:25:0x0050). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final int _skipWSOrEnd() {
        if (this.f5090p < this.f5091q || q0()) {
            char[] cArr = this.P;
            int i2 = this.f5090p;
            int i3 = i2 + 1;
            this.f5090p = i3;
            char c2 = cArr[i2];
            if (c2 <= ' ') {
                if (c2 != ' ') {
                    if (c2 == '\n') {
                        this.f5093s++;
                        this.f5094t = i3;
                    } else {
                        if (c2 != '\r') {
                        }
                        B0();
                    }
                }
                while (true) {
                    int i4 = this.f5090p;
                    if (i4 >= this.f5091q) {
                        return _skipWSOrEnd2();
                    }
                    char[] cArr2 = this.P;
                    int i5 = i4 + 1;
                    this.f5090p = i5;
                    c2 = cArr2[i4];
                    if (c2 <= ' ') {
                        if (c2 != ' ') {
                            if (c2 == '\n') {
                                this.f5093s++;
                                this.f5094t = i5;
                            } else if (c2 == '\r') {
                                break;
                            } else if (c2 != '\t') {
                                break;
                            }
                        }
                    } else if (c2 != '/' && c2 != '#') {
                        return c2;
                    } else {
                        this.f5090p = i5 - 1;
                    }
                }
            } else if (c2 != '/' && c2 != '#') {
                return c2;
            } else {
                this.f5090p = i3 - 1;
            }
            return _skipWSOrEnd2();
        }
        return J();
    }

    private int _skipWSOrEnd2() {
        char c2;
        while (true) {
            if (this.f5090p >= this.f5091q && !q0()) {
                return J();
            }
            char[] cArr = this.P;
            int i2 = this.f5090p;
            int i3 = i2 + 1;
            this.f5090p = i3;
            c2 = cArr[i2];
            if (c2 > ' ') {
                if (c2 == '/') {
                    _skipComment();
                } else if (c2 != '#' || !_skipYAMLComment()) {
                    break;
                }
            } else if (c2 != ' ') {
                if (c2 == '\n') {
                    this.f5093s++;
                    this.f5094t = i3;
                } else if (c2 == '\r') {
                    B0();
                } else if (c2 != '\t') {
                    u(c2);
                }
            }
        }
        return c2;
    }

    private boolean _skipYAMLComment() {
        if ((this.f5048a & FEAT_MASK_ALLOW_YAML_COMMENTS) == 0) {
            return false;
        }
        _skipLine();
        return true;
    }

    private final void _updateLocation() {
        int i2 = this.f5090p;
        this.u = this.f5092r + i2;
        this.v = this.f5093s;
        this.w = i2 - this.f5094t;
    }

    private final void _updateNameLocation() {
        int i2 = this.f5090p;
        this.V = i2;
        this.W = this.f5093s;
        this.X = i2 - this.f5094t;
    }

    private char _verifyNLZ2() {
        char c2;
        if ((this.f5090p < this.f5091q || q0()) && (c2 = this.P[this.f5090p]) >= '0' && c2 <= '9') {
            if ((this.f5048a & FEAT_MASK_LEADING_ZEROS) == 0) {
                w("Leading zeroes not allowed");
            }
            this.f5090p++;
            if (c2 == '0') {
                do {
                    if (this.f5090p >= this.f5091q && !q0()) {
                        break;
                    }
                    char[] cArr = this.P;
                    int i2 = this.f5090p;
                    c2 = cArr[i2];
                    if (c2 < '0' || c2 > '9') {
                        return '0';
                    }
                    this.f5090p = i2 + 1;
                } while (c2 == '0');
            }
            return c2;
        }
        return '0';
    }

    private final char _verifyNoLeadingZeroes() {
        char c2;
        int i2 = this.f5090p;
        if (i2 >= this.f5091q || ((c2 = this.P[i2]) >= '0' && c2 <= '9')) {
            return _verifyNLZ2();
        }
        return '0';
    }

    private final void _verifyRootSpace(int i2) {
        int i3 = this.f5090p + 1;
        this.f5090p = i3;
        if (i2 != 9) {
            if (i2 == 10) {
                this.f5093s++;
                this.f5094t = i3;
            } else if (i2 == 13) {
                B0();
            } else if (i2 != 32) {
                r(i2);
            }
        }
    }

    protected void A0(String str, String str2) {
        StringBuilder sb = new StringBuilder(str);
        while (true) {
            if (this.f5090p >= this.f5091q && !q0()) {
                break;
            }
            char c2 = this.P[this.f5090p];
            if (!Character.isJavaIdentifierPart(c2)) {
                break;
            }
            this.f5090p++;
            sb.append(c2);
            if (sb.length() >= 256) {
                sb.append("...");
                break;
            }
        }
        m("Unrecognized token '%s': was expecting %s", sb, str2);
    }

    protected final void B0() {
        if (this.f5090p < this.f5091q || q0()) {
            char[] cArr = this.P;
            int i2 = this.f5090p;
            if (cArr[i2] == '\n') {
                this.f5090p = i2 + 1;
            }
        }
        this.f5093s++;
        this.f5094t = this.f5090p;
    }

    protected final void C0() {
        this.U = false;
        int i2 = this.f5090p;
        int i3 = this.f5091q;
        char[] cArr = this.P;
        while (true) {
            if (i2 >= i3) {
                this.f5090p = i2;
                if (!q0()) {
                    p(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
                }
                i2 = this.f5090p;
                i3 = this.f5091q;
            }
            int i4 = i2 + 1;
            char c2 = cArr[i2];
            if (c2 <= '\\') {
                if (c2 == '\\') {
                    this.f5090p = i4;
                    I();
                    i2 = this.f5090p;
                    i3 = this.f5091q;
                } else if (c2 <= '\"') {
                    if (c2 == '\"') {
                        this.f5090p = i4;
                        return;
                    } else if (c2 < ' ') {
                        this.f5090p = i4;
                        S(c2, "string value");
                    }
                }
            }
            i2 = i4;
        }
    }

    @Deprecated
    protected char D0(String str) {
        return E0(str, null);
    }

    protected char E0(String str, JsonToken jsonToken) {
        if (this.f5090p >= this.f5091q && !q0()) {
            p(str, jsonToken);
        }
        char[] cArr = this.P;
        int i2 = this.f5090p;
        this.f5090p = i2 + 1;
        return cArr[i2];
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void F() {
        if (this.O != null) {
            if (this.f5088n.isResourceManaged() || isEnabled(JsonParser.Feature.AUTO_CLOSE_SOURCE)) {
                this.O.close();
            }
            this.O = null;
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected char I() {
        if (this.f5090p >= this.f5091q && !q0()) {
            p(" in character escape sequence", JsonToken.VALUE_STRING);
        }
        char[] cArr = this.P;
        int i2 = this.f5090p;
        this.f5090p = i2 + 1;
        char c2 = cArr[i2];
        if (c2 == '\"' || c2 == '/' || c2 == '\\') {
            return c2;
        }
        if (c2 != 'b') {
            if (c2 != 'f') {
                if (c2 != 'n') {
                    if (c2 != 'r') {
                        if (c2 != 't') {
                            if (c2 != 'u') {
                                return M(c2);
                            }
                            int i3 = 0;
                            for (int i4 = 0; i4 < 4; i4++) {
                                if (this.f5090p >= this.f5091q && !q0()) {
                                    p(" in character escape sequence", JsonToken.VALUE_STRING);
                                }
                                char[] cArr2 = this.P;
                                int i5 = this.f5090p;
                                this.f5090p = i5 + 1;
                                char c3 = cArr2[i5];
                                int charToHex = CharTypes.charToHex(c3);
                                if (charToHex < 0) {
                                    s(c3, "expected a hex-digit for character escape sequence");
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

    @Override // com.fasterxml.jackson.core.base.ParserBase
    protected void P() {
        char[] cArr;
        super.P();
        this.S.release();
        if (!this.Q || (cArr = this.P) == null) {
            return;
        }
        this.P = null;
        this.f5088n.releaseTokenBuffer(cArr);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void finishToken() {
        if (this.U) {
            this.U = false;
            i0();
        }
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        byte[] bArr;
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != JsonToken.VALUE_EMBEDDED_OBJECT || (bArr = this.D) == null) {
            if (jsonToken != JsonToken.VALUE_STRING) {
                k("Current token (" + this.f5104c + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
            }
            if (this.U) {
                try {
                    this.D = h0(base64Variant);
                    this.U = false;
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
        return bArr;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return this.R;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(K(), -1L, this.f5090p + this.f5092r, this.f5093s, (this.f5090p - this.f5094t) + 1);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getInputSource() {
        return this.O;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getText(Writer writer) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this.U) {
                this.U = false;
                i0();
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
    public final String getText() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken == JsonToken.VALUE_STRING) {
            if (this.U) {
                this.U = false;
                i0();
            }
            return this.z.contentsAsString();
        }
        return k0(jsonToken);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public final char[] getTextCharacters() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != null) {
            int id = jsonToken.id();
            if (id != 5) {
                if (id != 6) {
                    if (id != 7 && id != 8) {
                        return this.f5104c.asCharArray();
                    }
                } else if (this.U) {
                    this.U = false;
                    i0();
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
    public final int getTextLength() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != null) {
            int id = jsonToken.id();
            if (id != 5) {
                if (id != 6) {
                    if (id != 7 && id != 8) {
                        return this.f5104c.asCharArray().length;
                    }
                } else if (this.U) {
                    this.U = false;
                    i0();
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
    public final int getTextOffset() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != null) {
            int id = jsonToken.id();
            if (id != 6) {
                if (id != 7) {
                }
            } else if (this.U) {
                this.U = false;
                i0();
            }
            return this.z.getTextOffset();
        }
        return 0;
    }

    @Override // com.fasterxml.jackson.core.base.ParserBase, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        if (this.f5104c == JsonToken.FIELD_NAME) {
            return new JsonLocation(K(), -1L, this.f5092r + (this.V - 1), this.W, this.X);
        }
        return new JsonLocation(K(), -1L, this.u - 1, this.v, this.w);
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public final String getValueAsString() {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return jsonToken == JsonToken.FIELD_NAME ? getCurrentName() : super.getValueAsString(null);
        }
        if (this.U) {
            this.U = false;
            i0();
        }
        return this.z.contentsAsString();
    }

    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    public final String getValueAsString(String str) {
        JsonToken jsonToken = this.f5104c;
        if (jsonToken != JsonToken.VALUE_STRING) {
            return jsonToken == JsonToken.FIELD_NAME ? getCurrentName() : super.getValueAsString(str);
        }
        if (this.U) {
            this.U = false;
            i0();
        }
        return this.z.contentsAsString();
    }

    protected byte[] h0(Base64Variant base64Variant) {
        ByteArrayBuilder _getByteArrayBuilder = _getByteArrayBuilder();
        while (true) {
            if (this.f5090p >= this.f5091q) {
                r0();
            }
            char[] cArr = this.P;
            int i2 = this.f5090p;
            this.f5090p = i2 + 1;
            char c2 = cArr[i2];
            if (c2 > ' ') {
                int decodeBase64Char = base64Variant.decodeBase64Char(c2);
                if (decodeBase64Char < 0) {
                    if (c2 == '\"') {
                        return _getByteArrayBuilder.toByteArray();
                    }
                    decodeBase64Char = G(base64Variant, c2, 0);
                    if (decodeBase64Char < 0) {
                        continue;
                    }
                }
                if (this.f5090p >= this.f5091q) {
                    r0();
                }
                char[] cArr2 = this.P;
                int i3 = this.f5090p;
                this.f5090p = i3 + 1;
                char c3 = cArr2[i3];
                int decodeBase64Char2 = base64Variant.decodeBase64Char(c3);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = G(base64Variant, c3, 1);
                }
                int i4 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this.f5090p >= this.f5091q) {
                    r0();
                }
                char[] cArr3 = this.P;
                int i5 = this.f5090p;
                this.f5090p = i5 + 1;
                char c4 = cArr3[i5];
                int decodeBase64Char3 = base64Variant.decodeBase64Char(c4);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (c4 == '\"') {
                            _getByteArrayBuilder.append(i4 >> 4);
                            if (base64Variant.usesPadding()) {
                                this.f5090p--;
                                L(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char3 = G(base64Variant, c4, 2);
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this.f5090p >= this.f5091q) {
                            r0();
                        }
                        char[] cArr4 = this.P;
                        int i6 = this.f5090p;
                        this.f5090p = i6 + 1;
                        char c5 = cArr4[i6];
                        if (!base64Variant.usesPaddingChar(c5) && G(base64Variant, c5, 3) != -2) {
                            throw c0(base64Variant, c5, 3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        _getByteArrayBuilder.append(i4 >> 4);
                    }
                }
                int i7 = (i4 << 6) | decodeBase64Char3;
                if (this.f5090p >= this.f5091q) {
                    r0();
                }
                char[] cArr5 = this.P;
                int i8 = this.f5090p;
                this.f5090p = i8 + 1;
                char c6 = cArr5[i8];
                int decodeBase64Char4 = base64Variant.decodeBase64Char(c6);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 != -2) {
                        if (c6 == '\"') {
                            _getByteArrayBuilder.appendTwoBytes(i7 >> 2);
                            if (base64Variant.usesPadding()) {
                                this.f5090p--;
                                L(base64Variant);
                            }
                            return _getByteArrayBuilder.toByteArray();
                        }
                        decodeBase64Char4 = G(base64Variant, c6, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        _getByteArrayBuilder.appendTwoBytes(i7 >> 2);
                    }
                }
                _getByteArrayBuilder.appendThreeBytes((i7 << 6) | decodeBase64Char4);
            }
        }
    }

    protected final void i0() {
        int i2 = this.f5090p;
        int i3 = this.f5091q;
        if (i2 < i3) {
            int[] iArr = Y;
            int length = iArr.length;
            while (true) {
                char[] cArr = this.P;
                char c2 = cArr[i2];
                if (c2 >= length || iArr[c2] == 0) {
                    i2++;
                    if (i2 >= i3) {
                        break;
                    }
                } else if (c2 == '\"') {
                    TextBuffer textBuffer = this.z;
                    int i4 = this.f5090p;
                    textBuffer.resetWithShared(cArr, i4, i2 - i4);
                    this.f5090p = i2 + 1;
                    return;
                }
            }
        }
        TextBuffer textBuffer2 = this.z;
        char[] cArr2 = this.P;
        int i5 = this.f5090p;
        textBuffer2.resetWithCopy(cArr2, i5, i2 - i5);
        this.f5090p = i2;
        j0();
    }

    protected void j0() {
        char[] currentSegment = this.z.getCurrentSegment();
        int currentSegmentSize = this.z.getCurrentSegmentSize();
        int[] iArr = Y;
        int length = iArr.length;
        while (true) {
            if (this.f5090p >= this.f5091q && !q0()) {
                p(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
            }
            char[] cArr = this.P;
            int i2 = this.f5090p;
            this.f5090p = i2 + 1;
            char c2 = cArr[i2];
            if (c2 < length && iArr[c2] != 0) {
                if (c2 == '\"') {
                    this.z.setCurrentLength(currentSegmentSize);
                    return;
                } else if (c2 == '\\') {
                    c2 = I();
                } else if (c2 < ' ') {
                    S(c2, "string value");
                }
            }
            if (currentSegmentSize >= currentSegment.length) {
                currentSegment = this.z.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            currentSegment[currentSegmentSize] = c2;
            currentSegmentSize++;
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
        int currentSegmentSize = this.z.getCurrentSegmentSize();
        while (true) {
            if (this.f5090p >= this.f5091q && !q0()) {
                p(": was expecting closing quote for a string value", JsonToken.VALUE_STRING);
            }
            char[] cArr = this.P;
            int i2 = this.f5090p;
            this.f5090p = i2 + 1;
            char c2 = cArr[i2];
            if (c2 <= '\\') {
                if (c2 == '\\') {
                    c2 = I();
                } else if (c2 <= '\'') {
                    if (c2 == '\'') {
                        this.z.setCurrentLength(currentSegmentSize);
                        return JsonToken.VALUE_STRING;
                    } else if (c2 < ' ') {
                        S(c2, "string value");
                    }
                }
            }
            if (currentSegmentSize >= emptyAndGetCurrentSegment.length) {
                emptyAndGetCurrentSegment = this.z.finishCurrentSegment();
                currentSegmentSize = 0;
            }
            emptyAndGetCurrentSegment[currentSegmentSize] = c2;
            currentSegmentSize++;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r10v0 ??, r10v1 ??, r10v5 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
        */
    protected com.fasterxml.jackson.core.JsonToken m0(
    /*  JADX ERROR: JadxRuntimeException in pass: InitCodeVariables
        jadx.core.utils.exceptions.JadxRuntimeException: Several immutable types in one variable: [int, char], vars: [r10v0 ??, r10v1 ??, r10v5 ??]
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVarType(InitCodeVariables.java:107)
        	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:83)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:57)
        	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:45)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r10v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:227)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:222)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:167)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:372)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:306)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:272)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
        */

    protected String n0(int i2) {
        if (i2 != 39 || (this.f5048a & FEAT_MASK_ALLOW_SINGLE_QUOTES) == 0) {
            if ((this.f5048a & FEAT_MASK_ALLOW_UNQUOTED_NAMES) == 0) {
                s(i2, "was expecting double-quote to start field name");
            }
            int[] inputCodeLatin1JsNames = CharTypes.getInputCodeLatin1JsNames();
            int length = inputCodeLatin1JsNames.length;
            if (!(i2 < length ? inputCodeLatin1JsNames[i2] == 0 : Character.isJavaIdentifierPart((char) i2))) {
                s(i2, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
            }
            int i3 = this.f5090p;
            int i4 = this.T;
            int i5 = this.f5091q;
            if (i3 >= i5) {
                this.f5090p = i3;
                return _handleOddName2(this.f5090p - 1, i4, inputCodeLatin1JsNames);
            }
            do {
                char[] cArr = this.P;
                char c2 = cArr[i3];
                if (c2 < length) {
                    if (inputCodeLatin1JsNames[c2] != 0) {
                        int i6 = this.f5090p - 1;
                        this.f5090p = i3;
                        return this.S.findSymbol(cArr, i6, i3 - i6, i4);
                    }
                } else if (!Character.isJavaIdentifierPart(c2)) {
                    int i7 = this.f5090p - 1;
                    this.f5090p = i3;
                    return this.S.findSymbol(this.P, i7, i3 - i7, i4);
                }
                i4 = (i4 * 33) + c2;
                i3++;
            } while (i3 < i5);
            this.f5090p = i3;
            return _handleOddName2(this.f5090p - 1, i4, inputCodeLatin1JsNames);
        }
        return t0();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public final Boolean nextBooleanValue() {
        JsonReadContext createChildObjectContext;
        if (this.f5104c != JsonToken.FIELD_NAME) {
            JsonToken nextToken = nextToken();
            if (nextToken != null) {
                int id = nextToken.id();
                if (id == 9) {
                    return Boolean.TRUE;
                }
                if (id == 10) {
                    return Boolean.FALSE;
                }
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
        JsonToken w0;
        this.E = 0;
        JsonToken jsonToken = this.f5104c;
        JsonToken jsonToken2 = JsonToken.FIELD_NAME;
        if (jsonToken == jsonToken2) {
            _nextAfterName();
            return null;
        }
        if (this.U) {
            C0();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this.f5104c = null;
            return null;
        }
        this.D = null;
        if (_skipWSOrEnd == 93 || _skipWSOrEnd == 125) {
            _closeScope(_skipWSOrEnd);
            return null;
        }
        if (this.x.expectComma()) {
            _skipWSOrEnd = _skipComma(_skipWSOrEnd);
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
        String v0 = _skipWSOrEnd == 34 ? v0() : n0(_skipWSOrEnd);
        this.x.setCurrentName(v0);
        this.f5104c = jsonToken2;
        int _skipColon = _skipColon();
        _updateLocation();
        if (_skipColon == 34) {
            this.U = true;
            this.y = JsonToken.VALUE_STRING;
            return v0;
        }
        if (_skipColon == 45) {
            w0 = w0();
        } else if (_skipColon == 46) {
            w0 = u0();
        } else if (_skipColon == 91) {
            w0 = JsonToken.START_ARRAY;
        } else if (_skipColon == 102) {
            _matchFalse();
            w0 = JsonToken.VALUE_FALSE;
        } else if (_skipColon == 110) {
            _matchNull();
            w0 = JsonToken.VALUE_NULL;
        } else if (_skipColon == 116) {
            _matchTrue();
            w0 = JsonToken.VALUE_TRUE;
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
                    w0 = x0(_skipColon);
                    break;
                default:
                    w0 = o0(_skipColon);
                    break;
            }
        } else {
            w0 = JsonToken.START_OBJECT;
        }
        this.y = w0;
        return v0;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean nextFieldName(SerializableString serializableString) {
        int i2 = 0;
        this.E = 0;
        if (this.f5104c == JsonToken.FIELD_NAME) {
            _nextAfterName();
            return false;
        }
        if (this.U) {
            C0();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this.f5104c = null;
            return false;
        }
        this.D = null;
        if (_skipWSOrEnd == 93 || _skipWSOrEnd == 125) {
            _closeScope(_skipWSOrEnd);
            return false;
        }
        if (this.x.expectComma()) {
            _skipWSOrEnd = _skipComma(_skipWSOrEnd);
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
            char[] asQuotedChars = serializableString.asQuotedChars();
            int length = asQuotedChars.length;
            int i3 = this.f5090p;
            if (i3 + length + 4 < this.f5091q) {
                int i4 = length + i3;
                if (this.P[i4] == '\"') {
                    while (i3 != i4) {
                        if (asQuotedChars[i2] == this.P[i3]) {
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
        return p0(_skipWSOrEnd, serializableString.getValue());
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public final int nextIntValue(int i2) {
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
    public final long nextLongValue(long j2) {
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
    public final String nextTextValue() {
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
            if (this.U) {
                this.U = false;
                i0();
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

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0042, code lost:
        if (r0 == 125) goto L69;
     */
    @Override // com.fasterxml.jackson.core.base.ParserMinimalBase, com.fasterxml.jackson.core.JsonParser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final JsonToken nextToken() {
        JsonToken jsonToken;
        JsonToken jsonToken2 = this.f5104c;
        JsonToken jsonToken3 = JsonToken.FIELD_NAME;
        if (jsonToken2 == jsonToken3) {
            return _nextAfterName();
        }
        this.E = 0;
        if (this.U) {
            C0();
        }
        int _skipWSOrEnd = _skipWSOrEnd();
        if (_skipWSOrEnd < 0) {
            close();
            this.f5104c = null;
            return null;
        }
        this.D = null;
        if (_skipWSOrEnd != 93 && _skipWSOrEnd != 125) {
            if (this.x.expectComma()) {
                _skipWSOrEnd = _skipComma(_skipWSOrEnd);
                if ((this.f5048a & FEAT_MASK_TRAILING_COMMA) != 0) {
                    if (_skipWSOrEnd != 93) {
                    }
                }
            }
            boolean inObject = this.x.inObject();
            if (inObject) {
                _updateNameLocation();
                this.x.setCurrentName(_skipWSOrEnd == 34 ? v0() : n0(_skipWSOrEnd));
                this.f5104c = jsonToken3;
                _skipWSOrEnd = _skipColon();
            }
            _updateLocation();
            if (_skipWSOrEnd == 34) {
                this.U = true;
                jsonToken = JsonToken.VALUE_STRING;
            } else if (_skipWSOrEnd == 91) {
                if (!inObject) {
                    this.x = this.x.createChildArrayContext(this.v, this.w);
                }
                jsonToken = JsonToken.START_ARRAY;
            } else if (_skipWSOrEnd == 102) {
                _matchFalse();
                jsonToken = JsonToken.VALUE_FALSE;
            } else if (_skipWSOrEnd != 110) {
                if (_skipWSOrEnd != 116) {
                    if (_skipWSOrEnd == 123) {
                        if (!inObject) {
                            this.x = this.x.createChildObjectContext(this.v, this.w);
                        }
                        jsonToken = JsonToken.START_OBJECT;
                    } else if (_skipWSOrEnd == 125) {
                        s(_skipWSOrEnd, "expected a value");
                    } else if (_skipWSOrEnd == 45) {
                        jsonToken = w0();
                    } else if (_skipWSOrEnd != 46) {
                        switch (_skipWSOrEnd) {
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
                                jsonToken = x0(_skipWSOrEnd);
                                break;
                            default:
                                jsonToken = o0(_skipWSOrEnd);
                                break;
                        }
                    } else {
                        jsonToken = u0();
                    }
                }
                _matchTrue();
                jsonToken = JsonToken.VALUE_TRUE;
            } else {
                _matchNull();
                jsonToken = JsonToken.VALUE_NULL;
            }
            if (inObject) {
                this.y = jsonToken;
                return this.f5104c;
            }
            this.f5104c = jsonToken;
            return jsonToken;
        }
        _closeScope(_skipWSOrEnd);
        return this.f5104c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0017, code lost:
        if (r4 != 44) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0042, code lost:
        if (r3.x.inArray() == false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x004b, code lost:
        if (r3.x.inRoot() != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0052, code lost:
        if ((r3.f5048a & com.fasterxml.jackson.core.json.ReaderBasedJsonParser.FEAT_MASK_ALLOW_MISSING) == 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0054, code lost:
        r3.f5090p--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005b, code lost:
        return com.fasterxml.jackson.core.JsonToken.VALUE_NULL;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected JsonToken o0(int i2) {
        String str;
        if (i2 != 39) {
            if (i2 == 73) {
                s0("Infinity", 1);
                if ((this.f5048a & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                    return e0("Infinity", Double.POSITIVE_INFINITY);
                }
                str = "Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow";
            } else if (i2 == 78) {
                s0("NaN", 1);
                if ((this.f5048a & FEAT_MASK_NON_NUM_NUMBERS) != 0) {
                    return e0("NaN", Double.NaN);
                }
                str = "Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow";
            } else if (i2 != 93) {
                if (i2 == 43) {
                    if (this.f5090p >= this.f5091q && !q0()) {
                        q(JsonToken.VALUE_NUMBER_INT);
                    }
                    char[] cArr = this.P;
                    int i3 = this.f5090p;
                    this.f5090p = i3 + 1;
                    return m0(cArr[i3], false);
                }
            }
            k(str);
        } else if ((this.f5048a & FEAT_MASK_ALLOW_SINGLE_QUOTES) != 0) {
            return l0();
        }
        if (Character.isJavaIdentifierStart(i2)) {
            A0("" + ((char) i2), T());
        }
        s(i2, "expected a valid value " + U());
        return null;
    }

    protected boolean p0(int i2, String str) {
        JsonToken w0;
        String v0 = i2 == 34 ? v0() : n0(i2);
        this.x.setCurrentName(v0);
        this.f5104c = JsonToken.FIELD_NAME;
        int _skipColon = _skipColon();
        _updateLocation();
        if (_skipColon == 34) {
            this.U = true;
            w0 = JsonToken.VALUE_STRING;
        } else if (_skipColon == 45) {
            w0 = w0();
        } else if (_skipColon == 46) {
            w0 = u0();
        } else if (_skipColon == 91) {
            w0 = JsonToken.START_ARRAY;
        } else if (_skipColon == 102) {
            _matchFalse();
            w0 = JsonToken.VALUE_FALSE;
        } else if (_skipColon == 110) {
            _matchNull();
            w0 = JsonToken.VALUE_NULL;
        } else if (_skipColon == 116) {
            _matchTrue();
            w0 = JsonToken.VALUE_TRUE;
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
                    w0 = x0(_skipColon);
                    break;
                default:
                    w0 = o0(_skipColon);
                    break;
            }
        } else {
            w0 = JsonToken.START_OBJECT;
        }
        this.y = w0;
        return str.equals(v0);
    }

    protected boolean q0() {
        Reader reader = this.O;
        if (reader != null) {
            char[] cArr = this.P;
            int read = reader.read(cArr, 0, cArr.length);
            if (read > 0) {
                int i2 = this.f5091q;
                long j2 = i2;
                this.f5092r += j2;
                this.f5094t -= i2;
                this.V -= j2;
                this.f5090p = 0;
                this.f5091q = read;
                return true;
            }
            F();
            if (read == 0) {
                throw new IOException("Reader returned 0 characters when trying to read " + this.f5091q);
            }
        }
        return false;
    }

    protected void r0() {
        if (q0()) {
            return;
        }
        o();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
        if (!this.U || this.f5104c != JsonToken.VALUE_STRING) {
            byte[] binaryValue = getBinaryValue(base64Variant);
            outputStream.write(binaryValue);
            return binaryValue.length;
        }
        byte[] allocBase64Buffer = this.f5088n.allocBase64Buffer();
        try {
            return y0(base64Variant, outputStream, allocBase64Buffer);
        } finally {
            this.f5088n.releaseBase64Buffer(allocBase64Buffer);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int releaseBuffered(Writer writer) {
        int i2 = this.f5091q;
        int i3 = this.f5090p;
        int i4 = i2 - i3;
        if (i4 < 1) {
            return 0;
        }
        this.f5090p = i3 + i4;
        writer.write(this.P, i3, i4);
        return i4;
    }

    protected final void s0(String str, int i2) {
        int i3;
        int length = str.length();
        if (this.f5090p + length >= this.f5091q) {
            _matchToken2(str, i2);
            return;
        }
        do {
            if (this.P[this.f5090p] != str.charAt(i2)) {
                z0(str.substring(0, i2));
            }
            i3 = this.f5090p + 1;
            this.f5090p = i3;
            i2++;
        } while (i2 < length);
        char c2 = this.P[i3];
        if (c2 < '0' || c2 == ']' || c2 == '}') {
            return;
        }
        _checkMatchEnd(str, i2, c2);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this.R = objectCodec;
    }

    protected String t0() {
        int i2 = this.f5090p;
        int i3 = this.T;
        int i4 = this.f5091q;
        if (i2 < i4) {
            int[] iArr = Y;
            int length = iArr.length;
            do {
                char[] cArr = this.P;
                char c2 = cArr[i2];
                if (c2 != '\'') {
                    if (c2 < length && iArr[c2] != 0) {
                        break;
                    }
                    i3 = (i3 * 33) + c2;
                    i2++;
                } else {
                    int i5 = this.f5090p;
                    this.f5090p = i2 + 1;
                    return this.S.findSymbol(cArr, i5, i2 - i5, i3);
                }
            } while (i2 < i4);
        }
        int i6 = this.f5090p;
        this.f5090p = i2;
        return _parseName2(i6, i3, 39);
    }

    protected final JsonToken u0() {
        if (isEnabled(JsonReadFeature.ALLOW_LEADING_DECIMAL_POINT_FOR_NUMBERS.mappedFeature())) {
            int i2 = this.f5090p;
            return _parseFloat(46, i2 - 1, i2, false, 0);
        }
        return o0(46);
    }

    protected final String v0() {
        int i2 = this.f5090p;
        int i3 = this.T;
        int[] iArr = Y;
        while (true) {
            if (i2 >= this.f5091q) {
                break;
            }
            char[] cArr = this.P;
            char c2 = cArr[i2];
            if (c2 >= iArr.length || iArr[c2] == 0) {
                i3 = (i3 * 33) + c2;
                i2++;
            } else if (c2 == '\"') {
                int i4 = this.f5090p;
                this.f5090p = i2 + 1;
                return this.S.findSymbol(cArr, i4, i2 - i4, i3);
            }
        }
        int i5 = this.f5090p;
        this.f5090p = i2;
        return _parseName2(i5, i3, 34);
    }

    protected final JsonToken w0() {
        int i2 = this.f5090p;
        int i3 = i2 - 1;
        int i4 = this.f5091q;
        if (i2 >= i4) {
            return _parseNumber2(true, i3);
        }
        int i5 = i2 + 1;
        char c2 = this.P[i2];
        if (c2 > '9' || c2 < '0') {
            this.f5090p = i5;
            return m0(c2, true);
        } else if (c2 == '0') {
            return _parseNumber2(true, i3);
        } else {
            int i6 = 1;
            while (i5 < i4) {
                int i7 = i5 + 1;
                char c3 = this.P[i5];
                if (c3 < '0' || c3 > '9') {
                    if (c3 == '.' || c3 == 'e' || c3 == 'E') {
                        this.f5090p = i7;
                        return _parseFloat(c3, i3, i7, true, i6);
                    }
                    int i8 = i7 - 1;
                    this.f5090p = i8;
                    if (this.x.inRoot()) {
                        _verifyRootSpace(c3);
                    }
                    this.z.resetWithShared(this.P, i3, i8 - i3);
                    return g0(true, i6);
                }
                i6++;
                i5 = i7;
            }
            return _parseNumber2(true, i3);
        }
    }

    protected final JsonToken x0(int i2) {
        int i3 = this.f5090p;
        int i4 = i3 - 1;
        int i5 = this.f5091q;
        if (i2 == 48) {
            return _parseNumber2(false, i4);
        }
        int i6 = 1;
        while (i3 < i5) {
            int i7 = i3 + 1;
            char c2 = this.P[i3];
            if (c2 < '0' || c2 > '9') {
                if (c2 == '.' || c2 == 'e' || c2 == 'E') {
                    this.f5090p = i7;
                    return _parseFloat(c2, i4, i7, false, i6);
                }
                int i8 = i7 - 1;
                this.f5090p = i8;
                if (this.x.inRoot()) {
                    _verifyRootSpace(c2);
                }
                this.z.resetWithShared(this.P, i4, i8 - i4);
                return g0(false, i6);
            }
            i6++;
            i3 = i7;
        }
        this.f5090p = i4;
        return _parseNumber2(false, i4);
    }

    protected int y0(Base64Variant base64Variant, OutputStream outputStream, byte[] bArr) {
        int i2;
        int i3 = 3;
        int length = bArr.length - 3;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (this.f5090p >= this.f5091q) {
                r0();
            }
            char[] cArr = this.P;
            int i6 = this.f5090p;
            this.f5090p = i6 + 1;
            char c2 = cArr[i6];
            if (c2 > ' ') {
                int decodeBase64Char = base64Variant.decodeBase64Char(c2);
                if (decodeBase64Char < 0) {
                    if (c2 == '\"') {
                        break;
                    }
                    decodeBase64Char = G(base64Variant, c2, 0);
                    if (decodeBase64Char < 0) {
                    }
                }
                if (i4 > length) {
                    i5 += i4;
                    outputStream.write(bArr, 0, i4);
                    i4 = 0;
                }
                if (this.f5090p >= this.f5091q) {
                    r0();
                }
                char[] cArr2 = this.P;
                int i7 = this.f5090p;
                this.f5090p = i7 + 1;
                char c3 = cArr2[i7];
                int decodeBase64Char2 = base64Variant.decodeBase64Char(c3);
                if (decodeBase64Char2 < 0) {
                    decodeBase64Char2 = G(base64Variant, c3, 1);
                }
                int i8 = (decodeBase64Char << 6) | decodeBase64Char2;
                if (this.f5090p >= this.f5091q) {
                    r0();
                }
                char[] cArr3 = this.P;
                int i9 = this.f5090p;
                this.f5090p = i9 + 1;
                char c4 = cArr3[i9];
                int decodeBase64Char3 = base64Variant.decodeBase64Char(c4);
                if (decodeBase64Char3 < 0) {
                    if (decodeBase64Char3 != -2) {
                        if (c4 == '\"') {
                            int i10 = i4 + 1;
                            bArr[i4] = (byte) (i8 >> 4);
                            if (base64Variant.usesPadding()) {
                                this.f5090p--;
                                L(base64Variant);
                            }
                            i4 = i10;
                        } else {
                            decodeBase64Char3 = G(base64Variant, c4, 2);
                        }
                    }
                    if (decodeBase64Char3 == -2) {
                        if (this.f5090p >= this.f5091q) {
                            r0();
                        }
                        char[] cArr4 = this.P;
                        int i11 = this.f5090p;
                        this.f5090p = i11 + 1;
                        char c5 = cArr4[i11];
                        if (!base64Variant.usesPaddingChar(c5) && G(base64Variant, c5, i3) != -2) {
                            throw c0(base64Variant, c5, i3, "expected padding character '" + base64Variant.getPaddingChar() + "'");
                        }
                        bArr[i4] = (byte) (i8 >> 4);
                        i4++;
                    }
                }
                int i12 = (i8 << 6) | decodeBase64Char3;
                if (this.f5090p >= this.f5091q) {
                    r0();
                }
                char[] cArr5 = this.P;
                int i13 = this.f5090p;
                this.f5090p = i13 + 1;
                char c6 = cArr5[i13];
                int decodeBase64Char4 = base64Variant.decodeBase64Char(c6);
                if (decodeBase64Char4 < 0) {
                    if (decodeBase64Char4 == -2) {
                        i2 = 3;
                    } else if (c6 == '\"') {
                        int i14 = i12 >> 2;
                        int i15 = i4 + 1;
                        bArr[i4] = (byte) (i14 >> 8);
                        i4 = i15 + 1;
                        bArr[i15] = (byte) i14;
                        if (base64Variant.usesPadding()) {
                            this.f5090p--;
                            L(base64Variant);
                        }
                    } else {
                        i2 = 3;
                        decodeBase64Char4 = G(base64Variant, c6, 3);
                    }
                    if (decodeBase64Char4 == -2) {
                        int i16 = i12 >> 2;
                        int i17 = i4 + 1;
                        bArr[i4] = (byte) (i16 >> 8);
                        i4 = i17 + 1;
                        bArr[i17] = (byte) i16;
                        i3 = i2;
                    }
                } else {
                    i2 = 3;
                }
                int i18 = (i12 << 6) | decodeBase64Char4;
                int i19 = i4 + 1;
                bArr[i4] = (byte) (i18 >> 16);
                int i20 = i19 + 1;
                bArr[i19] = (byte) (i18 >> 8);
                bArr[i20] = (byte) i18;
                i4 = i20 + 1;
                i3 = i2;
            }
            i2 = i3;
            i3 = i2;
        }
        this.U = false;
        if (i4 > 0) {
            int i21 = i5 + i4;
            outputStream.write(bArr, 0, i4);
            return i21;
        }
        return i5;
    }

    protected void z0(String str) {
        A0(str, T());
    }
}
