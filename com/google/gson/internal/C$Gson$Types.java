package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import okhttp3.HttpUrl;
/* renamed from: com.google.gson.internal.$Gson$Types  reason: invalid class name */
/* loaded from: classes2.dex */
public final class C$Gson$Types {

    /* renamed from: a  reason: collision with root package name */
    static final Type[] f10149a = new Type[0];

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.gson.internal.$Gson$Types$GenericArrayTypeImpl */
    /* loaded from: classes2.dex */
    public static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        public GenericArrayTypeImpl(Type type) {
            this.componentType = C$Gson$Types.canonicalize(type);
        }

        public boolean equals(Object obj) {
            return (obj instanceof GenericArrayType) && C$Gson$Types.equals(this, (GenericArrayType) obj);
        }

        @Override // java.lang.reflect.GenericArrayType
        public Type getGenericComponentType() {
            return this.componentType;
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public String toString() {
            return C$Gson$Types.typeToString(this.componentType) + HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.gson.internal.$Gson$Types$ParameterizedTypeImpl */
    /* loaded from: classes2.dex */
    public static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type ownerType;
        private final Type rawType;
        private final Type[] typeArguments;

        public ParameterizedTypeImpl(Type type, Type type2, Type... typeArr) {
            if (type2 instanceof Class) {
                Class cls = (Class) type2;
                boolean z = true;
                boolean z2 = Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null;
                if (type == null && !z2) {
                    z = false;
                }
                C$Gson$Preconditions.checkArgument(z);
            }
            this.ownerType = type == null ? null : C$Gson$Types.canonicalize(type);
            this.rawType = C$Gson$Types.canonicalize(type2);
            Type[] typeArr2 = (Type[]) typeArr.clone();
            this.typeArguments = typeArr2;
            int length = typeArr2.length;
            for (int i2 = 0; i2 < length; i2++) {
                C$Gson$Preconditions.checkNotNull(this.typeArguments[i2]);
                C$Gson$Types.a(this.typeArguments[i2]);
                Type[] typeArr3 = this.typeArguments;
                typeArr3[i2] = C$Gson$Types.canonicalize(typeArr3[i2]);
            }
        }

        public boolean equals(Object obj) {
            return (obj instanceof ParameterizedType) && C$Gson$Types.equals(this, (ParameterizedType) obj);
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type[] getActualTypeArguments() {
            return (Type[]) this.typeArguments.clone();
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getOwnerType() {
            return this.ownerType;
        }

        @Override // java.lang.reflect.ParameterizedType
        public Type getRawType() {
            return this.rawType;
        }

        public int hashCode() {
            return (Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode()) ^ C$Gson$Types.e(this.ownerType);
        }

        public String toString() {
            int length = this.typeArguments.length;
            if (length == 0) {
                return C$Gson$Types.typeToString(this.rawType);
            }
            StringBuilder sb = new StringBuilder((length + 1) * 30);
            sb.append(C$Gson$Types.typeToString(this.rawType));
            sb.append("<");
            sb.append(C$Gson$Types.typeToString(this.typeArguments[0]));
            for (int i2 = 1; i2 < length; i2++) {
                sb.append(", ");
                sb.append(C$Gson$Types.typeToString(this.typeArguments[i2]));
            }
            sb.append(">");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.google.gson.internal.$Gson$Types$WildcardTypeImpl */
    /* loaded from: classes2.dex */
    public static final class WildcardTypeImpl implements WildcardType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type lowerBound;
        private final Type upperBound;

        public WildcardTypeImpl(Type[] typeArr, Type[] typeArr2) {
            C$Gson$Preconditions.checkArgument(typeArr2.length <= 1);
            C$Gson$Preconditions.checkArgument(typeArr.length == 1);
            if (typeArr2.length != 1) {
                C$Gson$Preconditions.checkNotNull(typeArr[0]);
                C$Gson$Types.a(typeArr[0]);
                this.lowerBound = null;
                this.upperBound = C$Gson$Types.canonicalize(typeArr[0]);
                return;
            }
            C$Gson$Preconditions.checkNotNull(typeArr2[0]);
            C$Gson$Types.a(typeArr2[0]);
            C$Gson$Preconditions.checkArgument(typeArr[0] == Object.class);
            this.lowerBound = C$Gson$Types.canonicalize(typeArr2[0]);
            this.upperBound = Object.class;
        }

        public boolean equals(Object obj) {
            return (obj instanceof WildcardType) && C$Gson$Types.equals(this, (WildcardType) obj);
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getLowerBounds() {
            Type type = this.lowerBound;
            return type != null ? new Type[]{type} : C$Gson$Types.f10149a;
        }

        @Override // java.lang.reflect.WildcardType
        public Type[] getUpperBounds() {
            return new Type[]{this.upperBound};
        }

        public int hashCode() {
            Type type = this.lowerBound;
            return (type != null ? type.hashCode() + 31 : 1) ^ (this.upperBound.hashCode() + 31);
        }

        public String toString() {
            StringBuilder sb;
            Type type;
            if (this.lowerBound != null) {
                sb = new StringBuilder();
                sb.append("? super ");
                type = this.lowerBound;
            } else if (this.upperBound == Object.class) {
                return "?";
            } else {
                sb = new StringBuilder();
                sb.append("? extends ");
                type = this.upperBound;
            }
            sb.append(C$Gson$Types.typeToString(type));
            return sb.toString();
        }
    }

    private C$Gson$Types() {
        throw new UnsupportedOperationException();
    }

    static void a(Type type) {
        C$Gson$Preconditions.checkArgument(((type instanceof Class) && ((Class) type).isPrimitive()) ? false : true);
    }

    public static GenericArrayType arrayOf(Type type) {
        return new GenericArrayTypeImpl(type);
    }

    static boolean b(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    static Type c(Type type, Class cls, Class cls2) {
        if (cls2 == cls) {
            return type;
        }
        if (cls2.isInterface()) {
            Class<?>[] interfaces = cls.getInterfaces();
            int length = interfaces.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (interfaces[i2] == cls2) {
                    return cls.getGenericInterfaces()[i2];
                }
                if (cls2.isAssignableFrom(interfaces[i2])) {
                    return c(cls.getGenericInterfaces()[i2], interfaces[i2], cls2);
                }
            }
        }
        if (!cls.isInterface()) {
            while (cls != Object.class) {
                Class<?> superclass = cls.getSuperclass();
                if (superclass == cls2) {
                    return cls.getGenericSuperclass();
                }
                if (cls2.isAssignableFrom(superclass)) {
                    return c(cls.getGenericSuperclass(), superclass, cls2);
                }
                cls = superclass;
            }
        }
        return cls2;
    }

    public static Type canonicalize(Type type) {
        if (type instanceof Class) {
            Class cls = (Class) type;
            return cls.isArray() ? new GenericArrayTypeImpl(canonicalize(cls.getComponentType())) : cls;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            return new ParameterizedTypeImpl(parameterizedType.getOwnerType(), parameterizedType.getRawType(), parameterizedType.getActualTypeArguments());
        } else if (type instanceof GenericArrayType) {
            return new GenericArrayTypeImpl(((GenericArrayType) type).getGenericComponentType());
        } else {
            if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                return new WildcardTypeImpl(wildcardType.getUpperBounds(), wildcardType.getLowerBounds());
            }
            return type;
        }
    }

    static Type d(Type type, Class cls, Class cls2) {
        if (type instanceof WildcardType) {
            type = ((WildcardType) type).getUpperBounds()[0];
        }
        C$Gson$Preconditions.checkArgument(cls2.isAssignableFrom(cls));
        return resolve(type, cls, c(type, cls, cls2));
    }

    private static Class<?> declaringClassOf(TypeVariable<?> typeVariable) {
        Object genericDeclaration = typeVariable.getGenericDeclaration();
        if (genericDeclaration instanceof Class) {
            return (Class) genericDeclaration;
        }
        return null;
    }

    static int e(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }

    public static boolean equals(Type type, Type type2) {
        if (type == type2) {
            return true;
        }
        if (type instanceof Class) {
            return type.equals(type2);
        }
        if (type instanceof ParameterizedType) {
            if (type2 instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                ParameterizedType parameterizedType2 = (ParameterizedType) type2;
                return b(parameterizedType.getOwnerType(), parameterizedType2.getOwnerType()) && parameterizedType.getRawType().equals(parameterizedType2.getRawType()) && Arrays.equals(parameterizedType.getActualTypeArguments(), parameterizedType2.getActualTypeArguments());
            }
            return false;
        } else if (type instanceof GenericArrayType) {
            if (type2 instanceof GenericArrayType) {
                return equals(((GenericArrayType) type).getGenericComponentType(), ((GenericArrayType) type2).getGenericComponentType());
            }
            return false;
        } else if (type instanceof WildcardType) {
            if (type2 instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                WildcardType wildcardType2 = (WildcardType) type2;
                return Arrays.equals(wildcardType.getUpperBounds(), wildcardType2.getUpperBounds()) && Arrays.equals(wildcardType.getLowerBounds(), wildcardType2.getLowerBounds());
            }
            return false;
        } else if ((type instanceof TypeVariable) && (type2 instanceof TypeVariable)) {
            TypeVariable typeVariable = (TypeVariable) type;
            TypeVariable typeVariable2 = (TypeVariable) type2;
            return typeVariable.getGenericDeclaration() == typeVariable2.getGenericDeclaration() && typeVariable.getName().equals(typeVariable2.getName());
        } else {
            return false;
        }
    }

    static Type f(Type type, Class cls, TypeVariable typeVariable) {
        Class<?> declaringClassOf = declaringClassOf(typeVariable);
        if (declaringClassOf == null) {
            return typeVariable;
        }
        Type c2 = c(type, cls, declaringClassOf);
        if (c2 instanceof ParameterizedType) {
            return ((ParameterizedType) c2).getActualTypeArguments()[indexOf(declaringClassOf.getTypeParameters(), typeVariable)];
        }
        return typeVariable;
    }

    public static Type getArrayComponentType(Type type) {
        return type instanceof GenericArrayType ? ((GenericArrayType) type).getGenericComponentType() : ((Class) type).getComponentType();
    }

    public static Type getCollectionElementType(Type type, Class<?> cls) {
        Type d2 = d(type, cls, Collection.class);
        if (d2 instanceof WildcardType) {
            d2 = ((WildcardType) d2).getUpperBounds()[0];
        }
        return d2 instanceof ParameterizedType ? ((ParameterizedType) d2).getActualTypeArguments()[0] : Object.class;
    }

    public static Type[] getMapKeyAndValueTypes(Type type, Class<?> cls) {
        if (type == Properties.class) {
            return new Type[]{String.class, String.class};
        }
        Type d2 = d(type, cls, Map.class);
        return d2 instanceof ParameterizedType ? ((ParameterizedType) d2).getActualTypeArguments() : new Type[]{Object.class, Object.class};
    }

    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            C$Gson$Preconditions.checkArgument(rawType instanceof Class);
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        } else {
            if (type instanceof TypeVariable) {
                return Object.class;
            }
            if (type instanceof WildcardType) {
                return getRawType(((WildcardType) type).getUpperBounds()[0]);
            }
            String name = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + name);
        }
    }

    private static int indexOf(Object[] objArr, Object obj) {
        int length = objArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (obj.equals(objArr[i2])) {
                return i2;
            }
        }
        throw new NoSuchElementException();
    }

