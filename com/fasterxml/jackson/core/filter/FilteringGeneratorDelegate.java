package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.util.JsonGeneratorDelegate;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes.dex */
public class FilteringGeneratorDelegate extends JsonGeneratorDelegate {

    /* renamed from: d  reason: collision with root package name */
    protected TokenFilter f5110d;

    /* renamed from: e  reason: collision with root package name */
    protected boolean f5111e;

    /* renamed from: f  reason: collision with root package name */
    protected boolean f5112f;
    @Deprecated

    /* renamed from: g  reason: collision with root package name */
    protected boolean f5113g;

    /* renamed from: h  reason: collision with root package name */
    protected TokenFilterContext f5114h;

    /* renamed from: i  reason: collision with root package name */
    protected TokenFilter f5115i;

    /* renamed from: j  reason: collision with root package name */
    protected int f5116j;

    public FilteringGeneratorDelegate(JsonGenerator jsonGenerator, TokenFilter tokenFilter, boolean z, boolean z2) {
        super(jsonGenerator, false);
        this.f5110d = tokenFilter;
        this.f5115i = tokenFilter;
        this.f5114h = TokenFilterContext.createRootContext(tokenFilter);
        this.f5112f = z;
        this.f5111e = z2;
    }

    protected boolean g() {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return false;
        }
        if (tokenFilter == TokenFilter.INCLUDE_ALL) {
            return true;
        }
        if (tokenFilter.includeBinary()) {
            h();
            return true;
        }
        return false;
    }

    public TokenFilter getFilter() {
        return this.f5110d;
    }

    public JsonStreamContext getFilterContext() {
        return this.f5114h;
    }

    public int getMatchCount() {
        return this.f5116j;
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public JsonStreamContext getOutputContext() {
        return this.f5114h;
    }

    protected void h() {
        this.f5116j++;
        if (this.f5112f) {
            this.f5114h.writePath(this.f5235b);
        }
        if (this.f5111e) {
            return;
        }
        this.f5114h.skipParentChecks();
    }

    protected void i() {
        this.f5116j++;
        if (this.f5112f) {
            this.f5114h.writePath(this.f5235b);
        } else if (this.f5113g) {
            this.f5114h.writeImmediatePath(this.f5235b);
        }
        if (this.f5111e) {
            return;
        }
        this.f5114h.skipParentChecks();
    }

    protected boolean j() {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return false;
        }
        if (tokenFilter == TokenFilter.INCLUDE_ALL) {
            return true;
        }
        if (tokenFilter.includeRawValue()) {
            h();
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i2) {
        if (g()) {
            return this.f5235b.writeBinary(base64Variant, inputStream, i2);
        }
        return -1;
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeBinary(Base64Variant base64Variant, byte[] bArr, int i2, int i3) {
        if (g()) {
            this.f5235b.writeBinary(base64Variant, bArr, i2, i3);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeBoolean(boolean z) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeBoolean(z)) {
                return;
            }
            h();
        }
        this.f5235b.writeBoolean(z);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeEndArray() {
        TokenFilterContext closeArray = this.f5114h.closeArray(this.f5235b);
        this.f5114h = closeArray;
        if (closeArray != null) {
            this.f5115i = closeArray.getFilter();
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeEndObject() {
        TokenFilterContext closeObject = this.f5114h.closeObject(this.f5235b);
        this.f5114h = closeObject;
        if (closeObject != null) {
            this.f5115i = closeObject.getFilter();
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldId(long j2) {
        writeFieldName(Long.toString(j2));
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(SerializableString serializableString) {
        TokenFilter fieldName = this.f5114h.setFieldName(serializableString.getValue());
        if (fieldName == null) {
            this.f5115i = null;
            return;
        }
        TokenFilter tokenFilter = TokenFilter.INCLUDE_ALL;
        if (fieldName == tokenFilter) {
            this.f5115i = fieldName;
            this.f5235b.writeFieldName(serializableString);
            return;
        }
        TokenFilter includeProperty = fieldName.includeProperty(serializableString.getValue());
        this.f5115i = includeProperty;
        if (includeProperty == tokenFilter) {
            i();
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeFieldName(String str) {
        TokenFilter fieldName = this.f5114h.setFieldName(str);
        if (fieldName == null) {
            this.f5115i = null;
            return;
        }
        TokenFilter tokenFilter = TokenFilter.INCLUDE_ALL;
        if (fieldName == tokenFilter) {
            this.f5115i = fieldName;
            this.f5235b.writeFieldName(str);
            return;
        }
        TokenFilter includeProperty = fieldName.includeProperty(str);
        this.f5115i = includeProperty;
        if (includeProperty == tokenFilter) {
            i();
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeNull() {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeNull()) {
                return;
            }
            h();
        }
        this.f5235b.writeNull();
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(double d2) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeNumber(d2)) {
                return;
            }
            h();
        }
        this.f5235b.writeNumber(d2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(float f2) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeNumber(f2)) {
                return;
            }
            h();
        }
        this.f5235b.writeNumber(f2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(int i2) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeNumber(i2)) {
                return;
            }
            h();
        }
        this.f5235b.writeNumber(i2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(long j2) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeNumber(j2)) {
                return;
            }
            h();
        }
        this.f5235b.writeNumber(j2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(String str) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeRawValue()) {
                return;
            }
            h();
        }
        this.f5235b.writeNumber(str);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigDecimal bigDecimal) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeNumber(bigDecimal)) {
                return;
            }
            h();
        }
        this.f5235b.writeNumber(bigDecimal);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(BigInteger bigInteger) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeNumber(bigInteger)) {
                return;
            }
            h();
        }
        this.f5235b.writeNumber(bigInteger);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(short s2) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeNumber((int) s2)) {
                return;
            }
            h();
        }
        this.f5235b.writeNumber(s2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeNumber(char[] cArr, int i2, int i3) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeRawValue()) {
                return;
            }
            h();
        }
        this.f5235b.writeNumber(cArr, i2, i3);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeObjectId(Object obj) {
        if (this.f5115i != null) {
            this.f5235b.writeObjectId(obj);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeObjectRef(Object obj) {
        if (this.f5115i != null) {
            this.f5235b.writeObjectRef(obj);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeOmittedField(String str) {
        if (this.f5115i != null) {
            this.f5235b.writeOmittedField(str);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char c2) {
        if (j()) {
            this.f5235b.writeRaw(c2);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(SerializableString serializableString) {
        if (j()) {
            this.f5235b.writeRaw(serializableString);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str) {
        if (j()) {
            this.f5235b.writeRaw(str);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(String str, int i2, int i3) {
        if (j()) {
            this.f5235b.writeRaw(str, i2, i3);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeRaw(char[] cArr, int i2, int i3) {
        if (j()) {
            this.f5235b.writeRaw(cArr, i2, i3);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawUTF8String(byte[] bArr, int i2, int i3) {
        if (j()) {
            this.f5235b.writeRawUTF8String(bArr, i2, i3);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str) {
        if (j()) {
            this.f5235b.writeRawValue(str);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(String str, int i2, int i3) {
        if (j()) {
            this.f5235b.writeRawValue(str, i2, i3);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeRawValue(char[] cArr, int i2, int i3) {
        if (j()) {
            this.f5235b.writeRawValue(cArr, i2, i3);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray() {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            this.f5114h = this.f5114h.createChildArrayContext(null, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this.f5114h = this.f5114h.createChildArrayContext(tokenFilter, true);
            this.f5235b.writeStartArray();
            return;
        }
        TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
        this.f5115i = checkValue;
        if (checkValue == null) {
            this.f5114h = this.f5114h.createChildArrayContext(null, false);
            return;
        }
        if (checkValue != tokenFilter2) {
            this.f5115i = checkValue.filterStartArray();
        }
        TokenFilter tokenFilter3 = this.f5115i;
        if (tokenFilter3 != tokenFilter2) {
            this.f5114h = this.f5114h.createChildArrayContext(tokenFilter3, false);
            return;
        }
        h();
        this.f5114h = this.f5114h.createChildArrayContext(this.f5115i, true);
        this.f5235b.writeStartArray();
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray(int i2) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            this.f5114h = this.f5114h.createChildArrayContext(null, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this.f5114h = this.f5114h.createChildArrayContext(tokenFilter, true);
            this.f5235b.writeStartArray(i2);
            return;
        }
        TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
        this.f5115i = checkValue;
        if (checkValue == null) {
            this.f5114h = this.f5114h.createChildArrayContext(null, false);
            return;
        }
        if (checkValue != tokenFilter2) {
            this.f5115i = checkValue.filterStartArray();
        }
        TokenFilter tokenFilter3 = this.f5115i;
        if (tokenFilter3 != tokenFilter2) {
            this.f5114h = this.f5114h.createChildArrayContext(tokenFilter3, false);
            return;
        }
        h();
        this.f5114h = this.f5114h.createChildArrayContext(this.f5115i, true);
        this.f5235b.writeStartArray(i2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray(Object obj) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            this.f5114h = this.f5114h.createChildArrayContext(null, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this.f5114h = this.f5114h.createChildArrayContext(tokenFilter, true);
            this.f5235b.writeStartArray(obj);
            return;
        }
        TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
        this.f5115i = checkValue;
        if (checkValue == null) {
            this.f5114h = this.f5114h.createChildArrayContext(null, false);
            return;
        }
        if (checkValue != tokenFilter2) {
            this.f5115i = checkValue.filterStartArray();
        }
        TokenFilter tokenFilter3 = this.f5115i;
        if (tokenFilter3 != tokenFilter2) {
            this.f5114h = this.f5114h.createChildArrayContext(tokenFilter3, false);
            return;
        }
        h();
        this.f5114h = this.f5114h.createChildArrayContext(this.f5115i, true);
        this.f5235b.writeStartArray(obj);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeStartArray(Object obj, int i2) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            this.f5114h = this.f5114h.createChildArrayContext(null, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this.f5114h = this.f5114h.createChildArrayContext(tokenFilter, true);
            this.f5235b.writeStartArray(obj, i2);
            return;
        }
        TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
        this.f5115i = checkValue;
        if (checkValue == null) {
            this.f5114h = this.f5114h.createChildArrayContext(null, false);
            return;
        }
        if (checkValue != tokenFilter2) {
            this.f5115i = checkValue.filterStartArray();
        }
        TokenFilter tokenFilter3 = this.f5115i;
        if (tokenFilter3 != tokenFilter2) {
            this.f5114h = this.f5114h.createChildArrayContext(tokenFilter3, false);
            return;
        }
        h();
        this.f5114h = this.f5114h.createChildArrayContext(this.f5115i, true);
        this.f5235b.writeStartArray(obj, i2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject() {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            this.f5114h = this.f5114h.createChildObjectContext(tokenFilter, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this.f5114h = this.f5114h.createChildObjectContext(tokenFilter, true);
            this.f5235b.writeStartObject();
            return;
        }
        TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
        if (checkValue == null) {
            return;
        }
        if (checkValue != tokenFilter2) {
            checkValue = checkValue.filterStartObject();
        }
        if (checkValue != tokenFilter2) {
            this.f5114h = this.f5114h.createChildObjectContext(checkValue, false);
            return;
        }
        h();
        this.f5114h = this.f5114h.createChildObjectContext(checkValue, true);
        this.f5235b.writeStartObject();
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject(Object obj) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            this.f5114h = this.f5114h.createChildObjectContext(tokenFilter, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this.f5114h = this.f5114h.createChildObjectContext(tokenFilter, true);
            this.f5235b.writeStartObject(obj);
            return;
        }
        TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
        if (checkValue == null) {
            return;
        }
        if (checkValue != tokenFilter2) {
            checkValue = checkValue.filterStartObject();
        }
        if (checkValue != tokenFilter2) {
            this.f5114h = this.f5114h.createChildObjectContext(checkValue, false);
            return;
        }
        h();
        this.f5114h = this.f5114h.createChildObjectContext(checkValue, true);
        this.f5235b.writeStartObject(obj);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeStartObject(Object obj, int i2) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            this.f5114h = this.f5114h.createChildObjectContext(tokenFilter, false);
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter == tokenFilter2) {
            this.f5114h = this.f5114h.createChildObjectContext(tokenFilter, true);
            this.f5235b.writeStartObject(obj, i2);
            return;
        }
        TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
        if (checkValue == null) {
            return;
        }
        if (checkValue != tokenFilter2) {
            checkValue = checkValue.filterStartObject();
        }
        if (checkValue != tokenFilter2) {
            this.f5114h = this.f5114h.createChildObjectContext(checkValue, false);
            return;
        }
        h();
        this.f5114h = this.f5114h.createChildObjectContext(checkValue, true);
        this.f5235b.writeStartObject(obj, i2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeString(SerializableString serializableString) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeString(serializableString.getValue())) {
                return;
            }
            h();
        }
        this.f5235b.writeString(serializableString);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeString(Reader reader, int i2) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeString(reader, i2)) {
                return;
            }
            h();
        }
        this.f5235b.writeString(reader, i2);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeString(String str) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            TokenFilter checkValue = this.f5114h.checkValue(tokenFilter);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeString(str)) {
                return;
            }
            h();
        }
        this.f5235b.writeString(str);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeString(char[] cArr, int i2, int i3) {
        TokenFilter tokenFilter = this.f5115i;
        if (tokenFilter == null) {
            return;
        }
        TokenFilter tokenFilter2 = TokenFilter.INCLUDE_ALL;
        if (tokenFilter != tokenFilter2) {
            String str = new String(cArr, i2, i3);
            TokenFilter checkValue = this.f5114h.checkValue(this.f5115i);
            if (checkValue == null) {
                return;
            }
            if (checkValue != tokenFilter2 && !checkValue.includeString(str)) {
                return;
            }
            h();
        }
        this.f5235b.writeString(cArr, i2, i3);
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeTypeId(Object obj) {
        if (this.f5115i != null) {
            this.f5235b.writeTypeId(obj);
        }
    }

    @Override // com.fasterxml.jackson.core.util.JsonGeneratorDelegate, com.fasterxml.jackson.core.JsonGenerator
    public void writeUTF8String(byte[] bArr, int i2, int i3) {
        if (j()) {
            this.f5235b.writeUTF8String(bArr, i2, i3);
        }
    }
}
