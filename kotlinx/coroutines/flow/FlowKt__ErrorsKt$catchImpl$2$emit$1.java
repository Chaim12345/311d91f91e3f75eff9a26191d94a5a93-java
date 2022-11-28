package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ErrorsKt$catchImpl$2", f = "Errors.kt", i = {0}, l = {CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256}, m = "emit", n = {"this"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class FlowKt__ErrorsKt$catchImpl$2$emit$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11805a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11806b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ FlowKt__ErrorsKt$catchImpl$2 f11807c;

    /* renamed from: d  reason: collision with root package name */
    int f11808d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ErrorsKt$catchImpl$2$emit$1(FlowKt__ErrorsKt$catchImpl$2 flowKt__ErrorsKt$catchImpl$2, Continuation continuation) {
        super(continuation);
        this.f11807c = flowKt__ErrorsKt$catchImpl$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11806b = obj;
        this.f11808d |= Integer.MIN_VALUE;
        return this.f11807c.emit(null, this);
    }
}
