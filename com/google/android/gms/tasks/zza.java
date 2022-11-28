package com.google.android.gms.tasks;
/* loaded from: classes2.dex */
final class zza implements OnSuccessListener<Void> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ OnTokenCanceledListener f7077a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zza(zzb zzbVar, OnTokenCanceledListener onTokenCanceledListener) {
        this.f7077a = onTokenCanceledListener;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final /* bridge */ /* synthetic */ void onSuccess(Void r1) {
        this.f7077a.onCanceled();
    }
}
