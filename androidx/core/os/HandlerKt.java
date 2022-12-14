package androidx.core.os;

import android.os.Handler;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a4\u0010\t\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u000e\b\u0004\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086\bø\u0001\u0000\u001a4\u0010\u000b\u001a\u00020\b*\u00020\u00002\u0006\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u000e\b\u0004\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\f"}, d2 = {"Landroid/os/Handler;", "", "delayInMillis", "", "token", "Lkotlin/Function0;", "", "action", "Ljava/lang/Runnable;", "postDelayed", "uptimeMillis", "postAtTime", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class HandlerKt {
    @NotNull
    public static final Runnable postAtTime(@NotNull Handler postAtTime, long j2, @Nullable Object obj, @NotNull Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(postAtTime, "$this$postAtTime");
        Intrinsics.checkNotNullParameter(action, "action");
        HandlerKt$postAtTime$runnable$1 handlerKt$postAtTime$runnable$1 = new HandlerKt$postAtTime$runnable$1(action);
        postAtTime.postAtTime(handlerKt$postAtTime$runnable$1, obj, j2);
        return handlerKt$postAtTime$runnable$1;
    }

    public static /* synthetic */ Runnable postAtTime$default(Handler postAtTime, long j2, Object obj, Function0 action, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            obj = null;
        }
        Intrinsics.checkNotNullParameter(postAtTime, "$this$postAtTime");
        Intrinsics.checkNotNullParameter(action, "action");
        HandlerKt$postAtTime$runnable$1 handlerKt$postAtTime$runnable$1 = new HandlerKt$postAtTime$runnable$1(action);
        postAtTime.postAtTime(handlerKt$postAtTime$runnable$1, obj, j2);
        return handlerKt$postAtTime$runnable$1;
    }

    @NotNull
    public static final Runnable postDelayed(@NotNull Handler postDelayed, long j2, @Nullable Object obj, @NotNull Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(postDelayed, "$this$postDelayed");
        Intrinsics.checkNotNullParameter(action, "action");
        HandlerKt$postDelayed$runnable$1 handlerKt$postDelayed$runnable$1 = new HandlerKt$postDelayed$runnable$1(action);
        if (obj == null) {
            postDelayed.postDelayed(handlerKt$postDelayed$runnable$1, j2);
        } else {
            HandlerCompat.postDelayed(postDelayed, handlerKt$postDelayed$runnable$1, obj, j2);
        }
        return handlerKt$postDelayed$runnable$1;
    }

    public static /* synthetic */ Runnable postDelayed$default(Handler postDelayed, long j2, Object obj, Function0 action, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            obj = null;
        }
        Intrinsics.checkNotNullParameter(postDelayed, "$this$postDelayed");
        Intrinsics.checkNotNullParameter(action, "action");
        HandlerKt$postDelayed$runnable$1 handlerKt$postDelayed$runnable$1 = new HandlerKt$postDelayed$runnable$1(action);
        if (obj == null) {
            postDelayed.postDelayed(handlerKt$postDelayed$runnable$1, j2);
        } else {
            HandlerCompat.postDelayed(postDelayed, handlerKt$postDelayed$runnable$1, obj, j2);
        }
        return handlerKt$postDelayed$runnable$1;
    }
}
