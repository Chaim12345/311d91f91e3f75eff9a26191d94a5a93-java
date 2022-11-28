package retrofit2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class ServiceMethod<T> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static ServiceMethod b(Retrofit retrofit, Method method) {
        RequestFactory b2 = RequestFactory.b(retrofit, method);
        Type genericReturnType = method.getGenericReturnType();
        if (Utils.i(genericReturnType)) {
            throw Utils.k(method, "Method return type must not include a type variable or wildcard: %s", genericReturnType);
        }
        if (genericReturnType != Void.TYPE) {
            return HttpServiceMethod.d(retrofit, method, b2);
        }
        throw Utils.k(method, "Service methods cannot return void.", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract Object a(Object[] objArr);
}
