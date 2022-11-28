package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
/* loaded from: classes2.dex */
final class zzer {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("VerifySliceTaskHandler");
    private final zzbh zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzer(zzbh zzbhVar) {
        this.zzb = zzbhVar;
    }

    private final void zzb(zzeq zzeqVar, File file) {
        try {
            File v = this.zzb.v(zzeqVar.f7833b, zzeqVar.f7851c, zzeqVar.f7852d, zzeqVar.f7853e);
            if (!v.exists()) {
                throw new zzck(String.format("Cannot find metadata files for slice %s.", zzeqVar.f7853e), zzeqVar.f7832a);
            }
            try {
                if (!zzdq.a(zzep.a(file, v)).equals(zzeqVar.f7854f)) {
                    throw new zzck(String.format("Verification failed for slice %s.", zzeqVar.f7853e), zzeqVar.f7832a);
                }
                zza.zzd("Verification of slice %s of pack %s successful.", zzeqVar.f7853e, zzeqVar.f7833b);
            } catch (IOException e2) {
                throw new zzck(String.format("Could not digest file during verification for slice %s.", zzeqVar.f7853e), e2, zzeqVar.f7832a);
            } catch (NoSuchAlgorithmException e3) {
                throw new zzck("SHA256 algorithm not supported.", e3, zzeqVar.f7832a);
            }
        } catch (IOException e4) {
            throw new zzck(String.format("Could not reconstruct slice archive during verification for slice %s.", zzeqVar.f7853e), e4, zzeqVar.f7832a);
        }
    }

    public final void zza(zzeq zzeqVar) {
        File w = this.zzb.w(zzeqVar.f7833b, zzeqVar.f7851c, zzeqVar.f7852d, zzeqVar.f7853e);
        if (!w.exists()) {
            throw new zzck(String.format("Cannot find unverified files for slice %s.", zzeqVar.f7853e), zzeqVar.f7832a);
        }
        zzb(zzeqVar, w);
        File x = this.zzb.x(zzeqVar.f7833b, zzeqVar.f7851c, zzeqVar.f7852d, zzeqVar.f7853e);
        if (!x.exists()) {
            x.mkdirs();
        }
        if (!w.renameTo(x)) {
            throw new zzck(String.format("Failed to move slice %s after verification.", zzeqVar.f7853e), zzeqVar.f7832a);
        }
    }
}
