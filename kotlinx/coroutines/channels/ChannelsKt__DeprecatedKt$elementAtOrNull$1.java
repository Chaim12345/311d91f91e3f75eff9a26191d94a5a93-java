package kotlinx.coroutines.channels;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0}, l = {53}, m = "elementAtOrNull", n = {"$this$consume$iv", FirebaseAnalytics.Param.INDEX, "count"}, s = {"L$0", "I$0", "I$1"})
/* loaded from: classes3.dex */
public final class ChannelsKt__DeprecatedKt$elementAtOrNull$1<E> extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    int f11385a;

    /* renamed from: b  reason: collision with root package name */
    int f11386b;

    /* renamed from: c  reason: collision with root package name */
    Object f11387c;

    /* renamed from: d  reason: collision with root package name */
    Object f11388d;

    /* renamed from: e  reason: collision with root package name */
    /* synthetic */ Object f11389e;

    /* renamed from: f  reason: collision with root package name */
    int f11390f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$elementAtOrNull$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object elementAtOrNull;
        this.f11389e = obj;
        this.f11390f |= Integer.MIN_VALUE;
        elementAtOrNull = ChannelsKt__DeprecatedKt.elementAtOrNull(null, 0, this);
        return elementAtOrNull;
    }
}
