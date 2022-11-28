package com.psa.mym.mycitroenconnect.views.skeleton_layout.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonConfig;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayout;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonLayoutUtils;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class SkeletonRecyclerViewAdapter extends RecyclerView.Adapter<SkeletonRecyclerViewHolder> {
    @NotNull
    private final SkeletonConfig config;
    private final int itemCount;
    private final int layoutResId;

    public SkeletonRecyclerViewAdapter(@LayoutRes int i2, int i3, @NotNull SkeletonConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.layoutResId = i2;
        this.itemCount = i3;
        this.config = config;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemCount;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull SkeletonRecyclerViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public SkeletonRecyclerViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View originView = LayoutInflater.from(parent.getContext()).inflate(this.layoutResId, parent, false);
        Intrinsics.checkNotNullExpressionValue(originView, "originView");
        SkeletonLayout skeletonLayout = (SkeletonLayout) SkeletonLayoutUtils.createSkeleton(originView, this.config);
        skeletonLayout.setLayoutParams(originView.getLayoutParams());
        skeletonLayout.showSkeleton();
        return new SkeletonRecyclerViewHolder(skeletonLayout);
    }
}
