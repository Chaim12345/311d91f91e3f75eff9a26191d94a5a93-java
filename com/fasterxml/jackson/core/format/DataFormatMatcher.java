package com.fasterxml.jackson.core.format;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.io.MergedStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
/* loaded from: classes.dex */
public class DataFormatMatcher {

    /* renamed from: a  reason: collision with root package name */
    protected final InputStream f5138a;

    /* renamed from: b  reason: collision with root package name */
    protected final byte[] f5139b;

    /* renamed from: c  reason: collision with root package name */
    protected final int f5140c;

    /* renamed from: d  reason: collision with root package name */
    protected final int f5141d;

    /* renamed from: e  reason: collision with root package name */
    protected final JsonFactory f5142e;

    /* renamed from: f  reason: collision with root package name */
    protected final MatchStrength f5143f;

    /* JADX INFO: Access modifiers changed from: protected */
    public DataFormatMatcher(InputStream inputStream, byte[] bArr, int i2, int i3, JsonFactory jsonFactory, MatchStrength matchStrength) {
        this.f5138a = inputStream;
        this.f5139b = bArr;
        this.f5140c = i2;
        this.f5141d = i3;
        this.f5142e = jsonFactory;
        this.f5143f = matchStrength;
        if ((i2 | i3) < 0 || i2 + i3 > bArr.length) {
            throw new IllegalArgumentException(String.format("Illegal start/length (%d/%d) wrt input array of %d bytes", Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(bArr.length)));
        }
    }

    public JsonParser createParserWithMatch() {
        JsonFactory jsonFactory = this.f5142e;
        if (jsonFactory == null) {
            return null;
        }
        return this.f5138a == null ? jsonFactory.createParser(this.f5139b, this.f5140c, this.f5141d) : jsonFactory.createParser(getDataStream());
    }

    public InputStream getDataStream() {
        return this.f5138a == null ? new ByteArrayInputStream(this.f5139b, this.f5140c, this.f5141d) : new MergedStream(null, this.f5138a, this.f5139b, this.f5140c, this.f5141d);
    }

    public JsonFactory getMatch() {
        return this.f5142e;
    }

    public MatchStrength getMatchStrength() {
        MatchStrength matchStrength = this.f5143f;
        return matchStrength == null ? MatchStrength.INCONCLUSIVE : matchStrength;
    }

    public String getMatchedFormatName() {
        if (hasMatch()) {
            return getMatch().getFormatName();
        }
        return null;
    }

    public boolean hasMatch() {
        return this.f5142e != null;
    }
}
