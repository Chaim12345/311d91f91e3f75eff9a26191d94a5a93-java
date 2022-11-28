package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.DataOutputAsStream;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.net.URL;
/* loaded from: classes.dex */
public abstract class TokenStreamFactory implements Versioned, Serializable {
    private static final long serialVersionUID = 2;

    /* JADX INFO: Access modifiers changed from: protected */
    public OutputStream a(DataOutput dataOutput) {
        return new DataOutputAsStream(dataOutput);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public InputStream b(URL url) {
        String host;
        return (!"file".equals(url.getProtocol()) || !((host = url.getHost()) == null || host.length() == 0) || url.getPath().indexOf(37) >= 0) ? url.openStream() : new FileInputStream(url.getPath());
    }

    public abstract boolean canHandleBinaryNatively();

    public abstract boolean canParseAsync();

    public abstract boolean canUseSchema(FormatSchema formatSchema);

    public abstract JsonGenerator createGenerator(DataOutput dataOutput);

    public abstract JsonGenerator createGenerator(DataOutput dataOutput, JsonEncoding jsonEncoding);

    public abstract JsonGenerator createGenerator(File file, JsonEncoding jsonEncoding);

    public abstract JsonGenerator createGenerator(OutputStream outputStream);

    public abstract JsonGenerator createGenerator(OutputStream outputStream, JsonEncoding jsonEncoding);

    public abstract JsonGenerator createGenerator(Writer writer);

    public abstract JsonParser createNonBlockingByteArrayParser();

    public abstract JsonParser createParser(DataInput dataInput);

    public abstract JsonParser createParser(File file);

    public abstract JsonParser createParser(InputStream inputStream);

    public abstract JsonParser createParser(Reader reader);

    public abstract JsonParser createParser(String str);

    public abstract JsonParser createParser(URL url);

    public abstract JsonParser createParser(byte[] bArr);

    public abstract JsonParser createParser(byte[] bArr, int i2, int i3);

    public abstract JsonParser createParser(char[] cArr);

    public abstract JsonParser createParser(char[] cArr, int i2, int i3);

    public abstract int getFormatGeneratorFeatures();

    public abstract String getFormatName();

    public abstract int getFormatParserFeatures();

    public abstract Class<? extends FormatFeature> getFormatReadFeatureType();

    public abstract Class<? extends FormatFeature> getFormatWriteFeatureType();

    public abstract int getGeneratorFeatures();

    public abstract int getParserFeatures();

    public abstract boolean isEnabled(JsonGenerator.Feature feature);

    public abstract boolean isEnabled(JsonParser.Feature feature);

    public abstract boolean requiresPropertyOrdering();
}
