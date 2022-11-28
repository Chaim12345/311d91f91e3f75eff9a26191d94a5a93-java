package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import com.appmattus.certificatetransparency.internal.loglist.deserializer.HttpUrlDeserializer;
import com.appmattus.certificatetransparency.internal.loglist.deserializer.StateDeserializer;
import com.google.android.gms.common.internal.ImagesContract;
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
import kotlinx.serialization.internal.StringSerializer;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Serializable
/* loaded from: classes.dex */
public final class Log {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final String description;
    @Nullable
    private final Hostname dns;
    @NotNull
    private final String key;
    @NotNull
    private final String logId;
    @Nullable
    private final LogType logType;
    private final int maximumMergeDelay;
    @Nullable
    private final State state;
    @Nullable
    private final TemporalInterval temporalInterval;
    @NotNull
    private final HttpUrl url;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<Log> serializer() {
            return Log$$serializer.INSTANCE;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0088  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ Log(int i2, @SerialName("description") String str, @SerialName("key") String str2, @SerialName("log_id") String str3, @SerialName("mmd") int i3, @SerialName("url") @Serializable(with = HttpUrlDeserializer.class) HttpUrl httpUrl, @SerialName("dns") Hostname hostname, @SerialName("temporal_interval") TemporalInterval temporalInterval, @SerialName("log_type") LogType logType, @SerialName("state") @Serializable(with = StateDeserializer.class) State state, SerializationConstructorMarker serializationConstructorMarker) {
        boolean z;
        if (30 != (i2 & 30)) {
            PluginExceptionsKt.throwMissingFieldException(i2, 30, Log$$serializer.INSTANCE.getDescriptor());
        }
        if ((i2 & 1) == 0) {
            this.description = null;
        } else {
            this.description = str;
        }
        this.key = str2;
        this.logId = str3;
        this.maximumMergeDelay = i3;
        this.url = httpUrl;
        if ((i2 & 32) == 0) {
            this.dns = null;
        } else {
            this.dns = hostname;
        }
        if ((i2 & 64) == 0) {
            this.temporalInterval = null;
        } else {
            this.temporalInterval = temporalInterval;
        }
        if ((i2 & 128) == 0) {
            this.logType = null;
        } else {
            this.logType = logType;
        }
        if ((i2 & 256) == 0) {
            this.state = null;
        } else {
            this.state = state;
        }
        String str4 = this.description;
        if (str4 != null) {
            if (!(str4.length() > 0)) {
                z = false;
                if (z) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                if (!(str3.length() == 44)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                if (!(i3 >= 1)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                return;
            }
        }
        z = true;
        if (z) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0062  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Log(@Nullable String str, @NotNull String key, @NotNull String logId, int i2, @NotNull HttpUrl url, @Nullable Hostname hostname, @Nullable TemporalInterval temporalInterval, @Nullable LogType logType, @Nullable State state) {
        boolean z;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(logId, "logId");
        Intrinsics.checkNotNullParameter(url, "url");
        this.description = str;
        this.key = key;
        this.logId = logId;
        this.maximumMergeDelay = i2;
        this.url = url;
        this.dns = hostname;
        this.temporalInterval = temporalInterval;
        this.logType = logType;
        this.state = state;
        if (str != null) {
            if (!(str.length() > 0)) {
                z = false;
                if (z) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                if (!(logId.length() == 44)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                if (!(i2 >= 1)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                return;
            }
        }
        z = true;
        if (z) {
        }
    }

    public /* synthetic */ Log(String str, String str2, String str3, int i2, HttpUrl httpUrl, Hostname hostname, TemporalInterval temporalInterval, LogType logType, State state, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, str2, str3, i2, httpUrl, (i3 & 32) != 0 ? null : hostname, (i3 & 64) != 0 ? null : temporalInterval, (i3 & 128) != 0 ? null : logType, (i3 & 256) != 0 ? null : state);
    }

    @SerialName("description")
    public static /* synthetic */ void getDescription$annotations() {
    }

    @SerialName("dns")
    public static /* synthetic */ void getDns$annotations() {
    }

    @SerialName("key")
    public static /* synthetic */ void getKey$annotations() {
    }

    @SerialName("log_id")
    public static /* synthetic */ void getLogId$annotations() {
    }

    @SerialName("log_type")
    public static /* synthetic */ void getLogType$annotations() {
    }

    @SerialName("mmd")
    public static /* synthetic */ void getMaximumMergeDelay$annotations() {
    }

    @SerialName("state")
    @Serializable(with = StateDeserializer.class)
    public static /* synthetic */ void getState$annotations() {
    }

    @SerialName("temporal_interval")
    public static /* synthetic */ void getTemporalInterval$annotations() {
    }

    @SerialName(ImagesContract.URL)
    @Serializable(with = HttpUrlDeserializer.class)
    public static /* synthetic */ void getUrl$annotations() {
    }

    @JvmStatic
    public static final void write$Self(@NotNull Log self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.description != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.description);
        }
        output.encodeStringElement(serialDesc, 1, self.key);
        output.encodeStringElement(serialDesc, 2, self.logId);
        output.encodeIntElement(serialDesc, 3, self.maximumMergeDelay);
        output.encodeSerializableElement(serialDesc, 4, new HttpUrlDeserializer(), self.url);
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.dns != null) {
            output.encodeNullableSerializableElement(serialDesc, 5, Hostname.Companion.serializer(), self.dns);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.temporalInterval != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, TemporalInterval$$serializer.INSTANCE, self.temporalInterval);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.logType != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, LogType$$serializer.INSTANCE, self.logType);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.state != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, new StateDeserializer(), self.state);
        }
    }

    @Nullable
    public final String component1() {
        return this.description;
    }

    @NotNull
    public final String component2() {
        return this.key;
    }

    @NotNull
    public final String component3() {
        return this.logId;
    }

    public final int component4() {
        return this.maximumMergeDelay;
    }

    @NotNull
    public final HttpUrl component5() {
        return this.url;
    }

    @Nullable
    public final Hostname component6() {
        return this.dns;
    }

    @Nullable
    public final TemporalInterval component7() {
        return this.temporalInterval;
    }

    @Nullable
    public final LogType component8() {
        return this.logType;
    }

    @Nullable
    public final State component9() {
        return this.state;
    }

    @NotNull
    public final Log copy(@Nullable String str, @NotNull String key, @NotNull String logId, int i2, @NotNull HttpUrl url, @Nullable Hostname hostname, @Nullable TemporalInterval temporalInterval, @Nullable LogType logType, @Nullable State state) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(logId, "logId");
        Intrinsics.checkNotNullParameter(url, "url");
        return new Log(str, key, logId, i2, url, hostname, temporalInterval, logType, state);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Log) {
            Log log = (Log) obj;
            return Intrinsics.areEqual(this.description, log.description) && Intrinsics.areEqual(this.key, log.key) && Intrinsics.areEqual(this.logId, log.logId) && this.maximumMergeDelay == log.maximumMergeDelay && Intrinsics.areEqual(this.url, log.url) && Intrinsics.areEqual(this.dns, log.dns) && Intrinsics.areEqual(this.temporalInterval, log.temporalInterval) && this.logType == log.logType && Intrinsics.areEqual(this.state, log.state);
        }
        return false;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final Hostname getDns() {
        return this.dns;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    @NotNull
    public final String getLogId() {
        return this.logId;
    }

    @Nullable
    public final LogType getLogType() {
        return this.logType;
    }

    public final int getMaximumMergeDelay() {
        return this.maximumMergeDelay;
    }

    @Nullable
    public final State getState() {
        return this.state;
    }

    @Nullable
    public final TemporalInterval getTemporalInterval() {
        return this.temporalInterval;
    }

    @NotNull
    public final HttpUrl getUrl() {
        return this.url;
    }

    public int hashCode() {
        String str = this.description;
        int hashCode = (((((((((str == null ? 0 : str.hashCode()) * 31) + this.key.hashCode()) * 31) + this.logId.hashCode()) * 31) + Integer.hashCode(this.maximumMergeDelay)) * 31) + this.url.hashCode()) * 31;
        Hostname hostname = this.dns;
        int hashCode2 = (hashCode + (hostname == null ? 0 : hostname.hashCode())) * 31;
        TemporalInterval temporalInterval = this.temporalInterval;
        int hashCode3 = (hashCode2 + (temporalInterval == null ? 0 : temporalInterval.hashCode())) * 31;
        LogType logType = this.logType;
        int hashCode4 = (hashCode3 + (logType == null ? 0 : logType.hashCode())) * 31;
        State state = this.state;
        return hashCode4 + (state != null ? state.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "Log(description=" + this.description + ", key=" + this.key + ", logId=" + this.logId + ", maximumMergeDelay=" + this.maximumMergeDelay + ", url=" + this.url + ", dns=" + this.dns + ", temporalInterval=" + this.temporalInterval + ", logType=" + this.logType + ", state=" + this.state + ')';
    }
}
