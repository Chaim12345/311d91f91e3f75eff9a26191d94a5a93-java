package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes2.dex */
abstract class zzbo implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    int f6269a;

    /* renamed from: b  reason: collision with root package name */
    int f6270b;

    /* renamed from: c  reason: collision with root package name */
    int f6271c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ zzbs f6272d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzbo(zzbs zzbsVar, zzbk zzbkVar) {
        int i2;
        this.f6272d = zzbsVar;
        i2 = zzbsVar.zzf;
        this.f6269a = i2;
        this.f6270b = zzbsVar.e();
        this.f6271c = -1;
    }

    private final void zzb() {
        int i2;
        i2 = this.f6272d.zzf;
        if (i2 != this.f6269a) {
            throw new ConcurrentModificationException();
        }
    }

    abstract Object a(int i2);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f6270b >= 0;
    }

    @Override // java.util.Iterator
    public final Object next() {
        zzb();
        if (hasNext()) {
            int i2 = this.f6270b;
            this.f6271c = i2;
            Object a2 = a(i2);
            this.f6270b = this.f6272d.f(this.f6270b);
            return a2;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        zzb();
        zzaq.zzd(this.f6271c >= 0, "no calls to next() since the last call to remove()");
        this.f6269a += 32;
        zzbs zzbsVar = this.f6272d;
        zzbsVar.remove(zzbs.g(zzbsVar, this.f6271c));
        this.f6270b--;
        this.f6271c = -1;
    }
}
