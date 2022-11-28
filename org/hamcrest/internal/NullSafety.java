package org.hamcrest.internal;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsNull;
/* loaded from: classes4.dex */
public class NullSafety {
    public static <E> List<Matcher<? super E>> nullSafe(Matcher<? super E>[] matcherArr) {
        ArrayList arrayList = new ArrayList(matcherArr.length);
        for (Matcher<? super E> matcher : matcherArr) {
            if (matcher == null) {
                matcher = IsNull.nullValue();
            }
            arrayList.add(matcher);
        }
        return arrayList;
    }
}
