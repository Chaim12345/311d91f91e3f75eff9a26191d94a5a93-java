package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzadh;
import com.google.android.libraries.places.internal.zzadk;
/* loaded from: classes2.dex */
public class zzadh<MessageType extends zzadk<MessageType, BuilderType>, BuilderType extends zzadh<MessageType, BuilderType>> extends zzacb<MessageType, BuilderType> {
    protected zzadk zza;
    protected boolean zzb = false;
    private final zzadk zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzadh(MessageType messagetype) {
        this.zzc = messagetype;
        this.zza = (zzadk) messagetype.zzb(4, null, null);
    }

    private static final void zza(zzadk zzadkVar, zzadk zzadkVar2) {
        zzaez.zza().zzb(zzadkVar.getClass()).zzd(zzadkVar, zzadkVar2);
    }

    @Override // com.google.android.libraries.places.internal.zzacb
    protected final /* synthetic */ zzacb zzp(zzacc zzaccVar) {
        zzs((zzadk) zzaccVar);
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzacb
    /* renamed from: zzr */
    public final zzadh zzo() {
        zzadh zzadhVar = (zzadh) this.zzc.zzb(5, null, null);
        zzadhVar.zzs(zzv());
        return zzadhVar;
    }

    public final zzadh zzs(zzadk zzadkVar) {
        if (this.zzb) {
            zzx();
            this.zzb = false;
        }
        zza(this.zza, zzadkVar);
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
        if (r3 != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final MessageType zzt() {
        MessageType zzv = zzv();
        byte byteValue = ((Byte) zzv.zzb(1, null, null)).byteValue();
        if (byteValue != 1) {
            if (byteValue != 0) {
                boolean zzf = zzaez.zza().zzb(zzv.getClass()).zzf(zzv);
                zzv.zzb(2, true != zzf ? null : zzv, null);
            }
            throw new zzafs(zzv);
        }
        return zzv;
    }

    @Override // com.google.android.libraries.places.internal.zzaeq
    /* renamed from: zzu */
    public MessageType zzv() {
        if (this.zzb) {
            return (MessageType) this.zza;
        }
        zzadk zzadkVar = this.zza;
        zzaez.zza().zzb(zzadkVar.getClass()).zzc(zzadkVar);
        this.zzb = true;
        return (MessageType) this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzaes
    public final /* synthetic */ zzaer zzw() {
        return this.zzc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void zzx() {
        zzadk zzadkVar = (zzadk) this.zza.zzb(4, null, null);
        zza(zzadkVar, this.zza);
        this.zza = zzadkVar;
    }
}
