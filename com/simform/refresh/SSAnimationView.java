package com.simform.refresh;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import pl.droidsonroids.gif.GifImageView;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/simform/refresh/SSAnimationView;", "Lpl/droidsonroids/gif/GifImageView;", "Lcom/simform/refresh/RefreshCallbacks;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "sspulltorefresh_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public abstract class SSAnimationView extends GifImageView implements RefreshCallbacks {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SSAnimationView(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }
}
