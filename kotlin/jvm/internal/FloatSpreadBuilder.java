package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FloatSpreadBuilder extends PrimitiveSpreadBuilder<float[]> {
    @NotNull
    private final float[] values;

    public FloatSpreadBuilder(int i2) {
        super(i2);
        this.values = new float[i2];
    }

    public final void add(float f2) {
        float[] fArr = this.values;
        int a2 = a();
        b(a2 + 1);
        fArr[a2] = f2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    /* renamed from: e */
    public int getSize(@NotNull float[] fArr) {
        Intrinsics.checkNotNullParameter(fArr, "<this>");
        return fArr.length;
    }

    @NotNull
    public final float[] toArray() {
        return (float[]) d(this.values, new float[c()]);
    }
}
