package com.google.android.libraries.places.widget.internal.ui;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.internal.zzev;
/* loaded from: classes2.dex */
public final class zzs extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView zza;
    private final TextView zzb;
    private AutocompletePrediction zzc;
    private boolean zzd;
    private final zze zze;

    public zzs(zze zzeVar, View view, byte[] bArr) {
        super(view);
        this.zze = zzeVar;
        this.zza = (TextView) view.findViewById(R.id.places_autocomplete_prediction_primary_text);
        this.zzb = (TextView) view.findViewById(R.id.places_autocomplete_prediction_secondary_text);
        this.itemView.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        try {
            zze zzeVar = this.zze;
            zzeVar.zza.zze(this.zzc, getAdapterPosition());
        } catch (Error | RuntimeException e2) {
            zzev.zzb(e2);
            throw e2;
        }
    }

    public final void zza(AutocompletePrediction autocompletePrediction, boolean z) {
        TextView textView;
        int i2;
        this.zzc = autocompletePrediction;
        this.zzd = z;
        this.zza.setText(autocompletePrediction.getPrimaryText(new ForegroundColorSpan(ContextCompat.getColor(this.itemView.getContext(), R.color.places_autocomplete_prediction_primary_text_highlight))));
        SpannableString secondaryText = autocompletePrediction.getSecondaryText(null);
        this.zzb.setText(secondaryText);
        if (secondaryText.length() == 0) {
            this.zzb.setVisibility(8);
            textView = this.zza;
            i2 = 16;
        } else {
            this.zzb.setVisibility(0);
            textView = this.zza;
            i2 = 80;
        }
        textView.setGravity(i2);
    }

    public final boolean zzb() {
        return this.zzd;
    }
}
