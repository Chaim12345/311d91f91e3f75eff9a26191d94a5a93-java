package kotlinx.coroutines.internal;

import java.util.Objects;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public class ArrayQueue<T> {
    @NotNull
    private Object[] elements = new Object[16];
    private int head;
    private int tail;

    private final void ensureCapacity() {
        Object[] objArr = this.elements;
        int length = objArr.length;
        Object[] objArr2 = new Object[length << 1];
        ArraysKt___ArraysJvmKt.copyInto$default(objArr, objArr2, 0, this.head, 0, 10, (Object) null);
        Object[] objArr3 = this.elements;
        int length2 = objArr3.length;
        int i2 = this.head;
        ArraysKt___ArraysJvmKt.copyInto$default(objArr3, objArr2, length2 - i2, 0, i2, 4, (Object) null);
        this.elements = objArr2;
        this.head = 0;
        this.tail = length;
    }

    public final void addLast(@NotNull T t2) {
        Object[] objArr = this.elements;
        int i2 = this.tail;
        objArr[i2] = t2;
        int length = (objArr.length - 1) & (i2 + 1);
        this.tail = length;
        if (length == this.head) {
            ensureCapacity();
        }
    }

    public final void clear() {
        this.head = 0;
        this.tail = 0;
        this.elements = new Object[this.elements.length];
    }

    public final boolean isEmpty() {
        return this.head == this.tail;
    }

    @Nullable
    public final T removeFirstOrNull() {
        int i2 = this.head;
        if (i2 == this.tail) {
            return null;
        }
        Object[] objArr = this.elements;
        T t2 = (T) objArr[i2];
        objArr[i2] = null;
        this.head = (i2 + 1) & (objArr.length - 1);
        Objects.requireNonNull(t2, "null cannot be cast to non-null type T of kotlinx.coroutines.internal.ArrayQueue");
        return t2;
    }
}
