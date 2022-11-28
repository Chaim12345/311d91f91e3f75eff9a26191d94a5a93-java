package com.google.maps.internal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.maps.model.Distance;
import org.bouncycastle.i18n.TextBundle;
/* loaded from: classes2.dex */
public class DistanceAdapter extends TypeAdapter<Distance> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Distance read(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        Distance distance = new Distance();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(TextBundle.TEXT_ENTRY)) {
                distance.humanReadable = jsonReader.nextString();
            } else if (nextName.equals("value")) {
                distance.inMeters = jsonReader.nextLong();
            }
        }
        jsonReader.endObject();
        return distance;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Distance distance) {
        throw new UnsupportedOperationException("Unimplemented method");
    }
}
