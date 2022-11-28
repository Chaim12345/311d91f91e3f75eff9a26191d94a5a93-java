package com.psa.mym.mycitroenconnect.views.skeleton_layout.mask;

import android.content.Context;
import android.view.View;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.BaseExtensionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class SkeletonMaskShimmer$refreshIntervalInMillis$2 extends Lambda implements Function0<Long> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ View f10788a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkeletonMaskShimmer$refreshIntervalInMillis$2(View view) {
        super(0);
        this.f10788a = view;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final Long invoke() {
        Context context = this.f10788a.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        return Long.valueOf((1000.0f / BaseExtensionsKt.refreshRateInSeconds(context)) * 0.9f);
    }
}
