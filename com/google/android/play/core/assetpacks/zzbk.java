package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
/* loaded from: classes2.dex */
final class zzbk extends com.google.android.play.core.internal.zzcm {
    private final File zza;
    private final File zzb;
    private final NavigableMap zzc = new TreeMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbk(File file, File file2) {
        this.zza = file;
        this.zzb = file2;
        List<File> a2 = zzep.a(file, file2);
        if (a2.isEmpty()) {
            throw new zzck(String.format("Virtualized slice archive empty for %s, %s", file, file2));
        }
        long j2 = 0;
        for (File file3 : a2) {
            this.zzc.put(Long.valueOf(j2), file3);
            j2 += file3.length();
        }
    }

    private final InputStream zzd(long j2, Long l2) {
        FileInputStream fileInputStream = new FileInputStream((File) this.zzc.get(l2));
        if (fileInputStream.skip(j2 - l2.longValue()) == j2 - l2.longValue()) {
            return fileInputStream;
        }
        throw new zzck(String.format("Virtualized slice archive corrupt, could not skip in file with key %s", l2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.play.core.internal.zzcm
    public final InputStream a(long j2, long j3) {
        if (j2 < 0 || j3 < 0) {
            throw new zzck(String.format("Invalid input parameters %s, %s", Long.valueOf(j2), Long.valueOf(j3)));
        }
        long j4 = j2 + j3;
        if (j4 <= zza()) {
            Long l2 = (Long) this.zzc.floorKey(Long.valueOf(j2));
            Long l3 = (Long) this.zzc.floorKey(Long.valueOf(j4));
            if (l2.equals(l3)) {
                return new zzbj(zzd(j2, l2), j3);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(zzd(j2, l2));
            Collection values = this.zzc.subMap(l2, false, l3, false).values();
            if (!values.isEmpty()) {
                arrayList.add(new zzdr(Collections.enumeration(values)));
            }
            arrayList.add(new zzbj(new FileInputStream((File) this.zzc.get(l3)), j3 - (l3.longValue() - j2)));
            return new SequenceInputStream(Collections.enumeration(arrayList));
        }
        throw new zzck(String.format("Trying to access archive out of bounds. Archive ends at: %s. Tried accessing: %s", Long.valueOf(zza()), Long.valueOf(j4)));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // com.google.android.play.core.internal.zzcm
    public final long zza() {
        Map.Entry lastEntry = this.zzc.lastEntry();
        return ((Long) lastEntry.getKey()).longValue() + ((File) lastEntry.getValue()).length();
    }
}
