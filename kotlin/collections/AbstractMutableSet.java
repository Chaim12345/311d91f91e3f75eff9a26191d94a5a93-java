package kotlin.collections;

import java.util.Set;
import kotlin.SinceKotlin;
@SinceKotlin(version = "1.1")
/* loaded from: classes3.dex */
public abstract class AbstractMutableSet<E> extends java.util.AbstractSet<E> implements Set<E> {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public abstract boolean add(E e2);

    public abstract int getSize();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final /* bridge */ int size() {
        return getSize();
    }
}
