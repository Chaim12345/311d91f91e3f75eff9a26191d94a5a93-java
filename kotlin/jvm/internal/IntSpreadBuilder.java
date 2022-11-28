package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class IntSpreadBuilder extends PrimitiveSpreadBuilder<int[]> {
    @NotNull
    private final int[] values;

    public IntSpreadBuilder(int i2) {
        super(i2);
        this.values = new int[i2];
    }

    public final void add(int i2) {
        int[] iArr = this.values;
        int a2 = a();
        b(a2 + 1);
        iArr[a2] = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    /* renamed from: e */
    public int getSize(@NotNull int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return iArr.length;
    }

    @NotNull
    public final int[] toArray() {
        return (int[]) d(this.values, new int[c()]);
    }
}
