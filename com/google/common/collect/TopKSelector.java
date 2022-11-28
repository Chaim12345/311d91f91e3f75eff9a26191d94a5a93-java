package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes2.dex */
public final class TopKSelector<T> {
    private final T[] buffer;
    private int bufferSize;
    private final Comparator<? super T> comparator;

    /* renamed from: k  reason: collision with root package name */
    private final int f9054k;
    @NullableDecl
    private T threshold;

    private TopKSelector(Comparator<? super T> comparator, int i2) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator, "comparator");
        this.f9054k = i2;
        Preconditions.checkArgument(i2 >= 0, "k (%s) must be >= 0", i2);
        Preconditions.checkArgument(i2 <= 1073741823, "k (%s) must be <= Integer.MAX_VALUE / 2", i2);
        this.buffer = (T[]) new Object[IntMath.checkedMultiply(i2, 2)];
        this.bufferSize = 0;
        this.threshold = null;
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> greatest(int i2) {
        return greatest(i2, Ordering.natural());
    }

    public static <T> TopKSelector<T> greatest(int i2, Comparator<? super T> comparator) {
        return new TopKSelector<>(Ordering.from(comparator).reverse(), i2);
    }

    public static <T extends Comparable<? super T>> TopKSelector<T> least(int i2) {
        return least(i2, Ordering.natural());
    }

    public static <T> TopKSelector<T> least(int i2, Comparator<? super T> comparator) {
        return new TopKSelector<>(comparator, i2);
    }

    private int partition(int i2, int i3, int i4) {
        T[] tArr = this.buffer;
        T t2 = tArr[i4];
        tArr[i4] = tArr[i3];
        int i5 = i2;
        while (i2 < i3) {
            if (this.comparator.compare((Object) this.buffer[i2], t2) < 0) {
                swap(i5, i2);
                i5++;
            }
            i2++;
        }
        T[] tArr2 = this.buffer;
        tArr2[i3] = tArr2[i5];
        tArr2[i5] = t2;
        return i5;
    }

    private void swap(int i2, int i3) {
        T[] tArr = this.buffer;
        T t2 = tArr[i2];
        tArr[i2] = tArr[i3];
        tArr[i3] = t2;
    }

    private void trim() {
        int i2 = (this.f9054k * 2) - 1;
        int log2 = IntMath.log2(i2 + 0, RoundingMode.CEILING) * 3;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i3 < i2) {
                int partition = partition(i3, i2, ((i3 + i2) + 1) >>> 1);
                int i6 = this.f9054k;
                if (partition <= i6) {
                    if (partition >= i6) {
                        break;
                    }
                    i3 = Math.max(partition, i3 + 1);
                    i5 = partition;
                } else {
                    i2 = partition - 1;
                }
                i4++;
                if (i4 >= log2) {
                    Arrays.sort(this.buffer, i3, i2, this.comparator);
                    break;
                }
            } else {
                break;
            }
        }
        this.bufferSize = this.f9054k;
        this.threshold = this.buffer[i5];
        while (true) {
            i5++;
            if (i5 >= this.f9054k) {
                return;
            }
            if (this.comparator.compare((Object) this.buffer[i5], (T) this.threshold) > 0) {
                this.threshold = this.buffer[i5];
            }
        }
    }

    public void offer(@NullableDecl T t2) {
        int i2 = this.f9054k;
        if (i2 == 0) {
            return;
        }
        int i3 = this.bufferSize;
        if (i3 == 0) {
            this.buffer[0] = t2;
            this.threshold = t2;
            this.bufferSize = 1;
        } else if (i3 < i2) {
            T[] tArr = this.buffer;
            this.bufferSize = i3 + 1;
            tArr[i3] = t2;
            if (this.comparator.compare(t2, (T) this.threshold) > 0) {
                this.threshold = t2;
            }
        } else if (this.comparator.compare(t2, (T) this.threshold) < 0) {
            T[] tArr2 = this.buffer;
            int i4 = this.bufferSize;
            int i5 = i4 + 1;
            this.bufferSize = i5;
            tArr2[i4] = t2;
            if (i5 == this.f9054k * 2) {
                trim();
            }
        }
    }

    public void offerAll(Iterable<? extends T> iterable) {
        offerAll(iterable.iterator());
    }

    public void offerAll(Iterator<? extends T> it) {
        while (it.hasNext()) {
            offer(it.next());
        }
    }

    public List<T> topK() {
        Arrays.sort(this.buffer, 0, this.bufferSize, this.comparator);
        int i2 = this.bufferSize;
        int i3 = this.f9054k;
        if (i2 > i3) {
            T[] tArr = this.buffer;
            Arrays.fill(tArr, i3, tArr.length, (Object) null);
            int i4 = this.f9054k;
            this.bufferSize = i4;
            this.threshold = this.buffer[i4 - 1];
        }
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(this.buffer, this.bufferSize)));
    }
}
