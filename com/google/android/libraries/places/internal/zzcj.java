package com.google.android.libraries.places.internal;

import android.location.Location;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
/* loaded from: classes2.dex */
public final class zzcj implements PlacesClient {
    private final zzbn zza;
    private final zzbd zzb;
    private final zzbi zzc;
    private final zzel zzd;
    private final zzas zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcj(zzbn zzbnVar, zzbd zzbdVar, zzbi zzbiVar, zzel zzelVar, zzas zzasVar) {
        this.zza = zzbnVar;
        this.zzb = zzbdVar;
        this.zzc = zzbiVar;
        this.zzd = zzelVar;
        this.zze = zzasVar;
    }

    private static void zzg(zzba zzbaVar, @Nullable zzbb zzbbVar) {
        zzba.zza(zzbaVar, zzba.zzb("Duration"));
        zzax.zza();
        zzax.zza();
        zzba.zza(zzbaVar, zzba.zzb("Battery"));
        zzax.zza();
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    public final Task<FetchPhotoResponse> fetchPhoto(final FetchPhotoRequest fetchPhotoRequest) {
        try {
            zzha.zzc(fetchPhotoRequest, "Request must not be null.");
            zzax.zza();
            final zzbb zza = zzbb.zza();
            return this.zza.zza(fetchPhotoRequest).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzcd
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzcj.this.zzc(fetchPhotoRequest, zza, task);
                }
            }).continueWithTask(zzch.zza);
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    public final Task<FetchPlaceResponse> fetchPlace(final FetchPlaceRequest fetchPlaceRequest) {
        try {
            zzha.zzc(fetchPlaceRequest, "Request must not be null.");
            zzax.zza();
            final zzbb zza = zzbb.zza();
            return this.zza.zzb(fetchPlaceRequest).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzce
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzcj.this.zzd(fetchPlaceRequest, zza, task);
                }
            }).continueWithTask(zzch.zza);
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    public final Task<FindAutocompletePredictionsResponse> findAutocompletePredictions(final FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        try {
            zzha.zzc(findAutocompletePredictionsRequest, "Request must not be null.");
            zzax.zza();
            final zzbb zza = zzbb.zza();
            return this.zza.zzc(findAutocompletePredictionsRequest).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzcf
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzcj.this.zze(findAutocompletePredictionsRequest, zza, task);
                }
            }).continueWithTask(zzch.zza);
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    @Override // com.google.android.libraries.places.api.net.PlacesClient
    @RequiresPermission(allOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_WIFI_STATE"})
    public final Task<FindCurrentPlaceResponse> findCurrentPlace(FindCurrentPlaceRequest findCurrentPlaceRequest) {
        return zza(findCurrentPlaceRequest, null);
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_WIFI_STATE"})
    public final Task zza(final FindCurrentPlaceRequest findCurrentPlaceRequest, @Nullable String str) {
        try {
            zzha.zzc(findCurrentPlaceRequest, "Request must not be null.");
            final long zza = this.zze.zza();
            zzax.zza();
            final zzbb zza2 = zzbb.zza();
            return this.zzb.zza(findCurrentPlaceRequest.getCancellationToken()).onSuccessTask(new SuccessContinuation(findCurrentPlaceRequest, null) { // from class: com.google.android.libraries.places.internal.zzci
                public final /* synthetic */ FindCurrentPlaceRequest zzb;

                @Override // com.google.android.gms.tasks.SuccessContinuation
                public final Task then(Object obj) {
                    return zzcj.this.zzb(this.zzb, null, (Location) obj);
                }
            }).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzcg
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    return zzcj.this.zzf(findCurrentPlaceRequest, zza, zza2, task);
                }
            }).continueWithTask(zzch.zza);
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ Task zzb(FindCurrentPlaceRequest findCurrentPlaceRequest, String str, Location location) {
        zzha.zzc(location, "Location must not be null.");
        return this.zza.zzd(findCurrentPlaceRequest, location, this.zzc.zza(null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FetchPhotoResponse zzc(FetchPhotoRequest fetchPhotoRequest, zzbb zzbbVar, Task task) {
        this.zzd.zza(fetchPhotoRequest);
        zzg(zzba.zzb("FetchPhoto"), zzbbVar);
        return (FetchPhotoResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FetchPlaceResponse zzd(FetchPlaceRequest fetchPlaceRequest, zzbb zzbbVar, Task task) {
        this.zzd.zzc(fetchPlaceRequest);
        zzg(zzba.zzb("FetchPlace"), zzbbVar);
        return (FetchPlaceResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FindAutocompletePredictionsResponse zze(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest, zzbb zzbbVar, Task task) {
        this.zzd.zze(findAutocompletePredictionsRequest);
        zzg(zzba.zzb("FindAutocompletePredictions"), zzbbVar);
        return (FindAutocompletePredictionsResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FindCurrentPlaceResponse zzf(FindCurrentPlaceRequest findCurrentPlaceRequest, long j2, zzbb zzbbVar, Task task) {
        this.zzd.zzg(findCurrentPlaceRequest, task, j2, this.zze.zza());
        zzg(zzba.zzb("FindCurrentPlace"), zzbbVar);
        return (FindCurrentPlaceResponse) task.getResult();
    }
}
