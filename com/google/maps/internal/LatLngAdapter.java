package com.google.maps.internal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.maps.model.LatLng;
/* loaded from: classes2.dex */
public class LatLngAdapter extends TypeAdapter<LatLng> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public LatLng read(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        jsonReader.beginObject();
        boolean z = false;
        double d2 = 0.0d;
        boolean z2 = false;
        double d3 = 0.0d;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if ("lat".equals(nextName) || "latitude".equals(nextName)) {
                d2 = jsonReader.nextDouble();
                z = true;
            } else if ("lng".equals(nextName) || "longitude".equals(nextName)) {
                d3 = jsonReader.nextDouble();
                z2 = true;
            }
        }
        jsonReader.endObject();
        if (z && z2) {
            return new LatLng(d2, d3);
        }
        return null;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, LatLng latLng) {
        throw new UnsupportedOperationException("Unimplemented method.");
    }
}
