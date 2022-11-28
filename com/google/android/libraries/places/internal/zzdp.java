package com.google.android.libraries.places.internal;

import android.location.Location;
import android.text.TextUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.android.libraries.places.api.model.PlaceLikelihood;
import com.google.android.libraries.places.api.net.FetchPhotoRequest;
import com.google.android.libraries.places.api.net.FetchPhotoResponse;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FetchPlaceResponse;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest;
import com.google.android.libraries.places.api.net.FindCurrentPlaceResponse;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import java.util.ArrayList;
import java.util.Locale;
/* loaded from: classes2.dex */
public final class zzdp implements zzbn {
    private final zzez zza;
    private final zzbs zzb;
    private final zzby zzc;
    private final zzel zzd;
    private final zzas zze;
    private final zzcq zzf;
    private final zzcu zzg;
    private final zzcy zzh;
    private final zzdc zzi;
    private final zzem zzj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdp(zzem zzemVar, zzez zzezVar, zzbs zzbsVar, zzby zzbyVar, zzel zzelVar, zzas zzasVar, zzcq zzcqVar, zzcu zzcuVar, zzcy zzcyVar, zzdc zzdcVar, byte[] bArr) {
        this.zzj = zzemVar;
        this.zza = zzezVar;
        this.zzb = zzbsVar;
        this.zzc = zzbyVar;
        this.zzd = zzelVar;
        this.zze = zzasVar;
        this.zzf = zzcqVar;
        this.zzg = zzcuVar;
        this.zzh = zzcyVar;
        this.zzi = zzdcVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ FetchPlaceResponse zzi(Task task) {
        zzct zzctVar = (zzct) task.getResult();
        int zza = zzdw.zza(zzctVar.status);
        if (PlacesStatusCodes.isError(zza)) {
            throw new ApiException(new Status(zza, zzdw.zzb(zzctVar.status, zzctVar.errorMessage)));
        }
        zzdv zzdvVar = zzctVar.result;
        String[] strArr = zzctVar.htmlAttributions;
        return FetchPlaceResponse.newInstance(zzds.zzc(zzdvVar, strArr != null ? zzhs.zzl(strArr) : null));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final /* synthetic */ FindCurrentPlaceResponse zzj(Task task) {
        zzdb zzdbVar = (zzdb) task.getResult();
        int zza = zzdw.zza(zzdbVar.status);
        if (PlacesStatusCodes.isError(zza)) {
            throw new ApiException(new Status(zza, zzdw.zzb(zzdbVar.status, zzdbVar.errorMessage)));
        }
        ArrayList arrayList = new ArrayList();
        zzdu[] zzduVarArr = zzdbVar.predictions;
        if (zzduVarArr != null) {
            for (zzdu zzduVar : zzduVarArr) {
                if (zzduVar.zza() == null) {
                    throw new ApiException(new Status(8, "Unexpected server error: PlaceLikelihood returned without a Place value"));
                }
                Double zzb = zzduVar.zzb();
                if (zzb == null) {
                    throw new ApiException(new Status(8, "Unexpected server error: PlaceLikelihood returned without a likelihood value"));
                }
                zzdv zza2 = zzduVar.zza();
                String[] strArr = zzdbVar.htmlAttributions;
                arrayList.add(PlaceLikelihood.newInstance(zzds.zzc(zza2, strArr != null ? zzhs.zzl(strArr) : null), zzb.doubleValue()));
            }
        }
        return FindCurrentPlaceResponse.newInstance(arrayList);
    }

    @Override // com.google.android.libraries.places.internal.zzbn
    public final Task zza(FetchPhotoRequest fetchPhotoRequest) {
        Integer maxWidth = fetchPhotoRequest.getMaxWidth();
        Integer maxHeight = fetchPhotoRequest.getMaxHeight();
        if (maxWidth == null && maxHeight == null) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Must include max width or max height in request.")));
        }
        if (maxWidth == null || maxWidth.intValue() > 0) {
            if (maxHeight == null || maxHeight.intValue() > 0) {
                String zza = this.zzj.zza();
                this.zzj.zze();
                zzcm zzcmVar = new zzcm(fetchPhotoRequest, zza, false, this.zza);
                final long zza2 = this.zze.zza();
                return this.zzc.zzb(zzcmVar, new zzcn()).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzdh
                    @Override // com.google.android.gms.tasks.Continuation
                    public final Object then(Task task) {
                        return FetchPhotoResponse.newInstance(((zzcp) task.getResult()).zza);
                    }
                }).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzdl
                    @Override // com.google.android.gms.tasks.Continuation
                    public final Object then(Task task) {
                        return zzdp.this.zze(zza2, task);
                    }
                });
            }
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, String.format("Max Height must not be < 1, but was: %d.", maxHeight))));
        }
        return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, String.format("Max Width must not be < 1, but was: %d.", maxWidth))));
    }

    @Override // com.google.android.libraries.places.internal.zzbn
    public final Task zzb(FetchPlaceRequest fetchPlaceRequest) {
        if (TextUtils.isEmpty(fetchPlaceRequest.getPlaceId())) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place ID must not be empty.")));
        }
        if (fetchPlaceRequest.getPlaceFields().isEmpty()) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty.")));
        }
        Locale zzb = this.zzj.zzb();
        String zza = this.zzj.zza();
        this.zzj.zze();
        zzcs zzcsVar = new zzcs(fetchPlaceRequest, zzb, zza, false, this.zza);
        final long zza2 = this.zze.zza();
        return this.zzb.zza(zzcsVar, zzct.class).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzdi
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzdp.zzi(task);
            }
        }).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzdm
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzdp.this.zzf(zza2, task);
            }
        });
    }

    @Override // com.google.android.libraries.places.internal.zzbn
    public final Task zzc(FindAutocompletePredictionsRequest findAutocompletePredictionsRequest) {
        String query = findAutocompletePredictionsRequest.getQuery();
        if (query == null || TextUtils.isEmpty(query.trim())) {
            return Tasks.forResult(FindAutocompletePredictionsResponse.newInstance(zzhs.zzm()));
        }
        Locale zzb = this.zzj.zzb();
        String zza = this.zzj.zza();
        this.zzj.zze();
        zzcw zzcwVar = new zzcw(findAutocompletePredictionsRequest, zzb, zza, false, this.zza);
        final long zza2 = this.zze.zza();
        return this.zzb.zza(zzcwVar, zzcx.class).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzdj
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzcy.zza((zzcx) task.getResult());
            }
        }).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzdn
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzdp.this.zzg(zza2, task);
            }
        });
    }

    @Override // com.google.android.libraries.places.internal.zzbn
    public final Task zzd(FindCurrentPlaceRequest findCurrentPlaceRequest, Location location, zzhs zzhsVar) {
        if (findCurrentPlaceRequest.getPlaceFields().isEmpty()) {
            return Tasks.forException(new ApiException(new Status((int) PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty.")));
        }
        Locale zzb = this.zzj.zzb();
        String zza = this.zzj.zza();
        this.zzj.zze();
        zzda zzdaVar = new zzda(findCurrentPlaceRequest, location, zzhsVar, zzb, zza, false, this.zza);
        final long zza2 = this.zze.zza();
        return this.zzb.zza(zzdaVar, zzdb.class).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzdk
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzdp.zzj(task);
            }
        }).continueWith(new Continuation() { // from class: com.google.android.libraries.places.internal.zzdo
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return zzdp.this.zzh(zza2, task);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FetchPhotoResponse zze(long j2, Task task) {
        this.zzd.zzb(task, j2, this.zze.zza());
        return (FetchPhotoResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FetchPlaceResponse zzf(long j2, Task task) {
        this.zzd.zzd(task, j2, this.zze.zza());
        return (FetchPlaceResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FindAutocompletePredictionsResponse zzg(long j2, Task task) {
        this.zzd.zzf(task, j2, this.zze.zza());
        return (FindAutocompletePredictionsResponse) task.getResult();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ FindCurrentPlaceResponse zzh(long j2, Task task) {
        this.zzd.zzh(task, j2, this.zze.zza());
        return (FindCurrentPlaceResponse) task.getResult();
    }
}
