package com.google.android.gms.internal.common;
/* loaded from: classes.dex */
final class zzae<E> extends zzz<E> {
    private final zzag<E> zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzae(zzag zzagVar, int i2) {
        super(zzagVar.size(), i2);
        this.zza = zzagVar;
    }

    @Override // com.google.android.gms.internal.common.zzz
    protected final Object a(int i2) {
        return this.zza.get(i2);
    }
}
