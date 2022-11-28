package com.google.android.libraries.places.internal;

import android.content.Context;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.runtime.TransportRuntime;
/* loaded from: classes2.dex */
public final class zzex {
    private final Transport zza;

    public zzex(Context context) {
        TransportRuntime.initialize(context.getApplicationContext());
        this.zza = TransportRuntime.getInstance().newFactory("cct").getTransport("LE", zzlg.class, zzew.zza);
    }

    public final void zza(zzlg zzlgVar) {
        this.zza.send(Event.ofData(zzlgVar));
    }
}
