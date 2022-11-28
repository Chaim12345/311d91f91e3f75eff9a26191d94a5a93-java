package okhttp3.tls.internal.der;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AnyValue {
    @NotNull
    private final ByteString bytes;
    private boolean constructed;
    private long length;
    private long tag;
    private int tagClass;

    public AnyValue(int i2, long j2, boolean z, long j3, @NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        this.tagClass = i2;
        this.tag = j2;
        this.constructed = z;
        this.length = j3;
        this.bytes = bytes;
    }

    public /* synthetic */ AnyValue(int i2, long j2, boolean z, long j3, ByteString byteString, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, j2, (i3 & 4) != 0 ? false : z, (i3 & 8) != 0 ? -1L : j3, byteString);
    }

    public static /* synthetic */ AnyValue copy$default(AnyValue anyValue, int i2, long j2, boolean z, long j3, ByteString byteString, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = anyValue.tagClass;
        }
        if ((i3 & 2) != 0) {
            j2 = anyValue.tag;
        }
        long j4 = j2;
        if ((i3 & 4) != 0) {
            z = anyValue.constructed;
        }
        boolean z2 = z;
        if ((i3 & 8) != 0) {
            j3 = anyValue.length;
        }
        long j5 = j3;
        if ((i3 & 16) != 0) {
            byteString = anyValue.bytes;
        }
        return anyValue.copy(i2, j4, z2, j5, byteString);
    }

    public final int component1() {
        return this.tagClass;
    }

    public final long component2() {
        return this.tag;
    }

    public final boolean component3() {
        return this.constructed;
    }

    public final long component4() {
        return this.length;
    }

    @NotNull
    public final ByteString component5() {
        return this.bytes;
    }

    @NotNull
    public final AnyValue copy(int i2, long j2, boolean z, long j3, @NotNull ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return new AnyValue(i2, j2, z, j3, bytes);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AnyValue) {
            AnyValue anyValue = (AnyValue) obj;
            return this.tagClass == anyValue.tagClass && this.tag == anyValue.tag && this.constructed == anyValue.constructed && this.length == anyValue.length && Intrinsics.areEqual(this.bytes, anyValue.bytes);
        }
        return false;
    }

    @NotNull
    public final ByteString getBytes() {
        return this.bytes;
    }

    public final boolean getConstructed() {
        return this.constructed;
    }

    public final long getLength() {
        return this.length;
    }

    public final long getTag() {
        return this.tag;
    }

    public final int getTagClass() {
        return this.tagClass;
    }

    public int hashCode() {
        return ((((((((0 + this.tagClass) * 31) + ((int) this.tag)) * 31) + (!this.constructed ? 1 : 0)) * 31) + ((int) this.length)) * 31) + this.bytes.hashCode();
    }

    public final void setConstructed(boolean z) {
        this.constructed = z;
    }

    public final void setLength(long j2) {
        this.length = j2;
    }

    public final void setTag(long j2) {
        this.tag = j2;
    }

    public final void setTagClass(int i2) {
        this.tagClass = i2;
    }

    @NotNull
    public String toString() {
        return "AnyValue(tagClass=" + this.tagClass + ", tag=" + this.tag + ", constructed=" + this.constructed + ", length=" + this.length + ", bytes=" + this.bytes + ')';
    }
}
