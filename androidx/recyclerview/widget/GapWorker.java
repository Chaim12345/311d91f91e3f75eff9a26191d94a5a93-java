package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import androidx.annotation.Nullable;
import androidx.core.os.TraceCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class GapWorker implements Runnable {

    /* renamed from: d  reason: collision with root package name */
    static final ThreadLocal f3575d = new ThreadLocal();

    /* renamed from: e  reason: collision with root package name */
    static Comparator f3576e = new Comparator<Task>() { // from class: androidx.recyclerview.widget.GapWorker.1
        @Override // java.util.Comparator
        public int compare(Task task, Task task2) {
            RecyclerView recyclerView = task.view;
            if ((recyclerView == null) != (task2.view == null)) {
                return recyclerView == null ? 1 : -1;
            }
            boolean z = task.immediate;
            if (z != task2.immediate) {
                return z ? -1 : 1;
            }
            int i2 = task2.viewVelocity - task.viewVelocity;
            if (i2 != 0) {
                return i2;
            }
            int i3 = task.distanceToItem - task2.distanceToItem;
            if (i3 != 0) {
                return i3;
            }
            return 0;
        }
    };

    /* renamed from: b  reason: collision with root package name */
    long f3578b;

    /* renamed from: c  reason: collision with root package name */
    long f3579c;

    /* renamed from: a  reason: collision with root package name */
    ArrayList f3577a = new ArrayList();
    private ArrayList<Task> mTasks = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    @SuppressLint({"VisibleForTests"})
    /* loaded from: classes.dex */
    public static class LayoutPrefetchRegistryImpl implements RecyclerView.LayoutManager.LayoutPrefetchRegistry {

        /* renamed from: a  reason: collision with root package name */
        int f3580a;

        /* renamed from: b  reason: collision with root package name */
        int f3581b;

        /* renamed from: c  reason: collision with root package name */
        int[] f3582c;

        /* renamed from: d  reason: collision with root package name */
        int f3583d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a() {
            int[] iArr = this.f3582c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            this.f3583d = 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.LayoutManager.LayoutPrefetchRegistry
        public void addPosition(int i2, int i3) {
            if (i2 < 0) {
                throw new IllegalArgumentException("Layout positions must be non-negative");
            }
            if (i3 < 0) {
                throw new IllegalArgumentException("Pixel distance must be non-negative");
            }
            int i4 = this.f3583d * 2;
            int[] iArr = this.f3582c;
            if (iArr == null) {
                int[] iArr2 = new int[4];
                this.f3582c = iArr2;
                Arrays.fill(iArr2, -1);
            } else if (i4 >= iArr.length) {
                int[] iArr3 = new int[i4 * 2];
                this.f3582c = iArr3;
                System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            }
            int[] iArr4 = this.f3582c;
            iArr4[i4] = i2;
            iArr4[i4 + 1] = i3;
            this.f3583d++;
        }

        void b(RecyclerView recyclerView, boolean z) {
            this.f3583d = 0;
            int[] iArr = this.f3582c;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.f3699k;
            if (recyclerView.f3698j == null || layoutManager == null || !layoutManager.isItemPrefetchEnabled()) {
                return;
            }
            if (z) {
                if (!recyclerView.f3691c.g()) {
                    layoutManager.collectInitialPrefetchPositions(recyclerView.f3698j.getItemCount(), this);
                }
            } else if (!recyclerView.hasPendingAdapterUpdates()) {
                layoutManager.collectAdjacentPrefetchPositions(this.f3580a, this.f3581b, recyclerView.B, this);
            }
            int i2 = this.f3583d;
            if (i2 > layoutManager.f3724i) {
                layoutManager.f3724i = i2;
                layoutManager.f3725j = z;
                recyclerView.f3689a.A();
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean c(int i2) {
            if (this.f3582c != null) {
                int i3 = this.f3583d * 2;
                for (int i4 = 0; i4 < i3; i4 += 2) {
                    if (this.f3582c[i4] == i2) {
                        return true;
                    }
                }
            }
            return false;
        }

        void d(int i2, int i3) {
            this.f3580a = i2;
            this.f3581b = i3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Task {
        public int distanceToItem;
        public boolean immediate;
        public int position;
        public RecyclerView view;
        public int viewVelocity;

        Task() {
        }

        public void clear() {
            this.immediate = false;
            this.viewVelocity = 0;
            this.distanceToItem = 0;
            this.view = null;
            this.position = 0;
        }
    }

    static boolean a(RecyclerView recyclerView, int i2) {
        int i3 = recyclerView.f3692d.i();
        for (int i4 = 0; i4 < i3; i4++) {
            RecyclerView.ViewHolder F = RecyclerView.F(recyclerView.f3692d.h(i4));
            if (F.mPosition == i2 && !F.isInvalid()) {
                return true;
            }
        }
        return false;
    }

    private void buildTaskList() {
        Task task;
        int size = this.f3577a.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            RecyclerView recyclerView = (RecyclerView) this.f3577a.get(i3);
            if (recyclerView.getWindowVisibility() == 0) {
                recyclerView.A.b(recyclerView, false);
                i2 += recyclerView.A.f3583d;
            }
        }
        this.mTasks.ensureCapacity(i2);
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            RecyclerView recyclerView2 = (RecyclerView) this.f3577a.get(i5);
            if (recyclerView2.getWindowVisibility() == 0) {
                LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView2.A;
                int abs = Math.abs(layoutPrefetchRegistryImpl.f3580a) + Math.abs(layoutPrefetchRegistryImpl.f3581b);
                for (int i6 = 0; i6 < layoutPrefetchRegistryImpl.f3583d * 2; i6 += 2) {
                    if (i4 >= this.mTasks.size()) {
                        task = new Task();
                        this.mTasks.add(task);
                    } else {
                        task = this.mTasks.get(i4);
                    }
                    int[] iArr = layoutPrefetchRegistryImpl.f3582c;
                    int i7 = iArr[i6 + 1];
                    task.immediate = i7 <= abs;
                    task.viewVelocity = abs;
                    task.distanceToItem = i7;
                    task.view = recyclerView2;
                    task.position = iArr[i6];
                    i4++;
                }
            }
        }
        Collections.sort(this.mTasks, f3576e);
    }

    private void flushTaskWithDeadline(Task task, long j2) {
        RecyclerView.ViewHolder prefetchPositionWithDeadline = prefetchPositionWithDeadline(task.view, task.position, task.immediate ? LongCompanionObject.MAX_VALUE : j2);
        if (prefetchPositionWithDeadline == null || prefetchPositionWithDeadline.mNestedRecyclerView == null || !prefetchPositionWithDeadline.isBound() || prefetchPositionWithDeadline.isInvalid()) {
            return;
        }
        prefetchInnerRecyclerViewWithDeadline(prefetchPositionWithDeadline.mNestedRecyclerView.get(), j2);
    }

    private void flushTasksWithDeadline(long j2) {
        for (int i2 = 0; i2 < this.mTasks.size(); i2++) {
            Task task = this.mTasks.get(i2);
            if (task.view == null) {
                return;
            }
            flushTaskWithDeadline(task, j2);
            task.clear();
        }
    }

    private void prefetchInnerRecyclerViewWithDeadline(@Nullable RecyclerView recyclerView, long j2) {
        if (recyclerView == null) {
            return;
        }
        if (recyclerView.v && recyclerView.f3692d.i() != 0) {
            recyclerView.Y();
        }
        LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl = recyclerView.A;
        layoutPrefetchRegistryImpl.b(recyclerView, true);
        if (layoutPrefetchRegistryImpl.f3583d != 0) {
            try {
                TraceCompat.beginSection("RV Nested Prefetch");
                recyclerView.B.b(recyclerView.f3698j);
                for (int i2 = 0; i2 < layoutPrefetchRegistryImpl.f3583d * 2; i2 += 2) {
                    prefetchPositionWithDeadline(recyclerView, layoutPrefetchRegistryImpl.f3582c[i2], j2);
                }
            } finally {
                TraceCompat.endSection();
            }
        }
    }

    private RecyclerView.ViewHolder prefetchPositionWithDeadline(RecyclerView recyclerView, int i2, long j2) {
        if (a(recyclerView, i2)) {
            return null;
        }
        RecyclerView.Recycler recycler = recyclerView.f3689a;
        try {
            recyclerView.S();
            RecyclerView.ViewHolder y = recycler.y(i2, false, j2);
            if (y != null) {
                if (!y.isBound() || y.isInvalid()) {
                    recycler.a(y, false);
                } else {
                    recycler.recycleView(y.itemView);
                }
            }
            return y;
        } finally {
            recyclerView.U(false);
        }
    }

    public void add(RecyclerView recyclerView) {
        this.f3577a.add(recyclerView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(RecyclerView recyclerView, int i2, int i3) {
        if (recyclerView.isAttachedToWindow() && this.f3578b == 0) {
            this.f3578b = recyclerView.getNanoTime();
            recyclerView.post(this);
        }
        recyclerView.A.d(i2, i3);
    }

    void c(long j2) {
        buildTaskList();
        flushTasksWithDeadline(j2);
    }

    public void remove(RecyclerView recyclerView) {
        this.f3577a.remove(recyclerView);
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            TraceCompat.beginSection("RV Prefetch");
            if (!this.f3577a.isEmpty()) {
                int size = this.f3577a.size();
                long j2 = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    RecyclerView recyclerView = (RecyclerView) this.f3577a.get(i2);
                    if (recyclerView.getWindowVisibility() == 0) {
                        j2 = Math.max(recyclerView.getDrawingTime(), j2);
                    }
                }
                if (j2 != 0) {
                    c(TimeUnit.MILLISECONDS.toNanos(j2) + this.f3579c);
                }
            }
        } finally {
            this.f3578b = 0L;
            TraceCompat.endSection();
        }
    }
}
