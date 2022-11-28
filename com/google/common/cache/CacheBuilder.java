package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Equivalence;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache;
import com.google.common.cache.LocalCache;
import com.google.errorprone.annotations.CheckReturnValue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes2.dex */
public final class CacheBuilder<K, V> {
    private static final int DEFAULT_CONCURRENCY_LEVEL = 4;
    private static final int DEFAULT_EXPIRATION_NANOS = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final int DEFAULT_REFRESH_NANOS = 0;
    @NullableDecl

    /* renamed from: f  reason: collision with root package name */
    Weigher f8205f;
    @NullableDecl

    /* renamed from: g  reason: collision with root package name */
    LocalCache.Strength f8206g;
    @NullableDecl

    /* renamed from: h  reason: collision with root package name */
    LocalCache.Strength f8207h;
    @NullableDecl

    /* renamed from: l  reason: collision with root package name */
    Equivalence f8211l;
    @NullableDecl

    /* renamed from: m  reason: collision with root package name */
    Equivalence f8212m;
    @NullableDecl

    /* renamed from: n  reason: collision with root package name */
    RemovalListener f8213n;
    @NullableDecl

    /* renamed from: o  reason: collision with root package name */
    Ticker f8214o;

    /* renamed from: q  reason: collision with root package name */
    static final Supplier f8196q = Suppliers.ofInstance(new AbstractCache.StatsCounter() { // from class: com.google.common.cache.CacheBuilder.1
        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordEviction() {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordHits(int i2) {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordLoadException(long j2) {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordLoadSuccess(long j2) {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public void recordMisses(int i2) {
        }

        @Override // com.google.common.cache.AbstractCache.StatsCounter
        public CacheStats snapshot() {
            return CacheBuilder.f8197r;
        }
    });

    /* renamed from: r  reason: collision with root package name */
    static final CacheStats f8197r = new CacheStats(0, 0, 0, 0, 0, 0);

    /* renamed from: s  reason: collision with root package name */
    static final Supplier f8198s = new Supplier<AbstractCache.StatsCounter>() { // from class: com.google.common.cache.CacheBuilder.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.common.base.Supplier
        public AbstractCache.StatsCounter get() {
            return new AbstractCache.SimpleStatsCounter();
        }
    };

    /* renamed from: t  reason: collision with root package name */
    static final Ticker f8199t = new Ticker() { // from class: com.google.common.cache.CacheBuilder.3
        @Override // com.google.common.base.Ticker
        public long read() {
            return 0L;
        }
    };
    private static final Logger logger = Logger.getLogger(CacheBuilder.class.getName());

    /* renamed from: a  reason: collision with root package name */
    boolean f8200a = true;

    /* renamed from: b  reason: collision with root package name */
    int f8201b = -1;

    /* renamed from: c  reason: collision with root package name */
    int f8202c = -1;

    /* renamed from: d  reason: collision with root package name */
    long f8203d = -1;

    /* renamed from: e  reason: collision with root package name */
    long f8204e = -1;

    /* renamed from: i  reason: collision with root package name */
    long f8208i = -1;

    /* renamed from: j  reason: collision with root package name */
    long f8209j = -1;

    /* renamed from: k  reason: collision with root package name */
    long f8210k = -1;

    /* renamed from: p  reason: collision with root package name */
    Supplier f8215p = f8196q;

    /* loaded from: classes2.dex */
    enum NullListener implements RemovalListener<Object, Object> {
        INSTANCE;

        @Override // com.google.common.cache.RemovalListener
        public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
        }
    }

    /* loaded from: classes2.dex */
    enum OneWeigher implements Weigher<Object, Object> {
        INSTANCE;

        @Override // com.google.common.cache.Weigher
        public int weigh(Object obj, Object obj2) {
            return 1;
        }
    }

    private CacheBuilder() {
    }

    private void checkNonLoadingCache() {
        Preconditions.checkState(this.f8210k == -1, "refreshAfterWrite requires a LoadingCache");
    }

    private void checkWeightWithWeigher() {
        boolean z;
        String str;
        if (this.f8205f == null) {
            z = this.f8204e == -1;
            str = "maximumWeight requires weigher";
        } else if (!this.f8200a) {
            if (this.f8204e == -1) {
                logger.log(Level.WARNING, "ignoring weigher specified without maximumWeight");
                return;
            }
            return;
        } else {
            z = this.f8204e != -1;
            str = "weigher requires maximumWeight";
        }
        Preconditions.checkState(z, str);
    }

    @GwtIncompatible
    public static CacheBuilder<Object, Object> from(CacheBuilderSpec cacheBuilderSpec) {
        return cacheBuilderSpec.b().p();
    }

    @GwtIncompatible
    public static CacheBuilder<Object, Object> from(String str) {
        return from(CacheBuilderSpec.parse(str));
    }

    public static CacheBuilder<Object, Object> newBuilder() {
        return new CacheBuilder<>();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int a() {
        int i2 = this.f8202c;
        if (i2 == -1) {
            return 4;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long b() {
        long j2 = this.f8209j;
        if (j2 == -1) {
            return 0L;
        }
        return j2;
    }

    public <K1 extends K, V1 extends V> Cache<K1, V1> build() {
        checkWeightWithWeigher();
        checkNonLoadingCache();
        return new LocalCache.LocalManualCache(this);
    }

    public <K1 extends K, V1 extends V> LoadingCache<K1, V1> build(CacheLoader<? super K1, V1> cacheLoader) {
        checkWeightWithWeigher();
        return new LocalCache.LocalLoadingCache(this, cacheLoader);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long c() {
        long j2 = this.f8208i;
        if (j2 == -1) {
            return 0L;
        }
        return j2;
    }

    public CacheBuilder<K, V> concurrencyLevel(int i2) {
        int i3 = this.f8202c;
        Preconditions.checkState(i3 == -1, "concurrency level was already set to %s", i3);
        Preconditions.checkArgument(i2 > 0);
        this.f8202c = i2;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d() {
        int i2 = this.f8201b;
        if (i2 == -1) {
            return 16;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Equivalence e() {
        return (Equivalence) MoreObjects.firstNonNull(this.f8211l, f().a());
    }

    public CacheBuilder<K, V> expireAfterAccess(long j2, TimeUnit timeUnit) {
        long j3 = this.f8209j;
        Preconditions.checkState(j3 == -1, "expireAfterAccess was already set to %s ns", j3);
        Preconditions.checkArgument(j2 >= 0, "duration cannot be negative: %s %s", j2, timeUnit);
        this.f8209j = timeUnit.toNanos(j2);
        return this;
    }

    public CacheBuilder<K, V> expireAfterWrite(long j2, TimeUnit timeUnit) {
        long j3 = this.f8208i;
        Preconditions.checkState(j3 == -1, "expireAfterWrite was already set to %s ns", j3);
        Preconditions.checkArgument(j2 >= 0, "duration cannot be negative: %s %s", j2, timeUnit);
        this.f8208i = timeUnit.toNanos(j2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalCache.Strength f() {
        return (LocalCache.Strength) MoreObjects.firstNonNull(this.f8206g, LocalCache.Strength.STRONG);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long g() {
        if (this.f8208i == 0 || this.f8209j == 0) {
            return 0L;
        }
        return this.f8205f == null ? this.f8203d : this.f8204e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long h() {
        long j2 = this.f8210k;
        if (j2 == -1) {
            return 0L;
        }
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RemovalListener i() {
        return (RemovalListener) MoreObjects.firstNonNull(this.f8213n, NullListener.INSTANCE);
    }

    public CacheBuilder<K, V> initialCapacity(int i2) {
        int i3 = this.f8201b;
        Preconditions.checkState(i3 == -1, "initial capacity was already set to %s", i3);
        Preconditions.checkArgument(i2 >= 0);
        this.f8201b = i2;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Supplier j() {
        return this.f8215p;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Ticker k(boolean z) {
        Ticker ticker = this.f8214o;
        return ticker != null ? ticker : z ? Ticker.systemTicker() : f8199t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Equivalence l() {
        return (Equivalence) MoreObjects.firstNonNull(this.f8212m, m().a());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalCache.Strength m() {
        return (LocalCache.Strength) MoreObjects.firstNonNull(this.f8207h, LocalCache.Strength.STRONG);
    }

    public CacheBuilder<K, V> maximumSize(long j2) {
        long j3 = this.f8203d;
        Preconditions.checkState(j3 == -1, "maximum size was already set to %s", j3);
        long j4 = this.f8204e;
        Preconditions.checkState(j4 == -1, "maximum weight was already set to %s", j4);
        Preconditions.checkState(this.f8205f == null, "maximum size can not be combined with weigher");
        Preconditions.checkArgument(j2 >= 0, "maximum size must not be negative");
        this.f8203d = j2;
        return this;
    }

    @GwtIncompatible
    public CacheBuilder<K, V> maximumWeight(long j2) {
        long j3 = this.f8204e;
        Preconditions.checkState(j3 == -1, "maximum weight was already set to %s", j3);
        long j4 = this.f8203d;
        Preconditions.checkState(j4 == -1, "maximum size was already set to %s", j4);
        this.f8204e = j2;
        Preconditions.checkArgument(j2 >= 0, "maximum weight must not be negative");
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Weigher n() {
        return (Weigher) MoreObjects.firstNonNull(this.f8205f, OneWeigher.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @GwtIncompatible
    public CacheBuilder o(Equivalence equivalence) {
        Equivalence equivalence2 = this.f8211l;
        Preconditions.checkState(equivalence2 == null, "key equivalence was already set to %s", equivalence2);
        this.f8211l = (Equivalence) Preconditions.checkNotNull(equivalence);
        return this;
    }

    @GwtIncompatible
    CacheBuilder p() {
        this.f8200a = false;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CacheBuilder q(LocalCache.Strength strength) {
        LocalCache.Strength strength2 = this.f8206g;
        Preconditions.checkState(strength2 == null, "Key strength was already set to %s", strength2);
        this.f8206g = (LocalCache.Strength) Preconditions.checkNotNull(strength);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CacheBuilder r(LocalCache.Strength strength) {
        LocalCache.Strength strength2 = this.f8207h;
        Preconditions.checkState(strength2 == null, "Value strength was already set to %s", strength2);
        this.f8207h = (LocalCache.Strength) Preconditions.checkNotNull(strength);
        return this;
    }

    public CacheBuilder<K, V> recordStats() {
        this.f8215p = f8198s;
        return this;
    }

    @GwtIncompatible
    public CacheBuilder<K, V> refreshAfterWrite(long j2, TimeUnit timeUnit) {
        Preconditions.checkNotNull(timeUnit);
        long j3 = this.f8210k;
        Preconditions.checkState(j3 == -1, "refresh was already set to %s ns", j3);
        Preconditions.checkArgument(j2 > 0, "duration must be positive: %s %s", j2, timeUnit);
        this.f8210k = timeUnit.toNanos(j2);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @CheckReturnValue
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> removalListener(RemovalListener<? super K1, ? super V1> removalListener) {
        Preconditions.checkState(this.f8213n == null);
        this.f8213n = (RemovalListener) Preconditions.checkNotNull(removalListener);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @GwtIncompatible
    public CacheBuilder s(Equivalence equivalence) {
        Equivalence equivalence2 = this.f8212m;
        Preconditions.checkState(equivalence2 == null, "value equivalence was already set to %s", equivalence2);
        this.f8212m = (Equivalence) Preconditions.checkNotNull(equivalence);
        return this;
    }

    @GwtIncompatible
    public CacheBuilder<K, V> softValues() {
        return r(LocalCache.Strength.SOFT);
    }

    public CacheBuilder<K, V> ticker(Ticker ticker) {
        Preconditions.checkState(this.f8214o == null);
        this.f8214o = (Ticker) Preconditions.checkNotNull(ticker);
        return this;
    }

    public String toString() {
        MoreObjects.ToStringHelper stringHelper = MoreObjects.toStringHelper(this);
        int i2 = this.f8201b;
        if (i2 != -1) {
            stringHelper.add("initialCapacity", i2);
        }
        int i3 = this.f8202c;
        if (i3 != -1) {
            stringHelper.add("concurrencyLevel", i3);
        }
        long j2 = this.f8203d;
        if (j2 != -1) {
            stringHelper.add("maximumSize", j2);
        }
        long j3 = this.f8204e;
        if (j3 != -1) {
            stringHelper.add("maximumWeight", j3);
        }
        if (this.f8208i != -1) {
            stringHelper.add("expireAfterWrite", this.f8208i + "ns");
        }
        if (this.f8209j != -1) {
            stringHelper.add("expireAfterAccess", this.f8209j + "ns");
        }
        LocalCache.Strength strength = this.f8206g;
        if (strength != null) {
            stringHelper.add("keyStrength", Ascii.toLowerCase(strength.toString()));
        }
        LocalCache.Strength strength2 = this.f8207h;
        if (strength2 != null) {
            stringHelper.add("valueStrength", Ascii.toLowerCase(strength2.toString()));
        }
        if (this.f8211l != null) {
            stringHelper.addValue("keyEquivalence");
        }
        if (this.f8212m != null) {
            stringHelper.addValue("valueEquivalence");
        }
        if (this.f8213n != null) {
            stringHelper.addValue("removalListener");
        }
        return stringHelper.toString();
    }

    @GwtIncompatible
    public CacheBuilder<K, V> weakKeys() {
        return q(LocalCache.Strength.WEAK);
    }

    @GwtIncompatible
    public CacheBuilder<K, V> weakValues() {
        return r(LocalCache.Strength.WEAK);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    public <K1 extends K, V1 extends V> CacheBuilder<K1, V1> weigher(Weigher<? super K1, ? super V1> weigher) {
        Preconditions.checkState(this.f8205f == null);
        if (this.f8200a) {
            long j2 = this.f8203d;
            Preconditions.checkState(j2 == -1, "weigher can not be combined with maximum size", j2);
        }
        this.f8205f = (Weigher) Preconditions.checkNotNull(weigher);
        return this;
    }
}
