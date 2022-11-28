package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.apache.http.cookie.ClientCookie;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Serializable
/* loaded from: classes.dex */
public final class LogListV2 {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<Operator> operators;
    @Nullable
    private final String version;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<LogListV2> serializer() {
            return LogListV2$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ LogListV2(int i2, @SerialName("version") String str, @SerialName("operators") List list, SerializationConstructorMarker serializationConstructorMarker) {
        if (2 != (i2 & 2)) {
            PluginExceptionsKt.throwMissingFieldException(i2, 2, LogListV2$$serializer.INSTANCE.getDescriptor());
        }
        if ((i2 & 1) == 0) {
            this.version = null;
        } else {
            this.version = str;
        }
        this.operators = list;
    }

    public LogListV2(@Nullable String str, @NotNull List<Operator> operators) {
        Intrinsics.checkNotNullParameter(operators, "operators");
        this.version = str;
        this.operators = operators;
    }

    public /* synthetic */ LogListV2(String str, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ LogListV2 copy$default(LogListV2 logListV2, String str, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = logListV2.version;
        }
        if ((i2 & 2) != 0) {
            list = logListV2.operators;
        }
        return logListV2.copy(str, list);
    }

    @SerialName("operators")
    public static /* synthetic */ void getOperators$annotations() {
    }

    @SerialName(ClientCookie.VERSION_ATTR)
    public static /* synthetic */ void getVersion$annotations() {
    }

    @JvmStatic
    public static final void write$Self(@NotNull LogListV2 self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.version != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.version);
        }
        output.encodeSerializableElement(serialDesc, 1, new ArrayListSerializer(Operator$$serializer.INSTANCE), self.operators);
    }

    @Nullable
    public final String component1() {
        return this.version;
    }

    @NotNull
    public final List<Operator> component2() {
        return this.operators;
    }

    @NotNull
    public final LogListV2 copy(@Nullable String str, @NotNull List<Operator> operators) {
        Intrinsics.checkNotNullParameter(operators, "operators");
        return new LogListV2(str, operators);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LogListV2) {
            LogListV2 logListV2 = (LogListV2) obj;
            return Intrinsics.areEqual(this.version, logListV2.version) && Intrinsics.areEqual(this.operators, logListV2.operators);
        }
        return false;
    }

    @NotNull
    public final List<Operator> getOperators() {
        return this.operators;
    }

    @Nullable
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.version;
        return ((str == null ? 0 : str.hashCode()) * 31) + this.operators.hashCode();
    }

    @NotNull
    public String toString() {
        return "LogListV2(version=" + this.version + ", operators=" + this.operators + ')';
    }
}
