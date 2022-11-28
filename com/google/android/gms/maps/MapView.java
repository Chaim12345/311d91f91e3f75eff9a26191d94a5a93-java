package com.google.android.gms.maps;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
/* loaded from: classes2.dex */
public class MapView extends FrameLayout {
    private final zzah zza;

    public MapView(@NonNull Context context) {
        super(context);
        this.zza = new zzah(this, context, null);
        setClickable(true);
    }

    public MapView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.zza = new zzah(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }

    public MapView(@NonNull Context context, @NonNull AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.zza = new zzah(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        setClickable(true);
    }

    public MapView(@NonNull Context context, @Nullable GoogleMapOptions googleMapOptions) {
        super(context);
        this.zza = new zzah(this, context, googleMapOptions);
        setClickable(true);
    }

    public void getMapAsync(@NonNull OnMapReadyCallback onMapReadyCallback) {
        Preconditions.checkMainThread("getMapAsync() must be called on the main thread");
        Preconditions.checkNotNull(onMapReadyCallback, "callback must not be null.");
        this.zza.zza(onMapReadyCallback);
    }

    public void onCreate(@Nullable Bundle bundle) {
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitAll().build());
        try {
            this.zza.onCreate(bundle);
            if (this.zza.getDelegate() == null) {
                DeferredLifecycleHelper.showGooglePlayUnavailableMessage(this);
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public void onDestroy() {
        this.zza.onDestroy();
    }

    public void onEnterAmbient(@Nullable Bundle bundle) {
        Preconditions.checkMainThread("onEnterAmbient() must be called on the main thread");
        zzah zzahVar = this.zza;
        if (zzahVar.getDelegate() != null) {
            zzahVar.getDelegate().zza(bundle);
        }
    }

    public void onExitAmbient() {
        Preconditions.checkMainThread("onExitAmbient() must be called on the main thread");
        zzah zzahVar = this.zza;
        if (zzahVar.getDelegate() != null) {
            zzahVar.getDelegate().zzb();
        }
    }

    public void onLowMemory() {
        this.zza.onLowMemory();
    }

    public void onPause() {
        this.zza.onPause();
    }

    public void onResume() {
        this.zza.onResume();
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        this.zza.onSaveInstanceState(bundle);
    }

    public void onStart() {
        this.zza.onStart();
    }

    public void onStop() {
        this.zza.onStop();
    }
}
