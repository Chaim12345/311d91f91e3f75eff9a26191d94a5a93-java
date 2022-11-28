package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
public class TokenFilterContext extends JsonStreamContext {

    /* renamed from: c  reason: collision with root package name */
    protected final TokenFilterContext f5128c;

    /* renamed from: d  reason: collision with root package name */
    protected TokenFilterContext f5129d;

    /* renamed from: e  reason: collision with root package name */
    protected String f5130e;

    /* renamed from: f  reason: collision with root package name */
    protected TokenFilter f5131f;

    /* renamed from: g  reason: collision with root package name */
    protected boolean f5132g;

    /* renamed from: h  reason: collision with root package name */
    protected boolean f5133h;

    protected TokenFilterContext(int i2, TokenFilterContext tokenFilterContext, TokenFilter tokenFilter, boolean z) {
        this.f5057a = i2;
        this.f5128c = tokenFilterContext;
        this.f5131f = tokenFilter;
        this.f5058b = -1;
        this.f5132g = z;
        this.f5133h = false;
    }

    private void _writePath(JsonGenerator jsonGenerator) {
        TokenFilter tokenFilter = this.f5131f;
        if (tokenFilter == null || tokenFilter == TokenFilter.INCLUDE_ALL) {
            return;
        }
        TokenFilterContext tokenFilterContext = this.f5128c;
        if (tokenFilterContext != null) {
            tokenFilterContext._writePath(jsonGenerator);
        }
        if (!this.f5132g) {
            this.f5132g = true;
            int i2 = this.f5057a;
            if (i2 != 2) {
                if (i2 == 1) {
                    jsonGenerator.writeStartArray();
                    return;
                }
                return;
            }
            jsonGenerator.writeStartObject();
            if (!this.f5133h) {
                return;
            }
        } else if (!this.f5133h) {
            return;
        }
        this.f5133h = false;
        jsonGenerator.writeFieldName(this.f5130e);
    }

    public static TokenFilterContext createRootContext(TokenFilter tokenFilter) {
        return new TokenFilterContext(0, null, tokenFilter, true);
    }

    protected void a(StringBuilder sb) {
        char c2;
        char c3;
        TokenFilterContext tokenFilterContext = this.f5128c;
        if (tokenFilterContext != null) {
            tokenFilterContext.a(sb);
        }
        int i2 = this.f5057a;
        if (i2 == 2) {
            sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
            if (this.f5130e != null) {
                c3 = '\"';
                sb.append('\"');
                sb.append(this.f5130e);
            } else {
                c3 = '?';
            }
            sb.append(c3);
            c2 = AbstractJsonLexerKt.END_OBJ;
        } else if (i2 != 1) {
            sb.append("/");
            return;
        } else {
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(getCurrentIndex());
            c2 = AbstractJsonLexerKt.END_LIST;
        }
        sb.append(c2);
    }

    protected TokenFilterContext b(int i2, TokenFilter tokenFilter, boolean z) {
        this.f5057a = i2;
        this.f5131f = tokenFilter;
        this.f5058b = -1;
        this.f5130e = null;
        this.f5132g = z;
        this.f5133h = false;
        return this;
    }

    public TokenFilter checkValue(TokenFilter tokenFilter) {
        int i2 = this.f5057a;
        if (i2 == 2) {
            return tokenFilter;
        }
        int i3 = this.f5058b + 1;
        this.f5058b = i3;
        return i2 == 1 ? tokenFilter.includeElement(i3) : tokenFilter.includeRootValue(i3);
    }

    public TokenFilterContext closeArray(JsonGenerator jsonGenerator) {
        if (this.f5132g) {
            jsonGenerator.writeEndArray();
        }
        TokenFilter tokenFilter = this.f5131f;
        if (tokenFilter != null && tokenFilter != TokenFilter.INCLUDE_ALL) {
            tokenFilter.filterFinishArray();
        }
        return this.f5128c;
    }

    public TokenFilterContext closeObject(JsonGenerator jsonGenerator) {
        if (this.f5132g) {
            jsonGenerator.writeEndObject();
        }
        TokenFilter tokenFilter = this.f5131f;
        if (tokenFilter != null && tokenFilter != TokenFilter.INCLUDE_ALL) {
            tokenFilter.filterFinishObject();
        }
        return this.f5128c;
    }

