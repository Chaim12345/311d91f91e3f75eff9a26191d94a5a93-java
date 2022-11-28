package kotlin.collections;

import java.util.List;
import kotlin.SinceKotlin;
@SinceKotlin(version = "1.1")
/* loaded from: classes3.dex */
public abstract class AbstractMutableList<E> extends java.util.AbstractList<E> implements List<E> {
    @Override // java.util.AbstractList, java.util.List
    public abstract void add(int i2, E e2);

    public abstract int getSize();

    @Override // java.util.AbstractList, java.util.List
    public final /* bridge */ E remove(int i2) {
        return removeAt(i2);
    }

    public abstract E removeAt(int i2);

    @Override // java.util.AbstractList, java.util.List
    public abstract E set(int i2, E e2);

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
