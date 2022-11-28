package com.google.android.gms.internal.mlkit_vision_barcode;

import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
/* loaded from: classes2.dex */
public final class zzdc extends zzdd {
    public static int zza(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), (int) LockFreeTaskQueueCore.MAX_CAPACITY_MASK);
    }
}
