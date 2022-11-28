package kotlinx.coroutines.channels;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.apache.commons.codec.language.bm.Languages;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0}, l = {404}, m = Languages.ANY, n = {"$this$consume$iv"}, s = {"L$0"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$any$1<E> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11351a;

    /* renamed from: b  reason: collision with root package name */
    /* synthetic */ Object f11352b;

    /* renamed from: c  reason: collision with root package name */
    int f11353c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$any$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object any;
        this.f11352b = obj;
        this.f11353c |= Integer.MIN_VALUE;
        any = ChannelsKt__DeprecatedKt.any(null, this);
        return any;
    }
}
