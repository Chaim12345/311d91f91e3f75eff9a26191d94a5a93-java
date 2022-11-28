package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.SerializedString;
import java.io.Serializable;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.http.message.TokenParser;
/* loaded from: classes.dex */
public class DefaultPrettyPrinter implements PrettyPrinter, Instantiatable<DefaultPrettyPrinter>, Serializable {
    public static final SerializedString DEFAULT_ROOT_VALUE_SEPARATOR = new SerializedString(" ");
    private static final long serialVersionUID = 1;

    /* renamed from: a  reason: collision with root package name */
    protected Indenter f5228a;

    /* renamed from: b  reason: collision with root package name */
    protected Indenter f5229b;

    /* renamed from: c  reason: collision with root package name */
    protected final SerializableString f5230c;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f5231d;

    /* renamed from: e  reason: collision with root package name */
    protected transient int f5232e;

    /* renamed from: f  reason: collision with root package name */
    protected Separators f5233f;

    /* renamed from: g  reason: collision with root package name */
    protected String f5234g;

    /* loaded from: classes.dex */
    public static class FixedSpaceIndenter extends NopIndenter {
        public static final FixedSpaceIndenter instance = new FixedSpaceIndenter();

        @Override // com.fasterxml.jackson.core.util.DefaultPrettyPrinter.NopIndenter, com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter
        public boolean isInline() {
            return true;
        }

        @Override // com.fasterxml.jackson.core.util.DefaultPrettyPrinter.NopIndenter, com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter
        public void writeIndentation(JsonGenerator jsonGenerator, int i2) {
            jsonGenerator.writeRaw(TokenParser.SP);
        }
    }

    /* loaded from: classes.dex */
    public interface Indenter {
        boolean isInline();

        void writeIndentation(JsonGenerator jsonGenerator, int i2);
    }

    /* loaded from: classes.dex */
    public static class NopIndenter implements Indenter, Serializable {
        public static final NopIndenter instance = new NopIndenter();

        @Override // com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter
        public boolean isInline() {
            return true;
        }

        @Override // com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter
        public void writeIndentation(JsonGenerator jsonGenerator, int i2) {
        }
    }

    public DefaultPrettyPrinter() {
        this(DEFAULT_ROOT_VALUE_SEPARATOR);
    }

    public DefaultPrettyPrinter(SerializableString serializableString) {
        this.f5228a = FixedSpaceIndenter.instance;
        this.f5229b = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        this.f5231d = true;
        this.f5230c = serializableString;
        withSeparators(PrettyPrinter.DEFAULT_SEPARATORS);
    }

    public DefaultPrettyPrinter(DefaultPrettyPrinter defaultPrettyPrinter) {
        this(defaultPrettyPrinter, defaultPrettyPrinter.f5230c);
    }

    public DefaultPrettyPrinter(DefaultPrettyPrinter defaultPrettyPrinter, SerializableString serializableString) {
        this.f5228a = FixedSpaceIndenter.instance;
        this.f5229b = DefaultIndenter.SYSTEM_LINEFEED_INSTANCE;
        this.f5231d = true;
        this.f5228a = defaultPrettyPrinter.f5228a;
        this.f5229b = defaultPrettyPrinter.f5229b;
        this.f5231d = defaultPrettyPrinter.f5231d;
        this.f5232e = defaultPrettyPrinter.f5232e;
        this.f5233f = defaultPrettyPrinter.f5233f;
        this.f5234g = defaultPrettyPrinter.f5234g;
        this.f5230c = serializableString;
    }

    public DefaultPrettyPrinter(String str) {
        this(str == null ? null : new SerializedString(str));
    }

