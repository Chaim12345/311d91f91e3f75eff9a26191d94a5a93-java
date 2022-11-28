package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes2.dex */
final class zzex extends zzez {
    private static final Class zza = Collections.unmodifiableList(Collections.emptyList()).getClass();

    private zzex() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzex(zzew zzewVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzez
    public final void a(Object obj, long j2) {
        Object unmodifiableList;
        List list = (List) zzgz.f(obj, j2);
        if (list instanceof zzev) {
            unmodifiableList = ((zzev) list).zze();
        } else if (zza.isAssignableFrom(list.getClass())) {
            return;
        } else {
            if ((list instanceof zzft) && (list instanceof zzek)) {
                zzek zzekVar = (zzek) list;
                if (zzekVar.zzc()) {
                    zzekVar.zzb();
                    return;
                }
                return;
            }
            unmodifiableList = Collections.unmodifiableList(list);
        }
        zzgz.s(obj, j2, unmodifiableList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009c  */
    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzez
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void b(Object obj, Object obj2, long j2) {
        zzeu zzeuVar;
        int size;
        List list = (List) zzgz.f(obj2, j2);
        int size2 = list.size();
        List list2 = (List) zzgz.f(obj, j2);
        if (!list2.isEmpty()) {
            if (zza.isAssignableFrom(list2.getClass())) {
                ArrayList arrayList = new ArrayList(list2.size() + size2);
                arrayList.addAll(list2);
                zzeuVar = arrayList;
            } else if (!(list2 instanceof zzgu)) {
                if ((list2 instanceof zzft) && (list2 instanceof zzek)) {
                    zzek zzekVar = (zzek) list2;
                    if (!zzekVar.zzc()) {
                        list2 = zzekVar.zzd(list2.size() + size2);
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
                zzgz.s(obj, j2, list);
            } else {
                zzeu zzeuVar2 = new zzeu(list2.size() + size2);
                zzeuVar2.addAll(zzeuVar2.size(), (zzgu) list2);
                zzeuVar = zzeuVar2;
            }
            zzgz.s(obj, j2, zzeuVar);
            list2 = zzeuVar;
            size = list2.size();
            int size32 = list.size();
            if (size > 0) {
                list2.addAll(list);
            }
            if (size > 0) {
            }
            zzgz.s(obj, j2, list);
        }
        list2 = list2 instanceof zzev ? new zzeu(size2) : ((list2 instanceof zzft) && (list2 instanceof zzek)) ? ((zzek) list2).zzd(size2) : new ArrayList(size2);
        zzgz.s(obj, j2, list2);
        size = list2.size();
        int size322 = list.size();
        if (size > 0) {
        }
        if (size > 0) {
        }
        zzgz.s(obj, j2, list);
    }
}
