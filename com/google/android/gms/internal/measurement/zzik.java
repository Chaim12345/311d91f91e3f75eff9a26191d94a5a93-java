package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzik;
import com.google.android.gms.internal.measurement.zzil;
/* loaded from: classes.dex */
public abstract class zzik<MessageType extends zzil<MessageType, BuilderType>, BuilderType extends zzik<MessageType, BuilderType>> implements zzli {
    protected abstract zzik a(zzil zzilVar);

    @Override // com.google.android.gms.internal.measurement.zzli
    public final /* synthetic */ zzli zzaA(byte[] bArr, zzjo zzjoVar) {
        return zzax(bArr, 0, bArr.length, zzjoVar);
    }

    @Override // 
    /* renamed from: zzau */
    public abstract zzik clone();

    public zzik zzaw(byte[] bArr, int i2, int i3) {
        throw null;
    }

    public zzik zzax(byte[] bArr, int i2, int i3, zzjo zzjoVar) {
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzli
    public final /* bridge */ /* synthetic */ zzli zzay(zzlj zzljVar) {
        if (zzbR().getClass().isInstance(zzljVar)) {
            return a((zzil) zzljVar);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }

    @Override // com.google.android.gms.internal.measurement.zzli
    public final /* synthetic */ zzli zzaz(byte[] bArr) {
        return zzaw(bArr, 0, bArr.length);
    }
}
