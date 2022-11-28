package okhttp3.tls.internal.der;

import java.math.BigInteger;
import java.net.ProtocolException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import okhttp3.tls.internal.der.BasicDerAdapter;
import okhttp3.tls.internal.der.DerAdapter;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Adapters {
    @NotNull
    private static final DerAdapter<AnyValue> ANY_VALUE;
    @NotNull
    private static final BasicDerAdapter<BitString> BIT_STRING;
    @NotNull
    private static final BasicDerAdapter<Boolean> BOOLEAN;
    @NotNull
    private static final BasicDerAdapter<Long> GENERALIZED_TIME;
    @NotNull
    private static final BasicDerAdapter<String> IA5_STRING;
    @NotNull
    public static final Adapters INSTANCE = new Adapters();
    @NotNull
    private static final BasicDerAdapter<BigInteger> INTEGER_AS_BIG_INTEGER;
    @NotNull
    private static final BasicDerAdapter<Long> INTEGER_AS_LONG;
    @NotNull
    private static final BasicDerAdapter<Unit> NULL;
    @NotNull
    private static final BasicDerAdapter<String> OBJECT_IDENTIFIER;
    @NotNull
    private static final BasicDerAdapter<ByteString> OCTET_STRING;
    @NotNull
    private static final BasicDerAdapter<String> PRINTABLE_STRING;
    @NotNull
    private static final BasicDerAdapter<Long> UTC_TIME;
    @NotNull
    private static final BasicDerAdapter<String> UTF8_STRING;
    @NotNull
    private static final List<Pair<KClass<? extends Object>, DerAdapter<? extends Object>>> defaultAnyChoices;

    static {
        List<Pair<KClass<? extends Object>, DerAdapter<? extends Object>>> listOf;
        BasicDerAdapter<Boolean> basicDerAdapter = new BasicDerAdapter<>("BOOLEAN", 0, 1L, new BasicDerAdapter.Codec<Boolean>() { // from class: okhttp3.tls.internal.der.Adapters$BOOLEAN$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @NotNull
            public Boolean decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return Boolean.valueOf(reader.readBoolean());
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public /* bridge */ /* synthetic */ void encode(DerWriter derWriter, Boolean bool) {
                encode(derWriter, bool.booleanValue());
            }

            public void encode(@NotNull DerWriter writer, boolean z) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                writer.writeBoolean(z);
            }
        }, false, null, false, 112, null);
        BOOLEAN = basicDerAdapter;
        INTEGER_AS_LONG = new BasicDerAdapter<>("INTEGER", 0, 2L, new BasicDerAdapter.Codec<Long>() { // from class: okhttp3.tls.internal.der.Adapters$INTEGER_AS_LONG$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @NotNull
            public Long decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return Long.valueOf(reader.readLong());
            }

            public void encode(@NotNull DerWriter writer, long j2) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                writer.writeLong(j2);
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public /* bridge */ /* synthetic */ void encode(DerWriter derWriter, Long l2) {
                encode(derWriter, l2.longValue());
            }
        }, false, null, false, 112, null);
        BasicDerAdapter<BigInteger> basicDerAdapter2 = new BasicDerAdapter<>("INTEGER", 0, 2L, new BasicDerAdapter.Codec<BigInteger>() { // from class: okhttp3.tls.internal.der.Adapters$INTEGER_AS_BIG_INTEGER$1
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @NotNull
            public BigInteger decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return reader.readBigInteger();
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public void encode(@NotNull DerWriter writer, @NotNull BigInteger value) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.writeBigInteger(value);
            }
        }, false, null, false, 112, null);
        INTEGER_AS_BIG_INTEGER = basicDerAdapter2;
        BasicDerAdapter<BitString> basicDerAdapter3 = new BasicDerAdapter<>("BIT STRING", 0, 3L, new BasicDerAdapter.Codec<BitString>() { // from class: okhttp3.tls.internal.der.Adapters$BIT_STRING$1
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @NotNull
            public BitString decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return reader.readBitString();
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public void encode(@NotNull DerWriter writer, @NotNull BitString value) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.writeBitString(value);
            }
        }, false, null, false, 112, null);
        BIT_STRING = basicDerAdapter3;
        BasicDerAdapter<ByteString> basicDerAdapter4 = new BasicDerAdapter<>("OCTET STRING", 0, 4L, new BasicDerAdapter.Codec<ByteString>() { // from class: okhttp3.tls.internal.der.Adapters$OCTET_STRING$1
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @NotNull
            public ByteString decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return reader.readOctetString();
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public void encode(@NotNull DerWriter writer, @NotNull ByteString value) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.writeOctetString(value);
            }
        }, false, null, false, 112, null);
        OCTET_STRING = basicDerAdapter4;
        BasicDerAdapter<Unit> basicDerAdapter5 = new BasicDerAdapter<>("NULL", 0, 5L, new BasicDerAdapter.Codec<Unit>() { // from class: okhttp3.tls.internal.der.Adapters$NULL$1
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @Nullable
            public Unit decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return null;
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public void encode(@NotNull DerWriter writer, @Nullable Unit unit) {
                Intrinsics.checkNotNullParameter(writer, "writer");
            }
        }, false, null, false, 112, null);
        NULL = basicDerAdapter5;
        BasicDerAdapter<String> basicDerAdapter6 = new BasicDerAdapter<>("OBJECT IDENTIFIER", 0, 6L, new BasicDerAdapter.Codec<String>() { // from class: okhttp3.tls.internal.der.Adapters$OBJECT_IDENTIFIER$1
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @NotNull
            public String decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return reader.readObjectIdentifier();
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public void encode(@NotNull DerWriter writer, @NotNull String value) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.writeObjectIdentifier(value);
            }
        }, false, null, false, 112, null);
        OBJECT_IDENTIFIER = basicDerAdapter6;
        BasicDerAdapter<String> basicDerAdapter7 = new BasicDerAdapter<>("UTF8", 0, 12L, new BasicDerAdapter.Codec<String>() { // from class: okhttp3.tls.internal.der.Adapters$UTF8_STRING$1
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @NotNull
            public String decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return reader.readUtf8String();
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public void encode(@NotNull DerWriter writer, @NotNull String value) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.writeUtf8(value);
            }
        }, false, null, false, 112, null);
        UTF8_STRING = basicDerAdapter7;
        BasicDerAdapter<String> basicDerAdapter8 = new BasicDerAdapter<>("PRINTABLE STRING", 0, 19L, new BasicDerAdapter.Codec<String>() { // from class: okhttp3.tls.internal.der.Adapters$PRINTABLE_STRING$1
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @NotNull
            public String decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return reader.readUtf8String();
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public void encode(@NotNull DerWriter writer, @NotNull String value) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.writeUtf8(value);
            }
        }, false, null, false, 112, null);
        PRINTABLE_STRING = basicDerAdapter8;
        BasicDerAdapter<String> basicDerAdapter9 = new BasicDerAdapter<>("IA5 STRING", 0, 22L, new BasicDerAdapter.Codec<String>() { // from class: okhttp3.tls.internal.der.Adapters$IA5_STRING$1
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @NotNull
            public String decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return reader.readUtf8String();
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public void encode(@NotNull DerWriter writer, @NotNull String value) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.writeUtf8(value);
            }
        }, false, null, false, 112, null);
        IA5_STRING = basicDerAdapter9;
        BasicDerAdapter<Long> basicDerAdapter10 = new BasicDerAdapter<>("UTC TIME", 0, 23L, new BasicDerAdapter.Codec<Long>() { // from class: okhttp3.tls.internal.der.Adapters$UTC_TIME$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @NotNull
            public Long decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return Long.valueOf(Adapters.INSTANCE.parseUtcTime$okhttp_tls(reader.readUtf8String()));
            }

            public void encode(@NotNull DerWriter writer, long j2) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                writer.writeUtf8(Adapters.INSTANCE.formatUtcTime$okhttp_tls(j2));
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public /* bridge */ /* synthetic */ void encode(DerWriter derWriter, Long l2) {
                encode(derWriter, l2.longValue());
            }
        }, false, null, false, 112, null);
        UTC_TIME = basicDerAdapter10;
        BasicDerAdapter<Long> basicDerAdapter11 = new BasicDerAdapter<>("GENERALIZED TIME", 0, 24L, new BasicDerAdapter.Codec<Long>() { // from class: okhttp3.tls.internal.der.Adapters$GENERALIZED_TIME$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            @NotNull
            public Long decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return Long.valueOf(Adapters.INSTANCE.parseGeneralizedTime$okhttp_tls(reader.readUtf8String()));
            }

            public void encode(@NotNull DerWriter writer, long j2) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                writer.writeUtf8(Adapters.INSTANCE.formatGeneralizedTime$okhttp_tls(j2));
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public /* bridge */ /* synthetic */ void encode(DerWriter derWriter, Long l2) {
                encode(derWriter, l2.longValue());
            }
        }, false, null, false, 112, null);
        GENERALIZED_TIME = basicDerAdapter11;
        DerAdapter<AnyValue> derAdapter = new DerAdapter<AnyValue>() { // from class: okhttp3.tls.internal.der.Adapters$ANY_VALUE$1
            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<List<AnyValue>> asSequenceOf(@NotNull String str, int i2, long j2) {
                return DerAdapter.DefaultImpls.asSequenceOf(this, str, i2, j2);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<List<AnyValue>> asSetOf() {
                return DerAdapter.DefaultImpls.asSetOf(this);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public AnyValue fromDer(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                if (reader.hasNext()) {
                    DerHeader derHeader = reader.peekedHeader;
                    Intrinsics.checkNotNull(derHeader);
                    reader.peekedHeader = null;
                    long j2 = reader.limit;
                    boolean z = reader.constructed;
                    long byteCount = derHeader.getLength() != -1 ? reader.getByteCount() + derHeader.getLength() : -1L;
                    if (j2 == -1 || byteCount <= j2) {
                        reader.limit = byteCount;
                        reader.constructed = derHeader.getConstructed();
                        reader.path.add("ANY");
                        try {
                            return new AnyValue(derHeader.getTagClass(), derHeader.getTag(), derHeader.getConstructed(), derHeader.getLength(), reader.readUnknown());
                        } finally {
                            reader.peekedHeader = null;
                            reader.limit = j2;
                            reader.constructed = z;
                            reader.path.remove(reader.path.size() - 1);
                        }
                    }
                    throw new ProtocolException("enclosed object too large");
                }
                throw new ProtocolException("expected a value");
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public AnyValue fromDer(@NotNull ByteString byteString) {
                return (AnyValue) DerAdapter.DefaultImpls.fromDer(this, byteString);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            public boolean matches(@NotNull DerHeader header) {
                Intrinsics.checkNotNullParameter(header, "header");
                return true;
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public ByteString toDer(@NotNull AnyValue anyValue) {
                return DerAdapter.DefaultImpls.toDer(this, anyValue);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            public void toDer(@NotNull DerWriter writer, @NotNull AnyValue value) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.write("ANY", value.getTagClass(), value.getTag(), new Adapters$ANY_VALUE$1$toDer$1(writer, value));
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<AnyValue> withExplicitBox(int i2, long j2, @Nullable Boolean bool) {
                return DerAdapter.DefaultImpls.withExplicitBox(this, i2, j2, bool);
            }
        };
        ANY_VALUE = derAdapter;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(Reflection.getOrCreateKotlinClass(Boolean.TYPE), basicDerAdapter), TuplesKt.to(Reflection.getOrCreateKotlinClass(BigInteger.class), basicDerAdapter2), TuplesKt.to(Reflection.getOrCreateKotlinClass(BitString.class), basicDerAdapter3), TuplesKt.to(Reflection.getOrCreateKotlinClass(ByteString.class), basicDerAdapter4), TuplesKt.to(Reflection.getOrCreateKotlinClass(Unit.class), basicDerAdapter5), TuplesKt.to(Reflection.getOrCreateKotlinClass(Void.class), basicDerAdapter6), TuplesKt.to(Reflection.getOrCreateKotlinClass(Void.class), basicDerAdapter7), TuplesKt.to(Reflection.getOrCreateKotlinClass(String.class), basicDerAdapter8), TuplesKt.to(Reflection.getOrCreateKotlinClass(Void.class), basicDerAdapter9), TuplesKt.to(Reflection.getOrCreateKotlinClass(Void.class), basicDerAdapter10), TuplesKt.to(Reflection.getOrCreateKotlinClass(Long.TYPE), basicDerAdapter11), TuplesKt.to(Reflection.getOrCreateKotlinClass(AnyValue.class), derAdapter)});
        defaultAnyChoices = listOf;
    }

    private Adapters() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DerAdapter any$default(Adapters adapters, Pair[] pairArr, boolean z, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            Object[] array = defaultAnyChoices.toArray(new Pair[0]);
            Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            pairArr = (Pair[]) array;
        }
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            obj = null;
        }
        return adapters.any(pairArr, z, obj);
    }

    @NotNull
    public final DerAdapter<Object> any(@NotNull final Pair<? extends KClass<?>, ? extends DerAdapter<?>>[] choices, final boolean z, @Nullable final Object obj) {
        Intrinsics.checkNotNullParameter(choices, "choices");
        return new DerAdapter<Object>() { // from class: okhttp3.tls.internal.der.Adapters$any$1
            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<List<Object>> asSequenceOf(@NotNull String str, int i2, long j2) {
                return DerAdapter.DefaultImpls.asSequenceOf(this, str, i2, j2);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<List<Object>> asSetOf() {
                return DerAdapter.DefaultImpls.asSetOf(this);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @Nullable
            public Object fromDer(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                if (!z || reader.hasNext()) {
                    DerHeader peekHeader = reader.peekHeader();
                    if (peekHeader != null) {
                        Pair[] pairArr = choices;
                        int i2 = 0;
                        int length = pairArr.length;
                        while (i2 < length) {
                            Pair pair = pairArr[i2];
                            i2++;
                            DerAdapter derAdapter = (DerAdapter) pair.component2();
                            if (derAdapter.matches(peekHeader)) {
                                return derAdapter.fromDer(reader);
                            }
                        }
                        throw new ProtocolException("expected any but was " + peekHeader + " at " + reader);
                    }
                    throw new ProtocolException(Intrinsics.stringPlus("expected a value at ", reader));
                }
                return obj;
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @Nullable
            public Object fromDer(@NotNull ByteString byteString) {
                return DerAdapter.DefaultImpls.fromDer(this, byteString);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            public boolean matches(@NotNull DerHeader header) {
                Intrinsics.checkNotNullParameter(header, "header");
                return true;
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public ByteString toDer(@Nullable Object obj2) {
                return DerAdapter.DefaultImpls.toDer(this, obj2);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            public void toDer(@NotNull DerWriter writer, @Nullable Object obj2) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                if (z && Intrinsics.areEqual(obj2, obj)) {
                    return;
                }
                Pair[] pairArr = choices;
                int i2 = 0;
                int length = pairArr.length;
                while (i2 < length) {
                    Pair pair = pairArr[i2];
                    i2++;
                    KClass kClass = (KClass) pair.component1();
                    DerAdapter derAdapter = (DerAdapter) pair.component2();
                    if (kClass.isInstance(obj2) || (obj2 == null && Intrinsics.areEqual(kClass, Reflection.getOrCreateKotlinClass(Unit.class)))) {
                        derAdapter.toDer(writer, obj2);
                        return;
                    }
                }
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<Object> withExplicitBox(int i2, long j2, @Nullable Boolean bool) {
                return DerAdapter.DefaultImpls.withExplicitBox(this, i2, j2, bool);
            }
        };
    }

    @NotNull
    public final DerAdapter<Pair<DerAdapter<?>, Object>> choice(@NotNull final DerAdapter<?>... choices) {
        Intrinsics.checkNotNullParameter(choices, "choices");
        return new DerAdapter<Pair<? extends DerAdapter<?>, ? extends Object>>() { // from class: okhttp3.tls.internal.der.Adapters$choice$1
            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<List<Pair<? extends DerAdapter<?>, ? extends Object>>> asSequenceOf(@NotNull String str, int i2, long j2) {
                return DerAdapter.DefaultImpls.asSequenceOf(this, str, i2, j2);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<List<Pair<? extends DerAdapter<?>, ? extends Object>>> asSetOf() {
                return DerAdapter.DefaultImpls.asSetOf(this);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public Pair<? extends DerAdapter<?>, ? extends Object> fromDer(@NotNull DerReader reader) {
                DerAdapter derAdapter;
                Intrinsics.checkNotNullParameter(reader, "reader");
                DerHeader peekHeader = reader.peekHeader();
                if (peekHeader != null) {
                    DerAdapter[] derAdapterArr = choices;
                    int i2 = 0;
                    int length = derAdapterArr.length;
                    while (true) {
                        if (i2 >= length) {
                            derAdapter = null;
                            break;
                        }
                        derAdapter = derAdapterArr[i2];
                        if (derAdapter.matches(peekHeader)) {
                            break;
                        }
                        i2++;
                    }
                    if (derAdapter != null) {
                        return TuplesKt.to(derAdapter, derAdapter.fromDer(reader));
                    }
                    throw new ProtocolException("expected a matching choice but was " + peekHeader + " at " + reader);
                }
                throw new ProtocolException(Intrinsics.stringPlus("expected a value at ", reader));
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public Pair<? extends DerAdapter<?>, ? extends Object> fromDer(@NotNull ByteString byteString) {
                return (Pair) DerAdapter.DefaultImpls.fromDer(this, byteString);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            public boolean matches(@NotNull DerHeader header) {
                Intrinsics.checkNotNullParameter(header, "header");
                return true;
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public ByteString toDer(@NotNull Pair<? extends DerAdapter<?>, ? extends Object> pair) {
                return DerAdapter.DefaultImpls.toDer(this, pair);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            public void toDer(@NotNull DerWriter writer, @NotNull Pair<? extends DerAdapter<?>, ? extends Object> value) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(value, "value");
                value.component1().toDer(writer, value.component2());
            }

            @NotNull
            public String toString() {
                String joinToString$default;
                joinToString$default = ArraysKt___ArraysKt.joinToString$default(choices, " OR ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
                return joinToString$default;
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<Pair<? extends DerAdapter<?>, ? extends Object>> withExplicitBox(int i2, long j2, @Nullable Boolean bool) {
                return DerAdapter.DefaultImpls.withExplicitBox(this, i2, j2, bool);
            }
        };
    }

    @NotNull
    public final String formatGeneralizedTime$okhttp_tls(long j2) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(timeZone);
        String format = simpleDateFormat.format(Long.valueOf(j2));
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(date)");
        return format;
    }

    @NotNull
    public final String formatUtcTime$okhttp_tls(long j2) {
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(timeZone);
        simpleDateFormat.set2DigitYearStart(new Date(-631152000000L));
        String format = simpleDateFormat.format(Long.valueOf(j2));
        Intrinsics.checkNotNullExpressionValue(format, "dateFormat.format(date)");
        return format;
    }

    @NotNull
    public final DerAdapter<AnyValue> getANY_VALUE() {
        return ANY_VALUE;
    }

    @NotNull
    public final BasicDerAdapter<BitString> getBIT_STRING() {
        return BIT_STRING;
    }

    @NotNull
    public final BasicDerAdapter<Boolean> getBOOLEAN() {
        return BOOLEAN;
    }

    @NotNull
    public final BasicDerAdapter<Long> getGENERALIZED_TIME() {
        return GENERALIZED_TIME;
    }

    @NotNull
    public final BasicDerAdapter<String> getIA5_STRING() {
        return IA5_STRING;
    }

    @NotNull
    public final BasicDerAdapter<BigInteger> getINTEGER_AS_BIG_INTEGER() {
        return INTEGER_AS_BIG_INTEGER;
    }

    @NotNull
    public final BasicDerAdapter<Long> getINTEGER_AS_LONG() {
        return INTEGER_AS_LONG;
    }

    @NotNull
    public final BasicDerAdapter<Unit> getNULL() {
        return NULL;
    }

    @NotNull
    public final BasicDerAdapter<String> getOBJECT_IDENTIFIER() {
        return OBJECT_IDENTIFIER;
    }

    @NotNull
    public final BasicDerAdapter<ByteString> getOCTET_STRING() {
        return OCTET_STRING;
    }

    @NotNull
    public final BasicDerAdapter<String> getPRINTABLE_STRING() {
        return PRINTABLE_STRING;
    }

    @NotNull
    public final BasicDerAdapter<Long> getUTC_TIME() {
        return UTC_TIME;
    }

    @NotNull
    public final BasicDerAdapter<String> getUTF8_STRING() {
        return UTF8_STRING;
    }

    public final long parseGeneralizedTime$okhttp_tls(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(timeZone);
        try {
            return simpleDateFormat.parse(string).getTime();
        } catch (ParseException unused) {
            throw new ProtocolException(Intrinsics.stringPlus("Failed to parse GeneralizedTime ", string));
        }
    }

    public final long parseUtcTime$okhttp_tls(@NotNull String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        TimeZone timeZone = TimeZone.getTimeZone("GMT");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss'Z'");
        simpleDateFormat.setTimeZone(timeZone);
        simpleDateFormat.set2DigitYearStart(new Date(-631152000000L));
        try {
            return simpleDateFormat.parse(string).getTime();
        } catch (ParseException unused) {
            throw new ProtocolException(Intrinsics.stringPlus("Failed to parse UTCTime ", string));
        }
    }

    @NotNull
    public final <T> BasicDerAdapter<T> sequence(@NotNull String name, @NotNull final DerAdapter<?>[] members, @NotNull final Function1<? super T, ? extends List<?>> decompose, @NotNull final Function1<? super List<?>, ? extends T> construct) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(members, "members");
        Intrinsics.checkNotNullParameter(decompose, "decompose");
        Intrinsics.checkNotNullParameter(construct, "construct");
        return new BasicDerAdapter<>(name, 0, 16L, new BasicDerAdapter.Codec<T>() { // from class: okhttp3.tls.internal.der.Adapters$sequence$codec$1
            /* JADX WARN: Type inference failed for: r4v1, types: [T, java.lang.Object] */
            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public T decode(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                return reader.withTypeHint(new Adapters$sequence$codec$1$decode$1(members, reader, construct));
            }

            @Override // okhttp3.tls.internal.der.BasicDerAdapter.Codec
            public void encode(@NotNull DerWriter writer, T t2) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                writer.withTypeHint(new Adapters$sequence$codec$1$encode$1((List) decompose.invoke(t2), members, writer));
            }
        }, false, null, false, 112, null);
    }

    @NotNull
    public final DerAdapter<Object> usingTypeHint(@NotNull final Function1<Object, ? extends DerAdapter<?>> chooser) {
        Intrinsics.checkNotNullParameter(chooser, "chooser");
        return new DerAdapter<Object>() { // from class: okhttp3.tls.internal.der.Adapters$usingTypeHint$1
            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<List<Object>> asSequenceOf(@NotNull String str, int i2, long j2) {
                return DerAdapter.DefaultImpls.asSequenceOf(this, str, i2, j2);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<List<Object>> asSetOf() {
                return DerAdapter.DefaultImpls.asSetOf(this);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @Nullable
            public Object fromDer(@NotNull DerReader reader) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                DerAdapter derAdapter = (DerAdapter) Function1.this.invoke(reader.getTypeHint());
                return derAdapter != null ? derAdapter.fromDer(reader) : reader.readUnknown();
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @Nullable
            public Object fromDer(@NotNull ByteString byteString) {
                return DerAdapter.DefaultImpls.fromDer(this, byteString);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            public boolean matches(@NotNull DerHeader header) {
                Intrinsics.checkNotNullParameter(header, "header");
                return true;
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public ByteString toDer(@Nullable Object obj) {
                return DerAdapter.DefaultImpls.toDer(this, obj);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            public void toDer(@NotNull DerWriter writer, @Nullable Object obj) {
                Intrinsics.checkNotNullParameter(writer, "writer");
                DerAdapter derAdapter = (DerAdapter) Function1.this.invoke(writer.getTypeHint());
                if (derAdapter != null) {
                    derAdapter.toDer(writer, obj);
                    return;
                }
                Objects.requireNonNull(obj, "null cannot be cast to non-null type okio.ByteString");
                writer.writeOctetString((ByteString) obj);
            }

            @Override // okhttp3.tls.internal.der.DerAdapter
            @NotNull
            public BasicDerAdapter<Object> withExplicitBox(int i2, long j2, @Nullable Boolean bool) {
                return DerAdapter.DefaultImpls.withExplicitBox(this, i2, j2, bool);
            }
        };
    }
}
