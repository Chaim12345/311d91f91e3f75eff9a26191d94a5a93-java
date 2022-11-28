package androidx.recyclerview.widget;

import androidx.recyclerview.widget.AdapterHelper;
import java.util.List;
/* loaded from: classes.dex */
class OpReorderer {

    /* renamed from: a  reason: collision with root package name */
    final Callback f3685a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Callback {
        AdapterHelper.UpdateOp obtainUpdateOp(int i2, int i3, int i4, Object obj);

        void recycleUpdateOp(AdapterHelper.UpdateOp updateOp);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpReorderer(Callback callback) {
        this.f3685a = callback;
    }

    private int getLastMoveOutOfOrder(List<AdapterHelper.UpdateOp> list) {
        boolean z = false;
        for (int size = list.size() - 1; size >= 0; size--) {
            if (list.get(size).f3479a != 8) {
                z = true;
            } else if (z) {
                return size;
            }
        }
        return -1;
    }

    private void swapMoveAdd(List<AdapterHelper.UpdateOp> list, int i2, AdapterHelper.UpdateOp updateOp, int i3, AdapterHelper.UpdateOp updateOp2) {
        int i4 = updateOp.f3482d;
        int i5 = updateOp2.f3480b;
        int i6 = i4 < i5 ? -1 : 0;
        int i7 = updateOp.f3480b;
        if (i7 < i5) {
            i6++;
        }
        if (i5 <= i7) {
            updateOp.f3480b = i7 + updateOp2.f3482d;
        }
        int i8 = updateOp2.f3480b;
        if (i8 <= i4) {
            updateOp.f3482d = i4 + updateOp2.f3482d;
        }
        updateOp2.f3480b = i8 + i6;
        list.set(i2, updateOp2);
        list.set(i3, updateOp);
    }

    private void swapMoveOp(List<AdapterHelper.UpdateOp> list, int i2, int i3) {
        AdapterHelper.UpdateOp updateOp = list.get(i2);
        AdapterHelper.UpdateOp updateOp2 = list.get(i3);
        int i4 = updateOp2.f3479a;
        if (i4 == 1) {
            swapMoveAdd(list, i2, updateOp, i3, updateOp2);
        } else if (i4 == 2) {
            b(list, i2, updateOp, i3, updateOp2);
        } else if (i4 != 4) {
        } else {
            c(list, i2, updateOp, i3, updateOp2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(List list) {
        while (true) {
            int lastMoveOutOfOrder = getLastMoveOutOfOrder(list);
            if (lastMoveOutOfOrder == -1) {
                return;
            }
            swapMoveOp(list, lastMoveOutOfOrder, lastMoveOutOfOrder + 1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x009e, code lost:
        if (r0 > r14.f3480b) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00c8, code lost:
        if (r0 >= r14.f3480b) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00ca, code lost:
        r12.f3482d = r0 - r14.f3482d;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void b(List list, int i2, AdapterHelper.UpdateOp updateOp, int i3, AdapterHelper.UpdateOp updateOp2) {
        boolean z;
        int i4;
        int i5 = updateOp.f3480b;
        int i6 = updateOp.f3482d;
        boolean z2 = false;
        int i7 = updateOp2.f3480b;
        if (i5 < i6) {
            if (i7 == i5 && updateOp2.f3482d == i6 - i5) {
                z = false;
                z2 = true;
            } else {
                z = false;
            }
        } else if (i7 == i6 + 1 && updateOp2.f3482d == i5 - i6) {
            z = true;
            z2 = true;
        } else {
            z = true;
        }
        int i8 = updateOp2.f3480b;
        if (i6 < i8) {
            updateOp2.f3480b = i8 - 1;
        } else {
            int i9 = updateOp2.f3482d;
            if (i6 < i8 + i9) {
                updateOp2.f3482d = i9 - 1;
                updateOp.f3479a = 2;
                updateOp.f3482d = 1;
                if (updateOp2.f3482d == 0) {
                    list.remove(i3);
                    this.f3685a.recycleUpdateOp(updateOp2);
                    return;
                }
                return;
            }
        }
        int i10 = updateOp.f3480b;
        int i11 = updateOp2.f3480b;
        AdapterHelper.UpdateOp updateOp3 = null;
        if (i10 <= i11) {
            updateOp2.f3480b = i11 + 1;
        } else {
            int i12 = updateOp2.f3482d;
            if (i10 < i11 + i12) {
                updateOp3 = this.f3685a.obtainUpdateOp(2, i10 + 1, (i11 + i12) - i10, null);
                updateOp2.f3482d = updateOp.f3480b - updateOp2.f3480b;
            }
        }
        if (z2) {
            list.set(i2, updateOp2);
            list.remove(i3);
            this.f3685a.recycleUpdateOp(updateOp);
            return;
        }
        if (z) {
            if (updateOp3 != null) {
                int i13 = updateOp.f3480b;
                if (i13 > updateOp3.f3480b) {
                    updateOp.f3480b = i13 - updateOp3.f3482d;
                }
                int i14 = updateOp.f3482d;
                if (i14 > updateOp3.f3480b) {
                    updateOp.f3482d = i14 - updateOp3.f3482d;
                }
            }
            int i15 = updateOp.f3480b;
            if (i15 > updateOp2.f3480b) {
                updateOp.f3480b = i15 - updateOp2.f3482d;
            }
            i4 = updateOp.f3482d;
        } else {
            if (updateOp3 != null) {
                int i16 = updateOp.f3480b;
                if (i16 >= updateOp3.f3480b) {
                    updateOp.f3480b = i16 - updateOp3.f3482d;
                }
                int i17 = updateOp.f3482d;
                if (i17 >= updateOp3.f3480b) {
                    updateOp.f3482d = i17 - updateOp3.f3482d;
                }
            }
            int i18 = updateOp.f3480b;
            if (i18 >= updateOp2.f3480b) {
                updateOp.f3480b = i18 - updateOp2.f3482d;
            }
            i4 = updateOp.f3482d;
        }
        list.set(i2, updateOp2);
        if (updateOp.f3480b != updateOp.f3482d) {
            list.set(i3, updateOp);
        } else {
            list.remove(i3);
        }
        if (updateOp3 != null) {
            list.add(i2, updateOp3);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void c(List list, int i2, AdapterHelper.UpdateOp updateOp, int i3, AdapterHelper.UpdateOp updateOp2) {
        AdapterHelper.UpdateOp obtainUpdateOp;
        int i4;
        int i5;
        int i6 = updateOp.f3482d;
        int i7 = updateOp2.f3480b;
        AdapterHelper.UpdateOp updateOp3 = null;
        if (i6 < i7) {
            updateOp2.f3480b = i7 - 1;
        } else {
            int i8 = updateOp2.f3482d;
            if (i6 < i7 + i8) {
                updateOp2.f3482d = i8 - 1;
                obtainUpdateOp = this.f3685a.obtainUpdateOp(4, updateOp.f3480b, 1, updateOp2.f3481c);
                i4 = updateOp.f3480b;
                i5 = updateOp2.f3480b;
                if (i4 > i5) {
                    updateOp2.f3480b = i5 + 1;
                } else {
                    int i9 = updateOp2.f3482d;
                    if (i4 < i5 + i9) {
                        int i10 = (i5 + i9) - i4;
                        updateOp3 = this.f3685a.obtainUpdateOp(4, i4 + 1, i10, updateOp2.f3481c);
                        updateOp2.f3482d -= i10;
                    }
                }
                list.set(i3, updateOp);
                if (updateOp2.f3482d <= 0) {
                    list.set(i2, updateOp2);
                } else {
                    list.remove(i2);
                    this.f3685a.recycleUpdateOp(updateOp2);
                }
                if (obtainUpdateOp != null) {
                    list.add(i2, obtainUpdateOp);
                }
                if (updateOp3 == null) {
                    list.add(i2, updateOp3);
                    return;
                }
                return;
            }
        }
        obtainUpdateOp = null;
        i4 = updateOp.f3480b;
        i5 = updateOp2.f3480b;
        if (i4 > i5) {
        }
        list.set(i3, updateOp);
        if (updateOp2.f3482d <= 0) {
        }
        if (obtainUpdateOp != null) {
        }
        if (updateOp3 == null) {
        }
    }
}
