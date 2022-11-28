package com.google.android.play.core.review;

import android.app.PendingIntent;
import android.os.Bundle;
import com.google.android.play.core.internal.zzag;
/* loaded from: classes2.dex */
final class zzh extends zzg {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(zzi zziVar, com.google.android.play.core.tasks.zzi zziVar2, String str) {
        super(zziVar, new zzag("OnRequestInstallCallback"), zziVar2);
    }

    @Override // com.google.android.play.core.review.zzg, com.google.android.play.core.internal.zzae
    public final void zzb(Bundle bundle) {
        super.zzb(bundle);
        this.f7875b.zze(new zza((PendingIntent) bundle.get("confirmation_intent"), bundle.getBoolean("is_review_no_op")));
    }
}
