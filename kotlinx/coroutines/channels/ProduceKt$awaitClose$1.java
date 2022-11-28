package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ProduceKt", f = "Produce.kt", i = {0, 0}, l = {CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA}, m = "awaitClose", n = {"$this$awaitClose", "block"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class ProduceKt$awaitClose$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11530a;

    /* renamed from: b  reason: collision with root package name */
    Object f11531b;

    /* renamed from: c  reason: collision with root package name */
    /* synthetic */ Object f11532c;

    /* renamed from: d  reason: collision with root package name */
    int f11533d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProduceKt$awaitClose$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11532c = obj;
        this.f11533d |= Integer.MIN_VALUE;
        return ProduceKt.awaitClose(null, null, this);
    }
}
