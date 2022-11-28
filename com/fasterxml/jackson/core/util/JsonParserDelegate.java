package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.Version;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
/* loaded from: classes.dex */
public class JsonParserDelegate extends JsonParser {

    /* renamed from: c  reason: collision with root package name */
    protected JsonParser f5237c;

    public JsonParserDelegate(JsonParser jsonParser) {
        this.f5237c = jsonParser;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean canReadObjectId() {
        return this.f5237c.canReadObjectId();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean canReadTypeId() {
        return this.f5237c.canReadTypeId();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean canUseSchema(FormatSchema formatSchema) {
        return this.f5237c.canUseSchema(formatSchema);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void clearCurrentToken() {
        this.f5237c.clearCurrentToken();
    }

    @Override // com.fasterxml.jackson.core.JsonParser, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f5237c.close();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken currentToken() {
        return this.f5237c.currentToken();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int currentTokenId() {
        return this.f5237c.currentTokenId();
    }

    public JsonParser delegate() {
        return this.f5237c;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser disable(JsonParser.Feature feature) {
        this.f5237c.disable(feature);
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser enable(JsonParser.Feature feature) {
        this.f5237c.enable(feature);
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void finishToken() {
        this.f5237c.finishToken();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public BigInteger getBigIntegerValue() {
        return this.f5237c.getBigIntegerValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public byte[] getBinaryValue(Base64Variant base64Variant) {
        return this.f5237c.getBinaryValue(base64Variant);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean getBooleanValue() {
        return this.f5237c.getBooleanValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public byte getByteValue() {
        return this.f5237c.getByteValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public ObjectCodec getCodec() {
        return this.f5237c.getCodec();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getCurrentLocation() {
        return this.f5237c.getCurrentLocation();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getCurrentName() {
        return this.f5237c.getCurrentName();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken getCurrentToken() {
        return this.f5237c.getCurrentToken();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getCurrentTokenId() {
        return this.f5237c.getCurrentTokenId();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getCurrentValue() {
        return this.f5237c.getCurrentValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public BigDecimal getDecimalValue() {
        return this.f5237c.getDecimalValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public double getDoubleValue() {
        return this.f5237c.getDoubleValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getEmbeddedObject() {
        return this.f5237c.getEmbeddedObject();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getFeatureMask() {
        return this.f5237c.getFeatureMask();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public float getFloatValue() {
        return this.f5237c.getFloatValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getInputSource() {
        return this.f5237c.getInputSource();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getIntValue() {
        return this.f5237c.getIntValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken getLastClearedToken() {
        return this.f5237c.getLastClearedToken();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getLongValue() {
        return this.f5237c.getLongValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser.NumberType getNumberType() {
        return this.f5237c.getNumberType();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Number getNumberValue() {
        return this.f5237c.getNumberValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getObjectId() {
        return this.f5237c.getObjectId();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonStreamContext getParsingContext() {
        return this.f5237c.getParsingContext();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public FormatSchema getSchema() {
        return this.f5237c.getSchema();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public short getShortValue() {
        return this.f5237c.getShortValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getText(Writer writer) {
        return this.f5237c.getText(writer);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getText() {
        return this.f5237c.getText();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public char[] getTextCharacters() {
        return this.f5237c.getTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getTextLength() {
        return this.f5237c.getTextLength();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getTextOffset() {
        return this.f5237c.getTextOffset();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonLocation getTokenLocation() {
        return this.f5237c.getTokenLocation();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public Object getTypeId() {
        return this.f5237c.getTypeId();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean() {
        return this.f5237c.getValueAsBoolean();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean getValueAsBoolean(boolean z) {
        return this.f5237c.getValueAsBoolean(z);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble() {
        return this.f5237c.getValueAsDouble();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public double getValueAsDouble(double d2) {
        return this.f5237c.getValueAsDouble(d2);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt() {
        return this.f5237c.getValueAsInt();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int getValueAsInt(int i2) {
        return this.f5237c.getValueAsInt(i2);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong() {
        return this.f5237c.getValueAsLong();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public long getValueAsLong(long j2) {
        return this.f5237c.getValueAsLong(j2);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString() {
        return this.f5237c.getValueAsString();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public String getValueAsString(String str) {
        return this.f5237c.getValueAsString(str);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasCurrentToken() {
        return this.f5237c.hasCurrentToken();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasTextCharacters() {
        return this.f5237c.hasTextCharacters();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasToken(JsonToken jsonToken) {
        return this.f5237c.hasToken(jsonToken);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean hasTokenId(int i2) {
        return this.f5237c.hasTokenId(i2);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isClosed() {
        return this.f5237c.isClosed();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isEnabled(JsonParser.Feature feature) {
        return this.f5237c.isEnabled(feature);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartArrayToken() {
        return this.f5237c.isExpectedStartArrayToken();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isExpectedStartObjectToken() {
        return this.f5237c.isExpectedStartObjectToken();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean isNaN() {
        return this.f5237c.isNaN();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken nextToken() {
        return this.f5237c.nextToken();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonToken nextValue() {
        return this.f5237c.nextValue();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void overrideCurrentName(String str) {
        this.f5237c.overrideCurrentName(str);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser overrideFormatFeatures(int i2, int i3) {
        this.f5237c.overrideFormatFeatures(i2, i3);
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser overrideStdFeatures(int i2, int i3) {
        this.f5237c.overrideStdFeatures(i2, i3);
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) {
        return this.f5237c.readBinaryValue(base64Variant, outputStream);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public boolean requiresCustomCodec() {
        return this.f5237c.requiresCustomCodec();
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCodec(ObjectCodec objectCodec) {
        this.f5237c.setCodec(objectCodec);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setCurrentValue(Object obj) {
        this.f5237c.setCurrentValue(obj);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    @Deprecated
    public JsonParser setFeatureMask(int i2) {
        this.f5237c.setFeatureMask(i2);
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public void setSchema(FormatSchema formatSchema) {
        this.f5237c.setSchema(formatSchema);
    }

    @Override // com.fasterxml.jackson.core.JsonParser
    public JsonParser skipChildren() {
        this.f5237c.skipChildren();
        return this;
    }

    @Override // com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.core.Versioned
    public Version version() {
        return this.f5237c.version();
    }
}
