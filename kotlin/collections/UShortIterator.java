package kotlin.collections;

import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.SinceKotlin;
import kotlin.UShort;
import kotlin.jvm.internal.markers.KMappedMarker;
@Deprecated(level = DeprecationLevel.ERROR, message = "This class is not going to be stabilized and is to be removed soon.")
@SinceKotlin(version = "1.3")
/* loaded from: classes3.dex */
public abstract class UShortIterator implements Iterator<UShort>, KMappedMarker {
    @Override // java.util.Iterator
    public /* bridge */ /* synthetic */ UShort next() {
        return UShort.m459boximpl(m649nextMh2AYeg());
    }

    /* renamed from: next-Mh2AYeg  reason: not valid java name */
    public final short m649nextMh2AYeg() {
        return mo532nextUShortMh2AYeg();
    }

    /* renamed from: nextUShort-Mh2AYeg */
    public abstract short mo532nextUShortMh2AYeg();

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
