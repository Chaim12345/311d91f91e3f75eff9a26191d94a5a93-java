package kotlinx.serialization.json.internal;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class ArrayAsSequence implements CharSequence {
    private final int length;
    @NotNull
    private final char[] source;

    public ArrayAsSequence(@NotNull char[] source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        this.length = source.length;
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ char charAt(int i2) {
        return get(i2);
    }

    public char get(int i2) {
        return this.source[i2];
    }

    public int getLength() {
        return this.length;
    }

    @Override // java.lang.CharSequence
    public final /* bridge */ int length() {
        return getLength();
    }

    @Override // java.lang.CharSequence
    @NotNull
    public CharSequence subSequence(int i2, int i3) {
        return new String(this.source, i2, i3 - i2);
    }
}
