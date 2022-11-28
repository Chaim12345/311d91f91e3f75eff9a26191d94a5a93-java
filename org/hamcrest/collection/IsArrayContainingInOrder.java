package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public class IsArrayContainingInOrder<E> extends TypeSafeMatcher<E[]> {
    private final IsIterableContainingInOrder<E> iterableMatcher;
    private final Collection<Matcher<? super E>> matchers;

    public IsArrayContainingInOrder(List<Matcher<? super E>> list) {
        this.iterableMatcher = new IsIterableContainingInOrder<>(list);
        this.matchers = list;
    }

    public static <E> Matcher<E[]> arrayContaining(List<Matcher<? super E>> list) {
        return new IsArrayContainingInOrder(list);
    }

    public static <E> Matcher<E[]> arrayContaining(E... eArr) {
        ArrayList arrayList = new ArrayList();
        for (E e2 : eArr) {
            arrayList.add(IsEqual.equalTo(e2));
        }
        return arrayContaining(arrayList);
    }

    public static <E> Matcher<E[]> arrayContaining(Matcher<? super E>... matcherArr) {
        return arrayContaining(Arrays.asList(matcherArr));
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(E[] eArr, Description description) {
        this.iterableMatcher.describeMismatch(Arrays.asList(eArr), description);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendList("[", ", ", "]", this.matchers);
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(E[] eArr) {
        return this.iterableMatcher.matches(Arrays.asList(eArr));
    }
}
