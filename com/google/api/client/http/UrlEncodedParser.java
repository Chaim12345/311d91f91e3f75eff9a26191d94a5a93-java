package com.google.api.client.http;

import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.Throwables;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class UrlEncodedParser implements ObjectParser {
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String MEDIA_TYPE = new HttpMediaType("application/x-www-form-urlencoded").setCharsetParameter(Charsets.UTF_8).build();

    public static void parse(Reader reader, Object obj) {
        parse(reader, obj, true);
    }

    public static void parse(Reader reader, Object obj, boolean z) {
        int read;
        Class<?> cls = obj.getClass();
        ClassInfo of = ClassInfo.of(cls);
        List asList = Arrays.asList(cls);
        GenericData genericData = GenericData.class.isAssignableFrom(cls) ? (GenericData) obj : null;
        Map map = Map.class.isAssignableFrom(cls) ? (Map) obj : null;
        ArrayValueMap arrayValueMap = new ArrayValueMap(obj);
        StringWriter stringWriter = new StringWriter();
        StringWriter stringWriter2 = new StringWriter();
        do {
            boolean z2 = true;
            while (true) {
                read = reader.read();
                if (read == -1 || read == 38) {
                    break;
                } else if (read != 61) {
                    if (z2) {
                        stringWriter.write(read);
                    } else {
                        stringWriter2.write(read);
                    }
                } else if (z2) {
                    z2 = false;
                } else {
                    stringWriter2.write(read);
                }
            }
            String stringWriter3 = stringWriter.toString();
            if (z) {
                stringWriter3 = CharEscapers.decodeUri(stringWriter3);
            }
            if (stringWriter3.length() != 0) {
                String stringWriter4 = stringWriter2.toString();
                if (z) {
                    stringWriter4 = CharEscapers.decodeUri(stringWriter4);
                }
                FieldInfo fieldInfo = of.getFieldInfo(stringWriter3);
                if (fieldInfo != null) {
                    Type resolveWildcardTypeOrTypeVariable = Data.resolveWildcardTypeOrTypeVariable(asList, fieldInfo.getGenericType());
                    if (Types.isArray(resolveWildcardTypeOrTypeVariable)) {
                        Class<?> rawArrayComponentType = Types.getRawArrayComponentType(asList, Types.getArrayComponentType(resolveWildcardTypeOrTypeVariable));
                        arrayValueMap.put(fieldInfo.getField(), rawArrayComponentType, parseValue(rawArrayComponentType, asList, stringWriter4));
                    } else if (Types.isAssignableToOrFrom(Types.getRawArrayComponentType(asList, resolveWildcardTypeOrTypeVariable), Iterable.class)) {
                        Collection<Object> collection = (Collection) fieldInfo.getValue(obj);
                        if (collection == null) {
                            collection = Data.newCollectionInstance(resolveWildcardTypeOrTypeVariable);
                            fieldInfo.setValue(obj, collection);
                        }
                        collection.add(parseValue(resolveWildcardTypeOrTypeVariable == Object.class ? null : Types.getIterableParameter(resolveWildcardTypeOrTypeVariable), asList, stringWriter4));
                    } else {
                        fieldInfo.setValue(obj, parseValue(resolveWildcardTypeOrTypeVariable, asList, stringWriter4));
                    }
                } else if (map != null) {
                    ArrayList arrayList = (ArrayList) map.get(stringWriter3);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        if (genericData != null) {
                            genericData.set(stringWriter3, arrayList);
                        } else {
                            map.put(stringWriter3, arrayList);
                        }
                    }
                    arrayList.add(stringWriter4);
                }
            }
            stringWriter = new StringWriter();
            stringWriter2 = new StringWriter();
        } while (read != -1);
        arrayValueMap.setValues();
    }

    public static void parse(String str, Object obj) {
        parse(str, obj, true);
    }

    public static void parse(String str, Object obj, boolean z) {
        if (str == null) {
            return;
        }
        try {
            parse(new StringReader(str), obj, z);
        } catch (IOException e2) {
            throw Throwables.propagate(e2);
        }
    }

    private static Object parseValue(Type type, List<Type> list, String str) {
        return Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(list, type), str);
    }

    @Override // com.google.api.client.util.ObjectParser
    public <T> T parseAndClose(InputStream inputStream, Charset charset, Class<T> cls) {
        return (T) parseAndClose((Reader) new InputStreamReader(inputStream, charset), (Class<Object>) cls);
    }

    @Override // com.google.api.client.util.ObjectParser
    public Object parseAndClose(InputStream inputStream, Charset charset, Type type) {
        return parseAndClose(new InputStreamReader(inputStream, charset), type);
    }

    @Override // com.google.api.client.util.ObjectParser
    public <T> T parseAndClose(Reader reader, Class<T> cls) {
        return (T) parseAndClose(reader, (Type) cls);
    }

    @Override // com.google.api.client.util.ObjectParser
    public Object parseAndClose(Reader reader, Type type) {
        Preconditions.checkArgument(type instanceof Class, "dataType has to be of type Class<?>");
        Object newInstance = Types.newInstance((Class) type);
        parse(new BufferedReader(reader), newInstance);
        return newInstance;
    }
}
