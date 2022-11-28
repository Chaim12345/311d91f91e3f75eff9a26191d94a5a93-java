package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdw;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzec;
/* loaded from: classes2.dex */
public class zzdw<MessageType extends zzec<MessageType, BuilderType>, BuilderType extends zzdw<MessageType, BuilderType>> extends zzcj<MessageType, BuilderType> {

    /* renamed from: a  reason: collision with root package name */
    protected zzec f6416a;

    /* renamed from: b  reason: collision with root package name */
    protected boolean f6417b = false;
    private final zzec zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzdw(zzec zzecVar) {
        this.zzc = zzecVar;
        this.f6416a = (zzec) zzecVar.p(4, null, null);
    }

    private static final void zza(zzec zzecVar, zzec zzecVar2) {
        zzfu.zza().zzb(zzecVar.getClass()).zzg(zzecVar, zzecVar2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcj
    protected final /* synthetic */ zzcj a(zzck zzckVar) {
        zzi((zzec) zzckVar);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        zzec zzecVar = (zzec) this.f6416a.p(4, null, null);
        zza(zzecVar, this.f6416a);
        this.f6416a = zzecVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm
    public final /* synthetic */ zzfl zzX() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfm
    public final boolean zzY() {
        return zzec.o(this.f6416a, false);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzcj
    /* renamed from: zzh */
    public final zzdw zze() {
        zzdw zzdwVar = (zzdw) this.zzc.p(5, null, null);
        zzdwVar.zzi(zzm());
        return zzdwVar;
    }

    public final zzdw zzi(zzec zzecVar) {
        if (this.f6417b) {
            b();
            this.f6417b = false;
        }
        zza(this.f6416a, zzecVar);
        return this;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfk
    /* renamed from: zzj */
    public final MessageType zzl() {
        MessageType zzm = zzm();
        if (zzm.zzY()) {
            return zzm;
        }
        throw new zzgo(zzm);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfk
    /* renamed from: zzk */
    public MessageType zzm() {
        if (this.f6417b) {
            return (MessageType) this.f6416a;
        }
        zzec zzecVar = this.f6416a;
        zzfu.zza().zzb(zzecVar.getClass()).zzf(zzecVar);
        this.f6417b = true;
        return (MessageType) this.f6416a;
    }
}
