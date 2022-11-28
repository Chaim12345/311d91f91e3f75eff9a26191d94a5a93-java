package kotlin.jvm.internal;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ByteSpreadBuilder extends PrimitiveSpreadBuilder<byte[]> {
    @NotNull
    private final byte[] values;

    public ByteSpreadBuilder(int i2) {
        super(i2);
        this.values = new byte[i2];
    }

    public final void add(byte b2) {
        byte[] bArr = this.values;
        int a2 = a();
        b(a2 + 1);
        bArr[a2] = b2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    /* renamed from: e */
    public int getSize(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return bArr.length;
    }

    @NotNull
    public final byte[] toArray() {
        return (byte[]) d(this.values, new byte[c()]);
    }
}
