package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
/* loaded from: classes.dex */
public class JsonFactoryBuilder extends TSFBuilder<JsonFactory, JsonFactoryBuilder> {

    /* renamed from: i  reason: collision with root package name */
    protected CharacterEscapes f5036i;

    /* renamed from: j  reason: collision with root package name */
    protected SerializableString f5037j;

    /* renamed from: k  reason: collision with root package name */
    protected int f5038k;

    /* renamed from: l  reason: collision with root package name */
    protected char f5039l;

    public JsonFactoryBuilder() {
        this.f5039l = '\"';
        this.f5037j = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
        this.f5038k = 0;
    }

    public JsonFactoryBuilder(JsonFactory jsonFactory) {
        super(jsonFactory);
        this.f5039l = '\"';
        this.f5036i = jsonFactory.getCharacterEscapes();
        this.f5037j = jsonFactory.f5033j;
        this.f5038k = jsonFactory.f5034k;
    }

    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactory build() {
        return new JsonFactory(this);
    }

    public JsonFactoryBuilder characterEscapes(CharacterEscapes characterEscapes) {
        this.f5036i = characterEscapes;
        return this;
    }

    public CharacterEscapes characterEscapes() {
        return this.f5036i;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactoryBuilder configure(JsonReadFeature jsonReadFeature, boolean z) {
        return z ? enable(jsonReadFeature) : disable(jsonReadFeature);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactoryBuilder configure(JsonWriteFeature jsonWriteFeature, boolean z) {
        return z ? enable(jsonWriteFeature) : disable(jsonWriteFeature);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactoryBuilder disable(JsonReadFeature jsonReadFeature) {
        b(jsonReadFeature.mappedFeature());
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactoryBuilder disable(JsonReadFeature jsonReadFeature, JsonReadFeature... jsonReadFeatureArr) {
        b(jsonReadFeature.mappedFeature());
        for (JsonReadFeature jsonReadFeature2 : jsonReadFeatureArr) {
            d(jsonReadFeature2.mappedFeature());
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactoryBuilder disable(JsonWriteFeature jsonWriteFeature) {
        a(jsonWriteFeature.mappedFeature());
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactoryBuilder disable(JsonWriteFeature jsonWriteFeature, JsonWriteFeature... jsonWriteFeatureArr) {
        a(jsonWriteFeature.mappedFeature());
        for (JsonWriteFeature jsonWriteFeature2 : jsonWriteFeatureArr) {
            a(jsonWriteFeature2.mappedFeature());
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactoryBuilder enable(JsonReadFeature jsonReadFeature) {
        d(jsonReadFeature.mappedFeature());
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactoryBuilder enable(JsonReadFeature jsonReadFeature, JsonReadFeature... jsonReadFeatureArr) {
        d(jsonReadFeature.mappedFeature());
        enable(jsonReadFeature);
        for (JsonReadFeature jsonReadFeature2 : jsonReadFeatureArr) {
            d(jsonReadFeature2.mappedFeature());
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactoryBuilder enable(JsonWriteFeature jsonWriteFeature) {
        JsonGenerator.Feature mappedFeature = jsonWriteFeature.mappedFeature();
        if (mappedFeature != null) {
            c(mappedFeature);
        }
        return this;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.TSFBuilder
    public JsonFactoryBuilder enable(JsonWriteFeature jsonWriteFeature, JsonWriteFeature... jsonWriteFeatureArr) {
        c(jsonWriteFeature.mappedFeature());
        for (JsonWriteFeature jsonWriteFeature2 : jsonWriteFeatureArr) {
            c(jsonWriteFeature2.mappedFeature());
        }
        return this;
    }

    public int highestNonEscapedChar() {
        return this.f5038k;
    }

    public JsonFactoryBuilder highestNonEscapedChar(int i2) {
        this.f5038k = i2 <= 0 ? 0 : Math.max(127, i2);
        return this;
    }

    public char quoteChar() {
        return this.f5039l;
    }

    public JsonFactoryBuilder quoteChar(char c2) {
        if (c2 <= 127) {
            this.f5039l = c2;
            return this;
        }
        throw new IllegalArgumentException("Can only use Unicode characters up to 0x7F as quote characters");
    }

    public JsonFactoryBuilder rootValueSeparator(SerializableString serializableString) {
        this.f5037j = serializableString;
        return this;
    }

    public JsonFactoryBuilder rootValueSeparator(String str) {
        this.f5037j = str == null ? null : new SerializedString(str);
        return this;
    }

    public SerializableString rootValueSeparator() {
        return this.f5037j;
    }
}
