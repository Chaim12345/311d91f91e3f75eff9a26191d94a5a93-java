package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
/* loaded from: classes2.dex */
public final class zzar {

    /* renamed from: a  reason: collision with root package name */
    final String f6692a;

    /* renamed from: b  reason: collision with root package name */
    final String f6693b;

    /* renamed from: c  reason: collision with root package name */
    final String f6694c;

    /* renamed from: d  reason: collision with root package name */
    final long f6695d;

    /* renamed from: e  reason: collision with root package name */
    final long f6696e;

    /* renamed from: f  reason: collision with root package name */
    final zzau f6697f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzar(zzgk zzgkVar, String str, String str2, String str3, long j2, long j3, Bundle bundle) {
        zzau zzauVar;
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        this.f6692a = str2;
        this.f6693b = str3;
        this.f6694c = true == TextUtils.isEmpty(str) ? null : str;
        this.f6695d = j2;
        this.f6696e = j3;
        if (j3 != 0 && j3 > j2) {
            zzgkVar.zzay().zzk().zzb("Event created with reverse previous/current timestamps. appId", zzfa.g(str2));
        }
        if (bundle == null || bundle.isEmpty()) {
            zzauVar = new zzau(new Bundle());
        } else {
            Bundle bundle2 = new Bundle(bundle);
            Iterator<String> it = bundle2.keySet().iterator();
            while (it.hasNext()) {
                String next = it.next();
                if (next == null) {
                    zzgkVar.zzay().zzd().zza("Param name can't be null");
                } else {
                    Object e2 = zzgkVar.zzv().e(next, bundle2.get(next));
                    if (e2 == null) {
                        zzgkVar.zzay().zzk().zzb("Param value can't be null", zzgkVar.zzj().e(next));
                    } else {
                        zzgkVar.zzv().o(bundle2, next, e2);
                    }
                }
                it.remove();
            }
            zzauVar = new zzau(bundle2);
        }
        this.f6697f = zzauVar;
    }

    private zzar(zzgk zzgkVar, String str, String str2, String str3, long j2, long j3, zzau zzauVar) {
        Preconditions.checkNotEmpty(str2);
        Preconditions.checkNotEmpty(str3);
        Preconditions.checkNotNull(zzauVar);
        this.f6692a = str2;
        this.f6693b = str3;
        this.f6694c = true == TextUtils.isEmpty(str) ? null : str;
        this.f6695d = j2;
        this.f6696e = j3;
        if (j3 != 0 && j3 > j2) {
            zzgkVar.zzay().zzk().zzc("Event created with reverse previous/current timestamps. appId, name", zzfa.g(str2), zzfa.g(str3));
        }
        this.f6697f = zzauVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzar a(zzgk zzgkVar, long j2) {
        return new zzar(zzgkVar, this.f6694c, this.f6692a, this.f6693b, this.f6695d, j2, this.f6697f);
    }

    public final String toString() {
        String str = this.f6692a;
        String str2 = this.f6693b;
        String obj = this.f6697f.toString();
        return "Event{appId='" + str + "', name='" + str2 + "', params=" + obj + "}";
    }
}
