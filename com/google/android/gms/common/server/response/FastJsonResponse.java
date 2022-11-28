package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.common.util.MapUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@ShowFirstParty
@KeepForSdk
/* loaded from: classes.dex */
public abstract class FastJsonResponse {

    @VisibleForTesting
    @SafeParcelable.Class(creator = "FieldCreator")
    @ShowFirstParty
    @KeepForSdk
    /* loaded from: classes.dex */
    public static class Field<I, O> extends AbstractSafeParcelable {
        public static final zaj CREATOR = new zaj();
        @SafeParcelable.Field(getter = "getTypeIn", id = 2)

        /* renamed from: a  reason: collision with root package name */
        protected final int f5802a;
        @SafeParcelable.Field(getter = "isTypeInArray", id = 3)

        /* renamed from: b  reason: collision with root package name */
        protected final boolean f5803b;
        @SafeParcelable.Field(getter = "getTypeOut", id = 4)

        /* renamed from: c  reason: collision with root package name */
        protected final int f5804c;
        @SafeParcelable.Field(getter = "isTypeOutArray", id = 5)

        /* renamed from: d  reason: collision with root package name */
        protected final boolean f5805d;
        @NonNull
        @SafeParcelable.Field(getter = "getOutputFieldName", id = 6)

        /* renamed from: e  reason: collision with root package name */
        protected final String f5806e;
        @SafeParcelable.Field(getter = "getSafeParcelableFieldId", id = 7)

        /* renamed from: f  reason: collision with root package name */
        protected final int f5807f;
        @Nullable

        /* renamed from: g  reason: collision with root package name */
        protected final Class f5808g;
        @Nullable
        @SafeParcelable.Field(getter = "getConcreteTypeName", id = 8)

        /* renamed from: h  reason: collision with root package name */
        protected final String f5809h;
        @SafeParcelable.VersionField(getter = "getVersionCode", id = 1)
        private final int zai;
        private zan zaj;
        @Nullable
        @SafeParcelable.Field(getter = "getWrappedConverter", id = 9, type = "com.google.android.gms.common.server.converter.ConverterWrapper")
        private FieldConverter<I, O> zak;

