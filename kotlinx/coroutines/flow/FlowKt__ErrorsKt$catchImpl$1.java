package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt", f = "Errors.kt", i = {0}, l = {CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256}, m = "catchImpl", n = {"fromDownstream"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__ErrorsKt$catchImpl$1<T> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11800a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11801b;

    /* renamed from: c  reason: collision with root package name */
    int f11802c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__ErrorsKt$catchImpl$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11801b = obj;
        this.f11802c |= Integer.MIN_VALUE;
        return FlowKt.catchImpl(null, null, this);
    }
}
