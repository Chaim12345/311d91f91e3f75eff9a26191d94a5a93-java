package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001¨\u0006\u0002"}, d2 = {"androidx/collection/LruCacheKt$lruCache$4", "Landroidx/collection/LruCache;", "collection-ktx"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class LruCacheKt$lruCache$4 extends LruCache<K, V> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function2 f1590a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Function1 f1591b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Function4 f1592c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LruCacheKt$lruCache$4(Function2 function2, Function1 function1, Function4 function4, int i2, int i3) {
        super(i3);
        this.f1590a = function2;
        this.f1591b = function1;
        this.f1592c = function4;
    }

    @Override // androidx.collection.LruCache
    @Nullable
    protected Object a(@NotNull Object key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        return this.f1591b.invoke(key);
    }

    @Override // androidx.collection.LruCache
    protected void b(boolean z, @NotNull Object key, @NotNull Object oldValue, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(oldValue, "oldValue");
        this.f1592c.invoke(Boolean.valueOf(z), key, oldValue, obj);
    }

    @Override // androidx.collection.LruCache
    protected int c(@NotNull Object key, @NotNull Object value) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        return ((Number) this.f1590a.invoke(key, value)).intValue();
    }
}
