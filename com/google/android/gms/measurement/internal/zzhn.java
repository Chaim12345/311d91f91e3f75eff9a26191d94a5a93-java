package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes2.dex */
public final class zzhn {

    /* renamed from: a  reason: collision with root package name */
    final Context f6811a;
    @Nullable

    /* renamed from: b  reason: collision with root package name */
    String f6812b;
    @Nullable

    /* renamed from: c  reason: collision with root package name */
    String f6813c;
    @Nullable

    /* renamed from: d  reason: collision with root package name */
    String f6814d;
    @Nullable

    /* renamed from: e  reason: collision with root package name */
    Boolean f6815e;

    /* renamed from: f  reason: collision with root package name */
    long f6816f;
    @Nullable

    /* renamed from: g  reason: collision with root package name */
    com.google.android.gms.internal.measurement.zzcl f6817g;

    /* renamed from: h  reason: collision with root package name */
    boolean f6818h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    final Long f6819i;
    @Nullable

    /* renamed from: j  reason: collision with root package name */
    String f6820j;

    @VisibleForTesting
    public zzhn(Context context, @Nullable com.google.android.gms.internal.measurement.zzcl zzclVar, @Nullable Long l2) {
        this.f6818h = true;
        Preconditions.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.f6811a = applicationContext;
        this.f6819i = l2;
        if (zzclVar != null) {
            this.f6817g = zzclVar;
            this.f6812b = zzclVar.zzf;
            this.f6813c = zzclVar.zze;
            this.f6814d = zzclVar.zzd;
            this.f6818h = zzclVar.zzc;
            this.f6816f = zzclVar.zzb;
            this.f6820j = zzclVar.zzh;
            Bundle bundle = zzclVar.zzg;
            if (bundle != null) {
                this.f6815e = Boolean.valueOf(bundle.getBoolean("dataCollectionDefaultEnabled", true));
            }
        }
    }
}
