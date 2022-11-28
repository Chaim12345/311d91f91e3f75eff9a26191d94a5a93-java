package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import com.appmattus.certificatetransparency.internal.loglist.deserializer.Rfc3339Deserializer;
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
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Serializable
/* loaded from: classes.dex */
public final class TemporalInterval {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private final long endExclusive;
    private final long startInclusive;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<TemporalInterval> serializer() {
            return TemporalInterval$$serializer.INSTANCE;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ TemporalInterval(int i2, @SerialName("start_inclusive") @Serializable(with = Rfc3339Deserializer.class) long j2, @SerialName("end_exclusive") @Serializable(with = Rfc3339Deserializer.class) long j3, SerializationConstructorMarker serializationConstructorMarker) {
        if (3 != (i2 & 3)) {
            PluginExceptionsKt.throwMissingFieldException(i2, 3, TemporalInterval$$serializer.INSTANCE.getDescriptor());
        }
        this.startInclusive = j2;
        this.endExclusive = j3;
    }

    public TemporalInterval(long j2, long j3) {
        this.startInclusive = j2;
        this.endExclusive = j3;
    }

    public static /* synthetic */ TemporalInterval copy$default(TemporalInterval temporalInterval, long j2, long j3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j2 = temporalInterval.startInclusive;
        }
        if ((i2 & 2) != 0) {
            j3 = temporalInterval.endExclusive;
        }
        return temporalInterval.copy(j2, j3);
    }

    @SerialName("end_exclusive")
    @Serializable(with = Rfc3339Deserializer.class)
    public static /* synthetic */ void getEndExclusive$annotations() {
    }

    @SerialName("start_inclusive")
    @Serializable(with = Rfc3339Deserializer.class)
    public static /* synthetic */ void getStartInclusive$annotations() {
    }

    @JvmStatic
    public static final void write$Self(@NotNull TemporalInterval self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        output.encodeSerializableElement(serialDesc, 0, new Rfc3339Deserializer(), Long.valueOf(self.startInclusive));
        output.encodeSerializableElement(serialDesc, 1, new Rfc3339Deserializer(), Long.valueOf(self.endExclusive));
    }

    public final long component1() {
        return this.startInclusive;
    }

    public final long component2() {
        return this.endExclusive;
    }

    @NotNull
    public final TemporalInterval copy(long j2, long j3) {
        return new TemporalInterval(j2, j3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TemporalInterval) {
            TemporalInterval temporalInterval = (TemporalInterval) obj;
            return this.startInclusive == temporalInterval.startInclusive && this.endExclusive == temporalInterval.endExclusive;
        }
        return false;
    }

    public final long getEndExclusive() {
        return this.endExclusive;
    }

    public final long getStartInclusive() {
        return this.startInclusive;
    }

    public int hashCode() {
        return (Long.hashCode(this.startInclusive) * 31) + Long.hashCode(this.endExclusive);
    }

    @NotNull
    public String toString() {
        return "TemporalInterval(startInclusive=" + this.startInclusive + ", endExclusive=" + this.endExclusive + ')';
    }
}
