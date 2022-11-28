package com.google.api.client.util;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
/* loaded from: classes2.dex */
public class Types {
    private Types() {
    }

    private static Type getActualParameterAtPosition(Type type, Class<?> cls, int i2) {
        Type resolveTypeVariable;
        ParameterizedType superParameterizedType = getSuperParameterizedType(type, cls);
        if (superParameterizedType == null) {
            return null;
        }
        Type type2 = superParameterizedType.getActualTypeArguments()[i2];
        return (!(type2 instanceof TypeVariable) || (resolveTypeVariable = resolveTypeVariable(Arrays.asList(type), (TypeVariable) type2)) == null) ? type2 : resolveTypeVariable;
    }

    public static Type getArrayComponentType(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    public static Type getBound(WildcardType wildcardType) {
        Type[] lowerBounds = wildcardType.getLowerBounds();
        return lowerBounds.length != 0 ? lowerBounds[0] : wildcardType.getUpperBounds()[0];
    }

    public static Type getIterableParameter(Type type) {
        return getActualParameterAtPosition(type, Iterable.class, 0);
    }

    public static Type getMapValueParameter(Type type) {
        return getActualParameterAtPosition(type, Map.class, 1);
    }

    public static Class<?> getRawArrayComponentType(List<Type> list, Type type) {
        if (type instanceof TypeVariable) {
            type = resolveTypeVariable(list, (TypeVariable) type);
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawArrayComponentType(list, getArrayComponentType(type)), 0).getClass();
        }
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getRawClass((ParameterizedType) type);
        }
        Preconditions.checkArgument(type == null, "wildcard type is not supported: %s", type);
        return Object.class;
    }

    public static Class<?> getRawClass(ParameterizedType parameterizedType) {
        return (Class) parameterizedType.getRawType();
    }

    public static ParameterizedType getSuperParameterizedType(Type type, Class<?> cls) {
        Class<?> cls2;
        Type[] genericInterfaces;
        if ((type instanceof Class) || (type instanceof ParameterizedType)) {
            while (type != null && type != Object.class) {
                if (type instanceof Class) {
                    cls2 = (Class) type;
                } else {
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Class<?> rawClass = getRawClass(parameterizedType);
                    if (rawClass == cls) {
                        return parameterizedType;
                    }
                    if (cls.isInterface()) {
                        for (Type type2 : rawClass.getGenericInterfaces()) {
                            if (cls.isAssignableFrom(type2 instanceof Class ? (Class) type2 : getRawClass((ParameterizedType) type2))) {
                                type = type2;
                                break;
                            }
                        }
                    }
                    cls2 = rawClass;
                }
                type = cls2.getGenericSuperclass();
            }
            return null;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static IllegalArgumentException handleExceptionForNewInstance(Exception exc, Class<?> cls) {
        String str;
        String str2;
        Iterator it;
        StringBuilder sb = new StringBuilder("unable to create new instance of class ");
        sb.append(cls.getName());
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        if (cls.isArray()) {
            str = "because it is an array";
        } else if (cls.isPrimitive()) {
            str = "because it is primitive";
        } else if (cls == Void.class) {
            str = "because it is void";
        } else {
            if (Modifier.isInterface(cls.getModifiers())) {
                str2 = "because it is an interface";
            } else {
                str2 = Modifier.isAbstract(cls.getModifiers()) ? "because it is abstract" : "because it is abstract";
                if (cls.getEnclosingClass() != null && !Modifier.isStatic(cls.getModifiers())) {
                    arrayList.add("because it is not static");
                }
                if (!Modifier.isPublic(cls.getModifiers())) {
                    try {
                        cls.getConstructor(new Class[0]);
                    } catch (NoSuchMethodException unused) {
                        str = "because it has no accessible default constructor";
                    }
                    it = arrayList.iterator();
                    while (it.hasNext()) {
                        String str3 = (String) it.next();
                        if (z) {
                            sb.append(" and");
                        } else {
                            z = true;
                        }
                        sb.append(" ");
                        sb.append(str3);
                    }
                    return new IllegalArgumentException(sb.toString(), exc);
                }
                str = "possibly because it is not public";
            }
            arrayList.add(str2);
            if (cls.getEnclosingClass() != null) {
                arrayList.add("because it is not static");
            }
            if (!Modifier.isPublic(cls.getModifiers())) {
            }
        }
        arrayList.add(str);
        it = arrayList.iterator();
        while (it.hasNext()) {
        }
        return new IllegalArgumentException(sb.toString(), exc);
    }

    public static boolean isArray(Type type) {
        return (type instanceof GenericArrayType) || ((type instanceof Class) && ((Class) type).isArray());
    }

    public static boolean isAssignableToOrFrom(Class<?> cls, Class<?> cls2) {
        return cls.isAssignableFrom(cls2) || cls2.isAssignableFrom(cls);
    }

    public static <T> Iterable<T> iterableOf(final Object obj) {
        if (obj instanceof Iterable) {
            return (Iterable) obj;
        }
        Class<?> cls = obj.getClass();
        Preconditions.checkArgument(cls.isArray(), "not an array or Iterable: %s", cls);
        return !cls.getComponentType().isPrimitive() ? Arrays.asList((Object[]) obj) : new Iterable<T>() { // from class: com.google.api.client.util.Types.1
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return new Iterator<T>() { // from class: com.google.api.client.util.Types.1.1

                    /* renamed from: a  reason: collision with root package name */
                    final int f8097a;

                    /* renamed from: b  reason: collision with root package name */
                    int f8098b = 0;

                    {
                        this.f8097a = Array.getLength(obj);
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.f8098b < this.f8097a;
                    }

                    /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
                    @Override // java.util.Iterator
                    public T next() {
                        if (hasNext()) {
                            Object obj2 = obj;
                            int i2 = this.f8098b;
                            this.f8098b = i2 + 1;
                            return Array.get(obj2, i2);
                        }
                        throw new NoSuchElementException();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public static <T> T newInstance(Class<T> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException e2) {
            throw handleExceptionForNewInstance(e2, cls);
        } catch (InstantiationException e3) {
            throw handleExceptionForNewInstance(e3, cls);
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.reflect.GenericDeclaration] */
    public static Type resolveTypeVariable(List<Type> list, TypeVariable<?> typeVariable) {
        Type resolveTypeVariable;
        ?? genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            Class cls = (Class) genericDeclaration;
            int size = list.size();
            ParameterizedType parameterizedType = null;
            while (parameterizedType == null) {
                size--;
                if (size < 0) {
                    break;
                }
                parameterizedType = getSuperParameterizedType(list.get(size), cls);
            }
            if (parameterizedType != null) {
                TypeVariable<?>[] typeParameters = genericDeclaration.getTypeParameters();
                int i2 = 0;
                while (i2 < typeParameters.length && !typeParameters[i2].equals(typeVariable)) {
                    i2++;
                }
                Type type = parameterizedType.getActualTypeArguments()[i2];
                return (!(type instanceof TypeVariable) || (resolveTypeVariable = resolveTypeVariable(list, (TypeVariable) type)) == null) ? type : resolveTypeVariable;
            }
        }
        return null;
    }

    public static Object toArray(Collection<?> collection, Class<?> cls) {
        if (cls.isPrimitive()) {
            Object newInstance = Array.newInstance(cls, collection.size());
            int i2 = 0;
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                Array.set(newInstance, i2, it.next());
                i2++;
            }
            return newInstance;
        }
        return collection.toArray((Object[]) Array.newInstance(cls, collection.size()));
    }
}
