package com.google.android.play.core.assetpacks;

import androidx.annotation.Nullable;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.zip.ZipException;
/* loaded from: classes2.dex */
final class zzbt {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static AssetLocation a(String str, String str2) {
        Long l2;
        int a2;
        com.google.android.play.core.internal.zzci.zzb(str != null, "Attempted to get file location from a null apk path.");
        com.google.android.play.core.internal.zzci.zzb(str2 != null, String.format("Attempted to get file location in apk %s with a null file path.", str));
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
        byte[] bArr = new byte[22];
        randomAccessFile.seek(randomAccessFile.length() - 22);
        randomAccessFile.readFully(bArr);
        zzbs zzb = zzbr.b(bArr, 0) == 1347093766 ? zzb(bArr) : null;
        byte b2 = 5;
        if (zzb == null) {
            long length = randomAccessFile.length() - 22;
            long j2 = (-65536) + length;
            if (j2 < 0) {
                j2 = 0;
            }
            int min = (int) Math.min(1024L, randomAccessFile.length());
            byte[] bArr2 = new byte[min];
            byte[] bArr3 = new byte[22];
            loop0: while (true) {
                long max = Math.max(3 + (length - min), j2);
                randomAccessFile.seek(max);
                randomAccessFile.readFully(bArr2);
                for (int i2 = min - 4; i2 >= 0; i2 -= 4) {
                    byte b3 = bArr2[i2];
                    int i3 = b3 != b2 ? b3 != 6 ? b3 != 75 ? b3 != 80 ? -1 : 0 : 1 : 3 : 2;
                    if (i3 >= 0 && i2 >= i3 && zzbr.b(bArr2, i2 - i3) == 1347093766) {
                        randomAccessFile.seek((max + i2) - i3);
                        randomAccessFile.readFully(bArr3);
                        zzb = zzb(bArr3);
                        break loop0;
                    }
                    b2 = 5;
                }
                if (max == j2) {
                    throw new ZipException(String.format("End Of Central Directory signature not found in APK %s", str));
                }
                length = max;
            }
        }
        long j3 = zzb.f7802a;
        byte[] bytes = str2.getBytes("UTF-8");
        byte[] bArr4 = new byte[46];
        byte[] bArr5 = new byte[str2.length()];
        int i4 = 0;
        while (true) {
            if (i4 >= zzb.f7803b) {
                l2 = null;
                break;
            }
            randomAccessFile.seek(j3);
            randomAccessFile.readFully(bArr4);
            int b4 = zzbr.b(bArr4, 0);
            if (b4 != 1347092738) {
                throw new ZipException(String.format("Missing central directory file header signature when looking for file %s in APK %s. Read %d entries out of %d. Found %d instead of the header signature %d.", str2, str, Integer.valueOf(i4), Integer.valueOf(zzb.f7803b), Integer.valueOf(b4), 1347092738));
            }
            randomAccessFile.seek(j3 + 28);
            if (zzbr.a(bArr4, 28) == str2.length()) {
                randomAccessFile.seek(46 + j3);
                randomAccessFile.read(bArr5);
                if (Arrays.equals(bArr5, bytes)) {
                    l2 = Long.valueOf(zzbr.c(bArr4, 42));
                    break;
                }
            }
            j3 += a2 + 46 + zzbr.a(bArr4, 30) + zzbr.a(bArr4, 32);
            i4++;
        }
        if (l2 == null) {
            return null;
        }
        long longValue = l2.longValue();
        byte[] bArr6 = new byte[8];
        randomAccessFile.seek(22 + longValue);
        randomAccessFile.readFully(bArr6);
        return new zzbl(str, longValue + 30 + zzbr.a(bArr6, 4) + zzbr.a(bArr6, 6), zzbr.c(bArr6, 0));
    }

    private static zzbs zzb(byte[] bArr) {
        int a2 = zzbr.a(bArr, 10);
        return new zzbs(zzbr.c(bArr, 16), zzbr.c(bArr, 12), a2);
    }
}
