package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__LimitKt", f = "Limit.kt", i = {0}, l = {CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA}, m = "collectWhile", n = {"collector"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__LimitKt$collectWhile$1<T> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11844a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11845b;

    /* renamed from: c  reason: collision with root package name */
    int f11846c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__LimitKt$collectWhile$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11845b = obj;
        this.f11846c |= Integer.MIN_VALUE;
        return FlowKt__LimitKt.collectWhile(null, null, this);
    }
}
