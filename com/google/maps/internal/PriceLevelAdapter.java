package com.google.maps.internal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.maps.model.PriceLevel;
/* loaded from: classes2.dex */
public class PriceLevelAdapter extends TypeAdapter<PriceLevel> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public PriceLevel read(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        if (jsonReader.peek() == JsonToken.NUMBER) {
            int nextInt = jsonReader.nextInt();
            if (nextInt == 0) {
                return PriceLevel.FREE;
            }
            if (nextInt == 1) {
                return PriceLevel.INEXPENSIVE;
            }
            if (nextInt == 2) {
                return PriceLevel.MODERATE;
            }
            if (nextInt == 3) {
                return PriceLevel.EXPENSIVE;
            }
            if (nextInt == 4) {
                return PriceLevel.VERY_EXPENSIVE;
            }
        }
        return PriceLevel.UNKNOWN;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, PriceLevel priceLevel) {
        throw new UnsupportedOperationException("Unimplemented method");
    }
}
