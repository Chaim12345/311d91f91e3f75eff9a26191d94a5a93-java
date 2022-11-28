package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.cache.LocalCache;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes2.dex */
public final class CacheBuilderSpec {
    private static final Splitter KEYS_SPLITTER = Splitter.on((char) AbstractJsonLexerKt.COMMA).trimResults();
    private static final Splitter KEY_VALUE_SPLITTER = Splitter.on('=').trimResults();
    private static final ImmutableMap<String, ValueParser> VALUE_PARSERS;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: a  reason: collision with root package name */
    Integer f8216a;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: b  reason: collision with root package name */
    Long f8217b;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: c  reason: collision with root package name */
    Long f8218c;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: d  reason: collision with root package name */
    Integer f8219d;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: e  reason: collision with root package name */
    LocalCache.Strength f8220e;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: f  reason: collision with root package name */
    LocalCache.Strength f8221f;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: g  reason: collision with root package name */
    Boolean f8222g;
    @VisibleForTesting

    /* renamed from: h  reason: collision with root package name */
    long f8223h;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: i  reason: collision with root package name */
    TimeUnit f8224i;
    @VisibleForTesting

    /* renamed from: j  reason: collision with root package name */
    long f8225j;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: k  reason: collision with root package name */
    TimeUnit f8226k;
    @VisibleForTesting

    /* renamed from: l  reason: collision with root package name */
    long f8227l;
    @VisibleForTesting
    @NullableDecl

    /* renamed from: m  reason: collision with root package name */
    TimeUnit f8228m;
    private final String specification;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.cache.CacheBuilderSpec$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8229a;

        static {
            int[] iArr = new int[LocalCache.Strength.values().length];
            f8229a = iArr;
            try {
                iArr[LocalCache.Strength.WEAK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8229a[LocalCache.Strength.SOFT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes2.dex */
    static class AccessDurationParser extends DurationParser {
        AccessDurationParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        protected void a(CacheBuilderSpec cacheBuilderSpec, long j2, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.f8226k == null, "expireAfterAccess already set");
            cacheBuilderSpec.f8225j = j2;
            cacheBuilderSpec.f8226k = timeUnit;
        }
    }

    /* loaded from: classes2.dex */
    static class ConcurrencyLevelParser extends IntegerParser {
        ConcurrencyLevelParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        protected void a(CacheBuilderSpec cacheBuilderSpec, int i2) {
            Integer num = cacheBuilderSpec.f8219d;
            Preconditions.checkArgument(num == null, "concurrency level was already set to ", num);
            cacheBuilderSpec.f8219d = Integer.valueOf(i2);
        }
    }

    /* loaded from: classes2.dex */
    static abstract class DurationParser implements ValueParser {
        DurationParser() {
        }

        protected abstract void a(CacheBuilderSpec cacheBuilderSpec, long j2, TimeUnit timeUnit);

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            TimeUnit timeUnit;
            Preconditions.checkArgument((str2 == null || str2.isEmpty()) ? false : true, "value of key %s omitted", str);
            try {
                char charAt = str2.charAt(str2.length() - 1);
                if (charAt == 'd') {
                    timeUnit = TimeUnit.DAYS;
                } else if (charAt == 'h') {
                    timeUnit = TimeUnit.HOURS;
                } else if (charAt == 'm') {
                    timeUnit = TimeUnit.MINUTES;
                } else if (charAt != 's') {
                    throw new IllegalArgumentException(CacheBuilderSpec.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", str, str2));
                } else {
                    timeUnit = TimeUnit.SECONDS;
                }
                a(cacheBuilderSpec, Long.parseLong(str2.substring(0, str2.length() - 1)), timeUnit);
            } catch (NumberFormatException unused) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2));
            }
        }
    }

    /* loaded from: classes2.dex */
    static class InitialCapacityParser extends IntegerParser {
        InitialCapacityParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.IntegerParser
        protected void a(CacheBuilderSpec cacheBuilderSpec, int i2) {
            Integer num = cacheBuilderSpec.f8216a;
            Preconditions.checkArgument(num == null, "initial capacity was already set to ", num);
            cacheBuilderSpec.f8216a = Integer.valueOf(i2);
        }
    }

    /* loaded from: classes2.dex */
    static abstract class IntegerParser implements ValueParser {
        IntegerParser() {
        }

