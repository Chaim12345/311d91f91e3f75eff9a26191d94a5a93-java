package com.google.android.gms.measurement.internal;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzex implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f6721a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ String f6722b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Object f6723c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Object f6724d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Object f6725e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzfa f6726f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzex(zzfa zzfaVar, int i2, String str, Object obj, Object obj2, Object obj3) {
        this.f6726f = zzfaVar;
        this.f6721a = i2;
        this.f6722b = str;
        this.f6723c = obj;
        this.f6724d = obj2;
        this.f6725e = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        char c2;
        long j2;
        char c3;
        long j3;
        zzfa zzfaVar;
        char c4;
        zzfp zzm = this.f6726f.f6809a.zzm();
        if (!zzm.d()) {
            Log.println(6, this.f6726f.zzq(), "Persisted config not initialized. Not logging error/warn");
            return;
        }
        zzfa zzfaVar2 = this.f6726f;
        c2 = zzfaVar2.zza;
        if (c2 == 0) {
            if (zzfaVar2.f6809a.zzf().zzy()) {
                zzfaVar = this.f6726f;
                zzfaVar.f6809a.zzaw();
                c4 = 'C';
            } else {
                zzfaVar = this.f6726f;
                zzfaVar.f6809a.zzaw();
                c4 = 'c';
            }
            zzfaVar.zza = c4;
        }
        zzfa zzfaVar3 = this.f6726f;
        j2 = zzfaVar3.zzb;
        if (j2 < 0) {
            zzfaVar3.f6809a.zzf().zzh();
            zzfaVar3.zzb = 64000L;
        }
        char charAt = "01VDIWEA?".charAt(this.f6721a);
        zzfa zzfaVar4 = this.f6726f;
        c3 = zzfaVar4.zza;
        j3 = zzfaVar4.zzb;
        String str = ExifInterface.GPS_MEASUREMENT_2D + charAt + c3 + j3 + ":" + zzfa.h(true, this.f6722b, this.f6723c, this.f6724d, this.f6725e);
        if (str.length() > 1024) {
            str = this.f6722b.substring(0, 1024);
        }
        zzfn zzfnVar = zzm.zzb;
        if (zzfnVar != null) {
            zzfnVar.zzb(str, 1L);
        }
    }
}
