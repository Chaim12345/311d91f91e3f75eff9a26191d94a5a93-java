package com.google.android.libraries.places.widget.internal.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzev;
import java.util.List;
/* loaded from: classes2.dex */
public final class zzr extends ListAdapter {
    private int zza;
    private boolean zzb;
    private final zze zzc;

    public zzr(zze zzeVar, byte[] bArr) {
        super(new zzq(null));
        this.zzb = true;
        this.zzc = zzeVar;
    }

    @Override // androidx.recyclerview.widget.ListAdapter
    public final void submitList(@Nullable List list) {
        try {
            int i2 = 0;
            this.zzb = (this.zza != 0 || list == null || list.isEmpty()) ? false : true;
            if (list != null) {
                i2 = list.size();
            }
            this.zza = i2;
            super.submitList(list);
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: zza */
    public final zzs onCreateViewHolder(ViewGroup viewGroup, int i2) {
        try {
            return new zzs(this.zzc, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.places_autocomplete_prediction, viewGroup, false), null);
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: zzb */
    public final void onBindViewHolder(zzs zzsVar, int i2) {
        try {
            zzsVar.zza((AutocompletePrediction) getItem(i2), this.zzb);
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }
}