    public TokenFilterContext createChildArrayContext(TokenFilter tokenFilter, boolean z) {
        TokenFilterContext tokenFilterContext = this.f5129d;
        if (tokenFilterContext == null) {
            TokenFilterContext tokenFilterContext2 = new TokenFilterContext(1, this, tokenFilter, z);
            this.f5129d = tokenFilterContext2;
            return tokenFilterContext2;
        }
        return tokenFilterContext.b(1, tokenFilter, z);
    }

    public TokenFilterContext createChildObjectContext(TokenFilter tokenFilter, boolean z) {
        TokenFilterContext tokenFilterContext = this.f5129d;
        if (tokenFilterContext == null) {
            TokenFilterContext tokenFilterContext2 = new TokenFilterContext(2, this, tokenFilter, z);
            this.f5129d = tokenFilterContext2;
            return tokenFilterContext2;
        }
        return tokenFilterContext.b(2, tokenFilter, z);
    }

    public TokenFilterContext findChildOf(TokenFilterContext tokenFilterContext) {
        TokenFilterContext tokenFilterContext2 = this.f5128c;
        if (tokenFilterContext2 == tokenFilterContext) {
            return this;
        }
        while (tokenFilterContext2 != null) {
            TokenFilterContext tokenFilterContext3 = tokenFilterContext2.f5128c;
            if (tokenFilterContext3 == tokenFilterContext) {
                return tokenFilterContext2;
            }
            tokenFilterContext2 = tokenFilterContext3;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public final String getCurrentName() {
        return this.f5130e;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public Object getCurrentValue() {
        return null;
    }

    public TokenFilter getFilter() {
        return this.f5131f;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public final TokenFilterContext getParent() {
        return this.f5128c;
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public boolean hasCurrentName() {
        return this.f5130e != null;
    }

    public boolean isStartHandled() {
        return this.f5132g;
    }

    public JsonToken nextTokenToRead() {
        if (!this.f5132g) {
            this.f5132g = true;
            return this.f5057a == 2 ? JsonToken.START_OBJECT : JsonToken.START_ARRAY;
        } else if (this.f5133h && this.f5057a == 2) {
            this.f5133h = false;
            return JsonToken.FIELD_NAME;
        } else {
            return null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public void setCurrentValue(Object obj) {
    }

    public TokenFilter setFieldName(String str) {
        this.f5130e = str;
        this.f5133h = true;
        return this.f5131f;
    }

    public void skipParentChecks() {
        this.f5131f = null;
        for (TokenFilterContext tokenFilterContext = this.f5128c; tokenFilterContext != null; tokenFilterContext = tokenFilterContext.f5128c) {
            this.f5128c.f5131f = null;
        }
    }

    @Override // com.fasterxml.jackson.core.JsonStreamContext
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        a(sb);
        return sb.toString();
    }

    public void writeImmediatePath(JsonGenerator jsonGenerator) {
        TokenFilter tokenFilter = this.f5131f;
        if (tokenFilter == null || tokenFilter == TokenFilter.INCLUDE_ALL) {
            return;
        }
        if (!this.f5132g) {
            this.f5132g = true;
            int i2 = this.f5057a;
            if (i2 != 2) {
                if (i2 == 1) {
                    jsonGenerator.writeStartArray();
                    return;
                }
                return;
            }
            jsonGenerator.writeStartObject();
            if (!this.f5133h) {
                return;
            }
        } else if (!this.f5133h) {
            return;
        }
        jsonGenerator.writeFieldName(this.f5130e);
    }

    public void writePath(JsonGenerator jsonGenerator) {
        TokenFilter tokenFilter = this.f5131f;
        if (tokenFilter == null || tokenFilter == TokenFilter.INCLUDE_ALL) {
            return;
        }
        TokenFilterContext tokenFilterContext = this.f5128c;
        if (tokenFilterContext != null) {
            tokenFilterContext._writePath(jsonGenerator);
        }
        if (!this.f5132g) {
            this.f5132g = true;
            int i2 = this.f5057a;
            if (i2 != 2) {
                if (i2 == 1) {
                    jsonGenerator.writeStartArray();
                    return;
                }
                return;
            }
            jsonGenerator.writeStartObject();
        } else if (!this.f5133h) {
            return;
        }
        jsonGenerator.writeFieldName(this.f5130e);
    }
}
