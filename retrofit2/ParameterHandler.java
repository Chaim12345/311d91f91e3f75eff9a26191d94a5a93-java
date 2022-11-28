package retrofit2;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class ParameterHandler<T> {

    /* loaded from: classes4.dex */
    static final class Body<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;
        private final Method method;

        /* renamed from: p  reason: collision with root package name */
        private final int f15337p;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Body(Method method, int i2, Converter converter) {
            this.method = method;
            this.f15337p = i2;
            this.converter = converter;
        }

        @Override // retrofit2.ParameterHandler
        void a(RequestBuilder requestBuilder, @Nullable Object obj) {
            if (obj == null) {
                throw Utils.m(this.method, this.f15337p, "Body parameter value must not be null.", new Object[0]);
            }
            try {
                requestBuilder.j(this.converter.convert(obj));
            } catch (IOException e2) {
                Method method = this.method;
                int i2 = this.f15337p;
                throw Utils.n(method, e2, i2, "Unable to convert " + obj + " to RequestBody", new Object[0]);
            }
        }
    }

    /* loaded from: classes4.dex */
    static final class Field<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Field(String str, Converter converter, boolean z) {
            Objects.requireNonNull(str, "name == null");
            this.name = str;
            this.valueConverter = converter;
            this.encoded = z;
        }

        @Override // retrofit2.ParameterHandler
        void a(RequestBuilder requestBuilder, @Nullable Object obj) {
            String convert;
            if (obj == null || (convert = this.valueConverter.convert(obj)) == null) {
                return;
            }
            requestBuilder.a(this.name, convert, this.encoded);
        }
    }

    /* loaded from: classes4.dex */
    static final class FieldMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Method method;

        /* renamed from: p  reason: collision with root package name */
        private final int f15338p;
        private final Converter<T, String> valueConverter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public FieldMap(Method method, int i2, Converter converter, boolean z) {
            this.method = method;
            this.f15338p = i2;
            this.valueConverter = converter;
            this.encoded = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // retrofit2.ParameterHandler
        /* renamed from: d */
        public void a(RequestBuilder requestBuilder, @Nullable Map map) {
            if (map == null) {
                throw Utils.m(this.method, this.f15338p, "Field map was null.", new Object[0]);
            }
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw Utils.m(this.method, this.f15338p, "Field map contained null key.", new Object[0]);
                }
                Object value = entry.getValue();
                if (value == null) {
                    Method method = this.method;
                    int i2 = this.f15338p;
                    throw Utils.m(method, i2, "Field map contained null value for key '" + str + "'.", new Object[0]);
                }
                String str2 = (String) this.valueConverter.convert(value);
                if (str2 == null) {
                    Method method2 = this.method;
                    int i3 = this.f15338p;
                    throw Utils.m(method2, i3, "Field map value '" + value + "' converted to null by " + this.valueConverter.getClass().getName() + " for key '" + str + "'.", new Object[0]);
                }
                requestBuilder.a(str, str2, this.encoded);
            }
        }
    }

    /* loaded from: classes4.dex */
    static final class Header<T> extends ParameterHandler<T> {
        private final String name;
        private final Converter<T, String> valueConverter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Header(String str, Converter converter) {
            Objects.requireNonNull(str, "name == null");
            this.name = str;
            this.valueConverter = converter;
        }

        @Override // retrofit2.ParameterHandler
        void a(RequestBuilder requestBuilder, @Nullable Object obj) {
            String convert;
            if (obj == null || (convert = this.valueConverter.convert(obj)) == null) {
                return;
            }
            requestBuilder.b(this.name, convert);
        }
    }

    /* loaded from: classes4.dex */
    static final class HeaderMap<T> extends ParameterHandler<Map<String, T>> {
        private final Method method;

        /* renamed from: p  reason: collision with root package name */
        private final int f15339p;
        private final Converter<T, String> valueConverter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public HeaderMap(Method method, int i2, Converter converter) {
            this.method = method;
            this.f15339p = i2;
            this.valueConverter = converter;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // retrofit2.ParameterHandler
        /* renamed from: d */
        public void a(RequestBuilder requestBuilder, @Nullable Map map) {
            if (map == null) {
                throw Utils.m(this.method, this.f15339p, "Header map was null.", new Object[0]);
            }
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw Utils.m(this.method, this.f15339p, "Header map contained null key.", new Object[0]);
                }
                Object value = entry.getValue();
                if (value == null) {
                    Method method = this.method;
                    int i2 = this.f15339p;
                    throw Utils.m(method, i2, "Header map contained null value for key '" + str + "'.", new Object[0]);
                }
                requestBuilder.b(str, (String) this.valueConverter.convert(value));
            }
        }
    }

    /* loaded from: classes4.dex */
    static final class Headers extends ParameterHandler<okhttp3.Headers> {
        private final Method method;

        /* renamed from: p  reason: collision with root package name */
        private final int f15340p;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Headers(Method method, int i2) {
            this.method = method;
            this.f15340p = i2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit2.ParameterHandler
        /* renamed from: d */
        public void a(RequestBuilder requestBuilder, @Nullable okhttp3.Headers headers) {
            if (headers == null) {
                throw Utils.m(this.method, this.f15340p, "Headers parameter must not be null.", new Object[0]);
            }
            requestBuilder.c(headers);
        }
    }

    /* loaded from: classes4.dex */
    static final class Part<T> extends ParameterHandler<T> {
        private final Converter<T, RequestBody> converter;
        private final okhttp3.Headers headers;
        private final Method method;

        /* renamed from: p  reason: collision with root package name */
        private final int f15341p;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Part(Method method, int i2, okhttp3.Headers headers, Converter converter) {
            this.method = method;
            this.f15341p = i2;
            this.headers = headers;
            this.converter = converter;
        }

        @Override // retrofit2.ParameterHandler
        void a(RequestBuilder requestBuilder, @Nullable Object obj) {
            if (obj == null) {
                return;
            }
            try {
                requestBuilder.d(this.headers, this.converter.convert(obj));
            } catch (IOException e2) {
                Method method = this.method;
                int i2 = this.f15341p;
                throw Utils.m(method, i2, "Unable to convert " + obj + " to RequestBody", e2);
            }
        }
    }

    /* loaded from: classes4.dex */
    static final class PartMap<T> extends ParameterHandler<Map<String, T>> {
        private final Method method;

        /* renamed from: p  reason: collision with root package name */
        private final int f15342p;
        private final String transferEncoding;
        private final Converter<T, RequestBody> valueConverter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public PartMap(Method method, int i2, Converter converter, String str) {
            this.method = method;
            this.f15342p = i2;
            this.valueConverter = converter;
            this.transferEncoding = str;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // retrofit2.ParameterHandler
        /* renamed from: d */
        public void a(RequestBuilder requestBuilder, @Nullable Map map) {
            if (map == null) {
                throw Utils.m(this.method, this.f15342p, "Part map was null.", new Object[0]);
            }
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw Utils.m(this.method, this.f15342p, "Part map contained null key.", new Object[0]);
                }
                Object value = entry.getValue();
                if (value == null) {
                    Method method = this.method;
                    int i2 = this.f15342p;
                    throw Utils.m(method, i2, "Part map contained null value for key '" + str + "'.", new Object[0]);
                }
                requestBuilder.d(okhttp3.Headers.of(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + str + "\"", "Content-Transfer-Encoding", this.transferEncoding), (RequestBody) this.valueConverter.convert(value));
            }
        }
    }

    /* loaded from: classes4.dex */
    static final class Path<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final Method method;
        private final String name;

        /* renamed from: p  reason: collision with root package name */
        private final int f15343p;
        private final Converter<T, String> valueConverter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Path(Method method, int i2, String str, Converter converter, boolean z) {
            this.method = method;
            this.f15343p = i2;
            Objects.requireNonNull(str, "name == null");
            this.name = str;
            this.valueConverter = converter;
            this.encoded = z;
        }

        @Override // retrofit2.ParameterHandler
        void a(RequestBuilder requestBuilder, @Nullable Object obj) {
            if (obj != null) {
                requestBuilder.f(this.name, this.valueConverter.convert(obj), this.encoded);
                return;
            }
            Method method = this.method;
            int i2 = this.f15343p;
            throw Utils.m(method, i2, "Path parameter \"" + this.name + "\" value must not be null.", new Object[0]);
        }
    }

    /* loaded from: classes4.dex */
    static final class Query<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final String name;
        private final Converter<T, String> valueConverter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Query(String str, Converter converter, boolean z) {
            Objects.requireNonNull(str, "name == null");
            this.name = str;
            this.valueConverter = converter;
            this.encoded = z;
        }

        @Override // retrofit2.ParameterHandler
        void a(RequestBuilder requestBuilder, @Nullable Object obj) {
            String convert;
            if (obj == null || (convert = this.valueConverter.convert(obj)) == null) {
                return;
            }
            requestBuilder.g(this.name, convert, this.encoded);
        }
    }

    /* loaded from: classes4.dex */
    static final class QueryMap<T> extends ParameterHandler<Map<String, T>> {
        private final boolean encoded;
        private final Method method;

        /* renamed from: p  reason: collision with root package name */
        private final int f15344p;
        private final Converter<T, String> valueConverter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public QueryMap(Method method, int i2, Converter converter, boolean z) {
            this.method = method;
            this.f15344p = i2;
            this.valueConverter = converter;
            this.encoded = z;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // retrofit2.ParameterHandler
        /* renamed from: d */
        public void a(RequestBuilder requestBuilder, @Nullable Map map) {
            if (map == null) {
                throw Utils.m(this.method, this.f15344p, "Query map was null", new Object[0]);
            }
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (str == null) {
                    throw Utils.m(this.method, this.f15344p, "Query map contained null key.", new Object[0]);
                }
                Object value = entry.getValue();
                if (value == null) {
                    Method method = this.method;
                    int i2 = this.f15344p;
                    throw Utils.m(method, i2, "Query map contained null value for key '" + str + "'.", new Object[0]);
                }
                String str2 = (String) this.valueConverter.convert(value);
                if (str2 == null) {
                    Method method2 = this.method;
                    int i3 = this.f15344p;
                    throw Utils.m(method2, i3, "Query map value '" + value + "' converted to null by " + this.valueConverter.getClass().getName() + " for key '" + str + "'.", new Object[0]);
                }
                requestBuilder.g(str, str2, this.encoded);
            }
        }
    }

    /* loaded from: classes4.dex */
    static final class QueryName<T> extends ParameterHandler<T> {
        private final boolean encoded;
        private final Converter<T, String> nameConverter;

        /* JADX INFO: Access modifiers changed from: package-private */
        public QueryName(Converter converter, boolean z) {
            this.nameConverter = converter;
            this.encoded = z;
        }

        @Override // retrofit2.ParameterHandler
        void a(RequestBuilder requestBuilder, @Nullable Object obj) {
            if (obj == null) {
                return;
            }
            requestBuilder.g(this.nameConverter.convert(obj), null, this.encoded);
        }
    }

    /* loaded from: classes4.dex */
    static final class RawPart extends ParameterHandler<MultipartBody.Part> {

        /* renamed from: a  reason: collision with root package name */
        static final RawPart f15345a = new RawPart();

        private RawPart() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // retrofit2.ParameterHandler
        /* renamed from: d */
        public void a(RequestBuilder requestBuilder, @Nullable MultipartBody.Part part) {
            if (part != null) {
                requestBuilder.e(part);
            }
        }
    }

    /* loaded from: classes4.dex */
    static final class RelativeUrl extends ParameterHandler<Object> {
        private final Method method;

        /* renamed from: p  reason: collision with root package name */
        private final int f15346p;

        /* JADX INFO: Access modifiers changed from: package-private */
        public RelativeUrl(Method method, int i2) {
            this.method = method;
            this.f15346p = i2;
        }

        @Override // retrofit2.ParameterHandler
        void a(RequestBuilder requestBuilder, @Nullable Object obj) {
            if (obj == null) {
                throw Utils.m(this.method, this.f15346p, "@Url parameter is null.", new Object[0]);
            }
            requestBuilder.k(obj);
        }
    }

    /* loaded from: classes4.dex */
    static final class Tag<T> extends ParameterHandler<T> {

        /* renamed from: a  reason: collision with root package name */
        final Class f15347a;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Tag(Class cls) {
            this.f15347a = cls;
        }

        @Override // retrofit2.ParameterHandler
        void a(RequestBuilder requestBuilder, @Nullable Object obj) {
            requestBuilder.h(this.f15347a, obj);
        }
    }

    ParameterHandler() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(RequestBuilder requestBuilder, @Nullable Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ParameterHandler b() {
        return new ParameterHandler<Object>() { // from class: retrofit2.ParameterHandler.2
            @Override // retrofit2.ParameterHandler
            void a(RequestBuilder requestBuilder, @Nullable Object obj) {
                if (obj == null) {
                    return;
                }
                int length = Array.getLength(obj);
                for (int i2 = 0; i2 < length; i2++) {
                    ParameterHandler.this.a(requestBuilder, Array.get(obj, i2));
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final ParameterHandler c() {
        return new ParameterHandler<Iterable<T>>() { // from class: retrofit2.ParameterHandler.1
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // retrofit2.ParameterHandler
            /* renamed from: d */
            public void a(RequestBuilder requestBuilder, @Nullable Iterable iterable) {
                if (iterable == null) {
                    return;
                }
                Iterator<T> it = iterable.iterator();
                while (it.hasNext()) {
                    ParameterHandler.this.a(requestBuilder, it.next());
                }
            }
        };
    }
}
