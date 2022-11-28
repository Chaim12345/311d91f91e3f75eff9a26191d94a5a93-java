package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.IInterface;
import androidx.annotation.NonNull;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.GoogleMapOptions;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public interface IMapFragmentDelegate extends IInterface {
    @NonNull
    IGoogleMapDelegate getMap();

    void getMapAsync(zzar zzarVar);

    boolean isReady();

    void onCreate(@NonNull Bundle bundle);

    @NonNull
    IObjectWrapper onCreateView(@NonNull IObjectWrapper iObjectWrapper, @NonNull IObjectWrapper iObjectWrapper2, @NonNull Bundle bundle);

    void onDestroy();

    void onDestroyView();

    void onEnterAmbient(@NonNull Bundle bundle);

    void onExitAmbient();

    void onInflate(@NonNull IObjectWrapper iObjectWrapper, @Nullable GoogleMapOptions googleMapOptions, @NonNull Bundle bundle);

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(@NonNull Bundle bundle);

    void onStart();

    void onStop();
}
