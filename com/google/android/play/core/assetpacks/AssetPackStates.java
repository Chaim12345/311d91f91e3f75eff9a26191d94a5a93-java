package com.google.android.play.core.assetpacks;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class AssetPackStates {
    public static AssetPackStates zza(Bundle bundle, zzco zzcoVar, zzeb zzebVar) {
        return zzd(bundle, zzcoVar, zzebVar, new ArrayList(), zzbf.zza);
    }

    public static AssetPackStates zzb(Bundle bundle, zzco zzcoVar, zzeb zzebVar, zzbe zzbeVar) {
        return zzd(bundle, zzcoVar, zzebVar, new ArrayList(), zzbeVar);
    }

    public static AssetPackStates zzc(Bundle bundle, zzco zzcoVar, zzeb zzebVar, List list) {
        return zzd(bundle, zzcoVar, zzebVar, list, zzbf.zza);
    }

    private static AssetPackStates zzd(Bundle bundle, zzco zzcoVar, zzeb zzebVar, List list, zzbe zzbeVar) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList("pack_names");
        HashMap hashMap = new HashMap();
        int size = stringArrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            String str = stringArrayList.get(i2);
            hashMap.put(str, AssetPackState.a(bundle, str, zzcoVar, zzebVar, zzbeVar));
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            hashMap.put(str2, AssetPackState.zzb(str2, 4, 0, 0L, 0L, 0.0d, 1, "", ""));
        }
        return new zzbo(bundle.getLong("total_bytes_to_download"), hashMap);
    }

    public abstract Map<String, AssetPackState> packStates();

    public abstract long totalBytes();
}
