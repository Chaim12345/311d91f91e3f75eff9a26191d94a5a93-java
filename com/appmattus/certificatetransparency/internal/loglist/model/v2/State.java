package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import com.appmattus.certificatetransparency.internal.loglist.deserializer.Rfc3339Deserializer;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
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
public abstract class State {
    @NotNull
    private static final Lazy<KSerializer<Object>> $cachedSerializer$delegate;
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final /* synthetic */ Lazy get$cachedSerializer$delegate() {
            return State.$cachedSerializer$delegate;
        }

        @NotNull
        public final KSerializer<State> serializer() {
            return (KSerializer) get$cachedSerializer$delegate().getValue();
        }
    }

    @Serializable
    @SerialName(AppConstants.SECONDARY_USER_STATE_PENDING)
    /* loaded from: classes.dex */
    public static final class Pending extends State {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final long timestamp;

        /* loaded from: classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final KSerializer<Pending> serializer() {
                return State$Pending$$serializer.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Pending(int i2, @SerialName("timestamp") @Serializable(with = Rfc3339Deserializer.class) long j2, SerializationConstructorMarker serializationConstructorMarker) {
            super(i2, serializationConstructorMarker);
            if (1 != (i2 & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i2, 1, State$Pending$$serializer.INSTANCE.getDescriptor());
            }
            this.timestamp = j2;
        }

        public Pending(long j2) {
            super(null);
            this.timestamp = j2;
        }

        public static /* synthetic */ Pending copy$default(Pending pending, long j2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = pending.getTimestamp();
            }
            return pending.copy(j2);
        }

        @SerialName("timestamp")
        @Serializable(with = Rfc3339Deserializer.class)
        public static /* synthetic */ void getTimestamp$annotations() {
        }

        @JvmStatic
        public static final void write$Self(@NotNull Pending self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            State.write$Self(self, output, serialDesc);
            output.encodeSerializableElement(serialDesc, 0, new Rfc3339Deserializer(), Long.valueOf(self.getTimestamp()));
        }

        public final long component1() {
            return getTimestamp();
        }

        @NotNull
        public final Pending copy(long j2) {
            return new Pending(j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Pending) && getTimestamp() == ((Pending) obj).getTimestamp();
        }

        @Override // com.appmattus.certificatetransparency.internal.loglist.model.v2.State
        public long getTimestamp() {
            return this.timestamp;
        }

        public int hashCode() {
            return Long.hashCode(getTimestamp());
        }

        @NotNull
        public String toString() {
            return "Pending(timestamp=" + getTimestamp() + ')';
        }
    }

    @Serializable
    @SerialName("qualified")
    /* loaded from: classes.dex */
    public static final class Qualified extends State {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final long timestamp;

        /* loaded from: classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final KSerializer<Qualified> serializer() {
                return State$Qualified$$serializer.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Qualified(int i2, @SerialName("timestamp") @Serializable(with = Rfc3339Deserializer.class) long j2, SerializationConstructorMarker serializationConstructorMarker) {
            super(i2, serializationConstructorMarker);
            if (1 != (i2 & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i2, 1, State$Qualified$$serializer.INSTANCE.getDescriptor());
            }
            this.timestamp = j2;
        }

        public Qualified(long j2) {
            super(null);
            this.timestamp = j2;
        }

        public static /* synthetic */ Qualified copy$default(Qualified qualified, long j2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = qualified.getTimestamp();
            }
            return qualified.copy(j2);
        }

        @SerialName("timestamp")
        @Serializable(with = Rfc3339Deserializer.class)
        public static /* synthetic */ void getTimestamp$annotations() {
        }

        @JvmStatic
        public static final void write$Self(@NotNull Qualified self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            State.write$Self(self, output, serialDesc);
            output.encodeSerializableElement(serialDesc, 0, new Rfc3339Deserializer(), Long.valueOf(self.getTimestamp()));
        }

        public final long component1() {
            return getTimestamp();
        }

        @NotNull
        public final Qualified copy(long j2) {
            return new Qualified(j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Qualified) && getTimestamp() == ((Qualified) obj).getTimestamp();
        }

        @Override // com.appmattus.certificatetransparency.internal.loglist.model.v2.State
        public long getTimestamp() {
            return this.timestamp;
        }

        public int hashCode() {
            return Long.hashCode(getTimestamp());
        }

        @NotNull
        public String toString() {
            return "Qualified(timestamp=" + getTimestamp() + ')';
        }
    }

    @Serializable
    @SerialName("readonly")
    /* loaded from: classes.dex */
    public static final class ReadOnly extends State {
        @NotNull
        public static final Companion Companion = new Companion(null);
        @NotNull
        private final FinalTreeHead finalTreeHead;
        private final long timestamp;

        /* loaded from: classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final KSerializer<ReadOnly> serializer() {
                return State$ReadOnly$$serializer.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ ReadOnly(int i2, @SerialName("timestamp") @Serializable(with = Rfc3339Deserializer.class) long j2, @SerialName("final_tree_head") FinalTreeHead finalTreeHead, SerializationConstructorMarker serializationConstructorMarker) {
            super(i2, serializationConstructorMarker);
            if (3 != (i2 & 3)) {
                PluginExceptionsKt.throwMissingFieldException(i2, 3, State$ReadOnly$$serializer.INSTANCE.getDescriptor());
            }
            this.timestamp = j2;
            this.finalTreeHead = finalTreeHead;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReadOnly(long j2, @NotNull FinalTreeHead finalTreeHead) {
            super(null);
            Intrinsics.checkNotNullParameter(finalTreeHead, "finalTreeHead");
            this.timestamp = j2;
            this.finalTreeHead = finalTreeHead;
        }

        public static /* synthetic */ ReadOnly copy$default(ReadOnly readOnly, long j2, FinalTreeHead finalTreeHead, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = readOnly.getTimestamp();
            }
            if ((i2 & 2) != 0) {
                finalTreeHead = readOnly.finalTreeHead;
            }
            return readOnly.copy(j2, finalTreeHead);
        }

        @SerialName("final_tree_head")
        public static /* synthetic */ void getFinalTreeHead$annotations() {
        }

        @SerialName("timestamp")
        @Serializable(with = Rfc3339Deserializer.class)
        public static /* synthetic */ void getTimestamp$annotations() {
        }

        @JvmStatic
        public static final void write$Self(@NotNull ReadOnly self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            State.write$Self(self, output, serialDesc);
            output.encodeSerializableElement(serialDesc, 0, new Rfc3339Deserializer(), Long.valueOf(self.getTimestamp()));
            output.encodeSerializableElement(serialDesc, 1, FinalTreeHead$$serializer.INSTANCE, self.finalTreeHead);
        }

        public final long component1() {
            return getTimestamp();
        }

        @NotNull
        public final FinalTreeHead component2() {
            return this.finalTreeHead;
        }

        @NotNull
        public final ReadOnly copy(long j2, @NotNull FinalTreeHead finalTreeHead) {
            Intrinsics.checkNotNullParameter(finalTreeHead, "finalTreeHead");
            return new ReadOnly(j2, finalTreeHead);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof ReadOnly) {
                ReadOnly readOnly = (ReadOnly) obj;
                return getTimestamp() == readOnly.getTimestamp() && Intrinsics.areEqual(this.finalTreeHead, readOnly.finalTreeHead);
            }
            return false;
        }

        @NotNull
        public final FinalTreeHead getFinalTreeHead() {
            return this.finalTreeHead;
        }

        @Override // com.appmattus.certificatetransparency.internal.loglist.model.v2.State
        public long getTimestamp() {
            return this.timestamp;
        }

        public int hashCode() {
            return (Long.hashCode(getTimestamp()) * 31) + this.finalTreeHead.hashCode();
        }

        @NotNull
        public String toString() {
            return "ReadOnly(timestamp=" + getTimestamp() + ", finalTreeHead=" + this.finalTreeHead + ')';
        }
    }

    @Serializable
    @SerialName("rejected")
    /* loaded from: classes.dex */
    public static final class Rejected extends State {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final long timestamp;

        /* loaded from: classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final KSerializer<Rejected> serializer() {
                return State$Rejected$$serializer.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Rejected(int i2, @SerialName("timestamp") @Serializable(with = Rfc3339Deserializer.class) long j2, SerializationConstructorMarker serializationConstructorMarker) {
            super(i2, serializationConstructorMarker);
            if (1 != (i2 & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i2, 1, State$Rejected$$serializer.INSTANCE.getDescriptor());
            }
            this.timestamp = j2;
        }

        public Rejected(long j2) {
            super(null);
            this.timestamp = j2;
        }

        public static /* synthetic */ Rejected copy$default(Rejected rejected, long j2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = rejected.getTimestamp();
            }
            return rejected.copy(j2);
        }

        @SerialName("timestamp")
        @Serializable(with = Rfc3339Deserializer.class)
        public static /* synthetic */ void getTimestamp$annotations() {
        }

        @JvmStatic
        public static final void write$Self(@NotNull Rejected self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            State.write$Self(self, output, serialDesc);
            output.encodeSerializableElement(serialDesc, 0, new Rfc3339Deserializer(), Long.valueOf(self.getTimestamp()));
        }

        public final long component1() {
            return getTimestamp();
        }

        @NotNull
        public final Rejected copy(long j2) {
            return new Rejected(j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Rejected) && getTimestamp() == ((Rejected) obj).getTimestamp();
        }

        @Override // com.appmattus.certificatetransparency.internal.loglist.model.v2.State
        public long getTimestamp() {
            return this.timestamp;
        }

        public int hashCode() {
            return Long.hashCode(getTimestamp());
        }

        @NotNull
        public String toString() {
            return "Rejected(timestamp=" + getTimestamp() + ')';
        }
    }

    @Serializable
    @SerialName("retired")
    /* loaded from: classes.dex */
    public static final class Retired extends State {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final long timestamp;

        /* loaded from: classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final KSerializer<Retired> serializer() {
                return State$Retired$$serializer.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Retired(int i2, @SerialName("timestamp") @Serializable(with = Rfc3339Deserializer.class) long j2, SerializationConstructorMarker serializationConstructorMarker) {
            super(i2, serializationConstructorMarker);
            if (1 != (i2 & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i2, 1, State$Retired$$serializer.INSTANCE.getDescriptor());
            }
            this.timestamp = j2;
        }

        public Retired(long j2) {
            super(null);
            this.timestamp = j2;
        }

        public static /* synthetic */ Retired copy$default(Retired retired, long j2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = retired.getTimestamp();
            }
            return retired.copy(j2);
        }

        @SerialName("timestamp")
        @Serializable(with = Rfc3339Deserializer.class)
        public static /* synthetic */ void getTimestamp$annotations() {
        }

        @JvmStatic
        public static final void write$Self(@NotNull Retired self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            State.write$Self(self, output, serialDesc);
            output.encodeSerializableElement(serialDesc, 0, new Rfc3339Deserializer(), Long.valueOf(self.getTimestamp()));
        }

        public final long component1() {
            return getTimestamp();
        }

        @NotNull
        public final Retired copy(long j2) {
            return new Retired(j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Retired) && getTimestamp() == ((Retired) obj).getTimestamp();
        }

        @Override // com.appmattus.certificatetransparency.internal.loglist.model.v2.State
        public long getTimestamp() {
            return this.timestamp;
        }

        public int hashCode() {
            return Long.hashCode(getTimestamp());
        }

        @NotNull
        public String toString() {
            return "Retired(timestamp=" + getTimestamp() + ')';
        }
    }

    @Serializable
    @SerialName("usable")
    /* loaded from: classes.dex */
    public static final class Usable extends State {
        @NotNull
        public static final Companion Companion = new Companion(null);
        private final long timestamp;

        /* loaded from: classes.dex */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final KSerializer<Usable> serializer() {
                return State$Usable$$serializer.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
        public /* synthetic */ Usable(int i2, @SerialName("timestamp") @Serializable(with = Rfc3339Deserializer.class) long j2, SerializationConstructorMarker serializationConstructorMarker) {
            super(i2, serializationConstructorMarker);
            if (1 != (i2 & 1)) {
                PluginExceptionsKt.throwMissingFieldException(i2, 1, State$Usable$$serializer.INSTANCE.getDescriptor());
            }
            this.timestamp = j2;
        }

        public Usable(long j2) {
            super(null);
            this.timestamp = j2;
        }

        public static /* synthetic */ Usable copy$default(Usable usable, long j2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                j2 = usable.getTimestamp();
            }
            return usable.copy(j2);
        }

        @SerialName("timestamp")
        @Serializable(with = Rfc3339Deserializer.class)
        public static /* synthetic */ void getTimestamp$annotations() {
        }

        @JvmStatic
        public static final void write$Self(@NotNull Usable self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
            Intrinsics.checkNotNullParameter(self, "self");
            Intrinsics.checkNotNullParameter(output, "output");
            Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
            State.write$Self(self, output, serialDesc);
            output.encodeSerializableElement(serialDesc, 0, new Rfc3339Deserializer(), Long.valueOf(self.getTimestamp()));
        }

        public final long component1() {
            return getTimestamp();
        }

        @NotNull
        public final Usable copy(long j2) {
            return new Usable(j2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Usable) && getTimestamp() == ((Usable) obj).getTimestamp();
        }

        @Override // com.appmattus.certificatetransparency.internal.loglist.model.v2.State
        public long getTimestamp() {
            return this.timestamp;
        }

        public int hashCode() {
            return Long.hashCode(getTimestamp());
        }

        @NotNull
        public String toString() {
            return "Usable(timestamp=" + getTimestamp() + ')';
        }
    }

    static {
        Lazy<KSerializer<Object>> lazy;
        lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) State$Companion$$cachedSerializer$delegate$1.INSTANCE);
        $cachedSerializer$delegate = lazy;
    }

    private State() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ State(int i2, SerializationConstructorMarker serializationConstructorMarker) {
    }

    public /* synthetic */ State(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    public static final void write$Self(@NotNull State self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
    }

    public abstract long getTimestamp();
}
