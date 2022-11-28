package okhttp3.tls.internal.der;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AttributeTypeAndValue {
    @NotNull
    private final String type;
    @Nullable
    private final Object value;

    public AttributeTypeAndValue(@NotNull String type, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(type, "type");
        this.type = type;
        this.value = obj;
    }

    public static /* synthetic */ AttributeTypeAndValue copy$default(AttributeTypeAndValue attributeTypeAndValue, String str, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            str = attributeTypeAndValue.type;
        }
        if ((i2 & 2) != 0) {
            obj = attributeTypeAndValue.value;
        }
        return attributeTypeAndValue.copy(str, obj);
    }

    @NotNull
    public final String component1() {
        return this.type;
    }

    @Nullable
    public final Object component2() {
        return this.value;
    }

    @NotNull
    public final AttributeTypeAndValue copy(@NotNull String type, @Nullable Object obj) {
        Intrinsics.checkNotNullParameter(type, "type");
        return new AttributeTypeAndValue(type, obj);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AttributeTypeAndValue) {
            AttributeTypeAndValue attributeTypeAndValue = (AttributeTypeAndValue) obj;
            return Intrinsics.areEqual(this.type, attributeTypeAndValue.type) && Intrinsics.areEqual(this.value, attributeTypeAndValue.value);
        }
        return false;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }

    @Nullable
    public final Object getValue() {
        return this.value;
    }

    public int hashCode() {
        int hashCode = this.type.hashCode() * 31;
        Object obj = this.value;
        return hashCode + (obj == null ? 0 : obj.hashCode());
    }

    @NotNull
    public String toString() {
        return "AttributeTypeAndValue(type=" + this.type + ", value=" + this.value + ')';
    }
}
