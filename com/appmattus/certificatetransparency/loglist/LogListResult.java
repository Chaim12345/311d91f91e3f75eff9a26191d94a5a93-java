package com.appmattus.certificatetransparency.loglist;

import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public abstract class LogListResult {

    /* loaded from: classes.dex */
    public static class Invalid extends LogListResult {
        public Invalid() {
            super(null);
        }
    }

    /* loaded from: classes.dex */
    public static final class Valid extends LogListResult {
        @NotNull
        private final List<LogServer> servers;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Valid(@NotNull List<LogServer> servers) {
            super(null);
            Intrinsics.checkNotNullParameter(servers, "servers");
            this.servers = servers;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Valid copy$default(Valid valid, List list, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                list = valid.servers;
            }
            return valid.copy(list);
        }

        @NotNull
        public final List<LogServer> component1() {
            return this.servers;
        }

        @NotNull
        public final Valid copy(@NotNull List<LogServer> servers) {
            Intrinsics.checkNotNullParameter(servers, "servers");
            return new Valid(servers);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Valid) && Intrinsics.areEqual(this.servers, ((Valid) obj).servers);
        }

        @NotNull
        public final List<LogServer> getServers() {
            return this.servers;
        }

        public int hashCode() {
            return this.servers.hashCode();
        }

        @NotNull
        public String toString() {
            return "Valid(servers=" + this.servers + ')';
        }
    }

    private LogListResult() {
    }

    public /* synthetic */ LogListResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
