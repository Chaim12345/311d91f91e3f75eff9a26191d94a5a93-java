package com.google.android.gms.internal.common;

import java.util.Iterator;
/* loaded from: classes.dex */
public final class zzad<E> extends zzaa<E> {
    public zzad() {
        super(4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzad(int i2) {
        super(4);
    }

    public final zzad<E> zzb(E e2) {
        super.zza(e2);
        return this;
    }

    public final zzad<E> zzc(Iterator<? extends E> it) {
        while (it.hasNext()) {
            super.zza(it.next());
        }
        return this;
    }
}
