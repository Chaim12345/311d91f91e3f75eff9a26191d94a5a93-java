package retrofit2;

import com.google.common.net.HttpHeaders;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import kotlin.coroutines.Continuation;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import retrofit2.ParameterHandler;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Tag;
import retrofit2.http.Url;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class RequestFactory {

    /* renamed from: a  reason: collision with root package name */
    final String f15348a;

    /* renamed from: b  reason: collision with root package name */
    final boolean f15349b;
    private final HttpUrl baseUrl;
    @Nullable
    private final MediaType contentType;
    private final boolean hasBody;
    @Nullable
    private final Headers headers;
    private final boolean isFormEncoded;
    private final boolean isMultipart;
    private final Method method;
    private final ParameterHandler<?>[] parameterHandlers;
    @Nullable
    private final String relativeUrl;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        final Retrofit f15350a;

        /* renamed from: b  reason: collision with root package name */
        final Method f15351b;

        /* renamed from: c  reason: collision with root package name */
        final Annotation[] f15352c;

        /* renamed from: d  reason: collision with root package name */
        final Annotation[][] f15353d;

        /* renamed from: e  reason: collision with root package name */
        final Type[] f15354e;

        /* renamed from: f  reason: collision with root package name */
        boolean f15355f;

        /* renamed from: g  reason: collision with root package name */
        boolean f15356g;

        /* renamed from: h  reason: collision with root package name */
        boolean f15357h;

        /* renamed from: i  reason: collision with root package name */
        boolean f15358i;

        /* renamed from: j  reason: collision with root package name */
        boolean f15359j;

        /* renamed from: k  reason: collision with root package name */
        boolean f15360k;

        /* renamed from: l  reason: collision with root package name */
        boolean f15361l;

        /* renamed from: m  reason: collision with root package name */
        boolean f15362m;
        @Nullable

        /* renamed from: n  reason: collision with root package name */
        String f15363n;

        /* renamed from: o  reason: collision with root package name */
        boolean f15364o;

        /* renamed from: p  reason: collision with root package name */
        boolean f15365p;

        /* renamed from: q  reason: collision with root package name */
        boolean f15366q;
        @Nullable

        /* renamed from: r  reason: collision with root package name */
        String f15367r;
        @Nullable

        /* renamed from: s  reason: collision with root package name */
        Headers f15368s;
        @Nullable

        /* renamed from: t  reason: collision with root package name */
        MediaType f15369t;
        @Nullable
        Set u;
        @Nullable
        ParameterHandler[] v;
        boolean w;
        private static final Pattern PARAM_URL_REGEX = Pattern.compile("\\{([a-zA-Z][a-zA-Z0-9_-]*)\\}");
        private static final String PARAM = "[a-zA-Z][a-zA-Z0-9_-]*";
        private static final Pattern PARAM_NAME_REGEX = Pattern.compile(PARAM);

        Builder(Retrofit retrofit, Method method) {
            this.f15350a = retrofit;
            this.f15351b = method;
            this.f15352c = method.getAnnotations();
            this.f15354e = method.getGenericParameterTypes();
            this.f15353d = method.getParameterAnnotations();
        }

        static Set b(String str) {
            Matcher matcher = PARAM_URL_REGEX.matcher(str);
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            while (matcher.find()) {
                linkedHashSet.add(matcher.group(1));
            }
            return linkedHashSet;
        }

        private static Class<?> boxIfPrimitive(Class<?> cls) {
            return Boolean.TYPE == cls ? Boolean.class : Byte.TYPE == cls ? Byte.class : Character.TYPE == cls ? Character.class : Double.TYPE == cls ? Double.class : Float.TYPE == cls ? Float.class : Integer.TYPE == cls ? Integer.class : Long.TYPE == cls ? Long.class : Short.TYPE == cls ? Short.class : cls;
        }

        private Headers parseHeaders(String[] strArr) {
            Headers.Builder builder = new Headers.Builder();
            for (String str : strArr) {
                int indexOf = str.indexOf(58);
                if (indexOf == -1 || indexOf == 0 || indexOf == str.length() - 1) {
                    throw Utils.k(this.f15351b, "@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str);
                }
                String substring = str.substring(0, indexOf);
                String trim = str.substring(indexOf + 1).trim();
                if ("Content-Type".equalsIgnoreCase(substring)) {
                    try {
                        this.f15369t = MediaType.get(trim);
                    } catch (IllegalArgumentException e2) {
                        throw Utils.l(this.f15351b, e2, "Malformed content type: %s", trim);
                    }
                } else {
                    builder.add(substring, trim);
                }
            }
            return builder.build();
        }

        private void parseHttpMethodAndPath(String str, String str2, boolean z) {
            String str3 = this.f15363n;
            if (str3 != null) {
                throw Utils.k(this.f15351b, "Only one HTTP method is allowed. Found: %s and %s.", str3, str);
            }
            this.f15363n = str;
            this.f15364o = z;
            if (str2.isEmpty()) {
                return;
            }
            int indexOf = str2.indexOf(63);
            if (indexOf != -1 && indexOf < str2.length() - 1) {
                String substring = str2.substring(indexOf + 1);
                if (PARAM_URL_REGEX.matcher(substring).find()) {
                    throw Utils.k(this.f15351b, "URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", substring);
                }
            }
            this.f15367r = str2;
            this.u = b(str2);
        }

        private void parseMethodAnnotation(Annotation annotation) {
            String value;
            String str;
            String value2;
            String str2;
            if (annotation instanceof DELETE) {
                value = ((DELETE) annotation).value();
                str = "DELETE";
            } else if (annotation instanceof GET) {
                value = ((GET) annotation).value();
                str = "GET";
            } else if (!(annotation instanceof HEAD)) {
                if (annotation instanceof PATCH) {
                    value2 = ((PATCH) annotation).value();
                    str2 = "PATCH";
                } else if (annotation instanceof POST) {
                    value2 = ((POST) annotation).value();
                    str2 = "POST";
                } else if (annotation instanceof PUT) {
                    value2 = ((PUT) annotation).value();
                    str2 = "PUT";
                } else if (!(annotation instanceof OPTIONS)) {
                    if (annotation instanceof HTTP) {
                        HTTP http = (HTTP) annotation;
                        parseHttpMethodAndPath(http.method(), http.path(), http.hasBody());
                        return;
                    } else if (annotation instanceof retrofit2.http.Headers) {
                        String[] value3 = ((retrofit2.http.Headers) annotation).value();
                        if (value3.length == 0) {
                            throw Utils.k(this.f15351b, "@Headers annotation is empty.", new Object[0]);
                        }
                        this.f15368s = parseHeaders(value3);
                        return;
                    } else if (annotation instanceof Multipart) {
                        if (this.f15365p) {
                            throw Utils.k(this.f15351b, "Only one encoding annotation is allowed.", new Object[0]);
                        }
                        this.f15366q = true;
                        return;
                    } else if (annotation instanceof FormUrlEncoded) {
                        if (this.f15366q) {
                            throw Utils.k(this.f15351b, "Only one encoding annotation is allowed.", new Object[0]);
                        }
                        this.f15365p = true;
                        return;
                    } else {
                        return;
                    }
                } else {
                    value = ((OPTIONS) annotation).value();
                    str = "OPTIONS";
                }
                parseHttpMethodAndPath(str2, value2, true);
                return;
            } else {
                value = ((HEAD) annotation).value();
                str = "HEAD";
            }
            parseHttpMethodAndPath(str, value, false);
        }

        @Nullable
        private ParameterHandler<?> parseParameter(int i2, Type type, @Nullable Annotation[] annotationArr, boolean z) {
            ParameterHandler<?> parameterHandler;
            if (annotationArr != null) {
                parameterHandler = null;
                for (Annotation annotation : annotationArr) {
                    ParameterHandler<?> parseParameterAnnotation = parseParameterAnnotation(i2, type, annotationArr, annotation);
                    if (parseParameterAnnotation != null) {
                        if (parameterHandler != null) {
                            throw Utils.m(this.f15351b, i2, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                        }
                        parameterHandler = parseParameterAnnotation;
                    }
                }
            } else {
                parameterHandler = null;
            }
            if (parameterHandler == null) {
                if (z) {
                    try {
                        if (Utils.g(type) == Continuation.class) {
                            this.w = true;
                            return null;
                        }
                    } catch (NoClassDefFoundError unused) {
                    }
                }
                throw Utils.m(this.f15351b, i2, "No Retrofit annotation found.", new Object[0]);
            }
            return parameterHandler;
        }

        @Nullable
        private ParameterHandler<?> parseParameterAnnotation(int i2, Type type, Annotation[] annotationArr, Annotation annotation) {
            if (annotation instanceof Url) {
                validateResolvableType(i2, type);
                if (this.f15362m) {
                    throw Utils.m(this.f15351b, i2, "Multiple @Url method annotations found.", new Object[0]);
                }
                if (this.f15358i) {
                    throw Utils.m(this.f15351b, i2, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if (this.f15359j) {
                    throw Utils.m(this.f15351b, i2, "A @Url parameter must not come after a @Query.", new Object[0]);
                }
                if (this.f15360k) {
                    throw Utils.m(this.f15351b, i2, "A @Url parameter must not come after a @QueryName.", new Object[0]);
                }
                if (this.f15361l) {
                    throw Utils.m(this.f15351b, i2, "A @Url parameter must not come after a @QueryMap.", new Object[0]);
                }
                if (this.f15367r == null) {
                    this.f15362m = true;
                    if (type == HttpUrl.class || type == String.class || type == URI.class || ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName()))) {
                        return new ParameterHandler.RelativeUrl(this.f15351b, i2);
                    }
                    throw Utils.m(this.f15351b, i2, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
                }
                throw Utils.m(this.f15351b, i2, "@Url cannot be used with @%s URL", this.f15363n);
            } else if (annotation instanceof Path) {
                validateResolvableType(i2, type);
                if (this.f15359j) {
                    throw Utils.m(this.f15351b, i2, "A @Path parameter must not come after a @Query.", new Object[0]);
                }
                if (this.f15360k) {
                    throw Utils.m(this.f15351b, i2, "A @Path parameter must not come after a @QueryName.", new Object[0]);
                }
                if (this.f15361l) {
                    throw Utils.m(this.f15351b, i2, "A @Path parameter must not come after a @QueryMap.", new Object[0]);
                }
                if (this.f15362m) {
                    throw Utils.m(this.f15351b, i2, "@Path parameters may not be used with @Url.", new Object[0]);
                }
                if (this.f15367r != null) {
                    this.f15358i = true;
                    Path path = (Path) annotation;
                    String value = path.value();
                    validatePathName(i2, value);
                    return new ParameterHandler.Path(this.f15351b, i2, value, this.f15350a.stringConverter(type, annotationArr), path.encoded());
                }
                throw Utils.m(this.f15351b, i2, "@Path can only be used with relative url on @%s", this.f15363n);
            } else if (annotation instanceof Query) {
                validateResolvableType(i2, type);
                Query query = (Query) annotation;
                String value2 = query.value();
                boolean encoded = query.encoded();
                Class g2 = Utils.g(type);
                this.f15359j = true;
                if (!Iterable.class.isAssignableFrom(g2)) {
                    return g2.isArray() ? new ParameterHandler.Query(value2, this.f15350a.stringConverter(boxIfPrimitive(g2.getComponentType()), annotationArr), encoded).b() : new ParameterHandler.Query(value2, this.f15350a.stringConverter(type, annotationArr), encoded);
                } else if (type instanceof ParameterizedType) {
                    return new ParameterHandler.Query(value2, this.f15350a.stringConverter(Utils.f(0, (ParameterizedType) type), annotationArr), encoded).c();
                } else {
                    throw Utils.m(this.f15351b, i2, g2.getSimpleName() + " must include generic type (e.g., " + g2.getSimpleName() + "<String>)", new Object[0]);
                }
            } else if (annotation instanceof QueryName) {
                validateResolvableType(i2, type);
                boolean encoded2 = ((QueryName) annotation).encoded();
                Class g3 = Utils.g(type);
                this.f15360k = true;
                if (!Iterable.class.isAssignableFrom(g3)) {
                    return g3.isArray() ? new ParameterHandler.QueryName(this.f15350a.stringConverter(boxIfPrimitive(g3.getComponentType()), annotationArr), encoded2).b() : new ParameterHandler.QueryName(this.f15350a.stringConverter(type, annotationArr), encoded2);
                } else if (type instanceof ParameterizedType) {
                    return new ParameterHandler.QueryName(this.f15350a.stringConverter(Utils.f(0, (ParameterizedType) type), annotationArr), encoded2).c();
                } else {
                    throw Utils.m(this.f15351b, i2, g3.getSimpleName() + " must include generic type (e.g., " + g3.getSimpleName() + "<String>)", new Object[0]);
                }
            } else if (annotation instanceof QueryMap) {
                validateResolvableType(i2, type);
                Class g4 = Utils.g(type);
                this.f15361l = true;
                if (Map.class.isAssignableFrom(g4)) {
                    Type h2 = Utils.h(type, g4, Map.class);
                    if (h2 instanceof ParameterizedType) {
                        ParameterizedType parameterizedType = (ParameterizedType) h2;
                        Type f2 = Utils.f(0, parameterizedType);
                        if (String.class == f2) {
                            return new ParameterHandler.QueryMap(this.f15351b, i2, this.f15350a.stringConverter(Utils.f(1, parameterizedType), annotationArr), ((QueryMap) annotation).encoded());
                        }
                        throw Utils.m(this.f15351b, i2, "@QueryMap keys must be of type String: " + f2, new Object[0]);
                    }
                    throw Utils.m(this.f15351b, i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw Utils.m(this.f15351b, i2, "@QueryMap parameter type must be Map.", new Object[0]);
            } else if (annotation instanceof Header) {
                validateResolvableType(i2, type);
                String value3 = ((Header) annotation).value();
                Class g5 = Utils.g(type);
                if (!Iterable.class.isAssignableFrom(g5)) {
                    return g5.isArray() ? new ParameterHandler.Header(value3, this.f15350a.stringConverter(boxIfPrimitive(g5.getComponentType()), annotationArr)).b() : new ParameterHandler.Header(value3, this.f15350a.stringConverter(type, annotationArr));
                } else if (type instanceof ParameterizedType) {
                    return new ParameterHandler.Header(value3, this.f15350a.stringConverter(Utils.f(0, (ParameterizedType) type), annotationArr)).c();
                } else {
                    throw Utils.m(this.f15351b, i2, g5.getSimpleName() + " must include generic type (e.g., " + g5.getSimpleName() + "<String>)", new Object[0]);
                }
            } else if (annotation instanceof HeaderMap) {
                if (type == Headers.class) {
                    return new ParameterHandler.Headers(this.f15351b, i2);
                }
                validateResolvableType(i2, type);
                Class g6 = Utils.g(type);
                if (Map.class.isAssignableFrom(g6)) {
                    Type h3 = Utils.h(type, g6, Map.class);
                    if (h3 instanceof ParameterizedType) {
                        ParameterizedType parameterizedType2 = (ParameterizedType) h3;
                        Type f3 = Utils.f(0, parameterizedType2);
                        if (String.class == f3) {
                            return new ParameterHandler.HeaderMap(this.f15351b, i2, this.f15350a.stringConverter(Utils.f(1, parameterizedType2), annotationArr));
                        }
                        throw Utils.m(this.f15351b, i2, "@HeaderMap keys must be of type String: " + f3, new Object[0]);
                    }
                    throw Utils.m(this.f15351b, i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw Utils.m(this.f15351b, i2, "@HeaderMap parameter type must be Map.", new Object[0]);
            } else if (annotation instanceof Field) {
                validateResolvableType(i2, type);
                if (this.f15365p) {
                    Field field = (Field) annotation;
                    String value4 = field.value();
                    boolean encoded3 = field.encoded();
                    this.f15355f = true;
                    Class g7 = Utils.g(type);
                    if (!Iterable.class.isAssignableFrom(g7)) {
                        return g7.isArray() ? new ParameterHandler.Field(value4, this.f15350a.stringConverter(boxIfPrimitive(g7.getComponentType()), annotationArr), encoded3).b() : new ParameterHandler.Field(value4, this.f15350a.stringConverter(type, annotationArr), encoded3);
                    } else if (type instanceof ParameterizedType) {
                        return new ParameterHandler.Field(value4, this.f15350a.stringConverter(Utils.f(0, (ParameterizedType) type), annotationArr), encoded3).c();
                    } else {
                        throw Utils.m(this.f15351b, i2, g7.getSimpleName() + " must include generic type (e.g., " + g7.getSimpleName() + "<String>)", new Object[0]);
                    }
                }
                throw Utils.m(this.f15351b, i2, "@Field parameters can only be used with form encoding.", new Object[0]);
            } else if (annotation instanceof FieldMap) {
                validateResolvableType(i2, type);
                if (this.f15365p) {
                    Class g8 = Utils.g(type);
                    if (Map.class.isAssignableFrom(g8)) {
                        Type h4 = Utils.h(type, g8, Map.class);
                        if (h4 instanceof ParameterizedType) {
                            ParameterizedType parameterizedType3 = (ParameterizedType) h4;
                            Type f4 = Utils.f(0, parameterizedType3);
                            if (String.class == f4) {
                                Converter stringConverter = this.f15350a.stringConverter(Utils.f(1, parameterizedType3), annotationArr);
                                this.f15355f = true;
                                return new ParameterHandler.FieldMap(this.f15351b, i2, stringConverter, ((FieldMap) annotation).encoded());
                            }
                            throw Utils.m(this.f15351b, i2, "@FieldMap keys must be of type String: " + f4, new Object[0]);
                        }
                        throw Utils.m(this.f15351b, i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw Utils.m(this.f15351b, i2, "@FieldMap parameter type must be Map.", new Object[0]);
                }
                throw Utils.m(this.f15351b, i2, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
            } else if (annotation instanceof Part) {
                validateResolvableType(i2, type);
                if (this.f15366q) {
                    Part part = (Part) annotation;
                    this.f15356g = true;
                    String value5 = part.value();
                    Class g9 = Utils.g(type);
                    if (value5.isEmpty()) {
                        if (Iterable.class.isAssignableFrom(g9)) {
                            if (!(type instanceof ParameterizedType)) {
                                throw Utils.m(this.f15351b, i2, g9.getSimpleName() + " must include generic type (e.g., " + g9.getSimpleName() + "<String>)", new Object[0]);
                            } else {
                                if (MultipartBody.Part.class.isAssignableFrom(Utils.g(Utils.f(0, (ParameterizedType) type)))) {
                                    return ParameterHandler.RawPart.f15345a.c();
                                }
                                throw Utils.m(this.f15351b, i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                            }
                        } else if (g9.isArray()) {
                            if (MultipartBody.Part.class.isAssignableFrom(g9.getComponentType())) {
                                return ParameterHandler.RawPart.f15345a.b();
                            }
                            throw Utils.m(this.f15351b, i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        } else if (MultipartBody.Part.class.isAssignableFrom(g9)) {
                            return ParameterHandler.RawPart.f15345a;
                        } else {
                            throw Utils.m(this.f15351b, i2, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                        }
                    }
                    Headers of = Headers.of(HttpHeaders.CONTENT_DISPOSITION, "form-data; name=\"" + value5 + "\"", "Content-Transfer-Encoding", part.encoding());
                    if (Iterable.class.isAssignableFrom(g9)) {
                        if (!(type instanceof ParameterizedType)) {
                            throw Utils.m(this.f15351b, i2, g9.getSimpleName() + " must include generic type (e.g., " + g9.getSimpleName() + "<String>)", new Object[0]);
                        } else {
                            Type f5 = Utils.f(0, (ParameterizedType) type);
                            if (MultipartBody.Part.class.isAssignableFrom(Utils.g(f5))) {
                                throw Utils.m(this.f15351b, i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                            }
                            return new ParameterHandler.Part(this.f15351b, i2, of, this.f15350a.requestBodyConverter(f5, annotationArr, this.f15352c)).c();
                        }
                    }
                    if (!g9.isArray()) {
                        if (MultipartBody.Part.class.isAssignableFrom(g9)) {
                            throw Utils.m(this.f15351b, i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        }
                        return new ParameterHandler.Part(this.f15351b, i2, of, this.f15350a.requestBodyConverter(type, annotationArr, this.f15352c));
                    }
                    Class<?> boxIfPrimitive = boxIfPrimitive(g9.getComponentType());
                    if (MultipartBody.Part.class.isAssignableFrom(boxIfPrimitive)) {
                        throw Utils.m(this.f15351b, i2, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                    return new ParameterHandler.Part(this.f15351b, i2, of, this.f15350a.requestBodyConverter(boxIfPrimitive, annotationArr, this.f15352c)).b();
                }
                throw Utils.m(this.f15351b, i2, "@Part parameters can only be used with multipart encoding.", new Object[0]);
            } else if (annotation instanceof PartMap) {
                validateResolvableType(i2, type);
                if (this.f15366q) {
                    this.f15356g = true;
                    Class g10 = Utils.g(type);
                    if (Map.class.isAssignableFrom(g10)) {
                        Type h5 = Utils.h(type, g10, Map.class);
                        if (h5 instanceof ParameterizedType) {
                            ParameterizedType parameterizedType4 = (ParameterizedType) h5;
                            Type f6 = Utils.f(0, parameterizedType4);
                            if (String.class == f6) {
                                Type f7 = Utils.f(1, parameterizedType4);
                                if (MultipartBody.Part.class.isAssignableFrom(Utils.g(f7))) {
                                    throw Utils.m(this.f15351b, i2, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                                }
                                return new ParameterHandler.PartMap(this.f15351b, i2, this.f15350a.requestBodyConverter(f7, annotationArr, this.f15352c), ((PartMap) annotation).encoding());
                            }
                            throw Utils.m(this.f15351b, i2, "@PartMap keys must be of type String: " + f6, new Object[0]);
                        }
                        throw Utils.m(this.f15351b, i2, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                    }
                    throw Utils.m(this.f15351b, i2, "@PartMap parameter type must be Map.", new Object[0]);
                }
                throw Utils.m(this.f15351b, i2, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
            } else if (!(annotation instanceof Body)) {
                if (annotation instanceof Tag) {
                    validateResolvableType(i2, type);
                    Class g11 = Utils.g(type);
                    for (int i3 = i2 - 1; i3 >= 0; i3--) {
                        ParameterHandler parameterHandler = this.v[i3];
                        if ((parameterHandler instanceof ParameterHandler.Tag) && ((ParameterHandler.Tag) parameterHandler).f15347a.equals(g11)) {
                            throw Utils.m(this.f15351b, i2, "@Tag type " + g11.getName() + " is duplicate of parameter #" + (i3 + 1) + " and would always overwrite its value.", new Object[0]);
                        }
                    }
                    return new ParameterHandler.Tag(g11);
                } else {
                    return null;
                }
            } else {
                validateResolvableType(i2, type);
                if (this.f15365p || this.f15366q) {
                    throw Utils.m(this.f15351b, i2, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
                }
                if (this.f15357h) {
                    throw Utils.m(this.f15351b, i2, "Multiple @Body method annotations found.", new Object[0]);
                }
                try {
                    Converter requestBodyConverter = this.f15350a.requestBodyConverter(type, annotationArr, this.f15352c);
                    this.f15357h = true;
                    return new ParameterHandler.Body(this.f15351b, i2, requestBodyConverter);
                } catch (RuntimeException e2) {
                    throw Utils.n(this.f15351b, e2, i2, "Unable to create @Body converter for %s", type);
                }
            }
        }

        private void validatePathName(int i2, String str) {
            if (!PARAM_NAME_REGEX.matcher(str).matches()) {
                throw Utils.m(this.f15351b, i2, "@Path parameter name must match %s. Found: %s", PARAM_URL_REGEX.pattern(), str);
            }
            if (!this.u.contains(str)) {
                throw Utils.m(this.f15351b, i2, "URL \"%s\" does not contain \"{%s}\".", this.f15367r, str);
            }
        }

        private void validateResolvableType(int i2, Type type) {
            if (Utils.i(type)) {
                throw Utils.m(this.f15351b, i2, "Parameter type must not include a type variable or wildcard: %s", type);
            }
        }

        RequestFactory a() {
            for (Annotation annotation : this.f15352c) {
                parseMethodAnnotation(annotation);
            }
            if (this.f15363n != null) {
                if (!this.f15364o) {
                    if (this.f15366q) {
                        throw Utils.k(this.f15351b, "Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                    }
                    if (this.f15365p) {
                        throw Utils.k(this.f15351b, "FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                    }
                }
                int length = this.f15353d.length;
                this.v = new ParameterHandler[length];
                int i2 = length - 1;
                int i3 = 0;
                while (true) {
                    boolean z = true;
                    if (i3 >= length) {
                        break;
                    }
                    ParameterHandler[] parameterHandlerArr = this.v;
                    Type type = this.f15354e[i3];
                    Annotation[] annotationArr = this.f15353d[i3];
                    if (i3 != i2) {
                        z = false;
                    }
                    parameterHandlerArr[i3] = parseParameter(i3, type, annotationArr, z);
                    i3++;
                }
                if (this.f15367r != null || this.f15362m) {
                    boolean z2 = this.f15365p;
                    if (z2 || this.f15366q || this.f15364o || !this.f15357h) {
                        if (!z2 || this.f15355f) {
                            if (!this.f15366q || this.f15356g) {
                                return new RequestFactory(this);
                            }
                            throw Utils.k(this.f15351b, "Multipart method must contain at least one @Part.", new Object[0]);
                        }
                        throw Utils.k(this.f15351b, "Form-encoded method must contain at least one @Field.", new Object[0]);
                    }
                    throw Utils.k(this.f15351b, "Non-body HTTP method cannot contain @Body.", new Object[0]);
                }
                throw Utils.k(this.f15351b, "Missing either @%s URL or @Url parameter.", this.f15363n);
            }
            throw Utils.k(this.f15351b, "HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
        }
    }

    RequestFactory(Builder builder) {
        this.method = builder.f15351b;
        this.baseUrl = builder.f15350a.f15371b;
        this.f15348a = builder.f15363n;
        this.relativeUrl = builder.f15367r;
        this.headers = builder.f15368s;
        this.contentType = builder.f15369t;
        this.hasBody = builder.f15364o;
        this.isFormEncoded = builder.f15365p;
        this.isMultipart = builder.f15366q;
        this.parameterHandlers = builder.v;
        this.f15349b = builder.w;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RequestFactory b(Retrofit retrofit, Method method) {
        return new Builder(retrofit, method).a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request a(Object[] objArr) {
        ParameterHandler<?>[] parameterHandlerArr = this.parameterHandlers;
        int length = objArr.length;
        if (length != parameterHandlerArr.length) {
            throw new IllegalArgumentException("Argument count (" + length + ") doesn't match expected count (" + parameterHandlerArr.length + ")");
        }
        RequestBuilder requestBuilder = new RequestBuilder(this.f15348a, this.baseUrl, this.relativeUrl, this.headers, this.contentType, this.hasBody, this.isFormEncoded, this.isMultipart);
        if (this.f15349b) {
            length--;
        }
        ArrayList arrayList = new ArrayList(length);
        for (int i2 = 0; i2 < length; i2++) {
            arrayList.add(objArr[i2]);
            parameterHandlerArr[i2].a(requestBuilder, objArr[i2]);
        }
        return requestBuilder.i().tag(Invocation.class, new Invocation(this.method, arrayList)).build();
    }
}
