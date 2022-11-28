package com.google.mlkit.common.internal;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.mlkit.common.sdkinternal.Cleaner;
/* loaded from: classes2.dex */
public final /* synthetic */ class zze implements ComponentFactory {
    public static final /* synthetic */ zze zza = new zze();

    private /* synthetic */ zze() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        return Cleaner.create();
    }
}
