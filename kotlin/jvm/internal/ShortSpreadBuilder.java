package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ShortSpreadBuilder extends PrimitiveSpreadBuilder<short[]> {
    @NotNull
    private final short[] values;

    public ShortSpreadBuilder(int i2) {
        super(i2);
        this.values = new short[i2];
    }

    public final void add(short s2) {
        short[] sArr = this.values;
        int a2 = a();
        b(a2 + 1);
        sArr[a2] = s2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    /* renamed from: e */
    public int getSize(@NotNull short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "<this>");
        return sArr.length;
    }

    @NotNull
    public final short[] toArray() {
        return (short[]) d(this.values, new short[c()]);
    }
}
