package com.google.android.libraries.places.internal;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnTokenCanceledListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public final class zzbs {
    private final RequestQueue zza;
    private final zzde zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbs(RequestQueue requestQueue, zzde zzdeVar, byte[] bArr) {
        this.zza = requestQueue;
        this.zzb = zzdeVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void zzc(TaskCompletionSource taskCompletionSource, VolleyError volleyError) {
        try {
            taskCompletionSource.trySetException(zzbm.zza(volleyError));
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    public final Task zza(zzca zzcaVar, final Class cls) {
        String zzc = zzcaVar.zzc();
        Map zzd = zzcaVar.zzd();
        CancellationToken zza = zzcaVar.zza();
        final TaskCompletionSource taskCompletionSource = zza != null ? new TaskCompletionSource(zza) : new TaskCompletionSource();
        final zzbr zzbrVar = new zzbr(this, 0, zzc, null, new Response.Listener() { // from class: com.google.android.libraries.places.internal.zzbp
            @Override // com.android.volley.Response.Listener
            public final void onResponse(Object obj) {
                zzbs.this.zzb(cls, taskCompletionSource, (JSONObject) obj);
            }
        }, new Response.ErrorListener() { // from class: com.google.android.libraries.places.internal.zzbo
            @Override // com.android.volley.Response.ErrorListener
            public final void onErrorResponse(VolleyError volleyError) {
                zzbs.zzc(TaskCompletionSource.this, volleyError);
            }
        }, zzd);
        if (zza != null) {
            zza.onCanceledRequested(new OnTokenCanceledListener() { // from class: com.google.android.libraries.places.internal.zzbq
                @Override // com.google.android.gms.tasks.OnTokenCanceledListener
                public final void onCanceled() {
                    JsonObjectRequest.this.cancel();
                }
            });
        }
        this.zza.add(zzbrVar);
        return taskCompletionSource.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zzb(Class cls, TaskCompletionSource taskCompletionSource, JSONObject jSONObject) {
        try {
            try {
                taskCompletionSource.trySetResult((zzcb) this.zzb.zza(jSONObject.toString(), cls));
            } catch (zzcc e2) {
                taskCompletionSource.trySetException(new ApiException(new Status(8, e2.getMessage())));
            }
        } catch (Error | RuntimeException e3) {
            zzev.zzb(e3);
            throw e3;
        }
    }
}
