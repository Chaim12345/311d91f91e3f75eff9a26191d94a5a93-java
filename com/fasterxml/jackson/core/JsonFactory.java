package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.InputDecorator;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.core.io.UTF8Writer;
import com.fasterxml.jackson.core.json.ByteSourceJsonBootstrapper;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.json.ReaderBasedJsonParser;
import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import com.fasterxml.jackson.core.json.UTF8JsonGenerator;
import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.BufferRecyclers;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.CharArrayReader;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.URL;
/* loaded from: classes.dex */
public class JsonFactory extends TokenStreamFactory {
    public static final char DEFAULT_QUOTE_CHAR = '\"';
    public static final String FORMAT_NAME_JSON = "JSON";
    private static final long serialVersionUID = 2;

    /* renamed from: a  reason: collision with root package name */
    protected final transient CharsToNameCanonicalizer f5024a;

    /* renamed from: b  reason: collision with root package name */
    protected final transient ByteQuadsCanonicalizer f5025b;

    /* renamed from: c  reason: collision with root package name */
    protected int f5026c;

    /* renamed from: d  reason: collision with root package name */
    protected int f5027d;

    /* renamed from: e  reason: collision with root package name */
    protected int f5028e;

    /* renamed from: f  reason: collision with root package name */
    protected ObjectCodec f5029f;

    /* renamed from: g  reason: collision with root package name */
    protected CharacterEscapes f5030g;

    /* renamed from: h  reason: collision with root package name */
    protected InputDecorator f5031h;

    /* renamed from: i  reason: collision with root package name */
    protected OutputDecorator f5032i;

    /* renamed from: j  reason: collision with root package name */
    protected SerializableString f5033j;

    /* renamed from: k  reason: collision with root package name */
    protected int f5034k;

    /* renamed from: l  reason: collision with root package name */
    protected final char f5035l;

    /* renamed from: m  reason: collision with root package name */
    protected static final int f5021m = Feature.collectDefaults();

    /* renamed from: n  reason: collision with root package name */
    protected static final int f5022n = JsonParser.Feature.collectDefaults();

    /* renamed from: o  reason: collision with root package name */
    protected static final int f5023o = JsonGenerator.Feature.collectDefaults();
    public static final SerializableString DEFAULT_ROOT_VALUE_SEPARATOR = DefaultPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;

    /* loaded from: classes.dex */
    public enum Feature {
        INTERN_FIELD_NAMES(true),
        CANONICALIZE_FIELD_NAMES(true),
        FAIL_ON_SYMBOL_HASH_OVERFLOW(true),
        USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING(true);
        
        private final boolean _defaultState;

        Feature(boolean z) {
            this._defaultState = z;
        }

