package androidx.fragment.app;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001a\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u0012\u0010\u0007\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001\u001aJ\u0010\r\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u000126\u0010\f\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00050\b\u001a\u0012\u0010\u000e\u001a\u00020\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u000f"}, d2 = {"Landroidx/fragment/app/Fragment;", "", "requestKey", "Landroid/os/Bundle;", "result", "", "setFragmentResult", "clearFragmentResult", "Lkotlin/Function2;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "bundle", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setFragmentResultListener", "clearFragmentResultListener", "fragment-ktx_release"}, k = 2, mv = {1, 4, 1})
/* loaded from: classes.dex */
public final class FragmentKt {
    public static final void clearFragmentResult(@NotNull Fragment clearFragmentResult, @NotNull String requestKey) {
        Intrinsics.checkNotNullParameter(clearFragmentResult, "$this$clearFragmentResult");
        Intrinsics.checkNotNullParameter(requestKey, "requestKey");
        clearFragmentResult.getParentFragmentManager().clearFragmentResult(requestKey);
    }

    public static final void clearFragmentResultListener(@NotNull Fragment clearFragmentResultListener, @NotNull String requestKey) {
        Intrinsics.checkNotNullParameter(clearFragmentResultListener, "$this$clearFragmentResultListener");
        Intrinsics.checkNotNullParameter(requestKey, "requestKey");
        clearFragmentResultListener.getParentFragmentManager().clearFragmentResultListener(requestKey);
    }

    public static final void setFragmentResult(@NotNull Fragment setFragmentResult, @NotNull String requestKey, @NotNull Bundle result) {
        Intrinsics.checkNotNullParameter(setFragmentResult, "$this$setFragmentResult");
        Intrinsics.checkNotNullParameter(requestKey, "requestKey");
        Intrinsics.checkNotNullParameter(result, "result");
        setFragmentResult.getParentFragmentManager().setFragmentResult(requestKey, result);
    }

    public static final void setFragmentResultListener(@NotNull Fragment setFragmentResultListener, @NotNull String requestKey, @NotNull final Function2<? super String, ? super Bundle, Unit> listener) {
        Intrinsics.checkNotNullParameter(setFragmentResultListener, "$this$setFragmentResultListener");
        Intrinsics.checkNotNullParameter(requestKey, "requestKey");
        Intrinsics.checkNotNullParameter(listener, "listener");
        setFragmentResultListener.getParentFragmentManager().setFragmentResultListener(requestKey, setFragmentResultListener, new FragmentResultListener() { // from class: androidx.fragment.app.FragmentKt$sam$androidx_fragment_app_FragmentResultListener$0
            @Override // androidx.fragment.app.FragmentResultListener
            public final /* synthetic */ void onFragmentResult(@NonNull String p0, @NonNull Bundle p1) {
                Intrinsics.checkNotNullParameter(p0, "p0");
                Intrinsics.checkNotNullParameter(p1, "p1");
                Intrinsics.checkNotNullExpressionValue(Function2.this.invoke(p0, p1), "invoke(...)");
            }
        });
    }
}
