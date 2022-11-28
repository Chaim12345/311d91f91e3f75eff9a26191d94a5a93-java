package kotlinx.coroutines.channels;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0}, l = {487}, m = "indexOf", n = {"element", FirebaseAnalytics.Param.INDEX, "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$indexOf$1<E> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11429a;

    /* renamed from: b  reason: collision with root package name */
    Object f11430b;

    /* renamed from: c  reason: collision with root package name */
    Object f11431c;

    /* renamed from: d  reason: collision with root package name */
    Object f11432d;

    /* renamed from: e  reason: collision with root package name */
    /* synthetic */ Object f11433e;

    /* renamed from: f  reason: collision with root package name */
    int f11434f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$indexOf$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object indexOf;
        this.f11433e = obj;
        this.f11434f |= Integer.MIN_VALUE;
        indexOf = ChannelsKt__DeprecatedKt.indexOf(null, null, this);
        return indexOf;
    }
}
