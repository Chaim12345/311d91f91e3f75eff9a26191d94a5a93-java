package com.simform.refresh;

import kotlin.Metadata;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\u0004\u001a\u00020\u0002H&J\b\u0010\u0005\u001a\u00020\u0002H&J\b\u0010\u0006\u001a\u00020\u0002H&J\b\u0010\u0007\u001a\u00020\u0002H&J\u0018\u0010\n\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH&¨\u0006\u000b"}, d2 = {"Lcom/simform/refresh/RefreshCallbacks;", "", "", "reset", "refreshing", "refreshComplete", "pullToRefresh", "releaseToRefresh", "", "pullDistance", "pullProgress", "sspulltorefresh_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public interface RefreshCallbacks {
    void pullProgress(float f2, float f3);

    void pullToRefresh();

    void refreshComplete();

    void refreshing();

    void releaseToRefresh();

    void reset();
}
