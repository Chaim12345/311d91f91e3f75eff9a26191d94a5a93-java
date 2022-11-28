package kotlinx.serialization.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import kotlin.KotlinNothingValueException;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Polymorphic;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.Serializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class PlatformKt {
    private static final Object companionOrNull(Class<?> cls) {
        try {
            Field declaredField = cls.getDeclaredField("Companion");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Throwable unused) {
            return null;
        }
    }

    @Nullable
    public static final <T> KSerializer<T> compiledSerializerImpl(@NotNull KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return constructSerializerForGivenTypeArgs(kClass, new KSerializer[0]);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0064, code lost:
        if (r3 == false) goto L30;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x007e A[Catch: NoSuchFieldException -> 0x0081, TRY_LEAVE, TryCatch #0 {NoSuchFieldException -> 0x0081, blocks: (B:18:0x003f, B:20:0x004e, B:27:0x0067, B:34:0x007a, B:36:0x007e, B:30:0x006d, B:33:0x0076), top: B:44:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0083 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0084  */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final <T> KSerializer<T> constructSerializerForGivenTypeArgs(@NotNull KClass<T> kClass, @NotNull KSerializer<Object>... args) {
        Class<?> cls;
        Field field;
        Object obj;
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(args, "args");
        Class javaClass = JvmClassMappingKt.getJavaClass((KClass) kClass);
        if (javaClass.isEnum() && isNotAnnotated(javaClass)) {
            return createEnumSerializer(javaClass);
        }
        if (javaClass.isInterface()) {
            return interfaceSerializer(kClass);
        }
        KSerializer<T> invokeSerializerOnCompanion = invokeSerializerOnCompanion(javaClass, (KSerializer[]) Arrays.copyOf(args, args.length));
        if (invokeSerializerOnCompanion != null) {
            return invokeSerializerOnCompanion;
        }
        KSerializer<T> findObjectSerializer = findObjectSerializer(javaClass);
        if (findObjectSerializer != null) {
            return findObjectSerializer;
        }
        KSerializer<T> kSerializer = null;
        try {
            Class<?>[] declaredClasses = javaClass.getDeclaredClasses();
            Intrinsics.checkNotNullExpressionValue(declaredClasses, "jClass.declaredClasses");
            int length = declaredClasses.length;
            int i2 = 0;
            Class<?> cls2 = null;
            boolean z = false;
            while (true) {
                if (i2 < length) {
                    Class<?> cls3 = declaredClasses[i2];
                    i2++;
                    if (Intrinsics.areEqual(cls3.getSimpleName(), "$serializer")) {
                        if (z) {
                            break;
                        }
                        z = true;
                        cls2 = cls3;
                    }
                }
            }
            cls2 = null;
            cls = cls2;
        } catch (NoSuchFieldException unused) {
        }
        if (cls != null && (field = cls.getField("INSTANCE")) != null) {
            obj = field.get(null);
            if (obj instanceof KSerializer) {
                kSerializer = (KSerializer) obj;
            }
            return kSerializer == null ? kSerializer : polymorphicSerializer(kClass);
        }
        obj = null;
        if (obj instanceof KSerializer) {
        }
        if (kSerializer == null) {
        }
    }

    private static final <T> KSerializer<T> createEnumSerializer(Class<T> cls) {
        T[] enumConstants = cls.getEnumConstants();
        String canonicalName = cls.getCanonicalName();
        Intrinsics.checkNotNullExpressionValue(canonicalName, "canonicalName");
        Objects.requireNonNull(enumConstants, "null cannot be cast to non-null type kotlin.Array<out kotlin.Enum<*>>");
        return new EnumSerializer(canonicalName, (Enum[]) enumConstants);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0041, code lost:
        if (r6 == false) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0094, code lost:
        if (r6 == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0096, code lost:
        r7 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x008e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x005a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final <T> KSerializer<T> findObjectSerializer(Class<T> cls) {
        boolean z;
        Field[] declaredFields = cls.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(declaredFields, "jClass.declaredFields");
        int length = declaredFields.length;
        int i2 = 0;
        boolean z2 = false;
        Field field = null;
        while (true) {
            if (i2 < length) {
                Field field2 = declaredFields[i2];
                i2++;
                if (Intrinsics.areEqual(field2.getName(), "INSTANCE") && Intrinsics.areEqual(field2.getType(), cls) && Modifier.isStatic(field2.getModifiers())) {
                    if (z2) {
                        break;
                    }
                    z2 = true;
                    field = field2;
                }
            }
        }
        field = null;
        Field field3 = field;
        if (field3 == null) {
            return null;
        }
        Object obj = field3.get(null);
        Method[] methods = cls.getMethods();
        Intrinsics.checkNotNullExpressionValue(methods, "jClass.methods");
        int length2 = methods.length;
        int i3 = 0;
        boolean z3 = false;
        Method method = null;
        while (true) {
            if (i3 < length2) {
                Method method2 = methods[i3];
                i3++;
                if (Intrinsics.areEqual(method2.getName(), "serializer")) {
                    Class<?>[] parameterTypes = method2.getParameterTypes();
                    Intrinsics.checkNotNullExpressionValue(parameterTypes, "it.parameterTypes");
                    if ((parameterTypes.length == 0) && Intrinsics.areEqual(method2.getReturnType(), KSerializer.class)) {
                        z = true;
                        if (z) {
                            if (z3) {
                                break;
                            }
                            z3 = true;
                            method = method2;
                        }
                    }
                }
                z = false;
                if (z) {
                }
            }
        }
        Method method3 = method;
        if (method3 == null) {
            return null;
        }
        Object invoke = method3.invoke(obj, new Object[0]);
        if (invoke instanceof KSerializer) {
            return (KSerializer) invoke;
        }
        return null;
    }

    public static final <T> T getChecked(@NotNull T[] tArr, int i2) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[i2];
    }

    public static final boolean getChecked(@NotNull boolean[] zArr, int i2) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[i2];
    }

    private static final <T> KSerializer<T> interfaceSerializer(KClass<T> kClass) {
        Serializable serializable = (Serializable) JvmClassMappingKt.getJavaClass((KClass) kClass).getAnnotation(Serializable.class);
        if (serializable == null || Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(serializable.with()), Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class))) {
            return new PolymorphicSerializer(kClass);
        }
        return null;
    }

    private static final <T> KSerializer<T> invokeSerializerOnCompanion(Class<?> cls, KSerializer<Object>... kSerializerArr) {
        Class[] clsArr;
        Object companionOrNull = companionOrNull(cls);
        if (companionOrNull == null) {
            return null;
        }
        try {
            if (kSerializerArr.length == 0) {
                clsArr = new Class[0];
            } else {
                int length = kSerializerArr.length;
                Class[] clsArr2 = new Class[length];
                for (int i2 = 0; i2 < length; i2++) {
                    clsArr2[i2] = KSerializer.class;
                }
                clsArr = clsArr2;
            }
            Object invoke = companionOrNull.getClass().getDeclaredMethod("serializer", (Class[]) Arrays.copyOf(clsArr, clsArr.length)).invoke(companionOrNull, Arrays.copyOf(kSerializerArr, kSerializerArr.length));
            if (invoke instanceof KSerializer) {
                return (KSerializer) invoke;
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause != null) {
                String message = cause.getMessage();
                if (message == null) {
                    message = e2.getMessage();
                }
                throw new InvocationTargetException(cause, message);
            }
            throw e2;
        }
    }

    public static final boolean isInstanceOf(@NotNull Object obj, @NotNull KClass<?> kclass) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        Intrinsics.checkNotNullParameter(kclass, "kclass");
        return JvmClassMappingKt.getJavaObjectType(kclass).isInstance(obj);
    }

    private static final <T> boolean isNotAnnotated(Class<T> cls) {
        return cls.getAnnotation(Serializable.class) == null && cls.getAnnotation(Polymorphic.class) == null;
    }

    public static final boolean isReferenceArray(@NotNull KClass<Object> rootClass) {
        Intrinsics.checkNotNullParameter(rootClass, "rootClass");
        return JvmClassMappingKt.getJavaClass((KClass) rootClass).isArray();
    }

    @NotNull
    public static final Void platformSpecificSerializerNotRegistered(@NotNull KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Platform_commonKt.serializerNotRegistered(kClass);
        throw new KotlinNothingValueException();
    }

    private static final <T> KSerializer<T> polymorphicSerializer(KClass<T> kClass) {
        Class javaClass = JvmClassMappingKt.getJavaClass((KClass) kClass);
        if (javaClass.getAnnotation(Polymorphic.class) != null) {
            return new PolymorphicSerializer(kClass);
        }
        Serializable serializable = (Serializable) javaClass.getAnnotation(Serializable.class);
        if (serializable == null || !Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(serializable.with()), Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class))) {
            return null;
        }
        return new PolymorphicSerializer(kClass);
    }

    @NotNull
    public static final <T, E extends T> E[] toNativeArrayImpl(@NotNull ArrayList<E> arrayList, @NotNull KClass<T> eClass) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        Intrinsics.checkNotNullParameter(eClass, "eClass");
        Object newInstance = Array.newInstance(JvmClassMappingKt.getJavaClass((KClass) eClass), arrayList.size());
        Objects.requireNonNull(newInstance, "null cannot be cast to non-null type kotlin.Array<E of kotlinx.serialization.internal.PlatformKt.toNativeArrayImpl>");
        E[] eArr = (E[]) arrayList.toArray((Object[]) newInstance);
        Intrinsics.checkNotNullExpressionValue(eArr, "toArray(java.lang.reflec….java, size) as Array<E>)");
        return eArr;
    }
}