    public static ParameterizedType newParameterizedTypeWithOwner(Type type, Type type2, Type... typeArr) {
        return new ParameterizedTypeImpl(type, type2, typeArr);
    }

    public static Type resolve(Type type, Class<?> cls, Type type2) {
        return resolve(type, cls, type2, new HashMap());
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x005e, code lost:
        if (b(r1, r9) != false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d7, code lost:
        if (r0 == null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00d9, code lost:
        r12.put(r0, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00dc, code lost:
        return r11;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v10, types: [java.lang.reflect.Type, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r11v13, types: [java.lang.reflect.Type] */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v15 */
    /* JADX WARN: Type inference failed for: r11v2, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r11v4, types: [java.lang.reflect.WildcardType] */
    /* JADX WARN: Type inference failed for: r11v5, types: [java.lang.reflect.ParameterizedType] */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.reflect.GenericArrayType] */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.util.Map<java.lang.reflect.TypeVariable<?>, java.lang.reflect.Type>, java.util.Map] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static Type resolve(Type type, Class<?> cls, Type type2, Map<TypeVariable<?>, Type> map) {
        Type resolve;
        Type newParameterizedTypeWithOwner;
        Type resolve2;
        TypeVariable typeVariable = null;
        ?? r11 = type2;
        while (true) {
            if (r11 instanceof TypeVariable) {
                TypeVariable typeVariable2 = r11;
                Type type3 = (Type) map.get(typeVariable2);
                if (type3 != null) {
                    return type3 == Void.TYPE ? r11 : type3;
                }
                map.put(typeVariable2, Void.TYPE);
                if (typeVariable == null) {
                    typeVariable = typeVariable2;
                }
                r11 = f(type, cls, typeVariable2);
                r11 = r11;
                if (r11 == typeVariable2) {
                    break;
                }
            } else {
                if (r11 instanceof Class) {
                    Class cls2 = r11;
                    if (cls2.isArray()) {
                        Class<?> componentType = cls2.getComponentType();
                        resolve2 = resolve(type, cls, componentType, map);
                        if (b(componentType, resolve2)) {
                            r11 = cls2;
                        }
                        newParameterizedTypeWithOwner = arrayOf(resolve2);
                        r11 = newParameterizedTypeWithOwner;
                    }
                }
                if (r11 instanceof GenericArrayType) {
                    r11 = (GenericArrayType) r11;
                    Type genericComponentType = r11.getGenericComponentType();
                    resolve2 = resolve(type, cls, genericComponentType, map);
                } else {
                    if (r11 instanceof ParameterizedType) {
                        r11 = (ParameterizedType) r11;
                        Type ownerType = r11.getOwnerType();
                        Type resolve3 = resolve(type, cls, ownerType, map);
                        boolean z = !b(resolve3, ownerType);
                        Type[] actualTypeArguments = r11.getActualTypeArguments();
                        int length = actualTypeArguments.length;
                        for (int i2 = 0; i2 < length; i2++) {
                            Type resolve4 = resolve(type, cls, actualTypeArguments[i2], map);
                            if (!b(resolve4, actualTypeArguments[i2])) {
                                if (!z) {
                                    actualTypeArguments = (Type[]) actualTypeArguments.clone();
                                    z = true;
                                }
                                actualTypeArguments[i2] = resolve4;
                            }
                        }
                        if (z) {
                            newParameterizedTypeWithOwner = newParameterizedTypeWithOwner(resolve3, r11.getRawType(), actualTypeArguments);
                            r11 = newParameterizedTypeWithOwner;
                        }
                    } else if (r11 instanceof WildcardType) {
                        r11 = (WildcardType) r11;
                        Type[] lowerBounds = r11.getLowerBounds();
                        Type[] upperBounds = r11.getUpperBounds();
                        if (lowerBounds.length == 1) {
                            Type resolve5 = resolve(type, cls, lowerBounds[0], map);
                            if (resolve5 != lowerBounds[0]) {
                                r11 = supertypeOf(resolve5);
                            }
                        } else if (upperBounds.length == 1 && (resolve = resolve(type, cls, upperBounds[0], map)) != upperBounds[0]) {
                            r11 = subtypeOf(resolve);
                        }
                    }
                }
            }
        }
    }

    public static WildcardType subtypeOf(Type type) {
        return new WildcardTypeImpl(type instanceof WildcardType ? ((WildcardType) type).getUpperBounds() : new Type[]{type}, f10149a);
    }

    public static WildcardType supertypeOf(Type type) {
        return new WildcardTypeImpl(new Type[]{Object.class}, type instanceof WildcardType ? ((WildcardType) type).getLowerBounds() : new Type[]{type});
    }

    public static String typeToString(Type type) {
        return type instanceof Class ? ((Class) type).getName() : type.toString();
    }
}
