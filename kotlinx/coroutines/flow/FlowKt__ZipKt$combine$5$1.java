package kotlinx.coroutines.flow;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class FlowKt__ZipKt$combine$5$1 extends Lambda implements Function0<T[]> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Flow[] f12169a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$5$1(Flow<? extends T>[] flowArr) {
        super(0);
        this.f12169a = flowArr;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T[], java.lang.Object[]] */
    @Override // kotlin.jvm.functions.Function0
    @Nullable
    public final T[] invoke() {
        int length = this.f12169a.length;
        Intrinsics.reifiedOperationMarker(0, "T?");
        return new Object[length];
    }
}
