package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsEqual;
@Deprecated
/* loaded from: classes4.dex */
public class IsArrayContainingInAnyOrder<E> extends TypeSafeMatcher<E[]> {
    private final IsIterableContainingInAnyOrder<E> iterableMatcher;
    private final Collection<Matcher<? super E>> matchers;

    public IsArrayContainingInAnyOrder(Collection<Matcher<? super E>> collection) {
        this.iterableMatcher = new IsIterableContainingInAnyOrder<>(collection);
        this.matchers = collection;
    }

    public static <E> Matcher<E[]> arrayContainingInAnyOrder(Collection<Matcher<? super E>> collection) {
        return new IsArrayContainingInAnyOrder(collection);
    }

    public static <E> Matcher<E[]> arrayContainingInAnyOrder(E... eArr) {
        ArrayList arrayList = new ArrayList();
        for (E e2 : eArr) {
            arrayList.add(IsEqual.equalTo(e2));
        }
        return new IsArrayContainingInAnyOrder(arrayList);
    }

    public static <E> Matcher<E[]> arrayContainingInAnyOrder(Matcher<? super E>... matcherArr) {
        return arrayContainingInAnyOrder(Arrays.asList(matcherArr));
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(E[] eArr, Description description) {
        this.iterableMatcher.describeMismatch(Arrays.asList(eArr), description);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendList("[", ", ", "]", this.matchers).appendText(" in any order");
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(E[] eArr) {
        return this.iterableMatcher.matches(Arrays.asList(eArr));
    }
}
