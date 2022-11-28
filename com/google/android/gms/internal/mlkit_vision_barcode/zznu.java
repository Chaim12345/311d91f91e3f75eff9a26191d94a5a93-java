package com.google.android.gms.internal.mlkit_vision_barcode;

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
/* loaded from: classes2.dex */
public final class zznu implements zznl {
    @Nullable
    private Provider zza;
    private final Provider zzb;
    private final zzne zzc;

    public zznu(Context context, zzne zzneVar) {
        this.zzc = zzneVar;
        CCTDestination cCTDestination = CCTDestination.INSTANCE;
        TransportRuntime.initialize(context);
        final TransportFactory newFactory = TransportRuntime.getInstance().newFactory(cCTDestination);
        if (cCTDestination.getSupportedEncodings().contains(Encoding.of("json"))) {
            this.zza = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zzns
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("json"), zznq.zza);
                }
            });
        }
        this.zzb = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zznt
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return TransportFactory.this.getTransport("FIREBASE_ML_SDK", byte[].class, Encoding.of("proto"), zznr.zza);
            }
        });
    }

    @VisibleForTesting
    static Event a(zzne zzneVar, zznp zznpVar) {
        int zza = zzneVar.zza();
        int zza2 = zznpVar.zza();
        byte[] zzc = zznpVar.zzc(zza, false);
        return zza2 != 0 ? Event.ofData(zzc) : Event.ofTelemetry(zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zznl
    public final void zza(zznp zznpVar) {
        Provider provider;
        if (this.zzc.zza() == 0) {
            provider = this.zza;
            if (provider == null) {
                return;
            }
        } else {
            provider = this.zzb;
        }
        ((Transport) provider.get()).send(a(this.zzc, zznpVar));
    }
}
