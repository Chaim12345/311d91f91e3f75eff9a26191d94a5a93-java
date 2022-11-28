package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
@KeepForSdk
/* loaded from: classes.dex */
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zaa;
    private ArrayList<Integer> zab;

    private final void zab() {
        synchronized (this) {
            if (!this.zaa) {
                int count = ((DataHolder) Preconditions.checkNotNull(this.f5724a)).getCount();
                ArrayList<Integer> arrayList = new ArrayList<>();
                this.zab = arrayList;
                if (count > 0) {
                    arrayList.add(0);
                    String c2 = c();
                    String string = this.f5724a.getString(c2, 0, this.f5724a.getWindowIndex(0));
                    for (int i2 = 1; i2 < count; i2++) {
                        int windowIndex = this.f5724a.getWindowIndex(i2);
                        String string2 = this.f5724a.getString(c2, i2, windowIndex);
                        if (string2 == null) {
                            StringBuilder sb = new StringBuilder(String.valueOf(c2).length() + 78);
                            sb.append("Missing value for markerColumn: ");
                            sb.append(c2);
                            sb.append(", at row: ");
                            sb.append(i2);
                            sb.append(", for window: ");
                            sb.append(windowIndex);
                            throw new NullPointerException(sb.toString());
                        }
                        if (!string2.equals(string)) {
                            this.zab.add(Integer.valueOf(i2));
                            string = string2;
                        }
                    }
                }
                this.zaa = true;
            }
        }
    }

    @Nullable
    @KeepForSdk
    protected String a() {
        return null;
    }

    @NonNull
    @KeepForSdk
    protected abstract Object b(int i2, int i3);

    @NonNull
    @KeepForSdk
    protected abstract String c();

    final int d(int i2) {
        if (i2 < 0 || i2 >= this.zab.size()) {
            StringBuilder sb = new StringBuilder(53);
            sb.append("Position ");
            sb.append(i2);
            sb.append(" is out of bounds for this buffer");
            throw new IllegalArgumentException(sb.toString());
        }
        return this.zab.get(i2).intValue();
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    @NonNull
    @KeepForSdk
    public final T get(int i2) {
        zab();
        int d2 = d(i2);
        int i3 = 0;
        if (i2 >= 0 && i2 != this.zab.size()) {
            int count = (i2 == this.zab.size() + (-1) ? ((DataHolder) Preconditions.checkNotNull(this.f5724a)).getCount() : this.zab.get(i2 + 1).intValue()) - this.zab.get(i2).intValue();
            if (count == 1) {
                int d3 = d(i2);
                int windowIndex = ((DataHolder) Preconditions.checkNotNull(this.f5724a)).getWindowIndex(d3);
                String a2 = a();
                if (a2 == null || this.f5724a.getString(a2, d3, windowIndex) != null) {
                    i3 = 1;
                }
            } else {
                i3 = count;
            }
        }
        return (T) b(d2, i3);
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    @KeepForSdk
    public int getCount() {
        zab();
        return this.zab.size();
    }
}
