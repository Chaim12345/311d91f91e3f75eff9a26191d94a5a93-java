package kotlinx.coroutines.sync;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.sync.MutexKt", f = "Mutex.kt", i = {0, 0, 0}, l = {112}, m = "withLock", n = {"$this$withLock", "owner", "action"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes3.dex */
public final class MutexKt$withLock$1<T> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f12396a;

    /* renamed from: b  reason: collision with root package name */
    Object f12397b;

    /* renamed from: c  reason: collision with root package name */
    Object f12398c;

    /* renamed from: d  reason: collision with root package name */
    /* synthetic */ Object f12399d;

    /* renamed from: e  reason: collision with root package name */
    int f12400e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MutexKt$withLock$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f12399d = obj;
        this.f12400e |= Integer.MIN_VALUE;
        return MutexKt.withLock(null, null, null, this);
    }
}
