package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt__SequenceBuilderKt;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0015\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0001H\u0086\u0002\u001a\u0015\u0010\u0007\u001a\u00020\u0006*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\n\u001a\u00020\b*\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0003H\u0086\n\u001a\r\u0010\u000b\u001a\u00020\u0006*\u00020\u0000H\u0086\b\u001a\r\u0010\f\u001a\u00020\u0006*\u00020\u0000H\u0086\b\u001a3\u0010\u0011\u001a\u00020\b*\u00020\u00002!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\b0\rH\u0086\bø\u0001\u0000\u001aH\u0010\u0013\u001a\u00020\b*\u00020\u000026\u0010\u0010\u001a2\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0002\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\b0\u0012H\u0086\bø\u0001\u0000\u001a\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014*\u00020\u0000H\u0086\u0002\u001a\u0017\u0010\u0018\u001a\u00020\b*\u00020\u00162\b\b\u0001\u0010\u0017\u001a\u00020\u0001H\u0086\b\u001a5\u0010\u001d\u001a\u00020\b*\u00020\u00162\b\b\u0003\u0010\u0019\u001a\u00020\u00012\b\b\u0003\u0010\u001a\u001a\u00020\u00012\b\b\u0003\u0010\u001b\u001a\u00020\u00012\b\b\u0003\u0010\u001c\u001a\u00020\u0001H\u0086\b\u001a5\u0010 \u001a\u00020\b*\u00020\u00162\b\b\u0003\u0010\u001e\u001a\u00020\u00012\b\b\u0003\u0010\u001a\u001a\u00020\u00012\b\b\u0003\u0010\u001f\u001a\u00020\u00012\b\b\u0003\u0010\u001c\u001a\u00020\u0001H\u0087\b\"\u0018\u0010\u0017\u001a\u00020\u0001*\u00020\u00008Æ\u0002@\u0006¢\u0006\u0006\u001a\u0004\b!\u0010\"\"\u001d\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00030#*\u00020\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b$\u0010%\"\u001d\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00030#*\u00020\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b'\u0010%\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006)"}, d2 = {"Landroid/view/ViewGroup;", "", FirebaseAnalytics.Param.INDEX, "Landroid/view/View;", "get", "view", "", "contains", "", "plusAssign", "minusAssign", "isEmpty", "isNotEmpty", "Lkotlin/Function1;", "Lkotlin/ParameterName;", AppMeasurementSdk.ConditionalUserProperty.NAME, "action", "forEach", "Lkotlin/Function2;", "forEachIndexed", "", "iterator", "Landroid/view/ViewGroup$MarginLayoutParams;", "size", "setMargins", "left", "top", "right", "bottom", "updateMargins", "start", "end", "updateMarginsRelative", "getSize", "(Landroid/view/ViewGroup;)I", "Lkotlin/sequences/Sequence;", "getChildren", "(Landroid/view/ViewGroup;)Lkotlin/sequences/Sequence;", "children", "getDescendants", "descendants", "core-ktx_release"}, k = 2, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class ViewGroupKt {
    public static final boolean contains(@NotNull ViewGroup contains, @NotNull View view) {
        Intrinsics.checkNotNullParameter(contains, "$this$contains");
        Intrinsics.checkNotNullParameter(view, "view");
        return contains.indexOfChild(view) != -1;
    }

    public static final void forEach(@NotNull ViewGroup forEach, @NotNull Function1<? super View, Unit> action) {
        Intrinsics.checkNotNullParameter(forEach, "$this$forEach");
        Intrinsics.checkNotNullParameter(action, "action");
        int childCount = forEach.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = forEach.getChildAt(i2);
            Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(index)");
            action.invoke(childAt);
        }
    }

    public static final void forEachIndexed(@NotNull ViewGroup forEachIndexed, @NotNull Function2<? super Integer, ? super View, Unit> action) {
        Intrinsics.checkNotNullParameter(forEachIndexed, "$this$forEachIndexed");
        Intrinsics.checkNotNullParameter(action, "action");
        int childCount = forEachIndexed.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            Integer valueOf = Integer.valueOf(i2);
            View childAt = forEachIndexed.getChildAt(i2);
            Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(index)");
            action.invoke(valueOf, childAt);
        }
    }

    @NotNull
    public static final View get(@NotNull ViewGroup get, int i2) {
        Intrinsics.checkNotNullParameter(get, "$this$get");
        View childAt = get.getChildAt(i2);
        if (childAt != null) {
            return childAt;
        }
        throw new IndexOutOfBoundsException("Index: " + i2 + ", Size: " + get.getChildCount());
    }

    @NotNull
    public static final Sequence<View> getChildren(@NotNull final ViewGroup children) {
        Intrinsics.checkNotNullParameter(children, "$this$children");
        return new Sequence<View>() { // from class: androidx.core.view.ViewGroupKt$children$1
            @Override // kotlin.sequences.Sequence
            @NotNull
            public Iterator<View> iterator() {
                return ViewGroupKt.iterator(children);
            }
        };
    }

    @NotNull
    public static final Sequence<View> getDescendants(@NotNull ViewGroup descendants) {
        Sequence<View> sequence;
        Intrinsics.checkNotNullParameter(descendants, "$this$descendants");
        sequence = SequencesKt__SequenceBuilderKt.sequence(new ViewGroupKt$descendants$1(descendants, null));
        return sequence;
    }

    public static final int getSize(@NotNull ViewGroup size) {
        Intrinsics.checkNotNullParameter(size, "$this$size");
        return size.getChildCount();
    }

    public static final boolean isEmpty(@NotNull ViewGroup isEmpty) {
        Intrinsics.checkNotNullParameter(isEmpty, "$this$isEmpty");
        return isEmpty.getChildCount() == 0;
    }

    public static final boolean isNotEmpty(@NotNull ViewGroup isNotEmpty) {
        Intrinsics.checkNotNullParameter(isNotEmpty, "$this$isNotEmpty");
        return isNotEmpty.getChildCount() != 0;
    }

    @NotNull
    public static final Iterator<View> iterator(@NotNull ViewGroup iterator) {
        Intrinsics.checkNotNullParameter(iterator, "$this$iterator");
        return new ViewGroupKt$iterator$1(iterator);
    }

    public static final void minusAssign(@NotNull ViewGroup minusAssign, @NotNull View view) {
        Intrinsics.checkNotNullParameter(minusAssign, "$this$minusAssign");
        Intrinsics.checkNotNullParameter(view, "view");
        minusAssign.removeView(view);
    }

    public static final void plusAssign(@NotNull ViewGroup plusAssign, @NotNull View view) {
        Intrinsics.checkNotNullParameter(plusAssign, "$this$plusAssign");
        Intrinsics.checkNotNullParameter(view, "view");
        plusAssign.addView(view);
    }

    public static final void setMargins(@NotNull ViewGroup.MarginLayoutParams setMargins, @Px int i2) {
        Intrinsics.checkNotNullParameter(setMargins, "$this$setMargins");
        setMargins.setMargins(i2, i2, i2, i2);
    }

    public static final void updateMargins(@NotNull ViewGroup.MarginLayoutParams updateMargins, @Px int i2, @Px int i3, @Px int i4, @Px int i5) {
        Intrinsics.checkNotNullParameter(updateMargins, "$this$updateMargins");
        updateMargins.setMargins(i2, i3, i4, i5);
    }

    public static /* synthetic */ void updateMargins$default(ViewGroup.MarginLayoutParams updateMargins, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i2 = updateMargins.leftMargin;
        }
        if ((i6 & 2) != 0) {
            i3 = updateMargins.topMargin;
        }
        if ((i6 & 4) != 0) {
            i4 = updateMargins.rightMargin;
        }
        if ((i6 & 8) != 0) {
            i5 = updateMargins.bottomMargin;
        }
        Intrinsics.checkNotNullParameter(updateMargins, "$this$updateMargins");
        updateMargins.setMargins(i2, i3, i4, i5);
    }

    @RequiresApi(17)
    public static final void updateMarginsRelative(@NotNull ViewGroup.MarginLayoutParams updateMarginsRelative, @Px int i2, @Px int i3, @Px int i4, @Px int i5) {
        Intrinsics.checkNotNullParameter(updateMarginsRelative, "$this$updateMarginsRelative");
        updateMarginsRelative.setMarginStart(i2);
        updateMarginsRelative.topMargin = i3;
        updateMarginsRelative.setMarginEnd(i4);
        updateMarginsRelative.bottomMargin = i5;
    }

    public static /* synthetic */ void updateMarginsRelative$default(ViewGroup.MarginLayoutParams updateMarginsRelative, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            i2 = updateMarginsRelative.getMarginStart();
        }
        if ((i6 & 2) != 0) {
            i3 = updateMarginsRelative.topMargin;
        }
        if ((i6 & 4) != 0) {
            i4 = updateMarginsRelative.getMarginEnd();
        }
        if ((i6 & 8) != 0) {
            i5 = updateMarginsRelative.bottomMargin;
        }
        Intrinsics.checkNotNullParameter(updateMarginsRelative, "$this$updateMarginsRelative");
        updateMarginsRelative.setMarginStart(i2);
        updateMarginsRelative.topMargin = i3;
        updateMarginsRelative.setMarginEnd(i4);
        updateMarginsRelative.bottomMargin = i5;
    }
}
