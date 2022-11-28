package com.google.android.gms.internal.measurement;

import android.net.Uri;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
public final class zzhg {
    private final Map zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhg(Map map) {
        this.zza = map;
    }

    @Nullable
    public final String zza(@Nullable Uri uri, @Nullable String str, @Nullable String str2, String str3) {
        if (uri != null) {
            Map map = (Map) this.zza.get(uri.toString());
            if (map == null) {
                return null;
            }
            return (String) map.get("".concat(String.valueOf(str3)));
        }
        return null;
    }
}
