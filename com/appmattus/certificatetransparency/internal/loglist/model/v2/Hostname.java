package com.appmattus.certificatetransparency.internal.loglist.model.v2;

import com.appmattus.certificatetransparency.internal.loglist.deserializer.HostnameDeserializer;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Serializable(with = HostnameDeserializer.class)
/* loaded from: classes.dex */
public final class Hostname {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String value;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<Hostname> serializer() {
            return new HostnameDeserializer();
        }
    }

    public Hostname(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        HttpUrl.Companion companion = HttpUrl.Companion;
        companion.get("http://" + value).host();
    }

    public static /* synthetic */ Hostname copy$default(Hostname hostname, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = hostname.value;
        }
        return hostname.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.value;
    }

    @NotNull
    public final Hostname copy(@NotNull String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new Hostname(value);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Hostname) && Intrinsics.areEqual(this.value, ((Hostname) obj).value);
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    @NotNull
    public String toString() {
        return "Hostname(value=" + this.value + ')';
    }
}
