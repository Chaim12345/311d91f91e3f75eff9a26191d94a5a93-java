package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import android.os.ParcelFileDescriptor;
/* loaded from: classes2.dex */
final class zzan extends zzal {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzan(zzaw zzawVar, com.google.android.play.core.tasks.zzi zziVar) {
        super(zzawVar, zziVar);
    }

    @Override // com.google.android.play.core.assetpacks.zzal, com.google.android.play.core.internal.zzw
    public final void zze(Bundle bundle, Bundle bundle2) {
        super.zze(bundle, bundle2);
        this.f7793a.zze((ParcelFileDescriptor) bundle.getParcelable("chunk_file_descriptor"));
    }
}
