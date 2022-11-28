package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class DoubleSpreadBuilder extends PrimitiveSpreadBuilder<double[]> {
    @NotNull
    private final double[] values;

    public DoubleSpreadBuilder(int i2) {
        super(i2);
        this.values = new double[i2];
    }

    public final void add(double d2) {
        double[] dArr = this.values;
        int a2 = a();
        b(a2 + 1);
        dArr[a2] = d2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    /* renamed from: e */
    public int getSize(@NotNull double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "<this>");
        return dArr.length;
    }

    @NotNull
    public final double[] toArray() {
        return (double[]) d(this.values, new double[c()]);
    }
}
