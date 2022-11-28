package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", f = "Channels.common.kt", i = {0, 0}, l = {CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA}, m = "toList", n = {"$this$toList_u24lambda_u2d3", "$this$consume$iv$iv"}, s = {"L$1", "L$2"})
/* loaded from: classes3.dex */
public final class ChannelsKt__Channels_commonKt$toList$1<E> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11345a;

    /* renamed from: b  reason: collision with root package name */
    Object f11346b;

    /* renamed from: c  reason: collision with root package name */
    Object f11347c;

    /* renamed from: d  reason: collision with root package name */
    Object f11348d;

    /* renamed from: e  reason: collision with root package name */
    /* synthetic */ Object f11349e;

    /* renamed from: f  reason: collision with root package name */
    int f11350f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__Channels_commonKt$toList$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f11349e = obj;
        this.f11350f |= Integer.MIN_VALUE;
        return ChannelsKt.toList(null, this);
    }
}
