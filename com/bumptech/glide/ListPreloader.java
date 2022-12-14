package com.bumptech.glide;

import android.graphics.drawable.Drawable;
import android.widget.AbsListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.util.List;
import java.util.Queue;
/* loaded from: classes.dex */
public class ListPreloader<T> implements AbsListView.OnScrollListener {
    private int lastEnd;
    private int lastStart;
    private final int maxPreload;
    private final PreloadSizeProvider<T> preloadDimensionProvider;
    private final PreloadModelProvider<T> preloadModelProvider;
    private final PreloadTargetQueue preloadTargetQueue;
    private final RequestManager requestManager;
    private int totalItemCount;
    private int lastFirstVisible = -1;
    private boolean isIncreasing = true;

    /* loaded from: classes.dex */
    public interface PreloadModelProvider<U> {
        @NonNull
        List<U> getPreloadItems(int i2);

        @Nullable
        RequestBuilder<?> getPreloadRequestBuilder(@NonNull U u);
    }

    /* loaded from: classes.dex */
    public interface PreloadSizeProvider<T> {
        @Nullable
        int[] getPreloadSize(@NonNull T t2, int i2, int i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class PreloadTarget implements Target<Object> {

        /* renamed from: a  reason: collision with root package name */
        int f4660a;

        /* renamed from: b  reason: collision with root package name */
        int f4661b;
        @Nullable
        private Request request;

        PreloadTarget() {
        }

        @Override // com.bumptech.glide.request.target.Target
        @Nullable
        public Request getRequest() {
            return this.request;
        }

        @Override // com.bumptech.glide.request.target.Target
        public void getSize(@NonNull SizeReadyCallback sizeReadyCallback) {
            sizeReadyCallback.onSizeReady(this.f4661b, this.f4660a);
        }

        @Override // com.bumptech.glide.manager.LifecycleListener
        public void onDestroy() {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(@Nullable Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadFailed(@Nullable Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadStarted(@Nullable Drawable drawable) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void onResourceReady(@NonNull Object obj, @Nullable Transition<? super Object> transition) {
        }

        @Override // com.bumptech.glide.manager.LifecycleListener
        public void onStart() {
        }

        @Override // com.bumptech.glide.manager.LifecycleListener
        public void onStop() {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void removeCallback(@NonNull SizeReadyCallback sizeReadyCallback) {
        }

        @Override // com.bumptech.glide.request.target.Target
        public void setRequest(@Nullable Request request) {
            this.request = request;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class PreloadTargetQueue {

        /* renamed from: a  reason: collision with root package name */
        final Queue f4662a;

        PreloadTargetQueue(int i2) {
            this.f4662a = Util.createQueue(i2);
            for (int i3 = 0; i3 < i2; i3++) {
                this.f4662a.offer(new PreloadTarget());
            }
        }

        public PreloadTarget next(int i2, int i3) {
            PreloadTarget preloadTarget = (PreloadTarget) this.f4662a.poll();
            this.f4662a.offer(preloadTarget);
            preloadTarget.f4661b = i2;
            preloadTarget.f4660a = i3;
            return preloadTarget;
        }
    }

    public ListPreloader(@NonNull RequestManager requestManager, @NonNull PreloadModelProvider<T> preloadModelProvider, @NonNull PreloadSizeProvider<T> preloadSizeProvider, int i2) {
        this.requestManager = requestManager;
        this.preloadModelProvider = preloadModelProvider;
        this.preloadDimensionProvider = preloadSizeProvider;
        this.maxPreload = i2;
        this.preloadTargetQueue = new PreloadTargetQueue(i2 + 1);
    }

    private void cancelAll() {
        for (int i2 = 0; i2 < this.preloadTargetQueue.f4662a.size(); i2++) {
            this.requestManager.clear(this.preloadTargetQueue.next(0, 0));
        }
    }

    private void preload(int i2, int i3) {
        int min;
        int i4;
        if (i2 < i3) {
            i4 = Math.max(this.lastEnd, i2);
            min = i3;
        } else {
            min = Math.min(this.lastStart, i2);
            i4 = i3;
        }
        int min2 = Math.min(this.totalItemCount, min);
        int min3 = Math.min(this.totalItemCount, Math.max(0, i4));
        if (i2 < i3) {
            for (int i5 = min3; i5 < min2; i5++) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i5), i5, true);
            }
        } else {
            for (int i6 = min2 - 1; i6 >= min3; i6--) {
                preloadAdapterPosition(this.preloadModelProvider.getPreloadItems(i6), i6, false);
            }
        }
        this.lastStart = min3;
        this.lastEnd = min2;
    }

    private void preload(int i2, boolean z) {
        if (this.isIncreasing != z) {
            this.isIncreasing = z;
            cancelAll();
        }
        preload(i2, (z ? this.maxPreload : -this.maxPreload) + i2);
    }

    private void preloadAdapterPosition(List<T> list, int i2, boolean z) {
        int size = list.size();
        if (z) {
            for (int i3 = 0; i3 < size; i3++) {
                preloadItem(list.get(i3), i2, i3);
            }
            return;
        }
        for (int i4 = size - 1; i4 >= 0; i4--) {
            preloadItem(list.get(i4), i2, i4);
        }
    }

    private void preloadItem(@Nullable T t2, int i2, int i3) {
        int[] preloadSize;
        RequestBuilder<?> preloadRequestBuilder;
        if (t2 == null || (preloadSize = this.preloadDimensionProvider.getPreloadSize(t2, i2, i3)) == null || (preloadRequestBuilder = this.preloadModelProvider.getPreloadRequestBuilder(t2)) == null) {
            return;
        }
        preloadRequestBuilder.into((RequestBuilder<?>) this.preloadTargetQueue.next(preloadSize[0], preloadSize[1]));
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i2, int i3, int i4) {
        this.totalItemCount = i4;
        int i5 = this.lastFirstVisible;
        if (i2 > i5) {
            preload(i3 + i2, true);
        } else if (i2 < i5) {
            preload(i2, false);
        }
        this.lastFirstVisible = i2;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i2) {
    }
}
