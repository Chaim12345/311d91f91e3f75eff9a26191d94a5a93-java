package com.appmattus.certificatetransparency.internal.verifier.model;

import java.util.Arrays;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class Host {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String WILDCARD = "*.";
    @NotNull
    private final String canonicalHostname;
    private final boolean matchAll;
    @NotNull
    private final String pattern;
    private final boolean startsWithWildcard;

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Host(@NotNull String pattern) {
        boolean startsWith$default;
        HttpUrl.Companion companion;
        String str;
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        this.pattern = pattern;
        startsWith$default = StringsKt__StringsJVMKt.startsWith$default(pattern, WILDCARD, false, 2, null);
        this.startsWithWildcard = startsWith$default;
        this.matchAll = Intrinsics.areEqual(pattern, "*.*");
        if (startsWith$default) {
            companion = HttpUrl.Companion;
            StringBuilder sb = new StringBuilder();
            sb.append("http://");
            String substring = pattern.substring(2);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
            sb.append(substring);
            str = sb.toString();
        } else {
            companion = HttpUrl.Companion;
            str = "http://" + pattern;
        }
        this.canonicalHostname = companion.get(str).host();
    }

    private final String component1() {
        return this.pattern;
    }

    public static /* synthetic */ Host copy$default(Host host, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = host.pattern;
        }
        return host.copy(str);
    }

    @NotNull
    public final Host copy(@NotNull String pattern) {
        Intrinsics.checkNotNullParameter(pattern, "pattern");
        return new Host(pattern);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Host) {
            Host host = (Host) obj;
            if (Intrinsics.areEqual(this.canonicalHostname, host.canonicalHostname) && this.startsWithWildcard == host.startsWithWildcard) {
                return true;
            }
        }
        return false;
    }

    public final boolean getMatchAll() {
        return this.matchAll;
    }

    public final boolean getStartsWithWildcard() {
        return this.startsWithWildcard;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.canonicalHostname, Boolean.valueOf(this.startsWithWildcard)});
    }

    public final boolean matches(@NotNull String hostname) {
        int indexOf$default;
        boolean regionMatches;
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        if (this.startsWithWildcard) {
            indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) hostname, '.', 0, false, 6, (Object) null);
            if (this.matchAll) {
                return true;
            }
            if ((hostname.length() - indexOf$default) - 1 == this.canonicalHostname.length()) {
                String str = this.canonicalHostname;
                regionMatches = StringsKt__StringsJVMKt.regionMatches(hostname, indexOf$default + 1, str, 0, str.length(), false);
                if (regionMatches) {
                    return true;
                }
            }
            return false;
        }
        return Intrinsics.areEqual(hostname, this.canonicalHostname);
    }

    @NotNull
    public String toString() {
        return "Host(pattern=" + this.pattern + ')';
    }
}