    protected DefaultPrettyPrinter a(boolean z) {
        if (this.f5231d == z) {
            return this;
        }
        DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter(this);
        defaultPrettyPrinter.f5231d = z;
        return defaultPrettyPrinter;
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void beforeArrayValues(JsonGenerator jsonGenerator) {
        this.f5228a.writeIndentation(jsonGenerator, this.f5232e);
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void beforeObjectEntries(JsonGenerator jsonGenerator) {
        this.f5229b.writeIndentation(jsonGenerator, this.f5232e);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.core.util.Instantiatable
    public DefaultPrettyPrinter createInstance() {
        if (getClass() == DefaultPrettyPrinter.class) {
            return new DefaultPrettyPrinter(this);
        }
        throw new IllegalStateException("Failed `createInstance()`: " + getClass().getName() + " does not override method; it has to");
    }

    public void indentArraysWith(Indenter indenter) {
        if (indenter == null) {
            indenter = NopIndenter.instance;
        }
        this.f5228a = indenter;
    }

    public void indentObjectsWith(Indenter indenter) {
        if (indenter == null) {
            indenter = NopIndenter.instance;
        }
        this.f5229b = indenter;
    }

    public DefaultPrettyPrinter withArrayIndenter(Indenter indenter) {
        if (indenter == null) {
            indenter = NopIndenter.instance;
        }
        if (this.f5228a == indenter) {
            return this;
        }
        DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter(this);
        defaultPrettyPrinter.f5228a = indenter;
        return defaultPrettyPrinter;
    }

    public DefaultPrettyPrinter withObjectIndenter(Indenter indenter) {
        if (indenter == null) {
            indenter = NopIndenter.instance;
        }
        if (this.f5229b == indenter) {
            return this;
        }
        DefaultPrettyPrinter defaultPrettyPrinter = new DefaultPrettyPrinter(this);
        defaultPrettyPrinter.f5229b = indenter;
        return defaultPrettyPrinter;
    }

    public DefaultPrettyPrinter withRootSeparator(SerializableString serializableString) {
        SerializableString serializableString2 = this.f5230c;
        return (serializableString2 == serializableString || (serializableString != null && serializableString.equals(serializableString2))) ? this : new DefaultPrettyPrinter(this, serializableString);
    }

    public DefaultPrettyPrinter withRootSeparator(String str) {
        return withRootSeparator(str == null ? null : new SerializedString(str));
    }

    public DefaultPrettyPrinter withSeparators(Separators separators) {
        this.f5233f = separators;
        this.f5234g = " " + separators.getObjectFieldValueSeparator() + " ";
        return this;
    }

    public DefaultPrettyPrinter withSpacesInObjectEntries() {
        return a(true);
    }

    public DefaultPrettyPrinter withoutSpacesInObjectEntries() {
        return a(false);
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeArrayValueSeparator(JsonGenerator jsonGenerator) {
        jsonGenerator.writeRaw(this.f5233f.getArrayValueSeparator());
        this.f5228a.writeIndentation(jsonGenerator, this.f5232e);
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeEndArray(JsonGenerator jsonGenerator, int i2) {
        if (!this.f5228a.isInline()) {
            this.f5232e--;
        }
        if (i2 > 0) {
            this.f5228a.writeIndentation(jsonGenerator, this.f5232e);
        } else {
            jsonGenerator.writeRaw(TokenParser.SP);
        }
        jsonGenerator.writeRaw(AbstractJsonLexerKt.END_LIST);
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeEndObject(JsonGenerator jsonGenerator, int i2) {
        if (!this.f5229b.isInline()) {
            this.f5232e--;
        }
        if (i2 > 0) {
            this.f5229b.writeIndentation(jsonGenerator, this.f5232e);
        } else {
            jsonGenerator.writeRaw(TokenParser.SP);
        }
        jsonGenerator.writeRaw(AbstractJsonLexerKt.END_OBJ);
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeObjectEntrySeparator(JsonGenerator jsonGenerator) {
        jsonGenerator.writeRaw(this.f5233f.getObjectEntrySeparator());
        this.f5229b.writeIndentation(jsonGenerator, this.f5232e);
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeObjectFieldValueSeparator(JsonGenerator jsonGenerator) {
        if (this.f5231d) {
            jsonGenerator.writeRaw(this.f5234g);
        } else {
            jsonGenerator.writeRaw(this.f5233f.getObjectFieldValueSeparator());
        }
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeRootValueSeparator(JsonGenerator jsonGenerator) {
        SerializableString serializableString = this.f5230c;
        if (serializableString != null) {
            jsonGenerator.writeRaw(serializableString);
        }
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeStartArray(JsonGenerator jsonGenerator) {
        if (!this.f5228a.isInline()) {
            this.f5232e++;
        }
        jsonGenerator.writeRaw(AbstractJsonLexerKt.BEGIN_LIST);
    }

    @Override // com.fasterxml.jackson.core.PrettyPrinter
    public void writeStartObject(JsonGenerator jsonGenerator) {
        jsonGenerator.writeRaw(AbstractJsonLexerKt.BEGIN_OBJ);
        if (this.f5229b.isInline()) {
            return;
        }
        this.f5232e++;
    }
}
