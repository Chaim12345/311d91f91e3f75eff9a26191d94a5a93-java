package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
/* loaded from: classes2.dex */
final class zzeg {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("PatchSliceTaskHandler");
    private final zzbh zzb;
    private final com.google.android.play.core.internal.zzco zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeg(zzbh zzbhVar, com.google.android.play.core.internal.zzco zzcoVar) {
        this.zzb = zzbhVar;
        this.zzc = zzcoVar;
    }

    public final void zza(zzef zzefVar) {
        File o2 = this.zzb.o(zzefVar.f7833b, zzefVar.f7840c, zzefVar.f7841d);
        File file = new File(this.zzb.p(zzefVar.f7833b, zzefVar.f7840c, zzefVar.f7841d), zzefVar.f7845h);
        try {
            InputStream inputStream = zzefVar.f7847j;
            if (zzefVar.f7844g == 2) {
                inputStream = new GZIPInputStream(inputStream, 8192);
            }
            zzbk zzbkVar = new zzbk(o2, file);
            File w = this.zzb.w(zzefVar.f7833b, zzefVar.f7842e, zzefVar.f7843f, zzefVar.f7845h);
            if (!w.exists()) {
                w.mkdirs();
            }
            zzen zzenVar = new zzen(this.zzb, zzefVar.f7833b, zzefVar.f7842e, zzefVar.f7843f, zzefVar.f7845h);
            com.google.android.play.core.internal.zzcl.zza(zzbkVar, inputStream, new zzcn(w, zzenVar), zzefVar.f7846i);
            zzenVar.i(0);
            inputStream.close();
            zza.zzd("Patching and extraction finished for slice %s of pack %s.", zzefVar.f7845h, zzefVar.f7833b);
            ((zzy) this.zzc.zza()).zzg(zzefVar.f7832a, zzefVar.f7833b, zzefVar.f7845h, 0);
            try {
                zzefVar.f7847j.close();
            } catch (IOException unused) {
                zza.zze("Could not close file for slice %s of pack %s.", zzefVar.f7845h, zzefVar.f7833b);
            }
        } catch (IOException e2) {
            zza.zzb("IOException during patching %s.", e2.getMessage());
            throw new zzck(String.format("Error patching slice %s of pack %s.", zzefVar.f7845h, zzefVar.f7833b), e2, zzefVar.f7832a);
        }
    }
}
