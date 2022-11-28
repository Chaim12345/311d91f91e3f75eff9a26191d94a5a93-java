package androidx.recyclerview.widget;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.LongSparseArray;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools;
import androidx.recyclerview.widget.RecyclerView;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ViewInfoStore {
    private static final boolean DEBUG = false;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    final SimpleArrayMap f3825a = new SimpleArrayMap();
    @VisibleForTesting

    /* renamed from: b  reason: collision with root package name */
    final LongSparseArray f3826b = new LongSparseArray();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class InfoRecord {

        /* renamed from: d  reason: collision with root package name */
        static Pools.Pool f3827d = new Pools.SimplePool(20);

        /* renamed from: a  reason: collision with root package name */
        int f3828a;
        @Nullable

        /* renamed from: b  reason: collision with root package name */
        RecyclerView.ItemAnimator.ItemHolderInfo f3829b;
        @Nullable

        /* renamed from: c  reason: collision with root package name */
        RecyclerView.ItemAnimator.ItemHolderInfo f3830c;

        private InfoRecord() {
        }

        static void a() {
            do {
            } while (f3827d.acquire() != null);
        }

        static InfoRecord b() {
            InfoRecord infoRecord = (InfoRecord) f3827d.acquire();
            return infoRecord == null ? new InfoRecord() : infoRecord;
        }

        static void c(InfoRecord infoRecord) {
            infoRecord.f3828a = 0;
            infoRecord.f3829b = null;
            infoRecord.f3830c = null;
            f3827d.release(infoRecord);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface ProcessCallback {
        void processAppeared(RecyclerView.ViewHolder viewHolder, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);

        void processDisappeared(RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @Nullable RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);

        void processPersistent(RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo, @NonNull RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2);

        void unused(RecyclerView.ViewHolder viewHolder);
    }

    private RecyclerView.ItemAnimator.ItemHolderInfo popFromLayoutStep(RecyclerView.ViewHolder viewHolder, int i2) {
        InfoRecord infoRecord;
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo;
        int indexOfKey = this.f3825a.indexOfKey(viewHolder);
        if (indexOfKey >= 0 && (infoRecord = (InfoRecord) this.f3825a.valueAt(indexOfKey)) != null) {
            int i3 = infoRecord.f3828a;
            if ((i3 & i2) != 0) {
                int i4 = (~i2) & i3;
                infoRecord.f3828a = i4;
                if (i2 == 4) {
                    itemHolderInfo = infoRecord.f3829b;
                } else if (i2 != 8) {
                    throw new IllegalArgumentException("Must provide flag PRE or POST");
                } else {
                    itemHolderInfo = infoRecord.f3830c;
                }
                if ((i4 & 12) == 0) {
                    this.f3825a.removeAt(indexOfKey);
                    InfoRecord.c(infoRecord);
                }
                return itemHolderInfo;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        InfoRecord infoRecord = (InfoRecord) this.f3825a.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.b();
            this.f3825a.put(viewHolder, infoRecord);
        }
        infoRecord.f3828a |= 2;
        infoRecord.f3829b = itemHolderInfo;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(RecyclerView.ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.f3825a.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.b();
            this.f3825a.put(viewHolder, infoRecord);
        }
        infoRecord.f3828a |= 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(long j2, RecyclerView.ViewHolder viewHolder) {
        this.f3826b.put(j2, viewHolder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        InfoRecord infoRecord = (InfoRecord) this.f3825a.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.b();
            this.f3825a.put(viewHolder, infoRecord);
        }
        infoRecord.f3830c = itemHolderInfo;
        infoRecord.f3828a |= 8;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(RecyclerView.ViewHolder viewHolder, RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo) {
        InfoRecord infoRecord = (InfoRecord) this.f3825a.get(viewHolder);
        if (infoRecord == null) {
            infoRecord = InfoRecord.b();
            this.f3825a.put(viewHolder, infoRecord);
        }
        infoRecord.f3829b = itemHolderInfo;
        infoRecord.f3828a |= 4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.f3825a.clear();
        this.f3826b.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecyclerView.ViewHolder g(long j2) {
        return (RecyclerView.ViewHolder) this.f3826b.get(j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean h(RecyclerView.ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.f3825a.get(viewHolder);
        return (infoRecord == null || (infoRecord.f3828a & 1) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean i(RecyclerView.ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.f3825a.get(viewHolder);
        return (infoRecord == null || (infoRecord.f3828a & 4) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j() {
        InfoRecord.a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public RecyclerView.ItemAnimator.ItemHolderInfo k(RecyclerView.ViewHolder viewHolder) {
        return popFromLayoutStep(viewHolder, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public RecyclerView.ItemAnimator.ItemHolderInfo l(RecyclerView.ViewHolder viewHolder) {
        return popFromLayoutStep(viewHolder, 4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(ProcessCallback processCallback) {
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo;
        RecyclerView.ItemAnimator.ItemHolderInfo itemHolderInfo2;
        for (int size = this.f3825a.size() - 1; size >= 0; size--) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) this.f3825a.keyAt(size);
            InfoRecord infoRecord = (InfoRecord) this.f3825a.removeAt(size);
            int i2 = infoRecord.f3828a;
            if ((i2 & 3) != 3) {
                if ((i2 & 1) != 0) {
                    itemHolderInfo = infoRecord.f3829b;
                    itemHolderInfo2 = itemHolderInfo != null ? infoRecord.f3830c : null;
                } else {
                    if ((i2 & 14) != 14) {
                        if ((i2 & 12) == 12) {
                            processCallback.processPersistent(viewHolder, infoRecord.f3829b, infoRecord.f3830c);
                        } else if ((i2 & 4) != 0) {
                            itemHolderInfo = infoRecord.f3829b;
                        } else if ((i2 & 8) == 0) {
                        }
                        InfoRecord.c(infoRecord);
                    }
                    processCallback.processAppeared(viewHolder, infoRecord.f3829b, infoRecord.f3830c);
                    InfoRecord.c(infoRecord);
                }
                processCallback.processDisappeared(viewHolder, itemHolderInfo, itemHolderInfo2);
                InfoRecord.c(infoRecord);
            }
            processCallback.unused(viewHolder);
            InfoRecord.c(infoRecord);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(RecyclerView.ViewHolder viewHolder) {
        InfoRecord infoRecord = (InfoRecord) this.f3825a.get(viewHolder);
        if (infoRecord == null) {
            return;
        }
        infoRecord.f3828a &= -2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(RecyclerView.ViewHolder viewHolder) {
        int size = this.f3826b.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            } else if (viewHolder == this.f3826b.valueAt(size)) {
                this.f3826b.removeAt(size);
                break;
            } else {
                size--;
            }
        }
        InfoRecord infoRecord = (InfoRecord) this.f3825a.remove(viewHolder);
        if (infoRecord != null) {
            InfoRecord.c(infoRecord);
        }
    }

    public void onViewDetached(RecyclerView.ViewHolder viewHolder) {
        n(viewHolder);
    }
}
