package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import javax.annotation.Nullable;
import okhttp3.ResponseBody;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import retrofit2.Converter;
/* JADX INFO: Access modifiers changed from: package-private */
@IgnoreJRERequirement
/* loaded from: classes4.dex */
public final class OptionalConverterFactory extends Converter.Factory {

    /* renamed from: a  reason: collision with root package name */
    static final Converter.Factory f15333a = new OptionalConverterFactory();

    @IgnoreJRERequirement
    /* loaded from: classes4.dex */
    static final class OptionalConverter<T> implements Converter<ResponseBody, Optional<T>> {

        /* renamed from: a  reason: collision with root package name */
        final Converter f15334a;

        OptionalConverter(Converter converter) {
            this.f15334a = converter;
        }

        @Override // retrofit2.Converter
        public Optional<T> convert(ResponseBody responseBody) {
            return Optional.ofNullable(this.f15334a.convert(responseBody));
        }
    }

    OptionalConverterFactory() {
    }

    @Override // retrofit2.Converter.Factory
    @Nullable
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (Converter.Factory.b(type) != Optional.class) {
            return null;
        }
        return new OptionalConverter(retrofit.responseBodyConverter(Converter.Factory.a(0, (ParameterizedType) type), annotationArr));
    }
}
