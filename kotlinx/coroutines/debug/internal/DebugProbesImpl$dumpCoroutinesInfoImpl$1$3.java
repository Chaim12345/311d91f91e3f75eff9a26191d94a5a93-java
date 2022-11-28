package kotlinx.coroutines.debug.internal;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
final class DebugProbesImpl$dumpCoroutinesInfoImpl$1$3 extends Lambda implements Function1<DebugProbesImpl.CoroutineOwner<?>, R> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function2 f11569a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DebugProbesImpl$dumpCoroutinesInfoImpl$1$3(Function2<? super DebugProbesImpl.CoroutineOwner<?>, ? super CoroutineContext, ? extends R> function2) {
        super(1);
        this.f11569a = function2;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [R, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final R invoke(@NotNull DebugProbesImpl.CoroutineOwner<?> coroutineOwner) {
        boolean isFinished;
        CoroutineContext context;
        isFinished = DebugProbesImpl.INSTANCE.isFinished(coroutineOwner);
        if (isFinished || (context = coroutineOwner.info.getContext()) == null) {
            return null;
        }
        return this.f11569a.invoke(coroutineOwner, context);
    }
}
