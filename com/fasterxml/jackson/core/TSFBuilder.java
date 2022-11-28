package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TSFBuilder;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
/* loaded from: classes.dex */
public abstract class TSFBuilder<F extends JsonFactory, B extends TSFBuilder<F, B>> {

    /* renamed from: f  reason: collision with root package name */
    protected static final int f5068f = JsonFactory.Feature.collectDefaults();

    /* renamed from: g  reason: collision with root package name */
    protected static final int f5069g = JsonParser.Feature.collectDefaults();

    /* renamed from: h  reason: collision with root package name */
    protected static final int f5070h = JsonGenerator.Feature.collectDefaults();

    /* renamed from: a  reason: collision with root package name */
    protected int f5071a;

    /* renamed from: b  reason: collision with root package name */
    protected int f5072b;

    /* renamed from: c  reason: collision with root package name */
    protected int f5073c;

    /* renamed from: d  reason: collision with root package name */
    protected InputDecorator f5074d;

    /* renamed from: e  reason: collision with root package name */
    protected OutputDecorator f5075e;

    /* JADX INFO: Access modifiers changed from: protected */
    public TSFBuilder() {
        this.f5071a = f5068f;
        this.f5072b = f5069g;
        this.f5073c = f5070h;
        this.f5074d = null;
        this.f5075e = null;
    }

