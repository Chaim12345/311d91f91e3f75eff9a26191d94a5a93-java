package kotlinx.coroutines.debug.internal;

import java.util.Comparator;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
/* renamed from: kotlinx.coroutines.debug.internal.DebugProbesImpl$dumpCoroutinesInfoImpl$lambda-12$$inlined$sortedBy$1  reason: invalid class name */
/* loaded from: classes3.dex */
public final class DebugProbesImpl$dumpCoroutinesInfoImpl$lambda12$$inlined$sortedBy$1<T> implements Comparator {
    @Override // java.util.Comparator
    public final int compare(T t2, T t3) {
        int compareValues;
        compareValues = ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((DebugProbesImpl.CoroutineOwner) t2).info.sequenceNumber), Long.valueOf(((DebugProbesImpl.CoroutineOwner) t3).info.sequenceNumber));
        return compareValues;
    }
}
