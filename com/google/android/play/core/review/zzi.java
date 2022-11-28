package com.google.android.play.core.review;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.play.core.internal.zzag;
import com.google.android.play.core.internal.zzas;
import com.google.android.play.core.internal.zzch;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;
@SuppressLint({"RestrictedApi"})
/* loaded from: classes2.dex */
public final class zzi {
    private static final zzag zzb = new zzag("ReviewService");
    @Nullable
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    zzas f7877a;
    private final String zzc;

    public zzi(Context context) {
        this.zzc = context.getPackageName();
        if (zzch.zzb(context)) {
            this.f7877a = new zzas(context, zzb, "com.google.android.finsky.inappreviewservice.InAppReviewService", new Intent("com.google.android.finsky.BIND_IN_APP_REVIEW_SERVICE").setPackage("com.android.vending"), zze.zza, null);
        }
    }

    public final Task zzb() {
        zzag zzagVar = zzb;
        zzagVar.zzd("requestInAppReview (%s)", this.zzc);
        if (this.f7877a == null) {
            zzagVar.zzb("Play Store app is either not installed or not the official version", new Object[0]);
            return Tasks.zza(new ReviewException(-1));
        }
        com.google.android.play.core.tasks.zzi zziVar = new com.google.android.play.core.tasks.zzi();
        this.f7877a.zzq(new zzf(this, zziVar, zziVar), zziVar);
        return zziVar.zza();
    }
}
