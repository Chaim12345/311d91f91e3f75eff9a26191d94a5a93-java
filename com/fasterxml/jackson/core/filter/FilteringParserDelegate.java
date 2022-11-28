package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes.dex */
public class FilteringParserDelegate extends JsonParserDelegate {

    /* renamed from: d  reason: collision with root package name */
    protected TokenFilter f5117d;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f5118e;

    /* renamed from: f  reason: collision with root package name */
    protected boolean f5119f;
    @Deprecated

    /* renamed from: g  reason: collision with root package name */
    protected boolean f5120g;

    /* renamed from: h  reason: collision with root package name */
    protected JsonToken f5121h;

    /* renamed from: i  reason: collision with root package name */
    protected JsonToken f5122i;

    /* renamed from: j  reason: collision with root package name */
    protected TokenFilterContext f5123j;

    /* renamed from: k  reason: collision with root package name */
    protected TokenFilterContext f5124k;

    /* renamed from: l  reason: collision with root package name */
    protected TokenFilter f5125l;

    /* renamed from: m  reason: collision with root package name */
    protected int f5126m;

    public FilteringParserDelegate(JsonParser jsonParser, TokenFilter tokenFilter, boolean z, boolean z2) {
        super(jsonParser);
        this.f5117d = tokenFilter;
        this.f5125l = tokenFilter;
        this.f5123j = TokenFilterContext.createRootContext(tokenFilter);
        this.f5119f = z;
        this.f5118e = z2;
    }

    private JsonToken _nextBuffered(TokenFilterContext tokenFilterContext) {
        this.f5124k = tokenFilterContext;
        JsonToken nextTokenToRead = tokenFilterContext.nextTokenToRead();
        if (nextTokenToRead != null) {
            return nextTokenToRead;
        }
        while (tokenFilterContext != this.f5123j) {
            tokenFilterContext = this.f5124k.findChildOf(tokenFilterContext);
            this.f5124k = tokenFilterContext;
            if (tokenFilterContext == null) {
                throw b("Unexpected problem: chain of filtered context broken");
            }
            JsonToken nextTokenToRead2 = tokenFilterContext.nextTokenToRead();
            if (nextTokenToRead2 != null) {
                return nextTokenToRead2;
            }
        }
        throw b("Internal error: failed to locate expected buffered tokens");
    }

