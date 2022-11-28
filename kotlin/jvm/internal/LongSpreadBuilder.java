package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class LongSpreadBuilder extends PrimitiveSpreadBuilder<long[]> {
    @NotNull
    private final long[] values;

    public LongSpreadBuilder(int i2) {
        super(i2);
        this.values = new long[i2];
    }

    public final void add(long j2) {
        long[] jArr = this.values;
        int a2 = a();
        b(a2 + 1);
        jArr[a2] = j2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    /* renamed from: e */
    public int getSize(@NotNull long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "<this>");
        return jArr.length;
    }

    @NotNull
    public final long[] toArray() {
        return (long[]) d(this.values, new long[c()]);
    }
}
