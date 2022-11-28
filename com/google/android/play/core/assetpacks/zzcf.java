package com.google.android.play.core.assetpacks;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.SequenceInputStream;
import java.util.zip.GZIPInputStream;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzcf {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("ExtractChunkTaskHandler");
    private final byte[] zzb = new byte[8192];
    private final zzbh zzc;
    private final com.google.android.play.core.internal.zzco zzd;
    private final com.google.android.play.core.internal.zzco zze;
    private final zzco zzf;
    private final zzeb zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcf(zzbh zzbhVar, com.google.android.play.core.internal.zzco zzcoVar, com.google.android.play.core.internal.zzco zzcoVar2, zzco zzcoVar3, zzeb zzebVar) {
        this.zzc = zzbhVar;
        this.zzd = zzcoVar;
        this.zze = zzcoVar2;
        this.zzf = zzcoVar3;
        this.zzg = zzebVar;
    }

    private final File zzb(zzce zzceVar) {
        File w = this.zzc.w(zzceVar.f7833b, zzceVar.f7804c, zzceVar.f7805d, zzceVar.f7807f);
        if (!w.exists()) {
            w.mkdirs();
        }
        return w;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:11|(2:13|(12:15|(1:(1:(2:19|(1:21)(2:85|86))(2:87|88))(2:89|(10:91|(7:24|(4:25|(2:29|(1:38)(4:33|(1:35)|36|37))|39|(1:41)(1:65))|43|44|(1:46)|47|(2:49|(1:51)(2:52|(1:54)(3:55|(2:57|(1:59)(2:61|62))(1:64)|60))))|66|67|(2:79|80)|69|70|71|72|(2:74|75)(1:76))(2:92|93)))(2:94|(4:96|(4:97|(1:99)|100|(1:103)(1:111))|106|(3:108|109|110))(2:112|113))|22|(0)|66|67|(0)|69|70|71|72|(0)(0))(2:114|115))|116|(0)|66|67|(0)|69|70|71|72|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x02fa, code lost:
        com.google.android.play.core.assetpacks.zzcf.zza.zze("Could not close file for chunk %s of slice %s of pack %s.", java.lang.Integer.valueOf(r23.f7809h), r23.f7807f, r23.f7833b);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0319  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x029e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0186 A[Catch: all -> 0x0292, TryCatch #2 {all -> 0x0292, blocks: (B:54:0x0186, B:55:0x018f, B:57:0x0199, B:59:0x019f, B:61:0x01a5, B:63:0x01ab, B:65:0x01cf, B:66:0x01db, B:67:0x01df, B:68:0x01e6, B:70:0x01ec, B:72:0x01f2, B:74:0x01f8, B:75:0x0208, B:77:0x020e, B:79:0x0214, B:80:0x0227, B:82:0x022d, B:83:0x023c, B:85:0x0242, B:91:0x0283, B:88:0x026a, B:89:0x0271, B:90:0x0272, B:47:0x0151, B:48:0x0156, B:49:0x0160, B:50:0x0161, B:51:0x0181), top: B:122:0x0043 }] */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.google.android.play.core.assetpacks.zzbh] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void zza(zzce zzceVar) {
        InputStream inputStream;
        SequenceInputStream sequenceInputStream;
        zzet b2;
        File c2;
        long length;
        int min;
        int max;
        long j2;
        ?? r10 = this.zzc;
        String str = zzceVar.f7833b;
        int i2 = zzceVar.f7804c;
        long j3 = zzceVar.f7805d;
        String str2 = zzceVar.f7807f;
        zzen zzenVar = new zzen(r10, str, i2, j3, str2);
        File v = r10.v(str, i2, j3, str2);
        if (!v.exists()) {
            v.mkdirs();
        }
        try {
            InputStream inputStream2 = zzceVar.f7813l;
            GZIPInputStream gZIPInputStream = zzceVar.f7808g != 1 ? inputStream2 : new GZIPInputStream(inputStream2, 8192);
            try {
                try {
                    if (zzceVar.f7809h > 0) {
                        zzem b3 = zzenVar.b();
                        int b4 = b3.b();
                        int i3 = zzceVar.f7809h;
                        if (b4 != i3 - 1) {
                            throw new zzck(String.format("Trying to resume with chunk number %s when previously processed chunk was number %s.", Integer.valueOf(i3), Integer.valueOf(b3.b())), zzceVar.f7832a);
                        }
                        int a2 = b3.a();
                        if (a2 == 1) {
                            zza.zza("Resuming zip entry from last chunk during file %s.", b3.e());
                            File file = new File(b3.e());
                            if (!file.exists()) {
                                throw new zzck("Partial file specified in checkpoint does not exist. Corrupt directory.", zzceVar.f7832a);
                            }
                            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                            randomAccessFile.seek(b3.c());
                            long d2 = b3.d();
                            while (true) {
                                min = (int) Math.min(d2, (long) PlaybackStateCompat.ACTION_PLAY_FROM_URI);
                                max = Math.max(gZIPInputStream.read(this.zzb, 0, min), 0);
                                if (max > 0) {
                                    randomAccessFile.write(this.zzb, 0, max);
                                }
                                j2 = d2 - max;
                                if (j2 <= 0 || max <= 0) {
                                    break;
                                }
                                d2 = j2;
                            }
                            long length2 = randomAccessFile.length();
                            randomAccessFile.close();
                            if (max != min) {
                                zza.zza("Chunk has ended while resuming the previous chunks file content.", new Object[0]);
                                inputStream = gZIPInputStream;
                                zzenVar.g(file.getCanonicalPath(), length2, j2, zzceVar.f7809h);
                            }
                        } else if (a2 == 2) {
                            zza.zza("Resuming zip entry from last chunk during local file header.", new Object[0]);
                            File t2 = this.zzc.t(zzceVar.f7833b, zzceVar.f7804c, zzceVar.f7805d, zzceVar.f7807f);
                            if (!t2.exists()) {
                                throw new zzck("Checkpoint extension file not found.", zzceVar.f7832a);
                            }
                            inputStream = gZIPInputStream;
                            sequenceInputStream = new SequenceInputStream(new FileInputStream(t2), gZIPInputStream);
                            if (sequenceInputStream != null) {
                                zzbw zzbwVar = new zzbw(sequenceInputStream);
                                File zzb = zzb(zzceVar);
                                do {
                                    b2 = zzbwVar.b();
                                    if (!b2.e() && !zzbwVar.c()) {
                                        if (!b2.h() || b2.g()) {
                                            zzenVar.k(b2.f(), zzbwVar);
                                        } else {
                                            zzenVar.j(b2.f());
                                            File file2 = new File(zzb, b2.c());
                                            file2.getParentFile().mkdirs();
                                            FileOutputStream fileOutputStream = new FileOutputStream(file2);
                                            int read = zzbwVar.read(this.zzb, 0, 8192);
                                            while (read > 0) {
                                                fileOutputStream.write(this.zzb, 0, read);
                                                read = zzbwVar.read(this.zzb, 0, 8192);
                                            }
                                            fileOutputStream.close();
                                        }
                                    }
                                    if (zzbwVar.d()) {
                                        break;
                                    }
                                } while (!zzbwVar.c());
                                if (zzbwVar.c()) {
                                    zza.zza("Writing central directory metadata.", new Object[0]);
                                    zzenVar.k(b2.f(), sequenceInputStream);
                                }
                                if (!zzceVar.a()) {
                                    if (b2.e()) {
                                        zza.zza("Writing slice checkpoint for partial local file header.", new Object[0]);
                                        zzenVar.h(b2.f(), zzceVar.f7809h);
                                    } else if (zzbwVar.c()) {
                                        zza.zza("Writing slice checkpoint for central directory.", new Object[0]);
                                        zzenVar.f(zzceVar.f7809h);
                                    } else {
                                        if (b2.a() == 0) {
                                            zza.zza("Writing slice checkpoint for partial file.", new Object[0]);
                                            c2 = new File(zzb(zzceVar), b2.c());
                                            length = b2.b() - zzbwVar.a();
                                            if (c2.length() != length) {
                                                throw new zzck("Partial file is of unexpected size.");
                                            }
                                        } else {
                                            zza.zza("Writing slice checkpoint for partial unextractable file.", new Object[0]);
                                            c2 = zzenVar.c();
                                            length = c2.length();
                                        }
                                        zzenVar.g(c2.getCanonicalPath(), length, zzbwVar.a(), zzceVar.f7809h);
                                    }
                                }
                            }
                            inputStream.close();
                            if (zzceVar.a()) {
                                try {
                                    zzenVar.i(zzceVar.f7809h);
                                } catch (IOException e2) {
                                    zza.zzb("Writing extraction finished checkpoint failed with %s.", e2.getMessage());
                                    throw new zzck("Writing extraction finished checkpoint failed.", e2, zzceVar.f7832a);
                                }
                            }
                            zza.zzd("Extraction finished for chunk %s of slice %s of pack %s of session %s.", Integer.valueOf(zzceVar.f7809h), zzceVar.f7807f, zzceVar.f7833b, Integer.valueOf(zzceVar.f7832a));
                            ((zzy) this.zzd.zza()).zzg(zzceVar.f7832a, zzceVar.f7833b, zzceVar.f7807f, zzceVar.f7809h);
                            zzceVar.f7813l.close();
                            if (zzceVar.f7812k == 3) {
                                String str3 = zzceVar.f7833b;
                                long j4 = zzceVar.f7811j;
                                ((zzbb) this.zze.zza()).d(AssetPackState.zzb(str3, 3, 0, j4, j4, this.zzf.b(str3, zzceVar), 1, zzceVar.f7806e, this.zzg.a(zzceVar.f7833b)));
                                return;
                            }
                            return;
                        } else if (a2 != 3) {
                            throw new zzck(String.format("Slice checkpoint file corrupt. Unexpected FileExtractionStatus %s.", Integer.valueOf(b3.a())), zzceVar.f7832a);
                        } else {
                            zza.zza("Resuming central directory from last chunk.", new Object[0]);
                            zzenVar.d(gZIPInputStream, b3.c());
                            if (!zzceVar.a()) {
                                throw new zzck("Chunk has ended twice during central directory. This should not be possible with chunk sizes of 50MB.", zzceVar.f7832a);
                            }
                            inputStream = gZIPInputStream;
                        }
                        sequenceInputStream = null;
                        if (sequenceInputStream != null) {
                        }
                        inputStream.close();
                        if (zzceVar.a()) {
                        }
                        zza.zzd("Extraction finished for chunk %s of slice %s of pack %s of session %s.", Integer.valueOf(zzceVar.f7809h), zzceVar.f7807f, zzceVar.f7833b, Integer.valueOf(zzceVar.f7832a));
                        ((zzy) this.zzd.zza()).zzg(zzceVar.f7832a, zzceVar.f7833b, zzceVar.f7807f, zzceVar.f7809h);
                        zzceVar.f7813l.close();
                        if (zzceVar.f7812k == 3) {
                        }
                    }
                    inputStream = gZIPInputStream;
                    sequenceInputStream = inputStream;
                    if (sequenceInputStream != null) {
                    }
                    inputStream.close();
                    if (zzceVar.a()) {
                    }
                    zza.zzd("Extraction finished for chunk %s of slice %s of pack %s of session %s.", Integer.valueOf(zzceVar.f7809h), zzceVar.f7807f, zzceVar.f7833b, Integer.valueOf(zzceVar.f7832a));
                    ((zzy) this.zzd.zza()).zzg(zzceVar.f7832a, zzceVar.f7833b, zzceVar.f7807f, zzceVar.f7809h);
                    zzceVar.f7813l.close();
                    if (zzceVar.f7812k == 3) {
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        r10.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                r10 = gZIPInputStream;
            }
        } catch (IOException e3) {
            zza.zzb("IOException during extraction %s.", e3.getMessage());
            throw new zzck(String.format("Error extracting chunk %s of slice %s of pack %s of session %s.", Integer.valueOf(zzceVar.f7809h), zzceVar.f7807f, zzceVar.f7833b, Integer.valueOf(zzceVar.f7832a)), e3, zzceVar.f7832a);
        }
    }
}
