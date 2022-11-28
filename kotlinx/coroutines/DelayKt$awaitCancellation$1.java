package kotlinx.coroutines;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.DelayKt", f = "Delay.kt", i = {}, l = {CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA}, m = "awaitCancellation", n = {}, s = {})
/* loaded from: classes3.dex */
public final class DelayKt$awaitCancellation$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    /* synthetic */ Object f11282a;

    /* renamed from: b  reason: collision with root package name */
    int f11283b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DelayKt$awaitCancellation$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11282a = obj;
        this.f11283b |= Integer.MIN_VALUE;
        return DelayKt.awaitCancellation(this);
    }
}
