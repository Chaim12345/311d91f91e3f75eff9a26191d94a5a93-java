package com.google.android.gms.common.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
@KeepForSdk
/* loaded from: classes.dex */
public abstract class DataBufferRef {
    @NonNull
    @KeepForSdk

    /* renamed from: a  reason: collision with root package name */
    protected final DataHolder f5730a;
    @KeepForSdk

    /* renamed from: b  reason: collision with root package name */
    protected int f5731b;
    private int zaa;

    @KeepForSdk
    public DataBufferRef(@NonNull DataHolder dataHolder, int i2) {
        this.f5730a = (DataHolder) Preconditions.checkNotNull(dataHolder);
        a(i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(int i2) {
        boolean z = false;
        if (i2 >= 0 && i2 < this.f5730a.getCount()) {
            z = true;
        }
        Preconditions.checkState(z);
        this.f5731b = i2;
        this.zaa = this.f5730a.getWindowIndex(i2);
    }

    @KeepForSdk
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof DataBufferRef) {
            DataBufferRef dataBufferRef = (DataBufferRef) obj;
            if (Objects.equal(Integer.valueOf(dataBufferRef.f5731b), Integer.valueOf(this.f5731b)) && Objects.equal(Integer.valueOf(dataBufferRef.zaa), Integer.valueOf(this.zaa)) && dataBufferRef.f5730a == this.f5730a) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public boolean hasColumn(@NonNull String str) {
        return this.f5730a.hasColumn(str);
    }

    @KeepForSdk
    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.f5731b), Integer.valueOf(this.zaa), this.f5730a);
    }

    @KeepForSdk
    public boolean isDataValid() {
        return !this.f5730a.isClosed();
    }
}
