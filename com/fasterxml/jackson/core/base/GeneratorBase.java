package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.InputStream;
import java.math.BigDecimal;
/* loaded from: classes.dex */
public abstract class GeneratorBase extends JsonGenerator {
    public static final int SURR1_FIRST = 55296;
    public static final int SURR1_LAST = 56319;
    public static final int SURR2_FIRST = 56320;
    public static final int SURR2_LAST = 57343;

    /* renamed from: g  reason: collision with root package name */
    protected static final int f5082g = (JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.getMask() | JsonGenerator.Feature.ESCAPE_NON_ASCII.getMask()) | JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.getMask();

    /* renamed from: b  reason: collision with root package name */
    protected ObjectCodec f5083b;

    /* renamed from: c  reason: collision with root package name */
    protected int f5084c;

    /* renamed from: d  reason: collision with root package name */
    protected boolean f5085d;

    /* renamed from: e  reason: collision with root package name */
    protected JsonWriteContext f5086e;

    /* renamed from: f  reason: collision with root package name */
    protected boolean f5087f;

    /* JADX INFO: Access modifiers changed from: protected */
    public GeneratorBase(int i2, ObjectCodec objectCodec) {
        this.f5084c = i2;
        this.f5083b = objectCodec;
        this.f5086e = JsonWriteContext.createRootContext(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION.enabledIn(i2) ? DupDetector.rootDetector(this) : null);
        this.f5085d = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(i2);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f5087f = true;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator disable(JsonGenerator.Feature feature) {
        int mask = feature.getMask();
        this.f5084c &= ~mask;
        if ((mask & f5082g) != 0) {
            if (feature == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
                this.f5085d = false;
            } else if (feature == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
                setHighestNonEscapedChar(0);
            } else if (feature == JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION) {
                this.f5086e = this.f5086e.withDupDetector(null);
            }
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator enable(JsonGenerator.Feature feature) {
        int mask = feature.getMask();
        this.f5084c |= mask;
        if ((mask & f5082g) != 0) {
            if (feature == JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS) {
                this.f5085d = true;
            } else if (feature == JsonGenerator.Feature.ESCAPE_NON_ASCII) {
                setHighestNonEscapedChar(127);
            } else if (feature == JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION && this.f5086e.getDupDetector() == null) {
                this.f5086e = this.f5086e.withDupDetector(DupDetector.rootDetector(this));
            }
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, java.io.Flushable
    public abstract void flush();

    /* JADX INFO: Access modifiers changed from: protected */
    public String g(BigDecimal bigDecimal) {
        if (JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN.enabledIn(this.f5084c)) {
            int scale = bigDecimal.scale();
            if (scale < -9999 || scale > 9999) {
                b(String.format("Attempt to write plain `java.math.BigDecimal` (see JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN) with illegal scale (%d): needs to be between [-%d, %d]", Integer.valueOf(scale), 9999, 9999));
            }
            return bigDecimal.toPlainString();
        }
        return bigDecimal.toString();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public ObjectCodec getCodec() {
        return this.f5083b;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public Object getCurrentValue() {
        return this.f5086e.getCurrentValue();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int getFeatureMask() {
        return this.f5084c;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonStreamContext getOutputContext() {
        return this.f5086e;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void h(int i2, int i3) {
        JsonWriteContext jsonWriteContext;
        DupDetector dupDetector;
        if ((f5082g & i3) == 0) {
            return;
        }
        this.f5085d = JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(i2);
        JsonGenerator.Feature feature = JsonGenerator.Feature.ESCAPE_NON_ASCII;
        if (feature.enabledIn(i3)) {
            setHighestNonEscapedChar(feature.enabledIn(i2) ? 127 : 0);
        }
        JsonGenerator.Feature feature2 = JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION;
        if (feature2.enabledIn(i3)) {
            if (!feature2.enabledIn(i2)) {
                jsonWriteContext = this.f5086e;
                dupDetector = null;
            } else if (this.f5086e.getDupDetector() != null) {
                return;
            } else {
                jsonWriteContext = this.f5086e;
                dupDetector = DupDetector.rootDetector(this);
            }
            this.f5086e = jsonWriteContext.withDupDetector(dupDetector);
        }
    }

    protected PrettyPrinter i() {
        return new DefaultPrettyPrinter();
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public boolean isClosed() {
        return this.f5087f;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public final boolean isEnabled(JsonGenerator.Feature feature) {
        return (feature.getMask() & this.f5084c) != 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int j(int i2, int i3) {
        if (i3 < 56320 || i3 > 57343) {
            b("Incomplete surrogate pair: first char 0x" + Integer.toHexString(i2) + ", second 0x" + Integer.toHexString(i3));
        }
        return ((i2 - SURR1_FIRST) << 10) + 65536 + (i3 - 56320);
    }

    protected abstract void k(String str);

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator overrideStdFeatures(int i2, int i3) {
        int i4 = this.f5084c;
        int i5 = (i2 & i3) | ((~i3) & i4);
        int i6 = i4 ^ i5;
        if (i6 != 0) {
            this.f5084c = i5;
            h(i5, i6);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator setCodec(ObjectCodec objectCodec) {
        this.f5083b = objectCodec;
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void setCurrentValue(Object obj) {
        JsonWriteContext jsonWriteContext = this.f5086e;
        if (jsonWriteContext != null) {
            jsonWriteContext.setCurrentValue(obj);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    @Deprecated
    public JsonGenerator setFeatureMask(int i2) {
        int i3 = this.f5084c ^ i2;
        this.f5084c = i2;
        if (i3 != 0) {
            h(i2, i3);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public JsonGenerator useDefaultPrettyPrinter() {
        return getPrettyPrinter() != null ? this : setPrettyPrinter(i());
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return PackageVersion.VERSION;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i2) {
        c();
        return 0;
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(SerializableString serializableString) {
        writeFieldName(serializableString.getValue());
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeObject(Object obj) {
        if (obj == null) {
            writeNull();
            return;
        }
        ObjectCodec objectCodec = this.f5083b;
        if (objectCodec != null) {
            objectCodec.writeValue(this, obj);
        } else {
            f(obj);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(SerializableString serializableString) {
        k("write raw value");
        writeRaw(serializableString);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str) {
        k("write raw value");
        writeRaw(str);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str, int i2, int i3) {
        k("write raw value");
        writeRaw(str, i2, i3);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(char[] cArr, int i2, int i3) {
        k("write raw value");
        writeRaw(cArr, i2, i3);
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject(Object obj) {
        writeStartObject();
        if (obj != null) {
            setCurrentValue(obj);
        }
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeString(SerializableString serializableString) {
        writeString(serializableString.getValue());
    }

    @Override // com.fasterxml.jackson.core.JsonGenerator
    public void writeTree(TreeNode treeNode) {
        if (treeNode == null) {
            writeNull();
            return;
        }
        ObjectCodec objectCodec = this.f5083b;
        if (objectCodec == null) {
            throw new IllegalStateException("No ObjectCodec defined");
        }
        objectCodec.writeValue(this, treeNode);
    }
}
