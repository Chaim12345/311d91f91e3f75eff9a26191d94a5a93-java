package com.appmattus.certificatetransparency.loglist;

import com.appmattus.certificatetransparency.internal.utils.PublicKeyExtKt;
import java.security.PublicKey;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class LogServer {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final byte[] id;
    @NotNull
    private final PublicKey key;
    @Nullable
    private final Long validUntil;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public LogServer(@NotNull PublicKey key, @Nullable Long l2) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.key = key;
        this.validUntil = l2;
        this.id = PublicKeyExtKt.sha256Hash(key);
    }

    public /* synthetic */ LogServer(PublicKey publicKey, Long l2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(publicKey, (i2 & 2) != 0 ? null : l2);
    }

    public static /* synthetic */ LogServer copy$default(LogServer logServer, PublicKey publicKey, Long l2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            publicKey = logServer.key;
        }
        if ((i2 & 2) != 0) {
            l2 = logServer.validUntil;
        }
        return logServer.copy(publicKey, l2);
    }

    @NotNull
    public final PublicKey component1() {
        return this.key;
    }

    @Nullable
    public final Long component2() {
        return this.validUntil;
    }

    @NotNull
    public final LogServer copy(@NotNull PublicKey key, @Nullable Long l2) {
        Intrinsics.checkNotNullParameter(key, "key");
        return new LogServer(key, l2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof LogServer) {
            LogServer logServer = (LogServer) obj;
            return Intrinsics.areEqual(this.key, logServer.key) && Intrinsics.areEqual(this.validUntil, logServer.validUntil);
        }
        return false;
    }

    @NotNull
    public final byte[] getId() {
        return this.id;
    }

    @NotNull
    public final PublicKey getKey() {
        return this.key;
    }

    @Nullable
    public final Long getValidUntil() {
        return this.validUntil;
    }

    public int hashCode() {
        int hashCode = this.key.hashCode() * 31;
        Long l2 = this.validUntil;
        return hashCode + (l2 == null ? 0 : l2.hashCode());
    }

    @NotNull
    public String toString() {
        return "LogServer(key=" + this.key + ", validUntil=" + this.validUntil + ')';
    }
}
