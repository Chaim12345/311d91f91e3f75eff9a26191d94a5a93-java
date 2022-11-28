package com.psa.mym.mycitroenconnect.views.skeleton_layout;

import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt___RangesKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class BaseExtensionsKt {
    public static final boolean isAttachedToWindowCompat(@NotNull View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return ViewCompat.isAttachedToWindow(view);
    }

    public static final float refreshRateInSeconds(@NotNull Context context) {
        Display defaultDisplay;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("window");
        WindowManager windowManager = systemService instanceof WindowManager ? (WindowManager) systemService : null;
        if (windowManager == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null) {
            return 60.0f;
        }
        return defaultDisplay.getRefreshRate();
    }

    @NotNull
    public static final String tag(@NotNull Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        String simpleName = obj.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "javaClass.simpleName");
        return simpleName;
    }

    @NotNull
    public static final List<View> views(@NotNull ViewGroup viewGroup) {
        IntRange until;
        int collectionSizeOrDefault;
        Intrinsics.checkNotNullParameter(viewGroup, "<this>");
        until = RangesKt___RangesKt.until(0, viewGroup.getChildCount());
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(until, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        Iterator<Integer> it = until.iterator();
        while (it.hasNext()) {
            arrayList.add(viewGroup.getChildAt(((IntIterator) it).nextInt()));
        }
        return arrayList;
    }
}
