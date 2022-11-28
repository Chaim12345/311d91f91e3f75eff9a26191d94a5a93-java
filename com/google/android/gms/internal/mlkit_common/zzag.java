package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes.dex */
class zzag extends zzah {

    /* renamed from: a  reason: collision with root package name */
    Object[] f6120a = new Object[4];

    /* renamed from: b  reason: collision with root package name */
    int f6121b = 0;

    /* renamed from: c  reason: collision with root package name */
    boolean f6122c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzag(int i2) {
    }

    private final void zzb(int i2) {
        Object[] objArr = this.f6120a;
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
            this.f6120a = Arrays.copyOf(objArr, i3);
        } else if (!this.f6122c) {
            return;
        } else {
            this.f6120a = (Object[]) objArr.clone();
        }
        this.f6122c = false;
    }

    public final zzag zza(Object obj) {
        Objects.requireNonNull(obj);
        zzb(this.f6121b + 1);
        Object[] objArr = this.f6120a;
        int i2 = this.f6121b;
        this.f6121b = i2 + 1;
        objArr[i2] = obj;
        return this;
    }
}
