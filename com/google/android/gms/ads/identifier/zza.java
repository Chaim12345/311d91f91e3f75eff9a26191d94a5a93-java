package com.google.android.gms.ads.identifier;

import android.net.Uri;
import java.util.Map;
/* loaded from: classes.dex */
final class zza extends Thread {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Map f5594a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(AdvertisingIdClient advertisingIdClient, Map map) {
        this.f5594a = map;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Map map = this.f5594a;
        Uri.Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
        for (String str : map.keySet()) {
            buildUpon.appendQueryParameter(str, (String) map.get(str));
        }
        zzc.zza(buildUpon.build().toString());
    }
}
