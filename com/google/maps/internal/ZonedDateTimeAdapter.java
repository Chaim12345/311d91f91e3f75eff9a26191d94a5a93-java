package com.google.maps.internal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.bouncycastle.i18n.TextBundle;
/* loaded from: classes2.dex */
public class ZonedDateTimeAdapter extends TypeAdapter<ZonedDateTime> {
    @Override // com.google.gson.TypeAdapter
    public ZonedDateTime read(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        long j2 = 0;
        jsonReader.beginObject();
        String str = "";
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals(TextBundle.TEXT_ENTRY)) {
                jsonReader.nextString();
            } else if (nextName.equals("time_zone")) {
                str = jsonReader.nextString();
            } else if (nextName.equals("value")) {
                j2 = jsonReader.nextLong();
            }
        }
        jsonReader.endObject();
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(j2 * 1000), ZoneId.of(str));
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, ZonedDateTime zonedDateTime) {
        throw new UnsupportedOperationException("Unimplemented method");
    }
}
