package com.psa.mym.mycitroenconnect.views.skeleton_layout.mask;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SkeletonMaskShimmer$shimmerGradient$2 extends Lambda implements Function0<LinearGradient> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SkeletonMaskShimmer f10789a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkeletonMaskShimmer$shimmerGradient$2(SkeletonMaskShimmer skeletonMaskShimmer) {
        super(0);
        this.f10789a = skeletonMaskShimmer;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final LinearGradient invoke() {
        int i2;
        float f2;
        float f3;
        int i3;
        i2 = this.f10789a.shimmerAngle;
        double radians = (float) Math.toRadians(i2);
        f2 = this.f10789a.width;
        f3 = this.f10789a.width;
        float sin = ((float) Math.sin(radians)) * f3;
        i3 = this.f10789a.shimmerColor;
        return new LinearGradient(0.0f, 0.0f, ((float) Math.cos(radians)) * f2, sin, new int[]{this.f10789a.getColor(), i3, this.f10789a.getColor()}, (float[]) null, Shader.TileMode.CLAMP);
    }
}
