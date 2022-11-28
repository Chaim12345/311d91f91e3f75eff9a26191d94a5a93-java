package com.psa.mym.mycitroenconnect.views.skeleton_layout.mask;

import android.graphics.Canvas;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class SkeletonMask$canvas$2 extends Lambda implements Function0<Canvas> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SkeletonMask f10786a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SkeletonMask$canvas$2(SkeletonMask skeletonMask) {
        super(0);
        this.f10786a = skeletonMask;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final Canvas invoke() {
        return this.f10786a.b();
    }
}
