package com.google.android.gms.measurement.internal;

import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zznw;
import com.google.firebase.messaging.Constants;
import kotlinx.coroutines.DebugKt;
/* loaded from: classes2.dex */
final class zzfq implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ com.google.android.gms.internal.measurement.zzbr f6738a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ ServiceConnection f6739b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ zzfr f6740c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfq(zzfr zzfrVar, com.google.android.gms.internal.measurement.zzbr zzbrVar, ServiceConnection serviceConnection) {
        this.f6740c = zzfrVar;
        this.f6738a = zzbrVar;
        this.f6739b = serviceConnection;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        Bundle bundle;
        zzey zzd;
        String str2;
        zzfr zzfrVar = this.f6740c;
        zzfs zzfsVar = zzfrVar.f6741a;
        str = zzfrVar.zzb;
        com.google.android.gms.internal.measurement.zzbr zzbrVar = this.f6738a;
        ServiceConnection serviceConnection = this.f6739b;
        zzfsVar.f6742a.zzaz().zzg();
        Bundle bundle2 = new Bundle();
        bundle2.putString("package_name", str);
        try {
            bundle = zzbrVar.zzd(bundle2);
        } catch (Exception e2) {
            zzfsVar.f6742a.zzay().zzd().zzb("Exception occurred while retrieving the Install Referrer", e2.getMessage());
        }
        if (bundle == null) {
            zzfsVar.f6742a.zzay().zzd().zza("Install Referrer Service returned a null response");
            bundle = null;
        }
        zzfsVar.f6742a.zzaz().zzg();
        zzgk.h();
        if (bundle != null) {
            long j2 = bundle.getLong("install_begin_timestamp_seconds", 0L) * 1000;
            if (j2 == 0) {
                zzd = zzfsVar.f6742a.zzay().zzk();
                str2 = "Service response is missing Install Referrer install timestamp";
            } else {
                String string = bundle.getString("install_referrer");
                if (string == null || string.isEmpty()) {
                    zzd = zzfsVar.f6742a.zzay().zzd();
                    str2 = "No referrer defined in Install Referrer response";
                } else {
                    zzfsVar.f6742a.zzay().zzj().zzb("InstallReferrer API result", string);
                    zzlt zzv = zzfsVar.f6742a.zzv();
                    Uri parse = Uri.parse("?".concat(string));
                    zznw.zzc();
                    boolean zzs = zzfsVar.f6742a.zzf().zzs(null, zzen.zzau);
                    zznw.zzc();
                    Bundle O = zzv.O(parse, zzs, zzfsVar.f6742a.zzf().zzs(null, zzen.zzax));
                    if (O == null) {
                        zzd = zzfsVar.f6742a.zzay().zzd();
                        str2 = "No campaign params defined in Install Referrer result";
                    } else {
                        String string2 = O.getString("medium");
                        if (string2 != null && !"(not set)".equalsIgnoreCase(string2) && !"organic".equalsIgnoreCase(string2)) {
                            long j3 = bundle.getLong("referrer_click_timestamp_seconds", 0L) * 1000;
                            if (j3 == 0) {
                                zzd = zzfsVar.f6742a.zzay().zzd();
                                str2 = "Install Referrer is missing click timestamp for ad campaign";
                            } else {
                                O.putLong("click_timestamp", j3);
                            }
                        }
                        if (j2 == zzfsVar.f6742a.zzm().zzd.zza()) {
                            zzfsVar.f6742a.zzay().zzj().zza("Logging Install Referrer campaign from module while it may have already been logged.");
                        }
                        if (zzfsVar.f6742a.zzJ()) {
                            zzfsVar.f6742a.zzm().zzd.zzb(j2);
                            zzfsVar.f6742a.zzay().zzj().zzb("Logging Install Referrer campaign from gmscore with ", "referrer API v2");
                            O.putString("_cis", "referrer API v2");
                            zzfsVar.f6742a.zzq().zzG(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, O, str);
                        }
                    }
                }
            }
            zzd.zza(str2);
        }
        ConnectionTracker.getInstance().unbindService(zzfsVar.f6742a.zzau(), serviceConnection);
    }
}
