package kotlinx.serialization;

import java.util.List;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@PublishedApi
/* loaded from: classes3.dex */
public final class MissingFieldException extends SerializationException {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MissingFieldException(@NotNull String fieldName) {
        this("Field '" + fieldName + "' is required, but it was missing", (Throwable) null);
        Intrinsics.checkNotNullParameter(fieldName, "fieldName");
    }

    public MissingFieldException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public MissingFieldException(@NotNull List<String> fieldNames, @NotNull String serialName) {
        this(r0.toString(), (Throwable) null);
        StringBuilder sb;
        String str;
        Intrinsics.checkNotNullParameter(fieldNames, "fieldNames");
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        if (fieldNames.size() == 1) {
            sb = new StringBuilder();
            sb.append("Field '");
            sb.append(fieldNames.get(0));
            sb.append("' is required for type with serial name '");
            sb.append(serialName);
            str = "', but it was missing";
        } else {
            sb = new StringBuilder();
            sb.append("Fields ");
            sb.append(fieldNames);
            sb.append(" are required for type with serial name '");
            sb.append(serialName);
            str = "', but they were missing";
        }
        sb.append(str);
    }
}
