package com.psa.mym.mycitroenconnect.views.recycler_view;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class LoadMoreScrollListener extends RecyclerView.OnScrollListener {
    private boolean isLoading;
    private int lastVisibleItem;
    @NotNull
    private RecyclerView.LayoutManager mLayoutManager;
    private OnLoadMoreListener mOnLoadMoreListener;
    private int totalItemCount;
    private int visibleThreshold;

    public LoadMoreScrollListener(@NotNull GridLayoutManager layoutManager) {
        Intrinsics.checkNotNullParameter(layoutManager, "layoutManager");
        this.visibleThreshold = 5;
        this.mLayoutManager = layoutManager;
        this.visibleThreshold = 5 * layoutManager.getSpanCount();
    }

    public LoadMoreScrollListener(@NotNull LinearLayoutManager layoutManager) {
        Intrinsics.checkNotNullParameter(layoutManager, "layoutManager");
        this.visibleThreshold = 5;
        this.mLayoutManager = layoutManager;
    }

    public LoadMoreScrollListener(@NotNull StaggeredGridLayoutManager layoutManager) {
        Intrinsics.checkNotNullParameter(layoutManager, "layoutManager");
        this.visibleThreshold = 5;
        this.mLayoutManager = layoutManager;
        this.visibleThreshold = 5 * layoutManager.getSpanCount();
    }

    private final int getLastVisibleItem(int[] iArr) {
        int length = iArr.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (i3 == 0) {
                i2 = iArr[i3];
            } else if (iArr[i3] > i2) {
                i2 = iArr[i3];
            }
        }
        return i2;
    }

    public final boolean getLoaded() {
        return this.isLoading;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(@NotNull RecyclerView recyclerView, int i2, int i3) {
        LinearLayoutManager linearLayoutManager;
        int findLastVisibleItemPosition;
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onScrolled(recyclerView, i2, i3);
        if (i3 <= 0) {
            return;
        }
        this.totalItemCount = this.mLayoutManager.getItemCount();
        RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
        OnLoadMoreListener onLoadMoreListener = null;
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null);
            Intrinsics.checkNotNullExpressionValue(lastVisibleItemPositions, "lastVisibleItemPositions");
            findLastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
        } else {
            if (!(layoutManager instanceof GridLayoutManager)) {
                if (layoutManager instanceof LinearLayoutManager) {
                    linearLayoutManager = (LinearLayoutManager) layoutManager;
                }
                if (!this.isLoading || this.totalItemCount > this.lastVisibleItem + this.visibleThreshold) {
                }
                OnLoadMoreListener onLoadMoreListener2 = this.mOnLoadMoreListener;
                if (onLoadMoreListener2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mOnLoadMoreListener");
                } else {
                    onLoadMoreListener = onLoadMoreListener2;
                }
                onLoadMoreListener.onLoadMore();
                this.isLoading = true;
                return;
            }
            linearLayoutManager = (GridLayoutManager) layoutManager;
            findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        }
        this.lastVisibleItem = findLastVisibleItemPosition;
        if (this.isLoading) {
        }
    }

    public final void setLoaded() {
        this.isLoading = false;
    }

    public final void setOnLoadMoreListener(@NotNull OnLoadMoreListener mOnLoadMoreListener) {
        Intrinsics.checkNotNullParameter(mOnLoadMoreListener, "mOnLoadMoreListener");
        this.mOnLoadMoreListener = mOnLoadMoreListener;
    }
}
