package com.google.android.libraries.places.internal;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzbm {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static ApiException zza(VolleyError volleyError) {
        int i2 = 8;
        if (volleyError instanceof NetworkError) {
            i2 = 7;
        } else if (volleyError instanceof TimeoutError) {
            i2 = 15;
        } else if (!(volleyError instanceof ServerError) && !(volleyError instanceof ParseError)) {
            i2 = volleyError instanceof AuthFailureError ? PlacesStatusCodes.REQUEST_DENIED : 13;
        }
        NetworkResponse networkResponse = volleyError.networkResponse;
        return new ApiException(new Status(i2, String.format("Unexpected server error (HTTP Code: %s. Message: %s.)", networkResponse == null ? "N/A" : String.valueOf(networkResponse.statusCode), volleyError)));
    }
}
