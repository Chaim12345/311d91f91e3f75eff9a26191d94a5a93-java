package com.google.android.gms.internal.measurement;

import android.util.Log;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzhs extends zzhy {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhs(zzhv zzhvVar, String str, Boolean bool, boolean z) {
        super(zzhvVar, str, bool, true, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.measurement.zzhy
    @Nullable
    final /* bridge */ /* synthetic */ Object a(Object obj) {
        if (zzgz.zzc.matcher(obj).matches()) {
            return Boolean.TRUE;
        }
        if (zzgz.zzd.matcher(obj).matches()) {
            return Boolean.FALSE;
        }
        String zzc = super.zzc();
        Log.e("PhenotypeFlag", "Invalid boolean value for " + zzc + ": " + ((String) obj));
        return null;
    }
}
