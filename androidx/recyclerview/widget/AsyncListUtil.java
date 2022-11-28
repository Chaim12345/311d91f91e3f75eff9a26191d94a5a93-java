package androidx.recyclerview.widget;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.recyclerview.widget.ThreadUtil;
import androidx.recyclerview.widget.TileList;
/* loaded from: classes.dex */
public class AsyncListUtil<T> {

    /* renamed from: a  reason: collision with root package name */
    final Class f3495a;

    /* renamed from: b  reason: collision with root package name */
    final int f3496b;

    /* renamed from: c  reason: collision with root package name */
    final DataCallback f3497c;

    /* renamed from: d  reason: collision with root package name */
    final ViewCallback f3498d;

    /* renamed from: e  reason: collision with root package name */
    final TileList f3499e;

    /* renamed from: f  reason: collision with root package name */
    final ThreadUtil.MainThreadCallback f3500f;

    /* renamed from: g  reason: collision with root package name */
    final ThreadUtil.BackgroundCallback f3501g;

    /* renamed from: k  reason: collision with root package name */
    boolean f3505k;
    private final ThreadUtil.BackgroundCallback<T> mBackgroundCallback;
    private final ThreadUtil.MainThreadCallback<T> mMainThreadCallback;

    /* renamed from: h  reason: collision with root package name */
    final int[] f3502h = new int[2];

    /* renamed from: i  reason: collision with root package name */
    final int[] f3503i = new int[2];

    /* renamed from: j  reason: collision with root package name */
    final int[] f3504j = new int[2];
    private int mScrollHint = 0;

    /* renamed from: l  reason: collision with root package name */
    int f3506l = 0;

    /* renamed from: m  reason: collision with root package name */
    int f3507m = 0;

    /* renamed from: n  reason: collision with root package name */
    int f3508n = 0;

    /* renamed from: o  reason: collision with root package name */
    final SparseIntArray f3509o = new SparseIntArray();

    /* loaded from: classes.dex */
    public static abstract class DataCallback<T> {
        @WorkerThread
        public abstract void fillData(@NonNull T[] tArr, int i2, int i3);

        @WorkerThread
        public int getMaxCachedTiles() {
            return 10;
        }

        @WorkerThread
        public void recycleData(@NonNull T[] tArr, int i2) {
        }

        @WorkerThread
        public abstract int refreshData();
    }

    /* loaded from: classes.dex */
    public static abstract class ViewCallback {
        public static final int HINT_SCROLL_ASC = 2;
        public static final int HINT_SCROLL_DESC = 1;
        public static final int HINT_SCROLL_NONE = 0;

        @UiThread
        public void extendRangeInto(@NonNull int[] iArr, @NonNull int[] iArr2, int i2) {
            int i3 = (iArr[1] - iArr[0]) + 1;
            int i4 = i3 / 2;
            iArr2[0] = iArr[0] - (i2 == 1 ? i3 : i4);
            int i5 = iArr[1];
            if (i2 != 2) {
                i3 = i4;
            }
            iArr2[1] = i5 + i3;
        }

        @UiThread
        public abstract void getItemRangeInto(@NonNull int[] iArr);

        @UiThread
        public abstract void onDataRefresh();

        @UiThread
        public abstract void onItemLoaded(int i2);
    }

