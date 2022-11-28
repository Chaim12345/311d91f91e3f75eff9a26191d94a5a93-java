package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class JsonParserSequence extends JsonParserDelegate {

    /* renamed from: d  reason: collision with root package name */
    protected final JsonParser[] f5238d;

    /* renamed from: e  reason: collision with root package name */
    protected final boolean f5239e;

    /* renamed from: f  reason: collision with root package name */
    protected int f5240f;

    /* renamed from: g  reason: collision with root package name */
    protected boolean f5241g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    protected JsonParserSequence(boolean z, JsonParser[] jsonParserArr) {
        super(jsonParserArr[0]);
        boolean z2 = false;
        this.f5239e = z;
        if (z && this.f5237c.hasCurrentToken()) {
            z2 = true;
        }
        this.f5241g = z2;
        this.f5238d = jsonParserArr;
        this.f5240f = 1;
    }

    @Deprecated
    public static JsonParserSequence createFlattened(JsonParser jsonParser, JsonParser jsonParser2) {
        return createFlattened(false, jsonParser, jsonParser2);
    }

    public static JsonParserSequence createFlattened(boolean z, JsonParser jsonParser, JsonParser jsonParser2) {
        boolean z2 = jsonParser instanceof JsonParserSequence;
        if (z2 || (jsonParser2 instanceof JsonParserSequence)) {
            ArrayList arrayList = new ArrayList();
            if (z2) {
                ((JsonParserSequence) jsonParser).d(arrayList);
            } else {
                arrayList.add(jsonParser);
            }
            if (jsonParser2 instanceof JsonParserSequence) {
                ((JsonParserSequence) jsonParser2).d(arrayList);
            } else {
                arrayList.add(jsonParser2);
            }
            return new JsonParserSequence(z, (JsonParser[]) arrayList.toArray(new JsonParser[arrayList.size()]));
        }
        return new JsonParserSequence(z, new JsonParser[]{jsonParser, jsonParser2});
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        do {
            this.f5237c.close();
        } while (f());
    }

    public int containedParsersCount() {
        return this.f5238d.length;
    }

    protected void d(List list) {
        int length = this.f5238d.length;
        for (int i2 = this.f5240f - 1; i2 < length; i2++) {
            JsonParser jsonParser = this.f5238d[i2];
            if (jsonParser instanceof JsonParserSequence) {
                ((JsonParserSequence) jsonParser).d(list);
            } else {
                list.add(jsonParser);
            }
        }
    }

    protected JsonToken e() {
        JsonToken nextToken;
        do {
            int i2 = this.f5240f;
            JsonParser[] jsonParserArr = this.f5238d;
            if (i2 >= jsonParserArr.length) {
                return null;
            }
            this.f5240f = i2 + 1;
            JsonParser jsonParser = jsonParserArr[i2];
            this.f5237c = jsonParser;
            if (this.f5239e && jsonParser.hasCurrentToken()) {
                return this.f5237c.getCurrentToken();
            }
            nextToken = this.f5237c.nextToken();
        } while (nextToken == null);
        return nextToken;
    }

    protected boolean f() {
        int i2 = this.f5240f;
        JsonParser[] jsonParserArr = this.f5238d;
        if (i2 < jsonParserArr.length) {
            this.f5240f = i2 + 1;
            this.f5237c = jsonParserArr[i2];
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() {
        JsonParser jsonParser = this.f5237c;
        if (jsonParser == null) {
            return null;
        }
        if (this.f5241g) {
            this.f5241g = false;
            return jsonParser.currentToken();
        }
        JsonToken nextToken = jsonParser.nextToken();
        return nextToken == null ? e() : nextToken;
    }

    @Override // com.fasterxml.jackson.core.util.JsonParserDelegate, com.fasterxml.jackson.core.JsonParser
    public JsonParser skipChildren() {
        if (this.f5237c.currentToken() != JsonToken.START_OBJECT && this.f5237c.currentToken() != JsonToken.START_ARRAY) {
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
