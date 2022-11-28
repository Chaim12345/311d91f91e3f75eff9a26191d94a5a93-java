package com.google.android.gms.internal.maps;

import android.os.IInterface;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.List;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
public interface zzad extends IInterface {
    void zzA(float f2);

    boolean zzB(@Nullable zzad zzadVar);

    boolean zzC();

    boolean zzD();

    boolean zzE();

    float zzd();

    float zze();

    int zzf();

    int zzg();

    int zzh();

    IObjectWrapper zzi();

    Cap zzj();

    Cap zzk();

    String zzl();

    List<PatternItem> zzm();

    List<LatLng> zzn();

    void zzo();

    void zzp(boolean z);

    void zzq(int i2);

    void zzr(Cap cap);

    void zzs(boolean z);

    void zzt(int i2);

    void zzu(@Nullable List<PatternItem> list);

    void zzv(List<LatLng> list);

    void zzw(Cap cap);

    void zzx(IObjectWrapper iObjectWrapper);

    void zzy(boolean z);

    void zzz(float f2);
}
