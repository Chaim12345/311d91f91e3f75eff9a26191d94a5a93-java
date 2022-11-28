package com.google.android.gms.internal.common;

import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.CheckForNull;
/* loaded from: classes.dex */
abstract class zzj<T> implements Iterator<T> {
    @CheckForNull
    private T zza;
    private int zzb = 2;

    @CheckForNull
    protected abstract Object a();

    /* JADX INFO: Access modifiers changed from: protected */
    @CheckForNull
    public final Object b() {
        this.zzb = 3;
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i2 = this.zzb;
        if (i2 != 4) {
            int i3 = i2 - 1;
            if (i2 != 0) {
                if (i3 != 0) {
                    if (i3 != 2) {
                        this.zzb = 4;
                        this.zza = (T) a();
                        if (this.zzb != 3) {
                            this.zzb = 1;
                            return true;
                        }
                    }
                    return false;
                }
                return true;
            }
            throw null;
        }
        throw new IllegalStateException();
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            this.zzb = 2;
            T t2 = this.zza;
            this.zza = null;
            return t2;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
