package com.google.android.gms.location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Response;
/* loaded from: classes2.dex */
public class LocationSettingsResponse extends Response<LocationSettingsResult> {
    public LocationSettingsResponse() {
    }

    public LocationSettingsResponse(@NonNull LocationSettingsResult locationSettingsResult) {
        super(locationSettingsResult);
    }

    @Nullable
    public LocationSettingsStates getLocationSettingsStates() {
        return ((LocationSettingsResult) a()).getLocationSettingsStates();
    }
}
