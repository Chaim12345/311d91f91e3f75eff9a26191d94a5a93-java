package kotlinx.coroutines.sync;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.sync.SemaphoreKt", f = "Semaphore.kt", i = {0, 0}, l = {85}, m = "withPermit", n = {"$this$withPermit", "action"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class SemaphoreKt$withPermit$1<T> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f12403a;

    /* renamed from: b  reason: collision with root package name */
    Object f12404b;

    /* renamed from: c  reason: collision with root package name */
    /* synthetic */ Object f12405c;

    /* renamed from: d  reason: collision with root package name */
    int f12406d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SemaphoreKt$withPermit$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f12405c = obj;
        this.f12406d |= Integer.MIN_VALUE;
        return SemaphoreKt.withPermit(null, null, this);
    }
}
