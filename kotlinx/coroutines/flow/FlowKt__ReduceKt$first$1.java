package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0, 0}, l = {CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384}, m = "first", n = {"result", "collector$iv"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class FlowKt__ReduceKt$first$1<T> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11933a;

    /* renamed from: b  reason: collision with root package name */
    Object f11934b;

    /* renamed from: c  reason: collision with root package name */
    /* synthetic */ Object f11935c;

    /* renamed from: d  reason: collision with root package name */
    int f11936d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__ReduceKt$first$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11935c = obj;
        this.f11936d |= Integer.MIN_VALUE;
        return FlowKt.first(null, this);
    }
}
