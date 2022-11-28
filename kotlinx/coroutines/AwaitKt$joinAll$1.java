package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.AwaitKt", f = "Await.kt", i = {0}, l = {54}, m = "joinAll", n = {"$this$forEach$iv"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class AwaitKt$joinAll$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11270a;

    /* renamed from: b  reason: collision with root package name */
    int f11271b;

    /* renamed from: c  reason: collision with root package name */
    int f11272c;

    /* renamed from: d  reason: collision with root package name */
    /* synthetic */ Object f11273d;

    /* renamed from: e  reason: collision with root package name */
    int f11274e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AwaitKt$joinAll$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11273d = obj;
        this.f11274e |= Integer.MIN_VALUE;
        return AwaitKt.joinAll((Job[]) null, this);
    }
}
