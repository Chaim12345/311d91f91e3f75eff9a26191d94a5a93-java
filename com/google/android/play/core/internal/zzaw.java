package com.google.android.play.core.internal;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.play.core.splitcompat.SplitCompat;
import com.google.android.play.core.splitinstall.model.SplitInstallErrorCode;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes2.dex */
public final class zzaw implements com.google.android.play.core.splitinstall.zzh {
    private final Context zza;
    private final com.google.android.play.core.splitcompat.zze zzb;
    private final zzay zzc;
    private final Executor zzd;
    private final com.google.android.play.core.splitcompat.zzr zze;

    public zzaw(Context context, Executor executor, zzay zzayVar, com.google.android.play.core.splitcompat.zze zzeVar, com.google.android.play.core.splitcompat.zzr zzrVar, byte[] bArr) {
        this.zza = context;
        this.zzb = zzeVar;
        this.zzc = zzayVar;
        this.zzd = executor;
        this.zze = zzrVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void b(zzaw zzawVar, List list, com.google.android.play.core.splitinstall.zzf zzfVar) {
        Integer zze = zzawVar.zze(list);
        if (zze == null) {
            return;
        }
        if (zze.intValue() == 0) {
            zzfVar.zzc();
        } else {
            zzfVar.zzb(zze.intValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* bridge */ /* synthetic */ void c(zzaw zzawVar, com.google.android.play.core.splitinstall.zzf zzfVar) {
        try {
        } catch (Exception e2) {
            Log.e("SplitCompat", "Error emulating splits.", e2);
        }
        if (SplitCompat.zzd(zzce.zza(zzawVar.zza))) {
            zzfVar.zza();
            return;
        }
        Log.e("SplitCompat", "Emulating splits failed.");
        zzfVar.zzb(-12);
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x010d A[Catch: Exception -> 0x0111, TRY_LEAVE, TryCatch #11 {Exception -> 0x0111, blocks: (B:3:0x0004, B:69:0x010d, B:5:0x0016, B:12:0x0024, B:13:0x0028, B:15:0x002e, B:17:0x0056, B:22:0x0069, B:24:0x0075, B:33:0x0094, B:40:0x00a1, B:20:0x0063, B:41:0x00a2, B:42:0x00ac, B:44:0x00b4, B:46:0x00bc, B:47:0x00ca, B:49:0x00ce, B:63:0x00fd, B:53:0x00e2, B:54:0x00e6, B:56:0x00ed), top: B:91:0x0004 }] */
    @Nullable
    @SplitInstallErrorCode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Integer zze(List list) {
        FileLock fileLock;
        String str;
        File[] listFiles;
        try {
            FileChannel channel = new RandomAccessFile(this.zzb.zzd(), "rw").getChannel();
            Integer num = null;
            try {
                fileLock = channel.tryLock();
            } catch (OverlappingFileLockException unused) {
                fileLock = null;
            }
            if (fileLock != null) {
                int i2 = 0;
                try {
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        Intent intent = (Intent) it.next();
                        String stringExtra = intent.getStringExtra("split_id");
                        AssetFileDescriptor openAssetFileDescriptor = this.zza.getContentResolver().openAssetFileDescriptor(intent.getData(), "r");
                        File zze = this.zzb.zze(stringExtra);
                        if ((!zze.exists() || zze.length() == openAssetFileDescriptor.getLength()) && zze.exists()) {
                        }
                        if (this.zzb.zzg(stringExtra).exists()) {
                            continue;
                        } else {
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(openAssetFileDescriptor.createInputStream());
                            try {
                                FileOutputStream fileOutputStream = new FileOutputStream(zze);
                                byte[] bArr = new byte[4096];
                                while (true) {
                                    int read = bufferedInputStream.read(bArr);
                                    if (read <= 0) {
                                        break;
                                    }
                                    fileOutputStream.write(bArr, 0, read);
                                }
                                fileOutputStream.close();
                                bufferedInputStream.close();
                            } finally {
                            }
                        }
                    }
                    try {
                        listFiles = this.zzb.zzb().listFiles();
                        try {
                        } catch (Exception e2) {
                            Log.e("SplitCompat", "Error verifying splits.", e2);
                        }
                    } catch (IOException e3) {
                        e = e3;
                        str = "Cannot access directory for unverified splits.";
                    }
                } catch (Exception e4) {
                    e = e4;
                    str = "Error copying splits.";
                }
                if (this.zzc.zzc(listFiles)) {
                    if (this.zzc.zza(listFiles)) {
                        try {
                            File[] listFiles2 = this.zzb.zzb().listFiles();
                            Arrays.sort(listFiles2);
                            int length = listFiles2.length;
                            while (true) {
                                length--;
                                if (length < 0) {
                                    break;
                                }
                                com.google.android.play.core.splitcompat.zze.zzm(listFiles2[length]);
                                File file = listFiles2[length];
                                file.renameTo(this.zzb.zzf(file));
                            }
                        } catch (IOException e5) {
                            e = e5;
                            str = "Cannot write verified split.";
                            Log.e("SplitCompat", str, e);
                            i2 = -13;
                            num = Integer.valueOf(i2);
                            fileLock.release();
                            if (channel != null) {
                            }
                            return num;
                        }
                        num = Integer.valueOf(i2);
                        fileLock.release();
                    }
                }
                Log.e("SplitCompat", "Split verification failed.");
                i2 = -11;
                num = Integer.valueOf(i2);
                fileLock.release();
            }
            if (channel != null) {
                channel.close();
            }
            return num;
        } catch (Exception e6) {
            Log.e("SplitCompat", "Error locking files.", e6);
            return -13;
        }
    }

    @Override // com.google.android.play.core.splitinstall.zzh
    public final void zzd(List list, com.google.android.play.core.splitinstall.zzf zzfVar) {
        if (!SplitCompat.zze()) {
            throw new IllegalStateException("Ingestion should only be called in SplitCompat mode.");
        }
        this.zzd.execute(new zzav(this, list, zzfVar));
    }
}
