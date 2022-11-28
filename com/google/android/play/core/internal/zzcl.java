package com.google.android.play.core.internal;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes2.dex */
public final class zzcl {
    public static long zza(zzcm zzcmVar, InputStream inputStream, OutputStream outputStream, long j2) {
        byte[] bArr = new byte[16384];
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(inputStream, 4096));
        int readInt = dataInputStream.readInt();
        if (readInt != -771763713) {
            String valueOf = String.valueOf(String.format("%x", Integer.valueOf(readInt)));
            throw new zzck(valueOf.length() != 0 ? "Unexpected magic=".concat(valueOf) : new String("Unexpected magic="));
        }
        int read = dataInputStream.read();
        if (read != 4) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Unexpected version=");
            sb.append(read);
            throw new zzck(sb.toString());
        }
        long j3 = 0;
        while (true) {
            long j4 = j2 - j3;
            try {
                int read2 = dataInputStream.read();
                if (read2 == -1) {
                    throw new IOException("Patch file overrun");
                }
                if (read2 == 0) {
                    return j3;
                }
                switch (read2) {
                    case 247:
                        read2 = dataInputStream.readUnsignedShort();
                        zzc(bArr, dataInputStream, outputStream, read2, j4);
                        break;
                    case 248:
                        read2 = dataInputStream.readInt();
                        zzc(bArr, dataInputStream, outputStream, read2, j4);
                        break;
                    case 249:
                        long readUnsignedShort = dataInputStream.readUnsignedShort();
                        read2 = dataInputStream.read();
                        if (read2 != -1) {
                            zzb(bArr, zzcmVar, outputStream, readUnsignedShort, read2, j4);
                            break;
                        } else {
                            throw new IOException("Unexpected end of patch");
                        }
                    case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /* 250 */:
                        read2 = dataInputStream.readUnsignedShort();
                        zzb(bArr, zzcmVar, outputStream, dataInputStream.readUnsignedShort(), read2, j4);
                        break;
                    case 251:
                        read2 = dataInputStream.readInt();
                        zzb(bArr, zzcmVar, outputStream, dataInputStream.readUnsignedShort(), read2, j4);
                        break;
                    case 252:
                        long readInt2 = dataInputStream.readInt();
                        read2 = dataInputStream.read();
                        if (read2 != -1) {
                            zzb(bArr, zzcmVar, outputStream, readInt2, read2, j4);
                            break;
                        } else {
                            throw new IOException("Unexpected end of patch");
                        }
                    case 253:
                        read2 = dataInputStream.readUnsignedShort();
                        zzb(bArr, zzcmVar, outputStream, dataInputStream.readInt(), read2, j4);
                        break;
                    case 254:
                        read2 = dataInputStream.readInt();
                        zzb(bArr, zzcmVar, outputStream, dataInputStream.readInt(), read2, j4);
                        break;
                    case 255:
                        long readLong = dataInputStream.readLong();
                        read2 = dataInputStream.readInt();
                        zzb(bArr, zzcmVar, outputStream, readLong, read2, j4);
                        break;
                    default:
                        zzc(bArr, dataInputStream, outputStream, read2, j4);
                        break;
                }
                j3 += read2;
            } finally {
                outputStream.flush();
            }
        }
    }

    private static void zzb(byte[] bArr, zzcm zzcmVar, OutputStream outputStream, long j2, int i2, long j3) {
        if (i2 < 0) {
            throw new IOException("copyLength negative");
        }
        if (j2 < 0) {
            throw new IOException("inputOffset negative");
        }
        long j4 = i2;
        if (j4 > j3) {
            throw new IOException("Output length overrun");
        }
        try {
            InputStream zzc = new zzcn(zzcmVar, j2, j4).zzc();
            while (i2 > 0) {
                int min = Math.min(i2, 16384);
                int i3 = 0;
                while (i3 < min) {
                    int read = zzc.read(bArr, i3, min - i3);
                    if (read == -1) {
                        throw new IOException("truncated input stream");
                    }
                    i3 += read;
                }
                outputStream.write(bArr, 0, min);
                i2 -= min;
            }
            zzc.close();
        } catch (EOFException e2) {
            throw new IOException("patch underrun", e2);
        }
    }

    private static void zzc(byte[] bArr, DataInputStream dataInputStream, OutputStream outputStream, int i2, long j2) {
        if (i2 < 0) {
            throw new IOException("copyLength negative");
        }
        if (i2 > j2) {
            throw new IOException("Output length overrun");
        }
        while (i2 > 0) {
            try {
                int min = Math.min(i2, 16384);
                dataInputStream.readFully(bArr, 0, min);
                outputStream.write(bArr, 0, min);
                i2 -= min;
            } catch (EOFException unused) {
                throw new IOException("patch underrun");
            }
        }
    }
}
