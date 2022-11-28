package kotlinx.coroutines.channels;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 0}, l = {487}, m = "lastIndexOf", n = {"element", "lastIndex", FirebaseAnalytics.Param.INDEX, "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$lastIndexOf$1<E> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f11440a;

    /* renamed from: b  reason: collision with root package name */
    Object f11441b;

    /* renamed from: c  reason: collision with root package name */
    Object f11442c;

    /* renamed from: d  reason: collision with root package name */
    Object f11443d;

    /* renamed from: e  reason: collision with root package name */
    Object f11444e;

    /* renamed from: f  reason: collision with root package name */
    /* synthetic */ Object f11445f;

    /* renamed from: g  reason: collision with root package name */
    int f11446g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$lastIndexOf$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object lastIndexOf;
        this.f11445f = obj;
        this.f11446g |= Integer.MIN_VALUE;
        lastIndexOf = ChannelsKt__DeprecatedKt.lastIndexOf(null, null, this);
        return lastIndexOf;
    }
}
