package com.google.android.play.core.assetpacks;

import androidx.exifinterface.media.ExifInterface;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Properties;
/* loaded from: classes2.dex */
final class zzen {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("SliceMetadataManager");
    private final zzbh zzc;
    private final String zzd;
    private final int zze;
    private final long zzf;
    private final String zzg;
    private final byte[] zzb = new byte[8192];
    private int zzh = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzen(zzbh zzbhVar, String str, int i2, long j2, String str2) {
        this.zzc = zzbhVar;
        this.zzd = str;
        this.zze = i2;
        this.zzf = j2;
        this.zzg = str2;
    }

    private final File zzn() {
        File v = this.zzc.v(this.zzd, this.zze, this.zzf, this.zzg);
        if (!v.exists()) {
            v.mkdirs();
        }
        return v;
    }

    private final File zzo() {
        File u = this.zzc.u(this.zzd, this.zze, this.zzf, this.zzg);
        u.getParentFile().mkdirs();
        u.createNewFile();
        return u;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a() {
        File u = this.zzc.u(this.zzd, this.zze, this.zzf, this.zzg);
        if (u.exists()) {
            FileInputStream fileInputStream = new FileInputStream(u);
            try {
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                if (Integer.parseInt(properties.getProperty("fileStatus", AppConstants.DEFAULT_VAL_SPEED_DLG)) == 4) {
                    return -1;
                }
                if (properties.getProperty("previousChunk") != null) {
                    return Integer.parseInt(properties.getProperty("previousChunk")) + 1;
                }
                throw new zzck("Slice checkpoint file corrupt.");
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzem b() {
        File u = this.zzc.u(this.zzd, this.zze, this.zzf, this.zzg);
        if (u.exists()) {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(u);
            try {
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("fileStatus") == null || properties.getProperty("previousChunk") == null) {
                    throw new zzck("Slice checkpoint file corrupt.");
                }
                try {
                    int parseInt = Integer.parseInt(properties.getProperty("fileStatus"));
                    String property = properties.getProperty("fileName");
                    long parseLong = Long.parseLong(properties.getProperty("fileOffset", AppConstants.DEFAULT_VAL_SPEED_DLG));
                    long parseLong2 = Long.parseLong(properties.getProperty("remainingBytes", AppConstants.DEFAULT_VAL_SPEED_DLG));
                    int parseInt2 = Integer.parseInt(properties.getProperty("previousChunk"));
                    this.zzh = Integer.parseInt(properties.getProperty("metadataFileCounter", "0"));
                    return new zzbp(parseInt, property, parseLong, parseLong2, parseInt2);
                } catch (NumberFormatException e2) {
                    throw new zzck("Slice checkpoint file corrupt.", e2);
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        throw new zzck("Slice checkpoint file does not exist.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File c() {
        return new File(zzn(), String.format("%s-NAM.dat", Integer.valueOf(this.zzh)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(InputStream inputStream, long j2) {
        int read;
        RandomAccessFile randomAccessFile = new RandomAccessFile(c(), "rw");
        try {
            randomAccessFile.seek(j2);
            do {
                read = inputStream.read(this.zzb);
                if (read > 0) {
                    randomAccessFile.write(this.zzb, 0, read);
                }
            } while (read == 8192);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(long j2, byte[] bArr, int i2, int i3) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(c(), "rw");
        try {
            randomAccessFile.seek(j2);
            randomAccessFile.write(bArr, i2, i3);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void f(int i2) {
        Properties properties = new Properties();
        properties.put("fileStatus", ExifInterface.GPS_MEASUREMENT_3D);
        properties.put("fileOffset", String.valueOf(c().length()));
        properties.put("previousChunk", String.valueOf(i2));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void g(String str, long j2, long j3, int i2) {
        Properties properties = new Properties();
        properties.put("fileStatus", "1");
        properties.put("fileName", str);
        properties.put("fileOffset", String.valueOf(j2));
        properties.put("remainingBytes", String.valueOf(j3));
        properties.put("previousChunk", String.valueOf(i2));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void h(byte[] bArr, int i2) {
        Properties properties = new Properties();
        properties.put("fileStatus", ExifInterface.GPS_MEASUREMENT_2D);
        properties.put("previousChunk", String.valueOf(i2));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
            File t2 = this.zzc.t(this.zzd, this.zze, this.zzf, this.zzg);
            if (t2.exists()) {
                t2.delete();
            }
            fileOutputStream = new FileOutputStream(t2);
            try {
                fileOutputStream.write(bArr);
            } finally {
                try {
                    fileOutputStream.close();
                } catch (Throwable unused) {
                }
            }
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void i(int i2) {
        Properties properties = new Properties();
        properties.put("fileStatus", "4");
        properties.put("previousChunk", String.valueOf(i2));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void j(byte[] bArr) {
        this.zzh++;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(zzn(), String.format("%s-LFH.dat", Integer.valueOf(this.zzh))));
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (IOException e2) {
            throw new zzck("Could not write metadata file.", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void k(byte[] bArr, InputStream inputStream) {
        this.zzh++;
        FileOutputStream fileOutputStream = new FileOutputStream(c());
        try {
            fileOutputStream.write(bArr);
            int read = inputStream.read(this.zzb);
            while (read > 0) {
                fileOutputStream.write(this.zzb, 0, read);
                read = inputStream.read(this.zzb);
            }
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void l(byte[] bArr, int i2, int i3) {
        this.zzh++;
        FileOutputStream fileOutputStream = new FileOutputStream(c());
        try {
            fileOutputStream.write(bArr, 0, i3);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean m() {
        File u = this.zzc.u(this.zzd, this.zze, this.zzf, this.zzg);
        if (u.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(u);
                Properties properties = new Properties();
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("fileStatus") != null) {
                    return Integer.parseInt(properties.getProperty("fileStatus")) == 4;
                }
                zza.zzb("Slice checkpoint file corrupt while checking if extraction finished.", new Object[0]);
                return false;
            } catch (IOException e2) {
                zza.zzb("Could not read checkpoint while checking if extraction finished. %s", e2);
                return false;
            }
        }
        return false;
    }
}
