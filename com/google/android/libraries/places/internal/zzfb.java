package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import java.util.List;
import java.util.Objects;
/* loaded from: classes2.dex */
final class zzfb extends zzfk {
    private AutocompleteActivityMode zza;
    private zzhs zzb;
    private zzfj zzc;
    private String zzd;
    private String zze;
    private LocationBias zzf;
    private LocationRestriction zzg;
    private zzhs zzh;
    private TypeFilter zzi;
    private int zzj;
    private int zzk;
    private byte zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfb() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzfb(zzfl zzflVar, zzfa zzfaVar) {
        this.zza = zzflVar.zzh();
        this.zzb = zzflVar.zzj();
        this.zzc = zzflVar.zzf();
        this.zzd = zzflVar.zzl();
        this.zze = zzflVar.zzk();
        this.zzf = zzflVar.zzc();
        this.zzg = zzflVar.zzd();
        this.zzh = zzflVar.zzi();
        this.zzi = zzflVar.zze();
        this.zzj = zzflVar.zza();
        this.zzk = zzflVar.zzb();
        this.zzl = (byte) 3;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zza(List list) {
        this.zzh = zzhs.zzk(list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzb(@Nullable String str) {
        this.zze = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzc(@Nullable String str) {
        this.zzd = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzd(@Nullable LocationBias locationBias) {
        this.zzf = locationBias;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zze(@Nullable LocationRestriction locationRestriction) {
        this.zzg = locationRestriction;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzf(AutocompleteActivityMode autocompleteActivityMode) {
        Objects.requireNonNull(autocompleteActivityMode, "Null mode");
        this.zza = autocompleteActivityMode;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzg(zzfj zzfjVar) {
        Objects.requireNonNull(zzfjVar, "Null origin");
        this.zzc = zzfjVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzh(List list) {
        this.zzb = zzhs.zzk(list);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzi(int i2) {
        this.zzj = i2;
        this.zzl = (byte) (this.zzl | 1);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzj(int i2) {
        this.zzk = i2;
        this.zzl = (byte) (this.zzl | 2);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfk zzk(@Nullable TypeFilter typeFilter) {
        this.zzi = typeFilter;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzfk
    public final zzfl zzl() {
        AutocompleteActivityMode autocompleteActivityMode;
        zzhs zzhsVar;
        zzfj zzfjVar;
        zzhs zzhsVar2;
        if (this.zzl != 3 || (autocompleteActivityMode = this.zza) == null || (zzhsVar = this.zzb) == null || (zzfjVar = this.zzc) == null || (zzhsVar2 = this.zzh) == null) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" mode");
            }
            if (this.zzb == null) {
                sb.append(" placeFields");
            }
            if (this.zzc == null) {
                sb.append(" origin");
            }
            if (this.zzh == null) {
                sb.append(" countries");
            }
            if ((this.zzl & 1) == 0) {
                sb.append(" primaryColor");
            }
            if ((this.zzl & 2) == 0) {
                sb.append(" primaryColorDark");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzfe(autocompleteActivityMode, zzhsVar, zzfjVar, this.zzd, this.zze, this.zzf, this.zzg, zzhsVar2, this.zzi, this.zzj, this.zzk);
    }
}
