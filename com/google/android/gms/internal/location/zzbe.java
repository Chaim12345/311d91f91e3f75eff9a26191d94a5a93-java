package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.ConnectionCallbacks;
import com.google.android.gms.common.api.internal.IStatusCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.OnConnectionFailedListener;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ICancelToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.CurrentLocationRequest;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LastLocationRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public final class zzbe extends GmsClient {
    public static final /* synthetic */ int zze = 0;
    private final Map zzf;
    private final Map zzg;
    private final Map zzh;
    private final String zzi;
    private boolean zzj;

    public zzbe(Context context, Looper looper, ClientSettings clientSettings, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str) {
        super(context, looper, 23, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzf = new HashMap();
        this.zzg = new HashMap();
        this.zzh = new HashMap();
        this.zzi = str;
    }

    private final boolean zzG(Feature feature) {
        Feature feature2;
        Feature[] availableFeatures = getAvailableFeatures();
        if (availableFeatures == null) {
            return false;
        }
        int length = availableFeatures.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                feature2 = null;
                break;
            }
            feature2 = availableFeatures[i2];
            if (feature.getName().equals(feature2.getName())) {
                break;
            }
            i2++;
        }
        return feature2 != null && feature2.getVersion() >= feature.getVersion();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        return queryLocalInterface instanceof zzam ? (zzam) queryLocalInterface : new zzal(iBinder);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final Bundle d() {
        Bundle bundle = new Bundle();
        bundle.putString("client_name", this.zzi);
        return bundle;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        synchronized (this) {
            if (isConnected()) {
                try {
                    synchronized (this.zzf) {
                        for (zzbc zzbcVar : this.zzf.values()) {
                            ((zzam) getService()).zzz(zzbh.zzb(zzbcVar, null));
                        }
                        this.zzf.clear();
                    }
                    synchronized (this.zzg) {
                        for (zzay zzayVar : this.zzg.values()) {
                            ((zzam) getService()).zzz(zzbh.zza(zzayVar, null));
                        }
                        this.zzg.clear();
                    }
                    synchronized (this.zzh) {
                        for (zzaz zzazVar : this.zzh.values()) {
                            ((zzam) getService()).zzy(new zzj(2, null, zzazVar, null));
                        }
                        this.zzh.clear();
                    }
                    if (this.zzj) {
                        zzF(false, new zzat(this));
                    }
                } catch (Exception e2) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e2);
                }
            }
            super.disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final String g() {
        return "com.google.android.gms.location.internal.IGoogleLocationManagerService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final Feature[] getApiFeatures() {
        return com.google.android.gms.location.zzy.zzj;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 11717000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.location.internal.GoogleLocationManagerService.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final boolean usesClientTelemetry() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzA(PendingIntent pendingIntent, zzai zzaiVar) {
        ((zzam) getService()).zzz(new zzbh(2, null, null, null, pendingIntent, zzaiVar, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzB(zzbf zzbfVar, ListenerHolder listenerHolder, zzai zzaiVar) {
        zzay zzayVar;
        ListenerHolder.ListenerKey listenerKey = listenerHolder.getListenerKey();
        if (listenerKey == null) {
            Log.e("LocationClientImpl", "requested location updates on dead listener", new RuntimeException());
            return;
        }
        getContext();
        synchronized (this) {
            synchronized (this.zzg) {
                zzay zzayVar2 = (zzay) this.zzg.get(listenerKey);
                if (zzayVar2 == null) {
                    zzayVar2 = new zzay(listenerHolder);
                    this.zzg.put(listenerKey, zzayVar2);
                }
                zzayVar = zzayVar2;
            }
            ((zzam) getService()).zzz(new zzbh(1, zzbfVar, null, zzayVar, null, zzaiVar, listenerKey.toIdString()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzC(zzbf zzbfVar, ListenerHolder listenerHolder, zzai zzaiVar) {
        zzbc zzbcVar;
        ListenerHolder.ListenerKey listenerKey = listenerHolder.getListenerKey();
        if (listenerKey == null) {
            Log.e("LocationClientImpl", "requested location updates on dead listener", new RuntimeException());
            return;
        }
        getContext();
        synchronized (this) {
            synchronized (this.zzf) {
                zzbc zzbcVar2 = (zzbc) this.zzf.get(listenerKey);
                if (zzbcVar2 == null) {
                    zzbcVar2 = new zzbc(listenerHolder);
                    this.zzf.put(listenerKey, zzbcVar2);
                }
                zzbcVar = zzbcVar2;
            }
            ((zzam) getService()).zzz(new zzbh(1, zzbfVar, zzbcVar, null, null, zzaiVar, listenerKey.toIdString()));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void zzD(zzbf zzbfVar, PendingIntent pendingIntent, zzai zzaiVar) {
        getContext();
        int hashCode = pendingIntent.hashCode();
        StringBuilder sb = new StringBuilder(25);
        sb.append("PendingIntent@");
        sb.append(hashCode);
        ((zzam) getService()).zzz(new zzbh(1, zzbfVar, null, null, pendingIntent, zzaiVar, sb.toString()));
    }

    public final void zzE(Location location, IStatusCallback iStatusCallback) {
        if (zzG(com.google.android.gms.location.zzy.zzh)) {
            ((zzam) getService()).zzv(location, iStatusCallback);
            return;
        }
        ((zzam) getService()).zzu(location);
        iStatusCallback.onResult(Status.RESULT_SUCCESS);
    }

    public final void zzF(boolean z, IStatusCallback iStatusCallback) {
        if (zzG(com.google.android.gms.location.zzy.zzg)) {
            ((zzam) getService()).zzx(z, iStatusCallback);
        } else {
            ((zzam) getService()).zzw(z);
            iStatusCallback.onResult(Status.RESULT_SUCCESS);
        }
        this.zzj = z;
    }

    public final LocationAvailability zzp() {
        return ((zzam) getService()).zzf(getContext().getPackageName());
    }

    public final void zzq(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, BaseImplementation.ResultHolder resultHolder) {
        Preconditions.checkNotNull(geofencingRequest, "geofencingRequest can't be null.");
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        Preconditions.checkNotNull(resultHolder, "ResultHolder not provided.");
        ((zzam) getService()).zzg(geofencingRequest, pendingIntent, new zzba(resultHolder));
    }

    public final void zzr(LocationSettingsRequest locationSettingsRequest, BaseImplementation.ResultHolder resultHolder, String str) {
        Preconditions.checkArgument(locationSettingsRequest != null, "locationSettingsRequest can't be null nor empty.");
        Preconditions.checkArgument(resultHolder != null, "listener can't be null.");
        ((zzam) getService()).zzh(locationSettingsRequest, new zzbd(resultHolder), null);
    }

    public final void zzs(zzai zzaiVar) {
        ((zzam) getService()).zzi(zzaiVar);
    }

    public final void zzt(CurrentLocationRequest currentLocationRequest, CancellationToken cancellationToken, zzao zzaoVar) {
        if (zzG(com.google.android.gms.location.zzy.zze)) {
            final ICancelToken zze2 = ((zzam) getService()).zze(currentLocationRequest, zzaoVar);
            if (cancellationToken != null) {
                cancellationToken.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.gms.internal.location.zzar
                    @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                    public final void onCanceled() {
                        ICancelToken iCancelToken = ICancelToken.this;
                        int i2 = zzbe.zze;
                        try {
                            iCancelToken.cancel();
                        } catch (RemoteException unused) {
                        }
                    }
                });
                return;
            }
            return;
        }
        final AtomicReference atomicReference = new AtomicReference();
        OnTokenCanceledListener onTokenCanceledListener = new OnTokenCanceledListener() { // from class: com.google.android.gms.internal.location.zzas
            @Override // com.google.android.gms.tasks.OnTokenCanceledListener
            public final void onCanceled() {
                zzbe zzbeVar = zzbe.this;
                ListenerHolder.ListenerKey listenerKey = ((ListenerHolder) Preconditions.checkNotNull((ListenerHolder) atomicReference.get())).getListenerKey();
                if (listenerKey != null) {
                    try {
                        zzbeVar.zzy(listenerKey, null);
                    } catch (RemoteException unused) {
                    }
                }
            }
        };
        ListenerHolder createListenerHolder = ListenerHolders.createListenerHolder(new zzau(this, zzaoVar, onTokenCanceledListener), zzbn.zza(Looper.getMainLooper()), LocationCallback.class.getSimpleName());
        atomicReference.set(createListenerHolder);
        if (cancellationToken != null) {
            cancellationToken.onCanceledRequested(onTokenCanceledListener);
        }
        LocationRequest create = LocationRequest.create();
        create.setPriority(currentLocationRequest.getPriority());
        create.setInterval(0L);
        create.setFastestInterval(0L);
        create.setExpirationDuration(currentLocationRequest.getDurationMillis());
        zzbf zzc = zzbf.zzc(null, create);
        zzc.f5881i = true;
        zzc.zze(currentLocationRequest.getMaxUpdateAgeMillis());
        zzB(zzc, createListenerHolder, new zzav(this, zzaoVar));
    }

    public final void zzu(LastLocationRequest lastLocationRequest, zzao zzaoVar) {
        if (zzG(com.google.android.gms.location.zzy.zzf)) {
            ((zzam) getService()).zzj(lastLocationRequest, zzaoVar);
        } else {
            zzaoVar.zzb(Status.RESULT_SUCCESS, ((zzam) getService()).zzd());
        }
    }

    public final void zzv(PendingIntent pendingIntent) {
        Preconditions.checkNotNull(pendingIntent);
        ((zzam) getService()).zzl(pendingIntent);
    }

    public final void zzw(PendingIntent pendingIntent, BaseImplementation.ResultHolder resultHolder) {
        Preconditions.checkNotNull(pendingIntent, "PendingIntent must be specified.");
        Preconditions.checkNotNull(resultHolder, "ResultHolder not provided.");
        ((zzam) getService()).zzn(pendingIntent, new zzba(resultHolder), getContext().getPackageName());
    }

    public final void zzx(List list, BaseImplementation.ResultHolder resultHolder) {
        Preconditions.checkArgument(list != null && list.size() > 0, "geofenceRequestIds can't be null nor empty.");
        Preconditions.checkNotNull(resultHolder, "ResultHolder not provided.");
        ((zzam) getService()).zzo((String[]) list.toArray(new String[0]), new zzba(resultHolder), getContext().getPackageName());
    }

    public final void zzy(ListenerHolder.ListenerKey listenerKey, zzai zzaiVar) {
        Preconditions.checkNotNull(listenerKey, "Invalid null listener key");
        synchronized (this.zzg) {
            zzay zzayVar = (zzay) this.zzg.remove(listenerKey);
            if (zzayVar != null) {
                zzayVar.zzc();
                ((zzam) getService()).zzz(zzbh.zza(zzayVar, zzaiVar));
            }
        }
    }

    public final void zzz(ListenerHolder.ListenerKey listenerKey, zzai zzaiVar) {
        Preconditions.checkNotNull(listenerKey, "Invalid null listener key");
        synchronized (this.zzf) {
            zzbc zzbcVar = (zzbc) this.zzf.remove(listenerKey);
            if (zzbcVar != null) {
                zzbcVar.zzc();
                ((zzam) getService()).zzz(zzbh.zzb(zzbcVar, zzaiVar));
            }
        }
    }
}
