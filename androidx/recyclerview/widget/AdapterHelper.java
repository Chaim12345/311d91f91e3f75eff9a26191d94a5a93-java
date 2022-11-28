package androidx.recyclerview.widget;

import androidx.core.util.Pools;
import androidx.recyclerview.widget.OpReorderer;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class AdapterHelper implements OpReorderer.Callback {
    private static final boolean DEBUG = false;
    private static final String TAG = "AHT";

    /* renamed from: a  reason: collision with root package name */
    final ArrayList f3473a;

    /* renamed from: b  reason: collision with root package name */
    final ArrayList f3474b;

    /* renamed from: c  reason: collision with root package name */
    final Callback f3475c;

    /* renamed from: d  reason: collision with root package name */
    Runnable f3476d;

    /* renamed from: e  reason: collision with root package name */
    final boolean f3477e;

    /* renamed from: f  reason: collision with root package name */
    final OpReorderer f3478f;
    private int mExistingUpdateTypes;
    private Pools.Pool<UpdateOp> mUpdateOpPool;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Callback {
        RecyclerView.ViewHolder findViewHolder(int i2);

        void markViewHoldersUpdated(int i2, int i3, Object obj);

        void offsetPositionsForAdd(int i2, int i3);

        void offsetPositionsForMove(int i2, int i3);

        void offsetPositionsForRemovingInvisible(int i2, int i3);

        void offsetPositionsForRemovingLaidOutOrNewView(int i2, int i3);

        void onDispatchFirstPass(UpdateOp updateOp);

        void onDispatchSecondPass(UpdateOp updateOp);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class UpdateOp {

        /* renamed from: a  reason: collision with root package name */
        int f3479a;

        /* renamed from: b  reason: collision with root package name */
        int f3480b;

        /* renamed from: c  reason: collision with root package name */
        Object f3481c;

        /* renamed from: d  reason: collision with root package name */
        int f3482d;

        UpdateOp(int i2, int i3, int i4, Object obj) {
            this.f3479a = i2;
            this.f3480b = i3;
            this.f3482d = i4;
            this.f3481c = obj;
        }

        String a() {
            int i2 = this.f3479a;
            return i2 != 1 ? i2 != 2 ? i2 != 4 ? i2 != 8 ? "??" : "mv" : "up" : "rm" : "add";
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof UpdateOp) {
                UpdateOp updateOp = (UpdateOp) obj;
                int i2 = this.f3479a;
                if (i2 != updateOp.f3479a) {
                    return false;
                }
                if (i2 == 8 && Math.abs(this.f3482d - this.f3480b) == 1 && this.f3482d == updateOp.f3480b && this.f3480b == updateOp.f3482d) {
                    return true;
                }
                if (this.f3482d == updateOp.f3482d && this.f3480b == updateOp.f3480b) {
                    Object obj2 = this.f3481c;
                    Object obj3 = updateOp.f3481c;
                    if (obj2 != null) {
                        if (!obj2.equals(obj3)) {
                            return false;
                        }
                    } else if (obj3 != null) {
                        return false;
                    }
                    return true;
                }
                return false;
            }
            return false;
        }

        public int hashCode() {
            return (((this.f3479a * 31) + this.f3480b) * 31) + this.f3482d;
        }

        public String toString() {
            return Integer.toHexString(System.identityHashCode(this)) + "[" + a() + ",s:" + this.f3480b + "c:" + this.f3482d + ",p:" + this.f3481c + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdapterHelper(Callback callback) {
        this(callback, false);
    }

    AdapterHelper(Callback callback, boolean z) {
        this.mUpdateOpPool = new Pools.SimplePool(30);
        this.f3473a = new ArrayList();
        this.f3474b = new ArrayList();
        this.mExistingUpdateTypes = 0;
        this.f3475c = callback;
        this.f3477e = z;
        this.f3478f = new OpReorderer(this);
    }

    private void applyAdd(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void applyMove(UpdateOp updateOp) {
        postponeAndUpdateViewHolders(updateOp);
    }

    private void applyRemove(UpdateOp updateOp) {
        boolean z;
        char c2;
        int i2 = updateOp.f3480b;
        int i3 = updateOp.f3482d + i2;
        char c3 = 65535;
        int i4 = i2;
        int i5 = 0;
        while (i4 < i3) {
            if (this.f3475c.findViewHolder(i4) != null || canFindInPreLayout(i4)) {
                if (c3 == 0) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(2, i2, i5, null));
                    z = true;
                } else {
                    z = false;
                }
                c2 = 1;
            } else {
                if (c3 == 1) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(2, i2, i5, null));
                    z = true;
                } else {
                    z = false;
                }
                c2 = 0;
            }
            if (z) {
                i4 -= i5;
                i3 -= i5;
                i5 = 1;
            } else {
                i5++;
            }
            i4++;
            c3 = c2;
        }
        if (i5 != updateOp.f3482d) {
            recycleUpdateOp(updateOp);
            updateOp = obtainUpdateOp(2, i2, i5, null);
        }
        if (c3 == 0) {
            dispatchAndUpdateViewHolders(updateOp);
        } else {
            postponeAndUpdateViewHolders(updateOp);
        }
    }

    private void applyUpdate(UpdateOp updateOp) {
        int i2 = updateOp.f3480b;
        int i3 = updateOp.f3482d + i2;
        int i4 = 0;
        boolean z = true;
        int i5 = i2;
        while (i2 < i3) {
            if (this.f3475c.findViewHolder(i2) != null || canFindInPreLayout(i2)) {
                if (!z) {
                    dispatchAndUpdateViewHolders(obtainUpdateOp(4, i5, i4, updateOp.f3481c));
                    i5 = i2;
                    i4 = 0;
                }
                z = true;
            } else {
                if (z) {
                    postponeAndUpdateViewHolders(obtainUpdateOp(4, i5, i4, updateOp.f3481c));
                    i5 = i2;
                    i4 = 0;
                }
                z = false;
            }
            i4++;
            i2++;
        }
        if (i4 != updateOp.f3482d) {
            Object obj = updateOp.f3481c;
            recycleUpdateOp(updateOp);
            updateOp = obtainUpdateOp(4, i5, i4, obj);
        }
        if (z) {
            postponeAndUpdateViewHolders(updateOp);
        } else {
            dispatchAndUpdateViewHolders(updateOp);
        }
    }

    private boolean canFindInPreLayout(int i2) {
        int size = this.f3474b.size();
        for (int i3 = 0; i3 < size; i3++) {
            UpdateOp updateOp = (UpdateOp) this.f3474b.get(i3);
            int i4 = updateOp.f3479a;
            if (i4 == 8) {
                if (e(updateOp.f3482d, i3 + 1) == i2) {
                    return true;
                }
            } else if (i4 == 1) {
                int i5 = updateOp.f3480b;
                int i6 = updateOp.f3482d + i5;
                while (i5 < i6) {
                    if (e(i5, i3 + 1) == i2) {
                        return true;
                    }
                    i5++;
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void dispatchAndUpdateViewHolders(UpdateOp updateOp) {
        int i2;
        int i3 = updateOp.f3479a;
        if (i3 == 1 || i3 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int updatePositionWithPostponed = updatePositionWithPostponed(updateOp.f3480b, i3);
        int i4 = updateOp.f3480b;
        int i5 = updateOp.f3479a;
        if (i5 == 2) {
            i2 = 0;
        } else if (i5 != 4) {
            throw new IllegalArgumentException("op should be remove or update." + updateOp);
        } else {
            i2 = 1;
        }
        int i6 = 1;
        for (int i7 = 1; i7 < updateOp.f3482d; i7++) {
            int updatePositionWithPostponed2 = updatePositionWithPostponed(updateOp.f3480b + (i2 * i7), updateOp.f3479a);
            int i8 = updateOp.f3479a;
            if (i8 == 2 ? updatePositionWithPostponed2 == updatePositionWithPostponed : i8 == 4 && updatePositionWithPostponed2 == updatePositionWithPostponed + 1) {
                i6++;
            } else {
                UpdateOp obtainUpdateOp = obtainUpdateOp(i8, updatePositionWithPostponed, i6, updateOp.f3481c);
                c(obtainUpdateOp, i4);
                recycleUpdateOp(obtainUpdateOp);
                if (updateOp.f3479a == 4) {
                    i4 += i6;
                }
                i6 = 1;
                updatePositionWithPostponed = updatePositionWithPostponed2;
            }
        }
        Object obj = updateOp.f3481c;
        recycleUpdateOp(updateOp);
        if (i6 > 0) {
            UpdateOp obtainUpdateOp2 = obtainUpdateOp(updateOp.f3479a, updatePositionWithPostponed, i6, obj);
            c(obtainUpdateOp2, i4);
            recycleUpdateOp(obtainUpdateOp2);
        }
    }

    private void postponeAndUpdateViewHolders(UpdateOp updateOp) {
        this.f3474b.add(updateOp);
        int i2 = updateOp.f3479a;
        if (i2 == 1) {
            this.f3475c.offsetPositionsForAdd(updateOp.f3480b, updateOp.f3482d);
        } else if (i2 == 2) {
            this.f3475c.offsetPositionsForRemovingLaidOutOrNewView(updateOp.f3480b, updateOp.f3482d);
        } else if (i2 == 4) {
            this.f3475c.markViewHoldersUpdated(updateOp.f3480b, updateOp.f3482d, updateOp.f3481c);
        } else if (i2 == 8) {
            this.f3475c.offsetPositionsForMove(updateOp.f3480b, updateOp.f3482d);
        } else {
            throw new IllegalArgumentException("Unknown update op type for " + updateOp);
        }
    }

    private int updatePositionWithPostponed(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        for (int size = this.f3474b.size() - 1; size >= 0; size--) {
            UpdateOp updateOp = (UpdateOp) this.f3474b.get(size);
            int i10 = updateOp.f3479a;
            if (i10 == 8) {
                int i11 = updateOp.f3480b;
                int i12 = updateOp.f3482d;
                if (i11 < i12) {
                    i6 = i11;
                    i5 = i12;
                } else {
                    i5 = i11;
                    i6 = i12;
                }
                if (i2 < i6 || i2 > i5) {
                    if (i2 < i11) {
                        if (i3 == 1) {
                            updateOp.f3480b = i11 + 1;
                            i7 = i12 + 1;
                        } else if (i3 == 2) {
                            updateOp.f3480b = i11 - 1;
                            i7 = i12 - 1;
                        }
                        updateOp.f3482d = i7;
                    }
                } else if (i6 == i11) {
                    if (i3 == 1) {
                        i9 = i12 + 1;
                    } else {
                        if (i3 == 2) {
                            i9 = i12 - 1;
                        }
                        i2++;
                    }
                    updateOp.f3482d = i9;
                    i2++;
                } else {
                    if (i3 == 1) {
                        i8 = i11 + 1;
                    } else {
                        if (i3 == 2) {
                            i8 = i11 - 1;
                        }
                        i2--;
                    }
                    updateOp.f3480b = i8;
                    i2--;
                }
            } else {
                int i13 = updateOp.f3480b;
                if (i13 > i2) {
                    if (i3 == 1) {
                        i4 = i13 + 1;
                    } else if (i3 == 2) {
                        i4 = i13 - 1;
                    }
                    updateOp.f3480b = i4;
                } else if (i10 == 1) {
                    i2 -= updateOp.f3482d;
                } else if (i10 == 2) {
                    i2 += updateOp.f3482d;
                }
            }
        }
        for (int size2 = this.f3474b.size() - 1; size2 >= 0; size2--) {
            UpdateOp updateOp2 = (UpdateOp) this.f3474b.get(size2);
            if (updateOp2.f3479a == 8) {
                int i14 = updateOp2.f3482d;
                if (i14 != updateOp2.f3480b && i14 >= 0) {
                }
                this.f3474b.remove(size2);
                recycleUpdateOp(updateOp2);
            } else {
                if (updateOp2.f3482d > 0) {
                }
                this.f3474b.remove(size2);
                recycleUpdateOp(updateOp2);
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        int size = this.f3474b.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f3475c.onDispatchSecondPass((UpdateOp) this.f3474b.get(i2));
        }
        n(this.f3474b);
        this.mExistingUpdateTypes = 0;
    }

    public int applyPendingUpdatesToPosition(int i2) {
        int size = this.f3473a.size();
        for (int i3 = 0; i3 < size; i3++) {
            UpdateOp updateOp = (UpdateOp) this.f3473a.get(i3);
            int i4 = updateOp.f3479a;
            if (i4 != 1) {
                if (i4 == 2) {
                    int i5 = updateOp.f3480b;
                    if (i5 <= i2) {
                        int i6 = updateOp.f3482d;
                        if (i5 + i6 > i2) {
                            return -1;
                        }
                        i2 -= i6;
                    } else {
                        continue;
                    }
                } else if (i4 == 8) {
                    int i7 = updateOp.f3480b;
                    if (i7 == i2) {
                        i2 = updateOp.f3482d;
                    } else {
                        if (i7 < i2) {
                            i2--;
                        }
                        if (updateOp.f3482d <= i2) {
                            i2++;
                        }
                    }
                }
            } else if (updateOp.f3480b <= i2) {
                i2 += updateOp.f3482d;
            }
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        a();
        int size = this.f3473a.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = (UpdateOp) this.f3473a.get(i2);
            int i3 = updateOp.f3479a;
            if (i3 == 1) {
                this.f3475c.onDispatchSecondPass(updateOp);
                this.f3475c.offsetPositionsForAdd(updateOp.f3480b, updateOp.f3482d);
            } else if (i3 == 2) {
                this.f3475c.onDispatchSecondPass(updateOp);
                this.f3475c.offsetPositionsForRemovingInvisible(updateOp.f3480b, updateOp.f3482d);
            } else if (i3 == 4) {
                this.f3475c.onDispatchSecondPass(updateOp);
                this.f3475c.markViewHoldersUpdated(updateOp.f3480b, updateOp.f3482d, updateOp.f3481c);
            } else if (i3 == 8) {
                this.f3475c.onDispatchSecondPass(updateOp);
                this.f3475c.offsetPositionsForMove(updateOp.f3480b, updateOp.f3482d);
            }
            Runnable runnable = this.f3476d;
            if (runnable != null) {
                runnable.run();
            }
        }
        n(this.f3473a);
        this.mExistingUpdateTypes = 0;
    }

    void c(UpdateOp updateOp, int i2) {
        this.f3475c.onDispatchFirstPass(updateOp);
        int i3 = updateOp.f3479a;
        if (i3 == 2) {
            this.f3475c.offsetPositionsForRemovingInvisible(i2, updateOp.f3482d);
        } else if (i3 != 4) {
            throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        } else {
            this.f3475c.markViewHoldersUpdated(i2, updateOp.f3482d, updateOp.f3481c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d(int i2) {
        return e(i2, 0);
    }

    int e(int i2, int i3) {
        int size = this.f3474b.size();
        while (i3 < size) {
            UpdateOp updateOp = (UpdateOp) this.f3474b.get(i3);
            int i4 = updateOp.f3479a;
            if (i4 == 8) {
                int i5 = updateOp.f3480b;
                if (i5 == i2) {
                    i2 = updateOp.f3482d;
                } else {
                    if (i5 < i2) {
                        i2--;
                    }
                    if (updateOp.f3482d <= i2) {
                        i2++;
                    }
                }
            } else {
                int i6 = updateOp.f3480b;
                if (i6 > i2) {
                    continue;
                } else if (i4 == 2) {
                    int i7 = updateOp.f3482d;
                    if (i2 < i6 + i7) {
                        return -1;
                    }
                    i2 -= i7;
                } else if (i4 == 1) {
                    i2 += updateOp.f3482d;
                }
            }
            i3++;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean f(int i2) {
        return (i2 & this.mExistingUpdateTypes) != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean g() {
        return this.f3473a.size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h() {
        return (this.f3474b.isEmpty() || this.f3473a.isEmpty()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean i(int i2, int i3, Object obj) {
        if (i3 < 1) {
            return false;
        }
        this.f3473a.add(obtainUpdateOp(4, i2, i3, obj));
        this.mExistingUpdateTypes |= 4;
        return this.f3473a.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean j(int i2, int i3) {
        if (i3 < 1) {
            return false;
        }
        this.f3473a.add(obtainUpdateOp(1, i2, i3, null));
        this.mExistingUpdateTypes |= 1;
        return this.f3473a.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k(int i2, int i3, int i4) {
        if (i2 == i3) {
            return false;
        }
        if (i4 == 1) {
            this.f3473a.add(obtainUpdateOp(8, i2, i3, null));
            this.mExistingUpdateTypes |= 8;
            return this.f3473a.size() == 1;
        }
        throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean l(int i2, int i3) {
        if (i3 < 1) {
            return false;
        }
        this.f3473a.add(obtainUpdateOp(2, i2, i3, null));
        this.mExistingUpdateTypes |= 2;
        return this.f3473a.size() == 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m() {
        this.f3478f.a(this.f3473a);
        int size = this.f3473a.size();
        for (int i2 = 0; i2 < size; i2++) {
            UpdateOp updateOp = (UpdateOp) this.f3473a.get(i2);
            int i3 = updateOp.f3479a;
            if (i3 == 1) {
                applyAdd(updateOp);
            } else if (i3 == 2) {
                applyRemove(updateOp);
            } else if (i3 == 4) {
                applyUpdate(updateOp);
            } else if (i3 == 8) {
                applyMove(updateOp);
            }
            Runnable runnable = this.f3476d;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f3473a.clear();
    }

    void n(List list) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            recycleUpdateOp((UpdateOp) list.get(i2));
        }
        list.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o() {
        n(this.f3473a);
        n(this.f3474b);
        this.mExistingUpdateTypes = 0;
    }

    @Override // androidx.recyclerview.widget.OpReorderer.Callback
    public UpdateOp obtainUpdateOp(int i2, int i3, int i4, Object obj) {
        UpdateOp acquire = this.mUpdateOpPool.acquire();
        if (acquire == null) {
            return new UpdateOp(i2, i3, i4, obj);
        }
        acquire.f3479a = i2;
        acquire.f3480b = i3;
        acquire.f3482d = i4;
        acquire.f3481c = obj;
        return acquire;
    }

    @Override // androidx.recyclerview.widget.OpReorderer.Callback
    public void recycleUpdateOp(UpdateOp updateOp) {
        if (this.f3477e) {
            return;
        }
        updateOp.f3481c = null;
        this.mUpdateOpPool.release(updateOp);
    }
}
