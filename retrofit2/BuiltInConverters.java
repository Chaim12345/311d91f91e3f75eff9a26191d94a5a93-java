package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import kotlin.Unit;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.http.Streaming;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class BuiltInConverters extends Converter.Factory {
    private boolean checkForKotlinUnit = true;

    /* loaded from: classes4.dex */
    static final class BufferingResponseBodyConverter implements Converter<ResponseBody, ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        static final BufferingResponseBodyConverter f15305a = new BufferingResponseBodyConverter();

        BufferingResponseBodyConverter() {
        }

        @Override // retrofit2.Converter
        public ResponseBody convert(ResponseBody responseBody) {
            try {
                return Utils.a(responseBody);
            } finally {
                responseBody.close();
            }
        }
    }

    /* loaded from: classes4.dex */
    static final class RequestBodyConverter implements Converter<RequestBody, RequestBody> {

        /* renamed from: a  reason: collision with root package name */
        static final RequestBodyConverter f15306a = new RequestBodyConverter();

        RequestBodyConverter() {
        }

        @Override // retrofit2.Converter
        public RequestBody convert(RequestBody requestBody) {
            return requestBody;
        }
    }

    /* loaded from: classes4.dex */
    static final class StreamingResponseBodyConverter implements Converter<ResponseBody, ResponseBody> {

        /* renamed from: a  reason: collision with root package name */
        static final StreamingResponseBodyConverter f15307a = new StreamingResponseBodyConverter();

        StreamingResponseBodyConverter() {
        }

        @Override // retrofit2.Converter
        public ResponseBody convert(ResponseBody responseBody) {
            return responseBody;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class ToStringConverter implements Converter<Object, String> {

        /* renamed from: a  reason: collision with root package name */
        static final ToStringConverter f15308a = new ToStringConverter();

        ToStringConverter() {
        }

        @Override // retrofit2.Converter
        public String convert(Object obj) {
            return obj.toString();
        }
    }

    /* loaded from: classes4.dex */
    static final class UnitResponseBodyConverter implements Converter<ResponseBody, Unit> {

        /* renamed from: a  reason: collision with root package name */
        static final UnitResponseBodyConverter f15309a = new UnitResponseBodyConverter();

        UnitResponseBodyConverter() {
        }

        @Override // retrofit2.Converter
        public Unit convert(ResponseBody responseBody) {
            responseBody.close();
            return Unit.INSTANCE;
        }
    }

    /* loaded from: classes4.dex */
    static final class VoidResponseBodyConverter implements Converter<ResponseBody, Void> {

        /* renamed from: a  reason: collision with root package name */
        static final VoidResponseBodyConverter f15310a = new VoidResponseBodyConverter();

        VoidResponseBodyConverter() {
        }

        @Override // retrofit2.Converter
        public Void convert(ResponseBody responseBody) {
            responseBody.close();
            return null;
        }
    }

    @Override // retrofit2.Converter.Factory
    @Nullable
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotationArr, Annotation[] annotationArr2, Retrofit retrofit) {
        if (RequestBody.class.isAssignableFrom(Utils.g(type))) {
            return RequestBodyConverter.f15306a;
        }
        return null;
    }

    @Override // retrofit2.Converter.Factory
    @Nullable
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (type == ResponseBody.class) {
            return Utils.j(annotationArr, Streaming.class) ? StreamingResponseBodyConverter.f15307a : BufferingResponseBodyConverter.f15305a;
        } else if (type == Void.class) {
            return VoidResponseBodyConverter.f15310a;
        } else {
            if (this.checkForKotlinUnit && type == Unit.class) {
                try {
                    return UnitResponseBodyConverter.f15309a;
                } catch (NoClassDefFoundError unused) {
                    this.checkForKotlinUnit = false;
                    return null;
                }
            }
            return null;
        }
    }
}
