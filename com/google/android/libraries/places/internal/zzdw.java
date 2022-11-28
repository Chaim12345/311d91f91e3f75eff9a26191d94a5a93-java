package com.google.android.libraries.places.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
/* loaded from: classes2.dex */
public final class zzdw {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int zza(@Nullable String str) {
        char c2;
        if (str == null) {
            return 13;
        }
        switch (str.hashCode()) {
            case -1698126997:
                if (str.equals("REQUEST_DENIED")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -1125000185:
                if (str.equals("INVALID_REQUEST")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case -813482689:
                if (str.equals("ZERO_RESULTS")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 2524:
                if (str.equals("OK")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 1023286998:
                if (str.equals("NOT_FOUND")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 1831775833:
                if (str.equals("OVER_QUERY_LIMIT")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0 || c2 == 1) {
            return 0;
        }
        if (c2 != 2) {
            if (c2 != 3) {
                if (c2 != 4) {
                    if (c2 != 5) {
                        return 13;
                    }
                    return PlacesStatusCodes.NOT_FOUND;
                }
                return PlacesStatusCodes.INVALID_REQUEST;
            }
            return PlacesStatusCodes.REQUEST_DENIED;
        }
        return PlacesStatusCodes.OVER_QUERY_LIMIT;
    }

    @Nullable
    public static String zzb(@Nullable String str, @Nullable String str2) {
        return TextUtils.isEmpty(str2) ? str : str2;
    }
}
