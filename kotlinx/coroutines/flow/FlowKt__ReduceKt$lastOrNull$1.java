package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ReduceKt", f = "Reduce.kt", i = {0}, l = {CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384}, m = "lastOrNull", n = {"result"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__ReduceKt$lastOrNull$1<T> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11963a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11964b;

    /* renamed from: c  reason: collision with root package name */
    int f11965c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__ReduceKt$lastOrNull$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11964b = obj;
        this.f11965c |= Integer.MIN_VALUE;
        return FlowKt.lastOrNull(null, this);
    }
}
