package kotlinx.coroutines.debug.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.debug.internal.DebugCoroutineInfoImpl", f = "DebugCoroutineInfoImpl.kt", i = {}, l = {80}, m = "yieldFrames", n = {}, s = {})
/* loaded from: classes3.dex */
public final class DebugCoroutineInfoImpl$yieldFrames$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11563a;

    /* renamed from: b  reason: collision with root package name */
    Object f11564b;

    /* renamed from: c  reason: collision with root package name */
    Object f11565c;

    /* renamed from: d  reason: collision with root package name */
    /* synthetic */ Object f11566d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ DebugCoroutineInfoImpl f11567e;

    /* renamed from: f  reason: collision with root package name */
    int f11568f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DebugCoroutineInfoImpl$yieldFrames$1(DebugCoroutineInfoImpl debugCoroutineInfoImpl, Continuation continuation) {
        super(continuation);
        this.f11567e = debugCoroutineInfoImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object yieldFrames;
        this.f11566d = obj;
        this.f11568f |= Integer.MIN_VALUE;
        yieldFrames = this.f11567e.yieldFrames(null, null, this);
        return yieldFrames;
    }
}
