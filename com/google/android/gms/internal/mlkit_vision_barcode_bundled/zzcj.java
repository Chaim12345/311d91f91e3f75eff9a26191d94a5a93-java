package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcj;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzck;
/* loaded from: classes2.dex */
public abstract class zzcj<MessageType extends zzck<MessageType, BuilderType>, BuilderType extends zzcj<MessageType, BuilderType>> implements zzfk {
    protected abstract zzcj a(zzck zzckVar);

    @Override // 
    /* renamed from: zze */
    public abstract zzcj clone();

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfk
    public final /* bridge */ /* synthetic */ zzfk zzg(zzfl zzflVar) {
        if (zzX().getClass().isInstance(zzflVar)) {
            return a((zzck) zzflVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
