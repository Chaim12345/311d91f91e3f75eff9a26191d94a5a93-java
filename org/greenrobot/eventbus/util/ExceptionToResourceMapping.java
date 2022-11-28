package org.greenrobot.eventbus.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.greenrobot.eventbus.Logger;
/* loaded from: classes4.dex */
public class ExceptionToResourceMapping {
    public final Map<Class<? extends Throwable>, Integer> throwableToMsgIdMap = new HashMap();

    protected Integer a(Throwable th) {
        Class<?> cls = th.getClass();
        Integer num = this.throwableToMsgIdMap.get(cls);
        if (num == null) {
            Class<? extends Throwable> cls2 = null;
            for (Map.Entry<Class<? extends Throwable>, Integer> entry : this.throwableToMsgIdMap.entrySet()) {
                Class<? extends Throwable> key = entry.getKey();
                if (key.isAssignableFrom(cls) && (cls2 == null || cls2.isAssignableFrom(key))) {
                    num = entry.getValue();
                    cls2 = key;
                }
            }
        }
        return num;
    }

    public ExceptionToResourceMapping addMapping(Class<? extends Throwable> cls, int i2) {
        this.throwableToMsgIdMap.put(cls, Integer.valueOf(i2));
        return this;
    }

    public Integer mapThrowable(Throwable th) {
        int i2 = 20;
        Throwable th2 = th;
        do {
            Integer a2 = a(th2);
            if (a2 == null) {
                th2 = th2.getCause();
                i2--;
                if (i2 <= 0 || th2 == th) {
                    break;
                }
            } else {
                return a2;
            }
        } while (th2 != null);
        Logger.Default.get().log(Level.FINE, "No specific message resource ID found for " + th);
        return null;
    }
}
