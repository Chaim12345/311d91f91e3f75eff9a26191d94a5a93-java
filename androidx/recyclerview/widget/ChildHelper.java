package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ChildHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "ChildrenHelper";

    /* renamed from: a  reason: collision with root package name */
    final Callback f3518a;

    /* renamed from: b  reason: collision with root package name */
    final Bucket f3519b = new Bucket();

    /* renamed from: c  reason: collision with root package name */
    final List f3520c = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Bucket {

        /* renamed from: a  reason: collision with root package name */
        long f3521a = 0;

        /* renamed from: b  reason: collision with root package name */
        Bucket f3522b;

        Bucket() {
        }

        private void ensureNext() {
            if (this.f3522b == null) {
                this.f3522b = new Bucket();
            }
        }

        void a(int i2) {
            if (i2 < 64) {
                this.f3521a &= ~(1 << i2);
                return;
            }
            Bucket bucket = this.f3522b;
            if (bucket != null) {
                bucket.a(i2 - 64);
            }
        }

        int b(int i2) {
            Bucket bucket = this.f3522b;
            return bucket == null ? i2 >= 64 ? Long.bitCount(this.f3521a) : Long.bitCount(this.f3521a & ((1 << i2) - 1)) : i2 < 64 ? Long.bitCount(this.f3521a & ((1 << i2) - 1)) : bucket.b(i2 - 64) + Long.bitCount(this.f3521a);
        }

        boolean c(int i2) {
            if (i2 < 64) {
                return (this.f3521a & (1 << i2)) != 0;
            }
            ensureNext();
            return this.f3522b.c(i2 - 64);
        }

        void d(int i2, boolean z) {
            if (i2 >= 64) {
                ensureNext();
                this.f3522b.d(i2 - 64, z);
                return;
            }
            long j2 = this.f3521a;
            boolean z2 = (Long.MIN_VALUE & j2) != 0;
            long j3 = (1 << i2) - 1;
            this.f3521a = ((j2 & (~j3)) << 1) | (j2 & j3);
            if (z) {
                g(i2);
            } else {
                a(i2);
            }
            if (z2 || this.f3522b != null) {
                ensureNext();
                this.f3522b.d(0, z2);
            }
        }

        boolean e(int i2) {
            if (i2 >= 64) {
                ensureNext();
                return this.f3522b.e(i2 - 64);
            }
            long j2 = 1 << i2;
            long j3 = this.f3521a;
            boolean z = (j3 & j2) != 0;
            long j4 = j3 & (~j2);
            this.f3521a = j4;
            long j5 = j2 - 1;
            this.f3521a = (j4 & j5) | Long.rotateRight((~j5) & j4, 1);
            Bucket bucket = this.f3522b;
            if (bucket != null) {
                if (bucket.c(0)) {
                    g(63);
                }
                this.f3522b.e(0);
            }
            return z;
        }

        void f() {
            this.f3521a = 0L;
            Bucket bucket = this.f3522b;
            if (bucket != null) {
                bucket.f();
            }
        }

        void g(int i2) {
            if (i2 < 64) {
                this.f3521a |= 1 << i2;
                return;
            }
            ensureNext();
            this.f3522b.g(i2 - 64);
        }

        public String toString() {
            if (this.f3522b == null) {
                return Long.toBinaryString(this.f3521a);
            }
            return this.f3522b.toString() + "xx" + Long.toBinaryString(this.f3521a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Callback {
        void addView(View view, int i2);

        void attachViewToParent(View view, int i2, ViewGroup.LayoutParams layoutParams);

        void detachViewFromParent(int i2);

        View getChildAt(int i2);

        int getChildCount();

        RecyclerView.ViewHolder getChildViewHolder(View view);

        int indexOfChild(View view);

        void onEnteredHiddenState(View view);

        void onLeftHiddenState(View view);

        void removeAllViews();

        void removeViewAt(int i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChildHelper(Callback callback) {
        this.f3518a = callback;
    }

    private int getOffset(int i2) {
        if (i2 < 0) {
            return -1;
        }
        int childCount = this.f3518a.getChildCount();
        int i3 = i2;
        while (i3 < childCount) {
            int b2 = i2 - (i3 - this.f3519b.b(i3));
            if (b2 == 0) {
                while (this.f3519b.c(i3)) {
                    i3++;
                }
                return i3;
            }
            i3 += b2;
        }
        return -1;
    }

    private void hideViewInternal(View view) {
        this.f3520c.add(view);
        this.f3518a.onEnteredHiddenState(view);
    }

    private boolean unhideViewInternal(View view) {
        if (this.f3520c.remove(view)) {
            this.f3518a.onLeftHiddenState(view);
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(View view, int i2, boolean z) {
        int childCount = i2 < 0 ? this.f3518a.getChildCount() : getOffset(i2);
        this.f3519b.d(childCount, z);
        if (z) {
            hideViewInternal(view);
        }
        this.f3518a.addView(view, childCount);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(View view, boolean z) {
        a(view, -1, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(View view, int i2, ViewGroup.LayoutParams layoutParams, boolean z) {
        int childCount = i2 < 0 ? this.f3518a.getChildCount() : getOffset(i2);
        this.f3519b.d(childCount, z);
        if (z) {
            hideViewInternal(view);
        }
        this.f3518a.attachViewToParent(view, childCount, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(int i2) {
        int offset = getOffset(i2);
        this.f3519b.e(offset);
        this.f3518a.detachViewFromParent(offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View e(int i2) {
        int size = this.f3520c.size();
        for (int i3 = 0; i3 < size; i3++) {
            View view = (View) this.f3520c.get(i3);
            RecyclerView.ViewHolder childViewHolder = this.f3518a.getChildViewHolder(view);
            if (childViewHolder.getLayoutPosition() == i2 && !childViewHolder.isInvalid() && !childViewHolder.isRemoved()) {
                return view;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View f(int i2) {
        return this.f3518a.getChildAt(getOffset(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.f3518a.getChildCount() - this.f3520c.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View h(int i2) {
        return this.f3518a.getChildAt(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int i() {
        return this.f3518a.getChildCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(View view) {
        int indexOfChild = this.f3518a.indexOfChild(view);
        if (indexOfChild >= 0) {
            this.f3519b.g(indexOfChild);
            hideViewInternal(view);
            return;
        }
        throw new IllegalArgumentException("view is not a child, cannot hide " + view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int k(View view) {
        int indexOfChild = this.f3518a.indexOfChild(view);
        if (indexOfChild == -1 || this.f3519b.c(indexOfChild)) {
            return -1;
        }
        return indexOfChild - this.f3519b.b(indexOfChild);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean l(View view) {
        return this.f3520c.contains(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m() {
        this.f3519b.f();
        for (int size = this.f3520c.size() - 1; size >= 0; size--) {
            this.f3518a.onLeftHiddenState((View) this.f3520c.get(size));
            this.f3520c.remove(size);
        }
        this.f3518a.removeAllViews();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(View view) {
        int indexOfChild = this.f3518a.indexOfChild(view);
        if (indexOfChild < 0) {
            return;
        }
        if (this.f3519b.e(indexOfChild)) {
            unhideViewInternal(view);
        }
        this.f3518a.removeViewAt(indexOfChild);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(int i2) {
        int offset = getOffset(i2);
        View childAt = this.f3518a.getChildAt(offset);
        if (childAt == null) {
            return;
        }
        if (this.f3519b.e(offset)) {
            unhideViewInternal(childAt);
        }
        this.f3518a.removeViewAt(offset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean p(View view) {
        int indexOfChild = this.f3518a.indexOfChild(view);
        if (indexOfChild == -1) {
            unhideViewInternal(view);
            return true;
        } else if (this.f3519b.c(indexOfChild)) {
            this.f3519b.e(indexOfChild);
            unhideViewInternal(view);
            this.f3518a.removeViewAt(indexOfChild);
            return true;
        } else {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void q(View view) {
        int indexOfChild = this.f3518a.indexOfChild(view);
        if (indexOfChild < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        } else if (this.f3519b.c(indexOfChild)) {
            this.f3519b.a(indexOfChild);
            unhideViewInternal(view);
        } else {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view);
        }
    }

    public String toString() {
        return this.f3519b.toString() + ", hidden list:" + this.f3520c.size();
    }
}