    private final boolean _verifyAllowedMatches() {
        int i2 = this.f5126m;
        if (i2 == 0 || this.f5118e) {
            this.f5126m = i2 + 1;
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public void clearCurrentToken() {
        JsonToken jsonToken = this.f5121h;
        if (jsonToken != null) {
            this.f5122i = jsonToken;
            this.f5121h = null;
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken currentToken() {
        return this.f5121h;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final int currentTokenId() {
        JsonToken jsonToken = this.f5121h;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    protected JsonStreamContext d() {
        TokenFilterContext tokenFilterContext = this.f5124k;
        return tokenFilterContext != null ? tokenFilterContext : this.f5123j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x0090, code lost:
        r1 = r6.f5123j.createChildArrayContext(r1, true);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final JsonToken e() {
        JsonToken nextToken;
        TokenFilter checkValue;
        TokenFilter includeProperty;
        JsonToken f2;
        JsonToken f3;
        TokenFilter tokenFilter;
        JsonToken f4;
        while (true) {
            nextToken = this.f5237c.nextToken();
            if (nextToken != null) {
                int id = nextToken.id();
                if (id == 1) {
                    tokenFilter = this.f5125l;
                    TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
                    if (tokenFilter == tokenFilter2) {
                        break;
                    }
                    if (tokenFilter != null && (tokenFilter = this.f5123j.checkValue(tokenFilter)) != null) {
                        if (tokenFilter != tokenFilter2) {
                            tokenFilter = tokenFilter.filterStartObject();
                        }
                        this.f5125l = tokenFilter;
                        if (tokenFilter == tokenFilter2) {
                            break;
                        }
                        TokenFilterContext createChildObjectContext = this.f5123j.createChildObjectContext(tokenFilter, false);
                        this.f5123j = createChildObjectContext;
                        if (this.f5119f && (f4 = f(createChildObjectContext)) != null) {
                            this.f5121h = f4;
                            return f4;
                        }
                    }
                    this.f5237c.skipChildren();
                } else {
                    if (id != 2) {
                        if (id == 3) {
                            TokenFilter tokenFilter3 = this.f5125l;
                            TokenFilter tokenFilter4 = TokenFilter.INCLUDE_ALL;
                            if (tokenFilter3 == tokenFilter4) {
                                break;
                            }
                            if (tokenFilter3 != null && (tokenFilter3 = this.f5123j.checkValue(tokenFilter3)) != null) {
                                if (tokenFilter3 != tokenFilter4) {
                                    tokenFilter3 = tokenFilter3.filterStartArray();
                                }
                                this.f5125l = tokenFilter3;
                                if (tokenFilter3 == tokenFilter4) {
                                    break;
                                }
                                TokenFilterContext createChildArrayContext = this.f5123j.createChildArrayContext(tokenFilter3, false);
                                this.f5123j = createChildArrayContext;
                                if (this.f5119f && (f3 = f(createChildArrayContext)) != null) {
                                    this.f5121h = f3;
                                    return f3;
                                }
                            }
                            this.f5237c.skipChildren();
                        } else if (id != 4) {
                            if (id != 5) {
                                TokenFilter tokenFilter5 = this.f5125l;
                                TokenFilter tokenFilter6 = TokenFilter.INCLUDE_ALL;
                                if (tokenFilter5 == tokenFilter6) {
                                    this.f5121h = nextToken;
                                    return nextToken;
                                } else if (tokenFilter5 != null && ((checkValue = this.f5123j.checkValue(tokenFilter5)) == tokenFilter6 || (checkValue != null && checkValue.includeValue(this.f5237c)))) {
                                    if (_verifyAllowedMatches()) {
                                        this.f5121h = nextToken;
                                        return nextToken;
                                    }
                                }
                            } else {
                                String currentName = this.f5237c.getCurrentName();
                                TokenFilter fieldName = this.f5123j.setFieldName(currentName);
                                TokenFilter tokenFilter7 = TokenFilter.INCLUDE_ALL;
                                if (fieldName == tokenFilter7) {
                                    this.f5125l = fieldName;
                                    break;
                                } else if (fieldName == null || (includeProperty = fieldName.includeProperty(currentName)) == null) {
                                    this.f5237c.nextToken();
                                    this.f5237c.skipChildren();
                                } else {
                                    this.f5125l = includeProperty;
                                    if (includeProperty == tokenFilter7) {
                                        if (_verifyAllowedMatches() && this.f5119f) {
                                            this.f5121h = nextToken;
                                            return nextToken;
                                        }
                                    } else if (this.f5119f && (f2 = f(this.f5123j)) != null) {
                                        this.f5121h = f2;
                                        return f2;
                                    }
                                }
                            }
                        }
                    }
                    boolean isStartHandled = this.f5123j.isStartHandled();
                    TokenFilter filter = this.f5123j.getFilter();
                    if (filter != null && filter != TokenFilter.INCLUDE_ALL) {
                        filter.filterFinishArray();
                    }
                    TokenFilterContext parent = this.f5123j.getParent();
                    this.f5123j = parent;
                    this.f5125l = parent.getFilter();
                    if (isStartHandled) {
                        this.f5121h = nextToken;
                        return nextToken;
                    }
                }
            } else {
                this.f5121h = nextToken;
                return nextToken;
            }
        }
        TokenFilterContext createChildArrayContext2 = this.f5123j.createChildObjectContext(tokenFilter, true);
        this.f5123j = createChildArrayContext2;
        this.f5121h = nextToken;
        return nextToken;
    }

    /* JADX WARN: Code restructure failed: missing block: B:33:0x005d, code lost:
        return _nextBuffered(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a9, code lost:
        r5.f5123j = r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected final JsonToken f(TokenFilterContext tokenFilterContext) {
        TokenFilter checkValue;
        TokenFilterContext createChildObjectContext;
        TokenFilterContext createChildObjectContext2;
        TokenFilter checkValue2;
        TokenFilter includeProperty;
        while (true) {
            JsonToken nextToken = this.f5237c.nextToken();
            if (nextToken == null) {
                return nextToken;
            }
            int id = nextToken.id();
            boolean z = false;
            if (id != 1) {
                if (id != 2) {
                    if (id == 3) {
                        TokenFilter checkValue3 = this.f5123j.checkValue(this.f5125l);
                        if (checkValue3 != null) {
                            TokenFilter tokenFilter = TokenFilter.INCLUDE_ALL;
                            if (checkValue3 != tokenFilter) {
                                checkValue3 = checkValue3.filterStartArray();
                            }
                            this.f5125l = checkValue3;
                            if (checkValue3 == tokenFilter) {
                                createChildObjectContext = this.f5123j.createChildArrayContext(checkValue3, true);
                                break;
                            }
                            createChildObjectContext2 = this.f5123j.createChildArrayContext(checkValue3, false);
                            this.f5123j = createChildObjectContext2;
                        }
                    } else if (id != 4) {
                        if (id != 5) {
                            TokenFilter tokenFilter2 = this.f5125l;
                            TokenFilter tokenFilter3 = TokenFilter.INCLUDE_ALL;
                            if (tokenFilter2 == tokenFilter3) {
                                return _nextBuffered(tokenFilterContext);
                            }
                            if (tokenFilter2 != null && ((checkValue2 = this.f5123j.checkValue(tokenFilter2)) == tokenFilter3 || (checkValue2 != null && checkValue2.includeValue(this.f5237c)))) {
                                if (_verifyAllowedMatches()) {
                                    return _nextBuffered(tokenFilterContext);
                                }
                            }
                        } else {
                            String currentName = this.f5237c.getCurrentName();
                            TokenFilter fieldName = this.f5123j.setFieldName(currentName);
                            TokenFilter tokenFilter4 = TokenFilter.INCLUDE_ALL;
                            if (fieldName == tokenFilter4) {
                                this.f5125l = fieldName;
                                break;
                            } else if (fieldName == null || (includeProperty = fieldName.includeProperty(currentName)) == null) {
                                this.f5237c.nextToken();
                            } else {
                                this.f5125l = includeProperty;
                                if (includeProperty != tokenFilter4) {
                                    continue;
                                } else if (_verifyAllowedMatches()) {
                                    return _nextBuffered(tokenFilterContext);
                                } else {
                                    this.f5125l = this.f5123j.setFieldName(currentName);
                                }
                            }
                        }
                    }
                    this.f5237c.skipChildren();
                }
                TokenFilter filter = this.f5123j.getFilter();
                if (filter != null && filter != TokenFilter.INCLUDE_ALL) {
                    filter.filterFinishArray();
                }
                TokenFilterContext tokenFilterContext2 = this.f5123j;
                if ((tokenFilterContext2 == tokenFilterContext) && tokenFilterContext2.isStartHandled()) {
                    z = true;
                }
                TokenFilterContext parent = this.f5123j.getParent();
                this.f5123j = parent;
                this.f5125l = parent.getFilter();
                if (z) {
                    return nextToken;
                }
            } else {
                TokenFilter tokenFilter5 = this.f5125l;
                TokenFilter tokenFilter6 = TokenFilter.INCLUDE_ALL;
                if (tokenFilter5 == tokenFilter6) {
                    this.f5123j = this.f5123j.createChildObjectContext(tokenFilter5, true);
                    return nextToken;
                }
                if (tokenFilter5 != null && (checkValue = this.f5123j.checkValue(tokenFilter5)) != null) {
                    if (checkValue != tokenFilter6) {
                        checkValue = checkValue.filterStartObject();
                    }
                    this.f5125l = checkValue;
                    TokenFilterContext tokenFilterContext3 = this.f5123j;
                    if (checkValue == tokenFilter6) {
                        createChildObjectContext = tokenFilterContext3.createChildObjectContext(checkValue, true);
                        break;
                    }
                    createChildObjectContext2 = tokenFilterContext3.createChildObjectContext(checkValue, false);
                    this.f5123j = createChildObjectContext2;
                }
                this.f5237c.skipChildren();
            }
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public BigInteger getBigIntegerValue() {
        return this.f5237c.getBigIntegerValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        return this.f5237c.getBinaryValue(base64Variant);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getBooleanValue() {
        return this.f5237c.getBooleanValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public byte getByteValue() {
        return this.f5237c.getByteValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return this.f5237c.getCurrentLocation();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getCurrentName() {
        JsonStreamContext d2 = d();
        JsonToken jsonToken = this.f5121h;
        if (jsonToken == JsonToken.START_OBJECT || jsonToken == JsonToken.START_ARRAY) {
            JsonStreamContext parent = d2.getParent();
            if (parent == null) {
                return null;
            }
            return parent.getCurrentName();
        }
        return d2.getCurrentName();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken getCurrentToken() {
        return this.f5121h;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final int getCurrentTokenId() {
        JsonToken jsonToken = this.f5121h;
        if (jsonToken == null) {
            return 0;
        }
        return jsonToken.id();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public BigDecimal getDecimalValue() {
        return this.f5237c.getDecimalValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getDoubleValue() {
        return this.f5237c.getDoubleValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public Object getEmbeddedObject() {
        return this.f5237c.getEmbeddedObject();
    }

    public TokenFilter getFilter() {
        return this.f5117d;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public float getFloatValue() {
        return this.f5237c.getFloatValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getIntValue() {
        return this.f5237c.getIntValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken getLastClearedToken() {
        return this.f5122i;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getLongValue() {
        return this.f5237c.getLongValue();
    }

    public int getMatchCount() {
        return this.f5126m;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonParser.NumberType getNumberType() {
        return this.f5237c.getNumberType();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public Number getNumberValue() {
        return this.f5237c.getNumberValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonStreamContext getParsingContext() {
        return d();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public short getShortValue() {
        return this.f5237c.getShortValue();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getText() {
        return this.f5237c.getText();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() {
        return this.f5237c.getTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getTextLength() {
        return this.f5237c.getTextLength();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getTextOffset() {
        return this.f5237c.getTextOffset();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        return this.f5237c.getTokenLocation();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean() {
        return this.f5237c.getValueAsBoolean();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean(boolean z) {
        return this.f5237c.getValueAsBoolean(z);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble() {
        return this.f5237c.getValueAsDouble();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble(double d2) {
        return this.f5237c.getValueAsDouble(d2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() {
        return this.f5237c.getValueAsInt();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i2) {
        return this.f5237c.getValueAsInt(i2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong() {
        return this.f5237c.getValueAsLong();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong(long j2) {
        return this.f5237c.getValueAsLong(j2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() {
        return this.f5237c.getValueAsString();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) {
        return this.f5237c.getValueAsString(str);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasCurrentToken() {
        return this.f5121h != null;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasTextCharacters() {
        return this.f5237c.hasTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public final boolean hasToken(JsonToken jsonToken) {
        return this.f5121h == jsonToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean hasTokenId(int i2) {
        JsonToken jsonToken = this.f5121h;
        return jsonToken == null ? i2 == 0 : jsonToken.id() == i2;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartArrayToken() {
        return this.f5121h == JsonToken.START_ARRAY;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartObjectToken() {
        return this.f5121h == JsonToken.START_OBJECT;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() {
        JsonToken nextToken;
        JsonToken f2;
        TokenFilterContext createChildObjectContext;
        JsonToken f3;
        TokenFilter includeProperty;
        JsonToken f4;
        TokenFilter checkValue;
        JsonToken jsonToken;
        if (!this.f5118e && (jsonToken = this.f5121h) != null && this.f5124k == null && jsonToken.isScalarValue() && !this.f5123j.isStartHandled() && !this.f5119f && this.f5125l == TokenFilter.INCLUDE_ALL) {
            this.f5121h = null;
            return null;
        }
        TokenFilterContext tokenFilterContext = this.f5124k;
        if (tokenFilterContext != null) {
            do {
                JsonToken nextTokenToRead = tokenFilterContext.nextTokenToRead();
                if (nextTokenToRead != null) {
                    this.f5121h = nextTokenToRead;
                    return nextTokenToRead;
                }
                TokenFilterContext tokenFilterContext2 = this.f5123j;
                if (tokenFilterContext == tokenFilterContext2) {
                    this.f5124k = null;
                    if (tokenFilterContext.inArray()) {
                        nextToken = this.f5237c.getCurrentToken();
                        this.f5121h = nextToken;
                        return nextToken;
                    }
                } else {
                    tokenFilterContext = tokenFilterContext2.findChildOf(tokenFilterContext);
                    this.f5124k = tokenFilterContext;
                }
            } while (tokenFilterContext != null);
            throw b("Unexpected problem: chain of filtered context broken");
        }
        nextToken = this.f5237c.nextToken();
        if (nextToken == null) {
            this.f5121h = nextToken;
            return nextToken;
        }
        int id = nextToken.id();
        if (id == 1) {
            TokenFilter tokenFilter = this.f5125l;
            TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
            if (tokenFilter != tokenFilter2) {
                if (tokenFilter != null && (tokenFilter = this.f5123j.checkValue(tokenFilter)) != null) {
                    if (tokenFilter != tokenFilter2) {
                        tokenFilter = tokenFilter.filterStartObject();
                    }
                    this.f5125l = tokenFilter;
                    if (tokenFilter != tokenFilter2) {
                        TokenFilterContext createChildObjectContext2 = this.f5123j.createChildObjectContext(tokenFilter, false);
                        this.f5123j = createChildObjectContext2;
                        if (this.f5119f && (f2 = f(createChildObjectContext2)) != null) {
                            this.f5121h = f2;
                            return f2;
                        }
                        return e();
                    }
                }
                this.f5237c.skipChildren();
                return e();
            }
            createChildObjectContext = this.f5123j.createChildObjectContext(tokenFilter, true);
            this.f5123j = createChildObjectContext;
            this.f5121h = nextToken;
            return nextToken;
        }
        if (id != 2) {
            if (id == 3) {
                TokenFilter tokenFilter3 = this.f5125l;
                TokenFilter tokenFilter4 = TokenFilter.INCLUDE_ALL;
                if (tokenFilter3 != tokenFilter4) {
                    if (tokenFilter3 != null && (tokenFilter3 = this.f5123j.checkValue(tokenFilter3)) != null) {
                        if (tokenFilter3 != tokenFilter4) {
                            tokenFilter3 = tokenFilter3.filterStartArray();
                        }
                        this.f5125l = tokenFilter3;
                        if (tokenFilter3 != tokenFilter4) {
                            TokenFilterContext createChildArrayContext = this.f5123j.createChildArrayContext(tokenFilter3, false);
                            this.f5123j = createChildArrayContext;
                            if (this.f5119f && (f3 = f(createChildArrayContext)) != null) {
                                this.f5121h = f3;
                                return f3;
                            }
                        }
                    }
                    this.f5237c.skipChildren();
                }
                createChildObjectContext = this.f5123j.createChildArrayContext(tokenFilter3, true);
                this.f5123j = createChildObjectContext;
                this.f5121h = nextToken;
                return nextToken;
            } else if (id != 4) {
                if (id != 5) {
                    TokenFilter tokenFilter5 = this.f5125l;
                    TokenFilter tokenFilter6 = TokenFilter.INCLUDE_ALL;
                    if (tokenFilter5 == tokenFilter6) {
                        this.f5121h = nextToken;
                        return nextToken;
                    } else if (tokenFilter5 != null && (((checkValue = this.f5123j.checkValue(tokenFilter5)) == tokenFilter6 || (checkValue != null && checkValue.includeValue(this.f5237c))) && _verifyAllowedMatches())) {
                        this.f5121h = nextToken;
                        return nextToken;
                    }
                } else {
                    String currentName = this.f5237c.getCurrentName();
                    TokenFilter fieldName = this.f5123j.setFieldName(currentName);
                    TokenFilter tokenFilter7 = TokenFilter.INCLUDE_ALL;
                    if (fieldName == tokenFilter7) {
                        this.f5125l = fieldName;
                        if (!this.f5119f && this.f5120g && !this.f5123j.isStartHandled()) {
                            nextToken = this.f5123j.nextTokenToRead();
                            this.f5124k = this.f5123j;
                        }
                        this.f5121h = nextToken;
                        return nextToken;
                    } else if (fieldName == null || (includeProperty = fieldName.includeProperty(currentName)) == null) {
                        this.f5237c.nextToken();
                        this.f5237c.skipChildren();
                    } else {
                        this.f5125l = includeProperty;
                        if (includeProperty == tokenFilter7) {
                            if (!_verifyAllowedMatches()) {
                                this.f5237c.nextToken();
                                this.f5237c.skipChildren();
                            } else if (this.f5119f) {
                                this.f5121h = nextToken;
                                return nextToken;
                            }
                        }
                        if (this.f5119f && (f4 = f(this.f5123j)) != null) {
                            this.f5121h = f4;
                            return f4;
                        }
                    }
                }
            }
            return e();
        }
        boolean isStartHandled = this.f5123j.isStartHandled();
        TokenFilter filter = this.f5123j.getFilter();
        if (filter != null && filter != TokenFilter.INCLUDE_ALL) {
            filter.filterFinishArray();
        }
        TokenFilterContext parent = this.f5123j.getParent();
        this.f5123j = parent;
        this.f5125l = parent.getFilter();
        if (isStartHandled) {
            this.f5121h = nextToken;
            return nextToken;
        }
        return e();
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextValue() {
        JsonToken nextToken = nextToken();
        return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public void overrideCurrentName(String str) {
        throw new UnsupportedOperationException("Can not currently override name during filtering read");
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
        return this.f5237c.readBinaryValue(base64Variant, outputStream);
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonParser skipChildren() {
        JsonToken jsonToken = this.f5121h;
        if (jsonToken != JsonToken.START_OBJECT && jsonToken != JsonToken.START_ARRAY) {
            return this;
        }
        int i2 = 1;
        while (true) {
            JsonToken nextToken = nextToken();
            if (nextToken == null) {
                return this;
            }
            if (nextToken.isStructStart()) {
                i2++;
            } else if (nextToken.isStructEnd() && i2 - 1 == 0) {
                return this;
            }
        }
    }
}
