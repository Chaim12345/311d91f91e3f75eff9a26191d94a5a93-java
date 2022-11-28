package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class zzao extends zzai {

    /* renamed from: c  reason: collision with root package name */
    protected final List f5928c;

    /* renamed from: d  reason: collision with root package name */
    protected final List f5929d;

    /* renamed from: e  reason: collision with root package name */
    protected zzg f5930e;

    private zzao(zzao zzaoVar) {
        super(zzaoVar.f5924a);
        ArrayList arrayList = new ArrayList(zzaoVar.f5928c.size());
        this.f5928c = arrayList;
        arrayList.addAll(zzaoVar.f5928c);
        ArrayList arrayList2 = new ArrayList(zzaoVar.f5929d.size());
        this.f5929d = arrayList2;
        arrayList2.addAll(zzaoVar.f5929d);
        this.f5930e = zzaoVar.f5930e;
    }

    public zzao(String str, List list, List list2, zzg zzgVar) {
        super(str);
        this.f5928c = new ArrayList();
        this.f5930e = zzgVar;
        if (!list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                this.f5928c.add(((zzap) it.next()).zzi());
            }
        }
        this.f5929d = new ArrayList(list2);
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzap zza(zzg zzgVar, List list) {
        String str;
        zzap zzapVar;
        zzg zza = this.f5930e.zza();
        for (int i2 = 0; i2 < this.f5928c.size(); i2++) {
            if (i2 < list.size()) {
                str = (String) this.f5928c.get(i2);
                zzapVar = zzgVar.zzb((zzap) list.get(i2));
            } else {
                str = (String) this.f5928c.get(i2);
                zzapVar = zzap.zzf;
            }
            zza.zze(str, zzapVar);
        }
        for (zzap zzapVar2 : this.f5929d) {
            zzap zzb = zza.zzb(zzapVar2);
            if (zzb instanceof zzaq) {
                zzb = zza.zzb(zzapVar2);
            }
            if (zzb instanceof zzag) {
                return ((zzag) zzb).zzb();
            }
        }
        return zzap.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzai, com.google.android.gms.internal.measurement.zzap
    public final zzap zzd() {
        return new zzao(this);
    }
}
