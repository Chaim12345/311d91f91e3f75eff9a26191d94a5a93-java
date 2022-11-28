package org.hamcrest.collection;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.DescribedAs;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public class IsArrayWithSize<E> extends FeatureMatcher<E[], Integer> {
    public IsArrayWithSize(Matcher<? super Integer> matcher) {
        super(matcher, "an array with size", "array size");
    }

    public static <E> Matcher<E[]> arrayWithSize(int i2) {
        return arrayWithSize(IsEqual.equalTo(Integer.valueOf(i2)));
    }

    public static <E> Matcher<E[]> arrayWithSize(Matcher<? super Integer> matcher) {
        return new IsArrayWithSize(matcher);
    }

    public static <E> Matcher<E[]> emptyArray() {
        return DescribedAs.describedAs("an empty array", arrayWithSize(0), new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.FeatureMatcher
    /* renamed from: c */
    public Integer b(Object[] objArr) {
        return Integer.valueOf(objArr.length);
    }
}
