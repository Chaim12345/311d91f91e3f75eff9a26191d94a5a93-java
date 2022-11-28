package kotlinx.coroutines.internal;

import java.lang.Comparable;
import java.util.Arrays;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.InternalCoroutinesApi;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@InternalCoroutinesApi
/* loaded from: classes3.dex */
public class ThreadSafeHeap<T extends ThreadSafeHeapNode & Comparable<? super T>> {
    @NotNull
    private volatile /* synthetic */ int _size = 0;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    private T[] f12362a;

    private final T[] realloc() {
        T[] tArr = this.f12362a;
        if (tArr == null) {
            T[] tArr2 = (T[]) new ThreadSafeHeapNode[4];
            this.f12362a = tArr2;
            return tArr2;
        } else if (getSize() >= tArr.length) {
            Object[] copyOf = Arrays.copyOf(tArr, getSize() * 2);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            T[] tArr3 = (T[]) ((ThreadSafeHeapNode[]) copyOf);
            this.f12362a = tArr3;
            return tArr3;
        } else {
            return tArr;
        }
    }

    private final void setSize(int i2) {
        this._size = i2;
    }

    private final void siftDownFrom(int i2) {
        while (true) {
            int i3 = (i2 * 2) + 1;
            if (i3 >= getSize()) {
                return;
            }
            T[] tArr = this.f12362a;
            Intrinsics.checkNotNull(tArr);
            int i4 = i3 + 1;
            if (i4 < getSize()) {
                T t2 = tArr[i4];
                Intrinsics.checkNotNull(t2);
                T t3 = tArr[i3];
                Intrinsics.checkNotNull(t3);
                if (((Comparable) t2).compareTo(t3) < 0) {
                    i3 = i4;
                }
            }
            T t4 = tArr[i2];
            Intrinsics.checkNotNull(t4);
            T t5 = tArr[i3];
            Intrinsics.checkNotNull(t5);
            if (((Comparable) t4).compareTo(t5) <= 0) {
                return;
            }
            swap(i2, i3);
            i2 = i3;
        }
    }

    private final void siftUpFrom(int i2) {
        while (i2 > 0) {
            T[] tArr = this.f12362a;
            Intrinsics.checkNotNull(tArr);
            int i3 = (i2 - 1) / 2;
            T t2 = tArr[i3];
            Intrinsics.checkNotNull(t2);
            T t3 = tArr[i2];
            Intrinsics.checkNotNull(t3);
            if (((Comparable) t2).compareTo(t3) <= 0) {
                return;
            }
            swap(i2, i3);
            i2 = i3;
        }
    }

    private final void swap(int i2, int i3) {
        T[] tArr = this.f12362a;
        Intrinsics.checkNotNull(tArr);
        T t2 = tArr[i3];
        Intrinsics.checkNotNull(t2);
        T t3 = tArr[i2];
        Intrinsics.checkNotNull(t3);
        tArr[i2] = t2;
        tArr[i3] = t3;
        t2.setIndex(i2);
        t3.setIndex(i3);
    }

    @PublishedApi
    public final void addImpl(@NotNull T t2) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(t2.getHeap() == null)) {
                throw new AssertionError();
            }
        }
        t2.setHeap(this);
        T[] realloc = realloc();
        int size = getSize();
        setSize(size + 1);
        realloc[size] = t2;
        t2.setIndex(size);
        siftUpFrom(size);
    }

    public final void addLast(@NotNull T t2) {
        synchronized (this) {
            addImpl(t2);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean addLastIf(@NotNull T t2, @NotNull Function1<? super T, Boolean> function1) {
        boolean z;
        synchronized (this) {
            try {
                if (function1.invoke(firstImpl()).booleanValue()) {
                    addImpl(t2);
                    z = true;
                } else {
                    z = false;
                }
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        return z;
    }

    public final void clear() {
        synchronized (this) {
            T[] tArr = this.f12362a;
            if (tArr != null) {
                ArraysKt___ArraysJvmKt.fill$default(tArr, (Object) null, 0, 0, 6, (Object) null);
            }
            this._size = 0;
            Unit unit = Unit.INSTANCE;
        }
    }

    @PublishedApi
    @Nullable
    public final T firstImpl() {
        T[] tArr = this.f12362a;
        if (tArr != null) {
            return tArr[0];
        }
        return null;
    }

    public final int getSize() {
        return this._size;
    }

    public final boolean isEmpty() {
        return getSize() == 0;
    }

    @Nullable
    public final T peek() {
        T firstImpl;
        synchronized (this) {
            firstImpl = firstImpl();
        }
        return firstImpl;
    }

    public final boolean remove(@NotNull T t2) {
        boolean z;
        synchronized (this) {
            z = true;
            if (t2.getHeap() == null) {
                z = false;
            } else {
                int index = t2.getIndex();
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    if (!(index >= 0)) {
                        throw new AssertionError();
                    }
                }
                removeAtImpl(index);
            }
        }
        return z;
    }

    @PublishedApi
    @NotNull
    public final T removeAtImpl(int i2) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getSize() > 0)) {
                throw new AssertionError();
            }
        }
        T[] tArr = this.f12362a;
        Intrinsics.checkNotNull(tArr);
        setSize(getSize() - 1);
        if (i2 < getSize()) {
            swap(i2, getSize());
            int i3 = (i2 - 1) / 2;
            if (i2 > 0) {
                T t2 = tArr[i2];
                Intrinsics.checkNotNull(t2);
                T t3 = tArr[i3];
                Intrinsics.checkNotNull(t3);
                if (((Comparable) t2).compareTo(t3) < 0) {
                    swap(i2, i3);
                    siftUpFrom(i3);
                }
            }
            siftDownFrom(i2);
        }
        T t4 = tArr[getSize()];
        Intrinsics.checkNotNull(t4);
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(t4.getHeap() == this)) {
                throw new AssertionError();
            }
        }
        t4.setHeap(null);
        t4.setIndex(-1);
        tArr[getSize()] = null;
        return t4;
    }

    @Nullable
    public final T removeFirstIf(@NotNull Function1<? super T, Boolean> function1) {
        synchronized (this) {
            try {
                T firstImpl = firstImpl();
                if (firstImpl == null) {
                    InlineMarker.finallyStart(2);
                    InlineMarker.finallyEnd(2);
                    return null;
                }
                T removeAtImpl = function1.invoke(firstImpl).booleanValue() ? removeAtImpl(0) : null;
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                return removeAtImpl;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
    }

    @Nullable
    public final T removeFirstOrNull() {
        T removeAtImpl;
        synchronized (this) {
            removeAtImpl = getSize() > 0 ? removeAtImpl(0) : null;
        }
        return removeAtImpl;
    }
}
