package okhttp3.tls.internal.der;

import com.fasterxml.jackson.core.JsonPointer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DerHeader {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int TAG_CLASS_APPLICATION = 64;
    public static final int TAG_CLASS_CONTEXT_SPECIFIC = 128;
    public static final int TAG_CLASS_PRIVATE = 192;
    public static final int TAG_CLASS_UNIVERSAL = 0;
    public static final long TAG_END_OF_CONTENTS = 0;
    private boolean constructed;
    private long length;
    private long tag;
    private int tagClass;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DerHeader(int i2, long j2, boolean z, long j3) {
        this.tagClass = i2;
        this.tag = j2;
        this.constructed = z;
        this.length = j3;
    }

    public static /* synthetic */ DerHeader copy$default(DerHeader derHeader, int i2, long j2, boolean z, long j3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = derHeader.tagClass;
        }
        if ((i3 & 2) != 0) {
            j2 = derHeader.tag;
        }
        long j4 = j2;
        if ((i3 & 4) != 0) {
            z = derHeader.constructed;
        }
        boolean z2 = z;
        if ((i3 & 8) != 0) {
            j3 = derHeader.length;
        }
        return derHeader.copy(i2, j4, z2, j3);
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
    public final DerHeader copy(int i2, long j2, boolean z, long j3) {
        return new DerHeader(i2, j2, z, j3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DerHeader) {
            DerHeader derHeader = (DerHeader) obj;
            return this.tagClass == derHeader.tagClass && this.tag == derHeader.tag && this.constructed == derHeader.constructed && this.length == derHeader.length;
        }
        return false;
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
        return ((((((0 + this.tagClass) * 31) + ((int) this.tag)) * 31) + (!this.constructed ? 1 : 0)) * 31) + ((int) this.length);
    }

    public final boolean isEndOfData() {
        return this.tagClass == 0 && this.tag == 0;
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
        StringBuilder sb = new StringBuilder();
        sb.append(this.tagClass);
        sb.append(JsonPointer.SEPARATOR);
        sb.append(this.tag);
        return sb.toString();
    }
}
