package com.simform.refresh;

import kotlin.Metadata;
import kotlin.ranges.RangesKt___RangesKt;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lcom/simform/refresh/SSDragDistanceConverter;", "", "", "scrollDistance", "refreshDistance", "convert", "<init>", "()V", "sspulltorefresh_release"}, k = 1, mv = {1, 5, 1})
/* loaded from: classes3.dex */
public final class SSDragDistanceConverter {
    public final float convert(float f2, float f3) {
        float coerceAtMost;
        float coerceAtMost2;
        float coerceAtLeast;
        coerceAtMost = RangesKt___RangesKt.coerceAtMost(1.0f, Math.abs(f2 / f3));
        coerceAtMost2 = RangesKt___RangesKt.coerceAtMost(Math.abs(f2) - f3, f3 * 2.0f);
        coerceAtLeast = RangesKt___RangesKt.coerceAtLeast(0.0f, coerceAtMost2 / f3);
        double d2 = coerceAtLeast / 4;
        return (int) ((f3 * coerceAtMost) + (((float) (d2 - Math.pow(d2, 2.0d))) * 2.0f * f3 * 2));
    }
}
