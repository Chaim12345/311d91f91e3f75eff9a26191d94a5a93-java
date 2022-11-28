package com.google.android.libraries.places.internal;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
/* loaded from: classes2.dex */
public abstract class zzacp implements Iterable, Serializable {
    private static final Comparator zza;
    public static final zzacp zzb = new zzacm(zzads.zzd);
    private static final zzaco zzd;
    private int zzc = 0;

    static {
        int i2 = zzace.zza;
        zzd = new zzaco(null);
        zza = new zzach();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzj(int i2, int i3, int i4) {
        if (((i4 - i3) | i3) >= 0) {
            return i3;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("End index: ");
        sb.append(i3);
        sb.append(" >= ");
        sb.append(i4);
        throw new IndexOutOfBoundsException(sb.toString());
    }

    public static zzacp zzl(String str) {
        return new zzacm(str.getBytes(zzads.zzb));
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i2 = this.zzc;
        if (i2 == 0) {
            int zzd2 = zzd();
            i2 = zze(zzd2, 0, zzd2);
            if (i2 == 0) {
                i2 = 1;
            }
            this.zzc = i2;
        }
        return i2;
    }

    @Override // java.lang.Iterable
    public final /* synthetic */ Iterator iterator() {
        return new zzacg(this);
    }

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(zzd());
        objArr[2] = zzd() <= 50 ? zzafr.zza(this) : zzafr.zza(zzf(0, 47)).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract byte zza(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte zzb(int i2);

    public abstract int zzd();

    protected abstract int zze(int i2, int i3, int i4);

    public abstract zzacp zzf(int i2, int i3);

    protected abstract String zzg(Charset charset);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzh(zzacf zzacfVar);

    public abstract boolean zzi();

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzk() {
        return this.zzc;
    }

    public final String zzm(Charset charset) {
        return zzd() == 0 ? "" : zzg(charset);
    }
}
