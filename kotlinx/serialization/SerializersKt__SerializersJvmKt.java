package kotlinx.serialization;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.PlatformKt;
import kotlinx.serialization.internal.Platform_commonKt;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final /* synthetic */ class SerializersKt__SerializersJvmKt {
    static /* synthetic */ KSerializer a(SerializersModule serializersModule, Type type, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = true;
        }
        return serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(serializersModule, type, z);
    }

    private static final KSerializer<Object> genericArraySerializer$SerializersKt__SerializersJvmKt(SerializersModule serializersModule, GenericArrayType genericArrayType, boolean z) {
        KSerializer<Object> serializerOrNull;
        KClass kClass;
        Object first;
        Type eType = genericArrayType.getGenericComponentType();
        if (eType instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) eType).getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "it.upperBounds");
            first = ArraysKt___ArraysKt.first(upperBounds);
            eType = (Type) first;
        }
        Intrinsics.checkNotNullExpressionValue(eType, "eType");
        if (z) {
            serializerOrNull = SerializersKt.serializer(serializersModule, eType);
        } else {
            serializerOrNull = SerializersKt.serializerOrNull(serializersModule, eType);
            if (serializerOrNull == null) {
                return null;
            }
        }
        if (eType instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) eType).getRawType();
            Objects.requireNonNull(rawType, "null cannot be cast to non-null type java.lang.Class<*>");
            kClass = JvmClassMappingKt.getKotlinClass((Class) rawType);
        } else if (!(eType instanceof KClass)) {
            throw new IllegalStateException(Intrinsics.stringPlus("unsupported type in GenericArray: ", Reflection.getOrCreateKotlinClass(eType.getClass())));
        } else {
            kClass = (KClass) eType;
        }
        return BuiltinSerializersKt.ArraySerializer(kClass, serializerOrNull);
    }

    private static final KClass<?> kclass$SerializersKt__SerializersJvmKt(Type type) {
        Type genericComponentType;
        String str;
        Object first;
        if (type instanceof KClass) {
            return (KClass) type;
        }
        if (type instanceof Class) {
            return JvmClassMappingKt.getKotlinClass((Class) type);
        }
        if (type instanceof ParameterizedType) {
            genericComponentType = ((ParameterizedType) type).getRawType();
            str = "it.rawType";
        } else if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "it.upperBounds");
            first = ArraysKt___ArraysKt.first(upperBounds);
            Intrinsics.checkNotNullExpressionValue(first, "it.upperBounds.first()");
            genericComponentType = (Type) first;
            return kclass$SerializersKt__SerializersJvmKt(genericComponentType);
        } else if (!(type instanceof GenericArrayType)) {
            throw new IllegalArgumentException("typeToken should be an instance of Class<?>, GenericArray, ParametrizedType or WildcardType, but actual type is " + type + TokenParser.SP + Reflection.getOrCreateKotlinClass(type.getClass()));
        } else {
            genericComponentType = ((GenericArrayType) type).getGenericComponentType();
            str = "it.genericComponentType";
        }
        Intrinsics.checkNotNullExpressionValue(genericComponentType, str);
        return kclass$SerializersKt__SerializersJvmKt(genericComponentType);
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final KSerializer<Object> serializer(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return SerializersKt.serializer(SerializersModuleKt.getEmptySerializersModule(), type);
    }

    @ExperimentalSerializationApi
    @NotNull
    public static final KSerializer<Object> serializer(@NotNull SerializersModule serializersModule, @NotNull Type type) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        KSerializer<Object> serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt = serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(serializersModule, type, true);
        if (serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt != null) {
            return serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt;
        }
        Platform_commonKt.serializerNotRegistered(kclass$SerializersKt__SerializersJvmKt(type));
        throw new KotlinNothingValueException();
    }

    private static final KSerializer<Object> serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(SerializersModule serializersModule, Type type, boolean z) {
        Object first;
        ArrayList<KSerializer> arrayList;
        int collectionSizeOrDefault;
        if (type instanceof GenericArrayType) {
            return genericArraySerializer$SerializersKt__SerializersJvmKt(serializersModule, (GenericArrayType) type, z);
        }
        if (type instanceof Class) {
            return typeSerializer$SerializersKt__SerializersJvmKt(serializersModule, (Class) type, z);
        }
        if (!(type instanceof ParameterizedType)) {
            if (type instanceof WildcardType) {
                Type[] upperBounds = ((WildcardType) type).getUpperBounds();
                Intrinsics.checkNotNullExpressionValue(upperBounds, "type.upperBounds");
                first = ArraysKt___ArraysKt.first(upperBounds);
                Intrinsics.checkNotNullExpressionValue(first, "type.upperBounds.first()");
                return a(serializersModule, (Type) first, false, 2, null);
            }
            throw new IllegalArgumentException("typeToken should be an instance of Class<?>, GenericArray, ParametrizedType or WildcardType, but actual type is " + type + TokenParser.SP + Reflection.getOrCreateKotlinClass(type.getClass()));
        }
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type rawType = parameterizedType.getRawType();
        Objects.requireNonNull(rawType, "null cannot be cast to non-null type java.lang.Class<*>");
        Class cls = (Class) rawType;
        Type[] args = parameterizedType.getActualTypeArguments();
        Intrinsics.checkNotNullExpressionValue(args, "args");
        if (z) {
            arrayList = new ArrayList(args.length);
            int length = args.length;
            int i2 = 0;
            while (i2 < length) {
                Type it = args[i2];
                i2++;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                arrayList.add(SerializersKt.serializer(serializersModule, it));
            }
        } else {
            arrayList = new ArrayList(args.length);
            int length2 = args.length;
            int i3 = 0;
            while (i3 < length2) {
                Type it2 = args[i3];
                i3++;
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                KSerializer<Object> serializerOrNull = SerializersKt.serializerOrNull(serializersModule, it2);
                if (serializerOrNull == null) {
                    return null;
                }
                arrayList.add(serializerOrNull);
            }
        }
        if (Set.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.SetSerializer((KSerializer) arrayList.get(0));
        }
        if (List.class.isAssignableFrom(cls) || Collection.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.ListSerializer((KSerializer) arrayList.get(0));
        }
        if (Map.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.MapSerializer((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1));
        }
        if (Map.Entry.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.MapEntrySerializer((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1));
        }
        if (Pair.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.PairSerializer((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1));
        }
        if (Triple.class.isAssignableFrom(cls)) {
            return BuiltinSerializersKt.TripleSerializer((KSerializer) arrayList.get(0), (KSerializer) arrayList.get(1), (KSerializer) arrayList.get(2));
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (KSerializer kSerializer : arrayList) {
            arrayList2.add(kSerializer);
        }
        KClass kotlinClass = JvmClassMappingKt.getKotlinClass(cls);
        Object[] array = arrayList2.toArray(new KSerializer[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        KSerializer[] kSerializerArr = (KSerializer[]) array;
        KSerializer<Object> constructSerializerForGivenTypeArgs = PlatformKt.constructSerializerForGivenTypeArgs(kotlinClass, (KSerializer[]) Arrays.copyOf(kSerializerArr, kSerializerArr.length));
        if (!(constructSerializerForGivenTypeArgs instanceof KSerializer)) {
            constructSerializerForGivenTypeArgs = null;
        }
        return constructSerializerForGivenTypeArgs == null ? SerializersKt.reflectiveOrContextual(serializersModule, JvmClassMappingKt.getKotlinClass(cls), arrayList2) : constructSerializerForGivenTypeArgs;
    }

    @ExperimentalSerializationApi
    @Nullable
    public static final KSerializer<Object> serializerOrNull(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return SerializersKt.serializerOrNull(SerializersModuleKt.getEmptySerializersModule(), type);
    }

    @ExperimentalSerializationApi
    @Nullable
    public static final KSerializer<Object> serializerOrNull(@NotNull SerializersModule serializersModule, @NotNull Type type) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        return serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(serializersModule, type, false);
    }

    private static final KSerializer<Object> typeSerializer$SerializersKt__SerializersJvmKt(SerializersModule serializersModule, Class<?> cls, boolean z) {
        List emptyList;
        KSerializer<Object> serializerOrNull;
        if (!cls.isArray() || cls.getComponentType().isPrimitive()) {
            KClass kotlinClass = JvmClassMappingKt.getKotlinClass(cls);
            emptyList = CollectionsKt__CollectionsKt.emptyList();
            return SerializersKt.reflectiveOrContextual(serializersModule, kotlinClass, emptyList);
        }
        Class<?> componentType = cls.getComponentType();
        Intrinsics.checkNotNullExpressionValue(componentType, "type.componentType");
        if (z) {
            serializerOrNull = SerializersKt.serializer(serializersModule, componentType);
        } else {
            serializerOrNull = SerializersKt.serializerOrNull(serializersModule, componentType);
            if (serializerOrNull == null) {
                return null;
            }
        }
        return BuiltinSerializersKt.ArraySerializer(JvmClassMappingKt.getKotlinClass(componentType), serializerOrNull);
    }
}
