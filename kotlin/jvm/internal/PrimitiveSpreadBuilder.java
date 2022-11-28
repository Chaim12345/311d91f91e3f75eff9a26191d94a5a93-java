package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public abstract class PrimitiveSpreadBuilder<T> {
    private int position;
    private final int size;
    @NotNull
    private final T[] spreads;

    public PrimitiveSpreadBuilder(int i2) {
        this.size = i2;
        this.spreads = (T[]) new Object[i2];
    }

    private static /* synthetic */ void getSpreads$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int a() {
        return this.position;
    }

    public final void addSpread(@NotNull T spreadArgument) {
        Intrinsics.checkNotNullParameter(spreadArgument, "spreadArgument");
        T[] tArr = this.spreads;
        int i2 = this.position;
        this.position = i2 + 1;
        tArr[i2] = spreadArgument;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(int i2) {
        this.position = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int c() {
        int i2 = this.size - 1;
        int i3 = 0;
        if (i2 >= 0) {
            int i4 = 0;
            while (true) {
                T t2 = this.spreads[i4];
                i3 += t2 != null ? getSize(t2) : 1;
                if (i4 == i2) {
                    break;
                }
                i4++;
            }
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @NotNull
    public final Object d(@NotNull Object values, @NotNull Object result) {
        int i2;
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(result, "result");
        int i3 = this.size - 1;
        int i4 = 0;
        if (i3 >= 0) {
            int i5 = 0;
            int i6 = 0;
            i2 = 0;
            while (true) {
                T t2 = this.spreads[i5];
                if (t2 != null) {
                    if (i6 < i5) {
                        int i7 = i5 - i6;
                        System.arraycopy(values, i6, result, i2, i7);
                        i2 += i7;
                    }
                    int size = getSize(t2);
                    System.arraycopy(t2, 0, result, i2, size);
                    i2 += size;
                    i6 = i5 + 1;
                }
                if (i5 == i3) {
                    break;
                }
                i5++;
            }
            i4 = i6;
        } else {
            i2 = 0;
        }
        int i8 = this.size;
        if (i4 < i8) {
            System.arraycopy(values, i4, result, i2, i8 - i4);
        }
        return result;
    }

    protected abstract int getSize(@NotNull Object obj);
}
