package okhttp3.tls.internal.der;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Extension {
    private final boolean critical;
    @NotNull
    private final String id;
    @Nullable
    private final Object value;

    public Extension(@NotNull String id, boolean z, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(id, "id");
        this.id = id;
        this.critical = z;
        this.value = obj;
    }

    public static /* synthetic */ Extension copy$default(Extension extension, String str, boolean z, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            str = extension.id;
        }
        if ((i2 & 2) != 0) {
            z = extension.critical;
        }
        if ((i2 & 4) != 0) {
            obj = extension.value;
        }
        return extension.copy(str, z, obj);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    public final boolean component2() {
        return this.critical;
    }

    @Nullable
    public final Object component3() {
        return this.value;
    }

    @NotNull
    public final Extension copy(@NotNull String id, boolean z, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(id, "id");
        return new Extension(id, z, obj);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Extension) {
            Extension extension = (Extension) obj;
            return Intrinsics.areEqual(this.id, extension.id) && this.critical == extension.critical && Intrinsics.areEqual(this.value, extension.value);
        }
        return false;
    }

    public final boolean getCritical() {
        return this.critical;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @Nullable
    public final Object getValue() {
        return this.value;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.id.hashCode() * 31;
        boolean z = this.critical;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int i3 = (hashCode + i2) * 31;
        Object obj = this.value;
        return i3 + (obj == null ? 0 : obj.hashCode());
    }

    @NotNull
    public String toString() {
        return "Extension(id=" + this.id + ", critical=" + this.critical + ", value=" + this.value + ')';
    }
}
