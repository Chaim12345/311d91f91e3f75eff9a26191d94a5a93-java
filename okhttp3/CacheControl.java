package okhttp3;

import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import okhttp3.internal.Util;
import org.apache.http.cookie.ClientCookie;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class CacheControl {
    @Nullable
    private String headerValue;
    private final boolean immutable;
    private final boolean isPrivate;
    private final boolean isPublic;
    private final int maxAgeSeconds;
    private final int maxStaleSeconds;
    private final int minFreshSeconds;
    private final boolean mustRevalidate;
    private final boolean noCache;
    private final boolean noStore;
    private final boolean noTransform;
    private final boolean onlyIfCached;
    private final int sMaxAgeSeconds;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
    @JvmField
    @NotNull
    public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();

    /* loaded from: classes3.dex */
    public static final class Builder {
        private boolean immutable;
        private int maxAgeSeconds = -1;
        private int maxStaleSeconds = -1;
        private int minFreshSeconds = -1;
        private boolean noCache;
        private boolean noStore;
        private boolean noTransform;
        private boolean onlyIfCached;

        private final int clampToInt(long j2) {
            if (j2 > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) j2;
        }

        @NotNull
        public final CacheControl build() {
            return new CacheControl(this.noCache, this.noStore, this.maxAgeSeconds, -1, false, false, false, this.maxStaleSeconds, this.minFreshSeconds, this.onlyIfCached, this.noTransform, this.immutable, null, null);
        }

        @NotNull
        public final Builder immutable() {
            this.immutable = true;
            return this;
        }

        @NotNull
        public final Builder maxAge(int i2, @NotNull TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (i2 >= 0) {
                this.maxAgeSeconds = clampToInt(timeUnit.toSeconds(i2));
                return this;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("maxAge < 0: ", Integer.valueOf(i2)).toString());
        }

        @NotNull
        public final Builder maxStale(int i2, @NotNull TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (i2 >= 0) {
                this.maxStaleSeconds = clampToInt(timeUnit.toSeconds(i2));
                return this;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("maxStale < 0: ", Integer.valueOf(i2)).toString());
        }

        @NotNull
        public final Builder minFresh(int i2, @NotNull TimeUnit timeUnit) {
            Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
            if (i2 >= 0) {
                this.minFreshSeconds = clampToInt(timeUnit.toSeconds(i2));
                return this;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("minFresh < 0: ", Integer.valueOf(i2)).toString());
        }

        @NotNull
        public final Builder noCache() {
            this.noCache = true;
            return this;
        }

        @NotNull
        public final Builder noStore() {
            this.noStore = true;
            return this;
        }

        @NotNull
        public final Builder noTransform() {
            this.noTransform = true;
            return this;
        }

        @NotNull
        public final Builder onlyIfCached() {
            this.onlyIfCached = true;
            return this;
        }
    }

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final int indexOfElement(String str, String str2, int i2) {
            boolean contains$default;
            int length = str.length();
            while (i2 < length) {
                int i3 = i2 + 1;
                contains$default = StringsKt__StringsKt.contains$default((CharSequence) str2, str.charAt(i2), false, 2, (Object) null);
                if (contains$default) {
                    return i2;
                }
                i2 = i3;
            }
            return str.length();
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x004c  */
        @JvmStatic
        @NotNull
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final CacheControl parse(@NotNull Headers headers) {
            boolean equals;
            boolean equals2;
            int i2;
            CharSequence trim;
            int i3;
            String str;
            boolean equals3;
            boolean equals4;
            boolean equals5;
            boolean equals6;
            boolean equals7;
            boolean equals8;
            boolean equals9;
            boolean equals10;
            boolean equals11;
            boolean equals12;
            boolean equals13;
            boolean equals14;
            CharSequence trim2;
            int indexOf$default;
            Headers headers2 = headers;
            Intrinsics.checkNotNullParameter(headers2, "headers");
            int size = headers.size();
            boolean z = true;
            boolean z2 = true;
            int i4 = 0;
            String str2 = null;
            boolean z3 = false;
            boolean z4 = false;
            int i5 = -1;
            int i6 = -1;
            boolean z5 = false;
            boolean z6 = false;
            boolean z7 = false;
            int i7 = -1;
            int i8 = -1;
            boolean z8 = false;
            boolean z9 = false;
            boolean z10 = false;
            while (i4 < size) {
                int i9 = i4 + 1;
                String name = headers2.name(i4);
                String value = headers2.value(i4);
                equals = StringsKt__StringsJVMKt.equals(name, "Cache-Control", z);
                if (!equals) {
                    equals2 = StringsKt__StringsJVMKt.equals(name, "Pragma", z);
                    if (!equals2) {
                        headers2 = headers;
                        i4 = i9;
                    }
                } else if (str2 == null) {
                    str2 = value;
                    i2 = 0;
                    while (i2 < value.length()) {
                        int indexOfElement = indexOfElement(value, "=,;", i2);
                        String substring = value.substring(i2, indexOfElement);
                        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                        trim = StringsKt__StringsKt.trim((CharSequence) substring);
                        String obj = trim.toString();
                        if (indexOfElement == value.length() || value.charAt(indexOfElement) == ',' || value.charAt(indexOfElement) == ';') {
                            i3 = indexOfElement + 1;
                            str = null;
                        } else {
                            int indexOfNonWhitespace = Util.indexOfNonWhitespace(value, indexOfElement + 1);
                            if (indexOfNonWhitespace >= value.length() || value.charAt(indexOfNonWhitespace) != '\"') {
                                i3 = indexOfElement(value, ",;", indexOfNonWhitespace);
                                String substring2 = value.substring(indexOfNonWhitespace, i3);
                                Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
                                trim2 = StringsKt__StringsKt.trim((CharSequence) substring2);
                                str = trim2.toString();
                            } else {
                                int i10 = indexOfNonWhitespace + 1;
                                indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) value, '\"', i10, false, 4, (Object) null);
                                str = value.substring(i10, indexOf$default);
                                Intrinsics.checkNotNullExpressionValue(str, "this as java.lang.String…ing(startIndex, endIndex)");
                                i3 = indexOf$default + 1;
                            }
                        }
                        z = true;
                        equals3 = StringsKt__StringsJVMKt.equals("no-cache", obj, true);
                        if (equals3) {
                            i2 = i3;
                            z3 = true;
                        } else {
                            equals4 = StringsKt__StringsJVMKt.equals("no-store", obj, true);
                            if (equals4) {
                                i2 = i3;
                                z4 = true;
                            } else {
                                equals5 = StringsKt__StringsJVMKt.equals(ClientCookie.MAX_AGE_ATTR, obj, true);
                                if (equals5) {
                                    i5 = Util.toNonNegativeInt(str, -1);
                                } else {
                                    equals6 = StringsKt__StringsJVMKt.equals("s-maxage", obj, true);
                                    if (equals6) {
                                        i6 = Util.toNonNegativeInt(str, -1);
                                    } else {
                                        equals7 = StringsKt__StringsJVMKt.equals("private", obj, true);
                                        if (equals7) {
                                            i2 = i3;
                                            z5 = true;
                                        } else {
                                            equals8 = StringsKt__StringsJVMKt.equals("public", obj, true);
                                            if (equals8) {
                                                i2 = i3;
                                                z6 = true;
                                            } else {
                                                equals9 = StringsKt__StringsJVMKt.equals("must-revalidate", obj, true);
                                                if (equals9) {
                                                    i2 = i3;
                                                    z7 = true;
                                                } else {
                                                    equals10 = StringsKt__StringsJVMKt.equals("max-stale", obj, true);
                                                    if (equals10) {
                                                        i7 = Util.toNonNegativeInt(str, Integer.MAX_VALUE);
                                                    } else {
                                                        equals11 = StringsKt__StringsJVMKt.equals("min-fresh", obj, true);
                                                        if (equals11) {
                                                            i8 = Util.toNonNegativeInt(str, -1);
                                                        } else {
                                                            equals12 = StringsKt__StringsJVMKt.equals("only-if-cached", obj, true);
                                                            if (equals12) {
                                                                i2 = i3;
                                                                z8 = true;
                                                            } else {
                                                                equals13 = StringsKt__StringsJVMKt.equals("no-transform", obj, true);
                                                                if (equals13) {
                                                                    i2 = i3;
                                                                    z9 = true;
                                                                } else {
                                                                    equals14 = StringsKt__StringsJVMKt.equals("immutable", obj, true);
                                                                    if (equals14) {
                                                                        i2 = i3;
                                                                        z10 = true;
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                i2 = i3;
                            }
                        }
                    }
                    headers2 = headers;
                    i4 = i9;
                }
                z2 = false;
                i2 = 0;
                while (i2 < value.length()) {
                }
                headers2 = headers;
                i4 = i9;
            }
            return new CacheControl(z3, z4, i5, i6, z5, z6, z7, i7, i8, z8, z9, z10, !z2 ? null : str2, null);
        }
    }

    private CacheControl(boolean z, boolean z2, int i2, int i3, boolean z3, boolean z4, boolean z5, int i4, int i5, boolean z6, boolean z7, boolean z8, String str) {
        this.noCache = z;
        this.noStore = z2;
        this.maxAgeSeconds = i2;
        this.sMaxAgeSeconds = i3;
        this.isPrivate = z3;
        this.isPublic = z4;
        this.mustRevalidate = z5;
        this.maxStaleSeconds = i4;
        this.minFreshSeconds = i5;
        this.onlyIfCached = z6;
        this.noTransform = z7;
        this.immutable = z8;
        this.headerValue = str;
    }

    public /* synthetic */ CacheControl(boolean z, boolean z2, int i2, int i3, boolean z3, boolean z4, boolean z5, int i4, int i5, boolean z6, boolean z7, boolean z8, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2, i2, i3, z3, z4, z5, i4, i5, z6, z7, z8, str);
    }

    @JvmStatic
    @NotNull
    public static final CacheControl parse(@NotNull Headers headers) {
        return Companion.parse(headers);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "immutable", imports = {}))
    @JvmName(name = "-deprecated_immutable")
    /* renamed from: -deprecated_immutable  reason: not valid java name */
    public final boolean m1705deprecated_immutable() {
        return this.immutable;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "maxAgeSeconds", imports = {}))
    @JvmName(name = "-deprecated_maxAgeSeconds")
    /* renamed from: -deprecated_maxAgeSeconds  reason: not valid java name */
    public final int m1706deprecated_maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "maxStaleSeconds", imports = {}))
    @JvmName(name = "-deprecated_maxStaleSeconds")
    /* renamed from: -deprecated_maxStaleSeconds  reason: not valid java name */
    public final int m1707deprecated_maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "minFreshSeconds", imports = {}))
    @JvmName(name = "-deprecated_minFreshSeconds")
    /* renamed from: -deprecated_minFreshSeconds  reason: not valid java name */
    public final int m1708deprecated_minFreshSeconds() {
        return this.minFreshSeconds;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "mustRevalidate", imports = {}))
    @JvmName(name = "-deprecated_mustRevalidate")
    /* renamed from: -deprecated_mustRevalidate  reason: not valid java name */
    public final boolean m1709deprecated_mustRevalidate() {
        return this.mustRevalidate;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noCache", imports = {}))
    @JvmName(name = "-deprecated_noCache")
    /* renamed from: -deprecated_noCache  reason: not valid java name */
    public final boolean m1710deprecated_noCache() {
        return this.noCache;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noStore", imports = {}))
    @JvmName(name = "-deprecated_noStore")
    /* renamed from: -deprecated_noStore  reason: not valid java name */
    public final boolean m1711deprecated_noStore() {
        return this.noStore;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "noTransform", imports = {}))
    @JvmName(name = "-deprecated_noTransform")
    /* renamed from: -deprecated_noTransform  reason: not valid java name */
    public final boolean m1712deprecated_noTransform() {
        return this.noTransform;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "onlyIfCached", imports = {}))
    @JvmName(name = "-deprecated_onlyIfCached")
    /* renamed from: -deprecated_onlyIfCached  reason: not valid java name */
    public final boolean m1713deprecated_onlyIfCached() {
        return this.onlyIfCached;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sMaxAgeSeconds", imports = {}))
    @JvmName(name = "-deprecated_sMaxAgeSeconds")
    /* renamed from: -deprecated_sMaxAgeSeconds  reason: not valid java name */
    public final int m1714deprecated_sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    @JvmName(name = "immutable")
    public final boolean immutable() {
        return this.immutable;
    }

    public final boolean isPrivate() {
        return this.isPrivate;
    }

    public final boolean isPublic() {
        return this.isPublic;
    }

    @JvmName(name = "maxAgeSeconds")
    public final int maxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    @JvmName(name = "maxStaleSeconds")
    public final int maxStaleSeconds() {
        return this.maxStaleSeconds;
    }

    @JvmName(name = "minFreshSeconds")
    public final int minFreshSeconds() {
        return this.minFreshSeconds;
    }

    @JvmName(name = "mustRevalidate")
    public final boolean mustRevalidate() {
        return this.mustRevalidate;
    }

    @JvmName(name = "noCache")
    public final boolean noCache() {
        return this.noCache;
    }

    @JvmName(name = "noStore")
    public final boolean noStore() {
        return this.noStore;
    }

    @JvmName(name = "noTransform")
    public final boolean noTransform() {
        return this.noTransform;
    }

    @JvmName(name = "onlyIfCached")
    public final boolean onlyIfCached() {
        return this.onlyIfCached;
    }

    @JvmName(name = "sMaxAgeSeconds")
    public final int sMaxAgeSeconds() {
        return this.sMaxAgeSeconds;
    }

    @NotNull
    public String toString() {
        String str = this.headerValue;
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            if (noCache()) {
                sb.append("no-cache, ");
            }
            if (noStore()) {
                sb.append("no-store, ");
            }
            if (maxAgeSeconds() != -1) {
                sb.append("max-age=");
                sb.append(maxAgeSeconds());
                sb.append(", ");
            }
            if (sMaxAgeSeconds() != -1) {
                sb.append("s-maxage=");
                sb.append(sMaxAgeSeconds());
                sb.append(", ");
            }
            if (isPrivate()) {
                sb.append("private, ");
            }
            if (isPublic()) {
                sb.append("public, ");
            }
            if (mustRevalidate()) {
                sb.append("must-revalidate, ");
            }
            if (maxStaleSeconds() != -1) {
                sb.append("max-stale=");
                sb.append(maxStaleSeconds());
                sb.append(", ");
            }
            if (minFreshSeconds() != -1) {
                sb.append("min-fresh=");
                sb.append(minFreshSeconds());
                sb.append(", ");
            }
            if (onlyIfCached()) {
                sb.append("only-if-cached, ");
            }
            if (noTransform()) {
                sb.append("no-transform, ");
            }
            if (immutable()) {
                sb.append("immutable, ");
            }
            if (sb.length() == 0) {
                return "";
            }
            sb.delete(sb.length() - 2, sb.length());
            String sb2 = sb.toString();
            Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
            this.headerValue = sb2;
            return sb2;
        }
        return str;
    }
}
