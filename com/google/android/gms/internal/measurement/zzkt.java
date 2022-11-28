package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
final class zzkt extends zzkx {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzkt() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzkt(zzks zzksVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzkx
    public final void a(Object obj, long j2) {
        Object unmodifiableList;
        List list = (List) zzmv.f(obj, j2);
        if (list instanceof zzkr) {
            unmodifiableList = ((zzkr) list).zze();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof zzlq) && (list instanceof zzkj)) {
                zzkj zzkjVar = (zzkj) list;
                if (zzkjVar.zzc()) {
                    zzkjVar.zzb();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        zzmv.s(obj, j2, unmodifiableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009c  */
    @Override // com.google.android.gms.internal.measurement.zzkx
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void b(Object obj, Object obj2, long j2) {
        zzkq zzkqVar;
        int size;
        List list = (List) zzmv.f(obj2, j2);
        int size2 = list.size();
        List list2 = (List) zzmv.f(obj, j2);
        if (!list2.isEmpty()) {
            if (zza.isAssignableFrom(list2.getClass())) {
                ArrayList arrayList = new ArrayList(list2.size() + size2);
                arrayList.addAll(list2);
                zzkqVar = arrayList;
            } else if (!(list2 instanceof zzmq)) {
                if ((list2 instanceof zzlq) && (list2 instanceof zzkj)) {
                    zzkj zzkjVar = (zzkj) list2;
                    if (!zzkjVar.zzc()) {
                        list2 = zzkjVar.zzd(list2.size() + size2);
                    }
                }
                size = list2.size();
                int size3 = list.size();
                if (size > 0 && size3 > 0) {
                    list2.addAll(list);
                }
                if (size > 0) {
                    list = list2;
                }
                zzmv.s(obj, j2, list);
            } else {
                zzkq zzkqVar2 = new zzkq(list2.size() + size2);
                zzkqVar2.addAll(zzkqVar2.size(), (zzmq) list2);
                zzkqVar = zzkqVar2;
            }
            zzmv.s(obj, j2, zzkqVar);
            list2 = zzkqVar;
            size = list2.size();
            int size32 = list.size();
            if (size > 0) {
                list2.addAll(list);
            }
            if (size > 0) {
            }
            zzmv.s(obj, j2, list);
        }
        list2 = list2 instanceof zzkr ? new zzkq(size2) : ((list2 instanceof zzlq) && (list2 instanceof zzkj)) ? ((zzkj) list2).zzd(size2) : new ArrayList(size2);
        zzmv.s(obj, j2, list2);
        size = list2.size();
        int size322 = list.size();
        if (size > 0) {
        }
        if (size > 0) {
        }
        zzmv.s(obj, j2, list);
    }
}
