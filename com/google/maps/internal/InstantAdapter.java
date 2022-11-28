package com.google.maps.internal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.time.Instant;
/* loaded from: classes2.dex */
public class InstantAdapter extends TypeAdapter<Instant> {
    @Override // com.google.gson.TypeAdapter
    public Instant read(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        } else if (jsonReader.peek() == JsonToken.NUMBER) {
            return Instant.ofEpochMilli(jsonReader.nextLong() * 1000);
        } else {
            throw new UnsupportedOperationException("Unsupported format");
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Instant instant) {
        throw new UnsupportedOperationException("Unimplemented method");
    }
}
