package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
public interface zzl extends IInterface {
    boolean zzA();

    double zzd();

    float zze();

    float zzf();

    int zzg();

    int zzh();

    int zzi();

    IObjectWrapper zzj();

    LatLng zzk();

    String zzl();

    List<PatternItem> zzm();

    void zzn();

    void zzo(LatLng latLng);

    void zzp(boolean z);

    void zzq(int i2);

    void zzr(double d2);

    void zzs(int i2);

    void zzt(@Nullable List<PatternItem> list);

    void zzu(float f2);

    void zzv(IObjectWrapper iObjectWrapper);

    void zzw(boolean z);

    void zzx(float f2);

    boolean zzy(@Nullable zzl zzlVar);

    boolean zzz();
}
