package androidx.car.app.serialization;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.car.app.utils.LogTags;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.os.EnvironmentCompat;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import kotlinx.serialization.json.internal.TreeJsonEncoderKt;
import org.apache.http.message.TokenParser;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public final class Bundler {
    private static final int CLASS = 8;
    private static final int ENUM = 7;
    private static final int IBINDER = 9;
    @VisibleForTesting
    static final String ICON_COMPAT_ANDROIDX = "androidx.core.graphics.drawable.IconCompat";
    @VisibleForTesting
    static final String ICON_COMPAT_SUPPORT = "android.support.v4.graphics.drawable.IconCompat";
    private static final int IINTERFACE = 1;
    private static final int IMAGE = 6;
    private static final int LIST = 4;
    private static final int MAP = 2;
    private static final int MAX_VALUE_LOG_LENGTH = 32;
    private static final int OBJECT = 5;
    private static final int PRIMITIVE = 0;
    private static final boolean REDACT_LOG_VALUES = true;
    private static final int SET = 3;
    private static final String TAG_1 = "tag_1";
    private static final String TAG_2 = "tag_2";
    private static final String TAG_CLASS_NAME = "tag_class_name";
    private static final String TAG_CLASS_TYPE = "tag_class_type";
    private static final String TAG_VALUE = "tag_value";
    private static final Map<Class<?>, String> UNOBFUSCATED_TYPE_NAMES = initUnobfuscatedTypeNames();
    private static final Map<Integer, String> BUNDLED_TYPE_NAMES = initBundledTypeNames();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class CycleDetectedBundlerException extends TracedBundlerException {
        CycleDetectedBundlerException(String str, Trace trace) {
            super(str, trace);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Frame {
        private final String mDisplay;
        private final Object mObj;

        Frame(Object obj, String str) {
            this.mObj = obj;
            this.mDisplay = str;
        }

        public Object getObj() {
            return this.mObj;
        }

        String toFlatString() {
            return "[" + this.mDisplay + ", " + Bundler.getUnobfuscatedClassName(this.mObj.getClass()) + "]";
        }

        public String toString() {
            return toFlatString();
        }

        String toTraceString() {
            return Bundler.getUnobfuscatedClassName(this.mObj.getClass()) + " " + this.mDisplay;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class Trace implements AutoCloseable {
        private static final int MAX_FLAT_FRAMES = 8;
        private static final int MAX_LOG_INDENT = 12;
        private final ArrayDeque<Frame> mFrames;
        @Nullable
        private String[] mIndents;

        private Trace(@Nullable Object obj, String str, ArrayDeque<Frame> arrayDeque) {
            this.mFrames = arrayDeque;
            if (obj != null) {
                Frame frame = new Frame(obj, str);
                arrayDeque.addFirst(frame);
                if (Log.isLoggable(LogTags.TAG_BUNDLER, 2)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(getIndent(arrayDeque.size()));
                    sb.append(frame.toTraceString());
                }
            }
        }

        static String bundleToString(Bundle bundle) {
            return Bundler.getBundledTypeName(bundle.getInt(Bundler.TAG_CLASS_TYPE));
        }

        static Trace create() {
            return new Trace(null, "", new ArrayDeque());
        }

        static Trace fromParent(@Nullable Object obj, String str, Trace trace) {
            return new Trace(obj, str, trace.mFrames);
        }

        private String getIndent(int i2) {
            int min = Math.min(i2, 11);
            if (this.mIndents == null) {
                this.mIndents = new String[12];
            }
            String str = this.mIndents[min];
            if (str == null) {
                str = repeatChar(TokenParser.SP, min);
                if (min == 11) {
                    str = str + "...";
                }
                this.mIndents[min] = str;
            }
            return str;
        }

        private static String repeatChar(char c2, int i2) {
            char[] cArr = new char[i2];
            Arrays.fill(cArr, c2);
            return new String(cArr);
        }

        @Override // java.lang.AutoCloseable
        public void close() {
            this.mFrames.removeFirst();
        }

        boolean find(Object obj) {
            Iterator<Frame> it = this.mFrames.iterator();
            while (it.hasNext()) {
                if (it.next().getObj() == obj) {
                    return true;
                }
            }
            return false;
        }

        String toFlatString() {
            StringBuilder sb = new StringBuilder();
            int min = Math.min(this.mFrames.size(), 8);
            Iterator<Frame> descendingIterator = this.mFrames.descendingIterator();
            while (descendingIterator.hasNext()) {
                int i2 = min - 1;
                if (min <= 0) {
                    break;
                }
                sb.append(descendingIterator.next().toFlatString());
                min = i2;
            }
            if (descendingIterator.hasNext()) {
                sb.append("[...]");
            }
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class TracedBundlerException extends BundlerException {
        TracedBundlerException(String str, Trace trace) {
            super(str + ", frames: " + trace.toFlatString());
        }

        TracedBundlerException(String str, Trace trace, Throwable th) {
            super(str + ", frames: " + trace.toFlatString(), th);
        }
    }

    private Bundler() {
    }

    private static Object deserializeClass(Bundle bundle, Trace trace) {
        String string = bundle.getString(TAG_VALUE);
        if (string != null) {
            try {
                return Class.forName(string);
            } catch (ClassNotFoundException e2) {
                throw new TracedBundlerException("Class name is unknown: " + string, trace, e2);
            }
        }
        throw new TracedBundlerException("Class is missing the class name", trace);
    }

    private static Object deserializeCollection(Bundle bundle, Collection<Object> collection, Trace trace) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(TAG_VALUE);
        if (parcelableArrayList != null) {
            Iterator it = parcelableArrayList.iterator();
            while (it.hasNext()) {
                collection.add(fromBundle((Bundle) ((Parcelable) it.next()), trace));
            }
            return collection;
        }
        throw new TracedBundlerException("Bundle is missing the collection", trace);
    }

    private static Object deserializeEnum(Bundle bundle, Trace trace) {
        String string = bundle.getString(TAG_VALUE);
        if (string == null) {
            throw new TracedBundlerException("Missing enum name [" + string + "]", trace);
        }
        String string2 = bundle.getString(TAG_CLASS_NAME);
        if (string2 == null) {
            throw new TracedBundlerException("Missing enum className [" + string2 + "]", trace);
        }
        try {
            return getClassOrSuperclassMethod(Class.forName(string2), "valueOf", trace).invoke(null, string);
        } catch (ClassNotFoundException e2) {
            throw new TracedBundlerException("Enum class [" + string2 + "] not found", trace, e2);
        } catch (IllegalArgumentException e3) {
            throw new TracedBundlerException("Enum value [" + string + "] does not exist in enum class [" + string2 + "]", trace, e3);
        } catch (ReflectiveOperationException e4) {
            throw new TracedBundlerException("Enum of class [" + string2 + "] missing valueOf method", trace, e4);
        }
    }

    private static Object deserializeIBinder(Bundle bundle, Trace trace) {
        IBinder binder = bundle.getBinder(TAG_VALUE);
        if (binder != null) {
            return binder;
        }
        throw new TracedBundlerException("Bundle is missing the binder", trace);
    }

    private static Object deserializeIInterface(Bundle bundle, Trace trace) {
        IBinder binder = bundle.getBinder(TAG_VALUE);
        if (binder != null) {
            String string = bundle.getString(TAG_CLASS_NAME);
            if (string != null) {
                try {
                    Object invoke = getClassOrSuperclassMethod(Class.forName(string), "asInterface", trace).invoke(null, binder);
                    if (invoke != null) {
                        return invoke;
                    }
                    throw new TracedBundlerException("Failed to get interface from binder", trace);
                } catch (ClassNotFoundException e2) {
                    throw new TracedBundlerException("Binder for unknown IInterface: " + string, trace, e2);
                } catch (ReflectiveOperationException e3) {
                    throw new TracedBundlerException("Method to create IInterface from a Binder is not accessible for interface: " + string, trace, e3);
                }
            }
            throw new TracedBundlerException("Bundle is missing IInterface class name", trace);
        }
        throw new TracedBundlerException("Bundle is missing the binder", trace);
    }

    private static Object deserializeImage(Bundle bundle, Trace trace) {
        Bundle bundle2 = bundle.getBundle(TAG_VALUE);
        if (bundle2 != null) {
            IconCompat createFromBundle = IconCompat.createFromBundle(bundle2);
            if (createFromBundle != null) {
                return createFromBundle;
            }
            throw new TracedBundlerException("Failed to create IconCompat from bundle", trace);
        }
        throw new TracedBundlerException("IconCompat bundle is null", trace);
    }

    private static Object deserializeList(Bundle bundle, Trace trace) {
        return deserializeCollection(bundle, new ArrayList(), trace);
    }

    private static Object deserializeMap(Bundle bundle, Trace trace) {
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(TAG_VALUE);
        if (parcelableArrayList != null) {
            HashMap hashMap = new HashMap();
            Iterator it = parcelableArrayList.iterator();
            while (it.hasNext()) {
                Bundle bundle2 = (Bundle) ((Parcelable) it.next());
                Bundle bundle3 = bundle2.getBundle(TAG_1);
                Bundle bundle4 = bundle2.getBundle(TAG_2);
                if (bundle3 == null) {
                    throw new TracedBundlerException("Bundle is missing key", trace);
                }
                hashMap.put(fromBundle(bundle3, trace), bundle4 == null ? null : fromBundle(bundle4, trace));
            }
            return hashMap;
        }
        throw new TracedBundlerException("Bundle is missing the map", trace);
    }

    private static Object deserializeObject(Bundle bundle, Trace trace) {
        String string = bundle.getString(TAG_CLASS_NAME);
        if (string != null) {
            try {
                Class<?> cls = Class.forName(string);
                Constructor<?> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
                declaredConstructor.setAccessible(true);
                Object newInstance = declaredConstructor.newInstance(new Object[0]);
                for (Field field : getFields(cls)) {
                    field.setAccessible(true);
                    String fieldName = getFieldName(field);
                    Object obj = bundle.get(fieldName);
                    if (obj == null) {
                        obj = bundle.get(fieldName.replaceAll(ICON_COMPAT_ANDROIDX, ICON_COMPAT_SUPPORT));
                    }
                    if (obj instanceof Bundle) {
                        field.set(newInstance, fromBundle((Bundle) obj, trace));
                    } else if (obj == null && Log.isLoggable(LogTags.TAG_BUNDLER, 3)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Value is null for field: ");
                        sb.append(field);
                    }
                }
                return newInstance;
            } catch (ClassNotFoundException e2) {
                throw new TracedBundlerException("Object for unknown class: " + string, trace, e2);
            } catch (IllegalArgumentException e3) {
                throw new TracedBundlerException("Failed to deserialize class: " + string, trace, e3);
            } catch (NoSuchMethodException e4) {
                throw new TracedBundlerException("Object missing no args constructor: " + string, trace, e4);
            } catch (ReflectiveOperationException e5) {
                throw new TracedBundlerException("Constructor or field is not accessible: " + string, trace, e5);
            }
        }
        throw new TracedBundlerException("Bundle is missing the class name", trace);
    }

    private static Object deserializePrimitive(Bundle bundle, Trace trace) {
        Object obj = bundle.get(TAG_VALUE);
        if (obj != null) {
            return obj;
        }
        throw new TracedBundlerException("Bundle is missing the primitive value", trace);
    }

    private static Object deserializeSet(Bundle bundle, Trace trace) {
        return deserializeCollection(bundle, new HashSet(), trace);
    }

    static String ellipsize(String str) {
        if (str.length() < 32) {
            return str;
        }
        return str.substring(0, 31) + "...";
    }

    @NonNull
    public static Object fromBundle(@NonNull Bundle bundle) {
        if (Log.isLoggable(LogTags.TAG_BUNDLER, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unbundling ");
            sb.append(getBundledTypeName(bundle.getInt(TAG_CLASS_TYPE)));
        }
        return fromBundle(bundle, Trace.create());
    }

    @NonNull
    private static Object fromBundle(@NonNull Bundle bundle, Trace trace) {
        ClassLoader classLoader = Bundler.class.getClassLoader();
        Objects.requireNonNull(classLoader);
        bundle.setClassLoader(classLoader);
        int i2 = bundle.getInt(TAG_CLASS_TYPE);
        Trace fromParent = Trace.fromParent(bundle, Trace.bundleToString(bundle), trace);
        try {
            switch (i2) {
                case 0:
                    Object deserializePrimitive = deserializePrimitive(bundle, fromParent);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return deserializePrimitive;
                case 1:
                    Object deserializeIInterface = deserializeIInterface(bundle, fromParent);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return deserializeIInterface;
                case 2:
                    Object deserializeMap = deserializeMap(bundle, fromParent);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return deserializeMap;
                case 3:
                    Object deserializeSet = deserializeSet(bundle, fromParent);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return deserializeSet;
                case 4:
                    Object deserializeList = deserializeList(bundle, fromParent);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return deserializeList;
                case 5:
                    Object deserializeObject = deserializeObject(bundle, fromParent);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return deserializeObject;
                case 6:
                    Object deserializeImage = deserializeImage(bundle, fromParent);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return deserializeImage;
                case 7:
                    Object deserializeEnum = deserializeEnum(bundle, fromParent);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return deserializeEnum;
                case 8:
                    Object deserializeClass = deserializeClass(bundle, fromParent);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return deserializeClass;
                case 9:
                    Object deserializeIBinder = deserializeIBinder(bundle, fromParent);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return deserializeIBinder;
                default:
                    throw new TracedBundlerException("Unsupported class type in bundle: " + i2, fromParent);
            }
        } catch (Throwable th) {
            if (fromParent != null) {
                try {
                    fromParent.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    static String getBundledTypeName(int i2) {
        String str = BUNDLED_TYPE_NAMES.get(Integer.valueOf(i2));
        return str == null ? EnvironmentCompat.MEDIA_UNKNOWN : str;
    }

    private static Method getClassOrSuperclassMethod(@Nullable Class<?> cls, String str, Trace trace) {
        Method[] declaredMethods;
        if (cls == null || cls == Object.class) {
            throw new TracedBundlerException("No method " + str + " in class " + cls, trace);
        }
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getName().equals(str)) {
                method.setAccessible(true);
                return method;
            }
        }
        return getClassOrSuperclassMethod(cls.getSuperclass(), str, trace);
    }

    @VisibleForTesting
    static String getFieldName(String str, String str2) {
        return str + str2;
    }

    @VisibleForTesting
    static String getFieldName(Field field) {
        return getFieldName(field.getDeclaringClass().getName(), field.getName());
    }

    private static List<Field> getFields(@Nullable Class<?> cls) {
        Field[] declaredFields;
        ArrayList arrayList = new ArrayList();
        if (cls != null && cls != Object.class) {
            for (Field field : cls.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    arrayList.add(field);
                }
            }
            arrayList.addAll(getFields(cls.getSuperclass()));
        }
        return arrayList;
    }

    static String getUnobfuscatedClassName(Class<?> cls) {
        String str = UNOBFUSCATED_TYPE_NAMES.get(cls);
        if (str == null) {
            if (List.class.isAssignableFrom(cls)) {
                return "<List>";
            }
            if (Map.class.isAssignableFrom(cls)) {
                return "<Map>";
            }
            if (Set.class.isAssignableFrom(cls)) {
                return "<Set>";
            }
        }
        return str == null ? cls.getSimpleName() : str;
    }

    private static Map<Integer, String> initBundledTypeNames() {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(0, TreeJsonEncoderKt.PRIMITIVE_TAG);
        arrayMap.put(1, "iInterface");
        arrayMap.put(9, "iBinder");
        arrayMap.put(2, "map");
        arrayMap.put(3, "set");
        arrayMap.put(4, "list");
        arrayMap.put(5, "object");
        arrayMap.put(6, "image");
        return arrayMap;
    }

    private static Map<Class<?>, String> initUnobfuscatedTypeNames() {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put(Boolean.class, "bool");
        arrayMap.put(Byte.class, "byte");
        arrayMap.put(Short.class, "short");
        arrayMap.put(Integer.class, "int");
        arrayMap.put(Long.class, "long");
        arrayMap.put(Double.class, "double");
        arrayMap.put(Float.class, TypedValues.Custom.S_FLOAT);
        arrayMap.put(String.class, TypedValues.Custom.S_STRING);
        arrayMap.put(Parcelable.class, "parcelable");
        arrayMap.put(Map.class, "map");
        arrayMap.put(List.class, "list");
        arrayMap.put(IconCompat.class, "image");
        return arrayMap;
    }

    static boolean isPrimitiveType(Object obj) {
        return (obj instanceof Boolean) || (obj instanceof Byte) || (obj instanceof Character) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof Float) || (obj instanceof String);
    }

    private static Bundle serializeClass(Class<?> cls) {
        Bundle bundle = new Bundle(2);
        bundle.putInt(TAG_CLASS_TYPE, 8);
        bundle.putString(TAG_VALUE, cls.getName());
        return bundle;
    }

    private static Bundle serializeCollection(Collection<Object> collection, Trace trace) {
        Bundle bundle = new Bundle(2);
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        int i2 = 0;
        for (Object obj : collection) {
            arrayList.add(toBundle(obj, "<item " + i2 + ">", trace));
            i2++;
        }
        bundle.putParcelableArrayList(TAG_VALUE, arrayList);
        return bundle;
    }

    private static Bundle serializeEnum(Object obj, Trace trace) {
        Bundle bundle = new Bundle(3);
        bundle.putInt(TAG_CLASS_TYPE, 7);
        try {
            bundle.putString(TAG_VALUE, (String) getClassOrSuperclassMethod(obj.getClass(), AppMeasurementSdk.ConditionalUserProperty.NAME, trace).invoke(obj, new Object[0]));
            bundle.putString(TAG_CLASS_NAME, obj.getClass().getName());
            return bundle;
        } catch (ReflectiveOperationException e2) {
            throw new TracedBundlerException("Enum missing name method", trace, e2);
        }
    }

    private static Bundle serializeIBinder(IBinder iBinder) {
        Bundle bundle = new Bundle(2);
        bundle.putInt(TAG_CLASS_TYPE, 9);
        bundle.putBinder(TAG_VALUE, iBinder);
        return bundle;
    }

    private static Bundle serializeIInterface(IInterface iInterface) {
        Bundle bundle = new Bundle(3);
        String name = iInterface.getClass().getName();
        bundle.putInt(TAG_CLASS_TYPE, 1);
        bundle.putBinder(TAG_VALUE, iInterface.asBinder());
        bundle.putString(TAG_CLASS_NAME, name);
        return bundle;
    }

    private static Bundle serializeImage(IconCompat iconCompat) {
        Bundle bundle = new Bundle(2);
        bundle.putInt(TAG_CLASS_TYPE, 6);
        bundle.putBundle(TAG_VALUE, iconCompat.toBundle());
        return bundle;
    }

    private static Bundle serializeList(List<Object> list, Trace trace) {
        Bundle serializeCollection = serializeCollection(list, trace);
        serializeCollection.putInt(TAG_CLASS_TYPE, 4);
        return serializeCollection;
    }

    private static Bundle serializeMap(Map<Object, Object> map, Trace trace) {
        Bundle bundle = new Bundle(2);
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        int i2 = 0;
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Bundle bundle2 = new Bundle(2);
            Object key = entry.getKey();
            bundle2.putBundle(TAG_1, toBundle(key, "<key " + i2 + ">", trace));
            if (entry.getValue() != null) {
                Object value = entry.getValue();
                bundle2.putBundle(TAG_2, toBundle(value, "<value " + i2 + ">", trace));
            }
            i2++;
            arrayList.add(bundle2);
        }
        bundle.putInt(TAG_CLASS_TYPE, 2);
        bundle.putParcelableArrayList(TAG_VALUE, arrayList);
        return bundle;
    }

    private static Bundle serializeObject(Object obj, Trace trace) {
        String name = obj.getClass().getName();
        try {
            obj.getClass().getDeclaredConstructor(new Class[0]);
            List<Field> fields = getFields(obj.getClass());
            Bundle bundle = new Bundle(fields.size() + 2);
            bundle.putInt(TAG_CLASS_TYPE, 5);
            bundle.putString(TAG_CLASS_NAME, name);
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = getFieldName(field);
                try {
                    Object obj2 = field.get(obj);
                    if (obj2 != null) {
                        bundle.putParcelable(fieldName, toBundle(obj2, field.getName(), trace));
                    }
                } catch (IllegalAccessException e2) {
                    throw new TracedBundlerException("Field is not accessible: " + fieldName, trace, e2);
                }
            }
            return bundle;
        } catch (NoSuchMethodException e3) {
            throw new TracedBundlerException("Class to deserialize is missing a no args constructor: " + name, trace, e3);
        }
    }

    private static Bundle serializePrimitive(Object obj, Trace trace) {
        Bundle bundle = new Bundle(2);
        bundle.putInt(TAG_CLASS_TYPE, 0);
        if (obj instanceof Boolean) {
            bundle.putBoolean(TAG_VALUE, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Byte) {
            bundle.putByte(TAG_VALUE, ((Byte) obj).byteValue());
        } else if (obj instanceof Character) {
            bundle.putChar(TAG_VALUE, ((Character) obj).charValue());
        } else if (obj instanceof Short) {
            bundle.putShort(TAG_VALUE, ((Short) obj).shortValue());
        } else if (obj instanceof Integer) {
            bundle.putInt(TAG_VALUE, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            bundle.putLong(TAG_VALUE, ((Long) obj).longValue());
        } else if (obj instanceof Double) {
            bundle.putDouble(TAG_VALUE, ((Double) obj).doubleValue());
        } else if (obj instanceof Float) {
            bundle.putFloat(TAG_VALUE, ((Float) obj).floatValue());
        } else if (obj instanceof String) {
            bundle.putString(TAG_VALUE, (String) obj);
        } else if (!(obj instanceof Parcelable)) {
            throw new TracedBundlerException("Unsupported primitive type: " + obj.getClass().getName(), trace);
        } else {
            bundle.putParcelable(TAG_VALUE, (Parcelable) obj);
        }
        return bundle;
    }

    private static Bundle serializeSet(Set<Object> set, Trace trace) {
        Bundle serializeCollection = serializeCollection(set, trace);
        serializeCollection.putInt(TAG_CLASS_TYPE, 3);
        return serializeCollection;
    }

    @NonNull
    public static Bundle toBundle(@NonNull Object obj) {
        String unobfuscatedClassName = getUnobfuscatedClassName(obj.getClass());
        if (Log.isLoggable(LogTags.TAG_BUNDLER, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Bundling ");
            sb.append(unobfuscatedClassName);
        }
        return toBundle(obj, unobfuscatedClassName, Trace.create());
    }

    private static Bundle toBundle(@Nullable Object obj, String str, Trace trace) {
        if (obj != null && trace.find(obj)) {
            throw new CycleDetectedBundlerException("Found cycle while bundling type " + obj.getClass().getSimpleName(), trace);
        }
        Trace fromParent = Trace.fromParent(obj, str, trace);
        try {
            if (obj != null) {
                if (obj instanceof IconCompat) {
                    Bundle serializeImage = serializeImage((IconCompat) obj);
                    if (fromParent != null) {
                        fromParent.close();
                    }
                    return serializeImage;
                }
                if (!isPrimitiveType(obj) && !(obj instanceof Parcelable)) {
                    if (obj instanceof IInterface) {
                        Bundle serializeIInterface = serializeIInterface((IInterface) obj);
                        if (fromParent != null) {
                            fromParent.close();
                        }
                        return serializeIInterface;
                    } else if (obj instanceof IBinder) {
                        Bundle serializeIBinder = serializeIBinder((IBinder) obj);
                        if (fromParent != null) {
                            fromParent.close();
                        }
                        return serializeIBinder;
                    } else if (obj instanceof Map) {
                        Bundle serializeMap = serializeMap((Map) obj, fromParent);
                        if (fromParent != null) {
                            fromParent.close();
                        }
                        return serializeMap;
                    } else if (obj instanceof List) {
                        Bundle serializeList = serializeList((List) obj, fromParent);
                        if (fromParent != null) {
                            fromParent.close();
                        }
                        return serializeList;
                    } else if (obj instanceof Set) {
                        Bundle serializeSet = serializeSet((Set) obj, fromParent);
                        if (fromParent != null) {
                            fromParent.close();
                        }
                        return serializeSet;
                    } else if (obj.getClass().isEnum()) {
                        Bundle serializeEnum = serializeEnum(obj, fromParent);
                        if (fromParent != null) {
                            fromParent.close();
                        }
                        return serializeEnum;
                    } else if (obj instanceof Class) {
                        Bundle serializeClass = serializeClass((Class) obj);
                        if (fromParent != null) {
                            fromParent.close();
                        }
                        return serializeClass;
                    } else if (obj.getClass().isArray()) {
                        throw new TracedBundlerException("Object serializing contains an array, use a list or a set instead", fromParent);
                    } else {
                        Bundle serializeObject = serializeObject(obj, fromParent);
                        if (fromParent != null) {
                            fromParent.close();
                        }
                        return serializeObject;
                    }
                }
                Bundle serializePrimitive = serializePrimitive(obj, fromParent);
                if (fromParent != null) {
                    fromParent.close();
                }
                return serializePrimitive;
            }
            throw new TracedBundlerException("Bundling of null object is not supported", fromParent);
        } catch (Throwable th) {
            if (fromParent != null) {
                try {
                    fromParent.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
