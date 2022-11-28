package org.hamcrest.collection;

import java.util.Iterator;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public class IsIterableWithSize<E> extends FeatureMatcher<Iterable<E>, Integer> {
    public IsIterableWithSize(Matcher<? super Integer> matcher) {
        super(matcher, "an iterable with size", "iterable size");
    }

    public static <E> Matcher<Iterable<E>> iterableWithSize(int i2) {
        return iterableWithSize(IsEqual.equalTo(Integer.valueOf(i2)));
    }

    public static <E> Matcher<Iterable<E>> iterableWithSize(Matcher<? super Integer> matcher) {
        return new IsIterableWithSize(matcher);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.FeatureMatcher
    /* renamed from: c */
    public Integer b(Iterable iterable) {
        Iterator it = iterable.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2++;
            it.next();
        }
        return Integer.valueOf(i2);
    }
}
