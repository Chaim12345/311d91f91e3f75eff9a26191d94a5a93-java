package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class BooleanSpreadBuilder extends PrimitiveSpreadBuilder<boolean[]> {
    @NotNull
    private final boolean[] values;

    public BooleanSpreadBuilder(int i2) {
        super(i2);
        this.values = new boolean[i2];
    }

    public final void add(boolean z) {
        boolean[] zArr = this.values;
        int a2 = a();
        b(a2 + 1);
        zArr[a2] = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    /* renamed from: e */
    public int getSize(@NotNull boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr.length;
    }

    @NotNull
    public final boolean[] toArray() {
        return (boolean[]) d(this.values, new boolean[c()]);
    }
}
