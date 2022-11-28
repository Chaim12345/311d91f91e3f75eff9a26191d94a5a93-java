package com.google.maps.internal;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.lang.Enum;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* loaded from: classes2.dex */
public class SafeEnumAdapter<E extends Enum<E>> extends TypeAdapter<E> {
    private static final Logger LOG = LoggerFactory.getLogger(SafeEnumAdapter.class.getName());
    private final Class<E> clazz;
    private final E unknownValue;

    public SafeEnumAdapter(E e2) {
        if (e2 == null) {
            throw new IllegalArgumentException();
        }
        this.unknownValue = e2;
        this.clazz = e2.getDeclaringClass();
    }

    @Override // com.google.gson.TypeAdapter
    public E read(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        String nextString = jsonReader.nextString();
        try {
            return (E) Enum.valueOf(this.clazz, nextString.toUpperCase(Locale.ENGLISH));
        } catch (IllegalArgumentException unused) {
            LOG.warn("Unknown type for enum {}: '{}'", this.clazz.getName(), nextString);
            return this.unknownValue;
        }
    }

    public void write(JsonWriter jsonWriter, E e2) {
        throw new UnsupportedOperationException("Unimplemented method");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.gson.TypeAdapter
    public /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, Object obj) {
        write(jsonWriter, (JsonWriter) ((Enum) obj));
    }
}
