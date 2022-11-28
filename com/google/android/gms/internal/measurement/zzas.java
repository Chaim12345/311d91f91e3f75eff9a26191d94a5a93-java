package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes.dex */
final class zzas implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzat f5932a;
    private int zzb = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzas(zzat zzatVar) {
        this.f5932a = zzatVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        String str;
        int i2 = this.zzb;
        str = this.f5932a.zza;
        return i2 < str.length();
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        String str;
        String str2;
        int i2 = this.zzb;
        zzat zzatVar = this.f5932a;
        str = zzatVar.zza;
        if (i2 < str.length()) {
            str2 = zzatVar.zza;
            this.zzb = i2 + 1;
            return new zzat(String.valueOf(str2.charAt(i2)));
        }
        throw new NoSuchElementException();
    }
}
