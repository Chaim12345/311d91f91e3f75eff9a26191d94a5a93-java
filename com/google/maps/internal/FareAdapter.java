package com.google.maps.internal;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.maps.model.Fare;
import java.math.BigDecimal;
import java.util.Currency;
/* loaded from: classes2.dex */
public class FareAdapter extends TypeAdapter<Fare> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public Fare read(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        Fare fare = new Fare();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (FirebaseAnalytics.Param.CURRENCY.equals(nextName)) {
                fare.currency = Currency.getInstance(jsonReader.nextString());
            } else if ("value".equals(nextName)) {
                fare.value = new BigDecimal(jsonReader.nextString());
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return fare;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, Fare fare) {
        throw new UnsupportedOperationException("Unimplemented method");
    }
}
