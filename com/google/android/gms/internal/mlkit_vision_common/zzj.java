package com.google.android.gms.internal.mlkit_vision_common;

import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes2.dex */
class zzj extends zzk {

    /* renamed from: a  reason: collision with root package name */
    Object[] f6573a = new Object[4];

    /* renamed from: b  reason: collision with root package name */
    int f6574b = 0;

    /* renamed from: c  reason: collision with root package name */
    boolean f6575c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(int i2) {
    }

    private final void zzb(int i2) {
        Object[] objArr = this.f6573a;
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
            this.f6573a = Arrays.copyOf(objArr, i3);
        } else if (!this.f6575c) {
            return;
        } else {
            this.f6573a = (Object[]) objArr.clone();
        }
        this.f6575c = false;
    }

    public final zzj zza(Object obj) {
        Objects.requireNonNull(obj);
        zzb(this.f6574b + 1);
        Object[] objArr = this.f6573a;
        int i2 = this.f6574b;
        this.f6574b = i2 + 1;
        objArr[i2] = obj;
        return this;
    }
}