        /* JADX INFO: Access modifiers changed from: package-private */
        @SafeParcelable.Constructor
        public Field(@SafeParcelable.Param(id = 1) int i2, @SafeParcelable.Param(id = 2) int i3, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) int i4, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) String str, @SafeParcelable.Param(id = 7) int i5, @Nullable @SafeParcelable.Param(id = 8) String str2, @Nullable @SafeParcelable.Param(id = 9) com.google.android.gms.common.server.converter.zaa zaaVar) {
            this.zai = i2;
            this.f5802a = i3;
            this.f5803b = z;
            this.f5804c = i4;
            this.f5805d = z2;
            this.f5806e = str;
            this.f5807f = i5;
            if (str2 == null) {
                this.f5808g = null;
                this.f5809h = null;
            } else {
                this.f5808g = SafeParcelResponse.class;
                this.f5809h = str2;
            }
            if (zaaVar == null) {
                this.zak = null;
            } else {
                this.zak = (FieldConverter<I, O>) zaaVar.zab();
            }
        }

        protected Field(int i2, boolean z, int i3, boolean z2, @NonNull String str, int i4, @Nullable Class cls, @Nullable FieldConverter fieldConverter) {
            this.zai = 1;
            this.f5802a = i2;
            this.f5803b = z;
            this.f5804c = i3;
            this.f5805d = z2;
            this.f5806e = str;
            this.f5807f = i4;
            this.f5808g = cls;
            this.f5809h = cls == null ? null : cls.getCanonicalName();
            this.zak = fieldConverter;
        }

        @NonNull
        @VisibleForTesting
        @KeepForSdk
        public static Field<byte[], byte[]> forBase64(@NonNull String str, int i2) {
            return new Field<>(8, false, 8, false, str, i2, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<Boolean, Boolean> forBoolean(@NonNull String str, int i2) {
            return new Field<>(6, false, 6, false, str, i2, null, null);
        }

        @NonNull
        @KeepForSdk
        public static <T extends FastJsonResponse> Field<T, T> forConcreteType(@NonNull String str, int i2, @NonNull Class<T> cls) {
            return new Field<>(11, false, 11, false, str, i2, cls, null);
        }

        @NonNull
        @KeepForSdk
        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> forConcreteTypeArray(@NonNull String str, int i2, @NonNull Class<T> cls) {
            return new Field<>(11, true, 11, true, str, i2, cls, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<Double, Double> forDouble(@NonNull String str, int i2) {
            return new Field<>(4, false, 4, false, str, i2, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<Float, Float> forFloat(@NonNull String str, int i2) {
            return new Field<>(3, false, 3, false, str, i2, null, null);
        }

        @NonNull
        @VisibleForTesting
        @KeepForSdk
        public static Field<Integer, Integer> forInteger(@NonNull String str, int i2) {
            return new Field<>(0, false, 0, false, str, i2, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<Long, Long> forLong(@NonNull String str, int i2) {
            return new Field<>(2, false, 2, false, str, i2, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<String, String> forString(@NonNull String str, int i2) {
            return new Field<>(7, false, 7, false, str, i2, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<HashMap<String, String>, HashMap<String, String>> forStringMap(@NonNull String str, int i2) {
            return new Field<>(10, false, 10, false, str, i2, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field<ArrayList<String>, ArrayList<String>> forStrings(@NonNull String str, int i2) {
            return new Field<>(7, true, 7, true, str, i2, null, null);
        }

        @NonNull
        @KeepForSdk
        public static Field withConverter(@NonNull String str, int i2, @NonNull FieldConverter<?, ?> fieldConverter, boolean z) {
            fieldConverter.zaa();
            fieldConverter.zab();
            return new Field(7, z, 0, false, str, i2, null, fieldConverter);
        }

        @Nullable
        final com.google.android.gms.common.server.converter.zaa a() {
            FieldConverter<I, O> fieldConverter = this.zak;
            if (fieldConverter == null) {
                return null;
            }
            return com.google.android.gms.common.server.converter.zaa.zaa(fieldConverter);
        }

        @Nullable
        final String c() {
            String str = this.f5809h;
            if (str == null) {
                return null;
            }
            return str;
        }

        @KeepForSdk
        public int getSafeParcelableFieldId() {
            return this.f5807f;
        }

        @NonNull
        public final String toString() {
            Objects.ToStringHelper add = Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zai)).add("typeIn", Integer.valueOf(this.f5802a)).add("typeInArray", Boolean.valueOf(this.f5803b)).add("typeOut", Integer.valueOf(this.f5804c)).add("typeOutArray", Boolean.valueOf(this.f5805d)).add("outputFieldName", this.f5806e).add("safeParcelFieldId", Integer.valueOf(this.f5807f)).add("concreteTypeName", c());
            Class cls = this.f5808g;
            if (cls != null) {
                add.add("concreteType.class", cls.getCanonicalName());
            }
            FieldConverter<I, O> fieldConverter = this.zak;
            if (fieldConverter != null) {
                add.add("converterName", fieldConverter.getClass().getCanonicalName());
            }
            return add.toString();
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NonNull Parcel parcel, int i2) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, this.zai);
            SafeParcelWriter.writeInt(parcel, 2, this.f5802a);
            SafeParcelWriter.writeBoolean(parcel, 3, this.f5803b);
            SafeParcelWriter.writeInt(parcel, 4, this.f5804c);
            SafeParcelWriter.writeBoolean(parcel, 5, this.f5805d);
            SafeParcelWriter.writeString(parcel, 6, this.f5806e, false);
            SafeParcelWriter.writeInt(parcel, 7, getSafeParcelableFieldId());
            SafeParcelWriter.writeString(parcel, 8, c(), false);
            SafeParcelWriter.writeParcelable(parcel, 9, a(), i2, false);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }

        @NonNull
        public final Field<I, O> zab() {
            return new Field<>(this.zai, this.f5802a, this.f5803b, this.f5804c, this.f5805d, this.f5806e, this.f5807f, this.f5809h, a());
        }

        @NonNull
        public final FastJsonResponse zad() {
            Preconditions.checkNotNull(this.f5808g);
            Class cls = this.f5808g;
            if (cls == SafeParcelResponse.class) {
                Preconditions.checkNotNull(this.f5809h);
                Preconditions.checkNotNull(this.zaj, "The field mapping dictionary must be set if the concrete type is a SafeParcelResponse object.");
                return new SafeParcelResponse(this.zaj, this.f5809h);
            }
            return (FastJsonResponse) cls.newInstance();
        }

        @NonNull
        public final O zae(@Nullable I i2) {
            Preconditions.checkNotNull(this.zak);
            return (O) Preconditions.checkNotNull(this.zak.zac(i2));
        }

        @NonNull
        public final I zaf(@NonNull O o2) {
            Preconditions.checkNotNull(this.zak);
            return this.zak.zad(o2);
        }

        @NonNull
        public final Map<String, Field<?, ?>> zah() {
            Preconditions.checkNotNull(this.f5809h);
            Preconditions.checkNotNull(this.zaj);
            return (Map) Preconditions.checkNotNull(this.zaj.zab(this.f5809h));
        }

        public final void zai(zan zanVar) {
            this.zaj = zanVar;
        }

        public final boolean zaj() {
            return this.zak != null;
        }
    }

    @ShowFirstParty
    /* loaded from: classes.dex */
    public interface FieldConverter<I, O> {
        int zaa();

        int zab();

        @Nullable
        O zac(@NonNull I i2);

        @NonNull
        I zad(@NonNull O o2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NonNull
    public static final Object j(@NonNull Field field, @Nullable Object obj) {
        return field.zak != null ? field.zaf(obj) : obj;
    }

    private final <I, O> void zaE(Field<I, O> field, @Nullable I i2) {
        String str = field.f5806e;
        O zae = field.zae(i2);
        int i3 = field.f5804c;
        switch (i3) {
            case 0:
                if (zae != null) {
                    e(field, str, ((Integer) zae).intValue());
                    return;
                } else {
                    zaG(str);
                    return;
                }
            case 1:
                m(field, str, (BigInteger) zae);
                return;
            case 2:
                if (zae != null) {
                    f(field, str, ((Long) zae).longValue());
                    return;
                } else {
                    zaG(str);
                    return;
                }
            case 3:
            default:
                StringBuilder sb = new StringBuilder(44);
                sb.append("Unsupported type for conversion: ");
                sb.append(i3);
                throw new IllegalStateException(sb.toString());
            case 4:
                if (zae != null) {
                    p(field, str, ((Double) zae).doubleValue());
                    return;
                } else {
                    zaG(str);
                    return;
                }
            case 5:
                k(field, str, (BigDecimal) zae);
                return;
            case 6:
                if (zae != null) {
                    c(field, str, ((Boolean) zae).booleanValue());
                    return;
                } else {
                    zaG(str);
                    return;
                }
            case 7:
                g(field, str, (String) zae);
                return;
            case 8:
            case 9:
                if (zae != null) {
                    d(field, str, (byte[]) zae);
                    return;
                } else {
                    zaG(str);
                    return;
                }
        }
    }

    private static final void zaF(StringBuilder sb, Field field, Object obj) {
        String fastJsonResponse;
        int i2 = field.f5802a;
        if (i2 == 11) {
            Class cls = field.f5808g;
            Preconditions.checkNotNull(cls);
            fastJsonResponse = ((FastJsonResponse) cls.cast(obj)).toString();
        } else if (i2 != 7) {
            sb.append(obj);
            return;
        } else {
            fastJsonResponse = "\"";
            sb.append("\"");
            sb.append(JsonUtils.escapeString((String) obj));
        }
        sb.append(fastJsonResponse);
    }

    private static final <O> void zaG(String str) {
        if (Log.isLoggable("FastJsonResponse", 6)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 58);
            sb.append("Output field (");
            sb.append(str);
            sb.append(") has a null value, but expected a primitive");
            Log.e("FastJsonResponse", sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    @KeepForSdk
    public Object a(@NonNull Field field) {
        String str = field.f5806e;
        if (field.f5808g != null) {
            Preconditions.checkState(getValueObject(str) == null, "Concrete field shouldn't be value object: %s", field.f5806e);
            try {
                char upperCase = Character.toUpperCase(str.charAt(0));
                String substring = str.substring(1);
                StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 4);
                sb.append("get");
                sb.append(upperCase);
                sb.append(substring);
                return getClass().getMethod(sb.toString(), new Class[0]).invoke(this, new Object[0]);
            } catch (Exception e2) {
                throw new RuntimeException(e2);
            }
        }
        return getValueObject(str);
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeArrayInternal(@NonNull Field field, @NonNull String str, @Nullable ArrayList<T> arrayList) {
        throw new UnsupportedOperationException("Concrete type array not supported");
    }

    @KeepForSdk
    public <T extends FastJsonResponse> void addConcreteTypeInternal(@NonNull Field field, @NonNull String str, @NonNull T t2) {
        throw new UnsupportedOperationException("Concrete type not supported");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @KeepForSdk
    public boolean b(@NonNull Field field) {
        if (field.f5804c == 11) {
            if (field.f5805d) {
                throw new UnsupportedOperationException("Concrete type arrays not supported");
            }
            throw new UnsupportedOperationException("Concrete types not supported");
        }
        return isPrimitiveFieldSet(field.f5806e);
    }

    @KeepForSdk
    protected void c(@NonNull Field field, @NonNull String str, boolean z) {
        throw new UnsupportedOperationException("Boolean not supported");
    }

    @KeepForSdk
    protected void d(@NonNull Field field, @NonNull String str, @Nullable byte[] bArr) {
        throw new UnsupportedOperationException("byte[] not supported");
    }

    @KeepForSdk
    protected void e(@NonNull Field field, @NonNull String str, int i2) {
        throw new UnsupportedOperationException("Integer not supported");
    }

    @KeepForSdk
    protected void f(@NonNull Field field, @NonNull String str, long j2) {
        throw new UnsupportedOperationException("Long not supported");
    }

    @KeepForSdk
    protected void g(@NonNull Field field, @NonNull String str, @Nullable String str2) {
        throw new UnsupportedOperationException("String not supported");
    }

    @NonNull
    @KeepForSdk
    public abstract Map<String, Field<?, ?>> getFieldMappings();

    @Nullable
    @KeepForSdk
    protected abstract Object getValueObject(@NonNull String str);

    @KeepForSdk
    protected void h(@NonNull Field field, @NonNull String str, @Nullable Map map) {
        throw new UnsupportedOperationException("String map not supported");
    }

    @KeepForSdk
    protected void i(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("String list not supported");
    }

    @KeepForSdk
    protected abstract boolean isPrimitiveFieldSet(@NonNull String str);

    protected void k(@NonNull Field field, @NonNull String str, @Nullable BigDecimal bigDecimal) {
        throw new UnsupportedOperationException("BigDecimal not supported");
    }

    protected void l(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("BigDecimal list not supported");
    }

    protected void m(@NonNull Field field, @NonNull String str, @Nullable BigInteger bigInteger) {
        throw new UnsupportedOperationException("BigInteger not supported");
    }

    protected void n(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("BigInteger list not supported");
    }

    protected void o(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("Boolean list not supported");
    }

    protected void p(@NonNull Field field, @NonNull String str, double d2) {
        throw new UnsupportedOperationException("Double not supported");
    }

    protected void q(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("Double list not supported");
    }

    protected void r(@NonNull Field field, @NonNull String str, float f2) {
        throw new UnsupportedOperationException("Float not supported");
    }

    protected void s(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("Float list not supported");
    }

    protected void t(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("Integer list not supported");
    }

    @NonNull
    @KeepForSdk
    public String toString() {
        String str;
        String encode;
        Map<String, Field<?, ?>> fieldMappings = getFieldMappings();
        StringBuilder sb = new StringBuilder(100);
        for (String str2 : fieldMappings.keySet()) {
            Field<?, ?> field = fieldMappings.get(str2);
            if (b(field)) {
                Object j2 = j(field, a(field));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"");
                sb.append(str2);
                sb.append("\":");
                if (j2 != null) {
                    switch (field.f5804c) {
                        case 8:
                            sb.append("\"");
                            encode = Base64Utils.encode((byte[]) j2);
                            sb.append(encode);
                            sb.append("\"");
                            break;
                        case 9:
                            sb.append("\"");
                            encode = Base64Utils.encodeUrlSafe((byte[]) j2);
                            sb.append(encode);
                            sb.append("\"");
                            break;
                        case 10:
                            MapUtils.writeStringMapToJson(sb, (HashMap) j2);
                            break;
                        default:
                            if (field.f5803b) {
                                ArrayList arrayList = (ArrayList) j2;
                                sb.append("[");
                                int size = arrayList.size();
                                for (int i2 = 0; i2 < size; i2++) {
                                    if (i2 > 0) {
                                        sb.append(",");
                                    }
                                    Object obj = arrayList.get(i2);
                                    if (obj != null) {
                                        zaF(sb, field, obj);
                                    }
                                }
                                str = "]";
                                break;
                            } else {
                                zaF(sb, field, j2);
                                break;
                            }
                    }
                } else {
                    str = "null";
                }
                sb.append(str);
            }
        }
        sb.append(sb.length() > 0 ? "}" : "{}");
        return sb.toString();
    }

    protected void u(@NonNull Field field, @NonNull String str, @Nullable ArrayList arrayList) {
        throw new UnsupportedOperationException("Long list not supported");
    }

    public final <O> void zaA(@NonNull Field<String, O> field, @Nullable String str) {
        if (((Field) field).zak != null) {
            zaE(field, str);
        } else {
            g(field, field.f5806e, str);
        }
    }

    public final <O> void zaB(@NonNull Field<Map<String, String>, O> field, @Nullable Map<String, String> map) {
        if (((Field) field).zak != null) {
            zaE(field, map);
        } else {
            h(field, field.f5806e, map);
        }
    }

    public final <O> void zaC(@NonNull Field<ArrayList<String>, O> field, @Nullable ArrayList<String> arrayList) {
        if (((Field) field).zak != null) {
            zaE(field, arrayList);
        } else {
            i(field, field.f5806e, arrayList);
        }
    }

    public final <O> void zaa(@NonNull Field<BigDecimal, O> field, @Nullable BigDecimal bigDecimal) {
        if (((Field) field).zak != null) {
            zaE(field, bigDecimal);
        } else {
            k(field, field.f5806e, bigDecimal);
        }
    }

    public final <O> void zac(@NonNull Field<ArrayList<BigDecimal>, O> field, @Nullable ArrayList<BigDecimal> arrayList) {
        if (((Field) field).zak != null) {
            zaE(field, arrayList);
        } else {
            l(field, field.f5806e, arrayList);
        }
    }

    public final <O> void zae(@NonNull Field<BigInteger, O> field, @Nullable BigInteger bigInteger) {
        if (((Field) field).zak != null) {
            zaE(field, bigInteger);
        } else {
            m(field, field.f5806e, bigInteger);
        }
    }

    public final <O> void zag(@NonNull Field<ArrayList<BigInteger>, O> field, @Nullable ArrayList<BigInteger> arrayList) {
        if (((Field) field).zak != null) {
            zaE(field, arrayList);
        } else {
            n(field, field.f5806e, arrayList);
        }
    }

    public final <O> void zai(@NonNull Field<Boolean, O> field, boolean z) {
        if (((Field) field).zak != null) {
            zaE(field, Boolean.valueOf(z));
        } else {
            c(field, field.f5806e, z);
        }
    }

    public final <O> void zaj(@NonNull Field<ArrayList<Boolean>, O> field, @Nullable ArrayList<Boolean> arrayList) {
        if (((Field) field).zak != null) {
            zaE(field, arrayList);
        } else {
            o(field, field.f5806e, arrayList);
        }
    }

    public final <O> void zal(@NonNull Field<byte[], O> field, @Nullable byte[] bArr) {
        if (((Field) field).zak != null) {
            zaE(field, bArr);
        } else {
            d(field, field.f5806e, bArr);
        }
    }

    public final <O> void zam(@NonNull Field<Double, O> field, double d2) {
        if (((Field) field).zak != null) {
            zaE(field, Double.valueOf(d2));
        } else {
            p(field, field.f5806e, d2);
        }
    }

    public final <O> void zao(@NonNull Field<ArrayList<Double>, O> field, @Nullable ArrayList<Double> arrayList) {
        if (((Field) field).zak != null) {
            zaE(field, arrayList);
        } else {
            q(field, field.f5806e, arrayList);
        }
    }

    public final <O> void zaq(@NonNull Field<Float, O> field, float f2) {
        if (((Field) field).zak != null) {
            zaE(field, Float.valueOf(f2));
        } else {
            r(field, field.f5806e, f2);
        }
    }

    public final <O> void zas(@NonNull Field<ArrayList<Float>, O> field, @Nullable ArrayList<Float> arrayList) {
        if (((Field) field).zak != null) {
            zaE(field, arrayList);
        } else {
            s(field, field.f5806e, arrayList);
        }
    }

    public final <O> void zau(@NonNull Field<Integer, O> field, int i2) {
        if (((Field) field).zak != null) {
            zaE(field, Integer.valueOf(i2));
        } else {
            e(field, field.f5806e, i2);
        }
    }

    public final <O> void zav(@NonNull Field<ArrayList<Integer>, O> field, @Nullable ArrayList<Integer> arrayList) {
        if (((Field) field).zak != null) {
            zaE(field, arrayList);
        } else {
            t(field, field.f5806e, arrayList);
        }
    }

    public final <O> void zax(@NonNull Field<Long, O> field, long j2) {
        if (((Field) field).zak != null) {
            zaE(field, Long.valueOf(j2));
        } else {
            f(field, field.f5806e, j2);
        }
    }

    public final <O> void zay(@NonNull Field<ArrayList<Long>, O> field, @Nullable ArrayList<Long> arrayList) {
        if (((Field) field).zak != null) {
            zaE(field, arrayList);
        } else {
            u(field, field.f5806e, arrayList);
        }
    }
}
