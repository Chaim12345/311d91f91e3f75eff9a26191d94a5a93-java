package kotlinx.coroutines.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@JvmInline
/* loaded from: classes3.dex */
public final class InlineList<E> {
    @Nullable
    private final Object holder;

    private /* synthetic */ InlineList(Object obj) {
        this.holder = obj;
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ InlineList m1663boximpl(Object obj) {
        return new InlineList(obj);
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static <E> Object m1664constructorimpl(@Nullable Object obj) {
        return obj;
    }

    /* renamed from: constructor-impl$default  reason: not valid java name */
    public static /* synthetic */ Object m1665constructorimpl$default(Object obj, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 1) != 0) {
            obj = null;
        }
        return m1664constructorimpl(obj);
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m1666equalsimpl(Object obj, Object obj2) {
        return (obj2 instanceof InlineList) && Intrinsics.areEqual(obj, ((InlineList) obj2).m1672unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m1667equalsimpl0(Object obj, Object obj2) {
        return Intrinsics.areEqual(obj, obj2);
    }

    /* renamed from: forEachReversed-impl  reason: not valid java name */
    public static final void m1668forEachReversedimpl(Object obj, @NotNull Function1<? super E, Unit> function1) {
        if (obj == null) {
            return;
        }
        if (!(obj instanceof ArrayList)) {
            function1.invoke(obj);
            return;
        }
        ArrayList arrayList = (ArrayList) obj;
        int size = arrayList.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return;
            }
            function1.invoke((Object) arrayList.get(size));
        }
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m1669hashCodeimpl(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @NotNull
    /* renamed from: plus-FjFbRPM  reason: not valid java name */
    public static final Object m1670plusFjFbRPM(Object obj, E e2) {
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(e2 instanceof List))) {
            if (obj == null) {
                return m1664constructorimpl(e2);
            }
            if (obj instanceof ArrayList) {
                ((ArrayList) obj).add(e2);
                return m1664constructorimpl(obj);
            }
            ArrayList arrayList = new ArrayList(4);
            arrayList.add(obj);
            arrayList.add(e2);
            return m1664constructorimpl(arrayList);
        }
        throw new AssertionError();
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m1671toStringimpl(Object obj) {
        return "InlineList(holder=" + obj + ')';
    }

    public boolean equals(Object obj) {
        return m1666equalsimpl(this.holder, obj);
    }

    public int hashCode() {
        return m1669hashCodeimpl(this.holder);
    }

    public String toString() {
        return m1671toStringimpl(this.holder);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ Object m1672unboximpl() {
        return this.holder;
    }
}
