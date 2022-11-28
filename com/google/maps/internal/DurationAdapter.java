package com.google.maps.internal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.maps.model.Duration;
import org.bouncycastle.i18n.TextBundle;
/* loaded from: classes2.dex */
public class DurationAdapter extends TypeAdapter<Duration> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Duration read(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        Duration duration = new Duration();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(TextBundle.TEXT_ENTRY)) {
                duration.humanReadable = jsonReader.nextString();
            } else if (nextName.equals("value")) {
                duration.inSeconds = jsonReader.nextLong();
            }
        }
        jsonReader.endObject();
        return duration;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Duration duration) {
        throw new UnsupportedOperationException("Unimplemented method");
    }
}
