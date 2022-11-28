package com.google.maps.internal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/* loaded from: classes2.dex */
public class LocalTimeAdapter extends TypeAdapter<LocalTime> {
    @Override // com.google.gson.TypeAdapter
    public LocalTime read(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        } else if (jsonReader.peek() == JsonToken.STRING) {
            return LocalTime.parse(jsonReader.nextString(), DateTimeFormatter.ofPattern("HHmm"));
        } else {
            throw new UnsupportedOperationException("Unsupported format");
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, LocalTime localTime) {
        throw new UnsupportedOperationException("Unimplemented method");
    }
}