    protected TSFBuilder(int i2, int i3, int i4) {
        this.f5071a = i2;
        this.f5072b = i3;
        this.f5073c = i4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TSFBuilder(JsonFactory jsonFactory) {
        this(jsonFactory.f5026c, jsonFactory.f5027d, jsonFactory.f5028e);
    }

    private B _failNonJSON(Object obj) {
        throw new IllegalArgumentException("Feature " + obj.getClass().getName() + "#" + obj.toString() + " not supported for non-JSON backend");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(JsonGenerator.Feature feature) {
        if (feature != null) {
            this.f5073c = (~feature.getMask()) & this.f5073c;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(JsonParser.Feature feature) {
        if (feature != null) {
            this.f5072b = (~feature.getMask()) & this.f5072b;
        }
    }

    public abstract F build();

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(JsonGenerator.Feature feature) {
        if (feature != null) {
            this.f5073c = feature.getMask() | this.f5073c;
        }
    }

    public B configure(JsonFactory.Feature feature, boolean z) {
        return z ? enable(feature) : disable(feature);
    }

    public B configure(StreamReadFeature streamReadFeature, boolean z) {
        return z ? enable(streamReadFeature) : disable(streamReadFeature);
    }

    public B configure(StreamWriteFeature streamWriteFeature, boolean z) {
        return z ? enable(streamWriteFeature) : disable(streamWriteFeature);
    }

    public B configure(JsonReadFeature jsonReadFeature, boolean z) {
        return _failNonJSON(jsonReadFeature);
    }

    public B configure(JsonWriteFeature jsonWriteFeature, boolean z) {
        return _failNonJSON(jsonWriteFeature);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(JsonParser.Feature feature) {
        if (feature != null) {
            this.f5072b = feature.getMask() | this.f5072b;
        }
    }

    public B disable(JsonFactory.Feature feature) {
        this.f5071a = (~feature.getMask()) & this.f5071a;
        return (B) e();
    }

    public B disable(StreamReadFeature streamReadFeature) {
        this.f5072b = (~streamReadFeature.mappedFeature().getMask()) & this.f5072b;
        return (B) e();
    }

    public B disable(StreamReadFeature streamReadFeature, StreamReadFeature... streamReadFeatureArr) {
        this.f5072b = (~streamReadFeature.mappedFeature().getMask()) & this.f5072b;
        for (StreamReadFeature streamReadFeature2 : streamReadFeatureArr) {
            this.f5072b = (~streamReadFeature2.mappedFeature().getMask()) & this.f5072b;
        }
        return (B) e();
    }

    public B disable(StreamWriteFeature streamWriteFeature) {
        this.f5073c = (~streamWriteFeature.mappedFeature().getMask()) & this.f5073c;
        return (B) e();
    }

    public B disable(StreamWriteFeature streamWriteFeature, StreamWriteFeature... streamWriteFeatureArr) {
        this.f5073c = (~streamWriteFeature.mappedFeature().getMask()) & this.f5073c;
        for (StreamWriteFeature streamWriteFeature2 : streamWriteFeatureArr) {
            this.f5073c = (~streamWriteFeature2.mappedFeature().getMask()) & this.f5073c;
        }
        return (B) e();
    }

    public B disable(JsonReadFeature jsonReadFeature) {
        return _failNonJSON(jsonReadFeature);
    }

    public B disable(JsonReadFeature jsonReadFeature, JsonReadFeature... jsonReadFeatureArr) {
        return _failNonJSON(jsonReadFeature);
    }

    public B disable(JsonWriteFeature jsonWriteFeature) {
        return _failNonJSON(jsonWriteFeature);
    }

    public B disable(JsonWriteFeature jsonWriteFeature, JsonWriteFeature... jsonWriteFeatureArr) {
        return _failNonJSON(jsonWriteFeature);
    }

    protected final TSFBuilder e() {
        return this;
    }

    public B enable(JsonFactory.Feature feature) {
        this.f5071a = feature.getMask() | this.f5071a;
        return (B) e();
    }

    public B enable(StreamReadFeature streamReadFeature) {
        this.f5072b = streamReadFeature.mappedFeature().getMask() | this.f5072b;
        return (B) e();
    }

    public B enable(StreamReadFeature streamReadFeature, StreamReadFeature... streamReadFeatureArr) {
        this.f5072b = streamReadFeature.mappedFeature().getMask() | this.f5072b;
        for (StreamReadFeature streamReadFeature2 : streamReadFeatureArr) {
            this.f5072b = streamReadFeature2.mappedFeature().getMask() | this.f5072b;
        }
        return (B) e();
    }

    public B enable(StreamWriteFeature streamWriteFeature) {
        this.f5073c = streamWriteFeature.mappedFeature().getMask() | this.f5073c;
        return (B) e();
    }

    public B enable(StreamWriteFeature streamWriteFeature, StreamWriteFeature... streamWriteFeatureArr) {
        this.f5073c = streamWriteFeature.mappedFeature().getMask() | this.f5073c;
        for (StreamWriteFeature streamWriteFeature2 : streamWriteFeatureArr) {
            this.f5073c = streamWriteFeature2.mappedFeature().getMask() | this.f5073c;
        }
        return (B) e();
    }

    public B enable(JsonReadFeature jsonReadFeature) {
        return _failNonJSON(jsonReadFeature);
    }

    public B enable(JsonReadFeature jsonReadFeature, JsonReadFeature... jsonReadFeatureArr) {
        return _failNonJSON(jsonReadFeature);
    }

    public B enable(JsonWriteFeature jsonWriteFeature) {
        return _failNonJSON(jsonWriteFeature);
    }

    public B enable(JsonWriteFeature jsonWriteFeature, JsonWriteFeature... jsonWriteFeatureArr) {
        return _failNonJSON(jsonWriteFeature);
    }

    public int factoryFeaturesMask() {
        return this.f5071a;
    }

    public B inputDecorator(InputDecorator inputDecorator) {
        this.f5074d = inputDecorator;
        return (B) e();
    }

    public InputDecorator inputDecorator() {
        return this.f5074d;
    }

    public B outputDecorator(OutputDecorator outputDecorator) {
        this.f5075e = outputDecorator;
        return (B) e();
    }

    public OutputDecorator outputDecorator() {
        return this.f5075e;
    }

    public int streamReadFeatures() {
        return this.f5072b;
    }

    public int streamWriteFeatures() {
        return this.f5073c;
    }
}
