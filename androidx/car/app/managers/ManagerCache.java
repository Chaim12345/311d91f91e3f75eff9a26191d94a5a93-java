package androidx.car.app.managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class ManagerCache {
    private final Map<Class<? extends Manager>, Manager> mValues = new HashMap();
    private final Map<Class<? extends Manager>, RuntimeException> mExceptions = new HashMap();
    private final Map<Class<? extends Manager>, ManagerFactory<?>> mFactories = new HashMap();
    private final Map<String, Class<? extends Manager>> mClassByName = new HashMap();
    private final Map<Class<? extends Manager>, String> mNameByClass = new HashMap();

    public <T extends Manager> void addFactory(@NonNull Class<T> cls, @Nullable String str, @NonNull ManagerFactory<T> managerFactory) {
        this.mFactories.put(cls, managerFactory);
        if (str != null) {
            this.mClassByName.put(str, cls);
            this.mNameByClass.put(cls, str);
        }
    }

    @NonNull
    public String getName(@NonNull Class<? extends Manager> cls) {
        String str = this.mNameByClass.get(cls);
        if (str != null) {
            return str;
        }
        throw new IllegalArgumentException("The class does not correspond to a car service");
    }

    @NonNull
    public <T extends Manager> T getOrCreate(@NonNull Class<T> cls) {
        RuntimeException runtimeException = this.mExceptions.get(cls);
        if (runtimeException == null) {
            T t2 = (T) this.mValues.get(cls);
            if (t2 != null) {
                return t2;
            }
            ManagerFactory<?> managerFactory = this.mFactories.get(cls);
            if (managerFactory == null) {
                throw new IllegalArgumentException("The class '" + cls + "' does not correspond to a car service");
            }
            try {
                T t3 = (T) managerFactory.create();
                this.mValues.put(cls, t3);
                return t3;
            } catch (RuntimeException e2) {
                this.mExceptions.put(cls, e2);
                throw e2;
            }
        }
        throw runtimeException;
    }

    @NonNull
    public Manager getOrCreate(@NonNull String str) {
        Class<? extends Manager> cls = this.mClassByName.get(str);
        if (cls != null) {
            return getOrCreate((Class<Manager>) cls);
        }
        throw new IllegalArgumentException("The name '" + str + "' does not correspond to a car service");
    }
}