        protected abstract void a(CacheBuilderSpec cacheBuilderSpec, int i2);

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument((str2 == null || str2.isEmpty()) ? false : true, "value of key %s omitted", str);
            try {
                a(cacheBuilderSpec, Integer.parseInt(str2));
            } catch (NumberFormatException e2) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2), e2);
            }
        }
    }

    /* loaded from: classes2.dex */
    static class KeyStrengthParser implements ValueParser {
        private final LocalCache.Strength strength;

        public KeyStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2) {
            Preconditions.checkArgument(str2 == null, "key %s does not take values", str);
            LocalCache.Strength strength = cacheBuilderSpec.f8220e;
            Preconditions.checkArgument(strength == null, "%s was already set to %s", str, strength);
            cacheBuilderSpec.f8220e = this.strength;
        }
    }

    /* loaded from: classes2.dex */
    static abstract class LongParser implements ValueParser {
        LongParser() {
        }

        protected abstract void a(CacheBuilderSpec cacheBuilderSpec, long j2);

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, String str2) {
            Preconditions.checkArgument((str2 == null || str2.isEmpty()) ? false : true, "value of key %s omitted", str);
            try {
                a(cacheBuilderSpec, Long.parseLong(str2));
            } catch (NumberFormatException e2) {
                throw new IllegalArgumentException(CacheBuilderSpec.format("key %s value set to %s, must be integer", str, str2), e2);
            }
        }
    }

    /* loaded from: classes2.dex */
    static class MaximumSizeParser extends LongParser {
        MaximumSizeParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        protected void a(CacheBuilderSpec cacheBuilderSpec, long j2) {
            Long l2 = cacheBuilderSpec.f8217b;
            Preconditions.checkArgument(l2 == null, "maximum size was already set to ", l2);
            Long l3 = cacheBuilderSpec.f8218c;
            Preconditions.checkArgument(l3 == null, "maximum weight was already set to ", l3);
            cacheBuilderSpec.f8217b = Long.valueOf(j2);
        }
    }

    /* loaded from: classes2.dex */
    static class MaximumWeightParser extends LongParser {
        MaximumWeightParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.LongParser
        protected void a(CacheBuilderSpec cacheBuilderSpec, long j2) {
            Long l2 = cacheBuilderSpec.f8218c;
            Preconditions.checkArgument(l2 == null, "maximum weight was already set to ", l2);
            Long l3 = cacheBuilderSpec.f8217b;
            Preconditions.checkArgument(l3 == null, "maximum size was already set to ", l3);
            cacheBuilderSpec.f8218c = Long.valueOf(j2);
        }
    }

    /* loaded from: classes2.dex */
    static class RecordStatsParser implements ValueParser {
        RecordStatsParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2) {
            Preconditions.checkArgument(str2 == null, "recordStats does not take values");
            Preconditions.checkArgument(cacheBuilderSpec.f8222g == null, "recordStats already set");
            cacheBuilderSpec.f8222g = Boolean.TRUE;
        }
    }

    /* loaded from: classes2.dex */
    static class RefreshDurationParser extends DurationParser {
        RefreshDurationParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        protected void a(CacheBuilderSpec cacheBuilderSpec, long j2, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.f8228m == null, "refreshAfterWrite already set");
            cacheBuilderSpec.f8227l = j2;
            cacheBuilderSpec.f8228m = timeUnit;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public interface ValueParser {
        void parse(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2);
    }

    /* loaded from: classes2.dex */
    static class ValueStrengthParser implements ValueParser {
        private final LocalCache.Strength strength;

        public ValueStrengthParser(LocalCache.Strength strength) {
            this.strength = strength;
        }

        @Override // com.google.common.cache.CacheBuilderSpec.ValueParser
        public void parse(CacheBuilderSpec cacheBuilderSpec, String str, @NullableDecl String str2) {
            Preconditions.checkArgument(str2 == null, "key %s does not take values", str);
            LocalCache.Strength strength = cacheBuilderSpec.f8221f;
            Preconditions.checkArgument(strength == null, "%s was already set to %s", str, strength);
            cacheBuilderSpec.f8221f = this.strength;
        }
    }

    /* loaded from: classes2.dex */
    static class WriteDurationParser extends DurationParser {
        WriteDurationParser() {
        }

        @Override // com.google.common.cache.CacheBuilderSpec.DurationParser
        protected void a(CacheBuilderSpec cacheBuilderSpec, long j2, TimeUnit timeUnit) {
            Preconditions.checkArgument(cacheBuilderSpec.f8224i == null, "expireAfterWrite already set");
            cacheBuilderSpec.f8223h = j2;
            cacheBuilderSpec.f8224i = timeUnit;
        }
    }

    static {
        ImmutableMap.Builder put = ImmutableMap.builder().put("initialCapacity", new InitialCapacityParser()).put("maximumSize", new MaximumSizeParser()).put("maximumWeight", new MaximumWeightParser()).put("concurrencyLevel", new ConcurrencyLevelParser());
        LocalCache.Strength strength = LocalCache.Strength.WEAK;
        VALUE_PARSERS = put.put("weakKeys", new KeyStrengthParser(strength)).put("softValues", new ValueStrengthParser(LocalCache.Strength.SOFT)).put("weakValues", new ValueStrengthParser(strength)).put("recordStats", new RecordStatsParser()).put("expireAfterAccess", new AccessDurationParser()).put("expireAfterWrite", new WriteDurationParser()).put("refreshAfterWrite", new RefreshDurationParser()).put("refreshInterval", new RefreshDurationParser()).build();
    }

    private CacheBuilderSpec(String str) {
        this.specification = str;
    }

    public static CacheBuilderSpec disableCaching() {
        return parse("maximumSize=0");
    }

    @NullableDecl
    private static Long durationInNanos(long j2, @NullableDecl TimeUnit timeUnit) {
        if (timeUnit == null) {
            return null;
        }
        return Long.valueOf(timeUnit.toNanos(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String format(String str, Object... objArr) {
        return String.format(Locale.ROOT, str, objArr);
    }

    public static CacheBuilderSpec parse(String str) {
        CacheBuilderSpec cacheBuilderSpec = new CacheBuilderSpec(str);
        if (!str.isEmpty()) {
            for (String str2 : KEYS_SPLITTER.split(str)) {
                ImmutableList copyOf = ImmutableList.copyOf(KEY_VALUE_SPLITTER.split(str2));
                Preconditions.checkArgument(!copyOf.isEmpty(), "blank key-value pair");
                Preconditions.checkArgument(copyOf.size() <= 2, "key-value pair %s with more than one equals sign", str2);
                String str3 = (String) copyOf.get(0);
                ValueParser valueParser = VALUE_PARSERS.get(str3);
                Preconditions.checkArgument(valueParser != null, "unknown key %s", str3);
                valueParser.parse(cacheBuilderSpec, str3, copyOf.size() == 1 ? null : (String) copyOf.get(1));
            }
        }
        return cacheBuilderSpec;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CacheBuilder b() {
        CacheBuilder<Object, Object> newBuilder = CacheBuilder.newBuilder();
        Integer num = this.f8216a;
        if (num != null) {
            newBuilder.initialCapacity(num.intValue());
        }
        Long l2 = this.f8217b;
        if (l2 != null) {
            newBuilder.maximumSize(l2.longValue());
        }
        Long l3 = this.f8218c;
        if (l3 != null) {
            newBuilder.maximumWeight(l3.longValue());
        }
        Integer num2 = this.f8219d;
        if (num2 != null) {
            newBuilder.concurrencyLevel(num2.intValue());
        }
        LocalCache.Strength strength = this.f8220e;
        if (strength != null) {
            if (AnonymousClass1.f8229a[strength.ordinal()] != 1) {
                throw new AssertionError();
            }
            newBuilder.weakKeys();
        }
        LocalCache.Strength strength2 = this.f8221f;
        if (strength2 != null) {
            int i2 = AnonymousClass1.f8229a[strength2.ordinal()];
            if (i2 == 1) {
                newBuilder.weakValues();
            } else if (i2 != 2) {
                throw new AssertionError();
            } else {
                newBuilder.softValues();
            }
        }
        Boolean bool = this.f8222g;
        if (bool != null && bool.booleanValue()) {
            newBuilder.recordStats();
        }
        TimeUnit timeUnit = this.f8224i;
        if (timeUnit != null) {
            newBuilder.expireAfterWrite(this.f8223h, timeUnit);
        }
        TimeUnit timeUnit2 = this.f8226k;
        if (timeUnit2 != null) {
            newBuilder.expireAfterAccess(this.f8225j, timeUnit2);
        }
        TimeUnit timeUnit3 = this.f8228m;
        if (timeUnit3 != null) {
            newBuilder.refreshAfterWrite(this.f8227l, timeUnit3);
        }
        return newBuilder;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CacheBuilderSpec) {
            CacheBuilderSpec cacheBuilderSpec = (CacheBuilderSpec) obj;
            return Objects.equal(this.f8216a, cacheBuilderSpec.f8216a) && Objects.equal(this.f8217b, cacheBuilderSpec.f8217b) && Objects.equal(this.f8218c, cacheBuilderSpec.f8218c) && Objects.equal(this.f8219d, cacheBuilderSpec.f8219d) && Objects.equal(this.f8220e, cacheBuilderSpec.f8220e) && Objects.equal(this.f8221f, cacheBuilderSpec.f8221f) && Objects.equal(this.f8222g, cacheBuilderSpec.f8222g) && Objects.equal(durationInNanos(this.f8223h, this.f8224i), durationInNanos(cacheBuilderSpec.f8223h, cacheBuilderSpec.f8224i)) && Objects.equal(durationInNanos(this.f8225j, this.f8226k), durationInNanos(cacheBuilderSpec.f8225j, cacheBuilderSpec.f8226k)) && Objects.equal(durationInNanos(this.f8227l, this.f8228m), durationInNanos(cacheBuilderSpec.f8227l, cacheBuilderSpec.f8228m));
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.f8216a, this.f8217b, this.f8218c, this.f8219d, this.f8220e, this.f8221f, this.f8222g, durationInNanos(this.f8223h, this.f8224i), durationInNanos(this.f8225j, this.f8226k), durationInNanos(this.f8227l, this.f8228m));
    }

    public String toParsableString() {
        return this.specification;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).addValue(toParsableString()).toString();
    }
}
