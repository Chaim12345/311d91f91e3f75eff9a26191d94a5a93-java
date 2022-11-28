package com.google.api.client.json;

import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Types;
import java.io.Closeable;
import java.io.Flushable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class JsonGenerator implements Closeable, Flushable {
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0102, code lost:
        if (r9 == null) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void serialize(boolean z, Object obj) {
        boolean z2;
        String name;
        if (obj == null) {
            return;
        }
        Class<?> cls = obj.getClass();
        if (!Data.isNull(obj)) {
            if (obj instanceof String) {
                writeString((String) obj);
                return;
            }
            boolean z3 = true;
            if (obj instanceof Number) {
                if (!z) {
                    if (obj instanceof BigDecimal) {
                        writeNumber((BigDecimal) obj);
                        return;
                    } else if (obj instanceof BigInteger) {
                        writeNumber((BigInteger) obj);
                        return;
                    } else if (obj instanceof Long) {
                        writeNumber(((Long) obj).longValue());
                        return;
                    } else if (obj instanceof Float) {
                        float floatValue = ((Number) obj).floatValue();
                        Preconditions.checkArgument((Float.isInfinite(floatValue) || Float.isNaN(floatValue)) ? false : false);
                        writeNumber(floatValue);
                        return;
                    } else if ((obj instanceof Integer) || (obj instanceof Short) || (obj instanceof Byte)) {
                        writeNumber(((Number) obj).intValue());
                        return;
                    } else {
                        double doubleValue = ((Number) obj).doubleValue();
                        Preconditions.checkArgument((Double.isInfinite(doubleValue) || Double.isNaN(doubleValue)) ? false : false);
                        writeNumber(doubleValue);
                        return;
                    }
                }
                name = obj.toString();
            } else if (obj instanceof Boolean) {
                writeBoolean(((Boolean) obj).booleanValue());
                return;
            } else if (obj instanceof DateTime) {
                name = ((DateTime) obj).toStringRfc3339();
            } else if (((obj instanceof Iterable) || cls.isArray()) && !(obj instanceof Map) && !(obj instanceof GenericData)) {
                writeStartArray();
                for (Object obj2 : Types.iterableOf(obj)) {
                    serialize(z, obj2);
                }
                writeEndArray();
                return;
            } else if (!cls.isEnum()) {
                writeStartObject();
                boolean z4 = (obj instanceof Map) && !(obj instanceof GenericData);
                ClassInfo of = z4 ? null : ClassInfo.of(cls);
                for (Map.Entry<String, Object> entry : Data.mapOf(obj).entrySet()) {
                    Object value = entry.getValue();
                    if (value != null) {
                        String key = entry.getKey();
                        if (z4) {
                            z2 = z;
                        } else {
                            Field field = of.getField(key);
                            z2 = (field == null || field.getAnnotation(JsonString.class) == null) ? false : true;
                        }
                        writeFieldName(key);
                        serialize(z2, value);
                    }
                }
                writeEndObject();
                return;
            } else {
                name = FieldInfo.of((Enum) obj).getName();
            }
            writeString(name);
            return;
        }
        writeNull();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public void enablePrettyPrint() {
    }

    @Override // java.io.Flushable
    public abstract void flush();

    public abstract JsonFactory getFactory();

    public final void serialize(Object obj) {
        serialize(false, obj);
    }

    public abstract void writeBoolean(boolean z);

    public abstract void writeEndArray();

    public abstract void writeEndObject();

    public abstract void writeFieldName(String str);

    public abstract void writeNull();

    public abstract void writeNumber(double d2);

    public abstract void writeNumber(float f2);

    public abstract void writeNumber(int i2);

    public abstract void writeNumber(long j2);

    public abstract void writeNumber(String str);

    public abstract void writeNumber(BigDecimal bigDecimal);

    public abstract void writeNumber(BigInteger bigInteger);

    public abstract void writeStartArray();

    public abstract void writeStartObject();

    public abstract void writeString(String str);
}
