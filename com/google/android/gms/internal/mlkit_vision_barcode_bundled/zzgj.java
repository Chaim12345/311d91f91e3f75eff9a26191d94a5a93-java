package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
final class zzgj implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzgl f6433a;
    private int zzb = -1;
    private boolean zzc;
    private Iterator zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgj(zzgl zzglVar, zzge zzgeVar) {
        this.f6433a = zzglVar;
    }

    private final Iterator zza() {
        Map map;
        if (this.zzd == null) {
            map = this.f6433a.zzc;
            this.zzd = map.entrySet().iterator();
        }
        return this.zzd;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        List list;
        Map map;
        int i2 = this.zzb + 1;
        list = this.f6433a.zzb;
        if (i2 >= list.size()) {
            map = this.f6433a.zzc;
            return !map.isEmpty() && zza().hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public final /* bridge */ /* synthetic */ Object next() {
        List list;
        Object next;
        List list2;
        this.zzc = true;
        int i2 = this.zzb + 1;
        this.zzb = i2;
        list = this.f6433a.zzb;
        if (i2 < list.size()) {
            list2 = this.f6433a.zzb;
            next = list2.get(this.zzb);
        } else {
            next = zza().next();
        }
        return (Map.Entry) next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        List list;
        if (!this.zzc) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzc = false;
        this.f6433a.zzn();
        int i2 = this.zzb;
        list = this.f6433a.zzb;
        if (i2 >= list.size()) {
            zza().remove();
            return;
        }
        zzgl zzglVar = this.f6433a;
        int i3 = this.zzb;
        this.zzb = i3 - 1;
        zzglVar.zzl(i3);
    }
}
