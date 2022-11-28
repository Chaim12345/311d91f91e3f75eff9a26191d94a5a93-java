package androidx.core.util;

import android.util.LruCache;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001¨\u0006\u0002"}, d2 = {"androidx/core/util/LruCacheKt$lruCache$4", "Landroid/util/LruCache;", "core-ktx_release"}, k = 1, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class LruCacheKt$lruCache$4 extends LruCache<K, V> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function2 f2622a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Function1 f2623b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Function4 f2624c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LruCacheKt$lruCache$4(Function2 function2, Function1 function1, Function4 function4, int i2, int i3) {
        super(i3);
        this.f2622a = function2;
        this.f2623b = function1;
        this.f2624c = function4;
    }

    @Override // android.util.LruCache
    @Nullable
    protected Object create(@NotNull Object key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.f2623b.invoke(key);
    }

    @Override // android.util.LruCache
    protected void entryRemoved(boolean z, @NotNull Object key, @NotNull Object oldValue, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(oldValue, "oldValue");
        this.f2624c.invoke(Boolean.valueOf(z), key, oldValue, obj);
    }

    @Override // android.util.LruCache
    protected int sizeOf(@NotNull Object key, @NotNull Object value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        return ((Number) this.f2622a.invoke(key, value)).intValue();
    }
}
