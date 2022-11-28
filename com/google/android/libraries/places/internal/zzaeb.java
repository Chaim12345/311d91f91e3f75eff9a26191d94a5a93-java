package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
final class zzaeb extends zzaef {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzaeb() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzaeb(zzaea zzaeaVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzaef
    public final void zza(Object obj, long j2) {
        Object unmodifiableList;
        List list = (List) zzagd.zzf(obj, j2);
        if (list instanceof zzadz) {
            unmodifiableList = ((zzadz) list).zzd();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof zzaey) && (list instanceof zzadr)) {
                zzadr zzadrVar = (zzadr) list;
                if (zzadrVar.zzc()) {
                    zzadrVar.zzb();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        zzagd.zzs(obj, j2, unmodifiableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009c  */
    @Override // com.google.android.libraries.places.internal.zzaef
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zzb(Object obj, Object obj2, long j2) {
        zzady zzadyVar;
        int size;
        List list = (List) zzagd.zzf(obj2, j2);
        int size2 = list.size();
        List list2 = (List) zzagd.zzf(obj, j2);
        if (!list2.isEmpty()) {
            if (zza.isAssignableFrom(list2.getClass())) {
                ArrayList arrayList = new ArrayList(list2.size() + size2);
                arrayList.addAll(list2);
                zzadyVar = arrayList;
            } else if (!(list2 instanceof zzafy)) {
                if ((list2 instanceof zzaey) && (list2 instanceof zzadr)) {
                    zzadr zzadrVar = (zzadr) list2;
                    if (!zzadrVar.zzc()) {
                        list2 = zzadrVar.zzf(list2.size() + size2);
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
                zzagd.zzs(obj, j2, list);
            } else {
                zzady zzadyVar2 = new zzady(list2.size() + size2);
                zzadyVar2.addAll(zzadyVar2.size(), (zzafy) list2);
                zzadyVar = zzadyVar2;
            }
            zzagd.zzs(obj, j2, zzadyVar);
            list2 = zzadyVar;
            size = list2.size();
            int size32 = list.size();
            if (size > 0) {
                list2.addAll(list);
            }
            if (size > 0) {
            }
            zzagd.zzs(obj, j2, list);
        }
        list2 = list2 instanceof zzadz ? new zzady(size2) : ((list2 instanceof zzaey) && (list2 instanceof zzadr)) ? ((zzadr) list2).zzf(size2) : new ArrayList(size2);
        zzagd.zzs(obj, j2, list2);
        size = list2.size();
        int size322 = list.size();
        if (size > 0) {
        }
        if (size > 0) {
        }
        zzagd.zzs(obj, j2, list);
    }
}
