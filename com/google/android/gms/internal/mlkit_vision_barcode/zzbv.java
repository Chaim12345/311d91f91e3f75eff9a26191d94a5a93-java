package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
/* loaded from: classes2.dex */
class zzbv extends zzbw {

    /* renamed from: a  reason: collision with root package name */
    Object[] f6279a = new Object[4];

    /* renamed from: b  reason: collision with root package name */
    int f6280b = 0;

    /* renamed from: c  reason: collision with root package name */
    boolean f6281c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbv(int i2) {
    }

    private final void zzd(int i2) {
        Object[] objArr = this.f6279a;
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
            this.f6279a = Arrays.copyOf(objArr, i3);
        } else if (!this.f6281c) {
            return;
        } else {
            this.f6279a = (Object[]) objArr.clone();
        }
        this.f6281c = false;
    }

    public final zzbv zza(Object obj) {
        Objects.requireNonNull(obj);
        zzd(this.f6280b + 1);
        Object[] objArr = this.f6279a;
        int i2 = this.f6280b;
        this.f6280b = i2 + 1;
        objArr[i2] = obj;
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbw
    public /* bridge */ /* synthetic */ zzbw zzb(Object obj) {
        throw null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzbw zzc(Iterable iterable) {
        if (iterable instanceof Collection) {
            zzd(this.f6280b + iterable.size());
            if (iterable instanceof zzbx) {
                this.f6280b = ((zzbx) iterable).a(this.f6279a, this.f6280b);
                return this;
            }
        }
        for (Object obj : iterable) {
            zzb(obj);
        }
        return this;
    }
}
