package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjy;
import com.google.android.gms.internal.measurement.zzkc;
import java.io.IOException;
/* loaded from: classes.dex */
public class zzjy<MessageType extends zzkc<MessageType, BuilderType>, BuilderType extends zzjy<MessageType, BuilderType>> extends zzik<MessageType, BuilderType> {

    /* renamed from: a  reason: collision with root package name */
    protected zzkc f6098a;

    /* renamed from: b  reason: collision with root package name */
    protected boolean f6099b = false;
    private final zzkc zzc;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzjy(zzkc zzkcVar) {
        this.zzc = zzkcVar;
        this.f6098a = (zzkc) zzkcVar.n(4, null, null);
    }

    private static final void zza(zzkc zzkcVar, zzkc zzkcVar2) {
        zzlr.zza().zzb(zzkcVar.getClass()).zzg(zzkcVar, zzkcVar2);
    }

    @Override // com.google.android.gms.internal.measurement.zzik
    protected final /* synthetic */ zzik a(zzil zzilVar) {
        zzaC((zzkc) zzilVar);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() {
        zzkc zzkcVar = (zzkc) this.f6098a.n(4, null, null);
        zza(zzkcVar, this.f6098a);
        this.f6098a = zzkcVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzik
    /* renamed from: zzaB */
    public final zzjy zzau() {
        zzjy zzjyVar = (zzjy) this.zzc.n(5, null, null);
        zzjyVar.zzaC(zzaG());
        return zzjyVar;
    }

    public final zzjy zzaC(zzkc zzkcVar) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        zza(this.f6098a, zzkcVar);
        return this;
    }

    public final zzjy zzaD(byte[] bArr, int i2, int i3, zzjo zzjoVar) {
        if (this.f6099b) {
            b();
            this.f6099b = false;
        }
        try {
            zzlr.zza().zzb(this.f6098a.getClass()).zzh(this.f6098a, bArr, 0, i3, new zzio(zzjoVar));
            return this;
        } catch (zzkm e2) {
            throw e2;
        } catch (IOException e3) {
            throw new RuntimeException("Reading from byte array should not throw IOException.", e3);
        } catch (IndexOutOfBoundsException unused) {
            throw zzkm.f();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
        if (r3 != false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final MessageType zzaE() {
        MessageType zzaG = zzaG();
        byte byteValue = ((Byte) zzaG.n(1, null, null)).byteValue();
        if (byteValue != 1) {
            if (byteValue != 0) {
                boolean zzk = zzlr.zza().zzb(zzaG.getClass()).zzk(zzaG);
                zzaG.n(2, true != zzk ? null : zzaG, null);
            }
            throw new zzmk(zzaG);
        }
        return zzaG;
    }

    @Override // com.google.android.gms.internal.measurement.zzli
    /* renamed from: zzaF */
    public MessageType zzaG() {
        if (this.f6099b) {
            return (MessageType) this.f6098a;
        }
        zzkc zzkcVar = this.f6098a;
        zzlr.zza().zzb(zzkcVar.getClass()).zzf(zzkcVar);
        this.f6099b = true;
        return (MessageType) this.f6098a;
    }

    @Override // com.google.android.gms.internal.measurement.zzik
    public final /* bridge */ /* synthetic */ zzik zzaw(byte[] bArr, int i2, int i3) {
        zzaD(bArr, 0, i3, zzjo.zza());
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzik
    public final /* bridge */ /* synthetic */ zzik zzax(byte[] bArr, int i2, int i3, zzjo zzjoVar) {
        zzaD(bArr, 0, i3, zzjoVar);
        return this;
    }

    @Override // com.google.android.gms.internal.measurement.zzlk
    public final /* synthetic */ zzlj zzbR() {
        return this.zzc;
    }
}
