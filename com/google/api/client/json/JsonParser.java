package com.google.api.client.json;

import com.google.api.client.json.JsonPolymorphicTypeMap;
import com.google.api.client.util.Beta;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Sets;
import com.google.api.client.util.Types;
import java.io.Closeable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes2.dex */
public abstract class JsonParser implements Closeable {
    private static WeakHashMap<Class<?>, Field> cachedTypemapFields = new WeakHashMap<>();
    private static final Lock lock = new ReentrantLock();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.api.client.json.JsonParser$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8062a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            f8062a = iArr;
            try {
                iArr[JsonToken.START_OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8062a[JsonToken.START_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8062a[JsonToken.END_ARRAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8062a[JsonToken.FIELD_NAME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8062a[JsonToken.END_OBJECT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f8062a[JsonToken.VALUE_TRUE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f8062a[JsonToken.VALUE_FALSE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f8062a[JsonToken.VALUE_NUMBER_FLOAT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f8062a[JsonToken.VALUE_NUMBER_INT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f8062a[JsonToken.VALUE_STRING.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f8062a[JsonToken.VALUE_NULL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
        }
    }

    private static Field getCachedTypemapFieldFor(Class<?> cls) {
        Field field = null;
        if (cls == null) {
            return null;
        }
        Lock lock2 = lock;
        lock2.lock();
        try {
            if (cachedTypemapFields.containsKey(cls)) {
                Field field2 = cachedTypemapFields.get(cls);
                lock2.unlock();
                return field2;
            }
            for (FieldInfo fieldInfo : ClassInfo.of(cls).getFieldInfos()) {
                Field field3 = fieldInfo.getField();
                JsonPolymorphicTypeMap jsonPolymorphicTypeMap = (JsonPolymorphicTypeMap) field3.getAnnotation(JsonPolymorphicTypeMap.class);
                if (jsonPolymorphicTypeMap != null) {
                    Preconditions.checkArgument(field == null, "Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", cls);
                    Preconditions.checkArgument(Data.isPrimitive(field3.getType()), "Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", cls, field3.getType());
                    JsonPolymorphicTypeMap.TypeDef[] typeDefinitions = jsonPolymorphicTypeMap.typeDefinitions();
                    HashSet newHashSet = Sets.newHashSet();
                    Preconditions.checkArgument(typeDefinitions.length > 0, "@JsonPolymorphicTypeMap must have at least one @TypeDef");
                    for (JsonPolymorphicTypeMap.TypeDef typeDef : typeDefinitions) {
                        Preconditions.checkArgument(newHashSet.add(typeDef.key()), "Class contains two @TypeDef annotations with identical key: %s", typeDef.key());
                    }
                    field = field3;
                }
            }
            cachedTypemapFields.put(cls, field);
            return field;
        } finally {
            lock.unlock();
        }
    }

    private void parse(ArrayList<Type> arrayList, Object obj, CustomizeJsonParser customizeJsonParser) {
        if (obj instanceof GenericJson) {
            ((GenericJson) obj).setFactory(getFactory());
        }
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        Class<?> cls = obj.getClass();
        ClassInfo of = ClassInfo.of(cls);
        boolean isAssignableFrom = GenericData.class.isAssignableFrom(cls);
        if (!isAssignableFrom && Map.class.isAssignableFrom(cls)) {
            parseMap(null, (Map) obj, Types.getMapValueParameter(cls), arrayList, customizeJsonParser);
            return;
        }
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (customizeJsonParser != null && customizeJsonParser.stopAt(obj, text)) {
                return;
            }
            FieldInfo fieldInfo = of.getFieldInfo(text);
            if (fieldInfo != null) {
                if (fieldInfo.isFinal() && !fieldInfo.isPrimitive()) {
                    throw new IllegalArgumentException("final array/object fields are not supported");
                }
                Field field = fieldInfo.getField();
                int size = arrayList.size();
                arrayList.add(field.getGenericType());
                Object parseValue = parseValue(field, fieldInfo.getGenericType(), arrayList, obj, customizeJsonParser, true);
                arrayList.remove(size);
                fieldInfo.setValue(obj, parseValue);
            } else if (isAssignableFrom) {
                ((GenericData) obj).set(text, parseValue(null, null, arrayList, obj, customizeJsonParser, true));
            } else {
                if (customizeJsonParser != null) {
                    customizeJsonParser.handleUnrecognizedKey(obj, text);
                }
                skipChildren();
            }
            startParsingObjectOrArray = nextToken();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T> void parseArray(Field field, Collection<T> collection, Type type, ArrayList<Type> arrayList, CustomizeJsonParser customizeJsonParser) {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray != JsonToken.END_ARRAY) {
            collection.add(parseValue(field, type, arrayList, collection, customizeJsonParser, true));
            startParsingObjectOrArray = nextToken();
        }
    }

    private void parseMap(Field field, Map<String, Object> map, Type type, ArrayList<Type> arrayList, CustomizeJsonParser customizeJsonParser) {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (customizeJsonParser != null && customizeJsonParser.stopAt(map, text)) {
                return;
            }
            map.put(text, parseValue(field, type, arrayList, map, customizeJsonParser, true));
            startParsingObjectOrArray = nextToken();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x01a5 A[Catch: IllegalArgumentException -> 0x030f, TryCatch #0 {IllegalArgumentException -> 0x030f, blocks: (B:14:0x0029, B:15:0x0033, B:16:0x0036, B:213:0x02fa, B:214:0x030e, B:18:0x003c, B:21:0x0043, B:23:0x004a, B:25:0x0052, B:27:0x005a, B:29:0x0067, B:31:0x006f, B:33:0x007c, B:35:0x0085, B:38:0x0099, B:48:0x00b9, B:51:0x00c3, B:54:0x00cc, B:55:0x00d1, B:41:0x009f, B:43:0x00a7, B:45:0x00af, B:58:0x00dc, B:61:0x00e5, B:63:0x00ec, B:68:0x00fa, B:71:0x0101, B:76:0x010b, B:80:0x0112, B:85:0x011b, B:90:0x0124, B:95:0x012d, B:98:0x0132, B:99:0x0148, B:100:0x0149, B:102:0x0152, B:104:0x015b, B:106:0x0164, B:108:0x016d, B:110:0x0176, B:112:0x017f, B:115:0x0186, B:118:0x018c, B:123:0x0198, B:125:0x01a5, B:126:0x01a8, B:128:0x01ab, B:132:0x01b5, B:137:0x01c1, B:140:0x01ce, B:143:0x01d6, B:145:0x01dc, B:150:0x01ef, B:152:0x01fe, B:147:0x01e3, B:149:0x01eb, B:155:0x0208, B:159:0x0211, B:161:0x021c, B:165:0x0226, B:168:0x022e, B:173:0x023b, B:180:0x0251, B:182:0x0257, B:184:0x025c, B:186:0x0264, B:188:0x026c, B:191:0x0275, B:193:0x0281, B:195:0x0286, B:198:0x028c, B:202:0x029c, B:204:0x02b5, B:206:0x02c1, B:207:0x02c6, B:211:0x02cd, B:178:0x0248, B:179:0x024d), top: B:225:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01a8 A[Catch: IllegalArgumentException -> 0x030f, TryCatch #0 {IllegalArgumentException -> 0x030f, blocks: (B:14:0x0029, B:15:0x0033, B:16:0x0036, B:213:0x02fa, B:214:0x030e, B:18:0x003c, B:21:0x0043, B:23:0x004a, B:25:0x0052, B:27:0x005a, B:29:0x0067, B:31:0x006f, B:33:0x007c, B:35:0x0085, B:38:0x0099, B:48:0x00b9, B:51:0x00c3, B:54:0x00cc, B:55:0x00d1, B:41:0x009f, B:43:0x00a7, B:45:0x00af, B:58:0x00dc, B:61:0x00e5, B:63:0x00ec, B:68:0x00fa, B:71:0x0101, B:76:0x010b, B:80:0x0112, B:85:0x011b, B:90:0x0124, B:95:0x012d, B:98:0x0132, B:99:0x0148, B:100:0x0149, B:102:0x0152, B:104:0x015b, B:106:0x0164, B:108:0x016d, B:110:0x0176, B:112:0x017f, B:115:0x0186, B:118:0x018c, B:123:0x0198, B:125:0x01a5, B:126:0x01a8, B:128:0x01ab, B:132:0x01b5, B:137:0x01c1, B:140:0x01ce, B:143:0x01d6, B:145:0x01dc, B:150:0x01ef, B:152:0x01fe, B:147:0x01e3, B:149:0x01eb, B:155:0x0208, B:159:0x0211, B:161:0x021c, B:165:0x0226, B:168:0x022e, B:173:0x023b, B:180:0x0251, B:182:0x0257, B:184:0x025c, B:186:0x0264, B:188:0x026c, B:191:0x0275, B:193:0x0281, B:195:0x0286, B:198:0x028c, B:202:0x029c, B:204:0x02b5, B:206:0x02c1, B:207:0x02c6, B:211:0x02cd, B:178:0x0248, B:179:0x024d), top: B:225:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01d6 A[Catch: IllegalArgumentException -> 0x030f, TryCatch #0 {IllegalArgumentException -> 0x030f, blocks: (B:14:0x0029, B:15:0x0033, B:16:0x0036, B:213:0x02fa, B:214:0x030e, B:18:0x003c, B:21:0x0043, B:23:0x004a, B:25:0x0052, B:27:0x005a, B:29:0x0067, B:31:0x006f, B:33:0x007c, B:35:0x0085, B:38:0x0099, B:48:0x00b9, B:51:0x00c3, B:54:0x00cc, B:55:0x00d1, B:41:0x009f, B:43:0x00a7, B:45:0x00af, B:58:0x00dc, B:61:0x00e5, B:63:0x00ec, B:68:0x00fa, B:71:0x0101, B:76:0x010b, B:80:0x0112, B:85:0x011b, B:90:0x0124, B:95:0x012d, B:98:0x0132, B:99:0x0148, B:100:0x0149, B:102:0x0152, B:104:0x015b, B:106:0x0164, B:108:0x016d, B:110:0x0176, B:112:0x017f, B:115:0x0186, B:118:0x018c, B:123:0x0198, B:125:0x01a5, B:126:0x01a8, B:128:0x01ab, B:132:0x01b5, B:137:0x01c1, B:140:0x01ce, B:143:0x01d6, B:145:0x01dc, B:150:0x01ef, B:152:0x01fe, B:147:0x01e3, B:149:0x01eb, B:155:0x0208, B:159:0x0211, B:161:0x021c, B:165:0x0226, B:168:0x022e, B:173:0x023b, B:180:0x0251, B:182:0x0257, B:184:0x025c, B:186:0x0264, B:188:0x026c, B:191:0x0275, B:193:0x0281, B:195:0x0286, B:198:0x028c, B:202:0x029c, B:204:0x02b5, B:206:0x02c1, B:207:0x02c6, B:211:0x02cd, B:178:0x0248, B:179:0x024d), top: B:225:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01dc A[Catch: IllegalArgumentException -> 0x030f, TryCatch #0 {IllegalArgumentException -> 0x030f, blocks: (B:14:0x0029, B:15:0x0033, B:16:0x0036, B:213:0x02fa, B:214:0x030e, B:18:0x003c, B:21:0x0043, B:23:0x004a, B:25:0x0052, B:27:0x005a, B:29:0x0067, B:31:0x006f, B:33:0x007c, B:35:0x0085, B:38:0x0099, B:48:0x00b9, B:51:0x00c3, B:54:0x00cc, B:55:0x00d1, B:41:0x009f, B:43:0x00a7, B:45:0x00af, B:58:0x00dc, B:61:0x00e5, B:63:0x00ec, B:68:0x00fa, B:71:0x0101, B:76:0x010b, B:80:0x0112, B:85:0x011b, B:90:0x0124, B:95:0x012d, B:98:0x0132, B:99:0x0148, B:100:0x0149, B:102:0x0152, B:104:0x015b, B:106:0x0164, B:108:0x016d, B:110:0x0176, B:112:0x017f, B:115:0x0186, B:118:0x018c, B:123:0x0198, B:125:0x01a5, B:126:0x01a8, B:128:0x01ab, B:132:0x01b5, B:137:0x01c1, B:140:0x01ce, B:143:0x01d6, B:145:0x01dc, B:150:0x01ef, B:152:0x01fe, B:147:0x01e3, B:149:0x01eb, B:155:0x0208, B:159:0x0211, B:161:0x021c, B:165:0x0226, B:168:0x022e, B:173:0x023b, B:180:0x0251, B:182:0x0257, B:184:0x025c, B:186:0x0264, B:188:0x026c, B:191:0x0275, B:193:0x0281, B:195:0x0286, B:198:0x028c, B:202:0x029c, B:204:0x02b5, B:206:0x02c1, B:207:0x02c6, B:211:0x02cd, B:178:0x0248, B:179:0x024d), top: B:225:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01e1  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01fe A[Catch: IllegalArgumentException -> 0x030f, TryCatch #0 {IllegalArgumentException -> 0x030f, blocks: (B:14:0x0029, B:15:0x0033, B:16:0x0036, B:213:0x02fa, B:214:0x030e, B:18:0x003c, B:21:0x0043, B:23:0x004a, B:25:0x0052, B:27:0x005a, B:29:0x0067, B:31:0x006f, B:33:0x007c, B:35:0x0085, B:38:0x0099, B:48:0x00b9, B:51:0x00c3, B:54:0x00cc, B:55:0x00d1, B:41:0x009f, B:43:0x00a7, B:45:0x00af, B:58:0x00dc, B:61:0x00e5, B:63:0x00ec, B:68:0x00fa, B:71:0x0101, B:76:0x010b, B:80:0x0112, B:85:0x011b, B:90:0x0124, B:95:0x012d, B:98:0x0132, B:99:0x0148, B:100:0x0149, B:102:0x0152, B:104:0x015b, B:106:0x0164, B:108:0x016d, B:110:0x0176, B:112:0x017f, B:115:0x0186, B:118:0x018c, B:123:0x0198, B:125:0x01a5, B:126:0x01a8, B:128:0x01ab, B:132:0x01b5, B:137:0x01c1, B:140:0x01ce, B:143:0x01d6, B:145:0x01dc, B:150:0x01ef, B:152:0x01fe, B:147:0x01e3, B:149:0x01eb, B:155:0x0208, B:159:0x0211, B:161:0x021c, B:165:0x0226, B:168:0x022e, B:173:0x023b, B:180:0x0251, B:182:0x0257, B:184:0x025c, B:186:0x0264, B:188:0x026c, B:191:0x0275, B:193:0x0281, B:195:0x0286, B:198:0x028c, B:202:0x029c, B:204:0x02b5, B:206:0x02c1, B:207:0x02c6, B:211:0x02cd, B:178:0x0248, B:179:0x024d), top: B:225:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0207 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Object parseValue(Field field, Type type, ArrayList<Type> arrayList, Object obj, CustomizeJsonParser customizeJsonParser, boolean z) {
        boolean z2;
        Collection<Object> newInstanceForArray;
        boolean z3;
        Type resolveWildcardTypeOrTypeVariable = Data.resolveWildcardTypeOrTypeVariable(arrayList, type);
        Type type2 = null;
        Class<?> cls = resolveWildcardTypeOrTypeVariable instanceof Class ? (Class) resolveWildcardTypeOrTypeVariable : null;
        if (resolveWildcardTypeOrTypeVariable instanceof ParameterizedType) {
            cls = Types.getRawClass((ParameterizedType) resolveWildcardTypeOrTypeVariable);
        }
        if (cls == Void.class) {
            skipChildren();
            return null;
        }
        JsonToken currentToken = getCurrentToken();
        try {
            switch (AnonymousClass1.f8062a[currentToken.ordinal()]) {
                case 1:
                case 4:
                case 5:
                    Preconditions.checkArgument(!Types.isArray(resolveWildcardTypeOrTypeVariable), "expected object or map type but got %s", resolveWildcardTypeOrTypeVariable);
                    Field cachedTypemapFieldFor = z ? getCachedTypemapFieldFor(cls) : null;
                    Object newInstanceForObject = (cls == null || customizeJsonParser == null) ? null : customizeJsonParser.newInstanceForObject(obj, cls);
                    boolean z4 = cls != null && Types.isAssignableToOrFrom(cls, Map.class);
                    if (cachedTypemapFieldFor != null) {
                        newInstanceForObject = new GenericJson();
                    } else if (newInstanceForObject == null) {
                        if (!z4 && cls != null) {
                            newInstanceForObject = Types.newInstance(cls);
                        }
                        newInstanceForObject = Data.newMapInstance(cls);
                    }
                    int size = arrayList.size();
                    if (resolveWildcardTypeOrTypeVariable != null) {
                        arrayList.add(resolveWildcardTypeOrTypeVariable);
                    }
                    if (z4 && !GenericData.class.isAssignableFrom(cls)) {
                        Type mapValueParameter = Map.class.isAssignableFrom(cls) ? Types.getMapValueParameter(resolveWildcardTypeOrTypeVariable) : null;
                        if (mapValueParameter != null) {
                            parseMap(field, (Map) newInstanceForObject, mapValueParameter, arrayList, customizeJsonParser);
                            return newInstanceForObject;
                        }
                    }
                    parse(arrayList, newInstanceForObject, customizeJsonParser);
                    if (resolveWildcardTypeOrTypeVariable != null) {
                        arrayList.remove(size);
                    }
                    if (cachedTypemapFieldFor == null) {
                        return newInstanceForObject;
                    }
                    Object obj2 = ((GenericJson) newInstanceForObject).get(cachedTypemapFieldFor.getName());
                    Preconditions.checkArgument(obj2 != null, "No value specified for @JsonPolymorphicTypeMap field");
                    String obj3 = obj2.toString();
                    JsonPolymorphicTypeMap.TypeDef[] typeDefinitions = ((JsonPolymorphicTypeMap) cachedTypemapFieldFor.getAnnotation(JsonPolymorphicTypeMap.class)).typeDefinitions();
                    int length = typeDefinitions.length;
                    int i2 = 0;
                    while (true) {
                        if (i2 < length) {
                            JsonPolymorphicTypeMap.TypeDef typeDef = typeDefinitions[i2];
                            if (typeDef.key().equals(obj3)) {
                                type2 = typeDef.ref();
                            } else {
                                i2++;
                            }
                        }
                    }
                    Type type3 = type2;
                    Preconditions.checkArgument(type3 != null, "No TypeDef annotation found with key: " + obj3);
                    JsonFactory factory = getFactory();
                    JsonParser createJsonParser = factory.createJsonParser(factory.toString(newInstanceForObject));
                    createJsonParser.startParsing();
                    return createJsonParser.parseValue(field, type3, arrayList, null, null, false);
                case 2:
                case 3:
                    boolean isArray = Types.isArray(resolveWildcardTypeOrTypeVariable);
                    if (resolveWildcardTypeOrTypeVariable != null && !isArray && (cls == null || !Types.isAssignableToOrFrom(cls, Collection.class))) {
                        z2 = false;
                        Preconditions.checkArgument(z2, "expected collection or array type but got %s", resolveWildcardTypeOrTypeVariable);
                        newInstanceForArray = (customizeJsonParser != null || field == null) ? null : customizeJsonParser.newInstanceForArray(obj, field);
                        if (newInstanceForArray == null) {
                            newInstanceForArray = Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable);
                        }
                        if (!isArray) {
                            type2 = Types.getArrayComponentType(resolveWildcardTypeOrTypeVariable);
                        } else if (cls != null && Iterable.class.isAssignableFrom(cls)) {
                            type2 = Types.getIterableParameter(resolveWildcardTypeOrTypeVariable);
                        }
                        Type resolveWildcardTypeOrTypeVariable2 = Data.resolveWildcardTypeOrTypeVariable(arrayList, type2);
                        parseArray(field, newInstanceForArray, resolveWildcardTypeOrTypeVariable2, arrayList, customizeJsonParser);
                        return !isArray ? Types.toArray(newInstanceForArray, Types.getRawArrayComponentType(arrayList, resolveWildcardTypeOrTypeVariable2)) : newInstanceForArray;
                    }
                    z2 = true;
                    Preconditions.checkArgument(z2, "expected collection or array type but got %s", resolveWildcardTypeOrTypeVariable);
                    if (customizeJsonParser != null) {
                    }
                    if (newInstanceForArray == null) {
                    }
                    if (!isArray) {
                    }
                    Type resolveWildcardTypeOrTypeVariable22 = Data.resolveWildcardTypeOrTypeVariable(arrayList, type2);
                    parseArray(field, newInstanceForArray, resolveWildcardTypeOrTypeVariable22, arrayList, customizeJsonParser);
                    if (!isArray) {
                    }
                    break;
                case 6:
                case 7:
                    if (resolveWildcardTypeOrTypeVariable != null && cls != Boolean.TYPE && (cls == null || !cls.isAssignableFrom(Boolean.class))) {
                        z3 = false;
                        Preconditions.checkArgument(z3, "expected type Boolean or boolean but got %s", resolveWildcardTypeOrTypeVariable);
                        return currentToken != JsonToken.VALUE_TRUE ? Boolean.TRUE : Boolean.FALSE;
                    }
                    z3 = true;
                    Preconditions.checkArgument(z3, "expected type Boolean or boolean but got %s", resolveWildcardTypeOrTypeVariable);
                    if (currentToken != JsonToken.VALUE_TRUE) {
                    }
                    break;
                case 8:
                case 9:
                    if (field == null || field.getAnnotation(JsonString.class) == null) {
                        r6 = true;
                    }
                    Preconditions.checkArgument(r6, "number type formatted as a JSON number cannot use @JsonString annotation");
                    if (cls != null && !cls.isAssignableFrom(BigDecimal.class)) {
                        if (cls == BigInteger.class) {
                            return getBigIntegerValue();
                        }
                        if (cls != Double.class && cls != Double.TYPE) {
                            if (cls != Long.class && cls != Long.TYPE) {
                                if (cls != Float.class && cls != Float.TYPE) {
                                    if (cls != Integer.class && cls != Integer.TYPE) {
                                        if (cls != Short.class && cls != Short.TYPE) {
                                            if (cls != Byte.class && cls != Byte.TYPE) {
                                                throw new IllegalArgumentException("expected numeric type but got " + resolveWildcardTypeOrTypeVariable);
                                            }
                                            return Byte.valueOf(getByteValue());
                                        }
                                        return Short.valueOf(getShortValue());
                                    }
                                    return Integer.valueOf(getIntValue());
                                }
                                return Float.valueOf(getFloatValue());
                            }
                            return Long.valueOf(getLongValue());
                        }
                        return Double.valueOf(getDoubleValue());
                    }
                    return getDecimalValue();
                case 10:
                    String lowerCase = getText().trim().toLowerCase(Locale.US);
                    if ((cls != Float.TYPE && cls != Float.class && cls != Double.TYPE && cls != Double.class) || (!lowerCase.equals("nan") && !lowerCase.equals("infinity") && !lowerCase.equals("-infinity"))) {
                        if (cls == null || !Number.class.isAssignableFrom(cls) || (field != null && field.getAnnotation(JsonString.class) != null)) {
                            r6 = true;
                        }
                        Preconditions.checkArgument(r6, "number field formatted as a JSON string must use the @JsonString annotation");
                    }
                    return Data.parsePrimitiveValue(resolveWildcardTypeOrTypeVariable, getText());
                case 11:
                    if (cls == null || !cls.isPrimitive()) {
                        r6 = true;
                    }
                    Preconditions.checkArgument(r6, "primitive number field but found a JSON null");
                    if (cls != null && (cls.getModifiers() & 1536) != 0) {
                        if (Types.isAssignableToOrFrom(cls, Collection.class)) {
                            return Data.nullOf(Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable).getClass());
                        }
                        if (Types.isAssignableToOrFrom(cls, Map.class)) {
                            return Data.nullOf(Data.newMapInstance(cls).getClass());
                        }
                    }
                    return Data.nullOf(Types.getRawArrayComponentType(arrayList, resolveWildcardTypeOrTypeVariable));
                default:
                    throw new IllegalArgumentException("unexpected JSON node type: " + currentToken);
            }
        } catch (IllegalArgumentException e2) {
            StringBuilder sb = new StringBuilder();
            String currentName = getCurrentName();
            if (currentName != null) {
                sb.append("key ");
                sb.append(currentName);
            }
            if (field != null) {
                if (currentName != null) {
                    sb.append(", ");
                }
                sb.append("field ");
                sb.append(field);
            }
            throw new IllegalArgumentException(sb.toString(), e2);
        }
    }

    private JsonToken startParsing() {
        JsonToken currentToken = getCurrentToken();
        if (currentToken == null) {
            currentToken = nextToken();
        }
        Preconditions.checkArgument(currentToken != null, "no JSON input found");
        return currentToken;
    }

    private JsonToken startParsingObjectOrArray() {
        JsonToken startParsing = startParsing();
        int i2 = AnonymousClass1.f8062a[startParsing.ordinal()];
        boolean z = true;
        if (i2 != 1) {
            return i2 != 2 ? startParsing : nextToken();
        }
        JsonToken nextToken = nextToken();
        if (nextToken != JsonToken.FIELD_NAME && nextToken != JsonToken.END_OBJECT) {
            z = false;
        }
        Preconditions.checkArgument(z, nextToken);
        return nextToken;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract BigInteger getBigIntegerValue();

    public abstract byte getByteValue();

    public abstract String getCurrentName();

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue();

    public abstract double getDoubleValue();

    public abstract JsonFactory getFactory();

    public abstract float getFloatValue();

    public abstract int getIntValue();

    public abstract long getLongValue();

    public abstract short getShortValue();

    public abstract String getText();

    public abstract JsonToken nextToken();

    public final <T> T parse(Class<T> cls) {
        return (T) parse((Class<Object>) cls, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> T parse(Class<T> cls, CustomizeJsonParser customizeJsonParser) {
        return (T) parse((Type) cls, false, customizeJsonParser);
    }

    public Object parse(Type type, boolean z) {
        return parse(type, z, (CustomizeJsonParser) null);
    }

    @Beta
    public Object parse(Type type, boolean z, CustomizeJsonParser customizeJsonParser) {
        try {
            if (!Void.class.equals(type)) {
                startParsing();
            }
            return parseValue(null, type, new ArrayList<>(), null, customizeJsonParser, true);
        } finally {
            if (z) {
                close();
            }
        }
    }

    public final void parse(Object obj) {
        parse(obj, (CustomizeJsonParser) null);
    }

    @Beta
    public final void parse(Object obj, CustomizeJsonParser customizeJsonParser) {
        ArrayList<Type> arrayList = new ArrayList<>();
        arrayList.add(obj.getClass());
        parse(arrayList, obj, customizeJsonParser);
    }

    public final <T> T parseAndClose(Class<T> cls) {
        return (T) parseAndClose((Class<Object>) cls, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> T parseAndClose(Class<T> cls, CustomizeJsonParser customizeJsonParser) {
        try {
            return (T) parse((Class<Object>) cls, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final void parseAndClose(Object obj) {
        parseAndClose(obj, (CustomizeJsonParser) null);
    }

    @Beta
    public final void parseAndClose(Object obj, CustomizeJsonParser customizeJsonParser) {
        try {
            parse(obj, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> Collection<T> parseArray(Class<?> cls, Class<T> cls2) {
        return parseArray(cls, cls2, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> Collection<T> parseArray(Class<?> cls, Class<T> cls2, CustomizeJsonParser customizeJsonParser) {
        Collection<T> collection = (Collection<T>) Data.newCollectionInstance(cls);
        parseArray(collection, cls2, customizeJsonParser);
        return collection;
    }

    public final <T> void parseArray(Collection<? super T> collection, Class<T> cls) {
        parseArray(collection, cls, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> void parseArray(Collection<? super T> collection, Class<T> cls, CustomizeJsonParser customizeJsonParser) {
        parseArray(null, collection, cls, new ArrayList<>(), customizeJsonParser);
    }

    public final <T> Collection<T> parseArrayAndClose(Class<?> cls, Class<T> cls2) {
        return parseArrayAndClose(cls, cls2, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> Collection<T> parseArrayAndClose(Class<?> cls, Class<T> cls2, CustomizeJsonParser customizeJsonParser) {
        try {
            return parseArray(cls, cls2, customizeJsonParser);
        } finally {
            close();
        }
    }

    public final <T> void parseArrayAndClose(Collection<? super T> collection, Class<T> cls) {
        parseArrayAndClose(collection, cls, (CustomizeJsonParser) null);
    }

    @Beta
    public final <T> void parseArrayAndClose(Collection<? super T> collection, Class<T> cls, CustomizeJsonParser customizeJsonParser) {
        try {
            parseArray(collection, cls, customizeJsonParser);
        } finally {
            close();
        }
    }

    public abstract JsonParser skipChildren();

    public final String skipToKey(Set<String> set) {
        JsonToken startParsingObjectOrArray = startParsingObjectOrArray();
        while (startParsingObjectOrArray == JsonToken.FIELD_NAME) {
            String text = getText();
            nextToken();
            if (set.contains(text)) {
                return text;
            }
            skipChildren();
            startParsingObjectOrArray = nextToken();
        }
        return null;
    }

    public final void skipToKey(String str) {
        skipToKey(Collections.singleton(str));
    }
}