        public static int collectDefaults() {
            Feature[] values;
            int i2 = 0;
            for (Feature feature : values()) {
                if (feature.enabledByDefault()) {
                    i2 |= feature.getMask();
                }
            }
            return i2;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int i2) {
            return (i2 & getMask()) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    public JsonFactory() {
        this((ObjectCodec) null);
    }

    protected JsonFactory(JsonFactory jsonFactory, ObjectCodec objectCodec) {
        this.f5024a = CharsToNameCanonicalizer.createRoot();
        this.f5025b = ByteQuadsCanonicalizer.createRoot();
        this.f5026c = f5021m;
        this.f5027d = f5022n;
        this.f5028e = f5023o;
        this.f5033j = DEFAULT_ROOT_VALUE_SEPARATOR;
        this.f5029f = objectCodec;
        this.f5026c = jsonFactory.f5026c;
        this.f5027d = jsonFactory.f5027d;
        this.f5028e = jsonFactory.f5028e;
        this.f5031h = jsonFactory.f5031h;
        this.f5032i = jsonFactory.f5032i;
        this.f5030g = jsonFactory.f5030g;
        this.f5033j = jsonFactory.f5033j;
        this.f5034k = jsonFactory.f5034k;
        this.f5035l = jsonFactory.f5035l;
    }

    public JsonFactory(JsonFactoryBuilder jsonFactoryBuilder) {
        this.f5024a = CharsToNameCanonicalizer.createRoot();
        this.f5025b = ByteQuadsCanonicalizer.createRoot();
        this.f5026c = f5021m;
        this.f5027d = f5022n;
        this.f5028e = f5023o;
        this.f5033j = DEFAULT_ROOT_VALUE_SEPARATOR;
        this.f5029f = null;
        this.f5026c = jsonFactoryBuilder.f5071a;
        this.f5027d = jsonFactoryBuilder.f5072b;
        this.f5028e = jsonFactoryBuilder.f5073c;
        this.f5031h = jsonFactoryBuilder.f5074d;
        this.f5032i = jsonFactoryBuilder.f5075e;
        this.f5030g = jsonFactoryBuilder.f5036i;
        this.f5033j = jsonFactoryBuilder.f5037j;
        this.f5034k = jsonFactoryBuilder.f5038k;
        this.f5035l = jsonFactoryBuilder.f5039l;
    }

    public JsonFactory(ObjectCodec objectCodec) {
        this.f5024a = CharsToNameCanonicalizer.createRoot();
        this.f5025b = ByteQuadsCanonicalizer.createRoot();
        this.f5026c = f5021m;
        this.f5027d = f5022n;
        this.f5028e = f5023o;
        this.f5033j = DEFAULT_ROOT_VALUE_SEPARATOR;
        this.f5029f = objectCodec;
        this.f5035l = '\"';
    }

    private final boolean _isJSONFactory() {
        return getFormatName() == FORMAT_NAME_JSON;
    }

    private final void _requireJSONFactory(String str) {
        if (!_isJSONFactory()) {
            throw new UnsupportedOperationException(String.format(str, getFormatName()));
        }
    }

    public static TSFBuilder<?, ?> builder() {
        return new JsonFactoryBuilder();
    }

    public BufferRecycler _getBufferRecycler() {
        return Feature.USE_THREAD_LOCAL_FOR_BUFFER_RECYCLING.enabledIn(this.f5026c) ? BufferRecyclers.getBufferRecycler() : new BufferRecycler();
    }

    protected void c(Class cls) {
        if (getClass() == cls) {
            return;
        }
        throw new IllegalStateException("Failed copy(): " + getClass().getName() + " (version: " + version() + ") does not override copy(); it has to");
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public boolean canHandleBinaryNatively() {
        return false;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public boolean canParseAsync() {
        return _isJSONFactory();
    }

    public boolean canUseCharArrays() {
        return true;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public boolean canUseSchema(FormatSchema formatSchema) {
        String formatName;
        return (formatSchema == null || (formatName = getFormatName()) == null || !formatName.equals(formatSchema.getSchemaType())) ? false : true;
    }

    @Deprecated
    public final JsonFactory configure(Feature feature, boolean z) {
        return z ? enable(feature) : disable(feature);
    }

    public final JsonFactory configure(JsonGenerator.Feature feature, boolean z) {
        return z ? enable(feature) : disable(feature);
    }

    public final JsonFactory configure(JsonParser.Feature feature, boolean z) {
        return z ? enable(feature) : disable(feature);
    }

    public JsonFactory copy() {
        c(JsonFactory.class);
        return new JsonFactory(this, null);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonGenerator createGenerator(DataOutput dataOutput) {
        return createGenerator(a(dataOutput), JsonEncoding.UTF8);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonGenerator createGenerator(DataOutput dataOutput, JsonEncoding jsonEncoding) {
        return createGenerator(a(dataOutput), jsonEncoding);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonGenerator createGenerator(File file, JsonEncoding jsonEncoding) {
        OutputStream fileOutputStream = new FileOutputStream(file);
        IOContext d2 = d(fileOutputStream, true);
        d2.setEncoding(jsonEncoding);
        return jsonEncoding == JsonEncoding.UTF8 ? l(p(fileOutputStream, d2), d2) : e(r(m(fileOutputStream, jsonEncoding, d2), d2), d2);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonGenerator createGenerator(OutputStream outputStream) {
        return createGenerator(outputStream, JsonEncoding.UTF8);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) {
        IOContext d2 = d(outputStream, false);
        d2.setEncoding(jsonEncoding);
        return jsonEncoding == JsonEncoding.UTF8 ? l(p(outputStream, d2), d2) : e(r(m(outputStream, jsonEncoding, d2), d2), d2);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonGenerator createGenerator(Writer writer) {
        IOContext d2 = d(writer, false);
        return e(r(writer, d2), d2);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(OutputStream outputStream) {
        return createGenerator(outputStream, JsonEncoding.UTF8);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(OutputStream outputStream, JsonEncoding jsonEncoding) {
        return createGenerator(outputStream, jsonEncoding);
    }

    @Deprecated
    public JsonGenerator createJsonGenerator(Writer writer) {
        return createGenerator(writer);
    }

    @Deprecated
    public JsonParser createJsonParser(File file) {
        return createParser(file);
    }

    @Deprecated
    public JsonParser createJsonParser(InputStream inputStream) {
        return createParser(inputStream);
    }

    @Deprecated
    public JsonParser createJsonParser(Reader reader) {
        return createParser(reader);
    }

    @Deprecated
    public JsonParser createJsonParser(String str) {
        return createParser(str);
    }

    @Deprecated
    public JsonParser createJsonParser(URL url) {
        return createParser(url);
    }

    @Deprecated
    public JsonParser createJsonParser(byte[] bArr) {
        return createParser(bArr);
    }

    @Deprecated
    public JsonParser createJsonParser(byte[] bArr, int i2, int i3) {
        return createParser(bArr, i2, i3);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createNonBlockingByteArrayParser() {
        _requireJSONFactory("Non-blocking source not (yet?) supported for this format (%s)");
        return new NonBlockingJsonParser(f(null), this.f5027d, this.f5025b.makeChild(this.f5026c));
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createParser(DataInput dataInput) {
        IOContext d2 = d(dataInput, false);
        return g(n(dataInput, d2), d2);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createParser(File file) {
        IOContext d2 = d(file, true);
        return h(o(new FileInputStream(file), d2), d2);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createParser(InputStream inputStream) {
        IOContext d2 = d(inputStream, false);
        return h(o(inputStream, d2), d2);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createParser(Reader reader) {
        IOContext d2 = d(reader, false);
        return i(q(reader, d2), d2);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createParser(String str) {
        int length = str.length();
        if (this.f5031h == null && length <= 32768 && canUseCharArrays()) {
            IOContext d2 = d(str, true);
            char[] allocTokenBuffer = d2.allocTokenBuffer(length);
            str.getChars(0, length, allocTokenBuffer, 0);
            return k(allocTokenBuffer, 0, length, d2, true);
        }
        return createParser(new StringReader(str));
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createParser(URL url) {
        IOContext d2 = d(url, true);
        return h(o(b(url), d2), d2);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createParser(byte[] bArr) {
        InputStream decorate;
        IOContext d2 = d(bArr, true);
        InputDecorator inputDecorator = this.f5031h;
        return (inputDecorator == null || (decorate = inputDecorator.decorate(d2, bArr, 0, bArr.length)) == null) ? j(bArr, 0, bArr.length, d2) : h(decorate, d2);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createParser(byte[] bArr, int i2, int i3) {
        InputStream decorate;
        IOContext d2 = d(bArr, true);
        InputDecorator inputDecorator = this.f5031h;
        return (inputDecorator == null || (decorate = inputDecorator.decorate(d2, bArr, i2, i3)) == null) ? j(bArr, i2, i3, d2) : h(decorate, d2);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createParser(char[] cArr) {
        return createParser(cArr, 0, cArr.length);
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public JsonParser createParser(char[] cArr, int i2, int i3) {
        return this.f5031h != null ? createParser(new CharArrayReader(cArr, i2, i3)) : k(cArr, i2, i3, d(cArr, true), false);
    }

    protected IOContext d(Object obj, boolean z) {
        return new IOContext(_getBufferRecycler(), obj, z);
    }

    @Deprecated
    public JsonFactory disable(Feature feature) {
        this.f5026c = (~feature.getMask()) & this.f5026c;
        return this;
    }

    public JsonFactory disable(JsonGenerator.Feature feature) {
        this.f5028e = (~feature.getMask()) & this.f5028e;
        return this;
    }

    public JsonFactory disable(JsonParser.Feature feature) {
        this.f5027d = (~feature.getMask()) & this.f5027d;
        return this;
    }

    protected JsonGenerator e(Writer writer, IOContext iOContext) {
        WriterBasedJsonGenerator writerBasedJsonGenerator = new WriterBasedJsonGenerator(iOContext, this.f5028e, this.f5029f, writer, this.f5035l);
        int i2 = this.f5034k;
        if (i2 > 0) {
            writerBasedJsonGenerator.setHighestNonEscapedChar(i2);
        }
        CharacterEscapes characterEscapes = this.f5030g;
        if (characterEscapes != null) {
            writerBasedJsonGenerator.setCharacterEscapes(characterEscapes);
        }
        SerializableString serializableString = this.f5033j;
        if (serializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
            writerBasedJsonGenerator.setRootValueSeparator(serializableString);
        }
        return writerBasedJsonGenerator;
    }

    @Deprecated
    public JsonFactory enable(Feature feature) {
        this.f5026c = feature.getMask() | this.f5026c;
        return this;
    }

    public JsonFactory enable(JsonGenerator.Feature feature) {
        this.f5028e = feature.getMask() | this.f5028e;
        return this;
    }

    public JsonFactory enable(JsonParser.Feature feature) {
        this.f5027d = feature.getMask() | this.f5027d;
        return this;
    }

    protected IOContext f(Object obj) {
        return new IOContext(_getBufferRecycler(), obj, false);
    }

    protected JsonParser g(DataInput dataInput, IOContext iOContext) {
        _requireJSONFactory("InputData source not (yet?) supported for this format (%s)");
        int skipUTF8BOM = ByteSourceJsonBootstrapper.skipUTF8BOM(dataInput);
        return new UTF8DataInputJsonParser(iOContext, this.f5027d, dataInput, this.f5029f, this.f5025b.makeChild(this.f5026c), skipUTF8BOM);
    }

    public CharacterEscapes getCharacterEscapes() {
        return this.f5030g;
    }

    public ObjectCodec getCodec() {
        return this.f5029f;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public int getFormatGeneratorFeatures() {
        return 0;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public String getFormatName() {
        if (getClass() == JsonFactory.class) {
            return FORMAT_NAME_JSON;
        }
        return null;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public int getFormatParserFeatures() {
        return 0;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public Class<? extends FormatFeature> getFormatReadFeatureType() {
        return null;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public Class<? extends FormatFeature> getFormatWriteFeatureType() {
        return null;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public final int getGeneratorFeatures() {
        return this.f5028e;
    }

    public InputDecorator getInputDecorator() {
        return this.f5031h;
    }

    public OutputDecorator getOutputDecorator() {
        return this.f5032i;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public final int getParserFeatures() {
        return this.f5027d;
    }

    public String getRootValueSeparator() {
        SerializableString serializableString = this.f5033j;
        if (serializableString == null) {
            return null;
        }
        return serializableString.getValue();
    }

    protected JsonParser h(InputStream inputStream, IOContext iOContext) {
        return new ByteSourceJsonBootstrapper(iOContext, inputStream).constructParser(this.f5027d, this.f5029f, this.f5025b, this.f5024a, this.f5026c);
    }

    public MatchStrength hasFormat(InputAccessor inputAccessor) {
        if (getClass() == JsonFactory.class) {
            return s(inputAccessor);
        }
        return null;
    }

    protected JsonParser i(Reader reader, IOContext iOContext) {
        return new ReaderBasedJsonParser(iOContext, this.f5027d, reader, this.f5029f, this.f5024a.makeChild(this.f5026c));
    }

    public final boolean isEnabled(Feature feature) {
        return (feature.getMask() & this.f5026c) != 0;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public final boolean isEnabled(JsonGenerator.Feature feature) {
        return (feature.getMask() & this.f5028e) != 0;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public final boolean isEnabled(JsonParser.Feature feature) {
        return (feature.getMask() & this.f5027d) != 0;
    }

    public final boolean isEnabled(StreamReadFeature streamReadFeature) {
        return (streamReadFeature.mappedFeature().getMask() & this.f5027d) != 0;
    }

    public final boolean isEnabled(StreamWriteFeature streamWriteFeature) {
        return (streamWriteFeature.mappedFeature().getMask() & this.f5028e) != 0;
    }

    protected JsonParser j(byte[] bArr, int i2, int i3, IOContext iOContext) {
        return new ByteSourceJsonBootstrapper(iOContext, bArr, i2, i3).constructParser(this.f5027d, this.f5029f, this.f5025b, this.f5024a, this.f5026c);
    }

    protected JsonParser k(char[] cArr, int i2, int i3, IOContext iOContext, boolean z) {
        return new ReaderBasedJsonParser(iOContext, this.f5027d, null, this.f5029f, this.f5024a.makeChild(this.f5026c), cArr, i2, i2 + i3, z);
    }

    protected JsonGenerator l(OutputStream outputStream, IOContext iOContext) {
        UTF8JsonGenerator uTF8JsonGenerator = new UTF8JsonGenerator(iOContext, this.f5028e, this.f5029f, outputStream, this.f5035l);
        int i2 = this.f5034k;
        if (i2 > 0) {
            uTF8JsonGenerator.setHighestNonEscapedChar(i2);
        }
        CharacterEscapes characterEscapes = this.f5030g;
        if (characterEscapes != null) {
            uTF8JsonGenerator.setCharacterEscapes(characterEscapes);
        }
        SerializableString serializableString = this.f5033j;
        if (serializableString != DEFAULT_ROOT_VALUE_SEPARATOR) {
            uTF8JsonGenerator.setRootValueSeparator(serializableString);
        }
        return uTF8JsonGenerator;
    }

    protected Writer m(OutputStream outputStream, JsonEncoding jsonEncoding, IOContext iOContext) {
        return jsonEncoding == JsonEncoding.UTF8 ? new UTF8Writer(iOContext, outputStream) : new OutputStreamWriter(outputStream, jsonEncoding.getJavaName());
    }

    protected final DataInput n(DataInput dataInput, IOContext iOContext) {
        DataInput decorate;
        InputDecorator inputDecorator = this.f5031h;
        return (inputDecorator == null || (decorate = inputDecorator.decorate(iOContext, dataInput)) == null) ? dataInput : decorate;
    }

    protected final InputStream o(InputStream inputStream, IOContext iOContext) {
        InputStream decorate;
        InputDecorator inputDecorator = this.f5031h;
        return (inputDecorator == null || (decorate = inputDecorator.decorate(iOContext, inputStream)) == null) ? inputStream : decorate;
    }

    protected final OutputStream p(OutputStream outputStream, IOContext iOContext) {
        OutputStream decorate;
        OutputDecorator outputDecorator = this.f5032i;
        return (outputDecorator == null || (decorate = outputDecorator.decorate(iOContext, outputStream)) == null) ? outputStream : decorate;
    }

    protected final Reader q(Reader reader, IOContext iOContext) {
        Reader decorate;
        InputDecorator inputDecorator = this.f5031h;
        return (inputDecorator == null || (decorate = inputDecorator.decorate(iOContext, reader)) == null) ? reader : decorate;
    }

    protected final Writer r(Writer writer, IOContext iOContext) {
        Writer decorate;
        OutputDecorator outputDecorator = this.f5032i;
        return (outputDecorator == null || (decorate = outputDecorator.decorate(iOContext, writer)) == null) ? writer : decorate;
    }

    public TSFBuilder<?, ?> rebuild() {
        _requireJSONFactory("Factory implementation for format (%s) MUST override `rebuild()` method");
        return new JsonFactoryBuilder(this);
    }

    public boolean requiresCustomCodec() {
        return false;
    }

    @Override // com.fasterxml.jackson.core.TokenStreamFactory
    public boolean requiresPropertyOrdering() {
        return false;
    }

    protected MatchStrength s(InputAccessor inputAccessor) {
        return ByteSourceJsonBootstrapper.hasJSONFormat(inputAccessor);
    }

    public JsonFactory setCharacterEscapes(CharacterEscapes characterEscapes) {
        this.f5030g = characterEscapes;
        return this;
    }

    public JsonFactory setCodec(ObjectCodec objectCodec) {
        this.f5029f = objectCodec;
        return this;
    }

    @Deprecated
    public JsonFactory setInputDecorator(InputDecorator inputDecorator) {
        this.f5031h = inputDecorator;
        return this;
    }

    @Deprecated
    public JsonFactory setOutputDecorator(OutputDecorator outputDecorator) {
        this.f5032i = outputDecorator;
        return this;
    }

    public JsonFactory setRootValueSeparator(String str) {
        this.f5033j = str == null ? null : new SerializedString(str);
        return this;
    }

    @Override // com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }
}
