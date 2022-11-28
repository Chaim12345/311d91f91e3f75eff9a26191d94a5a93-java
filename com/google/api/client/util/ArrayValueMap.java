package com.google.api.client.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
/* loaded from: classes2.dex */
public final class ArrayValueMap {
    private final Object destination;
    private final Map<String, ArrayValue> keyMap = ArrayMap.create();
    private final Map<Field, ArrayValue> fieldMap = ArrayMap.create();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class ArrayValue {

        /* renamed from: a  reason: collision with root package name */
        final Class f8074a;

        /* renamed from: b  reason: collision with root package name */
        final ArrayList f8075b = new ArrayList();

        ArrayValue(Class cls) {
            this.f8074a = cls;
        }

        void a(Class cls, Object obj) {
            Preconditions.checkArgument(cls == this.f8074a);
            this.f8075b.add(obj);
        }

        Object b() {
            return Types.toArray(this.f8075b, this.f8074a);
        }
    }

    public ArrayValueMap(Object obj) {
        this.destination = obj;
    }

    public void put(String str, Class<?> cls, Object obj) {
        ArrayValue arrayValue = this.keyMap.get(str);
        if (arrayValue == null) {
            arrayValue = new ArrayValue(cls);
            this.keyMap.put(str, arrayValue);
        }
        arrayValue.a(cls, obj);
    }

    public void put(Field field, Class<?> cls, Object obj) {
        ArrayValue arrayValue = this.fieldMap.get(field);
        if (arrayValue == null) {
            arrayValue = new ArrayValue(cls);
            this.fieldMap.put(field, arrayValue);
        }
        arrayValue.a(cls, obj);
    }

    public void setValues() {
        for (Map.Entry<String, ArrayValue> entry : this.keyMap.entrySet()) {
            ((Map) this.destination).put(entry.getKey(), entry.getValue().b());
        }
        for (Map.Entry<Field, ArrayValue> entry2 : this.fieldMap.entrySet()) {
            FieldInfo.setFieldValue(entry2.getKey(), this.destination, entry2.getValue().b());
        }
    }
}
