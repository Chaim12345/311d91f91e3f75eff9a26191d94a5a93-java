package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.CallbackFlowBuilder", f = "Builders.kt", i = {0}, l = {336}, m = "collectTo", n = {"scope"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class CallbackFlowBuilder$collectTo$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11574a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11575b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ CallbackFlowBuilder f11576c;

    /* renamed from: d  reason: collision with root package name */
    int f11577d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallbackFlowBuilder$collectTo$1(CallbackFlowBuilder callbackFlowBuilder, Continuation continuation) {
        super(continuation);
        this.f11576c = callbackFlowBuilder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11575b = obj;
        this.f11577d |= Integer.MIN_VALUE;
        return this.f11576c.c(null, this);
    }
}
