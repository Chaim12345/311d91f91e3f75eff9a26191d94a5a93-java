package androidx.core.database;

import android.database.Cursor;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u0017\u0010\u0004\u001a\u0004\u0018\u00010\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\b\u001a\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0005*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001e\u0010\t\u001a\u0004\u0018\u00010\b*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\b¢\u0006\u0004\b\t\u0010\n\u001a\u001e\u0010\u000b\u001a\u0004\u0018\u00010\u0001*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\b¢\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010\u000e\u001a\u0004\u0018\u00010\r*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\b¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0010*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\b¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u0013*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\b¨\u0006\u0015"}, d2 = {"Landroid/database/Cursor;", "", FirebaseAnalytics.Param.INDEX, "", "getBlobOrNull", "", "getDoubleOrNull", "(Landroid/database/Cursor;I)Ljava/lang/Double;", "", "getFloatOrNull", "(Landroid/database/Cursor;I)Ljava/lang/Float;", "getIntOrNull", "(Landroid/database/Cursor;I)Ljava/lang/Integer;", "", "getLongOrNull", "(Landroid/database/Cursor;I)Ljava/lang/Long;", "", "getShortOrNull", "(Landroid/database/Cursor;I)Ljava/lang/Short;", "", "getStringOrNull", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class CursorKt {
    @Nullable
    public static final byte[] getBlobOrNull(@NotNull Cursor getBlobOrNull, int i2) {
        Intrinsics.checkNotNullParameter(getBlobOrNull, "$this$getBlobOrNull");
        if (getBlobOrNull.isNull(i2)) {
            return null;
        }
        return getBlobOrNull.getBlob(i2);
    }

    @Nullable
    public static final Double getDoubleOrNull(@NotNull Cursor getDoubleOrNull, int i2) {
        Intrinsics.checkNotNullParameter(getDoubleOrNull, "$this$getDoubleOrNull");
        if (getDoubleOrNull.isNull(i2)) {
            return null;
        }
        return Double.valueOf(getDoubleOrNull.getDouble(i2));
    }

    @Nullable
    public static final Float getFloatOrNull(@NotNull Cursor getFloatOrNull, int i2) {
        Intrinsics.checkNotNullParameter(getFloatOrNull, "$this$getFloatOrNull");
        if (getFloatOrNull.isNull(i2)) {
            return null;
        }
        return Float.valueOf(getFloatOrNull.getFloat(i2));
    }

    @Nullable
    public static final Integer getIntOrNull(@NotNull Cursor getIntOrNull, int i2) {
        Intrinsics.checkNotNullParameter(getIntOrNull, "$this$getIntOrNull");
        if (getIntOrNull.isNull(i2)) {
            return null;
        }
        return Integer.valueOf(getIntOrNull.getInt(i2));
    }

    @Nullable
    public static final Long getLongOrNull(@NotNull Cursor getLongOrNull, int i2) {
        Intrinsics.checkNotNullParameter(getLongOrNull, "$this$getLongOrNull");
        if (getLongOrNull.isNull(i2)) {
            return null;
        }
        return Long.valueOf(getLongOrNull.getLong(i2));
    }

    @Nullable
    public static final Short getShortOrNull(@NotNull Cursor getShortOrNull, int i2) {
        Intrinsics.checkNotNullParameter(getShortOrNull, "$this$getShortOrNull");
        if (getShortOrNull.isNull(i2)) {
            return null;
        }
        return Short.valueOf(getShortOrNull.getShort(i2));
    }

    @Nullable
    public static final String getStringOrNull(@NotNull Cursor getStringOrNull, int i2) {
        Intrinsics.checkNotNullParameter(getStringOrNull, "$this$getStringOrNull");
        if (getStringOrNull.isNull(i2)) {
            return null;
        }
        return getStringOrNull.getString(i2);
    }
}
