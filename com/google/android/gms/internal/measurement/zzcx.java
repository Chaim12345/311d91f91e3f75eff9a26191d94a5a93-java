package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcx extends zzdt {

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ String f5966e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ String f5967f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Context f5968g;

    /* renamed from: h  reason: collision with root package name */
    final /* synthetic */ Bundle f5969h;

    /* renamed from: i  reason: collision with root package name */
    final /* synthetic */ zzee f5970i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcx(zzee zzeeVar, String str, String str2, Context context, Bundle bundle) {
        super(zzeeVar, true);
        this.f5970i = zzeeVar;
        this.f5966e = str;
        this.f5967f = str2;
        this.f5968g = context;
        this.f5969h = bundle;
    }

    @Override // com.google.android.gms.internal.measurement.zzdt
    public final void zza() {
        boolean zzV;
        String str;
        String str2;
        String str3;
        zzcc zzccVar;
        int remoteVersion;
        zzcc zzccVar2;
        String str4;
        String unused;
        try {
            zzee zzeeVar = this.f5970i;
            zzV = zzee.zzV(this.f5966e, this.f5967f);
            if (zzV) {
                String str5 = this.f5967f;
                String str6 = this.f5966e;
                str4 = this.f5970i.zzd;
                str2 = str6;
                str3 = str5;
                str = str4;
            } else {
                str = null;
                str2 = null;
                str3 = null;
            }
            Preconditions.checkNotNull(this.f5968g);
            zzee zzeeVar2 = this.f5970i;
            zzeeVar2.zzj = zzeeVar2.e(this.f5968g, true);
            zzccVar = this.f5970i.zzj;
            if (zzccVar == null) {
                unused = this.f5970i.zzd;
                return;
            }
            int localVersion = DynamiteModule.getLocalVersion(this.f5968g, ModuleDescriptor.MODULE_ID);
            zzcl zzclVar = new zzcl(64000L, Math.max(localVersion, remoteVersion), DynamiteModule.getRemoteVersion(this.f5968g, ModuleDescriptor.MODULE_ID) < localVersion, str, str2, str3, this.f5969h, com.google.android.gms.measurement.internal.zzgc.zza(this.f5968g));
            zzccVar2 = this.f5970i.zzj;
            ((zzcc) Preconditions.checkNotNull(zzccVar2)).initialize(ObjectWrapper.wrap(this.f5968g), zzclVar, this.f6026a);
        } catch (Exception e2) {
            this.f5970i.zzS(e2, true, false);
        }
    }
}
