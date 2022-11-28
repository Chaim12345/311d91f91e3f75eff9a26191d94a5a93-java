package com.google.android.gms.internal.safetynet;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafeBrowsingData;
import com.google.android.gms.safetynet.SafeBrowsingThreat;
import com.google.android.gms.safetynet.SafetyNetApi;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class zzac implements SafetyNetApi.SafeBrowsingResult {
    private Status zza;
    @Nullable
    private final SafeBrowsingData zzb;
    @Nullable
    private String zzc;
    private long zzd;
    @Nullable
    private byte[] zze;

    public zzac(Status status, @Nullable SafeBrowsingData safeBrowsingData) {
        this.zza = status;
        this.zzb = safeBrowsingData;
        this.zzc = null;
        if (safeBrowsingData != null) {
            this.zzc = safeBrowsingData.getMetadata();
            this.zzd = safeBrowsingData.getLastUpdateTimeMs();
            this.zze = safeBrowsingData.getState();
        } else if (status.isSuccess()) {
            this.zza = new Status(8);
        }
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult
    public final List<SafeBrowsingThreat> getDetectedThreats() {
        ArrayList arrayList = new ArrayList();
        String str = this.zzc;
        if (str != null) {
            try {
                JSONArray jSONArray = new JSONObject(str).getJSONArray("matches");
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    try {
                        arrayList.add(new SafeBrowsingThreat(Integer.parseInt(jSONArray.getJSONObject(i2).getString("threat_type"))));
                    } catch (NumberFormatException | JSONException unused) {
                    }
                }
            } catch (JSONException unused2) {
            }
        }
        return arrayList;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult
    public final long getLastUpdateTimeMs() {
        return this.zzd;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult
    @Nullable
    public final String getMetadata() {
        return this.zzc;
    }

    @Override // com.google.android.gms.safetynet.SafetyNetApi.SafeBrowsingResult
    @Nullable
    public final byte[] getState() {
        return this.zze;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zza;
    }
}
