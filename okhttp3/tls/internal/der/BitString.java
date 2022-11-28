package okhttp3.tls.internal.der;

import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class BitString {
    @NotNull
    private final ByteString byteString;
    private final int unusedBitsCount;

    public BitString(@NotNull ByteString byteString, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        this.byteString = byteString;
        this.unusedBitsCount = i2;
    }

    public static /* synthetic */ BitString copy$default(BitString bitString, ByteString byteString, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            byteString = bitString.byteString;
        }
        if ((i3 & 2) != 0) {
            i2 = bitString.unusedBitsCount;
        }
        return bitString.copy(byteString, i2);
    }

    @NotNull
    public final ByteString component1() {
        return this.byteString;
    }

    public final int component2() {
        return this.unusedBitsCount;
    }

    @NotNull
    public final BitString copy(@NotNull ByteString byteString, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        return new BitString(byteString, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BitString) {
            BitString bitString = (BitString) obj;
            return Intrinsics.areEqual(this.byteString, bitString.byteString) && this.unusedBitsCount == bitString.unusedBitsCount;
        }
        return false;
    }

    @NotNull
    public final ByteString getByteString() {
        return this.byteString;
    }

    public final int getUnusedBitsCount() {
        return this.unusedBitsCount;
    }

    public int hashCode() {
        return ((0 + this.byteString.hashCode()) * 31) + this.unusedBitsCount;
    }

    @NotNull
    public String toString() {
        return "BitString(byteString=" + this.byteString + ", unusedBitsCount=" + this.unusedBitsCount + ')';
    }
}
