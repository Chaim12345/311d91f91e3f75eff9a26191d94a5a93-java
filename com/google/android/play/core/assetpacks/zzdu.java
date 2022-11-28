package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.IOException;
/* loaded from: classes2.dex */
final class zzdu {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("MergeSliceTaskHandler");
    private final zzbh zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdu(zzbh zzbhVar) {
        this.zzb = zzbhVar;
    }

    private static void zzb(File file, File file2) {
        File[] listFiles;
        if (!file.isDirectory()) {
            if (file2.exists()) {
                throw new zzck("File clashing with existing file from other slice: ".concat(file2.toString()));
            }
            if (!file.renameTo(file2)) {
                throw new zzck("Unable to move file: ".concat(String.valueOf(file)));
            }
            return;
        }
        file2.mkdirs();
        for (File file3 : file.listFiles()) {
            zzb(file3, new File(file2, file3.getName()));
        }
        if (!file.delete()) {
            throw new zzck("Unable to delete directory: ".concat(String.valueOf(file)));
        }
    }

    public final void zza(zzdt zzdtVar) {
        File x = this.zzb.x(zzdtVar.f7833b, zzdtVar.f7834c, zzdtVar.f7835d, zzdtVar.f7836e);
        if (!x.exists()) {
            throw new zzck(String.format("Cannot find verified files for slice %s.", zzdtVar.f7836e), zzdtVar.f7832a);
        }
        File q2 = this.zzb.q(zzdtVar.f7833b, zzdtVar.f7834c, zzdtVar.f7835d);
        if (!q2.exists()) {
            q2.mkdirs();
        }
        zzb(x, q2);
        try {
            this.zzb.a(zzdtVar.f7833b, zzdtVar.f7834c, zzdtVar.f7835d, this.zzb.i(zzdtVar.f7833b, zzdtVar.f7834c, zzdtVar.f7835d) + 1);
        } catch (IOException e2) {
            zza.zzb("Writing merge checkpoint failed with %s.", e2.getMessage());
            throw new zzck("Writing merge checkpoint failed.", e2, zzdtVar.f7832a);
        }
    }
}
