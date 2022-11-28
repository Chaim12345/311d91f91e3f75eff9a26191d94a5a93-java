package com.psa.mym.mycitroenconnect.views.recycler_view;

import com.psa.mym.mycitroenconnect.views.recycler_view.SwipeHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt__IterablesKt;
/* loaded from: classes3.dex */
public final class SwipeHelperKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final float intrinsicWidth(List<SwipeHelper.UnderlayButton> list) {
        int collectionSizeOrDefault;
        if (list.isEmpty()) {
            return 0.0f;
        }
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (SwipeHelper.UnderlayButton underlayButton : list) {
            arrayList.add(Float.valueOf(underlayButton.getIntrinsicWidth()));
        }
        Iterator it = arrayList.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                next = Float.valueOf(((Number) next).floatValue() + ((Number) it.next()).floatValue());
            }
            return ((Number) next).floatValue();
        }
        throw new UnsupportedOperationException("Empty collection can't be reduced.");
    }
}
