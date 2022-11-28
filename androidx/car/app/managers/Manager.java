package androidx.car.app.managers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
/* loaded from: classes.dex */
public interface Manager {
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    static <U extends Manager> U create(@NonNull Class<U> cls, @NonNull String str, @NonNull Object... objArr) {
        try {
            Class<?> cls2 = Class.forName(str);
            Class<?>[] clsArr = new Class[objArr.length];
            for (int i2 = 0; i2 < objArr.length; i2++) {
                clsArr[i2] = objArr[i2].getClass();
            }
            return cls.cast(cls2.getConstructor(clsArr).newInstance(objArr));
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (ReflectiveOperationException e2) {
            throw new IllegalStateException("Mismatch with artifact", e2);
        }
    }
}
