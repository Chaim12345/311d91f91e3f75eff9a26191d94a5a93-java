package kotlin.collections;

import java.util.RandomAccess;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ArraysKt___ArraysJvmKt$asList$8 extends AbstractList<Character> implements RandomAccess {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ char[] f11034a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArraysKt___ArraysJvmKt$asList$8(char[] cArr) {
        this.f11034a = cArr;
    }

    public boolean contains(char c2) {
        boolean contains;
        contains = ArraysKt___ArraysKt.contains(this.f11034a, c2);
        return contains;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Character) {
            return contains(((Character) obj).charValue());
        }
        return false;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    @NotNull
    public Character get(int i2) {
        return Character.valueOf(this.f11034a[i2]);
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.f11034a.length;
    }

    public int indexOf(char c2) {
        return ArraysKt___ArraysKt.indexOf(this.f11034a, c2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Character) {
            return indexOf(((Character) obj).charValue());
        }
        return -1;
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return this.f11034a.length == 0;
    }

    public int lastIndexOf(char c2) {
        return ArraysKt___ArraysKt.lastIndexOf(this.f11034a, c2);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Character) {
            return lastIndexOf(((Character) obj).charValue());
        }
        return -1;
    }
}
