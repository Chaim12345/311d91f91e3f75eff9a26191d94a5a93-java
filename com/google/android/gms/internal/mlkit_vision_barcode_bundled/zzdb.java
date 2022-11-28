package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
import org.slf4j.Marker;
/* loaded from: classes2.dex */
public abstract class zzdb implements Iterable, Serializable {
    private static final Comparator zza;
    public static final zzdb zzb = new zzcz(zzel.zzd);
    private static final zzda zzd;
    private int zzc = 0;

    static {
        int i2 = zzcn.zza;
        zzd = new zzda(null);
        zza = new zzcu();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int i(int i2, int i3, int i4) {
        int i5 = i3 - i2;
        if ((i2 | i3 | i5 | (i4 - i3)) < 0) {
            if (i2 < 0) {
                StringBuilder sb = new StringBuilder(32);
                sb.append("Beginning index: ");
                sb.append(i2);
                sb.append(" < 0");
                throw new IndexOutOfBoundsException(sb.toString());
            } else if (i3 < i2) {
                StringBuilder sb2 = new StringBuilder(66);
                sb2.append("Beginning index larger than ending index: ");
                sb2.append(i2);
                sb2.append(", ");
                sb2.append(i3);
                throw new IndexOutOfBoundsException(sb2.toString());
            } else {
                StringBuilder sb3 = new StringBuilder(37);
                sb3.append("End index: ");
                sb3.append(i3);
                sb3.append(" >= ");
                sb3.append(i4);
                throw new IndexOutOfBoundsException(sb3.toString());
            }
        }
        return i5;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void k(int i2, int i3) {
        if (((i3 - (i2 + 1)) | i2) < 0) {
            if (i2 < 0) {
                StringBuilder sb = new StringBuilder(22);
                sb.append("Index < 0: ");
                sb.append(i2);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder(40);
            sb2.append("Index > length: ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(i3);
            throw new ArrayIndexOutOfBoundsException(sb2.toString());
        }
    }

    private static zzdb zzc(Iterator it, int i2) {
        if (i2 > 0) {
            if (i2 == 1) {
                return (zzdb) it.next();
            }
            int i3 = i2 >>> 1;
            zzdb zzc = zzc(it, i3);
            zzdb zzc2 = zzc(it, i2 - i3);
            if (Integer.MAX_VALUE - zzc.zzd() >= zzc2.zzd()) {
                return zzga.l(zzc, zzc2);
            }
            int zzd2 = zzc.zzd();
            int zzd3 = zzc2.zzd();
            StringBuilder sb = new StringBuilder(53);
            sb.append("ByteString would be too long: ");
            sb.append(zzd2);
            sb.append(Marker.ANY_NON_NULL_MARKER);
            sb.append(zzd3);
            throw new IllegalArgumentException(sb.toString());
        }
        throw new IllegalArgumentException(String.format("length (%s) must be >= 1", Integer.valueOf(i2)));
    }

    public static zzdb zzr(byte[] bArr, int i2, int i3) {
        i(i2, i2 + i3, bArr.length);
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i2, bArr2, 0, i3);
        return new zzcz(bArr2);
    }

    public static zzdb zzs(String str) {
        return new zzcz(str.getBytes(zzel.f6425a));
    }

    public static zzdb zzt(InputStream inputStream) {
        ArrayList arrayList = new ArrayList();
        int i2 = 256;
        while (true) {
            byte[] bArr = new byte[i2];
            int i3 = 0;
            while (i3 < i2) {
                int read = inputStream.read(bArr, i3, i2 - i3);
                if (read == -1) {
                    break;
                }
                i3 += read;
            }
            zzdb zzr = i3 == 0 ? null : zzr(bArr, 0, i3);
            if (zzr == null) {
                break;
            }
            arrayList.add(zzr);
            i2 = Math.min(i2 + i2, 8192);
        }
        int size = arrayList.size();
        return size == 0 ? zzb : zzc(arrayList.iterator(), size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte a(int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void b(byte[] bArr, int i2, int i3, int i4);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int c();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean d();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int e(int i2, int i3, int i4);

    public abstract boolean equals(Object obj);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int f(int i2, int i3, int i4);

    protected abstract String g(Charset charset);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void h(zzcr zzcrVar);

    public final int hashCode() {
        int i2 = this.zzc;
        if (i2 == 0) {
            int zzd2 = zzd();
            i2 = e(zzd2, 0, zzd2);
            if (i2 == 0) {
                i2 = 1;
            }
            this.zzc = i2;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int j() {
        return this.zzc;
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzd());
        objArr[2] = zzd() <= 50 ? zzgn.a(this) : zzgn.a(zzk(0, 47)).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int i2);

    public abstract int zzd();

    public abstract zzdb zzk(int i2, int i3);

    public abstract boolean zzn();

    @Override // java.lang.Iterable
    /* renamed from: zzq */
    public zzcx iterator() {
        return new zzcs(this);
    }

    public final String zzu(Charset charset) {
        return zzd() == 0 ? "" : g(charset);
    }

    public final String zzv() {
        return zzu(zzel.f6425a);
    }

    @Deprecated
    public final void zzx(byte[] bArr, int i2, int i3, int i4) {
        i(0, i4, zzd());
        i(i3, i3 + i4, bArr.length);
        if (i4 > 0) {
            b(bArr, 0, i3, i4);
        }
    }

    public final byte[] zzy() {
        int zzd2 = zzd();
        if (zzd2 == 0) {
            return zzel.zzd;
        }
        byte[] bArr = new byte[zzd2];
        b(bArr, 0, 0, zzd2);
        return bArr;
    }
}
