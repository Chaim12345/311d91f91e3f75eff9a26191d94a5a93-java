package com.google.mlkit.vision.barcode.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcc;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import java.util.List;
@KeepForSdk
/* loaded from: classes2.dex */
public class BarcodeRegistrar implements ComponentRegistrar {
    public static final /* synthetic */ int zza = 0;

    @Override // com.google.firebase.components.ComponentRegistrar
    @NonNull
    public final List getComponents() {
        return zzcc.zzi(Component.builder(zzf.class).add(Dependency.required(MlKitContext.class)).factory(zzc.zza).build(), Component.builder(zze.class).add(Dependency.required(zzf.class)).add(Dependency.required(ExecutorSelector.class)).factory(zzd.zza).build());
    }
}
