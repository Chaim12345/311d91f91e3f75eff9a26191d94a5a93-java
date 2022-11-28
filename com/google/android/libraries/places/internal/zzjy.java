package com.google.android.libraries.places.internal;

import android.os.Build;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.text.Typography;
/* loaded from: classes2.dex */
final class zzjy extends zzjs {
    private static final AtomicReference zza = new AtomicReference();
    private static final AtomicLong zzb = new AtomicLong();
    private static final ConcurrentLinkedQueue zzc = new ConcurrentLinkedQueue();
    private volatile zzja zzd;

    private zzjy(String str) {
        super(str);
        String str2 = Build.FINGERPRINT;
        boolean z = true;
        boolean z2 = str2 == null || "robolectric".equals(str2);
        String str3 = Build.HARDWARE;
        boolean z3 = "goldfish".equals(str3) || "ranchu".equals(str3);
        String str4 = Build.TYPE;
        if (!"eng".equals(str4) && !"userdebug".equals(str4)) {
            z = false;
        }
        this.zzd = (z2 || z3) ? new zzjt().zza(zza()) : z ? new zzka().zzb(false).zza(zza()) : null;
    }

    public static zzja zzb(String str) {
        AtomicReference atomicReference = zza;
        if (atomicReference.get() != null) {
            return ((zzju) atomicReference.get()).zza(str);
        }
        zzjy zzjyVar = new zzjy(str.replace(Typography.dollar, '.'));
        zzjw.zza.offer(zzjyVar);
        if (atomicReference.get() != null) {
            while (true) {
                zzjy zzjyVar2 = (zzjy) zzjw.zza.poll();
                if (zzjyVar2 == null) {
                    break;
                }
                zzjyVar2.zzd = ((zzju) zza.get()).zza(zzjyVar2.zza());
            }
            if (((zzjx) zzc.poll()) != null) {
                zzb.getAndDecrement();
                throw null;
            }
        }
        return zzjyVar;
    }
}
