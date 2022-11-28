package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0}, l = {CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384}, m = "singleOrNull", n = {"result", "collector$iv"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class FlowKt__ReduceKt$singleOrNull$1<T> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11980a;

    /* renamed from: b  reason: collision with root package name */
    Object f11981b;

    /* renamed from: c  reason: collision with root package name */
    /* synthetic */ Object f11982c;

    /* renamed from: d  reason: collision with root package name */
    int f11983d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__ReduceKt$singleOrNull$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11982c = obj;
        this.f11983d |= Integer.MIN_VALUE;
        return FlowKt.singleOrNull(null, this);
    }
}
