package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IInterface;
import androidx.annotation.NonNull;
import com.google.android.gms.dynamic.IObjectWrapper;
/* loaded from: classes2.dex */
public interface IMapViewDelegate extends IInterface {
    @NonNull
    IGoogleMapDelegate getMap();

    void getMapAsync(zzar zzarVar);

    @NonNull
    IObjectWrapper getView();

    void onCreate(@NonNull Bundle bundle);

    void onDestroy();

    void onEnterAmbient(@NonNull Bundle bundle);

    void onExitAmbient();

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(@NonNull Bundle bundle);

    void onStart();

    void onStop();
}
