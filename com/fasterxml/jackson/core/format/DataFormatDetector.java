package com.fasterxml.jackson.core.format;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.format.InputAccessor;
import java.io.InputStream;
import java.util.Collection;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
public class DataFormatDetector {
    public static final int DEFAULT_MAX_INPUT_LOOKAHEAD = 64;

    /* renamed from: a  reason: collision with root package name */
    protected final JsonFactory[] f5134a;

    /* renamed from: b  reason: collision with root package name */
    protected final MatchStrength f5135b;

    /* renamed from: c  reason: collision with root package name */
    protected final MatchStrength f5136c;

    /* renamed from: d  reason: collision with root package name */
    protected final int f5137d;

    public DataFormatDetector(Collection<JsonFactory> collection) {
        this((JsonFactory[]) collection.toArray(new JsonFactory[0]));
    }

    public DataFormatDetector(JsonFactory... jsonFactoryArr) {
        this(jsonFactoryArr, MatchStrength.SOLID_MATCH, MatchStrength.WEAK_MATCH, 64);
    }

    private DataFormatDetector(JsonFactory[] jsonFactoryArr, MatchStrength matchStrength, MatchStrength matchStrength2, int i2) {
        this.f5134a = jsonFactoryArr;
        this.f5135b = matchStrength;
        this.f5136c = matchStrength2;
        this.f5137d = i2;
    }

    private DataFormatMatcher _findFormat(InputAccessor.Std std) {
        JsonFactory[] jsonFactoryArr = this.f5134a;
        int length = jsonFactoryArr.length;
        JsonFactory jsonFactory = null;
        int i2 = 0;
        MatchStrength matchStrength = null;
        while (true) {
            if (i2 >= length) {
                break;
            }
            JsonFactory jsonFactory2 = jsonFactoryArr[i2];
            std.reset();
            MatchStrength hasFormat = jsonFactory2.hasFormat(std);
            if (hasFormat != null && hasFormat.ordinal() >= this.f5136c.ordinal() && (jsonFactory == null || matchStrength.ordinal() < hasFormat.ordinal())) {
                if (hasFormat.ordinal() >= this.f5135b.ordinal()) {
                    jsonFactory = jsonFactory2;
                    matchStrength = hasFormat;
                    break;
                }
                jsonFactory = jsonFactory2;
                matchStrength = hasFormat;
            }
            i2++;
        }
        return std.createMatcher(jsonFactory, matchStrength);
    }

    public DataFormatMatcher findFormat(InputStream inputStream) {
        return _findFormat(new InputAccessor.Std(inputStream, new byte[this.f5137d]));
    }

    public DataFormatMatcher findFormat(byte[] bArr) {
        return _findFormat(new InputAccessor.Std(bArr));
    }

    public DataFormatMatcher findFormat(byte[] bArr, int i2, int i3) {
        return _findFormat(new InputAccessor.Std(bArr, i2, i3));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AbstractJsonLexerKt.BEGIN_LIST);
        JsonFactory[] jsonFactoryArr = this.f5134a;
        int length = jsonFactoryArr.length;
        if (length > 0) {
            sb.append(jsonFactoryArr[0].getFormatName());
            for (int i2 = 1; i2 < length; i2++) {
                sb.append(", ");
                sb.append(this.f5134a[i2].getFormatName());
            }
        }
        sb.append(AbstractJsonLexerKt.END_LIST);
        return sb.toString();
    }

    public DataFormatDetector withMaxInputLookahead(int i2) {
        return i2 == this.f5137d ? this : new DataFormatDetector(this.f5134a, this.f5135b, this.f5136c, i2);
    }

    public DataFormatDetector withMinimalMatch(MatchStrength matchStrength) {
        return matchStrength == this.f5136c ? this : new DataFormatDetector(this.f5134a, this.f5135b, matchStrength, this.f5137d);
    }

    public DataFormatDetector withOptimalMatch(MatchStrength matchStrength) {
        return matchStrength == this.f5135b ? this : new DataFormatDetector(this.f5134a, matchStrength, this.f5136c, this.f5137d);
    }
}
