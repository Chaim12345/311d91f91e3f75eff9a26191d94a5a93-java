package com.google.android.play.core.splitcompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzi implements zzk {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Set f7886a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ zzs f7887b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ ZipFile f7888c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzi(zzm zzmVar, Set set, zzs zzsVar, ZipFile zipFile) {
        this.f7886a = set;
        this.f7887b = zzsVar;
        this.f7888c = zipFile;
    }

    @Override // com.google.android.play.core.splitcompat.zzk
    public final void zza(zzl zzlVar, File file, boolean z) {
        this.f7886a.add(file);
        if (z) {
            return;
        }
        String.format("NativeLibraryExtractor: split '%s' has native library '%s' that does not exist; extracting from '%s!%s' to '%s'", this.f7887b.b(), zzlVar.zza, this.f7887b.a().getAbsolutePath(), zzlVar.zzb.getName(), file.getAbsolutePath());
        ZipFile zipFile = this.f7888c;
        ZipEntry zipEntry = zzlVar.zzb;
        byte[] bArr = new byte[4096];
        if (file.exists()) {
            file.delete();
        }
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            zze.zzm(file);
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    fileOutputStream.close();
                    inputStream.close();
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }
}
