package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;
/* loaded from: classes.dex */
public final class zzlt implements zzlk {
    @Nullable
    private Provider zza;
    private final Provider zzb;
    private final zzle zzc;

    public zzlt(Context context, zzle zzleVar) {
        this.zzc = zzleVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory newFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.zza = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_common.zzlr
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), zzlp.zza);
                }
            });
        }
        this.zzb = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_common.zzls
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), zzlq.zza);
            }
        });
    }

    @VisibleForTesting
    static Event a(zzle zzleVar, zzlc zzlcVar) {
        return Event.ofTelemetry(zzlcVar.zze(zzleVar.zza(), false));
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzlk
    public final void zza(zzlc zzlcVar) {
        Provider provider;
        if (this.zzc.zza() == 0) {
            provider = this.zza;
            if (provider == null) {
                return;
            }
        } else {
            provider = this.zzb;
        }
        ((Transport) provider.get()).send(a(this.zzc, zzlcVar));
    }
}
