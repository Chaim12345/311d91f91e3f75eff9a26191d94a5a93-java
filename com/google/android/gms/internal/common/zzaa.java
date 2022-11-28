package com.google.android.gms.internal.common;

import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes.dex */
class zzaa<E> extends zzab<E> {

    /* renamed from: a  reason: collision with root package name */
    Object[] f5846a = new Object[4];

    /* renamed from: b  reason: collision with root package name */
    int f5847b = 0;

    /* renamed from: c  reason: collision with root package name */
    boolean f5848c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaa(int i2) {
    }

    private final void zzb(int i2) {
        Object[] objArr = this.f5846a;
        int length = objArr.length;
        if (length < i2) {
            int i3 = length + (length >> 1) + 1;
            if (i3 < i2) {
                int highestOneBit = Integer.highestOneBit(i2 - 1);
                i3 = highestOneBit + highestOneBit;
            }
            if (i3 < 0) {
                i3 = Integer.MAX_VALUE;
            }
            this.f5846a = Arrays.copyOf(objArr, i3);
        } else if (!this.f5848c) {
            return;
        } else {
            this.f5846a = (Object[]) objArr.clone();
        }
        this.f5848c = false;
    }

    public final zzaa<E> zza(E e2) {
        Objects.requireNonNull(e2);
        zzb(this.f5847b + 1);
        Object[] objArr = this.f5846a;
        int i2 = this.f5847b;
        this.f5847b = i2 + 1;
        objArr[i2] = e2;
        return this;
    }
}
