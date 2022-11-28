package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Serializable
/* loaded from: classes.dex */
public final class Operator {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final List<String> email;
    @NotNull
    private final List<Log> logs;
    @NotNull
    private final String name;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<Operator> serializer() {
            return Operator$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ Operator(int i2, @SerialName("name") String str, @SerialName("email") List list, @SerialName("logs") List list2, SerializationConstructorMarker serializationConstructorMarker) {
        if (7 != (i2 & 7)) {
            PluginExceptionsKt.throwMissingFieldException(i2, 7, Operator$$serializer.INSTANCE.getDescriptor());
        }
        this.name = str;
        this.email = list;
        this.logs = list2;
        if (!(str.length() > 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(!list.isEmpty())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(!list2.isEmpty())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public Operator(@NotNull String name, @NotNull List<String> email, @NotNull List<Log> logs) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(logs, "logs");
        this.name = name;
        this.email = email;
        this.logs = logs;
        if (!(name.length() > 0)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(!email.isEmpty())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (!(!logs.isEmpty())) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Operator copy$default(Operator operator, String str, List list, List list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = operator.name;
        }
        if ((i2 & 2) != 0) {
            list = operator.email;
        }
        if ((i2 & 4) != 0) {
            list2 = operator.logs;
        }
        return operator.copy(str, list, list2);
    }

    @SerialName("email")
    public static /* synthetic */ void getEmail$annotations() {
    }

    @SerialName("logs")
    public static /* synthetic */ void getLogs$annotations() {
    }

    @SerialName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    public static /* synthetic */ void getName$annotations() {
    }

    @JvmStatic
    public static final void write$Self(@NotNull Operator self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeStringElement(serialDesc, 0, self.name);
        output.encodeSerializableElement(serialDesc, 1, new ArrayListSerializer(StringSerializer.INSTANCE), self.email);
        output.encodeSerializableElement(serialDesc, 2, new ArrayListSerializer(Log$$serializer.INSTANCE), self.logs);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final List<String> component2() {
        return this.email;
    }

    @NotNull
    public final List<Log> component3() {
        return this.logs;
    }

    @NotNull
    public final Operator copy(@NotNull String name, @NotNull List<String> email, @NotNull List<Log> logs) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(logs, "logs");
        return new Operator(name, email, logs);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Operator) {
            Operator operator = (Operator) obj;
            return Intrinsics.areEqual(this.name, operator.name) && Intrinsics.areEqual(this.email, operator.email) && Intrinsics.areEqual(this.logs, operator.logs);
        }
        return false;
    }

    @NotNull
    public final List<String> getEmail() {
        return this.email;
    }

    @NotNull
    public final List<Log> getLogs() {
        return this.logs;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.email.hashCode()) * 31) + this.logs.hashCode();
    }

    @NotNull
    public String toString() {
        return "Operator(name=" + this.name + ", email=" + this.email + ", logs=" + this.logs + ')';
    }
}
