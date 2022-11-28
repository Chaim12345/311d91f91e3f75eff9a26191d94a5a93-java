package kotlinx.coroutines.channels;

import kotlin.PublishedApi;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.InternalCoroutinesApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@JvmInline
/* loaded from: classes3.dex */
public final class ChannelResult<T> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final Failed failed = new Failed();
    @Nullable
    private final Object holder;

    /* loaded from: classes3.dex */
    public static final class Closed extends Failed {
        @JvmField
        @Nullable
        public final Throwable cause;

        public Closed(@Nullable Throwable th) {
            this.cause = th;
        }

        public boolean equals(@Nullable Object obj) {
            return (obj instanceof Closed) && Intrinsics.areEqual(this.cause, ((Closed) obj).cause);
        }

        public int hashCode() {
            Throwable th = this.cause;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @Override // kotlinx.coroutines.channels.ChannelResult.Failed
        @NotNull
        public String toString() {
            return "Closed(" + this.cause + ')';
        }
    }

    @InternalCoroutinesApi
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @InternalCoroutinesApi
        @NotNull
        /* renamed from: closed-JP2dKIU  reason: not valid java name */
        public final <E> Object m1647closedJP2dKIU(@Nullable Throwable th) {
            return ChannelResult.m1635constructorimpl(new Closed(th));
        }

        @InternalCoroutinesApi
        @NotNull
        /* renamed from: failure-PtdJZtk  reason: not valid java name */
        public final <E> Object m1648failurePtdJZtk() {
            return ChannelResult.m1635constructorimpl(ChannelResult.failed);
        }

        @InternalCoroutinesApi
        @NotNull
        /* renamed from: success-JP2dKIU  reason: not valid java name */
        public final <E> Object m1649successJP2dKIU(E e2) {
            return ChannelResult.m1635constructorimpl(e2);
        }
    }

    /* loaded from: classes3.dex */
    public static class Failed {
        @NotNull
        public String toString() {
            return "Failed";
        }
    }

    @PublishedApi
    private /* synthetic */ ChannelResult(Object obj) {
        this.holder = obj;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ ChannelResult m1634boximpl(Object obj) {
        return new ChannelResult(obj);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static <T> Object m1635constructorimpl(@Nullable Object obj) {
        return obj;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m1636equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof ChannelResult) && Intrinsics.areEqual(obj, ((ChannelResult) obj2).m1646unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m1637equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    @Nullable
    /* renamed from: exceptionOrNull-impl  reason: not valid java name */
    public static final Throwable m1638exceptionOrNullimpl(Object obj) {
        Closed closed = obj instanceof Closed ? (Closed) obj : null;
        if (closed != null) {
            return closed.cause;
        }
        return null;
    }

    @PublishedApi
    public static /* synthetic */ void getHolder$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    /* renamed from: getOrNull-impl  reason: not valid java name */
    public static final T m1639getOrNullimpl(Object obj) {
        if (obj instanceof Failed) {
            return null;
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: getOrThrow-impl  reason: not valid java name */
    public static final T m1640getOrThrowimpl(Object obj) {
        Throwable th;
        if (obj instanceof Failed) {
            if (!(obj instanceof Closed) || (th = ((Closed) obj).cause) == null) {
                throw new IllegalStateException(("Trying to call 'getOrThrow' on a failed channel result: " + obj).toString());
            }
            throw th;
        }
        return obj;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m1641hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    /* renamed from: isClosed-impl  reason: not valid java name */
    public static final boolean m1642isClosedimpl(Object obj) {
        return obj instanceof Closed;
    }

    /* renamed from: isFailure-impl  reason: not valid java name */
    public static final boolean m1643isFailureimpl(Object obj) {
        return obj instanceof Failed;
    }

    /* renamed from: isSuccess-impl  reason: not valid java name */
    public static final boolean m1644isSuccessimpl(Object obj) {
        return !(obj instanceof Failed);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m1645toStringimpl(Object obj) {
        if (obj instanceof Closed) {
            return ((Closed) obj).toString();
        }
        return "Value(" + obj + ')';
    }

    public boolean equals(Object obj) {
        return m1636equalsimpl(this.holder, obj);
    }

    public int hashCode() {
        return m1641hashCodeimpl(this.holder);
    }

    @NotNull
    public String toString() {
        return m1645toStringimpl(this.holder);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Object m1646unboximpl() {
        return this.holder;
    }
}
