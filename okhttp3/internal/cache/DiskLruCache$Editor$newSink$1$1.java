package okhttp3.internal.cache;

import java.io.IOException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.internal.cache.DiskLruCache;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class DiskLruCache$Editor$newSink$1$1 extends Lambda implements Function1<IOException, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DiskLruCache f12525a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ DiskLruCache.Editor f12526b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiskLruCache$Editor$newSink$1$1(DiskLruCache diskLruCache, DiskLruCache.Editor editor) {
        super(1);
        this.f12525a = diskLruCache;
        this.f12526b = editor;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(IOException iOException) {
        invoke2(iOException);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull IOException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        DiskLruCache diskLruCache = this.f12525a;
        DiskLruCache.Editor editor = this.f12526b;
        synchronized (diskLruCache) {
            editor.detach$okhttp();
            Unit unit = Unit.INSTANCE;
        }
    }
}
