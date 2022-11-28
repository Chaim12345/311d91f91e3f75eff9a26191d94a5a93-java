package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
/* loaded from: classes4.dex */
public interface CallAdapter<R, T> {

    /* loaded from: classes4.dex */
    public static abstract class Factory {
        /* JADX INFO: Access modifiers changed from: protected */
        public static Type a(int i2, ParameterizedType parameterizedType) {
            return Utils.f(i2, parameterizedType);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public static Class b(Type type) {
            return Utils.g(type);
        }

        @Nullable
        public abstract CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit);
    }

    T adapt(Call<R> call);

    Type responseType();
}
