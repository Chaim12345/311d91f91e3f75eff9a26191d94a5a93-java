package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
/* loaded from: classes2.dex */
final class zzcn extends OutputStream {
    private final zzds zza = new zzds();
    private final File zzb;
    private final zzen zzc;
    private long zzd;
    private long zze;
    private FileOutputStream zzf;
    private zzet zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcn(File file, zzen zzenVar) {
        this.zzb = file;
        this.zzc = zzenVar;
    }

    @Override // java.io.OutputStream
    public final void write(int i2) {
        write(new byte[]{(byte) i2}, 0, 1);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i2, int i3) {
        int min;
        while (i3 > 0) {
            if (this.zzd == 0 && this.zze == 0) {
                int zzb = this.zza.zzb(bArr, i2, i3);
                if (zzb == -1) {
                    return;
                }
                i2 += zzb;
                i3 -= zzb;
                zzet zzc = this.zza.zzc();
                this.zzg = zzc;
                if (zzc.d()) {
                    this.zzd = 0L;
                    this.zzc.l(this.zzg.f(), 0, this.zzg.f().length);
                    this.zze = this.zzg.f().length;
                } else if (!this.zzg.h() || this.zzg.g()) {
                    byte[] f2 = this.zzg.f();
                    this.zzc.l(f2, 0, f2.length);
                    this.zzd = this.zzg.b();
                } else {
                    this.zzc.j(this.zzg.f());
                    File file = new File(this.zzb, this.zzg.c());
                    file.getParentFile().mkdirs();
                    this.zzd = this.zzg.b();
                    this.zzf = new FileOutputStream(file);
                }
            }
            if (!this.zzg.g()) {
                if (this.zzg.d()) {
                    this.zzc.e(this.zze, bArr, i2, i3);
                    this.zze += i3;
                    min = i3;
                } else if (this.zzg.h()) {
                    min = (int) Math.min(i3, this.zzd);
                    this.zzf.write(bArr, i2, min);
                    long j2 = this.zzd - min;
                    this.zzd = j2;
                    if (j2 == 0) {
                        this.zzf.close();
                    }
                } else {
                    min = (int) Math.min(i3, this.zzd);
                    this.zzc.e((this.zzg.f().length + this.zzg.b()) - this.zzd, bArr, i2, min);
                    this.zzd -= min;
                }
                i2 += min;
                i3 -= min;
            }
        }
    }
}
