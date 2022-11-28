package com.psa.mym.mycitroenconnect.views.skeleton_layout;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.SkeletonConfig;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.recyclerview.SkeletonRecyclerView;
import com.psa.mym.mycitroenconnect.views.skeleton_layout.viewpager2.SkeletonViewPager2;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@JvmName(name = "SkeletonLayoutUtils")
/* loaded from: classes3.dex */
public final class SkeletonLayoutUtils {
    private static final int LIST_ITEM_COUNT_DEFAULT = 5;

    @JvmOverloads
    @NotNull
    public static final Skeleton applySkeleton(@NotNull RecyclerView recyclerView, @LayoutRes int i2) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        return applySkeleton$default(recyclerView, i2, 0, (SkeletonConfig) null, 6, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Skeleton applySkeleton(@NotNull RecyclerView recyclerView, @LayoutRes int i2, int i3) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        return applySkeleton$default(recyclerView, i2, i3, (SkeletonConfig) null, 4, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Skeleton applySkeleton(@NotNull RecyclerView recyclerView, @LayoutRes int i2, int i3, @NotNull SkeletonConfig config) {
        Intrinsics.checkNotNullParameter(recyclerView, "<this>");
        Intrinsics.checkNotNullParameter(config, "config");
        return new SkeletonRecyclerView(recyclerView, i2, i3, config);
    }

    @JvmOverloads
    @NotNull
    public static final Skeleton applySkeleton(@NotNull ViewPager2 viewPager2, @LayoutRes int i2) {
        Intrinsics.checkNotNullParameter(viewPager2, "<this>");
        return applySkeleton$default(viewPager2, i2, 0, (SkeletonConfig) null, 6, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Skeleton applySkeleton(@NotNull ViewPager2 viewPager2, @LayoutRes int i2, int i3) {
        Intrinsics.checkNotNullParameter(viewPager2, "<this>");
        return applySkeleton$default(viewPager2, i2, i3, (SkeletonConfig) null, 4, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Skeleton applySkeleton(@NotNull ViewPager2 viewPager2, @LayoutRes int i2, int i3, @NotNull SkeletonConfig config) {
        Intrinsics.checkNotNullParameter(viewPager2, "<this>");
        Intrinsics.checkNotNullParameter(config, "config");
        return new SkeletonViewPager2(viewPager2, i2, i3, config);
    }

    public static /* synthetic */ Skeleton applySkeleton$default(RecyclerView recyclerView, int i2, int i3, SkeletonConfig skeletonConfig, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i3 = 5;
        }
        if ((i4 & 4) != 0) {
            SkeletonConfig.Companion companion = SkeletonConfig.Companion;
            Context context = recyclerView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            skeletonConfig = companion.m174default(context);
        }
        return applySkeleton(recyclerView, i2, i3, skeletonConfig);
    }

    public static /* synthetic */ Skeleton applySkeleton$default(ViewPager2 viewPager2, int i2, int i3, SkeletonConfig skeletonConfig, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i3 = 5;
        }
        if ((i4 & 4) != 0) {
            SkeletonConfig.Companion companion = SkeletonConfig.Companion;
            Context context = viewPager2.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            skeletonConfig = companion.m174default(context);
        }
        return applySkeleton(viewPager2, i2, i3, skeletonConfig);
    }

    @JvmOverloads
    @NotNull
    public static final Skeleton createSkeleton(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return createSkeleton$default(view, null, 1, null);
    }

    @JvmOverloads
    @NotNull
    public static final Skeleton createSkeleton(@NotNull View view, @NotNull SkeletonConfig config) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(config, "config");
        ViewParent parent = view.getParent();
        ViewGroup viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        int indexOfChild = viewGroup != null ? viewGroup.indexOfChild(view) : 0;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        SkeletonLayout skeletonLayout = new SkeletonLayout(view, config);
        if (layoutParams != null) {
            skeletonLayout.setLayoutParams(layoutParams);
        }
        if (viewGroup != null) {
            viewGroup.addView(skeletonLayout, indexOfChild);
        }
        return skeletonLayout;
    }

    public static /* synthetic */ Skeleton createSkeleton$default(View view, SkeletonConfig skeletonConfig, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            SkeletonConfig.Companion companion = SkeletonConfig.Companion;
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "context");
            skeletonConfig = companion.m174default(context);
        }
        return createSkeleton(view, skeletonConfig);
    }
}