    public AsyncListUtil(@NonNull Class<T> cls, int i2, @NonNull DataCallback<T> dataCallback, @NonNull ViewCallback viewCallback) {
        ThreadUtil.MainThreadCallback<T> mainThreadCallback = (ThreadUtil.MainThreadCallback<T>) new ThreadUtil.MainThreadCallback<Object>() { // from class: androidx.recyclerview.widget.AsyncListUtil.1
            private boolean isRequestedGeneration(int i3) {
                return i3 == AsyncListUtil.this.f3508n;
            }

            private void recycleAllTiles() {
                for (int i3 = 0; i3 < AsyncListUtil.this.f3499e.size(); i3++) {
                    AsyncListUtil asyncListUtil = AsyncListUtil.this;
                    asyncListUtil.f3501g.recycleTile(asyncListUtil.f3499e.getAtIndex(i3));
                }
                AsyncListUtil.this.f3499e.clear();
            }

            @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
            public void addTile(int i3, TileList.Tile<Object> tile) {
                if (!isRequestedGeneration(i3)) {
                    AsyncListUtil.this.f3501g.recycleTile(tile);
                    return;
                }
                TileList.Tile<T> addOrReplace = AsyncListUtil.this.f3499e.addOrReplace(tile);
                if (addOrReplace != null) {
                    Log.e("AsyncListUtil", "duplicate tile @" + addOrReplace.mStartPosition);
                    AsyncListUtil.this.f3501g.recycleTile(addOrReplace);
                }
                int i4 = tile.mStartPosition + tile.mItemCount;
                int i5 = 0;
                while (i5 < AsyncListUtil.this.f3509o.size()) {
                    int keyAt = AsyncListUtil.this.f3509o.keyAt(i5);
                    if (tile.mStartPosition > keyAt || keyAt >= i4) {
                        i5++;
                    } else {
                        AsyncListUtil.this.f3509o.removeAt(i5);
                        AsyncListUtil.this.f3498d.onItemLoaded(keyAt);
                    }
                }
            }

            @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
            public void removeTile(int i3, int i4) {
                if (isRequestedGeneration(i3)) {
                    TileList.Tile<T> removeAtPos = AsyncListUtil.this.f3499e.removeAtPos(i4);
                    if (removeAtPos != null) {
                        AsyncListUtil.this.f3501g.recycleTile(removeAtPos);
                        return;
                    }
                    Log.e("AsyncListUtil", "tile not found @" + i4);
                }
            }

            @Override // androidx.recyclerview.widget.ThreadUtil.MainThreadCallback
            public void updateItemCount(int i3, int i4) {
                if (isRequestedGeneration(i3)) {
                    AsyncListUtil asyncListUtil = AsyncListUtil.this;
                    asyncListUtil.f3506l = i4;
                    asyncListUtil.f3498d.onDataRefresh();
                    AsyncListUtil asyncListUtil2 = AsyncListUtil.this;
                    asyncListUtil2.f3507m = asyncListUtil2.f3508n;
                    recycleAllTiles();
                    AsyncListUtil asyncListUtil3 = AsyncListUtil.this;
                    asyncListUtil3.f3505k = false;
                    asyncListUtil3.a();
                }
            }
        };
        this.mMainThreadCallback = mainThreadCallback;
        ThreadUtil.BackgroundCallback<T> backgroundCallback = (ThreadUtil.BackgroundCallback<T>) new ThreadUtil.BackgroundCallback<Object>() { // from class: androidx.recyclerview.widget.AsyncListUtil.2

            /* renamed from: a  reason: collision with root package name */
            final SparseBooleanArray f3511a = new SparseBooleanArray();
            private int mFirstRequiredTileStart;
            private int mGeneration;
            private int mItemCount;
            private int mLastRequiredTileStart;
            private TileList.Tile<Object> mRecycledRoot;

            private TileList.Tile<Object> acquireTile() {
                TileList.Tile<Object> tile = this.mRecycledRoot;
                if (tile != null) {
                    this.mRecycledRoot = tile.f3817a;
                    return tile;
                }
                AsyncListUtil asyncListUtil = AsyncListUtil.this;
                return new TileList.Tile<>(asyncListUtil.f3495a, asyncListUtil.f3496b);
            }

            private void addTile(TileList.Tile<Object> tile) {
                this.f3511a.put(tile.mStartPosition, true);
                AsyncListUtil.this.f3500f.addTile(this.mGeneration, tile);
            }

            private void flushTileCache(int i3) {
                int maxCachedTiles = AsyncListUtil.this.f3497c.getMaxCachedTiles();
                while (this.f3511a.size() >= maxCachedTiles) {
                    int keyAt = this.f3511a.keyAt(0);
                    SparseBooleanArray sparseBooleanArray = this.f3511a;
                    int keyAt2 = sparseBooleanArray.keyAt(sparseBooleanArray.size() - 1);
                    int i4 = this.mFirstRequiredTileStart - keyAt;
                    int i5 = keyAt2 - this.mLastRequiredTileStart;
                    if (i4 > 0 && (i4 >= i5 || i3 == 2)) {
                        removeTile(keyAt);
                    } else if (i5 <= 0) {
                        return;
                    } else {
                        if (i4 >= i5 && i3 != 1) {
                            return;
                        }
                        removeTile(keyAt2);
                    }
                }
            }

            private int getTileStart(int i3) {
                return i3 - (i3 % AsyncListUtil.this.f3496b);
            }

            private boolean isTileLoaded(int i3) {
                return this.f3511a.get(i3);
            }

            private void log(String str, Object... objArr) {
                StringBuilder sb = new StringBuilder();
                sb.append("[BKGR] ");
                sb.append(String.format(str, objArr));
            }

            private void removeTile(int i3) {
                this.f3511a.delete(i3);
                AsyncListUtil.this.f3500f.removeTile(this.mGeneration, i3);
            }

            private void requestTiles(int i3, int i4, int i5, boolean z) {
                int i6 = i3;
                while (i6 <= i4) {
                    AsyncListUtil.this.f3501g.loadTile(z ? (i4 + i3) - i6 : i6, i5);
                    i6 += AsyncListUtil.this.f3496b;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
            public void loadTile(int i3, int i4) {
                if (isTileLoaded(i3)) {
                    return;
                }
                TileList.Tile<Object> acquireTile = acquireTile();
                acquireTile.mStartPosition = i3;
                int min = Math.min(AsyncListUtil.this.f3496b, this.mItemCount - i3);
                acquireTile.mItemCount = min;
                AsyncListUtil.this.f3497c.fillData(acquireTile.mItems, acquireTile.mStartPosition, min);
                flushTileCache(i4);
                addTile(acquireTile);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
            public void recycleTile(TileList.Tile<Object> tile) {
                AsyncListUtil.this.f3497c.recycleData(tile.mItems, tile.mItemCount);
                tile.f3817a = this.mRecycledRoot;
                this.mRecycledRoot = tile;
            }

            @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
            public void refresh(int i3) {
                this.mGeneration = i3;
                this.f3511a.clear();
                int refreshData = AsyncListUtil.this.f3497c.refreshData();
                this.mItemCount = refreshData;
                AsyncListUtil.this.f3500f.updateItemCount(this.mGeneration, refreshData);
            }

            @Override // androidx.recyclerview.widget.ThreadUtil.BackgroundCallback
            public void updateRange(int i3, int i4, int i5, int i6, int i7) {
                if (i3 > i4) {
                    return;
                }
                int tileStart = getTileStart(i3);
                int tileStart2 = getTileStart(i4);
                this.mFirstRequiredTileStart = getTileStart(i5);
                int tileStart3 = getTileStart(i6);
                this.mLastRequiredTileStart = tileStart3;
                if (i7 == 1) {
                    requestTiles(this.mFirstRequiredTileStart, tileStart2, i7, true);
                    requestTiles(tileStart2 + AsyncListUtil.this.f3496b, this.mLastRequiredTileStart, i7, false);
                    return;
                }
                requestTiles(tileStart, tileStart3, i7, false);
                requestTiles(this.mFirstRequiredTileStart, tileStart - AsyncListUtil.this.f3496b, i7, true);
            }
        };
        this.mBackgroundCallback = backgroundCallback;
        this.f3495a = cls;
        this.f3496b = i2;
        this.f3497c = dataCallback;
        this.f3498d = viewCallback;
        this.f3499e = new TileList(i2);
        MessageThreadUtil messageThreadUtil = new MessageThreadUtil();
        this.f3500f = messageThreadUtil.getMainThreadProxy(mainThreadCallback);
        this.f3501g = messageThreadUtil.getBackgroundProxy(backgroundCallback);
        refresh();
    }

    private boolean isRefreshPending() {
        return this.f3508n != this.f3507m;
    }

    void a() {
        this.f3498d.getItemRangeInto(this.f3502h);
        int[] iArr = this.f3502h;
        if (iArr[0] > iArr[1] || iArr[0] < 0 || iArr[1] >= this.f3506l) {
            return;
        }
        if (this.f3505k) {
            int i2 = iArr[0];
            int[] iArr2 = this.f3503i;
            if (i2 <= iArr2[1] && iArr2[0] <= iArr[1]) {
                if (iArr[0] < iArr2[0]) {
                    this.mScrollHint = 1;
                } else if (iArr[0] > iArr2[0]) {
                    this.mScrollHint = 2;
                }
                int[] iArr3 = this.f3503i;
                iArr3[0] = iArr[0];
                iArr3[1] = iArr[1];
                this.f3498d.extendRangeInto(iArr, this.f3504j, this.mScrollHint);
                int[] iArr4 = this.f3504j;
                iArr4[0] = Math.min(this.f3502h[0], Math.max(iArr4[0], 0));
                int[] iArr5 = this.f3504j;
                iArr5[1] = Math.max(this.f3502h[1], Math.min(iArr5[1], this.f3506l - 1));
                ThreadUtil.BackgroundCallback backgroundCallback = this.f3501g;
                int[] iArr6 = this.f3502h;
                int i3 = iArr6[0];
                int i4 = iArr6[1];
                int[] iArr7 = this.f3504j;
                backgroundCallback.updateRange(i3, i4, iArr7[0], iArr7[1], this.mScrollHint);
            }
        }
        this.mScrollHint = 0;
        int[] iArr32 = this.f3503i;
        iArr32[0] = iArr[0];
        iArr32[1] = iArr[1];
        this.f3498d.extendRangeInto(iArr, this.f3504j, this.mScrollHint);
        int[] iArr42 = this.f3504j;
        iArr42[0] = Math.min(this.f3502h[0], Math.max(iArr42[0], 0));
        int[] iArr52 = this.f3504j;
        iArr52[1] = Math.max(this.f3502h[1], Math.min(iArr52[1], this.f3506l - 1));
        ThreadUtil.BackgroundCallback backgroundCallback2 = this.f3501g;
        int[] iArr62 = this.f3502h;
        int i32 = iArr62[0];
        int i42 = iArr62[1];
        int[] iArr72 = this.f3504j;
        backgroundCallback2.updateRange(i32, i42, iArr72[0], iArr72[1], this.mScrollHint);
    }

    @Nullable
    public T getItem(int i2) {
        if (i2 < 0 || i2 >= this.f3506l) {
            throw new IndexOutOfBoundsException(i2 + " is not within 0 and " + this.f3506l);
        }
        T t2 = (T) this.f3499e.getItemAt(i2);
        if (t2 == null && !isRefreshPending()) {
            this.f3509o.put(i2, 0);
        }
        return t2;
    }

    public int getItemCount() {
        return this.f3506l;
    }

    public void onRangeChanged() {
        if (isRefreshPending()) {
            return;
        }
        a();
        this.f3505k = true;
    }

    public void refresh() {
        this.f3509o.clear();
        ThreadUtil.BackgroundCallback backgroundCallback = this.f3501g;
        int i2 = this.f3508n + 1;
        this.f3508n = i2;
        backgroundCallback.refresh(i2);
    }
}
