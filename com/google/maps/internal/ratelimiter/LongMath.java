package com.google.maps.internal.ratelimiter;

import kotlin.jvm.internal.LongCompanionObject;
/* loaded from: classes2.dex */
public final class LongMath {
    private LongMath() {
    }

    public static long saturatedAdd(long j2, long j3) {
        long j4 = j2 + j3;
        return (((j3 ^ j2) > 0L ? 1 : ((j3 ^ j2) == 0L ? 0 : -1)) < 0) | ((j2 ^ j4) >= 0) ? j4 : ((j4 >>> 63) ^ 1) + LongCompanionObject.MAX_VALUE;
    }
}
