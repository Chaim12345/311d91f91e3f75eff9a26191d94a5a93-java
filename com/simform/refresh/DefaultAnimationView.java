package com.simform.refresh;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0003\u001a\u00020\u0002H\u0016J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0002H\u0016J\b\u0010\u0006\u001a\u00020\u0002H\u0016J\b\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\n\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\u000f"}, d2 = {"Lcom/simform/refresh/DefaultAnimationView;", "Lcom/simform/refresh/SSLottieAnimationView;", "", "reset", "refreshing", "refreshComplete", "pullToRefresh", "releaseToRefresh", "", "pullDistance", "pullProgress", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "sspulltorefresh_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class DefaultAnimationView extends SSLottieAnimationView {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultAnimationView(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // com.simform.refresh.RefreshCallbacks
    public void pullProgress(float f2, float f3) {
    }

    @Override // com.simform.refresh.RefreshCallbacks
    public void pullToRefresh() {
        playAnimation();
    }

    @Override // com.simform.refresh.RefreshCallbacks
    public void refreshComplete() {
        cancelAnimation();
    }

    @Override // com.simform.refresh.RefreshCallbacks
    public void refreshing() {
    }

    @Override // com.simform.refresh.RefreshCallbacks
    public void releaseToRefresh() {
    }

    @Override // com.simform.refresh.RefreshCallbacks
    public void reset() {
        cancelAnimation();
        playAnimation();
    }
}
