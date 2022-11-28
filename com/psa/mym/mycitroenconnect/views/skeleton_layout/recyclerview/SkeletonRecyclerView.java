package com.psa.mym.mycitroenconnect.views.skeleton_layout.recyclerview;

import android.annotation.SuppressLint;
import androidx.annotation.ColorInt;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.Skeleton;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonConfig;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.mask.SkeletonShimmerDirection;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@SuppressLint({"NotifyDataSetChanged"})
/* loaded from: classes3.dex */
public final class SkeletonRecyclerView implements Skeleton, SkeletonStyle {
    private final /* synthetic */ SkeletonConfig $$delegate_0;
    @Nullable
    private final RecyclerView.Adapter originalAdapter;
    @NotNull
    private final RecyclerView recyclerView;
    @NotNull
    private SkeletonRecyclerViewAdapter skeletonAdapter;

    /* renamed from: com.psa.mym.mycitroenconnect.views.skeleton_layout.recyclerview.SkeletonRecyclerView$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        AnonymousClass1() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function0
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2() {
            SkeletonRecyclerView.this.skeletonAdapter.notifyDataSetChanged();
        }
    }

    public SkeletonRecyclerView(@NotNull RecyclerView recyclerView, @LayoutRes int i2, int i3, @NotNull SkeletonConfig config) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(config, "config");
        this.recyclerView = recyclerView;
        this.$$delegate_0 = config;
        this.originalAdapter = recyclerView.getAdapter();
        this.skeletonAdapter = new SkeletonRecyclerViewAdapter(i2, i3, config);
        config.addValueObserver(new AnonymousClass1());
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    @ColorInt
    public int getMaskColor() {
        return this.$$delegate_0.getMaskColor();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public float getMaskCornerRadius() {
        return this.$$delegate_0.getMaskCornerRadius();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public int getShimmerAngle() {
        return this.$$delegate_0.getShimmerAngle();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    @ColorInt
    public int getShimmerColor() {
        return this.$$delegate_0.getShimmerColor();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    @NotNull
    public SkeletonShimmerDirection getShimmerDirection() {
        return this.$$delegate_0.getShimmerDirection();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public long getShimmerDurationInMillis() {
        return this.$$delegate_0.getShimmerDurationInMillis();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public boolean getShowShimmer() {
        return this.$$delegate_0.getShowShimmer();
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonAction
    public boolean isSkeleton() {
        return Intrinsics.areEqual(this.recyclerView.getAdapter(), this.skeletonAdapter);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setMaskColor(int i2) {
        this.$$delegate_0.setMaskColor(i2);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setMaskCornerRadius(float f2) {
        this.$$delegate_0.setMaskCornerRadius(f2);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerAngle(int i2) {
        this.$$delegate_0.setShimmerAngle(i2);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerColor(int i2) {
        this.$$delegate_0.setShimmerColor(i2);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerDirection(@NotNull SkeletonShimmerDirection skeletonShimmerDirection) {
        Intrinsics.checkNotNullParameter(skeletonShimmerDirection, "<set-?>");
        this.$$delegate_0.setShimmerDirection(skeletonShimmerDirection);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShimmerDurationInMillis(long j2) {
        this.$$delegate_0.setShimmerDurationInMillis(j2);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonStyle
    public void setShowShimmer(boolean z) {
        this.$$delegate_0.setShowShimmer(z);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonAction
    public void showOriginal() {
        this.recyclerView.setAdapter(this.originalAdapter);
    }

    @Override // com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonAction
    public void showSkeleton() {
        this.recyclerView.setAdapter(this.skeletonAdapter);
    }
}
