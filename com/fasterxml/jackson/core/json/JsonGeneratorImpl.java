package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.VersionUtil;
/* loaded from: classes.dex */
public abstract class JsonGeneratorImpl extends GeneratorBase {

    /* renamed from: n  reason: collision with root package name */
    protected static final int[] f5184n = CharTypes.get7BitOutputEscapes();

    /* renamed from: h  reason: collision with root package name */
    protected final IOContext f5185h;

    /* renamed from: i  reason: collision with root package name */
    protected int[] f5186i;

    /* renamed from: j  reason: collision with root package name */
    protected int f5187j;

    /* renamed from: k  reason: collision with root package name */
    protected CharacterEscapes f5188k;

    /* renamed from: l  reason: collision with root package name */
    protected SerializableString f5189l;

    /* renamed from: m  reason: collision with root package name */
    protected boolean f5190m;

    public JsonGeneratorImpl(IOContext iOContext, int i2, ObjectCodec objectCodec) {
        super(i2, objectCodec);
        this.f5186i = f5184n;
        this.f5189l = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        this.f5185h = iOContext;
        if (JsonGenerator.Feature.ESCAPE_NON_ASCII.enabledIn(i2)) {
            this.f5187j = 127;
        }
        this.f5190m = !JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(i2);
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator disable(JsonGenerator.Feature feature) {
        super.disable(feature);
        if (feature == JsonGenerator.Feature.QUOTE_FIELD_NAMES) {
            this.f5190m = true;
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator enable(JsonGenerator.Feature feature) {
        super.enable(feature);
        if (feature == JsonGenerator.Feature.QUOTE_FIELD_NAMES) {
            this.f5190m = false;
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public CharacterEscapes getCharacterEscapes() {
        return this.f5188k;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int getHighestEscapedChar() {
        return this.f5187j;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase
    protected void h(int i2, int i3) {
        super.h(i2, i3);
        this.f5190m = !JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void l(String str) {
        b(String.format("Can not %s, expecting field name (context: %s)", str, this.f5086e.typeDesc()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void m(String str, int i2) {
        if (i2 == 0) {
            if (this.f5086e.inArray()) {
                this.f5041a.beforeArrayValues(this);
            } else if (this.f5086e.inObject()) {
                this.f5041a.beforeObjectEntries(this);
            }
        } else if (i2 == 1) {
            this.f5041a.writeArrayValueSeparator(this);
        } else if (i2 == 2) {
            this.f5041a.writeObjectFieldValueSeparator(this);
        } else if (i2 == 3) {
            this.f5041a.writeRootValueSeparator(this);
        } else if (i2 != 5) {
            d();
        } else {
            l(str);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator setCharacterEscapes(CharacterEscapes characterEscapes) {
        this.f5188k = characterEscapes;
        if (characterEscapes == null) {
            this.f5186i = f5184n;
        } else {
            this.f5186i = characterEscapes.getEscapeCodesForAscii();
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator setHighestNonEscapedChar(int i2) {
        if (i2 < 0) {
            i2 = 0;
        }
        this.f5187j = i2;
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator setRootValueSeparator(SerializableString serializableString) {
        this.f5189l = serializableString;
        return this;
    }

    @Override // com.fasterxml.jackson.core.base.GeneratorBase, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return VersionUtil.versionFor(getClass());
    }
}
