package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zznw;
import com.google.firebase.messaging.Constants;
import kotlinx.coroutines.DebugKt;
/* loaded from: classes2.dex */
final class zzim implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ boolean f6886a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Uri f6887b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ String f6888c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ String f6889d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ zzio f6890e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzim(zzio zzioVar, boolean z, Uri uri, String str, String str2) {
        this.f6890e = zzioVar;
        this.f6886a = z;
        this.f6887b = uri;
        this.f6888c = str;
        this.f6889d = str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00b0 A[Catch: RuntimeException -> 0x01b2, TRY_ENTER, TryCatch #0 {RuntimeException -> 0x01b2, blocks: (B:3:0x0011, B:31:0x00b0, B:33:0x00de, B:36:0x00eb, B:38:0x00f1, B:39:0x0106, B:41:0x0117, B:44:0x011f, B:48:0x0147, B:50:0x0165, B:49:0x0154, B:52:0x016d, B:54:0x0173, B:56:0x0179, B:58:0x017f, B:60:0x0185, B:62:0x018d, B:64:0x0195, B:66:0x019b, B:68:0x01a2, B:7:0x0052, B:9:0x0058, B:11:0x005e, B:13:0x0064, B:16:0x006c, B:18:0x0074, B:21:0x007e, B:25:0x0089, B:26:0x0097, B:28:0x00a7), top: B:73:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x011f A[Catch: RuntimeException -> 0x01b2, TRY_LEAVE, TryCatch #0 {RuntimeException -> 0x01b2, blocks: (B:3:0x0011, B:31:0x00b0, B:33:0x00de, B:36:0x00eb, B:38:0x00f1, B:39:0x0106, B:41:0x0117, B:44:0x011f, B:48:0x0147, B:50:0x0165, B:49:0x0154, B:52:0x016d, B:54:0x0173, B:56:0x0179, B:58:0x017f, B:60:0x0185, B:62:0x018d, B:64:0x0195, B:66:0x019b, B:68:0x01a2, B:7:0x0052, B:9:0x0058, B:11:0x005e, B:13:0x0064, B:16:0x006c, B:18:0x0074, B:21:0x007e, B:25:0x0089, B:26:0x0097, B:28:0x00a7), top: B:73:0x0011 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        Bundle O;
        String str;
        zzio zzioVar = this.f6890e;
        boolean z = this.f6886a;
        Uri uri = this.f6887b;
        String str2 = this.f6888c;
        String str3 = this.f6889d;
        zzioVar.f6891a.zzg();
        try {
            zzlt zzv = zzioVar.f6891a.f6809a.zzv();
            zznw.zzc();
            zzag zzf = zzioVar.f6891a.f6809a.zzf();
            zzem zzemVar = zzen.zzav;
            boolean zzs = zzf.zzs(null, zzemVar);
            zznw.zzc();
            zzag zzf2 = zzioVar.f6891a.f6809a.zzf();
            zzem zzemVar2 = zzen.zzaw;
            boolean zzs2 = zzf2.zzs(null, zzemVar2);
            if (!TextUtils.isEmpty(str3)) {
                if (!str3.contains("gclid") && !str3.contains("utm_campaign") && !str3.contains("utm_source") && !str3.contains("utm_medium") && (!zzs || (!str3.contains("utm_id") && !str3.contains("dclid")))) {
                    if (zzs2 && str3.contains("srsltid")) {
                        zzs2 = true;
                    }
                    zzv.f6809a.zzay().zzc().zza("Activity created with data 'referrer' without required params");
                }
                O = zzv.O(Uri.parse("https://google.com/search?".concat(str3)), zzs, zzs2);
                if (O != null) {
                    O.putString("_cis", "referrer");
                }
                if (z) {
                    zzlt zzv2 = zzioVar.f6891a.f6809a.zzv();
                    zznw.zzc();
                    boolean zzs3 = zzioVar.f6891a.f6809a.zzf().zzs(null, zzemVar);
                    zznw.zzc();
                    Bundle O2 = zzv2.O(uri, zzs3, zzioVar.f6891a.f6809a.zzf().zzs(null, zzemVar2));
                    if (O2 != null) {
                        O2.putString("_cis", "intent");
                        if (!O2.containsKey("gclid") && O != null && O.containsKey("gclid")) {
                            O2.putString("_cer", String.format("gclid=%s", O.getString("gclid")));
                        }
                        str = str2;
                        zzioVar.f6891a.f(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, O2);
                        zzioVar.f6891a.f6893c.a(str, O2);
                        if (TextUtils.isEmpty(str3)) {
                            return;
                        }
                        zzioVar.f6891a.f6809a.zzay().zzc().zzb("Activity created with referrer", str3);
                        if (zzioVar.f6891a.f6809a.zzf().zzs(null, zzen.zzZ)) {
                            if (O != null) {
                                zzioVar.f6891a.f(str, Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, O);
                                zzioVar.f6891a.f6893c.a(str, O);
                            } else {
                                zzioVar.f6891a.f6809a.zzay().zzc().zzb("Referrer does not contain valid parameters", str3);
                            }
                            zzioVar.f6891a.zzY(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ldl", null, true);
                            return;
                        } else if (!str3.contains("gclid") || (!str3.contains("utm_campaign") && !str3.contains("utm_source") && !str3.contains("utm_medium") && !str3.contains("utm_term") && !str3.contains("utm_content"))) {
                            zzioVar.f6891a.f6809a.zzay().zzc().zza("Activity created with data 'referrer' without required params");
                            return;
                        } else if (TextUtils.isEmpty(str3)) {
                            return;
                        } else {
                            zzioVar.f6891a.zzY(DebugKt.DEBUG_PROPERTY_VALUE_AUTO, "_ldl", str3, true);
                            return;
                        }
                    }
                }
                str = str2;
                if (TextUtils.isEmpty(str3)) {
                }
            }
            O = null;
            if (z) {
            }
            str = str2;
            if (TextUtils.isEmpty(str3)) {
            }
        } catch (RuntimeException e2) {
            zzioVar.f6891a.f6809a.zzay().zzd().zzb("Throwable caught in handleReferrerForOnActivityCreated", e2);
        }
    }
}
