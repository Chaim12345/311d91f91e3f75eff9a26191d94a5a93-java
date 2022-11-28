package org.apache.http.client.utils;

import java.lang.reflect.InvocationTargetException;
/* loaded from: classes3.dex */
public class CloneUtils {
    private CloneUtils() {
    }

    public static Object clone(Object obj) {
        return cloneObject(obj);
    }

    public static <T> T cloneObject(T t2) {
        if (t2 == null) {
            return null;
        }
        if (t2 instanceof Cloneable) {
            try {
                try {
                    return (T) t2.getClass().getMethod("clone", null).invoke(t2, null);
                } catch (IllegalAccessException e2) {
                    throw new IllegalAccessError(e2.getMessage());
                } catch (InvocationTargetException e3) {
                    Throwable cause = e3.getCause();
                    if (cause instanceof CloneNotSupportedException) {
                        throw ((CloneNotSupportedException) cause);
                    }
                    throw new Error("Unexpected exception", cause);
                }
            } catch (NoSuchMethodException e4) {
                throw new NoSuchMethodError(e4.getMessage());
            }
        }
        throw new CloneNotSupportedException();
    }
}
